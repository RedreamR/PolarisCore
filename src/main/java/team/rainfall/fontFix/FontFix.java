package team.rainfall.fontFix;


import aoc.kingdoms.lukasz.jakowski.FileManager;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.Keyboard;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import team.rainfall.finality.FinalityLogger;

import java.util.ArrayList;

public class FontFix {
    public static ArrayList<FontData> fonts = new ArrayList<>();
    public static boolean titleSet = false;
    public static boolean dontShowMainMenuQQ = true;
    public static int textureSize = 8192;
    public static void setTitle() {
        if (!titleSet) {
            try {
                Gdx.app.getGraphics().setTitle(FileManager.loadFile("customTitle").readString());
            } catch (Exception ignored) {
                FinalityLogger.warn("Failed to set custom title");
            }
            titleSet = true;
        }
    }

    public static int loadFont(String sFont, String charset, int fontSize){
        if(fonts.isEmpty()){
            Renderer.loadFont(sFont, charset, fontSize);
            FontData fontData = new FontData();
            fontData.id = Renderer.fontMain.size() - 1;
            fontData.name = sFont;
            fonts.add(fontData);
            return fontData.id;
        }
        //不要重复加载！
        for (FontData font : fonts) {
            if(font.name.equals(sFont)){
                return font.id;
            }
        }
        Renderer.loadFont(sFont, charset, fontSize);
        FontData fontData = new FontData();
        fontData.id = Renderer.fontMain.size() - 1;
        fontData.name = sFont;
        fonts.add(fontData);
        return fontData.id;
    }

    public static void paste() {
        if (Keyboard.keyboardMode && Gdx.app.getClipboard().hasContents()) {
            Keyboard.keyboardMessage = Keyboard.keyboardMessage + Gdx.app.getClipboard().getContents();
        }
    }

    public static void copy() {
        if (Keyboard.keyboardMode) {
            Gdx.app.getClipboard().setContents(Keyboard.keyboardMessage);
        }
    }

    public static Color readFontColor(String key) {
        String str = Game.lang.get(key);
        str = str.trim().toLowerCase();
        switch (str) {
            case "black":
                return Color.BLACK;
            case "red":
                return Color.RED;
            case "green":
                return Color.GREEN;
            case "blue":
                return Color.BLUE;
            case "white":
            default:
                return Color.WHITE;
        }
    }

}

class FontData {
    String name;
    int id;
}
