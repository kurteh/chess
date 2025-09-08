package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return type;
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        ChessPiece piece = board.getPiece(myPosition);
        List<ChessMove> moves = new ArrayList<>();
        int start_col = myPosition.getColumn();
        int start_row = myPosition.getRow();
        // below is hardcoded to pass test case
        //if (piece.getPieceType() == PieceType.BISHOP){
        //   return List.of(new ChessMove(new ChessPosition(5,4), new ChessPosition(1,8), null));
        //}
        //return List.of();

        // Bishop move logic
        if (piece.getPieceType() == PieceType.BISHOP){
            // up right
            start_col = myPosition.getColumn();
            start_row = myPosition.getRow();
            while ((start_row < 8) && (start_col < 8)){ // Go up and to the right as long as there is board left.
                start_row += 1;
                start_col += 1;
                ChessPosition to_position = new ChessPosition(start_row, start_col);
                // Check if position is taken, take the position is the other team has a piece there.
                if (board.getPiece(to_position) != null){
                    if (board.getPiece(to_position).pieceColor != piece.pieceColor){
                        moves.add(new ChessMove(myPosition, to_position, null));
                    }
                    break;
                } else {
                    moves.add(new ChessMove(myPosition, to_position, null));
                }
            }
            // up left
            start_col = myPosition.getColumn();
            start_row = myPosition.getRow();
            while ((start_row < 8) && (start_col > 1)){ // Go up and to the right as long as there is board left.
                start_row += 1;
                start_col -= 1;
                ChessPosition to_position = new ChessPosition(start_row, start_col);
                // Check if position is taken, take the position is the other team has a piece there.
                if (board.getPiece(to_position) != null){
                    if (board.getPiece(to_position).pieceColor != piece.pieceColor){
                        moves.add(new ChessMove(myPosition, to_position, null));
                    }
                    break;
                } else {
                    moves.add(new ChessMove(myPosition, to_position, null));
                }
            }
            // down right
            start_col = myPosition.getColumn();
            start_row = myPosition.getRow();
            while ((start_row > 1) && (start_col < 8)){ // Go up and to the right as long as there is board left.
                start_row -= 1;
                start_col += 1;
                ChessPosition to_position = new ChessPosition(start_row, start_col);
                // Check if position is taken, take the position is the other team has a piece there.
                if (board.getPiece(to_position) != null){
                    if (board.getPiece(to_position).pieceColor != piece.pieceColor){
                        moves.add(new ChessMove(myPosition, to_position, null));
                    }
                    break;
                } else {
                    moves.add(new ChessMove(myPosition, to_position, null));
                }
            }
            // down left
            start_col = myPosition.getColumn();
            start_row = myPosition.getRow();
            while ((start_row > 1) && (start_col > 1)){ // Go up and to the right as long as there is board left.
                start_row -= 1;
                start_col -= 1;
                ChessPosition to_position = new ChessPosition(start_row, start_col);
                // Check if position is taken, take the position is the other team has a piece there.
                if (board.getPiece(to_position) != null){
                    if (board.getPiece(to_position).pieceColor != piece.pieceColor){
                        moves.add(new ChessMove(myPosition, to_position, null));
                    }
                    break;
                } else {
                    moves.add(new ChessMove(myPosition, to_position, null));
                }
            }
        }

        // King move logic

        // Knight move logic

        // Pawn move logic

        // Queen move logic

        // Rook move logic

        return moves;
    }

    @Override
    public boolean equals(Object o) {
        //return super.equals(obj);
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessPiece that = (ChessPiece) o;
        if( pieceColor != that.pieceColor){
            return false;
        }
        return type == that.type;
    }

    @Override
    public int hashCode() {
        int hashed = 1;
        hashed = 31 * hashed +  pieceColor.hashCode();
        hashed = 31 * hashed +  type.hashCode();
        return hashed;
    }
}
