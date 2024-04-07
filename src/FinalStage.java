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

public class FinalStage extends Application {
    private final Label finLabel = new Label("Fin");
    Font fontSize = Font.font(18);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Final Stage");
        VBox root = new VBox();
        root.getChildren().add(finLabel);
        finLabel.setFont(fontSize);
        root.setAlignment(Pos.TOP_CENTER);
        root.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        Scene scene = new Scene(root,800,600);
        primaryStage.setScene(scene);
        primaryStage.show();

        scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()){
                case SPACE:
                    primaryStage.close();
                    break;
            }
        });
    }
}
