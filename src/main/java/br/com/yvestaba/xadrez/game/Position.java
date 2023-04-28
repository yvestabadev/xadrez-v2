package br.com.yvestaba.xadrez.game;

import java.util.Objects;

public class Position {

    private int col;
    private int lin;

    public Position(int col, int lin){
        if(col > 7 || col < 0 || lin > 7|| lin < 0){
            throw new RuntimeException("invalid position. col: " + col + "; lin: " + lin);
        }
        this.col = col;
        this.lin = lin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return col == position.col && lin == position.lin;
    }

    @Override
    public int hashCode() {
        return Objects.hash(col, lin);
    }

    public int getCol() {
        return col;
    }

    public int getLin() {
        return lin;
    }
}
