package com.xyzcorp;

public enum Rank {
    ACE("A"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    JACK("J"),
    QUEEN("Q"),
    KING("K");

    private final String symbol;

    Rank(String symbol) {
        this.symbol = symbol;
    }

    public int value() {
        if ("JQK".contains(symbol)) {
            return 10;
        } else if (symbol.equals("A")) {
            return 1;
        } else {
            return Integer.parseInt(symbol);
        }
    }
}
