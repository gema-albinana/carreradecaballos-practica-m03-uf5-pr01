package com.lasalle.horseracinggui;

import com.lasalle.horseracinggui.model.cards.Card;
import com.lasalle.horseracinggui.model.raceboard.Board;
import com.lasalle.horseracinggui.model.cards.CardsDeck;
import com.lasalle.horseracinggui.model.raceboard.Summary;
import com.lasalle.horseracinggui.model.table.*;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The entry point of the horse racing game application.
 * This class initializes the game components, manages the flow of the game,
 * and displays the game state and results to the console.
 */
public class Main {
    public static void main(String[] args) {
        final String ANSI_COLOR_RED = "\u001B[31m";
        final String ANSI_COLOR_RESET = "\u001B[0m";
        final String ANSI_COLOR_GREEN = "\u001B[32m";

        ArrayList<Card> horses; // List of horse cards
        String boardGame; // String representation of the game board
        Scanner input = new Scanner(System.in);

        String banner = "______  __                                    ________                      \n" +
                "___  / / /______ _____________________        ___  __ \\______ _____________ \n" +
                "__  /_/ / _  __ \\__  ___/__  ___/_  _ \\       __  /_/ /_  __ `/_  ___/_  _ \\\n" +
                "_  __  /  / /_/ /_  /    _(__  ) /  __/       _  _, _/ / /_/ / / /__  /  __/\n" +
                "/_/ /_/   \\____/ /_/     /____/  \\___/        /_/ |_|  \\__,_/  \\___/  \\___/ \n" +
                "                                                                            ";

        // Print banner
        System.out.println(ANSI_COLOR_RED + banner + ANSI_COLOR_RESET);

        // The deck of cards is instantiated
        CardsDeck cardsDeck = new CardsDeck();

        // The game board is instantiated
        Board board = new Board();

        // The crupier is instantiated
        Crupier crupier = new Crupier(cardsDeck, board);

        // The crupier draws the four knights from the deck of cards
        // and places each knight on a lane of the game board
        horses = crupier.setHorsesOnBoard();

        // Initializes the BetManager to handle bets from players
        BetManager betManager = new BetManager(horses);
        betManager.startBet();

        // Initializes the game with the players and their bets
        crupier.initializeGame(betManager.getPlayers());

        Summary summary = null; // Summary object to hold race results

        // Loop for each round of the race
        while (true) {
            summary = crupier.startNewRound(); // Start a new round of the race
            boardGame = crupier.getBoardGame(); // Get the current state of the game board
            System.out.println(boardGame); // Display the board state

            // If there is a winner, display the summary and exit the loop
            if (summary != null) {
                System.out.println(summary);
                break;
            }
            System.out.println(ANSI_COLOR_GREEN + "                        >> PRESS [ENTER] TO CONTINUE <<"
                    + ANSI_COLOR_RESET);
            input.nextLine();
        }
    }
}
