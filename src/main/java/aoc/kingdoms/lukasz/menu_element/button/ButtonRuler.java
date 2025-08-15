//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.menu_element.button;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.map.RulersManager;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.*;
import aoc.kingdoms.lukasz.menu_element.textStatic.TextIcon2;
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.textures.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import team.rainfall.fontFix.Config;

import java.util.ArrayList;
import java.util.List;

public class ButtonRuler extends Button {
    public int iCivID;

    public ButtonRuler(int iCivID, int iPosX, int iPosY) {
        this.iCivID = iCivID;
        this.init(Game.getCiv(iCivID).ruler.Name, CFG.FONT_REGULAR_SMALL, this.iTextPositionX, iPosX, iPosY, getButtonWidth(), getButtonHeight(), true, true, false, false);
        int tWMax = 0;

        while(this.iTextWidth > this.getWidth() && this.getText().length() > 5) {
            ++tWMax;
            if (tWMax >= 100) {
                break;
            }

            this.setText(this.getText().substring(0, Math.max(1, this.getText().length() - 3)) + "..");
        }

    }

    protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        if (this.getIsHovered() || isActive) {
            Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, ImageManager.getImage(Images.rulerFrame).getWidth(), ImageManager.getImage(Images.rulerFrame).getHeight());
        }

        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, 0.5F));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0F);
        oSB.setColor(Color.WHITE);
        oSB.setColor(TextIcon2.getColor_gradientXY());
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - getTextHeightBG() + iTranslateY, this.getWidth(), getTextHeightBG());
        oSB.setColor(TextIcon2.getColor_gradientFull());
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - getTextHeightBG() + iTranslateY, this.getWidth(), 1);
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 + iTranslateY, this.getWidth(), 1);
        oSB.setColor(Color.WHITE);

        try {
            if (RulersManager.rulerIMG != null) {
                RulersManager.rulerIMG.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, ImageManager.getImage(Images.rulerFrame).getWidth(), ImageManager.getImage(Images.rulerFrame).getHeight());
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

        ImageManager.getImage(Images.rulerFrame).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY);
        oSB.setColor(Color.WHITE);
    }

    protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        Renderer.drawText(oSB, this.fontID, this.getText(), this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 + iTranslateX, this.getPosY() + ImageManager.getImage(Images.rulerFrame).getHeight() + getTextHeightBG() / 2 - this.iTextHeight / 2 + iTranslateY, this.getColor(isActive));
    }

    public static int getButtonWidth() {
        return ImageManager.getImage(Images.rulerFrame).getWidth();
    }

    public static int getButtonHeight() {
        return ImageManager.getImage(Images.rulerFrame).getHeight() + CFG.TEXT_HEIGHT + CFG.PADDING * 2;
    }

    public static int getTextHeightBG() {
        return CFG.TEXT_HEIGHT + CFG.PADDING * 2;
    }

    public void actionElementPPM() {
        if (Game.getCiv(this.iCivID).getCapitalProvinceID() >= 0) {
            Game.mapCoords.centerToProvinceID(Game.getCiv(this.iCivID).getCapitalProvinceID());
        }

    }

    public void buildElementHover() {
        List<MenuElement_HoverElement> nElements = new ArrayList();
        List<MenuElement_HoverElement_Type> nData = new ArrayList();
        nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(this.iCivID, Game.ideologiesManager.getIdeology(Game.getCiv(this.iCivID).getIdeologyID()).RulerTitle + ": " + Game.getCiv(this.iCivID).ruler.Name));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Born") + ": ", CFG.FONT_REGULAR_SMALL));
        nData.add(new MenuElement_HoverElement_Type_Text(Game.getCiv(this.iCivID).ruler.BornDay + " " + Game_Calendar.getMonthName(Game.getCiv(this.iCivID).ruler.BornMonth) + " " + Game.getCiv(this.iCivID).ruler.BornYear, CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
        nData.add(new MenuElement_HoverElement_Type_Image(Images.time, CFG.PADDING, CFG.PADDING));
        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("XYearsOld", Math.min(99, Game_Calendar.currentYear - Game.getCiv(this.iCivID).ruler.BornYear)), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        if(Game.getCiv(iCivID).ruler.Desc != null && !Game.getCiv(iCivID).ruler.Desc.isEmpty()) {
            nData.add(new MenuElement_HoverElement_Type_Line());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            //Desc
            nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.getCiv(iCivID).ruler.Desc));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        nData.add(new MenuElement_HoverElement_Type_Line());
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        if(Config.getConfig().hideBonus){
            if (Game.menuManager.getInGame()) {
                nData.add(new MenuElement_HoverElement_Type_Empty());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get(GameValues.court.COUNCIL_NAME), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.council, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            } else {
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
            return;
        }
        if (Game.getCiv(this.iCivID).ruler.rulerBonuses.MonthlyIncome != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MonthlyIncome") + ": ", (Game.getCiv(this.iCivID).ruler.rulerBonuses.MonthlyIncome > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(this.iCivID).ruler.rulerBonuses.MonthlyIncome, 100), Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (Game.getCiv(this.iCivID).ruler.rulerBonuses.TaxEfficiency != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("TaxEfficiency") + ": ", (Game.getCiv(this.iCivID).ruler.rulerBonuses.TaxEfficiency > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(this.iCivID).ruler.rulerBonuses.TaxEfficiency, 100) + "%", Images.tax, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (Game.getCiv(this.iCivID).ruler.rulerBonuses.ProductionEfficiency != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ProductionEfficiency") + ": ", (Game.getCiv(this.iCivID).ruler.rulerBonuses.ProductionEfficiency > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(this.iCivID).ruler.rulerBonuses.ProductionEfficiency, 100) + "%", Images.goods, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (Game.getCiv(this.iCivID).ruler.rulerBonuses.ProvinceMaintenance != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ProvinceMaintenance") + ": ", (Game.getCiv(this.iCivID).ruler.rulerBonuses.ProvinceMaintenance > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(this.iCivID).ruler.rulerBonuses.ProvinceMaintenance, 100) + "%", Images.goods, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (Game.getCiv(this.iCivID).ruler.rulerBonuses.MaxManpower != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaximumManpower") + ": ", (Game.getCiv(this.iCivID).ruler.rulerBonuses.MaxManpower > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(this.iCivID).ruler.rulerBonuses.MaxManpower, 1), Game_Calendar.IMG_MANPOWER_UP, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (Game.getCiv(this.iCivID).ruler.rulerBonuses.IncreaseManpowerCost != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("IncreaseManpowerCost") + ": ", (Game.getCiv(this.iCivID).ruler.rulerBonuses.IncreaseManpowerCost > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(this.iCivID).ruler.rulerBonuses.IncreaseManpowerCost, 100) + "%", Game_Calendar.IMG_MANPOWER_UP, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (Game.getCiv(this.iCivID).ruler.rulerBonuses.RecruitmentTime != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("RecruitmentTime") + ": ", (Game.getCiv(this.iCivID).ruler.rulerBonuses.RecruitmentTime > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(this.iCivID).ruler.rulerBonuses.RecruitmentTime, 100) + "%", Game_Calendar.IMG_MANPOWER_TIME, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (Game.getCiv(this.iCivID).ruler.rulerBonuses.Research != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Research") + ": ", (Game.getCiv(this.iCivID).ruler.rulerBonuses.Research > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(this.iCivID).ruler.rulerBonuses.Research, 100) + "%", Game_Calendar.IMG_TECHNOLOGY, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (Game.getCiv(this.iCivID).ruler.rulerBonuses.ResearchPoints != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ResearchPerMonth") + ": ", (Game.getCiv(this.iCivID).ruler.rulerBonuses.ResearchPoints > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(this.iCivID).ruler.rulerBonuses.ResearchPoints, 100), Game_Calendar.IMG_TECHNOLOGY, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (Game.getCiv(this.iCivID).ruler.rulerBonuses.Devastation != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Devastation") + ": ", (Game.getCiv(this.iCivID).ruler.rulerBonuses.Devastation > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(this.iCivID).ruler.rulerBonuses.Devastation * 100.0F, 100) + "%", Images.devastation, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (Game.getCiv(this.iCivID).ruler.rulerBonuses.GeneralCost != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("GeneralCost") + ": ", (Game.getCiv(this.iCivID).ruler.rulerBonuses.GeneralCost > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(this.iCivID).ruler.rulerBonuses.GeneralCost * 100.0F, 100) + "%", Game_Calendar.IMG_MANPOWER, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (Game.getCiv(this.iCivID).ruler.rulerBonuses.ConstructionCost != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ConstructionCost") + ": ", (Game.getCiv(this.iCivID).ruler.rulerBonuses.ConstructionCost > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(this.iCivID).ruler.rulerBonuses.ConstructionCost * 100.0F, 100) + "%", Images.construction, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (Game.getCiv(this.iCivID).ruler.rulerBonuses.AdministrationBuildingsCost != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("AdministrationBuildingsCost") + ": ", (Game.getCiv(this.iCivID).ruler.rulerBonuses.AdministrationBuildingsCost > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(this.iCivID).ruler.rulerBonuses.AdministrationBuildingsCost * 100.0F, 100) + "%", Images.construction, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (Game.getCiv(this.iCivID).ruler.rulerBonuses.MilitaryBuildingsCost != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MilitaryBuildingsCost") + ": ", (Game.getCiv(this.iCivID).ruler.rulerBonuses.MilitaryBuildingsCost > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(this.iCivID).ruler.rulerBonuses.MilitaryBuildingsCost * 100.0F, 100) + "%", Images.construction, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (Game.getCiv(this.iCivID).ruler.rulerBonuses.EconomyBuildingsCost != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("EconomyBuildingsCost") + ": ", (Game.getCiv(this.iCivID).ruler.rulerBonuses.EconomyBuildingsCost > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(this.iCivID).ruler.rulerBonuses.EconomyBuildingsCost * 100.0F, 100) + "%", Images.construction, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (Game.getCiv(this.iCivID).ruler.rulerBonuses.InvestInEconomyCost != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("InvestInEconomyCost") + ": ", (Game.getCiv(this.iCivID).ruler.rulerBonuses.InvestInEconomyCost > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(this.iCivID).ruler.rulerBonuses.InvestInEconomyCost * 100.0F, 100) + "%", Game_Calendar.IMG_ECONOMY_UP, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (Game.getCiv(this.iCivID).ruler.rulerBonuses.IncreaseTaxEfficiencyCost != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("IncreaseTaxEfficiencyCost") + ": ", (Game.getCiv(this.iCivID).ruler.rulerBonuses.IncreaseTaxEfficiencyCost > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(this.iCivID).ruler.rulerBonuses.IncreaseTaxEfficiencyCost * 100.0F, 100) + "%", Images.taxUp, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (Game.getCiv(this.iCivID).ruler.rulerBonuses.IncreaseGrowthRateCost != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("IncreaseGrowthRateCost") + ": ", (Game.getCiv(this.iCivID).ruler.rulerBonuses.IncreaseGrowthRateCost > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(this.iCivID).ruler.rulerBonuses.IncreaseGrowthRateCost * 100.0F, 100) + "%", Images.populationUp, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (Game.getCiv(this.iCivID).ruler.rulerBonuses.DevelopInfrastructureCost != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("DevelopInfrastructureCost") + ": ", (Game.getCiv(this.iCivID).ruler.rulerBonuses.DevelopInfrastructureCost > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(this.iCivID).ruler.rulerBonuses.DevelopInfrastructureCost * 100.0F, 100) + "%", Images.infrastructureUp, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (Game.getCiv(this.iCivID).ruler.rulerBonuses.ImproveRelationsModifier != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ImproveRelationsModifier") + ": ", (Game.getCiv(this.iCivID).ruler.rulerBonuses.ImproveRelationsModifier > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(this.iCivID).ruler.rulerBonuses.ImproveRelationsModifier, 100) + "%", Images.diplomacy, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (Game.getCiv(this.iCivID).ruler.rulerBonuses.LoanInterest != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("LoanInterest") + ": ", (Game.getCiv(this.iCivID).ruler.rulerBonuses.LoanInterest > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(this.iCivID).ruler.rulerBonuses.LoanInterest, 100) + "%", Images.loan, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (Game.getCiv(this.iCivID).ruler.rulerBonuses.MonthlyLegacy != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MonthlyLegacy") + ": ", (Game.getCiv(this.iCivID).ruler.rulerBonuses.MonthlyLegacy > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(this.iCivID).ruler.rulerBonuses.MonthlyLegacy, 100), Images.legacy, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (Game.getCiv(this.iCivID).ruler.rulerBonuses.UnitsAttack != 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("UnitsAttack") + ": ", (Game.getCiv(this.iCivID).ruler.rulerBonuses.UnitsAttack > 0 ? "+" : "") + Game.getCiv(this.iCivID).ruler.rulerBonuses.UnitsAttack, Images.attack, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (Game.getCiv(this.iCivID).ruler.rulerBonuses.UnitsDefense != 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("UnitsDefense") + ": ", (Game.getCiv(this.iCivID).ruler.rulerBonuses.UnitsDefense > 0 ? "+" : "") + Game.getCiv(this.iCivID).ruler.rulerBonuses.UnitsDefense, Images.defense, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (Game.getCiv(this.iCivID).ruler.rulerBonuses.GeneralAttack != 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("GeneralsAttack") + ": ", (Game.getCiv(this.iCivID).ruler.rulerBonuses.GeneralAttack > 0 ? "+" : "") + Game.getCiv(this.iCivID).ruler.rulerBonuses.GeneralAttack, Images.attack, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (Game.getCiv(this.iCivID).ruler.rulerBonuses.GeneralDefense != 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("GeneralsDefense") + ": ", (Game.getCiv(this.iCivID).ruler.rulerBonuses.GeneralDefense > 0 ? "+" : "") + Game.getCiv(this.iCivID).ruler.rulerBonuses.GeneralDefense, Images.defense, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (Game.menuManager.getInGame()) {
            nData.add(new MenuElement_HoverElement_Type_Empty());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get(GameValues.court.COUNCIL_NAME), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
            nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.council, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            this.menuElementHover = new MenuElement_Hover(nElements, true);
        } else {
            this.menuElementHover = new MenuElement_Hover(nElements);
        }

    }

    protected Color getColor(boolean isActive) {
        return Colors.getColorButtonHover(isActive, this.getIsHovered());
    }
}
