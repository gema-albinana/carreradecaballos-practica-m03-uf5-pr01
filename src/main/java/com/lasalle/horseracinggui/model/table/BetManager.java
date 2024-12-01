package com.lasalle.horseracinggui.model.table;

import com.lasalle.horseracinggui.model.cards.Card;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages the betting game involving a set of horses and players.
 * This class handles player input for betting, including the number of players,
 * which horse to bet on, and the amount of the bet.
 */
public class BetManager {
    final String ANSI_COLOR_BLUE = "\u001B[34m";
    final String ANSI_COLOR_RESET = "\u001B[0m";

    int numPlayerToPlay = 1;  // Number of players participating in the game
    int chosenHorse = 0;      // Index of the horse chosen for betting
    int betAmount = 0;        // Amount of the bet placed by a player
    final int MAX_PLAYER = 4; // Maximum number of players allowed

    Scanner input = new Scanner(System.in); // Scanner for user input
    ArrayList<Card> horses;  // List of horses available for betting
    ArrayList<Player> players = new ArrayList<>(); // List of players participating in the game

    /**
     * Constructs a BetManager with the specified list of horses.
     *
     * @param horses an ArrayList of Card objects representing the horses involved in the betting.
     */
    public BetManager(ArrayList<Card> horses) {
        this.horses = horses;
    }

    /**
     * Retrieves the list of players participating in the betting.
     *
     * @return an ArrayList of Player objects representing the current players.
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Starts the betting process, allowing players to choose their horses
     * and the amount they wish to bet. Handles both human and bot players.
     */
    public void startBet() {
        // The game MENU is displayed
        do {
            System.out.print("How many players? [1-4]: ");
            if (input.hasNextInt()) {
                numPlayerToPlay = input.nextInt();
                if (numPlayerToPlay > MAX_PLAYER) {
                    System.out.println("The number of eligible players is from 1 to 4.");
                    continue;
                }
                break;
            } else {
                System.out.println("You must enter a number from 1 to 4.");
                input.next();
            }
        } while (true);
        System.out.println();

        // HUMANS: You choose which horse to bet on and what the amount is.
        for (int i = 1; i <= numPlayerToPlay; i++) {
            do {
                System.out.println(ANSI_COLOR_BLUE + "PLAYER " + i + ANSI_COLOR_RESET +
                        ": Which horse are you going to bet on?");

                // Show all the candidate horses.
                for (int j = 0; j < horses.size(); j++)
                    System.out.println((j + 1) + " - " + horses.get(j).getDescription());

                System.out.print("[1 - " + horses.size() + "]:");
                if (input.hasNextInt()) {
                    chosenHorse = input.nextInt();
                    if (chosenHorse < 1 || chosenHorse > horses.size()) {
                        System.out.println("You have chosen an unavailable horse, please select one from the list.");
                        continue;
                    }

                    do {
                        System.out.print("How much do you want to bet?: ");
                        if (input.hasNextInt()) {
                            betAmount = input.nextInt();
                            if (betAmount < 1) {
                                System.out.println("You must bet at least 1 token.");
                                continue;
                            }
                            break;
                        } else {
                            System.out.println("The bet must be numeric");
                            input.next();
                        }
                    } while (true);

                    Human humanPlayer = new Human(i, betAmount, horses.get(chosenHorse - 1));
                    players.add(humanPlayer);

                    // This horse has already been chosen, so we remove it from the candidates.
                    horses.remove(chosenHorse - 1);

                    break;
                } else {
                    System.out.println("You must enter a number from 1 to " + horses.size());
                    input.next();
                }
            } while (true);
            System.out.println();
        }

        // BOTS: They choose which horse to bet on and what the amount is.

        int remainingPlayers = MAX_PLAYER - numPlayerToPlay;

        for (int i = 1; i <= remainingPlayers; i++) {
            chosenHorse = horses.size() - 1; // Choose the last horse for bots
            betAmount = (int) (Math.random() * 50 + 1); // Random bet amount between 1 and 50
            Bot botPlayer = new Bot(numPlayerToPlay + i, betAmount, horses.get(chosenHorse));
            players.add(botPlayer);

            // This horse has already been chosen, so we remove it from the candidates.
            horses.remove(chosenHorse);
        }
    }
}
