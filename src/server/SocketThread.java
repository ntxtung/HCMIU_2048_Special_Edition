package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

public class SocketThread extends Thread {
	protected Socket socket;

	public SocketThread(Socket clientSocket) {
		this.socket = clientSocket;
	}

	public void run() {
		BufferedReader is = null;
		BufferedWriter os = null;
		try {
			is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			os = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String line;
		while (true) {
			try {
				line = is.readLine();
				if ((line == null) || line.equals("QUIT")) {
					socket.close();
					return;
				}
				else {
				System.out.println("MSG from "+socket.getInetAddress()+": " +line);
					os.write("Feedback >>" +line);
					os.newLine();
					Thread.sleep(2000);
					os.flush();
				}
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
				return;
			}
		}
	}
}
