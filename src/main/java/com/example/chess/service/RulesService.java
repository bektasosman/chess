package com.example.chess.service;

import com.example.chess.model.Board;
import com.example.chess.model.Player;
import com.example.chess.model.Position;
import com.example.chess.model.pieces.King;
import com.example.chess.model.pieces.Piece;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class RulesService {

    public List<Position> getLegalMoves(Board board, Position from) {
        Piece piece = board.getPiece(from);
        if (piece == null) {
            return List.of();
        }
        List<Position> pseudoMoves = piece.moveOptions(board, from);
        List<Position> legalMoves = new ArrayList<>();
        for (Position to : pseudoMoves) {
            // Zug simulieren
            Piece capturedPiece = board.getPiece(to);
            board.setPiece(piece, to.row(), to.col());
            board.setPiece(null, from.row(), from.col());
            boolean inCheck = isKingInCheck(board, piece.getPlayer());
            // Undo (Brett zurücksetzen)
            board.setPiece(piece, from.row(), from.col());
            board.setPiece(capturedPiece, to.row(), to.col());
            if (!inCheck) {
                legalMoves.add(to);
            }
        }

        return legalMoves;
    }

    public Position findKing(Board board, Player player) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = board.getPiece(row, col);
                if (piece instanceof King && piece.getPlayer() == player) {
                    return new Position(row, col);
                }
            }
        }
        throw new IllegalStateException("König von " + player + " nicht gefunden!");
    }

    public boolean isKingInCheck(Board board, Player player) {
        Position kingPos = findKing(board, player);
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = board.getPiece(row, col);

                if (piece != null && piece.getPlayer() != player) {
                    List<Position> enemyMoves = piece.moveOptions(board, new Position(row, col));
                    if (enemyMoves.contains(kingPos)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isStalemate(Board board, Player player) {
        return !isKingInCheck(board, player) && !hasAnyLegalMoves(board, player);
    }

    public boolean isCheckmate(Board board, Player player) {
        return (isKingInCheck(board, player) && !hasAnyLegalMoves(board, player));
    }

    public boolean hasAnyLegalMoves(Board board, Player player) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = board.getPiece(row, col);
                if (piece != null && piece.getPlayer() == player) {
                    if (!getLegalMoves(board, new Position(row, col)).isEmpty()) {
                        return true; // Mindestens ein legaler Zug gefunden!
                    }
                }
            }
        }
        return false;
    }
}
