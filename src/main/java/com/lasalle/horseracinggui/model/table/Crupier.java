package com.lasalle.horseracinggui.model.table;

import com.lasalle.horseracinggui.model.cards.Card;
import com.lasalle.horseracinggui.model.cards.CardsDeck;
import com.lasalle.horseracinggui.model.raceboard.Board;
import com.lasalle.horseracinggui.model.raceboard.Lane;
import com.lasalle.horseracinggui.model.raceboard.Summary;

import java.util.ArrayList;

/**
 * Represents the dealer (Crupier) in the betting game.
 * This class manages the game state, handles the players,
 * and controls the flow of the game rounds.
 */
public class Crupier {

    CardsDeck cardsDeck;          // Deck of cards used for the game
    Board boardGame;              // Game board for displaying the state of the game
    ArrayList<Player> players = new ArrayList<>(); // List of players in the game
    int round = 0;                // Current round number
    int profit = 0;               // Total profit from bets
    Card card;                    // Current card drawn
    Lane winnerLane = null;       // Lane that has the winning horse

    /**
     * Constructs a Crupier with the specified deck of cards and game board.
     *
     * @param cardsDeck the CardsDeck object representing the deck of cards.
     * @param boardGame the Board object representing the game board.
     */
    public Crupier(CardsDeck cardsDeck, Board boardGame) {
        this.cardsDeck = cardsDeck;
        this.boardGame = boardGame;
    }

    /**
     * Finds the index of the player who has bet on the specified horse.
     *
     * @param players the list of players to check against.
     * @param horseToCheck the Card representing the horse to check for.
     * @return the 1-based index of the player who has the horse, or -1 if not found.
     */
    private int findPlayerWithHorse(ArrayList<Player> players, Card horseToCheck) {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getBettingHorse().equals(horseToCheck)) {
                return i + 1;
            }
        }
        return -1;
    }

    /**
     * Draws the four knight cards from the deck and places them on the game board.
     *
     * @return an ArrayList of Card objects representing the horses placed on the board.
     */
    public ArrayList<Card> setHorsesOnBoard() {
        // We draw the four knights from the deck of cards.
        ArrayList<Card> horses = new ArrayList<>();
        horses.add(cardsDeck.getSpecificCard(CardsDeck.KNIGHT_GOLD));
        horses.add(cardsDeck.getSpecificCard(CardsDeck.KNIGHT_CUPS));
        horses.add(cardsDeck.getSpecificCard(CardsDeck.KNIGHT_CLUBS));
        horses.add(cardsDeck.getSpecificCard(CardsDeck.KNIGHT_SWORDS));

        // Each knight is placed on a lane of the game board
        boardGame.addCardInLane(horses.get(0));
        boardGame.addCardInLane(horses.get(1));
        boardGame.addCardInLane(horses.get(2));
        boardGame.addCardInLane(horses.get(3));
        return horses;
    }

    /**
     * Starts a new round of the game, draws a card, updates the board,
     * and determines the winning lane if applicable.
     *
     * @return a Summary object containing the results of the round, or null if no winner.
     */
    public Summary startNewRound() {
        round++;
        card = cardsDeck.getCardFromDeck();

        // If empty cards deck
        if (card == null) {
            cardsDeck.shuffleDeck();
            card = cardsDeck.getCardFromDeck();
        }

        // The card is passed so that it can be shown in the current round on the board.
        boardGame.setDealerCard(card);

        int laneToMove = boardGame.getIndexLaneForSuit(card.getSuit()) + 1;

        // If retreat round
        if (round % 5 == 0) {
            boardGame.getLane(laneToMove).retreatPosition();
        } else {
            boardGame.getLane(laneToMove).advancePosition();
        }

        winnerLane = boardGame.getLaneWinner();
        if (winnerLane != null) {
            return new Summary(winnerLane.getCardDescription(), profit, round,
                    findPlayerWithHorse(players, winnerLane.getCard()), players);
        }
        return null;
    }

    /**
     * Retrieves the current state of the game board as a String.
     *
     * @return a String representation of the updated game board.
     */
    public String getBoardGame() {
        return boardGame.getUpdatedBoard(round);
    }

    /**
     * Initializes the game with the specified players and calculates the total profit.
     *
     * @param players an ArrayList of Player objects representing the players in the game.
     */
    public void initializeGame(ArrayList<Player> players) {
        this.players = players;
        for (Player player : players) {
            profit += player.getBetAmount();
        }
    }
}
