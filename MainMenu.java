/*
Name: Sujay and Akaren
Class: ICS 3U7
Teacher: Ms.Strelkovska
*/

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.Exception;

public class MainMenu extends JPanel implements ActionListener {
  private JButton play, ins, quit;
  private BufferedImage menuBackground;

  public MainMenu() throws Exception{
    this.setLayout(null);

    play = new JButton(new ImageIcon("play.png"));
    play.setBounds(450,250,100,50);
    play.setBorderPainted(false);
    play.setContentAreaFilled(false);
    play.setOpaque(false);
    play.addActionListener(this);
    this.add(play);

    ins = new JButton(new ImageIcon("instructions.png"));
    ins.setBounds(450,310,100,50);
    ins.setBorderPainted(false);
    ins.setContentAreaFilled(false);
    ins.setOpaque(false);
    ins.addActionListener(this);
    this.add(ins);

    quit = new JButton(new ImageIcon("quit.png"));
    quit.setBounds(450,370,100,50);
    quit.setBorderPainted(false);
    quit.setContentAreaFilled(false);
    quit.setOpaque(false);
    quit.addActionListener(this);
    this.add(quit);



    menuBackground = ImageIO.read(new File("mMenu.jpg"));
  }
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    g.drawImage(menuBackground, 0,0, null);
  }
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == play){
      Cards.flipToCard("Homepage");
    }
    else if (e.getSource() == ins)
      Cards.flipToCard("Instructions1");

    else if (e.getSource() == quit)
      System.exit(0);
  }
}