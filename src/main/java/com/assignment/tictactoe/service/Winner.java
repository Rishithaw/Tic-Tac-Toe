package com.assignment.tictactoe.service;

class Winner {
    private final Piece winningPiece;
    private final int col1, row1, col2, row2, col3, row3;

    public Winner(Piece winningPiece, int row1, int col1, int row2, int col2, int row3, int col3) {
        this.winningPiece = winningPiece;
        this.col1 = col1;
        this.row1 = row1;
        this.col2 = col2;
        this.row2 = row2;
        this.col3 = col3;
        this.row3 = row3;
    }

    public Piece getWinningPiece() {
        return winningPiece;
    }
}

