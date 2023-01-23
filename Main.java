/*
Name: Sujay and Akaren
Class: ICS 3U7
Teacher: Ms.Strelkovska
Description: Creates the main frame of the program and initializes the game
*/

import javax.swing.*;

public class Main {
  public static void main(String[] args) throws Exception{
    // Create and configure the main frame
    Cards game = new Cards();
    game.setSize(1000,500);
    game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    game.setVisible(true);
    game.setResizable(false);

    // Create an instance of the Inventory class
    new Inventory();
  }
}