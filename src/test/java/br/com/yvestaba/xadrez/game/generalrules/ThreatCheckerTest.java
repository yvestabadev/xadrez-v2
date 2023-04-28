package br.com.yvestaba.xadrez.game.generalrules;

import br.com.yvestaba.xadrez.game.GameStatus;
import br.com.yvestaba.xadrez.game.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ThreatCheckerTest {

    private PiecesMover piecesMover;

    @BeforeEach
    void setUp(){
        this.piecesMover = new PiecesMover();
    }

    @Test
    void checkMate(){
        piecesMover.movePiece(new Position(6, 1), new Position(6,3));
        assertEquals(GameStatus.NONE, piecesMover.getStatus());
        piecesMover.movePiece(new Position(4,6), new Position(4,4));
        assertEquals(GameStatus.NONE, piecesMover.getStatus());
        piecesMover.movePiece(new Position(5,1), new Position(5,2));
        assertEquals(GameStatus.NONE, piecesMover.getStatus());
        piecesMover.movePiece(new Position(3,7), new Position(7, 3));
        assertEquals(GameStatus.CHECKMATE, piecesMover.getStatus());
    }

    @Test
    void check(){
        piecesMover.movePiece(new Position(5, 1), new Position(5,3));
        assertEquals(GameStatus.NONE, piecesMover.getStatus());
        piecesMover.movePiece(new Position(4,6), new Position(4,4));
        assertEquals(GameStatus.NONE, piecesMover.getStatus());
        piecesMover.movePiece(new Position(4,1), new Position(4,2));
        assertEquals(GameStatus.NONE, piecesMover.getStatus());
        piecesMover.movePiece(new Position(3,7), new Position(7, 3));
        assertEquals(GameStatus.CHECK, piecesMover.getStatus());
    }

}