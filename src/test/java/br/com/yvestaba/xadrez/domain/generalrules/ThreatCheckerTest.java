package br.com.yvestaba.xadrez.domain.generalrules;

import br.com.yvestaba.xadrez.domain.GameStatus;
import br.com.yvestaba.xadrez.domain.Position;
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
        piecesMover.movePiece(new Position(4,6), new Position(4,4));
        piecesMover.movePiece(new Position(5,1), new Position(5,2));
        piecesMover.movePiece(new Position(3,7), new Position(7, 3));
        assertEquals(GameStatus.CHECKMATE, piecesMover.getStatus());
    }

}