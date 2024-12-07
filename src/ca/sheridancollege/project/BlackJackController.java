/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

/**
 *
 * @author Steven
 */
public class BlackJackController {

    private BlackjackGame game;
    private BlackJackView view;

    public BlackJackController(BlackjackGame game, BlackJackView view) {
        this.game = game;
        this.view = view;
    }

    // Manage Game
    public void start() {
        // Show rules
        this.view.displayRules();

        // Create player
        // Add player to game
        this.game.setPlayer(new BlackjackPlayer(this.view.getPlayerName()));

        // Actual game start
        while (true) {
            // Show current balance:
            this.view.displayBalance(this.game.getPlayer());

            // Ask for bet amount: this returns a value that needs to be validated
            boolean validBet = false;
            int currentBet = 0;
            // Then validate. Keep asking until value is valid.
            // While the bet is not valid
            while (!validBet) {
                currentBet = this.view.getBet();
                validBet = getValidBet(currentBet, this.game.getPlayer().getBalance());
            }
            // Move on once bet is valid and tracked using current bet and place the bet
            this.game.getPlayer().placeBet(currentBet);

            // Deal cards:
            this.game.dealInitialCards();
            this.view.informDealing();
            // Show the cards
            this.view.displayHands(this.game.getPlayer(), this.game.getDealer());

            // Check if someone has immediately won with BlackJack.
            // If they did, check what kinda of win.
            if (this.view.displayWinner(this.game.checkBlackJack())) {
                // Have the game end here if Blackjack occurs
                // Player has immediate Blackjack
                if (this.game.getPlayer().calculateHandValue() == 21) {
                    this.view.explainWin(this.game.getPlayer().calculateHandValue(), this.game.getDealer().calculateHandValue());
                } // Dealer has immediate Blackjack
                else if (this.game.getDealer().calculateHandValue() == 21) {
                    this.view.explainWin(this.game.getPlayer().calculateHandValue(), this.game.getDealer().calculateHandValue());
                } // If both have Blackjack
                else if (this.game.getPlayer().calculateHandValue() == 21 && this.game.getDealer().calculateHandValue() == 21) {
                    this.view.explainWin(this.game.getPlayer().calculateHandValue(), this.game.getDealer().calculateHandValue());
                }

                // Asks if player wants to replay
                if (replayHelper()) {
                    continue;
                } else {
                    break;
                }
            }

            // Player's turn
            this.view.displayPlayerTurn();
            // Ask Player for action
            // Validate the Player's action
            boolean validAction = false;
            String action = "";
            // Looking for invalid actions
            while (!validAction) {
                action = this.view.askAction();
                validAction = validateAction(action);
            }

            // Handle the Player's action now that we have a valid action to take
            this.game.cPlayerTurn(action);

            // Handle Dealer's action
            this.view.displayDealerAction(this.game.getDealer());
            this.game.cDealerTurn();

            // Show both hands
            this.view.displayHands(this.game.getPlayer(), this.game.getDealer());

            // Check if someone wins.
            this.view.displayWinner(this.game.controllerResults());
            this.view.explainWin(this.game.getPlayer().calculateHandValue(), this.game.getDealer().calculateHandValue());

            // Check if Player is out of money and show you lost
            // If out of funds, replaying should entirely start over.
            if (this.game.getPlayer().getBalance() <= 0) {
                this.view.noFunds();
            }

            // Clear hand before moving to next potential round
            // Asks if player wants to replay
            if (replayHelper()) {
                continue;
            } else {
                break;
            }
        }
    }

    // Helper to check if the user's hit or stand actions are valid
    public boolean validateAction(String action) {
        // If action is hit or stand.
        return (action.toLowerCase().equals("hit")) || (action.toLowerCase().equals("stand"));
    }

    // Helper function to get a valid bet value
    // Returns a boolean to indicate if the bet is good or bad.
    // False = bad (get a new one)
    public boolean getValidBet(int bet, int balance) {
        // Good value: balance is enough and it is greater then 0
        return (bet <= balance) && (bet > 0);
    }

    // Helper to determine if the replay action is valid.
    public boolean validReplay(String action) {
        action = action.toLowerCase();
        return action.equals("yes") || action.equals("no");
    }

    // Helper function to ask if the player wants to replay.
    public boolean replayHelper() {
        // Ask if player wants to play again
        boolean replay = false;
        String replayValue = "";
        // Validates response
        while (!replay) {
            replayValue = this.view.askPlayAgain();
            replay = validReplay(replayValue);
        }
        // If player wants to replay
        if (replayValue.equals("yes")) {
            // Want to play again
            return true;
        } // Already validated above so if it is not yes it has to be no.
        else {
            // Do not want to play again
            this.view.exitStatement();
            return false;
        }
    }

}
