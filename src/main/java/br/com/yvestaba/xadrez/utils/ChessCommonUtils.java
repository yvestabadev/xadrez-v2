package br.com.yvestaba.xadrez.utils;

import br.com.yvestaba.xadrez.domain.Board;
import br.com.yvestaba.xadrez.domain.Color;
import br.com.yvestaba.xadrez.domain.Position;
import br.com.yvestaba.xadrez.domain.pieces.Piece;

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
}
