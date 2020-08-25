package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import java.io.IOException;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException {
		try {
			// Load the FXML file and draw scene graph based on that.
			// Currently, BorderPane is root element and container. 
			Parent root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
			// Generate 'Scene' instance. It needs root element(currently read from FXML) as argument.
			Scene scene = new Scene(root); // without width and height value, it uses pref value.
			// Apply CSS
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			// Set title of Stage instance.
			primaryStage.setTitle("Hello World!");
			// Add Scene instance to Stage instance.
			primaryStage.setScene(scene);
			// Show Stage instance.
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// Start JavaFX Application Thread(event handling thread).
		launch(args);
	}
}
