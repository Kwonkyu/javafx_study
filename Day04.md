# [ Day 4 ]

**JavaFX**

- 리스트 항목들을 나열할 방향(가로/세로)을 지정하려면 setOrientation() 메소드를 활용하거나 orientation 속성을 HORIZONTAL로 변경하면 된다(기본 VERTICAL).
- 리스트 항목에 대해 선택 이벤트 처리는 ListView 클래스가 아닌 `javafx.scene.control.MultipleSelectionModel` 클래스에서 처리한다. 이는 ListView 객체에 대하여 getSelectionModel() 메소드를 사용하여 얻을 수 있다.
- 모델은 기본적으로 단일 선택이며 다중 선택 모드로 전환하기 위해 `javafx.scene.control.SelectionMode` Enum에서 SINGLE, MULTIPLE 값을 적용한다.
- 단일 선택의 경우 getSelectedItem() 메소드에서 항목을 가져올 수 있으며 getSelectedIndex()로 항목의 인덱스를 가져올 수 있다.
- 다중 선택의 경우 getSelectedItems() 메소드로 가져올 수 있으며 ObservableList로 반환된다. 인덱스의 경우 getSelectedIndices()로 ObservableList 형식으로 반환받을 수 있다.
- 다중 선택 상황에서 getSelectedItem() 메소드를 호출하면 마지막으로 선택한 항목을 가져온다. getSelectedIndex()도 동일하다.
- 다중 선택의 경우 반환받은 ObservableList에 대하여 addListener() 메소드로 이벤트 핸들러를 구현할 수 있으나 단일 선택의 경우 항목 자체를 반환받기 때문에 이에 대해 이벤트 핸들러를 구현하려면 다른 속성을 사용해야 한다.
- JavaFX의 클래스들은 대부분 xProperty 라는 이름을 가진 메소드로 요소들의 속성을 얻을 수 있다. 예를 들어 다중 선택된 항목들의 속성을 구하려면 MultiSelectionModel 클래스의 selectedItemProperty() 라는 메소드를 사용할 수 있다.
- 이를 이용하여 `javafx.beans.value.ChangeListener` 인터페이스를 이용하여 처리할 수 있다. 선택된 요소의 속성에 접근하기 위해 selectedItemProperty() 메소드를 사용한 후 addListener() 메소드를 통해 ChangeListener 핸들러를 등록해주면 된다.
- ListView는 리스트 항목 각각을 셀로 취급할 수 있는데 셀은 `javafx.scene.control.Cell` 클래스의 하위 클래스(기본 ListCell)로 다룰 수 있다.
- 리스트의 항목들을 편집하기 위해서는 리스트 항목들의 셀을 `javafx.scene.control.cell.TextFieldListCell` 클래스로 지정해야 한다.
- 하지만 ListView 클래스에는 셀을 등록하는 메소드가 정의되어 있지 않기 때문에 ListCell 객체를 생성하는 팩토리 클래스를 지정하여 사용한다.
  - ListView 클래스의 setEditable() 메소드를 true로 설정한다.
  - ListView 클래스의 setCellFactory() 메소드를 이용하여 `TextFieldListCell.forListView()` 로 셀의 팩토리를 지정한다.
  - 이후 항목의 편집이 완료됐을 때 결과를 반영하기 위해 해당 이벤트에 대한 핸들러를 setOnEditCommit() 메소드를 이용하여 등록한다.
  - 이벤트 핸들러에서는 해당 EditEvent가 발생한 곳, 즉 편집된 항목의 인덱스를 구해서 리스트에 반영해야 한다. 이는 이벤트에서 getSource(), getItems() 메소드 등을 활용하여 원본 자료구조를 참조한 후 수정할 수 있다.
- ListCell의 서브 클래스를 직접 구현하여 클래스의 객체를 반환하는 팩토리를 작성할 수도 있다.

```java
	class ColorCell extends ListCell<String>{
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
```

```
	listViewSelectedItems.setCellFactory(new Callback<ListView<String>, ListCell<String>>(){
    	@Override
        public ListCell<String> call(ListView<String> list){
    	    return new ColorCell();
        }
    });
```

