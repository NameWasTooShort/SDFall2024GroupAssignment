/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.util.Scanner;

/**
 *
 * @author basso
 */
public class BlackjackMain {

    public static void main(String[] args) {
        displayRules();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String playerName = scanner.nextLine();

        BlackjackPlayer player = new BlackjackPlayer(playerName);
        BlackjackGame game = new BlackjackGame("Single-Player Blackjack");
        game.setPlayer(player);

        game.play();

        scanner.close();
    }

    private static void displayRules() {
        System.out.println("==================================");
        System.out.println("      Welcome to Blackjack!       ");
        System.out.println("==================================");
        System.out.println("Rules:");
        System.out.println("1. The goal is to get as close to 21 as possible without going over.");
        System.out.println("2. Face cards (King, Queen, Jack) are worth 10 points.");
        System.out.println("3. Aces can be worth 1 or 11, depending on your hand.");
        System.out.println("4. The dealer must stand on 17 or higher.");
        System.out.println("5. You start with $100 and place bets each round.");
        System.out.println("6. If you win, your bet is doubled and added to your balance.");
        System.out.println("7. In case of a tie, your bet is returned.");
        System.out.println("==================================\n");
    }
}