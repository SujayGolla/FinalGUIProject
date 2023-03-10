/*
Name: Sujay and Akaren
Class: ICS 3U7
Teacher: Ms.Strelkovska
*/

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Edit extends Map implements MouseListener, MouseWheelListener, KeyListener {
    private ShopItemTiles currentItem = null;
    public Edit(){
        //Gets everything from Map
        super();
        addMouseListener(this);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }
    public void mouseClicked(MouseEvent e) {
        //To see which item got selected
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
        //Controls all translations and transformations
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
            case KeyEvent.VK_UP:
                if(currentItem != null){
                    if(currentItem.getY() - 30 >= 0){
                        currentItem.setY(currentItem.getY() - 30);
                    }
                    break;
                }
            case KeyEvent.VK_DOWN:
                if(currentItem != null){
                    if(currentItem.getY() + 60 <= 600){
                        currentItem.setY(currentItem.getY() + 30);
                    }
                    break;
                }
            case KeyEvent.VK_RIGHT:
                if(currentItem != null){
                    if(currentItem.getX() + 60 <= 1830){
                        currentItem.setX(currentItem.getX() + 30);
                    }
                    break;
                }
            case KeyEvent.VK_LEFT:
                if(currentItem != null){
                    if(currentItem.getX() - 30 >= 0){
                        currentItem.setX(currentItem.getX() - 30);
                    }
                    break;
                }
        }
        repaint();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        super.mouseWheelMoved(e);
    }
    public void addToMap(String s){
        //Just to add an item to the map
        myLabel:
        for(int i = 0; i < items.size(); i++) {
            if(items.get(i).getName().equals(s) && !items.get(i).isPlaced()) {
                currentItem = items.get(i);
                for(int j = 0; j < tiles.size(); j++){
                    if(tiles.get(j).getName().equals("Grass")){
                        if(nothingOnTile(tiles.get(j))) {
                            currentItem.replaceTile(tiles.get(j));
                            break myLabel;
                        }
                    }
                }
            }
        }
        repaint();
        currentItem = null;
    }
    public void reset(){
        //To clear everything
        for(int i = 0; i < items.size(); i++){
            items.get(i).setY(-1);
            items.get(i).setX(-1);
        }
        repaint();
    }
}