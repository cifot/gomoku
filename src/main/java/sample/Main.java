package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private final int width = 900;
    private final int height = 780;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/game.fxml"));
        primaryStage.setTitle("Gomoku");
        primaryStage.setScene(new Scene(root, width, height));
        primaryStage.setMinHeight(height);
        primaryStage.setMinWidth(width);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
