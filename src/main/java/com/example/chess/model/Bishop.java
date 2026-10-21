package com.example.chess.model;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece{

    private static final int[][] DIRECTIONS = {
            {1, 1}, {-1, -1}, {1, -1}, {-1, 1},
    };

    public Bishop(Player player){
        super(player);
    }

    @Override
    public List<Position> moveOptions(Board board, Position currentPos) {
        return getSlidingMoves(board, currentPos, DIRECTIONS);
    }

}
