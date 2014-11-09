package elsys.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class HttpGet {
	private static String HTTP_METHOD = "GET";
	private static int HTTP_PORT = 80;
	private static String HTTP_VERSION = "HTTP/1.1";
	
	public class HttpResponse{
			// should be in lower case
			private static final String HEADER_CONTENT_LENGTH = "content-length";
			private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 10;
			private String statusLine;
			private final List<HttpHeader> headers = new LinkedList<HttpHeader>();
			private char[] body;
			
			public String getStatusLine() {
				return statusLine;
			}
			
			public void setStatusLine(String statusLine) {
				this.statusLine = statusLine;
			}
			
			public List<HttpHeader> getHeaders() {
				return headers;
			}
			
			
			public char[] getBody(BufferedReader in) throws IOException {	
				if (body == null) {
					body = new char[getContentLength()];
				}
				in.read(body);
				
				return body;
			}

			private int getContentLength() {
				for (HttpHeader next : headers) {
					if (next.getName().toLowerCase().equals(HEADER_CONTENT_LENGTH)) {
						final int result = Integer.parseInt(next.getValue());
						return Math.min(MAX_REQUEST_SIZE, result);
					}
				}
				return 0;
			}
			

	};
	public static void main(String[] args) throws UnknownHostException, IOException {
		final HttpGet example = new HttpGet();
		boolean quit = false;
		
		final InputStream inputStream = System.in;
		
		final InputStreamReader inputStreamReader = 
				new InputStreamReader(inputStream);
		final BufferedReader in = new BufferedReader(inputStreamReader);
		
		while(!quit){
			System.out.println("Input host: ");
			String host = in.readLine();
			System.out.println("Input path: ");
			String path = in.readLine();
			
			quit = example.createRequest(host,
					HTTP_METHOD, path);
		}
		

	}
	
	public boolean createRequest(String host, String method, String path) throws UnknownHostException, IOException{
		
		Socket clientSocket = new Socket(host, HTTP_PORT);
		final InputStream inputStream = clientSocket.getInputStream();
		final OutputStream outputStream = clientSocket.getOutputStream();
		
		final InputStreamReader inputStreamReader = 
				new InputStreamReader(inputStream);
		final BufferedReader in = new BufferedReader(inputStreamReader);
		final PrintWriter out =  new PrintWriter(outputStream);
		
		writeRequest(out, host, method, path);
		out.flush();
		
		boolean success = parseResponse(in);
		clientSocket.close();
		return success;
		
	}
	private boolean parseResponse(BufferedReader in) throws IOException{
		final HttpResponse result = new HttpResponse();

		String statusline = in.readLine();
		result.setStatusLine(statusline);
		
		Pattern p = Pattern.compile("\\d\\d\\d");
		Matcher m = p.matcher(statusline);
		
		while(m.find()){
			int statuscode = Integer.parseInt(m.group());
			System.out.println(statuscode);
			if(statuscode<300){
				System.out.println(statusline);
				String next;
				while(!(next = in.readLine()).equals("")) {
					System.out.println(next);
					result.getHeaders().add(HttpHeader.createFromHeaderLine(next));
				}
				System.out.println(result.getBody(in));
				break;
			}else if(statuscode>=300){
				System.out.println("Error! Status code :"+statuscode);
				return false;
			}
		}
		
		return true;
	}
	
	private void writeRequest(PrintWriter out, String host, 
			String method, String path){
		out.printf("%s %s %s\n", method, path, HTTP_VERSION);
		out.printf("Host: %s\n", host);
		out.printf("\n");
	}
}
