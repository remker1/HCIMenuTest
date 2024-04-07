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

public class IntroStage extends Application {
    private final Label introLabel = new Label("Welcome Tester..." +
            "This is a testing system to find out the properties\n" +
            "between Linear Menu and Radial Menu based on Fitt's Law\n");
    Label instructionLabel = new Label("Please Press 'SPACE' Key to Start.");
    Font fontSize = Font.font(18);


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Intro Stage");
        VBox root = new VBox();
        root.getChildren().addAll(introLabel,instructionLabel);
        introLabel.setFont(fontSize);
        instructionLabel.setFont(fontSize);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root,800,600);
        primaryStage.setScene(scene);
        primaryStage.show();

        BeforeBlock beforeBlock = new BeforeBlock();

        scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()){
                case SPACE:
                    beforeBlock.start(primaryStage);
                    break;
            }
        });
    }
}
