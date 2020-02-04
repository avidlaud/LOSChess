package com.LOSChess;

import java.util.ArrayList;

public class Pawn extends Piece{

    public final static int BLACKSTARTRANK = 7;
    public final static int WHITESTARTRANK = 2;

    public Pawn(int file, Player player) {
        super(file, 0, player);
        if(player.isWhite()) {
            setRank(WHITESTARTRANK);
        }
        else {
            setRank(BLACKSTARTRANK);
        }
    }

    @Override
    public ArrayList<int[]> getMoves(Board board) {
        ArrayList<int[]> moves = new ArrayList<>();
        //First move - able to move up two
        if(getNumMoves() == 0) {
            moves.add(Board.calcPos(getPlayer(), getPosition(), 0, 2));
        }
        moves.add(Board.calcPos(getPlayer(), getPosition(), 0, 1));
        if(board.checkPos(Board.calcPos(getPlayer(), getPosition(), 1, 1)).getPlayer() != getPlayer()) {
            moves.add(Board.calcPos(getPlayer(), getPosition(), 1, 1));
        }
        if(board.checkPos(Board.calcPos(getPlayer(), getPosition(), -1, 1)).getPlayer() != getPlayer()) {
            moves.add(Board.calcPos(getPlayer(), getPosition(), -1, 1));
        }
        return moves;
    }

    /**
     * Move pawn to a new location
     * @param destination Int array of {file, rank} for destination on board
     * @param board Board used
     * @return True if successfully moved pawn, false otherwise
     */
    public boolean move(int[] destination, Board board) {
        if(!getMoves(board).contains(destination)) { //Illegal move
            return false;
        }
        else {
            board.movePiece(this, destination);
            return true;
        }
    }

}
