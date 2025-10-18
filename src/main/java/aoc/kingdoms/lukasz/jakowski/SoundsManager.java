//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.jakowski;

import aoc.kingdoms.lukasz.menusInGame.InGame_Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;

import team.rainfall.fontFix.Config;
import team.rainfall.fontFix.FontFix;
import team.rainfall.fontFix.utils.MP3DurationParser;
import team.rainfall.fontFix.utils.OggDurationParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@SuppressWarnings("ALL")
public class SoundsManager {
    //0 - 顺序播放
    //1 - 循环播放
    //2 - 随机播放
    public static short playMode = 0;
    public static float masterVolume = 1.0F;
    public static float musicVolume = 0.4F;
    public static float soundsVolume = 0.2F;
    public static float ambienceVolume = 0.2F;
    public static float PERC_VOLUME_SELECT_PROVINCE = 0.95F;
    public static float PERC_VOLUME_KEYBOARD = 0.9F;
    public List<String> lTitles = new ArrayList<>();
    public List<String> lTitlesWar = new ArrayList<>();
    public Music currentMusic = null;
    public int currentMusicDuration = -1;
    public String currentMusicDuraStr = "";
    public int iCurrentMusicID = 0;
    public static boolean isWarMusicPlaying = false;
    public List<String> lSoundsDura = new ArrayList<>();
    public List<Sound> lSounds = new ArrayList<>();
    public List<Sound> lSoundsRandom = new ArrayList<>();
    public int soundsRandomSize = 0;
    public long SOUNDS_RANDOM_TIME;
    public static int SOUND_CLICK_MAIN;
    public static int SOUND_CLICK_MAIN2;
    public static int SOUND_CLICK2;
    public static int SOUND_CLICK3;
    public static int SOUND_CLICK_PAGE;
    public static int SOUND_CLICK_PAGE_1;
    public static int SOUND_PROVINCE;
    public static int SOUND_BATTLE;
    public static int SOUND_BATTLE2;
    public static int SOUND_NUKE;
    public static int SOUND_NUKE2;
    public static int SOUND_CLICK_WAR;
    public static int SOUND_FORMABLE;
    public static int SOUND_HOVER_0;
    public static int SOUND_HOVER_1;
    public static int SOUND_HOVER_2;
    public static int SOUND_HOVER_3;
    public static int SOUND_HOVER_4;
    public static int SOUND_HOVER_5;
    public static int SOUND_HOVER_6;
    public static int SOUND_HOVER_7;
    public static int SOUND_HOVER_8;
    public static int SOUND_SELECTED_ARMY_0;
    public static int SOUND_SELECTED_ARMY_1;
    public static int SOUND_RECRUIT_ARMY_0;
    public static int SOUND_RECRUIT_ARMY_1;
    public static int SOUND_RECRUIT_CANCEL;
    public static int SOUND_GOLD_0;
    public static int SOUND_GOLD_1;
    public static int SOUND_GOLD_2;
    public static int SOUND_GOLD_3;
    public static int SOUND_GOLD_4;
    public static int TAB_0;
    public static int TAB_1;
    public static int SOUND_WAR_END;
    public static int SOUND_GOLD_LEVEL_0;
    public static int SOUND_GOLD_LEVEL_1;
    public static int SOUND_GOLD_LEVEL_2;
    public static int SOUND_COIN_0;
    public static int SOUND_COIN_1;
    public static int SOUND_COIN_2;
    public static int SOUND_PLAY_NEW_GAME;
    public static int SOUND_CREATE_ARMY;
    public static int SOUND_ECONOMY_0;
    public static int SOUND_ECONOMY_1;
    public static int SOUND_INFRASTRUCTURE;
    public static int SOUND_INFRASTRUCTURE_1;
    public static int SOUND_INCREASE_MANPOWER;
    public static int SOUND_INCREASE_MANPOWER2;
    public static int SOUND_CORES;
    public static int SOUND_GROWTH_RATE;
    public static int SOUND_GROWTH_RATE2;
    public static int SOUND_ADVANTAGE0;
    public static int SOUND_ADVANTAGE1;
    public static int SOUND_ADVANTAGE2;
    public static int SOUND_CLICK_TOP;
    public static int SOUND_LOAN;
    public static int SOUND_LOAN_REPAY;
    public static int MOVE_0;
    public static int MOVE_1;
    public static int MOVE_2;
    public static int MOVE_3;
    public static int MOVE_4;
    public static int MOVE_SEA_0;
    public static int MOVE_SEA_1;
    public static int LEGACY_0;
    public static int LEGACY_1;
    public static int LEGACY_2;
    public static int TECHNOLOGY;
    public static int TECHNOLOGY_CLICK;
    public static int ARMY_CLICK;
    public static int GENERALS_CLICK;
    public static int MAP_MODE0;
    public static int MAP_MODE1;
    public static int PLAY;
    public static int FLAG_CLICK;
    public static int BUDGET_CLICK;
    public static int CIV_OPTIONS_CLICK;
    public static int CIV_OPTIONS_CLICK1;
    public static int BUILD0;
    public static int BUILD1;
    public static int WAR;
    public static int SIEGE;
    public static int DIPLOMACY0;
    public static int DIPLOMACY1;
    public static int DIPLOMACY_CLICK;
    public static int INFO_BOX;
    public static int EVENT;
    public static int EVENT_INFO;
    public long WAR_MUSIC_LAST_TIME_PLAYED = 0L;
    public static int iCivOptionsSound = 0;
    public static int iGrowthRate = 0;
    public int iHoverID = 0;
    public float hoverVolume = 0.5F;
    public long lHoverTime = 0L;
    public int iSelectArmy = 0;
    public long lRecruitTime = 0L;
    public int iRecruitArmy = 0;
    public int tabNum = 0;
    public int iDiplomacyButton = 0;
    public int iEconomy = 0;
    public int iIncreaseManpower = 0;


