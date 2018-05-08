package server;

import java.util.ArrayList;

public class Room implements IMsgProcessable {

	private Integer roomId;
	private String 	roomName;
	private Integer maxPlayer;
	
	private ArrayList<PlayerThread> players = new ArrayList<>();
	
	public Room(int roomId, String roomName, int maxPlayer) {
		this.roomId = roomId;
		this.roomName = roomName;
		this.maxPlayer = maxPlayer;
	}
	
	public void startGame() {
		for (PlayerThread player : players) {
			player.feedback("START");
		}
	}
	
	
	@Override
	public void onReceivedMsgFromClient(PlayerThread playerThread, String msg) {
		// TODO Auto-generated method stub
		
	}
	
	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public Integer getMaxPlayer() {
		return maxPlayer;
	}

	public void setMaxPlayer(Integer maxPlayer) {
		this.maxPlayer = maxPlayer;
	}

	public ArrayList<PlayerThread> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<PlayerThread> players) {
		this.players = players;
	}



}
