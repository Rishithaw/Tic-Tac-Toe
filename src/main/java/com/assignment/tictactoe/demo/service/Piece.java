package com.assignment.tictactoe.demo.service;

public enum Piece {
    X,O,EMPTY;

    @Override
    public String toString() {
        return this == EMPTY ? "." : this.name();
    }
}
