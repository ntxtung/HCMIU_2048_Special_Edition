package game;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Player extends RecursiveTreeObject<Player> {
	
	SimpleIntegerProperty id;
    SimpleStringProperty  name;
    SimpleIntegerProperty level;
 
    public Player(int id, String name, int level) {
        this.id 	 = new SimpleIntegerProperty(id);
        this.name 	 = new SimpleStringProperty(name);
        this.level   = new SimpleIntegerProperty(level);
    }
 
    public Integer getId() {
        return id.get();
    }
    public void setId(Integer id) {
        this.id.set(id);
    }
    
    public String getName() {
        return name.get();
    }
    public void setName(String fName) {
        name.set(fName);
    }
    
    public Integer getLevel() {
        return level.get();
    }
    public void setLevel(Integer level) {
        this.level.set(level);
    }
        
	
}
