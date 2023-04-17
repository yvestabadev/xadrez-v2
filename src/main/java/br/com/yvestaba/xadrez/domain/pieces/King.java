package br.com.yvestaba.xadrez.domain.pieces;

import br.com.yvestaba.xadrez.domain.Board;
import br.com.yvestaba.xadrez.domain.Color;
import br.com.yvestaba.xadrez.domain.Position;

import java.util.Set;

public class King extends Piece{

    public King(Color color){
        super(color);
    }
    @Override
    protected Set<Position> getPositions(Board board, Position position) {
        return null;
    }
}
