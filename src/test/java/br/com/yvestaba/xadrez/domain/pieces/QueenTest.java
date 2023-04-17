package br.com.yvestaba.xadrez.domain.pieces;

import br.com.yvestaba.xadrez.domain.Board;
import br.com.yvestaba.xadrez.domain.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueenTest {

    private Board board;

    @BeforeEach
    void setup(){
        board = Board.startGame();
    }

    @Test
    void moveAllSides(){
        board.movePiece(new Position(3, 1), new Position(3, 3));
        board.movePiece(new Position(4, 6), new Position(4,4));
        board.movePiece(new Position(4,1), new Position(4,3));
        board.movePiece(new Position(4,4), new Position(3,3));
        board.movePiece(new Position(3,0), new Position(3,3));
        board.movePiece(new Position(0,6), new Position(0,5));
        var valid  = board.getValidPlaces(new Position(3,3));
        assertTrue(valid.contains(new Position(3,2)));
        assertTrue(valid.contains(new Position(3,1)));
        assertTrue(valid.contains(new Position(3,0)));
        assertTrue(valid.contains(new Position(3,4)));
        assertTrue(valid.contains(new Position(3,5)));
        assertTrue(valid.contains(new Position(3,6)));
        assertTrue(valid.contains(new Position(2,3)));
        assertTrue(valid.contains(new Position(1,3)));
        assertTrue(valid.contains(new Position(2,2)));
        assertTrue(valid.contains(new Position(4,4)));
        assertTrue(valid.contains(new Position(5,5)));
        assertTrue(valid.contains(new Position(6,6)));
        assertTrue(valid.contains(new Position(2,2)));
        assertTrue(valid.contains(new Position(2,4)));
        assertTrue(valid.contains(new Position(1,5)));
        assertTrue(valid.contains(new Position(0,6)));
        assertTrue(valid.contains(new Position(4,2)));
        assertEquals(17, valid.size());
    }

}