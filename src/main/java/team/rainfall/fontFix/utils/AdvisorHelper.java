package team.rainfall.fontFix.utils;

import aoc.kingdoms.lukasz.jakowski.*;
import aoc.kingdoms.lukasz.map.advisors.Advisor;
import aoc.kingdoms.lukasz.map.civilization.CivilizationAdvisorsPool;
import aoc.kingdoms.lukasz.map.civilization.CivilizationBonuses;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import team.rainfall.finality.FinalityLogger;

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
                        json.setElementType(CharactersManager.Characters.class,"Bonuses", CivilizationBonuses.class);
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
                            advisor.Desc = getDesc(tData);
                            CivilizationBonuses bonuses = getBonuses(tData);
                            if(bonuses != null){
                                applyBonuses(advisor,bonuses);
                            }else {
                                CivilizationAdvisorsPool.buildAdvisorBonuses(advisor, iAdvisorType);
                            }
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
    private static CivilizationBonuses getBonuses(CharactersManager.Characters advisor){
        try {
            Object obj = advisor.getClass().getField("Bonuses").get(advisor);
            if(obj instanceof CivilizationBonuses){
                return (CivilizationBonuses) obj;
            }
        } catch (IllegalAccessException | NoSuchFieldException e) {
            FinalityLogger.error("",e);
        }
        return null;
    }

    public static void applyBonuses(Advisor advisor,CivilizationBonuses bonuses) {
        if (bonuses.TaxEfficiency != 0) {
            advisor.TaxEfficiency = bonuses.TaxEfficiency;
        }
        if (bonuses.ProvinceMaintenance != 0) {
            advisor.ProvinceMaintenance = bonuses.ProvinceMaintenance;
        }
        if (bonuses.GrowthRate != 0) {
            advisor.GrowthRate = bonuses.GrowthRate;
        }
        if (bonuses.ProductionEfficiency != 0) {
            advisor.ProductionEfficiency = bonuses.ProductionEfficiency;
        }
        if (bonuses.IncomeProduction != 0) {
            advisor.IncomeProduction = bonuses.IncomeProduction;
        }
        if (bonuses.MonthlyLegacy != 0) {
            advisor.MonthlyLegacy = bonuses.MonthlyLegacy;
        }
        if (bonuses.MaxManpower != 0) {
            advisor.MaxManpower = bonuses.MaxManpower;
        }
        if (bonuses.ArmyMaintenance != 0) {
            advisor.ArmyMaintenance = bonuses.ArmyMaintenance;
        }
        if (bonuses.RecruitmentTime != 0) {
            advisor.RecruitmentTime = bonuses.RecruitmentTime;
        }
        if (bonuses.RecruitArmyCost != 0) {
            advisor.RecruitArmyCost = bonuses.RecruitArmyCost;
        }
        if (bonuses.Research != 0) {
            advisor.Research = bonuses.Research;
        }
        if (bonuses.ConstructionCost != 0) {
            advisor.ConstructionCost = bonuses.ConstructionCost;
        }
        if (bonuses.AdministrationBuildingsCost != 0) {
            advisor.AdministrationBuildingsCost = bonuses.AdministrationBuildingsCost;
        }
        if (bonuses.MilitaryBuildingsCost != 0) {
            advisor.MilitaryBuildingsCost = bonuses.MilitaryBuildingsCost;
        }
        if (bonuses.EconomyBuildingsCost != 0) {
            advisor.EconomyBuildingsCost = bonuses.EconomyBuildingsCost;
        }
        if (bonuses.ConstructionTime != 0) {
            advisor.ConstructionTime = bonuses.ConstructionTime;
        }
        if (bonuses.InvestInEconomyCost != 0) {
            advisor.InvestInEconomyCost = bonuses.InvestInEconomyCost;
        }
        if (bonuses.IncreaseManpowerCost != 0) {
            advisor.IncreaseManpowerCost = bonuses.IncreaseManpowerCost;
        }
        if (bonuses.IncreaseTaxEfficiencyCost != 0) {
            advisor.IncreaseTaxEfficiencyCost = bonuses.IncreaseTaxEfficiencyCost;
        }
        if (bonuses.IncreaseGrowthRateCost != 0) {
            advisor.IncreaseGrowthRateCost = bonuses.IncreaseGrowthRateCost;
        }
        if (bonuses.DevelopInfrastructureCost != 0) {
            advisor.DevelopInfrastructureCost = bonuses.DevelopInfrastructureCost;
        }
        if (bonuses.GeneralAttack != 0) {
            advisor.GeneralAttack = bonuses.GeneralAttack;
        }
        if (bonuses.GeneralDefense != 0) {
            advisor.GeneralDefense = bonuses.GeneralDefense;
        }
        if (bonuses.UnitsAttack != 0) {
            advisor.UnitsAttack = bonuses.UnitsAttack;
        }
        if (bonuses.UnitsDefense != 0) {
            advisor.UnitsDefense = bonuses.UnitsDefense;
        }
        if (bonuses.MaxMorale != 0) {
            advisor.MaxMorale = bonuses.MaxMorale;
        }
        if (bonuses.ArmyMovementSpeed != 0) {
            advisor.ArmyMovementSpeed = bonuses.ArmyMovementSpeed;
        }
        if (bonuses.SiegeEffectiveness != 0) {
            advisor.SiegeEffectiveness = bonuses.SiegeEffectiveness;
        }
        if (bonuses.ImproveRelationsModifier != 0) {
            advisor.ImproveRelationsModifier = bonuses.ImproveRelationsModifier;
        }
        if (bonuses.LoanInterest != 0) {
            advisor.LoanInterest = bonuses.LoanInterest;
        }
        if (bonuses.CoreCost != 0) {
            advisor.CoreCost = bonuses.CoreCost;
        }
        if (bonuses.ReligionCost != 0) {
            advisor.ReligionCost = bonuses.ReligionCost;
        }
        if (bonuses.RegimentsLimit != 0) {
            advisor.RegimentsLimit = bonuses.RegimentsLimit;
        }
    }
    private static String getDesc(CharactersManager.Characters characters){
        try{
            String str = (String) characters.getClass().getField("Desc").get(characters);
            if(str != null && !str.isEmpty()){
                return str;
            }
        }catch (Exception ignored){

        }
        return "";
    }
}
