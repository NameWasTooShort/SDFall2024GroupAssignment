/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.util.ArrayList;

/**
 *
 * @author basso
 */

public class BlackjackPlayer extends Player {

    private ArrayList<BlackjackCard> hand;
    private boolean isStanding;
    private int balance;
    private int bet;

    public BlackjackPlayer(String name) {
        super(name);
        this.hand = new ArrayList<>();
        this.isStanding = false;
        this.balance = 100;
        this.bet = 0;
    }

    public void addCardToHand(BlackjackCard card) {
        hand.add(card);
    }

    public ArrayList<BlackjackCard> getHand() {
        return hand;
    }

    public int calculateHandValue() {
        int totalValue = 0;
        int aceCount = 0;

        for (BlackjackCard card : hand) {
            totalValue += card.getValue();
            if (card.getRank().equals("Ace")) {
                aceCount++;
            }
        }

        while (totalValue > 21 && aceCount > 0) {
            totalValue -= 10;
            aceCount--;
        }

        return totalValue;
    }

    public boolean isStanding() {
        return isStanding;
    }

    public void setStanding(boolean standing) {
        isStanding = standing;
    }

    public String showHand() {
        StringBuilder sb = new StringBuilder();
        for (BlackjackCard card : hand) {
            sb.append(card.toString()).append(", ");
        }
        return sb.toString().isEmpty() ? "No cards" : sb.toString();
    }

    public void resetHand() {
        hand.clear();
        isStanding = false;
    }

    public int getBalance() {
        return balance;
    }

    public void placeBet(int amount) {
        if (amount <= balance) {
            bet = amount;
            balance -= amount;
        } else {
            throw new IllegalArgumentException("Insufficient balance.");
        }
    }

    public void adjustBalance(int outcome) {
        System.out.println("adjust was called");
        if (outcome == 0) {
            balance += bet * 2;
        } else if (outcome == 1) {
            //do nothing
        }else if (outcome == 2){
            balance += bet;
        }

        bet = 0;
    }



    public int getBet() {
        return bet;
    }

    @Override
    public void play() {
    }
}
