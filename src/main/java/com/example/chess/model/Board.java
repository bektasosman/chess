package com.example.chess.model;

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
}