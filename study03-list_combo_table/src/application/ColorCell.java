package application;

import javafx.scene.control.ListCell;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ColorCell extends ListCell<String>{
	@Override
	public void updateItem(String item, boolean empty) {
		super.updateItem(item, empty);
		
		if(!empty) {
			Rectangle rect = new Rectangle(20, 20);
			rect.setFill(Color.web(item));
			setGraphic(rect);
			setText(item);
		}
	}
}