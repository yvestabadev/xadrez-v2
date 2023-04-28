package br.com.yvestaba.xadrez.game.generalrules;

import br.com.yvestaba.xadrez.game.Position;

import java.util.Set;

public interface MoveValidator {
    Set<Position> getValidPlaces(Position from, Board board, Set<Position> valid);
}
