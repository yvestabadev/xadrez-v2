package br.com.yvestaba.xadrez.domain.generalrules;

import br.com.yvestaba.xadrez.domain.Color;
import br.com.yvestaba.xadrez.domain.Position;
import br.com.yvestaba.xadrez.domain.pieces.King;
import br.com.yvestaba.xadrez.domain.pieces.Piece;
import br.com.yvestaba.xadrez.domain.pieces.Tower;

import static br.com.yvestaba.xadrez.domain.Color.WHITE;

class RockChecker implements MoveChecker{

    private boolean whiteSmallRock = true;
    private boolean whiteBigRock = true;
    private boolean blackSmallRock = true;
    private boolean blackBigRock = true;

    public void movePiece(Position from, Position to, Board b) {
        Piece piece = b.getPiece(from);
        if (piece instanceof King) {
            if (piece.getColor() == WHITE) {
                whiteSmallRock = false;
                whiteBigRock = false;
                return;
            }
            blackSmallRock = false;
            blackBigRock = false;
            return;
        }

        if(piece instanceof Tower){
            verify(from, piece.getColor());
        }
    }

    private void verify(Position from, Color color){
        if(color == WHITE){
            if(from.getCol() == 0 && from.getLin() == 0){
                whiteBigRock = false;
                return;
            }
            whiteSmallRock = false;
            return;
        }

        if(from.getCol() == 0 && from.getLin() == 7){
            blackBigRock = false;
            return;
        }
        blackSmallRock = false;
    }

    public boolean smallRockByColor(Color color) {
        if(color == WHITE){
            return whiteSmallRock;
        }
        return blackSmallRock;
    }

    public boolean bigRockByColor(Color color) {
        if(color == WHITE){
            return whiteBigRock;
        }
        return blackBigRock;
    }

}
