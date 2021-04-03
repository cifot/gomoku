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
import sample.game.GameMode;
import sample.game.board.Board;
import sample.game.board.PlaceState;

public class Controller {

    private PlaceState color;
    private Board board;
    private GameMode gameMode;

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
    private AnchorPane game;

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
        board.putStone(color, GridPane.getColumnIndex(emptyPlaceButton), GridPane.getRowIndex(emptyPlaceButton));
        emptyPlaceButton.setStyle(color.getStyle());
        if (board.getScore(color) >= Integer.MAX_VALUE) {
            System.out.println(color);
        }
        if (gameMode == GameMode.UserUser) {
            color = color == PlaceState.WHITE ? PlaceState.BLACK : PlaceState.WHITE;
        }
    }

    @FXML
    void gameKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ESCAPE) {
            game.setVisible(false);
            helpMenu.setVisible(false);
            menu.setVisible(true);
        }
    }

    @FXML
    void actionStartButton1(ActionEvent event) {

    }

    @FXML
    void actionStartButton2(ActionEvent event) {
        board = new Board(size);
        gameMode = GameMode.UserUser;
        color = PlaceState.WHITE;
        gridPane.getChildren().forEach(e -> {e.setStyle(PlaceState.AVAILABLE.getStyle());
                                             e.setDisable(false);});
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