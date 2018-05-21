package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GameGrid {
	private Integer size;
	private Integer[][] table;
	private Integer score;

	public GameGrid clone() {
		GameGrid cloner = new GameGrid(this.size);
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				cloner.table[i][j] = this.table[i][j];
		return cloner;
	}

	public GameGrid(int size) {
		this.size = size;
		table = new Integer[size][size];
		for (int i = 0; i < this.size; i++) {
			for (int j = 0; j < this.size; j++)
				table[i][j] = 0;
		}
		this.score = 0;
	}

	public void consoleDisplay() {
		for (int i = 0; i < this.size; i++) {
			for (int j = 0; j < this.size; j++)
				System.out.print(table[i][j] + " ");
			System.out.println();
		}
	}

	/**
	 * 
	 */
	public void move(MoveType mt) {
		boolean state = true;
		switch (mt) {
		case UP:
			state = pushUp();
			break;
		case DOWN:
			state = pushDown();
			break;
		case LEFT:
			state = pushLeft();
			break;
		case RIGHT:
			state = pushRight();
			break;
		}
		if (numEmptyCell() > 0 && state == true) {
			generateNumber();
		}
	}

	private boolean pushUp() {
		boolean a = false;
		for (int col = 0; col < size; col++) {
			ArrayList<Integer> list = new ArrayList<>();
			for (int row = 0; row < size; row++) {
				list.add(table[row][col]);
			}
			// eliminate zero
			for (int i = 0; i < list.size(); i++) {
				if (i + 1 < list.size())
					if (list.get(i).equals(0) && !list.get(i + 1).equals(0)) {
						a = true;
					}
				if (list.get(i).equals(0)) {
					list.remove(i--);
				}
			}
			// push
			int i = 0;
			while (i < list.size() - 1) {
				if (i + 1 < list.size() && list.get(i).equals(list.get(i + 1))) {
					list.set(i, list.get(i) * 2);
					score += list.get(i);
					list.remove(i + 1);
					a = true;
				}
				i++;
			}
			for (int row = 0; row < size; row++) {
				if (row < list.size()) {
					table[row][col] = list.get(row);
				} else {
					table[row][col] = 0;
				}
			}
		}
		return a;
	}

	private boolean pushDown() {
		boolean a = false;
		for (int col = 0; col < size; col++) {
			ArrayList<Integer> list = new ArrayList<>();
			for (int row = 0; row < size; row++) {
				list.add(table[row][col]);
			}
			// eliminate zero
			for (int i = 0; i < list.size(); i++) {
				if (i + 1 < list.size())
					if (list.get(i).equals(0) && !list.get(i + 1).equals(0)) {
						a = true;
					}
				if (list.get(i).equals(0))
					list.remove(i--);
			}
			// push
			int i = 0;
			while (i < list.size()) {
				if (i + 1 < list.size() && list.get(i).equals(list.get(i + 1))) {
					list.set(i, list.get(i) * 2);
					score += list.get(i);
					list.remove(i + 1);
					a = true;
				}
				i++;
			}
			// return
			Collections.reverse(list);
			for (int row = size - 1; row >= 0; row--) {
				if (list.size() > 0) {
					table[row][col] = list.get(0);
					list.remove(0);
				} else {
					table[row][col] = 0;
				}
			}
		}
		return a;
	}

	private boolean pushLeft() {
		boolean a = false;
		for (int row = 0; row < size; row++) {
			ArrayList<Integer> list = new ArrayList<>();
			for (int col = 0; col < size; col++) {
				list.add(table[row][col]);
			}
			// eliminate zero
			for (int i = 0; i < list.size(); i++) {
				if (i + 1 < list.size())
					if (list.get(i).equals(0) && !list.get(i + 1).equals(0)) {
						a = true;
					}
				if (list.get(i).equals(0))
					list.remove(i--);
			}
			// push
			int i = 0;
			while (i < list.size() - 1) {
				if (i + 1 < list.size() && list.get(i).equals(list.get(i + 1))) {
					list.set(i, list.get(i) * 2);
					score += list.get(i);
					list.remove(i + 1);
					a = true;
				}
				i++;
			}
			for (int col = 0; col < size; col++) {
				if (col < list.size()) {
					table[row][col] = list.get(col);
				} else {
					table[row][col] = 0;
				}
			}
		}
		return a;
	}

	private boolean pushRight() {
		boolean a = false;
		for (int row = 0; row < size; row++) {
			ArrayList<Integer> list = new ArrayList<>();
			for (int col = 0; col < size; col++) {
				list.add(table[row][col]);
			}
			// eliminate zero
			for (int i = 0; i < list.size(); i++) {
				if (i + 1 < list.size())
					if (list.get(i).equals(0) && !list.get(i + 1).equals(0)) {
						a = true;
					}
				if (list.get(i).equals(0))
					list.remove(i--);
			}
			// push
			int i = 0;
			while (i < list.size()) {
				if (i + 1 < list.size() && list.get(i).equals(list.get(i + 1))) {
					list.set(i, list.get(i) * 2);
					score += list.get(i);
					list.remove(i + 1);
					a = true;
				}
				i++;
			}
			// return
			Collections.reverse(list);
			for (int col = size - 1; col >= 0; col--) {
				if (list.size() > 0) {
					table[row][col] = list.get(0);
					list.remove(0);
				} else {
					table[row][col] = 0;
				}
			}
		}
		return a;
	}

	/**
	 * 
	 */
	public void generateNumber() {
		Random r = new Random();
		ArrayList<Integer> emptyspacesX = new ArrayList<Integer>();
		ArrayList<Integer> emptyspacesY = new ArrayList<Integer>();
		for (int x = 0; x < this.size; x++) {
			for (int y = 0; y < this.size; y++) {
				if (table[x][y] == 0) {
					emptyspacesX.add(new Integer(x));
					emptyspacesY.add(new Integer(y));
				}
			}
		}
		int choice = r.nextInt(emptyspacesX.size());
		int numchooser = r.nextInt(10); // value 0-9
		int newPopup = 2;
		if (numchooser == 0) {
			newPopup = 4;
		}
		int X = emptyspacesX.get(choice);
		int Y = emptyspacesY.get(choice);

		ClientSocket.getInstance().sendMsg("gen@@" + X + "@@" + Y + "@@" + newPopup);
	}

	public void insert(int X, int Y, int value) {
		this.table[X][Y] = value;
	}

	public Integer get(int row, int col) {
		return table[row][col];
	}

	public Integer numEmptyCell() {
		Integer n = 0;
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				if (table[row][col] == 0)
					n++;
			}
		}
		return n;
	}

	public Boolean isOver() {
		if (this.numEmptyCell() == 0) {
			for (int i = 0; i < size; i++)
				for (int j = 0; j < size; j++) {
					if (i != size - 1) {
						if (this.table[i][j] == this.table[i + 1][j])
							return false;
					}
					if (j != size - 1) {
						if (this.table[i][j] == this.table[i][j + 1])
							return false;
					}
				}
			return true;
		}
		return false;
	}

	public Integer getScore() {
		return score;
	}

}
