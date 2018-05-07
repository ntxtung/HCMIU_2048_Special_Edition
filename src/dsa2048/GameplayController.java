package dsa2048;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class GameplayController {

	class TitleList {
		private TitleFX[][] listOfTitles = new TitleFX[4][4];

		public TitleList() {
			for (int i = 0; i < paneGame.getRowCount(); i++) {
				for (int j = 0; j < paneGame.getColumnCount(); j++) {
					listOfTitles[i][j] = new TitleFX(GameplayContainer.getGameTable().get(i, j));
				}
			}
		}

		public TitleFX get(int row, int col) {
			return listOfTitles[row][col];
		}

		public void update() {
			for (int i = 0; i < paneGame.getRowCount(); i++) {
				for (int j = 0; j < paneGame.getColumnCount(); j++) {
					listOfTitles[i][j].setValue(GameplayContainer.getGameTable().get(i, j));
				}
			}
		}
	}

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

	private TitleList tiList;

	@FXML
	public void initialize() {
		GameplayContainer.initialize();
		tiList = new TitleList();
		textScoreBest = new Text();
		textScoreCurrent = new Text();
		this.render();
	}

	public void render() {
		tiList.update();
		paneGame.getChildren().clear();
		Pane pn = new Pane();
		for (int i = 0; i < paneGame.getRowCount(); i++) {
			for (int j = 0; j < paneGame.getColumnCount(); j++) {
				paneGame.add(tiList.get(i, j).getTitle(), j, i);
			}
		}
		scoreCurrent.setText(GameplayContainer.getScore().toString());
		scoreBest.setText(GameplayContainer.getScore().toString());
	}

	@FXML
	public void onKeyboardPressed(KeyEvent e) {
		GameplayContainer.pushTable();
		if (e.getCode() == KeyCode.W) {
			GameplayContainer.getGameTable().move(MoveType.UP);
		} else if (e.getCode() == KeyCode.A) {
			GameplayContainer.getGameTable().move(MoveType.LEFT);
		} else if (e.getCode() == KeyCode.S) {
			GameplayContainer.getGameTable().move(MoveType.DOWN);
		} else if (e.getCode() == KeyCode.D) {
			GameplayContainer.getGameTable().move(MoveType.RIGHT);
		}

//		GameplayContainer.getGameTable().consoleDisplay();
		GameplayContainer.updateScore();
		this.render();
		if (GameplayContainer.getGameTable().isOver()) {
			Alert al = new Alert(AlertType.INFORMATION);
			al.setTitle("2048 - GameOver");
			al.setContentText("GAME OVER!");
			al.show();
		}
//		System.out.println();
	}
	
	@FXML
	public void onRestartButtonClicked() {
		GameplayContainer.restart();
		this.render();
	}
	
	@FXML
	public void onUndoButtonClicked() {
		GameplayContainer.undo();

//		GameplayContainer.getGameTable().consoleDisplay();
		this.render();
	}

}
