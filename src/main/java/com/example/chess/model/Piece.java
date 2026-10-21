package com.example.chess.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class Piece {

    private final Player player;

    public Piece(Player player) {
        this.player = player;
    }

    protected List<Position> getSlidingMoves(Board board, Position currentPos, int[][] directions) {
        List<Position> moves = new ArrayList<>();

        for (int[] dir : directions) {
            int dRow = dir[0];
            int dCol = dir[1];

            int newRow = currentPos.row() + dRow;
            int newCol = currentPos.col() + dCol;

            while (board.isWithinBoard(newRow, newCol)) {
                Piece pieceAtTarget = board.getPiece(newRow, newCol);

                if (pieceAtTarget == null) {
                    moves.add(new Position(newRow, newCol));
                } else {
                    if (pieceAtTarget.getPlayer() != this.getPlayer()) {
                        moves.add(new Position(newRow, newCol));
                    }
                    break;
                }

                newRow += dRow;
                newCol += dCol;
            }
        }
        return moves;
    }

    public abstract List<Position> moveOptions(Board board, Position currentPos);
}