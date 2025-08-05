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
    private HashMap<String, Integer> nsImages = new HashMap<>();

    public void loadNS() {
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
                int i = ImageManager.addImage("gfx/ns/" + nationalSpirit.id + ".png");
                nsImages.put(nationalSpirit.id, i);
            }
        } catch (Exception e) {
            Gdx.app.error("PolarisCore", "Failed to load NS", e);
        }
    }

    public int getNSImage(String id) {
        return nsImages.get(id);
    }

    public NationalSpirit getNS(String id) {
        for (NationalSpirit nationalSpirit : nationalSpirits) {
            if (nationalSpirit.id.equals(id)) {
                return nationalSpirit;
            }
        }
        return null;
    }

    public static void removeNS(int civID, String id) {
        if (Config.getConfig().applyNSv2) {
            removeNS2(civID, id);
            return;
        }
        Game.getCiv(civID).eventsDataVariables.v.removeIf(s -> s.startsWith("$$NationalSpirit_" + id));
        CivilizationBonuses bonuses = INSTANCE.getNS(id).Bonuses;
        CivilizationBonuses advisor = Game.getCiv(civID).civBonuses;
        if (bonuses.MonthlyIncome != 0) {
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

    public static void applyNS(int civID, String id) {
        if (Config.getConfig().applyNSv2) {
            applyNS2(civID, id);
            return;
        }
        Game.getCiv(civID).eventsDataVariables.addVariable("$$NationalSpirit_" + id + "-0");
        CivilizationBonuses bonuses = INSTANCE.getNS(id).Bonuses;
        CivilizationBonuses advisor = Game.getCiv(civID).civBonuses;
        if (bonuses.MonthlyIncome != 0) {
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

    public static void applyNS2(int civID, String id) {
        Game.getCiv(civID).eventsDataVariables.addVariable("$$NationalSpirit_" + id + "-0");
        CivilizationBonuses bonuses = INSTANCE.getNS(id).Bonuses;
        CivilizationBonuses advisor = Game.getCiv(civID).civBonuses;
        // --- 经济与收入 ---
        if (bonuses.MonthlyIncome != 0) {
            advisor.MonthlyIncome += bonuses.MonthlyIncome;
        }

        if (bonuses.TaxEfficiency != 0) {
            advisor.TaxEfficiency += bonuses.TaxEfficiency;
        }

        if (bonuses.ProvinceMaintenance != 0) {
            advisor.ProvinceMaintenance += bonuses.ProvinceMaintenance;
        }

        if (bonuses.BuildingsMaintenanceCost != 0) {
            advisor.BuildingsMaintenanceCost += bonuses.BuildingsMaintenanceCost;
        }

        if (bonuses.GrowthRate != 0) {
            advisor.GrowthRate += bonuses.GrowthRate;
        }

        if (bonuses.MaintenanceCost != 0) {
            advisor.MaintenanceCost += bonuses.MaintenanceCost;
        }

        if (bonuses.ProductionEfficiency != 0) {
            advisor.ProductionEfficiency += bonuses.ProductionEfficiency;
        }

        if (bonuses.IncomeProduction != 0) {
            advisor.IncomeProduction += bonuses.IncomeProduction;
        }

        if (bonuses.IncomeTaxation != 0) {
            advisor.IncomeTaxation += bonuses.IncomeTaxation;
        }

        if (bonuses.IncomeEconomy != 0) {
            advisor.IncomeEconomy += bonuses.IncomeEconomy;
        }

        if (bonuses.MonthlyLegacy != 0) {
            advisor.MonthlyLegacy += bonuses.MonthlyLegacy;
        }

        if (bonuses.MonthlyLegacy_Percentage != 0) {
            advisor.MonthlyLegacy_Percentage += bonuses.MonthlyLegacy_Percentage;
        }

// --- 人力与军事恢复 ---
        if (bonuses.MaxManpower != 0) {
            advisor.MaxManpower += bonuses.MaxManpower;
        }

        if (bonuses.MaxManpower_Percentage != 0) {
            advisor.MaxManpower_Percentage += bonuses.MaxManpower_Percentage;
        }

        if (bonuses.ManpowerRecoverySpeed != 0) {
            advisor.ManpowerRecoverySpeed += bonuses.ManpowerRecoverySpeed;
        }

        if (bonuses.ReinforcementSpeed != 0) {
            advisor.ReinforcementSpeed += bonuses.ReinforcementSpeed;
        }

        if (bonuses.ArmyMoraleRecovery != 0) {
            advisor.ArmyMoraleRecovery += bonuses.ArmyMoraleRecovery;
        }

// --- 战争与外交 ---
        if (bonuses.WarScoreCost != 0) {
            advisor.WarScoreCost += bonuses.WarScoreCost;
        }

        if (bonuses.ArmyMaintenance != 0) {
            advisor.ArmyMaintenance += bonuses.ArmyMaintenance;
        }

        if (bonuses.ImproveRelationsModifier != 0) {
            advisor.ImproveRelationsModifier += bonuses.ImproveRelationsModifier;
        }

        if (bonuses.IncomeFromVassals != 0) {
            advisor.IncomeFromVassals += bonuses.IncomeFromVassals;
        }

        if (bonuses.AggressiveExpansion != 0) {
            advisor.AggressiveExpansion += bonuses.AggressiveExpansion;
        }

        if (bonuses.MaxNumOfAlliances != 0) {
            advisor.MaxNumOfAlliances += bonuses.MaxNumOfAlliances;
        }

// --- 军队招募与成本 ---
        if (bonuses.RecruitmentTime != 0) {
            advisor.RecruitmentTime += bonuses.RecruitmentTime;
        }

        if (bonuses.RecruitArmyCost != 0) {
            advisor.RecruitArmyCost += bonuses.RecruitArmyCost;
        }

        if (bonuses.RecruitArmyFirstLineCost != 0) {
            advisor.RecruitArmyFirstLineCost += bonuses.RecruitArmyFirstLineCost;
        }

        if (bonuses.RecruitArmySecondLineCost != 0) {
            advisor.RecruitArmySecondLineCost += bonuses.RecruitArmySecondLineCost;
        }

// --- 科技与发展 ---
        if (bonuses.Research != 0) {
            advisor.Research += bonuses.Research;
        }

        if (bonuses.ResearchPoints != 0) {
            advisor.ResearchPoints += bonuses.ResearchPoints;
        }

        if (bonuses.TechnologyCost != 0) {
            advisor.TechnologyCost += bonuses.TechnologyCost;
        }

// --- 建设与成本 ---
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

        if (bonuses.WonderConstructionCost != 0) {
            advisor.WonderConstructionCost += bonuses.WonderConstructionCost;
        }

        if (bonuses.ConstructionTime != 0) {
            advisor.ConstructionTime += bonuses.ConstructionTime;
        }

        if (bonuses.BuildingSlot != 0) {
            advisor.BuildingSlot += bonuses.BuildingSlot; // 注意：目标为int，需要转换
        }

        if (bonuses.MaxInfrastructure != 0) {
            advisor.MaxInfrastructure += bonuses.MaxInfrastructure; // 注意：目标为int，需要转换
        }

// --- 投资与开发 ---
        if (bonuses.InvestInEconomyCost != 0) {
            advisor.InvestInEconomyCost += bonuses.InvestInEconomyCost;
        }

        if (bonuses.IncreaseManpowerCost != 0) {
            advisor.IncreaseManpowerCost += bonuses.IncreaseManpowerCost;
        }

        if (bonuses.IncreaseTaxEfficiencyCost != 0) {
            advisor.IncreaseTaxEfficiencyCost += bonuses.IncreaseTaxEfficiencyCost;
        }

        if (bonuses.DevelopInfrastructureCost != 0) {
            advisor.DevelopInfrastructureCost += bonuses.DevelopInfrastructureCost;
        }

        if (bonuses.IncreaseGrowthRateCost != 0) {
            advisor.IncreaseGrowthRateCost += bonuses.IncreaseGrowthRateCost;
        }

// --- 军事能力 ---
        if (bonuses.GeneralAttack != 0) {
            advisor.GeneralAttack += bonuses.GeneralAttack; // 注意：目标为int，需要转换
        }

        if (bonuses.GeneralDefense != 0) {
            advisor.GeneralDefense += bonuses.GeneralDefense; // 注意：目标为int，需要转换
        }

        if (bonuses.UnitsAttack != 0) {
            advisor.UnitsAttack += bonuses.UnitsAttack; // 注意：目标为int，需要转换
        }

        if (bonuses.UnitsDefense != 0) {
            advisor.UnitsDefense += bonuses.UnitsDefense; // 注意：目标为int，需要转换
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

        if (bonuses.Discipline != 0) {
            advisor.Discipline += bonuses.Discipline;
        }

        if (bonuses.BattleWidth != 0) {
            advisor.BattleWidth += bonuses.BattleWidth; // 注意：目标为int，需要转换
        }

// --- 财政与稳定 ---
        if (bonuses.LoanInterest != 0) {
            advisor.LoanInterest += bonuses.LoanInterest;
        }

        if (bonuses.RevolutionaryRisk != 0) {
            advisor.RevolutionaryRisk += bonuses.RevolutionaryRisk;
        }

        if (bonuses.Inflation != 0) {
            advisor.Inflation += bonuses.Inflation;
        }

        if (bonuses.Corruption != 0) {
            advisor.Corruption += bonuses.Corruption;
        }

        if (bonuses.MaximumAmountOfGold != 0) {
            advisor.MaximumAmountOfGold += bonuses.MaximumAmountOfGold;
        }

        if (bonuses.MaximumAmountOfGold_Percentage != 0) {
            advisor.MaximumAmountOfGold_Percentage += bonuses.MaximumAmountOfGold_Percentage;
        }

        if (bonuses.Loot != 0) {
            advisor.Loot += bonuses.Loot;
        }

// --- 核心与行政 ---
        if (bonuses.CoreCost != 0) {
            advisor.CoreCost += bonuses.CoreCost;
        }

        if (bonuses.ReligionCost != 0) {
            advisor.ReligionCost += bonuses.ReligionCost;
        }

// --- 顾问与人物 ---
        if (bonuses.AdvisorCost != 0) {
            advisor.AdvisorCost += bonuses.AdvisorCost;
        }

        if (bonuses.GeneralCost != 0) {
            advisor.GeneralCost += bonuses.GeneralCost;
        }

        if (bonuses.AdvisorMaxLevel != 0) {
            advisor.AdvisorMaxLevel += bonuses.AdvisorMaxLevel; // 注意：目标为int，需要转换
        }

        if (bonuses.AdvisorPoolSize != 0) {
            advisor.AdvisorPoolSize += bonuses.AdvisorPoolSize; // 注意：目标为int，需要转换
        }

        if (bonuses.AllCharactersLifeExpectancy != 0) {
            advisor.AllCharactersLifeExpectancy += bonuses.AllCharactersLifeExpectancy; // 注意：目标为int，需要转换
        }

// --- 其他系统 ---
        if (bonuses.MaxNumberOfLoans != 0) {
            advisor.MaxNumberOfLoans += bonuses.MaxNumberOfLoans; // 注意：目标为int，需要转换
        }

        if (bonuses.DiseaseDeathRate != 0) {
            advisor.DiseaseDeathRate += bonuses.DiseaseDeathRate;
        }

        if (bonuses.DiplomacyPoints != 0) {
            advisor.DiplomacyPoints += bonuses.DiplomacyPoints;
        }

        if (bonuses.Devastation != 0) {
            advisor.Devastation += bonuses.Devastation;
        }

        if (bonuses.RegimentsLimit != 0) {
            advisor.RegimentsLimit += bonuses.RegimentsLimit; // 注意：目标为int，需要转换
        }

        if (bonuses.ManpowerRecoveryFromADisbandedArmy != 0) {
            advisor.ManpowerRecoveryFromADisbandedArmy += bonuses.ManpowerRecoveryFromADisbandedArmy;
        }

// --- 建筑等级上限 ---
        if (bonuses.MaximumLevelOfCapitalCity != 0) {
            advisor.MaximumLevelOfCapitalCity += bonuses.MaximumLevelOfCapitalCity; // 注意：目标为int，需要转换
        }

        if (bonuses.MaximumLevelOfTheMilitaryAcademyForGenerals != 0) {
            advisor.MaximumLevelOfTheMilitaryAcademyForGenerals += bonuses.MaximumLevelOfTheMilitaryAcademyForGenerals; // 注意：目标为int，需要转换
        }

        if (bonuses.MaximumLevelOfTheMilitaryAcademy != 0) {
            advisor.MaximumLevelOfTheMilitaryAcademy += bonuses.MaximumLevelOfTheMilitaryAcademy; // 注意：目标为int，需要转换
        }

        if (bonuses.MaximumLevelOfTheSupremeCourt != 0) {
            advisor.MaximumLevelOfTheSupremeCourt += bonuses.MaximumLevelOfTheSupremeCourt; // 注意：目标为int，需要转换
        }

        if (bonuses.MaximumLevelOfNuclearReactor != 0) {
            advisor.MaximumLevelOfNuclearReactor += bonuses.MaximumLevelOfNuclearReactor; // 注意：目标为int，需要转换
        }

    }

    public static void removeNS2(int civID, String id) {
        Game.getCiv(civID).eventsDataVariables.v.removeIf(s -> s.startsWith("$$NationalSpirit_" + id));
        CivilizationBonuses bonuses = INSTANCE.getNS(id).Bonuses;
        CivilizationBonuses advisor = Game.getCiv(civID).civBonuses;
        // --- 经济与收入 ---
        if (bonuses.MonthlyIncome != 0) {
            advisor.MonthlyIncome -= bonuses.MonthlyIncome;
        }

        if (bonuses.TaxEfficiency != 0) {
            advisor.TaxEfficiency -= bonuses.TaxEfficiency;
        }

        if (bonuses.ProvinceMaintenance != 0) {
            advisor.ProvinceMaintenance -= bonuses.ProvinceMaintenance;
        }

        if (bonuses.BuildingsMaintenanceCost != 0) {
            advisor.BuildingsMaintenanceCost -= bonuses.BuildingsMaintenanceCost;
        }

        if (bonuses.GrowthRate != 0) {
            advisor.GrowthRate -= bonuses.GrowthRate;
        }

        if (bonuses.MaintenanceCost != 0) {
            advisor.MaintenanceCost -= bonuses.MaintenanceCost;
        }

        if (bonuses.ProductionEfficiency != 0) {
            advisor.ProductionEfficiency -= bonuses.ProductionEfficiency;
        }

        if (bonuses.IncomeProduction != 0) {
            advisor.IncomeProduction -= bonuses.IncomeProduction;
        }

        if (bonuses.IncomeTaxation != 0) {
            advisor.IncomeTaxation -= bonuses.IncomeTaxation;
        }

        if (bonuses.IncomeEconomy != 0) {
            advisor.IncomeEconomy -= bonuses.IncomeEconomy;
        }

        if (bonuses.MonthlyLegacy != 0) {
            advisor.MonthlyLegacy -= bonuses.MonthlyLegacy;
        }

        if (bonuses.MonthlyLegacy_Percentage != 0) {
            advisor.MonthlyLegacy_Percentage -= bonuses.MonthlyLegacy_Percentage;
        }

// --- 人力与军事恢复 ---
        if (bonuses.MaxManpower != 0) {
            advisor.MaxManpower -= bonuses.MaxManpower;
        }

        if (bonuses.MaxManpower_Percentage != 0) {
            advisor.MaxManpower_Percentage -= bonuses.MaxManpower_Percentage;
        }

        if (bonuses.ManpowerRecoverySpeed != 0) {
            advisor.ManpowerRecoverySpeed -= bonuses.ManpowerRecoverySpeed;
        }

        if (bonuses.ReinforcementSpeed != 0) {
            advisor.ReinforcementSpeed -= bonuses.ReinforcementSpeed;
        }

        if (bonuses.ArmyMoraleRecovery != 0) {
            advisor.ArmyMoraleRecovery -= bonuses.ArmyMoraleRecovery;
        }

// --- 战争与外交 ---
        if (bonuses.WarScoreCost != 0) {
            advisor.WarScoreCost -= bonuses.WarScoreCost;
        }

        if (bonuses.ArmyMaintenance != 0) {
            advisor.ArmyMaintenance -= bonuses.ArmyMaintenance;
        }

        if (bonuses.ImproveRelationsModifier != 0) {
            advisor.ImproveRelationsModifier -= bonuses.ImproveRelationsModifier;
        }

        if (bonuses.IncomeFromVassals != 0) {
            advisor.IncomeFromVassals -= bonuses.IncomeFromVassals;
        }

        if (bonuses.AggressiveExpansion != 0) {
            advisor.AggressiveExpansion -= bonuses.AggressiveExpansion;
        }

        if (bonuses.MaxNumOfAlliances != 0) {
            advisor.MaxNumOfAlliances -= bonuses.MaxNumOfAlliances;
        }

// --- 军队招募与成本 ---
        if (bonuses.RecruitmentTime != 0) {
            advisor.RecruitmentTime -= bonuses.RecruitmentTime;
        }

        if (bonuses.RecruitArmyCost != 0) {
            advisor.RecruitArmyCost -= bonuses.RecruitArmyCost;
        }

        if (bonuses.RecruitArmyFirstLineCost != 0) {
            advisor.RecruitArmyFirstLineCost -= bonuses.RecruitArmyFirstLineCost;
        }

        if (bonuses.RecruitArmySecondLineCost != 0) {
            advisor.RecruitArmySecondLineCost -= bonuses.RecruitArmySecondLineCost;
        }

// --- 科技与发展 ---
        if (bonuses.Research != 0) {
            advisor.Research -= bonuses.Research;
        }

        if (bonuses.ResearchPoints != 0) {
            advisor.ResearchPoints -= bonuses.ResearchPoints;
        }

        if (bonuses.TechnologyCost != 0) {
            advisor.TechnologyCost -= bonuses.TechnologyCost;
        }

// --- 建设与成本 ---
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

        if (bonuses.WonderConstructionCost != 0) {
            advisor.WonderConstructionCost -= bonuses.WonderConstructionCost;
        }

        if (bonuses.ConstructionTime != 0) {
            advisor.ConstructionTime -= bonuses.ConstructionTime;
        }

        if (bonuses.BuildingSlot != 0) {
            advisor.BuildingSlot -= bonuses.BuildingSlot; // 注意：目标为int，需要转换
        }

        if (bonuses.MaxInfrastructure != 0) {
            advisor.MaxInfrastructure -= bonuses.MaxInfrastructure; // 注意：目标为int，需要转换
        }

// --- 投资与开发 ---
        if (bonuses.InvestInEconomyCost != 0) {
            advisor.InvestInEconomyCost -= bonuses.InvestInEconomyCost;
        }

        if (bonuses.IncreaseManpowerCost != 0) {
            advisor.IncreaseManpowerCost -= bonuses.IncreaseManpowerCost;
        }

        if (bonuses.IncreaseTaxEfficiencyCost != 0) {
            advisor.IncreaseTaxEfficiencyCost -= bonuses.IncreaseTaxEfficiencyCost;
        }

        if (bonuses.DevelopInfrastructureCost != 0) {
            advisor.DevelopInfrastructureCost -= bonuses.DevelopInfrastructureCost;
        }

        if (bonuses.IncreaseGrowthRateCost != 0) {
            advisor.IncreaseGrowthRateCost -= bonuses.IncreaseGrowthRateCost;
        }

// --- 军事能力 ---
        if (bonuses.GeneralAttack != 0) {
            advisor.GeneralAttack -= bonuses.GeneralAttack; // 注意：目标为int，需要转换
        }

        if (bonuses.GeneralDefense != 0) {
            advisor.GeneralDefense -= bonuses.GeneralDefense; // 注意：目标为int，需要转换
        }

        if (bonuses.UnitsAttack != 0) {
            advisor.UnitsAttack -= bonuses.UnitsAttack; // 注意：目标为int，需要转换
        }

        if (bonuses.UnitsDefense != 0) {
            advisor.UnitsDefense -= bonuses.UnitsDefense; // 注意：目标为int，需要转换
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

        if (bonuses.Discipline != 0) {
            advisor.Discipline -= bonuses.Discipline;
        }

        if (bonuses.BattleWidth != 0) {
            advisor.BattleWidth -= bonuses.BattleWidth; // 注意：目标为int，需要转换
        }

// --- 财政与稳定 ---
        if (bonuses.LoanInterest != 0) {
            advisor.LoanInterest -= bonuses.LoanInterest;
        }

        if (bonuses.RevolutionaryRisk != 0) {
            advisor.RevolutionaryRisk -= bonuses.RevolutionaryRisk;
        }

        if (bonuses.Inflation != 0) {
            advisor.Inflation -= bonuses.Inflation;
        }

        if (bonuses.Corruption != 0) {
            advisor.Corruption -= bonuses.Corruption;
        }

        if (bonuses.MaximumAmountOfGold != 0) {
            advisor.MaximumAmountOfGold -= bonuses.MaximumAmountOfGold;
        }

        if (bonuses.MaximumAmountOfGold_Percentage != 0) {
            advisor.MaximumAmountOfGold_Percentage -= bonuses.MaximumAmountOfGold_Percentage;
        }

        if (bonuses.Loot != 0) {
            advisor.Loot -= bonuses.Loot;
        }

// --- 核心与行政 ---
        if (bonuses.CoreCost != 0) {
            advisor.CoreCost -= bonuses.CoreCost;
        }

        if (bonuses.ReligionCost != 0) {
            advisor.ReligionCost -= bonuses.ReligionCost;
        }

// --- 顾问与人物 ---
        if (bonuses.AdvisorCost != 0) {
            advisor.AdvisorCost -= bonuses.AdvisorCost;
        }

        if (bonuses.GeneralCost != 0) {
            advisor.GeneralCost -= bonuses.GeneralCost;
        }

        if (bonuses.AdvisorMaxLevel != 0) {
            advisor.AdvisorMaxLevel -= bonuses.AdvisorMaxLevel; // 注意：目标为int，需要转换
        }

        if (bonuses.AdvisorPoolSize != 0) {
            advisor.AdvisorPoolSize -= bonuses.AdvisorPoolSize; // 注意：目标为int，需要转换
        }

        if (bonuses.AllCharactersLifeExpectancy != 0) {
            advisor.AllCharactersLifeExpectancy -= bonuses.AllCharactersLifeExpectancy; // 注意：目标为int，需要转换
        }

// --- 其他系统 ---
        if (bonuses.MaxNumberOfLoans != 0) {
            advisor.MaxNumberOfLoans -= bonuses.MaxNumberOfLoans; // 注意：目标为int，需要转换
        }

        if (bonuses.DiseaseDeathRate != 0) {
            advisor.DiseaseDeathRate -= bonuses.DiseaseDeathRate;
        }

        if (bonuses.DiplomacyPoints != 0) {
            advisor.DiplomacyPoints -= bonuses.DiplomacyPoints;
        }

        if (bonuses.Devastation != 0) {
            advisor.Devastation -= bonuses.Devastation;
        }

        if (bonuses.RegimentsLimit != 0) {
            advisor.RegimentsLimit -= bonuses.RegimentsLimit; // 注意：目标为int，需要转换
        }

        if (bonuses.ManpowerRecoveryFromADisbandedArmy != 0) {
            advisor.ManpowerRecoveryFromADisbandedArmy -= bonuses.ManpowerRecoveryFromADisbandedArmy;
        }

// --- 建筑等级上限 ---
        if (bonuses.MaximumLevelOfCapitalCity != 0) {
            advisor.MaximumLevelOfCapitalCity -= bonuses.MaximumLevelOfCapitalCity; // 注意：目标为int，需要转换
        }

        if (bonuses.MaximumLevelOfTheMilitaryAcademyForGenerals != 0) {
            advisor.MaximumLevelOfTheMilitaryAcademyForGenerals -= bonuses.MaximumLevelOfTheMilitaryAcademyForGenerals; // 注意：目标为int，需要转换
        }

        if (bonuses.MaximumLevelOfTheMilitaryAcademy != 0) {
            advisor.MaximumLevelOfTheMilitaryAcademy -= bonuses.MaximumLevelOfTheMilitaryAcademy; // 注意：目标为int，需要转换
        }

        if (bonuses.MaximumLevelOfTheSupremeCourt != 0) {
            advisor.MaximumLevelOfTheSupremeCourt -= bonuses.MaximumLevelOfTheSupremeCourt; // 注意：目标为int，需要转换
        }

        if (bonuses.MaximumLevelOfNuclearReactor != 0) {
            advisor.MaximumLevelOfNuclearReactor -= bonuses.MaximumLevelOfNuclearReactor; // 注意：目标为int，需要转换
        }

    }
}

class NSList {
    Array<NationalSpirit> nationalSpirits = new Array<>();
}
