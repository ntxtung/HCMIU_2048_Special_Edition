package server;

import org.hyperic.sigar.SigarException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;

public class ServerFXController {
	
	private static final int MAX_USER = 100;
	private static final int MAX_ROOM = 100;
	
	@FXML
    private BorderPane scene;
	@FXML
	private ProgressBar progressUser;
	@FXML
	private ProgressBar progressRoom;
	@FXML
	private ProgressBar progressCPU;
	@FXML
	private ProgressBar progressRAM;
	@FXML
	private ProgressBar progressNetwork;
	@FXML
	private Label lblStatusUser;
	@FXML
	private Label lblStatusRoom;
	@FXML
	private Label lblStatusCPU;
	@FXML
	private Label lblStatusRAM;
	@FXML
	private Label lblStatusNetwork;
	
	@FXML 
	private void initialize() {
		try {
			Thread sigarThread = new SigarThread(this);
			sigarThread.setDaemon(true);
			sigarThread.start();
		} catch (SigarException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void  setStatusUser(int value) {
		Platform.runLater(() -> {	
		lblStatusUser.setText(""+value+"/"+ MAX_USER);
		progressUser.setProgress(1f*value/MAX_USER);
		});
	}
	public void  setStatusRoom(int value) {
		Platform.runLater(() -> {	
		lblStatusRoom.setText(""+value+"/"+ MAX_ROOM);
		progressRoom.setProgress(1f*value/MAX_ROOM);
		});
	}
	public void  setStatusCPU(int value) {
		Platform.runLater(() -> {	
			lblStatusCPU.setText(""+value+"%");
			progressCPU.setProgress(value/100f);
		});
	}
	public void  setStatusRAM(int value) {
	
	}
	public void  setStatusNetwork(int value) {
	
	}
	
	

}
