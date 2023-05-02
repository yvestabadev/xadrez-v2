package br.com.yvestaba.xadrez.utils;

import br.com.yvestaba.xadrez.game.generalrules.Board;
import br.com.yvestaba.xadrez.game.Position;
import br.com.yvestaba.xadrez.game.pieces.Piece;

import java.util.Set;

import static java.util.Objects.isNull;

public class ChessCommonUtils {

    private ChessCommonUtils(){

    }

    /**
     * Add to set valid positions that the turn owner can get to
     * @param positions
     * @param board
     * @param position
     * @return true if no piece was found at this position
     */
    public static boolean addPositionIfvalid(Set<Position> positions, Board board, Position position){
        Piece piece = board.getPiece(position);
        if(isNull(piece) || piece.getColor() != board.getTurnOwner()){
            positions.add(position);
        }
        return isNull(piece);
    }

    /**
     *
     * @param positions
     * @param board
     * @param col
     * @param lin
     * @return true if no piece was found at this position
     */
    public static boolean addThreatAndGetPiece(Set<Position> positions, Board board, int col, int lin) {
        Piece piece;
        Position pos = new Position(col, lin);
        piece = board.getPiece(pos);
        positions.add(pos);
        return isNull(piece);
    }

    public static boolean validateColLin(int col, int lin){
        return col >= 0 && col < 8 && lin >= 0 && lin < 8;
    }
}
