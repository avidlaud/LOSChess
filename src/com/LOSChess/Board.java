package com.LOSChess;

public class Board {

    public final static int MIN_RANK = 1;
    public final static int MIN_FILE = 1;
    public final static int MAX_RANK = 8;
    public final static int MAX_FILE = 8;

    Piece[][] board = new Piece[8][8];

    public Board(Player white, Player black) {
        //Initialize pawns
        for(int i = 1; i <= 8; i++) {
            board[i][Pawn.WHITESTARTRANK-1] = new Pawn(i, white);
            board[i][Pawn.BLACKSTARTRANK-1] = new Pawn(i, black);
        }
        //Initialize rooks
        board[0][0] = new Rook(0, white);
        board[7][0] = new Rook(7, black);
        board[0][7] = new Rook(0, white);
        board[7][7] = new Rook(7, black);
        //Initialize knights
        board[1][0] = new Knight(1, white);
        board[6][0] = new Knight(6, white);
        board[1][7] = new Knight(1, black);
        board[6][7] = new Knight(6, black);
        //Initialize bishops
        board[2][0] = new Bishop(2, white);
        board[5][0] = new Bishop(5, white);
        board[2][7] = new Bishop(2, black);
        board[5][7] = new Bishop(5, black);
        //Initialize queens
        board[3][0] = new Queen(3, white);
        board[3][7] = new Queen(3, black);
        //Initialize kings
        board[4][0] = new King(4, white);
        board[4][7] = new King(4, black);
    }

    private int[] pieceIndexing(int[] input) {
        return new int[]{input[0]-1, input[1] -1};
    }

    /**
     *
     * @param player
     * @param startPos
     * @param rMove
     * @param fMove
     * @return Int array of new {rank, file} or null if off board
     */
    public static int[] calcPos(Player player, int[] startPos, int fMove, int rMove) {
        int[] finalPos = {0, 0};
        if(player.isWhite()) {
            finalPos[0] = startPos[0] + fMove;
            finalPos[1] = startPos[1] + rMove;
            if(!validPosition(finalPos)) {
                return null;
            }
        }
        else { //Black
            finalPos[0] = startPos[0] - fMove;
            finalPos[1] = startPos[1] - rMove;
            if(!validPosition(finalPos)) {
                return null;
            }
        }
        return finalPos;
    }

    public Piece checkPos(int[] pos) {
        return board[pieceIndexing(pos)[0]][pieceIndexing(pos)[1]];
    }

    private static boolean validPosition(int[] pos) {
        return (!(pos[0] < 1 || pos[0] > 8 || pos[1] < 1 || pos[1] > 8));
    }

    public void movePiece(Piece piece, int[] destination) {

    }


}
