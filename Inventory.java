/*
Name: Sujay and Akaren
Class: ICS 3U7
Teacher: Ms.Strelkovska
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Inventory {
    private static ArrayList<ShopItemTiles> houses;
    private static ArrayList<ShopItemTiles> factories;
    private static ArrayList<ShopItemTiles> farms;
    private static ArrayList<ShopItemTiles> basics;
    private static ArrayList<ShopItemTiles> specials;
    private static ArrayList<ArrayList<ShopItemTiles>> inventory = new ArrayList<ArrayList<ShopItemTiles>>();

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
        } catch (Exception e) {
            System.out.println(e);
        }
        for(int i = 0; i < inventory.size(); i++) {
            inventory.get(i).clear();
            if(sc.hasNextLine())
                sc.nextLine();
            else
                break;
            String line = sc.nextLine();
            while(!line.equals("----------")){
                int numItems = Integer.parseInt(line.substring(0, line.indexOf('-')));
                String name = line.substring(line.indexOf('-') + 1).replace('_', ' ');
                addItemsInitialization(inventory.get(i), numItems, name);
                line = sc.nextLine();
            }
        }
        sc.close();
    }
    public static void addItemsInitialization(ArrayList<ShopItemTiles> a, int n, String name){
        for(int i = 0; i < n; i++){
            a.add(ShopItemTiles.getShopItem(name));
        }
    }
    public static void update(){
        try {
            for(ArrayList<ShopItemTiles> a : inventory){
                for(ShopItemTiles s : a){
                    System.out.println(s.getName());
                }
            }
            new FileWriter("Inventory.txt", false).close();
            FileWriter gameData = new FileWriter("Inventory.txt");
            gameData.write("Houses" + "\n");
            gameData.write(specificItemCounter(inventory.get(0), "Townhouse") + "-" + "Townhouse\n");
            gameData.write(specificItemCounter(inventory.get(0), "Bungalow") + "-" + "Bungalow\n");
            gameData.write(specificItemCounter(inventory.get(0), "Apartment") + "-" + "Apartment\n");
            gameData.write(specificItemCounter(inventory.get(0), "Condos") + "-" + "Condos\n");
            gameData.write("----------\n");
            gameData.write("Factories" + "\n");
            gameData.write(specificItemCounter(inventory.get(1), "Feed Mill") + "-" + "Feed_Mill\n");
            gameData.write(specificItemCounter(inventory.get(1), "Dairy Production") + "-" + "Dairy_Factory\n");
            gameData.write(specificItemCounter(inventory.get(1), "Textile Production") + "-" + "Textile_Factory\n");
            gameData.write(specificItemCounter(inventory.get(1), "Bakery") + "-" + "Bakery\n");
            gameData.write(specificItemCounter(inventory.get(1), "Fast Food Restaurant") + "-" + "Fast_Food_Restaurant\n");
            gameData.write("----------\n");
            gameData.write("Farms" + "\n");
            gameData.write(specificItemCounter(inventory.get(2), "Cowshed") + "-" + "Cowshed\n");
            gameData.write(specificItemCounter(inventory.get(2), "Chicken Coop") + "-" + "Chicken_Coop\n");
            gameData.write(specificItemCounter(inventory.get(2), "Sheep Farm") + "-" + "Sheep_Farm\n");
            gameData.write("----------\n");
            gameData.write("Basics" + "\n");
            gameData.write(specificItemCounter(inventory.get(3), "Roads") + "-" + "Roads\n");
            gameData.write(specificItemCounter(inventory.get(3), "Gravel") + "-" + "Gravel\n");
            gameData.write(specificItemCounter(inventory.get(3), "Tiles") + "-" + "Tiles\n");
            gameData.write("----------\n");
            gameData.write("Specials" + "\n");
            gameData.write(specificItemCounter(inventory.get(4), "Barn") + "-" + "Barn\n");
            gameData.write(specificItemCounter(inventory.get(4), "Townhall") + "-" + "Townhall\n");
            gameData.write(specificItemCounter(inventory.get(4), "Fountain") + "-" + "Fountain\n");
            gameData.write("----------");
            gameData.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    public static int specificItemCounter(ArrayList<ShopItemTiles> a, String name){
        int cnt = 0;
        for (int i = 0; i < a.size(); i++) {
            ShopItemTiles s = a.get(i);
            if (name.equals(s.getName())) {
                cnt++;
            }
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