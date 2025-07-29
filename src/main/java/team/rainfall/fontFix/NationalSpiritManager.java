package team.rainfall.fontFix;

import aoc.kingdoms.lukasz.jakowski.FileManager;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.map.civilization.CivilizationBonuses;
import aoc.kingdoms.lukasz.textures.ImageManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class NationalSpiritManager {
    public static int nsEntryImg = -1;
    public static final NationalSpiritManager INSTANCE = new NationalSpiritManager();
    private Array<NationalSpirit> nationalSpirits = new Array<>();
    private HashMap<String,Integer> nsImages = new HashMap<>();
    public void loadNS(){
        try {
            Json json = new Json();
            json.setIgnoreUnknownFields(true);
            FileHandle fileList = FileManager.loadFile("game/NationalSpirit.json");
            json.setElementType(NSList.class, "nationalSpirits", NationalSpirit.class);
            NSList nsList = json.fromJson(NSList.class, fileList);
            this.nationalSpirits = nsList.nationalSpirits;
            for (NationalSpirit nationalSpirit : nationalSpirits) {
                nationalSpirit.desc = Game.lang.get(nationalSpirit.desc);
                nationalSpirit.name = Game.lang.get(nationalSpirit.name);
                int i = ImageManager.addImage("gfx/ns/"+nationalSpirit.id+".png");
                nsImages.put(nationalSpirit.id, i);
            }
        }catch (Exception e){
            Gdx.app.error("PolarisCore","Failed to load NS",e);
        }
    }
    public int getNSImage(String id){
        return nsImages.get(id);
    }
    public NationalSpirit getNS(String id){
        for (NationalSpirit nationalSpirit : nationalSpirits) {
            if(nationalSpirit.id.equals(id)){
                return nationalSpirit;
            }
        }
        return null;
    }
    public static void removeNS(int civID,String id){
        Game.getCiv(civID).eventsDataVariables.v.removeIf(s -> s.startsWith("$$NationalSpirit_" + id));
        CivilizationBonuses bonuses = INSTANCE.getNS(id).Bonuses;
        CivilizationBonuses advisor = Game.getCiv(civID).civBonuses;
        if(bonuses.MonthlyIncome != 0){
            advisor.MonthlyIncome -= bonuses.MonthlyIncome;
        }
        if (bonuses.TaxEfficiency != 0) {
            advisor.TaxEfficiency -= bonuses.TaxEfficiency;
        }
        if (bonuses.ProvinceMaintenance != 0) {
            advisor.ProvinceMaintenance -= bonuses.ProvinceMaintenance;
        }
        if (bonuses.GrowthRate != 0) {
            advisor.GrowthRate -= bonuses.GrowthRate;
        }
        if (bonuses.ProductionEfficiency != 0) {
            advisor.ProductionEfficiency -= bonuses.ProductionEfficiency;
        }
        if (bonuses.IncomeProduction != 0) {
            advisor.IncomeProduction -= bonuses.IncomeProduction;
        }
        if (bonuses.MonthlyLegacy != 0) {
            advisor.MonthlyLegacy -= bonuses.MonthlyLegacy;
        }
        if (bonuses.MaxManpower != 0) {
            advisor.MaxManpower -= bonuses.MaxManpower;
        }
        if (bonuses.ArmyMaintenance != 0) {
            advisor.ArmyMaintenance -= bonuses.ArmyMaintenance;
        }
        if (bonuses.RecruitmentTime != 0) {
            advisor.RecruitmentTime -= bonuses.RecruitmentTime;
        }
        if (bonuses.RecruitArmyCost != 0) {
            advisor.RecruitArmyCost -= bonuses.RecruitArmyCost;
        }
        if (bonuses.Research != 0) {
            advisor.Research -= bonuses.Research;
        }
        if (bonuses.ConstructionCost != 0) {
            advisor.ConstructionCost -= bonuses.ConstructionCost;
        }
        if (bonuses.AdministrationBuildingsCost != 0) {
            advisor.AdministrationBuildingsCost -= bonuses.AdministrationBuildingsCost;
        }
        if (bonuses.MilitaryBuildingsCost != 0) {
            advisor.MilitaryBuildingsCost -= bonuses.MilitaryBuildingsCost;
        }
        if (bonuses.EconomyBuildingsCost != 0) {
            advisor.EconomyBuildingsCost -= bonuses.EconomyBuildingsCost;
        }
        if (bonuses.ConstructionTime != 0) {
            advisor.ConstructionTime -= bonuses.ConstructionTime;
        }
        if (bonuses.InvestInEconomyCost != 0) {
            advisor.InvestInEconomyCost -= bonuses.InvestInEconomyCost;
        }
        if (bonuses.IncreaseManpowerCost != 0) {
            advisor.IncreaseManpowerCost -= bonuses.IncreaseManpowerCost;
        }
        if (bonuses.IncreaseTaxEfficiencyCost != 0) {
            advisor.IncreaseTaxEfficiencyCost -= bonuses.IncreaseTaxEfficiencyCost;
        }
        if (bonuses.IncreaseGrowthRateCost != 0) {
            advisor.IncreaseGrowthRateCost -= bonuses.IncreaseGrowthRateCost;
        }
        if (bonuses.DevelopInfrastructureCost != 0) {
            advisor.DevelopInfrastructureCost -= bonuses.DevelopInfrastructureCost;
        }
        if (bonuses.GeneralAttack != 0) {
            advisor.GeneralAttack -= bonuses.GeneralAttack;
        }
        if (bonuses.GeneralDefense != 0) {
            advisor.GeneralDefense -= bonuses.GeneralDefense;
        }
        if (bonuses.UnitsAttack != 0) {
            advisor.UnitsAttack -= bonuses.UnitsAttack;
        }
        if (bonuses.UnitsDefense != 0) {
            advisor.UnitsDefense -= bonuses.UnitsDefense;
        }
        if (bonuses.MaxMorale != 0) {
            advisor.MaxMorale -= bonuses.MaxMorale;
        }
        if (bonuses.ArmyMovementSpeed != 0) {
            advisor.ArmyMovementSpeed -= bonuses.ArmyMovementSpeed;
        }
        if (bonuses.SiegeEffectiveness != 0) {
            advisor.SiegeEffectiveness -= bonuses.SiegeEffectiveness;
        }
        if (bonuses.ImproveRelationsModifier != 0) {
            advisor.ImproveRelationsModifier -= bonuses.ImproveRelationsModifier;
        }
        if (bonuses.LoanInterest != 0) {
            advisor.LoanInterest -= bonuses.LoanInterest;
        }
        if (bonuses.CoreCost != 0) {
            advisor.CoreCost -= bonuses.CoreCost;
        }
        if (bonuses.ReligionCost != 0) {
            advisor.ReligionCost -= bonuses.ReligionCost;
        }
        if (bonuses.RegimentsLimit != 0) {
            advisor.RegimentsLimit -= bonuses.RegimentsLimit;
        }
    }
    public static void applyNS(int civID,String id){
        Game.getCiv(civID).eventsDataVariables.addVariable("$$NationalSpirit_"+id+"-0");
        CivilizationBonuses bonuses = INSTANCE.getNS(id).Bonuses;
        CivilizationBonuses advisor = Game.getCiv(civID).civBonuses;
        if(bonuses.MonthlyIncome != 0){
            advisor.MonthlyIncome += bonuses.MonthlyIncome;
        }
        if (bonuses.TaxEfficiency != 0) {
            advisor.TaxEfficiency += bonuses.TaxEfficiency;
        }
        if (bonuses.ProvinceMaintenance != 0) {
            advisor.ProvinceMaintenance += bonuses.ProvinceMaintenance;
        }
        if (bonuses.GrowthRate != 0) {
            advisor.GrowthRate += bonuses.GrowthRate;
        }
        if (bonuses.ProductionEfficiency != 0) {
            advisor.ProductionEfficiency += bonuses.ProductionEfficiency;
        }
        if (bonuses.IncomeProduction != 0) {
            advisor.IncomeProduction += bonuses.IncomeProduction;
        }
        if (bonuses.MonthlyLegacy != 0) {
            advisor.MonthlyLegacy += bonuses.MonthlyLegacy;
        }
        if (bonuses.MaxManpower != 0) {
            advisor.MaxManpower += bonuses.MaxManpower;
        }
        if (bonuses.ArmyMaintenance != 0) {
            advisor.ArmyMaintenance += bonuses.ArmyMaintenance;
        }
        if (bonuses.RecruitmentTime != 0) {
            advisor.RecruitmentTime += bonuses.RecruitmentTime;
        }
        if (bonuses.RecruitArmyCost != 0) {
            advisor.RecruitArmyCost += bonuses.RecruitArmyCost;
        }
        if (bonuses.Research != 0) {
            advisor.Research += bonuses.Research;
        }
        if (bonuses.ConstructionCost != 0) {
            advisor.ConstructionCost += bonuses.ConstructionCost;
        }
        if (bonuses.AdministrationBuildingsCost != 0) {
            advisor.AdministrationBuildingsCost += bonuses.AdministrationBuildingsCost;
        }
        if (bonuses.MilitaryBuildingsCost != 0) {
            advisor.MilitaryBuildingsCost += bonuses.MilitaryBuildingsCost;
        }
        if (bonuses.EconomyBuildingsCost != 0) {
            advisor.EconomyBuildingsCost += bonuses.EconomyBuildingsCost;
        }
        if (bonuses.ConstructionTime != 0) {
            advisor.ConstructionTime += bonuses.ConstructionTime;
        }
        if (bonuses.InvestInEconomyCost != 0) {
            advisor.InvestInEconomyCost += bonuses.InvestInEconomyCost;
        }
        if (bonuses.IncreaseManpowerCost != 0) {
            advisor.IncreaseManpowerCost += bonuses.IncreaseManpowerCost;
        }
        if (bonuses.IncreaseTaxEfficiencyCost != 0) {
            advisor.IncreaseTaxEfficiencyCost += bonuses.IncreaseTaxEfficiencyCost;
        }
        if (bonuses.IncreaseGrowthRateCost != 0) {
            advisor.IncreaseGrowthRateCost += bonuses.IncreaseGrowthRateCost;
        }
        if (bonuses.DevelopInfrastructureCost != 0) {
            advisor.DevelopInfrastructureCost += bonuses.DevelopInfrastructureCost;
        }
        if (bonuses.GeneralAttack != 0) {
            advisor.GeneralAttack += bonuses.GeneralAttack;
        }
        if (bonuses.GeneralDefense != 0) {
            advisor.GeneralDefense += bonuses.GeneralDefense;
        }
        if (bonuses.UnitsAttack != 0) {
            advisor.UnitsAttack += bonuses.UnitsAttack;
        }
        if (bonuses.UnitsDefense != 0) {
            advisor.UnitsDefense += bonuses.UnitsDefense;
        }
        if (bonuses.MaxMorale != 0) {
            advisor.MaxMorale += bonuses.MaxMorale;
        }
        if (bonuses.ArmyMovementSpeed != 0) {
            advisor.ArmyMovementSpeed += bonuses.ArmyMovementSpeed;
        }
        if (bonuses.SiegeEffectiveness != 0) {
            advisor.SiegeEffectiveness += bonuses.SiegeEffectiveness;
        }
        if (bonuses.ImproveRelationsModifier != 0) {
            advisor.ImproveRelationsModifier += bonuses.ImproveRelationsModifier;
        }
        if (bonuses.LoanInterest != 0) {
            advisor.LoanInterest += bonuses.LoanInterest;
        }
        if (bonuses.CoreCost != 0) {
            advisor.CoreCost += bonuses.CoreCost;
        }
        if (bonuses.ReligionCost != 0) {
            advisor.ReligionCost += bonuses.ReligionCost;
        }
        if (bonuses.RegimentsLimit != 0) {
            advisor.RegimentsLimit += bonuses.RegimentsLimit;
        }
    }
}
class NSList {
    Array<NationalSpirit> nationalSpirits = new Array<>();
}
