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
import aoc.kingdoms.lukasz.jakowski.Renderer.SparksAnimation;
import aoc.kingdoms.lukasz.map.RulersManager;
import aoc.kingdoms.lukasz.map.civilization.CivilizationRanking;
import aoc.kingdoms.lukasz.map.diplomacy.DiplomacyManager;
import aoc.kingdoms.lukasz.map.province.ProvinceBorderManager;
import aoc.kingdoms.lukasz.map.war.War;
import aoc.kingdoms.lukasz.map.war.WarCivilization;
import aoc.kingdoms.lukasz.map.war.WarManager;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.menu.Menu;
import aoc.kingdoms.lukasz.menu.MenuManager;
import aoc.kingdoms.lukasz.menu.menuTitle.MenuTitleIMG_War;
import aoc.kingdoms.lukasz.menu_element.Empty;
import aoc.kingdoms.lukasz.menu_element.MenuElement;
import aoc.kingdoms.lukasz.menu_element.Status;
import aoc.kingdoms.lukasz.menu_element.button.ButtonFlag_Diplomacy;
import aoc.kingdoms.lukasz.menu_element.button.ButtonGame_Image;
import aoc.kingdoms.lukasz.menu_element.button.ButtonRuler_Diplomacy;
import aoc.kingdoms.lukasz.menu_element.button.ButtonStatsRectIMG_Diplomacy;
import aoc.kingdoms.lukasz.menu_element.button.ButtonStatsRectIMG_Diplomacy_Flip;
import aoc.kingdoms.lukasz.menu_element.button.ButtonStats_WarScore;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_Hover;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonusFlag;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_FlagTitle;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text_Desc;
import aoc.kingdoms.lukasz.menu_element.textStatic.TextIcon_Diplomacy;
import aoc.kingdoms.lukasz.menu_element.textStatic.Text_StaticBG;
import aoc.kingdoms.lukasz.menu_element.textStatic.Text_StaticBG_ID_FlagCiv;
import aoc.kingdoms.lukasz.menu_element.textStatic.Text_StaticBG_ID_Image;
import aoc.kingdoms.lukasz.menu_element.textStatic.Text_Title_v2Center;
import aoc.kingdoms.lukasz.menusInGame.Diplomacy.InGame_CallAllies;
import aoc.kingdoms.lukasz.menusInGame.Diplomacy.InGame_Intervene;
import aoc.kingdoms.lukasz.menusInGame.Info.InGame_Info;
import aoc.kingdoms.lukasz.textures.Image;
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.textures.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import team.rainfall.fontFix.Config;

import java.util.ArrayList;
import java.util.List;

public class InGame_War extends Menu {
    public static SparksAnimation sparksAnimation = new SparksAnimation();
    public static String key;
    public static final int ANIMATION_TIME = 60;
    public static long lTime = 0L;
    public int imageOverID = 0;
    public boolean whitePeaceConfirm = false;
    public boolean surrenderConfirm = false;

