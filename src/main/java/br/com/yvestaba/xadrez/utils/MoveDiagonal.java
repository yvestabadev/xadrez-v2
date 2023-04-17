package br.com.yvestaba.xadrez.utils;

import br.com.yvestaba.xadrez.domain.Board;
import br.com.yvestaba.xadrez.domain.Position;
import br.com.yvestaba.xadrez.domain.pieces.Piece;

import java.util.HashSet;
import java.util.Set;

import static br.com.yvestaba.xadrez.utils.ChessCommonUtils.addIfDoesNotExistOnBoard;
import static br.com.yvestaba.xadrez.utils.ChessCommonUtils.addIfDoesNotExistOnBoardOrCapturable;
import static java.util.Objects.isNull;

public class MoveDiagonal {

    private MoveDiagonal(){

    }

    public static Set<Position> getMovements(Board board, Position position){
        var ret = new HashSet<Position>();
        fulfillNE(ret, board, position);
        fulfillNW(ret, board, position);
        fulfillSE(ret, board, position);
        fulfillSW(ret, board, position);
        return ret;
    }

    private static void fulfillNE(Set<Position> positions, Board board, Position position){
        int col = position.getCol() + 1;
        int lin = position.getLin() + 1;

        Piece piece = null;
        while(col < 8 && lin < 8 && isNull(piece)){
            piece = addIfDoesNotExistOnBoardOrCapturable(positions, board, new Position(col, lin));
            col++;
            lin++;
        }
    }

    private static void fulfillNW(Set<Position> positions, Board board, Position position){
        int col = position.getCol() - 1;
        int lin = position.getLin() + 1;
        Piece piece = null;
        while(col >= 0 && lin < 8 && isNull(piece)){
            piece = addIfDoesNotExistOnBoardOrCapturable(positions, board, new Position(col, lin));
            col--;
            lin++;
        }
    }

    private static void fulfillSE(Set<Position> positions, Board board, Position position){
        int col = position.getCol() + 1;
        int lin = position.getLin() - 1;
        Piece piece = null;
        while(col < 8 && lin >= 0 && isNull(piece)){
            piece = addIfDoesNotExistOnBoardOrCapturable(positions, board, new Position(col, lin));
            col++;
            lin--;
        }
    }

    private static void fulfillSW(Set<Position> positions, Board board, Position position){
        int col = position.getCol() - 1;
        int lin = position.getLin() - 1;
        Piece piece = null;
        while(col >= 0 && lin >= 0 && isNull(piece)){
            piece = addIfDoesNotExistOnBoardOrCapturable(positions, board, new Position(col, lin));
            col--;
            lin--;
        }
    }
}
