package com.LOSChess;

import java.util.List;
import java.util.ArrayList;

public class Knight extends Piece{

    /**
     * List of all valid knight moves
     */
    private static final List<int[]> jumps = new ArrayList<int[]>() {
        {
            add(new int[]{1,2});
            add(new int[]{-1, 2});
            add(new int[]{2,1});
            add(new int[]{-2, 1});
            add(new int[]{1,-2});
            add(new int[]{-1,-2});
            add(new int[]{2, -1});
            add(new int[]{-2, -1});
        }
    };

    public Knight(int file, int rank, Player player){
        super(file, rank, player);
    }

    @Override
    public ArrayList<int[]> getMoves(Board board) {
        ArrayList<int[]> moves = new ArrayList<>();
        for(int[] i : jumps) {
            if((board.checkPos(Board.calcPos(getPlayer(), getPosition(), i[0], i[1])) == null)
                || (board.checkPos(Board.calcPos(getPlayer(), getPosition(), i[0], i[1])).getPlayer() != getPlayer())) {
                moves.add(Board.calcPos(getPlayer(), getPosition(), i[0], i[1]));
            }
        }
        return moves;
    }

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
