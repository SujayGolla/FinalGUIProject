/*
Name: Sujay and Akaren
Class: ICS 3U7
Teacher: Ms.Strelkovska
Description: This is the map class where it reads a file to make the default map and then saves the buildings that are added onto the map
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Map extends JPanel implements MouseWheelListener, KeyListener {
  
      protected double zoomFactor = 1;
      protected boolean zoomer, translater;
      protected char[][] map;
      protected int xCoord = 0, yCoord = 0;


    protected static ArrayList<ShopItemTiles> tiles;
    protected static ArrayList<ShopItemTiles> items;
    protected Scanner sc;

    public Map(){
        addKeyListener(this);
        addMouseWheelListener(this);
        this.setFocusable(true); // makes the game detect key pressing
        try {
            sc = new Scanner(new File("Map.txt")); // reading the txt to make the default map
            new Game();
        } catch (Exception e) {
            System.out.println(e);
        }
        map = new char[20][61]; // reading the map and making the map according to its type and the row and column
        tiles = new ArrayList<ShopItemTiles>();
        for(int i = 0; i < map.length; i++){
            for(int j= 0; j < map[i].length; j++){
                map[i][j] = sc.next().charAt(0);
                if(map[i][j] == 'g') {
                    addToTiles("Grass", i, j);
                } else if(map[i][j] == 'w'){
                    addToTiles("Water", i, j);
                } else if(map[i][j] == 'm'){
                    addToTiles("Mountains", i, j);
                }
            }
        }
        sc.close();
        makeItems();
    }
    public void makeItems(){
        items = new ArrayList<ShopItemTiles>();
        ArrayList<ArrayList<ShopItemTiles>> inventory = Inventory.getInventory();
        ArrayList<ArrayList<Integer>> inventoryQuan = Inventory.getInventoryCnt();
        for (int i = 0; i < inventory.size(); i++){
            for (int j = 0; j < inventory.get(i).size(); j++){
                for(int k = 0; k < inventoryQuan.get(i).get(j); k++) {
                    items.add(ShopItemTiles.getShopItem(inventory.get(i).get(j).getName()));
                }
            }
        }

        try {
            sc = new Scanner(new File("MapItems.txt"));
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                String name = line.substring(0, line.indexOf("="));
                name = name.replace('_', ' ');
                int x = Integer.parseInt(line.substring(line.indexOf("=") + 1, line.indexOf(",")));
                int y = Integer.parseInt(line.substring(line.indexOf(",") + 1));
                for (int i = 0; i < items.size(); i++){
                    if(name.equals(items.get(i).getName()) && !items.get(i).isPlaced()){
                        items.get(i).setX(x);
                        items.get(i).setY(y);
                    }
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    public boolean nothingOnTile(ShopItemTiles s){
        for(int i = 0; i < items.size(); i++){
            if(s.isOnTile(items.get(i).getX(), items.get(i).getY()))
                return false;
        }
        return true;
    }
    public void saveMap() {
        try {
            new FileWriter("MapItems.txt", false).close(); // clear the file
            FileWriter gameData = new FileWriter("MapItems.txt"); // create new file writer
            for(int i = 0; i < items.size(); i++){
                gameData.write(items.get(i).getName().replace(' ', '_') + "=" + items.get(i).getX() + "," + items.get(i).getY()+"\n"); // write to the file
            }
            gameData.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addToTiles(String name, int i, int j){
        ShopItemTiles s = ShopItemTiles.getShopItem(name);
        s.setX(j*30);
        s.setY(i*30);
        tiles.add(s);
    }
    public void paintComponent(Graphics g){
        requestFocusInWindow();
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g);
        saveMap();

        // If the mouse is scrolled zoomer = true
        if (zoomer) {
            g2.scale(zoomFactor, zoomFactor); // zooming in/out
            g2.translate(xCoord, yCoord); // then translating it to the point where it was previously translated to
            zoomer = false;
        }

        if (translater) { // if they press a translate key
            g2.translate(xCoord, yCoord); // translate first
            g2.scale(zoomFactor, zoomFactor); // then zoom in.out
            translater = false;
        }


        for(int i = 0; i < tiles.size(); i++){
            tiles.get(i).myDraw(g);
        }
        for(int i = 0; i < items.size(); i++){
            if(items.get(i).isPlaced())
                items.get(i).myDraw(g);
        }
    }
  @Override
  public void mouseWheelMoved(MouseWheelEvent e) {
    zoomer = true;
    //Zoom in
    if (e.getWheelRotation() < 0) { // if they zoom in
      if (zoomFactor < 1.45) // putting restrictions in order to not zoom in too much
        zoomFactor += 0.05;
    }
    else {
      if (zoomFactor > 1) // putting restrictions in order to not zoom out too much
        zoomFactor -= 0.05;
    }
    repaint(); // repainting the entire map
  }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_W:
                translater = true;
                if(yCoord + 10 <= 0) // restrictions so that they dont go beyond the map
                    yCoord += 10;
                break;
            case KeyEvent.VK_S:
                translater = true;
                if(yCoord - 10 >= -200) // restrictions so that they dont go beyond the map
                    yCoord -= 10;
                break;
            case KeyEvent.VK_A:
                translater = true;
                if(xCoord + 10 <= 0) // restrictions so that they dont go beyond the map
                    xCoord += 10;
                break;
            case KeyEvent.VK_D:
                translater = true;
                if(xCoord - 10 >= -700) // restrictions so that they dont go beyond the map
                    xCoord -= 10;
                break;
        }
        repaint();
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
}