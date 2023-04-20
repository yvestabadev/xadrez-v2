package br.com.yvestaba.xadrez.domain.pieces;

import br.com.yvestaba.xadrez.domain.generalrules.Board;
import br.com.yvestaba.xadrez.domain.Color;
import br.com.yvestaba.xadrez.domain.Position;

import java.util.*;

import static br.com.yvestaba.xadrez.utils.ChessCommonUtils.*;

public class Horse extends Piece{

    public Horse(Color color){
        super(color);
    }
    @Override
    protected Set<Position> getPositions(Board board, Position position) {
        var ret = new HashSet<Position>();
        int col = position.getCol();
        int lin = position.getLin();

        var positions = new ArrayList<Position>();
        ignoreInvalidPositionsAndAdd(positions,col + 2, lin + 1);
        ignoreInvalidPositionsAndAdd(positions,col + 2, lin - 1);
        ignoreInvalidPositionsAndAdd(positions,col - 2, lin + 1);
        ignoreInvalidPositionsAndAdd(positions,col - 2, lin - 1);
        ignoreInvalidPositionsAndAdd(positions,col + 1, lin + 2);
        ignoreInvalidPositionsAndAdd(positions,col + 1, lin - 2);
        ignoreInvalidPositionsAndAdd(positions,col - 1, lin + 2);
        ignoreInvalidPositionsAndAdd(positions,col - 1, lin - 2);

        for(var pos : positions){
            addIfDoesNotExistOnBoardOrCapturable(ret, board, pos);
        }
        return ret;
    }

    @Override
    public Set<Position> threat(Board board, Position position) {
        var ret = new HashSet<Position>();
        int col = position.getCol();
        int lin = position.getLin();

        var positions = new ArrayList<Position>();
        ignoreInvalidPositionsAndAdd(positions,col + 2, lin + 1);
        ignoreInvalidPositionsAndAdd(positions,col + 2, lin - 1);
        ignoreInvalidPositionsAndAdd(positions,col - 2, lin + 1);
        ignoreInvalidPositionsAndAdd(positions,col - 2, lin - 1);
        ignoreInvalidPositionsAndAdd(positions,col + 1, lin + 2);
        ignoreInvalidPositionsAndAdd(positions,col + 1, lin - 2);
        ignoreInvalidPositionsAndAdd(positions,col - 1, lin + 2);
        ignoreInvalidPositionsAndAdd(positions,col - 1, lin - 2);

        for(var pos : positions){
            addThreatAndGetPiece(ret, board, pos.getCol(), pos.getLin());
        }
        return ret;
    }

    private void ignoreInvalidPositionsAndAdd(List<Position> list, int col, int lin){
        if(validateColLin(col, lin)){
            list.add(new Position(col, lin));
        }
    }
}
