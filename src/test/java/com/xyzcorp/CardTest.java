package com.xyzcorp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.fusesource.jansi.Ansi.ansi;

/**
 * Source: Ted Young
 * <a href="https://moretestable.com/">MoreTestable.com</a>
 */
class CardTest {

  @Test
  public void withNumberCardHasNumericValueOfTheNumber() throws Exception {
    Card card = new Card(Suit.HEARTS, Rank.SEVEN);

    assertThat(card.rank().value())
        .isEqualTo(7);
  }

  @Test
  public void withValueOfQueenHasNumericValueOf10() throws Exception {
    Card card = new Card(Suit.HEARTS, Rank.QUEEN);

    assertThat(card.rank().value())
        .isEqualTo(10);
  }

  @Test
  public void withAceHasNumericValueOf1() throws Exception {
    Card card = new Card(Suit.HEARTS, Rank.ACE);

    assertThat(card.rank().value())
        .isEqualTo(1);
  }

  @Test
  public void suitOfHeartsOrDiamondsIsDisplayedInRed() throws Exception {
    // given a card with Hearts or Diamonds
    Card heartsCard = new Card(Suit.HEARTS, Rank.TEN);
    Card diamondsCard = new Card(Suit.HEARTS, Rank.EIGHT);

    // when we ask for its display representation
    String ansiRedString = ansi().fgRed().toString();

    // then we expect a red color ansi sequence
    assertThat(heartsCard.display())
        .contains(ansiRedString);
    assertThat(diamondsCard.display())
        .contains(ansiRedString);
  }

}
