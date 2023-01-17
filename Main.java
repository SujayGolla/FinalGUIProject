/*
Name: Sujay and Akaren
Class: ICS 3U7
Teacher: Ms.Strelkovska
*/

import javax.swing.*;

public class Main {
  public static void main(String[] args) throws Exception{
    Cards game = new Cards();
    game.setSize(1000,500);
    game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    game.setVisible(true);
    game.setResizable(false);
    new Inventory();
  }
}