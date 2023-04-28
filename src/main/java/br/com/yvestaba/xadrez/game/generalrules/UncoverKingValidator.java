package br.com.yvestaba.xadrez.game.generalrules;

import br.com.yvestaba.xadrez.game.Color;
import br.com.yvestaba.xadrez.game.Direction;
import br.com.yvestaba.xadrez.game.Position;
import br.com.yvestaba.xadrez.game.pieces.King;
import br.com.yvestaba.xadrez.game.pieces.Piece;

import java.util.HashSet;
import java.util.Set;

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
            return KingMoveValidator.validate(valid, threatChecker);
        }
        Position kingPosition = this.kingPosition.getPositionByColor(board.getTurnOwner());
        Direction direction = getDirection(from, kingPosition);
        if (isNull(direction)) {
            return valid;
        }
        if(isCoveringKing(from, board, direction, kingPosition)){
            var positions = possiblePositions(from, board, direction, piece.getColor().getEnemy());
            if(!positions.isEmpty()) {
                valid.retainAll(positions);
            }
            return valid;
        }
        return valid;
    }

    private Set<Position> possiblePositions(Position from, Board board, Direction direction, Color enemy){
        switch (direction){
            case E:
                Piece pieceE = null;
                int colE = from.getCol() + 1;
                var ret = new HashSet<Position>();
                while(isNull(pieceE) && colE < 8){
                    Position pos = new Position(colE, from.getLin());
                    ret.add(pos);
                    pieceE = board.getPiece(pos);
                    if(pieceE instanceof MoveCross && pieceE.getColor() == enemy){
                        return ret;
                    }
                    colE++;
                }
                return new HashSet<>();
            case W:
                Piece pieceW = null;
                int colW = from.getCol() - 1;
                var retW = new HashSet<Position>();
                while(isNull(pieceW) && colW >= 0){
                    Position pos = new Position(colW, from.getLin());
                    retW.add(pos);
                    pieceW = board.getPiece(pos);
                    if(pieceW instanceof MoveCross && pieceW.getColor() == enemy){
                        return retW;
                    }
                    colW--;
                }
                return new HashSet<>();
            case N:
                Piece pieceN = null;
                int linN = from.getLin() + 1;
                var retN = new HashSet<Position>();
                while(isNull(pieceN) && linN < 8){
                    Position pos = new Position(from.getCol(), linN);
                    retN.add(pos);
                    pieceN = board.getPiece(pos);
                    if(pieceN instanceof MoveCross && pieceN.getColor() == enemy){
                        return retN;
                    }
                    linN++;
                }
                return new HashSet<>();
            case S:
                Piece pieceS = null;
                int linS = from.getLin() - 1;
                var retS = new HashSet<Position>();
                while(isNull(pieceS) && linS < 8){
                    Position pos = new Position(from.getCol(), linS);
                    retS.add(pos);
                    pieceS = board.getPiece(pos);
                    if(pieceS instanceof MoveCross && pieceS.getColor() == enemy){
                        return retS;
                    }
                    linS--;
                }
                return new HashSet<>();
            case NE:
                Piece pieceNE = null;
                int colNE = from.getCol() + 1;
                int linNE = from.getLin() + 1;
                var retNE = new HashSet<Position>();
                while(isNull(pieceNE) && colNE < 8 && linNE < 8){
                    Position pos = new Position(colNE, linNE);
                    retNE.add(pos);
                    pieceNE = board.getPiece(pos);
                    if(pieceNE instanceof MoveDiagonal && pieceNE.getColor() == enemy){
                        return retNE;
                    }
                    colNE++;
                    linNE++;
                }
                return new HashSet<>();
            case NW:
                Piece pieceNW = null;
                int colNW = from.getCol() - 1;
                int linNW = from.getLin() + 1;
                var retNW = new HashSet<Position>();
                while(isNull(pieceNW) && colNW >= 0 && linNW < 8){
                    Position pos = new Position(colNW, linNW);
                    retNW.add(pos);
                    pieceNW = board.getPiece(pos);
                    if(pieceNW instanceof MoveDiagonal && pieceNW.getColor() == enemy){
                        return retNW;
                    }
                    colNW--;
                    linNW++;
                }
                return new HashSet<>();
            case SE:
                Piece pieceSE = null;
                int linSE = from.getLin() - 1;
                int colSE = from.getCol() + 1;
                var retSE = new HashSet<Position>();
                while(isNull(pieceSE) && linSE >= 0 && colSE < 8){
                    Position pos = new Position(colSE, linSE);
                    retSE.add(pos);
                    pieceSE = board.getPiece(pos);
                    if(pieceSE instanceof MoveDiagonal && pieceSE.getColor() == enemy){
                        return retSE;
                    }
                    linSE--;
                    colSE++;
                }
                return new HashSet<>();
            case SW:
                Piece pieceSW = null;
                int linSW = from.getLin() - 1;
                int colSW = from.getCol() - 1;
                var retSW = new HashSet<Position>();
                while(isNull(pieceSW) && linSW >= 0 && colSW >= 0){
                    Position pos = new Position(colSW, linSW);
                    retSW.add(pos);
                    pieceSW = board.getPiece(pos);
                    if(pieceSW instanceof MoveDiagonal && pieceSW.getColor() == enemy){
                        return retSW;
                    }
                    linSW--;
                    colSW--;
                }
                return new HashSet<>();
        }
        throw new RuntimeException("Unexpected error");
    }

    private boolean isCoveringKing(Position from, Board board, Direction direction, Position kingPosition) {
        switch (direction){
            case E:
                int colN = from.getCol() - 1;
                while(kingPosition.getCol() < colN){
                    Piece piece = board.getPiece(new Position(colN, from.getLin()));
                    if (nonNull(piece)) {
                        return false;
                    }
                    colN--;
                }
                return true;
            case W:
                int colW = from.getCol() + 1;
                while(kingPosition.getCol() > colW){
                    Piece pieceS = board.getPiece(new Position(colW, from.getLin()));
                    if (nonNull(pieceS)) {
                        return false;
                    }
                    colW++;
                }
                return true;
            case N:
                int linN = from.getLin() - 1;
                while(kingPosition.getLin() < linN){
                    Piece pieceE = board.getPiece(new Position(from.getCol(), linN));
                    if (nonNull(pieceE)) {
                        return false;
                    }
                    linN--;
                }
                return true;
            case S:
                int linS = from.getLin() + 1;
                while(kingPosition.getLin() > linS){
                    Piece pieceW = board.getPiece(new Position(from.getCol(), linS));
                    if (nonNull(pieceW)) {
                        return false;
                    }
                    linS++;
                }
                return true;
            case NE:
                int colNE = from.getCol() - 1;
                int linNE = from.getLin() - 1;
                while(kingPosition.getCol() < colNE && kingPosition.getLin() < linNE){
                    Piece pieceNE = board.getPiece(new Position(colNE, linNE));
                    if (nonNull(pieceNE)) {
                        return false;
                    }
                    colNE--;
                    linNE--;
                }
                return true;
            case NW:
                int colNW = from.getCol() + 1;
                int linNW = from.getLin() - 1;
                while(kingPosition.getCol() > colNW && kingPosition.getLin() < linNW){
                    Piece pieceNW = board.getPiece(new Position(colNW, linNW));
                    if (nonNull(pieceNW)) {
                        return false;
                    }
                    colNW++;
                    linNW--;
                }
                return true;
            case SE:
                int colSE = from.getCol() - 1;
                int linSE = from.getLin() + 1;
                while(kingPosition.getCol() < colSE && kingPosition.getLin() > linSE){
                    Piece pieceSE = board.getPiece(new Position(colSE, linSE));
                    if (nonNull(pieceSE)) {
                        return false;
                    }
                    colSE--;
                    linSE++;
                }
                return true;
            case SW:
                int colSW = from.getCol() + 1;
                int linSW = from.getLin() + 1;
                while(kingPosition.getCol() > colSW && kingPosition.getLin() > linSW){
                    Piece pieceSW = board.getPiece(new Position(colSW, linSW));
                    if (nonNull(pieceSW)) {
                        return false;
                    }
                    colSW++;
                    linSW++;
                }
                return true;
        }
        throw new RuntimeException("Unexpected error");
    }

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
