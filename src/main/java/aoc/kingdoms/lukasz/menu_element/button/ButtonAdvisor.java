//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.menu_element.button;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.FileManager;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.map.advisors.Advisor;
import aoc.kingdoms.lukasz.map.advisors.AdvisorManager;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.*;
import aoc.kingdoms.lukasz.textures.Image;
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.textures.Images;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class ButtonAdvisor extends Button {
    public int iCivID;
    public int imageID;
    public Image advisorImage;
    public int iHireModeID = -1;
    public int iAdvisorType;

    public ButtonAdvisor(int iPosX, int iPosY, String sName, int imageID, int iCivID, int iHireModeID, int iAdvisorType, String sIMG) {
        this.init(sName, CFG.FONT_REGULAR_SMALL, this.iTextPositionX, iPosX, iPosY, ImageManager.getImage(Images.generalFrame).getWidth(), getButtonHeight(), true, true, false, false);
        this.iCivID = iCivID;
        this.imageID = imageID;
        this.iHireModeID = iHireModeID;
        this.iAdvisorType = iAdvisorType;

        try {
            if (sIMG != null) {
                if (FileManager.loadFile("game/characters/" + CFG.getRescouresPath_Short() + sIMG + ".png").exists()) {
                    this.advisorImage = new Image(ImageManager.loadTexture_RGB888("game/characters/" + CFG.getRescouresPath_Short() + sIMG + ".png"), TextureFilter.Linear, TextureWrap.ClampToEdge);
                } else if (FileManager.loadFile("game/characters/" + CFG.getRescouresPath_Short_H() + sIMG + ".png").exists()) {
                    this.advisorImage = new Image(ImageManager.loadTexture_RGB888("game/characters/" + CFG.getRescouresPath_Short_H() + sIMG + ".png"), TextureFilter.Linear, TextureWrap.ClampToEdge);
                }
            }

            if (this.advisorImage == null) {
                if (FileManager.loadFile("game/advisors/" + AdvisorManager.getAdvisorsImgPath() + CFG.getRescouresPath_Short() + Game.getCiv(iCivID).iGroupID + "/" + imageID + ".png").exists()) {
                    this.advisorImage = new Image(ImageManager.loadTexture_RGB888("game/advisors/" + AdvisorManager.getAdvisorsImgPath() + CFG.getRescouresPath_Short() + Game.getCiv(iCivID).iGroupID + "/" + imageID + ".png"), TextureFilter.Linear, TextureWrap.ClampToEdge);
                } else {
                    try {
                        this.advisorImage = new Image(ImageManager.loadTexture_RGB888("game/advisors/" + AdvisorManager.getAdvisorsImgPath() + CFG.getRescouresPath_Short() + Game.getCiv(iCivID).iGroupID + "/0.png"), TextureFilter.Linear, TextureWrap.ClampToEdge);
                    } catch (Exception ex) {
                        this.advisorImage = new Image(ImageManager.loadTexture_RGB888("game/advisors/advisors/" + CFG.getRescouresPath_Short() + "noAdvisor.png"), TextureFilter.Linear, TextureWrap.ClampToEdge);
                        CFG.exceptionStack(ex);
                    }
                }
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        if (this.getIsHovered() || isActive) {
            Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, ImageManager.getImage(Images.generalFrame).getWidth(), ImageManager.getImage(Images.generalFrame).getHeight());
        }

        try {
            this.advisorImage.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, ImageManager.getImage(Images.generalFrame).getWidth(), ImageManager.getImage(Images.generalFrame).getHeight());
        } catch (Exception var6) {
        }

        ImageManager.getImage(Images.generalFrame).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY);
    }

    protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
    }

    public static int getButtonHeight() {
        return ImageManager.getImage(Images.generalFrame).getHeight();
    }

    public static int getButtonWidth() {
        return ImageManager.getImage(Images.generalFrame).getWidth();
    }

    public void dispose() {
        if (this.advisorImage != null) {
            this.advisorImage.dispose();
            this.advisorImage = null;
        }

    }

    public int getCurrent() {
        return this.iHireModeID;
    }

    public void buildElementHover() {
        List<MenuElement_HoverElement> nElements = new ArrayList();
        List<MenuElement_HoverElement_Type> nData = new ArrayList();
        if (this.iHireModeID >= 0) {
            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("HireAdvisor") + ": ", CFG.FONT_BOLD));
            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Clear(this.getText(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            AdvisorManager var10003 = Game.advisorManager;
            nData.add(new MenuElement_HoverElement_Type_Text(AdvisorManager.getAdvisorGroupName(this.iAdvisorType), CFG.FONT_REGULAR_SMALL));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Cost") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + AdvisorManager.getRecruitGoldCost(Game.player.iCivID), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("LegacyPoints") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text("" + AdvisorManager.getRecruitCostLegacy(Game.player.iCivID), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.legacy, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            this.menuElementHover = new MenuElement_Hover(nElements);
        } else if (AdvisorManager.getAdvisor(this.iAdvisorType).sName == null) {
            nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("NoAdvisor"), CFG.FONT_BOLD, Colors.HOVER_LEFT));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        } else {
            nData.add(new MenuElement_HoverElement_Type_TextTitle(AdvisorManager.getAdvisor(this.iAdvisorType).sName, CFG.FONT_BOLD, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            AdvisorManager var3 = Game.advisorManager;
            nData.add(new MenuElement_HoverElement_Type_Text(AdvisorManager.getAdvisorGroupName(this.iAdvisorType), CFG.FONT_REGULAR_SMALL));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Line());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("AdvisorSkillLevel") + ": ", "" + AdvisorManager.getAdvisor(this.iAdvisorType).iLevel, Images.skill, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Line());
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Born") + ": ", CFG.FONT_REGULAR_SMALL));
            nData.add(new MenuElement_HoverElement_Type_Text(AdvisorManager.getAdvisor(this.iAdvisorType).iDayOfBirth + " " + Game_Calendar.getMonthName(AdvisorManager.getAdvisor(this.iAdvisorType).iMonthOfBirth) + " " + AdvisorManager.getAdvisor(this.iAdvisorType).iYearOfBirth, CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
            nData.add(new MenuElement_HoverElement_Type_Image(Images.time, CFG.PADDING, 0));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("XYearsOld", Math.min(99, Game_Calendar.currentYear - AdvisorManager.getAdvisor(this.iAdvisorType).iYearOfBirth)), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
            if(AdvisorManager.getAdvisor(this.iAdvisorType).Desc != null && !AdvisorManager.getAdvisor(this.iAdvisorType).Desc.isEmpty()) {
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                //Desc
                nData.add(new MenuElement_HoverElement_Type_Text_Desc(AdvisorManager.getAdvisor(this.iAdvisorType).Desc));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
            if (this.iCivID == Game.player.iCivID) {
                nData.add(new MenuElement_HoverElement_Type_Empty());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("ClickToFireAnAdvisor"), CFG.FONT_BOLD, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            } else {
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        }

    }
}
