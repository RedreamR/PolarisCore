package team.rainfall.fontFix.utils;

import aoc.kingdoms.lukasz.jakowski.*;
import aoc.kingdoms.lukasz.map.advisors.Advisor;
import aoc.kingdoms.lukasz.map.advisors.AdvisorManager;
import aoc.kingdoms.lukasz.map.civilization.CivilizationAdvisorsPool;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

import java.util.ArrayList;

public class AdvisorHelper {
    public static ArrayList<PoolAdvisor> poolAdvisors = new ArrayList<>();
    public static Advisor loadAdvisor(int iCivID, String fileName, int iAdvisorType) {
        try {
            if (FileManager.loadFile("game/characters/" + fileName + ".json").exists()) {
                FileHandle fileList = FileManager.loadFile("game/characters/" + fileName + ".json");
                Json json = new Json();

                for(JsonValue jValue : (ArrayList<JsonValue>)json.fromJson(ArrayList.class, fileList)) {
                    try {
                        CharactersManager.Characters tData = json.readValue(CharactersManager.Characters.class, jValue);
                        if (tData != null && tData.ImageID != null && !tData.ImageID.isEmpty()) {
                            int bornYear = tData.BornYear;
                            if (Game_Calendar.currentYear - tData.BornYear < 10 || Game_Calendar.currentYear - tData.BornYear > 99) {
                                bornYear = Game_Calendar.currentYear - GameValues.advisors.ADVISOR_YEARS_OLD_MIN - Game.oR.nextInt(Math.max(1, GameValues.advisors.ADVISOR_YEARS_OLD_RANDOM));
                            }

                            int advIMG;
                            if (iAdvisorType == 3) {
                                advIMG = Game.advisorManager.getRandomGeneralImage(iCivID);
                            } else {
                                advIMG = Game.advisorManager.getRandomImage(iCivID, iAdvisorType);
                            }

                            Advisor advisor = new Advisor(CFG.checkName(tData.Name), advIMG, bornYear, tData.ImageID);
                            advisor = CivilizationAdvisorsPool.buildAdvisorBonuses(advisor, iAdvisorType);
                            advisor.iDayOfBirth = tData.BornDay;
                            advisor.iMonthOfBirth = tData.BornMonth;
                            return advisor;
                        }
                    } catch (Exception ex) {
                        CFG.exceptionStack(ex);
                    }
                }
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }
        return null;
    }
}
