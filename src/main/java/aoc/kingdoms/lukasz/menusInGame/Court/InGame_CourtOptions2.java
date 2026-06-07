//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.menusInGame.Court;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.jakowski.SoundsManager;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.menu.Menu;
import aoc.kingdoms.lukasz.menu.MenuManager;
import aoc.kingdoms.lukasz.menu.menuTitle.MenuTitle;
import aoc.kingdoms.lukasz.menu_element.IconCourt;
import aoc.kingdoms.lukasz.menu_element.IconCourt_Notification;
import aoc.kingdoms.lukasz.menu_element.MenuElement;
import aoc.kingdoms.lukasz.menu_element.SpaceHorizontal;
import aoc.kingdoms.lukasz.menu_element.Status;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_Hover;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_FlagTitle;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoc.kingdoms.lukasz.menusInGame.InGame;
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.textures.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import team.rainfall.fontFix.FontFix;

import java.util.ArrayList;
import java.util.List;

public class InGame_CourtOptions2 extends Menu {
    protected static final int ANIMATION_TIME = 60;
    public static int menuH = 0;
    public static int buttonW_Draw = 0;
    public static int idProvinces = -77;
    public static int idCourt = -9;
    public static int idExploitEconomy = -94;
    public static int idCores = -21;
    public static int idReligion = -24;
    public static boolean isOptionHovered = false;
    public static int textMaxWidth = 0;
    public static long TEXT_TIME = 0L;
    public static int TEXT_ANIMATION_TIME = 165;
    public static int HEIGHT = 100;

    public static final int getMenuWidth() {
        return ImageManager.getImage(Images.leftSideBar).getWidth() - InGame.leftSideBarInnerWidth;
    }

    public static final int getOtherMenuPosX() {
        return CFG.PADDING * 2 + getMenuWidth() + Game.settingsManager.IN_GAME_LEFT_PADDING_EXTRA;
    }

    public static final int getOtherMenuPosX_2() {
        return (Game.settingsManager.enableHideSideMenu ? 0 : getMenuWidth()) + CFG.PADDING * 2 + Game.settingsManager.IN_GAME_LEFT_PADDING_EXTRA;
    }

    public int getPosX() {
        return super.getPosX() + Game.settingsManager.IN_GAME_LEFT_PADDING_EXTRA;
    }

