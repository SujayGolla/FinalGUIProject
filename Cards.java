/*
Name: Sujay and Akaren
Class: ICS 3U7
Teacher: Ms.Strelkovska
*/

import javax.swing.*;
import java.awt.*;

public class Cards extends JFrame{
    static CardLayout layout;
    static Container c;
    static MainMenu m;
    static Homepage h;
    static Shop s;
    static EditOptionPanel iP;
    static Merchant me;
    static Production f;

    public Cards() throws Exception{
        c = getContentPane();
        layout = new CardLayout();
        c.setLayout(layout);

        m = new MainMenu();
        h = new Homepage();
        s = new Shop();
        iP = new EditOptionPanel();
        me = new Merchant();
        f = new Production();

        c.add("MainMenu", m);
        c.add("Homepage", h);
        c.add("Shop", s);
        c.add("Inventory", iP);
        c.add("Merchant", me);
        c.add("Production", f);
    }

    public static void flipToCard(String cardID){
        layout.show(c, cardID);
    }
}
