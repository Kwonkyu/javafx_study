package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

public class SampleController implements Initializable {
	private Image image = null;
	
	private final FileChooser fileChooser = new FileChooser();
	
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
	
	@FXML
	public void handleOpen(ActionEvent event) {
		try {
			// https://stackoverflow.com/questions/13585590/how-to-get-parent-window-in-fxml-controller
			// File file = fileChooser.showOpenDialog(((MenuItem)event.getTarget()).getParentPopup().getOwnerWindow());
			File file = fileChooser.showOpenDialog(null);
			if (file != null) {
				// File.toURL() is deprecated!!
				image = new Image(file.toURI().toURL().toString());
				imageView1.setImage(image);
				imageView2.setImage(image);
				imageView3.setImage(image);
				imageView4.setImage(image);
			}
		} catch (MalformedURLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// generate extension filter for FileChooser and apply.
		FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Images", "*.jpg", "*.gif", "*.png");
		fileChooser.getExtensionFilters().add(filter);
		
		exitMenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl+Q"));
		exitMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				handleExit(event);
			}
		});
		
		newFileMenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl+O"));
		newFileMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				handleOpen(event);
			}
		});
		
		
		
		vbox.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getButton() == MouseButton.SECONDARY) {
					// popup.show(vbox, event.getScreenX(), event.getScreenY());
					popup.show(((Node)event.getTarget()).getParent(), event.getScreenX(), event.getScreenY());
					// currently, context menu is hard coded in FXML.
				} else {
					popup.hide();
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
