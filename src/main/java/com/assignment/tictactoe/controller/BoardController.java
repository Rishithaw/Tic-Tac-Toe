package com.assignment.tictactoe.controller;

import com.assignment.tictactoe.service.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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
    private GridPane grdPane;

    @FXML
    private Label lblHeader;

    @FXML
    private Label lblWinner;

    BoardImpl board;
    AiPlayer ai;
    HumanPlayer human;

    public BoardController() {
        board = new BoardImpl();
        ai = new AiPlayer(board);
        human = new HumanPlayer(board);
    }

    @FXML
    private void handleOnAction(ActionEvent event) {
        Button button = (Button) event.getSource();
        int row = Integer.parseInt(button.getId().split("")[2]);
        int col = Integer.parseInt(button.getId().split("")[3]);

        if (board.isLegalMove(row, col)) {
            human.move(row, col);
            ai.findBestMove();
            board.printBoard();
            updateUi();

            Piece winner = board.checkWinner() != null ? board.checkWinner().getWinningPiece() : null;

            if (winner != null) {
                NotifyWinner(winner);
            } else if (board.isFull()) {
                lblHeader.setText("So Close!");
                lblWinner.setText("It's a Tie!");
            }
        }
    }

    public void updateUi() {
        for (int i = 0; i < board.getPieces().length; i++) {
            for (int j = 0; j < board.getPieces()[i].length; j++) {
                update(i, j, board.getPieces()[i][j]);
            }
        }
    }

    @FXML
    void resetOnAction(ActionEvent event) {
        try {
            ancPane.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/game.fxml"));
            ancPane.getChildren().add(load);
        }catch (IOException e){
            new Alert(Alert.AlertType.ERROR,"Failed to load UI").show();
        }
    }

    @Override
    public void update(int row, int col, Piece piece) {
        String buttonId = "id" + row + col;
        for (Node node : grdPane.getChildren()) {
            if (node instanceof Button button && buttonId.equals(node.getId())) {
                if (piece == Piece.X) {
                    button.setText("X");
                } else if (piece == Piece.O) {
                    button.setText("O");
                } else {
                    button.setText("");
                }
                break;
            }
        }
    }

    @Override
    public void NotifyWinner(Piece winner) {
        if (winner == Piece.X) {
            lblHeader.setText("You Beat the Game");
            lblWinner.setText("Player X Wins!");
        } else if (winner == Piece.O) {
            lblHeader.setText("Better Luck Next Time");
            lblWinner.setText("Player O Wins!");
        }
    }

//    @Override
//    public void update(int row, int col, Piece piece) {
//        String buttonId = "id" + row + col;
//        for (Node node : grdPane.getChildren()) {
//            if (node instanceof Button button && buttonId.equals(node.getId())) {
//                if (piece == Piece.X) {
//                    button.setText("X");
//                } else if (piece == Piece.O) {
//                    button.setText("O");
//                } else {
//                    button.setText("");
//                }
//                break;
//            }
//        }
//    }
//
//    @Override
//    public void NotifyWinner(Piece winner) {
//        if (winner == Piece.X) {
//            lblHeader.setText("You Beat the Game");
//            lblWinner.setText("Player X Wins!");
//        } else if (winner == Piece.O) {
//            lblHeader.setText("Better Luck Next Time");
//            lblWinner.setText("Player O Wins!");
//        }
//    }
}
