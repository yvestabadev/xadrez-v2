package br.com.yvestaba.xadrez.game.generalrules;

import br.com.yvestaba.xadrez.game.Position;

public interface MoveChecker {
    void movePiece(Position from, Position to, Board b);
}
