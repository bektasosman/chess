package com.example.chess.model;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece{

    private static final int[][] DIRECTIONS = {
            {1, 1}, {-1, -1}, {1, -1}, {-1, 1},
            {1, 0}, {-1, 0}, {0, 1}, {0, -1}
    };

    public Queen(Player player){
        super(player);
    }

    @Override
    public List<Position> moveOptions(Board board, Position currentPos) {
       return getSlidingMoves(board, currentPos, DIRECTIONS);
    }


}
