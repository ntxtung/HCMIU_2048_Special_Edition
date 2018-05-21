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
	@FXML
	private Text txtPlayerName;

	private TitleList tiList;

	@FXML
	public void initialize() {
		gameGridInit();
		
		Platform.runLater(() -> {
			txtPlayerName.setText(ClientSocket.getPlayerName());
		});
		
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
			ClientSocket.getInstance().sendMsg("control@@W");
		} else if (e.getCode() == KeyCode.A) {
			ClientSocket.getInstance().sendMsg("control@@A");
		} else if (e.getCode() == KeyCode.S) {
			ClientSocket.getInstance().sendMsg("control@@S");
		} else if (e.getCode() == KeyCode.D) {
			ClientSocket.getInstance().sendMsg("control@@D");
		}

		if (gameTable.isOver()) {
			ClientSocket.getInstance().sendMsg("lose");
			Alert al = new Alert(AlertType.INFORMATION);
			al.setTitle("2048 - GameOver");
			al.setContentText("GAME OVER!");
			al.show();
		}
	}

	@FXML
	public void onRestartButtonClicked() {
		gameGridRestart();
	}

	@FXML
	public void onUndoButtonClicked() {
		ClientSocket.getInstance().sendMsg("undo");
	}

	@Override
	public void onReceivedMsgFromServer(String msg) {
		String[] subStr = msg.split("@@");
		if (subStr[0].equals("control")) {
			if (subStr[1].equals("W")) {
				gameTable.move(MoveType.UP);
			} else if (subStr[1].equals("A")) {
				gameTable.move(MoveType.LEFT);
			} else if (subStr[1].equals("S")) {
				gameTable.move(MoveType.DOWN);
			} else if (subStr[1].equals("D")) {
				gameTable.move(MoveType.RIGHT);
			} 
		} else if (subStr[0].equals("gen")) {
			int x, y, value;
			x = Integer.parseInt(subStr[1]);
			y = Integer.parseInt(subStr[2]);
			value = Integer.parseInt(subStr[3]);
			gameTable.insert(x, y, value);
			System.out.println("Generate: " + x + " " + y + " " + value);
		} else if (subStr[0].equals("undo")) {
			gameGridUndo();
		}
		
		Platform.setImplicitExit(false);
		Platform.runLater(() -> {
			updateScore();
			render();
		});
		
	}

}
