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
    private static ArrayList<Integer> housesCnt;
    private static ArrayList<ShopItemTiles> factories;
    private static ArrayList<Integer> factoriesCnt;
    private static ArrayList<ShopItemTiles> farms;
    private static ArrayList<Integer> farmsCnt;
    private static ArrayList<ShopItemTiles> basics;
    private static ArrayList<Integer> basicsCnt;
    private static ArrayList<ShopItemTiles> specials;
    private static ArrayList<Integer> specialsCnt;
    private static ArrayList<ArrayList<ShopItemTiles>> inventory = new ArrayList<ArrayList<ShopItemTiles>>();
    private static ArrayList<ArrayList<Integer>> inventoryCnt = new ArrayList<ArrayList<Integer>>();

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

        housesCnt = new ArrayList<Integer>();
        inventoryCnt.add(housesCnt);
        factoriesCnt = new ArrayList<Integer>();
        inventoryCnt.add(factoriesCnt);
        farmsCnt = new ArrayList<Integer>();
        inventoryCnt.add(farmsCnt);
        basicsCnt = new ArrayList<Integer>();
        inventoryCnt.add(basicsCnt);
        specialsCnt = new ArrayList<Integer>();
        inventoryCnt.add(specialsCnt);

        Scanner sc = null;
        try {
            sc = new Scanner(new File("Inventory.txt"));
        } catch (Exception e) {
            System.out.println(e);
        }
        while (sc.hasNextLine()) {
            boolean added = false;
            String name = sc.nextLine();
            ArrayList<ShopItemTiles> array = null;
            ArrayList<Integer> arrayQuan = null;
            if(ShopItemTiles.getHousesNames().contains(name)) {
                array = houses;
                arrayQuan = housesCnt;
            } else if (ShopItemTiles.getFactoriesNames().contains(name)) {
                array = factories;
                arrayQuan = factoriesCnt;
            } else if (ShopItemTiles.getFarmsNames().contains(name)){
                array = farms;
                arrayQuan = farmsCnt;
            } else if (ShopItemTiles.getBasicsNames().contains(name)){
                array = basics;
                arrayQuan = basicsCnt;
            } else if (ShopItemTiles.getSpecialsNames().contains(name)){
                array = specials;
                arrayQuan = specialsCnt;
            }
            for (int i = 0; i < array.size(); i++) {
                if(array.get(i) != null) {
                    if (array.get(i).getName().equals(name)) {
                        arrayQuan.set(i, arrayQuan.get(i) + 1);
                        added = true;
                    }
                }
            }
            if (!added) {
                array.add(ShopItemTiles.getShopItem(name));
                arrayQuan.add(1);
            }
        }
        sc.close();
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
    public static void addShopItem(ShopItemTiles s) {
        boolean added = false;
        ArrayList<ShopItemTiles> array = null;
        ArrayList<Integer> arrayQuan = null;
        if(ShopItemTiles.getHousesNames().contains(s.getName())) {
            array = houses;
            arrayQuan = housesCnt;
        } else if (ShopItemTiles.getFactoriesNames().contains(s.getName())) {
            array = factories;
            arrayQuan = factoriesCnt;
        } else if (ShopItemTiles.getFarmsNames().contains(s.getName())){
            array = farms;
            arrayQuan = farmsCnt;
        } else if (ShopItemTiles.getBasicsNames().contains(s.getName())){
            array = basics;
            arrayQuan = basicsCnt;
        } else if (ShopItemTiles.getSpecialsNames().contains(s.getName())){
            array = specials;
            arrayQuan = specialsCnt;
        }
        for (int i = 0; i < array.size(); i++) {
            if(array.get(i) != null) {
                if (array.get(i).getName().equals(s.getName())) {
                    arrayQuan.set(i, arrayQuan.get(i) + 1);
                    added = true;
                }
            }
        }
        if (!added) {
            array.add(ShopItemTiles.getShopItem(s.getName()));
            arrayQuan.add(1);
        }

        String fileContent = "";
        try {
            Scanner sc = new Scanner(new File("Inventory.txt"));
            fileContent = "";
            while(sc.hasNextLine()) {
                fileContent += sc.nextLine() + "\n";
            }
            sc.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }

        FileWriter gameData = null;

        try {
            new FileWriter("Inventory.txt", false).close();
            gameData = new FileWriter("Inventory.txt");
            gameData.write(fileContent);
            gameData.write(s.getName());
            gameData.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static ArrayList<ArrayList<ShopItemTiles>> getInventory(){
        return inventory;
    }
    public static ArrayList<ArrayList<Integer>> getInventoryCnt(){
        return inventoryCnt;
    }
}