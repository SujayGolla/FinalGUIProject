import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class BasicProductionFrame extends JFrame implements ActionListener {
    private String facilityName;
    private BarnItem[] items;
    private Container c;
    private JPanel center;
    private boolean isProcessing;
    public BasicProductionFrame(String facilityName, BarnItem[] items){
        this.setSize(500,300);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.facilityName = facilityName;
        this.items = items;
        c = getContentPane();
        c.setLayout(new BorderLayout());
        isProcessing = false;

        JLabel title = new JLabel(facilityName);
        title.setFont(new Font("Times New Roman", Font.BOLD, 14));
        JPanel p = new JPanel(new FlowLayout());
        p.add(title);
        c.add(p, BorderLayout.NORTH);

        center = new JPanel(new GridLayout(1, items.length+1));
        makeCenter();
        JScrollPane scrollPane = new JScrollPane(center);
        c.add(scrollPane, BorderLayout.CENTER);
    }
    public void makeCenter(){
        if(items.length > 2) {
            JLabel blank = new JLabel(EditOptionPanel.resizeImg(new ImageIcon("Untitled(1).png"), 205, 220));
            center.add(blank);
        }

        for(BarnItem b : items){
            ShopItemTiles[] requirements = b.getRequirements();

            ArrayList<String> names = new ArrayList<String>();
            ArrayList <Integer> quantity = new ArrayList<Integer>();

            JPanel panel = new JPanel(null);

            JButton label = new JButton(EditOptionPanel.resizeImg(b.getImg(), 75,75));
            label.setName(b.getName());
            label.setBorderPainted(false);
            label.setContentAreaFilled(false);
            label.setOpaque(false);
            label.setFocusable(false);
            label.addActionListener(this);
            label.setBounds(100-37, 30, 75,75);
            panel.add(label);

            JLabel jl = new JLabel("Requirements:");
            jl.setFont(new Font("Times New Roman", Font.BOLD, 10));
            Dimension size = jl.getPreferredSize();
            jl.setBounds(100-(size.width/2),125,size.width,size.height);
            panel.add(jl);

            int xCnt = 30;
            for(ShopItemTiles s : requirements){
                boolean added = false;

                for (int i = 0; i < names.size(); i++) {
                    String name = names.get(i);
                    if (name.equals(s.getName())) {
                        quantity.set(i, quantity.get(i) + 1);
                        added = true;
                    }
                }

                if (!added) {
                    names.add(s.getName());
                    quantity.add(1);
                }

                JLabel l = new JLabel(EditOptionPanel.resizeImg(s.getImg(), 30, 30));
                l.setBounds(xCnt,140, 30, 30);
                panel.add(l);
                xCnt += 40;
            }


            JLabel background = new JLabel(new ImageIcon("productionBox.png"));
            Dimension backGSize = background.getPreferredSize();
            background.setBounds(0,0,backGSize.width, backGSize.height);
            panel.add(background);

            center.add(panel);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton) e.getSource();
        String name = "";
        if(b.getName() != null)
            name = b.getName();
        for(int i = 0; i < items.length; i++){
            if(items[i].getName().equals(name)){
                JOptionPane.showMessageDialog(Cards.c, "Item successfully added to Barn!", "Success!", JOptionPane.PLAIN_MESSAGE);
                Barn.addToBarn(BarnItem.getBarnItem(items[i].getName()));
                break;
            }
        }
    }
    public boolean isProducing(){
        return isProcessing;
    }
}
