/*
Name: Sujay and Akaren
Class: ICS 3U7
Teacher: Ms.Strelkovska
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


public class Map extends JPanel implements MouseWheelListener, KeyListener {
  
      private double zoomFactor = 1;
      private boolean zoomer, translater;
      private char[][] map;

      private int XCoord = 0, YCoord = 0;


    private ArrayList<ShopItemTiles> tiles;
    private Scanner sc;
    private ShopItemTiles currentItem = null;

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
        ArrayList<ArrayList<ShopItemTiles>> inventory = Inventory.getInventory();
        for(ArrayList<ShopItemTiles> a : inventory){
            for(ShopItemTiles s : a){
                if(s.getX() != -1 && s.getY() != -1){
                    tiles.add(s);
                }
            }
        }
    }
    public void addToTiles(String name, int i, int j){
        ShopItemTiles s = ShopItemTiles.getShopItem(name);
        s.setX(j*30);
        s.setY(i*30);
        tiles.add(s);
    }
    public void paintComponent(Graphics g){
      Graphics2D g2 = (Graphics2D) g;
      super.paintComponent(g);

        if (zoomer) {
            g2.scale(zoomFactor, zoomFactor);
            zoomer = false;
        }

        if (translater) {
            g2.translate(XCoord, YCoord);
            translater = false;
        }

        for(int i = 0; i < tiles.size(); i++){
            tiles.get(i).myDraw(g);
        }
        if(currentItem != null)
            currentItem.myDraw(g);
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
      if (zoomFactor > 0.55)
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
                System.out.println("up");
                translater = true;
                YCoord += 10;
                break;
            case KeyEvent.VK_S:
                System.out.println("down");
                translater = true;
                YCoord -= 10;
                break;
            case KeyEvent.VK_A:
                System.out.println("left");
                translater = true;
                XCoord += 10;
                break;
            case KeyEvent.VK_D:
                System.out.println("right");
                translater = true;
                XCoord -= 10;
                break;
            case KeyEvent.VK_PLUS:
                System.out.println("in");
                zoomer = true;
                if (zoomFactor < 1.45)
                    zoomFactor += 0.05;
                break;
            case KeyEvent.VK_MINUS:
                System.out.println("out");
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