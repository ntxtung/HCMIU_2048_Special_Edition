package game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class LoginController {

	@FXML
	private TextField txtUsername;

	@FXML
	private TextField txtPassword;

	@FXML
	void onLogin(ActionEvent event) {
		Alert al = new Alert(AlertType.INFORMATION);
		al.setContentText(ClientSocket.getInstance().
				sendMsgAndReceive(String.format("login@@%s@@%s", txtUsername.getText(), txtPassword.getText())));
		al.show();
		
		//System.out.println();
	}
}
