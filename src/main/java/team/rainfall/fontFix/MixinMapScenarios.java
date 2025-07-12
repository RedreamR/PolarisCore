package team.rainfall.fontFix;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.CharactersManager;
import aoc.kingdoms.lukasz.jakowski.FileManager;
import aoc.kingdoms.lukasz.jakowski.Game;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import team.rainfall.finality.luminosity2.annotations.Mixin;
import team.rainfall.finality.luminosity2.annotations.Shadow;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
@Mixin(mixinClass =  "aoc.kingdoms.lukasz.map.map.MapScenarios")
public class MixinMapScenarios {
    @Shadow
    public List<String> lScenarios_TagsList = new ArrayList();
    public final void buildProvincesReligion() {
        for(int i = 0; i < Game.getProvincesSize(); ++i) {
            if (!Game.getProvince(i).getSeaProvince() && Game.getProvince(i).getCivID() > 0) {
                Game.getProvince(i).setReligion_LoadScenario(Game.getCiv(Game.getProvince(i).getCivID()).getReligionID());
                Game.getProvince(i).addCore(Game.getProvince(i).getCivID());
            }
        }
    }
    public final void buildProvincesCores() {

    }

    public final void loadScenarioCharacters(boolean nEditor) {
        if (!nEditor) {
            try {
                if (FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + this.lScenarios_TagsList.get(Game.scenarioID) + "/" + "Characters.json").exists()) {
                    FileHandle fileList = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + this.lScenarios_TagsList.get(Game.scenarioID) + "/" + "Characters.json");
                    Json json = new Json();
                    ArrayList<JsonValue> tempArrayData = (ArrayList<JsonValue>)json.fromJson(ArrayList.class, fileList);

                    for(JsonValue jValue : tempArrayData) {
                        try {
                            CharactersManager.ScenarioCharacters tData = json.readValue(CharactersManager.ScenarioCharacters.class, jValue);
                            int civID = Game.getCivID(tData.CivTAG);
                            if (civID > 0) {
                                if (tData.Administrative != null && !tData.Administrative.isEmpty()) {
                                    CharactersManager.loadAdvisor(civID, tData.Administrative, 0);
                                }

                                if (tData.Economic != null && !tData.Economic.isEmpty()) {
                                    CharactersManager.loadAdvisor(civID, tData.Economic, 1);
                                }

                                if (tData.Innovation != null && !tData.Innovation.isEmpty()) {
                                    CharactersManager.loadAdvisor(civID, tData.Innovation, 2);
                                }

                                if (tData.Military != null && !tData.Military.isEmpty()) {
                                    CharactersManager.loadAdvisor(civID, tData.Military, 3);
                                }

                                if (tData.Generals != null && tData.Generals.length > 0) {
                                    for(int i = tData.Generals.length - 1; i >= 0; --i) {
                                        if (!tData.Generals[i].isEmpty()) {
                                            CharactersManager.loadGeneral(civID, tData.Generals[i], -99, -99);
                                        }
                                    }
                                }
                            }
                        } catch (Exception ex) {
                            CFG.exceptionStack(ex);
                        }
                    }

                    tempArrayData.clear();
                }
            } catch (Exception ex) {
                CFG.exceptionStack(ex);
            }
        }

    }
}
