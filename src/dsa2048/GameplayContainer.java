package dsa2048;

import java.util.ArrayList;
import java.util.Random;

class Table {
	private Integer size;
	private Integer[][] table;

	public Table(int size) {
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

	/**
	 * Push table up
	 * 
	 */
	public void pushUp() {
		ArrayList<Integer> line;

		for (int i = 0; i < this.size; i++) {
			line = new ArrayList();

			for (int j = 0; j < this.size; j++) {
				if (table[j][i] > 0)
					line.add(table[j][i]);
			}

			for (int j = 0; j < line.size() - 1; j++) {
				if (line.get(j) == line.get(j + 1)) {
					line.set(j, line.get(j) * 2);
					line.remove(j + 1);
				}
			}

			for (int j = 0; j < this.size; j++) {
				if (j < line.size())
					table[j][i] = line.get(j);
				else
					table[j][i] = 0;
			}
		}
	}

	/**
	 * Push table left
	 * 
	 */
	public void pushLeft() {
		ArrayList<Integer> line;

		for (int i = 0; i < this.size; i++) {
			line = new ArrayList();

			for (int j = 0; j < this.size; j++) {
				if (table[i][j] > 0)
					line.add(table[i][j]);
			}

			for (int j = 0; j < line.size() - 1; j++) {
				if (line.get(j) == line.get(j + 1)) {
					line.set(j, line.get(j) * 2);
					line.remove(j + 1);
				}
			}

			for (int j = 0; j < this.size; j++) {
				if (j < line.size())
					table[i][j] = line.get(j);
				else
					table[i][j] = 0;
			}
		}
	}

	/**
	 * Push table down
	 * 
	 */
	public void pushDown() {
		ArrayList<Integer> line;

		for (int i = 0; i < this.size; i++) {
			line = new ArrayList();

			for (int j = 0; j < this.size; j++) {
				if (table[j][i] > 0)
					line.add(table[j][i]);
			}

			for (int j = 0; j < line.size() - 1; j++) {
				if (line.get(j) == line.get(j + 1)) {
					line.set(j, line.get(j) * 2);
					line.remove(j + 1);
				}
			}

			for (int j = 0; j < this.size; j++) {
				if (j < line.size())
					table[this.size - j - 1][i] = line.get(j);
				else
					table[this.size - j - 1][i] = 0;
			}
		}
	}

	/**
	 * Push table right
	 * 
	 */
	public void pushRight() {
		ArrayList<Integer> line;

		for (int i = 0; i < this.size; i++) {
			line = new ArrayList();

			for (int j = 0; j < this.size; j++) {
				if (table[i][j] > 0)
					line.add(table[i][j]);
			}

			for (int j = 0; j < line.size() - 1; j++) {
				if (line.get(j) == line.get(j + 1)) {
					line.set(j, line.get(j) * 2);
					line.remove(j + 1);
				}
			}

			for (int j = 0; j < this.size; j++) {
				if (j < line.size())
					table[i][this.size - j - 1] = line.get(j);
				else
					table[i][this.size - j - 1] = 0;
			}
		}
	}
}

public class GameplayContainer {
	private static Table gameTable = new Table(4);

	public static void initialize() {
		gameTable = new Table(4);
		gameTable.generateNumber();
	}

	public static void main(String agrs[]) {
		gameTable.consoleDisplay();

		System.out.println();
		gameTable.generateNumber();
		gameTable.consoleDisplay();

		System.out.println();
		gameTable.pushUp();
		gameTable.generateNumber();
		gameTable.consoleDisplay();
		
		System.out.println();
		gameTable.pushLeft();
		gameTable.generateNumber();
		gameTable.consoleDisplay();
		
		System.out.println();
		gameTable.pushUp();
		gameTable.generateNumber();
		gameTable.consoleDisplay();
		
		System.out.println();
		gameTable.pushLeft();
		gameTable.generateNumber();
		gameTable.consoleDisplay();
		
		System.out.println();
		gameTable.pushUp();
		gameTable.generateNumber();
		gameTable.consoleDisplay();
		
		System.out.println();
		gameTable.pushLeft();
		gameTable.generateNumber();
		gameTable.consoleDisplay();
		
		System.out.println();
		gameTable.pushUp();
		gameTable.generateNumber();
		gameTable.consoleDisplay();
		
		System.out.println();
		gameTable.pushLeft();
		gameTable.generateNumber();
		gameTable.consoleDisplay();
		
		System.out.println();
		gameTable.pushUp();
		gameTable.generateNumber();
		gameTable.consoleDisplay();
		
		System.out.println();
		gameTable.pushLeft();
		gameTable.generateNumber();
		gameTable.consoleDisplay();
		
		System.out.println();
		gameTable.pushUp();
		gameTable.generateNumber();
		gameTable.consoleDisplay();
		
		System.out.println();
		gameTable.pushLeft();
		gameTable.generateNumber();
		gameTable.consoleDisplay();
		
		System.out.println();
		gameTable.pushUp();
		gameTable.generateNumber();
		gameTable.consoleDisplay();
		
		System.out.println();
		gameTable.pushLeft();
		gameTable.generateNumber();
		gameTable.consoleDisplay();
		
		
	}
}