    public InGame_War() {
        List<MenuElement> menuElements = new ArrayList();
        int paddingLeft = Images.boxTitleBORDERWIDTH + CFG.PADDING;
        int titleHeight = ImageManager.getImage(Images.title580).getHeight();
        int menuWidth = ImageManager.getImage(Images.title580).getWidth() - Images.boxTitleBORDERWIDTH;
        int menuMinHeight = 50;
        int menuHeight = 50;
        int menuX = 0;
        int var10000 = CFG.GAME_HEIGHT - menuHeight;
        int buttonY = CFG.PADDING;
        int buttonX = paddingLeft;
        String tTitle = WarManager.getWarName(key);

        try {
            if (WarManager.lWars.containsKey(key)) {
                int iCivLeft = ((WarCivilization)((War)WarManager.lWars.get(key)).lAggressors.get(0)).iCivID;
                int iCivRight = ((WarCivilization)((War)WarManager.lWars.get(key)).lDefenders.get(0)).iCivID;
                int maxWidth = ImageManager.getImage(Images.warBig).getWidth() + CFG.PADDING * 4;
                int tempTitlePaddingY = CFG.PADDING;
                int tempTitleH = ImageManager.getImage(Images.flagDiplomacyOver).getHeight() + tempTitlePaddingY * 2;
                int statsX = paddingLeft + ButtonRuler_Diplomacy.getButtonWidth() + CFG.PADDING;
                int statsW = menuWidth / 2 - statsX - CFG.PADDING / 2;
                int statsH = (ButtonRuler_Diplomacy.getButtonHeight() - tempTitleH - CFG.PADDING * 3) / 3;

                try {
                    if (((War)WarManager.lWars.get(key)).isCoalition) {
                        tTitle = Game.lang.get("Coalition");
                    }
                } catch (Exception var30) {
                }

                menuElements.add(new ButtonFlag_Diplomacy(iCivLeft, menuWidth / 2 - maxWidth / 2 - CFG.PADDING * 2 - ImageManager.getImage(Images.flagDiplomacyOver).getWidth(), buttonY + tempTitlePaddingY, true));
                menuElements.add(new ButtonFlag_Diplomacy(iCivRight, menuWidth / 2 + maxWidth / 2 + CFG.PADDING * 2, buttonY + tempTitlePaddingY, true));
                menuElements.add(new TextIcon_Diplomacy(Images.warBig, statsX, buttonY, statsW * 2 + CFG.PADDING, tempTitleH, maxWidth) {
                    public Color getColorBar() {
                        return DiplomacyManager.COLOR_RED;
                    }
                });
                buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                int maxIconW = ImageManager.getImage(Game_Calendar.IMG_MANPOWER).getWidth() + CFG.PADDING * 2;
                menuElements.add(new ButtonStats_WarScore(Game.lang.get("WarScore") + ": ", statsX, buttonY, statsW * 2 + CFG.PADDING, statsH, iCivLeft, iCivRight, key) {
                    public void buildElementHover() {
                        List<MenuElement_HoverElement> nElements = new ArrayList();
                        List<MenuElement_HoverElement_Type> nData = new ArrayList();
                        float lastValue2 = (float)((War)WarManager.lWars.get(InGame_War.key)).getWarScore_Side(this.getCurrent());
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("WarScore") + ": ", CFG.FONT_BOLD));
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(CFG.getPrecision2(Math.min(Math.max(((War)WarManager.lWars.get(this.key)).warScore * lastValue2, -100.0F), 100.0F), 1) + "%", CFG.FONT_BOLD, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.victoryPoints, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        if (Math.abs(((War)WarManager.lWars.get(this.key)).warScore) >= 1.0F) {
                            nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("InFavorOf") + ": ", CFG.FONT_REGULAR));
                            nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getCiv(((War)WarManager.lWars.get(this.key)).warScore > 0.0F ? ((WarCivilization)((War)WarManager.lWars.get(this.key)).lAggressors.get(0)).iCivID : ((WarCivilization)((War)WarManager.lWars.get(this.key)).lDefenders.get(0)).iCivID).getCivName(), CFG.FONT_BOLD, Colors.HOVER_POSITIVE));
                            nData.add(new MenuElement_HoverElement_Type_FlagTitle(((War)WarManager.lWars.get(this.key)).warScore > 0.0F ? ((WarCivilization)((War)WarManager.lWars.get(this.key)).lAggressors.get(0)).iCivID : ((WarCivilization)((War)WarManager.lWars.get(this.key)).lDefenders.get(0)).iCivID, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                        }

                        nData.add(new MenuElement_HoverElement_Type_Line());
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("OccupiedProvinces") + ": ", CFG.FONT_REGULAR_SMALL));
                        nData.add(new MenuElement_HoverElement_Type_Text(CFG.getPrecision2(((War)WarManager.lWars.get(this.key)).warScoreFromOccupiedProvinces * lastValue2, 10) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
                        nData.add(new MenuElement_HoverElement_Type_Image(Images.victoryPoints, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("WarScoreFromBattles") + ": ", CFG.FONT_REGULAR_SMALL));
                        nData.add(new MenuElement_HoverElement_Type_Text(CFG.getPrecision2(((War)WarManager.lWars.get(this.key)).warScoreFromBattles * lastValue2, 10) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
                        nData.add(new MenuElement_HoverElement_Type_Image(Images.victoryPoints, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("TickingWarScore") + ": ", CFG.FONT_REGULAR_SMALL));
                        nData.add(new MenuElement_HoverElement_Type_Text(CFG.getPrecision2(((War)WarManager.lWars.get(this.key)).tickingWarScore * lastValue2, 10) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
                        nData.add(new MenuElement_HoverElement_Type_Image(Images.victoryPoints, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Line());
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("EachMonthTheWinningSideGainsXOfTheTickingWarScoreBasedOnTheCurrentOverallWarScoreFromBattlesAndOccupiedProvinces", CFG.getPrecision2(GameValues.war.TICKING_WAR_SCORE_EACH_MONTH * 100.0F, 100)), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }
                });
                menuElements.add(new ButtonStatsRectIMG_Diplomacy("" + CFG.getShortNumber(((War)WarManager.lWars.get(key)).getCasualties_Aggressors()), Images.skull, statsX, buttonY + CFG.PADDING + statsH, statsW, statsH, maxIconW, 0) {
                    public void buildElementHover() {
                        List<MenuElement_HoverElement> nElements = new ArrayList();
                        List<MenuElement_HoverElement_Type> nData = new ArrayList();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Casualties") + ": ", CFG.FONT_BOLD));
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(CFG.getNumberWithSpaces("" + ((War)WarManager.lWars.get(InGame_War.key)).getCasualties_Aggressors()), CFG.FONT_BOLD, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.skull, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }
                });
                menuElements.add(new ButtonStatsRectIMG_Diplomacy("" + CFG.getPrecision2(Game.getCiv(iCivLeft).getWarWeariness(), 10) + "%", Images.weariness, statsX, buttonY + CFG.PADDING * 2 + statsH * 2, statsW, statsH, maxIconW, iCivLeft) {
                    public void buildElementHover() {
                        List<MenuElement_HoverElement> nElements = new ArrayList();
                        List<MenuElement_HoverElement_Type> nData = new ArrayList();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("WarWeariness") + ": ", CFG.FONT_BOLD));
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(CFG.getPrecision2(Game.getCiv(this.id).getWarWeariness(), 10) + "%", CFG.FONT_BOLD, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.weariness, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }
                });
                menuElements.add(new ButtonStatsRectIMG_Diplomacy_Flip("" + CFG.getShortNumber(((War)WarManager.lWars.get(key)).getCasualties_Defenders()), Images.skull, statsX + CFG.PADDING + statsW, buttonY + CFG.PADDING + statsH, statsW, statsH, maxIconW, 0) {
                    public void buildElementHover() {
                        List<MenuElement_HoverElement> nElements = new ArrayList();
                        List<MenuElement_HoverElement_Type> nData = new ArrayList();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Casualties") + ": ", CFG.FONT_BOLD));
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(CFG.getNumberWithSpaces("" + ((War)WarManager.lWars.get(InGame_War.key)).getCasualties_Defenders()), CFG.FONT_BOLD, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.skull, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }
                });
                menuElements.add(new ButtonStatsRectIMG_Diplomacy_Flip("" + CFG.getPrecision2(Game.getCiv(iCivRight).getWarWeariness(), 10) + "%", Images.weariness, statsX + CFG.PADDING + statsW, buttonY + CFG.PADDING * 2 + statsH * 2, statsW, statsH, maxIconW, iCivRight) {
                    public void buildElementHover() {
                        List<MenuElement_HoverElement> nElements = new ArrayList();
                        List<MenuElement_HoverElement_Type> nData = new ArrayList();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("WarWeariness") + ": ", CFG.FONT_BOLD));
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(CFG.getPrecision2(Game.getCiv(this.id).getWarWeariness(), 10) + "%", CFG.FONT_BOLD, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.weariness, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }
                });
                buttonY = CFG.PADDING;
                RulersManager.loadRulerIMG_DiplomacyLeft(iCivLeft);
                RulersManager.loadRulerIMG_DiplomacyRight(iCivRight);
                menuElements.add(new ButtonRuler_Diplomacy(iCivLeft, buttonX, buttonY));
                menuElements.add(new ButtonRuler_Diplomacy(iCivRight, menuWidth - paddingLeft - ButtonRuler_Diplomacy.getButtonWidth(), buttonY) {
                    public Image getRulerImage() {
                        return RulersManager.rulerIMG_DiplomacyRight;
                    }
                });
                buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;

                try {
                    if (((WarCivilization)((War)WarManager.lWars.get(key)).lAggressors.get(0)).iCivID == Game.player.iCivID || ((WarCivilization)((War)WarManager.lWars.get(key)).lDefenders.get(0)).iCivID == Game.player.iCivID) {
                        menuElements.add(new ButtonGame_Image(Game.lang.get("Surrender"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING * 2) / 3, CFG.BUTTON_HEIGHT4, true, Images.warSurrender) {
                            public void actionElement() {
                                if (!InGame_War.this.surrenderConfirm) {
                                    InGame_War.this.surrenderConfirm = true;
                                    this.setText(Game.lang.get("Confirm"));
                                } else {
                                    int tCivID = ((WarCivilization)((War)WarManager.lWars.get(InGame_War.key)).lAggressors.get(0)).iCivID;
                                    int tCivID2 = ((WarCivilization)((War)WarManager.lWars.get(InGame_War.key)).lDefenders.get(0)).iCivID;
                                    if ((tCivID == Game.player.iCivID || tCivID2 == Game.player.iCivID) && DiplomacyManager.surrenderWar(InGame_War.key, Game.player.iCivID)) {
                                        Game.menuManager.setVisibleInGame_War(false);
                                        Game.menuManager.rebuildInGame_Wars();
                                    }
                                }

                            }

                            public boolean getIsHovered() {
                                return super.getIsHovered() || InGame_War.this.surrenderConfirm;
                            }

                            public void buildElementHover() {
                                List<MenuElement_HoverElement> nElements = new ArrayList();
                                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Surrender"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.warSurrender, CFG.PADDING, 0));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                                this.menuElementHover = new MenuElement_Hover(nElements, true);
                            }
                        });
                        menuElements.add(new ButtonGame_Image(Game.lang.get("WhitePeace"), CFG.FONT_REGULAR, -1, paddingLeft + CFG.PADDING + (menuWidth - paddingLeft * 2 - CFG.PADDING * 2) / 3, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING * 2) / 3, CFG.BUTTON_HEIGHT4, true, Images.warPeace) {
                            public void actionElement() {
                                float tWarScore = (float)((int)((War)WarManager.lWars.get(InGame_War.key)).warScore);
                                if (!((War)WarManager.lWars.get(InGame_War.key)).isDefender(Game.player.iCivID) && !((War)WarManager.lWars.get(InGame_War.key)).isAggressor(Game.player.iCivID)) {
                                    Game.menuManager.addToast_Error(Game.lang.get("Error"));
                                } else {
                                    tWarScore = tWarScore * (float)((War)WarManager.lWars.get(InGame_War.key)).getWarScore_Side(Game.player.iCivID) * -1.0F;
                                    if (tWarScore < GameValues.peace.WAR_WHITE_PEACE_MIN_WAR_SCORE) {
                                        Game.menuManager.addToast_Error(Game.lang.get("WithAWarScoreOfXTheWinningSideCanProposeAWhitePeace", CFG.getPrecision2(GameValues.peace.WAR_WHITE_PEACE_MIN_WAR_SCORE, 100) + "%"), Images.warPeace);
                                    } else {
                                        if (!InGame_War.this.whitePeaceConfirm) {
                                            InGame_War.this.whitePeaceConfirm = true;
                                            this.setText(Game.lang.get("Confirm"));
                                        } else {
                                            int tCivID = ((WarCivilization)((War)WarManager.lWars.get(InGame_War.key)).lAggressors.get(0)).iCivID;
                                            int tCivID2 = ((WarCivilization)((War)WarManager.lWars.get(InGame_War.key)).lDefenders.get(0)).iCivID;
                                            if (DiplomacyManager.whitePeace(InGame_War.key)) {
                                                Game.menuManager.setVisibleInGame_War(false);
                                                Game.menuManager.rebuildInGame_Wars();
                                                InGame_Info.iCivID = tCivID;
                                                InGame_Info.iCivID2 = tCivID2;
                                                Game.menuManager.rebuildInGame_Info(Game.lang.get("WhitePeace"), Game_Calendar.getCurrentDate());
                                                InGame_Info.imgID = Images.infoDiplomacy;
                                            }
                                        }

                                    }
                                }
                            }

                            public boolean getIsHovered() {
                                return super.getIsHovered() || InGame_War.this.whitePeaceConfirm;
                            }

                            public void buildElementHover() {
                                List<MenuElement_HoverElement> nElements = new ArrayList();
                                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("WhitePeace"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.warPeace, CFG.PADDING, 0));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                                nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("WithAWarScoreOfXTheWinningSideCanProposeAWhitePeace", CFG.getPrecision2(GameValues.peace.WAR_WHITE_PEACE_MIN_WAR_SCORE, 100) + "%"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                                this.menuElementHover = new MenuElement_Hover(nElements);
                            }
                        });
                        menuElements.add(new ButtonGame_Image(Game.lang.get("MakeDemands"), CFG.FONT_REGULAR, -1, paddingLeft + CFG.PADDING * 2 + (menuWidth - paddingLeft * 2 - CFG.PADDING * 2) / 3 * 2, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING * 2) / 3, CFG.BUTTON_HEIGHT4, true, Images.warDemands) {
                            public void actionElement() {
                                float tWarScore = (float)((int)((War)WarManager.lWars.get(InGame_War.key)).warScore);
                                if (!((War)WarManager.lWars.get(InGame_War.key)).isDefender(Game.player.iCivID) && !((War)WarManager.lWars.get(InGame_War.key)).isAggressor(Game.player.iCivID)) {
                                    Game.menuManager.addToast_Error(Game.lang.get("Error"));
                                } else {
                                    tWarScore = tWarScore * (float)((War)WarManager.lWars.get(InGame_War.key)).getWarScore_Side(Game.player.iCivID) * -1.0F;
                                    if (tWarScore < GameValues.peace.WAR_MAKE_DEMANDS_MIN_WAR_SCORE) {
                                        Game.menuManager.addToast_Error(Game.lang.get("WithAWarScoreOfXTheWinningSideCanMakeDemands", CFG.getPrecision2(GameValues.peace.WAR_MAKE_DEMANDS_MIN_WAR_SCORE, 100) + "%"), Images.warDemands);
                                    } else {
                                        Game.player.initPeaceTreaty_Player(InGame_War.key);
                                        Game.menuManager.setVisibleInGame_War(false);
                                        Game.menuManager.rebuildInGame_Peace();
                                        if (Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_PEACE_VIEW) {
                                            Game.mapModes.setActiveViewID(Game.mapModes.MODE_PEACE_VIEW);
                                        }

                                    }
                                }
                            }

                            public void buildElementHover() {
                                List<MenuElement_HoverElement> nElements = new ArrayList();
                                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("MakeDemands"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.warDemands, CFG.PADDING, 0));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                                nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("WithAWarScoreOfXTheWinningSideCanMakeDemands", CFG.getPrecision2(GameValues.peace.WAR_MAKE_DEMANDS_MIN_WAR_SCORE, 100) + "%"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                                this.menuElementHover = new MenuElement_Hover(nElements);
                            }
                        });
                        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;

                        try {
                            if (((War)WarManager.lWars.get(key)).isCoalition) {
                                tTitle = Game.lang.get("Coalition");
                            }
                        } catch (Exception var29) {
                        }
                    }
                } catch (Exception ex) {
                    CFG.exceptionStack(ex);
                }

                List<Integer> alliesCall = new ArrayList();
                List<Integer> alliesCall_Right = new ArrayList();
                if (((WarCivilization)((War)WarManager.lWars.get(key)).lAggressors.get(0)).iCivID == Game.player.iCivID) {
                    alliesCall = DiplomacyManager.declareWar_AlliesAttacker(Game.player.iCivID, ((WarCivilization)((War)WarManager.lWars.get(key)).lDefenders.get(0)).iCivID);

                    for(int i = alliesCall.size() - 1; i >= 0; --i) {
                        for(int j = ((War)WarManager.lWars.get(key)).lAggressors.size() - 1; j >= 0; --j) {
                            if (((WarCivilization)((War)WarManager.lWars.get(key)).lAggressors.get(j)).iCivID == (Integer)alliesCall.get(i)) {
                                alliesCall.remove(i);
                                break;
                            }
                        }
                    }
                } else if (((WarCivilization)((War)WarManager.lWars.get(key)).lDefenders.get(0)).iCivID == Game.player.iCivID) {
                    alliesCall_Right = DiplomacyManager.declareWar_AlliesDefender(((WarCivilization)((War)WarManager.lWars.get(key)).lAggressors.get(0)).iCivID, Game.player.iCivID);

                    for(int i = alliesCall_Right.size() - 1; i >= 0; --i) {
                        for(int j = ((War)WarManager.lWars.get(key)).lDefenders.size() - 1; j >= 0; --j) {
                            if (((WarCivilization)((War)WarManager.lWars.get(key)).lDefenders.get(j)).iCivID == (Integer)alliesCall_Right.get(i)) {
                                alliesCall_Right.remove(i);
                                break;
                            }
                        }
                    }
                }

                if (!((War)WarManager.lWars.get(key)).isInThisWar(Game.player.iCivID)) {
                    int alliesWidth = (menuWidth - paddingLeft * 2 - CFG.PADDING) / 2;
                    int buttonH = CFG.BUTTON_HEIGHT3;
                    int maxIconW_Intervene = ImageManager.getImage(Images.intervene).getWidth() + CFG.PADDING * 3;
                    menuElements.add(new Text_StaticBG_ID_Image(Game.lang.get("InterveneInWar"), CFG.FONT_REGULAR_SMALL, maxIconW_Intervene, paddingLeft, buttonY, alliesWidth, buttonH, ((WarCivilization)((War)WarManager.lWars.get(key)).lAggressors.get(0)).iCivID, Images.intervene) {
                        public void actionElement() {
                            if (Game.menuManager.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == 48 && InGame_Intervene.warKey.equals(InGame_War.key)) {
                                Game.menuManager.setVisibleInGame_PopUp(false);
                            } else {
                                InGame_Intervene.warKey = InGame_War.key;
                                Game.menuManager.rebuildInGame_Intervene(((WarCivilization)((War)WarManager.lWars.get(InGame_War.key)).lAggressors.get(0)).iCivID, ((WarCivilization)((War)WarManager.lWars.get(InGame_War.key)).lDefenders.get(0)).iCivID);
                            }

                        }

                        public void buildElementHover() {
                            List<MenuElement_HoverElement> nElements = new ArrayList();
                            List<MenuElement_HoverElement_Type> nData = new ArrayList();
                            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("InterveneInWar"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.intervene, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();

                            try {
                                nData.add(new MenuElement_HoverElement_Type_Button_TextBonusFlag(Game.lang.get("JoinAWarAgainst") + ": ", Game.getCiv(((WarCivilization)((War)WarManager.lWars.get(InGame_War.key)).lDefenders.get(0)).iCivID).getCivName(), ((WarCivilization)((War)WarManager.lWars.get(InGame_War.key)).lDefenders.get(0)).iCivID, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                            } catch (Exception var4) {
                            }

                            this.menuElementHover = new MenuElement_Hover(nElements);
                        }
                    });
                    menuElements.add(new Text_StaticBG_ID_Image(Game.lang.get("InterveneInWar"), CFG.FONT_REGULAR_SMALL, maxIconW_Intervene, paddingLeft + CFG.PADDING + alliesWidth, buttonY, alliesWidth, buttonH, ((WarCivilization)((War)WarManager.lWars.get(key)).lDefenders.get(0)).iCivID, Images.intervene) {
                        public void actionElement() {
                            if (Game.menuManager.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == 48 && InGame_Intervene.warKey.equals(InGame_War.key)) {
                                Game.menuManager.setVisibleInGame_PopUp(false);
                            } else {
                                InGame_Intervene.warKey = InGame_War.key;
                                Game.menuManager.rebuildInGame_Intervene(((WarCivilization)((War)WarManager.lWars.get(InGame_War.key)).lDefenders.get(0)).iCivID, ((WarCivilization)((War)WarManager.lWars.get(InGame_War.key)).lAggressors.get(0)).iCivID);
                            }

                        }

                        public void buildElementHover() {
                            List<MenuElement_HoverElement> nElements = new ArrayList();
                            List<MenuElement_HoverElement_Type> nData = new ArrayList();
                            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("InterveneInWar"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.intervene, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();

                            try {
                                nData.add(new MenuElement_HoverElement_Type_Button_TextBonusFlag(Game.lang.get("JoinAWarAgainst") + ": ", Game.getCiv(((WarCivilization)((War)WarManager.lWars.get(InGame_War.key)).lAggressors.get(0)).iCivID).getCivName(), ((WarCivilization)((War)WarManager.lWars.get(InGame_War.key)).lAggressors.get(0)).iCivID, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                            } catch (Exception var4) {
                            }

                            this.menuElementHover = new MenuElement_Hover(nElements);
                        }
                    });
                    buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (!alliesCall.isEmpty() || !alliesCall_Right.isEmpty() || ((War)WarManager.lWars.get(key)).lAggressors.size() > 1 || ((War)WarManager.lWars.get(key)).lDefenders.size() > 1) {
                    menuElements.add(new Text_Title_v2Center(Game.lang.get("Allies"), -1, CFG.FONT_BOLD, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - 3 - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 6));
                    buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                    int tMenuElementsBefore = menuElements.size();
                    int buttonYStart = buttonY;
                    int alliesWidth = (menuWidth - paddingLeft * 2 - CFG.PADDING) / 2;
                    int buttonH = CFG.BUTTON_HEIGHT3;
                    if (((WarCivilization)((War)WarManager.lWars.get(key)).lAggressors.get(0)).iCivID == Game.player.iCivID && !alliesCall.isEmpty()) {
                        menuElements.add(new Text_StaticBG_ID_FlagCiv(Game.lang.get("CallAllies"), CFG.FONT_REGULAR_SMALL, CFG.PADDING * 2, paddingLeft, buttonY, alliesWidth, buttonH, Game.player.iCivID) {
                            public void actionElement() {
                                if (Game.menuManager.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == 37 && InGame_CallAllies.warKey.equals(InGame_War.key)) {
                                    Game.menuManager.setVisibleInGame_PopUp(false);
                                } else {
                                    InGame_CallAllies.warKey = InGame_War.key;
                                    Game.menuManager.rebuildInGame_CallAllies(((WarCivilization)((War)WarManager.lWars.get(InGame_War.key)).lDefenders.get(0)).iCivID);
                                }

                            }

                            public void buildElementHover() {
                                List<MenuElement_HoverElement> nElements = new ArrayList();
                                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("CallAllies"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.alliance, CFG.PADDING, 0));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                                this.menuElementHover = new MenuElement_Hover(nElements, true);
                            }
                        });
                        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                    }

                    for(int i = 1; i < ((War)WarManager.lWars.get(key)).lAggressors.size(); ++i) {
                        if (Game.getCiv(((WarCivilization)((War)WarManager.lWars.get(key)).lAggressors.get(i)).iCivID).getNumOfProvinces() > 0) {
                            menuElements.add(new Text_StaticBG_ID_FlagCiv("" + Game.getCiv(((WarCivilization)((War)WarManager.lWars.get(key)).lAggressors.get(i)).iCivID).getCivName(), CFG.FONT_REGULAR_SMALL, CFG.PADDING * 2, paddingLeft, buttonY, alliesWidth, buttonH, ((WarCivilization)((War)WarManager.lWars.get(key)).lAggressors.get(i)).iCivID) {
                                public void actionElement() {
                                    if (Game.getCiv(this.getCurrent()).getCapitalProvinceID() >= 0 && Game.getProvince(Game.getCiv(this.getCurrent()).getCapitalProvinceID()).getCivID() == this.getCurrent()) {
                                        if (Game.iActiveProvince >= 0 && Game.getProvince(Game.iActiveProvince).getCivID() == this.getCurrent()) {
                                            Game.menuManager.rebuildInGame_Civ();
                                        } else {
                                            Game.mapCoords.centerToProvinceID(Game.getCiv(this.getCurrent()).getCapitalProvinceID());
                                            Game.setActiveProvinceID(Game.getCiv(this.getCurrent()).getCapitalProvinceID());
                                            ProvinceBorderManager.action.setProvinceID(Game.iActiveProvince);
                                        }
                                    }

                                }

                                public void buildElementHover() {
                                    this.menuElementHover = CivilizationRanking.getHover_CivilizationRanking_Short(this.getCurrent(), false, false);
                                }
                            });
                            buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                        }
                    }

                    if (tMenuElementsBefore == menuElements.size()) {
                        menuElements.add(new Text_StaticBG(Game.lang.get("None"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, alliesWidth, buttonH));
                        var10000 = buttonY + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                    }

                    buttonY = buttonYStart;
                    tMenuElementsBefore = menuElements.size();
                    if (((WarCivilization)((War)WarManager.lWars.get(key)).lDefenders.get(0)).iCivID == Game.player.iCivID && !alliesCall_Right.isEmpty()) {
                        menuElements.add(new Text_StaticBG_ID_FlagCiv(Game.lang.get("CallAllies"), CFG.FONT_REGULAR_SMALL, CFG.PADDING * 2, paddingLeft + CFG.PADDING + alliesWidth, buttonYStart, alliesWidth, buttonH, Game.player.iCivID) {
                            public void actionElement() {
                                if (Game.menuManager.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == 37 && InGame_CallAllies.warKey.equals(InGame_War.key)) {
                                    Game.menuManager.setVisibleInGame_PopUp(false);
                                } else {
                                    InGame_CallAllies.warKey = InGame_War.key;
                                    Game.menuManager.rebuildInGame_CallAllies(((WarCivilization)((War)WarManager.lWars.get(InGame_War.key)).lAggressors.get(0)).iCivID);
                                }

                            }

                            public void buildElementHover() {
                                List<MenuElement_HoverElement> nElements = new ArrayList();
                                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("CallAllies"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.alliance, CFG.PADDING, 0));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                                this.menuElementHover = new MenuElement_Hover(nElements, true);
                            }
                        });
                        buttonY = buttonYStart + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                    }

                    for(int i = 1; i < ((War)WarManager.lWars.get(key)).lDefenders.size(); ++i) {
                        if (Game.getCiv(((WarCivilization)((War)WarManager.lWars.get(key)).lDefenders.get(i)).iCivID).getNumOfProvinces() > 0) {
                            menuElements.add(new Text_StaticBG_ID_FlagCiv("" + Game.getCiv(((WarCivilization)((War)WarManager.lWars.get(key)).lDefenders.get(i)).iCivID).getCivName(), CFG.FONT_REGULAR_SMALL, CFG.PADDING * 2, paddingLeft + CFG.PADDING + alliesWidth, buttonY, alliesWidth, buttonH, ((WarCivilization)((War)WarManager.lWars.get(key)).lDefenders.get(i)).iCivID) {
                                public void actionElement() {
                                    if (Game.getCiv(this.getCurrent()).getCapitalProvinceID() >= 0 && Game.getProvince(Game.getCiv(this.getCurrent()).getCapitalProvinceID()).getCivID() == this.getCurrent()) {
                                        if (Game.iActiveProvince >= 0 && Game.getProvince(Game.iActiveProvince).getCivID() == this.getCurrent()) {
                                            Game.menuManager.rebuildInGame_Civ();
                                        } else {
                                            Game.mapCoords.centerToProvinceID(Game.getCiv(this.getCurrent()).getCapitalProvinceID());
                                            Game.setActiveProvinceID(Game.getCiv(this.getCurrent()).getCapitalProvinceID());
                                            ProvinceBorderManager.action.setProvinceID(Game.iActiveProvince);
                                        }
                                    }

                                }

                                public void buildElementHover() {
                                    this.menuElementHover = CivilizationRanking.getHover_CivilizationRanking_Short(this.getCurrent(), false, false);
                                }
                            });
                            buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                        }
                    }

                    if (tMenuElementsBefore == menuElements.size()) {
                        menuElements.add(new Text_StaticBG(Game.lang.get("None"), CFG.FONT_REGULAR, -1, paddingLeft + CFG.PADDING + alliesWidth, buttonY, alliesWidth, buttonH));
                        var10000 = buttonY + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                    }
                }

                buttonY = 0;
                int i = 0;

                for(int iSize = menuElements.size(); i < iSize; ++i) {
                    if (buttonY < ((MenuElement)menuElements.get(i)).getPosY() + ((MenuElement)menuElements.get(i)).getHeight() + CFG.PADDING) {
                        buttonY = ((MenuElement)menuElements.get(i)).getPosY() + ((MenuElement)menuElements.get(i)).getHeight() + CFG.PADDING;
                    }
                }
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

        buttonY = 0;
        int i = 0;

        for(int iSize = menuElements.size(); i < iSize; ++i) {
            if (buttonY < ((MenuElement)menuElements.get(i)).getPosY() + ((MenuElement)menuElements.get(i)).getHeight() + CFG.PADDING) {
                buttonY = ((MenuElement)menuElements.get(i)).getPosY() + ((MenuElement)menuElements.get(i)).getHeight() + CFG.PADDING;
            }
        }

        menuHeight = Math.max(menuMinHeight, buttonY);
        int menuY = CFG.GAME_HEIGHT - Math.min(ImageManager.getImage(Images.warViewOver).getHeight() + CFG.PADDING * 2, menuHeight);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, menuHeight)));
        menuHeight = Math.min(menuHeight, CFG.GAME_HEIGHT - menuY);
        String civLeft = "";
        String civRight = "";

        try {
            civLeft = Game.getCiv(((WarCivilization)((War)WarManager.lWars.get(key)).lAggressors.get(0)).iCivID).getCivName();
            civRight = Game.getCiv(((WarCivilization)((War)WarManager.lWars.get(key)).lDefenders.get(0)).iCivID).getCivName();
        } catch (Exception var28) {
        }

        this.initMenu(new MenuTitleIMG_War(tTitle, Game.lang.get("Attackers"), Game.lang.get("Defenders"), civLeft, civRight, false, false, Images.title580) {
            public void draw(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, Status titleStatus) {
                super.draw(oSB, nPosX, nPosY, nWidth + Images.boxTitleBORDERWIDTH, titleStatus);
            }

            public long getTime() {
                return InGame_War.lTime;
            }
        }, menuX, menuY, menuWidth, menuHeight, menuElements, false, true);
    }

    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean menuIsActive, Status titleStatus) {
        DiplomacyManager.updateInAnimation();
        if (lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateX = iTranslateX - CFG.BUTTON_WIDTH + (int)((float)CFG.BUTTON_WIDTH * ((float)(CFG.currentTimeMillis - lTime) / 60.0F));
            iTranslateY += (int)((float)CFG.BUTTON_HEIGHT / 2.0F - (float)(CFG.BUTTON_HEIGHT / 2) * ((float)(CFG.currentTimeMillis - lTime) / 60.0F));
        }

        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth() + Images.boxTitleBORDERWIDTH, this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth() + Images.boxTitleBORDERWIDTH, this.getHeight() + CFG.PADDING, false, Images.insideTop580, Images.insideBot580);
        ImageManager.getImage(Images.warViewOver).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.warViewOver).getHeight()));
        if(Config.getAnimationConfig().InGame_War_Spark) {
            oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.25F));
            sparksAnimation.draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - Images.sparkHeight + iTranslateY, this.getWidth(), Images.sparkHeight);
        }
        oSB.setColor(Color.WHITE);
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }

    public void setVisible(boolean visible) {
        super.setVisible(visible);
        lTime = CFG.currentTimeMillis;
    }
}
