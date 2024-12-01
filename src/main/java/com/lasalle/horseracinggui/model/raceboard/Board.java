package com.lasalle.horseracinggui.model.raceboard;

import com.lasalle.horseracinggui.model.cards.Card;
import com.lasalle.horseracinggui.model.cards.CardSuit;

import java.util.ArrayList;

/**
 * Represents the game board used in the racing game.
 * It manages the lanes, the dealer's card, and the positions of the horses.
 */
public class Board {

    private final String ANSI_COLOR_GREEN = "\u001B[32m";
    private final String ANSI_COLOR_BLUE = "\u001B[34m";
    private final String ANSI_COLOR_GREY = "\u001B[37m";
    private final String ANSI_COLOR_RESET = "\u001B[0m";

    private final int MAX_ADVANCES_SPACES = 8; // Maximum spaces a horse can advance
    private final int MAX_LANES = 4; // Maximum number of lanes on the board
    private final int MIN_ADVANCES_SPACES = 5; // Minimum spaces a horse can advance
    private final int MIN_LANES = 1; // Minimum number of lanes on the board
    private final int FINISH_POSITION = 8; // Position representing the finish line
    private final int START_POSITION = 0; // Starting position on the board

    private int numAdvanceSpaces; // Number of spaces to advance for horses
    private int numLanes; // Number of lanes on the board
    private StringBuilder board; // StringBuilder for visual representation of the board

    private ArrayList<Lane> lanes; // List of lanes on the board
    private Card dealerCard; // Card held by the dealer

    /**
     * Constructor with parameters to initialize the board with a specific number of lanes and advance spaces.
     *
     * @param numLanes the number of lanes on the board.
     * @param numAdvanceSpaces the number of spaces for horses to advance.
     */
    public Board(int numLanes, int numAdvanceSpaces) {
        if (numLanes <= MAX_LANES && numLanes >= MIN_LANES)
            this.numLanes = numLanes;
        if (numAdvanceSpaces <= MAX_ADVANCES_SPACES && numAdvanceSpaces >= MIN_ADVANCES_SPACES)
            this.numAdvanceSpaces = numAdvanceSpaces;

        this.lanes = new ArrayList<>();
        this.board = new StringBuilder();
    }

    /**
     * Default constructor that initializes the board with maximum lanes and advance spaces.
     */
    public Board() {
        this.numLanes = MAX_LANES;
        this.numAdvanceSpaces = MAX_ADVANCES_SPACES;
        this.lanes = new ArrayList<>();
        this.board = new StringBuilder();
    }

    /**
     * Generates the updated representation of the board.
     *
     * @return a String containing the visual representation of the board.
     */
    public String getUpdatedBoard(int round) {
        generateBoard(round);
        return board.toString();
    }

    /**
     * Adds a player's card to a specified lane on the board.
     *
     * @param card the card to be added to the lane.
     */
    public void addCardInLane(Card card) {
        this.lanes.add(new Lane(card));
    }

    /**
     * Retrieves the specified lane for managing the forward and backward movement of horses.
     *
     * @param lane the index of the lane (1-based).
     * @return the Lane object at the specified index.
     */
    public Lane getLane(int lane) {
        return this.lanes.get(lane - 1);
    }

    /**
     * Sets the dealer's card to the specified card.
     *
     * @param dealerCard the card to be assigned to the dealer.
     */
    public void setDealerCard(Card dealerCard) {
        this.dealerCard = dealerCard;
    }

    /**
     * Retrieves the current card assigned to the dealer.
     *
     * @return the dealer's card.
     */
    private Card getDealerCard() {
        return this.dealerCard;
    }

    /**
     * Checks for a lane that has reached the finish position.
     *
     * @return the Lane object that has reached the finish position, or null if none have.
     */
    public Lane getLaneWinner() {
        for (Lane lane : lanes)
            if (lane.getPosition() == FINISH_POSITION)
                return lane;
        return null;
    }

    /**
     * Retrieves the index of the lane associated with the specified suit.
     *
     * @param suit the CardSuit to check.
     * @return the index of the lane containing the specified suit, or -1 if not found.
     */
    public int getIndexLaneForSuit(CardSuit suit) {
        for (int i = 0; i < lanes.size(); i++) {
            if (lanes.get(i).getCardSuit().equals(suit))
                return i;
        }
        return -1;
    }

    /**
     * Generates the visual representation of the board, including the positions of the horses and dealer's card.
     */
    private void generateBoard(int round) {
        String cardName = "";
        board.setLength(0); // Clear previous board

        // Advance Round o Retreat Round
        if (round % 5 == 0)
            board.append("  RETREAT ROUND :" + round +"\n");
        else
            board.append("  ADVANCE ROUND :" + round +"\n");

        board.append("  ");
        // Create lanes
        for (int j = 0; j < numLanes; j++)
            board.append(" ░░▒▒▓▓ Lane " + (j + 1) + "     ");

        board.append("\n");

        board.append("  ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\n");
        board.append("  Finish Line | The winner is ||\n");
        board.append("  ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\n  ");

        for (int i = numAdvanceSpaces - 1; i >= 0; i--) {
            if (i == 0)
                board.append("░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░ Start Line ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\n  ");

            for (int j = 0; j < numLanes; j++)
                board.append(ANSI_COLOR_GREY + "┌─────────────────┐" + ANSI_COLOR_RESET);

            // Top line box for Card Decks
            if (i == START_POSITION)
                board.append(ANSI_COLOR_GREEN + "┌─────────────────┐" + ANSI_COLOR_RESET);

            board.append("\n");
            for (int j = 0; j < numLanes; j++) {
                cardName = "";

                if (j == 0) board.append(i + " ");

                board.append(ANSI_COLOR_GREY + "│ " + ANSI_COLOR_RESET);

                if (lanes.get(j).getPosition() == i)
                    cardName = lanes.get(j).getCardDescription();
                board.append(ANSI_COLOR_BLUE + String.format("%-" + 16 + "s", cardName));
                board.append(ANSI_COLOR_GREY + "│" + ANSI_COLOR_RESET);
            }

            // Middle line box for Card Deck
            if (i == START_POSITION) {
                board.append(ANSI_COLOR_GREEN + "│ " + ANSI_COLOR_RESET);
                board.append(ANSI_COLOR_GREEN + String.format("%-" + 16 + "s", getDealerCard().getDescription()));
                board.append(ANSI_COLOR_GREEN + "│" + ANSI_COLOR_RESET);
            }

            board.append("\n  ");
            for (int j = 0; j < numLanes; j++)
                board.append(ANSI_COLOR_GREY + "└─────────────────┘" + ANSI_COLOR_RESET);

            // Bottom line box for Card Deck
            if (i == START_POSITION)
                board.append(ANSI_COLOR_GREEN + "└─────────────────┘" + ANSI_COLOR_RESET);

            board.append("\n  ");

            // Check for a winner and update board accordingly
            if (getLaneWinner() != null) {
                String boardWinner = board.toString().replace("||",
                        getLaneWinner().getCard().getDescription());
                board = new StringBuilder(boardWinner);
            }
        }
    }
}
