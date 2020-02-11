package com.LOSChess;

public class Board {

    public final static int MIN_RANK = 1;
    public final static int MIN_FILE = 1;
    public final static int MAX_RANK = 8;
    public final static int MAX_FILE = 8;

    private Player white;
    private Player black;

    private Piece[][] board = new Piece[8][8];

    public Board(Player white, Player black) {
        //Initialize pawns
        for(int i = 0; i <= 7; i++) { // changed from 1-8 to 0-7 b/c didnt work with 1-8
            board[i][Pawn.WHITESTARTRANK-1] = new Pawn(i+1, white);
            board[i][Pawn.BLACKSTARTRANK-1] = new Pawn(i+1, black);
        }
        //Initialize rooks
        board[0][0] = new Rook(1, white);
        board[7][0] = new Rook(8, black);
        board[0][7] = new Rook(1, white);
        board[7][7] = new Rook(8, black);
        //Initialize knights

        board[1][0] = new Knight(2,1, white);
        board[6][0] = new Knight(7,1, white);
        board[1][7] = new Knight(2,8, black);
        board[6][7] = new Knight(7,8, black);
        //Initialize bishops
        board[2][0] = new Bishop(2, white);
        board[5][0] = new Bishop(5, white);
        board[2][7] = new Bishop(2, black);
        board[5][7] = new Bishop(5, black);
        //Initialize queens
        board[3][0] = new Queen(3, white);
        board[3][7] = new Queen(3, black);
        //Initialize kings
        board[4][0] = new King(5, 1, white);
        board[4][7] = new King(5, 8, black);

        this.white = white;
        this.black = black;
    }

    private int[] pieceIndexing(int[] input) {
        return new int[]{input[0]-1, input[1] -1};
    }

    public void printBoard(){ //added printBoard for the driver
        //Player temp = new Player();
        char[][] dispBoard = new char[8][8];
        for(int rank = 0; rank<board.length; rank++){
            for(int file = 0; file<board[rank].length; file++){
                if(board[file][rank] instanceof Pawn) System.out.print("P" + " ");
                else if(board[file][rank] instanceof Rook) System.out.print("R" + " ");
                else if(board[file][rank] instanceof Bishop) System.out.print("B" + " ");
                else if(board[file][rank] instanceof Queen) System.out.print("Q" + " ");
                else if(board[file][rank] instanceof King) System.out.print("K" + " ");
                else if(board[file][rank] instanceof Knight) System.out.print("N" + " ");
                else{
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
    }

    public Player getEnemy(Piece piece) {
        if(piece.getPlayer() == white) {
            return black;
        }
        return white;
    }

    /**
     * Determine the new position of after moving forward x ranks and right y files, from the perspective of a given player.
     * @param player Player from which to calculate position
     * @param startPos Int array of {file, rank} for starting position
     * @param rMove Number of ranks to move, positive indicating moving forward, e.g., 1 -> 3 for white
     * @param fMove Number of files to move, positive indicating moving right, e.g., F -> D for black
     * @return Int array of new {file, rank} or null if off board
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

    /**
     * Check what piece, if any, is in the given board position
     * @param pos Int array of {file, rank} to check
     * @return The Piece at the position, or null if empty
     */
    public Piece checkPos(int[] pos) {
        return board[pieceIndexing(pos)[0]][pieceIndexing(pos)[1]];
    }

    /**
     * Check if the given location is a valid location on the board, i.e., A-H and 1-8
     * @param pos Int array for postion to check
     * @return True if legal position, false if not
     */
    private static boolean validPosition(int[] pos) {
        return (!(pos[0] < 1 || pos[0] > 8 || pos[1] < 1 || pos[1] > 8));
    }

    /**
     * Move a piece on the board from one position to another
     * @param piece The piece to move
     * @param destination The final destination of the piece
     */
    public void movePiece(Piece piece, int[] destination) {
        if(!validPosition(destination)) { //Illegal position
            return;
        }
        board[piece.getFile()][piece.getRank()] = null;
        piece.setPosition(destination);
        board[destination[0]][destination[1]] = piece;
    }

    /**
     * Determine if a position is under attack, used for prohibiting King moves
     * @param enemyPlayer The target player whose pieces attack the position
     * @param position The position on the board to check
     * @return True if under attack by enemyPlayer, false otherwise
     */
    public boolean underAttack(Player enemyPlayer, int[] position) {
        for (Piece p : enemyPlayer.getPieces()) {
            if(p.getMoves(this).contains(position)) {
                return true;
            }
        }
        return false;
    }


}
