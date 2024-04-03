import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;


import javafx.scene.Scene;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

public class TestingApp extends Application {

    Stages currentStage;
    Stages preWorkStage;
    private final  Label initLabel = new Label("Init");
    private final Label instructionLabel = new Label("Intro");
    private final Label beforeBlockLabel = new Label("Before");
    private final Label linearLabel = new Label("Linear Menu Testing\nWe are looking for:");
    private  final Label radialLabel = new Label("Radial Menu Testing\nWe are looking for:");
    private final Label finishLabel = new Label("Fin");
    Label target;
    Font labelSize = Font.font(18);
    String[] firstContainer;
    String[] secondContainer;
    String[] thirdContainer;
    ArrayList<String> randomItemList;
    String menuName1;
    String menuName2;
    String menuName3;
    String forthItem;
    String fifthItem;

    ContextMenu linearMenu;
    RadialMenu radialMenu;
    private Scene scene;
    int numOfTrails = 9;
    int trailIdx = 0;

    Boolean radialFound = false;


    @Override
    public void start(Stage primaryStage) {

        // Create a pane for the stage
        VBox root = new VBox();
        Group group = new Group();


        currentStage = Stages.INSTRUCTIONS;
        root.getChildren().add(initLabel);
        root.setAlignment(Pos.TOP_CENTER);
        scene = new Scene(root,800, 600);
        initStage();

        scene.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

            if (event.getButton() == MouseButton.SECONDARY){
                switch (currentStage){
                    case INSTRUCTIONS:
                        System.out.println("Instruction");
                        root.getChildren().clear();
                        root.setBackground(new Background(new BackgroundFill(Color.LIGHTGOLDENRODYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
                        root.getChildren().add(instructionLabel);
                        currentStage = Stages.BEFORE_BLOCK;
                        preWorkStage = Stages.RADIAL_STAGE;
                        break;

                    case BEFORE_BLOCK:
                        System.out.println("Before_Block");
                        root.getChildren().clear();
                        root.getChildren().add(beforeBlockLabel);
                        root.setBackground(new Background(new BackgroundFill(Color.LIGHTGOLDENRODYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
                        root.setAlignment(Pos.TOP_CENTER);
                        radialFound = false;
                        if (preWorkStage == Stages.LINEAR_STAGE){
                            currentStage = Stages.RADIAL_STAGE;
                        }else if(preWorkStage == Stages.RADIAL_STAGE){
                            currentStage = Stages.LINEAR_STAGE;
                        }
                        break;

                    case LINEAR_STAGE:
                        System.out.println("Linear");
                        root.getChildren().clear();
                        root.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));

                        target = new Label(randomItemDistributor(trailIdx));
                        target.setFont(labelSize);
                        root.setAlignment(Pos.TOP_LEFT);
                        root.getChildren().addAll(linearLabel,target);
                        scene.getStylesheets().add(getClass().getResource("linearmenu.css").toExternalForm());

                        preWorkStage = currentStage;
                        currentStage = Stages.BEFORE_BLOCK;
                        break;

                    case RADIAL_STAGE:
                        System.out.println("Radial");
                        root.getChildren().clear();
                        root.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
                        target = new Label("Target: " + randomItemDistributor(trailIdx));
                        target.setFont(labelSize);
                        root.setAlignment(Pos.TOP_LEFT);
                        group.getChildren().clear();  // Clear previous content from group
                        group.getChildren().addAll(radialMenu);  // Add updated content to group
                        root.getChildren().addAll(radialLabel, target,group);

                        showMenuAtPosition(root.getWidth() / 2, root.getHeight() / 2);

                        // Reassigning the updated scene to the stage
                        scene.getStylesheets().add(getClass().getResource("radialmenu.css").toExternalForm());

                        if(checkRadialInput(target)){
                            preWorkStage = currentStage;
                            currentStage = Stages.FINISHED;
                        }

                        break;

                    case FINISHED:
                        System.out.println("Fin");
                        root.getChildren().clear();
                        root.getChildren().add(finishLabel);
                        root.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
                        root.setAlignment(Pos.TOP_CENTER);
                        scene.setFill(Color.LIGHTGREEN);
                        currentStage = Stages.QUIT;
                        break;

                    case QUIT:
                        System.out.println("Quit");
                        primaryStage.close();
                        break;

                }
            }

        });


        primaryStage.setScene(scene);
        primaryStage.setTitle("Side Menus");
        primaryStage.show();
    }

    private void showMenuAtPosition(double x, double y) {
        radialMenu.setLayoutX(x);
        radialMenu.setLayoutY(y);
        radialMenu.setVisible(true); // If not already visible
    }

    public void initStage(){

        initLabel.setFont(labelSize);
        instructionLabel.setFont(labelSize);
        beforeBlockLabel.setFont(labelSize);
        finishLabel.setFont(labelSize);
        linearLabel.setFont(labelSize);
        radialLabel.setFont(labelSize);


        firstContainer = new String[]{"Apple", "Apricot", "Avocado", "Asparagus", "Artichoke"};
        secondContainer = new String[]{"Banana", "Blueberry", "Beetroot", "Broccoli", "Bean-Sprouts"};
        thirdContainer = new String[]{"Cherry", "Carrot", "Cantaloupe", "Cauliflower", "Cucumber"};
        menuName1 = "A words";
        menuName2 = "B words";
        menuName3 = "C words";
        forthItem = "Durian";
        fifthItem = "Edamame";

        linearMenu = new ContextMenu();
        radialMenu = new RadialMenu(50, 100, 360, 5);

        // Create submenus
        Menu linearFirstContainer = new Menu(menuName1);
        Menu linearSecondContainer = new Menu(menuName2);
        Menu linearThirdContainer = new Menu(menuName3);
        RadialMenuContainer radialFirstContainer = new RadialMenuContainer();
        radialFirstContainer.setText(menuName1);
        radialFirstContainer.setChildrenCenterOnParent(true);
        RadialMenuContainer radialSecondContainer = new RadialMenuContainer();
        radialSecondContainer.setText(menuName2);
        radialSecondContainer.setChildrenCenterOnParent(true);
        RadialMenuContainer radialThirdContainer = new RadialMenuContainer();
        radialThirdContainer.setText(menuName3);


        MenuItem linearForthItem = new MenuItem(forthItem);
        MenuItem linearFifthItem = new MenuItem(fifthItem);
        RadialMenuItem radialFourthItem = new RadialMenuItem();
        radialFourthItem.setText(forthItem);
        RadialMenuItem radialFifthItem = new RadialMenuItem();
        radialFifthItem.setText(fifthItem);

        for (String item : firstContainer) {

            linearFirstContainer = linearAddItem(item, linearFirstContainer);
            radialFirstContainer = radialAddItem(item,radialFirstContainer);

        }

        for (String item : secondContainer) {

            linearSecondContainer = linearAddItem(item,linearSecondContainer);
            radialSecondContainer = radialAddItem(item,radialSecondContainer);

        }

        for (String item : thirdContainer) {

            linearThirdContainer = linearAddItem(item,linearThirdContainer);
            radialThirdContainer = radialAddItem(item, radialThirdContainer);
        }

        linearMenu.getItems().addAll(
                linearFirstContainer,
                linearSecondContainer,
                linearThirdContainer,
                linearForthItem,
                linearFifthItem
        );

        radialMenu.addRootItem(radialFirstContainer);
        radialMenu.addRootItem(radialSecondContainer);
        radialMenu.addRootItem(radialThirdContainer);
        radialMenu.addRootItem(radialFourthItem);
        radialMenu.addRootItem(radialFifthItem);
        radialMenu.setVisible(false);


        randomItemList = randomItemListGenerator(numOfTrails);

        System.out.println("Data Structures Initialized!");
    }

    public Boolean checkRadialInput(Label target){

        for (RadialMenuItem item : radialMenu.getAllItems()){
            if (item.getClass() == RadialMenuContainer.class){
                for (RadialMenuItem item1 : ((RadialMenuContainer) item).getItems()){
                    item1.setOnMouseClicked(event -> {
                        if (item1.getText().equals(target.getText())) {
                            radialFound = true;
                        }
                    });
                }
            }
            item.setOnMouseClicked(event -> {
                if (item.getText().equals(target.getText())) {
                    radialFound = true;
                }
            });
        }
        System.out.println(radialFound);
        return radialFound;
    }

    public Menu linearAddItem(String item, Menu container){
        MenuItem linearItem = new MenuItem();
        linearItem.setText(item);
        container.getItems().add(linearItem);
        return container;
    }

    public RadialMenuContainer radialAddItem(String item, RadialMenuContainer container){
        RadialMenuItem radialItem = new RadialMenuItem();
        radialItem.setText(item);
        container.addItem(radialItem);
        return container;
    }

    public String randomItemDistributor(int index){
        return randomItemList.get(index);
    }

    public ArrayList<String> randomItemListGenerator(int size){

        String randomItem;
        ArrayList<String> randomItemList = new ArrayList<>(size);
        Random random = new Random();
        Set<String> addedItems = new HashSet<>();

        while (randomItemList.size() < size) {
            int randIndex = random.nextInt(5);
            if (randIndex <= 2){
                int randInnerIndex = random.nextInt(5);
                if (randIndex == 0){
                    randomItem = firstContainer[randInnerIndex];
                } else if (randIndex == 1) {
                    randomItem = secondContainer[randInnerIndex];
                } else {
                    randomItem = thirdContainer[randInnerIndex];
                }
            } else {
                if (randIndex == 3){
                    randomItem = forthItem;
                } else {
                    randomItem = fifthItem;
                }
            }
            if (!addedItems.contains(randomItem)) {
                randomItemList.add(randomItem);
                addedItems.add(randomItem);
            }
        }

        // Test to see all the items in the list
        //for (String x : randomItemList){
            //System.out.println(x);
        //}

        return randomItemList;
    }


    private enum Stages{
        INSTRUCTIONS,
        BEFORE_BLOCK,
        LINEAR_STAGE,
        RADIAL_STAGE,
        FINISHED,
        QUIT;

    }


    public static void main(String[] args) {
        launch(args);
    }
}
