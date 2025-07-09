package team.rainfall.fontFix;

import aoc.kingdoms.lukasz.jakowski.FileManager;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.map.IdeologiesManager;
import aoc.kingdoms.lukasz.map.civilization.Civilization;
import aoc.kingdoms.lukasz.textures.Image;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import team.rainfall.finality.FinalityLogger;
import team.rainfall.finality.luminosity2.annotations.Mixin;
import team.rainfall.finality.luminosity2.annotations.Shadow;

@Mixin(mixinClass = "aoc.kingdoms.lukasz.map.civilization.Civilization")
public class MixinCivlization {
    @Shadow
    public String realTag;
    @Shadow
    public boolean isFlagNearest = false;
    @Shadow
    private Image civFlag = null;

    public final boolean loadFlag() {
        Civilization civilization = (Civilization) (Object) this;
        int gpID = Game.ideologiesManager.getIdeology(civilization.getIdeologyID()).GOV_GROUP_ID;
        String s = "_gp" + gpID;
        FinalityLogger.debug("FontFix.Flag "+"gfx/flagsXH/" + this.realTag+s + ".png");
        FinalityLogger.debug("FontFix.Flag "+"gfx/flagsXH/" + this.getCivTag() + ".png");
        if (FileManager.loadFile("gfx/flagsXH/" + this.getCivTag() + ".png").exists()) {
            this.civFlag = new Image(new Texture(FileManager.loadFile("gfx/flagsXH/" + this.getCivTag() + ".png"), Pixmap.Format.RGB888, false), Texture.TextureFilter.Linear);
        } else if (FileManager.loadFile("gfx/flagsXH/" + this.realTag+s + ".png").exists()) {
            this.civFlag = new Image(new Texture(FileManager.loadFile("gfx/flagsXH/" + this.realTag+s + ".png"), Pixmap.Format.RGB888, false), Texture.TextureFilter.Linear);
        } else if (FileManager.loadFile("gfx/flagsXH/" + this.realTag + ".png").exists()) {
            this.civFlag = new Image(new Texture(FileManager.loadFile("gfx/flagsXH/" + this.realTag + ".png"), Pixmap.Format.RGB888, false), Texture.TextureFilter.Linear);
        } else if (FileManager.loadFile("gfx/flagsH/" + this.getCivTag() + ".png").exists()) {
            this.civFlag = new Image(new Texture(FileManager.loadFile("gfx/flagsH/" + this.getCivTag() + ".png"), Pixmap.Format.RGB888, false), Texture.TextureFilter.Linear);
        }else if (FileManager.loadFile("gfx/flagsH/" + this.realTag+s + ".png").exists()) {
            this.civFlag = new Image(new Texture(FileManager.loadFile("gfx/flagsH/" + this.realTag+s + ".png"), Pixmap.Format.RGB888, false), Texture.TextureFilter.Linear);
        } else if (FileManager.loadFile("gfx/flagsH/" + this.realTag + ".png").exists()) {
            this.civFlag = new Image(new Texture(FileManager.loadFile("gfx/flagsH/" + this.realTag + ".png"), Pixmap.Format.RGB888, false), Texture.TextureFilter.Linear);
        } else if (FileManager.loadFile("gfx/flags/" + this.getCivTag() + ".png").exists()) {
            this.civFlag = new Image(new Texture(FileManager.loadFile("gfx/flags/" + this.getCivTag() + ".png"), Pixmap.Format.RGB888, false), Texture.TextureFilter.Nearest);
            this.isFlagNearest = true;
        }else if (FileManager.loadFile("gfx/flags/" + this.realTag+s + ".png").exists()) {
            this.civFlag = new Image(new Texture(FileManager.loadFile("gfx/flags/" + this.realTag+s + ".png"), Pixmap.Format.RGB888, false), Texture.TextureFilter.Nearest);
            this.isFlagNearest = true;
        } else if (FileManager.loadFile("gfx/flags/" + this.realTag + ".png").exists()) {
            this.civFlag = new Image(new Texture(FileManager.loadFile("gfx/flags/" + this.realTag + ".png"), Pixmap.Format.RGB888, false), Texture.TextureFilter.Nearest);
            this.isFlagNearest = true;
        } else if (FileManager.loadFile("mods/GameCivs/gfx/flagsH/" + this.getCivTag() + ".png").exists()) {
            this.civFlag = new Image(new Texture(FileManager.loadFile("mods/GameCivs/gfx/flagsH/" + this.getCivTag() + ".png"), Pixmap.Format.RGB888, false), Texture.TextureFilter.Linear);
        } else if (FileManager.loadFile("mods/GameCivs/gfx/flagsH/" + this.realTag + ".png").exists()) {
            this.civFlag = new Image(new Texture(FileManager.loadFile("mods/GameCivs/gfx/flagsH/" + this.realTag + ".png"), Pixmap.Format.RGB888, false), Texture.TextureFilter.Linear);
        } else {
            this.civFlag = new Image(new Texture(FileManager.loadFile("gfx/flagsXH/ran.png"), Pixmap.Format.RGB888, false), Texture.TextureFilter.Nearest);
            this.isFlagNearest = true;
        }
        if(Config.getConfig().forceFlagNearest){
            this.isFlagNearest = true;
        }
        return true;
    }
    @Shadow
    public final String getCivTag() {
        return "";
    }
}
