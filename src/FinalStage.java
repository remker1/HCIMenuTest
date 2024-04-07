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

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FinalStage extends Application {
    private final Label finLabel = new Label("Thank you for participating!");
    Label instructionLabel = new Label("Press 'SPACE' to exit.");
    Font fontSize = Font.font(20);
    BeforeBlock beforeBlock = new BeforeBlock();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Final Stage");
        VBox root = new VBox();
        root.getChildren().addAll(finLabel,instructionLabel);
        finLabel.setFont(fontSize);
        instructionLabel.setFont(fontSize);
        root.setAlignment(Pos.CENTER);
        root.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        Scene scene = new Scene(root,800,600);
        primaryStage.setScene(scene);
        primaryStage.show();

        scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()){
                case SPACE:
                    String boderLine = "==============================================================";
                    appendToFile(beforeBlock.getFilePath(), boderLine);
                    primaryStage.close();
                    break;
            }
        });
    }

    public static void appendToFile(String filePath, String textToAppend) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(textToAppend);
            writer.newLine(); // Add newline after each appended string
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
