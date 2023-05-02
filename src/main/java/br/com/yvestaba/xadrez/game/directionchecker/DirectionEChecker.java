package br.com.yvestaba.xadrez.game.directionchecker;

import br.com.yvestaba.xadrez.game.Direction;
import br.com.yvestaba.xadrez.game.Position;
import br.com.yvestaba.xadrez.game.generalrules.Board;
import br.com.yvestaba.xadrez.game.generalrules.MoveCross;
import br.com.yvestaba.xadrez.game.pieces.Piece;

import static java.util.Objects.nonNull;

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
            } else if (nonNull(piece)) {
                break;
            }
        }
        return null;
    }
}
