/*
Name: Sujay and Akaren
Class: ICS 3U7
Teacher: Ms.Strelkovska
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;

public class Edit extends JPanel implements MouseMotionListener, MouseListener, MouseWheelListener {
    private char[][] map;
    private ArrayList<ShopItemTiles> tiles;
    private Scanner sc;
    private ShopItemTiles currentItem = null;
    private int xCnt = 30;
    private int yCnt = 30;
    private double zoomFactor = 1;
    private boolean zoomer;
    private int zoomPointX;
    private int zoomPointY;
    private boolean zoomIn;

    public Edit(){
        try {
            sc = new Scanner(new File("Map.txt"));
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        map = new char[20][50];
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
                if(s.isPlaced()){
                    tiles.add(s);
                }
            }
        }
        addMouseListener(this);
        addMouseMotionListener(this);
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
        addMouseWheelListener(this);
        if (zoomer) {
          AffineTransform at = g2.getTransform();
          if (zoomIn) {
            at.translate(zoomPointX, zoomPointY);
            at.scale(zoomFactor, zoomFactor);
            at.translate(-zoomPointX, -zoomPointY);
            g2.setTransform(at);
          }
          else {
            at.scale(zoomFactor, zoomFactor);
            g2.transform(at);
          }
          zoomer = false;
        }
        for(int i = 0; i < tiles.size(); i++){
            tiles.get(i).myDraw(g);
        }
        if(currentItem != null)
            currentItem.myDraw(g);
    }
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        for(int i = 0; i < tiles.size(); i++){
            if(tiles.get(i).isOnTile(e.getX(), e.getY())) {
                currentItem = tiles.get(i);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for(int i = 0; i < tiles.size(); i++){
            if(tiles.get(i).isOnTile(e.getX(), e.getY()) && currentItem != null && !currentItem.isSpecialTile()) {
                currentItem.replaceTile(tiles.get(i));
                tiles.set(i, currentItem);
            }
        }
        repaint();
        currentItem = null;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(currentItem != null && !currentItem.isSpecialTile()){
            currentItem.setX(e.getX());
            currentItem.setY(e.getY());
        }
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        zoomPointX = e.getX();
        zoomPointY = e.getY();
        zoomer = true;
        //Zoom in
        if (e.getWheelRotation() < 0) {
          zoomIn = true;
          if (zoomFactor < 1.45)
            zoomFactor += 0.05;
        }
        else {
          zoomIn = false;
          if (zoomFactor > 0.65)
            zoomFactor -= 0.05;
        }
        repaint();
    }
    public void addToMap(ShopItemTiles s){
        boolean isPlaced = false;
        while(!isPlaced) {
            boolean hasTileInitialLocation = false;
            for (int i = 0; i < tiles.size(); i++) {
                if (tiles.get(i).getX() == xCnt && tiles.get(i).getY() == yCnt && !tiles.get(i).isSpecialTile()) {
                    hasTileInitialLocation = true;
                    xCnt += 30;
                    yCnt += 30;
                    break;
                }
            }
            if (!hasTileInitialLocation){
                s.setX(xCnt);
                s.setY(yCnt);
                tiles.add(s);
                isPlaced = true;
            }
        }
        xCnt = 180;
        yCnt = 180;
    }
}