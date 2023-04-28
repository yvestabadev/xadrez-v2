package br.com.yvestaba.xadrez.game.pieces;

import br.com.yvestaba.xadrez.game.generalrules.Board;
import br.com.yvestaba.xadrez.game.Color;
import br.com.yvestaba.xadrez.game.Position;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static br.com.yvestaba.xadrez.utils.ChessCommonUtils.addIfDoesNotExistOnBoardOrCapturable;

public class King extends Piece{

    public King(Color color){
        super(color);
    }
    @Override
    protected Set<Position> getPositions(Board board, Position position) {
        var ret = new HashSet<Position>();
        Integer col = position.getCol();
        Integer lin = position.getLin();
        Integer colPlusOne = col + 1;
        Integer colMinusOne = col - 1;
        Integer linPlusOne = lin + 1;
        Integer linMinusOne = lin - 1;

        var columns = new ArrayList<Integer>();
        if(colPlusOne < 8){
            columns.add(colPlusOne);
            addIfDoesNotExistOnBoardOrCapturable(ret, board, new Position(colPlusOne, lin));
        }
        if(colMinusOne >= 0){
            columns.add(colMinusOne);
            addIfDoesNotExistOnBoardOrCapturable(ret, board, new Position(colMinusOne, lin));
        }

        var lines = new ArrayList<Integer>();
        if(linPlusOne < 8){
            lines.add(linPlusOne);
            addIfDoesNotExistOnBoardOrCapturable(ret, board, new Position(col, linPlusOne));
        }
        if(linMinusOne >= 0){
            lines.add(linMinusOne);
            addIfDoesNotExistOnBoardOrCapturable(ret, board, new Position(col, linMinusOne));
        }
        for(var column : columns){
            for(var line : lines){
                addIfDoesNotExistOnBoardOrCapturable(ret, board, new Position(column, line));
            }
        }
        return ret;
    }

    @Override
    public Set<Position> threat(Board board, Position position) {
        var ret = new HashSet<Position>();
        Integer col = position.getCol();
        Integer lin = position.getLin();
        Integer colPlusOne = col + 1;
        Integer colMinusOne = col - 1;
        Integer linPlusOne = lin + 1;
        Integer linMinusOne = lin - 1;

        var columns = new ArrayList<Integer>();
        if(colPlusOne < 8){
            columns.add(colPlusOne);
            ret.add(new Position(colPlusOne, lin));
        }
        if(colMinusOne >= 0){
            columns.add(colMinusOne);
            ret.add(new Position(colMinusOne, lin));
        }

        var lines = new ArrayList<Integer>();
        if(linPlusOne < 8){
            lines.add(linPlusOne);
            ret.add(new Position(col, linPlusOne));
        }
        if(linMinusOne >= 0){
            lines.add(linMinusOne);
            ret.add(new Position(col, linMinusOne));
        }
        for(var column : columns){
            for(var line : lines){
                ret.add(new Position(column, line));
            }
        }
        return ret;
    }
}
