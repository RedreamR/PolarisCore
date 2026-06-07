package team.rainfall.fontFix.text;

import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.map.civilization.Civilization;

import java.lang.reflect.InvocationTargetException;

public class TextFuncService {
    public static String formatCiv(String s) {
        String[] list = s.split("\\.");
        int civID = Game.getCivID(list[1]);
        Civilization civilization = Game.getCiv(civID);
        switch (list[2]) {
            case "name":
                return civilization.getCivName();
            case "leader":
            case "leaderName":
            case "leader_name":
                return civilization.ruler.Name;
            case "gov":
            case "govName":
            case "gov_name":
                return Game.ideologiesManager.getIdeology(civilization.getIdeologyID()).Name;
        }
        return s;
    }
    public static String formatCounter(String s){
        String s1 = s.replace("counter.","");
        try {
            return (String) Class.forName("team.rainfall.rfEvent.rfEvent").getMethod("formatCounter", String.class).invoke(null,s1);
        } catch (NoSuchMethodException | ClassNotFoundException | InvocationTargetException | IllegalAccessException e){
            return s;
        }
    }
    public static String formatPlayer(String s){
        String[] list = s.split("\\.");
        Civilization civilization = Game.getCiv(Game.player.iCivID);
        switch (list[1]) {
            case "name":
                return civilization.getCivName();
            case "leader":
            case "leaderName":
            case "leader_name":
                return civilization.ruler.Name;
            case "gov":
            case "govName":
            case "gov_name":
                return Game.ideologiesManager.getIdeology(civilization.getIdeologyID()).Name;
            case "counter":
                return formatCounter(s.replace("player.","civ."+civilization.getCivTag()+"."));
        }
        return s;
    }
}
