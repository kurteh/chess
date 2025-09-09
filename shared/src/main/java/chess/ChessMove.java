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

//    @Override
//    public String toString() {
//        return String.format("%s%s", startPosition, endPosition);
//    }

    @Override

    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessMove that = (ChessMove) o;
        if (!startPosition.equals(that.getStartPosition())){
            return false;
        }
        if(!endPosition.equals(that.getEndPosition())){
            return false;
        }
        if (promotionPiece == null && that.getPromotionPiece() == null){
            return true;
        }
        if (promotionPiece == null || that.getPromotionPiece() == null){
            return false;
        }
        if(!promotionPiece.equals(that.getPromotionPiece())){
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hashed = 1;
        hashed = 31 * hashed + startPosition.hashCode();
        hashed = 31 * hashed + endPosition.hashCode();
        if(promotionPiece != null) {
            hashed = 31 * hashed + promotionPiece.hashCode();
        }
        return hashed;
    }

    @Override
    public String toString(){
        String move = "[" + startPosition.getRow() + "," + startPosition.getColumn() + "]" + " " +
                      "[" + endPosition.getRow() + "," + endPosition.getColumn() + "]";
        if (promotionPiece != null){
            move +=  " " + promotionPiece;
        } else {
            move += " null";
        }
        return move;
    }

}
