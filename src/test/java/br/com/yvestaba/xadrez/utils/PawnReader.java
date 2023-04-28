package br.com.yvestaba.xadrez.utils;

import br.com.yvestaba.xadrez.game.Color;
import br.com.yvestaba.xadrez.game.Position;
import br.com.yvestaba.xadrez.game.generalrules.PiecesMover;
import br.com.yvestaba.xadrez.game.pieces.Piece;

import static java.util.Objects.isNull;

public class PawnReader extends Reader{
    public PawnReader(Reader next) {
        super(next);
    }

    @Override
    protected boolean read(String play, Color color, PiecesMover mover) {
        if(Character.isLowerCase(play.charAt(0))){
            pawnMovement(play, color, mover);
            return true;
        }
        return false;
    }

    private void pawnMovement(String play, Color color, PiecesMover mover) {
        final int complement = color == Color.WHITE ? -1 : 1;
        if(play.length() == 2) {
            int destinyCol = getColByChar(play.charAt(0));
            int destinyLin = getLinByCharNumber(play.charAt(1));

            Piece piece = null;
            int originLin = destinyLin + complement;
            while(isNull(piece)){
                piece = mover.getPiece(new Position(destinyCol, originLin));
                if (isNull(piece)) {
                    originLin += complement;
                }
            }
            mover.movePiece(new Position(destinyCol, originLin), new Position(destinyCol, destinyLin));
        } else if (play.contains("x") && play.length() == 4) {
            int destinyCol = getColByChar(play.charAt(2));
            int destinyLin = getLinByCharNumber(play.charAt(3));
            int originCol = getColByChar(play.charAt(0));
            int originLin = destinyLin + complement;
            mover.movePiece(new Position(originCol, originLin), new Position(destinyCol, destinyLin));
        }
    }
}
