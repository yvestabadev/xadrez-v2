package br.com.yvestaba.xadrez.game.pieces;

import br.com.yvestaba.xadrez.game.Position;
import br.com.yvestaba.xadrez.game.generalrules.PiecesMover;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PawnTest {

    private PiecesMover board;

    @BeforeEach
    void setup(){
        board = new PiecesMover();
    }

    @Test
    void moveForward(){
        var valid = board.validMoves(new Position(0, 1));
        assertTrue(valid.contains(new Position(0, 2)));
        assertTrue(valid.contains(new Position(0, 3)));
        assertEquals(2, valid.size());
    }

    @Test
    void cannotMoveSinceThereIsAnotherPieceInFrontOf(){
        board.movePiece(new Position(0, 1), new Position(0,3));
        board.movePiece(new Position(0, 6), new Position(0, 4));
        var valid = board.validMoves(new Position(0, 3));
        assertTrue(valid.isEmpty());
    }

    @Test
    void moveToCapture(){
        board.movePiece(new Position(3, 1), new Position(3,3));
        board.movePiece(new Position(4, 6), new Position(4, 4));
        var valid = board.validMoves(new Position(3, 3));
        assertTrue(valid.contains(new Position(4,4)));
        assertTrue(valid.contains(new Position(3,4)));
        assertEquals(2, valid.size());
    }

    @Test
    void moveToCaptureBlack(){
        board.movePiece(new Position(3, 1), new Position(3,3));
        board.movePiece(new Position(4, 6), new Position(4, 4));
        board.movePiece(new Position(4, 1), new Position(4,3));
        var valid = board.validMoves(new Position(4, 4));
        assertTrue(valid.contains(new Position(3,3)));
        assertEquals(1, valid.size());
    }

}