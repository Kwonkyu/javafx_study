# [ Day 5 ]

**JavaFX**

- GUI를 MVC 구조로 구축했을 때 GUI에 표시되는 정보들은 M, Model에 저장된다. 이때 모델과 뷰의 대응관계를 구축시킬 때 바인딩(binding)을 유용하게 사용할 수 있다.
- 바인딩이란 두 변수를 자동으로 동기화시키는 기구로 이를 가능케 하는것이 프로퍼티(Property)에 해당한다.
- Java에서 프로퍼티를 표현하는데에는 Java Beans를 사용했으며 JavaFX의 프로퍼티 역시 이를 기반으로 하고있다.
- JavaFX의 프로퍼티 속성은 JavaFX가 제공하는 전용 클래스를 사용하여 처리할 수 있는데 이들은 `javafx.beans.property` 패키지에 정의되어 있다.
- 일반적으로 프로퍼티 클래스는 추상 클래스이며 이에 해당하는 구현 클래스가 여러개 준비되어 있다. 모두 동일하게 접두어 'Simple'을 붙인 클래스 형태로 제공되며 그 예로 SimpleIntegerProperty, SimpleStringProperty 클래스가 존재한다.

```
IntegerProperty value = new SimpleIntegerProperty(10);
value.set(20);
value.get();
```

- 간단하게 변수에 저장하지 않고 이런 클래스를 사용하는 이유는 변수에 저장된 값에 어떤 변화가 일어났을때 이를 감지하고 처리할 수단이 필요하기 때문이다. 모델이 사용자 입력 등으로 변경되었을때 이것이 바로 GUI, 즉 화면에 나타나야 하기 때문이다.

- 모든 프로퍼티는 보유하고 있는 값이 변경되었을 때 이벤트가 발생하는데 이를 처리하기 위해 ChangeListener라는 인터페이스를 사용한다. 이는 프로퍼티에 대해 addListener() 메소드의 매개변수로 넘겨줘서 호출함으로써 등록할 수 있다.

  - 인터페이스는 changed() 메소드를 재정의하여 구현될 수 있는데 이는 ObservableValue 인터페이스, 제너릭, 제너릭 자료형 총 3개의 매개변수를 가진다.
  - 첫번째 매개변수는 프로퍼티 클래스가 구현하고 있는 인터페이스로 실제는 프로퍼티 객체가 전달된다.
  - 두번째, 세번째 매개변수는 기존값, 신규값을 담고있다. 제너릭으로 전달된 자료형이기 때문에 ChangeListener를 정의할 때 전달해 준 제너릭과 동일하다.

- 이를 활용하는 데 가장 좋은 예는 슬라이더 부품으로 슬라이더가 움직일 때마다 그 값을 라벨에 출력하는 것이다.

- 슬라이더의 값은 valueProperty() 메소드로 취득할 수 있으며 반환형식은 DoubleProperty 클래스다. 이 프로퍼티에 addListener()를 통해 다음과 같이 핸들러를 등록할 수 있다.

  ```
  slider.valueProperty().addListener(new ChangeListener<Number>() {
  	@Override
  	public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue){
  		String number = String.format("%d", newValue.intValue());
  		label.setText(number);
  	}
  });
  ```

- 위의 슬라이더처럼 프로퍼티를 처리하는 클래스를 작성할 때는 몇가지 명명 규칙이 있는데 이는 다음과 같다.

  - 각 변수에 대한 getter, setter 메소드 정의: 프로퍼티에 대해 get, set 메소드를 호출하는 방식으로 값을 적용한다.
  - 프로퍼티 x를 반환하기 위한 xProperty 메소드 정의: 이때는 단순히 프로퍼티 자체를 반환한다.
  - 이는 단순히 값에 대해 참조하는 것과 속성으로 취득하여 바인딩하는 것을 구분하여 모두 지원하기 위함이다.
  - 위에서 Slider 클래스가 자신의 값을 가리키는 프로퍼티로 valueProperty()를 사용하는 것도 위의 규칙을 따른 것이다.

- JavaFX에서 사용하는 컬렉션도 속성과 마찬가지로 변화를 감지할 수 있다. JavaFX에서는 다음과 같은 3가지의 컬렉션 인터페이스를 제공한다.

  - ObservableList
  - ObservableMap
  - ObservableSet

