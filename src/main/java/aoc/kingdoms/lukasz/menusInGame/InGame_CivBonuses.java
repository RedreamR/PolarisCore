//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.menusInGame;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.jakowski.SaveLoad.LoadSavedGameManager;
import aoc.kingdoms.lukasz.map.civilization.CivilizationRanking;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.menu.Menu;
import aoc.kingdoms.lukasz.menu.menuTitle.MenuTitleIMG_FlagCenter;
import aoc.kingdoms.lukasz.menu_element.Empty;
import aoc.kingdoms.lukasz.menu_element.MenuElement;
import aoc.kingdoms.lukasz.menu_element.Status;
import aoc.kingdoms.lukasz.menu_element.button.*;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_Hover;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoc.kingdoms.lukasz.menu_element.textStatic.Text_Static;
import aoc.kingdoms.lukasz.menu_element.textStatic.Text_Title_v2;
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.textures.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import team.rainfall.finality.FinalityLogger;
import team.rainfall.fontFix.NationalSpirit;
import team.rainfall.fontFix.NationalSpiritManager;

import java.util.ArrayList;
import java.util.List;

public class InGame_CivBonuses extends Menu {
    public static boolean nationSpirit = false;
    public static int iCivID;
    protected static final int ANIMATION_TIME = 150;
    public static long lTime = 0L;

