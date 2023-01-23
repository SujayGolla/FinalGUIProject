/*
Name: Sujay and Akaren
Class: ICS 3U7
Teacher: Ms.Strelkovska
Description: The merchant panel which allows you to sell your barn items and get money back in return
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Merchant extends JPanel  implements ActionListener{

    private int random;
    private BarnItem item1, item2;
    private int cnt = 0;
    private JPanel top, center;
    private JButton back;



    public Merchant() throws InterruptedException {
        this.setLayout(new BorderLayout());

        new Barn(); // This was done because there would be an error since the barnItems weren't in the barn when the merchant was created first
        
        // Creating the back button to go back to the homepage
        back = new JButton(new ImageIcon("back.png"));
        back.setBorderPainted(false);
        back.setContentAreaFilled(false);
        back.setOpaque(false);
        back.setFocusable(false);
        back.addActionListener(this);

        center = new JPanel(new GridLayout(1,2));
        
        // Choosing two random items from their barn that they could sell
        random = (int) (Math.random() * Barn.getBarn().size());
        if (Barn.getBarn().size() > 0) { // making sure they have at least 1 barn item
            item1 = Barn.getBarn().get(random);
            displaySellableItem(item1, 50, 50);
        } else {
            noItems(50, 50);
        }

        if (Barn.getBarn().size() > 1) { // making sure they have at least more than one item

            do {
                random = (int) (Math.random() * Barn.getBarn().size());
                item2 = Barn.getBarn().get(random);
            }
            while (item1.getName().equals(item2.getName()));

            displaySellableItem(item2, 50, 50);
        } else {
            noItems(50, 50);
        }

        this.add(center, BorderLayout.CENTER);

        // adding the title and back button
        top = new JPanel(new GridLayout(1, 5));
        top.setBackground(Color.GRAY);
        JLabel title = new JLabel("    Merchant");
        title.setFont(new Font("Times New Roman", Font.BOLD, 24));
        top.add(back);
        top.add(new JLabel(""));
        top.add(title);
        top.add(new JLabel(""));
        top.add(new JLabel(""));
        this.add(top, BorderLayout.NORTH);
    }

    //displaying the sellable items
    public void displaySellableItem(BarnItem s, int x, int y){
        JPanel p = new JPanel();
        p.setLayout(null);
        Dimension sizeTitle,sizePrice, sizeSell;

        // Including a title with the name of the item
        JLabel title = new JLabel(s.getName());
        sizeTitle = title.getPreferredSize();
        title.setFont(new Font("Times New Roman", Font.BOLD, 12));
        title.setBounds(x+150-(sizeTitle.width / 2),y+30, sizeTitle.width, sizeTitle.height); // some calculations done through some trial and error to find the spacing
        p.add(title);

        // The image of the item
        JLabel img = new JLabel(EditOptionPanel.resizeImg(s.getImg(),40,40));
        img.setBounds(x+50, y+30+sizeTitle.height+15, 200, 200);
        p.add(img);

        // Including the price
        JLabel price = new JLabel(s.getValue() + "", new ImageIcon("shopCoin.png"), JLabel.CENTER);
        price.setFont(new Font("Times New Roman", Font.BOLD, 18));
        sizePrice = price.getPreferredSize();
        price.setBounds(x+75-(sizePrice.width / 2), y+30+sizeTitle.height+15+200-5+((y+350 - (y+30+sizeTitle.height+15+200-5))/2)-(sizePrice.height/2), sizePrice.width, sizePrice.height);// some calculations done through some trial and error to find the spacing
        p.add(price);

        // Creating the sell button
        JButton sell = new JButton(new ImageIcon("sell.png"));
        sell.setName(s.getName() + " Sell");
        sell.setBorderPainted(false);
        sell.setContentAreaFilled(false);
        sell.setOpaque(false);
        sell.setFocusable(false);
        sell.addActionListener(this);
        sizeSell = sell.getPreferredSize();
        sell.setBounds(x+225-(sizeSell.width / 2), y+30+sizeTitle.height+15+200-5+((y+350 - (y+30+sizeTitle.height+15+200-5))/2)-(sizeSell.height/2), sizeSell.width, sizeSell.height);// some calculations done through some trial and error to find the spacing
        p.add(sell);

        JLabel box = new JLabel(new ImageIcon("ShopItemDisplayBox.png"));
        box.setBounds(x,y,300,350);
        p.add(box);

        p.setBounds(350*cnt++, 0, 350, 400);
        center.add(p);
    }

    // In the case that they dont have any barn items
    public void noItems(int x, int y) {
        JPanel p = new JPanel();
        p.setLayout(null);
        Dimension sizeTitle;

        JLabel title = new JLabel("No Barn Items");
        sizeTitle = title.getPreferredSize();
        title.setFont(new Font("Times New Roman", Font.BOLD, 12));
        title.setBounds(x+150-(sizeTitle.width / 2),y+30, sizeTitle.width, sizeTitle.height);
        p.add(title);


        JLabel box = new JLabel(new ImageIcon("ShopItemDisplayBox.png"));
        box.setBounds(x,y,300,350);
        p.add(box);

        p.setBounds(350*cnt++, 0, 350, 400);
        center.add(p);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }

    public void actionPerformed(ActionEvent e){
        JButton b = (JButton) e.getSource();
        String name = "";
        if(b.getName() != null)
            name = b.getName();

        if(b == back)
            Cards.flipToCard("Homepage");
        else {
            // they got through all barnitems possible and find the items that the player has
            for (BarnItem a : Barn.getBarn()) {
                if (a != null) {
                    if (name.startsWith(a.getName())) {
                        try {
                            // they try to sell the item
                            a.sellItem();
                            // if they have the item
                            if (a.canSell()) {
                                // removing the item from the text file and the array
                               Barn.removeBarnItem(a);
                            }
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            }
        }
    }
}