- ListView 항목들의 기본 클래스인 ListCell에 대하여 하위 클래스를 재정의했을 때는 updateItem() 메소드를 재정의해야 한다. 해당 함수의 첫번째 인수는 항목에 표시할 객체, 두번째는 해당 항목이 비어있는지 여부를 나타낸다.
- ListCell 클래스는 `javafx.scene.control.Labeled` 클래스의 서브 클래스기 때문에 해당 클래스의 기능을 사용할 수 있다. 위의 예에서는 setGraphic() 함수로 텍스트에 아이콘을 붙이고 setText()로 라벨(ListCell)의 텍스트를 수정했다.
- 이후 ListView에서 셀에 대한 팩토리를 등록할 때 Callback 인터페이스로 call() 메소드를 재정의하여 ColorCell 클래스를 생성하여 반환하고 있다. 팩토리로 셀을 생성하여 채워넣을 때 call() 메소드로 받은 ListCell을 그 하위 클래스로 재정의된 ColorCell로 반환하여 셀을 ColorCell로 채워넣는 것 같다. ColorCell에서는 updateItem() 메소드를 재정의하여 셀의 내용을 setGraphic(), setText() 메소드를 이용하여 커스터마이징한다.
- JavaFX에서 콤보 박스는 `javafx.scene.control.ComboBox` 를 이용하여 처리할 수 있다.
- 콤보 박스의 항목들은 리스트와 동일하게 ObservableList로 처리되며 ListCell 인터페이스를 이용하여 항목을 사용자 정의할 수 있다.
- 콤보 박스는 단일선택 모드만 지원한다. 항목을 편집할 때 리스트처럼 팩토리를 재지정할 필요가 없다.
- 리스트와 비슷하게 SelectionModel을 얻을 수 있으며 모델에 대해 select() 메소드를 사용하여 항목을 선택할 수 있다.
- 콤보 박스는 항목들이 선택되면 ActionEvent가 발생하므로 이벤트 처리를 위해 setOnAction() 메소드에 EventHandler 핸들러를 전달한다.
- 이벤트 처리 시 선택된 항목값을 구하려면 getValue() 메소드를 사용하거나 getSelectionModel()로 모델을 구해서 리스트처럼 getSelectedItem() 메소드를 활용할 수도 있다.
- 테이블(표)은 `javafx.scene.control.TableView` 클래스로 구현할 수 있다.
- 테이블의 열은 `javafx.scene.control.TableColumn` 클래스, 행은 `javafx.scene.control.TableRow` 클래스를 사용하여 표현한다. 그러나 TableRow 클래스는 대부분 사용되지 않으며 TableView는 보통 TableColumn들로 이루어져 있기 때문에 이 클래스에 신경써야 한다.
- TableColumn 생성자에 문자열을 인수로 넘기면 열의 제목이 된다. 이들은 TableView 객체에 getColumns() 메소드로 얻은 열 목록에 addAll() 메소드 등으로 추가되어야 테이블이 표시될 수 있다. 제너릭 인수로는 항목의 유형, 셀에 표시할 데이터 형식을 넘겨준다.
- 클래스의 멤버와 컬럼을 맵핑하기 위해 TableColumn 클래스의 setCellValueFactory() 메소드를 이용할 수 있는데 이는 ListView에서 봤던 Callback 인터페이스와 인수의 형태가 동일하다. TableView에서는 대표적으로 `javafx.scene.control.cell.PropertyValueFactory`를 사용할 수 있다.
- PropertyValueFactory는 두 개의 제너릭 매개변수를 받는데 전자는 Java Beans의 유형, 후자는 테이블에 표시할 데이터 형식을 의미한다.
- 이전과 비슷하게 ObservableList 객체에 대하여 setItems() 메소드로 항목을 지정해줄 수 있다.



**FXML**

- 리스트 항목들을 FXML에서 기술할 때는 <ListView> - <Items> - <FXCollections> 내에 <String> 요소를 삽입할 수 있다.
- 이는 Java 코드에서 ObservableList 객체로 FXCollections 클래스의 observableArrayList() 메소드를 활용하는 것과 동일하다.
- <FXCollections> 요소에는 fx:factory 속성이 "observableArrayList"로 지정되어 있어야 한다.
- <String> 요소에는 fx:value 속성으로 값을 지정하고 있다(Java에서 String은 불변객체).
- 편집가능한 리스트 요소를 구현하려면 ListView 하위 요소에 cellFactory 요소를 구현해야 한다. 이 경우 다음과 같이 지정할 수 있다.

```java
<ListView ... >
    <cellFactory>
    	<TextFieldListCell fx:factory="forListView"/>
	</cellFactory>
</ListView>
```

- 콤보 박스를 구현하려면 다음과 같이 작성할 수 있다.

```
<ComboBox ...>
	<items>
		<FXCollections ...>
			<String fx:value="Serif" />
			<String fx:value="Sans Serif" />
		</FXCollections>
	</items>
</ComboBox>
```

- 테이블을 구현하려면 다음과 같다.

```
<TableView ...>
	<columns>
		<TableColumn ...>
		<TableColumn ...>
		<TableColumn ...>
	</columns>
</TableView>
```



