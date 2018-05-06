package dsa2048;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class GameplayController {
	@FXML
	private Pane paneMain;
	@FXML
	private GridPane paneGame;
	@FXML
	private Pane paneScoreCurrent;
	@FXML
	private Pane paneScoreBest;
	@FXML
	private Text scoreCurrent;
	@FXML
	private Text scoreBest;
	@FXML
	private Text textScoreCurrent;
	@FXML
	private Text textScoreBest;
	@FXML
	private Text gameTitle;
	@FXML
	private Button btnUndo;
	@FXML
	private Button btnRestart;
	private GameplayContainer gc;

	@FXML
	public void initialize() {
		gc = new GameplayContainer();
		this.render();
	}

	public void render() {
	}

	@FXML
	public void onKeyboardPressed(KeyEvent e) {
	}

	@FXML
	public void onRestartButtonClicked() {
	}

	@FXML
	public void onUndoButtonClicked() {
	}

}
