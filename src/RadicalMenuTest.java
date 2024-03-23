import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class RadicalMenuTest extends Application {

    RadialMenu radialMenu;
    List<String> names = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception {

        radialMenu = new RadialMenu(60, 120, 360, 5);


        RadialMenuContainer firstContainer = new RadialMenuContainer();
        firstContainer.setText("Submenu 1");
        firstContainer.setChildrenCenterOnParent(true);
        RadialMenuContainer secondContainer = new RadialMenuContainer();
        secondContainer.setText("Submenu 2");
        secondContainer.setChildrenCenterOnParent(true);
        RadialMenuContainer thirdContainer = new RadialMenuContainer();
        thirdContainer.setText("Submenu 3");
        RadialMenuItem fourthItem = new RadialMenuItem();
        fourthItem.setText("Item 4");
        names.add(fourthItem.getText());
        RadialMenuItem fifthItem = new RadialMenuItem();
        fifthItem.setText("Item 5");
        names.add(fifthItem.getText());

        RadialMenuItem firstContainerSecondStage = new RadialMenuItem();
        firstContainerSecondStage.setText("Item 11");
        RadialMenuItem firstContainerSecondStage2 = new RadialMenuItem();
        firstContainerSecondStage2.setText("Item 12");
        RadialMenuItem firstContainerSecondStage3 = new RadialMenuItem();
        firstContainerSecondStage3.setText("Item 13");
        RadialMenuItem firstContainerSecondStage4 = new RadialMenuItem();
        firstContainerSecondStage4.setText("Item 14");
        firstContainer.addItem(firstContainerSecondStage);
        firstContainer.addItem(firstContainerSecondStage2);
        firstContainer.addItem(firstContainerSecondStage3);
        firstContainer.addItem(firstContainerSecondStage4);
        for (RadialMenuItem item : firstContainer.getItems()){
            names.add(item.getText());
        }

        RadialMenuItem secondContainerSecondStage = new RadialMenuItem();
        secondContainerSecondStage.setText("Item 21");
        RadialMenuItem secondContainerSecondStage2 = new RadialMenuItem();
        secondContainerSecondStage2.setText("Item 22");
        RadialMenuItem secondContainerSecondStage3 = new RadialMenuItem();
        secondContainerSecondStage3.setText("Item 23");
        RadialMenuItem secondContainerSecondStage4 = new RadialMenuItem();
        secondContainerSecondStage4.setText("Item 24");
        secondContainer.addItem(secondContainerSecondStage);
        secondContainer.addItem(secondContainerSecondStage2);
        secondContainer.addItem(secondContainerSecondStage3);
        secondContainer.addItem(secondContainerSecondStage4);
        for (RadialMenuItem item : secondContainer.getItems()){
            names.add(item.getText());
        }

        RadialMenuItem thirdContainerSecondStage = new RadialMenuItem();
        thirdContainerSecondStage.setText("Item 31");
        RadialMenuItem thirdContainerSecondStage2 = new RadialMenuItem();
        thirdContainerSecondStage2.setText("Item 32");
        RadialMenuItem thirdContainerSecondStage3 = new RadialMenuItem();
        thirdContainerSecondStage3.setText("Item 33");
        RadialMenuItem thirdContainerSecondStage4 = new RadialMenuItem();
        thirdContainerSecondStage4.setText("Item 34");
        thirdContainer.addItem(thirdContainerSecondStage);
        thirdContainer.addItem(thirdContainerSecondStage2);
        thirdContainer.addItem(thirdContainerSecondStage3);
        thirdContainer.addItem(thirdContainerSecondStage4);
        for (RadialMenuItem item : thirdContainer.getItems()){
            names.add(item.getText());
        }

        radialMenu.addRootItem(firstContainer);
        radialMenu.addRootItem(secondContainer);
        radialMenu.addRootItem(thirdContainer);
        radialMenu.addRootItem(fourthItem);
        radialMenu.addRootItem(fifthItem);


        Group group = new Group();
        Scene scene = new Scene(group, 800, 600);
        scene.getStylesheets().add(getClass().getResource("radialmenu.css").toExternalForm());

        scene.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                // Show menu at click coordinates
                showMenuAtPosition(event.getSceneX(), event.getSceneY());
            }
        });

        group.getChildren().add(radialMenu);
        radialMenu.setVisible(false);
        primaryStage.setTitle("Radial Menu with Submenus Example");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Add event handlers to all RadialMenuItem objects
        for (RadialMenuItem item : radialMenu.getAllItems()) {
            item.setOnMouseClicked(event -> {
                if (names.contains(item.getText())) {
                    System.out.println(item.getText());
                    radialMenu.setVisible(false); // Hide the menu
                }
            });
        }
        for (RadialMenuItem item : firstContainer.getItems()) {
            item.setOnMouseClicked(event -> {
                if (names.contains(item.getText())) {
                    System.out.println(item.getText());
                    radialMenu.setVisible(false); // Hide the menu
                    firstContainer.setChildrenVisible(false); // Hide the submenu
                }
            });
        }
        for (RadialMenuItem item : secondContainer.getItems()) {
            item.setOnMouseClicked(event -> {
                if (names.contains(item.getText())) {
                    System.out.println(item.getText());
                    radialMenu.setVisible(false); // Hide the menu
                    secondContainer.setChildrenVisible(false); // Hide the submenu
                }
            });
        }
        for (RadialMenuItem item : thirdContainer.getItems()) {
            item.setOnMouseClicked(event -> {
                if (names.contains(item.getText())) {
                    System.out.println(item.getText());
                    radialMenu.setVisible(false); // Hide the menu
                    thirdContainer.setChildrenVisible(false); // Hide the submenu
                }
            });
        }

    }

    private void showMenuAtPosition(double x, double y) {
        radialMenu.setLayoutX(x);
        radialMenu.setLayoutY(y);
        radialMenu.setVisible(true); // If not already visible
    }


    public static void main(String[] args) {
        launch(args);
    }

}