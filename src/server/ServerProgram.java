package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerProgram {
	
	private static int port = 2048;
	
	public static void main(String[] args) {
		
		ServerSocket listener = null;
		
		System.out.println("<<< DSA2048 SERVER >>>");
		System.out.println("Waiting to accept player...");
		
		int clientNumber = 0;
		
//		System.out.println(Cryptography.encrypt("2048"));
//		System.out.println(Cryptography.decrypt("‰‘…»XÔ±f“_≥∫÷f"));
		
		Login.getInstance();
		RoomContainer.getInstance();
		PlayerContainer.getInstance();
		
		RoomContainer.getInstance().createNewRoom("aaaaaa", 2);
		RoomContainer.getInstance().createNewRoom("dsfssdf", 3);
		RoomContainer.getInstance().createNewRoom("asdwe", 4);
		RoomContainer.getInstance().createNewRoom("asdcx", 6);
		RoomContainer.getInstance().createNewRoom("qwe", 8);
			
		try {
			listener = new ServerSocket(port);
		} catch (IOException e) {
			System.out.println("[ERROR] " + e.getMessage());
			System.exit(1);
		}
		
		try {
			while (true) {
				Socket socket = listener.accept();
				PlayerThread handleThread = new PlayerThread(socket, clientNumber++);
				handleThread.start();
			}
		} catch( IOException e) {
			
		}
	}
}
