package dsa2048;

import javafx.scene.layout.GridPane;

public class GameGridFX extends GameGrid{
	private GridPane grid;
	private TileFX[][] tileFX;
	
	
	public GameGridFX(int sizeX, int sizeY, GridPane grid) {
		super(sizeX, sizeY);
		this.grid = grid;
		this.tileFX = new TileFX[sizeX][sizeY];
	}
	
	public void updateGridPane() {
		
	}

}
