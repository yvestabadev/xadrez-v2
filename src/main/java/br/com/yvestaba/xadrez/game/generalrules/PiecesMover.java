package br.com.yvestaba.xadrez.game.generalrules;

import br.com.yvestaba.xadrez.game.GameStatus;
import br.com.yvestaba.xadrez.game.Position;
import br.com.yvestaba.xadrez.game.pieces.Piece;

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
    private final PawnPromotionChecker pawnPromotionChecker;

    public PiecesMover(){
        final var enPassantChecker = new EnPassantChecker();
        board = Board.startGame(new EnPassantValidator(enPassantChecker));
        threatChecker = new ThreatChecker();
        final var rockChecker = new RockChecker();
        kingPosition = new KingPosition();
        pawnPromotionChecker = new PawnPromotionChecker();
        movers = Arrays.asList(board, pawnPromotionChecker, threatChecker, rockChecker, kingPosition, enPassantChecker, new EnPassantCaptor(enPassantChecker));
        validators = Arrays.asList(board, new EnPassantValidator(enPassantChecker), new RockValidator(rockChecker, threatChecker), new UncoverKingValidator(kingPosition, threatChecker), new MoveInCheckValidator(kingPosition, threatChecker));
    }

    public void movePiece(Position from, Position to){
        var places = validMoves(from);
        if(!places.contains(to)){
            throw new RuntimeException("Disallowed movement");
        }
        movers.forEach(m -> m.movePiece(from, to, board));
        CheckMateChecker.check(threatChecker, kingPosition, board, this);
    }

    /**
     * Since board movement was already made, proceed with others movers
     * @param type
     */
    public void promotePawn(Class<Piece> type){
        pawnPromotionChecker.promotePawn(type);
        for (MoveChecker mover : movers.subList(1, movers.size())) {
            mover.movePiece(pawnPromotionChecker.getFrom(), pawnPromotionChecker.getTo(), board);
        }
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

    public Piece getPiece(Position from){
        return board.getPiece(from);
    }

    public Board getBoard() {
        return this.board;
    }
}
