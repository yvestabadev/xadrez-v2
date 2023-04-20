package br.com.yvestaba.xadrez.domain.generalrules;

import br.com.yvestaba.xadrez.domain.Direction;
import br.com.yvestaba.xadrez.domain.Position;
import br.com.yvestaba.xadrez.domain.pieces.King;
import br.com.yvestaba.xadrez.domain.pieces.Piece;

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
        if(board.getPiece(from) instanceof King){
            return KingMoveValidator.validate(valid, threatChecker);
        }
        Direction direction = getDirection(from, kingPosition.getPositionByColor(board.getTurnOwner()));
        if (isNull(direction)) {
            return valid;
        }
        if(isCoveringKing(from, board, direction)){
            var positions = possiblePositions(from, board, direction);
            if(!positions.isEmpty()) {
                valid.retainAll(positions);
            }
            return valid;
        }
        return valid;
    }

    private Set<Position> possiblePositions(Position from, Board board, Direction direction){
        switch (direction){
            case E:
                Piece pieceE = null;
                int colE = from.getCol() + 1;
                var ret = new HashSet<Position>();
                while(isNull(pieceE) && colE < 8){
                    Position pos = new Position(colE, from.getLin());
                    ret.add(pos);
                    pieceE = board.getPiece(pos);
                    if(pieceE instanceof MoveCross){
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
                    if(pieceW instanceof MoveCross){
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
                    if(pieceN instanceof MoveCross){
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
                    if(pieceS instanceof MoveCross){
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
                    if(pieceNE instanceof MoveDiagonal){
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
                    if(pieceNW instanceof MoveDiagonal){
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
                    if(pieceSE instanceof MoveDiagonal){
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
                    if(pieceSW instanceof MoveDiagonal){
                        return retSW;
                    }
                    linSW--;
                    colSW--;
                }
                return new HashSet<>();
        }
        throw new RuntimeException("Unexpected error");
    }

    private boolean isCoveringKing(Position from, Board board, Direction direction) {
        switch (direction){
            case E:
                int colN = from.getCol() - 1;
                Piece pieceN = null;
                while(isNull(pieceN)){
                    pieceN = board.getPiece(new Position(colN, from.getLin()));
                    if(nonNull(pieceN) && pieceN instanceof King){
                        return true;
                    } else if (nonNull(pieceN)) {
                        return false;
                    }
                    colN--;
                }
                break;
            case W:
                int colS = from.getCol() + 1;
                Piece pieceS = null;
                while(isNull(pieceS)){
                    pieceS = board.getPiece(new Position(colS, from.getLin()));
                    if(nonNull(pieceS) && pieceS instanceof King){
                        return true;
                    } else if (nonNull(pieceS)) {
                        return false;
                    }
                    colS++;
                }
                break;
            case N:
                int linE = from.getLin() - 1;
                Piece pieceE = null;
                while(isNull(pieceE)){
                    pieceE = board.getPiece(new Position(from.getCol(), linE));
                    if(nonNull(pieceE) && pieceE instanceof King){
                        return true;
                    } else if (nonNull(pieceE)) {
                        return false;
                    }
                    linE--;
                }
                break;
            case S:
                int linW = from.getLin() + 1;
                Piece pieceW = null;
                while(isNull(pieceW)){
                    pieceW = board.getPiece(new Position(from.getCol(), linW));
                    if(nonNull(pieceW) && pieceW instanceof King){
                        return true;
                    } else if (nonNull(pieceW)) {
                        return false;
                    }
                    linW++;
                }
                break;
            case NE:
                int colNE = from.getCol() - 1;
                int linNE = from.getLin() - 1;
                Piece pieceNE = null;
                while(isNull(pieceNE)){
                    pieceNE = board.getPiece(new Position(colNE, linNE));
                    if(nonNull(pieceNE) && pieceNE instanceof King){
                        return true;
                    } else if (nonNull(pieceNE)) {
                        return false;
                    }
                    colNE--;
                    linNE--;
                }
                break;
            case NW:
                int colNW = from.getCol() + 1;
                int linNW = from.getLin() - 1;
                Piece pieceNW = null;
                while(isNull(pieceNW)){
                    pieceNW = board.getPiece(new Position(colNW, linNW));
                    if(nonNull(pieceNW) && pieceNW instanceof King){
                        return true;
                    } else if (nonNull(pieceNW)) {
                        return false;
                    }
                    colNW++;
                    linNW--;
                }
                break;
            case SE:
                int colSE = from.getCol() - 1;
                int linSE = from.getLin() + 1;
                Piece pieceSE = null;
                while(isNull(pieceSE)){
                    pieceSE = board.getPiece(new Position(colSE, linSE));
                    if(nonNull(pieceSE) && pieceSE instanceof King){
                        return true;
                    } else if (nonNull(pieceSE)) {
                        return false;
                    }
                    colSE--;
                    linSE++;
                }
                break;
            case SW:
                int colSW = from.getCol() + 1;
                int linSW = from.getLin() + 1;
                Piece pieceSW = null;
                while(isNull(pieceSW)){
                    pieceSW = board.getPiece(new Position(colSW, linSW));
                    if(nonNull(pieceSW) && pieceSW instanceof King){
                        return true;
                    } else if (nonNull(pieceSW)) {
                        return false;
                    }
                    colSW++;
                    linSW++;
                }
                break;
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
