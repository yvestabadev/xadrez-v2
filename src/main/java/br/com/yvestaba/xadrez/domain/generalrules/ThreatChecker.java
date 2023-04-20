package br.com.yvestaba.xadrez.domain.generalrules;

import br.com.yvestaba.xadrez.domain.Position;

import java.util.HashSet;
import java.util.Set;

class ThreatChecker implements MoveChecker{

    private Set<Position> threatenArea = new HashSet<>();

    public void movePiece(Position from, Position to, Board b) {
        var pieces = b.getPiecesByColor(b.getPiece(to).getColor());
        threatenArea = new HashSet<>();
        pieces.forEach((pos, piece) ->{
            threatenArea.addAll(piece.threat(b, pos));
        });
    }

    public Set<Position> getThreatenArea() {
        return threatenArea;
    }
}
