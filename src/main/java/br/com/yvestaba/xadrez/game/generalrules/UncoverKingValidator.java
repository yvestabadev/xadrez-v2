package br.com.yvestaba.xadrez.game.generalrules;

import br.com.yvestaba.xadrez.game.Direction;
import br.com.yvestaba.xadrez.game.Position;
import br.com.yvestaba.xadrez.game.directionchecker.*;
import br.com.yvestaba.xadrez.game.pieces.King;
import br.com.yvestaba.xadrez.game.pieces.Piece;

import java.util.HashSet;
import java.util.Set;

import static br.com.yvestaba.xadrez.utils.DirectionUtils.getPath;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

class UncoverKingValidator implements MoveValidator{

    private final KingPosition kingPosition;
    private final ThreatChecker threatChecker;

    UncoverKingValidator(KingPosition kingPosition, ThreatChecker threatChecker){
        this.kingPosition = kingPosition;
        this.threatChecker = threatChecker;
    }
    @Override
    public Set<Position> getValidPlaces(Position from, Board board, Set<Position> valid) {
        Piece piece = board.getPiece(from);
        if(piece instanceof King){
            return KingMoveValidator.validate(from, valid, threatChecker, board);
        }
        if(!threatChecker.getThreatenArea().contains(from)){
            return valid;
        }
        Position kingPosition = this.kingPosition.getPositionByColor(board.getTurnOwner());
        Direction direction = getDirection(from, kingPosition);
        if (isNull(direction)) {
            return valid;
        }
        var intersection = getPathTillKing(from, board, direction, kingPosition);
        if (nonNull(intersection) && isPathThreatened(from, board, direction, intersection)) {
            valid.retainAll(intersection);
        }
        return valid;
    }

    private boolean isPathThreatened(Position from, Board board, Direction direction, Set<Position> intersection) {
        DirectionChecker directionChecker = null;
        switch (direction){
            case E:
                directionChecker =
                    new DirectionEChecker(null).setDirectionAndPosition(from.getCol(), from.getLin(), board);
                break;
            case W:
                directionChecker =
                        new DirectionWChecker(null).setDirectionAndPosition(from.getCol(), from.getLin(), board);
                break;
            case N:
                directionChecker =
                        new DirectionNChecker(null).setDirectionAndPosition(from.getCol(), from.getLin(), board);
                break;
            case S:
                directionChecker =
                        new DirectionSChecker(null).setDirectionAndPosition(from.getCol(), from.getLin(), board);
                break;
            case NE:
                directionChecker =
                        new DirectionNEChecker(null).setDirectionAndPosition(from.getCol(), from.getLin(), board);
                break;
            case NW:
                directionChecker =
                        new DirectionNWChecker(null).setDirectionAndPosition(from.getCol(), from.getLin(), board);
                break;
            case SE:
                directionChecker =
                        new DirectionSEChecker(null).setDirectionAndPosition(from.getCol(), from.getLin(), board);
                break;
            case SW:
                directionChecker =
                        new DirectionSWChecker(null).setDirectionAndPosition(from.getCol(), from.getLin(), board);
                break;
        }
        Position threatPosition = directionChecker.getThreatPosition();
        boolean isThreatened = nonNull(threatPosition);
        if(isThreatened){
            intersection.addAll(getPath(from, threatPosition, direction));
        }
        return isThreatened;
    }

    private Set<Position> getPathTillKing(Position from, Board board, Direction direction, Position kingPosition) {
        Set<Position> intersection = new HashSet<>();
        var path = getPath(from, kingPosition, direction.opposite());
        path.remove(kingPosition);
        for(var pos : path){
            if(nonNull(board.getPiece(pos))){
                return null;
            }
            intersection.add(pos);
        }
        return intersection;
    }

    /**
     *
     * @param piecePosition
     * @param kingPosition
     * @return direction of the piece related to its king
     */
    private Direction getDirection(Position piecePosition, Position kingPosition){
        if(piecePosition.getCol() == kingPosition.getCol()){
            if(piecePosition.getLin() > kingPosition.getLin()){
                return Direction.N;
            }
            return Direction.S;
        }

        if(piecePosition.getLin() == kingPosition.getLin()){
            if(piecePosition.getCol() > kingPosition.getCol()){
                return Direction.E;
            }
            return Direction.W;
        }
        if(piecePosition.getLin() - kingPosition.getLin() != piecePosition.getCol() - kingPosition.getCol() &&
            piecePosition.getLin() - kingPosition.getLin() != kingPosition.getCol() - piecePosition.getCol()){
            return null;
        }
        if(piecePosition.getLin() > kingPosition.getLin() && piecePosition.getCol() > kingPosition.getCol()){
            return Direction.NE;
        }
        if(piecePosition.getLin() > kingPosition.getLin() && piecePosition.getCol() < kingPosition.getCol()){
            return Direction.NW;
        }
        if(piecePosition.getLin() < kingPosition.getLin() && piecePosition.getCol() > kingPosition.getCol()){
            return Direction.SE;
        }
        if(piecePosition.getLin() < kingPosition.getLin() && piecePosition.getCol() < kingPosition.getCol()){
            return Direction.SW;
        }
        throw new RuntimeException("unexpected error");
    }
}
