package br.com.yvestaba.xadrez.game.generalrules;

import br.com.yvestaba.xadrez.game.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RockValidatorTest {

    private PiecesMover piecesMover;

    @BeforeEach
    void setUp(){
        this.piecesMover = new PiecesMover();
    }

    @Test
    void smallRockWhite(){
        piecesMover.movePiece(new Position(6,0), new Position(7,2));
        piecesMover.movePiece(new Position(0,6), new Position(0,5));
        piecesMover.movePiece(new Position(4,1), new Position(4,2));
        piecesMover.movePiece(new Position(0,5), new Position(0,4));
        piecesMover.movePiece(new Position(5,0), new Position(4,1));
        piecesMover.movePiece(new Position(0,4), new Position(0,3));
        var valid = piecesMover.validMoves(new Position(4,0));
        assertTrue(valid.contains(new Position(6,0)));
    }

    @Test
    void bigRockWhite(){
        piecesMover.movePiece(new Position(1,0), new Position(0,2));
        piecesMover.movePiece(new Position(7,6), new Position(7,5));
        piecesMover.movePiece(new Position(3,1), new Position(3,3));
        piecesMover.movePiece(new Position(7,5), new Position(7,4));
        piecesMover.movePiece(new Position(2,0), new Position(4,2));
        piecesMover.movePiece(new Position(7,4), new Position(7,3));
        piecesMover.movePiece(new Position(3,0), new Position(3,1));
        piecesMover.movePiece(new Position(7,3), new Position(7,2));
        var valid = piecesMover.validMoves(new Position(4,0));
        assertTrue(valid.contains(new Position(2,0)));
    }

    @Test
    void smallRockWhiteThreat(){
        piecesMover.movePiece(new Position(6,0), new Position(7,2));
        piecesMover.movePiece(new Position(4,6), new Position(4,5));
        piecesMover.movePiece(new Position(4,1), new Position(4,3));
        piecesMover.movePiece(new Position(3,7), new Position(7,3));
        piecesMover.movePiece(new Position(5,0), new Position(2,3));
        piecesMover.movePiece(new Position(7,3), new Position(4,3));
        var valid = piecesMover.validMoves(new Position(4,0));
        assertFalse(valid.contains(new Position(6,0)));
    }

    @Test
    void smallRockWhiteThreatInTheMiddle(){
        piecesMover.movePiece(new Position(6,0), new Position(7,2));
        piecesMover.movePiece(new Position(4,6), new Position(4,5));
        piecesMover.movePiece(new Position(4,1), new Position(4,3));
        piecesMover.movePiece(new Position(3,7), new Position(7,3));
        piecesMover.movePiece(new Position(5,0), new Position(2,3));
        piecesMover.movePiece(new Position(7,3), new Position(5,3));
        piecesMover.movePiece(new Position(5,1), new Position(5,2));
        piecesMover.movePiece(new Position(5,3), new Position(5,2));
        var valid = piecesMover.validMoves(new Position(4,0));
        assertFalse(valid.contains(new Position(6,0)));
    }

    @Test
    void smallRockBlack(){
        piecesMover.movePiece(new Position(0,1), new Position(0,2));
        piecesMover.movePiece(new Position(4,6), new Position(4,5));
        piecesMover.movePiece(new Position(0,2), new Position(0,3));
        piecesMover.movePiece(new Position(5,7), new Position(3,5));
        piecesMover.movePiece(new Position(0,3), new Position(0,4));
        piecesMover.movePiece(new Position(6,7), new Position(7,5));
        piecesMover.movePiece(new Position(0,4), new Position(0,5));
        var valid = piecesMover.validMoves(new Position(4,7));
        assertTrue(valid.contains(new Position(6,7)));
    }

    @Test
    void bigRockBlack(){
        piecesMover.movePiece(new Position(0,1), new Position(0,2));
        piecesMover.movePiece(new Position(3,6), new Position(3,5));
        piecesMover.movePiece(new Position(0,2), new Position(0,3));
        piecesMover.movePiece(new Position(2,7), new Position(4,5));
        piecesMover.movePiece(new Position(0,3), new Position(0,4));
        piecesMover.movePiece(new Position(1,7), new Position(0,5));
        piecesMover.movePiece(new Position(1,1), new Position(1,2));
        piecesMover.movePiece(new Position(3,7), new Position(3,6));
        piecesMover.movePiece(new Position(1,2), new Position(1,3));
        var valid = piecesMover.validMoves(new Position(4,7));
        assertTrue(valid.contains(new Position(2,7)));
    }

    @Test
    void smallRockBlackThreatInTheMiddle(){
        piecesMover.movePiece(new Position(4,1), new Position(4,2));
        piecesMover.movePiece(new Position(4,6), new Position(4,5));
        piecesMover.movePiece(new Position(3,0), new Position(7,4));
        piecesMover.movePiece(new Position(5,7), new Position(3,5));
        piecesMover.movePiece(new Position(0,1), new Position(0,2));
        piecesMover.movePiece(new Position(6,7), new Position(7,5));
        piecesMover.movePiece(new Position(0,2), new Position(0,3));
        piecesMover.movePiece(new Position(6,6), new Position(6,5));
        piecesMover.movePiece(new Position(7,4), new Position(7,5));
        var valid = piecesMover.validMoves(new Position(4,7));
        assertFalse(valid.contains(new Position(6,7)));
    }

}