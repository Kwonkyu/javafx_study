# javafx_study
build application with javafx.

### [ Day 1 ]
**JavaFX**
- JavaFX는 'Composite' 디자인 패턴을 사용하여 GUI 요소들의 구조를 구축한다.
- GUI 응용 프로그램의 이벤트 핸들링은 JavaFX에서 'event handler' 인터페이스를 구현하여 처리된다.
- 이벤트들은 이벤트 큐에 저장되어 JavaFX Application Thread라는 하나의 스레드에 의해 관리된다. 그렇기 때문에 blocking 상태를 일으킬 수 있는 작업(네트워크 통신, 파일 입출력 등)을 이 스레드에서 처리할 경우 프로그램 전체가 동작을 멈출 수 있다.
- 'Scene graph'란 3-D CG에서 사용되는 단어로 화면에 나타나는 모든 요소들을 트리 구조로 나타낸 것이다. JavaFX에서는 루트 요소가 'javafx.scene.Scene' 클래스인 트리 구조로 나타내어진다. 자식 노드들은 container, panes, nodes 등이 해당된다.
- 모든 JavaFX 응용 프로그램은 'javafx.application.Application' 클래스의 자식 클래스로 정의된다. 이 'Application' 클래스는 추상 클래스로 'start()' 메소드를 재정의해야 하며 보통 GUI 초기 설정을 이 메소드에서 수행하게 된다.
- JavaFX에서 각각의 윈도우는 'javafx.stage.Stage' 클래스로 구현된다. 즉 Stage - Scene - Container 순으로 응용 프로그램 구조가 구축된다. 이는 직접 Composite 디자인 패턴을 적용하며 코딩하거나 FXML를 통해 수행할 수 있다.
- JavaFX의 그리기 요소는 'java.scene.Node' 클래스에서 상속받은 클래스들로 이루어진다. 크게 다음과 같은 종류가 있다.
  - Parts: labels, buttons... at 'javafx.scene.control.Control'
  - Shape: circle, rectangle... at 'javafx.scene.shape.Shape'
  - Container: element containers... at 'javafx.scene.Parent'
  - 'Shape', 'Parent' 클래스는 'Node' 클래스에서 직접 상속받은 클래스지만 'Control' 클래스는 StackPane 등을 다루는 'Region' 클래스에서 상속받았다.
  - 이는 'Parent' 클래스의 자식 클래스로 'Control' 클래스가 다른 요소들을 담는 컨테이너가 될 수 있다는 것을 의미한다.
  - 이 클래스들은 모두 추상 클래스로 실제로 사용하게 될 'Label', 'Rectangle' 클래스들은 이들을 상속받은 클래스다.

**FXML**
- FXML에서 클래스의 이름은 각 XML 요소의 이름에 해당한다. 이를 사용하기 위해서는 `<?import ...?>` 처럼 클래스를 import 할 필요가 있다.
- 노드의 크기는 루트 요소의 컨테이너 레이아웃에 의해 결정되며 루트 요소는 prefWidth, prefHeight 속성으로 크기를 지정할 수 있다.
- SceneBuilder에서 루트 요소를 'wrap-in'하면 새로운 컨테이너로 루트 요소를 씌움으로써 루트 요소의 타입을 바꿀 수 있다(ex: AnchorPane -> VBox).
- 루트 요소의 크기가 지정되지 않는다면 SceneBuilder에서 아무것도 볼 수 없기 때문에 Layout 설정에서 prefHeight, prefWidth 속성을 설정해줘야 한다.
- 이런 변화들은 저장 시 FXML 파일에 자동으로 적용된다.
- VBox나 HBox같은 컨테이너를 사용할 때 Properties 설정에서 Alignment 속성을 변경함으로써 해당 컨테이너의 요소들이 어떻게 배치될지 지정할 수 있다. Layout 설정에서 spacing 속성을 변경함으로써 요소들 간 간격도 지정할 수 있다.
- TextField 컨트롤에서 'Pref Column Count' 속성을 설정함으로써 기본 가로 크기를 지정할 수 있다.

### [ Day 2 ]

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

