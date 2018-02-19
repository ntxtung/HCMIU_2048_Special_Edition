package server;

public class Player implements Comparable<Player> {
	private String username;
	private String ip;
	private SocketThread handingThread;
	
	public Player (String username, String ip, SocketThread handingThread) {
		this.username = username;
		this.ip = ip;
		this.handingThread = handingThread;
	}
	
	public String getUsername() 				{return username;}
	public void setUsername(String username)    {this.username = username;}
	
	public String getIP() 						{return ip;}
	public void setIP(String ip) 				{this.ip = ip;}
	
	public Thread getHandingThread() 			{return handingThread;}
	public void setHandingThread(SocketThread thread) {this.handingThread = thread;}

	
	@Override
	public int compareTo(Player anotherPlayer) {
		return username.compareTo(anotherPlayer.username);
	}
}
