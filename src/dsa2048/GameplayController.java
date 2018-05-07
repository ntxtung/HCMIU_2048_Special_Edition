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
					listOfTitles[i][j] = new TitleFX(gameContainer.getGameTable().get(i, j));
				}
			}
		}

		public TitleFX get(int row, int col) {
			return listOfTitles[row][col];
		}

		public void update() {
			for (int i = 0; i < paneGame.getRowCount(); i++) {
				for (int j = 0; j < paneGame.getColumnCount(); j++) {
					listOfTitles[i][j].setValue(gameContainer.getGameTable().get(i, j));
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
	private GameplayContainer gameContainer;

	@FXML
	public void initialize() {
		gameContainer = new GameplayContainer();
		gameContainer.initialize();
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
		scoreCurrent.setText(gameContainer.getScore().toString());
		scoreBest.setText(gameContainer.getBestScore().toString());
	}

	@FXML
	public void onKeyboardPressed(KeyEvent e) {
		gameContainer.pushTable();
		if (e.getCode() == KeyCode.W) {
			gameContainer.getGameTable().move(MoveType.UP);
		} else if (e.getCode() == KeyCode.A) {
			gameContainer.getGameTable().move(MoveType.LEFT);
		} else if (e.getCode() == KeyCode.S) {
			gameContainer.getGameTable().move(MoveType.DOWN);
		} else if (e.getCode() == KeyCode.D) {
			gameContainer.getGameTable().move(MoveType.RIGHT);
		}

//		gameContainer.getGameTable().consoleDisplay();
		gameContainer.updateScore();
		this.render();
		if (gameContainer.getGameTable().isOver()) {
			Alert al = new Alert(AlertType.INFORMATION);
			al.setTitle("2048 - GameOver");
			al.setContentText("GAME OVER!");
			al.show();
		}
//		System.out.println();
	}
	
	@FXML
	public void onRestartButtonClicked() {
		gameContainer.restart();
		this.render();
	}
	
	@FXML
	public void onUndoButtonClicked() {
		gameContainer.undo();

//		gameContainer.getGameTable().consoleDisplay();
		this.render();
	}

}
