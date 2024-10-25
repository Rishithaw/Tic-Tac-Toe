package com.assignment.tictactoe.controller;

import com.assignment.tictactoe.service.AiPlayer;
import com.assignment.tictactoe.service.BoardImpl;
import com.assignment.tictactoe.service.BoardUI;
import com.assignment.tictactoe.service.Piece;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class BoardController implements BoardUI {

    @FXML
    private AnchorPane ancPane;

    @FXML
    private Button btn00;

    @FXML
    private Button btn01;

    @FXML
    private Button btn02;

    @FXML
    private Button btn10;

    @FXML
    private Button btn11;

    @FXML
    private Button btn12;

    @FXML
    private Button btn20;

    @FXML
    private Button btn21;

    @FXML
    private Button btn22;

    @FXML
    private Label lblHeader;

    @FXML
    private Label lblWinner;

    private BoardImpl board;
    private AiPlayer aiPlayer;

    @FXML
    public void initialize() {
        board = new BoardImpl();
        aiPlayer = new AiPlayer(board);
    }

    @FXML
    private void handleOnAction(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        int row = getRow(clickedButton);
        int col = getCol(clickedButton);

        if (board.isLegalMove(row, col)) {
            clickedButton.setText(Piece.X.toString()); // Display 'X'
            board.updateMove(row, col, Piece.X);
            board.printBoard();
            update(row, col, true); // Update UI for human's move

            NotifyWinner(); // Check for winner after human's move

            if (board.checkWinner() == Piece.EMPTY) { // Proceed only if no winner
                aiPlayer.move(row, col);
                updateBoardUI(); // This will update the buttons for AI's move
                board.printBoard();
                NotifyWinner(); // Check for winner after AI's move
            }
        }
    }

    @FXML
    private void resetOnAction() {
        try {
            ancPane.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/gameLayout.fxml"));
            ancPane.getChildren().add(load);
        }catch (IOException e){
            new Alert(Alert.AlertType.ERROR,"Failed to load UI").show();
        }
    }

    @Override
    public void update(int row, int col, boolean isHuman) {
        Button buttonToUpdate = getButtonByPosition(row, col);
        if (buttonToUpdate != null) {
            buttonToUpdate.setText(isHuman ? Piece.X.toString() : Piece.O.toString());
            buttonToUpdate.setDisable(true); // Disable button after it's clicked
        }
    }

    @Override
    public void NotifyWinner() {
        Piece winner = board.checkWinner();
        if (winner != Piece.EMPTY) {
            lblWinner.setText("Player " + (winner == Piece.X ? "X" : "O") + " wins!");
            disableButtons();
            lblHeader.setText(winner == Piece.X ? "Congratulations! You won!" : "Better luck next time!");
        } else if (board.isFull()) {
            lblHeader.setText("So Close!");
            lblWinner.setText("It's a tie!");
            disableButtons();
        }
    }

    private void updateBoardUI() {
        // Update the UI with AI's move
        updateButton(btn00, 0, 0);
        updateButton(btn01, 1, 0);
        updateButton(btn02, 2, 0);
        updateButton(btn10, 0, 1);
        updateButton(btn11, 1, 1);
        updateButton(btn12, 2, 1);
        updateButton(btn20, 0, 2);
        updateButton(btn21, 1, 2);
        updateButton(btn22, 2, 2);
    }

    private void updateButton(Button button, int row, int col) {
        if (board.getPieces(col, row) == Piece.O) {
            button.setText("O");
            button.setDisable(true);
        }
    }

    private int getRow(Button button) {
        return (button == btn00 || button == btn01 || button == btn02) ? 0 :
               (button == btn10 || button == btn11 || button == btn12) ? 1 :
               (button == btn20 || button == btn21 || button == btn22) ? 2 : -1; // Error case
    }

    private int getCol(Button button) {
        return (button == btn00 || button == btn10 || button == btn20) ? 0 :
               (button == btn01 || button == btn11 || button == btn21) ? 1 :
               (button == btn02 || button == btn12 || button == btn22) ? 2 : -1; // Error case
    }

    private void disableButtons() {
        for (Button button : new Button[]{btn00, btn01, btn02, btn10, btn11, btn12, btn20, btn21, btn22}) {
            button.setDisable(true);
        }
    }

    private Button getButtonByPosition(int row, int col) {
        Button[][] buttons = {
                {btn00, btn01, btn02},
                {btn10, btn11, btn12},
                {btn20, btn21, btn22}
        };

        if (row >= 0 && row < buttons.length && col >= 0 && col < buttons[row].length) {
            return buttons[row][col];
        }
        return null; // If no button is found
    }
}
