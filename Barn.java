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
    private static JPanel s, c;
    private static ArrayList <BarnItem> barnItems = new ArrayList <BarnItem>();
    private static ArrayList <Integer> barnQuan = new ArrayList <Integer>();
    private JButton back;

    public Barn(){
        back = new JButton("back.png");
        this.setLayout(new BorderLayout());
        this.setBackground(Color.GRAY);

        barnItems.clear();
        barnQuan.clear();

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
                if (barnItems.get(i).getName().equals(name.replace('_', ' '))) {
                    barnQuan.set(i,barnQuan.get(i)+1);
                    added = true;
                }
            }


            if (!added) {
                barnItems.add(BarnItem.getBarnItem(name.replace('_', ' ')));
                barnQuan.add(1);
            }
        }
        sc.close();
        makeSouth();
        this.add(s, BorderLayout.SOUTH);
        c = new JPanel();
        JLabel l = new JLabel(new ImageIcon("Untitled(3).png"));
        l.setBounds(0,0,1,100);
        c.add(l);
        this.add(c, BorderLayout.NORTH);

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

    public void makeCenter(Graphics g) {
        int xCnt = 22;
        int yCnt = 40;
        int cnt = 0;
        for (int i = 0; i < barnItems.size(); i++) {
            BarnItem s = barnItems.get(i);
            g.drawImage(new ImageIcon("barnBox.png").getImage(), xCnt, yCnt + 15, null);
            g.drawImage(EditOptionPanel.resizeImg(s.getImg(), 35,35).getImage(), xCnt + 5, yCnt + 15 + 5, null);
            g.drawString("" + barnQuan.get(i), xCnt + 5 + 35 + 10, yCnt + 15 + 5 + 10);
            cnt++;
            xCnt += 100 + 10;
            if(cnt%8 == 0) {
                xCnt = 22;
                yCnt += 70 + 10;
            }
        }
    }

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

    public void addToBarn(BarnItem b) {
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
            gameData.write(fileContent);
            gameData.write(b.getName());
        }
        catch (Exception e) {
            System.out.println(e);
        }
        repaint();
    }

    public static ArrayList<BarnItem> getBarnItems() {
        return barnItems;
    }
    public void removeBarnItem(BarnItem b) {
        for (int i = 0; i < barnItems.size(); i++) {
            if (barnItems.get(i).getName().equals(b.getName())) {
                barnQuan.set(i,barnQuan.get(i)-1);
            }
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
            gameData.write(fileContent);
            gameData.write(b.getName());
        }
        catch (Exception e) {
            System.out.println(e);
        }
        repaint();
    }
}
