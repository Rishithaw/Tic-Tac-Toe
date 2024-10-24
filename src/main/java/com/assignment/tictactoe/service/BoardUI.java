package com.assignment.tictactoe.service;

public interface BoardUI {
    void update(int row, int col, boolean isHuman);
    void NotifyWinner();
}
