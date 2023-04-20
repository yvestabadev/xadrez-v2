package br.com.yvestaba.xadrez.domain.pieces;

import br.com.yvestaba.xadrez.domain.generalrules.Board;
import br.com.yvestaba.xadrez.domain.Color;
import br.com.yvestaba.xadrez.domain.Position;
import br.com.yvestaba.xadrez.domain.generalrules.MoveDiagonal;

import java.util.Set;

public class Bishop extends Piece implements MoveDiagonal{

    public Bishop(Color color){
        super(color);
    }
    @Override
    protected Set<Position> getPositions(Board board, Position position) {
        return getMovements(board, position);
    }

    @Override
    public Set<Position> threat(Board board, Position position) {
        return getThreatenedPositions(board, position);
    }
}
