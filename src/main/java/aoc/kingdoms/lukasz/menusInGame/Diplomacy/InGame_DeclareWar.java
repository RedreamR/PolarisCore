//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.menusInGame.Diplomacy;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.jakowski.SoundsManager;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.jakowski.Steam.SteamAchievementsManager;
import aoc.kingdoms.lukasz.map.RulersManager;
import aoc.kingdoms.lukasz.map.civilization.CivilizationRanking;
import aoc.kingdoms.lukasz.map.diplomacy.Diplomacy;
import aoc.kingdoms.lukasz.map.diplomacy.DiplomacyManager;
import aoc.kingdoms.lukasz.map.province.ProvinceBorderManager;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.menu.Menu;
import aoc.kingdoms.lukasz.menu.menuTitle.MenuTitleIMG;
import aoc.kingdoms.lukasz.menu_element.Empty;
import aoc.kingdoms.lukasz.menu_element.MenuElement;
import aoc.kingdoms.lukasz.menu_element.Status;
import aoc.kingdoms.lukasz.menu_element.button.ButtonFlag_Diplomacy;
import aoc.kingdoms.lukasz.menu_element.button.ButtonGame;
import aoc.kingdoms.lukasz.menu_element.button.ButtonGame_ImageSparks;
import aoc.kingdoms.lukasz.menu_element.button.ButtonRuler_Diplomacy;
import aoc.kingdoms.lukasz.menu_element.button.ButtonStatsRectIMG_Bonuses;
import aoc.kingdoms.lukasz.menu_element.button.ButtonStatsRectIMG_Diplomacy;
import aoc.kingdoms.lukasz.menu_element.button.ButtonStatsRectIMG_Diplomacy_Flip;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_Hover;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_FlagCiv_Title;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text_Desc;
import aoc.kingdoms.lukasz.menu_element.textStatic.TextBonus;
import aoc.kingdoms.lukasz.menu_element.textStatic.TextIcon_Diplomacy;
import aoc.kingdoms.lukasz.menu_element.textStatic.Text_Desc;
import aoc.kingdoms.lukasz.menu_element.textStatic.Text_StaticBG;
import aoc.kingdoms.lukasz.menu_element.textStatic.Text_StaticBG_ID_FlagCiv;
import aoc.kingdoms.lukasz.menu_element.textStatic.Text_StaticBG_ID_FlagCiv_GreenRed;
import aoc.kingdoms.lukasz.menu_element.textStatic.Text_Static_ID;
import aoc.kingdoms.lukasz.menu_element.textStatic.Text_Title_v2Center;
import aoc.kingdoms.lukasz.menusInGame.Civ.InGame_Civ;
import aoc.kingdoms.lukasz.menusInGame.Court.InGame_CourtOptions2;
import aoc.kingdoms.lukasz.menusInGame.Info.InGame_Info;
import aoc.kingdoms.lukasz.textures.Image;
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.textures.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import team.rainfall.fontFix.FontFix;

import java.util.ArrayList;
import java.util.List;

public class InGame_DeclareWar extends Menu {
    public static final int ANIMATION_TIME = 60;
    public static long lTime = 0L;
    public static int iCivID = 0;
    public static List<Integer> callToWar = new ArrayList();

