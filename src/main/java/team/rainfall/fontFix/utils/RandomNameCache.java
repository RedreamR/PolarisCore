package team.rainfall.fontFix.utils;

import aoc.kingdoms.lukasz.jakowski.FileManager;
import aoc.kingdoms.lukasz.jakowski.Game;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
 
import team.rainfall.fontFix.Config;
import team.rainfall.fontFix.FontFix;

import java.util.HashMap;

public class RandomNameCache {
    static RandomNamesData firstName = null;
    static RandomNamesData surName = null;
    static RandomNamesData rulerName = null;
    static HashMap<String,String > links = new HashMap<>();
    public static void init(){
        if(Config.getConfig().randNameCache){
            try {
                Json json = new Json();
                json.setIgnoreUnknownFields(true);
                surName = json.fromJson(RandomNamesData.class, FileManager.loadFile("game/randomNames/surnames/nameData.json"));
                json = new Json();
                json.setIgnoreUnknownFields(true);
                firstName = json.fromJson(RandomNamesData.class, FileManager.loadFile("game/randomNames/names/nameData.json"));
                json = new Json();
                json.setIgnoreUnknownFields(true);
                rulerName = json.fromJson(RandomNamesData.class, FileManager.loadFile("game/rulersRandom/nameData.json"));
                String linkString = FileManager.loadFile("game/rulersRandom/link/links").readString();
                String[] split = linkString.split("\\)");
                for (String s : split) {
                    String[] s1 = s.split(">");
                    links.put(s1[0], s1[1]);
                }
            }catch (Exception e){
                FontFix.LOGGER.error("Failed to load randNameCache",e);
                Config.getConfig().randNameCache = false;
            }
        }
    }

    public static String getRulerRandomName(int iCivID,String civTag) {
        if(rulerName == null) init();
        String sCivTAG = Game.getCiv(iCivID).realTag;
        String civTagWithGP = Game.getCiv(iCivID).realTag + "_gp" + Game.ideologiesManager.getIdeology(Game.getCiv(iCivID).getIdeologyID()).GOV_GROUP_ID;
        String civTagWithGov = Game.getCiv(iCivID).getCivTag();
        // --------
        String s = findLinkAndChoose2(sCivTAG);
        if(s != null) return s;
        s = findLinkAndChoose2(civTagWithGov);
        if(s != null) return s;
        s = findLinkAndChoose2(civTagWithGP);
        return s;
    }

    private static String findLinkAndChoose2(String name){
        String s = choose(name,rulerName);
        if(s != null) return s;
        String s1 = links.get(name);
        if(s1 != null){
            s = choose(s1, rulerName);
        }
        return s;
    }


    public static String getGeneralRandomName(int iCivID) {
        if(firstName == null) init();
        String sCivTAG = Game.getCiv(iCivID).realTag;
        String civTagWithGP = Game.getCiv(iCivID).realTag + "_gp" + Game.ideologiesManager.getIdeology(Game.getCiv(iCivID).getIdeologyID()).GOV_GROUP_ID;
        String civTagWithGov = Game.getCiv(iCivID).getCivTag();
        // --------
        String s = findLinkAndChoose(sCivTAG,false);
        if(s != null) return s;
        s = findLinkAndChoose(civTagWithGov,false);
        if(s != null) return s;
        s = findLinkAndChoose(civTagWithGP,false);
        return s;
    }
    public static String getGeneralRandomSurName(int iCivID) {
        if(surName == null) init();
        String sCivTAG = Game.getCiv(iCivID).realTag;
        String civTagWithGP = Game.getCiv(iCivID).realTag + "_gp" + Game.ideologiesManager.getIdeology(Game.getCiv(iCivID).getIdeologyID()).GOV_GROUP_ID;
        String civTagWithGov = Game.getCiv(iCivID).getCivTag();
        // --------
        String s = findLinkAndChoose(sCivTAG,true);
        if(s != null) return s;
        s = findLinkAndChoose(civTagWithGov,true);
        if(s != null) return s;
        s = findLinkAndChoose(civTagWithGP,true);
        return s;
    }

    private static String findLinkAndChoose(String name,boolean isSurName){
        String s = choose(name,isSurName ? surName : firstName);
        if(s != null) return s;
        String s1 = links.get(name);
        if(s1 != null){
            s = choose(s1, isSurName ? surName:firstName);
        }
        return s;
    }

    private static String choose(String name,RandomNamesData data){
        for (RandomNameData randomName : data.randomNames) {
            if(randomName.name.equals(name) && randomName.content.size > 0){
                return randomName.content.get(Game.oR.nextInt(randomName.content.size));
            }
        }
        return null;
    }
}
class RandomNamesData{
    Array<RandomNameData> randomNames = new Array<>();
}
class RandomNameData{
    String name;
    Array<String> content = new Array<>();
}