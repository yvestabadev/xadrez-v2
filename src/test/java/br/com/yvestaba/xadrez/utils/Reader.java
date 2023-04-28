package br.com.yvestaba.xadrez.utils;

import br.com.yvestaba.xadrez.game.Color;
import br.com.yvestaba.xadrez.game.generalrules.PiecesMover;

import static java.util.Objects.isNull;

public abstract class Reader {

    protected Reader next;
    protected Boolean hasRead;

    public Reader(Reader next){
        this.next = next;
    }

    public void readAllAndPlay(String play, Color color, PiecesMover mover){
        boolean isRead = read(play, color, mover);
        Reader next = this.next;
        while (!isRead) {
            isRead = next.read(play, color, mover);
            next = next.next;
        }

    }

    protected abstract boolean read(String play, Color color, PiecesMover mover);

    public static int getLinByCharNumber(char ch) {
        return Integer.valueOf(String.valueOf(ch)) - 1;
    }

    public static int getColByChar(char ch) {
        return Character.getNumericValue(ch) - 10;
    }
}
