package br.com.yvestaba.xadrez.game.generalrules;

import br.com.yvestaba.xadrez.game.Color;
import br.com.yvestaba.xadrez.game.Position;
import br.com.yvestaba.xadrez.game.pieces.Pawn;

class EnPassantChecker implements MoveChecker{

    private Position validEnPassant;

    public void movePiece(Position from, Position to, Board board) {
        validEnPassant = null;
        var piece = board.getPiece(to);
        if(piece instanceof Pawn){
            if(from.getLin() - to.getLin() != 2 && to.getLin() - from.getLin() != 2){
                return;
            }
            if(initialLine(piece.getColor(), from.getLin())){
                validEnPassant = new Position(from.getCol(), (from.getLin() + to.getLin()) / 2);
            }
        }
    }

    private boolean initialLine(Color color, int lin) {
        switch (color){
            case WHITE:
                return lin == 1;
            case BLACK:
                return lin == 6;
        }
        throw new RuntimeException("Unexpected error");
    }

    public Position getValidEnPassant() {
        return validEnPassant;
    }
}
