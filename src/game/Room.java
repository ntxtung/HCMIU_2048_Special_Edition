package game;

import java.util.ArrayList;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.SimpleStringProperty;

public class Room extends RecursiveTreeObject<Room> {
	
	SimpleStringProperty id;
    SimpleStringProperty name;
    SimpleStringProperty players;
    SimpleStringProperty status;
    int maxPlayers = 1;
    
    private ArrayList<Player> playerList = new ArrayList<>();
 
    public Room(int id, String name, int maxPlayers) {
    	this.maxPlayers = maxPlayers;
        this.id 	 = new SimpleStringProperty(""+id);
        this.name 	 = new SimpleStringProperty(name);
        this.players = new SimpleStringProperty("0/"+ maxPlayers);
        this.status  = new SimpleStringProperty("Waiting");
    }
 
    public String getId() {
        return id.get();
    }
    public void setId(String id) {
        this.id.set(id);
    }
    
    public String getName() {
        return name.get();
    }
    public void setName(String fName) {
        name.set(fName);
    }
    
    public String getPlayers() {
        return players.get();
    }
    public void setPlayer(int playerCount) {
        this.players.set("" + playerCount + "/" + maxPlayers);
    }
    
    public String getStatus() {
        return status.get();
    }
    public void setStatus(String status) {
        this.status.set(status);
    }
    
}
