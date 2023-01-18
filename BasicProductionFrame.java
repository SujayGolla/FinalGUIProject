import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BasicProductionFrame extends JFrame implements ActionListener {
    private String facilityName;
    private BarnItem[] items;
    private Container c;
    private JPanel center;
    private boolean isProcessing;
    public BasicProductionFrame(String facilityName, BarnItem[] items){
        this.setSize(500,150);
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
//        JLabel blank = new JLabel(EditOptionPanel.resizeImg(new ImageIcon("Untitled(1).png"), 250, 200));
//        center.add(blank);

        for(BarnItem b : items){
            System.out.println(b.getName());
            JPanel panel = new JPanel(null);

            JButton label = new JButton(b.getImg());
            label.setBorderPainted(false);
            label.setContentAreaFilled(false);
            label.setOpaque(false);
            label.setFocusable(false);
            label.addActionListener(this);
            label.setBounds(10, 10, 40,40);
            panel.add(b.getName(), label);

            ShopItemTiles[] requirements = b.getRequirements();
            JPanel p = new JPanel(new GridLayout(requirements.length, 1));
            for(ShopItemTiles s : requirements){
                JLabel l = new JLabel(Inventory.specificItemCounter(requirements, s.getName()) + "", EditOptionPanel.resizeImg(s.getImg(), 18, 18), JLabel.CENTER);
                l.setFont(new Font("Times New Roman", Font.BOLD, 8));
                p.add(l);
            }
            p.setLocation(55,5);
            panel.add(p);

            JLabel background = new JLabel(new ImageIcon("inventoryBox.png"));
            Dimension backGSize = background.getPreferredSize();
            background.setBounds(0,0,backGSize.width, backGSize.height);
            panel.add(background);

            center.add(panel);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
    public boolean isProducing(){
        return isProcessing;
    }
}
