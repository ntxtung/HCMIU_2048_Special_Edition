package dsa2048;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

class Table {
	private Integer size;
	private Integer[][] table;
	
	public Table clone() {
		Table cloner = new Table(this.size);
		for (int i=0; i<size; i++)
			for (int j=0; j<size; j++)
				cloner.table[i][j] = this.table[i][j];
		return cloner;
	}
	
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
	
	public void pushUp() {
		int topRow;
		for (int y = 0; y < 4; y++) {
			topRow = 0;
			for (int x = 0; x < 4; x++) {
				if (topRow == x || table[x][y] == 0) {
				} else if (table[x][y] == table[topRow][y]) {
					table[topRow][y] = table[topRow][y] * 2;
					table[x][y] = 0;
					topRow++;
				} else {
					if (table[topRow][y] != 0) {
						topRow++;
					}
					if (topRow != x) {
						table[topRow][y] = table[x][y];
						table[x][y] = 0;
					}
				}
			}
		}
	}

	public void pushDown() {
		int lastRow;
		for (int y = 0; y < 4; y++) {
			lastRow = 3;
			for (int x = 3; x >= 0; x--) {
				if (lastRow == x || table[x][y] == 0) {
					continue;
				} else if (table[x][y] == table[lastRow][y]) {
					table[lastRow][y] = table[lastRow][y] * 2;
					table[x][y] = 0;
					lastRow--;
				} else {
					if (table[lastRow][y] != 0)
						lastRow--;
					if (lastRow != x) {
						table[lastRow][y] = table[x][y];
						table[x][y] = 0;
					}
				}
			}
		}
	}

	public void pushLeft() {
		int lastleftCol;
		for (int x = 0; x < 4; x++) {
			lastleftCol = 0;
			for (int y = 0; y < 4; y++) {
				if (lastleftCol == y || table[x][y] == 0) {

				} else if (table[x][y] == table[x][lastleftCol]) {
					table[x][lastleftCol] = table[x][lastleftCol] * 2;
					table[x][y] = 0;
				} else {
					if (table[x][lastleftCol] != 0)
						lastleftCol++;
					if (lastleftCol != y) {
						table[x][lastleftCol] = table[x][y];
						table[x][y] = 0;
					}
				}
			}
		}
	}

	public void pushRight() {
		int lastrightcol;
		for (int x = 0; x < 4; x++) {
			lastrightcol = 3;
			for (int y = 3; y >= 0; y--) {
				if (lastrightcol == y || table[x][y] == 0) {

				} else if (table[x][y] == table[x][lastrightcol]) {
					table[x][lastrightcol] = table[x][lastrightcol] * 2;
					table[x][y] = 0;
				} else {
					if (table[x][lastrightcol] != 0)
						lastrightcol--;
					if (lastrightcol != y) {
						table[x][lastrightcol] = table[x][y];
						table[x][y] = 0;
					}
				}
			}
		}
	}

	/**
	 * 
	 */
	@SuppressWarnings("deprecation")
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
	
	public Boolean isOver(){
        boolean check = true;
        for (int i=0; i<4;i++)
            for(int j=0; j<4; j++){
                if (this.table[i][j]==0)
                    check = false;
                if (i!= 3){
                    if(this.table[i][j]==this.table[i+1][j])
                        check = false;
                }
                if (j!= 3){
                    if(this.table[i][j]==this.table[i][j+1])
                        check = false;
                }
            }
        return check;
    }
}

public class GameplayContainer {
	private static Table gameTable = new Table(4);
	private static Stack<Table> historyTable = new Stack<Table>();
	
	public static Table getGameTable() {
		return gameTable;
	}

	public static void initialize() {
		gameTable = new Table(4);
		gameTable.generateNumber();
	}
	
	public static void pushTable() {
		historyTable.push(gameTable.clone());
	}
	
	public static void popTable() {
		historyTable.pop();
	}
	
	public static void undo() {
		if (!historyTable.isEmpty()) {
			gameTable = historyTable.peek();
			historyTable.pop();
		}
	}
	

}
