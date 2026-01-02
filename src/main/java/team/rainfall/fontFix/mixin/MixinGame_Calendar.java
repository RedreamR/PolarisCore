package team.rainfall.fontFix.mixin;

import aoc.kingdoms.lukasz.jakowski.Game;
import team.rainfall.finality.luminosity2.annotations.Mixin;

import static aoc.kingdoms.lukasz.jakowski.Game_Calendar.getMonthName;
@Mixin(mixinClass = "aoc.kingdoms.lukasz.jakowski.Game_Calendar")
public class MixinGame_Calendar {
    public static int TURN_ID = 1;
    public static int HOUR = 0;
    public static int IMG_MANPOWER = 0;
    public static int IMG_MANPOWER_SPLIT = 0;
    public static int IMG_MANPOWER_DISBAND = 0;
    public static int IMG_MANPOWER_REORGANIZE = 0;
    public static int IMG_MANPOWER_MERGE = 0;
    public static int IMG_MANPOWER_UP = 0;
    public static int IMG_MANPOWER_TIME = 0;
    public static int IMG_TECHNOLOGY = 0;
    public static int IMG_ECONOMY = 0;
    public static int IMG_ECONOMY_UP = 0;
    public static int IMG_ECONOMY_DOWN = 0;
    public static int IMG_FORT_DEFENSE = 0;
    public static int CURRENT_AGEID = 0;
    public static int currentDay = 1;
    public static int currentMonth = 1;
    public static int currentYear = 2014;
    public static final String getCurrentDate() {
        if(Game.settingsManager.LANGUAGE_TAG.contains("cn")){
            return Game.gameAges.getYear(currentYear) + "年" + getMonthName(currentMonth) + " " + currentDay + "日" +  (Game.HOURS_PER_TURN < 24 ? (HOUR < 10 ? "0" : "") + HOUR + ":00 " : "");
        }
        return (Game.HOURS_PER_TURN < 24 ? (HOUR < 10 ? "0" : "") + HOUR + ":00, " : "") + currentDay + " " + getMonthName(currentMonth) + " " + Game.gameAges.getYear(currentYear);
    }

    public static final String getCurrentDate_Simple() {
        return (Game.HOURS_PER_TURN < 24 ? (HOUR < 10 ? "0" : "") + HOUR + ":00, " : "") + currentDay + " " + currentMonth + " " + Game.gameAges.getYear(currentYear);
    }
}
