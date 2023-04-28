package br.com.yvestaba.xadrez.game.generalrules;

import br.com.yvestaba.xadrez.game.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UncoverKingValidatorTest {

    private PiecesMover piecesMover;

    @BeforeEach
    void setUp(){
        this.piecesMover = new PiecesMover();
    }

    @Test
    void uncoverNE(){
        piecesMover.movePiece(new Position(4,1), new Position(4,3));
        piecesMover.movePiece(new Position(4,6), new Position(4,4));
        piecesMover.movePiece(new Position(3,1), new Position(3,3));
        piecesMover.movePiece(new Position(3,7), new Position(7,3));
        var valid = piecesMover.validMoves(new Position(5,1));
        assertTrue(valid.isEmpty());
    }

    @Test
    void uncoverNW(){
        piecesMover.movePiece(new Position(4,1), new Position(4,3));
        piecesMover.movePiece(new Position(2,6), new Position(2,4));
        piecesMover.movePiece(new Position(2,1), new Position(2,3));
        piecesMover.movePiece(new Position(3,7), new Position(0,4));
        var valid = piecesMover.validMoves(new Position(3,1));
        assertTrue(valid.isEmpty());
    }

}