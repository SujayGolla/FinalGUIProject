/*
Name: Sujay and Akaren
Class: ICS 3U7
Teacher: Ms.Strelkovska
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Merchant extends JPanel  implements ActionListener{

  private int random;
  private BarnItem item1, item2;
  private int cnt = 0;
  private JPanel top, center;
  private JButton back;

  public Merchant() {
    this.setLayout(new BorderLayout());

    new Barn();

    back = new JButton(new ImageIcon("back.png"));
    back.setBorderPainted(false);
    back.setContentAreaFilled(false);
    back.setOpaque(false);
    back.setFocusable(false);
    back.addActionListener(this);

    center = new JPanel(new GridLayout(1,2));

    random = (int) (Math.random()*Barn.getBarnItems().size());
    item1 = Barn.getBarnItems().get(random);
    displaySellableItem(item1, 50, 50);


    do {
        random = (int) (Math.random() * Barn.getBarnItems().size());
        item2 = Barn.getBarnItems().get(random);
    }
    while (item1.getName().equals(item2.getName()));

      displaySellableItem(item2, 50, 50);

    this.add(center, BorderLayout.CENTER);


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

  public void displaySellableItem(BarnItem s, int x, int y){
    JPanel p = new JPanel();
    p.setLayout(null);
    Dimension sizeTitle,sizePrice, sizeSell;

   JLabel title = new JLabel(s.getName());
    sizeTitle = title.getPreferredSize();
    title.setFont(new Font("Times New Roman", Font.BOLD, 12));
    title.setBounds(x+150-(sizeTitle.width / 2),y+30, sizeTitle.width, sizeTitle.height);
    p.add(title);

    JLabel img = new JLabel(EditOptionPanel.resizeImg(s.getImg(),40,40));
    img.setBounds(x+50, y+30+sizeTitle.height+15, 200, 200);
    p.add(img);

    JLabel price = new JLabel(s.getValue() + "", new ImageIcon("shopCoin.png"), JLabel.CENTER);
    price.setFont(new Font("Times New Roman", Font.BOLD, 18));
    sizePrice = price.getPreferredSize();
    price.setBounds(x+75-(sizePrice.width / 2), y+30+sizeTitle.height+15+200-5+((y+350 - (y+30+sizeTitle.height+15+200-5))/2)-(sizePrice.height/2), sizePrice.width, sizePrice.height);
    p.add(price);

    JLabel box = new JLabel(new ImageIcon("ShopItemDisplayBox.png"));
    box.setBounds(x,y,300,350);
    p.add(box);

      JButton sell = new JButton(new ImageIcon("sell.png"));
      sell.setName(s.getName() + " Sell");
      sell.setBorderPainted(false);
      sell.setContentAreaFilled(false);
      sell.setOpaque(false);
      sell.setFocusable(false);
      sell.addActionListener(this);
      sizeSell = sell.getPreferredSize();
      sell.setBounds(x+225-(sizeSell.width / 2), y+30+sizeTitle.height+15+200-5+((y+350 - (y+30+sizeTitle.height+15+200-5))/2)-(sizeSell.height/2), sizeSell.width, sizeSell.height);
      p.add(sell);

    p.setBounds(350*cnt++, 0, 350, 400);
    center.add(p);
}
  public void paintComponent(Graphics g){
    super.paintComponent(g);
  }

  public void actionPerformed(ActionEvent e){
        JButton b = (JButton) e.getSource();
        String name = "";
        if(b.getName() != null)
            name = b.getName();

        if(b == back)
            Cards.flipToCard("Homepage");
        else {
            for (int i = 0; i < Barn.getBarnItems().size(); i++) {
                BarnItem a = Barn.getBarnItems().get(i);
                    if(a != null) {
                        if (name.startsWith(a.getName())) {
                            try {
                                a.sellItem(a);
                                if (BarnItem.hasItem(a)) {
                                    new Barn().removeBarnItem(a);
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

