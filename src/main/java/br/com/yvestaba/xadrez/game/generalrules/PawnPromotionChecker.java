package br.com.yvestaba.xadrez.game.generalrules;

import br.com.yvestaba.xadrez.game.Color;
import br.com.yvestaba.xadrez.game.Position;
import br.com.yvestaba.xadrez.game.exceptions.PawnPromotionException;
import br.com.yvestaba.xadrez.game.pieces.Pawn;
import br.com.yvestaba.xadrez.game.pieces.Piece;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static br.com.yvestaba.xadrez.game.Color.WHITE;

class PawnPromotionChecker implements MoveChecker{

    private Position from;
    private Position to;
    private Board board;

    public void movePiece(Position from, Position to, Board b) {
        Color turnOwner = b.getTurnOwner();
        if(!linePawnPromotion(turnOwner, to.getLin())){
            return;
        }
        if(b.getPiece(to) instanceof Pawn){
            this.from = from;
            this.to = to;
            this.board = b;
            throw new PawnPromotionException();
        }
    }

    public void promotePawn(Class<Piece> type){
        try {
            Piece pawn = board.getPiece(this.to);
            pawn = type.getConstructor(Color.class).newInstance(pawn.getColor());
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Turn already changed when this method is called
     * @param turnOwner
     * @param lin
     * @return
     */
    private boolean linePawnPromotion(Color turnOwner, int lin) {
        return turnOwner == WHITE ? lin == 0 : lin == 7;
    }

    public Position getFrom() {
        return from;
    }

    public Position getTo() {
        return to;
    }

    public Board getBoard() {
        return board;
    }
}
