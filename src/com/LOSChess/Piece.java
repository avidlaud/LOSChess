package com.LOSChess;

public class Piece {

    private int rank, file;
    private Player player;

    public Piece(int rank, int file, Player player) {
        this.rank = rank;
        this.file = file;
        this.player = player;
    }

    public Piece() {}

    public int getRank() {
        return rank;
    }

    public int getFile() {
        return file;
    }

    public int[] getPosition() {
        return new int[]{rank, file};
    }

    public void setRank(int rank) {
        if(rank < Board.MIN_RANK || rank > Board.MAX_RANK) {

        }
        this.rank = rank;
    }

    public void setFile(int file) {
        this.file = file;
    }

    public void setPosition(int[] pos) {
        this.rank = pos[0];
        this.file = pos[1];
    }

    //Will be overridden by the subclasses
    public int[] getMoves() {
        return null;
    }

}

