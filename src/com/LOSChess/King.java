package com.LOSChess;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece{

    private static final List<int[]> standardMoves = new ArrayList<int[]>() {
        {
            add(new int[]{1, 1});
            add(new int[]{1, -1});
            add(new int[]{-1, 1});
            add(new int[]{-1, -1});
            add(new int[]{0, 1});
            add(new int[]{0, -1});
            add(new int[]{1, 0});
            add(new int[]{-1, 0});
        }
    };

    public King(int file, int rank, Player player) {
        super(file, rank, player);
    }

    @Override
    public ArrayList<int[]> getMoves(Board board) {
        ArrayList<int[]> moves = new ArrayList<>();
        for(int[] i: standardMoves) {
            if(!board.underAttack(board.getEnemy(this), i)) {
                if((board.checkPos(Board.calcPos(getPlayer(), getPosition(), i[0], i[1])) == null)
                        || (board.checkPos(Board.calcPos(getPlayer(), getPosition(), i[0], i[1])).getPlayer() != getPlayer())) {
                    moves.add(Board.calcPos(getPlayer(), getPosition(), i[0], i[1]));
                }
            }
        }
        return moves;
    }

    public boolean canCastle(Board board) {
        if(this.getNumMoves() != 0) {
            return false;
        }
        //Find rooks
        ArrayList<Piece> rooks = new ArrayList<>();
        for(Piece p : this.getPlayer().getPieces()) {
            if(p instanceof Rook) {
                rooks.add(p);
            }
        }
        if(rooks.size() != 2) {
            return false;
        }
        for(Piece r : rooks) {
            if (r.getNumMoves() != 0) {
                return false;
            }
        }
        //King side castle
        if(this.getPlayer().isWhite()) { //Check the first rank
            if(board.checkPos(new int[] {2,1}) == null && board.checkPos(new int[] {3,1}) == null) {
                return true;
            }
        }
        return true;
    }
}
