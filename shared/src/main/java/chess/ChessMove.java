package chess;

/**
 * Represents moving a chess piece on a chessboard
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessMove {

    private final ChessPosition startPosition;
    private final ChessPosition endPosition;
    private final ChessPiece.PieceType promotionPiece;

    public ChessMove(ChessPosition startPosition, ChessPosition endPosition,
                     ChessPiece.PieceType promotionPiece) {
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        this.promotionPiece = promotionPiece;
    }
    public ChessMove(ChessPosition startPosition, ChessPosition endPosition) {
        this(startPosition, endPosition, null);
    }

    /**
     * @return ChessPosition of starting location
     */
    public ChessPosition getStartPosition() {
        return startPosition;
    }

    /**
     * @return ChessPosition of ending location
     */
    public ChessPosition getEndPosition() {
        return endPosition;
    }

    /**
     * Gets the type of piece to promote a pawn to if pawn promotion is part of this
     * chess move
     *
     * @return Type of piece to promote a pawn to, or null if no promotion
     */
    public ChessPiece.PieceType getPromotionPiece() {
        return promotionPiece;
    }

    @Override
    public int hashCode() {
        //return super.hashCode();
        int result = startPosition.hashCode();
        result = 31 * result + endPosition.hashCode();
        if (promotionPiece != null) {
            result = 31 * result + promotionPiece.hashCode();
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null){
            return false;
        }
        if(obj == this){
            return true;
        }
        ChessMove other = (ChessMove) obj;
        if(promotionPiece == null && other.promotionPiece != null){
            return false;
        }
        if(promotionPiece != null && other.promotionPiece == null){
            return false;
        }
        if(promotionPiece == null && other.promotionPiece == null){
            return startPosition.equals(other.startPosition) && endPosition.equals(other.endPosition);
        }
        return (startPosition.equals(other.startPosition) && endPosition.equals(other.endPosition)&& promotionPiece.equals(other.promotionPiece));

    }
}
