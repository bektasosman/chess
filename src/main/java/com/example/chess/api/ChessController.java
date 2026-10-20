package com.example.chess.api;

import com.example.chess.service.ChessService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChessController {

    private final ChessService chessService;

    @PostMapping("/api/games")
    public ResponseEntity<String> newGame(){
        String responseMessage = chessService.newGame();
        return ResponseEntity.ok(responseMessage);
    }



}