    public short musicPlayed = 0;

    public static String getFileExtension() {
        return CFG.isiOS ? "mp3" : "ogg";
    }
    public static String getPlayModeStr(){
        switch (playMode){
            case 0:
                return  "Sequential";
            case 1:
                return "Looping";
            case 2:
                return "RandomPlay";
            default:
                return "Unknown";
        }
    }
    public String getDura2(int id){
        if(lSoundsDura.size() > id){
            return lSoundsDura.get(id);
        }
        return "";
    }
    public static String getPlayModeShortStr(){
        switch (playMode){
            case 0:
                return  "S";
            case 1:
                return "L";
            case 2:
                return "R";
            default:
                return "U";
        }
    }
    public SoundsManager() {
        SOUND_CLICK_MAIN = this.addSoundSFX("click." + getFileExtension());
        SOUND_CLICK_MAIN2 = this.addSoundSFX("click." + getFileExtension());
        SOUND_CLICK2 = this.addSound("click2." + getFileExtension());
        SOUND_CLICK3 = this.addSound("click3." + getFileExtension());
        SOUND_CLICK_PAGE = this.addSound("click_page_0." + getFileExtension());
        SOUND_CLICK_PAGE_1 = this.addSound("click_page_1." + getFileExtension());
        SOUND_PROVINCE = this.addSoundSFX("clickProvince." + getFileExtension());
        SOUND_HOVER_0 = this.addSoundSFX("hover0." + getFileExtension());
        SOUND_HOVER_1 = this.addSoundSFX("hover1." + getFileExtension());
        SOUND_HOVER_2 = this.addSoundSFX("hover2." + getFileExtension());
        SOUND_HOVER_3 = this.addSoundSFX("hover3." + getFileExtension());
        SOUND_HOVER_4 = this.addSoundSFX("hover4." + getFileExtension());
        SOUND_HOVER_5 = this.addSoundSFX("hover5." + getFileExtension());
        SOUND_HOVER_6 = this.addSoundSFX("hover6." + getFileExtension());
        SOUND_HOVER_7 = this.addSoundSFX("hover7." + getFileExtension());
        SOUND_HOVER_8 = this.addSoundSFX("hover8." + getFileExtension());
        masterVolume = Game.settingsManager.VOLUME_MASTER;
        this.setSoundsVolume(Game.settingsManager.VOLUME_SOUNDS);
        this.setAmbienceVolume(Game.settingsManager.VOLUME_AMBIENCE);
        this.setMusicVolume(Game.settingsManager.VOLUME_MUSIC);
        this.hoverVolume = Game.settingsManager.VOLUME_HOVER;
    }

