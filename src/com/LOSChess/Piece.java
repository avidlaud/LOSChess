package com.LOSChess;

import java.util.ArrayList;

public class Piece {

    private int rank, file, numMoves;
    private Player player;

    public Piece(int file, int rank, Player player) {
        this.file = file;
        this.rank = rank;
        this.player = player;
    }

    public Piece() {}

    public int getRank() {
        return rank;
    }

    public int getFile() {
        return file;
    }

    public Player getPlayer() {
        return player;
    }

    public int getNumMoves() {
        return numMoves;
    }

    public int[] getPosition() {
        return new int[]{file, rank};
    }

    public void setRank(int rank) {
        if(rank < Board.MIN_RANK || rank > Board.MAX_RANK) {
            throw new ArrayIndexOutOfBoundsException("Illegal Rank");
        }
        this.rank = rank;
    }

    public void setFile(int file) {
        if(file < Board.MIN_FILE || file > Board.MAX_FILE) {
            throw new ArrayIndexOutOfBoundsException("Illegal Rank");
        }
        this.file = file;
    }

    public void setPosition(int[] pos) {
        this.file = pos[0];
        this.rank = pos[1];
    }

    //Will be overridden by the subclasses
    public ArrayList<int[]> getMoves(Board board) {
        return null;
    }
}

