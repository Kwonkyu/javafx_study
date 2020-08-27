package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class SampleController implements Initializable {
	
	private MenuItem newFileMenuItem = new MenuItem("New File");
	private MenuItem exitMenuItem = new MenuItem("Exit");
	private ContextMenu popup = new ContextMenu(newFileMenuItem, exitMenuItem);
	
	@FXML
	private VBox vbox;
	
	@FXML
	private ImageView imageView1;
	@FXML
	private ImageView imageView2;
	@FXML
	private ImageView imageView3;
	@FXML
	private ImageView imageView4;
	
	@FXML
	public void handleExit(ActionEvent event) {
		Platform.exit();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		exitMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				handleExit(event);
			}
		});
		
		
		
		
		vbox.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// show context menu on vbox too.
				if (event.getButton() == MouseButton.SECONDARY) {
					popup.show(vbox, event.getScreenX(), event.getScreenY());
					// currently, context menu is hard coded in FXML.
				}
			}
		});
		
		try {
			// hard coded absolute path.
			Image image = new Image(new FileInputStream("C:\\Users\\park2\\source\\repos\\javafx_study\\study02-menu\\image.png"));
			imageView1.setImage(image);
			imageView2.setImage(image);
			imageView3.setImage(image);
			imageView4.setImage(image);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
