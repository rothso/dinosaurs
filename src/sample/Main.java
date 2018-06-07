package sample;

import java.io.File;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

  @Override
  public void start(Stage primaryStage) {
    StackPane root = new StackPane();
    primaryStage.setTitle("Hello Dinosaur");
    primaryStage.setScene(new Scene(root, 450, 340));
    primaryStage.setFullScreen(true);

    // Create an image from the dinosaur gif
    File file = new File("dinojump.gif");
    Image dino = new Image(file.toURI().toString());
    ImageView imageView = new ImageView(dino);

    // Bind spacebar to jump
    EventHandler<KeyEvent> doJump = new EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent event) {
        // TODO!
      }
    };
    imageView.setOnKeyPressed(doJump);

    // Add the gif to a pane
    root.getChildren().add(imageView);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
