package br.com.yvestaba.xadrez.domain.pieces;

import br.com.yvestaba.xadrez.domain.Board;
import br.com.yvestaba.xadrez.domain.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BishopTest {

    private Board board;

    @BeforeEach
    void setUp() {
        this.board = Board.startGame();
    }

    @Test
    void moveForwardAndBackward(){
        board.movePiece(new Position(3, 1), new Position(3,3));
        board.movePiece(new Position(3, 6), new Position(3,4));
        board.movePiece(new Position(2, 0), new Position(5,3));
        board.movePiece(new Position(4, 6), new Position(4, 4));
        var valid = board.getValidPlaces(new Position(5, 3));
        assertTrue(valid.contains(new Position(4, 4)));
        assertTrue(valid.contains(new Position(6,4)));
        assertTrue(valid.contains(new Position(6,2)));
        assertTrue(valid.contains(new Position(7,5)));
        assertTrue(valid.contains(new Position(2,0)));
        assertTrue(valid.contains(new Position(3,1)));
        assertTrue(valid.contains(new Position(4,2)));
        assertEquals(7, valid.size());
    }

}