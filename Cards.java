/*
Name: Sujay and Akaren
Class: ICS 3U7
Teacher: Ms.Strelkovska
Description: Manages the card layout and switches between panels
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
    static Barn b;
    static String currentPanelFocus = "MainMenu";

    static Instructions1 ins;
    static Instructions2 ins2;
    static Instructions3 ins3;

    public Cards() throws Exception{
        // Get the content pane and set its layout to card layout
        c = getContentPane();
        layout = new CardLayout();
        c.setLayout(layout);

        // Initialize all the panels
        m = new MainMenu();
        h = new Homepage();
        s = new Shop();
        iP = new EditOptionPanel();
        me = new Merchant();
        f = new Production();
        b = new Barn();
        ins = new Instructions1();
        ins2 = new Instructions2();
        ins3 = new Instructions3();

        // Add all the panels to the container
        c.add("MainMenu", m);
        c.add("Homepage", h);
        c.add("Shop", s);
        c.add("Inventory", iP);
        c.add("Merchant", me);
        c.add("Production", f);
        c.add("Barn",b);
        c.add("Instructions1", ins);
        c.add("Instructions2", ins2);
        c.add("Instructions3", ins3);
    }

    public static void flipToCard(String cardID){
        // Show the card specified by cardID
        layout.show(c, cardID);
        // Update the current panel in focus
        currentPanelFocus = cardID;
    }

    public static String getCurrentPanelFocus() {
        return currentPanelFocus;
    }
}