    public final void loadSounds() {
        SOUND_BATTLE = this.addSoundSFX("battle." + getFileExtension());
        SOUND_BATTLE2 = this.addSoundSFX("battle2." + getFileExtension());
        SOUND_NUKE = this.addSound("nuke." + getFileExtension());
        PLAY = this.addSoundSFX("play." + getFileExtension());
        SOUND_SELECTED_ARMY_0 = this.addSoundSFX("selectedArmy0." + getFileExtension());
        SOUND_SELECTED_ARMY_1 = this.addSoundSFX("selectedArmy1." + getFileExtension());
        SOUND_RECRUIT_ARMY_0 = this.addSoundSFX("recruitArmy0." + getFileExtension());
        SOUND_RECRUIT_ARMY_1 = this.addSoundSFX("recruitArmy1." + getFileExtension());
        SOUND_RECRUIT_CANCEL = this.addSoundSFX("recruitArmyCancel." + getFileExtension());
        SOUND_CORES = this.addSoundSFX("cores." + getFileExtension());
        SOUND_GOLD_0 = this.addSoundSFX("gold0." + getFileExtension());
        SOUND_GOLD_1 = this.addSoundSFX("gold1." + getFileExtension());
        SOUND_GOLD_2 = this.addSoundSFX("gold2." + getFileExtension());
        SOUND_GOLD_3 = this.addSoundSFX("gold3." + getFileExtension());
        SOUND_GOLD_4 = this.addSoundSFX("gold4." + getFileExtension());
        TAB_0 = this.addSoundSFX("tab0." + getFileExtension());
        TAB_1 = this.addSoundSFX("tab1." + getFileExtension());
        SOUND_GOLD_LEVEL_0 = this.addSoundSFX("goldLevel0." + getFileExtension());
        SOUND_GOLD_LEVEL_1 = this.addSoundSFX("goldLevel1." + getFileExtension());
        SOUND_GOLD_LEVEL_2 = this.addSoundSFX("goldLevel2." + getFileExtension());
        SOUND_COIN_0 = this.addSoundSFX("coin0." + getFileExtension());
        SOUND_COIN_1 = this.addSoundSFX("coin1." + getFileExtension());
        SOUND_COIN_2 = this.addSoundSFX("coin2." + getFileExtension());
        SOUND_CREATE_ARMY = this.addSoundSFX("createArmy." + getFileExtension());
        SOUND_CLICK_WAR = this.addSoundSFX("clickWar." + getFileExtension());
        SOUND_FORMABLE = this.addSoundSFX("formable." + getFileExtension());
        SOUND_INFRASTRUCTURE = this.addSoundSFX("infrastructure." + getFileExtension());
        SOUND_INFRASTRUCTURE_1 = this.addSoundSFX("infrastructure1." + getFileExtension());
        SOUND_ECONOMY_0 = this.addSoundSFX("economy0." + getFileExtension());
        SOUND_ECONOMY_1 = this.addSoundSFX("economy1." + getFileExtension());
        SOUND_INCREASE_MANPOWER = this.addSoundSFX("increaseManpower." + getFileExtension());
        SOUND_INCREASE_MANPOWER2 = this.addSoundSFX("increaseManpower2." + getFileExtension());
        SOUND_GROWTH_RATE = this.addSoundSFX("growthRate." + getFileExtension());
        SOUND_GROWTH_RATE2 = this.addSoundSFX("growthRate2." + getFileExtension());
        SOUND_ADVANTAGE0 = this.addSoundSFX("advantage0." + getFileExtension());
        SOUND_ADVANTAGE1 = this.addSoundSFX("advantage1." + getFileExtension());
        SOUND_ADVANTAGE2 = this.addSoundSFX("advantage2." + getFileExtension());
        SOUND_CLICK_TOP = this.addSoundSFX("clickTop." + getFileExtension());
        EVENT = this.addSoundSFX("event." + getFileExtension());
        EVENT_INFO = this.addSoundSFX("eventInfo2." + getFileExtension());
        SOUND_LOAN = this.addSoundSFX("loan." + getFileExtension());
        SOUND_LOAN_REPAY = this.addSoundSFX("loanRepay." + getFileExtension());
        MOVE_0 = this.addSoundSFX("move0." + getFileExtension());
        MOVE_1 = this.addSoundSFX("move1." + getFileExtension());
        MOVE_2 = this.addSoundSFX("move2." + getFileExtension());
        MOVE_3 = this.addSoundSFX("move3." + getFileExtension());
        MOVE_4 = this.addSoundSFX("move4." + getFileExtension());
        MOVE_SEA_0 = this.addSoundSFX("moveSea." + getFileExtension());
        MOVE_SEA_1 = this.addSoundSFX("moveSea1." + getFileExtension());
        SOUND_WAR_END = this.addSoundSFX("warEnd." + getFileExtension());
        SOUND_PLAY_NEW_GAME = this.addSoundSFX("playNewGame." + getFileExtension());
        LEGACY_0 = this.addSoundSFX("legacy0." + getFileExtension());
        LEGACY_1 = this.addSoundSFX("legacy1." + getFileExtension());
        LEGACY_2 = this.addSoundSFX("legacy2." + getFileExtension());
        TECHNOLOGY = this.addSoundSFX("technology." + getFileExtension());
        TECHNOLOGY_CLICK = this.addSoundSFX("technologyClick." + getFileExtension());
        ARMY_CLICK = this.addSoundSFX("armyClick." + getFileExtension());
        GENERALS_CLICK = this.addSoundSFX("generals." + getFileExtension());
        MAP_MODE0 = this.addSoundSFX("mapMode0." + getFileExtension());
        MAP_MODE1 = this.addSoundSFX("mapMode1." + getFileExtension());
        BUILD0 = this.addSoundSFX("build." + getFileExtension());
        BUILD1 = this.addSoundSFX("build1." + getFileExtension());
        DIPLOMACY0 = this.addSoundSFX("diplomacy." + getFileExtension());
        DIPLOMACY1 = this.addSoundSFX("diplomacy1." + getFileExtension());
        DIPLOMACY_CLICK = this.addSoundSFX("diplomacyClick." + getFileExtension());
        WAR = this.addSoundSFX("war." + getFileExtension());
        SIEGE = this.addSoundSFX("siege." + getFileExtension());
        FLAG_CLICK = this.addSoundSFX("flagClick." + getFileExtension());
        BUDGET_CLICK = this.addSoundSFX("budgetClick." + getFileExtension());
        CIV_OPTIONS_CLICK = this.addSoundSFX("civOptionsClick." + getFileExtension());
        CIV_OPTIONS_CLICK1 = this.addSoundSFX("civOptionsClick1." + getFileExtension());
        INFO_BOX = this.addSoundSFX("infoBox." + getFileExtension());
        this.loadSFXRandom();
        this.SOUNDS_RANDOM_TIME = System.currentTimeMillis() + (long)GameValues.inGame.SOUNDS_RANDOM_MIN + (long)Game.oR.nextInt(GameValues.inGame.SOUNDS_RANDOM_RANDOM);
    }

