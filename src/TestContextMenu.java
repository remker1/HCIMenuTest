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
        scene.getStylesheets().add(getClass().getResource("linearmenu.css").toExternalForm());
        // Create the main context menu
        ContextMenu contextMenu = new ContextMenu();

        String[] firstContainer = new String[]{"Apple", "Apricot", "Avocado", "Asparagus", "Artichoke"};
        String[] secondContainer = new String[]{"Banana", "Blueberry", "Beetroot", "Broccoli", "Bean-Sprouts"};
        String[] thirdContainer = new String[]{"Cherry", "Carrot", "Cantaloupe", "Cauliflower", "Cucumber"};
        String menuName1 = "A words";
        String menuName2 = "B words";
        String menuName3 = "C words";
        String forthItem = "Durian";
        String fifthItem = "Edamame";

        // Create submenus
        Menu linearFirstContainer = new Menu(menuName1);
        Menu linearSecondContainer = new Menu(menuName2);
        Menu linearThirdContainer = new Menu(menuName3);
        MenuItem linearForthItem = new MenuItem(forthItem);
        MenuItem linearFifthItem = new MenuItem(fifthItem);

        for (String item : firstContainer) {

            linearFirstContainer = linearAddItem(item, linearFirstContainer);

        }

        for (String item : secondContainer) {

            linearSecondContainer = linearAddItem(item,linearSecondContainer);

        }

        for (String item : thirdContainer) {

            linearThirdContainer = linearAddItem(item,linearThirdContainer);
        }


        contextMenu.getItems().addAll(
                linearFirstContainer,
                linearSecondContainer,
                linearThirdContainer,
                linearForthItem,
                linearFifthItem
        );

        // TODO: Add event handlers to print out the clicked item
        linearFirstContainer.setOnAction(event -> {
            MenuItem menuItem = (MenuItem) event.getTarget();
            System.out.println("Clicked: " + menuItem.getText());
        });

        linearSecondContainer.setOnAction(event -> {
            MenuItem menuItem = (MenuItem) event.getTarget();
            System.out.println("Clicked: " + menuItem.getText());
        });

        linearThirdContainer.setOnAction(event -> {
            MenuItem menuItem = (MenuItem) event.getTarget();
            System.out.println("Clicked: " + menuItem.getText());
        });

        linearForthItem.setOnAction(event -> {
            MenuItem menuItem = (MenuItem) event.getTarget();
            System.out.println("Clicked: " + menuItem.getText());
        });

        linearFifthItem.setOnAction(event -> {
            MenuItem menuItem = (MenuItem) event.getTarget();
            System.out.println("Clicked: " + menuItem.getText());
        });

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

    public Menu linearAddItem(String item, Menu container){
        MenuItem linearItem = new MenuItem();
        linearItem.setText(item);
        container.getItems().add(linearItem);
        return container;
    }

    public static void main(String[] args) {
        launch(args);
    }
}