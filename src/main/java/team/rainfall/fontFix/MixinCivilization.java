package team.rainfall.fontFix;

import aoc.kingdoms.lukasz.jakowski.FileManager;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.map.civilization.Civilization;
import aoc.kingdoms.lukasz.map.technology.TechnologyResearch;
import aoc.kingdoms.lukasz.map.technology.TechnologyTree;
import aoc.kingdoms.lukasz.textures.Image;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import team.rainfall.finality.luminosity2.annotations.Mixin;
import team.rainfall.finality.luminosity2.annotations.Shadow;

@Mixin(mixinClass = "aoc.kingdoms.lukasz.map.civilization.Civilization")
public class MixinCivilization {
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
        if (FileManager.loadFile("gfx/flagsXH/" + this.getCivTag() + ".png").exists()) {
            this.civFlag = new Image(new Texture(FileManager.loadFile("gfx/flagsXH/" + this.getCivTag() + ".png"), Pixmap.Format.RGB888, false), Texture.TextureFilter.Linear);
        } else if (!Config.getConfig().fastLoadFlag && FileManager.loadFile("gfx/flagsXH/" + this.realTag + s + ".png").exists()) {
            this.civFlag = new Image(new Texture(FileManager.loadFile("gfx/flagsXH/" + this.realTag + s + ".png"), Pixmap.Format.RGB888, false), Texture.TextureFilter.Linear);
        } else if (FileManager.loadFile("gfx/flagsXH/" + this.realTag + ".png").exists()) {
            this.civFlag = new Image(new Texture(FileManager.loadFile("gfx/flagsXH/" + this.realTag + ".png"), Pixmap.Format.RGB888, false), Texture.TextureFilter.Linear);
        } else if (FileManager.loadFile("gfx/flagsH/" + this.getCivTag() + ".png").exists()) {
            this.civFlag = new Image(new Texture(FileManager.loadFile("gfx/flagsH/" + this.getCivTag() + ".png"), Pixmap.Format.RGB888, false), Texture.TextureFilter.Linear);
        } else if (!Config.getConfig().fastLoadFlag && FileManager.loadFile("gfx/flagsH/" + this.realTag + s + ".png").exists()) {
            this.civFlag = new Image(new Texture(FileManager.loadFile("gfx/flagsH/" + this.realTag + s + ".png"), Pixmap.Format.RGB888, false), Texture.TextureFilter.Linear);
        } else if (FileManager.loadFile("gfx/flagsH/" + this.realTag + ".png").exists()) {
            this.civFlag = new Image(new Texture(FileManager.loadFile("gfx/flagsH/" + this.realTag + ".png"), Pixmap.Format.RGB888, false), Texture.TextureFilter.Linear);
        } else if (FileManager.loadFile("gfx/flags/" + this.getCivTag() + ".png").exists()) {
            this.civFlag = new Image(new Texture(FileManager.loadFile("gfx/flags/" + this.getCivTag() + ".png"), Pixmap.Format.RGB888, false), Texture.TextureFilter.Nearest);
            this.isFlagNearest = true;
        } else if (!Config.getConfig().fastLoadFlag && FileManager.loadFile("gfx/flags/" + this.realTag + s + ".png").exists()) {
            this.civFlag = new Image(new Texture(FileManager.loadFile("gfx/flags/" + this.realTag + s + ".png"), Pixmap.Format.RGB888, false), Texture.TextureFilter.Nearest);
            this.isFlagNearest = true;
        } else if (FileManager.loadFile("gfx/flags/" + this.realTag + ".png").exists()) {
            this.civFlag = new Image(new Texture(FileManager.loadFile("gfx/flags/" + this.realTag + ".png"), Pixmap.Format.RGB888, false), Texture.TextureFilter.Nearest);
            this.isFlagNearest = true;
        } else {
            this.civFlag = new Image(new Texture(FileManager.loadFile("gfx/flagsXH/ran.png"), Pixmap.Format.RGB888, false), Texture.TextureFilter.Nearest);
            this.isFlagNearest = true;
        }
        if (Config.getConfig().forceFlagNearest) {
            this.isFlagNearest = true;
        }
        return true;
    }
    public final void addResearchProgress(int iTechID, float nProgress) {
        Civilization self = (Civilization) (Object) this;
        if (iTechID >= 0) {
            for(int i = self.lResearching.size() - 1; i >= 0; --i) {
                if (self.lResearching.get(i).iTechID == iTechID) {
                    self.lResearching.get(i).fProgress = Math.max(0.0F, self.lResearching.get(i).fProgress + nProgress);
                    if(nProgress == -727772f){
                        self.addTechnology(iTechID, false);
                    }
                    if (self.lResearching.get(i).fProgress >= TechnologyTree.getResearchCost(self.lResearching.get(i).iTechID, self.getCivID())) {
                        self.addTechnology(iTechID, false);
                    }

                    return;
                }
            }

            self.lResearching.add(new TechnologyResearch(iTechID));
            TechnologyResearch var10000 = self.lResearching.get(self.lResearching.size() - 1);
            var10000.fProgress += nProgress;
        }

    }
    @Shadow
    public final String getCivTag() {
        return "";
    }
}
