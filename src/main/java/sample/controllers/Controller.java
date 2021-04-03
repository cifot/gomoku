package sample.controllers;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private Label winLabel;

    @FXML
    private AnchorPane gamePane;

    @FXML
    private GridPane boardGridPane;

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
            boardGridPane.setDisable(true);
            winLabel.setText(String.format("%s WIN", currentColor.getName()));
            winLabel.setVisible(true);
        } else {
            currentColor = game.changeColor();
        }
    }

    @FXML
    void backToMainMenu(ActionEvent event) {
        gamePane.setVisible(false);
        helpMenu.setVisible(false);
        winLabel.setVisible(false);
        menu.setVisible(true);
    }

    @FXML
    void gameKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ESCAPE) {
            backToMainMenu(new ActionEvent());
        }
    }

    @FXML
    void actionStartButton1(ActionEvent event) {

    }

    @FXML
    void actionStartButton2(ActionEvent event) {
        game = new Game(new Board(size), GameMode.UserUser, PlaceState.WHITE);
        currentColor = PlaceState.WHITE;
        boardGridPane.setDisable(false);
        boardGridPane.getChildren().forEach(e -> {e.setStyle(PlaceState.AVAILABLE.getStyle());
                                                    e.setDisable(false);});
        menu.setVisible(false);
        gamePane.setVisible(true);
        continueButton.setDisable(false);
    }

    @FXML
    void actionContinueButton(ActionEvent event) {
        menu.setVisible(false);
        gamePane.setVisible(true);
        if (game.getBoard().getScore(currentColor) >= Integer.MAX_VALUE) {
            winLabel.setVisible(true);
        }
    }

    @FXML
    void initialize() {
    }

    void emptyButtonAction() {

    }
}