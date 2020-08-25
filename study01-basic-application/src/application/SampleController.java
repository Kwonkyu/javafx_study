package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SampleController implements Initializable {
	// Model
	private Dictionary dictionary;
	@FXML
	private TextField keyField;
	@FXML
	private Label valueLabel;
	
	// Event Handler
	@FXML
	private void search(ActionEvent event) {
		String value = (String)dictionary.get(keyField.getText());
		valueLabel.setText(value);
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// initialization of model
		dictionary = new Dictionary();
	}
	
}
