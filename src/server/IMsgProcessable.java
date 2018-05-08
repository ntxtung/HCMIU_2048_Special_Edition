package server;

public interface IMsgProcessable {
	void onReceivedMsgFromClient(PlayerThread playerThread, String msg);
}
