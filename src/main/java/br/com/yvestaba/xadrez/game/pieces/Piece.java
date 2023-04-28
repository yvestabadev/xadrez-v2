package br.com.yvestaba.xadrez.game.pieces;

import br.com.yvestaba.xadrez.game.generalrules.Board;
import br.com.yvestaba.xadrez.game.Color;
import br.com.yvestaba.xadrez.game.Position;

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

    public abstract Set<Position> threat(Board board, Position position);

    public Color getColor() {
        return color;
    }
}
