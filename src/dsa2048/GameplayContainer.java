package dsa2048;

import java.util.Stack;

public class GameplayContainer {
	private static GameGrid gameTable = new GameGrid(4);
	private static Stack<GameGrid> historyTable = new Stack<GameGrid>();
	private static Integer currentScore = 0;
	private static Integer bestScore;

	public static GameGrid getGameTable() {
		return gameTable;
	}

	public static void initialize() {
		if (bestScore == null)
			bestScore = 0;
		gameTable = new GameGrid(4);
		gameTable.generateNumber();
		gameTable.generateNumber();
	}

	public static void pushTable() {
		historyTable.push(gameTable.clone());
	}

	public static void popTable() {
		historyTable.pop();
	}

	public static void restart() {
		initialize();
		historyTable.clear();
		currentScore = 0;
	}

	public static void undo() {
		if (!historyTable.isEmpty()) {
			gameTable = historyTable.peek();
			historyTable.pop();
		}
	}

	public static void updateScore() {
		currentScore = gameTable.getScore();
		if (currentScore > bestScore) {
			bestScore = currentScore;
		}
	}

	public static Integer getScore() {
		return currentScore;
	}

}
