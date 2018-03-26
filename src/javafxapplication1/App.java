
package javafxapplication1;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class App extends Application{
    Text te = new Text(300,300,"2048");
    Game g = new Game();
    @Override
    public void start(Stage primaryStage) {
        g.addNum();
        BorderPane root2 = new BorderPane();
        root2.getChildren().add(g.getImage());
        Scene scene = new Scene(root2, 800, 800);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event) {
                
                if(g.isover()){
                    root2.getChildren().remove(0);  
                    root2.getChildren().add(g.getGameoverImage()); 
                }else{
                    if(event.getCode()== KeyCode.LEFT)
                {
                    String olds = g.statestring();
                    g.pushUp();
                    String news = g.statestring();
                    if(!olds.equals(news))
                        g.addNum();       
                    root2.getChildren().remove(0);  
                    root2.getChildren().add(g.getImage());
                } 
                if(event.getCode()== KeyCode.DOWN)
                {
                    String olds = g.statestring();
                    g.pushright();
                    String news = g.statestring();
                    if(!olds.equals(news))
                        g.addNum();        
                    root2.getChildren().remove(0);  
                    root2.getChildren().add(g.getImage());
                } 
                if(event.getCode()== KeyCode.RIGHT)
                {
                    String olds = g.statestring();
                    g.pushdown();
                    String news = g.statestring();
                    if(!olds.equals(news))
                        g.addNum();        
                    root2.getChildren().remove(0);  
                    root2.getChildren().add(g.getImage());
                } 
                if(event.getCode()== KeyCode.UP)
                {
                    String olds = g.statestring();
                    g.pushleft();
                    String news = g.statestring();
                    if(!olds.equals(news))
                        g.addNum();         
                    root2.getChildren().remove(0);  
                    root2.getChildren().add(g.getImage());  
                }
                }
            }
        });
        root2.getChildren().removeAll();
        primaryStage.setTitle("2048");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
  
   
    public static void main(String[] args) {
        launch(args);
    }

}
