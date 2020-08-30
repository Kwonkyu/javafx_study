package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class SampleController implements Initializable{
	@FXML
	Slider sliderR;
	@FXML
	Slider sliderG;
	@FXML
	Slider sliderB;
	@FXML
	Label sliderLabel;	
	@FXML
	Circle circle;
	
	@FXML
	Slider sliderProgress;
	@FXML
	ProgressBar progressBar;

	@FXML
	TextArea textArea1;
	@FXML
	TextArea textArea2;
	
	@FXML
	Circle circleBound;
	
	ChangeListener<Number> sliderRGBChangeListener = new ChangeListener<Number>() {

		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
			int r = (int)sliderR.getValue();
			int g = (int)sliderG.getValue();
			int b = (int)sliderB.getValue();
			
			circle.setFill(Color.rgb(r, g, b));
			sliderLabel.setText(String.format("%d %d %d", r, g, b));
		}
		
	};


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		sliderR.valueProperty().addListener(sliderRGBChangeListener);
		sliderG.valueProperty().addListener(sliderRGBChangeListener);
		sliderB.valueProperty().addListener(sliderRGBChangeListener);
		
		// sliderProgress.valueProperty().bind(progressBar.progressProperty());
		progressBar.progressProperty().bind(sliderProgress.valueProperty());
		
		textArea1.textProperty().bindBidirectional(textArea2.textProperty());
	}
	
	
}
