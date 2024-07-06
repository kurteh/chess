package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * For a class that can manage a chess game, making moves on a board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessGame {
    private ChessBoard board;
    private TeamColor teamTurn;
    public ChessGame() {
        this.board = new ChessBoard();
        this.teamTurn = TeamColor.WHITE;
        this.board.resetBoard(); // should this be here? Helps pass game status test newgame

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
        this.teamTurn = team;
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
        Collection<ChessMove> valid = new ArrayList<>();
        Collection<ChessMove> possible = board.getPiece(startPosition).pieceMoves(board,startPosition);
        for (ChessMove move : possible) {
            if(hypotheticalMove(move)){
                valid.add(move);
            }
        }
        return valid;
    }

    public boolean hypotheticalMove(ChessMove move){
        //doesn't actually make copy, makes moves.
        //ChessBoard boardCopy = this.board;
        //TeamColor teamTurnCopy = this.teamTurn;
//        if(move.getStartPosition() == null){
//            return false;
//        }
        ChessBoard boardCopy = new ChessBoard(board);//pass in board as parameter, team turn
        ChessPosition startPosition = move.getStartPosition();
        ChessPosition endPosition = move.getEndPosition();
        boardCopy.addPiece(endPosition,boardCopy.getPiece(startPosition));
        boardCopy.addPiece(startPosition,null);
        if(isInCheck(teamTurn,boardCopy)){
            return false;
        }
        return true;
    }

    public void nextTurn(){
        if(this.teamTurn == TeamColor.BLACK){
            this.teamTurn = TeamColor.WHITE;
        }
        else{
            this.teamTurn = TeamColor.BLACK;
        }
    }
    /**
     * Makes a move in a chess game
     *
     * @param move chess move to preform
     * @throws InvalidMoveException if move is invalid
     */
    public void makeMove(ChessMove move) throws InvalidMoveException {
        ChessPosition startPosition = move.getStartPosition();
        ChessPosition endPosition = move.getEndPosition();
        if(board.getPiece(move.getStartPosition()) == null){
            throw new InvalidMoveException();
        }
        Collection<ChessMove> validMoves = validMoves(startPosition);
//        if(board.getPiece(move.getStartPosition()) == null){
//            throw new InvalidMoveException();
//        }
        if(validMoves.contains(move)){
            board.addPiece(endPosition,board.getPiece(startPosition));
            board.addPiece(startPosition,null);
            nextTurn();
        }
        else{
            throw new InvalidMoveException(); // is this right?
        }

    }

    /**
     * Determines if the given team is in check
     *
     * @param teamColor which team to check for check
     * @return True if the specified team is in check
     */
    public boolean isInCheck(TeamColor teamColor) {

        //THIS DOES NOT ACCOUNT FOR VALID MOVES. THIS MAY NEED TO BE UPDATED
        Collection<ChessMove> otherTeamMoves = new ArrayList<>();
        ChessPosition kingSpot = new ChessPosition(1,1);
        for(int i = 1; i <= 8; i++) {
            for(int j = 1; j <= 8; j++) {
                ChessPosition place = new ChessPosition(i, j);
                if(board.getPiece(place) != null) {
                    if (board.getPiece(place).getTeamColor() != teamColor) {
                        otherTeamMoves.addAll(board.getPiece(place).pieceMoves(board,place));
                    }
                    if(board.getPiece(place).getTeamColor() == teamColor) {
                        if(board.getPiece(place).getPieceType() == ChessPiece.PieceType.KING){
                            kingSpot = place;
                        }
                    }
                }

            }
        }
        //check if king is in other team's valid move set
        for(ChessMove move : otherTeamMoves) {
            if(move.getEndPosition().equals(kingSpot)) {
                return true;
            }
        }
        return false;
    }

    //This is for checking a hypothetical move
    public boolean isInCheck(TeamColor teamColor, ChessBoard boardCopy) {
        //THIS DOES NOT ACCOUNT FOR VALID MOVES. THIS MAY NEED TO BE UPDATED
        Collection<ChessMove> otherTeamMoves = new ArrayList<>();
        ChessPosition kingSpot = new ChessPosition(1,1);
        for(int i = 1; i <= 8; i++) {
            for(int j = 1; j <= 8; j++) {
                ChessPosition place = new ChessPosition(i, j);
                if(boardCopy.getPiece(place) != null) {
                    if (boardCopy.getPiece(place).getTeamColor() != teamColor) {
                        otherTeamMoves.addAll(boardCopy.getPiece(place).pieceMoves(boardCopy,place));
                    }
                    if(boardCopy.getPiece(place).getTeamColor() == teamColor) {
                        if(boardCopy.getPiece(place).getPieceType() == ChessPiece.PieceType.KING){
                            kingSpot = place;
                        }
                    }
                }

            }
        }
        //check if king is in other team's valid move set
        for(ChessMove move : otherTeamMoves) {
            if(move.getEndPosition().equals(kingSpot)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determines if the given team is in checkmate
     *
     * @param teamColor which team to check for checkmate
     * @return True if the specified team is in checkmate
     */
    public boolean isInCheckmate(TeamColor teamColor) {
        //if team's valid moves set it empty, return true
        Collection<ChessMove> moves = new ArrayList<>();
        for(int i = 1; i <= 8; i++) {
            for(int j = 1; j <= 8; j++) {
                ChessPosition place = new ChessPosition(i, j);
                if(board.getPiece(place) != null) {
                    if(board.getPiece(place).getTeamColor() == teamColor) {
                        moves.addAll(validMoves(place));
                    }
                }

            }
        }
        if(moves.isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Determines if the given team is in stalemate, which here is defined as having
     * no valid moves
     *
     * @param teamColor which team to check for stalemate
     * @return True if the specified team is in stalemate, otherwise false
     */
    public boolean isInStalemate(TeamColor teamColor) {
        Collection<ChessMove> moves = new ArrayList<>();
        for(int i = 1; i <= 8; i++) {
            for(int j = 1; j <= 8; j++) {
                ChessPosition place = new ChessPosition(i, j);
                if(board.getPiece(place) != null) {
                    if(board.getPiece(place).getTeamColor() == teamColor) {
                        moves.addAll(validMoves(place));
                    }
                }
            }
        }
        return moves.isEmpty();
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


    //do we need this for testing?
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessGame chessGame = (ChessGame) o;
        return board.equals(chessGame.board) && teamTurn == chessGame.teamTurn;
    }

    @Override
    public int hashCode() {
        return Objects.hash(board, teamTurn);
    }
}
