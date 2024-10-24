package com.assignment.tictactoe.service;

public enum Piece{
    X,O,EMPTY;

    @Override
    public String toString() {
        return this == EMPTY ? "." : this.name();
    }
}
