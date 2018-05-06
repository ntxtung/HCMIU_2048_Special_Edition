package javafxapplication1;

import java.util.ArrayList;
import java.util.Random;
import java.awt.Graphics2D;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Game {
    private final int[][] gameBoard;
    private final Random r = new Random();
    private Color[] cs = {
        Color.rgb(205, 193, 180),
        Color.rgb(238, 228, 218),
        Color.rgb(237, 224, 200),
        Color.rgb(242, 177, 121),
        Color.rgb(245, 149, 99),
        Color.rgb(246, 124, 95),
        Color.rgb(246, 94, 29),
        Color.rgb(237, 207, 114),
        Color.rgb(237, 204, 97),
        Color.rgb(237, 200, 80),
        Color.rgb(237, 197, 63),
        Color.rgb(237, 194, 46),
        Color.rgb(60, 58, 50),
        Color.rgb(60, 58, 50),
        Color.rgb(60, 58, 50)
    };
    public Game()
    {
       gameBoard = new int [4][4]; 
       System.setProperty("title2", "0xeee4da");
       System.setProperty("title4", "0xede0c8");
       System.setProperty("title8", "0xf2b179");
       System.setProperty("title16", "0xf59563");
       System.setProperty("title32", "0xf67c5f");
       System.setProperty("title64", "0xf65e3b");
       System.setProperty("title128", "0xedcf72");
       System.setProperty("title256", "0xedcc61");
       System.setProperty("title512", "0xedc850");
       System.setProperty("title1024", "0xedc53f");
       System.setProperty("title2048", "0xedc22e");
       System.setProperty("titlesuper", "0x3c3a32");            
    }
    public void printArray()
    {
        for(int[] x : gameBoard)
        {
            System.out.format("%6d%6d%6d%6d%n", x[0],x[1],x[2],x[3]);
        }
        System.out.println();
    }
    
    public BorderPane getNode(int x, int y) {
        BorderPane b = new BorderPane();
        StackPane s = new StackPane();
        Rectangle re = new Rectangle(0, 0, 99, 99);
        int j=0;
        for(int i = 1; Math.pow(2, i)<=this.gameBoard[x][y]; i++){
            j=i;
        }
        re.setFill(cs[j]);
        s.getChildren().add(re);
        Text te = new Text(String.valueOf(this.gameBoard[x][y]));
        te.setFont(Font.font ("Arial", FontWeight.BOLD, 40));
        te.setFill(Color.WHITE);
        if(this.gameBoard[x][y]!=0)
            s.getChildren().add(te);
        b.getChildren().add(s);
        b.setTranslateX(x*100+150);
        b.setTranslateY(y*100+150);
        return b;
}
    public BorderPane getImage(){
        BorderPane b = new BorderPane();
        for (int i=0; i<4; i++)
            for(int j= 0; j<4; j++)
            {
                b.getChildren().add(getNode(i,j));
            }
        return b;
    }
    public BorderPane getGameoverImage(){
        BorderPane b = new BorderPane();
        for (int i=0; i<4; i++)
            for(int j= 0; j<4; j++)
            {
                b.getChildren().add(getNode(i,j));
            }
        Text te = new Text("Game over");
        te.setFont(Font.font("Arial", FontWeight.BOLD, 60));
        te.setTranslateX(130);
        te.setTranslateY(300);
        b.getChildren().add(te);
        return b;
    }
   
    public void addNum()
    {
        ArrayList<Integer> emptyspacesX = new ArrayList<Integer>();
        ArrayList<Integer> emptyspacesY = new ArrayList<Integer>();
        for(int x=0;x<4;x++)
        {
            for(int y=0;y<4;y++)
            {
                if(gameBoard[x][y]==0)
                {
                    emptyspacesX.add(new Integer(x));
                    emptyspacesY.add(new Integer(y));
                }
            }
        }
        int choice = r.nextInt(emptyspacesX.size());
        int numchooser = r.nextInt(10); //value 0-9
        int newPopup = 2;
        if (numchooser == 0)
        {
                newPopup = 4;
        }
        int X = emptyspacesX.get(choice);
        int Y = emptyspacesY.get(choice);
        gameBoard[X][Y]= newPopup;
    }
    public void pushUp()
    {
 
        int topRow;
		for (int y = 0; y < 4; y++) 
                {
			topRow = 0;
			for (int x = 0; x < 4; x++) 
                        {
				if (topRow == x || gameBoard[x][y] == 0) 
                                {
				} 
                                else if (gameBoard[x][y] == gameBoard[topRow][y])
                                {
					gameBoard[topRow][y] = gameBoard[topRow][y] * 2;
					gameBoard[x][y] = 0;
					topRow ++;
				} else 
                                {
					if (gameBoard[topRow][y] != 0) 
                                        {
                                            topRow++;
                                        }
					if(topRow != x) 
                                        {
						gameBoard[topRow][y] = gameBoard[x][y];
						gameBoard[x][y] = 0;
					}
				}
			}
		}
    }
    public void pushdown()
    {
       
        int lastRow;
        for(int y=0;y<4;y++)
        {
            lastRow=3;
            for(int x=3;x>=0;x--)
            {
                if(lastRow == x || gameBoard[x][y]==0)
                {
                    continue;
                }
                else if(gameBoard[x][y] == gameBoard[lastRow][y])
                {
                    gameBoard[lastRow][y] = gameBoard[lastRow][y]*2;
                    gameBoard[x][y]=0;
                    lastRow--;
                }
                else
                {
                    if(gameBoard[lastRow][y]!=0)
                    lastRow--;
                    if(lastRow != x){
                    gameBoard[lastRow][y]=gameBoard[x][y];
                    gameBoard[x][y]=0;}
                }
            }
        }
    }
    
    public void pushleft()
    {
       
        int lastleftCol;
        for(int x=0;x<4;x++)
        {
            lastleftCol=0;
            for(int y=0;y<4;y++)
            {
                if(lastleftCol == y || gameBoard[x][y]==0)
                {
                    
                }
                else if(gameBoard[x][y] == gameBoard[x][lastleftCol])
                {
                    gameBoard[x][lastleftCol] = gameBoard[x][lastleftCol] * 2;
                    gameBoard[x][y]=0;
                }
                else
                {
                    if(gameBoard[x][lastleftCol]!=0)
                        lastleftCol++;
                    if(lastleftCol != y)
                    {
                        gameBoard[x][lastleftCol] = gameBoard[x][y];
                        gameBoard[x][y]=0;
                    }
                }
            }
        }
    }
    public boolean isover(){
        boolean check = true;
        for (int i=0; i<4;i++)
            for(int j=0; j<4; j++){
                if (this.gameBoard[i][j]==0)
                    check = false;
                if (i!= 3){
                    if(this.gameBoard[i][j]==this.gameBoard[i+1][j])
                        check = false;
                }
                if (j!= 3){
                    if(this.gameBoard[i][j]==this.gameBoard[i][j+1])
                        check = false;
                }
            }
        return check;
    }
    public void pushright()
    {
       
        int lastrightcol;
        for(int x=0;x<4;x++)
        {
            lastrightcol=3;
            for(int y=3;y>=0;y--)
            {
                if(lastrightcol == y || gameBoard[x][y] == 0)
                {
                    
                }
                else if(gameBoard[x][y] == gameBoard[x][lastrightcol])
                {
                    gameBoard[x][lastrightcol] = gameBoard[x][lastrightcol] * 2;
                    gameBoard[x][y]=0;
                }
                else
                {
                    if(gameBoard[x][lastrightcol]!= 0)
                        lastrightcol--;
                    if(lastrightcol != y)
                    {
                        gameBoard[x][lastrightcol]=gameBoard[x][y];
                    gameBoard[x][y]=0;
                    }
                }
            }
        }
    }

    public String statestring(){
        String str = "";
        for (int i = 0; i<4; i++)
            for (int j=0; j<4;j++)
                str+= String.valueOf(this.gameBoard[i][j]);
        return str;
    }
}
