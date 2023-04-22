package br.com.yvestaba.xadrez.domain.pieces;

import br.com.yvestaba.xadrez.domain.generalrules.Board;
import br.com.yvestaba.xadrez.domain.Position;
import br.com.yvestaba.xadrez.domain.generalrules.PiecesMover;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {

    private PiecesMover board;
    @BeforeEach
    void setUp() {
        this.board = new PiecesMover();
    }

    @Test
    void initialPosition(){
        var valid = board.validMoves(new Position(1, 0));
        assertTrue(valid.contains(new Position(0,2)));
        assertTrue(valid.contains(new Position(2,2)));
        assertEquals(2, valid.size());
    }

    @Test
    void canCapture(){
        board.movePiece(new Position(1,0), new Position(2,2));
        board.movePiece(new Position(3,6), new Position(3,4));
        var valid = board.validMoves(new Position(2,2));
        assertTrue(valid.contains(new Position(3,4)));
        assertEquals(5, valid.size());
    }
}