package sample.controllers;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import sample.game.Game;
import sample.game.GameMode;
import sample.game.board.Board;
import sample.game.board.PlaceState;

public class Controller {

    private PlaceState currentColor;
    private Game game;

    final int size = 19;


    @FXML
    private ResourceBundle resources;

    @FXML
    private AnchorPane helpMenu;

    @FXML
    private URL location;

    @FXML
    private AnchorPane menu;

    @FXML
    private AnchorPane gamePane;

    @FXML
    private GridPane gridPane;

    @FXML
    private Button continueButton;

    @FXML
    private Button startButton1;

    @FXML
    private Button startButton2;


    @FXML
    void actionHelpButton(ActionEvent event) {
        menu.setVisible(false);
        helpMenu.setVisible(true);
    }

    @FXML
    void actionEmptyPlaceButton(ActionEvent event) {
        Button emptyPlaceButton = (Button) event.getSource();
        emptyPlaceButton.setDisable(true);
        game.getBoard().putStone(currentColor, GridPane.getColumnIndex(emptyPlaceButton), GridPane.getRowIndex(emptyPlaceButton));
        emptyPlaceButton.setStyle(currentColor.getStyle());
        if (game.getBoard().getScore(currentColor) >= Integer.MAX_VALUE) {
            System.out.println(currentColor);
        }
        currentColor = game.changeColor();
    }

    @FXML
    void gameKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ESCAPE) {
            gamePane.setVisible(false);
            helpMenu.setVisible(false);
            menu.setVisible(true);
        }
    }

    @FXML
    void actionStartButton1(ActionEvent event) {

    }

    @FXML
    void actionStartButton2(ActionEvent event) {
        game = new Game(new Board(size), GameMode.UserUser, PlaceState.WHITE);
        currentColor = PlaceState.WHITE;
        gridPane.getChildren().forEach(e -> {e.setStyle(PlaceState.AVAILABLE.getStyle());
                                             e.setDisable(false);});
        menu.setVisible(false);
        gamePane.setVisible(true);
        continueButton.setDisable(false);
    }

    @FXML
    void actionContinueButton(ActionEvent event) {
        menu.setVisible(false);
        gamePane.setVisible(true);
    }

    @FXML
    void initialize() {
    }

    void emptyButtonAction() {

    }
}