package br.com.yvestaba.xadrez.domain.pieces;

import br.com.yvestaba.xadrez.domain.Board;
import br.com.yvestaba.xadrez.domain.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PawnTest {

    private Board board;

    @BeforeEach
    void setup(){
        board = Board.startGame();
    }

    @Test
    void moveForward(){
        var valid = board.getValidPlaces(new Position(0, 1));
        assertTrue(valid.contains(new Position(0, 2)));
        assertTrue(valid.contains(new Position(0, 3)));
        assertEquals(2, valid.size());
    }

    @Test
    void cannotMoveSinceThereIsAnotherPieceInFrontOf(){
        board.movePiece(new Position(0, 1), new Position(0,3));
        board.movePiece(new Position(0, 6), new Position(0, 4));
        var valid = board.getValidPlaces(new Position(0, 3));
        assertTrue(valid.isEmpty());
    }

    @Test
    void moveToCapture(){
        board.movePiece(new Position(3, 1), new Position(3,3));
        board.movePiece(new Position(4, 6), new Position(4, 4));
        var valid = board.getValidPlaces(new Position(3, 3));
        assertTrue(valid.contains(new Position(4,4)));
        assertTrue(valid.contains(new Position(3,4)));
        assertEquals(2, valid.size());
    }

    @Test
    void moveToCaptureBlack(){
        board.movePiece(new Position(3, 1), new Position(3,3));
        board.movePiece(new Position(4, 6), new Position(4, 4));
        board.movePiece(new Position(4, 1), new Position(4,3));
        var valid = board.getValidPlaces(new Position(4, 4));
        assertTrue(valid.contains(new Position(3,3)));
        assertEquals(1, valid.size());
    }

}