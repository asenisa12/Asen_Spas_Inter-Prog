package elsys.hw.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class TimeClient {
	private static final String SERVER = "localhost";
	public static int SERVER_PORT = 44012;
	private Socket cliSock;
	
	public TimeClient() throws UnknownHostException, IOException {
		this.cliSock = new Socket(SERVER,SERVER_PORT);
	}
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		System.out.println("Enter date: ");
		TimeClient client= new TimeClient(); 
		InputStream input = System.in;
		InputStreamReader inputStreamReader = new InputStreamReader(input);
		BufferedReader in = new BufferedReader(inputStreamReader);
		
		client.send(in.readLine());
		System.out.println(client.getMessage());
		
	}
	public void send(String mess) throws IOException{
		OutputStream outStream = cliSock.getOutputStream();
		PrintWriter out = new PrintWriter(outStream);
		 out.println(mess);
		 out.flush();
		
	}
	
	public String getMessage() throws IOException{
		InputStream inputStream = cliSock.getInputStream();
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		BufferedReader in = new BufferedReader(inputStreamReader);
		
		String response = in.readLine();
		cliSock.close();
		return response;
	}
}
