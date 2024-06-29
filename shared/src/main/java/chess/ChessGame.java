package chess;

import java.util.ArrayList;
import java.util.Collection;

/**
 * For a class that can manage a chess game, making moves on a board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessGame {
    private ChessBoard board;
    public ChessGame() {
        ChessBoard board = new ChessBoard(); // not sure if this should be here
    }

    /**
     * @return Which team's turn it is
     */
    public TeamColor getTeamTurn() {
        throw new RuntimeException("Not implemented");
    }

    /**
     * Set's which teams turn it is
     *
     * @param team the team whose turn it is
     */
    public void setTeamTurn(TeamColor team) {
        throw new RuntimeException("Not implemented");
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
        //throw new RuntimeException("Not implemented");

        //get piece at position, need color too
        //Collection<ChessMove> validMoves = new ArrayList<ChessMove>();
        ChessPiece pieceAtStart = board.getPiece(startPosition);
        //get positions for each piece
        //pawn
        if(pieceAtStart.getPieceType() == ChessPiece.PieceType.PAWN) {
            return validPawnMoves(pieceAtStart,startPosition);
        }
        //rook
        else if(pieceAtStart.getPieceType() == ChessPiece.PieceType.ROOK) {
            return validRookMoves(pieceAtStart,startPosition);
        }
        //knight
        else if(pieceAtStart.getPieceType() == ChessPiece.PieceType.KNIGHT) {
            return validKnightMoves(pieceAtStart,startPosition);
        }
        //queen
        else if(pieceAtStart.getPieceType() == ChessPiece.PieceType.QUEEN) {
            return validQueenMoves(pieceAtStart,startPosition);
        }
        //king
        else if(pieceAtStart.getPieceType() == ChessPiece.PieceType.KING) {
            return validKingMoves(pieceAtStart,startPosition);
        }
        //else if (pieceAtStart.getPieceType() == ChessPiece.PieceType.BISHOP) {
        else {
            return validBishopMoves(pieceAtStart,startPosition);
        }
        //return new int[][]{};
    }

    public Collection<ChessMove> validPawnMoves(ChessPiece pieceAtStart, ChessPosition startPosition){
        Collection<ChessMove> moves = new ArrayList<>();
        return moves;
    }

    public Collection<ChessMove> validRookMoves(ChessPiece pieceAtStart, ChessPosition startPosition){
        Collection<ChessMove> moves = new ArrayList<>();
        return moves;
    }

    public Collection<ChessMove> validKnightMoves(ChessPiece pieceAtStart, ChessPosition startPosition){
        Collection<ChessMove> moves = new ArrayList<>();
        //ArrayList<ArrayList<Integer>> toPlaces = new ArrayList<>();
        if(startPosition.getRow()+2 < 9 && startPosition.getColumn()+1 < 9){
            ChessPosition position = new ChessPosition(startPosition.getRow()+2, startPosition.getColumn()+1);
            ChessMove move = new ChessMove(startPosition,position);
            moves.add(move);
        }
        if(startPosition.getRow()+1 < 9 && startPosition.getColumn()+2 < 9){
            ChessPosition position = new ChessPosition(startPosition.getRow()+1, startPosition.getColumn()+2);
            ChessMove move = new ChessMove(startPosition,position);
            moves.add(move);
        }
        if(startPosition.getRow()-1 > 0 && startPosition.getColumn()+2 < 9){
            ChessPosition position = new ChessPosition(startPosition.getRow()-1, startPosition.getColumn()+2);
            ChessMove move = new ChessMove(startPosition,position);
            moves.add(move);
        }
        if(startPosition.getRow()-2 > 0 && startPosition.getColumn()+1 < 9){
            ChessPosition position = new ChessPosition(startPosition.getRow()-2, startPosition.getColumn()+1);
            ChessMove move = new ChessMove(startPosition,position);
            moves.add(move);
        }
        if(startPosition.getRow()-2 > 0 && startPosition.getColumn()-1 > 0){
            ChessPosition position = new ChessPosition(startPosition.getRow()-2, startPosition.getColumn()-1);
            ChessMove move = new ChessMove(startPosition,position);
            moves.add(move);
        }
        if(startPosition.getRow()-1 > 0 && startPosition.getColumn()-2 > 0){
            ChessPosition position = new ChessPosition(startPosition.getRow()-1, startPosition.getColumn()-2);
            ChessMove move = new ChessMove(startPosition,position);
            moves.add(move);
        }
        if(startPosition.getRow()+1 < 9 && startPosition.getColumn()-2 > 0){
            ChessPosition position = new ChessPosition(startPosition.getRow()+1, startPosition.getColumn()-2);
            ChessMove move = new ChessMove(startPosition,position);
            moves.add(move);
        }
        if(startPosition.getRow()+2 < 9 && startPosition.getColumn()-1 > 0){
            ChessPosition position = new ChessPosition(startPosition.getRow()+2, startPosition.getColumn()-1);
            ChessMove move = new ChessMove(startPosition,position);
            moves.add(move);
        }

        return moves;
    }

    public Collection<ChessMove> validQueenMoves(ChessPiece pieceAtStart, ChessPosition startPosition){
        Collection<ChessMove> moves = new ArrayList<>();
        return moves;
    }

    public Collection<ChessMove> validKingMoves(ChessPiece pieceAtStart, ChessPosition startPosition){
        Collection<ChessMove> moves = new ArrayList<>();
        return moves;
    }

    public Collection<ChessMove> validBishopMoves(ChessPiece pieceAtStart, ChessPosition startPosition){
        Collection<ChessMove> moves = new ArrayList<>();
        return moves;
    }
    /**
     * Makes a move in a chess game
     *
     * @param move chess move to preform
     * @throws InvalidMoveException if move is invalid
     */
    public void makeMove(ChessMove move) throws InvalidMoveException {
        throw new RuntimeException("Not implemented");
    }

    /**
     * Determines if the given team is in check
     *
     * @param teamColor which team to check for check
     * @return True if the specified team is in check
     */
    public boolean isInCheck(TeamColor teamColor) {
        throw new RuntimeException("Not implemented");
    }

    /**
     * Determines if the given team is in checkmate
     *
     * @param teamColor which team to check for checkmate
     * @return True if the specified team is in checkmate
     */
    public boolean isInCheckmate(TeamColor teamColor) {
        throw new RuntimeException("Not implemented");
    }

    /**
     * Determines if the given team is in stalemate, which here is defined as having
     * no valid moves
     *
     * @param teamColor which team to check for stalemate
     * @return True if the specified team is in stalemate, otherwise false
     */
    public boolean isInStalemate(TeamColor teamColor) {
        throw new RuntimeException("Not implemented");
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
}
