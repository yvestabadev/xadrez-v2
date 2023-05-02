package br.com.yvestaba.xadrez.game.generalrules;

import br.com.yvestaba.xadrez.game.Direction;
import br.com.yvestaba.xadrez.game.GameStatus;
import br.com.yvestaba.xadrez.game.Position;
import br.com.yvestaba.xadrez.game.directionchecker.*;
import br.com.yvestaba.xadrez.utils.ChessCommonUtils;

import java.util.List;
import java.util.Set;

class KingMoveValidator {

    static Set<Position> validate(Position kingPosition, Set<Position> valid, ThreatChecker threatChecker, Board board){
        valid.removeAll(threatChecker.getThreatenArea());
        if(threatChecker.getThreatenArea().contains(kingPosition)){
            DirectionChecker checker = getThreatDirection(kingPosition, board);
            invalidateByDirections(valid, checker.getDirections(), kingPosition);
        }
        return valid;
    }

    private static void invalidateByDirections(Set<Position> valid, List<Direction> directions, Position kingPosition) {
        for(var direction : directions){
            switch(direction){
                case N:
                    validateAndRemove(valid, kingPosition.getCol(), kingPosition.getLin() - 1);
                    break;
                case S:
                    validateAndRemove(valid, kingPosition.getCol(), kingPosition.getLin() + 1);
                    break;
                case E:
                    validateAndRemove(valid, kingPosition.getCol() - 1, kingPosition.getLin());
                    break;
                case W:
                    validateAndRemove(valid, kingPosition.getCol() + 1, kingPosition.getLin());
                    break;
                case NE:
                    validateAndRemove(valid, kingPosition.getCol() - 1, kingPosition.getLin() - 1);
                    break;
                case NW:
                    validateAndRemove(valid, kingPosition.getCol() + 1, kingPosition.getLin() - 1);
                    break;
                case SE:
                    validateAndRemove(valid, kingPosition.getCol() + 1, kingPosition.getLin() + 1);
                    break;
                case SW:
                    validateAndRemove(valid, kingPosition.getCol() - 1, kingPosition.getLin() + 1);
                    break;
            }
        }
    }

    private static void validateAndRemove(Set<Position> set, int col, int lin){
        if(ChessCommonUtils.validateColLin(col, lin)){
            set.remove(new Position(col, lin));
        }
    }

    private static DirectionChecker getThreatDirection(Position positionKing, Board board) {
        var checker = new DirectionLChecker(
                new DirectionEChecker(new DirectionSChecker(new DirectionNChecker(
                        new DirectionWChecker(new DirectionSEChecker(
                                new DirectionSWChecker(new DirectionNEChecker(new DirectionNWChecker(null)))))))));
        return checker.setDirectionAndPosition(positionKing.getCol(), positionKing.getLin(), board);
    }
}
