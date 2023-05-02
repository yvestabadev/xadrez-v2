package br.com.yvestaba.xadrez.game.generalrules;

import br.com.yvestaba.xadrez.game.GameStatus;
import br.com.yvestaba.xadrez.game.Position;
import br.com.yvestaba.xadrez.game.directionchecker.*;

import java.util.Set;

class KingMoveValidator {

    static Set<Position> validate(Position kingPosition, Set<Position> valid, ThreatChecker threatChecker, Board board){
        valid.removeAll(threatChecker.getThreatenArea());
        if(board.getStatus() == GameStatus.CHECK){
            DirectionChecker checker = getThreatDirection(kingPosition, board);
        }
        return valid;
    }

    private static DirectionChecker getThreatDirection(Position positionKing, Board board) {
        var checker = new DirectionLChecker(
                new DirectionEChecker(new DirectionSChecker(new DirectionNChecker(
                        new DirectionWChecker(new DirectionSEChecker(
                                new DirectionSWChecker(new DirectionNEChecker(new DirectionNWChecker(null)))))))));
        return checker.setDirectionAndPosition(positionKing.getCol(), positionKing.getLin(), board);
    }
}
