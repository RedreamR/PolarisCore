//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.menu_element.button;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.SoundsManager;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.menu.MenuManager;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.*;
import aoc.kingdoms.lukasz.menus.MainMenu;
import aoc.kingdoms.lukasz.menusInGame.Civ.InGame_Civ;
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.textures.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import team.rainfall.fontFix.NationalSpirit;
import team.rainfall.fontFix.NationalSpiritManager;

import java.util.ArrayList;
import java.util.List;

public class ButtonNS_Info extends Button {
    public NationalSpirit nationalSpirit;
    public int imageID;
    public List<String> sLines = new ArrayList();
    public List<Integer> iLinesWidth = new ArrayList();
    public int iLineSize = 0;
    public int iY_Text = 0;
    public int iconY = 0;
    public int iconH = 0;

    public ButtonNS_Info(NationalSpirit nationalSpirit, int iPosX, int iPosY, int iWidth, int iHeight, boolean clickable) {
        this.nationalSpirit = nationalSpirit;
        this.imageID = NationalSpiritManager.INSTANCE.getNSImage(nationalSpirit.id);
        this.init(nationalSpirit.name, CFG.FONT_REGULAR_SMALL, this.iTextPositionX, iPosX, iPosY, iWidth, iHeight, clickable, true, false, false);
    }

    protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
    }

    protected void drawButtonSparks(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {

    }

    protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        ImageManager.getImage(this.imageID).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, getWidth(), getHeight());

    }

    public void setText(String sText) {
        this.sLines.clear();
        this.iLineSize = 0;
        this.iLinesWidth.clear();
        String[] words = sText.split(" ");
        int textPosX = 0;
        int maxW = this.getWidth() - CFG.PADDING * 2;
        String currentLine = "";
        int i = 0;

        for (int iSize = words.length; i < iSize; ++i) {
            Renderer.glyphLayout.setText((BitmapFont) Renderer.fontMain.get(this.fontID), words[i] + " ");
            this.iTextWidth = (int) Renderer.glyphLayout.width;
            textPosX += this.iTextWidth;
            if (textPosX < maxW) {
                currentLine = currentLine + words[i] + " ";
            } else {
                if (currentLine.length() <= 1) {
                    currentLine = words[i] + " ";
                    this.sLines.add(currentLine);
                    currentLine = "";
                } else {
                    this.sLines.add(currentLine);
                    currentLine = words[i] + " ";
                }

                textPosX = this.iTextWidth;
            }
        }

        if (!currentLine.isEmpty()) {
            this.sLines.add(currentLine);
        }

        if (!this.sLines.isEmpty() && !((String) this.sLines.get(0)).isEmpty()) {
            Renderer.glyphLayout.setText((BitmapFont) Renderer.fontMain.get(this.fontID), (CharSequence) this.sLines.get(0));
            this.iTextHeight = (int) Renderer.glyphLayout.height;
        }

        this.iLineSize = this.sLines.size();

        for (i = 0; i < this.iLineSize; ++i) {
            Renderer.glyphLayout.setText((BitmapFont) Renderer.fontMain.get(this.fontID), (CharSequence) this.sLines.get(i));
            this.iLinesWidth.add((int) Renderer.glyphLayout.width);
        }

        this.iY_Text = this.iconH + (this.getHeight() - this.iconH) / 2 - (this.iTextHeight * this.iLineSize + CFG.PADDING * 2 * (this.iLineSize - 1)) / 2;
    }
    public void buildElementHover() {
        List<MenuElement_HoverElement> nElements = new ArrayList();
        List<MenuElement_HoverElement_Type> nData = new ArrayList();
        if(NationalSpiritManager.nsEntryImg > 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(nationalSpirit.name, "", NationalSpiritManager.nsEntryImg, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
        }else {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(nationalSpirit.name, "", Images.advantages, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
        }
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Text_Desc(nationalSpirit.desc, CFG.FONT_BOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        //Bonuses
        if (nationalSpirit.Bonuses.MonthlyIncome != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MonthlyIncome") + ": ", (nationalSpirit.Bonuses.MonthlyIncome > 0.0F ? "+" : "") + CFG.getPrecision2(nationalSpirit.Bonuses.MonthlyIncome, 100), Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        if (nationalSpirit.Bonuses.TaxEfficiency != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("TaxEfficiency") + ": ", (nationalSpirit.Bonuses.TaxEfficiency > 0.0F ? "+" : "") + CFG.getPrecision2(nationalSpirit.Bonuses.TaxEfficiency, 100), Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (nationalSpirit.Bonuses.ProvinceMaintenance != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ProvinceMaintenance") + ": ", (nationalSpirit.Bonuses.ProvinceMaintenance > 0.0F ? "+" : "") + CFG.getPrecision2(nationalSpirit.Bonuses.ProvinceMaintenance, 100), Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (nationalSpirit.Bonuses.GrowthRate != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("GrowthRate") + ": ", (nationalSpirit.Bonuses.GrowthRate > 0.0F ? "+" : "") + CFG.getPrecision2(nationalSpirit.Bonuses.GrowthRate, 100), Images.populationGrowth, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (nationalSpirit.Bonuses.MaxManpower != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaxManpower") + ": ", (nationalSpirit.Bonuses.MaxManpower > 0.0F ? "+" : "") + CFG.getPrecision2(nationalSpirit.Bonuses.MaxManpower, 100), Images.manpower, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (nationalSpirit.Bonuses.ArmyMoraleRecovery != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ArmyMoraleRecovery") + ": ", (nationalSpirit.Bonuses.ArmyMoraleRecovery > 0.0F ? "+" : "") + CFG.getPrecision2(nationalSpirit.Bonuses.ArmyMoraleRecovery, 100), Images.morale, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (nationalSpirit.Bonuses.ResearchPoints != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ResearchPoints") + ": ", (nationalSpirit.Bonuses.ResearchPoints > 0.0F ? "+" : "") + CFG.getPrecision2(nationalSpirit.Bonuses.ResearchPoints, 100), Images.technology, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (nationalSpirit.Bonuses.UnitsAttack != 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("UnitsAttack") + ": ", (nationalSpirit.Bonuses.UnitsAttack > 0 ? "+" : "") + nationalSpirit.Bonuses.UnitsAttack, Images.attack, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (nationalSpirit.Bonuses.UnitsDefense != 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("UnitsDefense") + ": ", (nationalSpirit.Bonuses.UnitsDefense > 0 ? "+" : "") + nationalSpirit.Bonuses.UnitsDefense, Images.defense, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (nationalSpirit.Bonuses.Discipline != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Discipline") + ": ", (nationalSpirit.Bonuses.Discipline > 0.0F ? "+" : "") + CFG.getPrecision2(nationalSpirit.Bonuses.Discipline, 100), Images.discipline, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (nationalSpirit.Bonuses.ConstructionCost != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("ConstructionCost") + ": ", (nationalSpirit.Bonuses.ConstructionCost > 0.0F ? "+" : "") + CFG.getPrecision2(nationalSpirit.Bonuses.ConstructionCost, 100), Images.construction, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (nationalSpirit.Bonuses.BuildingSlot != 0) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("BuildingSlot") + ": ", (nationalSpirit.Bonuses.BuildingSlot > 0 ? "+" : "") + nationalSpirit.Bonuses.BuildingSlot, Images.build, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        if (nationalSpirit.Bonuses.DiplomacyPoints != 0.0F) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("DiplomacyPoints") + ": ", (nationalSpirit.Bonuses.DiplomacyPoints > 0.0F ? "+" : "") + CFG.getPrecision2(nationalSpirit.Bonuses.DiplomacyPoints, 100), Images.diplomacy, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        this.menuElementHover = new MenuElement_Hover(nElements);
    }
    public int getSFX() {
        return SoundsManager.DIPLOMACY_CLICK;
    }
}
