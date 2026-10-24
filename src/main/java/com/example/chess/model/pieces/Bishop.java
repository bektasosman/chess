package com.example.chess.model.pieces;

import com.example.chess.model.Board;
import com.example.chess.model.Player;
import com.example.chess.model.Position;

import java.util.List;

public class Bishop extends Piece {

    private static final int[][] DIRECTIONS = {
            {1, 1}, {-1, -1}, {1, -1}, {-1, 1},
    };

    public Bishop(Player player){
        super(player, PieceType.BISHOP);
    }

    @Override
    public List<Position> moveOptions(Board board, Position currentPos) {
        return getSlidingMoves(board, currentPos, DIRECTIONS);
    }

}
