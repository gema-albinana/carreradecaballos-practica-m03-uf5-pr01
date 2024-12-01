package com.lasalle.horseracinggui.model.table;

import com.lasalle.horseracinggui.model.cards.Card;

/**
 * Represents a bot player in the betting game.
 * This class extends the Player class and provides specific
 * implementations for bot players.
 */
public class Bot extends Player {

    /**
     * Constructs a Bot player with the specified player number,
     * betting amount, and the horse being bet on.
     *
     * @param numPlayer the unique identifier for the player.
     * @param betAmount the amount of tokens the bot is betting.
     * @param bettingHorse the Card object representing the horse the bot is betting on.
     */
    public Bot(int numPlayer, int betAmount, Card bettingHorse) {
        super(numPlayer, betAmount, bettingHorse);
    }

    /**
     * Retrieves a description of the bot player, including the player number,
     * the horse they have bet on, and the amount of tokens wagered.
     *
     * @return a String detailing the bot player's information and bet.
     */
    @Override
    public String getDescriptions() {
        return "Bot player " + getNumPlayer() + " has bet on the horse " +
                this.bettingHorse.getDescription() +
                ", with an amount of " + this.betAmount + " tokens.";
    }
}
