package br.com.yvestaba.xadrez.domain.generalrules;

import br.com.yvestaba.xadrez.domain.Position;

public interface MoveChecker {
    void movePiece(Position from, Position to, Board b);
}
