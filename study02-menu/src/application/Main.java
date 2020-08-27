package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Sample.fxml"));
			/*
			// code version of menu, menu item implementation.
			root.setPrefSize(300, 100);
			MenuBar bar = new MenuBar();
			root.setTop(bar); // BorderPane has 5 sub containers. Top, Bottom, Left, Right, Center.
			
			// '_' in front of character sets mnemonic for menu item(combination with 'Alt' key).
			Menu fileMenu = new Menu("_File");
			Menu editMenu = new Menu("_Edit");
			
			bar.getMenus().addAll(fileMenu, editMenu);
			
			// add sub-menu in menu
			Menu subMenu = new Menu("SubMenu");
			fileMenu.getItems().addAll(subMenu);
			*/
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			primaryStage.setTitle("Menu Application");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
