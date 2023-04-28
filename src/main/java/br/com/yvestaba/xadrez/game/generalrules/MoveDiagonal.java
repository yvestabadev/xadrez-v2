package br.com.yvestaba.xadrez.game.generalrules;

import br.com.yvestaba.xadrez.game.Position;
import br.com.yvestaba.xadrez.game.pieces.Piece;

import java.util.HashSet;
import java.util.Set;

import static br.com.yvestaba.xadrez.utils.ChessCommonUtils.addIfDoesNotExistOnBoardOrCapturable;
import static br.com.yvestaba.xadrez.utils.ChessCommonUtils.addThreatAndGetPiece;
import static java.util.Objects.isNull;

public interface MoveDiagonal {

    default Set<Position> getMovements(Board board, Position position){
        var ret = new HashSet<Position>();
        fulfillNE(ret, board, position);
        fulfillNW(ret, board, position);
        fulfillSE(ret, board, position);
        fulfillSW(ret, board, position);
        return ret;
    }

    default Set<Position> getThreatenedPositions(Board board, Position position){
        var ret = new HashSet<Position>();
        fulfillThreatNE(ret, board, position);
        fulfillThreatNW(ret, board, position);
        fulfillThreatSE(ret, board, position);
        fulfillThreatSW(ret, board, position);
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

    private static void fulfillThreatNE(Set<Position> positions, Board board, Position position){
        int col = position.getCol() + 1;
        int lin = position.getLin() + 1;

        Piece piece = null;
        while(col < 8 && lin < 8 && isNull(piece)){
            piece = addThreatAndGetPiece(positions, board, col, lin);
            col++;
            lin++;
        }
    }

    private static void fulfillThreatNW(Set<Position> positions, Board board, Position position){
        int col = position.getCol() - 1;
        int lin = position.getLin() + 1;
        Piece piece = null;
        while(col >= 0 && lin < 8 && isNull(piece)){
            piece = addThreatAndGetPiece(positions, board, col, lin);
            col--;
            lin++;
        }
    }

    private static void fulfillThreatSE(Set<Position> positions, Board board, Position position){
        int col = position.getCol() + 1;
        int lin = position.getLin() - 1;
        Piece piece = null;
        while(col < 8 && lin >= 0 && isNull(piece)){
            piece = addThreatAndGetPiece(positions, board, col, lin);
            col++;
            lin--;
        }
    }

    private static void fulfillThreatSW(Set<Position> positions, Board board, Position position){
        int col = position.getCol() - 1;
        int lin = position.getLin() - 1;
        Piece piece = null;
        while(col >= 0 && lin >= 0 && isNull(piece)){
            piece = addThreatAndGetPiece(positions, board, col, lin);
            col--;
            lin--;
        }
    }
}
