package dsa2048;

import java.util.ArrayList;

public class GameGrid {
	public Tile[][] field;
	private Tile[][] undoField;
	private Tile[][] bufferField;

	public Tile[][] getField() {
		return field;
	}

	public Tile[][] getUndoField() {
		return undoField;
	}

	public GameGrid(int sizeX, int sizeY) {
		field = new Tile[sizeX][sizeY];
		undoField = new Tile[sizeX][sizeY];
		bufferField = new Tile[sizeX][sizeY];
		clearField();
		clearUndoField();
	}

	public Cell randomAvailableCell() {
		ArrayList<Cell> availableCells = getAvailableCells();
		if (availableCells.size() >= 1) {
			return availableCells.get((int) Math.floor(Math.random() * availableCells.size()));
		}
		return null;
	}

	private ArrayList<Cell> getAvailableCells() {
		ArrayList<Cell> availableCells = new ArrayList<>();
		for (int x = 0; x < field.length; x++) {
			for (int y = 0; y < field[0].length; y++) {
				if (field[x][y] == null) {
					availableCells.add(new Cell(x, y));
				}
			}
		}
		return availableCells;
	}

	public boolean isCellsAvailable() {
		return (getAvailableCells().size() >= 1);
	}

	public boolean isCellAvailable(Cell cell) {
		return !isCellOccupied(cell);
	}

	public boolean isCellOccupied(Cell cell) {
		return (getCellContent(cell) != null);
	}

	public Tile getCellContent(Cell cell) {
		if (cell != null && isCellWithinBounds(cell)) {
			return field[cell.getX()][cell.getY()];
		} else {
			return null;
		}
	}

	public Tile getCellContent(int x, int y) {
		if (isCellWithinBounds(x, y)) {
			return field[x][y];
		} else {
			return null;
		}
	}

	public boolean isCellWithinBounds(Cell cell) {
		return 0 <= cell.getX() && cell.getX() < field.length && 
			   0 <= cell.getY() && cell.getY() < field[0].length;
	}

	private boolean isCellWithinBounds(int x, int y) {
		return 0 <= x && x < field.length && 0 <= y && 
			   y < field[0].length;
	}

	public void insertTile(Tile tile) {
		field[tile.getX()][tile.getY()] = tile;
	}

	public void removeTile(Tile tile) {
		field[tile.getX()][tile.getY()] = null;
	}

	public void saveTiles() {
		for (int x = 0; x < bufferField.length; x++) {
			for (int y = 0; y < bufferField[0].length; y++) {
				if (bufferField[x][y] == null) {
					undoField[x][y] = null;
				} else {
					undoField[x][y] = new Tile(x, y, bufferField[x][y].getValue());
				}
			}
		}
	}

	public void prepareSaveTiles() {
		for (int x = 0; x < field.length; x++) {
			for (int y = 0; y < field[0].length; y++) {
				if (field[x][y] == null) {
					bufferField[x][y] = null;
				} else {
					bufferField[x][y] = new Tile(x, y, field[x][y].getValue());
				}
			}
		}
	}

	public void revertTiles() {
		for (int x = 0; x < undoField.length; x++) {
			for (int y = 0; y < undoField[0].length; y++) {
				if (undoField[x][y] == null) {
					field[x][y] = null;
				} else {
					field[x][y] = new Tile(x, y, undoField[x][y].getValue());
				}
			}
		}
	}

	public void clearField() {
		for (int x = 0; x < field.length; x++) {
			for (int y = 0; y < field[0].length; y++) {
				field[x][y] = null;
			}
		}
	}

	private void clearUndoField() {
		for (int x = 0; x < undoField.length; x++) {
			for (int y = 0; y < undoField[0].length; y++) {
				undoField[x][y] = null;
			}
		}
	}
}
