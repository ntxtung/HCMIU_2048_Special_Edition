package socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
	
	private static ServerSocket listener = null;
	private static String line;
	private static BufferedReader is;
	private static BufferedWriter os;
	private static Socket socketOfServer = null;
	
	public static boolean startServer(int port) {
		try {
			listener = new ServerSocket(port);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return false;
		}
		
		try {
			System.out.println("Started server successfully! Waiting for player...");
			socketOfServer = listener.accept();
			is = new BufferedReader(new InputStreamReader(socketOfServer.getInputStream()));
			os = new BufferedWriter(new OutputStreamWriter(socketOfServer.getOutputStream()));
			
			while (true) {
				line = is.readLine();
				line = line.toUpperCase();
				
				os.write(line);
				os.newLine();
				os.flush();
				
				if (line.equals("QUIT")) {
					break;
				}
			}
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return true;
	}
	
	public static void main(String[] args) {
		startServer(9000);
	}
}
