package br.com.yvestaba.xadrez.utils;

import br.com.yvestaba.xadrez.game.Color;
import br.com.yvestaba.xadrez.game.Position;
import br.com.yvestaba.xadrez.game.generalrules.PiecesMover;
import br.com.yvestaba.xadrez.game.pieces.*;
import br.com.yvestaba.xadrez.utils.finder.PiecesFinder;

public class OthersReader extends Reader {
    public OthersReader(Reader next) {
        super(next);
    }

    @Override
    protected boolean read(String play, Color color, PiecesMover mover) {
        try {
            switch (play.charAt(0)) {
                case 'R':
                    PiecesFinder finder = () -> Tower.class;
                    finder.move(play, color, mover);
                    return true;
                case 'N':
                    PiecesFinder finderHorse = () -> Horse.class;
                    finderHorse.move(play, color, mover);
                    return true;
                case 'B':
                    PiecesFinder finderBishop = () -> Bishop.class;
                    finderBishop.move(play, color, mover);
                    return true;
                case 'Q':
                    PiecesFinder finderQueen = () -> Queen.class;
                    finderQueen.move(play, color, mover);
                    return true;
                case 'K':
                    PiecesFinder finderKing = () -> King.class;
                    finderKing.move(play, color, mover);
                    return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        throw new RuntimeException("Unexpected Error");
    }
}
