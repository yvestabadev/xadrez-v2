package br.com.yvestaba.xadrez.utils;

import br.com.yvestaba.xadrez.game.Color;
import br.com.yvestaba.xadrez.game.Position;
import br.com.yvestaba.xadrez.game.generalrules.PiecesMover;

public class RockReader extends Reader{
    public RockReader(Reader next) {
        super(next);
    }

    @Override
    protected boolean read(String play, Color color, PiecesMover mover) {
        if(play.contains("O-O-O")){
            bigRockMovement(color, mover);
            return true;
        }
        if(play.contains("O-O")){
            smallRockMovement(color, mover);
            return true;
        }
        return false;
    }

    private void bigRockMovement(Color color, PiecesMover mover) {
        int col = 4;
        int lin = color == Color.WHITE ? 0 : 7;
        mover.movePiece(new Position(col, lin), new Position(2, lin));
    }

    private void smallRockMovement(Color color, PiecesMover mover) {
        int col = 4;
        int lin = color == Color.WHITE ? 0 : 7;
        mover.movePiece(new Position(col, lin), new Position(6, lin));
    }
}
