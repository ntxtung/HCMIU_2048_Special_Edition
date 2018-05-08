package game;

import java.util.Stack;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class MultiplayerController implements ClientCallbacker {

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
	private Text scoreCurrent;

	@FXML
	private Text textGameTitle;

	private TitleList tiList;

	@FXML
	public void initialize() {
		gameGridInit();
		tiList = new TitleList();
		scoreCurrent = new Text();
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
	}

	@Override
	public void onReceivedMsgFromServer(String msg) {

	}

}
