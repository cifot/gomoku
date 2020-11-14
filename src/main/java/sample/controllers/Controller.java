package sample.controllers;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class Controller {

    private final String whiteStoneStyle = "-fx-background-color: #FFFFFF;" +
            "-fx-border-color: #707070;";

    private final String blackStoneStyle = "-fx-background-color: #333333;" +
            "-fx-border-color: #222222;";

    private final String emptyPlaceStyle = "-fx-border-width: 0;" +
            "-fx-pref-width: 18.0;" +
            "-fx-pref-height: 18.0;" +
            "-fx-background-color: #c0c0c0;";

    final int size = 19;


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane menu;

    @FXML
    private AnchorPane game;

    @FXML
    private GridPane gridPane;

    @FXML
    private Button continueButton;

    @FXML
    private Button startButton;


    @FXML
    void actionEmptyPlaceButton(ActionEvent event) {
        Button emptyPlaceButton = (Button) event.getSource();
        emptyPlaceButton.setDisable(true);
        emptyPlaceButton.setStyle(whiteStoneStyle);
    }

    @FXML
    void gameKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ESCAPE) {
            game.setVisible(false);
            menu.setVisible(true);
        }
    }

    @FXML
    void actionStartButton(ActionEvent event) {
        gridPane.getChildren().forEach(e -> {e.setStyle(emptyPlaceStyle);
                                             e.setDisable(false);});
        gridPane.getChildren().stream().
                filter(button -> GridPane.getRowIndex(button) == (size - 1) / 2
                        && GridPane.getColumnIndex(button) == (size - 1) / 2)
                .forEach(button -> {button.setDisable(true); button.setStyle(blackStoneStyle);});
        menu.setVisible(false);
        game.setVisible(true);
        continueButton.setDisable(false);
    }

    @FXML
    void actionContinueButton(ActionEvent event) {
        menu.setVisible(false);
        game.setVisible(true);
    }

    @FXML
    void initialize() {
    }

    void emptyButtonAction() {

    }
}