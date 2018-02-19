package server;

import java.util.ArrayList;
import java.util.Collections;

public class Room {
	private String name = "";
	private Integer id = 0;
	private GameMode gameMode = GameMode.REACH_2048;
	private ArrayList<Player> playerList = new ArrayList<Player>();
	private long time = 0l;
	
	public Room(String name, Integer id, GameMode gameMode) {
		this.name = name;
		this.id = id;
		this.gameMode = gameMode;	
	}

	public String getName() 			       {return name;}
	public void setName(String name) 		   {this.name = name;}

	public Integer getId() 					   {return id;}
	public void setId(Integer id) 			   {this.id = id;}

	public GameMode getGameMode() 			   {return gameMode;}
	public void setGameMode(GameMode gameMode) {this.gameMode = gameMode;}

	public long getTime() 					   {return time;}
	public void setTime(long time)			   {this.time = time;}
	
	public ArrayList<Player> getPlayerList()   {return playerList;}
	
	public void addPlayer(Player player) {
		playerList.add(player);
		Collections.sort(playerList);
	}
	
	public void removePlayer(Player player) {
		playerList.remove(player);
	}
	
	public void removePlayer(String playerUsername) {
		playerList.remove(Collections.binarySearch(playerList, new Player(playerUsername, null, null)));
	}
	
}
