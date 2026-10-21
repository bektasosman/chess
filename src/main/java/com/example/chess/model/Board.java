package com.example.chess.model;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private final Piece[][] field;

    public Board() {
        this.field = new Piece[8][8];
        initializeBoard();
    }

    private void initializeBoard() {
        // Bauern
        for (int col = 0; col < 8; col++) {
            setPiece(new Pawn(Player.WHITE), 1, col);
            setPiece(new Pawn(Player.BLACK), 6, col);
        }

        // Grundreihen
        setupBackRank(Player.WHITE, 0);
        setupBackRank(Player.BLACK, 7);
    }

    private void setupBackRank(Player player, int row) {
        setPiece(new Rook(player), row, 0);
        setPiece(new Knight(player), row, 1);
        setPiece(new Bishop(player), row, 2);
        setPiece(new Queen(player), row, 3);
        setPiece(new King(player), row, 4);
        setPiece(new Bishop(player), row, 5);
        setPiece(new Knight(player), row, 6);
        setPiece(new Rook(player), row, 7);
    }

    public void setPiece(Piece piece, int row, int col) {
        this.field[row][col] = piece;
    }

    public Piece getPiece(int row, int col) {
        return this.field[row][col];
    }

    public Piece getPiece(Position pos) {
        return this.field[pos.row()][pos.col()];
    }

    // Saubere Zugausführung ohne Nebeneffekte auf Piece
    public void movePiece(Position from, Position to) {
        Piece piece = getPiece(from);
        if (piece == null) {
            throw new IllegalArgumentException("Keine Figur auf " + from);
        }

        field[to.row()][to.col()] = piece;
        field[from.row()][from.col()] = null;
    }

    public boolean isWithinBoard(int row, int col) {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }

    /**
     * Liefert alle WIRKLICH erlaubten Züge für eine Figur auf der Startposition.
     */
    public List<Position> getLegalMoves(Position from) {
        Piece piece = getPiece(from);
        if (piece == null) {
            return List.of();
        }

        // 1. Alle geometrisch denkbaren Züge holen
        List<Position> pseudoMoves = piece.moveOptions(this, from);
        List<Position> legalMoves = new ArrayList<>();

        // 2. Jeden Zug kurz simulieren
        for (Position to : pseudoMoves) {
            // Zug ausführen und eventuell geschlagene Figur merken
            Piece capturedPiece = field[to.row()][to.col()];
            field[to.row()][to.col()] = piece;
            field[from.row()][from.col()] = null;

            // Prüfen: Steht mein eigener König nach diesem Zug im Schach?
            boolean inCheck = isKingInCheck(piece.getPlayer());

            // 3. Brett exakt zurücksetzen (Undo)
            field[from.row()][from.col()] = piece;
            field[to.row()][to.col()] = capturedPiece;

            // War der König sicher? Dann ist der Zug legal!
            if (!inCheck) {
                legalMoves.add(to);
            }
        }

        return legalMoves;
    }

    // 1. Hilfsmethode: Findet die Position des Königs einer Farbe
    public Position findKing(Player player) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = field[row][col];
                if (piece instanceof King && piece.getPlayer() == player) {
                    return new Position(row, col);
                }
            }
        }
        throw new IllegalStateException("König von " + player + " nicht gefunden!");
    }

    // 2. Prüft, ob der König des übergebenen Spielers bedroht wird
    public boolean isKingInCheck(Player player) {
        Position kingPos = findKing(player);

        // Laufe über alle 64 Felder
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = field[row][col];

                // Nur gegnerische Figuren prüfen
                if (piece != null && piece.getPlayer() != player) {
                    List<Position> enemyMoves = piece.moveOptions(this, new Position(row, col));

                    // Kann der Gegner das Feld des Königs schlagen?
                    if (enemyMoves.contains(kingPos)) {
                        return true; // König steht im Schach!
                    }
                }
            }
        }

        return false; // König steht sicher
    }
}