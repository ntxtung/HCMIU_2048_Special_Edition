package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

	static final int PORT = 1998;

	public static void start() {
		ServerSocket serverSocket = null;
		Socket socket = null;

		try {
			serverSocket = new ServerSocket(PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}

		while (true) {
			try {
				socket = serverSocket.accept();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				SocketThread clientThread = new SocketThread(socket);
				clientThread.setDaemon(true);
				clientThread.start();
				System.out.println("Connected to " + socket.getInetAddress() + " on THREAD #" + clientThread.getId());
			}
		}
	}
	
	
	
	public static void main(String args[]) {
		start();
	}
}
