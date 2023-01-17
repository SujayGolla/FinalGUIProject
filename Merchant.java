/*
Name: Sujay and Akaren
Class: ICS 3U7
Teacher: Ms.Strelkovska
*/

import javax.swing.*;
import java.awt.*;

public class Merchant extends JPanel  {

  private int random;
  private String[] items;
  private ShopItemTiles item;
  private int cnt;
  private JPanel top;
  private JButton back;

  public Merchant() {
    this.setLayout(new BorderLayout());
    
    random = (int) (Math.random()*8);
    items = new String[] {"Wheat", "Carrot", "Corn", "Tomatoes", "Rice", "Apples", "Strawberry", "Cotton"};
    
    item = ShopItemTiles.getShopItem(items[random]);
    cnt = 0;
    displaySellableItem(item, 50, 50);

    random = (int) (Math.random()*8);
    item = ShopItemTiles.getShopItem(items[random]);
    displaySellableItem(item, 50, 50);
    
    back = new JButton(new ImageIcon("back.png"));
    back.setBorderPainted(false);
    back.setContentAreaFilled(false);
    back.setOpaque(false);
    back.setFocusable(false);
    //back.addActionListener(this);
    
    
    top = new JPanel(new GridLayout(1, 5));
        top.setBackground(Color.GRAY);
        JLabel title = new JLabel("    Merchant");
        title.setFont(new Font("Times New Roman", Font.BOLD, 24));
        top.add(back);
        top.add(new JLabel(""));
        top.add(title);
        top.add(new JLabel(""));
        top.add(new JLabel(""));
    this.add(top, BorderLayout.NORTH);
  }

  public void displaySellableItem(ShopItemTiles s, int x, int y){
    JPanel p = new JPanel();
    p.setLayout(null);
    Dimension sizeTitle,sizePrice;

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

    JLabel box = new JLabel(new ImageIcon("ShopItemDisplayBox.png"));
    box.setBounds(x,y,300,350);
    p.add(box);

    p.setBounds(350*cnt, 0, 350, 400);
    this.add(p);
}
  public void paintComponent(Graphics g){
    super.paintComponent(g);
  }

}

  