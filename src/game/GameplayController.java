package game;

import java.util.Stack;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class GameplayController implements ClientCallbacker {
	class TitleList {
		private TitleFX[][] listOfTitles = new TitleFX[4][4];

		public TitleList() {
			for (int i = 0; i < paneGame.getRowCount(); i++) {
				for (int j = 0; j < paneGame.getColumnCount(); j++) {
					listOfTitles[i][j] = new TitleFX(gameTable.get(i, j));
				}
			}
		}

		public TitleFX get(int row, int col) {
			return listOfTitles[row][col];
		}

		public void update() {
			for (int i = 0; i < paneGame.getRowCount(); i++) {
				for (int j = 0; j < paneGame.getColumnCount(); j++) {
					listOfTitles[i][j].setValue(gameTable.get(i, j));
				}
			}
		}
	}

	private GameGrid gameTable = new GameGrid(4);
	private Stack<GameGrid> historyTable = new Stack<GameGrid>();
	private Stack<Integer> historyScore = new Stack<Integer>();
	private Integer currentScore = 0;
	private Integer bestScore;

	private void gameGridInit() {
		if (bestScore == null) {
			bestScore = 0;
		}
		gameTable = new GameGrid(4);
		gameTable.generateNumber();
		gameTable.generateNumber();
	}

	private void gameGridUndo() {
		if (!historyTable.isEmpty()) {
			gameTable = historyTable.pop();
			currentScore = historyScore.pop();
		}
	}

	private void gameGridRestart() {
		gameGridInit();
		historyTable.clear();
		currentScore = 0;
	}

	public void updateScore() {
		currentScore = gameTable.getScore();
		if (currentScore > bestScore) {
			bestScore = currentScore;
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
		gameGridInit();

		tiList = new TitleList();
		textScoreBest = new Text();
		textScoreCurrent = new Text();
		this.render();
	}

	public void render() {
		tiList.update();
		paneGame.getChildren().clear();
		for (int i = 0; i < paneGame.getRowCount(); i++) {
			for (int j = 0; j < paneGame.getColumnCount(); j++) {
				paneGame.add(tiList.get(i, j).getTitle(), j, i);
			}
		}
		scoreCurrent.setText(gameTable.getScore().toString());
		// scoreBest.setText(gameContainer.getBestScore().toString());
	}

	@FXML
	public void onKeyboardPressed(KeyEvent e) {
		System.out.println("Event key");
		historyTable.push(gameTable.clone());
		historyScore.push(currentScore);
		if (e.getCode() == KeyCode.W) {
			// gameContainer.getGameTable().move(MoveType.UP);
			ClientSocket.getInstance().sendMsg("control@@W");
		} else if (e.getCode() == KeyCode.A) {
			// gameContainer.getGameTable().move(MoveType.LEFT);
			ClientSocket.getInstance().sendMsg("control@@A");
		} else if (e.getCode() == KeyCode.S) {
			// gameContainer.getGameTable().move(MoveType.DOWN);
			ClientSocket.getInstance().sendMsg("control@@S");
		} else if (e.getCode() == KeyCode.D) {
			// gameContainer.getGameTable().move(MoveType.RIGHT);
			ClientSocket.getInstance().sendMsg("control@@D");
		}

		// gameContainer.getGameTable().consoleDisplay();
		// gameContainer.updateScore();
		// this.render();
		if (gameTable.isOver()) {
			Alert al = new Alert(AlertType.INFORMATION);
			al.setTitle("2048 - GameOver");
			al.setContentText("GAME OVER!");
			al.show();
		}
	}

	@FXML
	public void onRestartButtonClicked() {
		gameGridRestart();
		this.render();
	}

	@FXML
	public void onUndoButtonClicked() {
		gameGridUndo();

		// gameContainer.getGameTable().consoleDisplay();
		this.render();
	}

	@Override
	public void onReceivedMsgFromServer(String msg) {
		if (msg.equals("control@@W")) {
			gameTable.move(MoveType.UP);
		} else if (msg.equals("control@@A")) {
			gameTable.move(MoveType.LEFT);
		} else if (msg.equals("control@@S")) {
			gameTable.move(MoveType.DOWN);
		} else if (msg.equals("control@@D")) {
			gameTable.move(MoveType.RIGHT);
		}
//		gameTable.consoleDisplay();
//		System.out.println("test " +msg);
		Platform.setImplicitExit(false);
		Platform.runLater(() -> {
			updateScore();
			render();
		});
		
	}

}
