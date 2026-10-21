package com.example.chess.model;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece{

    private static final int[][] DIRECTIONS = {
            {1, 1}, {-1, -1}, {1, -1}, {-1, 1},
            {1, 0}, {0, 1}, {-1, 0}, {0, -1},

    };

    public King(Player player){
        super(player);
    }

    @Override
    public List<Position> moveOptions(Board board, Position currentPos) {
        List<Position> moves = new ArrayList<>();

        for (int[] dir : DIRECTIONS) {
            int dRow = dir[0];
            int dCol = dir[1];

            int newRow = currentPos.row() + dRow;
            int newCol = currentPos.col() + dCol;
            if (board.isWithinBoard(newRow, newCol)) {
                Piece pieceAtTarget = board.getPiece(newRow, newCol);
                if (pieceAtTarget == null) {
                    moves.add(new Position(newRow, newCol));
                } else {
                    if (pieceAtTarget.getPlayer() != this.getPlayer()) {
                        moves.add(new Position(newRow, newCol));
                    }
                }
            }
        }
        return moves;

    }

}
