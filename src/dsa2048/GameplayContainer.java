package dsa2048;

import java.util.Stack;

public class GameplayContainer {
	private static GameGrid gameTable = new GameGrid(4);
	private static Stack<GameGrid> historyTable = new Stack<GameGrid>();
	
	public static GameGrid getGameTable() {
		return gameTable;
	}

	public static void initialize() {
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
	}
	
	public static void undo() {
		if (!historyTable.isEmpty()) {
			gameTable = historyTable.peek();
			historyTable.pop();
		}
	}
	

}
