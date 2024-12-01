package com.lasalle.horseracinggui.model.raceboard;

import com.lasalle.horseracinggui.model.table.Player;

import java.util.ArrayList;

/**
 * Represents a summary of the game after a round has concluded.
 * This class holds information about the winner, total profit,
 * the number of rounds played, and details of the players involved.
 */
public class Summary {
    private String winner; // Description of the winning horse
    private int profit; // Total profit generated from the game
    private int rounds; // Number of rounds played
    private int winnerPlayer; // Player number of the winner
    private ArrayList<Player> players; // List of players in the game

    /**
     * Constructor to initialize a Summary object with winner details, profit, rounds, and player info.
     *
     * @param winner the description of the winning horse.
     * @param profit the total profit generated in the game.
     * @param rounds the total number of rounds played.
     * @param winnerPlayer the player number of the winner.
     * @param players the list of players participating in the game.
     */
    public Summary(String winner, int profit, int rounds, int winnerPlayer, ArrayList<Player> players) {
        this.winner = winner;
        this.profit = profit;
        this.rounds = rounds;
        this.winnerPlayer = winnerPlayer;
        this.players = players;
    }

    /**
     * Provides a string representation of the summary, including player information,
     * the winner, rounds played, and total profit.
     *
     * @return a formatted string containing the summary of the game.
     */
    @Override
    public String toString() {
        String summary = "  - Players Info --------------------------------------------------------------\n";

        for (Player player : players)
            summary += "\t" + player.getDescriptions() + "\n";

        summary += "  ----------------------------------------------------------------------\n" +
                "  - Summary ------------------------------------------------------------------\n" +
                "  \tThe winner is the Player " + this.winnerPlayer + " with the horse " + winner + "\n" +
                "  \tIn " + rounds + " rounds\n" +
                "  \tThe profit is: " + this.profit + "\n" +
                "  - End Summary --------------------------------------------------------------\n";
        return summary;
    }
}
