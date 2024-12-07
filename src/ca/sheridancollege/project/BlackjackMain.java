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
        // Controller based:
        BlackjackGame game1 = new BlackjackGame("Single-Player Blackjack");
        Scanner scanner = new Scanner(System.in);
        BlackJackView view1 = new BlackJackView(scanner);
        
        BlackJackController controller = new BlackJackController(game1, view1);
        
        controller.start();
    }
}