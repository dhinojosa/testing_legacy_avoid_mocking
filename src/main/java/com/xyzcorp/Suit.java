package com.xyzcorp;

public enum Suit {
    CLUBS("♣"),
    HEARTS("♥"),
    DIAMONDS("♦"),
    SPADES("♠");

    private final String symbol;

    Suit(String symbol) {
        this.symbol = symbol;
    }

    boolean isRed() {
        return "♥♦".contains(symbol);
    }
}
