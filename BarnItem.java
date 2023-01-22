import javax.swing.*;
import java.util.ArrayList;

public class BarnItem extends ShopItemTiles{
    private static int val;
    private final String factoryName;
    private ShopItemTiles[] requirements;
    private static final int barnItemCnt = 16;
    private static final BarnItem[][] barn = {{BarnItem.getBarnItem("Bread")}, {BarnItem.getBarnItem("Cow Feed"), BarnItem.getBarnItem("Chicken Feed"), BarnItem.getBarnItem("Sheep Feed")}, {BarnItem.getBarnItem("Cream"), BarnItem.getBarnItem("Cheese"), BarnItem.getBarnItem("Butter")}, {BarnItem.getBarnItem("Cotton Fabric"), BarnItem.getBarnItem("Yarn")}, {BarnItem.getBarnItem("Cheeseburger"), BarnItem.getBarnItem("Sandwich"), BarnItem.getBarnItem("Milkshake"), BarnItem.getBarnItem("French fries")}, {BarnItem.getBarnItem("Milk")}, {BarnItem.getBarnItem("Egg")}, {BarnItem.getBarnItem("Wool")}};
    public BarnItem(String name, ShopItemTiles[] requirements, int val, ImageIcon img, String factoryName){
        super(name, 0, img, null, 0);
        this.val = val;
        this.factoryName = factoryName;
        this.requirements = requirements;

        try {
            new Game();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static BarnItem getBarnItem(String name){
        if (name.equals("Bread")) {
            return new BarnItem("Bread", new ShopItemTiles[]{ShopItemTiles.getShopItem("Wheat"), ShopItemTiles.getShopItem("Wheat")}, 20, new ImageIcon("Bread.png"), "Bakery");
        } else if (name.equals("Cow Feed")) {
            return new BarnItem("Cow Feed", new ShopItemTiles[]{ShopItemTiles.getShopItem("Wheat"), ShopItemTiles.getShopItem("Wheat"), ShopItemTiles.getShopItem("Corn")}, 20, new ImageIcon("Cowfeed.png"), "Feed Mill");
        } else if (name.equals("Chicken Feed")) {
            return new BarnItem("Chicken Feed", new ShopItemTiles[]{ShopItemTiles.getShopItem("Wheat"), ShopItemTiles.getShopItem("Wheat"), ShopItemTiles.getShopItem("Carrot")}, 20, new ImageIcon("Chickenfeed.png"), "Feed Mill");
        } else if (name.equals("Sheep Feed")) {
            return new BarnItem("Sheep Feed", new ShopItemTiles[]{ShopItemTiles.getShopItem("Corn"), ShopItemTiles.getShopItem("Corn"), ShopItemTiles.getShopItem("Carrot"), ShopItemTiles.getShopItem("Carrot")}, 20, new ImageIcon("Sheepfeed.png"), "Feed Mill");
        } else if (name.equals("Cream")) {
            return new BarnItem("Cream", new ShopItemTiles[]{BarnItem.getBarnItem("Milk")}, 20, new ImageIcon("Cream.png"), "Dairy Production");
        } else if (name.equals("Cheese")) {
            return new BarnItem("Cheese", new ShopItemTiles[]{BarnItem.getBarnItem("Milk"), BarnItem.getBarnItem("Milk")}, 20, new ImageIcon("Cheese.png"), "Dairy Production");
        } else if (name.equals("Butter")) {
            return new BarnItem("Butter", new ShopItemTiles[]{BarnItem.getBarnItem("Milk"), BarnItem.getBarnItem("Milk"), BarnItem.getBarnItem("Milk")}, 20, new ImageIcon("Butter.png"), "Dairy Production");
        } else if (name.equals("Cotton Fabric")) {
            return new BarnItem("Cotton Fabric", new ShopItemTiles[]{ShopItemTiles.getShopItem("Cotton"), ShopItemTiles.getShopItem("Cotton")}, 20, new ImageIcon("Cotton_fabric.png"), "Textile Production");
        } else if (name.equals("Yarn")) {
            return new BarnItem("Yarn", new ShopItemTiles[]{BarnItem.getBarnItem("Wool"), BarnItem.getBarnItem("Wool")}, 20, new ImageIcon("Yarn.png"), "Textile Production");
        } else if (name.equals("Cheeseburger")) {
            return new BarnItem("Cheeseburger", new ShopItemTiles[]{BarnItem.getBarnItem("Bread"), BarnItem.getBarnItem("Cheese"), ShopItemTiles.getShopItem("Tomatoes")}, 20, new ImageIcon("Cheeseburger.png"), "Fast Food Restaurant");
        } else if (name.equals("Sandwich")) {
            return new BarnItem("Sandwich", new ShopItemTiles[]{BarnItem.getBarnItem("Bread"), BarnItem.getBarnItem("Butter"), ShopItemTiles.getShopItem("Strawberry"), ShopItemTiles.getShopItem("Strawberry")}, 20, new ImageIcon("Sandwich.png"), "Fast Food Restaurant");
        } else if (name.equals("French fries")) {
            return new BarnItem("French fries", new ShopItemTiles[]{ShopItemTiles.getShopItem("Potatoes"), ShopItemTiles.getShopItem("Potatoes"), BarnItem.getBarnItem("Cream")}, 20, new ImageIcon("French_fries.png"), "Fast Food Restaurant");
        } else if (name.equals("Milkshake")) {
            return new BarnItem("Milkshake", new ShopItemTiles[]{BarnItem.getBarnItem("Milk"), BarnItem.getBarnItem("Milk"), ShopItemTiles.getShopItem("Strawberry")}, 20, new ImageIcon("Milkshake.png"), "Fast Food Restaurant");
        } else if (name.equals("Milk")) {
            return new BarnItem("Milk", new ShopItemTiles[]{BarnItem.getBarnItem("Cow Feed")}, 20, new ImageIcon("Milk.png"), "Cowshed");
        } else if (name.equals("Egg")) {
            return new BarnItem("Egg", new ShopItemTiles[]{BarnItem.getBarnItem("Chicken Feed")}, 20, new ImageIcon("Egg.png"), "Chicken Coop");
        } else if (name.equals("Wool")) {
            return new BarnItem("Wool", new ShopItemTiles[]{BarnItem.getBarnItem("Sheep Feed")}, 20, new ImageIcon("Wool.png"), "Sheep Farm");
        }
        return null;
    }
    public boolean canBuyItem(){
        ArrayList<ArrayList<ShopItemTiles>> inventory = Inventory.getInventory();
        for(ShopItemTiles s : requirements){
            boolean found = false;
            myLabel:
            for(ArrayList<ShopItemTiles> a : inventory){
                for (ShopItemTiles item : a){
                    if(item.getName().equals(s.getName())) {
                        found = true;
                        a.remove(item);
                        break myLabel;
                    }
                }
            }
            if(!found)
                return false;
        }
        return true;
    }

    public static BarnItem[][] getBarn() {
        return barn;
    }

    public static int getBarnCnt() {
        return barnItemCnt;
    }

    public ShopItemTiles[] getRequirements() {
        return requirements;
    }

    public static int getValue() {
        return val;
    }

    public void sellItem() throws Exception {
        if(hasItem()) {
            Game.setCoins(Game.getCoins() + val);
            if(val > 0)
                Game.setXp(Game.getXp() + 10);
            JOptionPane.showMessageDialog(Cards.c, "You successfully sold the item!", "Success!", JOptionPane.PLAIN_MESSAGE);
        } else {
                JOptionPane.showMessageDialog(Cards.c, "You don't have that item.", "Can't sell", JOptionPane.WARNING_MESSAGE);
        }
        Game.update();
    }

    public boolean hasItem() {
        for (int i = 0; i < Barn.getBarn().size(); i++) {
            if (name.equals(Barn.getBarn().get(i).getName()))
                return true;
        }
        return false;
    }

}