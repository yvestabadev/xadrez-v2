package br.com.yvestaba.xadrez.game.generalrules;

import br.com.yvestaba.xadrez.game.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnPassantValidatorTest {

    private PiecesMover piecesMover;

    @BeforeEach
    void setUp(){
        this.piecesMover = new PiecesMover();
    }

    @Test
    void enPassantNE(){
        piecesMover.movePiece(new Position(4,1), new Position(4,3));
        piecesMover.movePiece(new Position(0,6), new Position(0,4));
        piecesMover.movePiece(new Position(4,3), new Position(4,4));
        piecesMover.movePiece(new Position(3,6), new Position(3,4));
        var valid = piecesMover.validMoves(new Position(4,4));
        assertTrue(valid.contains(new Position(3,5)));
    }
}