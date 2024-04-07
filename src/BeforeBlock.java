import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class BeforeBlock extends Application {

    String[] firstContainer = new String[]{"Apple", "Apricot", "Avocado", "Asparagus", "Artichoke"};
    String[] secondContainer = new String[]{"Banana", "Blueberry", "Beetroot", "Broccoli", "Bean-Sprouts"};
    String[] thirdContainer = new String[]{"Cherry", "Carrot", "Cantaloupe", "Cauliflower", "Cucumber"};
    String menuName1 = "A words";
    String menuName2 = "B words";
    String menuName3 = "C words";
    String forthItem = "Durian";
    String fifthItem = "Edamame";

    private final Label beforeBlockLabel = new Label("Prepare yourself..." +
            "Timer will Start as soon as you press one of the keys\n" +
            "Right click your mouse to open the pop up window and select\n" +
            "the target item.");
    Font fontSize = Font.font(18);
    int trailRadialNumber ;
    int trailLinearNumber ;
    int maxTrailNum = 10;
    Label instructionLabel = new Label("Please press 'R' for Radial Menu Testing\n" +
            "Please press 'L' for Linear Menu Testing\n");
    Label trailRadialNumLabel;
    Label trailLinearNumLabel;

    Label warningLabel = new Label("Warning Message:\n" +
            "If you have finished one of the test, please try\n" +
            "the other test, this test would only end if both tests\n" +
            "are reaching 10 trails.");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        ContextMenuTest linearMenuTest = new ContextMenuTest();
        RadicalMenuTest radicalMenuTest = new RadicalMenuTest();
        linearMenuTest.setBeforeBlock(this);
        radicalMenuTest.setBeforeBlock(this);// Pass the instance of BeforeBlock
        trailLinearNumLabel = new Label("Linear Menu Trails Progress: "+trailLinearNumber+" / "+ maxTrailNum);
        trailRadialNumLabel = new Label("Radial Menu Trails Progress: "+trailRadialNumber+" / "+ maxTrailNum);

        primaryStage.setTitle("Before Block");
        VBox root = new VBox();
        root.getChildren().addAll(beforeBlockLabel,instructionLabel,trailRadialNumLabel,trailLinearNumLabel,warningLabel);
        beforeBlockLabel.setFont(fontSize);
        instructionLabel.setFont(fontSize);
        trailRadialNumLabel.setFont(fontSize);
        trailLinearNumLabel.setFont(fontSize);
        root.setAlignment(Pos.CENTER);
        root.setBackground(new Background(new BackgroundFill(Color.LIGHTGOLDENRODYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
        Scene scene = new Scene(root,800,600);


        FinalStage finalStage = new FinalStage();

        scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()){
                case L:
                    linearMenuTest.start(primaryStage);
                    break;
                case R:
                    try {
                        radicalMenuTest.start(primaryStage);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case SPACE:
                    finalStage.start(primaryStage);
                    break;
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public int getTrailRadialNumber(){
        return trailRadialNumber;
    }
    public int getTrailLinearNumber(){
        return trailLinearNumber;
    }
    public int getMaxTrailNum(){
        return maxTrailNum;
    }
    public String[] getFirstContainer(){
        return firstContainer;
    }
    public String[] getSecondContainer(){
        return secondContainer;
    }
    public String[] getThirdContainer(){
        return thirdContainer;
    }
    public String getMenuName1(){
        return menuName1;
    }
    public String getMenuName2(){
        return menuName2;
    }
    public String getMenuName3(){
        return menuName3;
    }
    public String getForthItem(){
        return forthItem;
    }
    public String getFifthItem(){
        return fifthItem;
    }



}
