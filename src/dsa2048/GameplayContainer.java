package dsa2048;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameplayContainer {
	private GameGrid gameGrid;

	public boolean canUndo;
	private long score = 0;
	private long highScore = 0;
	private long lastScore = 0;
	private long bufferScore = 0;

	private int numX = 4;
	private int numY = 4;

	public GameplayContainer() {

	}

	public void newGame() {
		if (gameGrid == null) {
			this.gameGrid = new GameGrid(numX, numY);
		} else {
			gameGrid.clearField();
		}

		if (score >= highScore) {
			highScore = score;
		}

		score = 0;
		this.addRandomTile();
		this.addRandomTile();

	}

	private void addRandomTile() {
		if (gameGrid.isCellsAvailable()) {
			int value = Math.random() < 0.9 ? 2 : 4;
			Tile tile = new Tile(gameGrid.randomAvailableCell(), value);
			spawnTile(tile);
		}
	}

	private void spawnTile(Tile tile) {
		gameGrid.insertTile(tile);
	}

	private void recordHighScore() {
	}

	private long getHighScore() {
		return 0;
	}

	private void prepareTiles() {
		for (Tile[] array : gameGrid.field) {
			for (Tile tile : array) {
				if (gameGrid.isCellOccupied(tile)) {
					tile.setMergedFrom(null);
				}
			}
		}
	}

	private void moveTile(Tile tile, Cell cell) {
		gameGrid.field[tile.getX()][tile.getY()] = null;
		gameGrid.field[cell.getX()][cell.getY()] = tile;
		tile.updatePosition(cell);
	}

	private void saveUndoState() {
		gameGrid.saveTiles();
		canUndo = true;
		lastScore = bufferScore;
	}

	private void prepareUndoState() {
		gameGrid.prepareSaveTiles();
		bufferScore = score;
	}

	public void move(int direction) {
		// animation cancel()
		// 0: up, 1: right, 2: down, 3: left
		if (!movesAvailable()) {
			return;
		}
		prepareUndoState();
		Cell vector = getVector(direction);
		List<Integer> traversalsX = buildTraversalsX(vector);
		List<Integer> traversalsY = buildTraversalsY(vector);
		boolean moved = false;

		prepareTiles();

		for (int x : traversalsX) {
			for (int y : traversalsY) {
				Cell cell = new Cell(x, y);
				Tile tile = gameGrid.getCellContent(cell);

				if (tile != null) {
					Cell[] positions = findFarthestPosition(cell, vector);
					Tile next = gameGrid.getCellContent(positions[1]);

					if (next != null && next.getValue() == tile.getValue() && next.getMergedFrom() == null) {
						Tile merged = new Tile(positions[1], tile.getValue() * 2);
						Tile[] temp = { tile, next };
						merged.setMergedFrom(temp);

						gameGrid.insertTile(merged);
						gameGrid.removeTile(tile);

						// Converge the two tiles' positions
						tile.updatePosition(positions[1]);

						int[] extras = { x, y };

						// Update the score
						score = score + merged.getValue();
						highScore = Math.max(score, highScore);

					} else {
						moveTile(tile, positions[0]);
						int[] extras = { x, y, 0 };
					}

					if (!positionsEqual(cell, tile)) {
						moved = true;
					}
				}
			}
		}

		if (moved) {
			saveUndoState();
			addRandomTile();
			checkLose();
		}
	}

	private void checkLose() {
		if (!movesAvailable()) {
			endGame();
		}
	}

	private void endGame() {
		if (score >= highScore) {
			highScore = score;
			recordHighScore();
		}
	}

	private Cell getVector(int direction) {
		Cell[] map = { new Cell(0, -1), // up
				new Cell(1, 0), // right
				new Cell(0, 1), // down
				new Cell(-1, 0) // left
		};
		return map[direction];
	}

	private List<Integer> buildTraversalsX(Cell vector) {
		List<Integer> traversals = new ArrayList<>();

		for (int x = 0; x < numX; x++) {
			traversals.add(x);
		}
		if (vector.getX() == 1) {
			Collections.reverse(traversals);
		}

		return traversals;
	}

	private List<Integer> buildTraversalsY(Cell vector) {
		List<Integer> traversals = new ArrayList<>();

		for (int x = 0; x < numY; x++) {
			traversals.add(x);
		}
		if (vector.getY() == 1) {
			Collections.reverse(traversals);
		}

		return traversals;
	}

	private Cell[] findFarthestPosition(Cell cell, Cell vector) {
		Cell previous;
		Cell nextCell = new Cell(cell.getX(), cell.getY());
		do {
			previous = nextCell;
			nextCell = new Cell(previous.getX() + vector.getX(), previous.getY() + vector.getY());
		} while (gameGrid.isCellWithinBounds(nextCell) && gameGrid.isCellAvailable(nextCell));

		return new Cell[] { previous, nextCell };
	}

	private boolean movesAvailable() {
		return gameGrid.isCellsAvailable() || tileMatchesAvailable();
	}

	private boolean tileMatchesAvailable() {
		Tile tile;

		for (int x = 0; x < numX; x++) {
			for (int y = 0; y < numY; y++) {
				tile = gameGrid.getCellContent(new Cell(x, y));

				if (tile != null) {
					for (int direction = 0; direction < 4; direction++) {
						Cell vector = getVector(direction);
						Cell cell = new Cell(x + vector.getX(), y + vector.getY());

						Tile other = gameGrid.getCellContent(cell);

						if (other != null && other.getValue() == tile.getValue()) {
							return true;
						}
					}
				}
			}
		}

		return false;
	}

	private boolean positionsEqual(Cell first, Cell second) {
		return first.getX() == second.getX() && first.getY() == second.getY();
	}
}
