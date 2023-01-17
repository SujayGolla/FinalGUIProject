/*
Name: Sujay and Akaren
Class: ICS 3U7
Teacher: Ms.Strelkovska
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Inventory {
    private static ArrayList<ShopItemTiles> houses;
    private static ArrayList<ShopItemTiles> factories;
    private static ArrayList<ShopItemTiles> farms;
    private static ArrayList<ShopItemTiles> basics;
    private static ArrayList<ShopItemTiles> specials;
    private static ArrayList<ArrayList<ShopItemTiles>> inventory = new ArrayList<ArrayList<ShopItemTiles>>();
    private static int cnt = 0;

    public Inventory() {
        houses = new ArrayList<ShopItemTiles>();
        inventory.add(houses);
        factories = new ArrayList<ShopItemTiles>();
        inventory.add(factories);
        farms = new ArrayList<ShopItemTiles>();
        inventory.add(farms);
        basics = new ArrayList<ShopItemTiles>();
        inventory.add(basics);
        specials = new ArrayList<ShopItemTiles>();
        inventory.add(specials);

        Scanner sc = null;
        try {
            sc = new Scanner(new File("Inventory.txt"));
            new ShopItemTiles();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        cnt++;
        System.out.println(cnt);
        for(ArrayList<ShopItemTiles> a : inventory) {
            int tmp = 0;
            if(a == houses)
                tmp = ShopItemTiles.getNumHouses();
            else if (a == factories)
                tmp = ShopItemTiles.getNumFactories();
            else if (a == farms)
                tmp = ShopItemTiles.getNumFarms();
            else if (a == basics)
                tmp = ShopItemTiles.getNumBasics();
            else if (a == specials)
                tmp = ShopItemTiles.getNumSpecials();

            sc.nextLine();
            for(int i = 0; i < tmp; i++){
                String line = sc.nextLine();
                int numItems = Integer.parseInt(line.substring(0, line.indexOf("-")));
                String name = line.substring(line.indexOf("-") + 1);
                name = name.replace('_', ' ');
                System.out.println(i + " "+line + " " + numItems + " " + name);
                addItemsInitialization(a, numItems, name);
            }
            sc.nextLine();
        }
    }
    public static void addItemsInitialization(ArrayList<ShopItemTiles> a, int n, String name){
        for(int i = 0; i < n; i++){
            a.add(ShopItemTiles.getShopItem(name));
        }
    }
    public static void update(){
        try {
            new FileWriter("Inventory.txt", false).close();
            FileWriter gameData = new FileWriter("Inventory.txt");
            gameData.write("Houses" + "\n");
            gameData.write(specificItemCounter(houses, "Townhouse") + "-" + "Townhouse\n");
            gameData.write(specificItemCounter(houses, "Bungalow") + "-" + "Bungalow\n");
            gameData.write(specificItemCounter(houses, "Apartment") + "-" + "Apartment\n");
            gameData.write(specificItemCounter(houses, "Condos") + "-" + "Condos\n");
            gameData.write("----------\n");
            gameData.write("Factories" + "\n");
            gameData.write(specificItemCounter(factories, "Feed Mill") + "-" + "Feed_Mill\n");
            gameData.write(specificItemCounter(factories, "Dairy Factory") + "-" + "Dairy_Factory\n");
            gameData.write(specificItemCounter(factories, "Textile Factory") + "-" + "Textile_Factory\n");
            gameData.write(specificItemCounter(factories, "Bakery") + "-" + "Bakery\n");
            gameData.write(specificItemCounter(factories, "Fast Food Restaurant") + "-" + "Fast_Food_Restaurant\n");
            gameData.write("----------\n");
            gameData.write("Farms" + "\n");
            gameData.write(specificItemCounter(farms, "Field") + "-" + "Field\n");
            gameData.write(specificItemCounter(farms, "Cowshed") + "-" + "Cowshed\n");
            gameData.write(specificItemCounter(farms, "Chicken Coop") + "-" + "Chicken_Coop\n");
            gameData.write(specificItemCounter(farms, "Sheep Farm") + "-" + "Sheep_Farm\n");
            gameData.write("----------\n");
            gameData.write("Basics" + "\n");
            gameData.write(specificItemCounter(basics, "Roads") + "-" + "Roads\n");
            gameData.write(specificItemCounter(basics, "Gravel") + "-" + "Gravel\n");
            gameData.write(specificItemCounter(basics, "Tiles") + "-" + "Tiles\n");
            gameData.write("----------\n");
            gameData.write("Specials" + "\n");
            gameData.write(specificItemCounter(specials, "Barn") + "-" + "Barn\n");
            gameData.write(specificItemCounter(specials, "Townhall") + "-" + "Townhall\n");
            gameData.write(specificItemCounter(specials, "Fountain") + "-" + "Fountain\n");
            gameData.write("----------");
            gameData.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    public static int specificItemCounter(ArrayList<ShopItemTiles> a, String name){
        int cnt = 0;
        for(ShopItemTiles s : a){
            if(s.getName().equals(name))
                cnt++;
        }
        return cnt;
    }
    public static int specificItemCounter(ShopItemTiles[] a, String name){
        int cnt = 0;
        for(int i = 0; i < a.length; i++){
            if(a[i].getName().equals(name))
                cnt++;
        }
        return cnt;
    }
    public static void addShopItem(ShopItemTiles s, String category) {
        if (category.equals("Houses")) {
            houses.add(ShopItemTiles.getShopItem(s.getName()));
        } else if (category.equals("Factories")) {
            factories.add(ShopItemTiles.getShopItem(s.getName()));
        } else if (category.equals("Farms")) {
            farms.add(ShopItemTiles.getShopItem(s.getName()));
        } else if (category.equals("Basics")) {
            basics.add(ShopItemTiles.getShopItem(s.getName()));
        } else if (category.equals("Specials")) {
            specials.add(ShopItemTiles.getShopItem(s.getName()));
        }
        update();
        new EditOptionPanel().update();
    }
    public static ArrayList<ArrayList<ShopItemTiles>> getInventory(){
        return inventory;
    }
}