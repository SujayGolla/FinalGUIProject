/*
Name: Sujay and Akaren
Class: ICS 3U7
Teacher: Ms.Strelkovska
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
        this.setFocusable(true);
        try {
            sc = new Scanner(new File("Map.txt"));
            new Inventory();
            new Game();
        } catch (Exception e) {
            System.out.println(e);
        }
        map = new char[20][61];
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
        try {
            sc = new Scanner(new File("MapItems.txt"));
            items = new ArrayList<ShopItemTiles>();
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                int cnt = 0;
                for(int i = 0; i < 252; i++) {
                    String nextL = "";
                    if(sc.hasNextLine())
                        nextL = sc.nextLine();
                    if(!nextL.equals(line))
                        break;
                }
                String name = line.substring(0, line.indexOf("="));
                name = name.replace('_', ' ');
                int x = Integer.parseInt(line.substring(line.indexOf("=") + 1, line.indexOf(",")));
                int y = Integer.parseInt(line.substring(line.indexOf(",") + 1));
                ShopItemTiles s = ShopItemTiles.getShopItem(name);
                if (!s.isSpecialTile()) {
                    s.setX(x);
                    s.setY(y);
                    items.add(s);
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void saveMap() {
        try {
            new FileWriter("MapItems.txt", false).close();
            FileWriter gameData = new FileWriter("MapItems.txt");
            for(int i = 0; i < items.size(); i++){
                gameData.write(items.get(i).getName().replace(' ', '_') + "=" + items.get(i).getX() + "," + items.get(i).getY()+"\n");
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


        if (zoomer) {
            g2.scale(zoomFactor, zoomFactor);
            g2.translate(xCoord, yCoord);
            zoomer = false;
        }

        if (translater) {
            g2.translate(xCoord, yCoord);
            g2.scale(zoomFactor, zoomFactor);
            translater = false;
        }

        for(int i = 0; i < tiles.size(); i++){
            tiles.get(i).myDraw(g);
        }
        for(int i = 0; i < items.size(); i++){
            items.get(i).myDraw(g);
        }
    }
  @Override
  public void mouseWheelMoved(MouseWheelEvent e) {
    zoomer = true;
    //Zoom in
    if (e.getWheelRotation() < 0) {
      if (zoomFactor < 1.45)
        zoomFactor += 0.05;
    }
    else {
      if (zoomFactor > 1)
        zoomFactor -= 0.05;
    }
    repaint();
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
                if(yCoord + 10 <= 0)
                    yCoord += 10;
                break;
            case KeyEvent.VK_S:
                translater = true;
                if(yCoord - 10 >= -200)
                    yCoord -= 10;
                break;
            case KeyEvent.VK_A:
                translater = true;
                if(xCoord + 10 <= 0)
                    xCoord += 10;
                break;
            case KeyEvent.VK_D:
                translater = true;
                if(xCoord - 10 >= -830)
                    xCoord -= 10;
                break;
            case KeyEvent.VK_PLUS:
                zoomer = true;
                if (zoomFactor < 1.45)
                    zoomFactor += 0.05;
                break;
            case KeyEvent.VK_MINUS:
                zoomer = true;
                if (zoomFactor > 0.55)
                    zoomFactor -= 0.05;
                break;
        }
        repaint();
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
}