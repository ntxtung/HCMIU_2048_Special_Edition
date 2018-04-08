package dsa2048;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class GameplayController {
	class Title {
		private StackPane title;
		private Rectangle rect;
		private Integer value;
		private Text text;

		public Title(Integer value) {
			rect = new Rectangle();
			Double size = 130.0;
			rect.setWidth(size);
			rect.setHeight(size);
			this.value = value;

			rect.getStyleClass().clear();
			rect.getStyleClass().add("title");

			title = new StackPane();

			text = new Text();
			text.setTextAlignment(TextAlignment.CENTER);
			text.setText(this.value.toString());
			text.setFont(new Font(40));
			text.setFill(Color.WHITE);
			title.getChildren().addAll(rect, text);
		}

		public StackPane getTitle() {
			return title;
		}

		public void updateTitle() {
			switch (this.value) {
			case 2:
				rect.getStyleClass().clear();
				rect.getStyleClass().add("title");
				rect.getStyleClass().add("title-2");
				break;
			case 4:
				rect.getStyleClass().clear();
				rect.getStyleClass().add("title");
				rect.getStyleClass().add("title-4");
				break;
			case 8:
				rect.getStyleClass().clear();
				rect.getStyleClass().add("title");
				rect.getStyleClass().add("title-8");
				break;
			case 16:
				rect.getStyleClass().clear();
				rect.getStyleClass().add("title");
				rect.getStyleClass().add("title-16");
				break;
			case 32:
				rect.getStyleClass().clear();
				rect.getStyleClass().add("title");
				rect.getStyleClass().add("title-32");
				break;
			case 64:
				rect.getStyleClass().clear();
				rect.getStyleClass().add("title");
				rect.getStyleClass().add("title-64");
				break;
			case 128:
				rect.getStyleClass().clear();
				rect.getStyleClass().add("title");
				rect.getStyleClass().add("title-128");
				break;

			default:
				rect.getStyleClass().clear();
				rect.getStyleClass().add("title");
				break;
			}
		}

		public void setValue(Integer newValue) {
			this.value = newValue;
			if (this.value>0) {
				text.setText(this.value.toString());
			} else {
				text.setText("");
			}
			
			this.updateTitle();
		}
	}

	class TitleList {
		private Title[][] listOfTitles = new Title[4][4];

		public TitleList() {
			for (int i = 0; i < paneGame.getRowCount(); i++) {
				for (int j = 0; j < paneGame.getColumnCount(); j++) {
					listOfTitles[i][j] = new Title(GameplayContainer.getGameTable().get(i, j));
				}
			}
		}

		public Title get(int row, int col) {
			return listOfTitles[row][col];
		}

		public void update() {
			for (int i = 0; i < paneGame.getRowCount(); i++) {
				for (int j = 0; j < paneGame.getColumnCount(); j++) {
					listOfTitles[i][j].setValue(GameplayContainer.getGameTable().get(i, j));
				}
			}
		}

	}

	@FXML
	private Pane paneMain;
	@FXML
	private GridPane paneGame;
	@FXML
	private Pane paneScoreCurrent;
	@FXML
	private Pane paneScoreBest;
	@FXML
	private Text scoreCurrent;
	@FXML
	private Text scoreBest;
	@FXML
	private Text textScoreCurrent;
	@FXML
	private Text textScoreBest;
	@FXML
	private Text gameTitle;
	@FXML
	private Button btnUndo;
	@FXML
	private Button btnRestart;

	private TitleList tiList;

	@FXML
	public void initialize() {
		GameplayContainer.initialize();
		tiList = new TitleList();
		this.render();
	}

	public void render() {
		tiList.update();
		paneGame.getChildren().clear();
		Pane pn = new Pane();
		for (int i = 0; i < paneGame.getRowCount(); i++) {
			for (int j = 0; j < paneGame.getColumnCount(); j++) {
				paneGame.add(tiList.get(i, j).getTitle(), j, i);
			}
		}
	}

	@FXML
	public void onKeyboardPressed(KeyEvent e) {
		GameplayContainer.pushTable();
		if (e.getCode() == KeyCode.W) {
			GameplayContainer.getGameTable().pushUp();
		} else if (e.getCode() == KeyCode.A) {
			GameplayContainer.getGameTable().pushLeft();
		} else if (e.getCode() == KeyCode.S) {
			GameplayContainer.getGameTable().pushDown();
		} else if (e.getCode() == KeyCode.D) {
			GameplayContainer.getGameTable().pushRight();
		}

		GameplayContainer.getGameTable().generateNumber();
//		GameplayContainer.getGameTable().consoleDisplay();
		this.render();
		if (GameplayContainer.getGameTable().isOver()) {
			Alert al = new Alert(AlertType.INFORMATION);
			al.setTitle("2048 - GameOver");
			al.setContentText("GAME OVER!");
			al.show();
		}
//		System.out.println();
	}
	
	@FXML
	public void onRestartButtonClicked() {
		GameplayContainer.restart();
		this.render();
	}
	
	@FXML
	public void onUndoButtonClicked() {
		GameplayContainer.undo();

//		GameplayContainer.getGameTable().consoleDisplay();
		this.render();
	}

}
