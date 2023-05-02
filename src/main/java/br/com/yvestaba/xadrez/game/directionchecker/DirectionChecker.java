package br.com.yvestaba.xadrez.game.directionchecker;

import br.com.yvestaba.xadrez.game.Direction;
import br.com.yvestaba.xadrez.game.Position;
import br.com.yvestaba.xadrez.game.generalrules.Board;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

public abstract class DirectionChecker {

    private final DirectionChecker next;
    private List<Direction> directions = new ArrayList<>();
    protected Position threatPosition;

    protected DirectionChecker(DirectionChecker next) {
        this.next = next;
    }

    public DirectionChecker setDirectionAndPosition(int col, int lin, Board board){
        Direction direction = getDirection(col, lin, board);
        if(nonNull(direction))
            directions.add(direction);
        if(nonNull(next)){
            next.directions = this.directions;
            next.threatPosition = this.threatPosition;
            return next.setDirectionAndPosition(col, lin, board);
        }
        return this;
    }

    protected abstract Direction getDirection(int col, int lin, Board board);

    public Position getThreatPosition() {
        return threatPosition;
    }

    public List<Direction> getDirections() {
        return directions;
    }

    public Direction getTheOnlyDirection(){
        if(directions.size() != 1){
            throw new RuntimeException("Cannot call getTheOnlyDirection if directions != 1");
        }
        return directions.get(0);
    }

    public boolean hasManyDirections(){
        return this.directions.size() > 1;
    }

    public boolean hasNoDirections(){
        return this.directions.isEmpty();
    }
}
