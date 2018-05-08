package game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientSocket {

	private static ClientSocket instance = null;

	private static String hostname = "169.254.83.106";
	private static int port = 2048;
	private static Socket socket = null;
	private static BufferedWriter os = null;
	private static BufferedReader is = null;

	private static ClientCallbacker clientCallbacker;

	// public static void main(String[] args) {
	// connect();
	// String msg = "";
	// Scanner sc = new Scanner(System.in);
	// while (!msg.equals("QUIT")) {
	// msg = sc.nextLine();
	// sendMsg(msg);
	// }
	// }

	public static void setClientCallbacker(ClientCallbacker clientCallbacker) {
		instance.clientCallbacker = clientCallbacker;
	}

	private ClientSocket() {
		connect();
	}

	public static ClientSocket getInstance() {
		if (instance == null) {
			instance = new ClientSocket();
		}
		return instance;
	}

	public static void connect() {

		try {
			socket = new Socket(hostname, port);

			os = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			is = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
			return;
		}

		Thread inputMsg = new Thread() {
			@Override
			public void run() {
				try {
					String msg;
					while (true) {

						msg = is.readLine();
						System.out.println(msg);
						if (clientCallbacker != null)
							clientCallbacker.onReceivedMsgFromServer(msg);

					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		inputMsg.start();
	}

	public static Boolean sendMsg(String msg) {
		try {
			if (socket.isConnected()) {
				os.write(msg);
				os.newLine();
				os.flush();
				return true;
			} else
				return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String sendMsgAndReceive(String msg) {
		try {
			if (socket.isConnected()) {
				os.write(msg);
				os.newLine();
				os.flush();
				return is.readLine();
			} else
				return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