    public InGame_CivBonuses() {
        List<MenuElement> menuElements = new ArrayList();
        int paddingLeft = Images.boxTitleBORDERWIDTH + CFG.PADDING;
        int titleHeight = ImageManager.getImage(Images.title2).getHeight();
        int menuWidth = ImageManager.getImage(Images.insideTop).getWidth();
        int menuY = ImageManager.getImage(Images.topStats).getHeight() + Renderer.boxBGExtraY + CFG.PADDING + ImageManager.getImage(Images.title1Red).getHeight();
        int buttonYPadding = CFG.PADDING;
        int maxIconW = ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 2;
        int buttonH = CFG.TEXT_HEIGHT + CFG.PADDING * 6;
        FinalityLogger.debug("NS!!!222");
        if (nationSpirit) {
            int buttonW = (menuWidth - paddingLeft * 2 - CFG.PADDING * 3) / 5;
            int buttonH2 = (int)((float)buttonW * 1.1F);
            int buttonY = buttonYPadding + CFG.PADDING;
            int a = 0;
            LoadSavedGameManager.loadSave_CivsEventsVariables();
            for (String string : Game.getCiv(iCivID).eventsDataVariables.v) {
                if(string.startsWith("$$NationalSpirit_")){
                    String s2 = string.replace("$$NationalSpirit_","");
                    String[] s3 = s2.split("-");
                    NationalSpirit nationalSpirit = NationalSpiritManager.INSTANCE.getNS(s3[0]);
                    float fl = a / 5f;
                    menuElements.add(new ButtonNS_Info(nationalSpirit, (int) (paddingLeft + menuWidth * fl),buttonY,buttonW,buttonH2,true));
                    a++;
                    if(a > 4){
                        a = 0;
                        buttonY = buttonY + menuElements.get(menuElements.size() - 1).getHeight();
                    }
                }
            }
            if(menuElements.isEmpty()){
                menuElements.add(new Text_Static(Game.lang.get("None") + ".", CFG.FONT_REGULAR_SMALL, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT) {
                    protected Color getColor(boolean isActive) {
                        if (isActive) {
                            return Colors.BUTTON_TEXT_ACTIVE;
                        } else if (this.getIsHovered()) {
                            return Colors.BUTTON_TEXT_HOVERED;
                        } else {
                            return this.getClickable() ? Colors.BUTTON_TEXT : Colors.BUTTON_TEXT_DISABLED;
                        }
                    }
                });
            }
            int i = 0;

            for (int iSize = menuElements.size(); i < iSize; ++i) {
                if (buttonY < menuElements.get(i).getPosY() + menuElements.get(i).getHeight() + CFG.PADDING) {
                    buttonY = menuElements.get(i).getPosY() + menuElements.get(i).getHeight() + CFG.PADDING;
                }
            }

            i = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - (CFG.GAME_HEIGHT - Game.menuManager.getInGame_MapModesPosY() + CFG.PADDING * 3));
            menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, i)));
            this.initMenu(new MenuTitleIMG_FlagCenter("", Game.getCiv(iCivID).getCivName(), false, false, Images.title1Red) {
                public long getTime() {
                    return InGame_CivBonuses.lTime;
                }

                public int getFlagCivID() {
                    return InGame_CivBonuses.iCivID;
                }
            }, CFG.GAME_WIDTH - menuWidth - CFG.PADDING * 2, menuY, menuWidth, i, menuElements, false, true);
            return;
        }
        menuElements.add(new ButtonStatsRectIMG_Active_Click(Game.lang.get("CivilizationAdvantages") + ": " + Game.getCiv(iCivID).iAdvantagesSize, Images.advantages, paddingLeft, buttonYPadding, menuWidth - paddingLeft * 2, buttonH, maxIconW, 0) {
            public void actionElement() {
                Game.menuManager.setVisibleInGame_CivBonuses(false);
                Game.menuManager.rebuildInGame_Civ_UnlockedAdvantages();
            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.getCiv(InGame_CivBonuses.iCivID).getCivName() + ": " + Game.lang.get("CivilizationAdvantages"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.advantages, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
        });
        int buttonY = buttonYPadding + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        List<MenuElement> mElementsToSort = new ArrayList();
        if (Game.getCiv(iCivID).canAccessSea) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("ArmyCanGoToSea"), "", Images.ship, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).canColonize) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("ColonizationAllowed"), "", Images.population, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).canBuildNuke) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("NuclearWeapons"), "", Images.nuke, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.MonthlyIncome != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("MonthlyIncome"), (Game.getCiv(iCivID).civBonuses.MonthlyIncome > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).civBonuses.MonthlyIncome, 100), Images.gold, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.TaxEfficiency != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("TaxEfficiency"), (Game.getCiv(iCivID).civBonuses.TaxEfficiency > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).civBonuses.TaxEfficiency, 100) + "%", Images.tax, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW) {
                public Color getColorBonus() {
                    return Game.getCiv(InGame_CivBonuses.iCivID).civBonuses.TaxEfficiency > 0.0F ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE;
                }
            });
        }

        if (Game.getCiv(iCivID).civBonuses.IncomeTaxation != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("IncomeTaxation"), (Game.getCiv(iCivID).civBonuses.IncomeTaxation > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).civBonuses.IncomeTaxation * 100.0F, 100) + "%", Images.tax, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.IncomeEconomy != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("MonthlyIncomeEconomy"), (Game.getCiv(iCivID).civBonuses.IncomeEconomy > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).civBonuses.IncomeEconomy * 100.0F, 100) + "%", Game_Calendar.IMG_ECONOMY, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.IncomeProduction != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("IncomeProduction"), (Game.getCiv(iCivID).civBonuses.IncomeProduction > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).civBonuses.IncomeProduction, 100) + "%", Images.goods, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.ProductionEfficiency != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("ProductionEfficiency"), (Game.getCiv(iCivID).civBonuses.ProductionEfficiency > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).civBonuses.ProductionEfficiency, 100) + "%", Images.goods, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.ProvinceMaintenance != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("ProvinceMaintenance"), CFG.getPrecision2(Game.getCiv(iCivID).civBonuses.ProvinceMaintenance, 100) + "%", Images.provinces, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.BuildingsMaintenanceCost != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("BuildingsMaintenanceCost"), CFG.getPrecision2(Game.getCiv(iCivID).civBonuses.BuildingsMaintenanceCost * 100.0F, 100) + "%", Images.buildings, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.MaintenanceCost != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("MaintenanceCost"), CFG.getPrecision2(Game.getCiv(iCivID).civBonuses.MaintenanceCost, 100), Images.gold, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).getInflation_Just() > 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("Inflation"), CFG.getPrecision2(Game.getCiv(iCivID).getInflation_Just() * 100.0F, 100) + "%", Images.inflation, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.GrowthRate != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("GrowthRate"), (Game.getCiv(iCivID).civBonuses.GrowthRate > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).civBonuses.GrowthRate, 100) + "%", Images.populationGrowth, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.MonthlyLegacy != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("MonthlyLegacy"), (Game.getCiv(iCivID).civBonuses.MonthlyLegacy > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).civBonuses.MonthlyLegacy, 100), Images.legacy, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.MonthlyLegacy_Percentage != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("MonthlyLegacy"), (Game.getCiv(iCivID).civBonuses.MonthlyLegacy_Percentage > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).civBonuses.MonthlyLegacy_Percentage * 100.0F, 100) + "%", Images.legacy, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.MaxManpower != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("MaximumManpower"), (Game.getCiv(iCivID).civBonuses.MaxManpower > 0.0F ? "+" : "") + (int) Game.getCiv(iCivID).civBonuses.MaxManpower, Game_Calendar.IMG_MANPOWER_UP, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.MaxManpower_Percentage != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("MaximumManpower"), (Game.getCiv(iCivID).civBonuses.MaxManpower_Percentage > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).civBonuses.MaxManpower_Percentage * 100.0F, 100) + "%", Game_Calendar.IMG_MANPOWER_UP, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.ManpowerRecoverySpeed != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("ManpowerRecoverySpeed"), (Game.getCiv(iCivID).civBonuses.ManpowerRecoverySpeed > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).civBonuses.ManpowerRecoverySpeed * 100.0F, 100) + "%", Game_Calendar.IMG_MANPOWER_TIME, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.ReinforcementSpeed != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("ReinforcementSpeed"), (Game.getCiv(iCivID).civBonuses.ReinforcementSpeed > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).civBonuses.ReinforcementSpeed * 100.0F, 100) + "%", Game_Calendar.IMG_MANPOWER_TIME, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.ArmyMoraleRecovery != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("ArmyMoraleRecovery"), (Game.getCiv(iCivID).civBonuses.ArmyMoraleRecovery > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).civBonuses.ArmyMoraleRecovery * 100.0F, 100) + "%", Images.morale, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.WarScoreCost != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("WarScoreCost"), (Game.getCiv(iCivID).civBonuses.WarScoreCost > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).civBonuses.WarScoreCost * 100.0F, 100) + "%", Images.victoryPoints, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.ArmyMaintenance != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("ArmyMaintenance"), (Game.getCiv(iCivID).civBonuses.ArmyMaintenance > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).civBonuses.ArmyMaintenance, 100) + "%", Images.armyMaintenance, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.RecruitmentTime != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("RecruitmentTime"), (Game.getCiv(iCivID).civBonuses.RecruitmentTime > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).civBonuses.RecruitmentTime, 100) + "%", Game_Calendar.IMG_MANPOWER_TIME, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.RecruitArmyCost != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("ArmyRecruitmentCost"), (Game.getCiv(iCivID).civBonuses.RecruitArmyCost > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).civBonuses.RecruitArmyCost, 100) + "%", Images.gold, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.RecruitArmyFirstLineCost != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("FirstLineArmyRecruitmentCost"), (Game.getCiv(iCivID).civBonuses.RecruitArmyFirstLineCost > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).civBonuses.RecruitArmyFirstLineCost, 100) + "%", Images.gold, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.RecruitArmySecondLineCost != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("SecondLineArmyRecruitmentCost"), (Game.getCiv(iCivID).civBonuses.RecruitArmySecondLineCost > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).civBonuses.RecruitArmySecondLineCost, 100) + "%", Images.gold, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.Research != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("Research"), (Game.getCiv(iCivID).civBonuses.Research > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).civBonuses.Research, 100) + "%", Game_Calendar.IMG_TECHNOLOGY, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.ResearchPoints != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("ResearchPerMonth"), (Game.getCiv(iCivID).civBonuses.ResearchPoints > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).civBonuses.ResearchPoints, 100), Game_Calendar.IMG_TECHNOLOGY, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.Devastation != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("Devastation"), (Game.getCiv(iCivID).civBonuses.Devastation > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).civBonuses.Devastation * 100.0F, 100) + "%", Images.devastation, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.TechnologyCost != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("TechnologyCost"), (Game.getCiv(iCivID).civBonuses.TechnologyCost > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).civBonuses.TechnologyCost, 100) + "%", Game_Calendar.IMG_TECHNOLOGY, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.ConstructionCost != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("ConstructionCost"), (Game.getCiv(iCivID).civBonuses.ConstructionCost > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).civBonuses.ConstructionCost * 100.0F, 100) + "%", Images.construction, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.AdministrationBuildingsCost != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("AdministrationBuildingsCost"), (Game.getCiv(iCivID).civBonuses.AdministrationBuildingsCost > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).civBonuses.AdministrationBuildingsCost * 100.0F, 100) + "%", Images.construction, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.MilitaryBuildingsCost != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("MilitaryBuildingsCost"), (Game.getCiv(iCivID).civBonuses.MilitaryBuildingsCost > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).civBonuses.MilitaryBuildingsCost * 100.0F, 100) + "%", Images.construction, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.EconomyBuildingsCost != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("EconomyBuildingsCost"), (Game.getCiv(iCivID).civBonuses.EconomyBuildingsCost > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).civBonuses.EconomyBuildingsCost * 100.0F, 100) + "%", Images.construction, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.ConstructionTime != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("ConstructionTime"), (Game.getCiv(iCivID).civBonuses.ConstructionTime > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).civBonuses.ConstructionTime * 100.0F, 100) + "%", Images.buildTime, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.WonderConstructionCost != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("WonderConstructionCost"), (Game.getCiv(iCivID).civBonuses.WonderConstructionCost > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).civBonuses.WonderConstructionCost * 100.0F, 100) + "%", Images.mapModesWonders, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.BuildingSlot != 0) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("BuildingSlot"), (Game.getCiv(iCivID).civBonuses.BuildingSlot > 0 ? "+" : "") + CFG.getPrecision2((float) Game.getCiv(iCivID).civBonuses.BuildingSlot, 100), Images.build, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.MaxInfrastructure != 0) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("MaximumInfrastructureLevel"), (Game.getCiv(iCivID).civBonuses.MaxInfrastructure > 0 ? "+" : "") + CFG.getPrecision2((float) Game.getCiv(iCivID).civBonuses.MaxInfrastructure, 100), Images.infrastructure, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.InvestInEconomyCost != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("InvestInEconomyCost"), (Game.getCiv(iCivID).civBonuses.InvestInEconomyCost > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).civBonuses.InvestInEconomyCost * 100.0F, 100) + "%", Game_Calendar.IMG_ECONOMY_UP, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.IncreaseTaxEfficiencyCost != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("IncreaseTaxEfficiencyCost"), (Game.getCiv(iCivID).civBonuses.IncreaseTaxEfficiencyCost > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).civBonuses.IncreaseTaxEfficiencyCost * 100.0F, 100) + "%", Images.taxUp, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.IncreaseGrowthRateCost != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("IncreaseGrowthRateCost"), (Game.getCiv(iCivID).civBonuses.IncreaseGrowthRateCost > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).civBonuses.IncreaseGrowthRateCost * 100.0F, 100) + "%", Images.populationUp, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.DevelopInfrastructureCost != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("DevelopInfrastructureCost"), (Game.getCiv(iCivID).civBonuses.DevelopInfrastructureCost > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).civBonuses.DevelopInfrastructureCost * 100.0F, 100) + "%", Images.infrastructureUp, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.IncreaseManpowerCost != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("IncreaseManpowerCost"), (Game.getCiv(iCivID).civBonuses.IncreaseManpowerCost > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).civBonuses.IncreaseManpowerCost, 100) + "%", Game_Calendar.IMG_MANPOWER_UP, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.GeneralAttack != 0) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("GeneralsAttack"), (Game.getCiv(iCivID).civBonuses.GeneralAttack > 0 ? "+" : "") + CFG.getPrecision2((float) Game.getCiv(iCivID).civBonuses.GeneralAttack, 100), Images.attack, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.GeneralDefense != 0) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("GeneralsDefense"), (Game.getCiv(iCivID).civBonuses.GeneralDefense > 0 ? "+" : "") + CFG.getPrecision2((float) Game.getCiv(iCivID).civBonuses.GeneralDefense, 100), Images.defense, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.RegimentsLimit != 0) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("RegimentsLimit"), (Game.getCiv(iCivID).civBonuses.RegimentsLimit > 0 ? "+" : "") + Game.getCiv(iCivID).civBonuses.RegimentsLimit, Images.regimentsLimit, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.UnitsAttack != 0) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("UnitsAttack"), (Game.getCiv(iCivID).civBonuses.UnitsAttack > 0 ? "+" : "") + CFG.getPrecision2((float) Game.getCiv(iCivID).civBonuses.UnitsAttack, 100), Images.attack, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.UnitsDefense != 0) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("UnitsDefense"), (Game.getCiv(iCivID).civBonuses.UnitsDefense > 0 ? "+" : "") + CFG.getPrecision2((float) Game.getCiv(iCivID).civBonuses.UnitsDefense, 100), Images.defense, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.MaxMorale != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("MaxMorale"), (Game.getCiv(iCivID).civBonuses.MaxMorale > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).civBonuses.MaxMorale * 100.0F, 100) + "%", Images.morale, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.AdvisorMaxLevel != 0) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("MaximumAdvisorSkillLevel"), (Game.getCiv(iCivID).civBonuses.AdvisorMaxLevel > 0 ? "+" : "") + Game.getCiv(iCivID).civBonuses.AdvisorMaxLevel, Images.skill, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.AdvisorPoolSize != 0) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("AdvisorPool"), (Game.getCiv(iCivID).civBonuses.AdvisorPoolSize > 0 ? "+" : "") + Game.getCiv(iCivID).civBonuses.AdvisorPoolSize, Images.council, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.ArmyMovementSpeed != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("ArmyMovementSpeed"), (Game.getCiv(iCivID).civBonuses.ArmyMovementSpeed > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).civBonuses.ArmyMovementSpeed, 100) + "%", Images.movementSpeed, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.SiegeEffectiveness != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("SiegeEffectiveness"), (Game.getCiv(iCivID).civBonuses.SiegeEffectiveness > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).civBonuses.SiegeEffectiveness * 100.0F, 100) + "%", Images.siege, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.ImproveRelationsModifier != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("ImproveRelationsModifier"), (Game.getCiv(iCivID).civBonuses.ImproveRelationsModifier > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).civBonuses.ImproveRelationsModifier, 100) + "%", Images.relations, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.IncomeFromVassals != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("IncomeFromVassals"), (Game.getCiv(iCivID).civBonuses.IncomeFromVassals > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).civBonuses.IncomeFromVassals * 100.0F, 100) + "%", Images.vassal, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.DiplomacyPoints != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("DiplomacyPoints"), (Game.getCiv(iCivID).civBonuses.DiplomacyPoints > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).civBonuses.DiplomacyPoints * 100.0F, 100) + "%", Images.diplomacy, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.Corruption != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("Corruption"), (Game.getCiv(iCivID).civBonuses.Corruption > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).civBonuses.Corruption * 100.0F, 100), Images.corruption, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.Inflation != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("Inflation"), (Game.getCiv(iCivID).civBonuses.Inflation > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).civBonuses.Inflation * 100.0F, 100), Images.inflation, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.MaxNumberOfLoans != 0) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("MaximumNumberOfLoans"), (Game.getCiv(iCivID).civBonuses.MaxNumberOfLoans > 0 ? "+" : "") + Game.getCiv(iCivID).civBonuses.MaxNumberOfLoans, Images.loan, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.MaximumLevelOfTheMilitaryAcademyForGenerals != 0) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("MaximumLevelOfTheMilitaryAcademyForGenerals"), (Game.getCiv(iCivID).civBonuses.MaximumLevelOfTheMilitaryAcademyForGenerals > 0 ? "+" : "") + Game.getCiv(iCivID).civBonuses.MaximumLevelOfTheMilitaryAcademyForGenerals, Images.general, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.MaximumLevelOfTheMilitaryAcademy != 0) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("MaximumLevelOfTheMilitaryAcademy"), (Game.getCiv(iCivID).civBonuses.MaximumLevelOfTheMilitaryAcademy > 0 ? "+" : "") + Game.getCiv(iCivID).civBonuses.MaximumLevelOfTheMilitaryAcademy, Game_Calendar.IMG_MANPOWER, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.MaximumLevelOfTheSupremeCourt != 0) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("MaximumLevelOfTheSupremeCourt"), (Game.getCiv(iCivID).civBonuses.MaximumLevelOfTheSupremeCourt > 0 ? "+" : "") + Game.getCiv(iCivID).civBonuses.MaximumLevelOfTheSupremeCourt, Images.stability, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.MaximumLevelOfNuclearReactor != 0) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("MaximumLevelOfTheNuclearReactor"), (Game.getCiv(iCivID).civBonuses.MaximumLevelOfNuclearReactor > 0 ? "+" : "") + Game.getCiv(iCivID).civBonuses.MaximumLevelOfNuclearReactor, Images.nuke, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.MaximumLevelOfCapitalCity != 0) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("MaximumLevelOfCapitalCity"), (Game.getCiv(iCivID).civBonuses.MaximumLevelOfCapitalCity > 0 ? "+" : "") + Game.getCiv(iCivID).civBonuses.MaximumLevelOfCapitalCity, Images.capital, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.Discipline != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("Discipline"), (Game.getCiv(iCivID).civBonuses.Discipline > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).civBonuses.Discipline * 100.0F, 10) + "%", Images.discipline, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.MaximumAmountOfGold != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("MaximumAmountOfGold"), (Game.getCiv(iCivID).civBonuses.MaximumAmountOfGold > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).civBonuses.MaximumAmountOfGold, 10), Images.gold, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.MaximumAmountOfGold_Percentage != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("MaximumAmountOfGold"), (Game.getCiv(iCivID).civBonuses.MaximumAmountOfGold_Percentage > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).civBonuses.MaximumAmountOfGold_Percentage * 100.0F, 100) + "%", Images.gold, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.Loot != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("Loot"), (Game.getCiv(iCivID).civBonuses.Loot > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).civBonuses.Loot * 100.0F, 10) + "%", Images.loot, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.ManpowerRecoveryFromADisbandedArmy != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("ManpowerRecoveryFromADisbandedArmy"), (Game.getCiv(iCivID).civBonuses.ManpowerRecoveryFromADisbandedArmy > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).civBonuses.ManpowerRecoveryFromADisbandedArmy * 100.0F, 10) + "%", Game_Calendar.IMG_MANPOWER_DISBAND, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.BattleWidth != 0) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("BattleWidth"), (Game.getCiv(iCivID).civBonuses.BattleWidth > 0 ? "+" : "") + Game.getCiv(iCivID).civBonuses.BattleWidth, Images.battleWidth, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.AllCharactersLifeExpectancy != 0) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("AllCharactersLifeExpectancy"), (Game.getCiv(iCivID).civBonuses.AllCharactersLifeExpectancy > 0 ? "+" : "") + Game.lang.get("YearsX", Game.getCiv(iCivID).civBonuses.AllCharactersLifeExpectancy), Images.council, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.DiseaseDeathRate != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("DiseasesDeathRate"), (Game.getCiv(iCivID).civBonuses.DiseaseDeathRate > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).civBonuses.DiseaseDeathRate * 100.0F, 100) + "%", Images.disease, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.LoanInterest != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("LoanInterest"), (Game.getCiv(iCivID).civBonuses.LoanInterest > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).civBonuses.LoanInterest, 100) + "%", Images.loan, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.AggressiveExpansion != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("AggressiveExpansion"), (Game.getCiv(iCivID).civBonuses.AggressiveExpansion > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).civBonuses.AggressiveExpansion, 100) + "%", Images.war, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.MaxNumOfAlliances != 0) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("MaxNumOfAlliances"), (Game.getCiv(iCivID).civBonuses.MaxNumOfAlliances > 0 ? "+" : "") + CFG.getPrecision2((float) Game.getCiv(iCivID).civBonuses.MaxNumOfAlliances, 100), Images.alliance, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.AdvisorCost != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("AdvisorCost"), (Game.getCiv(iCivID).civBonuses.AdvisorCost > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).civBonuses.AdvisorCost * 100.0F, 100) + "%", Images.council, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.GeneralCost != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("GeneralCost"), (Game.getCiv(iCivID).civBonuses.GeneralCost > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).civBonuses.GeneralCost * 100.0F, 100) + "%", Images.general, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.RevolutionaryRisk != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("RevolutionaryRisk"), (Game.getCiv(iCivID).civBonuses.RevolutionaryRisk > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).civBonuses.RevolutionaryRisk, 100) + "%", Images.revolutionRisk, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.CoreCost != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("CoreConstruction"), (Game.getCiv(iCivID).civBonuses.CoreCost > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).civBonuses.CoreCost, 100) + "%", Images.core, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (Game.getCiv(iCivID).civBonuses.ReligionCost != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses_Right_Color(Game.lang.get("ReligionConversionCost"), (Game.getCiv(iCivID).civBonuses.ReligionCost > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).civBonuses.ReligionCost, 100) + "%", Images.religion, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        while (!mElementsToSort.isEmpty()) {
            int addID = 0;
            int o = 1;

            for (int oSize = mElementsToSort.size(); o < oSize; ++o) {
                if (CFG.compareAlphabetic_TwoString(mElementsToSort.get(addID).getText(), mElementsToSort.get(o).getText())) {
                    addID = o;
                }
            }

            menuElements.add(mElementsToSort.get(addID));
            menuElements.get(menuElements.size() - 1).setPosY(buttonY);
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            mElementsToSort.remove(addID);
        }

        if (menuElements.isEmpty()) {
            menuElements.add(new Text_Static(Game.lang.get("None") + ".", CFG.FONT_REGULAR_SMALL, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT) {
                protected Color getColor(boolean isActive) {
                    if (isActive) {
                        return Colors.BUTTON_TEXT_ACTIVE;
                    } else if (this.getIsHovered()) {
                        return Colors.BUTTON_TEXT_HOVERED;
                    } else {
                        return this.getClickable() ? Colors.BUTTON_TEXT : Colors.BUTTON_TEXT_DISABLED;
                    }
                }
            });
        }

        int i = 0;

        for (int iSize = menuElements.size(); i < iSize; ++i) {
            if (buttonY < menuElements.get(i).getPosY() + menuElements.get(i).getHeight() + CFG.PADDING) {
                buttonY = menuElements.get(i).getPosY() + menuElements.get(i).getHeight() + CFG.PADDING;
            }
        }

        menuElements.add(new Text_Title_v2(Game.lang.get("CivilizationRank") + ": " + CivilizationRanking.getCivilizationRank_Name(Game.getCiv(iCivID).iCivRankID), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 6));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        if (GameValues.civRank.CIV_RANK_MANPOWER_MAX[Game.getCiv(iCivID).iCivRankID] != 0) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses2(Game.lang.get("MaximumManpower") + ": ", "+" + GameValues.civRank.CIV_RANK_MANPOWER_MAX[Game.getCiv(iCivID).iCivRankID], Game_Calendar.IMG_MANPOWER_UP, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (GameValues.civRank.CIV_RANK_ARMY_MAINTENANCE[Game.getCiv(iCivID).iCivRankID] != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses2(Game.lang.get("ArmyMaintenance") + ": ", CFG.getPrecision2(GameValues.civRank.CIV_RANK_ARMY_MAINTENANCE[Game.getCiv(iCivID).iCivRankID] * 100.0F, 100) + "%", Images.armyMaintenance, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (GameValues.civRank.CIV_RANK_INVEST_ECONOMY_COST[Game.getCiv(iCivID).iCivRankID] != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses2(Game.lang.get("InvestInEconomyCost") + ": ", CFG.getPrecision2(GameValues.civRank.CIV_RANK_INVEST_ECONOMY_COST[Game.getCiv(iCivID).iCivRankID] * 100.0F, 100) + "%", Game_Calendar.IMG_ECONOMY_UP, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (GameValues.civRank.CIV_RANK_BUILDING_CONSTRUCTION_COST[Game.getCiv(iCivID).iCivRankID] != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses2(Game.lang.get("ConstructionCost") + ": ", CFG.getPrecision2(GameValues.civRank.CIV_RANK_BUILDING_CONSTRUCTION_COST[Game.getCiv(iCivID).iCivRankID] * 100.0F, 100) + "%", Images.construction, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (GameValues.civRank.CIV_RANK_ADVISOR_COST[Game.getCiv(iCivID).iCivRankID] != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses2(Game.lang.get("AdvisorCost") + ": ", CFG.getPrecision2(GameValues.civRank.CIV_RANK_ADVISOR_COST[Game.getCiv(iCivID).iCivRankID] * 100.0F, 100) + "%", Images.council, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (GameValues.civRank.CIV_RANK_GENERAL_COST[Game.getCiv(iCivID).iCivRankID] != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses2(Game.lang.get("GeneralCost") + ": ", CFG.getPrecision2(GameValues.civRank.CIV_RANK_GENERAL_COST[Game.getCiv(iCivID).iCivRankID] * 100.0F, 100) + "%", Images.general, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (GameValues.civRank.CIV_RANK_MONTHLY_LEGACY[Game.getCiv(iCivID).iCivRankID] != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses2(Game.lang.get("MonthlyLegacy") + ": ", "+" + CFG.getPrecision2(GameValues.civRank.CIV_RANK_MONTHLY_LEGACY[Game.getCiv(iCivID).iCivRankID], 100), Images.legacy, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (GameValues.civRank.CIV_RANK_MONTHLY_RESEARCH[Game.getCiv(iCivID).iCivRankID] != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses2(Game.lang.get("ResearchPerMonth") + ": ", "+" + CFG.getPrecision2(GameValues.civRank.CIV_RANK_MONTHLY_RESEARCH[Game.getCiv(iCivID).iCivRankID], 100), Game_Calendar.IMG_TECHNOLOGY, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (GameValues.civRank.CIV_RANK_PROVINCE_MAINTENANCE[Game.getCiv(iCivID).iCivRankID] != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses2(Game.lang.get("ProvinceMaintenance") + ": ", (GameValues.civRank.CIV_RANK_PROVINCE_MAINTENANCE[Game.getCiv(iCivID).iCivRankID] > 0.0F ? "+" : "") + CFG.getPrecision2(GameValues.civRank.CIV_RANK_PROVINCE_MAINTENANCE[Game.getCiv(iCivID).iCivRankID] * 100.0F, 100) + "%", Images.gold, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (GameValues.civRank.CIV_RANK_LOAN_INTEREST[Game.getCiv(iCivID).iCivRankID] != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses2(Game.lang.get("Interest") + ": ", CFG.getPrecision2(GameValues.civRank.CIV_RANK_LOAN_INTEREST[Game.getCiv(iCivID).iCivRankID], 100) + "%", Images.loan, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        if (GameValues.civRank.CIV_RANK_WAR_SCORE_COST[Game.getCiv(iCivID).iCivRankID] != 0.0F) {
            mElementsToSort.add(new ButtonStatsRectIMG_Bonuses2(Game.lang.get("WarScoreCost") + ": ", (GameValues.civRank.CIV_RANK_WAR_SCORE_COST[Game.getCiv(iCivID).iCivRankID] > 0.0F ? "+" : "") + CFG.getPrecision2(GameValues.civRank.CIV_RANK_WAR_SCORE_COST[Game.getCiv(iCivID).iCivRankID] * 100.0F, 100) + "%", Images.victoryPoints, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH, maxIconW));
        }

        while (mElementsToSort.size() > 0) {
            i = 0;
            int o = 1;

            for (int oSize = mElementsToSort.size(); o < oSize; ++o) {
                if (CFG.compareAlphabetic_TwoString(mElementsToSort.get(i).getText(), mElementsToSort.get(o).getText())) {
                    i = o;
                }
            }

            menuElements.add(mElementsToSort.get(i));
            menuElements.get(menuElements.size() - 1).setPosY(buttonY);
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
            mElementsToSort.remove(i);
        }

        i = 0;

        for (int iSize = menuElements.size(); i < iSize; ++i) {
            if (buttonY < menuElements.get(i).getPosY() + menuElements.get(i).getHeight() + CFG.PADDING) {
                buttonY = menuElements.get(i).getPosY() + menuElements.get(i).getHeight() + CFG.PADDING;
            }
        }

        i = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - (CFG.GAME_HEIGHT - Game.menuManager.getInGame_MapModesPosY() + CFG.PADDING * 3));
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, i)));
        this.initMenu(new MenuTitleIMG_FlagCenter("", Game.getCiv(iCivID).getCivName(), false, false, Images.title1Red) {
            public long getTime() {
                return InGame_CivBonuses.lTime;
            }

            public int getFlagCivID() {
                return InGame_CivBonuses.iCivID;
            }
        }, CFG.GAME_WIDTH - menuWidth - CFG.PADDING * 2, menuY, menuWidth, i, menuElements, false, true);
    }

    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean menuIsActive, Status titleStatus) {
        if (lTime + ANIMATION_TIME >= CFG.currentTimeMillis) {
            float f = ((float) (CFG.currentTimeMillis - lTime) / ANIMATION_TIME);
            f = Interpolation.smooth.apply(f);
            iTranslateX = iTranslateX + CFG.BUTTON_WIDTH - (int) ((float) CFG.BUTTON_WIDTH * f);
        }

        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false);
        ImageManager.getImage(Images.newGameOver).draw2(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.newGameOver).getWidth() / 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.newGameOver).getHeight()));
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }

    public void updateLanguage() {
        super.updateLanguage();
        if(nationSpirit){
            this.getTitle().setText(Game.lang.get("NationSpirit"));
        }else {
            this.getTitle().setText(Game.lang.get("CivilizationBonuses"));
        }
    }

    public void setVisible(boolean visible) {
        super.setVisible(visible);
        lTime = CFG.currentTimeMillis;
    }

    public boolean getVisible() {
        return super.getVisible() && Game.mapBG.getHideMenuZoomOut();
    }
}
