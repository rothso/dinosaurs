package sample;

import java.io.File;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

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

    // Bind spacebar to jump (tell the image it needs to go up)
    EventHandler<KeyEvent> doJump = new EventHandler<KeyEvent>() {
      int dy = 0; // TODO move to the outside so the backgroundLoop can access it

      @Override
      public void handle(KeyEvent event) {
        if (event.getCode() == KeyCode.SPACE) {
          imageView.setTranslateY(dy); // TODO actually think about this
        }
      }
    };
    imageView.setOnKeyPressed(doJump);

    // The background loop will emulate gravity (what goes up must go down)
    KeyFrame keyFrame = new KeyFrame(Duration.millis(10), event -> {
      double currentY = imageView.getY();
      // TODO update the new y-position of the image based on dy
    });
    Timeline backgroundLoop = new Timeline(); // TODO configure the timeline and start() it

    // Add the gif to a pane
    root.getChildren().add(imageView);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
