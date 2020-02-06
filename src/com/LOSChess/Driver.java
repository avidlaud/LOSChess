package com.LOSChess;

public class Driver {
    public static void main(String[] args){
        Player one = new Player();
        Player two = new Player();

        Board board = new Board(one, two);
        board.printBoard();

    }
}
