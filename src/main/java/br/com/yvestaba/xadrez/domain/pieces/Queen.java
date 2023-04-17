package br.com.yvestaba.xadrez.domain.pieces;

import br.com.yvestaba.xadrez.domain.Board;
import br.com.yvestaba.xadrez.domain.Color;
import br.com.yvestaba.xadrez.domain.Position;
import br.com.yvestaba.xadrez.utils.MoveCross;
import br.com.yvestaba.xadrez.utils.MoveDiagonal;

import java.util.Set;

public class Queen extends Piece{

    public Queen(Color color){
        super(color);
    }
    @Override
    protected Set<Position> getPositions(Board board, Position position) {
        var ret = MoveDiagonal.getMovements(board, position);
        ret.addAll(MoveCross.getMovements(board, position));
        return ret;
    }
}
