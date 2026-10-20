package com.example.chess.model;

import lombok.Getter;

import java.util.List;

@Getter
public abstract class Piece {

    private final Player player;
    private Position position;

    Piece(Player player, Position position){
        this.player = player;
        this.position = position;
    }

    public abstract List<Position> moveOptions(Board board);

    public Position move(Position to) {
         this.position = to;
         return this.position;
    }


}
