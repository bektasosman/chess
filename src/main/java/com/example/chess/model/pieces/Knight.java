package com.example.chess.model.pieces;

import com.example.chess.model.*;

import java.util.List;

public class Knight extends Piece {

    private static final int[][] DIRECTIONS = {
            {2, 1}, {-2, -1}, {2, -1}, {-2, 1},
            {1, 2}, {-1, -2}, {1, -2}, {-1, 2},

    };

    public Knight(Player player){
        super(player, PieceType.KNIGHT);
    }

    @Override
    public List<Position> moveOptions(Board board, Position currentPos) {
        return getStepMoves(board, currentPos, DIRECTIONS);
    }

}
