package dsa2048;

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
		if (this.value > 0) {
			text.setText(this.value.toString());
		} else {
			text.setText("");
		}

		this.updateTitle();
	}
}
