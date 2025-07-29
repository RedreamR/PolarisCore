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
import aoc.kingdoms.lukasz.map.civilization.Civilization;
import aoc.kingdoms.lukasz.map.civilization.CivilizationBonuses;
import aoc.kingdoms.lukasz.map.technology.TechnologyTree;
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

public class IdeologiesManager {
    public List<Ideology> lIdeologies = null;
    private int iIdeologiesSize = 0;
    public List<Image> ideologiesImages = new ArrayList();
    public int maxWidth = 0;
    public int maxHeight = 0;

    public IdeologiesManager() {
    }

    public final void loadIdeologies() {
        if (this.lIdeologies != null) {
            this.lIdeologies.clear();
        }

        this.lIdeologies = new ArrayList();

        try {
            FileHandle fileList = FileManager.loadFile("game/Governments.json");
            String fileContent = fileList.readString();
            Json json = new Json();
            json.setElementType(ConfigIdeologiesData.class, "Government", Ideology.class);
            new ConfigIdeologiesData();
            ConfigIdeologiesData data = (ConfigIdeologiesData)json.fromJson(ConfigIdeologiesData.class, fileContent);

            for(Object e : data.Government) {
                Ideology tempIdeology = (Ideology)e;
                tempIdeology.Name = Game.lang.get(tempIdeology.Name);
                tempIdeology.RulerTitle = Game.lang.get(tempIdeology.RulerTitle);
                tempIdeology.Extra_Tag = tempIdeology.Extra_Tag.length() > 0 ? "_" + tempIdeology.Extra_Tag : "";

                for(int i = tempIdeology.AI_BUILD_SCORE.length - 1; i >= 0; --i) {
                    tempIdeology.AI_BUILD_SCORE_TOTAL += tempIdeology.AI_BUILD_SCORE[i];
                }

                this.lIdeologies.add(tempIdeology);
            }

            this.iIdeologiesSize = this.lIdeologies.size();

            for(int i = 0; i < this.iIdeologiesSize; ++i) {
                this.lIdeologies.get(i).Color[0] /= 255.0F;
                this.lIdeologies.get(i).Color[1] /= 255.0F;
                this.lIdeologies.get(i).Color[2] /= 255.0F;
            }
        } catch (GdxRuntimeException var10) {
        }

        for(int i = 0; i < this.iIdeologiesSize; ++i) {
            try {
                if (FileManager.loadFile("gfx/government/" + CFG.getRescouresPath_Short() + "gov" + ((Ideology)this.lIdeologies.get(i)).Extra_Tag + ".png").exists()) {
                    this.ideologiesImages.add(new Image(ImageManager.loadTexture("gfx/government/" + CFG.getRescouresPath_Short() + "gov" + ((Ideology)this.lIdeologies.get(i)).Extra_Tag + ".png")));
                } else {
                    this.ideologiesImages.add(new Image(ImageManager.loadTexture("gfx/government/" + CFG.getRescouresPath_Short_H() + "gov" + ((Ideology)this.lIdeologies.get(i)).Extra_Tag + ".png")));
                }
            } catch (GdxRuntimeException var9) {
                this.ideologiesImages.add(new Image(ImageManager.loadTexture("gfx/government/" + CFG.getRescouresPath_Short() + "gov.png")));
            }
        }

        for(int i = 0; i < this.iIdeologiesSize; ++i) {
            if (((Image)this.ideologiesImages.get(i)).getWidth() > this.maxWidth) {
                this.maxWidth = ((Image)this.ideologiesImages.get(i)).getWidth();
            }

            if (((Image)this.ideologiesImages.get(i)).getHeight() > this.maxHeight) {
                this.maxHeight = ((Image)this.ideologiesImages.get(i)).getHeight();
            }
        }

    }

    public final String getRealTag(String sIn) {
        return sIn.contains("_") ? sIn.substring(0, sIn.indexOf(95)) : sIn;
    }

    public final int getIdeologyID(String nCivTag) {
        if (nCivTag.lastIndexOf(95) > 0) {
            String trueTag = nCivTag.substring(0, nCivTag.lastIndexOf(95) + 2);

            for(int i = 0; i < this.iIdeologiesSize; ++i) {
                try {
                    if (trueTag.charAt(trueTag.length() - 1) == ((Ideology)this.lIdeologies.get(i)).Extra_Tag.charAt(1) || trueTag.charAt(trueTag.indexOf(95) + 1) == ((Ideology)this.lIdeologies.get(i)).Extra_Tag.charAt(1)) {
                        return i;
                    }
                } catch (StringIndexOutOfBoundsException var5) {
                }
            }
        }

        return 0;
    }

    protected boolean canBeAdded(int nCivID, int nIdeologyID) {
        String tTag = Game.getCiv(nCivID).realTag + ((Ideology)this.lIdeologies.get(nIdeologyID)).Extra_Tag;

        for(int i = 0; i < Game.getCivsSize(); ++i) {
            if (Game.getCiv(i).getCivTag().equals(tTag)) {
                return false;
            }
        }

        return true;
    }

    protected List<Boolean> canChangeToIdeology(int nCivID) {
        List<Boolean> out = new ArrayList();

        for(int i = 0; i < this.getIdeologiesSize(); ++i) {
            if (i == Game.getCiv(nCivID).getIdeologyID()) {
                out.add(false);
            } else if (((Ideology)this.lIdeologies.get(i)).REQUIRED_TECHNOLOGY >= 0 && !Game.getCiv(nCivID).getTechResearched(((Ideology)this.lIdeologies.get(i)).REQUIRED_TECHNOLOGY)) {
                out.add(false);
            } else if (!this.canBeAdded(nCivID, i)) {
                out.add(false);
            } else if (((Ideology)this.lIdeologies.get(i)).REVOLUTIONISTS) {
                out.add(false);
            } else {
                out.add(true);
            }
        }

        return out;
    }

