/*
Name: Sujay and Akaren
Class: ICS 3U7
Teacher: Ms.Strelkovska
*/

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Edit extends Map implements MouseMotionListener, MouseListener, MouseWheelListener, KeyListener {
    private ShopItemTiles currentItem = null;
    public Edit(){
        super();
        addMouseListener(this);
        addMouseMotionListener(this);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        for(int i = 0; i < items.size(); i++){
            if(items.get(i).isOnTile(e.getX(), e.getY())) {
                currentItem = items.get(i);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for(int i = 0; i < tiles.size(); i++){
            if(tiles.get(i).isOnTile(e.getX(), e.getY()) && currentItem != null && !currentItem.isSpecialTile() && !tiles.get(i).isSpecialTile()) {
                currentItem.replaceTile(tiles.get(i));
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
        translater = true;
        repaint();
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