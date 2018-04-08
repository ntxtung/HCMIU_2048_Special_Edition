package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class PlayerThread extends Thread {
	
	private int clientNumber;
	private Socket socket;
	
	public PlayerThread(Socket socket, int clientNumber) {
		this.clientNumber = clientNumber;
		this.socket = socket;
		
		System.out.println("New connection #" + clientNumber + " at thread " + this);
	}
	
	@Override
	public void run() {
		try {
			BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedWriter os = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			String msg = "";
			while (true) {
				msg = is.readLine();
				
				os.write(">>" + msg);
				os.newLine();
				
				os.flush();
				
				if (msg.equals("QUIT")) {
					os.write(">> OK");
                    os.newLine();
                    os.flush();
                    System.exit(0);
                    break;
				}
			}
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
			System.out.println("Perhap the player had disconnected!");
		}
	}
}
