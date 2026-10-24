package com.example.chess.model.pieces;

import com.example.chess.model.Board;
import com.example.chess.model.Player;
import com.example.chess.model.Position;

import java.util.List;

public class Queen extends Piece {

    private static final int[][] DIRECTIONS = {
            {1, 1}, {-1, -1}, {1, -1}, {-1, 1},
            {1, 0}, {-1, 0}, {0, 1}, {0, -1}
    };

    public Queen(Player player){
        super(player, PieceType.QUEEN);
    }

    @Override
    public List<Position> moveOptions(Board board, Position currentPos) {
       return getSlidingMoves(board, currentPos, DIRECTIONS);
    }


}
