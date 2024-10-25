package com.assignment.tictactoe.service;

public class BoardImpl implements Board {
    Piece[][] pieces = new Piece[3][3]; // 3x3 gird in which the game is played

    public BoardImpl() {
        initializeBoard(); // Initialize the game board
    }

    @Override
    public void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                pieces[i][j] = Piece.EMPTY; // Initialize board to EMPTY
            }
        }
    }

    @Override
    public boolean isLegalMove(int row, int col) { // This method is used to check is all moves are made in the grid
        return row >= 0 && row < 3 && col >= 0 && col < 3 && pieces[row][col] == Piece.EMPTY;
    }

    @Override
    public void updateMove(int row, int col, Piece piece) {
        //System.out.println("Row: "+row+"\nCol"+col+"\nTurn : "+piece);
        pieces[row][col] = piece;
    }

    @Override
    public Piece checkWinner() {
        for (int i = 0; i < 3; i++) { // 1st & 2nd, 2nd & 3rd and 3rd & 1st
            if (pieces[i][0] == pieces[i][1] && pieces[i][1] == pieces[i][2] && pieces[i][0] != Piece.EMPTY) { // Row check
                return pieces[i][0];
            }
            if (pieces[0][i] == pieces[1][i] && pieces[1][i] == pieces[2][i] && pieces[0][i] != Piece.EMPTY) { // Column check, L to R
                return pieces[0][i];
            }
        }

        if (pieces[0][0] == pieces[1][1] && pieces[1][1] == pieces[2][2] && pieces[0][0] != Piece.EMPTY) { // Diagonal check \
            return pieces[0][0];
        }

        if (pieces[0][2] == pieces[1][1] && pieces[1][1] == pieces[2][0] && pieces[0][2] != Piece.EMPTY) { // Diagonal check /
            return pieces[0][2];
        }
        return Piece.EMPTY;
    }

    @Override
    public void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(pieces[j][i] + " ");
            }
            System.out.println();
        }
        System.out.println("======");
    }

    @Override
    public boolean isFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (pieces[i][j] == Piece.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public Piece getPieces(int row, int col) {
        return pieces[row][col];
    }
}