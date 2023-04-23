package br.com.yvestaba.xadrez.domain;

public enum Color {
    BLACK, WHITE;

    public Color getEnemy(){
        return this == WHITE ? BLACK : WHITE;
    }
}
