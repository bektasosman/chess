package com.example.chess.service;

import com.example.chess.dto.NewGameResponse;
import com.example.chess.model.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChessService {

    public NewGameResponse newGame() {
        Board board = new Board();
        return new NewGameResponse(board);
    }
}
