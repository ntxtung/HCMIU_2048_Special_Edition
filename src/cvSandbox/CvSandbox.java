package cvSandbox;

import java.util.concurrent.TimeUnit;

import org.opencv.core.Core;
import org.opencv.core.Mat;
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
		VideoCapture vc = new VideoCapture(0);
		while (running) {
			vc.read(frame);
			System.out.println("Hello World!");
			controller.updateImg(frame);
			try {
				TimeUnit.MICROSECONDS.sleep(33);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}
