package br.com.yvestaba.xadrez.game.generalrules;

import br.com.yvestaba.xadrez.game.Color;
import br.com.yvestaba.xadrez.game.GameStatus;

import static br.com.yvestaba.xadrez.game.Color.WHITE;

class CheckMateChecker {

    static void check(ThreatChecker threatChecker, KingPosition kingPosition, Board board, PiecesMover mover){
        Color turnOwner = board.getTurnOwner();
        //when this method is triggered, the turn owner already changed
        var positionKing = kingPosition.getPositionByColor(turnOwner);
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
