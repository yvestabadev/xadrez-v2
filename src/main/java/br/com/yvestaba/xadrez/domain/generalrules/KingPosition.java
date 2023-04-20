package br.com.yvestaba.xadrez.domain.generalrules;

import br.com.yvestaba.xadrez.domain.Color;
import br.com.yvestaba.xadrez.domain.Position;
import br.com.yvestaba.xadrez.domain.pieces.King;
import br.com.yvestaba.xadrez.domain.pieces.Piece;

import static br.com.yvestaba.xadrez.domain.Color.WHITE;

class KingPosition implements MoveChecker{

    private Position whitePosition = new Position(4, 0);
    private Position blackPosition = new Position(4, 7);

    public void movePiece(Position from, Position to, Board b) {
        Piece piece = b.getPiece(from);
        if (piece instanceof King) {
            if (piece.getColor() == WHITE) {
                whitePosition = to;
                return;
            }
            blackPosition = to;
        }
    }

    public Position getWhitePosition() {
        return whitePosition;
    }

    public Position getBlackPosition() {
        return blackPosition;
    }

    public Position getPositionByColor(Color color){
        if(color == WHITE){
            return whitePosition;
        }
        return blackPosition;
    }
}