    public InGame_DeclareWar(int nCivID) {
        List<MenuElement> menuElements = new ArrayList();
        callToWar.clear();
        int paddingLeft = CFG.PADDING + Images.boxTitleBORDERWIDTH;
        int titleHeight = ImageManager.getImage(Images.title600).getHeight();
        int menuWidth = ImageManager.getImage(Images.insideTop600).getWidth();
        int menuX = InGame_CourtOptions2.getOtherMenuPosX_2();
        int menuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY + CFG.PADDING + ImageManager.getImage(Images.title1Red).getHeight();
        int buttonYPadding = CFG.PADDING * 2;
        int buttonY = CFG.PADDING;
        iCivID = nCivID;
        int maxWidth = ImageManager.getImage(Images.warBig).getWidth() + CFG.PADDING * 4;
        int tempTitlePaddingY = CFG.PADDING;
        int tempTitleH = ImageManager.getImage(Images.flagDiplomacyOver).getHeight() + tempTitlePaddingY * 2;
        int tempTextW = menuWidth / 2 - paddingLeft - CFG.PADDING * 2 - maxWidth / 2 - ImageManager.getImage(Images.flagDiplomacyOver).getWidth();
        menuElements.add(new ButtonFlag_Diplomacy(Game.player.iCivID, menuWidth / 2 - maxWidth / 2 - CFG.PADDING * 2 - ImageManager.getImage(Images.flagDiplomacyOver).getWidth(), buttonY + tempTitlePaddingY, true));
        menuElements.add(new ButtonFlag_Diplomacy(iCivID, menuWidth / 2 + maxWidth / 2 + CFG.PADDING * 2, buttonY + tempTitlePaddingY, true));
        menuElements.add(new Text_Static_ID(iCivID, Game.getCiv(iCivID).getCivName(), CFG.FONT_REGULAR, -1, menuWidth / 2 + maxWidth / 2 + CFG.PADDING * 2 + ImageManager.getImage(Images.flagDiplomacyOver).getWidth(), buttonY, tempTextW, tempTitleH));
        menuElements.add(new Text_Static_ID(Game.player.iCivID, Game.getCiv(Game.player.iCivID).getCivName(), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, tempTextW, tempTitleH));
        menuElements.add(new TextIcon_Diplomacy(Images.warBig, paddingLeft, buttonY, menuWidth - paddingLeft * 2, tempTitleH, maxWidth));
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        int statsX = paddingLeft + ButtonRuler_Diplomacy.getButtonWidth() + CFG.PADDING;
        int statsW = menuWidth / 2 - statsX - CFG.PADDING / 2;
        int statsH = (ButtonRuler_Diplomacy.getButtonHeight() - CFG.PADDING * 2) / 3;
        int maxIconW = ImageManager.getImage(Game_Calendar.IMG_MANPOWER).getWidth() + CFG.PADDING * 2;
        menuElements.add(new ButtonStatsRectIMG_Diplomacy("" + CFG.getShortNumber(Game.getCiv(Game.player.iCivID).iRegimentsLimit), Images.regimentsLimit, statsX, buttonY, statsW, statsH, maxIconW, 0) {
            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("RegimentsLimit") + ": ", CFG.FONT_BOLD));
                nData.add(new MenuElement_HoverElement_Type_TextTitle("" + CFG.getShortNumber(Game.getCiv(Game.player.iCivID).iRegimentsLimit), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.regimentsLimit, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        menuElements.add(new ButtonStatsRectIMG_Diplomacy("" + Game.getCiv(Game.player.iCivID).getResearchedTechnologies(), Game_Calendar.IMG_TECHNOLOGY, statsX, buttonY + CFG.PADDING + statsH, statsW, statsH, maxIconW, 0) {
            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("UnlockedTechnologies") + ": ", CFG.FONT_BOLD));
                nData.add(new MenuElement_HoverElement_Type_TextTitle("" + Game.getCiv(Game.player.iCivID).getResearchedTechnologies(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle(Game_Calendar.IMG_TECHNOLOGY, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        menuElements.add(new ButtonStatsRectIMG_Diplomacy("" + Game.getCiv(Game.player.iCivID).getNumOfProvinces(), Images.provinces, statsX, buttonY + (CFG.PADDING + statsH) * 2, statsW, statsH, maxIconW, 0) {
            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Provinces") + ": ", CFG.FONT_BOLD));
                nData.add(new MenuElement_HoverElement_Type_TextTitle("" + Game.getCiv(Game.player.iCivID).getNumOfProvinces(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.provinces, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        menuElements.add(new ButtonStatsRectIMG_Diplomacy_Flip("" + CFG.getShortNumber(Game.getCiv(iCivID).iRegimentsLimit), Images.regimentsLimit, statsX + CFG.PADDING + statsW, buttonY, statsW, statsH, maxIconW, 0) {
            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("RegimentsLimit") + ": ", CFG.FONT_BOLD));
                nData.add(new MenuElement_HoverElement_Type_TextTitle("" + CFG.getShortNumber(Game.getCiv(InGame_DeclareWar.iCivID).iRegimentsLimit), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.regimentsLimit, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        menuElements.add(new ButtonStatsRectIMG_Diplomacy_Flip("" + Game.getCiv(iCivID).getResearchedTechnologies(), Game_Calendar.IMG_TECHNOLOGY, statsX + CFG.PADDING + statsW, buttonY + CFG.PADDING + statsH, statsW, statsH, maxIconW, 0) {
            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("UnlockedTechnologies") + ": ", CFG.FONT_BOLD));
                nData.add(new MenuElement_HoverElement_Type_TextTitle("" + Game.getCiv(InGame_DeclareWar.iCivID).getResearchedTechnologies(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle(Game_Calendar.IMG_TECHNOLOGY, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        menuElements.add(new ButtonStatsRectIMG_Diplomacy_Flip("" + Game.getCiv(iCivID).getNumOfProvinces(), Images.provinces, statsX + CFG.PADDING + statsW, buttonY + (CFG.PADDING + statsH) * 2, statsW, statsH, maxIconW, 0) {
            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("Provinces") + ": ", CFG.FONT_BOLD));
                nData.add(new MenuElement_HoverElement_Type_TextTitle("" + Game.getCiv(InGame_DeclareWar.iCivID).getNumOfProvinces(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle(Images.provinces, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        RulersManager.loadRulerIMG_DiplomacyLeft(Game.player.iCivID);
        RulersManager.loadRulerIMG_DiplomacyRight(iCivID);
        menuElements.add(new ButtonRuler_Diplomacy(Game.player.iCivID, paddingLeft, buttonY));
        menuElements.add(new ButtonRuler_Diplomacy(iCivID, menuWidth - paddingLeft - ButtonRuler_Diplomacy.getButtonWidth(), buttonY) {
            public Image getRulerImage() {
                return RulersManager.rulerIMG_DiplomacyRight;
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        menuElements.add(new Text_Desc(DiplomacyManager.getWarMessage(), paddingLeft, buttonY, menuWidth - paddingLeft * 2) {
            protected Color getColor(boolean isActive) {
                return Colors.getColorButtonHover2(isActive, this.getIsHovered());
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        if (Game.getCiv(iCivID).diplomacy.getRelation(Game.player.iCivID) > (float)GameValues.war.RELATIONS_TO_DECLARE_WAR) {
            menuElements.add(new Text_Desc(Game.lang.get("ToDeclareWarTheRelationsBetweenCivilizationsMustBeBelowX", GameValues.war.RELATIONS_TO_DECLARE_WAR), paddingLeft, buttonY, menuWidth - paddingLeft * 2) {
                protected Color getColor(boolean isActive) {
                    return Colors.getColorNegative(isActive, this.getIsHovered());
                }

                public void buildElementHover() {
                    List<MenuElement_HoverElement> nElements = new ArrayList();
                    List<MenuElement_HoverElement_Type> nData = new ArrayList();
                    nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(Game.player.iCivID, Game.getCiv(InGame_DeclareWar.iCivID).getCivName()));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Opinion") + ": ", (Game.getCiv(InGame_DeclareWar.iCivID).diplomacy.getRelation(Game.player.iCivID) > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(InGame_DeclareWar.iCivID).diplomacy.getRelation(Game.player.iCivID), 10), Images.relations, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("DamageRelations"), "", Images.relationsDown, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("SendAnInsult"), "", Images.insult, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }

                public void actionElement() {
                    if (Game.getCiv(InGame_DeclareWar.iCivID).getCapitalProvinceID() >= 0) {
                        Game.setActiveProvinceID(Game.getCiv(InGame_DeclareWar.iCivID).getCapitalProvinceID());
                        ProvinceBorderManager.action.setProvinceID(Game.iActiveProvince);
                        Game.menuManager.rebuildInGame_Civ();
                    }

                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        if (Game.getCiv(iCivID).eventsDataVariables.hasVariable("$$PolarizedLight_NATDW")) {
            menuElements.add(new Text_Desc(Game.lang.get("NotAllowedToDeclareWar"), paddingLeft, buttonY, menuWidth - paddingLeft * 2) {
                protected Color getColor(boolean isActive) {
                    return Colors.getColorNegative(isActive, this.getIsHovered());
                }

                public void buildElementHover() {
                    List<MenuElement_HoverElement> nElements = new ArrayList();
                    List<MenuElement_HoverElement_Type> nData = new ArrayList();
                    nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(Game.player.iCivID, Game.getCiv(InGame_DeclareWar.iCivID).getCivName()));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("NotAllowedToDeclareWar"), "", Images.peace, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Wait"), "", Images.time, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }

                public void actionElement() {
                    if (Game.getCiv(InGame_DeclareWar.iCivID).getCapitalProvinceID() >= 0) {
                        Game.setActiveProvinceID(Game.getCiv(InGame_DeclareWar.iCivID).getCapitalProvinceID());
                        ProvinceBorderManager.action.setProvinceID(Game.iActiveProvince);
                        Game.menuManager.rebuildInGame_Civ();
                    }

                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }
        if (FontFix.isDemilitarization(iCivID)) {
            menuElements.add(new Text_Desc(Game.lang.get("ToDeclareWarMustBeNotDemilitarization"), paddingLeft, buttonY, menuWidth - paddingLeft * 2) {
                protected Color getColor(boolean isActive) {
                    return Colors.getColorNegative(isActive, this.getIsHovered());
                }

                public void buildElementHover() {
                    List<MenuElement_HoverElement> nElements = new ArrayList();
                    List<MenuElement_HoverElement_Type> nData = new ArrayList();
                    nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(Game.player.iCivID, Game.getCiv(InGame_DeclareWar.iCivID).getCivName()));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Demilitarization") + ": ", ""+FontFix.getDemilitarization(iCivID), Images.disbandArmy, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Wait"), "", Images.time, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }

                public void actionElement() {
                    if (Game.getCiv(InGame_DeclareWar.iCivID).getCapitalProvinceID() >= 0) {
                        Game.setActiveProvinceID(Game.getCiv(InGame_DeclareWar.iCivID).getCapitalProvinceID());
                        ProvinceBorderManager.action.setProvinceID(Game.iActiveProvince);
                        Game.menuManager.rebuildInGame_Civ();
                    }

                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }

        paddingLeft += CFG.PADDING;
        int iconWidth = (int)Math.ceil((double)((float)ImageManager.getImage(Images.gold).getWidth() * 1.5F));
        if (Game.getCiv(Game.player.iCivID).fManpower / Game.getCiv(Game.player.iCivID).fManpowerMax < (double)GameValues.diplomacy.DIPLOMACY_DECLARE_WAR_LOW_MANPOWER_INFO_IF_BELOW) {
            menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("Manpower") + ": ", "" + CFG.getNumberWithSpaces("" + (int)Game.getCiv(Game.player.iCivID).fManpower) + " / " + CFG.getNumberWithSpaces("" + (int)Game.getCiv(Game.player.iCivID).fManpowerMax) + " [" + CFG.getPrecision2(Game.getCiv(Game.player.iCivID).fManpower / Game.getCiv(Game.player.iCivID).fManpowerMax * (double)100.0F, 10) + "%]", Game_Calendar.IMG_MANPOWER, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT3, iconWidth, CFG.FONT_REGULAR_SMALL, CFG.FONT_REGULAR_SMALL) {
                public Color getColorBonus() {
                    return Colors.HOVER_NEGATIVE;
                }

                public void buildElementHover() {
                    List<MenuElement_HoverElement> nElements = new ArrayList();
                    List<MenuElement_HoverElement_Type> nData = new ArrayList();
                    nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(Game.player.iCivID, Game.lang.get("MaximumManpower") + ": " + CFG.getNumberWithSpaces("" + (int)Game.getCiv(Game.player.iCivID).fManpowerMax)));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(this.getText(), this.sText2, this.imageID, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Manpower") + ": 0% -> 100%: ", Game.lang.get("MonthsX", (int)GameValues.manpower.MANPOWER_FULL_RECOVERY_MONTHS), Game_Calendar.IMG_MANPOWER, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        }

        menuElements.add(new TextBonus(Game.lang.get("Opinion") + ": ", (Game.getCiv(iCivID).diplomacy.getRelation(Game.player.iCivID) > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(iCivID).diplomacy.getRelation(Game.player.iCivID), 10), Images.relations, paddingLeft, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING / 2 * 2) / 2, CFG.TEXT_HEIGHT + CFG.PADDING * 5, iconWidth) {
            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(Game.player.iCivID, Game.getCiv(InGame_DeclareWar.iCivID).getCivName()));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Opinion") + ": ", (Game.getCiv(InGame_DeclareWar.iCivID).diplomacy.getRelation(Game.player.iCivID) > 0.0F ? "+" : "") + CFG.getPrecision2(Game.getCiv(InGame_DeclareWar.iCivID).diplomacy.getRelation(Game.player.iCivID), 10), Images.relations, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, DiplomacyManager.getOpinion_Color((int)Game.getCiv(InGame_DeclareWar.iCivID).diplomacy.getRelation(Game.player.iCivID))));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("ToDeclareWarTheRelationsBetweenCivilizationsMustBeBelowX", GameValues.war.RELATIONS_TO_DECLARE_WAR), CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }

            public void actionElement() {
                if (Game.getCiv(InGame_DeclareWar.iCivID).diplomacy.getRelation(Game.player.iCivID) > (float)GameValues.war.RELATIONS_TO_DECLARE_WAR && Game.getCiv(InGame_DeclareWar.iCivID).getCapitalProvinceID() >= 0) {
                    Game.setActiveProvinceID(Game.getCiv(InGame_DeclareWar.iCivID).getCapitalProvinceID());
                    ProvinceBorderManager.action.setProvinceID(Game.iActiveProvince);
                    Game.menuManager.rebuildInGame_Civ();
                }

            }
        });
        menuElements.add(new TextBonus(Game.lang.get("Cost") + ": ", "" + CFG.getPrecision2(GameValues.diplomacy.DIPLOMACY_DECLARE_WAR_COST, 100), Images.diplomacy, paddingLeft + CFG.PADDING + (menuWidth - paddingLeft * 2 - CFG.PADDING / 2 * 2) / 2, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING / 2 * 2) / 2, CFG.TEXT_HEIGHT + CFG.PADDING * 5, iconWidth) {
            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("DiplomacyPoints") + ": ", "" + CFG.getPrecision2(GameValues.diplomacy.DIPLOMACY_DECLARE_WAR_COST, 100), Images.diplomacy, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonGame(Game.lang.get("Cancel"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING / 2 * 2) / 2, true) {
            public void actionElement() {
                Game.menuManager.setVisibleInGame_PopUp(false);
                if (Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_DIPLOMACY) {
                    Game.mapModes.setActiveViewID(Game.mapModes.MODE_DIPLOMACY);
                }

                ProvinceBorderManager.updateDrawProvinceBorder_SelectCiv_ByCivID(InGame_DeclareWar.iCivID);
                InGame_Civ.iRebuildToCivID = InGame_DeclareWar.iCivID;
                Game.menuManager.rebuildInGame_Civ(true);
                InGame_Civ.lTime = 0L;
            }
        });
        menuElements.add(new ButtonGame_ImageSparks(Game.lang.get("DeclareWar"), CFG.FONT_REGULAR, -1, paddingLeft + CFG.PADDING + (menuWidth - paddingLeft * 2 - CFG.PADDING / 2 * 2) / 2, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING / 2 * 2) / 2, true, Images.war) {
            public int getSFX() {
                return -1;
            }

            public void actionElement() {
                InGame_DeclareWar.confirm();
            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("DeclareWar"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.war, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                if (Game.getCiv(InGame_DeclareWar.iCivID).diplomacy.getRelation(Game.player.iCivID) > (float)GameValues.war.RELATIONS_TO_DECLARE_WAR) {
                    nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("ToDeclareWarTheRelationsBetweenCivilizationsMustBeBelowX", GameValues.war.RELATIONS_TO_DECLARE_WAR), CFG.FONT_REGULAR_SMALL, Game.getCiv(InGame_DeclareWar.iCivID).diplomacy.getRelation(Game.player.iCivID) > (float)GameValues.war.RELATIONS_TO_DECLARE_WAR ? Colors.HOVER_NEGATIVE : Colors.HOVER_LEFT));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }

                if (Game.getCiv(Game.player.iCivID).diplomacy.haveNonAggressionPact(InGame_DeclareWar.iCivID)) {
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("NonAggressionPact"), "", Images.nonAggression, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Expires") + ": ", "" + Game_Calendar.getDate_ByTurnID(((Diplomacy.DiplomacyData)Game.getCiv(Game.player.iCivID).diplomacy.nonAggressionPact.get(InGame_DeclareWar.iCivID)).iTurnID), Images.time, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }

                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("DiplomacyPoints") + ": ", "" + CFG.getPrecision2(GameValues.diplomacy.DIPLOMACY_DECLARE_WAR_COST, 100), Images.diplomacy, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                if (GameValues.aggressiveExpansion.AE_DECLARE_WAR > 0.0F) {
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("AggressiveExpansion") + ": ", (GameValues.aggressiveExpansion.AE_DECLARE_WAR > 0.0F ? "+" : "") + CFG.getPrecision2(GameValues.aggressiveExpansion.AE_DECLARE_WAR, 100), Images.aggressiveExpansion, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }

                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        List<Integer> alliesLeft = DiplomacyManager.declareWar_AlliesAttacker(Game.player.iCivID, iCivID);
        List<Integer> alliesRight = DiplomacyManager.declareWar_AlliesDefender(iCivID, Game.player.iCivID);
        if (alliesLeft.size() > 0 || alliesRight.size() > 0) {
            menuElements.add(new Text_Title_v2Center(Game.lang.get("Allies"), -1, CFG.FONT_BOLD, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 6));
            buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
            int alliesWidth = (menuWidth - paddingLeft * 2 - CFG.PADDING) / 2;
            int buttonH = CFG.BUTTON_HEIGHT3;
            int tMenuElementsBefore = menuElements.size();
            int buttonYStart = buttonY;
            if (alliesLeft.size() > 1) {
                menuElements.add(new Text_StaticBG_ID_FlagCiv("" + Game.lang.get("CallAllies"), CFG.FONT_REGULAR_SMALL, CFG.PADDING * 2, paddingLeft, buttonY, alliesWidth, buttonH, Game.player.iCivID) {
                    public void actionElement() {
                        List<Integer> alliesLeft = DiplomacyManager.declareWar_AlliesAttacker(Game.player.iCivID, InGame_DeclareWar.iCivID);
                        if (alliesLeft.size() > 0) {
                            for(int i = alliesLeft.size() - 1; i >= 0; --i) {
                                if (InGame_DeclareWar.callToWar.contains(alliesLeft.get(i))) {
                                    for(int a = InGame_DeclareWar.callToWar.size() - 1; a >= 0; --a) {
                                        if (((Integer)InGame_DeclareWar.callToWar.get(a)).equals(alliesLeft.get(i))) {
                                            InGame_DeclareWar.callToWar.remove(a);
                                            break;
                                        }
                                    }
                                } else {
                                    InGame_DeclareWar.callToWar.add((Integer)alliesLeft.get(i));
                                }
                            }
                        }

                    }
                });
                buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
            }

            for(int i = 0; i < alliesLeft.size(); ++i) {
                menuElements.add(new Text_StaticBG_ID_FlagCiv_GreenRed("" + Game.getCiv((Integer)alliesLeft.get(i)).getCivName(), CFG.FONT_REGULAR_SMALL, CFG.PADDING * 2, paddingLeft, buttonY, alliesWidth, buttonH, (Integer)alliesLeft.get(i)) {
                    public void actionElement() {
                        if (InGame_DeclareWar.callToWar.contains(this.getCurrent())) {
                            for(int i = InGame_DeclareWar.callToWar.size() - 1; i >= 0; --i) {
                                if ((Integer)InGame_DeclareWar.callToWar.get(i) == this.getCurrent()) {
                                    InGame_DeclareWar.callToWar.remove(i);
                                    break;
                                }
                            }
                        } else {
                            InGame_DeclareWar.callToWar.add(this.getCurrent());
                        }

                    }

                    public boolean getCheckboxState() {
                        return InGame_DeclareWar.callToWar.contains(this.getCurrent());
                    }

                    public void buildElementHover() {
                        List<MenuElement_HoverElement> nElements = new ArrayList();
                        List<MenuElement_HoverElement_Type> nData = new ArrayList();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("CallAlly") + ": " + Game.getCiv(this.getCurrent()).getCivName(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.alliance, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements, true);
                    }
                });
                buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
            }

            if (tMenuElementsBefore == menuElements.size()) {
                menuElements.add(new Text_StaticBG(Game.lang.get("None"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, alliesWidth, buttonH));
                int var10000 = buttonY + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
            }

            buttonY = buttonYStart;
            tMenuElementsBefore = menuElements.size();

            for(int i = 0; i < alliesRight.size(); ++i) {
                menuElements.add(new Text_StaticBG_ID_FlagCiv("" + Game.getCiv((Integer)alliesRight.get(i)).getCivName(), CFG.FONT_REGULAR_SMALL, CFG.PADDING * 2, paddingLeft + CFG.PADDING + alliesWidth, buttonY, alliesWidth, buttonH, (Integer)alliesRight.get(i)) {
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
                        this.menuElementHover = CivilizationRanking.getHover_CivilizationRanking(this.getCurrent(), false, false);
                    }
                });
                buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
            }

            if (tMenuElementsBefore == menuElements.size()) {
                menuElements.add(new Text_StaticBG(Game.lang.get("None"), CFG.FONT_REGULAR, -1, paddingLeft + CFG.PADDING + alliesWidth, buttonY, alliesWidth, buttonH));
                buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
            }
        }

        for(int i = menuElements.size() - 1; i >= 0; --i) {
            buttonY = Math.max(buttonY, ((MenuElement)menuElements.get(i)).getPosY() + ((MenuElement)menuElements.get(i)).getHeight() + CFG.PADDING);
        }

        int menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - CFG.PADDING * 3);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, buttonY)));
        this.initMenu(new MenuTitleIMG(Game.lang.get("DeclareWar"), true, false, Images.title600) {
            public long getTime() {
                return InGame_DeclareWar.lTime;
            }
        }, menuX, menuY, menuWidth, menuHeight, menuElements, false, true);
    }

    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean menuIsActive, Status titleStatus) {
        DiplomacyManager.updateInAnimation();
        if (lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateX = iTranslateX - CFG.BUTTON_WIDTH + (int)((float)CFG.BUTTON_WIDTH * ((float)(CFG.currentTimeMillis - lTime) / 60.0F));
        }

        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop600, Images.insideBot600);
        ImageManager.getImage(Images.civInfoOver).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.civInfoOver).getHeight()));
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }

    public void setVisible(boolean visible) {
        super.setVisible(visible);
        lTime = CFG.currentTimeMillis;
        DiplomacyManager.updateAnimationTime();
    }

    public static final boolean actionDeclareWar(int iCivPlayer, int onCivID) {
        return actionDeclareWar(iCivPlayer, onCivID, false);
    }

    public static final boolean actionDeclareWar(int iCivPlayer, int onCivID, boolean free) {
        return actionDeclareWar(iCivPlayer, onCivID, free, new ArrayList());
    }

    public static final boolean actionDeclareWar(int iCivPlayer, int onCivID, boolean free, List<Integer> callToWarAttacker) {
        if (!free) {
            if (Game.getCiv(onCivID).diplomacy.getRelation(iCivPlayer) > (float)GameValues.war.RELATIONS_TO_DECLARE_WAR) {
                Game.menuManager.addToast_Error(Game.lang.get("ToDeclareWarTheRelationsBetweenCivilizationsMustBeBelowX", GameValues.war.RELATIONS_TO_DECLARE_WAR), Images.relations);
                return false;
            }

            if (Game.getCiv(iCivPlayer).diplomacy.haveNonAggressionPact(onCivID)) {
                Game.menuManager.addToast_Error(Game.lang.get("NonAggressionPact") + ": " + Game.getCiv(iCivPlayer).getCivName() + " - " + Game.getCiv(onCivID).getCivName(), Images.nonAggression);
                return false;
            }

            if (Game.getCiv(iCivPlayer).diplomacy.haveAlliance(onCivID) || Game.getCiv(onCivID).diplomacy.haveAlliance(iCivPlayer)) {
                Game.menuManager.addToast_Error(Game.lang.get("Alliance") + ": " + Game.getCiv(iCivPlayer).getCivName() + " - " + Game.getCiv(onCivID).getCivName(), Images.alliance);
                return false;
            }

            if (Game.getCiv(iCivPlayer).fDiplomacy < GameValues.diplomacy.DIPLOMACY_DECLARE_WAR_COST) {
                Game.menuManager.addToastInsufficient(Game.lang.get("Cost") + ", " + Game.lang.get("DiplomacyPoints") + ": ", CFG.getPrecision2(GameValues.diplomacy.DIPLOMACY_DECLARE_WAR_COST, 100), Images.diplomacy);
                return false;
            }
        }

        if (Game.getCiv(iCivPlayer).diplomacy.truce.containsKey(onCivID)) {
            if (((Diplomacy.DiplomacyData)Game.getCiv(iCivPlayer).diplomacy.truce.get(onCivID)).iTurnID >= Game_Calendar.TURN_ID) {
                Game.menuManager.addToastInsufficient(Game.lang.get("Truce") + ": " + Game.lang.get("Expires") + ": ", Game_Calendar.getDate_ByTurnID(((Diplomacy.DiplomacyData)Game.getCiv(iCivPlayer).diplomacy.truce.get(onCivID)).iTurnID), Images.time);
                return false;
            }

            Game.getCiv(iCivPlayer).diplomacy.truce.remove(onCivID);
            Game.getCiv(onCivID).diplomacy.truce.remove(iCivPlayer);
        }

        try {
            if (DiplomacyManager.declareWar(iCivPlayer, onCivID, free, callToWarAttacker)) {
                if (GameValues.provinceBorderWar.ENABLE_WAR_BORDER) {
                    Game.addSimpleTask(new Game.SimpleTask("updateProvinceBorder") {
                        public void update() {
                            ProvinceBorderManager.updateProvinceBorder();
                        }
                    });
                }

                Game.menuManager.rebuildInGame_Wars();
                Game.menuManager.setVisibleInGame_PopUp(false);
                InGame_Info.iCivID = iCivPlayer;
                InGame_Info.iCivID2 = onCivID;
                Game.menuManager.rebuildInGame_Info(Game.lang.get("War"), Game.getCiv(iCivPlayer).getCivName() + " - " + Game.getCiv(onCivID).getCivName());
                InGame_Info.imgID = Images.infoWar;
                Game.soundsManager.loadNextMusicWar();
                Game.menuManager.WAR_TIME = CFG.currentTimeMillis;
            } else {
                Game.menuManager.addToastInsufficient(Game.lang.get("Refused"), "", Images.x);
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

        return true;
    }

    public static void confirm() {
        if (actionDeclareWar(Game.player.iCivID, iCivID, false, callToWar)) {
            Game.soundsManager.playSound(SoundsManager.WAR);
            SteamAchievementsManager.unlockAchievement(SteamAchievementsManager.DECLARE_WAR);
        } else {
            Game.soundsManager.playSound(Game.soundsManager.getClickMain());
        }

    }
}
