package game;

import java.util.Stack;

public class GameplayContainer {
	private GameGrid gameTable = new GameGrid(4);
	private Stack<GameGrid> historyTable = new Stack<GameGrid>();
	private Stack<Integer> historyScore = new Stack<Integer>();
	private Integer currentScore = 0;
	private Integer bestScore;
	
	public GameplayContainer() {
		
	}
	
	public GameGrid getGameTable() {
		return gameTable;
	}

	public void initialize() {
		if (bestScore == null) {
			bestScore = 0;
		}
		gameTable = new GameGrid(4);
		gameTable.generateNumber();
		gameTable.generateNumber();
	}

	public void pushTable() {
		historyTable.push(gameTable.clone());
		historyScore.push(currentScore);
	}

	public void restart() {
		initialize();
		historyTable.clear();
		currentScore = 0;
	}

	public void undo() {
		if (!historyTable.isEmpty()) {
			gameTable = historyTable.pop();
			currentScore = historyScore.pop();
		}
	}

	public void updateScore() {
		currentScore = gameTable.getScore();
		if (currentScore > bestScore) {
			bestScore = currentScore;
		}
	}

	public Integer getScore() {
		return currentScore;
	}

	public Integer getBestScore() {
		return bestScore;
	}
	
}
