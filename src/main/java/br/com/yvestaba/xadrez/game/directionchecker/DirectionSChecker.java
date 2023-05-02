package br.com.yvestaba.xadrez.game.directionchecker;

import br.com.yvestaba.xadrez.game.Direction;
import br.com.yvestaba.xadrez.game.Position;
import br.com.yvestaba.xadrez.game.generalrules.Board;
import br.com.yvestaba.xadrez.game.generalrules.MoveCross;
import br.com.yvestaba.xadrez.game.pieces.Piece;

import static java.util.Objects.nonNull;

public class DirectionSChecker extends DirectionChecker{

    public DirectionSChecker(DirectionChecker next) {
        super(next);
    }

    @Override
    protected Direction getDirection(int col, int lin, Board board) {
        for(int i = lin - 1; i >= 0; i--){
            Piece piece = board.getPiece(new Position(col, i));
            if(nonNull(piece) && piece instanceof MoveCross &&
                    piece.getColor().isEnemy(board.getTurnOwner())){
                this.threatPosition = new Position(col, i);
                return Direction.S;
            } else if(nonNull(piece)){
                break;
            }
        }
        return null;
    }
}
