/*
Name: Sujay and Akaren
Class: ICS 3U7
Teacher: Ms.Strelkovska
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
    protected final int size = 30;

    public ShopItemTiles(String name, int price, ImageIcon img, ImageIcon[] animations, int unlockLVL){
        try {
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
            Game.setCoins(Game.getCoins() - price);
            Game.setXp(Game.getXp() + 10);
            JOptionPane.showMessageDialog(Cards.c, "You successfully purchased the item!", "Success!", JOptionPane.PLAIN_MESSAGE);
        }else {
            if(Game.getLvl() < unlockLVL)
                JOptionPane.showMessageDialog(Cards.c, "You haven't reached Level " + unlockLVL + " yet.", "Can't buy", JOptionPane.WARNING_MESSAGE);
            else if (Game.getCoins() - price >= 0)
                JOptionPane.showMessageDialog(Cards.c, "You don't have enough coins.", "Can't buy", JOptionPane.WARNING_MESSAGE);
        }
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

    public ImageIcon[] getAnimations() {
        return animations;
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

    public void setX(int x) {
        this.x = x;
    }

    public boolean isSpecialTile(){
        return false;
    }

    public void setY(int y) {
        this.y = y;
    }

    public static int getNumHouses() {
        return numHouses;
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

    public static int getNumFarms() {
        return numFarms;
    }

    public ImageIcon getRandomImg(){
        return img;
    }

    public static int getNumCrops() {
        return numCrops;
    }

    public static int getNumBasics() {
        return numBasics;
    }

    public static int getNumSpecials() {
        return numSpecials;
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
            return new ShopItemTiles("Roads", 0, new ImageIcon("Road.png"), null, 1);
        } else if (name.equals("Gravel")) {
            return new ShopItemTiles("Gravel", 0, new ImageIcon("Gravel.png"), null, 1);
        } else if (name.equals("Tiles")) {
            return new ShopItemTiles("Tiles", 0, new ImageIcon("Tiles.png"), null, 1);
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