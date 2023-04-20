package br.com.yvestaba.xadrez.domain.generalrules;

import br.com.yvestaba.xadrez.domain.Position;

import java.util.Set;

public interface MoveValidator {
    Set<Position> getValidPlaces(Position from, Board board, Set<Position> valid);
}
