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
        JLabel blank = new JLabel(EditOptionPanel.resizeImg(new ImageIcon("Untitled(1).png"), 200, 200));
        center.add(blank);

        for(BarnItem b : items){
            System.out.println(b.getName());
            JPanel panel = new JPanel(null);

            JButton label = new JButton(EditOptionPanel.resizeImg(b.getImg(), 75,75));

            label.setBorderPainted(false);
            label.setContentAreaFilled(false);
            label.setOpaque(false);
            label.setFocusable(false);
            label.addActionListener(this);
            label.setBounds(30, 30, 75,75);
            panel.add(b.getName(), label);

            ShopItemTiles[] requirements = b.getRequirements();

            ArrayList<String> names = new ArrayList<String>();
            ArrayList <Integer> quantity = new ArrayList<Integer>();

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

                JLabel l = new JLabel(quantity.get(names.indexOf(s.getName())) + "");
                l.setIcon(EditOptionPanel.resizeImg(s.getImg(), 30, 30));
                l.setFont(new Font("Times New Roman", Font.BOLD, 8));
                panel.add(l);
            }

//            JButton produce = new JButton(new ImageIcon("produce.png"));
//            produce.setBorderPainted(false);
//            produce.setContentAreaFilled(false);
//            produce.setOpaque(false);
//            produce.setFocusable(false);
//            produce.addActionListener(this);

//            p.add(produce);


            JLabel background = new JLabel(new ImageIcon("productionBox.png"));
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
