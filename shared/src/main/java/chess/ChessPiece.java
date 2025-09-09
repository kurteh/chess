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
        if (piece.getPieceType() == PieceType.KING) {
            start_col = myPosition.getColumn();
            start_row = myPosition.getRow();
            List<Integer> row_nums = List.of(-1, 0, 1);
            List<Integer> row_cols = List.of(-1, 0, 1);
            for (int i : row_nums) {
                for (int k : row_cols) {
                    if(start_row + i > 8 || start_col + k > 8){
                        continue;
                    }
                    ChessPosition to_position = new ChessPosition(start_row + i, start_col + k);
                    if (i == 0 && k == 0) {
                        continue;
                    }
                    if ((board.getPiece(to_position) == null || board.getPiece(to_position).pieceColor != pieceColor)) {
                        moves.add(new ChessMove(myPosition, to_position, null));
                    }
                }
            }
        }
        // Knight move logic
        if (piece.getPieceType() == PieceType.KNIGHT){
            start_col = myPosition.getColumn();
            start_row = myPosition.getRow();
            List<Integer> row_nums = List.of(-2,-1, 1, 2);
            List<Integer> row_cols = List.of(-2,-1, 1, 2);
            for (int i : row_nums){
                for (int k : row_cols){
                    if ( Math.abs(i) == Math.abs(k)){
                        continue;
                    }
                    if (start_row + i > 8 || start_row + i < 1 || start_col + k > 8 || start_col + k < 1){
                        continue;
                    }
                    ChessPosition to_position = new ChessPosition(start_row + i, start_col + k);
                    if ((board.getPiece(to_position) == null || board.getPiece(to_position).pieceColor != pieceColor)) {
                        moves.add(new ChessMove(myPosition, to_position, null));
                    }
                }
            }
        }

        // Pawn move logic
        if (piece.getPieceType() == PieceType.PAWN){
            start_col = myPosition.getColumn();
            start_row = myPosition.getRow();
            if (piece.getTeamColor() == ChessGame.TeamColor.WHITE){
                // Move forward
                if (start_row < 7) {
                    ChessPosition forward = new ChessPosition(start_row + 1, start_col);
                    if (board.getPiece(forward) == null) {
                        moves.add(new ChessMove(myPosition, forward, null));
                    }
                    // Capture
                    if (start_col < 8 && board.getPiece(new ChessPosition(start_row+1, start_col+1)) != null && board.getPiece(new ChessPosition(start_row+1, start_col+1)).pieceColor != ChessGame.TeamColor.WHITE){
                        moves.add(new ChessMove(myPosition, new ChessPosition(start_row+1, start_col+1), null));
                    }
                    if (start_col > 1 && board.getPiece(new ChessPosition(start_row+1, start_col-1)) != null && board.getPiece(new ChessPosition(start_row+1, start_col-1)).pieceColor != ChessGame.TeamColor.WHITE){
                        moves.add(new ChessMove(myPosition, new ChessPosition(start_row+1, start_col-1), null));
                    }
                }
                // First move
                if ( start_row == 2) {
                    ChessPosition forward_two = new ChessPosition(start_row + 2, start_col);
                    if (board.getPiece(forward_two) == null && board.getPiece(new ChessPosition(start_row + 1, start_col)) == null) {
                        moves.add(new ChessMove(myPosition, forward_two, null));
                    }
                }
                // Promotion
                if (start_row == 7){
                    ChessPosition forward = new ChessPosition(start_row + 1, start_col);
                    if (board.getPiece(forward) == null) {
                        moves.add(new ChessMove(myPosition, forward, PieceType.QUEEN));
                        moves.add(new ChessMove(myPosition, forward, PieceType.BISHOP));
                        moves.add(new ChessMove(myPosition, forward, PieceType.ROOK));
                        moves.add(new ChessMove(myPosition, forward, PieceType.KNIGHT));
                    }
                    // Capture
                    if (start_col < 8 && board.getPiece(new ChessPosition(start_row+1, start_col+1)) != null && board.getPiece(new ChessPosition(start_row+1, start_col+1)).pieceColor != ChessGame.TeamColor.WHITE){
                        moves.add(new ChessMove(myPosition, new ChessPosition(start_row+1, start_col+1), PieceType.QUEEN));
                        moves.add(new ChessMove(myPosition, new ChessPosition(start_row+1, start_col+1), PieceType.BISHOP));
                        moves.add(new ChessMove(myPosition, new ChessPosition(start_row+1, start_col+1), PieceType.ROOK));
                        moves.add(new ChessMove(myPosition, new ChessPosition(start_row+1, start_col+1), PieceType.KNIGHT));
                    }
                    if (start_col > 1 && board.getPiece(new ChessPosition(start_row+1, start_col-1)) != null && board.getPiece(new ChessPosition(start_row+1, start_col-1)).pieceColor != ChessGame.TeamColor.WHITE){
                        moves.add(new ChessMove(myPosition, new ChessPosition(start_row+1, start_col-1), PieceType.QUEEN));
                        moves.add(new ChessMove(myPosition, new ChessPosition(start_row+1, start_col-1), PieceType.BISHOP));
                        moves.add(new ChessMove(myPosition, new ChessPosition(start_row+1, start_col-1), PieceType.ROOK));
                        moves.add(new ChessMove(myPosition, new ChessPosition(start_row+1, start_col-1), PieceType.KNIGHT));
                    }
                }

            } else {
                // Move forward
                if (start_row > 2) {
                    ChessPosition forward = new ChessPosition(start_row - 1, start_col);
                    if (board.getPiece(forward) == null) {
                        moves.add(new ChessMove(myPosition, forward, null));
                    }
                    // Capture
                    if (start_col < 8 && board.getPiece(new ChessPosition(start_row-1, start_col+1)) != null && board.getPiece(new ChessPosition(start_row-1, start_col+1)).pieceColor != ChessGame.TeamColor.BLACK){
                        moves.add(new ChessMove(myPosition, new ChessPosition(start_row-1, start_col+1), null));
                    }
                    if (start_col > 1 && board.getPiece(new ChessPosition(start_row-1, start_col-1)) != null && board.getPiece(new ChessPosition(start_row-1, start_col-1)).pieceColor != ChessGame.TeamColor.BLACK){
                        moves.add(new ChessMove(myPosition, new ChessPosition(start_row-1, start_col-1), null));
                    }
                }
                // First move
                if ( start_row == 7) {
                    ChessPosition forward_two = new ChessPosition(start_row - 2, start_col);
                    if (board.getPiece(forward_two) == null && board.getPiece(new ChessPosition(start_row - 1, start_col)) == null) {
                        moves.add(new ChessMove(myPosition, forward_two, null));
                    }
                }
                // Promotion
                if (start_row == 2){
                    ChessPosition forward = new ChessPosition(start_row - 1, start_col);
                    if (board.getPiece(forward) == null) {
                        moves.add(new ChessMove(myPosition, forward, PieceType.QUEEN));
                        moves.add(new ChessMove(myPosition, forward, PieceType.BISHOP));
                        moves.add(new ChessMove(myPosition, forward, PieceType.ROOK));
                        moves.add(new ChessMove(myPosition, forward, PieceType.KNIGHT));
                    }
                    // Capture
                    if (start_col < 8 && board.getPiece(new ChessPosition(start_row-1, start_col+1)) != null && board.getPiece(new ChessPosition(start_row-1, start_col+1)).pieceColor != ChessGame.TeamColor.BLACK){
                        moves.add(new ChessMove(myPosition, new ChessPosition(start_row-1, start_col+1), PieceType.QUEEN));
                        moves.add(new ChessMove(myPosition, new ChessPosition(start_row-1, start_col+1), PieceType.BISHOP));
                        moves.add(new ChessMove(myPosition, new ChessPosition(start_row-1, start_col+1), PieceType.ROOK));
                        moves.add(new ChessMove(myPosition, new ChessPosition(start_row-1, start_col+1), PieceType.KNIGHT));
                    }
                    if (start_col > 1 && board.getPiece(new ChessPosition(start_row-1, start_col-1)) != null && board.getPiece(new ChessPosition(start_row-1, start_col-1)).pieceColor != ChessGame.TeamColor.BLACK){
                        moves.add(new ChessMove(myPosition, new ChessPosition(start_row-1, start_col-1), PieceType.QUEEN));
                        moves.add(new ChessMove(myPosition, new ChessPosition(start_row-1, start_col-1), PieceType.BISHOP));
                        moves.add(new ChessMove(myPosition, new ChessPosition(start_row-1, start_col-1), PieceType.ROOK));
                        moves.add(new ChessMove(myPosition, new ChessPosition(start_row-1, start_col-1), PieceType.KNIGHT));
                    }
                }
            }

        }

        // Queen move logic
        if (piece.getPieceType() == PieceType.QUEEN){
            // Rook logic.
            //Left
            start_col = myPosition.getColumn();
            start_row = myPosition.getRow();
            while (start_row > 1){
                start_row -= 1;
                ChessPosition to_position = new ChessPosition(start_row , start_col);
                if (board.getPiece(to_position) == null) {
                    moves.add(new ChessMove(myPosition, to_position, null));
                } else {
                    if (board.getPiece(to_position).pieceColor != pieceColor){
                        moves.add(new ChessMove(myPosition, to_position, null));
                    }
                    break;
                }
            }
            // Right
            start_col = myPosition.getColumn();
            start_row = myPosition.getRow();
            while (start_row < 8){
                start_row += 1;
                ChessPosition to_position = new ChessPosition(start_row , start_col);
                if (board.getPiece(to_position) == null) {
                    moves.add(new ChessMove(myPosition, to_position, null));
                } else {
                    if (board.getPiece(to_position).pieceColor != pieceColor){
                        moves.add(new ChessMove(myPosition, to_position, null));
                    }
                    break;
                }
            }
            // Up
            start_col = myPosition.getColumn();
            start_row = myPosition.getRow();
            while (start_col < 8){
                start_col += 1;
                ChessPosition to_position = new ChessPosition(start_row , start_col);
                if (board.getPiece(to_position) == null) {
                    moves.add(new ChessMove(myPosition, to_position, null));
                } else {
                    if (board.getPiece(to_position).pieceColor != pieceColor){
                        moves.add(new ChessMove(myPosition, to_position, null));
                    }
                    break;
                }
            }
            // Down
            start_col = myPosition.getColumn();
            start_row = myPosition.getRow();
            while (start_col > 1){
                start_col -= 1;
                ChessPosition to_position = new ChessPosition(start_row , start_col);
                if (board.getPiece(to_position) == null) {
                    moves.add(new ChessMove(myPosition, to_position, null));
                } else {
                    if (board.getPiece(to_position).pieceColor != pieceColor){
                        moves.add(new ChessMove(myPosition, to_position, null));
                    }
                    break;
                }
            }

            // Bishop logic
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

        if (piece.getPieceType() == PieceType.ROOK){
            //Left
            start_col = myPosition.getColumn();
            start_row = myPosition.getRow();
            while (start_row > 1){
                start_row -= 1;
                ChessPosition to_position = new ChessPosition(start_row , start_col);
                if (board.getPiece(to_position) == null) {
                    moves.add(new ChessMove(myPosition, to_position, null));
                } else {
                    if (board.getPiece(to_position).pieceColor != pieceColor){
                        moves.add(new ChessMove(myPosition, to_position, null));
                    }
                    break;
                }
            }
            // Right
            start_col = myPosition.getColumn();
            start_row = myPosition.getRow();
            while (start_row < 8){
                start_row += 1;
                ChessPosition to_position = new ChessPosition(start_row , start_col);
                if (board.getPiece(to_position) == null) {
                    moves.add(new ChessMove(myPosition, to_position, null));
                } else {
                    if (board.getPiece(to_position).pieceColor != pieceColor){
                        moves.add(new ChessMove(myPosition, to_position, null));
                    }
                    break;
                }
            }
            // Up
            start_col = myPosition.getColumn();
            start_row = myPosition.getRow();
            while (start_col < 8){
                start_col += 1;
                ChessPosition to_position = new ChessPosition(start_row , start_col);
                if (board.getPiece(to_position) == null) {
                    moves.add(new ChessMove(myPosition, to_position, null));
                } else {
                    if (board.getPiece(to_position).pieceColor != pieceColor){
                        moves.add(new ChessMove(myPosition, to_position, null));
                    }
                    break;
                }
            }
            // Down
            start_col = myPosition.getColumn();
            start_row = myPosition.getRow();
            while (start_col > 1){
                start_col -= 1;
                ChessPosition to_position = new ChessPosition(start_row , start_col);
                if (board.getPiece(to_position) == null) {
                    moves.add(new ChessMove(myPosition, to_position, null));
                } else {
                    if (board.getPiece(to_position).pieceColor != pieceColor){
                        moves.add(new ChessMove(myPosition, to_position, null));
                    }
                    break;
                }
            }
        }

        return moves;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessPiece that = (ChessPiece) o;
        if( !pieceColor.equals(that.pieceColor)){
            return false;
        }
        return type.equals(that.type);
    }

    @Override
    public int hashCode() {
        int hashed = 1;
        hashed = 31 * hashed +  pieceColor.hashCode();
        hashed = 31 * hashed +  type.hashCode();
        return hashed;
    }
}
