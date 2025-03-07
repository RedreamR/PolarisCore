package team.rainfall.fontFix;


import aoc.kingdoms.lukasz.jakowski.AA_Game;
import aoc.kingdoms.lukasz.jakowski.FileManager;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.Keyboard;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.jakowski.setting.SettingsManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
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

    public static boolean isWrapByChar(){
        String tag = Game.settingsManager.LANGUAGE_TAG;
        //如果tag为cn_sp或cn_tr或jp，返回true
        return tag.equals("cn_sp") || tag.equals("cn_tr") || tag.equals("jp");
    }

    public static boolean containsChineseOrJapanese(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }

        // 中文字符范围：\u4e00-\u9fa5
        // 日文平假名范围：\u3040-\u309F
        // 日文片假名范围：\u30A0-\u30FF
        String regex = "[\\u4e00-\\u9fa5\\u3040-\\u309F\\u30A0-\\u30FF]+";

        return input.matches(".*" + regex + ".*");
    }

}

class FontData {
    String name;
    int id;
}
