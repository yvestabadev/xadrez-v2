package br.com.yvestaba.xadrez.game.pieces;

import br.com.yvestaba.xadrez.game.generalrules.Board;
import br.com.yvestaba.xadrez.game.Color;
import br.com.yvestaba.xadrez.game.Position;
import br.com.yvestaba.xadrez.game.generalrules.MoveCross;

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
