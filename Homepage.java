/*
Name: Sujay and Akaren
Class: ICS 3U7
Teacher: Ms.Strelkovska
Description: The main game where the map is in the center, the top is the currency/other stuff and the bottom includes the buttons with the other features/panels
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Homepage extends JPanel implements ActionListener {
    private JButton shop, inventoryBtn, factoryBtn, barn;
    private JPanel top, center, bottom;
    public Homepage(){
        // Setting the layout to border layout
        this.setLayout(new BorderLayout());

        // Creating a panel for the top, which includes all the currency/bars
        top = new JPanel();
        top.setBackground(new Color(0, 0, 0, 0));
        JLabel l = new JLabel(new ImageIcon("Untitled.png"));
        l.setBounds(0,0,1,100);
        top.add(l);
        this.add(top, BorderLayout.NORTH);

        // Creating a center panel which includes the map
        center = new Map();
        this.add(center, BorderLayout.CENTER);

        bottom = new JPanel(new GridLayout(1,5));
        bottom.setBackground(Color.LIGHT_GRAY);
        // Adding the shop button which takes you to the shop page
        shop = new JButton(new ImageIcon("shop.png"));
        shop.setBorderPainted(false);
        shop.setContentAreaFilled(false);
        shop.setOpaque(false);
        shop.addActionListener(this);
        bottom.add(shop);

        // Adding the production button which takes you to the factories
        factoryBtn = new JButton(new ImageIcon("factory.png"));
        factoryBtn.setBorderPainted(false);
        factoryBtn.setContentAreaFilled(false);
        factoryBtn.setOpaque(false);
        factoryBtn.addActionListener(this);
        bottom.add(factoryBtn);

        for (int i = 0; i < 3; i++){
            bottom.add(new JLabel(""));
        }

        // Adding the inventory button which takes you to the edit page
        inventoryBtn = new JButton(new ImageIcon("edit.png"));
        inventoryBtn.setBorderPainted(false);
        inventoryBtn.setContentAreaFilled(false);
        inventoryBtn.setOpaque(false);
        inventoryBtn.addActionListener(this);
        bottom.add(inventoryBtn);



        //Adding the barn button which takes you to the barn page
        barn = new JButton(new ImageIcon("barn1.png"));
        barn.setBorderPainted(false);
        barn.setContentAreaFilled(false);
        barn.setOpaque(false);
        barn.addActionListener(this);
        bottom.add(barn);
        this.add(bottom, BorderLayout.SOUTH);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Drawing all the bars for xp, population and coins
        g2.setColor(new Color(0,0,0,100));
        g2.fillRoundRect(50,20,100,25,20,20);
        g2.fillRoundRect(50,60,100,25,20,20);
        g2.fillRoundRect(850,20,100,25,20,20);

        // Filling the bars for the amount the player has
        g2.setPaint(new GradientPaint(50, 20, new Color(8, 238, 179), 50+Game.getXp()%100, 45, new Color(0, 69, 146)));
        g2.fillRoundRect(50,20, Game.getXp()%100, 25,20,20);
        g2.fillRoundRect(50,60, Game.getPopulation()*100 / Game.getMaxPopulation(), 25,20,20);

        // Including the images
        g.drawImage(new ImageIcon("star.png").getImage(), 17,7, null);
        g.drawImage(new ImageIcon("population.png").getImage(), 17,47, null);
        g.drawImage(new ImageIcon("coin.png").getImage(), 827,7, null);

        //Writing the number for each type
        g.setColor(new Color(255, 255, 255));
        g.setFont(new Font("Monospaced", Font.BOLD, 18));
        g.drawString("" + Game.getLvl(), 37,36);
        g.drawString(Game.getPopulation() + "/" + Game.getMaxPopulation(), 78,78);
        g.drawString(""+Game.getCoins(), 885, 38);
    }
    @Override
    // Switch panels if their button is pressed
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == shop)
            Cards.flipToCard("Shop");
        else if(e.getSource() == inventoryBtn)
            Cards.flipToCard("Inventory");
        else if(e.getSource() == factoryBtn)
            Cards.flipToCard("Production");
        else if(e.getSource() == barn)
            Cards.flipToCard("Barn");
    }
}
