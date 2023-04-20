package br.com.yvestaba.xadrez.domain.directionchecker;

import br.com.yvestaba.xadrez.domain.Color;
import br.com.yvestaba.xadrez.domain.Direction;
import br.com.yvestaba.xadrez.domain.Position;
import br.com.yvestaba.xadrez.domain.generalrules.Board;
import br.com.yvestaba.xadrez.domain.generalrules.MoveDiagonal;
import br.com.yvestaba.xadrez.domain.pieces.Horse;
import br.com.yvestaba.xadrez.domain.pieces.Pawn;
import br.com.yvestaba.xadrez.domain.pieces.Piece;
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
            if(board.getPiece(position) instanceof Horse){
                this.threatPosition = new Position(position.getCol(), position.getLin());
                return Direction.L;
            }
        }
        return null;
    }
}
