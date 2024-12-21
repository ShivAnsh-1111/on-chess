package com.onchess.controller;

//#### Service 2: Game Service
//        Handles game logic and player interactions.

import com.onchess.dto.GameRequest;
import com.onchess.dto.MoveRequest;
import com.onchess.entity.Game;
import com.onchess.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping("/start")
    public ResponseEntity<?> startGame(@RequestBody GameRequest gameRequest) {
        Game game = gameService.startGame(gameRequest);
        return ResponseEntity.ok(game);
    }

    @PostMapping("/move")
    public ResponseEntity<?> makeMove(@RequestBody MoveRequest moveRequest) {
        Game updatedGame = gameService.makeMove(moveRequest);
        return ResponseEntity.ok(updatedGame);
    }
}
