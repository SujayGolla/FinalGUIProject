/*
Name: Sujay and Akaren
Class: ICS 3U7
Teacher: Ms.Strelkovska
*/

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Edit extends Map implements MouseListener, MouseWheelListener, KeyListener {
    private ShopItemTiles currentItem = null;
    public Edit(){
        super();
        addMouseListener(this);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }
    public void mouseClicked(MouseEvent e) {
        for(int i = 0; i < items.size(); i++){
            if(items.get(i).isOnTile(e.getX(), e.getY())) {
                currentItem = items.get(i);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

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
                if(yCoord - 10 >= -280)
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
            case KeyEvent.VK_UP:
                if(currentItem != null){
                    if(currentItem.getY() - 30 >= 0){
                        currentItem.setY(currentItem.getY() - 30);
                    }
                }
            case KeyEvent.VK_DOWN:
                if(currentItem != null){
                    if(currentItem.getY() + 60 <= 600){
                        currentItem.setY(currentItem.getY() + 30);
                    }
                }
            case KeyEvent.VK_RIGHT:
                if(currentItem != null){
                    if(currentItem.getX() + 60 <= 1830){
                        currentItem.setX(currentItem.getX() + 30);
                    }
                }
            case KeyEvent.VK_LEFT:
                if(currentItem != null){
                    if(currentItem.getX() - 30 >= 0){
                        currentItem.setX(currentItem.getX() - 30);
                    }
                }
        }
        repaint();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        super.mouseWheelMoved(e);
    }
    public void addToMap(ShopItemTiles s){
        currentItem = s;
        for(int i = tiles.size()-1; i >= 0; i--){
            if(tiles.get(i).getName().equals("Grass")){
                if(nothingOnTile(tiles.get(i))) {
                    currentItem.replaceTile(tiles.get(i));
                    items.add(currentItem);
                }
            }
        }
        repaint();
        currentItem = null;
    }
    public boolean nothingOnTile(ShopItemTiles s){
        for(int i = 0; i < items.size(); i++){
            if(s.isOnTile(items.get(i).getX(), items.get(i).getY()))
                return false;
        }
        return true;
    }

    public void reset(){
        for(int i = 0; i < items.size(); i++){
            items.get(i).setY(-1);
            items.get(i).setX(-1);
        }
        items.clear();
        repaint();
    }
}