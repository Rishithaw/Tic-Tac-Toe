package com.assignment.tictactoe.service;

public class AiPlayer extends Player {

    public AiPlayer(BoardImpl board) {
        super(board);
    }

    public void move(int row, int col) {
        int[] bestMove = findBestMove();
        board.updateMove(bestMove[0], bestMove[1], Piece.O);
    }

     //Public method for AI to make its move
    private int[] findBestMove() {
        int bestScore = Integer.MIN_VALUE;
        int[] move = new int[2]; // Store the best move

        // Iterate over all possible moves
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.isLegalMove(i, j)) {
                    // Make the move
                    board.updateMove(i, j, Piece.O);

                    // Evaluate the move using Minimax
                    int score = minimax(board, 0, false);

                    // Undo the move
                    board.updateMove(i, j, Piece.EMPTY);

                    // If the move is better than the current best, update bestScore and move
                    if (score > bestScore) {
                        bestScore = score;
                        move[0] = i;
                        move[1] = j;
                    }
                }
            }
        }
        return move;
    }

    private static int minimax(BoardImpl board, int depth, boolean isMaximizing) {
        Piece winner = board.checkWinner(); // Checks if there's a winner on the board
        if (winner == Piece.O) return 1;    // AI wins
        if (winner == Piece.X) return -1;   // Human wins
        if (board.isFull()) return 0;       // Tie

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            // AI's turn
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.isLegalMove(i, j)) {
                        board.updateMove(i, j, Piece.O);
                        int score = minimax(board, depth + 1, false);
                        board.updateMove(i, j, Piece.EMPTY);
                        bestScore = Math.max(score, bestScore);
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            // Human's turn
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.isLegalMove(i, j)) {
                        board.updateMove(i, j, Piece.X);
                        int score = minimax(board, depth + 1, true);
                        board.updateMove(i, j, Piece.EMPTY);
                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
            return bestScore;
        }
    }
}