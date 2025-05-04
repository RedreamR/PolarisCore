package team.rainfall.fontFix;


import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.FileManager;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.Keyboard;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import team.rainfall.finality.FinalityLogger;

import java.util.ArrayList;

import static aoc.kingdoms.lukasz.jakowski.SoundsManager.masterVolume;
import static aoc.kingdoms.lukasz.jakowski.SoundsManager.musicVolume;

public class FontFix {
    //是否尝试过加载compactScale
    public static boolean tried = false;
    public static CompactScale compactScale = null;
    public static ArrayList<FontData> fonts = new ArrayList<>();
    public static boolean titleSet = false;
    public static boolean dontShowMainMenuQQ = true;
    public static final String CORE_VERSION = "3.1.0";
    public static final String POLARIS_VERSION = "1.6";
    public static void setTitle() {
        if (!titleSet) {
            try {
                if (FileManager.loadFile("customTitle").exists()) {
                    Gdx.app.getGraphics().setTitle(FileManager.loadFile("customTitle").readString());
                } else {
                    Gdx.app.getGraphics().setTitle("Age of History 3 - Polaris Core");
                }
            } catch (Exception ignored) {
                FinalityLogger.warn("Failed to set custom title");
            }
            titleSet = true;
        }
    }

    public static int loadFont(String sFont, String charset, int fontSize) {
        if (fonts.isEmpty()) {
            Renderer.loadFont(sFont, charset, fontSize);
            FontData fontData = new FontData();
            fontData.id = Renderer.fontMain.size() - 1;
            fontData.name = sFont;
            fonts.add(fontData);
            return fontData.id;
        }
        //不要重复加载！
        for (FontData font : fonts) {
            if (font.name.equals(sFont)) {
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

    public static void playStartMusic() {
        try {
            setTitle();
            Game.soundsManager.disposeCurrentMusic();
            if (FileManager.loadFile("startMusic").exists()) {
                Game.soundsManager.currentMusic = Gdx.audio.newMusic(FileManager.loadFile("audio/music/" + FileManager.loadFile("startMusic").readString()));
            } else {
                Game.soundsManager.currentMusic = Gdx.audio.newMusic(FileManager.loadFile("audio/music/" + Game.soundsManager.lTitles.get(0) + Game.soundsManager.getFileType()));
            }
            Game.soundsManager.currentMusic.setLooping(false);
            Game.soundsManager.currentMusic.play();
            Game.soundsManager.currentMusic.setVolume(musicVolume * masterVolume);
            Game.soundsManager.currentMusic.setOnCompletionListener(music -> Game.soundsManager.loadNextMusic());
            return;
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }
        Game.soundsManager.loadNextMusic();
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
            case "purple":
                return Color.PURPLE;
            case "cyan":
                return Color.CYAN;
            case "orange":
                return Color.ORANGE;
            case "brown":
                return Color.BROWN;
            case "pink":
                return Color.PINK;
            case "yellow":
                return Color.YELLOW;
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


