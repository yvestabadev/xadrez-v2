package br.com.yvestaba.xadrez.domain.generalrules;

import br.com.yvestaba.xadrez.domain.GameStatus;
import br.com.yvestaba.xadrez.domain.Position;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PiecesMover {

    private final List<MoveChecker> movers;
    private final List<MoveValidator> validators;
    private final ThreatChecker threatChecker;
    private final KingPosition kingPosition;
    private final Board board;

    public PiecesMover(){
        final var enPassantChecker = new EnPassantChecker();
        board = Board.startGame(new EnPassantValidator(enPassantChecker));
        threatChecker = new ThreatChecker();
        final var rockChecker = new RockChecker();
        kingPosition = new KingPosition();
        movers = Arrays.asList(board, threatChecker, rockChecker, kingPosition, enPassantChecker, new EnPassantCaptor(enPassantChecker));
        validators = Arrays.asList(board, new EnPassantValidator(enPassantChecker), new RockValidator(rockChecker, threatChecker), new UncoverKingValidator(kingPosition, threatChecker), new MoveInCheckValidator(kingPosition, threatChecker));
    }

    public void movePiece(Position from, Position to){
        movers.forEach(m -> m.movePiece(from, to, board));
        CheckMateChecker.check(threatChecker, kingPosition, board, this);
    }

    public Set<Position> validMoves(Position from){
        Set<Position> ret = new HashSet<>();
        for(var validator : validators){
            ret = validator.getValidPlaces(from, board, ret);
        }
        return ret;
    }

    public GameStatus getStatus(){
        return board.getStatus();
    }
}
