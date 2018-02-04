package dsa2048;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class DSA2048Controller {
	
	 @FXML
	    private TextField txtUsername;

	    @FXML
	    private TextField txtPassword;

	    @FXML
	    void onLogin(ActionEvent event) {
	    	Alert al = new Alert(AlertType.INFORMATION);
	    	al.setContentText("LOGGING IN");
	    	al.show();
	    }
}