    public final void loadSFXRandom() {
        try {
            FileHandle tempFileT = FileManager.loadFile("audio/random/list.txt");
            String[] split = tempFileT.readString().split(";");

            for (String s : split) {
                this.addSoundSFXRandom(s + "." + getFileExtension());
            }
        } catch (GdxRuntimeException ex) {
            CFG.exceptionStack(ex);
        }

        this.soundsRandomSize = this.lSoundsRandom.size();
    }

    public final void loadMusic_List() {
        try {
            FileHandle tempFileT = FileManager.loadFile("audio/music/list.txt");
            String tempT = tempFileT.readString();
            String[] tagsSPLITED = tempT.split(";");
            Collections.addAll(this.lTitles, tagsSPLITED);
            tempFileT = FileManager.loadFile("audio/music/listDura.txt");
            if(tempFileT.exists()){
                tagsSPLITED = tempFileT.readString().split(";");
                Collections.addAll(this.lSoundsDura,tagsSPLITED);
            }
        } catch (GdxRuntimeException ex) {
            CFG.LOG(ex);
        }

        try {
            FileHandle tempFileT = FileManager.loadFile("audio/music/listWar.txt");
            String tempT = tempFileT.readString();
            String[] tagsSPLITED = tempT.split(";");

            Collections.addAll(this.lTitlesWar, tagsSPLITED);
        } catch (GdxRuntimeException ex) {
            CFG.LOG(ex);
        }

        this.randomizePlayList();
    }

