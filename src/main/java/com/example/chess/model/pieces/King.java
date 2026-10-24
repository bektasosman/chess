package com.example.chess.model.pieces;

import com.example.chess.model.*;

import java.util.List;

public class King extends Piece {

    private static final int[][] DIRECTIONS = {
            {1, 1}, {-1, -1}, {1, -1}, {-1, 1},
            {1, 0}, {0, 1}, {-1, 0}, {0, -1},

    };

    public King(Player player){
        super(player, PieceType.KING);
    }

    @Override
    public List<Position> moveOptions(Board board, Position currentPos) {
        return getStepMoves(board, currentPos, DIRECTIONS);
    }

}
