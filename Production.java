/*
Name: Sujay and Akaren
Class: ICS 3U7
Teacher: Ms.Strelkovska
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class Production extends JPanel implements ActionListener{
    private JPanel n, c;
    private JButton factories, farming, back;
    private CardLayout layout;
    private final ShopItemTiles[] factoriesArray = {ShopItemTiles.getShopItem("Bakery"), ShopItemTiles.getShopItem("Feed Mill"), ShopItemTiles.getShopItem("Dairy Production"), ShopItemTiles.getShopItem("Textile Production"), ShopItemTiles.getShopItem("Fast Food Restaurant")};
    private final ShopItemTiles[] farmsArray = {ShopItemTiles.getShopItem("Field"), ShopItemTiles.getShopItem("Cowshed"), ShopItemTiles.getShopItem("Chicken Coop"), ShopItemTiles.getShopItem("Sheep Farm")};
    private final BarnItem[][] barn;
    private final ArrayList<ArrayList<ShopItemTiles>> inventory;
    private final ShopItemTiles[][] shop = {factoriesArray, farmsArray};
    private int cnt = 0;
    public Production(){
        this.setLayout(new BorderLayout());
        this.setBackground(Color.GRAY);
        barn = BarnItem.getBarn();
        inventory = Inventory.getInventory();
        makeNorth();
        this.add(n, BorderLayout.NORTH);
        makeCenter();
        this.add(c, BorderLayout.CENTER);
    }
    public void makeNorth(){
        farming = new JButton(new ImageIcon("shopFarming.png"));
        defaultButtonSetup(farming);
        factories = new JButton(new ImageIcon("shopFactories.png"));
        defaultButtonSetup(factories);
        back = new JButton(new ImageIcon("back.png"));
        defaultButtonSetup(back);

        JPanel navBar = new JPanel();
        navBar.setLayout(new GridLayout(1,2));
        n = new JPanel();
        n.setLayout(new BorderLayout());
        n.setBackground(Color.GRAY);
        JPanel titleN = new JPanel();
        titleN.setLayout(new GridLayout(1,5));
        titleN.setBackground(Color.LIGHT_GRAY);

        n.add(titleN, BorderLayout.NORTH);
        JLabel title = new JLabel("Production");
        title.setFont(new Font("Times New Roman", Font.BOLD, 30));
        titleN.add(back);
        titleN.add(new JLabel(""));
        titleN.add(title);
        titleN.add(new JLabel(""));
        titleN.add(new JLabel(""));

        n.add(navBar, BorderLayout.CENTER);
        navBar.add(factories);
        navBar.add(farming);
    }
    public void makeCenter() {
        c = new JPanel();
        layout = new CardLayout();
        c.setLayout(layout);
        c.setBackground(Color.WHITE);

        JPanel cFac = new JPanel(new GridLayout(1, inventory.get(1).size()+1));
        JPanel cFar = new JPanel(new GridLayout(1, inventory.get(2).size()+1));

        c.add("Factories", cFac);
        c.add("Farming", cFar);
    }
    public void centerFlipToCard(String ID){
        layout.show(c, ID);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }
    public void defaultButtonSetup(JButton b){
        b.setBorderPainted(false);
        b.setContentAreaFilled(false);
        b.setOpaque(false);
        b.setFocusable(false);
        b.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e){
        JButton b = (JButton) e.getSource();
        String name = "";
        if(b.getName() != null)
            name = b.getName();

        if(b == back)
            Cards.flipToCard("Homepage");
        else if(b == factories)
            centerFlipToCard("Factories");
        else if(b == farming)
            centerFlipToCard("Farming");
        else {
            for(ShopItemTiles[] a : shop){
                for(ShopItemTiles s : a){
                    if (name.startsWith(s.getName())) {
                        String category = "";
                        if (Arrays.equals(a, factoriesArray)) {
                            category = "Factories";
                        } else if (Arrays.equals(a, farmsArray)) {
                            category = "Farms";
                        } 
                        try {
                            if(s.canBuyItem()) {
                                s.purchaseItem();
                                Inventory.addShopItem(s, category);
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
