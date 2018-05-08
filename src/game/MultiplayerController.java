package game;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class MultiplayerController extends GameplayController{

    @FXML
    private Pane paneMain;

    @FXML
    private GridPane paneGame;

    @FXML
    private Pane paneScoreCurrent;

    @FXML
    private Text scoreCurrent;

    @FXML
    private Text textGameTitle;

}
