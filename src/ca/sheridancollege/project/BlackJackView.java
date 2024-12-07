/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.util.Scanner;

/**
 *
 * @author Steven
 */
public class BlackJackView {

    private Scanner scanner;

    public BlackJackView(Scanner scanner) {
        this.scanner = scanner;
    }

    public void displayRules() {
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

    public String getPlayerName() {
        System.out.print("Enter your name: ");
        String playerName = this.scanner.nextLine();
        return playerName;
    }

    public void displayBalance(BlackjackPlayer player) {
        System.out.println("\nYour current balance is $" + player.getBalance());
    }

    // Will return a int.
    // Validation occurs elsewhere
    public int getBet() {
        int bet = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Enter your bet amount: ");
            if (this.scanner.hasNextInt()) {
                bet = this.scanner.nextInt();
                validInput = true; // Valid input received
            } else {
                System.out.println("Invalid input. Please enter a numeric value.");
                this.scanner.next(); // Consume the invalid input to avoid an infinite loop
            }
        }
        return bet;
    }
    
    public void informDealing(){
        System.out.println();
        System.out.println("Dealing cards...");
    }

    public void displayHands(BlackjackPlayer player, BlackjackPlayer dealer) {
        System.out.println("\nPlayer's Hand: " + player.showHand() + " (Total: " + player.calculateHandValue() + ")");
        System.out.println("Dealer's Hand: " + dealer.showHand() + " (Total: " + dealer.calculateHandValue() + ")");
        System.out.println();
    }

    // Ask for then return the action
    public String askAction() {
        System.out.print("\nWould you like to 'hit' or 'stand'? ");
        this.scanner.nextLine(); // Skip a line here, otherwise the .nextLine() below will pick up a empty line.
        String choice = this.scanner.nextLine();
        return choice.toLowerCase();
    }

    public void displayPlayerTurn() {
        System.out.println("Player's Turn:");
    }

    public void displayDealerAction(BlackjackPlayer dealer) {
        System.out.println("Dealer's Turn:");
        if (dealer.calculateHandValue() < 17) {
            System.out.println("Dealer hits.");
            //dealer.addCardToHand(deck.dealCard());

            if (dealer.calculateHandValue() > 21) {
                System.out.println("Dealer busted with " + dealer.calculateHandValue() + ". You win!");
            }

        } else {
            System.out.println("Dealer stands with " + dealer.calculateHandValue() + ".");
        }
    }
    
    // Shows if there is a winner and Returns true if there is a winner
    public boolean displayWinner(String winner) {
        // If no one is a winner
        if (winner.equals("")) {
            return false;
        } else if (winner.equals("player")) {
            // Player wins
            System.out.println("Congratulations! You win!");
            return true;
        } else if (winner.equals("dealer")) {
            // dealer wins
            System.out.println("The dealer has won!");
            return true;
        } else if (winner.equals("tie")) {
            System.out.println("It's a tie!");
            // We consider a tie to be 2 winners
            return true;
        } else {
            System.out.println("ERROR");
            return false;
        }
    }

    public void noFunds() {
        System.out.println("Out of funds. Game Over.");
    }

    public String askPlayAgain() {
        String playAgain;

        System.out.print("Would you like to play again? (yes/no): ");
        playAgain = this.scanner.nextLine().toLowerCase();

        return playAgain;
    }
    
    public void explainWin(int playerValue, int dealerValue){
        if(playerValue == 21){
            System.out.println("Player BLACKJACK!");
        }
        else if(dealerValue == 21){
            System.out.println("Dealer BLACKJACK!");
        }
        if (playerValue > 21) {
            System.out.println("You busted! Dealer wins.");
        } else if (dealerValue > 21) {
            System.out.println("Dealer busted! You win!");
        } else if (dealerValue >= 17 && dealerValue > playerValue) {
            System.out.println("Dealer wins with " + dealerValue + "!");
        } else if (playerValue > dealerValue) {
            System.out.println("You win with " + playerValue + "!");
        } else {
            System.out.println("It's a Blackjack tie!");
        }
    }
    public void exitStatement(){
        System.out.println("Thank you for playing!");
    }
    
}