- Java의 일반 컬렉션 인터페이스 List, Map, Set의 하위 인터페이스로 정의되었으며 Java와는 달리 해당 인터페이스의 구현 클래스를 제공하지 않는다는 것이 특징이다. 그렇기 때문에 FXCollections 클래스를 활용하여 객체를 생성해야 한다.

- ObservableList는 observableList()나 observableArrayList()로 정의하게 되며 나머지 인터페이스들도 비슷하게 생성할 수 있다.

- 프로퍼티와 동일하게 컬렉션이 유지하고 있는 요소에 변경이 일어났을때 이를 탐지할 수 있다. ObservableList의 경우 ListChangeListener 인터페이스를 구현하여 핸들러를 적용할 수 있다.

- 핸들러는 onChanged() 메소드를 재정의하는데 이는 Change형 객체를 매개변수로 받는다. 이는 컬렉션의 요소의 변경을 탐지할 수 있는 다양한 메소드가 구현되어있다.

- Change 객체에 대하여 next() 메소드로 Iterator를 활용하듯이 요소들을 탐색하면서 wasAdded(), wasX() 등의 메소드를 적용하여 새로 추가된 요소거나 이전에 X 값이었는지 확인해볼 수 있다.

- 이를 확인해보는 가장 좋은 방법은 컬렉션에 add(), set(), addAll() 등의 메소드를 사용하여 요소에 변화를 일으켜 보는것이다.

- 바인딩이란 속성(프로퍼티)끼리 자동으로 동기화시키는 개념으로 bind() 메소드를 사용한다.

  ```
  IntegerProperty x = new SimpleIntegerProperty(0);
  IntegerProperty y = new SimpleIntegerProperty();
  
  y.bind(x);
  ```

- 이렇게 되면 y는 get() 메소드로 값을 구할 시 x와 같은 값을 반환하게 되며 x의 값이 변하면 이를 반영하게 된다. 이는 바인딩 처리 시에는 지연평가되었다가 바인딩 속성이 사용될 때 평가된다는 특징이 있다.

- A.bind(B) 같은 코드의 경우 A의 값이 B에 묶이기 때문에 A를 조작할 수 없다. 예를 들어 슬라이더가 A, Progress Bar가 B라면 슬라이더를 조작할 수 없게 된다.

- 이를 위해서 양방향 바인딩을 지원하는 메소드 bindBidirectional() 메소드를 사용할 수 있다.

- 이런 바인딩은 모델과 뷰, 컨트롤러와 모델의 대응관계에서 사용할 때 유용하다. Progress Bar를 보이는 예를 들면 다음과 같다.

  ```
  progressBar.progressProperty().bind(sliderProgress.valueProperty());
  ```

- 슬라이더는 MIN ~ MAX인데 비해 Progress Bar는 0 ~ 1 값을 가지고 있기 때문에 초기 설정으로는 값이 맞지않다. 그러므로 슬라이더를 0 ~ 100으로 설정했다면 다음과 같이 슬라이더의 값을 100으로 나눠서 바인딩하는 유틸리티 클래스 `javafx.binding.Bindings` 를 활용할 수 있다.

  ```
  value.bind(Bindings.divide(slider.valueProperty(), 100.0));
  ```

- 이런 바인딩은 고수준 바인딩으로 지연 평가를 실시하며 서로 다른 자료형의 프로퍼티 바인딩은 지원하지 않는다는 특징을 가지고 있다.

- 이를 보완하려면 저수준 바인드 API를 사용하여야 한다. 이는 바인딩 클래스의 computeValue() 메소드를 오버로딩함으로써 수행할 수 있다.

  ```
  ObjectBinding<Color> objectBinding = new ObjectBinding<Color>(){
  	{
  		super.bind(slider1.valueProperty(), slider2.valueProperty(), slider3.valueProperty());
  	}
  	
  	@Override
  	protected Color computeValue(){
  		return Color.color(slider1.valueProperty().get(), slider2.valueProperty().get(), slider3.valueProperty().get());
  	}
  }
  
  ...
  
  rectangle.fillProperty().bind(objectBinding);
  ```

- 위의 바인딩은 실제로 성공해보지 못했기 때문에 추후 실습해볼것.



**FXML**

- 
- 