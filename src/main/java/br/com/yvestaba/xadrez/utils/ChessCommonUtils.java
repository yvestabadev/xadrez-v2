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
     *   @returns piece if any
     *   If space has no piece, add as possible move
     */
    public static Piece addIfDoesNotExistOnBoard(Set<Position> positions, Board board, Position position){
        Piece piece = board.getPiece(position);
        if(isNull(piece)){
            positions.add(position);
        }
        return piece;
    }

    public static Piece addIfDoesNotExistOnBoardOrCapturable(Set<Position> positions, Board board, Position position){
        Piece piece = board.getPiece(position);
        if(isNull(piece) || piece.getColor() != board.getTurnOwner()){
            positions.add(position);
        }
        return piece;
    }

    public static Piece addThreatAndGetPiece(Set<Position> positions, Board board, int col, int lin) {
        Piece piece;
        Position pos = new Position(col, lin);
        piece = board.getPiece(pos);
        positions.add(pos);
        return piece;
    }

    public static boolean validateColLin(int col, int lin){
        return col >= 0 && col < 8 && lin >= 0 && lin < 8;
    }
}
