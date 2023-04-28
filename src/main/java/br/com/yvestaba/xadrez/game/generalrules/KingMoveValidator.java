package br.com.yvestaba.xadrez.game.generalrules;

import br.com.yvestaba.xadrez.game.Position;

import java.util.Set;

class KingMoveValidator {

    static Set<Position> validate(Set<Position> valid, ThreatChecker threatChecker){
        valid.removeAll(threatChecker.getThreatenArea());
        return valid;
    }
}
