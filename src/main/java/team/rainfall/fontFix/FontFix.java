package team.rainfall.fontFix;


import aoc.kingdoms.lukasz.jakowski.*;
import aoc.kingdoms.lukasz.map.civilization.save.CivData3;
import aoc.kingdoms.lukasz.menusInGame.InGame_CivBonuses;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

import team.rainfall.finality.api.logging.Logger;
import team.rainfall.fontFix.utils.Const;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static aoc.kingdoms.lukasz.jakowski.SoundsManager.masterVolume;
import static aoc.kingdoms.lukasz.jakowski.SoundsManager.musicVolume;
/*
    Polaris Core
    Copyright (C) 2026  Team Rainfall

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
public class FontFix {
    //假装自己是Polaris AoH3，没事别开（因为会带起Sternstunden）
    public static final boolean fakeAndroid = false;
    //玄星的定制提示
    public static final boolean isXuanxing = false;
    public static final Logger LOGGER = Logger.getLogger("PolarisCore");
    public static int manpowerSid = -1;
    public static int musicIconID = -1;
    //渲染线程
    public static Thread renderThread = null;
    //是否尝试过加载compactScale
    public static boolean tried = false;
    public static CompactScale compactScale = null;
    public static final boolean NO_GOAL = false;
    public static boolean titleSet = false;
    public static final String CORE_VERSION = "4.4.0";
    public static final String POLARIS_VERSION = "2.14";
    public static int isLocalStorage = 0;
    public static final Lock lock = new ReentrantLock();
    public static final Condition finished = lock.newCondition();
    public static boolean desktopIncremental = false;
    public static boolean di_set = false;
    public static boolean getDI(){
        if(di_set) return desktopIncremental;
        desktopIncremental = FileManager.loadFile("rainfall/DesktopIncremental").exists();
        di_set = true;
        return desktopIncremental;
    }
    public static GlyphLayout getGlyphLayoutData(BitmapFont font, CharSequence str) {
        if (Thread.currentThread().getName().contains(Const.GL_THREAD)) {
            GlyphLayout layout = new GlyphLayout();
            layout.setText(font, str);
            return layout;
        }

        lock.lock();
        try {
            AtomicReference<GlyphLayout> ref = new AtomicReference<>();
            Gdx.app.postRunnable(() -> {
                lock.lock();
                try {
                    GlyphLayout glyphLayout = new GlyphLayout();
                    glyphLayout.setText(font, str);
                    ref.set(glyphLayout);
                    finished.signal();
                } finally {
                    lock.unlock();
                }
            });
            finished.await();
            return ref.get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        } finally {
            lock.unlock();
        }
    }

    public static boolean isXuanxing() {
        if (CFG.isDesktop() && !fakeAndroid) return false;
        return Sternstunden.getPackageString().contains("age.of.history3.polaris.xuanxing.cbtm") || isXuanxing;
    }

    public static boolean canUseGoal() {
        if (NO_GOAL) return false;
        try {
            int i = (int) Class.forName("team.rainfall.rfEvent.rfEvent").getMethod("getGoalID").invoke(null);
            return i > -2;
        } catch (Exception e) {
            FontFix.LOGGER.error("GoalERR ", e);
            return false;
        }
    }

    public static int getGoalID() {
        try {
            return (int) Class.forName("team.rainfall.rfEvent.rfEvent").getMethod("getGoalID").invoke(null);
        } catch (Exception e) {
            return -1;
        }
    }

    public static int getGoalDura() {
        try {
            return (int) Class.forName("team.rainfall.rfEvent.rfEvent").getMethod("getGoalDura").invoke(null);
        } catch (Exception e) {
            return -1;
        }
    }

    public static boolean isLocalStorage() {
        if (isLocalStorage == 0 && Gdx.files.internal("localStorage").exists()) {
            isLocalStorage = 1;
            return true;
        } else if (isLocalStorage == 0 && !Gdx.files.internal("localStorage").exists()) {
            isLocalStorage = 2;
            return false;
        } else return isLocalStorage == 1;
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
                    Gdx.app.getGraphics().setTitle("Age of History 3");
                }
            } catch (Exception ignored) {
                LOGGER.warn("Failed to set custom title");
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
            if (CFG.isAndroid() || fakeAndroid) {
                Sternstunden.init();
            }
            setTitle();
            Game.soundsManager.disposeCurrentMusic();
            if (FileManager.loadFile("startMusic").exists()) {
                Game.soundsManager.setCurrentMusic(FileManager.loadFile("audio/music/" + FileManager.loadFile("startMusic").readString()));
            } else {
                Game.soundsManager.setCurrentMusic(FileManager.loadFile("audio/music/" + Game.soundsManager.lTitles.get(0) + Game.soundsManager.getFileType(Game.soundsManager.lTitles.get(0))));
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

    public static int getDemilitarization(int iCivID) {
        CivData3 civData3 = Game.getCiv(iCivID).civData3;
        try {
            int d = (int) CivData3.class.getDeclaredField("d").get(civData3);
            return d;
        } catch (IllegalAccessException | NoSuchFieldException ignored) {

        }
        return 0;
    }

    public static boolean isDemilitarization(int iCivID) {
        CivData3 civData3 = Game.getCiv(iCivID).civData3;
        try {
            int d = (int) CivData3.class.getDeclaredField("d").get(civData3);
            if (d > Game_Calendar.TURN_ID) {
                return true;
            }
        } catch (IllegalAccessException | NoSuchFieldException ignored) {

        }
        return false;
    }
}