    public InGame_CourtOptions2() {
        List<MenuElement> menuElements = new ArrayList();
        int paddingLeft = CFG.PADDING;
        int menuX = 0;
        int menuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY;
        int buttonYPadding = CFG.PADDING * 2;
        int buttonX = 0;
        int buttonY = 0;
        buttonW_Draw = getMenuWidth();
        int buttonW = buttonW_Draw + CFG.PADDING * 2;
        int buttonH = CFG.isDesktop() ? CFG.BUTTON_HEIGHT2 : CFG.BUTTON_HEIGHT;
        int tID = 0;
        menuElements.add(new IconCourt_Notification(Game.lang.get("Missions"), Images.missions, buttonX, buttonY, buttonW, buttonH, tID++, buttonW_Draw) {
            public void actionElement() {
                InGame_CourtOptions2.actionMissions();
            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Missions") + ": " + Game.getCiv(Game.player.iCivID).getCivName(), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.missions, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                if (Game.player.currSituation.missionCanBeUnlockedNum > 0) {
                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Available") + ": ", CFG.FONT_REGULAR));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle("" + Game.player.currSituation.missionCanBeUnlockedNum, CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.missions, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }

                this.menuElementHover = new MenuElement_Hover(nElements, Game.player.currSituation.missionCanBeUnlockedNum == 0);
            }

            public void updateValue() {
                super.updateValue();
                if (this.value != Game.player.currSituation.missionCanBeUnlockedNum) {
                    this.setNumber(Game.player.currSituation.missionCanBeUnlockedNum);
                }

            }

            protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                super.drawButtonBG(oSB, iTranslateX, iTranslateY, isActive);
                if (Game.player.currSituation.missionCanBeUnlocked) {
                    oSB.setColor(new Color(Colors.COLOR_GRADIENT.r, Colors.COLOR_GRADIENT.g, Colors.COLOR_GRADIENT.b, 0.65F));
                    ImageManager.getImage(Images.gradientXYVertical).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.widthDraw * 3 / 4, this.getHeight());
                    oSB.setColor(Color.WHITE);
                }

            }

            public int getSFX() {
                return SoundsManager.getClickSound_CivOptions();
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight();
        menuElements.add(new SpaceHorizontal(buttonX, buttonY, buttonW_Draw));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight();
        InGame_CourtOptions.iGovernmentID = tID;
        menuElements.add(new IconCourt(Game.lang.get("Government"), Images.government, buttonX, buttonY, buttonW, buttonH, tID++, buttonW_Draw) {
            public void actionElement() {
                if (this.id != InGame_CourtOptions.iActiveID) {
                    InGame_CourtOptions.iActiveID = this.id;
                    InGame_CourtOptions2.disableAllViews();
                    Game.menuManager.rebuildInGame_Government();
                    Game.menuManager.setVisibleInGame_Court(true);
                    InGame_Court.lTime = 0L;
                }

            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Government"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.government, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Capital") + ": ", CFG.FONT_REGULAR));
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getCiv(Game.player.iCivID).getCapitalProvinceID() >= 0 ? Game.getProvince(Game.getCiv(Game.player.iCivID).getCapitalProvinceID()).getProvinceName() : Game.lang.get("None"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.capital, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Level") + ": ", CFG.FONT_REGULAR));
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getCiv(Game.player.iCivID).getCapitalLevel() + " / " + Game.getCapital_MaxLvl(Game.player.iCivID), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.capital, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }

            public int getSFX() {
                return SoundsManager.getClickSound_CivOptions();
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight();
        menuElements.add(new SpaceHorizontal(buttonX, buttonY, buttonW_Draw));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight();
        if (GameValues.zoom.SIDEBAR_ZOOM_SCALE_BUTTONS) {
            menuElements.add(new IconCourt(Game.lang.get("Scale") + " +", Images.plus, buttonX, buttonY, buttonW, buttonH, tID++, buttonW_Draw) {
                public void actionElement() {
                    Game.mapScale.scrollScale(-1);
                }

                public void buildElementHover() {
                    List<MenuElement_HoverElement> nElements = new ArrayList();
                    List<MenuElement_HoverElement_Type> nData = new ArrayList();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Scale"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.plus, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements, true);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new SpaceHorizontal(buttonX, buttonY, buttonW_Draw));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new IconCourt(Game.lang.get("Scale") + " -", Images.minus, buttonX, buttonY, buttonW, buttonH, tID++, buttonW_Draw) {
                public void actionElement() {
                    Game.mapScale.scrollScale(1);
                }

                public void buildElementHover() {
                    List<MenuElement_HoverElement> nElements = new ArrayList();
                    List<MenuElement_HoverElement_Type> nData = new ArrayList();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Scale"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.minus, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements, true);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
            menuElements.add(new SpaceHorizontal(buttonX, buttonY, buttonW_Draw));
            buttonY += menuElements.get(menuElements.size() - 1).getHeight();
        }

        InGame_CourtOptions.iLawID = tID;
        menuElements.add(new IconCourt_Notification(Game.lang.get("Laws"), Images.law, buttonX, buttonY, buttonW, buttonH, tID++, buttonW_Draw) {
            public void actionElement() {
                InGame_CourtOptions2.actionLaws(InGame_CourtOptions.iLawID);
            }

            public void updateValue() {
                super.updateValue();
                if (this.value != Game.player.currSituation.newLawAvailableNum) {
                    this.setNumber(Game.player.currSituation.newLawAvailableNum);
                }

            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Laws"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.law, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                if (CFG.isDesktop()) {
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Shortcut") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT));
                    nData.add(new MenuElement_HoverElement_Type_Text("L", CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }

                this.menuElementHover = new MenuElement_Hover(nElements, !CFG.isDesktop());
            }

            public int getSFX() {
                return SoundsManager.getClickSound_CivOptions();
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight();
        menuElements.add(new SpaceHorizontal(buttonX, buttonY, buttonW_Draw));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight();
        InGame_CourtOptions.buildID = tID;
        menuElements.add(new IconCourt(Game.lang.get("Buildings"), Images.build, buttonX, buttonY, buttonW, buttonH, tID++, buttonW_Draw) {
            public void actionElement() {
                InGame_CourtOptions2.actionBuildings(InGame_CourtOptions.buildID);
            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Buildings"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.build, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                float fAverage = 0.0F;

                for(int i = 0; i < Game.getCiv(Game.player.iCivID).getNumOfProvinces(); ++i) {
                    fAverage += (float)Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(i)).iBuildingsSize;
                }

                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Average") + ": ", CFG.FONT_REGULAR));
                nData.add(new MenuElement_HoverElement_Type_TextTitle(CFG.getPrecision2(fAverage / (float) Game.getCiv(Game.player.iCivID).getNumOfProvinces(), 100), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.build, CFG.PADDING, 0));
                nData.add(new MenuElement_HoverElement_Type_FlagTitle(Game.player.iCivID, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                if (CFG.isDesktop()) {
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Shortcut") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT));
                    nData.add(new MenuElement_HoverElement_Type_Text("B", CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }

                this.menuElementHover = new MenuElement_Hover(nElements);
            }

            public int getSFX() {
                return SoundsManager.getClickSound_CivOptions();
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight();
        menuElements.add(new SpaceHorizontal(buttonX, buttonY, buttonW_Draw));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight();
        menuElements.add(new IconCourt(Game.lang.get("TaxEfficiency"), Images.taxUp, buttonX, buttonY, buttonW, buttonH, tID++, buttonW_Draw) {
            public void actionElement() {
                if (this.id != InGame_CourtOptions.iActiveID) {
                    InGame_CourtOptions.iActiveID = this.id;
                    InGame_CourtOptions2.disableAllViews();
                    InGame_Court_IncreaseTaxEfficiency.CLICK_X_TIMES = 1;
                    Game.menuManager.rebuildInGame_IncreaseTaxEfficiency();
                    Game.menuManager.setVisibleInGame_Court(true);
                    InGame_Court.lTime = 0L;
                    if (Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_INCREASE_TAX_EFFICIENCY) {
                        Game.mapModes.setActiveViewID(Game.mapModes.MODE_INCREASE_TAX_EFFICIENCY);
                    }
                }

            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("IncreaseTaxEfficiency"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.taxUp, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                float fAverage = 0.0F;

                for(int i = 0; i < Game.getCiv(Game.player.iCivID).getNumOfProvinces(); ++i) {
                    fAverage += Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(i)).getTaxEfficiencyWithBonuses();
                }

                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Average") + ": ", CFG.FONT_REGULAR));
                nData.add(new MenuElement_HoverElement_Type_TextTitle(CFG.getPrecision2(fAverage / (float)Game.getCiv(Game.player.iCivID).getNumOfProvinces(), 100) + "%", CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.tax, CFG.PADDING, 0));
                nData.add(new MenuElement_HoverElement_Type_FlagTitle(Game.player.iCivID, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }

            public int getSFX() {
                return SoundsManager.getClickSound_CivOptions();
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight();
        menuElements.add(new SpaceHorizontal(buttonX, buttonY, buttonW_Draw));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight();
        menuElements.add(new IconCourt(Game.lang.get("Economy"), Game_Calendar.IMG_ECONOMY_UP, buttonX, buttonY, buttonW, buttonH, tID++, buttonW_Draw) {
            public void actionElement() {
                if (this.id != InGame_CourtOptions.iActiveID) {
                    InGame_CourtOptions.iActiveID = this.id;
                    InGame_CourtOptions2.disableAllViews();
                    InGame_Court_InvestInEconomy.CLICK_X_TIMES = 1;
                    Game.menuManager.rebuildInGame_InvestInEconomy();
                    Game.menuManager.setVisibleInGame_Court(true);
                    InGame_Court.lTime = 0L;
                    if (Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_INVEST_IN_ECONOMY) {
                        Game.mapModes.setActiveViewID(Game.mapModes.MODE_INVEST_IN_ECONOMY);
                    }
                }

            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("InvestInEconomy"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Game_Calendar.IMG_ECONOMY_UP, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                float fAverage = 0.0F;

                for(int i = 0; i < Game.getCiv(Game.player.iCivID).getNumOfProvinces(); ++i) {
                    fAverage += Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(i)).getEconomyWithBonuses();
                }

                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Average") + ": ", CFG.FONT_REGULAR));
                nData.add(new MenuElement_HoverElement_Type_TextTitle(CFG.getPrecision2(fAverage / (float) Game.getCiv(Game.player.iCivID).getNumOfProvinces(), 100), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle(Game_Calendar.IMG_ECONOMY, CFG.PADDING, 0));
                nData.add(new MenuElement_HoverElement_Type_FlagTitle(Game.player.iCivID, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }

            public int getSFX() {
                return SoundsManager.getClickSound_CivOptions();
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight();
        menuElements.add(new SpaceHorizontal(buttonX, buttonY, buttonW_Draw));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight();
        menuElements.add(new IconCourt(Game.lang.get("Infrastructure"), Images.infrastructureUp, buttonX, buttonY, buttonW, buttonH, tID++, buttonW_Draw) {
            public void actionElement() {
                if (this.id != InGame_CourtOptions.iActiveID) {
                    InGame_CourtOptions.iActiveID = this.id;
                    InGame_CourtOptions2.disableAllViews();
                    Game.menuManager.rebuildInGame_DevelopInfrastructure();
                    Game.menuManager.setVisibleInGame_Court(true);
                    InGame_Court.lTime = 0L;
                    if (Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_DEVELOP_INFRASTRUCTURE) {
                        Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEVELOP_INFRASTRUCTURE);
                    }
                }

            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("DevelopInfrastructure"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.infrastructureUp, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                float fAverage = 0.0F;

                for(int i = 0; i < Game.getCiv(Game.player.iCivID).getNumOfProvinces(); ++i) {
                    fAverage += (float)Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(i)).getInfrastructure();
                }

                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Average") + ": ", CFG.FONT_REGULAR));
                nData.add(new MenuElement_HoverElement_Type_TextTitle(CFG.getPrecision2(fAverage / (float) Game.getCiv(Game.player.iCivID).getNumOfProvinces(), 100), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.infrastructure, CFG.PADDING, 0));
                nData.add(new MenuElement_HoverElement_Type_FlagTitle(Game.player.iCivID, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }

            public int getSFX() {
                return SoundsManager.getClickSound_CivOptions();
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight();
        menuElements.add(new SpaceHorizontal(buttonX, buttonY, buttonW_Draw));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight();
        menuElements.add(new IconCourt(Game.lang.get("GrowthRate"), Images.populationUp, buttonX, buttonY, buttonW, buttonH, tID++, buttonW_Draw) {
            public void actionElement() {
                if (this.id != InGame_CourtOptions.iActiveID) {
                    InGame_CourtOptions.iActiveID = this.id;
                    InGame_CourtOptions2.disableAllViews();
                    InGame_Court_IncreaseGrowthRate.CLICK_X_TIMES = 1;
                    Game.menuManager.rebuildInGame_IncreaseGrowthRate();
                    Game.menuManager.setVisibleInGame_Court(true);
                    InGame_Court.lTime = 0L;
                    if (Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_INCREASE_GROWTH_RATE) {
                        Game.mapModes.setActiveViewID(Game.mapModes.MODE_INCREASE_GROWTH_RATE);
                    }
                }

            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("IncreasePopulationGrowthRate"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.populationUp, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                float fAverage = 0.0F;

                for(int i = 0; i < Game.getCiv(Game.player.iCivID).getNumOfProvinces(); ++i) {
                    fAverage += Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(i)).getGrowthRateWithBonuses();
                }

                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Average") + ": ", CFG.FONT_REGULAR));
                nData.add(new MenuElement_HoverElement_Type_TextTitle(CFG.getPrecision2(fAverage / (float)Game.getCiv(Game.player.iCivID).getNumOfProvinces(), 100) + "%", CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.populationGrowth, CFG.PADDING, 0));
                nData.add(new MenuElement_HoverElement_Type_FlagTitle(Game.player.iCivID, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }

            public int getImageID() {
                return Images.populationGrowth;
            }

            public int getSFX() {
                return SoundsManager.getClickSound_CivOptions();
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight();
        menuElements.add(new SpaceHorizontal(buttonX, buttonY, buttonW_Draw));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight();
        menuElements.add(new IconCourt(Game.lang.get("Manpower"), FontFix.manpowerSid > 0 ? FontFix.manpowerSid : Game_Calendar.IMG_MANPOWER, buttonX, buttonY, buttonW, buttonH, tID++, buttonW_Draw) {
            public void actionElement() {
                if (this.id != InGame_CourtOptions.iActiveID) {
                    InGame_CourtOptions.iActiveID = this.id;
                    InGame_CourtOptions2.disableAllViews();
                    InGame_Court_IncreaseManpower.CLICK_X_TIMES = 1;
                    Game.menuManager.rebuildInGame_IncreaseManpower();
                    Game.menuManager.setVisibleInGame_Court(true);
                    InGame_Court.lTime = 0L;
                    if (Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_INCREASE_MANPOWER) {
                        Game.mapModes.setActiveViewID(Game.mapModes.MODE_INCREASE_MANPOWER);
                    }
                }

            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("IncreaseManpower"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Game_Calendar.IMG_MANPOWER, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                float fAverage = 0.0F;

                for(int i = 0; i < Game.getCiv(Game.player.iCivID).getNumOfProvinces(); ++i) {
                    fAverage += Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(i)).getManpower();
                }

                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Average") + ": ", CFG.FONT_REGULAR));
                nData.add(new MenuElement_HoverElement_Type_TextTitle(CFG.getPrecision2(fAverage / (float) Game.getCiv(Game.player.iCivID).getNumOfProvinces(), 100), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle(Game_Calendar.IMG_MANPOWER, CFG.PADDING, 0));
                nData.add(new MenuElement_HoverElement_Type_FlagTitle(Game.player.iCivID, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }

            public int getImageID() {
                return FontFix.manpowerSid > 0 ? FontFix.manpowerSid : Game_Calendar.IMG_MANPOWER;
            }

            public int getSFX() {
                return SoundsManager.getClickSound_CivOptions();
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight();
        menuElements.add(new SpaceHorizontal(buttonX, buttonY, buttonW_Draw));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight();
        idCores = tID;
        menuElements.add(new IconCourt_Notification(Game.lang.get("Cores"), Images.core, buttonX, buttonY, buttonW, buttonH, tID++, buttonW_Draw) {
            public void actionElement() {
                InGame_CourtOptions2.actionCores(this.id);
            }

            public void updateValue() {
                super.updateValue();
                if (this.value != Game.player.currSituation.nonCoreProvincesNum) {
                    this.setNumber(Game.player.currSituation.nonCoreProvincesNum);
                }

            }

            protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                super.drawButtonBG(oSB, iTranslateX, iTranslateY, isActive);
                if (Game.player.currSituation.nonCoreProvinces) {
                    oSB.setColor(new Color(Colors.COLOR_GRADIENT.r, Colors.COLOR_GRADIENT.g, Colors.COLOR_GRADIENT.b, 0.65F));
                    ImageManager.getImage(Images.gradientXYVertical).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.widthDraw * 3 / 4, this.getHeight());
                    oSB.setColor(Color.WHITE);
                }

            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("CoreConstruction"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.core, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                int tNum = 0;

                for(int i = 0; i < Game.getCiv(Game.player.iCivID).getNumOfProvinces(); ++i) {
                    if (Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(i)).haveACore(Game.player.iCivID)) {
                        ++tNum;
                    }
                }

                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Cores") + ": ", CFG.FONT_REGULAR));
                nData.add(new MenuElement_HoverElement_Type_TextTitle(tNum + " / " + Game.getCiv(Game.player.iCivID).getNumOfProvinces(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.core, CFG.PADDING, 0));
                nData.add(new MenuElement_HoverElement_Type_FlagTitle(Game.player.iCivID, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }

            public int getSFX() {
                return SoundsManager.getClickSound_CivOptions();
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight();
        menuElements.add(new SpaceHorizontal(buttonX, buttonY, buttonW_Draw));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight();
        idReligion = tID;
        menuElements.add(new IconCourt_Notification(Game.lang.get("Religion"), Images.religion, buttonX, buttonY, buttonW, buttonH, tID++, buttonW_Draw) {
            public void actionElement() {
                InGame_CourtOptions2.actionReligion(this.id);
            }

            public void updateValue() {
                super.updateValue();
                if (this.value != Game.player.currSituation.differentReligionProvincesNum) {
                    this.setNumber(Game.player.currSituation.differentReligionProvincesNum);
                }

            }

            protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                super.drawButtonBG(oSB, iTranslateX, iTranslateY, isActive);
                if (Game.player.currSituation.differentReligionProvinces) {
                    oSB.setColor(new Color(Colors.COLOR_GRADIENT.r, Colors.COLOR_GRADIENT.g, Colors.COLOR_GRADIENT.b, 0.65F));
                    ImageManager.getImage(Images.gradientXYVertical).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.widthDraw * 3 / 4, this.getHeight());
                    oSB.setColor(Color.WHITE);
                }

            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Religion"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.religion, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                int tNum = 0;

                for(int i = 0; i < Game.getCiv(Game.player.iCivID).getNumOfProvinces(); ++i) {
                    if (Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(i)).getReligion() == Game.getCiv(Game.player.iCivID).getReligionID()) {
                        ++tNum;
                    }
                }

                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.religionManager.getReligion(Game.getCiv(Game.player.iCivID).getReligionID()).Name + ": ", CFG.FONT_REGULAR));
                nData.add(new MenuElement_HoverElement_Type_TextTitle(tNum + " / " + Game.getCiv(Game.player.iCivID).getNumOfProvinces(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.religion, CFG.PADDING, 0));
                nData.add(new MenuElement_HoverElement_Type_FlagTitle(Game.player.iCivID, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }

            public int getSFX() {
                return SoundsManager.getClickSound_CivOptions();
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight();
        menuElements.add(new SpaceHorizontal(buttonX, buttonY, buttonW_Draw));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight();
        menuElements.add(new IconCourt(Game.lang.get("Sandbox"), Images.sandbox, buttonX, buttonY, buttonW, buttonH, tID++, buttonW_Draw) {
            public void actionElement() {
                if (Game.menuManager.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == 42) {
                    Game.menuManager.setVisibleInGame_PopUp(false);
                } else {
                    Game.menuManager.rebuildInGame_Sandbox();
                }

            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Sandbox"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.sandbox, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }

            public int getSFX() {
                return SoundsManager.getClickSound_CivOptions();
            }

            public boolean getVisible() {
                return Game.SANDBOX;
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight();
        menuElements.add(new SpaceHorizontal(buttonX, buttonY, buttonW_Draw) {
            public boolean getVisible() {
                return Game.SANDBOX;
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight();
        textMaxWidth = 0;

        for(int i = menuElements.size() - 1; i >= 0; --i) {
            if (menuElements.get(i).getTextWidth() > textMaxWidth) {
                textMaxWidth = menuElements.get(i).getTextWidth();
            }
        }

        textMaxWidth += CFG.PADDING * 6;
        int menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - CFG.PADDING * 3);
        this.initMenu(null, menuX, menuY, buttonW + textMaxWidth + CFG.PADDING * 2, menuHeight, menuElements, !Game.settingsManager.enableHideSideMenu, false);
        this.drawScrollPositionAlways = false;
        this.drawScrollPositionAlways2 = false;
        HEIGHT = 0;

        for(int i = 0; i < this.getMenuElementsSize(); ++i) {
            if (this.getMenuElement(i).getVisible() && this.getMenuElement(i).getPosY() + this.getHeight() > HEIGHT) {
                HEIGHT = this.getMenuElement(i).getPosY() + this.getMenuElement(i).getHeight();
            }
        }

        HEIGHT += this.getPosY() + InGame.leftSideBarPadding;
    }

    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean menuIsActive, Status titleStatus) {
        isOptionHovered = false;

        for(int i = 0; i < this.getMenuElementsSize(); ++i) {
            if (this.getMenuElement(i).getIsHovered()) {
                isOptionHovered = true;
                if (!MenuManager.orderOfMenuInGame && !Game.menuManager.getVisibleInGame_TechnologyTree()) {
                    Game.addSimpleTask_First(new Game.SimpleTask("setOrderOfMenu_InGame") {
                        public void update() {
                            Game.menuManager.setOrderOfMenu_InGame();
                        }
                    });
                }
                break;
            }
        }

        if (!isOptionHovered) {
            TEXT_TIME = CFG.currentTimeMillis;
        }

        if (Game.settingsManager.enableHideSideMenu && InGame_Court.lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateX = iTranslateX - CFG.BUTTON_WIDTH + (int)((float)CFG.BUTTON_WIDTH * ((float)(CFG.currentTimeMillis - InGame_Court.lTime) / 60.0F));
        }

        oSB.setColor(Color.WHITE);
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }

    public void onHovered() {
        super.onHovered();
        Game.menuManager.setOrderOfMenu_InGameCourt();
    }

    public void actionCloseMenu() {
        super.actionCloseMenu();
        Game.menuManager.setVisibleInGame_Court(false);
    }

    public static void disableAllViews() {
        if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_INVEST_IN_ECONOMY) {
            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
        } else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DEVELOP_INFRASTRUCTURE) {
            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
        } else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_INCREASE_TAX_EFFICIENCY) {
            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
        } else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_INCREASE_MANPOWER) {
            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
        } else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_MOVE_CAPITAL) {
            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
        } else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_INCREASE_GROWTH_RATE) {
            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
        } else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_CONVERT_RELIGION) {
            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
        } else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_CORE) {
            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
        } else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_BUILDING) {
            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
        }

        InGame_Court_Buildings2.oBuildingID = null;
    }

    public void actionElement(int nMenuElementID) {
        if (InGame_Court.iActiveCivID != Game.player.iCivID) {
            InGame_Court.iActiveCivID = Game.player.iCivID;
        } else if (Game.menuManager.getVisibleInGame_Court() && this.getMenuElement(nMenuElementID).getCurrent() == InGame_CourtOptions.iActiveID) {
            Game.menuManager.setVisibleInGame_Court(false);
            return;
        }

        super.actionElement(nMenuElementID);
    }

    public static final void actionCourt(int id) {
        if (InGame_Court.iActiveCivID != Game.player.iCivID) {
            InGame_Court.iActiveCivID = Game.player.iCivID;
            InGame_CourtOptions.iActiveID = id;
            disableAllViews();
            Game.menuManager.rebuildInGame_Court();
            Game.menuManager.setVisibleInGame_Court(true);
            InGame_Court.lTime = 0L;
        } else if (id != InGame_CourtOptions.iActiveID || !InGame_Court.inCourt) {
            InGame_CourtOptions.iActiveID = id;
            disableAllViews();
            Game.menuManager.rebuildInGame_Court();
            Game.menuManager.setVisibleInGame_Court(true);
            InGame_Court.lTime = 0L;
        }

    }

    public static final void actionProvinces(int id) {
        if (id != InGame_CourtOptions.iActiveID) {
            InGame_CourtOptions.iActiveID = id;
            disableAllViews();
            InGame_Court_Provinces.sSearch = "";
            Game.menuManager.rebuildInGame_CourtProvinces();
            Game.menuManager.setVisibleInGame_Court(true);
            InGame_Court.lTime = 0L;
        }

    }

    public static final void actionBuildings(int id) {
        if (id != InGame_CourtOptions.iActiveID) {
            InGame_CourtOptions.iActiveID = id;
            disableAllViews();
            Game.menuManager.rebuildInGame_Buildings2();
            Game.menuManager.setVisibleInGame_Court(true);
            InGame_Court.lTime = 0L;
        } else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_BUILDING) {
            InGame_CourtOptions.iActiveID = id;
            disableAllViews();
            Game.menuManager.rebuildInGame_Buildings2();
            Game.menuManager.setVisibleInGame_Court(true);
            InGame_Court.lTime = 0L;
        }

    }

    public static final void actionLaws(int id) {
        if (id != InGame_CourtOptions.iActiveID) {
            InGame_CourtOptions.iActiveID = id;
            disableAllViews();
            Game.menuManager.rebuildInGame_LawsCourt();
            Game.menuManager.setVisibleInGame_Court(true);
            InGame_Court.lTime = 0L;
        }

    }

    public static final void actionCores(int id) {
        if (id != InGame_CourtOptions.iActiveID) {
            InGame_CourtOptions.iActiveID = id;
            disableAllViews();
            Game.menuManager.rebuildInGame_Core();
            Game.menuManager.setVisibleInGame_Court(true);
            InGame_Court.lTime = 0L;
            if (Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_CORE) {
                Game.mapModes.setActiveViewID(Game.mapModes.MODE_CORE);
            }
        }

    }

    public static final void actionReligion(int id) {
        if (id != InGame_CourtOptions.iActiveID) {
            InGame_CourtOptions.iActiveID = id;
            disableAllViews();
            Game.menuManager.rebuildInGame_Religion();
            Game.menuManager.setVisibleInGame_Court(true);
            InGame_Court.lTime = 0L;
            if (Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_CONVERT_RELIGION) {
                Game.mapModes.setActiveViewID(Game.mapModes.MODE_CONVERT_RELIGION);
            }
        }

    }

    public static void actionMissions() {
//        if (Game.menuManager.getVisibleInGame_TechnologyTree()) {
//            Game.menuManager.setVisibleInGame_TechnologyTree(false);
//        } else {
//            Game.menuManager.rebuildInGame_MissionTree(false, true);
//            Game.addSimpleTask(new Game.SimpleTask("setOrderOfMenu_TechnologyTree") {
//                public void update() {
//                    Game.menuManager.setOrderOfMenu_TechnologyTree();
//                }
//            });
//        }
        Game.menuManager.rebuildInGame_CourtMissions();
        Game.menuManager.setVisibleInGame_Court(true);

    }
}
