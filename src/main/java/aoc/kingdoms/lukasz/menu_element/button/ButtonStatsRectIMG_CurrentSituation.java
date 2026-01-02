//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.menu_element.button;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_Hover;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.textures.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import team.rainfall.fontFix.Config;

import java.util.ArrayList;
import java.util.List;

public class ButtonStatsRectIMG_CurrentSituation extends Button {
    public int iconWidth;
    public int iconHeight;
    public int maxIconWidth;
    protected static long lTimeAnimation = 0L;
    protected static int animationState = 0;

    public ButtonStatsRectIMG_CurrentSituation(String sText, int imageID, int iPosX, int iPosY, int nWidth, int nHeight, int maxIconWidth) {
        this.init(sText, CFG.FONT_REGULAR_SMALL, 0, iPosX, iPosY, nWidth, nHeight, true, true, false, false);
        this.maxIconWidth = maxIconWidth;
        float iconScale = this.getImageScale(Images.currentSituation) * 1.3F;
        this.iconWidth = (int)((float)ImageManager.getImage(Images.currentSituation).getWidth() * iconScale);
        this.iconHeight = (int)((float)ImageManager.getImage(Images.currentSituation).getHeight() * iconScale);
    }

    protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        iTranslateX = this.getPosX() + iTranslateX;
        iTranslateY = this.getPosY() + iTranslateY;

        oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG_BLUE.r, Colors.COLOR_GRADIENT_BG_BLUE.g, Colors.COLOR_GRADIENT_BG_BLUE.b, !this.getIsHovered() && !isActive ? 0.2F : 0.5F));
        Renderer.drawBox(oSB, Images.statsRectBG, iTranslateX, iTranslateY, this.getWidth(), this.getHeight(), 1.0F);
        oSB.setColor(Color.WHITE);
        if(Config.getGradientConfig().textTop < 3) {
            oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.65F));
            Images.gradientFull.draw(oSB, iTranslateX, iTranslateY, this.getWidth(), this.getHeight());
            oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.3F));
            Images.gradientXY.draw(oSB, iTranslateX, iTranslateY, this.getWidth(), this.getHeight());
        }
        if(Config.getGradientConfig().textTop < 2) {
            oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.25F));
            Images.gradientXY.draw(oSB, iTranslateX, iTranslateY, this.getWidth(), CFG.PADDING * 2, false, true);
            Images.gradientXY.draw(oSB, iTranslateX, iTranslateY + this.getHeight() - CFG.PADDING * 2, this.getWidth(), CFG.PADDING * 2);
        }
        oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.25F));
        Renderer.drawBox(oSB, Images.statsRectBGBorder, iTranslateX, iTranslateY, this.getWidth(), this.getHeight(), 1.0F);
        if(Config.getGradientConfig().textTop < 1) {
            oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.5F));
            Images.gradientFull.draw(oSB, iTranslateX, iTranslateY + this.getHeight() - 1, this.getWidth(), 1);
            Images.gradientFull.draw(oSB, iTranslateX, iTranslateY, this.getWidth(), 1);
            oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.85F));
            Images.gradientFull.draw(oSB, iTranslateX, iTranslateY + this.getHeight() - 2, this.getWidth(), 1);
            Images.gradientFull.draw(oSB, iTranslateX, iTranslateY + 1, this.getWidth(), 1);
            oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.55F));
            Images.gradientFull.draw(oSB, iTranslateX, iTranslateY + this.getHeight() - 1, this.getWidth(), 1);
            Images.gradientFull.draw(oSB, iTranslateX, iTranslateY, this.getWidth(), 1);
            oSB.setColor(new Color(Colors.COLOR_GRADIENT_OVER_BLUE.r, Colors.COLOR_GRADIENT_OVER_BLUE.g, Colors.COLOR_GRADIENT_OVER_BLUE.b, 0.9F));
            Images.gradientFull.draw(oSB, iTranslateX, iTranslateY + this.getHeight() - 2, this.getWidth(), 1);
            Images.gradientFull.draw(oSB, iTranslateX, iTranslateY + 1, this.getWidth(), 1);
        }
        if (this.getClickable() && this.getIsHovered() && animationState >= 0) {
            if (animationState == 0) {
                float drawPerc = Math.min((float) (CFG.currentTimeMillis - lTimeAnimation) / 1000.0F, 1.0F);
                oSB.setColor(ButtonGame.getColorLine());
                Images.line_32_off1.draw(oSB, iTranslateX + CFG.PADDING, iTranslateY + 1, (int)((float)(this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                Images.line_32_off1.draw(oSB, iTranslateX + CFG.PADDING, iTranslateY + this.getHeight() - 2, (int)((float)(this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                if (lTimeAnimation < CFG.currentTimeMillis - 1000L) {
                    ++animationState;
                    lTimeAnimation = CFG.currentTimeMillis;
                }
            } else {
                float drawPerc = Math.min((float) (CFG.currentTimeMillis - lTimeAnimation) / 1000.0F, 1.0F);
                oSB.setColor(ButtonGame.getColorLine());
                Images.line_32_off1.draw(oSB, iTranslateX + CFG.PADDING + (int)((float)(this.getWidth() - CFG.PADDING * 2) * drawPerc), iTranslateY + 1, this.getWidth() - CFG.PADDING * 2 - (int)((float)(this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                Images.line_32_off1.draw(oSB, iTranslateX + CFG.PADDING + (int)((float)(this.getWidth() - CFG.PADDING * 2) * drawPerc), iTranslateY + this.getHeight() - 2, this.getWidth() - CFG.PADDING * 2 - (int)((float)(this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                if (lTimeAnimation < CFG.currentTimeMillis - 1000L) {
                    animationState = 0;
                    lTimeAnimation = CFG.currentTimeMillis;
                }
            }

            oSB.setColor(Color.WHITE);
        }

        oSB.setColor(Color.WHITE);
    }

    public static final float getBoxAlpha(boolean clickable, boolean isHovered, boolean isActive) {
        return clickable ? (isActive ? 0.85F : (isHovered ? 0.7F : 0.5F)) : 0.2F;
    }

    protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        ImageManager.getImage(Images.currentSituation).draw(oSB, this.getPosX() + CFG.PADDING + this.maxIconWidth / 2 - this.iconWidth / 2 + iTranslateX, this.getPosY() + (this.getHeight() - this.iconHeight) / 2 + iTranslateY, this.iconWidth, this.iconHeight);
        Renderer.drawTextWithShadow(oSB, this.fontID, this.getTextToDraw(), this.getPosX() + CFG.PADDING * 2 + this.maxIconWidth + (this.getWidth() - (CFG.PADDING * 2 + this.maxIconWidth)) / 2 - this.getTextWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.iTextHeight / 2 + iTranslateY, this.getColor(isActive));
    }

    private final float getImageScale(int iImageID) {
        return (float)CFG.TEXT_HEIGHT / (float)ImageManager.getImage(iImageID).getHeight();
    }

    protected Color getColor(boolean isActive) {
        return Colors.getColorButtonHover(isActive, this.getIsHovered());
    }

    public String getTextToDraw() {
        if (this.iCurrent != Game.player.currSituation.currentSituationNum) {
            this.setText("" + Game.player.currSituation.currentSituationNum);
            this.iCurrent = Game.player.currSituation.currentSituationNum;
        }

        return super.getTextToDraw();
    }

    public void buildElementHover() {
        List<MenuElement_HoverElement> nElements = new ArrayList();
        List<MenuElement_HoverElement_Type> nData = new ArrayList();
        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("CurrentSituation"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.currentSituation, CFG.PADDING, 0));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Messages") + ": ", "" + Game.player.currSituation.currentSituationNum, Images.currentSituation, CFG.FONT_REGULAR, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        if (Game.player.currSituation.currentSituationNum > 0) {
            nData.add(new MenuElement_HoverElement_Type_Line());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            if (Game.player.currSituation.noActiveResearch) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("NoActiveResearch"), CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_TECHNOLOGY, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }

            if (Game.player.currSituation.maxAmountOfGold) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("MaximumAmountOfGold") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text(CFG.getPrecision2(Game.getCiv(Game.player.iCivID).fGold, 1) + " / " + CFG.getPrecision2((float)Game.getMaxAmountOfGold(Game.player.iCivID), 1), CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }

            if (Game.player.currSituation.missionCanBeUnlocked) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Missions") + ": " + Game.lang.get("Available"), CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.missions, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }

            if (Game.player.currSituation.chooseRivals) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("ChooseYourRivals"), CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.rivals, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }

            if (Game.player.currSituation.availableAdvantage) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("AdvantagePoints") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text("" + Game.getCiv(Game.player.iCivID).getAdvantagePoints(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.advantages, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }

            if (Game.player.currSituation.newLawAvailable) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Law") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Unlocked"), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.law, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }

            if (Game.player.currSituation.highInflation) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("HighInflation") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text(CFG.getPrecision2(Game.getCiv(Game.player.iCivID).getInflation() * 100.0F, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.inflation, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }

            if (Game.player.currSituation.nonCoreProvinces) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("NonCoreProvince"), CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.core, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }

            if (Game.player.currSituation.differentReligionProvinces) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("DifferentReligion"), CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.religion, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }

            if (Game.player.currSituation.availableCivilizationLegacy) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("AvailableCivilizationLegacy"), CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.legacy, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }

            if (Game.player.currSituation.noAdvisor > 0) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("NoAdvisor"), CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.skill, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }

            if (Game.player.currSituation.promoteAdvisor > 0) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("PromoteAdvisor"), CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.skill, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }

            if (Game.player.currSituation.lackOfGeneral) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("LackOfGeneral"), CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.general, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }

            if (Game.player.currSituation.upgradeCapitalCity) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("UpgradeCapitalCity"), CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.capital, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }

            if (Game.player.currSituation.upgradeSupremeCourt) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("UpgradeSupremeCourt"), CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.stability, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }

            if (Game.player.currSituation.militaryAcademyCanBeUpgraded) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("UpgradeMilitaryAcademy"), CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_MANPOWER, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }

            if (Game.player.currSituation.militaryAcademyForGeneralsCanBeUpgraded) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("UpgradeMilitaryAcademyForGenerals"), CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.general, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }

            if (Game.player.currSituation.upgradeNuclearReactor) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("UpgradeNuclearReactor"), CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.nuke, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }

            if (Game.player.currSituation.wonderCanBeBuilt) {
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("AWonderCanBeBuilt"), CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Image(Images.mapModesWonders, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
        }

        if (CFG.isDesktop()) {
            nData.add(new MenuElement_HoverElement_Type_Line());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Shortcut") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT));
            nData.add(new MenuElement_HoverElement_Type_Text("T", CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.currentSituation, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }

        this.menuElementHover = new MenuElement_Hover(nElements);
    }

    public void setIsHovered(boolean isHovered) {
        super.setIsHovered(isHovered);
        lTimeAnimation = CFG.currentTimeMillis;
        animationState = 0;
    }
}
