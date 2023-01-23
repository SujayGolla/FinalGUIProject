/*
Name: Sujay and Akaren
Class: ICS 3U7
Teacher: Ms.Strelkovska
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class EditOptionPanel extends JPanel implements ActionListener, MouseListener {
    private JPanel top, bottom;
    private JButton back, reset;
    private Edit map;
    private ArrayList<ArrayList<ShopItemTiles>> inventory;
    private ArrayList<ArrayList<Integer>> inventoryQuan;
    private ArrayList<Integer> imgCoordinates;
    private ArrayList<String> imgNames;
    public EditOptionPanel(){
        //Getting things from Inventory
        inventory = Inventory.getInventory();
        inventoryQuan = Inventory.getInventoryCnt();
        this.setLayout(new BorderLayout());

        //Button setup
        back = new JButton(new ImageIcon("back.png"));
        defaultButtonSetup(back);
        reset = new JButton(new ImageIcon("reset.png"));
        defaultButtonSetup(reset);

        //Panels setup
        top = new JPanel();

        top.setBackground(new Color(0,0,0,0));
        JLabel l = new JLabel(new ImageIcon("Untitled(3).png"));
        l.setBounds(0,0,1,40);
        top.add(l);

        map = new Edit();

        bottom = new JPanel(new GridLayout(1, 5));
        bottom.setBackground(Color.GRAY);
        JLabel title = new JLabel("       Edit");
        title.setFont(new Font("Times New Roman", Font.BOLD, 24));
        bottom.add(back);
        bottom.add(new JLabel(new ImageIcon("Untitled(3).png")));
        bottom.add(title);
        bottom.add(new JLabel(""));
        bottom.add(reset);

        this.add(map, BorderLayout.CENTER);
        this.add(top, BorderLayout.NORTH);
        this.add(bottom, BorderLayout.SOUTH);
        addMouseListener(this);
    }
    public void makeTopInventory(Graphics gr){
        //To make it display all items in the inventory
        imgCoordinates = new ArrayList<Integer>();
        imgNames = new ArrayList<String>();
        inventory = Inventory.getInventory();
        inventoryQuan = Inventory.getInventoryCnt();

        int xCnt = 5;
        for (int i = 0; i < inventory.size(); i++) {
            for(int j = 0; j < inventory.get(i).size(); j++) {
                gr.drawImage(new ImageIcon("inventoryBox.png").getImage(), xCnt, 10, null);
                gr.drawImage(resizeImg(inventory.get(i).get(j).getImg(), 30, 30).getImage(), xCnt + 2, 10, null);
                imgCoordinates.add(xCnt + 2);
                imgNames.add(inventory.get(i).get(j).getName());
                gr.setFont(new Font("Times New Roman", Font.BOLD, 12));
                gr.drawString("" + inventoryQuan.get(i).get(j), xCnt + 2 + 30 + 2, 27);
                xCnt += 55;
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
        //To resize images
        Image image = img.getImage();
        Image newImg = image.getScaledInstance(w, h,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(newImg);
    }

    public void paintComponent(Graphics gr){
        super.paintComponent(gr);
        makeTopInventory(gr); //Updates Inventory every time the panel is painted
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == back)
            Cards.flipToCard("Homepage");
        else if (e.getSource() == reset)
            map.reset(); //Resets the map
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //To detect which inventory item has been selected and to send to the map for painting
        for(int i = 0; i < imgCoordinates.size(); i++){
            if(imgCoordinates.get(i) <= e.getX() && imgCoordinates.get(i) + 30 >= e.getX() && e.getY() >= 10 && e.getY() <= 40){
                map.addToMap(imgNames.get(i));
            }
        }
        repaint();
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
}