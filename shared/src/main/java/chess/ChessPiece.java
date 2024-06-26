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
        //return Objects.hash(pieceColor, type);
        int result = pieceColor.hashCode();
        result = 31 * result + type.hashCode();
        return result; // check this
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
            return validPawnMoves(board,pieceAtStart,myPosition);
        }
        //rook
        else if(pieceAtStart.getPieceType() == ChessPiece.PieceType.ROOK) {
            return validRookMoves(board,pieceAtStart,myPosition);
        }
        //knight
        else if(pieceAtStart.getPieceType() == ChessPiece.PieceType.KNIGHT) {
            return validKnightMoves(board,pieceAtStart,myPosition);
        }
        //queen
        else if(pieceAtStart.getPieceType() == ChessPiece.PieceType.QUEEN) {
            return validQueenMoves(board,pieceAtStart,myPosition);
        }
        //king
        else if(pieceAtStart.getPieceType() == ChessPiece.PieceType.KING) {
            return validKingMoves(board,pieceAtStart,myPosition);
        }
        //else if (pieceAtStart.getPieceType() == ChessPiece.PieceType.BISHOP) {
        else {
            return validBishopMoves(board,pieceAtStart,myPosition);
        }
    }

    public Collection<ChessMove> validPawnMoves(ChessBoard board, ChessPiece pieceAtStart, ChessPosition startPosition){
        Collection<ChessMove> moves = new ArrayList<>();
        //if(pieceAtStart.getTeamColor() == ChessGame.TeamColor.WHITE){
        if(pieceAtStart.getTeamColor() == ChessGame.TeamColor.WHITE && startPosition.getRow()+1<9) {
            ChessPosition positionUpRow = new ChessPosition(startPosition.getRow() + 1, startPosition.getColumn());
            if (positionUpRow.getRow() < 9 && board.getPiece(positionUpRow) == null) {
                if (positionUpRow.getRow() != 8) {
                    ChessMove move = new ChessMove(startPosition, positionUpRow);
                    moves.add(move);
                } else {
                    ChessMove move1 = new ChessMove(startPosition, positionUpRow, PieceType.BISHOP);
                    ChessMove move2 = new ChessMove(startPosition, positionUpRow, PieceType.KNIGHT);
                    ChessMove move3 = new ChessMove(startPosition, positionUpRow, PieceType.ROOK);
                    ChessMove move4 = new ChessMove(startPosition, positionUpRow, PieceType.QUEEN);
                    moves.add(move1);
                    moves.add(move2);
                    moves.add(move3);
                    moves.add(move4);
                }
            }
            if (startPosition.getRow() == 2 && board.getPiece(new ChessPosition(startPosition.getRow() + 2, startPosition.getColumn())) == null && board.getPiece(new ChessPosition(startPosition.getRow() + 1, startPosition.getColumn())) == null) { // check this
                ChessMove move = new ChessMove(startPosition, new ChessPosition(startPosition.getRow() + 2, startPosition.getColumn()));
                moves.add(move);
            }
            if (startPosition.getRow() + 1 < 9 && startPosition.getColumn() + 1 < 9) {
                if ((positionUpRow.getRow() < 9 && board.getPiece(new ChessPosition(startPosition.getRow() + 1, startPosition.getColumn() + 1)) != null)) {
                    //if(board.getPiece(new ChessPosition(startPosition.getRow()+1,startPosition.getColumn()+1)).pieceColor != ChessGame.TeamColor.WHITE){
                    if (!board.getPiece(new ChessPosition(startPosition.getRow() + 1, startPosition.getColumn() + 1)).pieceColor.equals(pieceAtStart.pieceColor)) {
                        if (positionUpRow.getRow() != 8) {
                            ChessMove move = new ChessMove(startPosition, new ChessPosition(startPosition.getRow() + 1, startPosition.getColumn() + 1));
                            moves.add(move);
                        } else {
                            ChessMove move1 = new ChessMove(startPosition, new ChessPosition(startPosition.getRow() + 1, startPosition.getColumn() + 1), PieceType.BISHOP);
                            ChessMove move2 = new ChessMove(startPosition, new ChessPosition(startPosition.getRow() + 1, startPosition.getColumn() + 1), PieceType.KNIGHT);
                            ChessMove move3 = new ChessMove(startPosition, new ChessPosition(startPosition.getRow() + 1, startPosition.getColumn() + 1), PieceType.ROOK);
                            ChessMove move4 = new ChessMove(startPosition, new ChessPosition(startPosition.getRow() + 1, startPosition.getColumn() + 1), PieceType.QUEEN);
                            moves.add(move1);
                            moves.add(move2);
                            moves.add(move3);
                            moves.add(move4);
                        }
                    }
                }
            }
            if (startPosition.getRow() + 1 < 9 && startPosition.getColumn() - 1 > 0) {
                if ((positionUpRow.getRow() < 9 && board.getPiece(new ChessPosition(startPosition.getRow() + 1, startPosition.getColumn() - 1)) != null)) {
                    //if(board.getPiece(new ChessPosition(startPosition.getRow()+1,startPosition.getColumn()-1)).pieceColor != ChessGame.TeamColor.WHITE){
                    if (!board.getPiece(new ChessPosition(startPosition.getRow() + 1, startPosition.getColumn() - 1)).pieceColor.equals(pieceAtStart.pieceColor)) {
                        if (positionUpRow.getRow() != 8) {
                            ChessMove move = new ChessMove(startPosition, new ChessPosition(startPosition.getRow() + 1, startPosition.getColumn() - 1));
                            moves.add(move);
                        } else {
                            ChessMove move1 = new ChessMove(startPosition, new ChessPosition(startPosition.getRow() + 1, startPosition.getColumn() - 1), PieceType.BISHOP);
                            ChessMove move2 = new ChessMove(startPosition, new ChessPosition(startPosition.getRow() + 1, startPosition.getColumn() - 1), PieceType.KNIGHT);
                            ChessMove move3 = new ChessMove(startPosition, new ChessPosition(startPosition.getRow() + 1, startPosition.getColumn() - 1), PieceType.ROOK);
                            ChessMove move4 = new ChessMove(startPosition, new ChessPosition(startPosition.getRow() + 1, startPosition.getColumn() - 1), PieceType.QUEEN);
                            moves.add(move1);
                            moves.add(move2);
                            moves.add(move3);
                            moves.add(move4);
                        }
                    }
                }
            }
        }
        //else{
        else if(pieceAtStart.getTeamColor() == ChessGame.TeamColor.BLACK && startPosition.getRow()-1 > 0) {
            ChessPosition positionDownRow = new ChessPosition(startPosition.getRow() - 1, startPosition.getColumn());
            if (positionDownRow.getRow() > 0 && board.getPiece(positionDownRow) == null) {
                if (positionDownRow.getRow() != 1) {
                    ChessMove move = new ChessMove(startPosition, positionDownRow);
                    moves.add(move);
                } else {
                    ChessMove move1 = new ChessMove(startPosition, positionDownRow, PieceType.BISHOP);
                    ChessMove move2 = new ChessMove(startPosition, positionDownRow, PieceType.KNIGHT);
                    ChessMove move3 = new ChessMove(startPosition, positionDownRow, PieceType.ROOK);
                    ChessMove move4 = new ChessMove(startPosition, positionDownRow, PieceType.QUEEN);
                    moves.add(move1);
                    moves.add(move2);
                    moves.add(move3);
                    moves.add(move4);
                }
            }
            if (startPosition.getRow() == 7 && board.getPiece(new ChessPosition(startPosition.getRow() - 2, startPosition.getColumn())) == null && board.getPiece(new ChessPosition(startPosition.getRow() - 1, startPosition.getColumn())) == null) { // check this
                ChessMove move = new ChessMove(startPosition, new ChessPosition(startPosition.getRow() - 2, startPosition.getColumn()));
                moves.add(move);
            }
            if (startPosition.getRow() - 1 > 0 && startPosition.getColumn() + 1 < 9) {
                if ((positionDownRow.getRow() > 0 && board.getPiece(new ChessPosition(startPosition.getRow() - 1, startPosition.getColumn() + 1)) != null)) {
                    if (!board.getPiece(new ChessPosition(startPosition.getRow() - 1, startPosition.getColumn() + 1)).pieceColor.equals(pieceAtStart.pieceColor)) {
                        //if(board.getPiece(new ChessPosition(startPosition.getRow()-1,startPosition.getColumn()+1)).pieceColor != ChessGame.TeamColor.BLACK){
                        if (positionDownRow.getRow() != 1) {
                            ChessMove move = new ChessMove(startPosition, new ChessPosition(startPosition.getRow() - 1, startPosition.getColumn() + 1));
                            moves.add(move);
                        } else {
                            ChessMove move1 = new ChessMove(startPosition, new ChessPosition(startPosition.getRow() - 1, startPosition.getColumn() + 1), PieceType.BISHOP);
                            ChessMove move2 = new ChessMove(startPosition, new ChessPosition(startPosition.getRow() - 1, startPosition.getColumn() + 1), PieceType.KNIGHT);
                            ChessMove move3 = new ChessMove(startPosition, new ChessPosition(startPosition.getRow() - 1, startPosition.getColumn() + 1), PieceType.ROOK);
                            ChessMove move4 = new ChessMove(startPosition, new ChessPosition(startPosition.getRow() - 1, startPosition.getColumn() + 1), PieceType.QUEEN);
                            moves.add(move1);
                            moves.add(move2);
                            moves.add(move3);
                            moves.add(move4);
                        }
                    }
                }
            }
            if (startPosition.getRow() - 1 > 0 && startPosition.getColumn() - 1 > 0) {
                if ((positionDownRow.getRow() > 0 && board.getPiece(new ChessPosition(startPosition.getRow() - 1, startPosition.getColumn() - 1)) != null)) {
                    if (!board.getPiece(new ChessPosition(startPosition.getRow() - 1, startPosition.getColumn() - 1)).pieceColor.equals(pieceAtStart.pieceColor)) {
                        if (positionDownRow.getRow() != 1) {
                            ChessMove move = new ChessMove(startPosition, new ChessPosition(startPosition.getRow() - 1, startPosition.getColumn() + 1));
                            moves.add(move);
                        } else {
                            ChessMove move1 = new ChessMove(startPosition, new ChessPosition(startPosition.getRow() - 1, startPosition.getColumn() - 1), PieceType.BISHOP);
                            ChessMove move2 = new ChessMove(startPosition, new ChessPosition(startPosition.getRow() - 1, startPosition.getColumn() - 1), PieceType.KNIGHT);
                            ChessMove move3 = new ChessMove(startPosition, new ChessPosition(startPosition.getRow() - 1, startPosition.getColumn() - 1), PieceType.ROOK);
                            ChessMove move4 = new ChessMove(startPosition, new ChessPosition(startPosition.getRow() - 1, startPosition.getColumn() - 1), PieceType.QUEEN);
                            moves.add(move1);
                            moves.add(move2);
                            moves.add(move3);
                            moves.add(move4);
                        }
                    }
                }
            }
        }
        return moves;
    }

    public Collection<ChessMove> validRookMoves(ChessBoard board, ChessPiece pieceAtStart, ChessPosition startPosition){
        Collection<ChessMove> moves = new ArrayList<>();
        int upRow = startPosition.getRow()+1;
        int downRow = startPosition.getRow()-1;
        int upCol = startPosition.getColumn()+1;
        int downCol = startPosition.getColumn()-1;

        while(upRow < 9){
            ChessPosition position = new ChessPosition(upRow, startPosition.getColumn());
            if(board.getPiece(position) == null){
                ChessMove move = new ChessMove(startPosition, position);
                moves.add(move);
            }
            else if(!board.getPiece(position).pieceColor.equals(pieceAtStart.pieceColor)) {
                ChessMove move = new ChessMove(startPosition, position);
                moves.add(move);
                break;
            }
            else{
                break;
            }
            upRow++;
        }

        while(downRow > 0){
            ChessPosition position = new ChessPosition(downRow, startPosition.getColumn());
            if(board.getPiece(position) == null){
                ChessMove move = new ChessMove(startPosition, position);
                moves.add(move);
            }
            else if(!board.getPiece(position).pieceColor.equals(pieceAtStart.pieceColor)) {
                ChessMove move = new ChessMove(startPosition, position);
                moves.add(move);
                break;
            }
            else{
                break;
            }
            downRow--;
        }

        while(upCol < 9){
            ChessPosition position = new ChessPosition(startPosition.getRow(), upCol);
            if(board.getPiece(position) == null){
                ChessMove move = new ChessMove(startPosition, position);
                moves.add(move);
            }
            else if(!board.getPiece(position).pieceColor.equals(pieceAtStart.pieceColor)) {
                ChessMove move = new ChessMove(startPosition, position);
                moves.add(move);
                break;
            }
            else{
                break;
            }
            upCol++;
        }

        while(downCol > 0){
            ChessPosition position = new ChessPosition(startPosition.getRow(), downCol);
            if(board.getPiece(position) == null){
                ChessMove move = new ChessMove(startPosition, position);
                moves.add(move);
            }
            else if(!board.getPiece(position).pieceColor.equals(pieceAtStart.pieceColor)) {
                ChessMove move = new ChessMove(startPosition, position);
                moves.add(move);
                break;
            }
            else{
                break;
            }
            downCol--;
        }

        return moves;
    }

    public Collection<ChessMove> validKnightMoves(ChessBoard board, ChessPiece pieceAtStart, ChessPosition startPosition){
        Collection<ChessMove> moves = new ArrayList<>();
        if(startPosition.getRow()+2 < 9 && startPosition.getColumn()+1 < 9){
            ChessPosition position = new ChessPosition(startPosition.getRow()+2, startPosition.getColumn()+1);
            if(board.getPiece(position) == null){
                ChessMove move = new ChessMove(startPosition, position);
                moves.add(move);
            }
            else if(!board.getPiece(position).pieceColor.equals(pieceAtStart.pieceColor)) {
                ChessMove move = new ChessMove(startPosition, position);
                moves.add(move);
            }
        }
        if(startPosition.getRow()+1 < 9 && startPosition.getColumn()+2 < 9){
            ChessPosition position = new ChessPosition(startPosition.getRow()+1, startPosition.getColumn()+2);
            if(board.getPiece(position) == null){
                ChessMove move = new ChessMove(startPosition, position);
                moves.add(move);
            }
            else if(!board.getPiece(position).pieceColor.equals(pieceAtStart.pieceColor)) {
                ChessMove move = new ChessMove(startPosition, position);
                moves.add(move);
            }
        }
        if(startPosition.getRow()-1 > 0 && startPosition.getColumn()+2 < 9){
            ChessPosition position = new ChessPosition(startPosition.getRow()-1, startPosition.getColumn()+2);
            if(board.getPiece(position) == null){
                ChessMove move = new ChessMove(startPosition, position);
                moves.add(move);
            }
            else if(!board.getPiece(position).pieceColor.equals(pieceAtStart.pieceColor)) {
                ChessMove move = new ChessMove(startPosition, position);
                moves.add(move);
            }
        }
        if(startPosition.getRow()-2 > 0 && startPosition.getColumn()+1 < 9){
            ChessPosition position = new ChessPosition(startPosition.getRow()-2, startPosition.getColumn()+1);
            if(board.getPiece(position) == null){
                ChessMove move = new ChessMove(startPosition, position);
                moves.add(move);
            }
            else if(!board.getPiece(position).pieceColor.equals(pieceAtStart.pieceColor)) {
                ChessMove move = new ChessMove(startPosition, position);
                moves.add(move);
            }
        }
        if(startPosition.getRow()-2 > 0 && startPosition.getColumn()-1 > 0){
            ChessPosition position = new ChessPosition(startPosition.getRow()-2, startPosition.getColumn()-1);
            if(board.getPiece(position) == null){
                ChessMove move = new ChessMove(startPosition, position);
                moves.add(move);
            }
            else if(!board.getPiece(position).pieceColor.equals(pieceAtStart.pieceColor)) {
                ChessMove move = new ChessMove(startPosition, position);
                moves.add(move);
            }
        }
        if(startPosition.getRow()-1 > 0 && startPosition.getColumn()-2 > 0){
            ChessPosition position = new ChessPosition(startPosition.getRow()-1, startPosition.getColumn()-2);
            if(board.getPiece(position) == null){
                ChessMove move = new ChessMove(startPosition, position);
                moves.add(move);
            }
            else if(!board.getPiece(position).pieceColor.equals(pieceAtStart.pieceColor)) {
                ChessMove move = new ChessMove(startPosition, position);
                moves.add(move);
            }
        }
        if(startPosition.getRow()+1 < 9 && startPosition.getColumn()-2 > 0){
            ChessPosition position = new ChessPosition(startPosition.getRow()+1, startPosition.getColumn()-2);
            if(board.getPiece(position) == null){
                ChessMove move = new ChessMove(startPosition, position);
                moves.add(move);
            }
            else if(!board.getPiece(position).pieceColor.equals(pieceAtStart.pieceColor)) {
                ChessMove move = new ChessMove(startPosition, position);
                moves.add(move);
            }
        }
        if(startPosition.getRow()+2 < 9 && startPosition.getColumn()-1 > 0){
            ChessPosition position = new ChessPosition(startPosition.getRow()+2, startPosition.getColumn()-1);
            if(board.getPiece(position) == null){
                ChessMove move = new ChessMove(startPosition, position);
                moves.add(move);
            }
            else if(!board.getPiece(position).pieceColor.equals(pieceAtStart.pieceColor)) {
                ChessMove move = new ChessMove(startPosition, position);
                moves.add(move);
            }
        }

        return moves;
    }

    public Collection<ChessMove> validQueenMoves(ChessBoard board, ChessPiece pieceAtStart, ChessPosition startPosition){
        Collection<ChessMove> moves = validBishopMoves(board,pieceAtStart,startPosition);
        Collection<ChessMove> rookMoves = validRookMoves(board,pieceAtStart,startPosition);
        moves.addAll(rookMoves);
        return moves;
    }

    public Collection<ChessMove> validKingMoves(ChessBoard board, ChessPiece pieceAtStart, ChessPosition startPosition){
        Collection<ChessMove> moves = new ArrayList<>();
        if(startPosition.getRow()+1 < 9 && startPosition.getColumn()+1 < 9){
            ChessPosition position = new ChessPosition(startPosition.getRow()+1, startPosition.getColumn()+1);
            if(board.getPiece(position) == null){
                ChessMove move = new ChessMove(startPosition, position);
                moves.add(move);
            }
            else if(!board.getPiece(position).pieceColor.equals(pieceAtStart.pieceColor)) {
                ChessMove move = new ChessMove(startPosition, position);
                moves.add(move);
            }
        }
        if(startPosition.getRow() < 9 && startPosition.getColumn()+1 < 9){
            ChessPosition position = new ChessPosition(startPosition.getRow(), startPosition.getColumn()+1);
            if(board.getPiece(position) == null){
                ChessMove move = new ChessMove(startPosition, position);
                moves.add(move);
            }
            else if(!board.getPiece(position).pieceColor.equals(pieceAtStart.pieceColor)) {
                ChessMove move = new ChessMove(startPosition, position);
                moves.add(move);
            }
        }
        if(startPosition.getRow()-1 > 0 && startPosition.getColumn()+1 < 9){
            ChessPosition position = new ChessPosition(startPosition.getRow()-1, startPosition.getColumn()+1);
            if(board.getPiece(position) == null){
                ChessMove move = new ChessMove(startPosition, position);
                moves.add(move);
            }
            else if(!board.getPiece(position).pieceColor.equals(pieceAtStart.pieceColor)) {
                ChessMove move = new ChessMove(startPosition, position);
                moves.add(move);
            }
        }
        if(startPosition.getRow()-1 > 0 && startPosition.getColumn() > 0){
            ChessPosition position = new ChessPosition(startPosition.getRow()-1, startPosition.getColumn());
            if(board.getPiece(position) == null){
                ChessMove move = new ChessMove(startPosition, position);
                moves.add(move);
            }
            else if(!board.getPiece(position).pieceColor.equals(pieceAtStart.pieceColor)) {
                ChessMove move = new ChessMove(startPosition, position);
                moves.add(move);
            }
        }
        if(startPosition.getRow()-1 > 0 && startPosition.getColumn()-1 > 0){
            ChessPosition position = new ChessPosition(startPosition.getRow()-1, startPosition.getColumn()-1);
            if(board.getPiece(position) == null){
                ChessMove move = new ChessMove(startPosition, position);
                moves.add(move);
            }
            else if(!board.getPiece(position).pieceColor.equals(pieceAtStart.pieceColor)) {
                ChessMove move = new ChessMove(startPosition, position);
                moves.add(move);
            }
        }
        if(startPosition.getRow() < 9 && startPosition.getColumn()-1 > 0){
            ChessPosition position = new ChessPosition(startPosition.getRow(), startPosition.getColumn()-1);
            if(board.getPiece(position) == null){
                ChessMove move = new ChessMove(startPosition, position);
                moves.add(move);
            }
            else if(!board.getPiece(position).pieceColor.equals(pieceAtStart.pieceColor)) {
                ChessMove move = new ChessMove(startPosition, position);
                moves.add(move);
            }
        }
        if(startPosition.getRow()+1 < 9 && startPosition.getColumn()-1 > 0){
            ChessPosition position = new ChessPosition(startPosition.getRow()+1, startPosition.getColumn()-1);
            if(board.getPiece(position) == null){
                ChessMove move = new ChessMove(startPosition, position);
                moves.add(move);
            }
            else if(!board.getPiece(position).pieceColor.equals(pieceAtStart.pieceColor)) {
                ChessMove move = new ChessMove(startPosition, position);
                moves.add(move);
            }
        }
        if(startPosition.getRow()+1 < 9 && startPosition.getColumn() > 0){
            ChessPosition position = new ChessPosition(startPosition.getRow()+1, startPosition.getColumn());
            if(board.getPiece(position) == null){
                ChessMove move = new ChessMove(startPosition, position);
                moves.add(move);
            }
            else if(!board.getPiece(position).pieceColor.equals(pieceAtStart.pieceColor)) {
                ChessMove move = new ChessMove(startPosition, position);
                moves.add(move);
            }
        }
        return moves;
    }

    public Collection<ChessMove> validBishopMoves(ChessBoard board, ChessPiece pieceAtStart, ChessPosition startPosition){
        Collection<ChessMove> moves = new ArrayList<>();
        int upLeftRow = startPosition.getRow()+1;
        int upLeftCol = startPosition.getColumn()-1;
        int upRightRow = startPosition.getRow()+1;
        int upRightCol = startPosition.getColumn()+1;
        int downLeftRow = startPosition.getRow()-1;
        int downLeftCol = startPosition.getColumn()-1;
        int downRightRow = startPosition.getRow()-1;
        int downRightCol = startPosition.getColumn()+1;

        while( (upLeftRow < 9) && (upLeftCol > 0)){
            ChessPosition position = new ChessPosition(upLeftRow, upLeftCol);
            if(board.getPiece(position) == null){
                ChessMove move = new ChessMove(startPosition, position);
                moves.add(move);
            }
            else if(!board.getPiece(position).pieceColor.equals(pieceAtStart.pieceColor)) {
                ChessMove move = new ChessMove(startPosition, position);
                moves.add(move);
                break;
            }
            else{
                break;
            }
            upLeftRow++;
            upLeftCol--;
        }
        while( (upRightRow < 9) && (upRightCol < 9)){
            ChessPosition position = new ChessPosition(upRightRow, upRightCol);
            if(board.getPiece(position) == null){
                ChessMove move = new ChessMove(startPosition, position);
                moves.add(move);
            }
            else if(!board.getPiece(position).pieceColor.equals(pieceAtStart.pieceColor)) {
                ChessMove move = new ChessMove(startPosition, position);
                moves.add(move);
                break;
            }
            else{
                break;
            }
            upRightRow++;
            upRightCol++;
        }
        while( (downLeftRow > 0) && (downLeftCol > 0)){
            ChessPosition position = new ChessPosition(downLeftRow, downLeftCol);
            if(board.getPiece(position) == null){
                ChessMove move = new ChessMove(startPosition, position);
                moves.add(move);
            }
            else if(!board.getPiece(position).pieceColor.equals(pieceAtStart.pieceColor)) {
                ChessMove move = new ChessMove(startPosition, position);
                moves.add(move);
                break;
            }
            else{
                break;
            }
            downLeftRow--;
            downLeftCol--;
        }
        while( (downRightRow > 0) && (downRightCol < 9)){
            ChessPosition position = new ChessPosition(downRightRow, downRightCol);
            if(board.getPiece(position) == null){
                ChessMove move = new ChessMove(startPosition, position);
                moves.add(move);
            }
            else if(!board.getPiece(position).pieceColor.equals(pieceAtStart.pieceColor)) {
                ChessMove move = new ChessMove(startPosition, position);
                moves.add(move);
                break;
            }
            else{
                break;
            }
            downRightRow--;
            downRightCol++;
        }
        return moves;
    }
}
