package com.LOSChess;

import java.util.ArrayList;

public class Player {

    boolean isWhite;

    private ArrayList<Piece> pieces;

    public boolean isWhite() {
        return isWhite;
    }

    public boolean isBlack() {
        return !isWhite;
    }

    public Player(Board board, boolean isWhite) {
        int[] pos = {0, 0};
        for(int file = 1; file <= 8; file++) {
            for (int rank = 1; rank <= 8; rank++) {
                pos[0] = file;
                pos[1] = rank;
                if(board.checkPos(pos) != null) {
                    if (board.checkPos(pos).getPlayer().isWhite() && isWhite) { //White pieces
                        pieces.add(board.checkPos(pos));
                    } else if (board.checkPos(pos).getPlayer().isBlack() && !isWhite) { //Black pieces
                        pieces.add(board.checkPos(pos));
                    }
                }
            }
        }
        this.isWhite = isWhite;
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }
}
