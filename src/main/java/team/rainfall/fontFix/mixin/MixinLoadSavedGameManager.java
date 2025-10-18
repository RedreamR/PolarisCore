package team.rainfall.fontFix.mixin;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.FileManager;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.map.civilization.CivilizationEventsData_Variables;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import team.rainfall.finality.luminosity2.annotations.Mixin;
import team.rainfall.fontFix.NationalSpiritManager;

import java.util.ArrayList;

import static aoc.kingdoms.lukasz.jakowski.SaveLoad.LoadSavedGameManager.key;

@Mixin(mixinClass = "aoc.kingdoms.lukasz.jakowski.SaveLoad.LoadSavedGameManager")
public class MixinLoadSavedGameManager {
    public static final void loadSave_CivsEventsVariables() {
        try {
            FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + key + "/" + "EventsVariables.json");
            Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList)json.fromJson(ArrayList.class, fileList);
            int tCivID = 1;

            for(JsonValue jValue : tempArrayData) {
                CivilizationEventsData_Variables tempData = json.readValue(CivilizationEventsData_Variables.class, jValue);
                if (tempData != null) {
                    for (String s : tempData.v) {
                        if(s.startsWith("$$NationalSpirit_")){
                            String s2 = s.replace("$$NationalSpirit_","").split("-")[0];
                            NationalSpiritManager.applyNS2(tCivID,s2);
                        }
                    }
                    Game.getCiv(tCivID).eventsDataVariables = tempData;

                }

                ++tCivID;
            }

            tempArrayData.clear();
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    public static final void loadSave_CivsEventsVariables2() {
        try {
            FileHandle fileList = FileManager.loadFile("saves/" + Game.map.getFile_ActiveMap_Path() + key + "/" + "EventsVariables2.json");
            Json json = new Json();
            ArrayList<JsonValue> tempArrayData = (ArrayList)json.fromJson(ArrayList.class, fileList);
            int tCivID = (int)Math.floor((float)Game.getCivsSize() / 2.0F);

            for(JsonValue jValue : tempArrayData) {
                CivilizationEventsData_Variables tempData = json.readValue(CivilizationEventsData_Variables.class, jValue);
                if (tempData != null) {
                    for (String s : tempData.v) {
                        if(s.startsWith("$$NationalSpirit_")){
                            String s2 = s.replace("$$NationalSpirit_","").split("-")[0];
                            NationalSpiritManager.applyNS2(tCivID,s2);
                        }
                    }
                    Game.getCiv(tCivID).eventsDataVariables = tempData;
                }

                ++tCivID;
            }

            tempArrayData.clear();
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }
}
