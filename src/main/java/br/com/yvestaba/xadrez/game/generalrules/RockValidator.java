package br.com.yvestaba.xadrez.game.generalrules;

import br.com.yvestaba.xadrez.game.Color;
import br.com.yvestaba.xadrez.game.GameStatus;
import br.com.yvestaba.xadrez.game.Position;

import java.util.Set;

import static br.com.yvestaba.xadrez.game.Color.WHITE;
import static java.util.Objects.nonNull;

class RockValidator implements MoveValidator{

    private final RockChecker checker;
    private final ThreatChecker threatChecker;

    RockValidator(RockChecker checker, ThreatChecker threatChecker){
        this.checker = checker;
        this.threatChecker = threatChecker;
    }
    @Override
    public Set<Position> getValidPlaces(Position from, Board board, Set<Position> valid) {
        Color turnOwner = board.getTurnOwner();
        if(!from.equals(getKingInitialPosition(turnOwner))){
            return valid;
        }
        if(board.getStatus() == GameStatus.CHECK){
            return valid;
        }

        if(checker.smallRockByColor(turnOwner)){
            addSmallRockIfValid(turnOwner, board, valid);
        }
        if(checker.bigRockByColor(turnOwner)){
            addBigRockIfValid(turnOwner, board, valid);
        }
        return valid;
    }

    private void addBigRockIfValid(Color turnOwner, Board board, Set<Position> valid) {
        int line = getLineByColor(turnOwner);
        for(int i = 2; i < 4; i++){
            Position position = new Position(i, line);
            boolean isThereAnyPiece = nonNull(board.getPiece(position));
            if(isThereAnyPiece || threatChecker.getThreatenArea().contains(position)){
                return;
            }
        }
        valid.add(new Position(2, line));
    }

    private void addSmallRockIfValid(Color turnOwner, Board board, Set<Position> valid) {
        int line = getLineByColor(turnOwner);
        for(int i = 5; i < 7; i++){
            Position position = new Position(i, line);
            boolean isThereAnyPiece = nonNull(board.getPiece(position));
            if(isThereAnyPiece || threatChecker.getThreatenArea().contains(position)){
                return;
            }
        }
        valid.add(new Position(6, line));
    }

    private int getLineByColor(Color turnOwner) {
        return turnOwner == WHITE ? 0 : 7;
    }


    private Position getKingInitialPosition(Color turnOwner) {
        if(turnOwner == WHITE){
            return new Position(4,0);
        }
        return new Position(4,7);
    }

}
