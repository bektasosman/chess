package com.example.chess.model.pieces;

import com.example.chess.model.Board;
import com.example.chess.model.Player;
import com.example.chess.model.Position;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class Piece {

    private final Player player;
    private final PieceType pieceType;

    public Piece(Player player, PieceType pieceType) {
        this.player = player;
        this.pieceType = pieceType;
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

    protected List<Position> getStepMoves(Board board, Position currentPos, int[][] directions) {
        List<Position> moves = new ArrayList<>();
        for (int[] dir : directions) {
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


    public abstract List<Position> moveOptions(Board board, Position currentPos);


}