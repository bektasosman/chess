package com.example.chess.dto;

import com.example.chess.model.Board;
import com.example.chess.model.GameState;
import com.example.chess.model.Player;
import com.example.chess.model.pieces.Piece;

import java.util.List;

public record GameStateResponse(
        Board board,
        Player currentTurn,
        GameState status,
        List<Piece> capturedPieces
) {
}
