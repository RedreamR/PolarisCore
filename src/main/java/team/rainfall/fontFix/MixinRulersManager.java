package team.rainfall.fontFix;

import aoc.kingdoms.lukasz.jakowski.*;
import aoc.kingdoms.lukasz.map.Ruler;
import aoc.kingdoms.lukasz.map.RulersManager;
import aoc.kingdoms.lukasz.map.civilization.CivilizationBonuses;
import aoc.kingdoms.lukasz.textures.Image;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import team.rainfall.finality.FinalityLogger;
import team.rainfall.finality.luminosity2.annotations.Mixin;
import team.rainfall.finality.luminosity2.annotations.Shadow;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Mixin(mixinClass = "aoc.kingdoms.lukasz.map.RulersManager")
public class MixinRulersManager {
    @Shadow
    public static Image rulerIMG = null;
    @Shadow
    public static Image rulerIMG_DiplomacyLeft = null;
    @Shadow
    public static Image rulerIMG_DiplomacyRight = null;
    @Shadow
    public static List<List<String>> defaultRandomNames = new ArrayList();
    @Shadow
    public static List<Integer> NUM_OF_RANDOM_RULERS = new ArrayList();
    @Shadow
    public static List<Integer> nextRulerIMG = new ArrayList();
    @Shadow
    public static List<Integer> NUM_OF_RANDOM_RULERS_2 = new ArrayList();
    @Shadow
    public static List<Integer> nextRulerIMG_2 = new ArrayList();
    @Shadow
    public static List<String> groups = new ArrayList();



    public static String getRulerRandomName(int iCivID, String sCivTAG) {
        sCivTAG = Game.getCiv(iCivID).realTag;
        String civTagWithGov = Game.getCiv(iCivID).getCivTag();
        String civTagWithGP = Game.getCiv(iCivID).realTag + "_gp" + Game.ideologiesManager.getIdeology(Game.getCiv(iCivID).getIdeologyID()).GOV_GROUP_ID;

        if (Game.ideologiesManager.getIdeology(Game.getCiv(iCivID).getIdeologyID()).RulerRoman) {
            FileHandle fileList;

            if (FileManager.loadFile("game/rulersRandom/" + civTagWithGov + ".txt").exists()) {
                fileList = FileManager.loadFile("game/rulersRandom/" + civTagWithGov + ".txt");
                String[] tSplit = fileList.readString().split(";");
                if (tSplit.length > 0) {
                    return tSplit[Game.oR.nextInt(tSplit.length)] + " " + RomanNumber.getRoman(1 + Game.oR.nextInt(Math.max(1, GameValues.court.RULER_ROMAN_NUMBER_MAX_RANDOM)));
                }
            } else if (FileManager.loadFile("game/rulersRandom/link/" + civTagWithGov + ".txt").exists()) {
                fileList = FileManager.loadFile("game/rulersRandom/link/" + civTagWithGov + ".txt");
                sCivTAG = fileList.readString();
                if (FileManager.loadFile("game/rulersRandom/" + sCivTAG + ".txt").exists()) {
                    FileHandle fileList2 = FileManager.loadFile("game/rulersRandom/" + sCivTAG + ".txt");
                    String[] tSplit = fileList2.readString().split(";");
                    if (tSplit.length > 0) {
                        return tSplit[Game.oR.nextInt(tSplit.length)] + " " + RomanNumber.getRoman(1 + Game.oR.nextInt(Math.max(1, GameValues.court.RULER_ROMAN_NUMBER_MAX_RANDOM)));
                    }
                }
            }

            if (FileManager.loadFile("game/rulersRandom/" + civTagWithGP + ".txt").exists()) {
                fileList = FileManager.loadFile("game/rulersRandom/" + civTagWithGP + ".txt");
                String[] tSplit = fileList.readString().split(";");
                if (tSplit.length > 0) {
                    return tSplit[Game.oR.nextInt(tSplit.length)] + " " + RomanNumber.getRoman(1 + Game.oR.nextInt(Math.max(1, GameValues.court.RULER_ROMAN_NUMBER_MAX_RANDOM)));
                }
            } else if (FileManager.loadFile("game/rulersRandom/link/" + civTagWithGP + ".txt").exists()) {
                fileList = FileManager.loadFile("game/rulersRandom/link/" + civTagWithGP + ".txt");
                sCivTAG = fileList.readString();
                if (FileManager.loadFile("game/rulersRandom/" + sCivTAG + ".txt").exists()) {
                    FileHandle fileList2 = FileManager.loadFile("game/rulersRandom/" + sCivTAG + ".txt");
                    String[] tSplit = fileList2.readString().split(";");
                    if (tSplit.length > 0) {
                        return tSplit[Game.oR.nextInt(tSplit.length)] + " " + RomanNumber.getRoman(1 + Game.oR.nextInt(Math.max(1, GameValues.court.RULER_ROMAN_NUMBER_MAX_RANDOM)));
                    }
                }
            }

            if (FileManager.loadFile("game/rulersRandom/" + sCivTAG + ".txt").exists()) {
                fileList = FileManager.loadFile("game/rulersRandom/" + sCivTAG + ".txt");
                String[] tSplit = fileList.readString().split(";");
                if (tSplit.length > 0) {
                    return tSplit[Game.oR.nextInt(tSplit.length)] + " " + RomanNumber.getRoman(1 + Game.oR.nextInt(Math.max(1, GameValues.court.RULER_ROMAN_NUMBER_MAX_RANDOM)));
                }
            } else if (FileManager.loadFile("game/rulersRandom/link/" + sCivTAG + ".txt").exists()) {
                fileList = FileManager.loadFile("game/rulersRandom/link/" + sCivTAG + ".txt");
                sCivTAG = fileList.readString();
                if (FileManager.loadFile("game/rulersRandom/" + sCivTAG + ".txt").exists()) {
                    FileHandle fileList2 = FileManager.loadFile("game/rulersRandom/" + sCivTAG + ".txt");
                    String[] tSplit = fileList2.readString().split(";");
                    if (tSplit.length > 0) {
                        return tSplit[Game.oR.nextInt(tSplit.length)] + " " + RomanNumber.getRoman(1 + Game.oR.nextInt(Math.max(1, GameValues.court.RULER_ROMAN_NUMBER_MAX_RANDOM)));
                    }
                }
            }

            return Game.generalManager.getGeneralRandomName(iCivID) + " " + RomanNumber.getRoman(1 + Game.oR.nextInt(9));
        } else {
            return Game.generalManager.getGeneralRandomName(iCivID) + " " + Game.generalManager.getGeneralRandomSurname(iCivID);
        }
    }

