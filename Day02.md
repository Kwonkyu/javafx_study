# [ Day 2 ]

**JavaFX**

- 'Node' 클래스는 거의 모든 그리기 요소의 부모 클래스로 이 클래스에서 정의된 기능은 그리기 요소들에서 사용할 수 있다.
  - Event Handling: Mouse click, keyboard input...(ex: setOnMouseClicked() to set mouse click event handler)
  - Drag & Drop
  - CSS: 각각의 Node에 따로 적용될 수 있는데 setStyle() 메소드로 '-fx-'가 붙은 CSS 스타일을 적용한다(ex: button.setStyle("**-fx-**font-size: 24pt;")).
  - Transformation: Movement, Scale, Rotation...(ex: setScaleX())
  - Effect: Shadows, Blur... 이는 'Effect'란 클래스로 Node에 나타낼 효과를 정의한다(ex: DropShadow() class).
- 이런 그리기 요소들을 'Controls'라 하며 MenuBar, ImageView, ScrollPane, FileChooser 등이 있다.
- JavaFX에서 메뉴는 메뉴바와 팝업 메뉴 두 가지로 이루어져 있는데 각각 MenuBar, ContextMenu 클래스로 나타낼 수 있다. 메뉴 아이템들은 'MenuItem' 클래스로 나타낼 수 있다.
  - 메뉴바의 경우 일반 컨트롤로 처리되어 원하는 위치에 메뉴바를 표시 가능하다.
  - 메뉴의 순서를 정할 때는 **수평 방향**으로, **왼쪽에서 오른쪽**으로 나열한다. 수직 방향으로 나열하고 싶다면 메뉴가 아닌 **도구 모음**을 사용해야 한다.
- 메뉴를 사용하려면 VBox, HBox 같은 컨테이너가 아닌 BorderPane 등을 활용해야 한다.

**FXML**

- SceneBuilder에서는 'Controller' 설정에서 해당 FXML 파일과 컨트롤러 클래스를 연결할 수 있다. 제대로 연결되었다면 요소들의 'Code' 설정에서 'fx:id'나 'On Action' 같은 속성에 컨트롤러에서 선언된 변수나 함수를 지정할 수 있다.
- 연결된 변수들은 Document의 'Controller' 설정에서 확인할 수 있다.
- 예를 들어 Button 같은 요소에 이벤트 핸들러를 적용하려면 'On Action' 속성의 드롭 다운 바를 클릭하여 '@FXML' 어노테이션이 붙은 함수 중 적절한 함수를 해당 컨트롤의 이벤트 핸들러로 적용할 수 있다.