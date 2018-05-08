package game;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

	@FXML
	private TextField txtUsername;

	@FXML
	private TextField txtPassword;

	@FXML
	void onLogin(ActionEvent event) {
		Alert al = new Alert(AlertType.INFORMATION);
		String receive = ClientSocket.getInstance()
				.sendMsgAndReceive(String.format("login@@%s@@%s", txtUsername.getText(), txtPassword.getText()));
		if (receive != "msg@@InvalidLogin") {
			completeLogin();
		} else {
			al.setContentText(receive);
			al.show();
		}

	}

	private void completeLogin() {
		try {
			Stage dashboardStage = new Stage();
			dashboardStage.setTitle("2048 Special!");

			FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Gameplay.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			Main.changeScene(scene);
			ClientSocket.setClientCallbacker(loader.getController());
			
			Stage dashboardStage2 = new Stage();
            dashboardStage2.setTitle("2048 Special!");
            Parent root2 = FXMLLoader.load(getClass().getResource("fxml/Multiplayer.fxml"));
            Scene scene2 = new Scene(root2);
            dashboardStage2.setScene(scene2);
            dashboardStage2.show();

		} catch (IOException ex) {
			Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
		}

	}
}
