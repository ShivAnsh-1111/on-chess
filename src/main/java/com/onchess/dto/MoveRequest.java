package com.onchess.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MoveRequest {
    private Long gameId;
    private String move;
    private String player;
    // getters and setters
}
