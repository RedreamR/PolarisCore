//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.map;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.FileManager;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.jakowski.SaveLoad.LoadManager;
import aoc.kingdoms.lukasz.map.civilization.CivilizationBonuses;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.menu_element.MenuElement;
import aoc.kingdoms.lukasz.menu_element.button.ButtonStatsRectIMG_Bonuses_Right_Color;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.*;
import aoc.kingdoms.lukasz.textures.Image;
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.textures.Images;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Json;
import java.util.ArrayList;
import java.util.List;

public class ReligionManager {
    public List<Religion> lReligions = null;
    public List<Image> religionImages = new ArrayList();
    private int iReligionsSize = 0;
    public int maxWidth = 0;
    public int maxHeight = 0;

    public ReligionManager() {
    }

    public final void loadReligions() {
        if (this.lReligions != null) {
            this.lReligions.clear();
        }

        this.lReligions = new ArrayList();

        try {
            FileHandle fileList = FileManager.loadFile("game/Religions.json");
            String fileContent = fileList.readString();
            Json json = new Json();
            json.setElementType(LoadManager.ConfigJson.class, "Data", Religion.class);
            LoadManager.ConfigJson data = json.fromJson(LoadManager.ConfigJson.class, fileContent);

            for(Object e : data.Data) {
                Religion tempData = (Religion)e;
                tempData.Name = Game.lang.get(tempData.Name);
                this.lReligions.add(tempData);
            }

            this.iReligionsSize = this.lReligions.size();

            for(int i = 0; i < this.iReligionsSize; ++i) {
                this.lReligions.get(i).Color[0] /= 255.0F;
                this.lReligions.get(i).Color[1] /= 255.0F;
                this.lReligions.get(i).Color[2] /= 255.0F;
            }

            Object var12 = null;
        } catch (GdxRuntimeException ex) {
            CFG.LOG(ex);
        }

        for(int i = 0; i < this.iReligionsSize; ++i) {
            try {
                if (FileManager.loadFile("gfx/religion/" + CFG.getRescouresPath_Short() + this.lReligions.get(i).Icon).exists()) {
                    this.religionImages.add(new Image(ImageManager.loadTexture("gfx/religion/" + CFG.getRescouresPath_Short() + this.lReligions.get(i).Icon)));
                } else {
                    this.religionImages.add(new Image(ImageManager.loadTexture("gfx/religion/" + CFG.getRescouresPath_Short_H() + this.lReligions.get(i).Icon)));
                }
            } catch (GdxRuntimeException var8) {
                this.religionImages.add(new Image(ImageManager.loadTexture("gfx/religion/" + CFG.getRescouresPath_Short() + "notfound.png")));
            }
        }

        for(int i = 0; i < this.iReligionsSize; ++i) {
            if (this.religionImages.get(i).getWidth() > this.maxWidth) {
                this.maxWidth = this.religionImages.get(i).getWidth();
            }

            if (this.religionImages.get(i).getHeight() > this.maxHeight) {
                this.maxHeight = this.religionImages.get(i).getHeight();
            }
        }

    }

    public final int getReligionsSize() {
        return this.iReligionsSize;
    }

    public final Religion getReligion(int i) {
        return this.lReligions.get(i);
    }

    public final int getReligionConversionCost(int iProvinceID) {
        return Math.max(1, (int)((GameValues.religion.DEFAULT_CONVERSION_COST + Game.getProvince(iProvinceID).getGrowthRateWithBonuses() * GameValues.religion.CONVERSION_COST_PER_GROWTH_RATE) * (1.0F + Game.getCiv(Game.getProvince(iProvinceID).getCivID()).getInflation() + Game.getCiv(Game.getProvince(iProvinceID).getCivID()).getWarWeariness() * GameValues.warWeariness.WW_CONVERSION_COST_PER_POINT + Game.getCiv(Game.getProvince(iProvinceID).getCivID()).civBonuses.ReligionCost / 100.0F) * (Game.religionManager.getReligion(Game.getProvince(iProvinceID).getReligion()).ReligionGroupID != Game.religionManager.getReligion(Game.getCiv(Game.getProvince(iProvinceID).getCivID()).getReligionID()).ReligionGroupID ? GameValues.religion.CONVERSION_COST_DIFFERENT_RELIGION_GROUP : 1.0F)));
    }

    public final int getReligionConversionTime(int iProvinceID) {
        return (int)((float)GameValues.religion.DEFAULT_CONVERSION_TIME + Math.min(GameValues.religion.DEFAULT_CONVERSION_TIME_POPULATION_MIN, (float)Game.getProvince(iProvinceID).getPopulationTotal() / GameValues.religion.DEFAULT_CONVERSION_TIME_POPULATION) * (Game.religionManager.getReligion(Game.getProvince(iProvinceID).getReligion()).ReligionGroupID != Game.religionManager.getReligion(Game.getCiv(Game.getProvince(iProvinceID).getCivID()).getReligionID()).ReligionGroupID ? GameValues.religion.CONVERSION_TIME_DIFFERENT_RELIGION_GROUP : 1.0F));
    }

