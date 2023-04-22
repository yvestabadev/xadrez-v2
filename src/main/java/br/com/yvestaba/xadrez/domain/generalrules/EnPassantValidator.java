package br.com.yvestaba.xadrez.domain.generalrules;

import br.com.yvestaba.xadrez.domain.Color;
import br.com.yvestaba.xadrez.domain.Position;
import br.com.yvestaba.xadrez.domain.pieces.Pawn;

import java.util.Set;

import static java.util.Objects.isNull;

class EnPassantValidator implements MoveValidator{

    private final EnPassantChecker enPassantChecker;

    EnPassantValidator(EnPassantChecker enPassantChecker){
        this.enPassantChecker = enPassantChecker;
    }
    @Override
    public Set<Position> getValidPlaces(Position from, Board board, Set<Position> valid) {
        var position = enPassantChecker.getValidEnPassant();
        if(isNull(position)){
            return valid;
        }
        var piece = board.getPiece(from);
        if(piece instanceof Pawn && from.getLin() == getLineValueByColor(piece.getColor()) &&
                    (from.getCol() - position.getCol() == 1 || position.getCol() - from.getCol() == 1)){
            valid.add(position);
        }
        return valid;
    }

    private int getLineValueByColor(Color color) {
        return color == Color.WHITE ? 4 : 3;
    }
}
