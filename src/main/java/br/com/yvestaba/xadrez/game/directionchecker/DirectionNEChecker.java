package br.com.yvestaba.xadrez.game.directionchecker;

import br.com.yvestaba.xadrez.game.Color;
import br.com.yvestaba.xadrez.game.Direction;
import br.com.yvestaba.xadrez.game.Position;
import br.com.yvestaba.xadrez.game.generalrules.Board;
import br.com.yvestaba.xadrez.game.generalrules.MoveDiagonal;
import br.com.yvestaba.xadrez.game.pieces.Pawn;
import br.com.yvestaba.xadrez.game.pieces.Piece;

import static java.util.Objects.nonNull;

public class DirectionNEChecker extends DirectionChecker{

    public DirectionNEChecker(DirectionChecker next) {
        super(next);
    }

    @Override
    protected Direction getDirection(int col, int lin, Board board) {
        int i = col + 1;
        int j = lin + 1;
        if(i > 7 || j > 7){
            return null;
        }
        Piece checkPawn = board.getPiece(new Position(i, j));
        if(nonNull(checkPawn) && checkPawn.getColor() == Color.BLACK && board.getTurnOwner() == Color.WHITE){
            this.threatPosition = new Position(i, j);
            return Direction.NE;
        }
        while(i < 8 && j < 8){
            Piece piece = board.getPiece(new Position(i, j));
            if(nonNull(piece) && piece.getColor() != board.getTurnOwner() &&
                    piece instanceof MoveDiagonal){
                this.threatPosition = new Position(i, j);
                return Direction.NE;
            }
            i++;
            j++;
        }
        return null;
    }
}