    public final void updateCivBonuses(int iCivID, int religionID, int mod, boolean initMode) {
        CivilizationBonuses var10000 = Game.getCiv(iCivID).civBonuses;
        var10000.MonthlyIncome += this.getReligion(religionID).MonthlyIncome * (float)mod;
        var10000 = Game.getCiv(iCivID).civBonuses;
        var10000.TaxEfficiency += this.getReligion(religionID).TaxEfficiency * (float)mod;
        var10000 = Game.getCiv(iCivID).civBonuses;
        var10000.ProductionEfficiency += this.getReligion(religionID).ProductionEfficiency * (float)mod;
        var10000 = Game.getCiv(iCivID).civBonuses;
        var10000.ProvinceMaintenance += this.getReligion(religionID).ProvinceMaintenance * (float)mod;
        if (this.getReligion(religionID).MonthlyLegacy != 0.0F) {
            Game.gameThread.addCivUpdateLegacyPerMonth(iCivID);
        }

        var10000 = Game.getCiv(iCivID).civBonuses;
        var10000.MonthlyLegacy += this.getReligion(religionID).MonthlyLegacy * (float)mod;
        if (this.getReligion(religionID).MaxManpower != 0.0F) {
            Game.gameThreadTurns.addCivUpdateMaxManpower(iCivID);
        }

        var10000 = Game.getCiv(iCivID).civBonuses;
        var10000.MaxManpower += this.getReligion(religionID).MaxManpower * (float)mod;
        if (this.getReligion(religionID).ArmyMaintenance != 0.0F) {
            var10000 = Game.getCiv(iCivID).civBonuses;
            var10000.ArmyMaintenance += this.getReligion(religionID).ArmyMaintenance * (float)mod;
            Game.gameThread.addCivUpdateArmyMaintenance(iCivID);
        }

        var10000 = Game.getCiv(iCivID).civBonuses;
        var10000.RecruitmentTime += this.getReligion(religionID).RecruitmentTime * (float)mod;
        var10000 = Game.getCiv(iCivID).civBonuses;
        var10000.RecruitArmyCost += this.getReligion(religionID).RecruitArmyCost * (float)mod;
        var10000 = Game.getCiv(iCivID).civBonuses;
        var10000.ConstructionCost += this.getReligion(religionID).ConstructionCost * (float)mod;
        var10000 = Game.getCiv(iCivID).civBonuses;
        var10000.AdministrationBuildingsCost += this.getReligion(religionID).AdministrationBuildingsCost * (float)mod;
        var10000 = Game.getCiv(iCivID).civBonuses;
        var10000.EconomyBuildingsCost += this.getReligion(religionID).EconomyBuildingsCost * (float)mod;
        var10000 = Game.getCiv(iCivID).civBonuses;
        var10000.MilitaryBuildingsCost += this.getReligion(religionID).MilitaryBuildingsCost * (float)mod;
        var10000 = Game.getCiv(iCivID).civBonuses;
        var10000.ConstructionTime += this.getReligion(religionID).ConstructionTime * (float)mod;
        var10000 = Game.getCiv(iCivID).civBonuses;
        var10000.InvestInEconomyCost += this.getReligion(religionID).InvestInEconomyCost * (float)mod;
        var10000 = Game.getCiv(iCivID).civBonuses;
        var10000.IncreaseTaxEfficiencyCost += this.getReligion(religionID).IncreaseTaxEfficiencyCost * (float)mod;
        var10000 = Game.getCiv(iCivID).civBonuses;
        var10000.DevelopInfrastructureCost += this.getReligion(religionID).DevelopInfrastructureCost * (float)mod;
        var10000 = Game.getCiv(iCivID).civBonuses;
        var10000.IncreaseManpowerCost += this.getReligion(religionID).IncreaseManpowerCost * (float)mod;
        var10000 = Game.getCiv(iCivID).civBonuses;
        var10000.GeneralAttack += this.getReligion(religionID).GeneralAttack * mod;
        var10000 = Game.getCiv(iCivID).civBonuses;
        var10000.GeneralDefense += this.getReligion(religionID).GeneralDefense * mod;
        var10000 = Game.getCiv(iCivID).civBonuses;
        var10000.UnitsAttack += this.getReligion(religionID).UnitsAttack * mod;
        var10000 = Game.getCiv(iCivID).civBonuses;
        var10000.UnitsDefense += this.getReligion(religionID).UnitsDefense * mod;
        var10000 = Game.getCiv(iCivID).civBonuses;
        var10000.MaxNumberOfLoans += this.getReligion(religionID).MaxNumberOfLoans * mod;
        var10000 = Game.getCiv(iCivID).civBonuses;
        var10000.BuildingSlot += this.getReligion(religionID).BuildingSlot * mod;
        var10000 = Game.getCiv(iCivID).civBonuses;
        var10000.AdvisorCost += this.getReligion(religionID).AdvisorCost * (float)mod;
        var10000 = Game.getCiv(iCivID).civBonuses;
        var10000.GeneralCost += this.getReligion(religionID).GeneralCost * (float)mod;
        var10000 = Game.getCiv(iCivID).civBonuses;
        var10000.ReligionCost += this.getReligion(religionID).ReligionCost * (float)mod;
        var10000 = Game.getCiv(iCivID).civBonuses;
        var10000.CoreCost += this.getReligion(religionID).CoreCost * (float)mod;
        if (!initMode) {
            Game.getCiv(iCivID).updateProvincesIncomeAndExpenses();
        }

    }

