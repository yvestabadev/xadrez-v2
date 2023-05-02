package br.com.yvestaba.xadrez.game;

import br.com.yvestaba.xadrez.game.directionchecker.DirectionChecker;

public enum Direction {

    N,NE,E,SE,S,SW,W,NW,L,MANY;

    public Direction opposite() {
        switch (this){
            case N:
                return S;
            case S:
                return N;
            case E:
                return W;
            case W:
                return E;
            case NE:
                return SW;
            case SW:
                return NE;
            case NW:
                return SE;
            case SE:
                return NW;
        }
        return null;
    }
}
