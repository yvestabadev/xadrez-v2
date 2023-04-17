package br.com.yvestaba.xadrez.domain.pieces;

import br.com.yvestaba.xadrez.domain.Board;
import br.com.yvestaba.xadrez.domain.Color;
import br.com.yvestaba.xadrez.domain.Position;

import java.util.HashSet;
import java.util.Set;

import static br.com.yvestaba.xadrez.domain.Color.WHITE;
import static br.com.yvestaba.xadrez.utils.ChessCommonUtils.addIfDoesNotExistOnBoard;
import static java.util.Objects.nonNull;

public class Pawn extends Piece{

    public Pawn(Color color){
        super(color);
    }
    @Override
    protected Set<Position> getPositions(Board board, Position position) {
        var ret = new HashSet<Position>();
        var pos = new Position(position.col(), position.lin() + getLineDirectionByColor(1));
        addIfDoesNotExistOnBoard(ret, board, pos);
        if(position.lin() == getInitialLineByColor() && ret.size() == 1){
            addIfDoesNotExistOnBoard(ret, board, new Position(position.col(),
                    position.lin() + getLineDirectionByColor(2)));
        }
        getCapturable(board, position, ret);
        return ret;
    }

    private void getCapturable(Board board, Position position, Set<Position> positions){
        Position plusOneColumn = new Position(position.col() + 1,
                position.lin() + getLineDirectionByColor(1));
        Piece piece = board.getPiece(plusOneColumn);
        if(nonNull(piece) && piece.getColor() != this.getColor()){
            positions.add(plusOneColumn);
        }

        Position minusOneColumn = new Position(position.col() - 1,
                position.lin() + getLineDirectionByColor(1));
        piece = board.getPiece(minusOneColumn);
        if(nonNull(piece) && piece.getColor() != this.getColor()){
            positions.add(minusOneColumn);
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
}
