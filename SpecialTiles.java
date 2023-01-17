/*
Name: Sujay and Akaren
Class: ICS 3U7
Teacher: Ms.Strelkovska
*/

import javax.swing.*;

public class SpecialTiles extends ShopItemTiles {
    public SpecialTiles(String name, ImageIcon img, ImageIcon[] sprites) {
        super(name, img, sprites);
        if(sprites != null)
            super.img = animations[(int)(Math.random() * animations.length)];
    }
    public SpecialTiles(String name, ImageIcon img) {
        super(name, img, null);
    }
    public boolean isSpecialTile(){
        return true;
    }
}