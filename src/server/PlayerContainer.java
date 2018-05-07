package server;

import java.util.ArrayList;

public class PlayerContainer implements IMsgProcessable {
	
	private static ArrayList<PlayerThread> players = new ArrayList<>();
	
	private static PlayerContainer instance;
	
	public static PlayerContainer getInstance() {
		if (instance == null)
			instance = new PlayerContainer();
		return instance;
	}
	
	
}
