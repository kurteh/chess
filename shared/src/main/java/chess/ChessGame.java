package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * For a class that can manage a chess game, making moves on a board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessGame implements Cloneable{
    private TeamColor teamTurn;
    private ChessBoard board;
    public ChessGame() {
        ChessBoard emptyBoard = new ChessBoard();
        emptyBoard.resetBoard();
        this.board = emptyBoard;
        this.teamTurn = TeamColor.WHITE;
    }

    /**
     * @return Which team's turn it is
     */
    public TeamColor getTeamTurn() {

        return teamTurn;
    }

    /**
     * Set's which teams turn it is
     *
     * @param team the team whose turn it is
     */
    public void setTeamTurn(TeamColor team) {

        teamTurn = team;
    }

    /**
     * Enum identifying the 2 possible teams in a chess game
     */
    public enum TeamColor {
        WHITE,
        BLACK
    }

    /**
     * Gets a valid moves for a piece at the given location
     *
     * @param startPosition the piece to get valid moves for
     * @return Set of valid moves for requested piece, or null if no piece at
     * startPosition
     */
    public Collection<ChessMove> validMoves(ChessPosition startPosition) {
//        Takes as input a position on the chessboard and returns all moves the piece there
//        can legally make. If there is no piece at that location, this method returns null.
//                A move is valid if it is a "piece move" for the piece at the input location and
//        making that move would not leave the team’s king in danger of check.

        //return null if no piece at location
        if(board.getPiece(startPosition) == null){
            return null;
        }

        //get the piece and its moves
        ChessPiece piece = board.getPiece(startPosition);
        List<ChessMove> moveValid = new ArrayList<>();
        Collection<ChessMove> potentialMoves = piece.pieceMoves(board, startPosition);

        //see if each move will put the king in check, if it does do not add it to the final list

        for(ChessMove move : potentialMoves){
            ChessGame copiedGame = clone();
            ChessBoard copiedBoard = copiedGame.getBoard();
            ChessPosition endPos = move.getEndPosition();
            ChessPosition startPos = move.getStartPosition();

//            TeamColor color = copiedBoard.getPiece(startPos).getTeamColor();
            TeamColor color = copiedGame.getTeamTurn();
            copiedBoard.addPiece(endPos, copiedBoard.getPiece(startPos));
            copiedBoard.deletePiece(startPos);
            if(!copiedGame.isInCheck(color)){
                moveValid.add(move);
            }
        }

        // return list
        return moveValid;
    }

    /**
     * Makes a move in a chess game
     *
     * @param move chess move to perform
     * @throws InvalidMoveException if move is invalid
     */
    public void makeMove(ChessMove move) throws InvalidMoveException {
//        Receives a given move and executes it, provided it is a legal move. If the move is illegal,
//                it throws an InvalidMoveException. A move is illegal if it is not a "valid" move
//        for the piece at the starting location, or if it’s not the corresponding team's turn.
        ChessPosition startPos = move.getStartPosition();
        ChessPosition endPos = move.getEndPosition();
        ChessPiece piece = board.getPiece(startPos);
        if(board.getPiece(startPos) == null){
            throw new InvalidMoveException();
        }
        if(board.getPiece(startPos).getTeamColor() != getTeamTurn()){
            throw new InvalidMoveException();
        }
        Collection<ChessMove> moveValid = validMoves(startPos);
        if(moveValid.contains(move)){

            //do the move
            //move the piece and account for promotion
            if(move.getPromotionPiece() == null){
                // move without promotion
                board.addPiece(endPos, piece);
                board.deletePiece(startPos);
            } else {
                //move with promotion
                ChessPiece promotedPiece = new ChessPiece(piece.getTeamColor(), move.getPromotionPiece());
                board.addPiece(endPos, promotedPiece);
                board.deletePiece(startPos);
            }
            //change team color
            if(teamTurn == TeamColor.WHITE){
                teamTurn = TeamColor.BLACK;
            } else {
                teamTurn = TeamColor.WHITE;
            }
        } else {
            throw new InvalidMoveException();
        }
    }

    /**
     * Determines if the given team is in check
     *
     * @param teamColor which team to check for check
     * @return True if the specified team is in check
     */
    public boolean isInCheck(TeamColor teamColor) {
        //Returns true if the specified team’s King could be captured by an opposing piece.
        boolean check = false;
        //Find team's king and save its location
        int king_row = 0;
        int king_col = 0;

        for(int i = 1; i < 9; i++){
            for(int k = 1; k < 9; k++){
                ChessPosition next_pos = new ChessPosition(i, k);
                if(board.getPiece(next_pos) != null){
                    if(board.getPiece(next_pos).getPieceType() == ChessPiece.PieceType.KING){
                        if(board.getPiece(next_pos).getTeamColor() == teamColor){
                            king_row = i;
                            king_col = k;
                            break;
                        }
                    }
                }
            }
        }
        ChessPosition king_pos = new ChessPosition(king_row, king_col);

        //Identify if king's location is in any of the available moves for the opposite team
        //iterate through end positions
        for(int i = 1; i < 9; i++){
            for(int k = 1; k < 9; k++){
                ChessPosition next_pos = new ChessPosition(i, k);
                if(board.getPiece(next_pos) != null){
                    if(board.getPiece(next_pos).getTeamColor() != teamColor){
                        Collection<ChessMove> moves = board.getPiece(next_pos).pieceMoves(board, next_pos);
                        for(ChessMove move: moves){
                            if(move.getEndPosition().equals(king_pos)){
                                check = true;
                                break;
                            }
                        }
                    }
                }
            }
        }
        return check;
    }

    /**
     * Determines if the given team is in checkmate
     *
     * @param teamColor which team to check for checkmate
     * @return True if the specified team is in checkmate
     */
    public boolean isInCheckmate(TeamColor teamColor) {

        throw new RuntimeException("Not implemented");
        //Returns true if the given team has no way to protect their king from being captured.

        //check if all available king moves would move the king into check or checkmate
    }

    /**
     * Determines if the given team is in stalemate, which here is defined as having
     * no valid moves while not in check.
     *
     * @param teamColor which team to check for stalemate
     * @return True if the specified team is in stalemate, otherwise false
     */
    public boolean isInStalemate(TeamColor teamColor) {

        throw new RuntimeException("Not implemented");
        //Returns true if the given team has no legal moves but their king is not in immediate danger.
    }

    /**
     * Sets this game's chessboard with a given board
     *
     * @param board the new board to use
     */
    public void setBoard(ChessBoard board) {

        this.board = board;
    }

    /**
     * Gets the current chessboard
     *
     * @return the chessboard
     */
    public ChessBoard getBoard() {

        return board;
    }

    @Override
    public int hashCode() {
        int hashed = 17;
        hashed += 31 * teamTurn.hashCode();
        hashed += 31 * board.hashCode();
        return hashed;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessGame that = (ChessGame) o;
        if(!teamTurn.equals(that.getTeamTurn())){
            return false;
        }
        if(!board.equals(that.getBoard())){
            return false;
        }
        return true;
    }

    //do we need to copy the whole game or just the board?

    // This is a shallow copy, we need a deep copy
//    @Override
//    public ChessGame clone() {
//        try {
//            return (ChessGame) super.clone();
//        } catch (CloneNotSupportedException e) {
//            throw new RuntimeException(e);
//        }
//    }

    @Override
    public ChessGame clone() {
        try {
            ChessGame clone = (ChessGame) super.clone();
            ChessBoard board_clone = (ChessBoard) getBoard().clone();
            clone.setBoard(board_clone);
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
