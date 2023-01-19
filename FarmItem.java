/*
Name: Sujay and Akaren
Class: ICS 3U7
Teacher: Ms.Strelkovska
*/

import javax.swing.*;
import java.awt.*;

public class FarmItem extends ShopItemTiles {
    private int reqPpl;
    private final int size = 30;
    private static final String farms = "Cowshed, Chicken Coop, Sheep Farm";
    public FarmItem(String name, int price, ImageIcon img, ImageIcon[] animations, int unlockLVL, int reqPpl) {
        super(name, price, img, animations, unlockLVL);
        this.reqPpl = reqPpl;
    }

    public int getReqPpl() {
        return reqPpl;
    }

    public boolean canBuyItem(){
        return super.canBuyItem() && Game.getPopulation() >= reqPpl;
    }

    public void purchaseItem() throws Exception {
        if(canBuyItem()){
            Game.setCoins(Game.getCoins() - price);
            Game.setXp(Game.getXp() + 10);
            reqPpl += 35;
        }else{
            if(Game.getLvl() < unlockLVL)
                JOptionPane.showMessageDialog(Cards.c, "You haven't reached Level " + unlockLVL + " yet.", "Can't buy", JOptionPane.WARNING_MESSAGE);
            else if (Game.getCoins() - price >= 0)
                JOptionPane.showMessageDialog(Cards.c, "You don't have enough coins.", "Can't buy", JOptionPane.WARNING_MESSAGE);
            else if (Game.getPopulation() < reqPpl)
                JOptionPane.showMessageDialog(Cards.c, "You don't have the required number of people", "Can't buy", JOptionPane.WARNING_MESSAGE);
        }
        Game.update();
    }
    public static boolean isFarm(String name){
        return farms.contains(name);
    }
    public void myDraw(Graphics g){
        g.drawImage(getRandomImg().getImage(), x, y, size, size,null);
    }
}