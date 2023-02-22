package com.xyzcorp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Source: Ted Young
 * <a href="https://moretestable.com/">MoreTestable.com</a>
 */
public class HandValueAceTest {

    @Test
    public void handWithOneAceTwoCardsIsValuedAt11() throws Exception {
        Hand hand = new Hand(new Card(Suit.HEARTS, Rank.ACE),
            new Card(Suit.HEARTS, Rank.FIVE));
        assertThat(hand.cardinalValue())
            .isEqualTo(11 + 5);
    }

    @Test
    public void handWithOneAceAndOtherCardsEqualTo11IsValuedAt1() throws Exception {
        Hand hand = new Hand(new Card(Suit.HEARTS, Rank.ACE),
            new Card(Suit.HEARTS, Rank.EIGHT),
            new Card(Suit.HEARTS, Rank.THREE));

        assertThat(hand.cardinalValue())
            .isEqualTo(1 + 8 + 3);
    }
}
