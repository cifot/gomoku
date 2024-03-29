package sample.controllers;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import sample.game.Game;
import sample.game.GameMode;
import sample.game.board.Board;
import sample.game.board.Color;
import sample.game.board.Place;
import sample.game.rulesData.Weight;
import sample.game.rulesData.rules.Rule;
import sample.game.rulesData.RulePattern;
import sample.game.rulesData.rules.captures.Capture;
import sample.game.rulesData.rules.classicWinRule.ClassicWinRule;
import sample.game.rulesData.rules.freeThrees.FreeThrees;

public class Controller {

    private Color currentColor;
    private Game game;
    final ObjectMapper objectMapper = new ObjectMapper();
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
    private AnchorPane captureBlock;

    @FXML
    private GridPane boardGridPane;

    @FXML
    private Button continueButton;

    @FXML
    private Button startButton1;

    @FXML
    private Button startButton2;

    @FXML
    private Label blackCaptureScore;

    @FXML
    private Label whiteCaptureScore;

    @FXML
    private CheckBox freeThreesCheckBox;

    @FXML
    private ChoiceBox<String> captureChoiceBox;



    @FXML
    void actionHelpButton(ActionEvent event) {
        menu.setVisible(false);
        helpMenu.setVisible(true);
    }

    @FXML
    void actionEmptyPlaceButton(ActionEvent event) {
        Button emptyPlaceButton = (Button) event.getSource();
        emptyPlaceButton.setDisable(true);
        game.putStone(currentColor, GridPane.getColumnIndex(emptyPlaceButton), GridPane.getRowIndex(emptyPlaceButton));
        emptyPlaceButton.setStyle(currentColor.getStyle());
        boardGridPane.setDisable(true);
        Color enemyColor = game.changeColor();
        updatePossiblePlaces(enemyColor);
        if (captureBlock.isVisible()) {
            whiteCaptureScore.setText(Long.toString(game.getCaptureScore(Color.WHITE)));
            blackCaptureScore.setText(Long.toString(game.getCaptureScore(Color.BLACK)));
        }
        if (game.isWin(currentColor) && (game.wasLastChance(currentColor) || !game.hasLastChance(enemyColor))) {
            winLabel.setText(String.format("%s WIN", currentColor.toString()));
            winLabel.setVisible(true);
        } else {
            if (game.getGameMode() == GameMode.UserUser) {
                currentColor = enemyColor;
            } else {
                currentColor = game.putComputerStone();
                updatePossiblePlaces(enemyColor);
            }
            boardGridPane.setDisable(false);
        }
    }

    private void updatePossiblePlaces(Color color) {
        boardGridPane.getChildren().forEach( visualPlace -> {
            int column = GridPane.getColumnIndex(visualPlace);
            int line = GridPane.getRowIndex(visualPlace);
            Place backendPlace = game.getBoard().getStoneBoard()[column][line];
            visualPlace.setDisable(!backendPlace.getCanPlace()[color.ordinal()]);
            visualPlace.setStyle(backendPlace.getColor().getStyle());
        });
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

    private List<Rule> initRules() {
        var rules = new ArrayList<Rule>();
        try {
            if (freeThreesCheckBox.isSelected())
                rules.add(new FreeThrees(objectMapper.readValue(getClass().getResource("../../rulePatterns/freeThreesPattern.json"), RulePattern[][].class)));
            captureBlock.setVisible(true);
            switch (captureChoiceBox.getValue()) {
                case "None" -> captureBlock.setVisible(false);
                case "Capture" -> rules.add(new Capture(10, 2, false, 10L,
                        objectMapper.readValue(getClass().getResource("../../rulePatterns/capture2Pattern.json"), RulePattern[][].class)));
                case "Game-ending capture" ->rules.add(new Capture(10, 2, true, 10L,
                        objectMapper.readValue(getClass().getResource("../../rulePatterns/capture2Pattern.json"), RulePattern[][].class)));
            }
            rules.add(new ClassicWinRule(new Weight(5, 10)));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return rules;
    }

    private void startGame() {
        boardGridPane.setDisable(false);
        boardGridPane.getChildren().forEach(e -> {e.setStyle(Color.EMPTY.getStyle());
            e.setDisable(false);});
        menu.setVisible(false);
        gamePane.setVisible(true);
        continueButton.setDisable(false);
    }

    @FXML
    void actionStartButton1(ActionEvent event) {
        List<Rule> rules = initRules();
        game = new Game(new Board(size, 5), GameMode.UserComputer, Color.WHITE, rules);
        currentColor = game.getCurrentColor();
        startGame();
    }

    @FXML
    void actionStartButton2(ActionEvent event) {
        List<Rule> rules = initRules();
        game = new Game(new Board(size, 5), GameMode.UserUser, Color.WHITE, rules);
        currentColor = game.getCurrentColor();
        startGame();
    }

    @FXML
    void actionContinueButton(ActionEvent event) {
        menu.setVisible(false);
        gamePane.setVisible(true);
    }

    @FXML
    void initialize() {
        captureChoiceBox.setItems(FXCollections.observableArrayList("None", "Capture", "Game-ending capture"));
        captureChoiceBox.setValue("None");
    }
}