    public static final void loadRuler(int iCivID, String sCivTAG, boolean random) {
        try {
            try {
                if (!random) {
                    String civTagWithGP = Game.getCiv(iCivID).realTag + "_gp" + Game.ideologiesManager.getIdeology(Game.getCiv(iCivID).getIdeologyID()).GOV_GROUP_ID;
                    FileHandle fileList = null;
                    if (FileManager.loadFile("game/rulers/" + sCivTAG + ".json").exists()) {
                        fileList = FileManager.loadFile("game/rulers/" + sCivTAG + ".json");
                    } else if (FileManager.loadFile("game/rulers/" + civTagWithGP + ".json").exists()) {
                        fileList = FileManager.loadFile("game/rulers/" + civTagWithGP + ".json");
                    } else if (FileManager.loadFile("game/rulers/" + Game.getCiv(iCivID).realTag + ".json").exists()) {
                        fileList = FileManager.loadFile("game/rulers/" + Game.getCiv(iCivID).realTag + ".json");
                    } else if (FileManager.loadFile("game/rulers/link/" + Game.getCiv(iCivID).realTag + ".txt").exists()) {
                        FileHandle tempFileT = FileManager.loadFile("game/rulers/link/" + Game.getCiv(iCivID).realTag + ".txt");
                        String tempFileName = tempFileT.readString();
                        if (FileManager.loadFile("game/rulers/" + tempFileName + ".json").exists()) {
                            fileList = FileManager.loadFile("game/rulers/" + tempFileName + ".json");
                        }
                    }

                    if (fileList != null) {
                        String fileContent = fileList.readString();
                        Json json = new Json();;
                        json.setElementType(RulersManager.Rulers.class,"Bonuses",CivilizationBonuses.class);
                        json.setElementType(RulersManager.ConfigRulersData.class, "Rulers", RulersManager.Rulers.class);
                        RulersManager.ConfigRulersData data = json.fromJson(RulersManager.ConfigRulersData.class, fileContent);
                        List<RulersManager.Rulers> tempRulers = new ArrayList();
                        int tRulersSize = 0;

                        for(Object e : data.Rulers) {
                            tempRulers.add((RulersManager.Rulers)e);
                            ++tRulersSize;
                        }

                        if (!tempRulers.isEmpty()) {
                            int bestID = 0;
                            if (tempRulers.get(bestID).ReignYear < Game_Calendar.currentYear) {
                                for(int i = tRulersSize - 1; i > 0; --i) {
                                    if (tempRulers.get(i).ReignYear <= Game_Calendar.currentYear) {
                                        bestID = i;
                                        break;
                                    }
                                }
                            }

                            if (tempRulers.get(bestID).ReignYear <= Game_Calendar.currentYear && tempRulers.get(bestID).BornYear > Game_Calendar.currentYear - 96 && tempRulers.get(bestID).BornYear < Game_Calendar.currentYear) {
                                if(getBonuses(tempRulers.get(bestID)) != null){
                                    Game.getCiv(iCivID).ruler = new Ruler(iCivID, tempRulers.get(bestID).Name, "" + tempRulers.get(bestID).ImageID, tempRulers.get(bestID).BornDay, tempRulers.get(bestID).BornMonth, tempRulers.get(bestID).BornYear, tempRulers.get(bestID).ReignYear, false, false,getBonuses(tempRulers.get(bestID)));
                                }else {
                                    Game.getCiv(iCivID).ruler = new Ruler(iCivID, tempRulers.get(bestID).Name, "" + tempRulers.get(bestID).ImageID, tempRulers.get(bestID).BornDay, tempRulers.get(bestID).BornMonth, tempRulers.get(bestID).BornYear, tempRulers.get(bestID).ReignYear, false, false);
                                }
                                Game.getCiv(iCivID).ruler.Desc = getDesc(tempRulers.get(bestID));
                                tempRulers.clear();
                                return;
                            }

                            tempRulers.clear();
                        }
                    }
                }
            } catch (Exception ex) {
                CFG.LOG("ERROR, CIV TAG: " + Game.getCiv(iCivID).getCivTag());
                CFG.exceptionStack(ex);
            }

            int nMonth = Game.oR.nextInt(12);
            if ((Game.ideologiesManager.getIdeology(Game.getCiv(iCivID).getIdeologyID()).KingsImages || !Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).ENABLE_NON_KINGS_IMG) && !Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).FORCE_NON_KINGS_IMG) {
                Game.getCiv(iCivID).ruler = new Ruler(iCivID, getRulerRandomName(iCivID, sCivTAG), "" + nextRulerIMG.get(Game.getCiv(iCivID).iGroupID), Game.oR.nextInt(Game_Calendar.getNumOfDaysInMonth(nMonth)), nMonth, Game_Calendar.currentYear - GameValues.court.RULER_YEARS_OLD_MIN - Game.oR.nextInt(GameValues.court.RULER_YEARS_OLD_RANDOM), Game_Calendar.currentYear, true, true);
                int nextIMG = nextRulerIMG.get(Game.getCiv(iCivID).iGroupID) + 1;
                if (nextIMG >= NUM_OF_RANDOM_RULERS.get(Game.getCiv(iCivID).iGroupID)) {
                    nextIMG = 0;
                }

                nextRulerIMG.set(Game.getCiv(iCivID).iGroupID, nextIMG);
            } else {
                Game.getCiv(iCivID).ruler = new Ruler(iCivID, getRulerRandomName(iCivID, sCivTAG), "" + nextRulerIMG_2.get(Game.getCiv(iCivID).iGroupID), Game.oR.nextInt(Game_Calendar.getNumOfDaysInMonth(nMonth)), nMonth, Game_Calendar.currentYear - GameValues.court.RULER_YEARS_OLD_MIN - Game.oR.nextInt(GameValues.court.RULER_YEARS_OLD_RANDOM), Game_Calendar.currentYear, true, false);
                int nextIMG = nextRulerIMG_2.get(Game.getCiv(iCivID).iGroupID) + 1;
                if (nextIMG >= NUM_OF_RANDOM_RULERS_2.get(Game.getCiv(iCivID).iGroupID)) {
                    nextIMG = 0;
                }

                nextRulerIMG_2.set(Game.getCiv(iCivID).iGroupID, nextIMG);
            }
        } catch (Exception ex) {
            CFG.LOG("ERROR, CIV TAG: " + Game.getCiv(iCivID).getCivTag());
            CFG.exceptionStack(ex);
        }

    }
    private static String getDesc(RulersManager.Rulers rulers){
        try {
            for (Field field : rulers.getClass().getFields()) {
                if (field.getName().equals("Desc")) {
                    return (String) field.get(rulers);
                }
            }
        } catch (Exception e){
            FinalityLogger.error("FontFix.rulerDesc Err ",e);
            return null;
        }
        return null;
    }
    private static CivilizationBonuses getBonuses(RulersManager.Rulers rulers){
        try {
            for (Field field : rulers.getClass().getFields()) {
                if (field.getName().equals("Bonuses")) {
                    return (CivilizationBonuses) field.get(rulers);
                }
            }
        } catch (Exception e){
            FinalityLogger.error("FontFix.rulerDesc Err ",e);
            return null;
        }
        return null;
    }
}
