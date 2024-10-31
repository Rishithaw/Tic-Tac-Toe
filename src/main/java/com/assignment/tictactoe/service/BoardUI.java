package com.assignment.tictactoe.service;

public interface BoardUI {
//    void update(int row, int col, boolean isHuman);
//    void NotifyWinner();
    void update(int row, int col, Piece piece);
    void NotifyWinner(Piece winner);
}