    public final int getIdeologiesSize() {
        return this.iIdeologiesSize;
    }

    public final Ideology getIdeology(int i) {
        return (Ideology)this.lIdeologies.get(i);
    }

    public final void updateCivBonuses(int iCivID, int ideologyID, int mod, boolean initMode) {
        CivilizationBonuses var10000 = Game.getCiv(iCivID).civBonuses;
        var10000.MonthlyIncome += this.getIdeology(ideologyID).MonthlyIncome * (float)mod;
        var10000 = Game.getCiv(iCivID).civBonuses;
        var10000.TaxEfficiency += this.getIdeology(ideologyID).TaxEfficiency * (float)mod;
        var10000 = Game.getCiv(iCivID).civBonuses;
        var10000.ProductionEfficiency += this.getIdeology(ideologyID).ProductionEfficiency * (float)mod;
        var10000 = Game.getCiv(iCivID).civBonuses;
        var10000.ProvinceMaintenance += this.getIdeology(ideologyID).ProvinceMaintenance * (float)mod;
        if (this.getIdeology(ideologyID).MonthlyLegacy != 0.0F) {
            Game.gameThread.addCivUpdateLegacyPerMonth(iCivID);
        }

        var10000 = Game.getCiv(iCivID).civBonuses;
        var10000.MonthlyLegacy += this.getIdeology(ideologyID).MonthlyLegacy * (float)mod;
        if (this.getIdeology(ideologyID).MaxManpower != 0.0F) {
            Game.gameThreadTurns.addCivUpdateMaxManpower(iCivID);
        }

        var10000 = Game.getCiv(iCivID).civBonuses;
        var10000.MaxManpower += this.getIdeology(ideologyID).MaxManpower * (float)mod;
        if (this.getIdeology(ideologyID).ArmyMaintenance != 0.0F) {
            var10000 = Game.getCiv(iCivID).civBonuses;
            var10000.ArmyMaintenance += this.getIdeology(ideologyID).ArmyMaintenance * (float)mod;
            Game.gameThread.addCivUpdateArmyMaintenance(iCivID);
        }

        var10000 = Game.getCiv(iCivID).civBonuses;
        var10000.RecruitmentTime += this.getIdeology(ideologyID).RecruitmentTime * (float)mod;
        var10000 = Game.getCiv(iCivID).civBonuses;
        var10000.RecruitArmyCost += this.getIdeology(ideologyID).RecruitArmyCost * (float)mod;
        var10000 = Game.getCiv(iCivID).civBonuses;
        var10000.ConstructionCost += this.getIdeology(ideologyID).ConstructionCost * (float)mod;
        var10000 = Game.getCiv(iCivID).civBonuses;
        var10000.AdministrationBuildingsCost += this.getIdeology(ideologyID).AdministrationBuildingsCost * (float)mod;
        var10000 = Game.getCiv(iCivID).civBonuses;
        var10000.EconomyBuildingsCost += this.getIdeology(ideologyID).EconomyBuildingsCost * (float)mod;
        var10000 = Game.getCiv(iCivID).civBonuses;
        var10000.MilitaryBuildingsCost += this.getIdeology(ideologyID).MilitaryBuildingsCost * (float)mod;
        var10000 = Game.getCiv(iCivID).civBonuses;
        var10000.ConstructionTime += this.getIdeology(ideologyID).ConstructionTime * (float)mod;
        var10000 = Game.getCiv(iCivID).civBonuses;
        var10000.InvestInEconomyCost += this.getIdeology(ideologyID).InvestInEconomyCost * (float)mod;
        var10000 = Game.getCiv(iCivID).civBonuses;
        var10000.IncreaseTaxEfficiencyCost += this.getIdeology(ideologyID).IncreaseTaxEfficiencyCost * (float)mod;
        var10000 = Game.getCiv(iCivID).civBonuses;
        var10000.DevelopInfrastructureCost += this.getIdeology(ideologyID).DevelopInfrastructureCost * (float)mod;
        var10000 = Game.getCiv(iCivID).civBonuses;
        var10000.IncreaseManpowerCost += this.getIdeology(ideologyID).IncreaseManpowerCost * (float)mod;
        var10000 = Game.getCiv(iCivID).civBonuses;
        var10000.GeneralAttack += this.getIdeology(ideologyID).GeneralAttack * mod;
        var10000 = Game.getCiv(iCivID).civBonuses;
        var10000.GeneralDefense += this.getIdeology(ideologyID).GeneralDefense * mod;
        var10000 = Game.getCiv(iCivID).civBonuses;
        var10000.UnitsAttack += this.getIdeology(ideologyID).UnitsAttack * mod;
        var10000 = Game.getCiv(iCivID).civBonuses;
        var10000.UnitsDefense += this.getIdeology(ideologyID).UnitsDefense * mod;
        var10000 = Game.getCiv(iCivID).civBonuses;
        var10000.MaxNumberOfLoans += this.getIdeology(ideologyID).MaxNumberOfLoans * mod;
        var10000 = Game.getCiv(iCivID).civBonuses;
        var10000.BuildingSlot += this.getIdeology(ideologyID).BuildingSlot * mod;
        var10000 = Game.getCiv(iCivID).civBonuses;
        var10000.AdvisorCost += this.getIdeology(ideologyID).AdvisorCost * (float)mod;
        var10000 = Game.getCiv(iCivID).civBonuses;
        var10000.GeneralCost += this.getIdeology(ideologyID).GeneralCost * (float)mod;
        var10000 = Game.getCiv(iCivID).civBonuses;
        var10000.ReligionCost += this.getIdeology(ideologyID).ReligionCost * (float)mod;
        var10000 = Game.getCiv(iCivID).civBonuses;
        var10000.CoreCost += this.getIdeology(ideologyID).CoreCost * (float)mod;
        if (!initMode) {
            Game.getCiv(iCivID).updateProvincesIncomeAndExpenses();
        }

    }

