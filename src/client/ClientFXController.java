package client;

import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

import javafx.event.ActionEvent;

import javafx.scene.control.TextArea;

public class ClientFXController {
	@FXML
	private TextArea memo;
	@FXML
	private TextField txtMsg;
	@FXML
	private Button btnSend;

	// Event Listener on Button[#btnSend].onAction
	@FXML
	public void send(ActionEvent event) {
		
		try {
			os.write(txtMsg.getText());
			os.newLine();
			os.flush();
			
			String res;
			res = is.readLine();
			memo.appendText(res+"\n");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	 @FXML
	 public void connect(ActionEvent event) {
		 try {
			socket = new Socket("localhost", 1998);
			is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			os = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }

	 @FXML
	 public void quit(ActionEvent event) {	 
		 try {
			 socket.close();
			 is.close();
			os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	
	@FXML
	public void initialize() {
//		Alert al = new Alert(AlertType.INFORMATION);
//		al.setContentText("Tesstttt");
//		al.show();
	}
	
	private Socket socket = null;
	BufferedReader is = null;
	BufferedWriter os = null;
	
	
//	public static void start() throws IOException {
//		InetAddress address = InetAddress.getLocalHost();
//		Socket socket = null;
//		String line = null;
//		BufferedReader br = null;
//		BufferedReader is = null;
//		BufferedWriter os = null;
//		
//		try {
//			socket = new Socket("localhost", 1998);
//			br = new BufferedReader(new InputStreamReader(System.in));
//			is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//			os = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//			
//			//os.println("Hello from client!");
//			for (int i=0;i<3;i++) {
//				os.write("Happy new year!");
//				os.newLine();
//				os.flush();
//				System.out.println(is.readLine());
//			}
//			os.flush();
			//os.close();
			
//			String response;
//			while (true) {
//			try{
//		        line=br.readLine(); 
//		        while(line.compareTo("QUIT")!=0){
//		        os.write(line);
//		        os.newLine();
//		        os.flush();
//		        response=is.readLine();
//		        System.out.println("Server Response : "+response);
//		        line=br.readLine();
//		        }
//		    }
//		    catch(IOException e){
//		        e.printStackTrace();
//		    System.out.println("Socket read Error");
//		    }
//		    finally{
//		        is.close();os.close();br.close();socket.close();
//		                System.out.println("Connection Closed");
//
//		    }
//			}
//			while ((line = br.readLine()) != null) {
//				os.write(line);
//				os.newLine();
//				os.flush();
//			}
			//System.out.println(socket.isConnected());
			
//		} catch (IOException e) {
//			System.out.println("Can't connect to server!");
//			e.printStackTrace();
//		}
//	}
}
