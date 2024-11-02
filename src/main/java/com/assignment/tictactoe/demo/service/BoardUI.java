package com.assignment.tictactoe.demo.service;

public interface BoardUI {
    void update(int row, int col, Piece piece);
    void NotifyWinner(Piece winner);
}
