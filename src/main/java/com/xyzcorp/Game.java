package com.xyzcorp;

import org.fusesource.jansi.Ansi;

import java.util.Scanner;
import java.util.stream.Collectors;

import static org.fusesource.jansi.Ansi.ansi;

/**
 * Source: Ted Young
 * <a href="https://moretestable.com/">MoreTestable.com</a>
 */
public class Game {

    private final Deck deck;

    private final Hand playerHand;
    private final Hand dealerHand;

    private final Wallet wallet;

    public Game() {
        this(new Deck(), new Hand(), new Hand(), new Wallet());
    }

    public Game(Deck deck, Hand playerHand, Hand dealerHand) {
        this.deck = deck;
        this.playerHand = playerHand;
        this.dealerHand = dealerHand;
        this.wallet = new Wallet();
    }

    public Game(Deck deck, Hand playerHand, Hand dealerHand, Wallet wallet) {
        this.deck = deck;
        this.playerHand = playerHand;
        this.dealerHand = dealerHand;
        this.wallet = wallet;
    }

    public static void main(String[] args) {
        Game game = new Game();

        System.out.println(ansi()
            .bgBright(Ansi.Color.WHITE)
            .eraseScreen()
            .cursor(1, 1)
            .fgGreen().a("Welcome to")
            .fgRed().a(" Jitterted's")
            .fgBlack().a(" BlackJack"));


        game.initialDeal();
        game.play();

        System.out.println(ansi().reset());
    }

    public void initialDeal() {

        // deal first round of cards, players first
        playerHand.drawFrom(deck);
        dealerHand.drawFrom(deck);

        // deal next round of cards
        playerHand.drawFrom(deck);
        dealerHand.drawFrom(deck);
    }

    public void play() {
        // get Player's decision: hit until they stand, then they're done (or
        // they go bust)
        while (!playerHand.isBust()) {
            displayGameState();
            String playerChoice = inputFromPlayer().toLowerCase();
            if (playerChoice.startsWith("s")) {
                break;
            }
            if (playerChoice.startsWith("h")) {
                playerHand.drawFrom(deck);
            } else {
                System.out.println("You need to [H]it or [S]tand");
            }
        }

        // Dealer makes its choice automatically based on a simple heuristic
        // (<=16, hit, 17>=stand)
        if (!playerHand.isBust()) {
            while (dealerHand.underLimit()) {
                dealerHand.drawFrom(deck);
            }
        }

        displayFinalGameState();

        if (playerHand.isBust()) {
            System.out.println("You Busted, so you lose.  💸");
        } else if (dealerHand.isBust()) {
            System.out.println("Dealer went BUST, Player wins! Yay for you!! " +
                "💵");
        } else if (dealerHand.losesTo(playerHand)) {
            System.out.println("You beat the Dealer! 💵");
        } else if (dealerHand.pushes(playerHand)) {
            System.out.println("Push: You tie with the Dealer. 💸");
        } else {
            System.out.println("You lost to the Dealer. 💸");
        }
    }

    private String inputFromPlayer() {
        System.out.println("[H]it or [S]tand?");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private void displayGameState() {
        System.out.print(ansi().eraseScreen().cursor(1, 1));
        System.out.println("Dealer has: ");
        System.out.println(dealerHand.topCard().display()); //
        // first card is Face Up

        // second card is the hole card, which is hidden
        displayBackOfCard();

        System.out.println();
        System.out.println("Player has: ");
        displayHand(playerHand);
        System.out.println(" (" + playerHand.cardinalValue() + ")");
    }

    private void displayBackOfCard() {
        System.out.print(
            ansi()
                .cursorUp(7)
                .cursorRight(12)
                .a("┌─────────┐").cursorDown(1).cursorLeft(11)
                .a("│░░░░░░░░░│").cursorDown(1).cursorLeft(11)
                .a("│░ J I T ░│").cursorDown(1).cursorLeft(11)
                .a("│░ T E R ░│").cursorDown(1).cursorLeft(11)
                .a("│░ T E D ░│").cursorDown(1).cursorLeft(11)
                .a("│░░░░░░░░░│").cursorDown(1).cursorLeft(11)
                .a("└─────────┘"));
    }


    private void displayHand(Hand hand) {
        System.out.println(hand.getCardList().stream()
                               .map(Card::display)
                               .collect(Collectors.joining(
                                   ansi().cursorUp(6).cursorRight(1).toString())));
    }

    private void displayFinalGameState() {
        System.out.print(ansi().eraseScreen().cursor(1, 1));
        System.out.println("Dealer has: ");
        displayHand(dealerHand);
        System.out.println(" (" + dealerHand.cardinalValue() + ")");

        System.out.println();
        System.out.println("Player has: ");
        displayHand(playerHand);
        System.out.println(" (" + playerHand.cardinalValue() + ")");
    }
}
