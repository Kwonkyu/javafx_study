package application;
	
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ListView.EditEvent;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;


public class Main extends Application {	
	@Override
	public void start(Stage primaryStage) {
		try {
			// BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Sample.fxml"));
			VBox root = new VBox(10);
			
			ListView<String> listViewVertical = new ListView<>();
			ListView<String> listViewHorizontal = new ListView<>();
			ListView<String> listViewSelectedItems = new ListView<>();
			ListView<String> listViewSelectedItem = new ListView<>();
			listViewHorizontal.setOrientation(Orientation.HORIZONTAL);
			
			ComboBox<String> comboBoxItems = new ComboBox<>();
			ComboBox<String> comboBoxItemsEditable = new ComboBox<>();
			
			Label label = new Label("COLOR COMBINATION.");
			
			// sync data model with observable list and table view.
			// https://jinseongsoft.tistory.com/195 maybe check out here.
			ObservableList<IDS> IDSItems = FXCollections.observableArrayList(new IDS("Snort", 9.7, true),
					new IDS("Suricata", 8.8, true), new IDS("Custom IDS", 0.0, false));
			TableView<IDS> tableView = new TableView<IDS>();
			tableView.setEditable(true);
			// generic type for table view and type of items in cell.
			TableColumn<IDS, String> nameColumn = new TableColumn<>("name");
			TableColumn<IDS, String> resultColumn = new TableColumn<>("result");
			TableColumn<IDS, String> isActiveColumn = new TableColumn<>("active");
			tableView.getColumns().addAll(nameColumn, resultColumn, isActiveColumn);
			
			root.getChildren().addAll(listViewVertical, listViewHorizontal, listViewSelectedItems,
					listViewSelectedItem, comboBoxItems, comboBoxItemsEditable, label, tableView);
			
			// if it has enough space, expand list to vertical direction.
			VBox.setVgrow(listViewVertical, Priority.SOMETIMES);
			
			// set items to ListView by implementing ObservableList interface.
			ObservableList<String> colorItems = FXCollections.observableArrayList(
					"Red", "Green", "Blue", "Cyan", "Black", "Magenta");
			listViewVertical.setItems(colorItems);
			listViewHorizontal.setItems(colorItems);
			comboBoxItems.setItems(colorItems);
			comboBoxItemsEditable.setItems(colorItems);
			comboBoxItemsEditable.setEditable(true);
			
			// it doesn't mean lists cells are editable!
			listViewHorizontal.setEditable(true);
			listViewHorizontal.setCellFactory(TextFieldListCell.forListView());
			listViewHorizontal.setOnEditCommit(new EventHandler<ListView.EditEvent<String>>() {

				@Override
				public void handle(EditEvent<String> event) {
					// TODO Auto-generated method stub
					int where = event.getIndex();
					event.getSource().getItems().set(where, event.getNewValue());
				}
				
			});
			
			listViewSelectedItems.setCellFactory(new Callback<ListView<String>, ListCell<String>>(){
				@Override
				public ListCell<String> call(ListView<String> list){
					return new ColorCell();
				}
			});
			
			MultipleSelectionModel<String> singleSelectModel = listViewVertical.getSelectionModel();
			MultipleSelectionModel<String> multiSelectModel = listViewVertical.getSelectionModel();
			multiSelectModel.setSelectionMode(SelectionMode.MULTIPLE);
			
			singleSelectModel.selectedItemProperty().addListener(new ChangeListener<String>() {

				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					// TODO Auto-generated method stub
					System.out.println("oldValue: " + oldValue + ", newValue: " + newValue);
					ObservableList<String> selectedItem = FXCollections.observableArrayList(newValue);
					listViewSelectedItem.setItems(selectedItem);
				}
				
			});
			
			ObservableList<String> selectedItems = multiSelectModel.getSelectedItems();
			selectedItems.addListener(new ListChangeListener<String>() {

				@Override
				public void onChanged(Change<? extends String> c) {
					ObservableList<String> selectedItemsList = FXCollections.observableArrayList(c.getList());
					// listViewSelectedItems.setItems((ObservableList<String>) c.getList());
					listViewSelectedItems.setItems(selectedItemsList);
				}
				
			});
			
			EventHandler<ActionEvent> comboBoxEventHandler = new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					String stringA = comboBoxItems.getSelectionModel().getSelectedItem();
					// String stringB = comboBoxItemsEditable.getSelectionModel().getSelectedItem();
					String stringB = comboBoxItemsEditable.getValue();
					label.setText(stringA + ", " + stringB);
				}
				
			};
			
			// comboBoxItems.getSelectionModel().select(0);
			comboBoxItemsEditable.getSelectionModel().select(1);
			comboBoxItems.setOnAction(comboBoxEventHandler);
			comboBoxItemsEditable.setOnAction(comboBoxEventHandler);
			
			// model data class(IDS) must be public! something with Java's Reflection...
			nameColumn.setCellValueFactory(new PropertyValueFactory<IDS, String>("name"));
			resultColumn.setCellValueFactory(new PropertyValueFactory<IDS, String>("result"));
			isActiveColumn.setCellValueFactory(new PropertyValueFactory<IDS, String>("active"));
			tableView.setItems(IDSItems);
			
			Callback<TableColumn<IDS, String>, TableCell<IDS, String>> nameFactory = TextFieldTableCell.forTableColumn();
			nameColumn.setCellFactory(nameFactory);
			nameColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<IDS,String>>() {

				@Override
				public void handle(CellEditEvent<IDS, String> event) {
					// TODO Auto-generated method stub
					IDS newIDS = (IDS)event.getTableView().getItems().get(event.getTablePosition().getRow());
					newIDS.setName(event.getNewValue());
				}
			});
			
			Scene scene = new Scene(root,0,0);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Showing items");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
