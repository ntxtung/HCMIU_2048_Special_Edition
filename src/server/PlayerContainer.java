package server;

import java.util.ArrayList;

import game.Player;

public class PlayerContainer implements IMsgProcessable {
	
	private static ArrayList<PlayerThread> players = new ArrayList<>();
	
	private static PlayerContainer instance;
	
	public static PlayerContainer getInstance() {
		if (instance == null)
			instance = new PlayerContainer();
		return instance;
	}

	@Override
	public void onReceivedMsgFromClient(PlayerThread playerThread, String msg) {
		String[] subStr = msg.split("@@");
		
		if (subStr[0].equals("login")) {
			String username = subStr[1];
			String password = subStr[2];
			Player player = Login.getInstance().getPlayerInfo(subStr[1], subStr[2]);
			if (player != null) {
				playerThread.feedback(String.format("%d@@%s@@%d", player.getId(), player.getName(), player.getLevel()));
				playerThread.setPlayer(player);
			} else
				playerThread.sendInfoMsg("INVALID LOGIN!");
		}
	
		
	}
	
	
}
