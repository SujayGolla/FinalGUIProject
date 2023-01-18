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
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class Barn extends JPanel implements ActionListener{
    private static JPanel n, c;
    private static CardLayout layout;
    private static ArrayList <BarnItem> barnItems = new ArrayList <BarnItem>();
    private static ArrayList <Integer> barnQuan = new ArrayList <Integer>();
    private static int cnt = 0;
    private JButton back;

    public Barn(){
        back = new JButton("back.png");
        this.setLayout(new BorderLayout());
        this.setBackground(Color.GRAY);

        Scanner sc = null;
        try {
            sc = new Scanner(new File("barn.txt"));
        }
        catch(Exception e) {
            System.out.println(e);
        }

        while (sc.hasNextLine()) {
            boolean added = false;
            String name = sc.nextLine();
            for (int i = 0; i < barnItems.size(); i++) {
                if (barnItems.get(i).getName().equals(name)) {
                    barnQuan.set(i,barnQuan.get(i)+1);
                    added = true;
                }
            }


            if (!added) {
                barnItems.add(BarnItem.getBarnItem(name));
                barnQuan.add(1);
            }
        }

        makeNorth();
        this.add(n, BorderLayout.NORTH);
        makeCenter();
        this.add(c, BorderLayout.CENTER);

    }

    public void makeCenter() {
        c = new JPanel();
        c.setLayout(new GridLayout(2,8,10,10));
        for (int i = 0; i < barnItems.size(); i++) {

            BarnItem s = barnItems.get(i);
            JPanel panel = new JPanel(null);

            JLabel b = new JLabel (EditOptionPanel.resizeImg(s.getImg(), 35,35));
            Dimension bSize = b.getPreferredSize();
            b.setBounds(13+50-(bSize.width/2),45-(bSize.height/2),bSize.width, bSize.height);
            System.out.println(s.getName());
            panel.add(s.getName(), b);

            JLabel l = new JLabel(""+barnQuan.get(i));
            Dimension lSize = l.getPreferredSize();
            l.setBounds(13+50-(lSize.width/2),15-(lSize.height/2),lSize.width, lSize.height);
            l.setFont(new Font("Times New Roman", Font.BOLD, 12));
            panel.add(l);

            JLabel background = new JLabel(new ImageIcon("inventoryBox.png"));
            Dimension backGSize = background.getPreferredSize();
            background.setBounds(13,0,backGSize.width, backGSize.height);
            panel.add(background);

            c.add(panel);
        }
        for (int i = 0; i < BarnItem.getBarnCnt()-barnItems.size(); i++) {
            c.add(new JLabel(new ImageIcon ("Untitled(2).png")));
        }

    }

    public void makeNorth(){
        back = new JButton(new ImageIcon("back.png"));
        defaultButtonSetup(back);

        n = new JPanel();
        n.setLayout(new BorderLayout());
        n.setBackground(Color.GRAY);
        JPanel titleN = new JPanel();
        titleN.setLayout(new GridLayout(1,9));
        titleN.setBackground(Color.LIGHT_GRAY);

        n.add(titleN, BorderLayout.NORTH);
        JLabel title = new JLabel("  Barn");
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
    }

    //public static ArrayList<ArrayList<Object>> getBarn() {
      //  return barn;
    //}

    public static void addToBarn(BarnItem b) {
        boolean added = false;
        for (int i = 0; i < barnItems.size(); i++) {
            if (barnItems.get(i).getName().equals(b.getName())) {
                barnQuan.set(i,barnQuan.get(i)+1);
                added = true;
            }
        }

        if (!added) {
            barnItems.add(BarnItem.getBarnItem(b.getName()));
            barnQuan.add(1);
        }

        Scanner sc = null;

        try {
            sc = new Scanner(new File("barn.txt"));
         }
        catch (Exception e) {
            System.out.println(e);
        }
        String fileContent = "";
        while(sc.hasNextLine()) {
            fileContent += sc.nextLine() + "\n";
        }

        FileWriter gameData = null;

        try {
            new FileWriter("barn.txt", false).close();
            gameData = new FileWriter("barn.txt");
            for (int i = 0; i < barnItems.size(); i++) {
                gameData.write(fileContent);
                gameData.write("\n"+b.getName());
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }


    }



}
