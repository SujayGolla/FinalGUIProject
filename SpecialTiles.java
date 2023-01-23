/*
Name: Sujay and Akaren
Class: ICS 3U7
Teacher: Ms.Strelkovska
Description: This class is similar to the shopItemTiles where it keeps track of all specialTiles. However this is used for the default map creation.
*/

import javax.swing.*;

public class SpecialTiles extends ShopItemTiles {
    public SpecialTiles(String name, ImageIcon img, ImageIcon[] sprites) {
        super(name, img, sprites);
        // if the sprites array is not null, set the image to a random animation
        if(sprites != null)
            super.img = animations[(int)(Math.random() * animations.length)];
    }
    public SpecialTiles(String name, ImageIcon img) {
        super(name, img, null);
    }
    public boolean isSpecialTile(){
        // returns true if the tile is a special tile
        return true;
    }
}