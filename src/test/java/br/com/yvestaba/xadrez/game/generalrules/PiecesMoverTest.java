package br.com.yvestaba.xadrez.game.generalrules;

import br.com.yvestaba.xadrez.utils.PlayReader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PiecesMoverTest {

    @Test
    void immortalMatch(){
        PlayReader.play("1.e4 e5 2.f4 exf4 3.Bc4 Qh4+ 4.Kf1 b5 5.Bxb5 Nf6 6.Nf3 Qh6 7.d3 Nh5 8.Nh4 Qg5 9.Nf5 c6 10.g4 Nf6 11.Rg1 cxb5 12.h4 Qg6 13.h5 Qg5 14.Qf3 Ng8 15.Bxf4 Qf6 16.Nc3 Bc5 17.Nd5 Qxb2 18.Bd6 Bxg1 19.e5 Qxa1+ 20.Ke2 Na6 21.Nxg7+ Kd8 22.Qf6+ Nxf6 23.Be7#");
    }

    @Test
    void anotherPlayWithSmallRock(){
        PlayReader.play("1.e4 e5 2.Nf3 Nc6 3.Bc4 Bc5 4.c3 Qe7 5.O-O d6 6.d4 Bb6 7.Bg5 f6 8.Bh4 g5 9.Nxg5 fxg5 10.Qh5+ Kd7 11.Bxg5 Qg7 12.Be6+ Kxe6 13.Qe8+ Nge7 14.d5#");
    }

}