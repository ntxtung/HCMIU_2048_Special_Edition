package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Arrays;

public class PlayerThread extends Thread {
	
	private int clientNumber;
	private Socket socket;
	private boolean isLoggedIn = false;
	
	private BufferedReader is;
	private BufferedWriter os;
	
	public PlayerThread(Socket socket, int clientNumber) {
		this.clientNumber = clientNumber;
		this.socket = socket;
		
		System.out.println("New connection #" + clientNumber + " at thread " + this);
	}
	
	@Override
	public void run() {
		try {
			is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			os = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
	
			String msg = "";
			while (true) {
				msg = is.readLine();
				System.out.println(">>" + msg);
				
				os.write(">>" + msg);
				os.newLine();
				
				os.flush();
				
				String[] subStr = null;
				subStr = msg.split("@@");
				System.out.println(Arrays.toString(subStr));
				
				if (subStr[0].equals("login"))
					if (Login.getInstance().isLoginValid(subStr[1], subStr[2]) == true) { //login successfully
						feedback("LOGIN SUCCESSFULLY");
					} else 
						feedback("INVALID LOGIN");
					//System.out.println("Try to log in!");
				
				
				if (msg.equals("QUIT")) {
					feedback(">> OK closing");
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
	
	private boolean feedback(String feedbackStr) {
		try {
			if (os != null) {
				os.write(feedbackStr);
				os.newLine();
				os.flush();
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
} 
