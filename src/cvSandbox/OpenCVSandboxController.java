package cvSandbox;

import java.io.ByteArrayInputStream;

import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class OpenCVSandboxController {
	@FXML
	private ImageView img;
	@FXML
    private Slider s1;
    @FXML
    private Slider s2;
    @FXML
    private Slider s3;
    @FXML
    private Slider s4;
    @FXML
    private Slider s5;
    @FXML
    private Slider s6;

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
	
	public int getSlider(int n) {
		switch (n) {
		case 1:
			return (int) s1.getValue();
		case 2:
			return (int) s2.getValue();
		case 3:
			return (int) s3.getValue();
		case 4:
			return (int) s4.getValue();
		case 5:
			return (int) s5.getValue();
		case 6:
			return (int) s6.getValue();
		default: return 0;
		}
	}
	
	
	@FXML
	protected void updateImg(Mat img) {
		MatOfByte buffer = new MatOfByte();
		Imgcodecs.imencode(".jpg", img, buffer);
		
		this.img.setImage(new Image(new ByteArrayInputStream(buffer.toArray())));
	}
	
}
