package cvSandbox;

import java.util.concurrent.TimeUnit;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

public class CvSandbox implements Runnable {
	public boolean running = false;
	public OpenCVSandboxController controller = null;

	public CvSandbox() {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}
	
//	public static void main(String args[]) {
//		new CvSandbox();
//	}

	@Override
	public void run() {
		new CvSandbox();
		Mat frame = new Mat();
		Mat black = new Mat();
		VideoCapture vc = new VideoCapture(0);
		while (running) {
			vc.read(frame);
				
			//Imgproc.Canny(frame, black, 20, 255);
			
			Imgproc.cvtColor(frame, black, Imgproc.COLOR_BGR2HSV);
			Core.inRange(black, new Scalar(controller.getSlider(1),controller.getSlider(2),controller.getSlider(3)), 
					new Scalar(controller.getSlider(4),controller.getSlider(5),controller.getSlider(6)), black);
			//Video.CamShift(probImage, window, criteria)
			Imgproc.dilate(black, black, Imgproc.getStructuringElement(Imgproc.MORPH_ELLIPSE, new Size(5,5)));
			Imgproc.erode(black, black, Imgproc.getStructuringElement(Imgproc.MORPH_ELLIPSE, new Size(5,5)));
			System.out.println("Hello World!");
			
			controller.updateImg(black);
			try {
				TimeUnit.MICROSECONDS.sleep(33);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}
