package br.com.yvestaba.xadrez.utils;

import br.com.yvestaba.xadrez.game.Direction;
import br.com.yvestaba.xadrez.game.Position;

import java.util.HashSet;
import java.util.Set;

public class DirectionUtils {

    private DirectionUtils(){

    }

    public static Set<Position> getPath(Position from, Position to, Direction direction){
        Set<Position> ret = new HashSet<>();
        switch(direction){
            case N:
                for(int i = from.getLin() + 1; i < to.getLin(); i++){
                    ret.add(new Position(from.getCol(), i));
                }
                break;
            case S:
                for(int i = from.getLin() - 1; i > to.getLin(); i--){
                    ret.add(new Position(from.getCol(), i));
                }
                break;
            case E:
                for(int i = from.getCol() + 1; i < to.getCol(); i++){
                    ret.add(new Position(i, from.getLin()));
                }
                break;
            case W:
                for(int i = from.getCol() - 1; i > to.getCol(); i--){
                    ret.add(new Position(i, from.getLin()));
                }
                break;
            case NE:
                int col = from.getCol() + 1;
                int lin = from.getLin() + 1;
                while(col <= to.getCol() && lin <= to.getLin()){
                    ret.add(new Position(col, lin));
                    col++;
                    lin++;
                }
                break;
            case SE:
                int colSE = from.getCol() + 1;
                int linSE = from.getLin() - 1;
                while(colSE <= to.getCol() && linSE >= to.getLin()){
                    ret.add(new Position(colSE, linSE));
                    colSE++;
                    linSE--;
                }
                break;
            case NW:
                int colNW = from.getCol() - 1;
                int linNW = from.getLin() + 1;
                while(colNW >= to.getCol() && linNW <= to.getLin()){
                    ret.add(new Position(colNW, linNW));
                    colNW--;
                    linNW++;
                }
                break;
            case SW:
                int colSW = from.getCol() - 1;
                int linSW = from.getLin() - 1;
                while(colSW >= to.getCol() && linSW >= to.getLin()){
                    ret.add(new Position(colSW, linSW));
                    colSW--;
                    linSW--;
                }
        }
        return ret;
    }
}
