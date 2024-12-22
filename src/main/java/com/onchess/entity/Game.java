package com.onchess.entity;

import com.onchess.dto.GameDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.NoArgsConstructor;

//@Builder
@Entity
@NoArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String boardState;
    private String playerTurn;
    private String status;
    // getters and setters

    public GameDto toGameDto(){
        return new GameDto(this.id, this.boardState, this.playerTurn, this.status);
    }
}
