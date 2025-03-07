package team.rainfall.fontFix;

import aoc.kingdoms.lukasz.jakowski.FileManager;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.RomanNumber;
import com.badlogic.gdx.files.FileHandle;
import team.rainfall.finality.luminosity2.annotations.Mixin;

@Mixin(mixinClass = "aoc.kingdoms.lukasz.map.RulersManager")
public class MixinRulersManager {
    public static String getRulerRandomName(int iCivID, String sCivTAG) {
        sCivTAG = Game.getCiv(iCivID).realTag;
        String civTagWithGov = Game.getCiv(iCivID).getCivTag();

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
}
