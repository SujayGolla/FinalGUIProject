/*
Name: Sujay and Akaren
Class: ICS 3U7
Teacher: Ms.Strelkovska
Description: This is the shopItemTiles class which includes all the items that the player can buy.
In this class there are arrays with all the items and types of items, and it also includes methods that check if the user can buy items.
*/

import javax.swing.*;
import java.awt.*;

public class ShopItemTiles {
    protected final String name;
    protected final int price;
    protected ImageIcon img;
    protected final ImageIcon[] animations;
    protected final int unlockLVL;
    protected int x = -1;
    protected int y = -1;
    protected static final int numFactories = 5;
    protected static final int numHouses = 4;
    protected static final int numFarms = 3;
    protected static final int numCrops = 7;
    protected static final int numBasics = 3;
    protected static final int numSpecials = 3;
    protected static final String housesNames = "Townhouse, Bungalow, Apartment, Condos";
    protected static final String factoriesNames = "Feed Mill, Dairy Production, Textile Production, Bakery, Fast Food Restaurant";
    protected static final String farmsNames = "Cowshed, Chicken Coop, Sheep Farm";
    protected static final String basicsNames = "Roads, Gravel, Tiles";
    protected static final String specialsNames = "Townhall, Barn, Fountain";
    protected static final String cropsNames = "Wheat, Carrot, Corn, Rice, Apples, Strawberry, Cotton, Tomatoes, Potatoes";
    protected final int size = 30;

