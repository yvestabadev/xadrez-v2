package br.com.yvestaba.xadrez.game.pieces;

import br.com.yvestaba.xadrez.game.generalrules.Board;
import br.com.yvestaba.xadrez.game.Color;
import br.com.yvestaba.xadrez.game.Position;

import java.util.HashSet;
import java.util.Set;

import static br.com.yvestaba.xadrez.game.Color.WHITE;
import static br.com.yvestaba.xadrez.utils.ChessCommonUtils.validateColLin;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class Pawn extends Piece{

    public Pawn(Color color){
        super(color);
    }
    @Override
    protected Set<Position> getPositions(Board board, Position position) {
        var ret = new HashSet<Position>();
        var pos = new Position(position.getCol(), position.getLin() + getLineDirectionByColor(1));
        addIfDoesNotExistOnBoard(ret, board, pos);
        if(position.getLin() == getInitialLineByColor() && ret.size() == 1){
            addIfDoesNotExistOnBoard(ret, board, new Position(position.getCol(),
                    position.getLin() + getLineDirectionByColor(2)));
        }
        getCapturable(board, position, ret);
        return ret;
    }

    @Override
    public Set<Position> threat(Board board, Position position) {
        var ret = new HashSet<Position>();
        int plusOneColumn = position.getCol() + 1;
        final int lin = position.getLin() + getLineDirectionByColor(1);
        if(validateColLin(plusOneColumn, lin)) {
            Position pos = new Position(plusOneColumn, lin);
            ret.add(pos);
        }
        int minusOneColumn = position.getCol() - 1;
        if(validateColLin(minusOneColumn, lin)) {
            Position pos = new Position(minusOneColumn, lin);
            ret.add(pos);
        }
        return ret;
    }

    private void getCapturable(Board board, Position position, Set<Position> positions){
        int plusOneColumn = position.getCol() + 1;
        final int lin = position.getLin() + getLineDirectionByColor(1);
        if(validateColLin(plusOneColumn, lin)) {
            Position pos = new Position(plusOneColumn, lin);
            Piece piece = board.getPiece(pos);
            if (nonNull(piece) && piece.getColor() != this.getColor()) {
                positions.add(pos);
            }
        }
        int minusOneColumn = position.getCol() - 1;
        if(validateColLin(minusOneColumn, lin)) {
            Position pos = new Position(minusOneColumn, lin);
            Piece piece = board.getPiece(pos);
            if (nonNull(piece) && piece.getColor() != this.getColor()) {
                positions.add(pos);
            }
        }
    }

    private int getLineDirectionByColor(int amount){
        if(this.getColor() == WHITE){
            return amount;
        }
        return amount * -1;
    }

    private int getInitialLineByColor(){
        if(this.getColor() == WHITE){
            return 1;
        }
        return 6;
    }

    /**
     *   If space has no piece, add as possible move
     */
    private void addIfDoesNotExistOnBoard(Set<Position> positions, Board board, Position position){
        Piece piece = board.getPiece(position);
        if(isNull(piece)){
            positions.add(position);
        }
    }
}
