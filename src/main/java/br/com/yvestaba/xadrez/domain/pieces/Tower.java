package br.com.yvestaba.xadrez.domain.pieces;

import br.com.yvestaba.xadrez.domain.generalrules.Board;
import br.com.yvestaba.xadrez.domain.Color;
import br.com.yvestaba.xadrez.domain.Position;
import br.com.yvestaba.xadrez.domain.generalrules.MoveCross;

import java.util.Set;

public class Tower extends Piece implements MoveCross{

    public Tower(Color color){
        super(color);
    }
    @Override
    protected Set<Position> getPositions(Board board, Position position) {
        return getMovementsCross(board, position);
    }

    @Override
    public Set<Position> threat(Board board, Position position) {
        return getThreatenedPositionsCross(board, position);
    }
}
