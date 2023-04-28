package br.com.yvestaba.xadrez.utils;

import br.com.yvestaba.xadrez.game.Color;
import br.com.yvestaba.xadrez.game.GameStatus;
import br.com.yvestaba.xadrez.game.generalrules.PiecesMover;
import org.junit.jupiter.api.Assertions;

public class PlayReader {

    public static void play(String list){
        final PiecesMover mover = new PiecesMover();
        var divideTurns = list.split("\\.");
        for(int i = 1; i < divideTurns.length; i++){
            var turn = divideTurns[i];
            var dividedPlays = turn.split(" ");
            var whitePlay = dividedPlays[0];
            var expectedStatus = expectedStatus(whitePlay);
            whitePlay = whitePlay.replaceAll("[#+]","");
            play(whitePlay, Color.WHITE, mover);
            Assertions.assertEquals(expectedStatus, mover.getStatus());
            if(dividedPlays.length == 1){
                break;
            }
            var blackPlay = dividedPlays[1];
            expectedStatus = expectedStatus(blackPlay);
            blackPlay = blackPlay.replaceAll("[#+]","");
            play(blackPlay, Color.BLACK, mover);
            Assertions.assertEquals(expectedStatus, mover.getStatus());
        }
    }

    private static GameStatus expectedStatus(String play){
        if(play.contains("+")){
            return GameStatus.CHECK;
        }
        if(play.contains("#")){
            return GameStatus.CHECKMATE;
        }
        return GameStatus.NONE;
    }

    private static void play(String play, Color color, PiecesMover mover) {
        new PawnReader(new RockReader(new OthersReader(null))).readAllAndPlay(play, color, mover);
    }




}
