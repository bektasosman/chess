package com.example.chess.model;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    public Pawn(Player player) {
        super(player);
    }

    @Override
    public List<Position> moveOptions(Board board, Position currentPos) {
        List<Position> moves = new ArrayList<>();

        // Weiß zieht Reihe aufwärts (+1), Schwarz abwärts (-1)
        int direction = (getPlayer() == Player.WHITE) ? 1 : -1;
        int startRow = (getPlayer() == Player.WHITE) ? 1 : 6;

        int row = currentPos.row();
        int col = currentPos.col();

        // 1. Einfacher Schritt nach vorn (nur wenn das Zielfeld leer ist)
        int forwardRow = row + direction;
        if (board.isWithinBoard(forwardRow, col) && board.getPiece(forwardRow, col) == null) {
            moves.add(new Position(forwardRow, col));

            // 2. Doppelschritt (nur von der Startreihe und wenn beide Felder davor frei sind)
            int doubleForwardRow = row + 2 * direction;
            if (row == startRow && board.getPiece(doubleForwardRow, col) == null) {
                moves.add(new Position(doubleForwardRow, col));
            }
        }

        // 3. Diagonales Schlagen (links & rechts)
        int[] captureCols = {col - 1, col + 1};
        for (int capCol : captureCols) {
            if (board.isWithinBoard(forwardRow, capCol)) {
                Piece target = board.getPiece(forwardRow, capCol);
                // Schlagen ist nur erlaubt, wenn dort eine gegnerische Figur steht
                if (target != null && target.getPlayer() != this.getPlayer()) {
                    moves.add(new Position(forwardRow, capCol));
                }
            }
        }

        return moves;
    }


}