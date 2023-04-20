package br.com.yvestaba.xadrez.domain.generalrules;

import br.com.yvestaba.xadrez.domain.Color;
import br.com.yvestaba.xadrez.domain.Direction;
import br.com.yvestaba.xadrez.domain.GameStatus;
import br.com.yvestaba.xadrez.domain.Position;

import java.util.Set;

import static br.com.yvestaba.xadrez.domain.Color.WHITE;

class CheckMateChecker {

    static void check(ThreatChecker threatChecker, KingPosition kingPosition, Board board, PiecesMover mover){
        Color turnOwner = board.getTurnOwner();
        //when this method is triggered, the turn owner already changed
        var positionKing = turnOwner == WHITE ? kingPosition.getWhitePosition() : kingPosition.getBlackPosition();
        if(!threatChecker.getThreatenArea().contains(positionKing)){
            checkDraw(board, turnOwner, mover);
            return;
        }
        for(var position : board.getPiecesByColor(turnOwner).keySet()){
            if(!mover.validMoves(position).isEmpty()){
                board.setStatus(GameStatus.CHECK);
                return;
            }
        }
        board.setStatus(GameStatus.CHECKMATE);
    }
    private static void checkDraw(Board board, Color turnOwner, PiecesMover mover) {
        for(var position : board.getPiecesByColor(turnOwner).keySet()){
            if(!mover.validMoves(position).isEmpty()){
                board.setStatus(GameStatus.NONE);
                return;
            }
        }
        board.setStatus(GameStatus.DRAW);
    }

}
