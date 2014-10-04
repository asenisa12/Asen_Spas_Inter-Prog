package elsys.hw.sockets;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TimeServer {
	
	public static int SERVER_PORT = 44012;
	
	private class ClientHandler extends Thread{
		private Socket cliSock;
		
		public ClientHandler(Socket socket) {
			this.cliSock = socket;
		}
		
		@Override
		public void run() {
			try{
				handleClient();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		
		private void handleClient() throws IOException{
			InputStream inStream = cliSock.getInputStream();
			OutputStream outStream = cliSock.getOutputStream();
			
			InputStreamReader inputStreamReader = new InputStreamReader(inStream);
			BufferedReader in = new BufferedReader(inputStreamReader);
			PrintWriter out =  new PrintWriter(outStream);
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date;
			String response = null;
			try {
				date = formatter.parse(in.readLine());
				Date today = new Date();
				int diffInDays = (int)( (date.getTime() - today.getTime()) 
						/ (1000 * 60 * 60 * 24) );
				response = "The diffrence is: "+diffInDays;
			} catch (ParseException e) {
				response = "Err Cannot prase date!!!";
			}
			
			
			out.println(response);
			out.flush();
			
			out.close();
		}
		
	}
	
	public static void main(String[] args) throws IOException{
		System.out.println("The server is started...");
		new TimeServer().run();
	}
	public void run() throws IOException{
		ServerSocket sc =  new ServerSocket(SERVER_PORT);
		int i=0;
		while(i<5){
			i++;
			final Socket clientSock = sc.accept();
			
			new ClientHandler(clientSock).start();
		}
		sc.close();
	}
	
}
