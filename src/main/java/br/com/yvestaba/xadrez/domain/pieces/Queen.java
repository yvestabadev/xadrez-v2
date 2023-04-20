package br.com.yvestaba.xadrez.domain.pieces;

import br.com.yvestaba.xadrez.domain.generalrules.Board;
import br.com.yvestaba.xadrez.domain.Color;
import br.com.yvestaba.xadrez.domain.Position;
import br.com.yvestaba.xadrez.domain.generalrules.MoveCross;
import br.com.yvestaba.xadrez.domain.generalrules.MoveDiagonal;

import java.util.Set;

public class Queen extends Piece implements MoveDiagonal, MoveCross{

    public Queen(Color color){
        super(color);
    }
    @Override
    protected Set<Position> getPositions(Board board, Position position) {
        var ret = getMovements(board, position);
        ret.addAll(getMovementsCross(board, position));
        return ret;
    }

    @Override
    public Set<Position> threat(Board board, Position position) {
        var ret = getThreatenedPositions(board, position);
        ret.addAll(getThreatenedPositionsCross(board, position));
        return ret;
    }
}
