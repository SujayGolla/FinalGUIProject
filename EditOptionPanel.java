/*
Name: Sujay and Akaren
Class: ICS 3U7
Teacher: Ms.Strelkovska
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class EditOptionPanel extends JPanel implements ActionListener, MouseListener, MouseMotionListener {
    private JPanel top, topBar, topInventory;
    private JButton back;
    private Edit map;
    private ArrayList<ArrayList<ShopItemTiles>> inventory;
    private ArrayList<ArrayList<Integer>> inventoryQuan;

    public EditOptionPanel(){
        this.setLayout(new BorderLayout());

        back = new JButton(new ImageIcon("back.png"));
        defaultButtonSetup(back);

        top = new JPanel(new BorderLayout());

        topBar = new JPanel(new GridLayout(1, 5));
        topBar.setBackground(Color.GRAY);
        JLabel title = new JLabel("    Edit");
        title.setFont(new Font("Times New Roman", Font.BOLD, 24));
        topBar.add(back);
        topBar.add(new JLabel(""));
        topBar.add(title);
        topBar.add(new JLabel(""));
        topBar.add(new JLabel(""));
        top.add(topBar, BorderLayout.NORTH);

        topInventory = new JPanel(null);
        topInventory.setBackground(new Color(0, 0, 0, 255));
        JLabel l = new JLabel(new ImageIcon("Untitled(3).png"));
        l.setBounds(0,40,1,100);
        topInventory.add(l);
        top.add(topInventory, BorderLayout.SOUTH);

        map = new Edit();

        this.add(map, BorderLayout.CENTER);
        this.add(top, BorderLayout.CENTER);
    }
    public void makeTopInventory(Graphics g){
        inventory = Inventory.getInventory();
        inventoryQuan = Inventory.getInventoryCnt();

        int xCnt = 5;
        int cnt = 0;
        for (int i = 0; i < inventory.size(); i++) {
            for(int j = 0; j < inventory.get(i).size(); j++) {
                g.drawImage(new ImageIcon("inventoryBox.png").getImage(), xCnt + ((cnt * 50) + (cnt * xCnt)), 40, null);
                g.drawImage(resizeImg(inventory.get(i).get(j).getImg(), 30,30).getImage(), xCnt + ((cnt * 50) + (cnt * xCnt)) + 2, 40, null);
                g.setFont(new Font("Times New Roman", Font.BOLD, 12));
                g.drawString("" + inventoryQuan.get(i).get(j), xCnt + ((cnt * 50) + (cnt * xCnt)) + 2 + 30 + 2, 47);
                xCnt+=55;
                cnt++;
            }
        }
    }
    public void defaultButtonSetup(JButton b){
        b.setBorderPainted(false);
        b.setContentAreaFilled(false);
        b.setOpaque(false);
        b.setFocusable(false);
        b.addActionListener(this);
    }
    public static ImageIcon resizeImg(ImageIcon img, int w, int h){
        Image image = img.getImage();
        Image newImg = image.getScaledInstance(w, h,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(newImg);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        makeTopInventory(g);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == back)
            Cards.flipToCard("Homepage");
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
/*

Name: Sujay and Akaren
Class: ICS 3U7
Teacher: Ms.Strelkovska


import javax.swing.*;
        import java.awt.*;
        import java.awt.event.*;
        import java.util.ArrayList;

public class EditOptionPanel extends JPanel implements ActionListener, MouseListener, MouseMotionListener {
    private JPanel top, center, centerLeft, navBar, itemsList, cHou, cFac, cFar, cBas, cSpe;
    private JButton factories, houses, farming, basics, special, back, merchant;
    private CardLayout layout;
    private Edit map;
    private ArrayList<ArrayList<ShopItemTiles>> inventory = Inventory.getInventory();
    private ArrayList<ArrayList<Integer>> inventoryQuan = Inventory.getInventoryCnt();
    private ShopItemTiles currentItem;
    public EditOptionPanel() {
        this.setLayout(new BorderLayout());

        back = new JButton(new ImageIcon("back.png"));
        defaultButtonSetup(back);
        farming = new JButton(new ImageIcon("shopFarming.png"));
        defaultButtonSetup(farming);
        houses = new JButton(new ImageIcon("shopHouses.png"));
        defaultButtonSetup(houses);
        factories = new JButton(new ImageIcon("shopFactories.png"));
        defaultButtonSetup(factories);
        basics = new JButton(new ImageIcon("shopBasics.png"));
        defaultButtonSetup(basics);
        special = new JButton(new ImageIcon("shopSpecials.png"));
        defaultButtonSetup(special);
        merchant = new JButton(resizeImg(new ImageIcon("Merchant.png"), 100,100));
        defaultButtonSetup(merchant);
        merchant.setSize(new Dimension(100,100));

        top = new JPanel(new GridLayout(1, 5));
        top.setBackground(Color.GRAY);
        JLabel title = new JLabel("    Edit");
        title.setFont(new Font("Times New Roman", Font.BOLD, 24));
        top.add(back);
        top.add(new JLabel(""));
        top.add(title);
        top.add(new JLabel(""));
        top.add(new JLabel(""));

        center = new JPanel(new BorderLayout());

        centerLeft = new JPanel(new BorderLayout());
        navBar = new JPanel(new GridLayout(7,1));
        navBar.setBackground(Color.LIGHT_GRAY);
        navBar.add(houses);
        navBar.add(factories);
        navBar.add(farming);
        navBar.add(basics);
        navBar.add(special);
        navBar.add(new JLabel(""));
        navBar.add(merchant);

        layout = new CardLayout();
        itemsList = new JPanel(layout);

        makeItemsList();

        centerLeft.add(navBar, BorderLayout.WEST);
        centerLeft.add(itemsList, BorderLayout.EAST);
        center.add(centerLeft, BorderLayout.WEST);

        map = new Edit();
        center.add(map, BorderLayout.CENTER);

        this.add(top, BorderLayout.NORTH);
        this.add(center, BorderLayout.CENTER);
    }
    public void makeItemsList(){
        cHou = new JPanel(new GridLayout(ShopItemTiles.getNumHouses()+1, 1));
        JLabel l = new JLabel("List of Houses", JLabel.CENTER);
        l.setFont(new Font("Times New Roman", Font.BOLD, 18));
        cHou.add(l);
        cFac = new JPanel(new GridLayout(ShopItemTiles.getNumFactories()+1, 1));
        l = new JLabel("List of Factories", JLabel.CENTER);
        l.setFont(new Font("Times New Roman", Font.BOLD, 18));
        cFac.add(l);
        cFar = new JPanel(new GridLayout(ShopItemTiles.getNumFarms()+1, 1));
        l = new JLabel("List of Farms", JLabel.CENTER);
        l.setFont(new Font("Times New Roman", Font.BOLD, 18));
        cFar.add(l);
        cBas = new JPanel(new GridLayout(ShopItemTiles.getNumBasics()+1, 1));
        l = new JLabel("List of Basics", JLabel.CENTER);
        l.setFont(new Font("Times New Roman", Font.BOLD, 18));
        cBas.add(l);
        cSpe = new JPanel(new GridLayout(ShopItemTiles.getNumSpecials()+1, 1));
        l = new JLabel("List of Specials", JLabel.CENTER);
        l.setFont(new Font("Times New Roman", Font.BOLD, 18));
        cSpe.add(l);
        inventory = Inventory.getInventory();
        inventoryQuan = Inventory.getInventoryCnt();
        for (int i = 0; i < inventory.size(); i++){
            for (int j = 0; j < inventory.get(i).size(); j++){
                System.out.println(inventory.get(i).get(j).getName());
            }
        }
        for (int i = 0; i < inventoryQuan.size(); i++){
            for (int j = 0; j < inventoryQuan.get(i).size(); j++){
                System.out.println(inventoryQuan.get(i).get(j));
            }
        }
        JPanel[] panels = {cHou, cFac, cFar, cBas, cSpe};
        for(int i = 0; i < panels.length; i++){
            JPanel p = panels[i];
            String onPanel = "";
            ArrayList<ShopItemTiles> get = inventory.get(i);
            for (int j = 0; j < get.size(); j++) {
                ShopItemTiles s = get.get(j);
                if (s != null) {
                    if (!onPanel.contains(s.getName()) && !s.isPlaced()) {
                        JPanel panel = new JPanel(null);

                        JButton b = new JButton(resizeImg(s.getImg(), 35, 35));
                        Dimension bSize = b.getPreferredSize();
                        b.setBounds(13 + 50 - (bSize.width / 2), 45 - (bSize.height / 2), bSize.width, bSize.height);
                        defaultButtonSetup(b);
                        b.setName(s.getName() + " Selected");
                        panel.add(b);

                        JLabel label = new JLabel(inventoryQuan.get(i).get(j) + "");
                        Dimension lSize = label.getPreferredSize();
                        label.setBounds(13 + 50 - (lSize.width / 2), 15 - (lSize.height / 2), lSize.width, lSize.height);
                        label.setFont(new Font("Times New Roman", Font.BOLD, 12));
                        panel.add(label);

                        JLabel background = new JLabel(new ImageIcon("inventoryBox.png"));
                        Dimension backGSize = background.getPreferredSize();
                        background.setBounds(13, 0, backGSize.width, backGSize.height);
                        panel.add(background);

                        p.add(panel);
                        onPanel += s.getName();
                    }
                }
            }
        }
        JScrollPane p = new JScrollPane(cHou);
        itemsList.add("Houses", p);

        p = new JScrollPane(cFac);
        itemsList.add("Factories", p);

        p = new JScrollPane(cFar);
        itemsList.add("Farming", p);

        p = new JScrollPane(cBas);
        itemsList.add("Basics", p);

        p = new JScrollPane(cSpe);
        itemsList.add("Specials", p);
    }
    public void update(){
        centerLeft.removeAll();
        centerLeft.add(navBar, BorderLayout.WEST);
        makeItemsList();
        centerLeft.add(itemsList, BorderLayout.EAST);
        center.removeAll();
        center.add(centerLeft, BorderLayout.WEST);
        center.add(map, BorderLayout.CENTER);

        System.out.println("success");
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

        if(b == back)
            Cards.flipToCard("Homepage");
        else if(b == factories)
            centerFlipToCard("Factories");
        else if(b == houses)
            centerFlipToCard("Houses");
        else if(b == farming)
            centerFlipToCard("Farming");
        else if(b == basics)
            centerFlipToCard("Basics");
        else if(b == special)
            centerFlipToCard("Specials");
        else if (b == merchant)
            Cards.flipToCard("Merchant");

        String name = "";
        if(b.getName() != null){
            name = b.getName();
        }
        for(ArrayList<ShopItemTiles> a : inventory){
            for(ShopItemTiles s : a){
                if(name.contains(s.getName())){
                    map.addToMap(s);
                }
            }
        }
    }
    public void centerFlipToCard(String ID){
        layout.show(itemsList, ID);
    }
    public static ImageIcon resizeImg(ImageIcon img, int w, int h){
        Image image = img.getImage();
        Image newImg = image.getScaledInstance(w, h,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(newImg);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Thread thread = new Thread(() -> {
            while(Cards.getCurrentPanelFocus().equals("Inventory")){
                update();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();
    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}

*/