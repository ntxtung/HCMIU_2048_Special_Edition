package game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {
	private static Stage primStage;
	public static void changeScene(Scene newScene) {
		primStage.setScene(newScene);
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			primStage = primaryStage;
			ClientSocket.getInstance();
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/LoginForm.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			
//			ClientSocket.setClientCallbacker(loader.getController());

			scene.getStylesheets().add(getClass().getResource("css/Gameplay.css").toExternalForm());
			primaryStage.setTitle("2048");
			primaryStage.setScene(scene);

			primaryStage.setResizable(false);
			primaryStage.show();
			
			// Set window to screeen center
//			Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
//			primaryStage.setX((screenBounds.getWidth() - primaryStage.getWidth()) / 2);
//			primaryStage.setY((screenBounds.getHeight() - primaryStage.getHeight()) / 2);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
