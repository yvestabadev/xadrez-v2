package br.com.yvestaba.xadrez.domain.pieces;

import br.com.yvestaba.xadrez.domain.Board;
import br.com.yvestaba.xadrez.domain.Color;
import br.com.yvestaba.xadrez.domain.Position;

import java.util.Map;
import java.util.Set;

public abstract class Piece {
    private Color color;

    public Piece(Color color){
        this.color = color;
    }

    public final Set<Position> getValidPlaces(Board board, Position position){
        if(board.getTurnOwner() != color){
            throw new RuntimeException("It's other's turn");
        }
        return getPositions(board, position);
    }

    protected abstract Set<Position> getPositions(Board board, Position position);

    public Color getColor() {
        return color;
    }
}
