package br.com.yvestaba.xadrez.domain.directionchecker;

import br.com.yvestaba.xadrez.domain.Direction;
import br.com.yvestaba.xadrez.domain.Position;
import br.com.yvestaba.xadrez.domain.generalrules.Board;
import br.com.yvestaba.xadrez.domain.generalrules.MoveCross;
import br.com.yvestaba.xadrez.domain.pieces.Piece;

public class DirectionEChecker extends DirectionChecker{

    public DirectionEChecker(DirectionChecker next) {
        super(next);
    }

    @Override
    protected Direction getDirection(int col, int lin, Board board) {
        for(int i = col + 1; i < 8; i++){
            Piece piece = board.getPiece(new Position(i, lin));
            if(piece instanceof MoveCross &&
                piece.getColor() != board.getTurnOwner()){
                this.threatPosition = new Position(i, lin);
                return Direction.E;
            }
        }
        return null;
    }
}