    public ShopItemTiles(String name, int price, ImageIcon img, ImageIcon[] animations, int unlockLVL){
        try {
            //create an instance of game
            new Game();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        this.name = name;
        this.price = price;
        this.img = img;
        this.animations = animations;
        this.unlockLVL = unlockLVL;
    }

    public ShopItemTiles(String name, ImageIcon img, ImageIcon[] sprites){
        try {
            //create an instance of game
            new Game();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        this.name = name;
        this.price = 0;
        this.img = img;
        this.animations = sprites;
        this.unlockLVL = 0;
    }

    public void purchaseItem() throws Exception {
        if(canBuyItem()) {
            // substract the price from the total coins
            Game.setCoins(Game.getCoins() - price);
            if(price > 0)
                //increment the xp by 10
                Game.setXp(Game.getXp() + 10);
            //show the success message
            JOptionPane.showMessageDialog(Cards.c, "You successfully purchased the item!", "Success!", JOptionPane.PLAIN_MESSAGE);
        } else {
            //if the lvl is less than the required lvl
            if(Game.getLvl() < unlockLVL)
                JOptionPane.showMessageDialog(Cards.c, "You haven't reached Level " + unlockLVL + " yet.", "Can't buy", JOptionPane.WARNING_MESSAGE);
                //if the coins are not enough
            else if (Game.getCoins() - price >= 0)
                JOptionPane.showMessageDialog(Cards.c, "You don't have enough coins.", "Can't buy", JOptionPane.WARNING_MESSAGE);
        }
        //update the game
        Game.update();
    }

    public boolean canBuyItem(){
        return isUnlocked() && Game.getCoins() - price >= 0;
    }

    public ImageIcon getImg() {
        return img;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public boolean isUnlocked(){
        return Game.getLvl() >= unlockLVL;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static String getBasicsNames() {
        return basicsNames;
    }

    public static String getFactoriesNames() {
        return factoriesNames;
    }

    public static String getFarmsNames() {
        return farmsNames;
    }

    public static String getHousesNames() {
        return housesNames;
    }

    public static String getSpecialsNames() {
        return specialsNames;
    }
    public static String getCropsNames(){
        return cropsNames;
    }
    public boolean isBarnItem(){
        return false;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }


    public static int getNumFactories() {
        return numFactories;
    }

    public int getPpl() {
        return 0;
    }

    public int getReqPpl(){
        return 0;
    }


    public ImageIcon getRandomImg(){
        if(animations != null)
            return animations[0];
        return img;
    }

    public void myDraw(Graphics g){
        g.drawImage(getRandomImg().getImage(), x, y, size, size,null);
    }
    public boolean isPlaced(){
        return x != -1 && y != -1;
    }
    public boolean isOnTile(int mouseX, int mouseY){
        return (x <= mouseX && mouseX <= x+30 && y <= mouseY && mouseY <= y+30);
    }
    public void replaceTile(ShopItemTiles other){
        x = other.x;
        y = other.y;
    }

    // all the shopItems with its name, price, image, level requirement and population
    public static ShopItemTiles getShopItem(String name){
        if (name.equals("Townhouse")) {
            return new HouseItem("Townhouse", 50, new ImageIcon("Townhouse.png"), null, 2, 15);
        } else if (name.equals("Bungalow")) {
            return new HouseItem("Bungalow", 30, new ImageIcon("Bungalow.png"), null, 1, 10);
        } else if (name.equals("Apartment")) {
            return new HouseItem("Apartment", 150, new ImageIcon("Apartment.png"), null, 4, 50);
        } else if (name.equals("Condos")) {
            return new HouseItem("Condos", 300, new ImageIcon("Condo.png"), null, 8, 100);
        } else if (name.equals("Feed Mill")) {
            return new FactoryItem("Feed Mill", 50, new ImageIcon("Feedmill.png"), null, 1, 10);
        } else if (name.equals("Dairy Production")) {
            return new FactoryItem("Dairy Production", 50, new ImageIcon("Dairy.png"), null, 2, 25);
        } else if (name.equals("Textile Production")) {
            return new FactoryItem("Textile Production", 100, new ImageIcon("Textile.png"), null, 4, 50);
        } else if (name.equals("Bakery")) {
            return new FactoryItem("Bakery", 200, new ImageIcon("Bakery.png"), null, 7, 100);
        } else if (name.equals("Fast Food Restaurant")) {
            return new FactoryItem("Fast Food Restaurant", 300, new ImageIcon("Fastfood.png"), null, 9, 120);
        } else if (name.equals("Cowshed")) {
            return new FarmItem("Cowshed", 50, new ImageIcon("Cowshed.png"), null, 1, 10);
        } else if (name.equals("Chicken Coop")) {
            return new FarmItem("Chicken Coop", 150, new ImageIcon("Chicken.png"), null, 2, 20);
        } else if (name.equals("Sheep Farm")) {
            return new FarmItem("Sheep Farm", 300, new ImageIcon("Sheep.png"), null, 5, 65);
        } else if (name.equals("Wheat")) {
            return new ShopItemTiles("Wheat", 5, new ImageIcon("Wheat.png"), null, 1);
        } else if (name.equals("Carrot")) {
            return new ShopItemTiles("Carrot", 5, new ImageIcon("Carrot.png"), null, 1);
        } else if (name.equals("Corn")) {
            return new ShopItemTiles("Corn", 5, new ImageIcon("Corn.png"), null, 2);
        } else if (name.equals("Rice")) {
            return new ShopItemTiles("Rice", 5, new ImageIcon("Rice.png"), null, 4);
        } else if (name.equals("Apples")) {
            return new ShopItemTiles("Apples", 10, new ImageIcon("Apple.png"), null, 5);
        } else if (name.equals("Strawberry")) {
            return new ShopItemTiles("Strawberry", 10, new ImageIcon("Strawberry.png"), null, 6);
        } else if (name.equals("Cotton")) {
            return new ShopItemTiles("Cotton", 5, new ImageIcon("Cotton.png"), null, 2);
        } else if (name.equals("Tomatoes")) {
            return new ShopItemTiles("Tomatoes", 5, new ImageIcon("Tomato.png"), null, 3);
        } else if (name.equals("Potatoes")){
            return new ShopItemTiles("Potatoes", 5, new ImageIcon("Potato.png"), null, 3);
        } else if (name.equals("Roads")) {
            return new ShopItemTiles("Roads", 0, new ImageIcon("Road.png"), new ImageIcon[]{new ImageIcon("Road(1).png")}, 1);
        } else if (name.equals("Gravel")) {
            return new ShopItemTiles("Gravel", 0, new ImageIcon("Gravel.png"), new ImageIcon[]{new ImageIcon("Gravel(1).png")}, 1);
        } else if (name.equals("Tiles")) {
            return new ShopItemTiles("Tiles", 0, new ImageIcon("Tiles.png"), new ImageIcon[]{new ImageIcon("Tiles(1).png")}, 1);
        } else if (name.equals("Barn")) {
            return new ShopItemTiles("Barn", 0, new ImageIcon("Barn.png"), null, 1);
        } else if (name.equals("Townhall")) {
            return new ShopItemTiles("Townhall", 0, new ImageIcon("Townhall.png"), null, 2);
        } else if (name.equals("Fountain")) {
            return new ShopItemTiles("Fountain", 200, new ImageIcon("Fountain.png"), null, 3);
        } else if (name.equals("Grass")) {
            return new SpecialTiles("Grass", new ImageIcon("grass.png"));
        } else if (name.equals("Water")) {
            return new SpecialTiles("Water", new ImageIcon("water.png"));
        } else if (name.equals("Mountains")){
            return new SpecialTiles("Mountains", new ImageIcon("mountain.jpg"), new ImageIcon[]{new ImageIcon("mountain.jpg"), new ImageIcon("mountain1.jpg")});
        }
        return null;
    }
}