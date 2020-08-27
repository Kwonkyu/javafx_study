package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class SampleController implements Initializable {
	@FXML
	private ContextMenu popup; 
	
	@FXML
	private VBox vbox;
	
	@FXML
	private ImageView imageView;
	
	@FXML
	public void handleExit(ActionEvent event) {
		Platform.exit();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// with Initializable interface, we can define pop-up menu elsewhere.
		
		vbox.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// show context menu on vbox too.
				if (event.getButton() == MouseButton.SECONDARY) {
					popup.show(vbox, event.getScreenX(), event.getScreenY());
				}
			}
			
		});
		
	}
	
	
}
