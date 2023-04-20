package br.com.yvestaba.xadrez.domain.generalrules;

import br.com.yvestaba.xadrez.domain.Position;

import java.util.HashSet;
import java.util.Set;

class KingMoveValidator {

    static Set<Position> validate(Set<Position> valid, ThreatChecker threatChecker){
        valid.removeAll(threatChecker.getThreatenArea());
        return valid;
    }
}
