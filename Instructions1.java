/*
Name: Sujay and Akaren
Class: ICS 3U7
Teacher: Ms.Strelkovska
Description: Creates a panel with the instructions as a background
*/

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.Exception;

public class Instructions1 extends JPanel implements ActionListener {
    private JButton back, next;
    private BufferedImage insBackground;

    public Instructions1() throws Exception{
        this.setLayout(null);

        // Initializing the back button
        back = new JButton(new ImageIcon("back.png"));
        back.setBounds(10,440,50,30);
        back.setBorderPainted(false);
        back.setContentAreaFilled(false);
        back.setOpaque(false);
        back.addActionListener(this);
        this.add(back);

        // Initializing the next button
        next = new JButton(new ImageIcon("next.png"));
        next.setBounds(940,440,50,30);
        next.setBorderPainted(false);
        next.setContentAreaFilled(false);
        next.setOpaque(false);
        next.addActionListener(this);
        this.add(next);


        // Initializing the background
        insBackground = ImageIO.read(new File("instructions1.png"));
    }

    // Painting the instructions and the background
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(insBackground, 0,0, null);
    }

    //Changing panels if their buttons are pressed
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back){
            Cards.flipToCard("MainMenu");
        }
        else if (e.getSource() == next)
            Cards.flipToCard("Instructions2");
    }
}