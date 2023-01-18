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
    private final ShopItemTiles[] farmsArray = {ShopItemTiles.getShopItem("Cowshed"), ShopItemTiles.getShopItem("Chicken Coop"), ShopItemTiles.getShopItem("Sheep Farm")};
    private final BarnItem[][] barn;
    private int cnt=0;
    private final ArrayList<ArrayList<ShopItemTiles>> inventory;
    private final ShopItemTiles[][] production = {factoriesArray, farmsArray};
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
        cFac.add(new JLabel(new ImageIcon("Untitled(1).png")));
        for (ShopItemTiles s : factoriesArray) {
            displayItems(s, cFac, cnt, 10);
        }
        JScrollPane cFacScroll = new JScrollPane(cFac);
        c.add("Factories", cFacScroll);
        JPanel cFar = new JPanel(new GridLayout(1, inventory.get(2).size()+1));
        cFar.add(new JLabel(new ImageIcon("Untitled(1).png")));
        for (ShopItemTiles s : farmsArray) {
            displayItems(s, cFar, cnt, 10);
        }
        JScrollPane cFarScroll = new JScrollPane(cFar);
        c.add("Farming", cFarScroll);
    }
    public void displayItems(ShopItemTiles s, JPanel panel, int x, int y){
        JPanel p = new JPanel();
        p.setLayout(null);
        Dimension sizeTitle, sizeProduce;

        JLabel title = new JLabel(s.getName());
        sizeTitle = title.getPreferredSize();
        title.setFont(new Font("Times New Roman", Font.BOLD, 12));
        title.setBounds(x+150-(sizeTitle.width / 2),y+30, sizeTitle.width, sizeTitle.height);
        p.add(title);

        JLabel img = new JLabel(s.getImg());
        img.setBounds(x+50, y+30+sizeTitle.height+15, 200, 200);
        p.add(img);

        JButton produce = new JButton(new ImageIcon("produce.png"));
        produce.setName(s.getName() + " Produce");
        defaultButtonSetup(produce);
        sizeProduce = produce.getPreferredSize();
        produce.setBounds(x+150-(sizeProduce.width / 2), y+30+sizeTitle.height+15+200-5+((y+350 - (y+30+sizeTitle.height+15+200-5))/2)-(sizeProduce.height/2), sizeProduce.width, sizeProduce.height);
        p.add(produce);

        JLabel box = new JLabel(new ImageIcon("ShopItemDisplayBox.png"));
        box.setBounds(x,y,300,350);
        p.add(box);

        p.setBounds(350*cnt++, 0, 350, 400);

        panel.add(p);
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
        else{
            for (int i = 0; i < factoriesArray.length; i++) {
                ShopItemTiles s = factoriesArray[i];
                if (s != null && name.startsWith(s.getName())) {
                    BasicProductionFrame frame = new BasicProductionFrame(s.getName(), BarnItem.getBarn()[i]);
                }
            }
            for (int i = 0; i < farmsArray.length; i++) {
                ShopItemTiles s = farmsArray[i];
                if (s != null && name.startsWith(s.getName())) {
                    BasicProductionFrame frame = new BasicProductionFrame(s.getName(), BarnItem.getBarn()[ShopItemTiles.getNumFactories() + i]);
                }
            }
        }
    }
}
