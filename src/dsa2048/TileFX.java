package dsa2048;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class TileFX extends Tile{

	private StackPane title;
	private Rectangle rect;
	private Text text;

	public TileFX(int x, int y, Integer value) {
		super(x, y, value);

		initTile();

		rect.getStyleClass().clear();
		rect.getStyleClass().add("title");

		text.setText(super.getValue().toString());
		text.setFont(new Font(40));
		text.setFill(Color.WHITE);
		title.getChildren().addAll(rect, text);
	}

	public void initTile() {
		title = new StackPane();
		rect = new Rectangle();
		text = new Text();

		Double size = 130.0;
		rect.setWidth(size);
		rect.setHeight(size);

		text.setTextAlignment(TextAlignment.CENTER);
	}

	public StackPane getTitle() {
		return title;
	}

	public void updateTitle() {
	}
}
