package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {

    private final ChessGame.TeamColor pieceColor;
    private final PieceType type;

    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
        this.pieceColor = pieceColor;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessPiece that = (ChessPiece) o;
        return pieceColor.equals(that.pieceColor) && type.equals(that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceColor, type);
    }

    /**
     * The various different chess piece options
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    /**
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor() {
        return pieceColor;
        //throw new RuntimeException("Not implemented");
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return type;
        //throw new RuntimeException("Not implemented");
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {

        //Collection<ChessMove> validMoves = new ArrayList<ChessMove>();
        ChessPiece pieceAtStart = board.getPiece(myPosition);
        //get positions for each piece
        //pawn
        if(pieceAtStart.getPieceType() == ChessPiece.PieceType.PAWN) {
            return validPawnMoves(pieceAtStart,myPosition);
        }
        //rook
        else if(pieceAtStart.getPieceType() == ChessPiece.PieceType.ROOK) {
            return validRookMoves(pieceAtStart,myPosition);
        }
        //knight
        else if(pieceAtStart.getPieceType() == ChessPiece.PieceType.KNIGHT) {
            return validKnightMoves(pieceAtStart,myPosition);
        }
        //queen
        else if(pieceAtStart.getPieceType() == ChessPiece.PieceType.QUEEN) {
            return validQueenMoves(pieceAtStart,myPosition);
        }
        //king
        else if(pieceAtStart.getPieceType() == ChessPiece.PieceType.KING) {
            return validKingMoves(pieceAtStart,myPosition);
        }
        //else if (pieceAtStart.getPieceType() == ChessPiece.PieceType.BISHOP) {
        else {
            return validBishopMoves(pieceAtStart,myPosition);
        }
    }

    //Check if these methods should be here.
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
        ArrayList<ArrayList<Integer>> toPlaces = new ArrayList<>();
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
        if(startPosition.getRow()-2 > 0 && startPosition.getColumn()+1 < 9){
            ChessPosition position = new ChessPosition(startPosition.getRow()-2, startPosition.getColumn()+1);
            ChessMove move = new ChessMove(startPosition,position);
            moves.add(move);
        }
        if(startPosition.getRow()-1 > 0 && startPosition.getColumn()+2 < 9){
            ChessPosition position = new ChessPosition(startPosition.getRow()-1, startPosition.getColumn()+2);
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
}
