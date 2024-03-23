import javafx.application.Application;
import javafx.stage.Stage;


import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;


public class TestingApp extends Application {

    private static final double SCREEN_DIVIDER = 0.5;

    @Override
    public void start(Stage primaryStage) {
        // Create a pane for the stage
        Pane root = new Pane();

        // Define linear context menu for left side
        ContextMenu leftMenu = createLinearMenu(5);

        // Define round context menu for right side
        StackPane rightMenuPane = createRoundMenu();

        // Create a context menu to trigger the round menu
        /*
        ContextMenu rightMenu = new ContextMenu();
        rightMenu.getItems().add(new MenuItem("Show Round Menu"));
        rightMenu.setOnAction(event -> {
            Point2D clickPoint1 = new Point2D(event.getX(), event.getY()); // Use get methods from ContextMenuEvent
            rightMenuPane.setVisible(true); // Make round menu visible
            rightMenuPane.relocate(event.getX(), event.getY()); // Set position
        });

         */

        // Set click handlers for left and right side
        root.setOnContextMenuRequested(event -> {
            Point2D clickPoint = new Point2D(event.getX(), event.getY()); // Use get methods from ContextMenuEvent
            double stageWidth = primaryStage.getWidth();
            if (clickPoint.getX() < stageWidth * SCREEN_DIVIDER) {
                leftMenu.show(root, clickPoint.getX(), clickPoint.getY());
            } else {
                //rightMenu.show(root, clickPoint.getX(), clickPoint.getY());
            }
        });

        // Set the scene and stage properties
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Side Menus");
        primaryStage.setWidth(600);
        primaryStage.setHeight(400);
        primaryStage.show();
    }

    private ContextMenu createLinearMenu(int numItems) {
        ContextMenu menu = new ContextMenu();
        for (int i = 1; i <= numItems; i++) {
            MenuItem item = new MenuItem("Option " + i);
            // Add action handlers if needed
            int finalI = i;
            item.setOnAction(event -> System.out.println("Clicked option " + finalI));
            menu.getItems().add(item);
        }
        return menu;
    }

    private StackPane createRoundMenu() {
        StackPane pane = new StackPane();
        Circle circle = new Circle(25, Color.LIGHTGRAY); // Adjust radius and color
        pane.getChildren().add(circle);

        for (int i = 1; i <= 4; i++) { // 4 items for a circular layout
            double angle = Math.PI * (i * 2 - 1) / 4; // Calculate angle for each item
            double xOffset = Math.cos(angle) * 20; // Adjust offset based on radius
            double yOffset = Math.sin(angle) * 20;
            Text text = new Text(i + "");
            text.setLayoutX(xOffset);
            text.setLayoutY(yOffset);
            pane.getChildren().add(text);
        }

        pane.setVisible(false); // Initially hide the round menu
        return pane;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
