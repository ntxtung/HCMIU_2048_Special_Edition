package dsa2048;

import java.util.ArrayList;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class TitleFX {
	private StackPane title;
	private Rectangle rect;
	private Integer value;
	private Text text;
	private ArrayList<String> color = new ArrayList<>();

	public TitleFX(Integer value) {
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
		text.setFill(Color.BLACK);
		text.getStyleClass().add("txt-tile");
		title.getChildren().addAll(rect, text);
	}

	public StackPane getTitle() {
		return title;
	}

	public void updateTitle() {
		rect.getStyleClass().clear();
		rect.getStyleClass().add("title");
		if (this.value >= 2 && this.value <=2048)
			rect.getStyleClass().add("title-" + this.value.toString());
		text.getStyleClass().clear();
		text.getStyleClass().add("txt-tile");
		if (this.value < 8)
			text.getStyleClass().add("txt-black");
		else
			text.getStyleClass().add("txt-white");
	}

	public void setValue(Integer newValue) {
		this.value = newValue;
		if (this.value > 0) {
			text.setText(this.value.toString());
		} else {
			text.setText("");
		}

		this.updateTitle();
	}
}
