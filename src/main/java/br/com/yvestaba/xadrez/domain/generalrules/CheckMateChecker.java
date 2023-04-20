package br.com.yvestaba.xadrez.domain.generalrules;

import br.com.yvestaba.xadrez.domain.Color;
import br.com.yvestaba.xadrez.domain.GameStatus;

import static br.com.yvestaba.xadrez.domain.Color.WHITE;

class CheckMateChecker {

    static GameStatus check(ThreatChecker threatChecker, KingPosition kingPosition, Board board, PiecesMover mover){
        Color turnOwner = board.getTurnOwner();
        //when this method is triggered, the turn owner already changed
        var positionKing = turnOwner == WHITE ? kingPosition.getWhitePosition() : kingPosition.getBlackPosition();
        if(!threatChecker.getThreatenArea().contains(positionKing)){
            return checkDraw(board, turnOwner, mover);
        }
        for(var position : board.getPiecesByColor(turnOwner).keySet()){
            //TODO forbid pieces not to defend the king
            if(!mover.validMoves(position).isEmpty()){
                return GameStatus.CHECK;
            }
        }
        return GameStatus.CHECKMATE;
    }

    private static GameStatus checkDraw(Board board, Color turnOwner, PiecesMover mover) {
        for(var position : board.getPiecesByColor(turnOwner).keySet()){
            if(!mover.validMoves(position).isEmpty()){
                return GameStatus.NONE;
            }
        }
        return GameStatus.DRAW;
    }

}