    public final List<MenuElement> getMenuElements(int ideologyID, int iX, int iY, int iW, int iH) {
        List<MenuElement> mElementsToSort = new ArrayList();
        int maxIconW = ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 2;
        if (this.getIdeology(ideologyID).MonthlyIncome != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("MonthlyIncome") + "", (this.getIdeology(ideologyID).MonthlyIncome > 0.0F ? "+" : "") + CFG.getPrecision2(this.getIdeology(ideologyID).MonthlyIncome, 100), Images.gold, iX, 0, iW, iH, maxIconW, this.getIdeology(ideologyID).MonthlyIncome == 0.0F ? Colors.HOVER_NEUTRAL : (this.getIdeology(ideologyID).MonthlyIncome < 0.0F ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }

        if (this.getIdeology(ideologyID).TaxEfficiency != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("TaxEfficiency") + "", (this.getIdeology(ideologyID).TaxEfficiency > 0.0F ? "+" : "") + CFG.getPrecision2(this.getIdeology(ideologyID).TaxEfficiency, 100) + "%", Images.tax, iX, 0, iW, iH, maxIconW, this.getIdeology(ideologyID).TaxEfficiency == 0.0F ? Colors.HOVER_NEUTRAL : (this.getIdeology(ideologyID).TaxEfficiency < 0.0F ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }

        if (this.getIdeology(ideologyID).ProvinceMaintenance != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("ProvinceMaintenance") + "", (this.getIdeology(ideologyID).ProvinceMaintenance > 0.0F ? "+" : "") + CFG.getPrecision2(this.getIdeology(ideologyID).ProvinceMaintenance, 100) + "%", Images.gold, iX, 0, iW, iH, maxIconW, this.getIdeology(ideologyID).ProvinceMaintenance == 0.0F ? Colors.HOVER_NEUTRAL : (this.getIdeology(ideologyID).ProvinceMaintenance > 0.0F ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }

        if (this.getIdeology(ideologyID).ProductionEfficiency != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("ProductionEfficiency") + "", (this.getIdeology(ideologyID).ProductionEfficiency > 0.0F ? "+" : "") + CFG.getPrecision2(this.getIdeology(ideologyID).ProductionEfficiency, 100) + "%", Images.goods, iX, 0, iW, iH, maxIconW, this.getIdeology(ideologyID).ProductionEfficiency == 0.0F ? Colors.HOVER_NEUTRAL : (this.getIdeology(ideologyID).ProductionEfficiency < 0.0F ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }

        if (this.getIdeology(ideologyID).MonthlyLegacy != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("MonthlyLegacy") + "", (this.getIdeology(ideologyID).MonthlyLegacy > 0.0F ? "+" : "") + CFG.getPrecision2(this.getIdeology(ideologyID).MonthlyLegacy, 100), Images.legacy, iX, 0, iW, iH, maxIconW, this.getIdeology(ideologyID).MonthlyLegacy == 0.0F ? Colors.HOVER_NEUTRAL : (this.getIdeology(ideologyID).MonthlyLegacy < 0.0F ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }

        if (this.getIdeology(ideologyID).MaxManpower != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("MaximumManpower") + "", (this.getIdeology(ideologyID).MaxManpower > 0.0F ? "+" : "") + (int)this.getIdeology(ideologyID).MaxManpower, Game_Calendar.IMG_MANPOWER_UP, iX, 0, iW, iH, maxIconW, this.getIdeology(ideologyID).MaxManpower == 0.0F ? Colors.HOVER_NEUTRAL : (this.getIdeology(ideologyID).MaxManpower < 0.0F ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }

        if (this.getIdeology(ideologyID).ArmyMaintenance != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("ArmyMaintenance") + "", (this.getIdeology(ideologyID).ArmyMaintenance > 0.0F ? "+" : "") + CFG.getPrecision2(this.getIdeology(ideologyID).ArmyMaintenance, 100) + "%", Images.armyMaintenance, iX, 0, iW, iH, maxIconW, this.getIdeology(ideologyID).ArmyMaintenance == 0.0F ? Colors.HOVER_NEUTRAL : (this.getIdeology(ideologyID).ArmyMaintenance > 0.0F ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }

        if (this.getIdeology(ideologyID).RecruitmentTime != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("RecruitmentTime") + "", (this.getIdeology(ideologyID).RecruitmentTime > 0.0F ? "+" : "") + CFG.getPrecision2(this.getIdeology(ideologyID).RecruitmentTime, 100) + "%", Game_Calendar.IMG_MANPOWER_TIME, iX, 0, iW, iH, maxIconW, this.getIdeology(ideologyID).RecruitmentTime == 0.0F ? Colors.HOVER_NEUTRAL : (this.getIdeology(ideologyID).RecruitmentTime > 0.0F ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }

        if (this.getIdeology(ideologyID).ConstructionCost != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("ConstructionCost") + "", (this.getIdeology(ideologyID).ConstructionCost > 0.0F ? "+" : "") + "" + CFG.getPrecision2(this.getIdeology(ideologyID).ConstructionCost * 100.0F, 100) + "%", Images.construction, iX, 0, iW, iH, maxIconW, this.getIdeology(ideologyID).ConstructionCost == 0.0F ? Colors.HOVER_NEUTRAL : (this.getIdeology(ideologyID).ConstructionCost > 0.0F ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }

        if (this.getIdeology(ideologyID).AdministrationBuildingsCost != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("AdministrationBuildingsCost") + "", (this.getIdeology(ideologyID).AdministrationBuildingsCost > 0.0F ? "+" : "") + "" + CFG.getPrecision2(this.getIdeology(ideologyID).AdministrationBuildingsCost * 100.0F, 100) + "%", Images.construction, iX, 0, iW, iH, maxIconW, this.getIdeology(ideologyID).AdministrationBuildingsCost == 0.0F ? Colors.HOVER_NEUTRAL : (this.getIdeology(ideologyID).AdministrationBuildingsCost > 0.0F ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }

        if (this.getIdeology(ideologyID).EconomyBuildingsCost != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("EconomyBuildingsCost") + "", (this.getIdeology(ideologyID).EconomyBuildingsCost > 0.0F ? "+" : "") + "" + CFG.getPrecision2(this.getIdeology(ideologyID).EconomyBuildingsCost * 100.0F, 100) + "%", Images.construction, iX, 0, iW, iH, maxIconW, this.getIdeology(ideologyID).EconomyBuildingsCost == 0.0F ? Colors.HOVER_NEUTRAL : (this.getIdeology(ideologyID).EconomyBuildingsCost > 0.0F ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }

        if (this.getIdeology(ideologyID).MilitaryBuildingsCost != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("MilitaryBuildingsCost") + "", (this.getIdeology(ideologyID).MilitaryBuildingsCost > 0.0F ? "+" : "") + "" + CFG.getPrecision2(this.getIdeology(ideologyID).MilitaryBuildingsCost * 100.0F, 100) + "%", Images.construction, iX, 0, iW, iH, maxIconW, this.getIdeology(ideologyID).MilitaryBuildingsCost == 0.0F ? Colors.HOVER_NEUTRAL : (this.getIdeology(ideologyID).MilitaryBuildingsCost > 0.0F ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }

        if (this.getIdeology(ideologyID).ConstructionTime != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("ConstructionTime") + "", (this.getIdeology(ideologyID).ConstructionTime > 0.0F ? "+" : "") + CFG.getPrecision2(this.getIdeology(ideologyID).ConstructionTime * 100.0F, 100) + "%", Images.buildTime, iX, 0, iW, iH, maxIconW, this.getIdeology(ideologyID).ConstructionTime == 0.0F ? Colors.HOVER_NEUTRAL : (this.getIdeology(ideologyID).ConstructionTime > 0.0F ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }

        if (this.getIdeology(ideologyID).BuildingSlot != 0) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("BuildingSlot") + "", (this.getIdeology(ideologyID).BuildingSlot > 0 ? "+" : "") + CFG.getPrecision2((float)this.getIdeology(ideologyID).BuildingSlot, 100), Images.build, iX, 0, iW, iH, maxIconW, this.getIdeology(ideologyID).BuildingSlot == 0 ? Colors.HOVER_NEUTRAL : (this.getIdeology(ideologyID).BuildingSlot < 0 ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }

        if (this.getIdeology(ideologyID).InvestInEconomyCost != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("InvestInEconomyCost") + "", "" + CFG.getPrecision2(this.getIdeology(ideologyID).InvestInEconomyCost * 100.0F, 100) + "%", Game_Calendar.IMG_ECONOMY_UP, iX, 0, iW, iH, maxIconW, this.getIdeology(ideologyID).InvestInEconomyCost == 0.0F ? Colors.HOVER_NEUTRAL : (this.getIdeology(ideologyID).InvestInEconomyCost > 0.0F ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }

        if (this.getIdeology(ideologyID).IncreaseTaxEfficiencyCost != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("IncreaseTaxEfficiencyCost") + "", "" + CFG.getPrecision2(this.getIdeology(ideologyID).IncreaseTaxEfficiencyCost * 100.0F, 100) + "%", Images.taxUp, iX, 0, iW, iH, maxIconW, this.getIdeology(ideologyID).IncreaseTaxEfficiencyCost == 0.0F ? Colors.HOVER_NEUTRAL : (this.getIdeology(ideologyID).IncreaseTaxEfficiencyCost > 0.0F ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }

        if (this.getIdeology(ideologyID).DevelopInfrastructureCost != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("DevelopInfrastructureCost") + "", "" + CFG.getPrecision2(this.getIdeology(ideologyID).DevelopInfrastructureCost * 100.0F, 100) + "%", Images.infrastructureUp, iX, 0, iW, iH, maxIconW, this.getIdeology(ideologyID).DevelopInfrastructureCost == 0.0F ? Colors.HOVER_NEUTRAL : (this.getIdeology(ideologyID).DevelopInfrastructureCost > 0.0F ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }

        if (this.getIdeology(ideologyID).IncreaseManpowerCost != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("IncreaseManpowerCost") + "", "" + CFG.getPrecision2(this.getIdeology(ideologyID).IncreaseManpowerCost, 100) + "%", Game_Calendar.IMG_MANPOWER, iX, 0, iW, iH, maxIconW, this.getIdeology(ideologyID).IncreaseManpowerCost == 0.0F ? Colors.HOVER_NEUTRAL : (this.getIdeology(ideologyID).IncreaseManpowerCost > 0.0F ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }

        if (this.getIdeology(ideologyID).RecruitArmyCost != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("ArmyRecruitmentCost") + "", "" + CFG.getPrecision2(this.getIdeology(ideologyID).RecruitArmyCost, 100) + "%", Images.gold, iX, 0, iW, iH, maxIconW, this.getIdeology(ideologyID).RecruitArmyCost == 0.0F ? Colors.HOVER_NEUTRAL : (this.getIdeology(ideologyID).RecruitArmyCost > 0.0F ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }

        if (this.getIdeology(ideologyID).GeneralAttack != 0) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("GeneralsAttack") + "", (this.getIdeology(ideologyID).GeneralAttack > 0 ? "+" : "") + CFG.getPrecision2((float)this.getIdeology(ideologyID).GeneralAttack, 100), Images.attack, iX, 0, iW, iH, maxIconW, this.getIdeology(ideologyID).GeneralAttack == 0 ? Colors.HOVER_NEUTRAL : (this.getIdeology(ideologyID).GeneralAttack < 0 ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }

        if (this.getIdeology(ideologyID).GeneralDefense != 0) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("GeneralsDefense") + "", (this.getIdeology(ideologyID).GeneralDefense > 0 ? "+" : "") + CFG.getPrecision2((float)this.getIdeology(ideologyID).GeneralDefense, 100), Images.defense, iX, 0, iW, iH, maxIconW, this.getIdeology(ideologyID).GeneralDefense == 0 ? Colors.HOVER_NEUTRAL : (this.getIdeology(ideologyID).GeneralDefense < 0 ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }

        if (this.getIdeology(ideologyID).UnitsAttack != 0) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("UnitsAttack") + "", (this.getIdeology(ideologyID).UnitsAttack > 0 ? "+" : "") + CFG.getPrecision2((float)this.getIdeology(ideologyID).UnitsAttack, 100), Images.attack, iX, 0, iW, iH, maxIconW, this.getIdeology(ideologyID).UnitsAttack == 0 ? Colors.HOVER_NEUTRAL : (this.getIdeology(ideologyID).UnitsAttack < 0 ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }

        if (this.getIdeology(ideologyID).UnitsDefense != 0) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("UnitsDefense") + "", (this.getIdeology(ideologyID).UnitsDefense > 0 ? "+" : "") + CFG.getPrecision2((float)this.getIdeology(ideologyID).UnitsDefense, 100), Images.defense, iX, 0, iW, iH, maxIconW, this.getIdeology(ideologyID).UnitsDefense == 0 ? Colors.HOVER_NEUTRAL : (this.getIdeology(ideologyID).UnitsDefense < 0 ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }

        if (this.getIdeology(ideologyID).AdvisorCost != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("AdvisorCost") + "", (this.getIdeology(ideologyID).AdvisorCost > 0.0F ? "+" : "") + CFG.getPrecision2(this.getIdeology(ideologyID).AdvisorCost * 100.0F, 100) + "%", Images.council, iX, 0, iW, iH, maxIconW, this.getIdeology(ideologyID).AdvisorCost == 0.0F ? Colors.HOVER_NEUTRAL : (this.getIdeology(ideologyID).AdvisorCost > 0.0F ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }

        if (this.getIdeology(ideologyID).GeneralCost != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("GeneralCost") + "", (this.getIdeology(ideologyID).GeneralCost > 0.0F ? "+" : "") + CFG.getPrecision2(this.getIdeology(ideologyID).GeneralCost * 100.0F, 100) + "%", Images.general, iX, 0, iW, iH, maxIconW, this.getIdeology(ideologyID).GeneralCost == 0.0F ? Colors.HOVER_NEUTRAL : (this.getIdeology(ideologyID).GeneralCost > 0.0F ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }

        if (this.getIdeology(ideologyID).MaxNumberOfLoans != 0) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("MaximumNumberOfLoans") + "", (this.getIdeology(ideologyID).MaxNumberOfLoans > 0 ? "+" : "") + this.getIdeology(ideologyID).MaxNumberOfLoans, Images.loan, iX, 0, iW, iH, maxIconW, this.getIdeology(ideologyID).MaxNumberOfLoans == 0 ? Colors.HOVER_NEUTRAL : (this.getIdeology(ideologyID).MaxNumberOfLoans < 0 ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }

        if (this.getIdeology(ideologyID).CoreCost != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("CoreConstruction") + "", (this.getIdeology(ideologyID).CoreCost > 0.0F ? "+" : "") + CFG.getPrecision2(this.getIdeology(ideologyID).CoreCost, 100) + "%", Images.core, iX, 0, iW, iH, maxIconW, this.getIdeology(ideologyID).CoreCost == 0.0F ? Colors.HOVER_NEUTRAL : (this.getIdeology(ideologyID).CoreCost > 0.0F ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }

        if (this.getIdeology(ideologyID).ReligionCost != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("ReligionConversionCost") + "", (this.getIdeology(ideologyID).ReligionCost > 0.0F ? "+" : "") + CFG.getPrecision2(this.getIdeology(ideologyID).ReligionCost, 100) + "%", Images.religion, iX, 0, iW, iH, maxIconW, this.getIdeology(ideologyID).ReligionCost == 0.0F ? Colors.HOVER_NEUTRAL : (this.getIdeology(ideologyID).ReligionCost > 0.0F ? Colors.HOVER_NEGATIVE : Colors.HOVER_POSITIVE)));
        }

        List<MenuElement> elementsOut = new ArrayList();

        while(mElementsToSort.size() > 0) {
            int addID = 0;
            int o = 1;

            for(int oSize = mElementsToSort.size(); o < oSize; ++o) {
                if (CFG.compareAlphabetic_TwoString(((MenuElement)mElementsToSort.get(addID)).getText(), ((MenuElement)mElementsToSort.get(o)).getText())) {
                    addID = o;
                }
            }

            elementsOut.add((MenuElement)mElementsToSort.get(addID));
            ((MenuElement)elementsOut.get(elementsOut.size() - 1)).setPosY(iY);
            iY += ((MenuElement)elementsOut.get(elementsOut.size() - 1)).getHeight() + CFG.PADDING;
            mElementsToSort.remove(addID);
        }

        return elementsOut;
    }

    public MenuElement_Hover getHoverIdeology(int ideologyID, boolean showChangeIdeology, boolean inChangeIdeology) {
        List<MenuElement_HoverElement> nElements = new ArrayList();
        List<MenuElement_HoverElement_Type> nData = new ArrayList();
        if (inChangeIdeology) {
            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("ChangeTypeOfGovernmentTo") + ": ", CFG.FONT_BOLD));
            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Clear(((Ideology)this.lIdeologies.get(ideologyID)).Name, CFG.FONT_BOLD, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            if (((Ideology)this.lIdeologies.get(ideologyID)).REQUIRED_TECHNOLOGY >= 0) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("RequiredTechnology") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text(((TechnologyTree.Technology)TechnologyTree.lTechnology.get(((Ideology)this.lIdeologies.get(ideologyID)).REQUIRED_TECHNOLOGY)).Name, CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_TECHNOLOGY, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }

            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Cost") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text(CFG.getPrecision2(GameValues.government.CHANGE_GOVERNMENT_COST, 10), CFG.FONT_BOLD_SMALL, Game.getCiv(Game.player.iCivID).fGold > GameValues.government.CHANGE_GOVERNMENT_COST ? Colors.HOVER_RIGHT : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("LegacyPoints") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text(CFG.getPrecision2(GameValues.government.CHANGE_GOVERNMENT_COST_LEGACY, 10), CFG.FONT_BOLD_SMALL, Game.getCiv(Game.player.iCivID).fLegacy > GameValues.government.CHANGE_GOVERNMENT_COST_LEGACY ? Colors.HOVER_RIGHT : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.legacy, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Line());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        } else {
            nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Government") + ": ", CFG.FONT_BOLD));
            nData.add(new MenuElement_HoverElement_Type_TextTitle(((Ideology)this.lIdeologies.get(ideologyID)).Name, CFG.FONT_BOLD, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Line());
            nElements.add(new MenuElement_HoverElement(nData));
            if(this.lIdeologies.get(ideologyID).Desc != null){
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text_Desc(this.lIdeologies.get(ideologyID).Desc,CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT2));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
            }
            nData.clear();
        }

        if (this.getIdeology(ideologyID).MonthlyIncome != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MonthlyIncome") + ": ", (this.getIdeology(ideologyID).MonthlyIncome > 0.0F ? "+" : "") + CFG.getPrecision2(this.getIdeology(ideologyID).MonthlyIncome, 100), Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, this.getIdeology(ideologyID).MonthlyIncome > 0.0F ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (this.getIdeology(ideologyID).MonthlyLegacy != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MonthlyLegacy") + ": ", (this.getIdeology(ideologyID).MonthlyLegacy > 0.0F ? "+" : "") + CFG.getPrecision2(this.getIdeology(ideologyID).MonthlyLegacy, 100), Images.legacy, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, this.getIdeology(ideologyID).MonthlyLegacy > 0.0F ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (this.getIdeology(ideologyID).TaxEfficiency != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("TaxEfficiency") + ": ", (this.getIdeology(ideologyID).TaxEfficiency > 0.0F ? "+" : "") + CFG.getPrecision2(this.getIdeology(ideologyID).TaxEfficiency, 100) + "%", Images.tax, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, this.getIdeology(ideologyID).TaxEfficiency > 0.0F ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (this.getIdeology(ideologyID).ProductionEfficiency != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ProductionEfficiency") + ": ", (this.getIdeology(ideologyID).ProductionEfficiency > 0.0F ? "+" : "") + CFG.getPrecision2(this.getIdeology(ideologyID).ProductionEfficiency, 100) + "%", Images.goods, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, this.getIdeology(ideologyID).ProductionEfficiency > 0.0F ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (this.getIdeology(ideologyID).ProvinceMaintenance != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ProvinceMaintenance") + ": ", (this.getIdeology(ideologyID).ProvinceMaintenance > 0.0F ? "+" : "") + CFG.getPrecision2(this.getIdeology(ideologyID).ProvinceMaintenance, 100) + "%", Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, this.getIdeology(ideologyID).ProvinceMaintenance < 0.0F ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (this.getIdeology(ideologyID).InvestInEconomyCost != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("InvestInEconomyCost") + ": ", (this.getIdeology(ideologyID).InvestInEconomyCost > 0.0F ? "+" : "") + CFG.getPrecision2(this.getIdeology(ideologyID).InvestInEconomyCost * 100.0F, 100) + "%", Game_Calendar.IMG_ECONOMY_UP, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, this.getIdeology(ideologyID).InvestInEconomyCost < 0.0F ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (this.getIdeology(ideologyID).IncreaseTaxEfficiencyCost != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("IncreaseTaxEfficiencyCost") + ": ", (this.getIdeology(ideologyID).IncreaseTaxEfficiencyCost > 0.0F ? "+" : "") + CFG.getPrecision2(this.getIdeology(ideologyID).IncreaseTaxEfficiencyCost * 100.0F, 100) + "%", Images.taxUp, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, this.getIdeology(ideologyID).IncreaseTaxEfficiencyCost < 0.0F ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (this.getIdeology(ideologyID).DevelopInfrastructureCost != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("DevelopInfrastructureCost") + ": ", (this.getIdeology(ideologyID).DevelopInfrastructureCost > 0.0F ? "+" : "") + CFG.getPrecision2(this.getIdeology(ideologyID).DevelopInfrastructureCost * 100.0F, 100) + "%", Images.infrastructureUp, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, this.getIdeology(ideologyID).DevelopInfrastructureCost < 0.0F ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (this.getIdeology(ideologyID).IncreaseManpowerCost != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("IncreaseManpowerCost") + ": ", (this.getIdeology(ideologyID).IncreaseManpowerCost > 0.0F ? "+" : "") + CFG.getPrecision2(this.getIdeology(ideologyID).IncreaseManpowerCost, 100) + "%", Game_Calendar.IMG_MANPOWER_UP, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, this.getIdeology(ideologyID).IncreaseManpowerCost < 0.0F ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (this.getIdeology(ideologyID).ConstructionCost != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ConstructionCost") + ": ", (this.getIdeology(ideologyID).ConstructionCost > 0.0F ? "+" : "") + CFG.getPrecision2(this.getIdeology(ideologyID).ConstructionCost * 100.0F, 100) + "%", Images.construction, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, this.getIdeology(ideologyID).ConstructionCost * 100.0F < 0.0F ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (this.getIdeology(ideologyID).AdministrationBuildingsCost != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("AdministrationBuildingsCost") + ": ", (this.getIdeology(ideologyID).AdministrationBuildingsCost * 100.0F > 0.0F ? "+" : "") + CFG.getPrecision2(this.getIdeology(ideologyID).AdministrationBuildingsCost * 100.0F, 100) + "%", Images.construction, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, this.getIdeology(ideologyID).AdministrationBuildingsCost * 100.0F < 0.0F ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (this.getIdeology(ideologyID).EconomyBuildingsCost != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("EconomyBuildingsCost") + ": ", (this.getIdeology(ideologyID).EconomyBuildingsCost * 100.0F > 0.0F ? "+" : "") + CFG.getPrecision2(this.getIdeology(ideologyID).EconomyBuildingsCost * 100.0F, 100) + "%", Images.construction, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, this.getIdeology(ideologyID).EconomyBuildingsCost * 100.0F < 0.0F ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (this.getIdeology(ideologyID).MilitaryBuildingsCost != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MilitaryBuildingsCost") + ": ", (this.getIdeology(ideologyID).MilitaryBuildingsCost * 100.0F > 0.0F ? "+" : "") + CFG.getPrecision2(this.getIdeology(ideologyID).MilitaryBuildingsCost * 100.0F, 100) + "%", Images.construction, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, this.getIdeology(ideologyID).MilitaryBuildingsCost * 100.0F < 0.0F ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (this.getIdeology(ideologyID).ConstructionTime != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ConstructionTime") + ": ", (this.getIdeology(ideologyID).ConstructionTime > 0.0F ? "+" : "") + CFG.getPrecision2(this.getIdeology(ideologyID).ConstructionTime * 100.0F, 100) + "%", Images.buildTime, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, this.getIdeology(ideologyID).ConstructionTime < 0.0F ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (this.getIdeology(ideologyID).BuildingSlot != 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("BuildingSlot") + ": ", (this.getIdeology(ideologyID).BuildingSlot > 0 ? "+" : "") + CFG.getPrecision2((float)this.getIdeology(ideologyID).BuildingSlot, 100), Images.build, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, this.getIdeology(ideologyID).BuildingSlot > 0 ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (this.getIdeology(ideologyID).MaxManpower != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaximumManpower") + ": ", (this.getIdeology(ideologyID).MaxManpower > 0.0F ? "+" : "") + (int)this.getIdeology(ideologyID).MaxManpower, Game_Calendar.IMG_MANPOWER_UP, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, this.getIdeology(ideologyID).MaxManpower > 0.0F ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (this.getIdeology(ideologyID).ArmyMaintenance != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ArmyMaintenance") + ": ", (this.getIdeology(ideologyID).ArmyMaintenance > 0.0F ? "+" : "") + CFG.getPrecision2(this.getIdeology(ideologyID).ArmyMaintenance, 100) + "%", Images.armyMaintenance, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, this.getIdeology(ideologyID).ArmyMaintenance < 0.0F ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (this.getIdeology(ideologyID).RecruitmentTime != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("RecruitmentTime") + ": ", (this.getIdeology(ideologyID).RecruitmentTime > 0.0F ? "+" : "") + CFG.getPrecision2(this.getIdeology(ideologyID).RecruitmentTime, 100) + "%", Game_Calendar.IMG_MANPOWER_TIME, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, this.getIdeology(ideologyID).RecruitmentTime < 0.0F ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (this.getIdeology(ideologyID).RecruitArmyCost != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ArmyRecruitmentCost") + ": ", (this.getIdeology(ideologyID).RecruitArmyCost > 0.0F ? "+" : "") + CFG.getPrecision2(this.getIdeology(ideologyID).RecruitArmyCost, 100) + "%", Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, this.getIdeology(ideologyID).RecruitArmyCost < 0.0F ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (this.getIdeology(ideologyID).GeneralAttack != 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("GeneralsAttack") + ": ", (this.getIdeology(ideologyID).GeneralAttack > 0 ? "+" : "") + CFG.getPrecision2((float)this.getIdeology(ideologyID).GeneralAttack, 1), Images.attack, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, this.getIdeology(ideologyID).GeneralAttack > 0 ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (this.getIdeology(ideologyID).GeneralDefense != 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("GeneralsDefense") + ": ", (this.getIdeology(ideologyID).GeneralDefense > 0 ? "+" : "") + CFG.getPrecision2((float)this.getIdeology(ideologyID).GeneralDefense, 1), Images.defense, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, this.getIdeology(ideologyID).GeneralDefense > 0 ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (this.getIdeology(ideologyID).UnitsAttack != 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("UnitsAttack") + ": ", (this.getIdeology(ideologyID).UnitsAttack > 0 ? "+" : "") + CFG.getPrecision2((float)this.getIdeology(ideologyID).UnitsAttack, 1), Images.attack, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, this.getIdeology(ideologyID).UnitsAttack > 0 ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (this.getIdeology(ideologyID).UnitsDefense != 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("UnitsDefense") + ": ", (this.getIdeology(ideologyID).UnitsDefense > 0 ? "+" : "") + CFG.getPrecision2((float)this.getIdeology(ideologyID).UnitsDefense, 1), Images.defense, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, this.getIdeology(ideologyID).UnitsDefense > 0 ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (this.getIdeology(ideologyID).AdvisorCost != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("AdvisorCost") + ": ", (this.getIdeology(ideologyID).AdvisorCost > 0.0F ? "+" : "") + CFG.getPrecision2(this.getIdeology(ideologyID).AdvisorCost * 100.0F, 100) + "%", Images.council, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, this.getIdeology(ideologyID).AdvisorCost < 0.0F ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (this.getIdeology(ideologyID).GeneralCost != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("GeneralCost") + ": ", (this.getIdeology(ideologyID).GeneralCost > 0.0F ? "+" : "") + CFG.getPrecision2(this.getIdeology(ideologyID).GeneralCost * 100.0F, 100) + "%", Images.general, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, this.getIdeology(ideologyID).GeneralCost < 0.0F ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (this.getIdeology(ideologyID).MaxNumberOfLoans != 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaximumNumberOfLoans") + ": ", (this.getIdeology(ideologyID).MaxNumberOfLoans > 0 ? "+" : "") + CFG.getPrecision2((float)this.getIdeology(ideologyID).MaxNumberOfLoans, 1), Images.loan, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, this.getIdeology(ideologyID).MaxNumberOfLoans > 0 ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (this.getIdeology(ideologyID).CoreCost != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("CoreConstruction") + ": ", (this.getIdeology(ideologyID).CoreCost > 0.0F ? "+" : "") + CFG.getPrecision2(this.getIdeology(ideologyID).CoreCost, 100) + "%", Images.core, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, this.getIdeology(ideologyID).CoreCost < 0.0F ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (this.getIdeology(ideologyID).ReligionCost != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ReligionConversionCost") + ": ", (this.getIdeology(ideologyID).ReligionCost > 0.0F ? "+" : "") + CFG.getPrecision2(this.getIdeology(ideologyID).ReligionCost, 100) + "%", Images.religion, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, this.getIdeology(ideologyID).ReligionCost < 0.0F ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (showChangeIdeology) {
            nData.add(new MenuElement_HoverElement_Type_Empty());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("ChangeTypeOfGovernment"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
            nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.government, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Cost") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text(CFG.getPrecision2(GameValues.government.CHANGE_GOVERNMENT_COST, 10), CFG.FONT_BOLD_SMALL, Game.getCiv(Game.player.iCivID).fGold > GameValues.government.CHANGE_GOVERNMENT_COST ? Colors.HOVER_RIGHT : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("LegacyPoints") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text(CFG.getPrecision2(GameValues.government.CHANGE_GOVERNMENT_COST_LEGACY, 10), CFG.FONT_BOLD_SMALL, Game.getCiv(Game.player.iCivID).fLegacy > GameValues.government.CHANGE_GOVERNMENT_COST_LEGACY ? Colors.HOVER_RIGHT : Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.legacy, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        return new MenuElement_Hover(nElements);
    }

    public final boolean changeGovernmentType(int iCivID, int toIdeologyID, boolean free) {
        if (Game.getCiv(iCivID).getIdeologyID() == toIdeologyID) {
            return false;
        } else if (!free && Game.getCiv(iCivID).fGold < GameValues.government.CHANGE_GOVERNMENT_COST) {
            return false;
        } else if (!free && Game.getCiv(iCivID).fLegacy < GameValues.government.CHANGE_GOVERNMENT_COST_LEGACY) {
            return false;
        } else if (!free && Game.ideologiesManager.getIdeology(toIdeologyID).REQUIRED_TECHNOLOGY >= 0 && !Game.getCiv(iCivID).getTechResearched(Game.ideologiesManager.getIdeology(toIdeologyID).REQUIRED_TECHNOLOGY)) {
            return false;
        } else {
            Game.addSimpleTask(new Game.SimpleTask("changeGovernment" + Game.getCiv(iCivID).getCivName(), iCivID, toIdeologyID) {
                public void update() {
                    IdeologiesManager.this.updateCivBonuses(this.id, Game.getCiv(this.id).getIdeologyID(), -1, true);
                    Game.updateCivilizationIdeology_InGame(this.id, Game.ideologiesManager.getRealTag(Game.getCiv(this.id).getCivTag()) + Game.ideologiesManager.getIdeology(this.id2).Extra_Tag);
                    IdeologiesManager.this.updateCivBonuses(this.id, Game.getCiv(this.id).getIdeologyID(), 1, false);
                    RulersManager.loadRuler(this.id, Game.getCiv(this.id).getCivTag(), false);
                    Civilization var10000 = Game.getCiv(this.id);
                    var10000.fGold -= GameValues.government.CHANGE_GOVERNMENT_COST;
                    var10000 = Game.getCiv(this.id);
                    var10000.fLegacy -= GameValues.government.CHANGE_GOVERNMENT_COST_LEGACY;
                    Game.getCiv(this.id).updateTotalIncomePerMonth();
                }
            });
            return true;
        }
    }

    public static class ConfigIdeologiesData {
        protected String Age_of_History;
        protected ArrayList Government;

        public ConfigIdeologiesData() {
        }
    }

    public static class Ideology {
        public String Name;
        public String Extra_Tag;
        public String Desc = null;
        public int GOV_GROUP_ID;
        public String RulerTitle = "";
        public boolean RulerRoman;
        public boolean KingsImages = true;
        public float[] Color;
        public int REQUIRED_TECHNOLOGY;
        public boolean REVOLUTIONISTS = false;
        public boolean MUST_BE_CHANGED = false;
        public boolean TRIBAL = false;
        public boolean CITY_STATE = false;
        public float STARTING_ARMY;
        public int AI_PEACE_ORDER_CHANCE = 50;
        public int AI_PEACE_ORDER_CHANCE2 = 50;
        public int[] AI_PEACE_ORDER = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8};
        public int[] AI_PEACE_ORDER2 = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8};
        public int[] AI_BUILD_SCORE = new int[]{70, 50, 40, 60, 40, 65, 60, 30, 40, 55, 50, 45, 30, 50, 40};
        public int AI_BUILD_SCORE_TOTAL = 0;
        public int AI_BUILD_INVEST_IN_ECONOMY = 25;
        public int AI_BUILD_INCREASE_TAX_EFFICIENCY = 40;
        public int AI_BUILD_INCREASE_MANPOWER = 50;
        public int AI_BUILD_INCREASE_GROWTH_RATE = 60;
        public int AI_BUILD_DEVELOP_INFRASTRUCTURE = 67;
        public int AI_BUILD_BUILDING = 100;
        public int AI_EXTRA_AGGRESSIVENESS = 0;
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

        public Ideology() {
        }
    }
}
