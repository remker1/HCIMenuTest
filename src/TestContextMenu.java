import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TestContextMenu extends Application {
    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, 800, 600, Color.WHITE);

        // Create the main context menu
        ContextMenu contextMenu = new ContextMenu();

        // Create submenus
        Menu firstContainer = new Menu("Submenu 1");
        Menu secondContainer = new Menu("Submenu 2");
        Menu thirdContainer = new Menu("Submenu 3");
        MenuItem forthItem = new MenuItem("Item 4");
        MenuItem fifthItem = new MenuItem("Item 5");

        MenuItem firstContainerSecondStage = new MenuItem("Item 11");
        MenuItem firstContainerSecondStage2 = new MenuItem("Item 12");
        MenuItem firstContainerSecondStage3 = new MenuItem("Item 13");
        MenuItem firstContainerSecondStage4 = new MenuItem("Item 14");
        firstContainer.getItems().addAll(
                firstContainerSecondStage,
                firstContainerSecondStage2,
                firstContainerSecondStage3,
                firstContainerSecondStage4
        );

        MenuItem secondContainerSecondStage = new MenuItem("Item 21");
        MenuItem secondContainerSecondStage2 = new MenuItem("Item 22");
        MenuItem secondContainerSecondStage3 = new MenuItem("Item 23");
        MenuItem secondContainerSecondStage4 = new MenuItem("Item 24");
        secondContainer.getItems().addAll(
                secondContainerSecondStage,
                secondContainerSecondStage2,
                secondContainerSecondStage3,
                secondContainerSecondStage4
        );

        MenuItem thirdContainerSecondStage = new MenuItem("Item 31");
        MenuItem thirdContainerSecondStage2 = new MenuItem("Item 32");
        MenuItem thirdContainerSecondStage3 = new MenuItem("Item 33");
        MenuItem thirdContainerSecondStage4 = new MenuItem("Item 34");
        thirdContainer.getItems().addAll(
                thirdContainerSecondStage,
                thirdContainerSecondStage2,
                thirdContainerSecondStage3,
                thirdContainerSecondStage4
        );

        contextMenu.getItems().addAll(
                firstContainer,
                secondContainer,
                thirdContainer,
                forthItem,
                fifthItem
        );


        // Add event handler to show context menu on right-click
        root.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                contextMenu.show(root, event.getScreenX(), event.getScreenY());
            }
        });

        primaryStage.setTitle("Linear Menu with Submenus Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
