package br.com.yvestaba.xadrez.utils.finder;

import br.com.yvestaba.xadrez.game.Color;
import br.com.yvestaba.xadrez.game.Position;
import br.com.yvestaba.xadrez.game.generalrules.PiecesMover;
import br.com.yvestaba.xadrez.game.pieces.Horse;
import br.com.yvestaba.xadrez.game.pieces.Piece;
import br.com.yvestaba.xadrez.game.pieces.Tower;

import java.lang.reflect.InvocationTargetException;

import static br.com.yvestaba.xadrez.utils.Reader.getColByChar;
import static br.com.yvestaba.xadrez.utils.Reader.getLinByCharNumber;
import static java.util.Objects.nonNull;

public interface PiecesFinder<T extends Piece> {

    default void move(String play, Color color, PiecesMover mover) throws Exception {
        int destinyCol = getColByChar(play.charAt(play.length() - 2));
        int destinyLin = getLinByCharNumber(play.charAt(play.length() - 1));
        var destinyPosition = new Position(destinyCol, destinyLin);
        Position originPosition;
        play = play.replace("x","");
        if(play.length() == 3) {
            originPosition = findOriginPosition(destinyCol, destinyLin, color, mover);
        } else{
            originPosition = findOriginPosition(color, mover, play.charAt(1));
        }
        mover.movePiece(originPosition, destinyPosition);
    }

    default Position findOriginPosition(Color color, PiecesMover mover, char origin){
        if(Character.isLowerCase(origin)) {
            return findByCol(getColByChar(origin), color, mover);
        }
            return findByLin(getLinByCharNumber(origin), color, mover);
    }

    default Position findOriginPosition(int destinyCol, int destinyLin, Color color, PiecesMover mover) throws Exception {
        var constructor = getType().getDeclaredConstructor(Color.class);
        Piece instance = constructor.newInstance(color);
        var positions = instance.threat(mover.getBoard(), new Position(destinyCol, destinyLin));
        for(var position : positions){
            Piece piece = mover.getPiece(position);
            if(nonNull(piece) && getType().isInstance(piece) && piece.getColor() == color){
                return position;
            }
        }
        throw new RuntimeException("Unexpected Error");
    }

    private Position findByCol(int col, Color color, PiecesMover mover){
        for(int i = 0; i < 8; i++){
            Position position = new Position(col, i);
            Piece piece = mover.getPiece(position);
            if(nonNull(piece) && getType().isInstance(piece) && piece.getColor() == color){
                return position;
            }
        }
        throw new RuntimeException("Unexpected Error");
    }

    private Position findByLin(int col, Color color, PiecesMover mover){
        for(int i = 0; i < 8; i++){
            Position position = new Position(i, col);
            Piece piece = mover.getPiece(position);
            if(nonNull(piece) && getType().isInstance(piece) && piece.getColor() == color){
                return position;
            }
        }
        throw new RuntimeException("Unexpected Error");
    }

    Class<T> getType();
}
