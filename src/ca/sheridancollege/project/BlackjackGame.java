/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.util.Scanner;

/**
 *
 * @author basso
 * @author Steven
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

    protected void setPlayer(BlackjackPlayer player) {
        this.player = player;
    }

    protected BlackjackPlayer getPlayer() {
        return this.player;
    }

    protected BlackjackPlayer getDealer() {
        return this.dealer;
    }

    protected void dealInitialCards() {
        this.deck.shuffle();
        
        this.player.resetHand();
        this.dealer.resetHand();
        
        this.player.addCardToHand(deck.dealCard());
        this.player.addCardToHand(deck.dealCard());
        this.dealer.addCardToHand(deck.dealCard());
        this.dealer.addCardToHand(deck.dealCard());
    }

    // Implementation for the Controller
    protected void cPlayerTurn(String action) {
        if (action.equals("hit")) {
            this.player.addCardToHand(this.deck.dealCard());
        } else if (action.equals("stand")) {
            this.player.setStanding(true);
        } 
        // This line shouldn't occur
        else {
            System.out.println("ERROR");
        }
    }
    
    // Implementation of handleDealerAction() meant for the controller:
    protected void cDealerTurn() {
        if (this.dealer.calculateHandValue() < 17) {
            this.dealer.addCardToHand(deck.dealCard());
            if (this.dealer.calculateHandValue() > 21) {
                // Dealer busts and wins here
            }
        } else {
            // Dealer stands
        }
    }
 
    // Implementation for Controller
    protected String controllerResults() {
        int playerValue = this.player.calculateHandValue();
        int dealerValue = this.dealer.calculateHandValue();
        
        if(playerValue == 21){
            this.player.adjustBalance(0);
            return "player";
        }
        else if(dealerValue == 21){
            this.dealer.adjustBalance(0);
            return "dealer";
        }
        else if (playerValue > 21) {
            this.player.adjustBalance(1);
            return "dealer";
        } else if (dealerValue > 21) {
            this.player.adjustBalance(0);
            return "player";
        } else if (dealerValue >= 17 && dealerValue > playerValue) {
            this.player.adjustBalance(1);
            return "dealer";
        } else if (playerValue > dealerValue) {
            this.player.adjustBalance(0);
            return "player";
        } else {
            this.player.adjustBalance(2);
            return "tie";
        }
    }

    protected String checkBlackJack() {
        if (this.player.calculateHandValue() == 21) {
            this.player.adjustBalance(0);
            return "player";
        } else if (this.dealer.calculateHandValue() == 21) {
            this.dealer.adjustBalance(1);
            return "dealer";
        }
        return "";
    }

    // We ended up not using these in a MVC pattern
    // Showing winner is done in View
    @Override
    public void declareWinner() {

    }
    // Playing game is done in controller
    @Override
    public void play(){
        
    }
}