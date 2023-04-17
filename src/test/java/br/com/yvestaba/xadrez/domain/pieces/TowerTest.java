package br.com.yvestaba.xadrez.domain.pieces;

import br.com.yvestaba.xadrez.domain.Board;
import br.com.yvestaba.xadrez.domain.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TowerTest {

    private Board board;

    @BeforeEach
    void setUp() {
        this.board = Board.startGame();
    }

    @Test
    void moveAllSides(){
        board.movePiece(new Position(0, 1), new Position(0,3));
        board.movePiece(new Position(0, 6), new Position(0,5));
        board.movePiece(new Position(0, 0), new Position(0,2));
        board.movePiece(new Position(6, 6), new Position(6,5));
        board.movePiece(new Position(0, 2), new Position(3,2));
        board.movePiece(new Position(3, 6), new Position(3,4));
        board.movePiece(new Position(3, 2), new Position(3,4));
        board.movePiece(new Position(0, 5), new Position(0,4));
        var valid = board.getValidPlaces(new Position(3, 4));
        assertTrue(valid.contains(new Position(3,5)));
        assertTrue(valid.contains(new Position(3,6)));
        assertTrue(valid.contains(new Position(3,7)));
        assertTrue(valid.contains(new Position(3,3)));
        assertTrue(valid.contains(new Position(2,4)));
        assertTrue(valid.contains(new Position(1,4)));
        assertTrue(valid.contains(new Position(0,4)));
        assertTrue(valid.contains(new Position(4,4)));
        assertTrue(valid.contains(new Position(5,4)));
        assertTrue(valid.contains(new Position(6,4)));
        assertTrue(valid.contains(new Position(7,4)));
        assertTrue(valid.contains(new Position(3,3)));
        assertEquals(12, valid.size());

    }
}