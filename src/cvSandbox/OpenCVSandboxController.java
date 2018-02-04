package cvSandbox;

import java.io.ByteArrayInputStream;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class OpenCVSandboxController {
	@FXML
	private ImageView img;

	CvSandbox updater = null;
	Thread thread = null;
	@FXML
	private void initialize() {
//		Alert al = new Alert(AlertType.ERROR);
//		al.setContentText("hihi");
//		al.show();
		
		CvSandbox updater = new CvSandbox();
		updater.controller = this;
		updater.running = true;
		Thread thread = new Thread(updater);
		thread.setDaemon(true);
		thread.start();
	}
	
	
	@FXML
	protected void updateImg(Mat img) {
		MatOfByte buffer = new MatOfByte();
		Imgcodecs.imencode(".jpg", img, buffer);
		
		this.img.setImage(new Image(new ByteArrayInputStream(buffer.toArray())));
	}
	
}