    public final void randomizePlayList() {


    }
    public final void loadPreviousMusic() {
        this.disposeCurrentMusic();
        isWarMusicPlaying = false;
        --this.iCurrentMusicID;
        if (this.iCurrentMusicID < 0) {
            this.iCurrentMusicID = 0;
            this.randomizePlayList();
        }
        try {
            if (FileManager.loadFile("audio/music/" + this.lTitles.get(this.iCurrentMusicID) + this.getFileType()).exists()) {
                setCurrentMusic(FileManager.loadFile("audio/music/" + this.lTitles.get(this.iCurrentMusicID) + this.getFileType()));
                this.currentMusic.setLooping(false);
                this.currentMusic.play();
                this.currentMusic.setVolume(musicVolume * masterVolume);
                this.currentMusic.setOnCompletionListener(music -> SoundsManager.this.loadNextMusic());
            } else if (Gdx.files.local("audio/music/" + this.lTitles.get(this.iCurrentMusicID) + this.getFileType()).exists()) {
                setCurrentMusic(Gdx.files.local("audio/music/" + this.lTitles.get(this.iCurrentMusicID) + this.getFileType()));
                this.currentMusic.setLooping(false);
                this.currentMusic.play();
                this.currentMusic.setVolume(musicVolume * masterVolume);
                this.currentMusic.setOnCompletionListener(music -> SoundsManager.this.loadNextMusic());
            } else {
                this.currentMusic.setOnCompletionListener(music -> {
                });
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }
    public final void loadNextMusic() {
        this.disposeCurrentMusic();
        isWarMusicPlaying = false;
        if(playMode == 0) {
            ++this.iCurrentMusicID;
            if (this.iCurrentMusicID >= this.lTitles.size()) {
                this.iCurrentMusicID = 0;
                this.randomizePlayList();
            }
        }
        if(playMode == 2){
            this.iCurrentMusicID = Game.oR.nextInt(this.lTitles.size() -1);
            while (this.iCurrentMusicID == musicPlayed){
                this.iCurrentMusicID = Game.oR.nextInt(this.lTitles.size() -1);
            }
        }
        try {
            if (FileManager.loadFile("audio/music/" + this.lTitles.get(this.iCurrentMusicID) + this.getFileType()).exists()) {
                setCurrentMusic(FileManager.loadFile("audio/music/" + this.lTitles.get(this.iCurrentMusicID) + this.getFileType()));
                this.currentMusic.setLooping(false);
                this.currentMusic.play();
                this.currentMusic.setVolume(musicVolume * masterVolume);
                this.currentMusic.setOnCompletionListener(music -> SoundsManager.this.loadNextMusic());
            } else if (Gdx.files.local("audio/music/" + this.lTitles.get(this.iCurrentMusicID) + this.getFileType()).exists()) {
                setCurrentMusic(Gdx.files.local("audio/music/" + this.lTitles.get(this.iCurrentMusicID) + this.getFileType()));
                this.currentMusic.setLooping(false);
                this.currentMusic.play();
                this.currentMusic.setVolume(musicVolume * masterVolume);
                this.currentMusic.setOnCompletionListener(music -> SoundsManager.this.loadNextMusic());
            } else {
                this.currentMusic.setOnCompletionListener(music -> {
                });
            }
            InGame_Audio.shouldRefresh = true;
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    public final void loadNextMusic(String fileName) {
        try {
            this.disposeCurrentMusic();
            isWarMusicPlaying = false;
            ++this.iCurrentMusicID;
            if (this.iCurrentMusicID >= this.lTitles.size()) {
                this.iCurrentMusicID = 0;
                this.randomizePlayList();
            }
            for (int i = 0; i < lTitles.size(); i++) {
                if(lTitles.get(i).equalsIgnoreCase(fileName)){
                    this.iCurrentMusicID = i;
                    break;
                }
            }
            try {
                if (FileManager.loadFile("audio/music/" + fileName + this.getFileType()).exists()) {
                    setCurrentMusic(FileManager.loadFile("audio/music/" + fileName + this.getFileType()));
                    this.currentMusic.setLooping(false);
                    this.currentMusic.play();
                    this.currentMusic.setVolume(musicVolume * masterVolume);
                    this.currentMusic.setOnCompletionListener(music -> SoundsManager.this.loadNextMusic());
                } else if (Gdx.files.local("audio/music/" + fileName + this.getFileType()).exists()) {
                    setCurrentMusic(Gdx.files.local("audio/music/" + fileName + this.getFileType()));
                    this.currentMusic.setLooping(false);
                    this.currentMusic.play();
                    this.currentMusic.setVolume(musicVolume * masterVolume);
                    this.currentMusic.setOnCompletionListener(music -> SoundsManager.this.loadNextMusic());
                } else {
                    this.currentMusic.setOnCompletionListener(music -> {
                    });
                }
                InGame_Audio.shouldRefresh = true;
            } catch (Exception ex) {
                CFG.exceptionStack(ex);
            }

        } catch (Exception ex) {
            CFG.exceptionStack(ex);
            this.loadNextMusic();
        }
    }

    public final void loadNextMusicWar() {
        if (!isWarMusicPlaying && CFG.currentTimeMillis - this.WAR_MUSIC_LAST_TIME_PLAYED > (long)GameValues.inGame.WAR_MUSIC_BREAK_BETWEEN_LAST_TIME_PLAYED) {
            this.disposeCurrentMusic();
            isWarMusicPlaying = true;
            this.WAR_MUSIC_LAST_TIME_PLAYED = CFG.currentTimeMillis;

            try {
                setCurrentMusic(FileManager.loadFile("audio/music/" + this.lTitlesWar.get(Game.oR.nextInt(this.lTitlesWar.size())) + this.getFileType()));
                this.currentMusic.setLooping(false);
                this.currentMusic.play();
                this.currentMusic.setVolume(musicVolume * masterVolume);
                this.currentMusic.setOnCompletionListener(music -> SoundsManager.this.loadNextMusic());
            } catch (GdxRuntimeException var4) {
                try {
                    setCurrentMusic(Gdx.files.local("audio/music/" + this.lTitles.get(this.iCurrentMusicID) + this.getFileType()));
                    this.currentMusic.setLooping(false);
                    this.currentMusic.play();
                    this.currentMusic.setVolume(musicVolume * masterVolume);
                    this.currentMusic.setOnCompletionListener(music -> SoundsManager.this.loadNextMusic());
                } catch (Exception exr) {
                    CFG.exceptionStack(exr);
                }
                InGame_Audio.shouldRefresh = true;
            } catch (Exception ex) {
                CFG.exceptionStack(ex);
            }
        }

    }

    public final void loadNextMusic(String sTitle, int id) {
        this.disposeCurrentMusic();
        this.iCurrentMusicID = id;

        try {
            setCurrentMusic(FileManager.loadFile("audio/music/" + sTitle + this.getFileType()));
            this.currentMusic.setLooping(false);
            this.currentMusic.play();
            this.currentMusic.setVolume(musicVolume * masterVolume);
            this.currentMusic.setOnCompletionListener(music -> SoundsManager.this.loadNextMusic());
            InGame_Audio.shouldRefresh = true;
        } catch (GdxRuntimeException var6) {
            try {
                setCurrentMusic(Gdx.files.local("audio/music/" + this.lTitles.get(this.iCurrentMusicID) + this.getFileType()));
                this.currentMusic.setLooping(false);
                this.currentMusic.play();
                this.currentMusic.setVolume(musicVolume * masterVolume);
                this.currentMusic.setOnCompletionListener(music -> SoundsManager.this.loadNextMusic());
            } catch (Exception exr) {
                CFG.exceptionStack(exr);
            }
            InGame_Audio.shouldRefresh = true;
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    public final void playStartMusic() {
        FontFix.playStartMusic();
    }

    public final void disposeCurrentMusic() {
        if (this.currentMusic != null) {
            this.currentMusic.stop();
            this.currentMusic.dispose();
        }

    }

    public String getFileType() {
        //Just ignore ios.No one like it.
        //return CFG.isIOS() ? ".mp3" : ".ogg";
        return ".ogg";
    }

    public final int addSound(String fileName) {
        try {
            this.lSounds.add(Gdx.audio.newSound(FileManager.loadFile("audio/sounds/" + fileName)));
        } catch (GdxRuntimeException ex) {
            ex.printStackTrace();

            try {
                this.lSounds.add(Gdx.audio.newSound(Gdx.files.local("audio/sounds/" + fileName)));
            } catch (GdxRuntimeException var4) {
                ex.printStackTrace();
            }
        }

        return this.lSounds.size() - 1;
    }

    public final int addSoundSFX(String fileName) {
        try {
            this.lSounds.add(Gdx.audio.newSound(FileManager.loadFile("audio/sfx/" + fileName)));
        } catch (GdxRuntimeException ex) {
            ex.printStackTrace();

            try {
                this.lSounds.add(Gdx.audio.newSound(Gdx.files.local("audio/sfx/" + fileName)));
            } catch (GdxRuntimeException var4) {
                ex.printStackTrace();
            }
        }

        return this.lSounds.size() - 1;
    }

    public final int addSoundSFXRandom(String fileName) {
        try {
            this.lSoundsRandom.add(Gdx.audio.newSound(FileManager.loadFile("audio/random/" + fileName)));
        } catch (GdxRuntimeException ex) {
            ex.printStackTrace();

            try {
                this.lSoundsRandom.add(Gdx.audio.newSound(Gdx.files.local("audio/random/" + fileName)));
            } catch (GdxRuntimeException var4) {
                ex.printStackTrace();
            }
        }

        return this.lSoundsRandom.size() - 1;
    }

    public final void playSound(int id) {
        try {
            if (id >= 0) {
                this.playSound(id, 1.0F);
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    public final void playSound(int id, float fPercOfVolume) {
        try {
            this.lSounds.get(id).stop();
            this.lSounds.get(id).play(soundsVolume * masterVolume * fPercOfVolume);
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    public final void stopLegacySound() {
        try {
            this.lSounds.get(LEGACY_0).stop();
            this.lSounds.get(LEGACY_1).stop();
            this.lSounds.get(LEGACY_2).stop();
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    public final String getCurrentMusicTittle() {
        return this.lTitles.get(this.iCurrentMusicID).substring(0, this.lTitles.get(this.iCurrentMusicID).indexOf("." + getFileExtension()) > 0 ? this.lTitles.get(this.iCurrentMusicID).indexOf("." + getFileExtension()) : this.lTitles.get(this.iCurrentMusicID).length()).replace("_", " ");
    }

    public final void setMusicVolume(float nMusicVolume) {
        musicVolume = nMusicVolume;

        try {
            if (this.currentMusic != null) {
                this.currentMusic.setVolume(musicVolume * masterVolume);
                if (musicVolume < 0.01F) {
                    this.currentMusic.pause();
                } else if (!this.currentMusic.isPlaying()) {
                    this.currentMusic.play();
                }
            }
        } catch (Exception var3) {
        }

    }
    public final void setCurrentMusic(FileHandle fileHandle){
        currentMusic =  Gdx.audio.newMusic(fileHandle);
        try {
            byte[] header = new byte[64];
            int read = fileHandle.readBytes(header, 0, header.length);
            if ("OggS".equals(new String(header, 0, 4))) {
                currentMusicDuration = (int) OggDurationParser.getOggDuration(fileHandle);
                currentMusicDuraStr = FontFix.formatSecondsToMinutes(currentMusicDuration);
            }else {
                currentMusicDuration = (int) MP3DurationParser.getMP3Duration(fileHandle);
                currentMusicDuraStr = FontFix.formatSecondsToMinutes(currentMusicDuration);
            }
            CFG.LOG("[PolarisCore-Radio]","tag loaded,dura "+currentMusicDuration);
        } catch (Exception e) {
            CFG.LOG("[PolarisCore-Radio]","Failed to load tag");
            CFG.LOG(e);
        }
        if(Config.getConfig().toastWhenPlayMusic) {
            Game.menuManager.addToast(getCurrentMusicTittle());
        }
    }
    public final float getMusicVolume() {
        return musicVolume;
    }

    public final void setSoundsVolume(float soundsVolume) {
        SoundsManager.soundsVolume = soundsVolume;
    }

    public final void setAmbienceVolume(float ambienceVolume) {
        SoundsManager.ambienceVolume = ambienceVolume;
    }

    public final float getSoundsVolume() {
        return soundsVolume;
    }

    public final float getSoundsVolumeMaster() {
        return soundsVolume * masterVolume;
    }

    public final void setMasterVolume(float masterVolume) {
        SoundsManager.masterVolume = masterVolume;
        this.setMusicVolume(this.getMusicVolume());
    }

    public final float getMasterVolume() {
        return masterVolume;
    }

    public final void dispose() {
        for (Sound lSound : this.lSounds) {
            lSound.dispose();
        }

        this.currentMusic.dispose();
    }

    public static int getClickSound_CivOptions() {
        switch (iCivOptionsSound++ % 2) {
            case 0:
                return CIV_OPTIONS_CLICK;
            default:
                return CIV_OPTIONS_CLICK1;
        }
    }

    public static int getGrowthRate() {
        switch (iGrowthRate++ % 2) {
            case 0:
                return SOUND_GROWTH_RATE;
            default:
                return SOUND_GROWTH_RATE2;
        }
    }

    public void playHover() {
        if (CFG.currentTimeMillis - this.lHoverTime > 36L) {
            this.lHoverTime = CFG.currentTimeMillis;
            switch (this.iHoverID++ % 2) {
                case 0:
                    this.playSound(SOUND_HOVER_0, this.hoverVolume);
                    break;
                case 1:
                    this.playSound(SOUND_HOVER_1, this.hoverVolume);
                    break;
                case 2:
                    this.playSound(SOUND_HOVER_2, this.hoverVolume);
                    break;
                case 3:
                    this.playSound(SOUND_HOVER_3, this.hoverVolume);
                    break;
                case 4:
                    this.playSound(SOUND_HOVER_4, this.hoverVolume);
                    break;
                case 5:
                    this.playSound(SOUND_HOVER_5, this.hoverVolume);
                    break;
                case 6:
                    this.playSound(SOUND_HOVER_6, this.hoverVolume);
                    break;
                case 7:
                    this.playSound(SOUND_HOVER_7, this.hoverVolume);
                    break;
                default:
                    this.playSound(SOUND_HOVER_8, this.hoverVolume);
            }

        }
    }

    public void playRandomSounds() {
        try {
            if (this.SOUNDS_RANDOM_TIME < CFG.currentTimeMillis && this.soundsRandomSize > 0) {
                int id = Game.oR.nextInt(this.soundsRandomSize);
                this.lSoundsRandom.get(id).stop();
                this.lSoundsRandom.get(id).play(soundsVolume * masterVolume * 0.75F);
                this.updateSoundsRandomTime();
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    public void updateSoundsRandomTime() {
        this.SOUNDS_RANDOM_TIME = CFG.currentTimeMillis + (long)GameValues.inGame.SOUNDS_RANDOM_MIN + (long)Game.oR.nextInt(GameValues.inGame.SOUNDS_RANDOM_RANDOM);
    }

    public void playSelectedArmy() {
        switch (this.iSelectArmy++ % 2) {
            case 0:
                this.playSound(SOUND_SELECTED_ARMY_0);
                break;
            default:
                this.playSound(SOUND_SELECTED_ARMY_1);
        }

    }

    public int getSelectedArmy() {
        switch (this.iSelectArmy++ % 2) {
            case 0:
                return SOUND_SELECTED_ARMY_0;
            default:
                return SOUND_SELECTED_ARMY_1;
        }
    }

    public void playRecruitArmy() {
        if (CFG.currentTimeMillis - this.lRecruitTime > 250L) {
            this.lRecruitTime = CFG.currentTimeMillis;
            switch (this.iRecruitArmy++ % 2) {
                case 0:
                    this.playSound(SOUND_RECRUIT_ARMY_0);
                    break;
                default:
                    this.playSound(SOUND_RECRUIT_ARMY_1);
            }

        }
    }

    public int getRecruitArmy() {
        switch (this.iRecruitArmy++ % 2) {
            case 0:
                return SOUND_RECRUIT_ARMY_0;
            default:
                return SOUND_RECRUIT_ARMY_1;
        }
    }

    public void playRecruitArmyCancel() {
        this.playSound(SOUND_RECRUIT_CANCEL);
    }

    public void playMove() {
        switch (Game.oR.nextInt(5)) {
            case 0:
                this.playSound(MOVE_0);
                break;
            case 1:
                this.playSound(MOVE_1);
                break;
            case 2:
                this.playSound(MOVE_2);
                break;
            case 3:
                this.playSound(MOVE_3);
                break;
            default:
                this.playSound(MOVE_4);
        }

    }

    public void playGold() {
        switch (Game.oR.nextInt(5)) {
            case 0:
                this.playSound(SOUND_GOLD_0);
                break;
            case 1:
                this.playSound(SOUND_GOLD_1);
                break;
            case 2:
                this.playSound(SOUND_GOLD_2);
                break;
            case 3:
                this.playSound(SOUND_GOLD_3);
                break;
            default:
                this.playSound(SOUND_GOLD_4);
        }

    }

    public int getGold() {
        switch (Game.oR.nextInt(5)) {
            case 0:
                return SOUND_GOLD_0;
            case 1:
                return SOUND_GOLD_1;
            case 2:
                return SOUND_GOLD_2;
            case 3:
                return SOUND_GOLD_3;
            default:
                return SOUND_GOLD_4;
        }
    }

    public int getTab() {
        this.tabNum = (this.tabNum + 1) % 2;
        switch (this.tabNum % 2) {
            case 0:
                return TAB_0;
            default:
                return TAB_1;
        }
    }

    public int getBuild() {
        switch (Game.oR.nextInt(2)) {
            case 0:
                return BUILD0;
            default:
                return BUILD1;
        }
    }

    public int getDiplomacy() {
        switch (this.iDiplomacyButton++ % 2) {
            case 0:
                return DIPLOMACY0;
            default:
                return DIPLOMACY1;
        }
    }

    public int getCoin() {
        switch (Game.oR.nextInt(5)) {
            case 0:
                return SOUND_COIN_0;
            case 1:
                return SOUND_COIN_1;
            default:
                return SOUND_COIN_2;
        }
    }

    public int getEconomy() {
        switch (this.iEconomy++ % 2) {
            case 0:
                return SOUND_ECONOMY_0;
            default:
                return SOUND_ECONOMY_1;
        }
    }

    public int getInfrastructure() {
        switch (Game.oR.nextInt(2)) {
            case 0:
                return SOUND_INFRASTRUCTURE;
            default:
                return SOUND_INFRASTRUCTURE_1;
        }
    }

    public int getClickMain() {
        switch (Game.oR.nextInt(2)) {
            case 0:
                return SOUND_CLICK_MAIN;
            default:
                return SOUND_CLICK_MAIN2;
        }
    }

    public int getClickIncreaseManpower() {
        switch (this.iIncreaseManpower++ % 2) {
            case 0:
                return SOUND_INCREASE_MANPOWER;
            default:
                return SOUND_INCREASE_MANPOWER2;
        }
    }

}
