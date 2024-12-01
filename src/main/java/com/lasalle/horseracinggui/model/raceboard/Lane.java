package com.lasalle.horseracinggui.model.raceboard;

import com.lasalle.horseracinggui.model.cards.Card;
import com.lasalle.horseracinggui.model.cards.CardSuit;

/**
 * Represents a lane on the race board, associated with a card.
 * Each lane tracks its position in the race and the card it contains.
 */
public class Lane {

    private Card card; // The card associated with this lane
    private int position; // The current position of the lane in the race
    private final int START_POSITION = 0; // Initial position of the lane

    /**
     * Constructs a Lane object with the specified card.
     * The lane starts at the initial position.
     *
     * @param card the card to be associated with this lane.
     */
    public Lane(Card card) {
        this.card = card;
        this.position = START_POSITION;
    }

    /**
     * Retrieves the current position of the lane.
     *
     * @return the position of the lane in the race.
     */
    public int getPosition() {
        return position;
    }

    /**
     * Retrieves the suit of the card associated with this lane.
     *
     * @return the suit of the card in this lane.
     */
    public CardSuit getCardSuit() {
        return card.getSuit();
    }

    /**
     * Retrieves the description of the card associated with this lane.
     *
     * @return the description of the card in this lane.
     */
    public String getCardDescription() {
        return this.card.getDescription();
    }

    /**
     * Retrieves the card associated with this lane.
     *
     * @return the card in this lane.
     */
    public Card getCard() {
        return this.card;
    }

    /**
     * Advances the position of the lane by one unit.
     */
    public void advancePosition() {
        this.position++;
    }

    /**
     * Retreats the position of the lane by one unit if it's greater than 1.
     */
    public void retreatPosition() {
        if (this.position > 1) {
            this.position--;
        }
    }
}
