package dsa2048;

public class Tile extends Cell {
	private Integer value;
	private Tile[] mergedFrom = null;

	public Tile(int x, int y, int value) {
		super(x, y);
		this.value = value;
	}

	public Tile(Cell cell, int value) {
		super(cell.getX(), cell.getY());
		this.value = value;
	}

	public void updatePosition(Cell cell) {
		this.setX(cell.getX());
		this.setY(cell.getY());
	}

	public Tile[] getMergedFrom() {
		return mergedFrom;
	}

	public void setMergedFrom(Tile[] tile) {
		mergedFrom = tile;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}
}
