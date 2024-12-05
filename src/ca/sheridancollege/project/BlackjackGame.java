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
public class BlackjackGame extends Game {

    private GroupOfCards deck;
    private BlackjackPlayer dealer;
    private BlackjackPlayer player;

    public BlackjackGame(String name) {
        super(name);
        deck = new GroupOfCards();
        deck.shuffle();
        dealer = new BlackjackPlayer("Dealer");
    }

    public void setPlayer(BlackjackPlayer player) {
        this.player = player;
    }

    private void dealInitialCards() {
        player.addCardToHand(deck.dealCard());
        player.addCardToHand(deck.dealCard());
        dealer.addCardToHand(deck.dealCard());
        dealer.addCardToHand(deck.dealCard());
    }

    private void displayHands() {
        System.out.println("\nPlayer's Hand: " + player.showHand() + " (Total: " + player.calculateHandValue() + ")");
        System.out.println("Dealer's Hand: " + dealer.showHand() + " (Total: " + dealer.calculateHandValue() + ")");
    }

    private void handlePlayerTurn(Scanner scanner) {
        while (!player.isStanding() && player.calculateHandValue() < 21) {
            System.out.print("\nWould you like to 'hit' or 'stand'? ");
            String choice = scanner.nextLine().toLowerCase();

            if (choice.equals("hit")) {
                player.addCardToHand(deck.dealCard());
                displayHands();
                if (player.calculateHandValue() > 21) {
                    System.out.println("You busted with " + player.calculateHandValue() + "!");
                    return;
                }
            } else if (choice.equals("stand")) {
                player.setStanding(true);
            } else {
                System.out.println("Invalid choice. Please type 'hit' or 'stand'.");
            }

            handleDealerAction();
        }
    }

    private void handleDealerAction() {
        if (dealer.calculateHandValue() < 17) {
            System.out.println("Dealer hits.");
            dealer.addCardToHand(deck.dealCard());
            displayHands();
            if (dealer.calculateHandValue() > 21) {
                System.out.println("Dealer busted with " + dealer.calculateHandValue() + ". You win!");
            }
        } else {
            System.out.println("Dealer stands with " + dealer.calculateHandValue() + ".");
        }
    }

    private void handleDealerTurn() {
        System.out.println("\nDealer's Turn:");
        if (dealer.calculateHandValue() == 21 && dealer.getHand().size() == 2) {
            System.out.println("Dealer hits Blackjack with 21! Dealer wins!");
            return;
        }

        while (dealer.calculateHandValue() < 17) {
            System.out.println("Dealer hits.");
            dealer.addCardToHand(deck.dealCard());
            displayHands();
            if (dealer.calculateHandValue() > 21) {
                System.out.println("Dealer busted with " + dealer.calculateHandValue() + ". You win!");
                return;
            }
        }

        System.out.println("Dealer stands with " + dealer.calculateHandValue() + ".");
    }

    private void processResults() {
        int playerValue = player.calculateHandValue();
        int dealerValue = dealer.calculateHandValue();

        if (playerValue > 21) {
            System.out.println("You busted! Dealer wins.");
            player.adjustBalance(1);
        } else if (dealerValue > 21) {
            System.out.println("Dealer busted! You win!");
            player.adjustBalance(0);
        } else if (dealerValue >= 17 && dealerValue > playerValue) {
            System.out.println("Dealer wins with " + dealerValue + "!");
            player.adjustBalance(1);
        } else if (playerValue > dealerValue) {
            System.out.println("You win with " + playerValue + "!");
            player.adjustBalance(0);
        } else {
            System.out.println("It's a tie!");
            player.adjustBalance(2);
        }
    }

    @Override
    public void play() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            deck = new GroupOfCards();
            deck.shuffle();
            player.resetHand();
            dealer.resetHand();

            System.out.println("\nYour current balance is $" + player.getBalance());
            int bet = 0;
            while (true) {
                System.out.print("Enter your bet amount: ");
                try {
                    bet = Integer.parseInt(scanner.nextLine());
                    if (bet > 0 && bet <= player.getBalance()) {
                        break;
                    } else {
                        System.out.println("Invalid amount. Bet must be greater than 0 and less than or equal to your balance.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                }
            }

            try {
                player.placeBet(bet);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }

            dealInitialCards();
            displayHands();

            if (player.calculateHandValue() == 21 && player.getHand().size() == 2) {
                System.out.println("You hit Blackjack with 21! You win!");
                player.adjustBalance(0);
                continue;
            }
            if (dealer.calculateHandValue() == 21 && dealer.getHand().size() == 2) {
                System.out.println("Dealer hits Blackjack with 21! Dealer wins!");
                player.adjustBalance(1);
                continue;
            }

            handlePlayerTurn(scanner);

            if (player.calculateHandValue() <= 20) {
                handleDealerTurn();
            }

            processResults();

            System.out.println("\nYour balance is now $" + player.getBalance());
            System.out.print("Would you like to play again? (yes/no): ");
            String playAgain = scanner.nextLine().toLowerCase();

            if (!playAgain.equals("yes")) {
                System.out.println("Thanks for playing! Goodbye.");
                break;
            }
        }

        scanner.close();
    }

    @Override
    public void declareWinner() {
    }
}
