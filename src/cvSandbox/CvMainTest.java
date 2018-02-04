package cvSandbox;
	
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class CvMainTest extends Application {
	OpenCVSandboxController controller;
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("OpenCVSandbox.fxml"));
			controller = loader.getController();
			
			AnchorPane root = (AnchorPane)loader.load();
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("UI.css").toExternalForm());
			//primaryStage.initStyle(StageStyle.TRANSPARENT);
			primaryStage.setScene(scene);
			
			primaryStage.show();
			// Set window to screeen center
			Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
			primaryStage.setX((screenBounds.getWidth() - primaryStage.getWidth())/2);
			primaryStage.setY((screenBounds.getHeight() - primaryStage.getHeight())/2);

			//primaryStage.setOnCloseRequest(e -> controller.stop());

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
