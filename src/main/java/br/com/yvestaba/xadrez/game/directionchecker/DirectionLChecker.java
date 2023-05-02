package br.com.yvestaba.xadrez.game.directionchecker;

import br.com.yvestaba.xadrez.game.Direction;
import br.com.yvestaba.xadrez.game.Position;
import br.com.yvestaba.xadrez.game.generalrules.Board;
import br.com.yvestaba.xadrez.game.pieces.Horse;
import br.com.yvestaba.xadrez.game.pieces.Piece;
import br.com.yvestaba.xadrez.utils.ChessCommonUtils;

import java.util.ArrayList;

public class DirectionLChecker extends DirectionChecker{

    public DirectionLChecker(DirectionChecker next) {
        super(next);
    }

    @Override
    protected Direction getDirection(int col, int lin, Board board) {
        var positions = new ArrayList<Position>();
        if(ChessCommonUtils.validateColLin(col + 2, lin + 1)){
            positions.add(new Position(col + 2, lin + 1));
        }
        if(ChessCommonUtils.validateColLin(col + 2, lin - 1)){
            positions.add(new Position(col + 2, lin - 1));
        }
        if(ChessCommonUtils.validateColLin(col - 2, lin + 1)){
            positions.add(new Position(col - 2, lin + 1));
        }
        if(ChessCommonUtils.validateColLin(col - 2, lin - 1)){
            positions.add(new Position(col - 2, lin - 1));
        }
        if(ChessCommonUtils.validateColLin(col + 1, lin + 2)){
            positions.add(new Position(col + 1, lin + 2));
        }
        if(ChessCommonUtils.validateColLin(col + 1, lin - 2)){
            positions.add(new Position(col + 1, lin - 2));
        }
        if(ChessCommonUtils.validateColLin(col - 1, lin + 2)){
            positions.add(new Position(col - 1, lin + 2));
        }
        if(ChessCommonUtils.validateColLin(col - 1, lin - 2)){
            positions.add(new Position(col - 1, lin - 2));
        }
        for(var position : positions){
            Piece piece = board.getPiece(position);
            if(piece instanceof Horse && piece.getColor().isEnemy(board.getTurnOwner())){
                this.threatPosition = new Position(position.getCol(), position.getLin());
                return Direction.L;
            }
        }
        return null;
    }
}
