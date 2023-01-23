/*
Name: Sujay and Akaren
Class: ICS 3U7
Teacher: Ms.Strelkovska
Description: This class is for the shop class and when the player tries to buy a factory. This is similar to the shopitem class but has different requirements than some other items
*/

import javax.swing.*;
import java.awt.*;

public class FactoryItem extends ShopItemTiles {
    private int reqPpl;
    private final int size = 30;
    private static final String factories = "Feed Mill, Dairy Production, Textile Production, Meat Production, Bakery, Fast Food Restaurant";
    public FactoryItem(String name, int price, ImageIcon img, ImageIcon[] animations, int unlockLVL, int reqPpl) {
        super(name, price, img, animations, unlockLVL);
        this.reqPpl = reqPpl;
    }

    public int getReqPpl() {
        return reqPpl;
    }

    public void setReqPpl(int reqPpl) {
        this.reqPpl = reqPpl;
    }
    public void purchaseItem() throws Exception {
        if(canBuyItem()){ // if they can buy the fatory (meet the requirements)
            Game.setCoins(Game.getCoins() - price);
            Game.setXp(Game.getXp() + 10); // increases their exp
            setReqPpl(getReqPpl() + 35); // increases the requirement of the number of people needed
        }else{
            if(Game.getLvl() < unlockLVL) // if the player has a lower level
                JOptionPane.showMessageDialog(Cards.getC(), "You haven't reached Level " + unlockLVL + " yet.", "Can't buy", JOptionPane.WARNING_MESSAGE);
            else if (Game.getCoins() - price >= 0) // if the player doesnt have enough money
                JOptionPane.showMessageDialog(Cards.getC(), "You don't have enough coins.", "Can't buy", JOptionPane.WARNING_MESSAGE);
            else if (Game.getPopulation() < reqPpl) // if they dont have a high enough population
                JOptionPane.showMessageDialog(Cards.getC(), "You don't have the required number of people", "Can't buy", JOptionPane.WARNING_MESSAGE);
        }
        Game.update();
    }
    public static boolean isFactory(String name){
        return factories.contains(name);
    }
    public void myDraw(Graphics g){
        g.drawImage(getRandomImg().getImage(), x, y, size, size,null);
    }
}
