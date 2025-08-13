package team.rainfall.fontFix;


import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.FileManager;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.Keyboard;
import aoc.kingdoms.lukasz.menusInGame.InGame_CivBonuses;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import org.lwjgl.system.CallbackI;
import team.rainfall.finality.FinalityLogger;

import java.io.File;

import static aoc.kingdoms.lukasz.jakowski.SoundsManager.masterVolume;
import static aoc.kingdoms.lukasz.jakowski.SoundsManager.musicVolume;

public class FontFix {
    //是否尝试过加载compactScale
    public static int musicIconID = -1;
    public static boolean tried = false;
    public static CompactScale compactScale = null;
    public static boolean titleSet = false;
    public static final String CORE_VERSION = "3.4.0";
    public static final String POLARIS_VERSION = "2.6";
    public static int isSplash = 0;

    public static File getReadableFile(FileHandle src) {
        if (CFG.isDesktop()) {
            return src.file();
        }
        if (src.type() == Files.FileType.Internal) {
            FileHandle dst = Gdx.files.local(src.path());
            if (!dst.exists()) {
                src.copyTo(dst);
            }
            return dst.file();
        }
        return src.file();
    }


    public static boolean isSplash() {
        if (isSplash == 0 && FileManager.loadFile("splashScreen").exists()) {
            isSplash = 1;
            return true;
        } else if (isSplash == 0 && !FileManager.loadFile("splashScreen").exists()) {
            isSplash = 2;
            return false;
        } else return isSplash == 1;
    }

    public static String langGet(String key, String fallback) {
        return Game.lang.get(key).equals(key) ? fallback : Game.lang.get(key);
    }

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

    public static String formatSecondsToMinutes(int totalSeconds) {
        if (totalSeconds < 0) {
            throw new IllegalArgumentException("Second can not be negative!!!!!!");
        }
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    public static void playStartMusic() {
        try {
            setTitle();
            Game.soundsManager.disposeCurrentMusic();
            if (FileManager.loadFile("startMusic").exists()) {
                Game.soundsManager.setCurrentMusic(FileManager.loadFile("audio/music/" + FileManager.loadFile("startMusic").readString()));
            } else {
                Game.soundsManager.setCurrentMusic(FileManager.loadFile("audio/music/" + Game.soundsManager.lTitles.get(0) + Game.soundsManager.getFileType()));
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
        if (CFG.isAndroid() && Sternstunden.bridge == null) {
            Sternstunden.init();
        }
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
                return Color.WHITE;
        }
        if (key.startsWith("#")) {
            try {
                return Color.valueOf(key.replaceAll("#", ""));
            } catch (Exception ignored) {
            }
        }
        return Color.WHITE;

    }

    public static void actionNationSpirit() {
        if (Game.menuManager.getVisibleInGame_CivBonuses()) {
            InGame_CivBonuses.nationSpirit = false;
            Game.menuManager.setVisibleInGame_CivBonuses(false);
        } else {
            InGame_CivBonuses.nationSpirit = true;
            Game.menuManager.rebuildInGame_CivBonuses();
            Game.menuManager.setVisibleInGame_CivBonuses(true);
            if (Game.menuManager.getVisibleInGame_Armies()) {
                Game.menuManager.setVisibleInGame_Armies(false);
            }
        }

    }
}


