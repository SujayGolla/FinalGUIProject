import javax.swing.*;
import java.util.ArrayList;

public class BarnItem extends ShopItemTiles{
    private final int val;
    private final String factoryName;
    private ShopItemTiles[] requirements;
    private static final BarnItem[][] barn = {{BarnItem.getBarnItem("Bread")}, {BarnItem.getBarnItem("Cow Feed"), BarnItem.getBarnItem("Chicken Feed"), BarnItem.getBarnItem("Sheep Feed")}, {BarnItem.getBarnItem("Cream"), BarnItem.getBarnItem("Cheese"), BarnItem.getBarnItem("Butter")}, {BarnItem.getBarnItem("Cotton Fabric"), BarnItem.getBarnItem("Yarn")}, {BarnItem.getBarnItem("Cheeseburger"), BarnItem.getBarnItem("Sandwich"), BarnItem.getBarnItem("French fries"), BarnItem.getBarnItem("Milkshake")}, {BarnItem.getBarnItem("Milk")}, {BarnItem.getBarnItem("Egg")}, {BarnItem.getBarnItem("Wool")}};
    public BarnItem(String name, ShopItemTiles[] requirements, int val, ImageIcon img, String factoryName){
        super(name, 0, img, null, 0);
        this.val = val;
        this.factoryName = factoryName;
        this.requirements = requirements;
    }
    public static BarnItem getBarnItem(String name){
        if (name.equals("Bread")) {
            return new BarnItem("Bread", new ShopItemTiles[]{ShopItemTiles.getShopItem("Wheat"), ShopItemTiles.getShopItem("Wheat")}, 20, new ImageIcon("Bread.webp"), "Bakery");
        } else if (name.equals("Cow Feed")) {
            return new BarnItem("Cow Feed", new ShopItemTiles[]{ShopItemTiles.getShopItem("Wheat"), ShopItemTiles.getShopItem("Wheat"), ShopItemTiles.getShopItem("Corn")}, 20, new ImageIcon("Cowfeed.webp"), "Feed Mill");
        } else if (name.equals("Chicken Feed")) {
            return new BarnItem("Chicken Feed", new ShopItemTiles[]{ShopItemTiles.getShopItem("Wheat"), ShopItemTiles.getShopItem("Wheat"), ShopItemTiles.getShopItem("Carrot")}, 20, new ImageIcon("Chickenfeed.webp"), "Feed Mill");
        } else if (name.equals("Sheep Feed")) {
            return new BarnItem("Sheep Feed", new ShopItemTiles[]{ShopItemTiles.getShopItem("Corn"), ShopItemTiles.getShopItem("Corn"), ShopItemTiles.getShopItem("Carrot"), ShopItemTiles.getShopItem("Carrot")}, 20, new ImageIcon("Sheepfeed.webp"), "Feed Mill");
        } else if (name.equals("Cream")) {
            return new BarnItem("Cream", new ShopItemTiles[]{BarnItem.getBarnItem("Milk")}, 20, new ImageIcon("Cream.webp"), "Dairy Production");
        } else if (name.equals("Cheese")) {
            return new BarnItem("Cheese", new ShopItemTiles[]{BarnItem.getBarnItem("Milk"), BarnItem.getBarnItem("Milk")}, 20, new ImageIcon("Cheese.webp"), "Dairy Production");
        } else if (name.equals("Butter")) {
            return new BarnItem("Butter", new ShopItemTiles[]{BarnItem.getBarnItem("Milk"), BarnItem.getBarnItem("Milk"), BarnItem.getBarnItem("Milk")}, 20, new ImageIcon("Butter.webp"), "Dairy Production");
        } else if (name.equals("Cotton Fabric")) {
            return new BarnItem("Cotton Fabric", new ShopItemTiles[]{ShopItemTiles.getShopItem("Cotton"), ShopItemTiles.getShopItem("Cotton")}, 20, new ImageIcon("Cotton_fabric.webp"), "Textile Production");
        } else if (name.equals("Yarn")) {
            return new BarnItem("Yarn", new ShopItemTiles[]{BarnItem.getBarnItem("Wool"), BarnItem.getBarnItem("Wool")}, 20, new ImageIcon("Yarn.webp"), "Textile Production");
        } else if (name.equals("Cheeseburger")) {
            return new BarnItem("Cheeseburger", new ShopItemTiles[]{BarnItem.getBarnItem("Bread"), BarnItem.getBarnItem("Cheese"), ShopItemTiles.getShopItem("Tomatoes")}, 20, new ImageIcon("Cheeseburger.webp"), "Fast Food Restaurant");
        } else if (name.equals("Sandwich")) {
            return new BarnItem("Sandwich", new ShopItemTiles[]{BarnItem.getBarnItem("Bread"), BarnItem.getBarnItem("Butter"), ShopItemTiles.getShopItem("Strawberry"), ShopItemTiles.getShopItem("Strawberry")}, 20, new ImageIcon("Sandwich.webp"), "Fast Food Restaurant");
        } else if (name.equals("French fries")) {
            return new BarnItem("French fries", new ShopItemTiles[]{ShopItemTiles.getShopItem("Potato"), ShopItemTiles.getShopItem("Potato"), BarnItem.getBarnItem("Cream")}, 20, new ImageIcon("French_fries.webp"), "Fast Food Restaurant");
        } else if (name.equals("Milkshake")) {
            return new BarnItem("Milkshake", new ShopItemTiles[]{BarnItem.getBarnItem("Milk"), BarnItem.getBarnItem("Milk"), ShopItemTiles.getShopItem("Strawberry")}, 20, new ImageIcon("Milkshake.webp"), "Fast Food Restaurant");
        } else if (name.equals("Milk")) {
            return new BarnItem("Milk", new ShopItemTiles[]{BarnItem.getBarnItem("Cow Feed")}, 20, new ImageIcon("Milk.webp"), "Cowshed");
        } else if (name.equals("Egg")) {
            return new BarnItem("Egg", new ShopItemTiles[]{BarnItem.getBarnItem("Chicken Feed")}, 20, new ImageIcon("Egg.webp"), "Chicken Coop");
        } else if (name.equals("Wool")) {
            return new BarnItem("Wool", new ShopItemTiles[]{BarnItem.getBarnItem("Sheep Feed")}, 20, new ImageIcon("Wool.webp"), "Sheep Farm");
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

    public ShopItemTiles[] getRequirements() {
        return requirements;
    }
}
