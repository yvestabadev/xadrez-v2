package br.com.yvestaba.xadrez.domain.generalrules;

import br.com.yvestaba.xadrez.domain.Color;
import br.com.yvestaba.xadrez.domain.Position;
import br.com.yvestaba.xadrez.domain.pieces.King;
import br.com.yvestaba.xadrez.domain.pieces.Pawn;

class RockTowerMover implements MoveChecker{

    public void movePiece(Position from, Position to, Board board) {
        if(!(board.getPiece(from) instanceof King)){
            return;
        }
        if(from.getCol() - to.getCol() != 2 &&
            to.getCol() - from.getCol() != 2){
            return;
        }
        board.moveTowerRock(getTowerPosition(to), getTowerDestiny(to));
    }

    private Position getTowerPosition(Position to) {
        int col = to.getCol() == 2 ? 0 : 7;
        int lin = to.getLin();
        return new Position(col, lin);
    }

    private Position getTowerDestiny(Position to) {
        int col = to.getCol() == 2 ? 3 : 5;
        int lin = to.getLin();
        return new Position(col, lin);
    }


}
