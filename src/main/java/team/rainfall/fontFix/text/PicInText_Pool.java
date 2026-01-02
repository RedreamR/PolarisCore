package team.rainfall.fontFix.text;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.textures.Image;
import aoc.kingdoms.lukasz.textures.ImageManager;

import java.util.ArrayList;
import java.util.HashMap;

public class PicInText_Pool {
    public static HashMap<Character,Integer> imageMap = new HashMap<>();
    public static Image loadImage(char c){
        if(imageMap.get(c) != null){
            return ImageManager.getImage(imageMap.get(c));
        }else {
            int i = ImageManager.addImage("ui/" + CFG.getRescouresPath() + "images_in_text/" + c + ".png");
            imageMap.put(c,i);
            return ImageManager.getImage(i);
        }
    }
}
