package game;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.util.Callback;

public class MainFormController implements Initializable {
	
	private ObservableList<Room> roomsList;
	
	final ObservableList<Room> data = FXCollections.observableArrayList(
		    new Room(1, "Room 1", 4),
		    new Room(2, "Room 2", 2)
		);
	
	@FXML
    private JFXTreeTableView<Room> tbRooms;

    @FXML
    private JFXTreeTableView<Player> tbPlayers;

    @FXML
    private void onCreateRoom(ActionEvent event) {
    	roomsList.get(1).setPlayer(2);
    }

    @FXML
    private void onJoinRoom(ActionEvent event) {
    	roomsList.remove(1);
    }
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		JFXTreeTableColumn<Room, String> idCol = new JFXTreeTableColumn<>("ID");
		idCol.setPrefWidth(50);
		idCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Room,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(CellDataFeatures<Room, String> param) {
				return param.getValue().getValue().id;
			}
		});
		JFXTreeTableColumn<Room, String> nameCol = new JFXTreeTableColumn<>("Name");
		nameCol.setPrefWidth(250);
		nameCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Room,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(CellDataFeatures<Room, String> param) {
				return param.getValue().getValue().name;
			}
		});
		JFXTreeTableColumn<Room, String> playerCol = new JFXTreeTableColumn<>("Players");
		playerCol.setPrefWidth(60);
		playerCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Room,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(CellDataFeatures<Room, String> param) {
				return param.getValue().getValue().players;
			}
		});
		JFXTreeTableColumn<Room, String> statusCol = new JFXTreeTableColumn<>("Status");
		statusCol.setPrefWidth(100);
		statusCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Room,String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Room, String> param) {
				return param.getValue().getValue().status;
			}
		});
		
		roomsList = FXCollections.observableArrayList();
		roomsList.add(new Room(1, "Room 1", 4));
		roomsList.add(new Room(2, "Room 2", 8));
		
		TreeItem<Room> root = new RecursiveTreeItem<>(roomsList, RecursiveTreeObject::getChildren);
		
		tbRooms.getColumns().setAll(idCol, nameCol, playerCol, statusCol);
		tbRooms.setRoot(root);
		tbRooms.setShowRoot(false);
		
	}

}
