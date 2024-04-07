import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;


public class RadicalMenuTest extends Application {

    RadialMenu radialMenu;
    String target;
    private  final Label radialLabel = new Label("Radial Menu Testing\nWe are looking for:");
    BeforeBlock beforeBlock;



    @Override
    public void start(Stage primaryStage) throws Exception {

        radialMenu = new RadialMenu(50, 100, 360, 5);



        RadialMenuContainer radialFirstContainer = new RadialMenuContainer();
        radialFirstContainer.setText(beforeBlock.getMenuName1());
        radialFirstContainer.setChildrenCenterOnParent(true);
        RadialMenuContainer radialSecondContainer = new RadialMenuContainer();
        radialSecondContainer.setText(beforeBlock.getMenuName2());
        radialSecondContainer.setChildrenCenterOnParent(true);
        RadialMenuContainer radialThirdContainer = new RadialMenuContainer();
        radialThirdContainer.setText(beforeBlock.getMenuName3());

        RadialMenuItem radialFourthItem = new RadialMenuItem();
        radialFourthItem.setText(beforeBlock.getForthItem());
        RadialMenuItem radialFifthItem = new RadialMenuItem();
        radialFifthItem.setText(beforeBlock.getFifthItem());

        for (String item : beforeBlock.getFirstContainer()) {
            radialFirstContainer = radialAddItem(item, radialFirstContainer);
        }
        for (String item : beforeBlock.getSecondContainer()) {
            radialSecondContainer = radialAddItem(item, radialSecondContainer);
        }
        for (String item : beforeBlock.getThirdContainer()) {
            radialThirdContainer = radialAddItem(item, radialThirdContainer);
        }

        radialMenu.addRootItem(radialFirstContainer);
        radialMenu.addRootItem(radialSecondContainer);
        radialMenu.addRootItem(radialThirdContainer);
        radialMenu.addRootItem(radialFourthItem);
        radialMenu.addRootItem(radialFifthItem);
        radialMenu.setVisible(false);
        target = randomItemGenerator();

        Group group = new Group();
        Group group1 = new Group();
        Scene scene = new Scene(group1, 800, 600);
        scene.getStylesheets().add(getClass().getResource("radialmenu.css").toExternalForm());

        scene.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                // Show menu at click coordinates
                showMenuAtPosition(event.getSceneX(), event.getSceneY());
            }
        });

        group.getChildren().add(radialMenu);
        VBox vBox1 = new VBox();
        vBox1.getChildren().addAll(radialLabel,new Label(target));
        group1.getChildren().addAll(vBox1,group);
        radialMenu.setVisible(false);
        primaryStage.setTitle("Radial Menu Testing");
        primaryStage.setScene(scene);
        primaryStage.show();

        long startTime;
        AtomicLong endTime = new AtomicLong();
        AtomicLong duration = new AtomicLong();
        FinalStage finalStage = new FinalStage();

        startTime = System.currentTimeMillis();

        for (RadialMenuItem item : radialMenu.getAllItems()) {
            if (item instanceof RadialMenuContainer) {
                for (RadialMenuItem subItem : ((RadialMenuContainer) item).getItems()) {
                    subItem.setOnMouseClicked(event -> {
                        String selectedItemText = subItem.getText();
                        if (selectedItemText.equals(target)) {
                            endTime.set(System.currentTimeMillis());
                            duration.set((endTime.get() - startTime));
                            System.out.println("RadialMenu,"+ beforeBlock.getTrailRadialNumber() + ',' + target + "," + duration);
                            radialMenu.setVisible(false);
                            beforeBlock.trailRadialNumber += 1;
                            if ((beforeBlock.getTrailRadialNumber() >= beforeBlock.getMaxTrailNum())&&
                                    (beforeBlock.getTrailLinearNumber() >= beforeBlock.maxTrailNum)){
                                finalStage.start(primaryStage);
                            }else if(beforeBlock.getTrailRadialNumber() == beforeBlock.maxTrailNum){
                                beforeBlock.start(primaryStage);
                            }else {
                                beforeBlock.start(primaryStage);
                            }
                        }
                    });
                }
            } else {
                item.setOnMouseClicked(event -> {
                    String selectedItemText = item.getText();
                    if (selectedItemText.equals(target)) {
                        endTime.set(System.currentTimeMillis());
                        duration.set((endTime.get() - startTime));
                        System.out.println("RadialMenu," + beforeBlock.getTrailRadialNumber() + ',' + target + "," + duration);
                        radialMenu.setVisible(false);
                        beforeBlock.trailRadialNumber += 1;
                        if ((beforeBlock.getTrailRadialNumber() >= beforeBlock.getMaxTrailNum())&&
                                (beforeBlock.getTrailLinearNumber() >= beforeBlock.maxTrailNum)){
                            finalStage.start(primaryStage);
                        }else if(beforeBlock.getTrailRadialNumber() == beforeBlock.maxTrailNum){
                            beforeBlock.start(primaryStage);
                        }else {
                            beforeBlock.start(primaryStage);
                        }

                    }

                });
            }

        }
    }

    public void setBeforeBlock(BeforeBlock beforeBlock) {
        this.beforeBlock = beforeBlock;
    }

    private void showMenuAtPosition(double x, double y) {
        radialMenu.setLayoutX(x);
        radialMenu.setLayoutY(y);
        radialMenu.setVisible(true); // If not already visible
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

    public RadialMenuContainer radialAddItem(String item, RadialMenuContainer container){
        RadialMenuItem radialItem = new RadialMenuItem();
        radialItem.setText(item);
        container.addItem(radialItem);
        return container;
    }


    public static void main(String[] args) {
        launch(args);
    }

}