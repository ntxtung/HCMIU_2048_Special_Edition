package server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Server2048 extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ServerFX.fxml"));
			ServerFXController controller = loader.getController();
			BorderPane root = (BorderPane)loader.load();
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("UI.css").toExternalForm());
			//primaryStage.initStyle(StageStyle.TRANSPARENT);
			primaryStage.setTitle("SERVER 2048 - 2T2P Team");
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
