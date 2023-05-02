package br.com.yvestaba.xadrez.game.generalrules;

import br.com.yvestaba.xadrez.game.Position;

import java.util.HashSet;
import java.util.Set;

import static br.com.yvestaba.xadrez.utils.ChessCommonUtils.*;

public interface MoveCross {


    default Set<Position> getMovementsCross(Board board, Position position){
        var ret = new HashSet<Position>();
        fulfillE(ret, board, position);
        fulfillW(ret, board, position);
        fulfillS(ret, board, position);
        fulfillN(ret, board, position);
        return ret;
    }

    default Set<Position> getThreatenedPositionsCross(Board board, Position position){
        var ret = new HashSet<Position>();
        fulfillThreatE(ret, board, position);
        fulfillThreatW(ret, board, position);
        fulfillThreatS(ret, board, position);
        fulfillThreatN(ret, board, position);
        return ret;
    }

    private static void fulfillN(Set<Position> positions, Board board, Position position){
        int col = position.getCol();
        int lin = position.getLin() + 1;

        boolean willContinue = true;
        while(lin < 8 && willContinue){
            willContinue = addPositionIfvalid(positions, board, new Position(col, lin));
            lin++;
        }
    }

    private static void fulfillS(Set<Position> positions, Board board, Position position){
        int col = position.getCol();
        int lin = position.getLin() - 1;

        boolean willContinue = true;
        while(lin >= 0 && willContinue){
            willContinue = addPositionIfvalid(positions, board, new Position(col, lin));
            lin--;
        }
    }

    private static void fulfillE(Set<Position> positions, Board board, Position position){
        int col = position.getCol() + 1;
        int lin = position.getLin();

        boolean willContinue = true;
        while(col < 8 && willContinue){
            willContinue = addPositionIfvalid(positions, board, new Position(col, lin));
            col++;
        }
    }

    private static void fulfillW(Set<Position> positions, Board board, Position position){
        int col = position.getCol() - 1;
        int lin = position.getLin();

        boolean willContinue = true;
        while(col >= 0 && willContinue){
            willContinue = addPositionIfvalid(positions, board, new Position(col, lin));
            col--;
        }
    }

    private static void fulfillThreatN(Set<Position> positions, Board board, Position position){
        int col = position.getCol();
        int lin = position.getLin() + 1;

        boolean willContinue = true;
        while(lin < 8 && willContinue){
            willContinue = addThreatAndGetPiece(positions, board, col, lin);
            lin++;
        }
    }

    private static void fulfillThreatS(Set<Position> positions, Board board, Position position){
        int col = position.getCol();
        int lin = position.getLin() - 1;

        boolean willContinue = true;
        while(lin >= 0 && willContinue){
            willContinue = addThreatAndGetPiece(positions, board, col, lin);
            lin--;
        }
    }

    private static void fulfillThreatE(Set<Position> positions, Board board, Position position){
        int col = position.getCol() + 1;
        int lin = position.getLin();

        boolean willContinue = true;
        while(col < 8 && willContinue){
            willContinue = addThreatAndGetPiece(positions, board, col, lin);
            col++;
        }
    }

    private static void fulfillThreatW(Set<Position> positions, Board board, Position position){
        int col = position.getCol() - 1;
        int lin = position.getLin();

        boolean willContinue = true;
        while(col >= 0 && willContinue){
            willContinue = addThreatAndGetPiece(positions, board, col, lin);
            col--;
        }
    }
}