    public final List<MenuElement> getMenuElements(int religionID, int iX, int iY, int iW, int iH) {
        List<MenuElement> mElementsToSort = new ArrayList();
        int maxIconW = ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 2;
        if (this.getReligion(religionID).MonthlyIncome != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("MonthlyIncome"), (this.getReligion(religionID).MonthlyIncome > 0.0F ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).MonthlyIncome, 100), Images.gold, iX, 0, iW, iH, maxIconW, this.getReligion(religionID).MonthlyIncome == 0.0F ? Colors.HOVER_NEUTRAL : (this.getReligion(religionID).MonthlyIncome < 0.0F ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }

        if (this.getReligion(religionID).TaxEfficiency != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("TaxEfficiency"), (this.getReligion(religionID).TaxEfficiency > 0.0F ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).TaxEfficiency, 100) + "%", Images.tax, iX, 0, iW, iH, maxIconW, this.getReligion(religionID).TaxEfficiency == 0.0F ? Colors.HOVER_NEUTRAL : (this.getReligion(religionID).TaxEfficiency < 0.0F ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }

        if (this.getReligion(religionID).ProvinceMaintenance != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("ProvinceMaintenance"), (this.getReligion(religionID).ProvinceMaintenance > 0.0F ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).ProvinceMaintenance, 100) + "%", Images.gold, iX, 0, iW, iH, maxIconW, this.getReligion(religionID).ProvinceMaintenance == 0.0F ? Colors.HOVER_NEUTRAL : (this.getReligion(religionID).ProvinceMaintenance > 0.0F ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }

        if (this.getReligion(religionID).ProductionEfficiency != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("ProductionEfficiency"), (this.getReligion(religionID).ProductionEfficiency > 0.0F ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).ProductionEfficiency, 100) + "%", Images.goods, iX, 0, iW, iH, maxIconW, this.getReligion(religionID).ProductionEfficiency == 0.0F ? Colors.HOVER_NEUTRAL : (this.getReligion(religionID).ProductionEfficiency < 0.0F ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }

        if (this.getReligion(religionID).MonthlyLegacy != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("MonthlyLegacy"), (this.getReligion(religionID).MonthlyLegacy > 0.0F ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).MonthlyLegacy, 100), Images.legacy, iX, 0, iW, iH, maxIconW, this.getReligion(religionID).MonthlyLegacy == 0.0F ? Colors.HOVER_NEUTRAL : (this.getReligion(religionID).MonthlyLegacy < 0.0F ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }

        if (this.getReligion(religionID).MaxManpower != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("MaximumManpower"), (this.getReligion(religionID).MaxManpower > 0.0F ? "+" : "") + (int)this.getReligion(religionID).MaxManpower, Game_Calendar.IMG_MANPOWER_UP, iX, 0, iW, iH, maxIconW, this.getReligion(religionID).MaxManpower == 0.0F ? Colors.HOVER_NEUTRAL : (this.getReligion(religionID).MaxManpower < 0.0F ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }

        if (this.getReligion(religionID).ArmyMaintenance != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("ArmyMaintenance"), (this.getReligion(religionID).ArmyMaintenance > 0.0F ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).ArmyMaintenance, 100) + "%", Images.armyMaintenance, iX, 0, iW, iH, maxIconW, this.getReligion(religionID).ArmyMaintenance == 0.0F ? Colors.HOVER_NEUTRAL : (this.getReligion(religionID).ArmyMaintenance > 0.0F ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }

        if (this.getReligion(religionID).RecruitmentTime != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("RecruitmentTime"), (this.getReligion(religionID).RecruitmentTime > 0.0F ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).RecruitmentTime, 100) + "%", Game_Calendar.IMG_MANPOWER_TIME, iX, 0, iW, iH, maxIconW, this.getReligion(religionID).RecruitmentTime == 0.0F ? Colors.HOVER_NEUTRAL : (this.getReligion(religionID).RecruitmentTime > 0.0F ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }

        if (this.getReligion(religionID).ConstructionCost != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("ConstructionCost"), (this.getReligion(religionID).ConstructionCost > 0.0F ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).ConstructionCost * 100.0F, 100) + "%", Images.construction, iX, 0, iW, iH, maxIconW, this.getReligion(religionID).ConstructionCost == 0.0F ? Colors.HOVER_NEUTRAL : (this.getReligion(religionID).ConstructionCost > 0.0F ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }

        if (this.getReligion(religionID).AdministrationBuildingsCost != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("AdministrationBuildingsCost"), (this.getReligion(religionID).AdministrationBuildingsCost > 0.0F ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).AdministrationBuildingsCost * 100.0F, 100) + "%", Images.construction, iX, 0, iW, iH, maxIconW, this.getReligion(religionID).AdministrationBuildingsCost == 0.0F ? Colors.HOVER_NEUTRAL : (this.getReligion(religionID).AdministrationBuildingsCost > 0.0F ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }

        if (this.getReligion(religionID).EconomyBuildingsCost != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("EconomyBuildingsCost"), (this.getReligion(religionID).EconomyBuildingsCost > 0.0F ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).EconomyBuildingsCost * 100.0F, 100) + "%", Images.construction, iX, 0, iW, iH, maxIconW, this.getReligion(religionID).EconomyBuildingsCost == 0.0F ? Colors.HOVER_NEUTRAL : (this.getReligion(religionID).EconomyBuildingsCost > 0.0F ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }

        if (this.getReligion(religionID).MilitaryBuildingsCost != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("MilitaryBuildingsCost"), (this.getReligion(religionID).MilitaryBuildingsCost > 0.0F ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).MilitaryBuildingsCost * 100.0F, 100) + "%", Images.construction, iX, 0, iW, iH, maxIconW, this.getReligion(religionID).MilitaryBuildingsCost == 0.0F ? Colors.HOVER_NEUTRAL : (this.getReligion(religionID).MilitaryBuildingsCost > 0.0F ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }

        if (this.getReligion(religionID).ConstructionTime != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("ConstructionTime"), (this.getReligion(religionID).ConstructionTime > 0.0F ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).ConstructionTime * 100.0F, 100) + "%", Images.buildTime, iX, 0, iW, iH, maxIconW, this.getReligion(religionID).ConstructionTime == 0.0F ? Colors.HOVER_NEUTRAL : (this.getReligion(religionID).ConstructionTime > 0.0F ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }

        if (this.getReligion(religionID).BuildingSlot != 0) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("BuildingSlot"), (this.getReligion(religionID).BuildingSlot > 0 ? "+" : "") + CFG.getPrecision2((float)this.getReligion(religionID).BuildingSlot, 100), Images.build, iX, 0, iW, iH, maxIconW, this.getReligion(religionID).BuildingSlot == 0 ? Colors.HOVER_NEUTRAL : (this.getReligion(religionID).BuildingSlot < 0 ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }

        if (this.getReligion(religionID).InvestInEconomyCost != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("InvestInEconomyCost"), CFG.getPrecision2(this.getReligion(religionID).InvestInEconomyCost * 100.0F, 100) + "%", Game_Calendar.IMG_ECONOMY_UP, iX, 0, iW, iH, maxIconW, this.getReligion(religionID).InvestInEconomyCost == 0.0F ? Colors.HOVER_NEUTRAL : (this.getReligion(religionID).InvestInEconomyCost > 0.0F ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }

        if (this.getReligion(religionID).IncreaseTaxEfficiencyCost != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("IncreaseTaxEfficiencyCost"), CFG.getPrecision2(this.getReligion(religionID).IncreaseTaxEfficiencyCost * 100.0F, 100) + "%", Images.taxUp, iX, 0, iW, iH, maxIconW, this.getReligion(religionID).IncreaseTaxEfficiencyCost == 0.0F ? Colors.HOVER_NEUTRAL : (this.getReligion(religionID).IncreaseTaxEfficiencyCost > 0.0F ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }

        if (this.getReligion(religionID).DevelopInfrastructureCost != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("DevelopInfrastructureCost"), CFG.getPrecision2(this.getReligion(religionID).DevelopInfrastructureCost * 100.0F, 100) + "%", Images.infrastructureUp, iX, 0, iW, iH, maxIconW, this.getReligion(religionID).DevelopInfrastructureCost == 0.0F ? Colors.HOVER_NEUTRAL : (this.getReligion(religionID).DevelopInfrastructureCost > 0.0F ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }

        if (this.getReligion(religionID).IncreaseManpowerCost != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("IncreaseManpowerCost"), CFG.getPrecision2(this.getReligion(religionID).IncreaseManpowerCost, 100) + "%", Game_Calendar.IMG_MANPOWER, iX, 0, iW, iH, maxIconW, this.getReligion(religionID).IncreaseManpowerCost == 0.0F ? Colors.HOVER_NEUTRAL : (this.getReligion(religionID).IncreaseManpowerCost > 0.0F ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }

        if (this.getReligion(religionID).RecruitArmyCost != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("ArmyRecruitmentCost"), CFG.getPrecision2(this.getReligion(religionID).RecruitArmyCost, 100) + "%", Images.gold, iX, 0, iW, iH, maxIconW, this.getReligion(religionID).RecruitArmyCost == 0.0F ? Colors.HOVER_NEUTRAL : (this.getReligion(religionID).RecruitArmyCost > 0.0F ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }

        if (this.getReligion(religionID).GeneralAttack != 0) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("GeneralsAttack"), (this.getReligion(religionID).GeneralAttack > 0 ? "+" : "") + CFG.getPrecision2((float)this.getReligion(religionID).GeneralAttack, 100), Images.attack, iX, 0, iW, iH, maxIconW, this.getReligion(religionID).GeneralAttack == 0 ? Colors.HOVER_NEUTRAL : (this.getReligion(religionID).GeneralAttack < 0 ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }

        if (this.getReligion(religionID).GeneralDefense != 0) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("GeneralsDefense"), (this.getReligion(religionID).GeneralDefense > 0 ? "+" : "") + CFG.getPrecision2((float)this.getReligion(religionID).GeneralDefense, 100), Images.defense, iX, 0, iW, iH, maxIconW, this.getReligion(religionID).GeneralDefense == 0 ? Colors.HOVER_NEUTRAL : (this.getReligion(religionID).GeneralDefense < 0 ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }

        if (this.getReligion(religionID).UnitsAttack != 0) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("UnitsAttack"), (this.getReligion(religionID).UnitsAttack > 0 ? "+" : "") + CFG.getPrecision2((float)this.getReligion(religionID).UnitsAttack, 100), Images.attack, iX, 0, iW, iH, maxIconW, this.getReligion(religionID).UnitsAttack == 0 ? Colors.HOVER_NEUTRAL : (this.getReligion(religionID).UnitsAttack < 0 ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }

        if (this.getReligion(religionID).UnitsDefense != 0) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("UnitsDefense"), (this.getReligion(religionID).UnitsDefense > 0 ? "+" : "") + CFG.getPrecision2((float)this.getReligion(religionID).UnitsDefense, 100), Images.defense, iX, 0, iW, iH, maxIconW, this.getReligion(religionID).UnitsDefense == 0 ? Colors.HOVER_NEUTRAL : (this.getReligion(religionID).UnitsDefense < 0 ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }

        if (this.getReligion(religionID).AdvisorCost != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("AdvisorCost"), (this.getReligion(religionID).AdvisorCost > 0.0F ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).AdvisorCost * 100.0F, 100) + "%", Images.council, iX, 0, iW, iH, maxIconW, this.getReligion(religionID).AdvisorCost == 0.0F ? Colors.HOVER_NEUTRAL : (this.getReligion(religionID).AdvisorCost > 0.0F ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }

        if (this.getReligion(religionID).GeneralCost != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("GeneralCost"), (this.getReligion(religionID).GeneralCost > 0.0F ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).GeneralCost * 100.0F, 100) + "%", Images.general, iX, 0, iW, iH, maxIconW, this.getReligion(religionID).GeneralCost == 0.0F ? Colors.HOVER_NEUTRAL : (this.getReligion(religionID).GeneralCost > 0.0F ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }

        if (this.getReligion(religionID).MaxNumberOfLoans != 0) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("MaximumNumberOfLoans"), (this.getReligion(religionID).MaxNumberOfLoans > 0 ? "+" : "") + this.getReligion(religionID).MaxNumberOfLoans, Images.loan, iX, 0, iW, iH, maxIconW, this.getReligion(religionID).MaxNumberOfLoans == 0 ? Colors.HOVER_NEUTRAL : (this.getReligion(religionID).MaxNumberOfLoans < 0 ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }

        if (this.getReligion(religionID).CoreCost != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("CoreConstruction"), (this.getReligion(religionID).CoreCost > 0.0F ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).CoreCost, 100) + "%", Images.core, iX, 0, iW, iH, maxIconW, this.getReligion(religionID).CoreCost == 0.0F ? Colors.HOVER_NEUTRAL : (this.getReligion(religionID).CoreCost > 0.0F ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }

        if (this.getReligion(religionID).ReligionCost != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("ReligionConversionCost"), (this.getReligion(religionID).ReligionCost > 0.0F ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).ReligionCost, 100) + "%", Images.religion, iX, 0, iW, iH, maxIconW, this.getReligion(religionID).ReligionCost == 0.0F ? Colors.HOVER_NEUTRAL : (this.getReligion(religionID).ReligionCost > 0.0F ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }

        List<MenuElement> elementsOut = new ArrayList();

        while(mElementsToSort.size() > 0) {
            int addID = 0;
            int o = 1;

            for(int oSize = mElementsToSort.size(); o < oSize; ++o) {
                if (CFG.compareAlphabetic_TwoString(mElementsToSort.get(addID).getText(), mElementsToSort.get(o).getText())) {
                    addID = o;
                }
            }

            elementsOut.add(mElementsToSort.get(addID));
            elementsOut.get(elementsOut.size() - 1).setPosY(iY);
            iY += elementsOut.get(elementsOut.size() - 1).getHeight() + CFG.PADDING;
            mElementsToSort.remove(addID);
        }

        return elementsOut;
    }

    public MenuElement_Hover getHoverReligion(int religionID, int civID) {
        List<MenuElement_HoverElement> nElements = new ArrayList();
        List<MenuElement_HoverElement_Type> nData = new ArrayList();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonusReligion(Game.lang.get("Religion") + ": ", this.getReligion(religionID).Name, religionID, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        if(Game.religionManager.getReligion(religionID).Desc != null) {
            nData.add(new MenuElement_HoverElement_Type_Line());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.religionManager.getReligion(religionID).Desc, CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT2));
            nElements.add(new MenuElement_HoverElement(nData));
        }

        int sizeBefore = nElements.size();
        if (this.getReligion(religionID).MonthlyIncome != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MonthlyIncome") + ": ", (this.getReligion(religionID).MonthlyIncome > 0.0F ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).MonthlyIncome, 100), Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, this.getReligion(religionID).MonthlyIncome > 0.0F ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (this.getReligion(religionID).MonthlyLegacy != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MonthlyLegacy") + ": ", (this.getReligion(religionID).MonthlyLegacy > 0.0F ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).MonthlyLegacy, 100), Images.legacy, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, this.getReligion(religionID).MonthlyLegacy > 0.0F ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (this.getReligion(religionID).TaxEfficiency != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("TaxEfficiency") + ": ", (this.getReligion(religionID).TaxEfficiency > 0.0F ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).TaxEfficiency, 100) + "%", Images.tax, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, this.getReligion(religionID).TaxEfficiency > 0.0F ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (this.getReligion(religionID).ProductionEfficiency != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ProductionEfficiency") + ": ", (this.getReligion(religionID).ProductionEfficiency > 0.0F ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).ProductionEfficiency, 100) + "%", Images.goods, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, this.getReligion(religionID).ProductionEfficiency > 0.0F ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (this.getReligion(religionID).ProvinceMaintenance != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ProvinceMaintenance") + ": ", (this.getReligion(religionID).ProvinceMaintenance > 0.0F ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).ProvinceMaintenance, 100) + "%", Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, this.getReligion(religionID).ProvinceMaintenance < 0.0F ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (this.getReligion(religionID).InvestInEconomyCost != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("InvestInEconomyCost") + ": ", (this.getReligion(religionID).InvestInEconomyCost > 0.0F ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).InvestInEconomyCost * 100.0F, 100) + "%", Game_Calendar.IMG_ECONOMY_UP, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, this.getReligion(religionID).InvestInEconomyCost < 0.0F ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (this.getReligion(religionID).IncreaseTaxEfficiencyCost != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("IncreaseTaxEfficiencyCost") + ": ", (this.getReligion(religionID).IncreaseTaxEfficiencyCost > 0.0F ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).IncreaseTaxEfficiencyCost * 100.0F, 100) + "%", Images.taxUp, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, this.getReligion(religionID).IncreaseTaxEfficiencyCost < 0.0F ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (this.getReligion(religionID).DevelopInfrastructureCost != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("DevelopInfrastructureCost") + ": ", (this.getReligion(religionID).DevelopInfrastructureCost > 0.0F ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).DevelopInfrastructureCost * 100.0F, 100) + "%", Images.infrastructureUp, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, this.getReligion(religionID).DevelopInfrastructureCost < 0.0F ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (this.getReligion(religionID).IncreaseManpowerCost != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("IncreaseManpowerCost") + ": ", (this.getReligion(religionID).IncreaseManpowerCost > 0.0F ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).IncreaseManpowerCost, 100) + "%", Game_Calendar.IMG_MANPOWER_UP, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, this.getReligion(religionID).IncreaseManpowerCost < 0.0F ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (this.getReligion(religionID).ConstructionCost != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ConstructionCost") + ": ", (this.getReligion(religionID).ConstructionCost > 0.0F ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).ConstructionCost * 100.0F, 100) + "%", Images.construction, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, this.getReligion(religionID).ConstructionCost * 100.0F < 0.0F ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (this.getReligion(religionID).AdministrationBuildingsCost != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("AdministrationBuildingsCost") + ": ", (this.getReligion(religionID).AdministrationBuildingsCost * 100.0F > 0.0F ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).AdministrationBuildingsCost * 100.0F, 100) + "%", Images.construction, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, this.getReligion(religionID).AdministrationBuildingsCost * 100.0F < 0.0F ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (this.getReligion(religionID).EconomyBuildingsCost != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("EconomyBuildingsCost") + ": ", (this.getReligion(religionID).EconomyBuildingsCost * 100.0F > 0.0F ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).EconomyBuildingsCost * 100.0F, 100) + "%", Images.construction, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, this.getReligion(religionID).EconomyBuildingsCost * 100.0F < 0.0F ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (this.getReligion(religionID).MilitaryBuildingsCost != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MilitaryBuildingsCost") + ": ", (this.getReligion(religionID).MilitaryBuildingsCost * 100.0F > 0.0F ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).MilitaryBuildingsCost * 100.0F, 100) + "%", Images.construction, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, this.getReligion(religionID).MilitaryBuildingsCost * 100.0F < 0.0F ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (this.getReligion(religionID).ConstructionTime != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ConstructionTime") + ": ", (this.getReligion(religionID).ConstructionTime > 0.0F ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).ConstructionTime * 100.0F, 100) + "%", Images.buildTime, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, this.getReligion(religionID).ConstructionTime < 0.0F ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (this.getReligion(religionID).BuildingSlot != 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("BuildingSlot") + ": ", (this.getReligion(religionID).BuildingSlot > 0 ? "+" : "") + CFG.getPrecision2((float)this.getReligion(religionID).BuildingSlot, 100), Images.build, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, this.getReligion(religionID).BuildingSlot > 0 ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (this.getReligion(religionID).MaxManpower != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaximumManpower") + ": ", (this.getReligion(religionID).MaxManpower > 0.0F ? "+" : "") + (int)this.getReligion(religionID).MaxManpower, Game_Calendar.IMG_MANPOWER_UP, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, this.getReligion(religionID).MaxManpower > 0.0F ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (this.getReligion(religionID).ArmyMaintenance != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ArmyMaintenance") + ": ", (this.getReligion(religionID).ArmyMaintenance > 0.0F ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).ArmyMaintenance, 100) + "%", Images.armyMaintenance, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, this.getReligion(religionID).ArmyMaintenance < 0.0F ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (this.getReligion(religionID).RecruitmentTime != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("RecruitmentTime") + ": ", (this.getReligion(religionID).RecruitmentTime > 0.0F ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).RecruitmentTime, 100) + "%", Game_Calendar.IMG_MANPOWER_TIME, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, this.getReligion(religionID).RecruitmentTime < 0.0F ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (this.getReligion(religionID).RecruitArmyCost != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ArmyRecruitmentCost") + ": ", (this.getReligion(religionID).RecruitArmyCost > 0.0F ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).RecruitArmyCost, 100) + "%", Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, this.getReligion(religionID).RecruitArmyCost < 0.0F ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (this.getReligion(religionID).GeneralAttack != 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("GeneralsAttack") + ": ", (this.getReligion(religionID).GeneralAttack > 0 ? "+" : "") + CFG.getPrecision2((float)this.getReligion(religionID).GeneralAttack, 1), Images.attack, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, this.getReligion(religionID).GeneralAttack > 0 ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (this.getReligion(religionID).GeneralDefense != 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("GeneralsDefense") + ": ", (this.getReligion(religionID).GeneralDefense > 0 ? "+" : "") + CFG.getPrecision2((float)this.getReligion(religionID).GeneralDefense, 1), Images.defense, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, this.getReligion(religionID).GeneralDefense > 0 ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (this.getReligion(religionID).UnitsAttack != 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("UnitsAttack") + ": ", (this.getReligion(religionID).UnitsAttack > 0 ? "+" : "") + CFG.getPrecision2((float)this.getReligion(religionID).UnitsAttack, 1), Images.attack, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, this.getReligion(religionID).UnitsAttack > 0 ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (this.getReligion(religionID).UnitsDefense != 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("UnitsDefense") + ": ", (this.getReligion(religionID).UnitsDefense > 0 ? "+" : "") + CFG.getPrecision2((float)this.getReligion(religionID).UnitsDefense, 1), Images.defense, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, this.getReligion(religionID).UnitsDefense > 0 ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (this.getReligion(religionID).AdvisorCost != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("AdvisorCost") + ": ", (this.getReligion(religionID).AdvisorCost > 0.0F ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).AdvisorCost * 100.0F, 100) + "%", Images.council, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, this.getReligion(religionID).AdvisorCost < 0.0F ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (this.getReligion(religionID).GeneralCost != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("GeneralCost") + ": ", (this.getReligion(religionID).GeneralCost > 0.0F ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).GeneralCost * 100.0F, 100) + "%", Images.general, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, this.getReligion(religionID).GeneralCost < 0.0F ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (this.getReligion(religionID).MaxNumberOfLoans != 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaximumNumberOfLoans") + ": ", (this.getReligion(religionID).MaxNumberOfLoans > 0 ? "+" : "") + CFG.getPrecision2((float)this.getReligion(religionID).MaxNumberOfLoans, 1), Images.loan, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, this.getReligion(religionID).MaxNumberOfLoans > 0 ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (this.getReligion(religionID).CoreCost != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("CoreConstruction") + ": ", (this.getReligion(religionID).CoreCost > 0.0F ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).CoreCost, 100) + "%", Images.core, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, this.getReligion(religionID).CoreCost < 0.0F ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (this.getReligion(religionID).ReligionCost != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ReligionConversionCost") + ": ", (this.getReligion(religionID).ReligionCost > 0.0F ? "+" : "") + CFG.getPrecision2(this.getReligion(religionID).ReligionCost, 100) + "%", Images.religion, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, this.getReligion(religionID).ReligionCost < 0.0F ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (civID > 0) {
            if (sizeBefore != nElements.size()) {
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }

            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Population") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text(CFG.getNumberWithSpaces("" + Game.getCiv(civID).getPopulationTotal()), CFG.FONT_BOLD_SMALL, Colors.COLOR_POPULATION));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.population, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();

            try {
                List<Integer> lReligionID = new ArrayList();
                List<Integer> religionPop = new ArrayList();

                for(int i = 0; i < Game.religionManager.getReligionsSize(); ++i) {
                    lReligionID.add(i);
                    religionPop.add(0);
                }

                for(int i = 0; i < Game.getCiv(civID).getNumOfProvinces(); ++i) {
                    religionPop.set(Game.getProvince(Game.getCiv(civID).getProvinceID(i)).getReligion(), religionPop.get(Game.getProvince(Game.getCiv(civID).getProvinceID(i)).getReligion()) + Game.getProvince(Game.getCiv(civID).getProvinceID(i)).getPopulationTotal());
                }

                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();

                for(int i = religionPop.size() - 1; i >= 0; --i) {
                    if (religionPop.get(i) <= 0) {
                        religionPop.remove(i);
                        lReligionID.remove(i);
                    }
                }

                while(religionPop.size() > 0) {
                    int bestID = 0;

                    for(int i = 1; i < religionPop.size(); ++i) {
                        if (religionPop.get(bestID) < religionPop.get(i)) {
                            bestID = i;
                        }
                    }

                    nData.add(new MenuElement_HoverElement_Type_Text(Game.religionManager.getReligion(lReligionID.get(bestID)).Name + ": ", CFG.FONT_REGULAR_SMALL));
                    nData.add(new MenuElement_HoverElement_Type_Text(CFG.getNumberWithSpaces("" + religionPop.get(bestID)), CFG.FONT_BOLD_SMALL, Colors.COLOR_POPULATION));
                    nData.add(new MenuElement_HoverElement_Type_Image(Images.population, CFG.PADDING, 0));
                    nData.add(new MenuElement_HoverElement_Type_Religion(lReligionID.get(bestID), CFG.PADDING, CFG.PADDING));
                    nData.add(new MenuElement_HoverElement_Type_Text(CFG.getPrecision2((float)((long) religionPop.get(bestID) / Game.getCiv(civID).getPopulationTotal()) * 100.0F, 100) + "%", CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    lReligionID.remove(bestID);
                    religionPop.remove(bestID);
                }
            } catch (Exception ex) {
                CFG.exceptionStack(ex);
            }
        }

        return new MenuElement_Hover(nElements);
    }

    public static class Religion {
        public String Name;
        public String Icon;
        public String Desc = null;
        public int ReligionGroupID;
        public float[] Color;
        public boolean Tribal = false;
        public float MonthlyIncome;
        public float TaxEfficiency;
        public float ProvinceMaintenance;
        public float ProductionEfficiency;
        public float MonthlyLegacy;
        public float MaxManpower;
        public float ArmyMaintenance;
        public float RecruitmentTime;
        public float ConstructionCost;
        public float AdministrationBuildingsCost;
        public float MilitaryBuildingsCost;
        public float EconomyBuildingsCost;
        public float ConstructionTime;
        public int BuildingSlot;
        public float InvestInEconomyCost;
        public float IncreaseManpowerCost;
        public float IncreaseTaxEfficiencyCost;
        public float DevelopInfrastructureCost;
        public float RecruitArmyCost;
        public int GeneralAttack;
        public int GeneralDefense;
        public int UnitsAttack;
        public int UnitsDefense;
        public float AdvisorCost;
        public float GeneralCost;
        public float CoreCost;
        public float ReligionCost;
        public int MaxNumberOfLoans;

        public Religion() {
        }
    }
}
