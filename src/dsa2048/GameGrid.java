package dsa2048;

import java.util.ArrayList;
import java.util.Random;

public class GameGrid {
	private Integer size;
	private Integer[][] table;

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
		switch (mt) {
		case UP:
			pushUp();
			break;
		case DOWN:
			pushDown();
			break;
		case LEFT:
			pushLeft();
			break;
		case RIGHT:
			pushRight();
			break;
		}
	}

	private void pushUp() {
		for (int col = 0; col < size; col++) {
			//eliminateZeroes
			for (int row=0; row < size; row++) {
				if (table[row][col] == 0) {
					for (int i = row+1; i < size; i++) {
						if (table[i][col] != 0) {
							table[row][col] = table[i][col];
							table[i][col] = 0;
						}
					}
				}
			}
			//push
			
			
		}
	}

	private void pushDown() {

	}

	private void pushLeft() {

	}

	private void pushRight() {

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
		table[X][Y] = newPopup;
	}

	public Integer get(int row, int col) {
		return table[row][col];
	}
	
	public Integer numEmptyCell() {
		Integer n = 0;
		for (int row=0; row < size; row++) {
			for (int col=0; col < size; col++) {
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

}
