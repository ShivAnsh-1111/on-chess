package com.onchess.entity;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String boardState;
    private String playerTurn;
    private String status;
    // getters and setters
}
