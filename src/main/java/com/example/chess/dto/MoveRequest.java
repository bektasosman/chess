package com.example.chess.dto;

import com.example.chess.model.Position;

public record MoveRequest(Position from, Position to) {
}
