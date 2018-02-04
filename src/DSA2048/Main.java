package DSA2048;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("DSA2048.fxml"));
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("UI.css").toExternalForm());
			primaryStage.initStyle(StageStyle.TRANSPARENT);
			primaryStage.setScene(scene);
			
			primaryStage.show();

			// Set window to screeen center
			Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
			primaryStage.setX((screenBounds.getWidth() - primaryStage.getWidth())/2);
			primaryStage.setY((screenBounds.getHeight() - primaryStage.getHeight())/2);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
