package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SocketClient extends Application {
	
	public static void start() throws IOException {
		InetAddress address = InetAddress.getLocalHost();
		Socket socket = null;
		String line = null;
		BufferedReader br = null;
		BufferedReader is = null;
		BufferedWriter os = null;
		
		try {
			socket = new Socket("localhost", 1998);
			socket.setSoTimeout(1000);
			br = new BufferedReader(new InputStreamReader(System.in));
			is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			os = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			//os.println("Hello from client!");
			for (int i=0;i<3;i++) {
				os.write("Happy new year!");
				os.newLine();
				os.flush();
				System.out.println(is.readLine());
			}
			os.flush();
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
			
		} catch (IOException e) {
			System.out.println("Can't connect to server!");
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]) {
		launch(args);	
	}

	@Override
	public void start(Stage arg0) throws Exception {
		AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("ClientFX.fxml"));
		Scene scene = new Scene(root);
		//scene.getStylesheets().add(getClass().getResource("UI.css").toExternalForm());
		//arg0.initStyle(StageStyle.TRANSPARENT);
		arg0.setScene(scene);

		arg0.show();
		
	}
}
