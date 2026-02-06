package team.rainfall.fontFix.mixin;

import aoc.kingdoms.lukasz.jakowski.FileManager;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.SaveLoad.LoadManager;
import aoc.kingdoms.lukasz.jakowski.SaveLoad.SaveManager;
import aoc.kingdoms.lukasz.jakowski.setting.SettingsManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import team.rainfall.finality.FinalityLogger;
import team.rainfall.finality.luminosity2.annotations.Mixin;
import team.rainfall.fontFix.FontFix;

import static aoc.kingdoms.lukasz.jakowski.Game.*;
@Mixin(mixinClass = "aoc.kingdoms.lukasz.jakowski.Game")
public class MixinGame {
    public static final void clearActiveArmy() {
        Game.activeArmy.clear();
        activeArmySize = 0;
        setRegroupArmyMode(false);
    }
    public static void saveSettings() {
        Json json = SaveManager.getJson();
        json.setElementType(LoadManager.ConfigJson.class, "Data", SettingsManager.class);
        FileHandle file;
        if (FileManager.IS_MAC) {
            file = Gdx.files.external("settings/Settings.txt");
        } else {
            file = Gdx.files.local("settings/Settings.txt");
        }
        if(FontFix.isLocalStorage()){
            file = Gdx.files.external("settings/Settings.txt");
        }
        file.writeString(json.prettyPrint(settingsManager), false);
    }
}
