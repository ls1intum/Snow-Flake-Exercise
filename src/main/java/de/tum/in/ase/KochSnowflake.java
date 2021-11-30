package de.tum.in.ase;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class KochSnowflake extends Application {

    @Override
    public void start(Stage primaryStage) {
        Point startPoint = new Point(0, 0);
        Point endPoint = new Point(1000, 0);

        Flake recursiveFlake = new Flake(startPoint, endPoint);
        recursiveFlake.recursiveSnow(startPoint, endPoint, 5);

        Flake iterativeFlake = new Flake(startPoint, endPoint);
        iterativeFlake.snow(4);

        Pane pane = new Pane();
        for (int i = 0; i < iterativeFlake.getPoints().size() - 1; i++) {
            Point p1 = iterativeFlake.getPoints().get(i);
            Point p2 = iterativeFlake.getPoints().get(i + 1);
            pane.getChildren().add(new Line(p1.getX(), 300 - p1.getY(), p2.getX(), 300 - p2.getY()));
        }
        primaryStage.setScene(new Scene(pane, 1000, 320));
        primaryStage.setTitle("Koch Snow Flake Iterative");
        primaryStage.show();
    }
}
