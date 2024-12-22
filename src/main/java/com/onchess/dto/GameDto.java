package com.onchess.dto;

import com.onchess.entity.Game;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GameDto {

    private Long id;
    private String boardState;
    private String playerTurn;
    private String status;

    public Game toGame(){
//        return Game.builder()
//                .id(this.id)
//                .status(this.status)
//                .playerTurn(this.playerTurn)
//                .boardState(this.boardState)
//                .build();
    	
    	return new Game();
    }
}
