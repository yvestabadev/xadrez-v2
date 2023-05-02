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

    //https://www.chessgames.com/perl/chessgame?gid=2490427
    @Test
    void borisVsMaksim(){
        PlayReader.play("1.e4 c5 2.Nc3 d6 3.d4 cxd4 4.Qxd4 Nf6 5.Be3 Nc6 6.Qd2 e6 7.h3 d5 8.exd5 exd5 9.O-O-O Be6 10.Bb5 Bb4 11.Nge2 Qa5 12.Bd4 Ne4 13.Qe3 O-O 14.Bxc6 bxc6 15.Nxe4 dxe4 16.a3 Be7 17.Qxe4 Rfe8 18.Nf4 Bg5 19.Bc3 Qc5 20.Qf3 Qc4 21.Rd4 Qa2 22.Rhd1 c5 23.Rd6 Rad8 24.b3 Rxd6 25.Rxd6 Qxa3+ 26.Bb2 Qb4 27.g3 Bxh3 28.Bc3 Qa3+ 29.Kb1 c4 30.Qc6 Rc8 31.Qa6 Qxa6 32.Rxa6 Bxf4 33.gxf4 cxb3 34.Kb2 bxc2 35.Kxc2 Rc7 36.Ra5 f6 37.f5 Bf1 38.Rd5 h5 39.Kd2 h4 40.Rd8+ Kh7 41.Bb4 Rc4");
    }

    //https://www.chessgames.com/perl/chessgame?gid=1259987
    @Test
    void oldestChessRecordEver(){
        PlayReader.play("1.e4 d5 2.exd5 Qxd5 3.Nc3 Qd8 4.Bc4 Nf6 5.Nf3 Bg4 6.h3 Bxf3 7.Qxf3 e6 8.Qxb7 Nbd7 9.Nb5 Rc8 10.Nxa7 Nb6 11.Nxc8 Nxc8 12.d4 Nd6 13.Bb5+ Nxb5 14.Qxb5+ Nd7 15.d5 exd5 16.Be3 Bd6 17.Rd1 Qf6 18.Rxd5 Qg6 19.Bf4 Bxf4 20.Qxd7+ Kf8 21.Qd8#");
    }

    //https://www.chessgames.com/perl/chessgame?gid=1581433
    @Test
    void pedroDamianoVsNN(){
        PlayReader.play("1.e4 e5 2.Nf3 f6 3.Nxe5 fxe5 4.Qh5+ Ke7 5.Qxe5+ Kf7 6.Bc4+ d5 7.Bxd5+ Kg6 8.f4 h6 9.f5+ Kh7 10.Bf7 Ne7 11.Qg3 Nbc6 12.Qg6+ Nxg6 13.fxg6#");
    }

    //https://www.chessgames.com/perl/chessgame?gid=2055543
    @Test
    void alexeiVsVincent(){
        PlayReader.play("1.c4 e6 2.Nc3 d5 3.cxd5 exd5 4.d4 c6 5.Bf4 Bd6 6.Bg3 Nf6 7.e3 Bf5 8.Bd3 Bxd3 9.Qxd3 O-O 10.Nge2 Re8 11.O-O Nh5 12.Bxd6 Qxd6 13.Rab1 a5 14.Ng3 Nxg3 15.hxg3 Nd7 16.Ne2 Nf6 17.Qa3 Qd7 18.f3 h5 19.Rbe1 Qf5 20.Nf4 g6 21.Qd3 Qg5 22.Kf2 Nh7 23.Rh1 Re7 24.a4 Qf6 25.Rh4 Ng5 26.Reh1 Rae8 27.Re1 Ne6 28.Nxe6 Rxe6 29.Re2 Qe7 30.Rh1 Qb4 31.b3 Qa3 32.Qc3 b6 33.Rhe1 Qd6 34.e4 dxe4 35.Rxe4 h4 36.gxh4 Rxe4 37.Rxe4 Rxe4 38.fxe4 Qf4+ 39.Kg1 Qxe4 40.g3 Kf8 41.Kf2 Qd5 42.g4 Qd6 43.Kg2 Qf6 44.g5 Qe6 45.Kg3 Ke8 46.Qf3 Qe1+ 47.Kh3 Qe6+ 48.Kg2 Kd7 49.Kf2 Kd6 50.Qg3+ Kd5");
    }


}