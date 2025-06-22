package team.rainfall.fontFix.utils;

import aoc.kingdoms.lukasz.textures.Image;
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.textures.Images;
import team.rainfall.finality.FinalityLogger;

public class IconParser {
    public static int parse(String sIcon){
        try {
            return (int) Images.class.getField(sIcon).get(null);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            FinalityLogger.error("Failed to parse icon "+sIcon,e);
        }
        return 0;
    }
}
