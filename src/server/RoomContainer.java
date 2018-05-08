package server;

import java.util.ArrayList;

public class RoomContainer {
	
	private static ArrayList<Room> rooms = new ArrayList<>();
	private static int roomId = 0;
	
	private static RoomContainer instance;
	
	public static RoomContainer getInstance() {
		if (instance == null)
			instance = new RoomContainer();
		return instance;
	}
	
	public static Room createNewRoom(String roomName, int maxPlayer) {
		Room newRoom = new Room(roomId++, roomName, maxPlayer);
		rooms.add(newRoom);
		return newRoom;
	}
	
	public static void deleteRoom(int roomId) {
		for (Room r : rooms) {
			if (r.getRoomId() == roomId) {
				rooms.remove(r);
			}
		}
	}
	
	public static Room findRoom(int roomId) {
		for (Room r : rooms) {
			if (r.getRoomId() == roomId) 
				return r;
		}
		return null;
	}

	public static String getRoomList() {
		String roomList = "";
		for (Room room : rooms) {
			roomList += room.getRoomId() + "@@" + room.getRoomName() + "@@" + room.getMaxPlayer() + "@@";
		}
		return roomList;
	}
	
	public static Room wannajoin(PlayerThread playerThread, int roomId) {
		Room roomWannaJoin = instance.findRoom(roomId);
		if (roomWannaJoin != null) {
			roomWannaJoin.onReceivedMsgFromClient(playerThread, "wannajoin");
		} else {
			playerThread.sendInfoMsg("The room is no longer available!");
		}
		return roomWannaJoin;
		//feedback(String.format("acceptjoin@@%s", roomId));	
	}
	
}
