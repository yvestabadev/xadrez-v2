package br.com.yvestaba.xadrez.domain.generalrules;

import br.com.yvestaba.xadrez.domain.Color;
import br.com.yvestaba.xadrez.domain.Direction;
import br.com.yvestaba.xadrez.domain.Position;
import br.com.yvestaba.xadrez.domain.directionchecker.*;
import br.com.yvestaba.xadrez.domain.pieces.Piece;

import java.util.HashSet;
import java.util.Set;

import static br.com.yvestaba.xadrez.domain.Color.WHITE;
import static br.com.yvestaba.xadrez.domain.Direction.*;
import static java.util.Objects.isNull;

class MoveInCheckValidator implements MoveValidator{

    private final KingPosition kingPosition;
    private final ThreatChecker threatChecker;

    MoveInCheckValidator(KingPosition kingPosition, ThreatChecker threatChecker){
        this.kingPosition = kingPosition;
        this.threatChecker = threatChecker;
    }
    @Override
    public Set<Position> getValidPlaces(Position from, Board board, Set<Position> valid) {
        Color turnOwner = board.getTurnOwner();
        var positionKing = turnOwner == WHITE ? kingPosition.getWhitePosition() : kingPosition.getBlackPosition();
        if(from.equals(positionKing)){
            return valid;
        }
        if(!threatChecker.getThreatenArea().contains(positionKing)){
            return valid;
        }
        var directionChecker = getThreatDirection(positionKing, board);
        return invalidateByDirection(valid, directionChecker, board, positionKing);
    }

    private Set<Position> invalidateByDirection(Set<Position> valid, DirectionChecker directionChecker, Board board, Position positionKing) {
        if(directionChecker.getDirection() == MANY){
            return new HashSet<>();
        }
        Piece piece = null;
        Set<Position> intersec = new HashSet<>();
        Integer col = getColumnByDirection(positionKing.getCol(), directionChecker.getDirection());
        Integer lin = getLineByDirection(positionKing.getLin(), directionChecker.getDirection());

        while(isNull(piece)){
            Position position = new Position(col, lin);
            piece = board.getPiece(position);
            intersec.add(position);
            col = getColumnByDirection(col, directionChecker.getDirection());
            lin = getLineByDirection(lin, directionChecker.getDirection());
        }
        valid.retainAll(intersec);
        return valid;
    }

    private Integer getColumnByDirection(int col, Direction direction) {
        switch(direction){
            case S,N:
                return col;
            case W,SW,NW:
                return col - 1;
            case E,NE,SE:
                return col + 1;
        }
        throw new RuntimeException("Unexpected Error");
    }

    private Integer getLineByDirection(int lin, Direction direction) {
        switch(direction){
            case E,W:
                return lin;
            case S,SE,SW:
                return lin - 1;
            case N,NE,NW:
                return lin + 1;
        }
        throw new RuntimeException("Unexpected Error");
    }

    private DirectionChecker getThreatDirection(Position positionKing, Board board) {
        var checker = new DirectionLChecker(
                new DirectionEChecker(new DirectionSChecker(new DirectionNChecker(
                        new DirectionWChecker(new DirectionSEChecker(
                                new DirectionSWChecker(new DirectionNEChecker(new DirectionNWChecker(null)))))))));
        return checker.setDirectionAndPosition(positionKing.getCol(), positionKing.getLin(), board);
    }


}