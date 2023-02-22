package com.xyzcorp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hand {
    private final List<Card> cardList = new ArrayList<Card>();

    public Hand(Card... cards) {
        cardList.addAll(Arrays.asList(cards));
    }

    public List<Card> getCardList() {
        return cardList;
    }

    public Hand() {
    }

    void drawFrom(Deck deck) {
        cardList.add(deck.draw());
    }

    Card topCard() {
        return cardList.get(0);
    }

    public int cardinalValue() {
        int handValue = cardList
            .stream()
            .mapToInt(c -> c.rank().value())
            .sum();

        // does the hand contain at least 1 Ace?
        boolean hasAce = cardList
            .stream()
            .anyMatch(card -> card.rank().value() == 1);

        // if the total hand value <= 11, then count the Ace as 11 by adding 10
        if (hasAce && handValue < 11) {
            handValue += 10;
        }

        return handValue;
    }

    boolean isBust() {
        return cardinalValue() > 21;
    }

    boolean pushes(Hand other) {
        return cardinalValue() == other.cardinalValue();
    }

    boolean losesTo(Hand other) {
        return cardinalValue() < other.cardinalValue();
    }

    boolean underLimit() {
        return cardinalValue() <= 16;
    }
}
