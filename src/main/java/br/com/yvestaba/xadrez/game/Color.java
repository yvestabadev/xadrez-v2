package br.com.yvestaba.xadrez.game;

public enum Color {
    BLACK, WHITE;

    public Color getEnemy(){
        return this == WHITE ? BLACK : WHITE;
    }

    public boolean isEnemy(Color color){
        return this != color;
    }
}
