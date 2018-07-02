package sample;

import java.io.File;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

  @Override
  public void start(Stage primaryStage) {
    // Our root pane is a StackPane, which overlays everything on top
    StackPane root = new StackPane();

    // Set up the "stage", which is the window
    primaryStage.setTitle("Hello Dinosaur");
    primaryStage.setScene(new Scene(root, 450, 340));
    primaryStage.setFullScreen(true);

    // Create an image from a file; we will animate the ImageView object
    File file = new File("dinojump.gif");
    Image dinoImg = new Image(file.toURI().toString());
    ImageView imageView = new ImageView(dinoImg);
    root.getChildren().add(imageView);
    // TODO: different animation states based on dino (KeyFrame, switch-case)

    // The Dinosaur object holds physics state (position, velocity, gravity)
    Dinosaur dino = new Dinosaur();

    // Add a button to the pane; we will temporarily use this to test jumping
    Button button = new Button("Hop");
    root.getChildren().add(button);

    // Move the button so it doesn't overlap the image (due to the nature of StackPane)
    button.setTranslateY(200);

    // For now, we will trigger jumps using the button (instead of the spacebar)
    button.setOnAction(event -> {
      // TODO add sound when jumping
      dino.setVy(75);
    });

    // TODO bind spacebar to jump (tell the image it needs to go up)
    //EventHandler<KeyEvent> doJump = event -> {
    //  if (event.getCode() == KeyCode.SPACE) {
    //    dino.setVy(40);
    //    System.out.println("Fortnite");
    //  }
    //};
    //imageView.setOnKeyPressed(doJump);

    // Update the position of the dinosaur using a keyframe that runs at 25fps
    KeyFrame keyFrame = new KeyFrame(Duration.millis(40), event -> {
      dino.update(40.0 / 100); // TODO make deltaTime more accurate
      System.out.println("dino.getY() = " + dino.getY());
      System.out.println("dino.getVy() = " + dino.getVy());
      imageView.setTranslateY(-dino.getY());
    });

    // Make a timeline that uses our keyFrame
    Timeline backgroundLoop = new Timeline(keyFrame);

    // Tell the timeline to run forever (otherwise it will only run once)
    backgroundLoop.setCycleCount(Timeline.INDEFINITE);

    // Start the timeline, which will run in the background
    backgroundLoop.play();

    // Don't forget to show() the stage! This is like the stage's "start" method
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}

class Dinosaur {
  private double y;
  private double vy;
  public static final double gravity = 9.8; // :shrug:

  public Dinosaur() {
    y = 0.0;
    vy = 0.0;
  }

  public void setVy(double vy) {
    this.vy = vy;
  }

  public double getY() {
    return y;
  }

  public double getVy() {
    return vy;
  }

  /**
   * Recomputes the y-position and y-velocity
   */
  public void update(double dt) {
    // Integrates velocity using the Verlet method
    y += (vy + dt * (gravity / 2)) * dt;
    if (y <= 0) {
      y = 0;
      vy = 0;
    } else {
      vy -= gravity * dt;
    }
  }
}