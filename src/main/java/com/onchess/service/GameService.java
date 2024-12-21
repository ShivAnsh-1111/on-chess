package com.onchess.service;

import com.onchess.dto.GameDto;
import com.onchess.dto.GameRequest;
import com.onchess.dto.MoveRequest;
import com.onchess.entity.Game;
import com.onchess.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public Game startGame(GameRequest gameRequest) {
        Optional<Game> game = gameRepository.findTopByOrderByIdDesc();
        Long id = 0L;

        if(game.isPresent()) {
            id = game.get().toGameDto().getId();
        }

        GameDto gameDto = new GameDto(id,"initial board state", "white", "ongoing" );
        return gameRepository.save(gameDto.toGame());
    }

    public Game makeMove(MoveRequest moveRequest) {
        Game game = gameRepository.findById(moveRequest.getGameId())
                .orElseThrow(() -> new RuntimeException("Game not found"));

        GameDto gameDto = game.toGameDto();
        if (!gameDto.getStatus().equals("ongoing")) {
            throw new RuntimeException("Game is not ongoing");
        }

        if (!gameDto.getPlayerTurn().equals(moveRequest.getPlayer())) {
            throw new RuntimeException("It's not your turn");
        }

        // Validate and apply the move
        String updatedBoardState = applyMove(gameDto.getBoardState(), moveRequest.getMove());
        gameDto.setBoardState(updatedBoardState);

        // Switch the turn
        gameDto.setPlayerTurn(gameDto.getPlayerTurn().equals("white") ? "black" : "white");

        // Check if the game is over (e.g., checkmate, stalemate, etc.)
        if (isGameOver(updatedBoardState)) {
            gameDto.setStatus("finished");
        }

        return gameRepository.save(game);
    }

    private String applyMove(String boardState, String move) {
        // Add logic to validate and update the board state with the new move
        return boardState; // Replace with actual updated state
    }

    private boolean isGameOver(String boardState) {
        // Add logic to check for checkmate, stalemate, etc.
        return false; // Replace with actual game over condition
    }}
