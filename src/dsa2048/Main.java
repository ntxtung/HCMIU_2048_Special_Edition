package dsa2048;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("fxml/Login.fxml"));
			Scene scene = new Scene(root, root.getPrefWidth(), root.getPrefHeight());
			
			scene.getStylesheets().add(getClass().getResource("css/Gameplay.css").toExternalForm());
//			primaryStage.initStyle(StageStyle.TRANSPARENT);
			primaryStage.setTitle("2048");
			primaryStage.setScene(scene);
			
			primaryStage.setResizable(false);
			primaryStage.show();

//			// Set window to screeen center
//			Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
//			primaryStage.setX((screenBounds.getWidth() - primaryStage.getWidth())/2);
//			primaryStage.setY((screenBounds.getHeight() - primaryStage.getHeight())/2);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
