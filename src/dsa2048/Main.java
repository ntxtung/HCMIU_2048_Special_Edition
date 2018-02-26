package dsa2048;
	
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
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("Gameplay.fxml"));
			Scene scene = new Scene(root, root.getPrefWidth(), root.getPrefHeight());

			//scene.getStylesheets().add(getClass().getResource("UI.css").toExternalForm());
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
