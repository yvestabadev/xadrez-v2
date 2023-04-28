package br.com.yvestaba.xadrez.game.pieces;

import br.com.yvestaba.xadrez.game.generalrules.Board;
import br.com.yvestaba.xadrez.game.Color;
import br.com.yvestaba.xadrez.game.Position;
import br.com.yvestaba.xadrez.game.generalrules.MoveCross;
import br.com.yvestaba.xadrez.game.generalrules.MoveDiagonal;

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
