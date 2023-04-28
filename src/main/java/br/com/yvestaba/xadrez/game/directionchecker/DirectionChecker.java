package br.com.yvestaba.xadrez.game.directionchecker;

import br.com.yvestaba.xadrez.game.Direction;
import br.com.yvestaba.xadrez.game.Position;
import br.com.yvestaba.xadrez.game.generalrules.Board;

import static java.util.Objects.nonNull;

public abstract class DirectionChecker {

    private final DirectionChecker next;
    private Direction direction;
    protected Position threatPosition;

    protected DirectionChecker(DirectionChecker next) {
        this.next = next;
        if(nonNull(next)) this.direction = next.direction;
    }

    public DirectionChecker setDirectionAndPosition(int col, int lin, Board board){
        Direction direction = getDirection(col, lin, board);
        if((nonNull(direction) && nonNull(this.direction))){
            this.direction = Direction.MANY;
            return this;
        }
        this.direction = nonNull(direction) ? direction : this.direction;
        if(nonNull(next)){
            next.direction = nonNull(direction) ? direction : this.direction;
            next.threatPosition = this.threatPosition;
            return next.setDirectionAndPosition(col, lin, board);
        }
        return this;
    }

    protected abstract Direction getDirection(int col, int lin, Board board);

    public Position getThreatPosition() {
        return threatPosition;
    }

    public Direction getDirection() {
        return direction;
    }
}
