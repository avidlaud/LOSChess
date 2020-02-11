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
}
