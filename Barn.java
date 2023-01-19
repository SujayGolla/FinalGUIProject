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
        sc.close();
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
}
