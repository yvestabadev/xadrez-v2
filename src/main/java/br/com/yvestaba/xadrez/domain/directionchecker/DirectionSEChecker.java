package br.com.yvestaba.xadrez.domain.directionchecker;

import br.com.yvestaba.xadrez.domain.Color;
import br.com.yvestaba.xadrez.domain.Direction;
import br.com.yvestaba.xadrez.domain.Position;
import br.com.yvestaba.xadrez.domain.generalrules.Board;
import br.com.yvestaba.xadrez.domain.generalrules.MoveDiagonal;
import br.com.yvestaba.xadrez.domain.pieces.Pawn;
import br.com.yvestaba.xadrez.domain.pieces.Piece;

import static java.util.Objects.nonNull;

public class DirectionSEChecker extends DirectionChecker{

    public DirectionSEChecker(DirectionChecker next) {
        super(next);
    }

    @Override
    protected Direction getDirection(int col, int lin, Board board) {
        int i = col + 1;
        int j = lin - 1;
        while(i < 8 && j >= 0){
            Piece piece = board.getPiece(new Position(i, j));
            if(nonNull(piece) && piece.getColor() != board.getTurnOwner() &&
                    (piece instanceof MoveDiagonal ||
                    (piece.getColor() == Color.WHITE && piece instanceof Pawn))){
                this.threatPosition = new Position(i, j);
                return Direction.SW;
            }
            i++;
            j--;
        }
        return null;
    }
}
