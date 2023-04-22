package br.com.yvestaba.xadrez.domain.generalrules;

import br.com.yvestaba.xadrez.domain.Color;
import br.com.yvestaba.xadrez.domain.Position;
import br.com.yvestaba.xadrez.domain.pieces.Pawn;

class EnPassantCaptor implements MoveChecker{

    private final EnPassantChecker enPassantChecker;

    EnPassantCaptor(EnPassantChecker enPassantChecker) {
        this.enPassantChecker = enPassantChecker;
    }

    public void movePiece(Position from, Position to, Board board) {
        var piece = board.getPiece(to);
        if(piece instanceof Pawn && to.equals(enPassantChecker.getValidEnPassant())){
            board.removePiece(new Position(to.getCol(), getLinCapturedPiece(to.getLin())));
        }
    }

    private int getLinCapturedPiece(int lin) {
        return lin == 2 ? 3 : 4;
    }

}
