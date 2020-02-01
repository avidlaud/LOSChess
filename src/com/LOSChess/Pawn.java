package com.LOSChess;

public class Pawn extends Piece{

    private final static int BLACKSTARTRANK = 7;
    private final static int WHITESTARTRANK = 2;

    public Pawn(int file, Player player) {
        super(0, file, player);
        if(player.isWhite()) {
            setRank(WHITESTARTRANK);
        }
        else {
            setRank(BLACKSTARTRANK);
        }
    }

}
