package com.lasalle.horseracinggui.model.table;

import com.lasalle.horseracinggui.model.cards.Card;

public abstract class Player {
    protected int betAmount;
    protected Card bettingHorse;
    private int numPlayer;

    public Player(int numPlayer, int betAmount, Card bettingHorse) {
        this.betAmount = betAmount;
        this.bettingHorse = bettingHorse;
        this.numPlayer = numPlayer;
    }

    abstract public String getDescriptions();
    public int getNumPlayer() {
        return this.numPlayer;
    }
    public int getBetAmount(){
        return betAmount;
    }
    public Card getBettingHorse() {
        return this.bettingHorse;
    }
}
