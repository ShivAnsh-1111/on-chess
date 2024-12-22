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
        // Parse the board state into a structure (e.g., 2D array or object representation)
        String[][] board = parseBoardState(boardState);

        // Extract move details (e.g., source and destination coordinates)
        String[] moveDetails = move.split("-");
        String source = moveDetails[0];
        String destination = moveDetails[1];

        // Validate move legality based on the chess rules (e.g., piece movement, no obstruction)
        if (!isValidMove(board, source, destination)) {
            throw new RuntimeException("Invalid move");
        }

        // Update board state with the new move
        updateBoardState(board, source, destination);

        // Serialize the updated board back to a string representation
        return serializeBoardState(board);
    }

    private boolean isGameOver(String boardState) {
        // Parse the board state into a structure (e.g., 2D array or object representation)
        String[][] board = parseBoardState(boardState);

        // Check for checkmate, stalemate, or insufficient material
        boolean isCheckmate = checkForCheckmate(board);
        boolean isStalemate = checkForStalemate(board);

        return isCheckmate || isStalemate;
    }

    private String[][] parseBoardState(String boardState) {
        // Implement parsing logic
        return new String[8][8]; // Placeholder
    }

    private String serializeBoardState(String[][] board) {
        // Implement serialization logic
        return ""; // Placeholder
    }

    private boolean isValidMove(String[][] board, String source, String destination) {
        // Implement move validation logic based on chess rules
        return true; // Placeholder
    }

    private void updateBoardState(String[][] board, String source, String destination) {
        // Implement logic to update the board state
    }

    private boolean checkForCheckmate(String[][] board) {
        // Implement checkmate detection logic
        return false; // Placeholder
    }

    private boolean checkForStalemate(String[][] board) {
        // Implement stalemate detection logic
        return false; // Placeholder
    }

}
