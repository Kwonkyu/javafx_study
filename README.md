# javafx_study
build application with javafx.

[ Day 1 ]
- JavaFX uses 'Composite' design pattern to describe GUI elements tree structure.
- GUI application's event handling is implemented by 'event handler' interface in JavaFX. Events are stored in event queue and it's managed by single thread, which is JavaFX Application Thread.
  - If work which can cause blocking state(Network, I/O etc) is processed on this thread, the whole program might get stuck. Use asynchronous API to handle this.
- 'Scene graph' is a term used in 3D CG, describing every element in this scene as tree hierarchy. In JavaFX, it's same but root element is 'javafx.scene.Scene' class. Children nodes will be container, panes, nodes.
- Every JavaFX application is defined in children class of 'javafx.application.Application'. This 'Application' class is abstract class that 'start()' method is blank. Mostly GUI initial settings are done in this method.
- In JavaFX, a unique window is implemented in class 'javafx.stage.Stage'. So element's hierarchy looks like Application - Stage - Scene - Container.
- You can construct this hierarchy by constructing elements in composite design pattern or FXML.
 

