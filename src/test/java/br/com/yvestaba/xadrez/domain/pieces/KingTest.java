package br.com.yvestaba.xadrez.domain.pieces;

import br.com.yvestaba.xadrez.domain.Board;
import br.com.yvestaba.xadrez.domain.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KingTest {

    private Board board;

    @BeforeEach
    void setup(){
        board = Board.startGame();
    }

    @Test
    void moveFront(){
        board.movePiece(new Position(4,1), new Position(4,3));
        board.movePiece(new Position(4,6), new Position(4,4));
        var valid = board.getValidPlaces(new Position(4,0));
        assertTrue(valid.contains(new Position(4,1)));
        assertEquals(1, valid.size());
    }

    @Test
    void moveBack(){
        board.movePiece(new Position(4,1), new Position(4,3));
        board.movePiece(new Position(4,6), new Position(4,4));
        board.movePiece(new Position(4,0), new Position(4,1));
        board.movePiece(new Position(3,6), new Position(3,5));

        var valid = board.getValidPlaces(new Position(4,1));
        assertTrue(valid.contains(new Position(4,2)));
        assertTrue(valid.contains(new Position(3,2)));
        assertTrue(valid.contains(new Position(5,2)));
        assertTrue(valid.contains(new Position(4,0)));
        assertEquals(4, valid.size());
    }

    @Test
    void moveSW(){
        board.movePiece(new Position(5,1), new Position(5,3));
        board.movePiece(new Position(4,6), new Position(4,4));
        board.movePiece(new Position(4,0), new Position(5,1));
        board.movePiece(new Position(3,6), new Position(3,5));
        var valid = board.getValidPlaces(new Position(5,1));

        assertTrue(valid.contains(new Position(4,0)));
        assertTrue(valid.contains(new Position(6,2)));
        assertTrue(valid.contains(new Position(5,2)));
        assertTrue(valid.contains(new Position(4,2)));

        assertEquals(4, valid.size());
    }

    @Test
    void moveNE(){
        board.movePiece(new Position(5,1), new Position(5,3));
        board.movePiece(new Position(4,6), new Position(4,4));
        var valid = board.getValidPlaces(new Position(4,0));
        assertTrue(valid.contains(new Position(5,1)));
        assertEquals(1, valid.size());
    }

    @Test
    void moveNW(){
        board.movePiece(new Position(3,1), new Position(3,3));
        board.movePiece(new Position(4,6), new Position(4,4));
        board.movePiece(new Position(4,0), new Position(3,1));
        board.movePiece(new Position(3,6), new Position(3,5));
        var valid = board.getValidPlaces(new Position(3,1));
        assertTrue(valid.contains(new Position(4,0)));
        assertTrue(valid.contains(new Position(2,2)));
        assertTrue(valid.contains(new Position(3,2)));
        assertTrue(valid.contains(new Position(4,2)));
        assertEquals(4, valid.size());
    }

    @Test
    void moveSE(){
        board.movePiece(new Position(3,1), new Position(3,3));
        board.movePiece(new Position(4,6), new Position(4,4));
        var valid = board.getValidPlaces(new Position(4,0));
        assertTrue(valid.contains(new Position(3,1)));
        assertEquals(1, valid.size());
    }

}