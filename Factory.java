/*
Name: Sujay and Akaren
Class: ICS 3U7
Teacher: Ms.Strelkovska
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Factory extends JPanel implements ActionListener{
    private JPanel n, c;
    private JButton factories, farming, back;
    private CardLayout layout;
    private final ShopItemTiles[] factoriesArray = {ShopItemTiles.getShopItem("Feed Mill"), ShopItemTiles.getShopItem("Dairy Factory"), ShopItemTiles.getShopItem("Textile Factory"), ShopItemTiles.getShopItem("Bakery"), ShopItemTiles.getShopItem("Fast Food Restaurant")};
    private final ShopItemTiles[] farmsArray = {ShopItemTiles.getShopItem("Field"), ShopItemTiles.getShopItem("Cowshed"), ShopItemTiles.getShopItem("Chicken Coop"), ShopItemTiles.getShopItem("Sheep Farm")};
    private final ShopItemTiles[][] shop = { factoriesArray, farmsArray};
    private int cnt = 0;
    public Shop(){
        this.setLayout(new BorderLayout());
        this.setBackground(Color.GRAY);
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
        navBar.setLayout(new GridLayout(1,8));
        n = new JPanel();
        n.setLayout(new BorderLayout());
        n.setBackground(Color.GRAY);
        JPanel titleN = new JPanel();
        titleN.setLayout(new GridLayout(1,9));
        titleN.setBackground(Color.LIGHT_GRAY);

        n.add(titleN, BorderLayout.NORTH);
        JLabel title = new JLabel("  Factories");
        title.setFont(new Font("Times New Roman", Font.BOLD, 30));
        titleN.add(back);
        titleN.add(new JLabel(""));
        titleN.add(new JLabel(""));
        titleN.add(new JLabel(""));
        titleN.add(title);
        titleN.add(new JLabel(""));
        titleN.add(new JLabel(""));
        titleN.add(new JLabel(""));
        titleN.add(new JLabel(""));

        n.add(navBar, BorderLayout.CENTER);
        navBar.add(new JLabel(""));
        navBar.add(factories);
        navBar.add(farming);
        navBar.add(new JLabel(""));
        navBar.add(new JLabel(""));
        navBar.add(new JLabel(""));
        navBar.add(new JLabel(""));
        navBar.add(new JLabel(""));
    }
    public void makeCenter() {
        c = new JPanel();
        layout = new CardLayout();
        c.setLayout(layout);
        c.setBackground(Color.WHITE);

        JPanel cFac = new JPanel(new GridLayout(1, factoriesArray.length + 1));
        JPanel cFar = new JPanel(new GridLayout(1, farmsArray.length + 1));

        addShopItemsToPanels(cFac, factoriesArray);
        cnt = 0;
        addShopItemsToPanels(cFar, farmsArray);

    }
    public void addShopItemsToPanels(JPanel p, ShopItemTiles[] array){
        p.add(new JLabel(new ImageIcon("Untitled(1).png")));
        for(ShopItemTiles i : array){
            if(i != null) {
                if (i.isUnlocked()) {
                    displayUnlockedItems(i, p, 30, 25);
                } else {
                    displayLockedItems(i, p, 30, 25);
                }
            }
        }
    }
    public void centerFlipToCard(String ID){
        layout.show(c, ID);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }
    public void displayUnlockedItems(ShopItemTiles s, JPanel panel, int x, int y){
        JPanel p = new JPanel();
        p.setLayout(null);
        Dimension sizeTitle,sizePrice, sizeBuy;

        JLabel title = new JLabel(s.getName());
        sizeTitle = title.getPreferredSize();
        title.setFont(new Font("Times New Roman", Font.BOLD, 12));
        title.setBounds(x+150-(sizeTitle.width / 2),y+30, sizeTitle.width, sizeTitle.height);
        p.add(title);

        JLabel img = new JLabel(s.getImg());
        img.setBounds(x+50, y+30+sizeTitle.height+15, 200, 200);
        p.add(img);

        JLabel price = new JLabel(s.getPrice() + "", new ImageIcon("shopCoin.png"), JLabel.CENTER);
        price.setFont(new Font("Times New Roman", Font.BOLD, 18));
        sizePrice = price.getPreferredSize();
        price.setBounds(x+75-(sizePrice.width / 2), y+30+sizeTitle.height+15+200-5+((y+350 - (y+30+sizeTitle.height+15+200-5))/2)-(sizePrice.height/2), sizePrice.width, sizePrice.height);
        p.add(price);

        if(HouseItem.isHouse(s.getName())){
            JLabel numPpl = new JLabel("+" + s.getPpl(),new ImageIcon("shopPopulation.png"), JLabel.CENTER);
            numPpl.setFont(new Font("Times New Roman", Font.BOLD, 14));
            Dimension sizeNumPpl = numPpl.getPreferredSize();
            numPpl.setBounds(x+150-(sizeNumPpl.width / 2), y+30+sizeTitle.height+15+200-5+((y+350 - (y+30+sizeTitle.height+15+200-5))/2)-(sizePrice.height/2), sizeNumPpl.width, sizeNumPpl.height);
            p.add(numPpl);
        } else if(FactoryItem.isFactory(s.getName()) || FarmItem.isFarm(s.getName())){
            JLabel reqPpl = new JLabel("Needs " + s.getReqPpl(),new ImageIcon("shopPopulation.png"), JLabel.CENTER);
            reqPpl.setFont(new Font("Times New Roman", Font.BOLD, 8));
            Dimension sizeReqPpl = reqPpl.getPreferredSize();
            reqPpl.setBounds(x+150-(sizeReqPpl.width / 2)-5, y+30+sizeTitle.height+15+200-5+((y+350 - (y+30+sizeTitle.height+15+200-5))/2)-(sizePrice.height/2), sizeReqPpl.width, sizeReqPpl.height);
            p.add(reqPpl);
        }

        JButton buy = new JButton(new ImageIcon("buy.png"));
        buy.setName(s.getName() + " Buy");
        defaultButtonSetup(buy);
        sizeBuy = buy.getPreferredSize();
        buy.setBounds(x+225-(sizeBuy.width / 2), y+30+sizeTitle.height+15+200-5+((y+350 - (y+30+sizeTitle.height+15+200-5))/2)-(sizeBuy.height/2), sizeBuy.width, sizeBuy.height);
        p.add(buy);

        JLabel box = new JLabel(new ImageIcon("ShopItemDisplayBox.png"));
        box.setBounds(x,y,300,350);
        p.add(box);

        p.setBounds(350*cnt++, 0, 350, 400);

        panel.add(p);
    }
    public void displayLockedItems(ShopItemTiles s, JPanel panel, int x, int y){
        JPanel p = new JPanel();
        p.setLayout(null);
        Dimension sizeTitle,sizePrice;

        JButton lock = new JButton(new ImageIcon("lock.png"));
        lock.setName(s.getName() + " Locked");
        defaultButtonSetup(lock);
        lock.setBounds(x,y,300,350);
        p.add(lock);

        JLabel title = new JLabel(s.getName());
        sizeTitle = title.getPreferredSize();
        title.setFont(new Font("Times New Roman", Font.BOLD, 12));
        title.setBounds(x+150-(sizeTitle.width / 2),y+30, sizeTitle.width, sizeTitle.height);
        p.add(title);

        JLabel img = new JLabel(s.getImg());
        img.setBounds(x+50, y+30+sizeTitle.height+15, 200, 200);
        p.add(img);

        JLabel price = new JLabel(s.getPrice() + "", new ImageIcon("shopCoin.png"), JLabel.CENTER);
        price.setFont(new Font("Times New Roman", Font.BOLD, 18));
        sizePrice = price.getPreferredSize();
        price.setBounds(x+150-(sizePrice.width / 2), y+30+sizeTitle.height+15+200+10, sizePrice.width, sizePrice.height);
        p.add(price);

        JLabel box = new JLabel(new ImageIcon("ShopItemDisplayBoxLocked.png"));
        box.setBounds(x,y,300,350);
        p.add(box);

        p.setBounds(350*cnt++, 0, 350, 400);

        panel.add(p);
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
                        } if (Arrays.equals(a, factoriesArray)) {
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
