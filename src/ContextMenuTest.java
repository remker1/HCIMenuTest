import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class ContextMenuTest extends Application {

    BeforeBlock beforeBlock;
    String target;
    private  final Label linearLabel = new Label("Linear Menu Testing\nWe are looking for:");

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        VBox vBox = new VBox();
        target = randomItemGenerator();
        Label targetLabel = new Label(target);
        long startTime;
        AtomicLong endTime = new AtomicLong();
        AtomicLong duration = new AtomicLong();
        FinalStage finalStage = new FinalStage();
        Scene scene = new Scene(root, 800, 600, Color.WHITE);
        ContextMenu contextMenu = new ContextMenu();


        vBox.getChildren().addAll(linearLabel,targetLabel);
        root.getChildren().addAll(vBox);
        scene.getStylesheets().add(getClass().getResource("linearmenu.css").toExternalForm());


        // Create submenus
        Menu linearFirstContainer = new Menu(beforeBlock.getMenuName1());
        Menu linearSecondContainer = new Menu(beforeBlock.getMenuName2());
        Menu linearThirdContainer = new Menu(beforeBlock.getMenuName3());
        MenuItem linearForthItem = new MenuItem(beforeBlock.getForthItem());
        MenuItem linearFifthItem = new MenuItem(beforeBlock.getFifthItem());

        for (String item : beforeBlock.getFirstContainer()) {
            linearFirstContainer = linearAddItem(item, linearFirstContainer);
        }
        for (String item : beforeBlock.getSecondContainer()) {
            linearSecondContainer = linearAddItem(item,linearSecondContainer);
        }
        for (String item : beforeBlock.getThirdContainer()) {
            linearThirdContainer = linearAddItem(item,linearThirdContainer);
        }

        contextMenu.getItems().addAll(
                linearFirstContainer,
                linearSecondContainer,
                linearThirdContainer,
                linearForthItem,
                linearFifthItem
        );
        startTime = System.currentTimeMillis();
        // Create a single event handler for all menu items
        EventHandler<ActionEvent> menuItemHandler = event -> {
            MenuItem menuItem = (MenuItem) event.getTarget();
            for (MenuItem item : contextMenu.getItems()){

                if (item instanceof Menu){
                    for (MenuItem subItems : ((Menu) item).getItems()){
                        if (subItems.getText().equals(menuItem.getText())){
                            endTime.set(System.currentTimeMillis());
                            duration.set((endTime.get() - startTime));
                            System.out.println("LinearMenu,"+ beforeBlock.getTrailRadialNumber() + ',' + target + "," + duration);
                            beforeBlock.trailLinearNumber += 1;
                            if ((beforeBlock.getTrailRadialNumber() >= beforeBlock.getMaxTrailNum())&&
                                    (beforeBlock.getTrailLinearNumber() >= beforeBlock.maxTrailNum)){
                                finalStage.start(primaryStage);
                            }else if(beforeBlock.getTrailLinearNumber() == beforeBlock.maxTrailNum){
                                beforeBlock.start(primaryStage);
                            }else {
                                beforeBlock.start(primaryStage);
                            }
                        }
                    }
                }else {
                    if (item.getText().equals(menuItem.getText())){
                        endTime.set(System.currentTimeMillis());
                        duration.set((endTime.get() - startTime));
                        System.out.println("LinearMenu,"+ beforeBlock.getTrailLinearNumber() + ',' + target + "," + duration);
                        beforeBlock.trailLinearNumber += 1;
                        if ((beforeBlock.getTrailRadialNumber() >= beforeBlock.getMaxTrailNum())&&
                                (beforeBlock.getTrailLinearNumber() >= beforeBlock.maxTrailNum)){
                            finalStage.start(primaryStage);
                        }else if(beforeBlock.getTrailLinearNumber() == beforeBlock.maxTrailNum){
                            beforeBlock.start(primaryStage);
                        }else {
                            beforeBlock.start(primaryStage);
                        }
                    }
                }
            }
        };

        // Set the event handler for all menu items
        for (MenuItem item : linearFirstContainer.getItems()) {
            item.setOnAction(menuItemHandler);
        }
        for (MenuItem item : linearSecondContainer.getItems()) {
            item.setOnAction(menuItemHandler);
        }
        for (MenuItem item : linearThirdContainer.getItems()) {
            item.setOnAction(menuItemHandler);
        }
        linearForthItem.setOnAction(menuItemHandler);
        linearFifthItem.setOnAction(menuItemHandler);

        // Add event handler to show context menu on right-click
        root.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                contextMenu.show(root, event.getScreenX(), event.getScreenY());
            }
        });

        primaryStage.setTitle("Linear Menu Testing");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public Menu linearAddItem(String item, Menu container){
        MenuItem linearItem = new MenuItem();
        linearItem.setText(item);
        container.getItems().add(linearItem);
        return container;
    }

    public String randomItemGenerator(){

        String randomItem;
        Random random = new Random();

        int randIndex = random.nextInt(5);
        if (randIndex <= 2){
            int randInnerIndex = random.nextInt(5);
            if (randIndex == 0){
                randomItem = beforeBlock.getFirstContainer()[randInnerIndex];
            } else if (randIndex == 1) {
                randomItem = beforeBlock.getSecondContainer()[randInnerIndex];
            } else {
                randomItem = beforeBlock.getThirdContainer()[randInnerIndex];
            }
        } else {
            if (randIndex == 3){
                randomItem = beforeBlock.getForthItem();
            } else {
                randomItem = beforeBlock.getFifthItem();
            }
        }

        return randomItem;
    }

    public void setBeforeBlock(BeforeBlock beforeBlock) {
        this.beforeBlock = beforeBlock;
    }

    public static void main(String[] args) {
        launch(args);
    }
}