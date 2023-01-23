/*
Name: Sujay and Akaren
Class: ICS 3U7
Teacher: Ms.Strelkovska
Description: This is the barn class, which keeps all the barnItems that the player has
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
    private static JPanel s, c;
    private static ArrayList <BarnItem> barnItems = new ArrayList <BarnItem>();
    private static ArrayList <Integer> barnQuan = new ArrayList <Integer>();
    private JButton back;

    public Barn(){
        back = new JButton("back.png");
        this.setLayout(new BorderLayout());
        this.setBackground(Color.GRAY);

        barnItems.clear(); //clears the items so that it doesnt overlap when the txt file is read and re-adds the items into the array
        barnQuan.clear(); //clears the items so that it doesnt overlap when the txt file is read and re-adds the items into the array

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
            for (int i = 0; i < barnItems.size(); i++) { // if the item is already in the arraylist with all the names of the items
                if (barnItems.get(i).getName().equals(name.replace('_', ' '))) {
                    barnQuan.set(i,barnQuan.get(i)+1); // increase the quantity
                    added = true;
                }
            }


            if (!added) { // if the item isnt already in the arraylist with all the names of the items
                barnItems.add(BarnItem.getBarnItem(name.replace('_', ' ')));
                barnQuan.add(1); // increase the quantity
            }
        }
        sc.close();

        makeSouth();
        // creating the borderLayout.south panel
        this.add(s, BorderLayout.SOUTH);
        c = new JPanel();
        JLabel l = new JLabel(new ImageIcon("Untitled(3).png"));
        l.setBounds(0,0,1,100);
        c.add(l);
        this.add(c, BorderLayout.NORTH);

        // refreshes the panel every 10 milliseconds in case the player gets new barnItems
        Thread thread = new Thread(() -> {
            while(true){
                repaint();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();
    }

    // Showing all the barn items
    public void makeCenter(Graphics g) {
        int xCnt = 22; // the counter for the space between each barn item (x value)
        int yCnt = 40;// the counter for the space between each barn item (y value)
        int cnt = 0; // the cnt for the number of barnItems
        for (int i = 0; i < barnItems.size(); i++) {
            BarnItem s = barnItems.get(i);
            g.drawImage(new ImageIcon("barnBox.png").getImage(), xCnt, yCnt + 15, null);
            g.drawImage(EditOptionPanel.resizeImg(s.getImg(), 35,35).getImage(), xCnt + 15, yCnt + 27 + 5, null); //some formatting values done through trial and error
            g.setFont(new Font("Times New Roman", Font.BOLD, 18));
            g.drawString("" + barnQuan.get(i), xCnt + 15 + 35 + 15, yCnt + 40 + 5 + 10);//some formatting values done through trial and error
            cnt++;
            xCnt += 100 + 10; // the cnt for the spacing between each item
            if(cnt%8 == 0) { // increasing the gap even more if theres a new row
                xCnt = 22;
                yCnt += 70 + 10;
            }
        }
        ArrayList<ShopItemTiles> crops = Inventory.getCrops();
        ArrayList<Integer> cropsCnt = Inventory.getCropsCnt();

        for(int i = 0; i < crops.size(); i++){
            ShopItemTiles s = crops.get(i);
            g.drawImage(new ImageIcon("barnBox.png").getImage(), xCnt, yCnt + 15, null);
            g.drawImage(EditOptionPanel.resizeImg(s.getImg(), 35,35).getImage(), xCnt + 15, yCnt + 27 + 5, null); //some formatting values done through trial and error
            g.setFont(new Font("Times New Roman", Font.BOLD, 18));
            g.drawString("" + cropsCnt.get(i), xCnt + 15 + 35 + 15, yCnt + 40 + 5 + 10);//some formatting values done through trial and error
            cnt++;
            xCnt += 100 + 10; // the cnt for the spacing between each item
            if(cnt%8 == 0) { // increasing the gap even more if theres a new row
                xCnt = 22;
                yCnt += 70 + 10;
            }
        }
    }


    // The title with the back button
    public void makeSouth(){
        back = new JButton(new ImageIcon("back.png"));
        defaultButtonSetup(back);

        s = new JPanel();
        s.setLayout(new GridLayout(1,9));
        s.setBackground(Color.LIGHT_GRAY);

        JLabel title = new JLabel("  Barn");
        title.setFont(new Font("Times New Roman", Font.BOLD, 30));
        s.add(back);
        s.add(new JLabel(""));
        s.add(new JLabel(""));
        s.add(new JLabel(""));
        s.add(title);
        s.add(new JLabel(""));
        s.add(new JLabel(""));
        s.add(new JLabel(""));
        s.add(new JLabel(""));
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        makeCenter(g);
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
    }


    public static ArrayList<BarnItem> getBarn() {
        return barnItems;
    }


    // adding a barnItem to the barn
    public static void addToBarn(BarnItem b) {
        boolean added = false;
        for (int i = 0; i < barnItems.size(); i++) { // if we already have the item in the barn
            if (barnItems.get(i).getName().equals(b.getName())) {
                barnQuan.set(i,barnQuan.get(i)+1); // increasing the quantity
                added = true;
            }
        }

        if (!added) { // if we dont already have the item in the barn
            barnItems.add(BarnItem.getBarnItem(b.getName())); // adding the name to the arraylist
            barnQuan.add(1);
        }

        FileWriter gameData = null;

        // rewriting the file and deleting the old one
        try {
            new FileWriter("barn.txt", false).close();
            gameData = new FileWriter("barn.txt");
            for (int i = 0; i < barnItems.size(); i++){
                for(int j = 0; j < barnQuan.get(i); j++) {
                    gameData.write(barnItems.get(i).getName() + "\n");
                }
            }
            gameData.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public static ArrayList<Integer> getBarnQuan() {
        return barnQuan;
    }

    // removing a barnItem from the barn
    public static void removeBarnItem(BarnItem b) {
        for (int i = 0; i < barnItems.size(); i++) { // if we already have the item in the barn
            if (barnItems.get(i).getName().equals(b.getName())) {
                barnQuan.set(i,barnQuan.get(i)-1); // decreasing the quantity
            }
        }

        FileWriter gameData = null;
        // rewriting the file and deleting the old one
        try {
            new FileWriter("barn.txt", false).close();
            gameData = new FileWriter("barn.txt");
            for (int i = 0; i < barnItems.size(); i++){
                for(int j = 0; j < barnQuan.get(i); j++) {
                    gameData.write(barnItems.get(i).getName() + "\n");
                }
            }
            gameData.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
