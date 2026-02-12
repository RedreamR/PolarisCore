package team.rainfall.fontFix.utils;

import aoc.kingdoms.lukasz.textures.Images;
import team.rainfall.fontFix.FontFix;


public class IconParser {
    public static int parse(String sIcon){
        try {
            return (int) Images.class.getField(sIcon).get(null);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            FontFix.LOGGER.error("Failed to parse icon "+sIcon,e);
        }
        return 0;
    }
}
