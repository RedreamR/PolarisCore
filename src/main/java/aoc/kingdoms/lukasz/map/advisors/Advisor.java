//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.map.advisors;

import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;

public class Advisor {
    public String Desc = "";
    public String sName = null;
    public int iYearOfBirth = 0;
    public int iMonthOfBirth = 0;
    public int iDayOfBirth = 0;
    public String sIMG = null;
    public int imageID = 0;
    public int iLevel = 1;
    public float TaxEfficiency;
    public float ProvinceMaintenance;
    public float GrowthRate;
    public float ProductionEfficiency;
    public float IncomeProduction;
    public float MonthlyLegacy;
    public float MaxManpower;
    public float ArmyMaintenance;
    public float RecruitmentTime;
    public float RecruitArmyCost;
    public float Research;
    public float ConstructionCost;
    public float AdministrationBuildingsCost;
    public float MilitaryBuildingsCost;
    public float EconomyBuildingsCost;
    public float ConstructionTime;
    public float InvestInEconomyCost;
    public float IncreaseManpowerCost;
    public float IncreaseTaxEfficiencyCost;
    public float IncreaseGrowthRateCost;
    public float DevelopInfrastructureCost;
    public float GeneralAttack;
    public float GeneralDefense;
    public float UnitsAttack;
    public float UnitsDefense;
    public float MaxMorale;
    public float ArmyMovementSpeed;
    public float SiegeEffectiveness;
    public float ImproveRelationsModifier;
    public float LoanInterest;
    public float CoreCost;
    public float ReligionCost;
    public int RegimentsLimit;

    public Advisor() {
    }

    public Advisor(String sName, int imageID, int iYearOfBirth, String sIMG) {
        this.sName = sName;
        this.imageID = imageID;
        this.iYearOfBirth = iYearOfBirth;
        this.sIMG = sIMG;
        this.iMonthOfBirth = 1 + Game.oR.nextInt(12);
        this.iDayOfBirth = 1 + Game.oR.nextInt(Game_Calendar.getNumOfDaysInMonth(this.iMonthOfBirth));
    }
}
