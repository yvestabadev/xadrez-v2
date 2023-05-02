package br.com.yvestaba.xadrez.game.generalrules;

import br.com.yvestaba.xadrez.game.Color;
import br.com.yvestaba.xadrez.game.GameStatus;
import br.com.yvestaba.xadrez.game.Position;
import br.com.yvestaba.xadrez.game.pieces.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static br.com.yvestaba.xadrez.game.Color.BLACK;
import static br.com.yvestaba.xadrez.game.Color.WHITE;

public class Board implements MoveChecker, MoveValidator {

    private Map<Position, Piece> board = new HashMap<>();
    private Color turnOwner = WHITE;
    private GameStatus status;
    private final EnPassantValidator enPassantValidator;

    private Board(EnPassantValidator enPassantValidator){
        this.enPassantValidator = enPassantValidator;
    }

    public Set<Position> getValidPlaces(Position from, Board b, Set<Position> valid){
        return board.get(from).getValidPlaces(this, from);
    }

    public void movePiece(Position from, Position to, Board b){
        var piece = board.get(from);
        if(turnOwner != piece.getColor()){
            throw new RuntimeException("It's other's turn");
        }
        board.remove(from);
        board.put(to, piece);
        turnOwner = turnOwner.getEnemy();
    }

    void moveTowerRock(Position from, Position to){
        Piece piece = board.get(from);
        board.remove(from);
        board.put(to, piece);
    }

    public Piece getPiece(Position position){
        return board.get(position);
    }

    static Board startGame(EnPassantValidator enPassantValidator){
        var board  = new Board(enPassantValidator);
        board.board.put(new Position(0,0), new Tower(WHITE));
        board.board.put(new Position(1,0), new Horse(WHITE));
        board.board.put(new Position(2,0), new Bishop(WHITE));
        board.board.put(new Position(3,0), new Queen(WHITE));
        board.board.put(new Position(4,0), new King(WHITE));
        board.board.put(new Position(5,0), new Bishop(WHITE));
        board.board.put(new Position(6,0), new Horse(WHITE));
        board.board.put(new Position(7,0), new Tower(WHITE));
        for(int i = 0; i < 8; i++){
            board.board.put(new Position(i, 1), new Pawn(WHITE));
            board.board.put(new Position(i, 6), new Pawn(BLACK));
        }
        board.board.put(new Position(0, 7), new Tower(BLACK));
        board.board.put(new Position(1, 7), new Horse(BLACK));
        board.board.put(new Position(2,7), new Bishop(BLACK));
        board.board.put(new Position(3,7), new Queen(BLACK));
        board.board.put(new Position(4,7), new King(BLACK));
        board.board.put(new Position(5,7), new Bishop(BLACK));
        board.board.put(new Position(6,7), new Horse(BLACK));
        board.board.put(new Position(7,7), new Tower(BLACK));

        return board;
    }

    public Color getTurnOwner() {
        return turnOwner;
    }

    Map<Position, Piece> getPiecesByColor(Color color){
        var ret = new HashMap<Position, Piece>();
        board.forEach((k, v) -> {
            if(v.getColor() == color){
                ret.put(k, v);
            }
        });
        return ret;
    }

    void setStatus(GameStatus status) {
        this.status = status;
    }

    void removePiece(Position position){
        board.remove(position);
    }

    GameStatus getStatus() {
        return status;
    }
}
