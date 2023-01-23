/*
Name: Sujay and Akaren
Class: ICS 3U7
Teacher: Ms.Strelkovska
*/

import java.util.*;
import java.io.*;
public class Game{
    private static int lvl;
    private static int xp;
    private static int coins;
    private static int population;
    private static int maxPopulation;

    public Game() throws Exception{
        // Reading the file to identify the amount for each type (population, coins, lvl, xp, max population)
        Scanner sc = new Scanner(new File("GameData.txt"));
        lvl = Integer.parseInt(sc.nextLine().substring(1,2));
        xp = Integer.parseInt(sc.nextLine().substring(2));
        population = Integer.parseInt(sc.nextLine().substring(1));
        coins = Integer.parseInt(sc.nextLine().substring(2));
        maxPopulation = lvl * 15;
    }

    // Getter and Setter Methods
    public static int getCoins() {
        return coins;
    }

    public static int getLvl() {
        return lvl;
    }

    public static int getPopulation() {
        return population;
    }

    public static int getXp() {
        return xp;
    }


    public static void setCoins(int c) {
        coins = c;
    }


    public static void setPopulation(int p) {
        population = p;
    }

    public static void setXp(int x) {
        xp = x;
    }

    public static int getMaxPopulation() {
        return maxPopulation;
    }


    // Updating the game if one of the values is changed when playing the game
    public static void update() throws Exception {
        lvl = (int)(xp/100.0);
        maxPopulation = lvl * 15;
        new FileWriter("GameData.txt", false).close();
        FileWriter gameData = new FileWriter("GameData.txt");
        gameData.write("L"+lvl + "\nXP" + xp + "\nP" + population + "\nCo" + coins);
        gameData.close();
    }
}