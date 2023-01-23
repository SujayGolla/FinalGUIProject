/*
Name: Sujay and Akaren
Class: ICS 3U7
Teacher: Ms.Strelkovska
Description: Sets the basic frame for the production class and all the factories
*/

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

    // constructor that takes in the facility name and items as arguments
    public BasicProductionFrame(String facilityName, BarnItem[] items) {
        // sets the size, visibility, and layout of the JFrame
        this.setSize(500, 300);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.facilityName = facilityName;
        this.items = items;
        c = getContentPane();
        c.setLayout(new BorderLayout());
        isProcessing = false;

        // creates a label for the facility name and adds it to the JFrame
        JLabel title = new JLabel(facilityName);
        title.setFont(new Font("Times New Roman", Font.BOLD, 14));
        JPanel p = new JPanel(new FlowLayout());
        p.add(title);
        c.add(p, BorderLayout.NORTH);

        // creates a panel to hold the BarnItems and sets it to a grid layout
        center = new JPanel(new GridLayout(1, items.length + 1));
        makeCenter();
        // adds the panel to a scroll pane and adds it to the JFrame
        JScrollPane scrollPane = new JScrollPane(center);
        c.add(scrollPane, BorderLayout.CENTER);
    }

    // method that creates the BarnItem panels and adds them to the center panel
    public void makeCenter() {
        // if there are more than 2 items, add a blank label to center the BarnItems
        if (items.length > 2) {
            JLabel blank = new JLabel(EditOptionPanel.resizeImg(new ImageIcon("Untitled(1).png"), 205, 220));
            center.add(blank);
        }

        // for each BarnItem, create a panel
        for (BarnItem b : items) {
            ShopItemTiles[] requirements = b.getRequirements();

            ArrayList<String> names = new ArrayList<String>();
            ArrayList<Integer> quantity = new ArrayList<Integer>();

            JPanel panel = new JPanel(null);

            // add a button for the BarnItem with the item's image and name
            JButton label = new JButton(EditOptionPanel.resizeImg(b.getImg(), 75, 75));
            label.setName(b.getName());
            label.setBorderPainted(false);
            label.setContentAreaFilled(false);
            label.setOpaque(false);
            label.setFocusable(false);
            label.addActionListener(this);
            label.setBounds(100 - 37, 30, 75, 75);
            panel.add(label);

            // Add the "Requirements:" label
            JLabel jl = new JLabel("Requirements:");
            jl.setFont(new Font("Times New Roman", Font.BOLD, 10));
            Dimension size = jl.getPreferredSize();
            jl.setBounds(100 - (size.width / 2), 125, size.width, size.height);
            panel.add(jl);

            int xCnt = 30;

            for (ShopItemTiles s : requirements) {
                boolean added = false;

                for (int i = 0; i < names.size(); i++) {
                    String name = names.get(i);
                    if (name.equals(s.getName())) {
                        // if the item already exists in the list, increase the quantity by 1
                        quantity.set(i, quantity.get(i) + 1);
                        added = true;
                    }
                }

                if (!added) {
                    // if the item doesn't exist in the list, add it to the list
                    names.add(s.getName());
                    quantity.add(1);
                }

                JLabel l = new JLabel(EditOptionPanel.resizeImg(s.getImg(), 30, 30));
                l.setBounds(xCnt, 140, 30, 30);
                panel.add(l);
                xCnt += 40;
            }


            JLabel background = new JLabel(new ImageIcon("productionBox.png"));
            Dimension backGSize = background.getPreferredSize();
            background.setBounds(0, 0, backGSize.width, backGSize.height);
            panel.add(background);

            center.add(panel);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton) e.getSource();
        String name = "";
        if (b.getName() != null)
            name = b.getName();
        for (int i = 0; i < items.length; i++) {
            if (items[i].getName().equals(name)) {
                // if the item is selected, add it to the barn
                JOptionPane.showMessageDialog(Cards.c, "Item successfully added to Barn!", "Success!", JOptionPane.PLAIN_MESSAGE);
                Barn.addToBarn(BarnItem.getBarnItem(items[i].getName()));
                break;
            }
        }
    }

    public boolean isProducing() {
        return isProcessing;
    }
}
