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
    private JPanel top, bottom;
    private JButton back, reset;
    private Edit map;
    private ArrayList<ArrayList<ShopItemTiles>> inventory;
    private ArrayList<ArrayList<Integer>> inventoryQuan;
    private ArrayList<Integer> imgCoordinates = new ArrayList<Integer>();
    private ArrayList<ShopItemTiles> itemTiles = new ArrayList<ShopItemTiles>();
    public EditOptionPanel(){
        new Inventory();
        inventory = Inventory.getInventory();
        inventoryQuan = Inventory.getInventoryCnt();
        this.setLayout(new BorderLayout());

        back = new JButton(new ImageIcon("back.png"));
        defaultButtonSetup(back);
        reset = new JButton(new ImageIcon("reset.png"));
        defaultButtonSetup(reset);

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
        bottom.add(new JLabel(new ImageIcon("Untitled.png")));
        bottom.add(title);
        bottom.add(new JLabel(""));
        bottom.add(reset);

        this.add(map, BorderLayout.CENTER);
        this.add(top, BorderLayout.NORTH);
        this.add(bottom, BorderLayout.SOUTH);
        addMouseListener(this);
        addMouseMotionListener(this);
    }
    public void makeTopInventory(Graphics gr){
        imgCoordinates = new ArrayList<Integer>();
        itemTiles = new ArrayList<ShopItemTiles>();

        int xCnt = 5;
        String displayed = "";
        for (int i = 0; i < inventory.size(); i++) {
            for(int j = 0; j < inventory.get(i).size(); j++) {
                if(!displayed.contains(inventory.get(i).get(j).getName())) {
                    gr.drawImage(new ImageIcon("inventoryBox.png").getImage(), xCnt, 10, null);
                    gr.drawImage(resizeImg(inventory.get(i).get(j).getImg(), 30, 30).getImage(), xCnt + 2, 10, null);
                    imgCoordinates.add(xCnt + 2);
                    itemTiles.add(inventory.get(i).get(j));
                    gr.setFont(new Font("Times New Roman", Font.BOLD, 12));
                    gr.drawString("" + inventoryQuan.get(i).get(j), xCnt + 2 + 30 + 2, 27);
                    xCnt += 55;
                    displayed += inventory.get(i).get(j).getName() + " ";
                }
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

    public void paintComponent(Graphics gr){
        super.paintComponent(gr);
        makeTopInventory(gr);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == back)
            Cards.flipToCard("Homepage");
        else if (e.getSource() == reset)
            map.reset();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        myLabel:
        for(int i = 0; i < imgCoordinates.size(); i++){
            if(imgCoordinates.get(i) <= e.getX() && imgCoordinates.get(i) + 30 >= e.getX() && e.getY() >= 10 && e.getY() <= 40){
                int num = specifiedItemInventoryQuan(itemTiles.get(i).getName());
                for(int j = 0; j < inventory.size(); j++){
                    for(int k = 0; k < inventory.get(j).size(); k++){
                        if(itemTiles.get(i).getName().equals(inventory.get(j).get(k).getName()) && num > 0){
                            if(!inventory.get(j).get(k).isPlaced()){
                                map.addToMap(inventory.get(j).get(k));
                                break myLabel;
                            }
                            num--;
                        }
                    }
                }
            }
        }
        repaint();
    }
    public int specifiedItemInventoryQuan(String name){
        for(int i = 0; i < inventory.size(); i++){
            for(int j = 0; j < inventory.get(i).size(); j++){
                if(inventory.get(i).get(j).getName().equals(name)){
                    return inventoryQuan.get(i).get(j);
                }
            }
        }
        return 0;
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