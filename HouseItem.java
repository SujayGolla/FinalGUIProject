/*
Name: Sujay and Akaren
Class: ICS 3U7
Teacher: Ms.Strelkovska
*/

import javax.swing.*;

public class HouseItem extends ShopItemTiles {
    private final int ppl;
    private static final String houses = "Bungalow, Townhouse, Apartment, Condos";
    public HouseItem(String name, int price, ImageIcon img, ImageIcon[] animations, int unlockLVL, int ppl) {
        super(name, price, img, animations, unlockLVL);
        this.ppl = ppl;
    }

    public int getPpl() {
        return ppl;
    }
    public void purchaseItem() throws Exception {
        if(canBuyItem()) {
            Game.setCoins(Game.getCoins() - price);
            Game.setXp(Game.getXp() + 10);
            Game.setPopulation(Game.getPopulation() + ppl);
        }else {
            if(Game.getLvl() < unlockLVL)
                JOptionPane.showMessageDialog(Cards.getC(), "You haven't reached Level " + unlockLVL + " yet.", "Can't buy", JOptionPane.WARNING_MESSAGE);
            else if (Game.getCoins() - price >= 0)
                JOptionPane.showMessageDialog(Cards.getC(), "You don't have enough coins.", "Can't buy", JOptionPane.WARNING_MESSAGE);
            else if (Game.getPopulation() + ppl <= Game.getMaxPopulation())
                JOptionPane.showMessageDialog(Cards.getC(), "Exceeds Max Population for this level", "Can't buy", JOptionPane.WARNING_MESSAGE);
        }
        Game.update();
    }
    public boolean canBuyItem(){
        return super.canBuyItem() && Game.getPopulation() + ppl <= Game.getMaxPopulation();
    }
    public static boolean isHouse(String name){
        return houses.contains(name);
    }
}
