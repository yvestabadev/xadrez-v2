package br.com.yvestaba.xadrez.game.generalrules;

import br.com.yvestaba.xadrez.utils.PlayReader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PiecesMoverTest {

    //https://www.chessgames.com/perl/chessgame?gid=1018910
    @Test
    void immortalMatch(){
        PlayReader.play("1.e4 e5 2.f4 exf4 3.Bc4 Qh4+ 4.Kf1 b5 5.Bxb5 Nf6 6.Nf3 Qh6 7.d3 Nh5 8.Nh4 Qg5 9.Nf5 c6 10.g4 Nf6 11.Rg1 cxb5 12.h4 Qg6 13.h5 Qg5 14.Qf3 Ng8 15.Bxf4 Qf6 16.Nc3 Bc5 17.Nd5 Qxb2 18.Bd6 Bxg1 19.e5 Qxa1+ 20.Ke2 Na6 21.Nxg7+ Kd8 22.Qf6+ Nxf6 23.Be7#");
    }

    //https://www.chessgames.com/perl/chessgame?gid=1259664
    @Test
    void anotherPlayWithSmallRock(){
        PlayReader.play("1.e4 e5 2.Nf3 Nc6 3.Bc4 Bc5 4.c3 Qe7 5.O-O d6 6.d4 Bb6 7.Bg5 f6 8.Bh4 g5 9.Nxg5 fxg5 10.Qh5+ Kd7 11.Bxg5 Qg7 12.Be6+ Kxe6 13.Qe8+ Nge7 14.d5#");
    }

    @Test
    void borisVsMaksim(){
        PlayReader.play("1.e4 c5 2.Nc3 d6 3.d4 cxd4 4.Qxd4 Nf6 5.Be3 Nc6 6.Qd2 e6 7.h3 d5 8.exd5 exd5 9.O-O-O Be6 10.Bb5 Bb4 11.Nge2 Qa5 12.Bd4 Ne4 13.Qe3 O-O 14.Bxc6 bxc6 15.Nxe4 dxe4 16.a3 Be7 17.Qxe4 Rfe8 18.Nf4 Bg5 19.Bc3 Qc5 20.Qf3 Qc4 21.Rd4 Qa2 22.Rhd1 c5 23.Rd6 Rad8 24.b3 Rxd6 25.Rxd6 Qxa3+ 26.Bb2 Qb4 27.g3 Bxh3 28.Bc3 Qa3+ 29.Kb1 c4 30.Qc6 Rc8 31.Qa6 Qxa6 32.Rxa6 Bxf4 33.gxf4 cxb3 34.Kb2 bxc2 35.Kxc2 Rc7 36.Ra5 f6 37.f5 Bf1 38.Rd5 h5 39.Kd2 h4 40.Rd8+ Kh7 41.Bb4 Rc4");
    }

}