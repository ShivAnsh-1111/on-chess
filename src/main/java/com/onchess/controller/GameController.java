package com.onchess.controller;

//#### Service 2: Game Service
//        Handles game logic and player interactions.

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
