package br.com.yvestaba.xadrez.domain.directionchecker;

import br.com.yvestaba.xadrez.domain.Direction;
import br.com.yvestaba.xadrez.domain.Position;
import br.com.yvestaba.xadrez.domain.generalrules.Board;
import br.com.yvestaba.xadrez.domain.generalrules.MoveCross;
import br.com.yvestaba.xadrez.domain.pieces.Piece;

import static java.util.Objects.nonNull;

public class DirectionWChecker extends DirectionChecker{

    public DirectionWChecker(DirectionChecker next) {
        super(next);
    }

    @Override
    protected Direction getDirection(int col, int lin, Board board) {
        for(int i = col - 1; i >= 0; i--){
            Piece piece = board.getPiece(new Position(i, lin));
            if(nonNull(piece) && piece instanceof MoveCross &&
                    piece.getColor() != board.getTurnOwner()){
                this.threatPosition = new Position(i, lin);
                return Direction.W;
            }
        }
        return null;
    }
}
