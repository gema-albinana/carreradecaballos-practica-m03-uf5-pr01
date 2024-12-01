package com.lasalle.horseracinggui.model.cards;

/**
 * Represents a generic card in a card game.
 * This abstract class provides common properties and methods for all types of cards.
 */
public abstract class Card {

    protected CardSuit suit;  // The suit of the card
    protected float value;     // The value of the card

    /**
     * Retrieves the value of the card.
     *
     * @return the float value of the card.
     */
    public float getValue() {
        return this.value;
    }

    /**
     * Formats the card's number or face, suit, and value into a string representation.
     *
     * @param numberOrFace the number or face name of the card (e.g., "Ace", "10").
     * @return a formatted string representing the card.
     */
    protected String toString(String numberOrFace) {
        return String.format("%7s of %6s, value %.1f", numberOrFace, suit, value);
    }

    /**
     * Retrieves the unique code associated with the card.
     *
     * @return a String representing the card's unique code.
     */
    public abstract String getCardCode();

    /**
     * Retrieves a description of the card.
     *
     * @return a String providing details about the card.
     */
    public abstract String getDescription();

    /**
     * Retrieves the suit of the card.
     *
     * @return the CardSuit representing the suit of the card.
     */
    public CardSuit getSuit() {
        return this.suit;
    }
}
