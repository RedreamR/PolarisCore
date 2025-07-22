//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.menusInGame.Court;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game_Ages;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.jakowski.Keyboard;
import aoc.kingdoms.lukasz.jakowski.SoundsManager;
import aoc.kingdoms.lukasz.jakowski.Keyboard.KeyboardActionType;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.jakowski.Steam.SteamAchievementsManager;
import aoc.kingdoms.lukasz.map.RulersManager;
import aoc.kingdoms.lukasz.map.advisors.AdvisorManager;
import aoc.kingdoms.lukasz.map.civilization.CivilizationRanking;
import aoc.kingdoms.lukasz.map.diplomacy.DiplomacyEspionageMission;
import aoc.kingdoms.lukasz.map.diplomacy.DiplomacyManager;
import aoc.kingdoms.lukasz.map.diplomacy.Vassal;
import aoc.kingdoms.lukasz.map.map.Map_Data;
import aoc.kingdoms.lukasz.map.province.ProvinceBorderManager;
import aoc.kingdoms.lukasz.menu.ClickAnimation;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.menu.Menu;
import aoc.kingdoms.lukasz.menu.MenuManager;
import aoc.kingdoms.lukasz.menu.View;
import aoc.kingdoms.lukasz.menu.menuTitle.MenuTitle;
import aoc.kingdoms.lukasz.menu_element.Empty;
import aoc.kingdoms.lukasz.menu_element.MenuElement;
import aoc.kingdoms.lukasz.menu_element.Status;
import aoc.kingdoms.lukasz.menu_element.button.*;
import aoc.kingdoms.lukasz.menu_element.graph.Graph_Vertical;
import aoc.kingdoms.lukasz.menu_element.graph.Graph_Vertical_Data_Type;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_Hover;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Empty;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Flag;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_FlagCiv_Title;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_FlagTitle;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG_Center;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG_Clear;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text_Desc;
import aoc.kingdoms.lukasz.menu_element.textStatic.TextIcon2_Value;
import aoc.kingdoms.lukasz.menu_element.textStatic.TextIcon2_Value_Levels;
import aoc.kingdoms.lukasz.menu_element.textStatic.Text_StaticBG;
import aoc.kingdoms.lukasz.menu_element.textStatic.Text_StaticBG_Advisor;
import aoc.kingdoms.lukasz.menu_element.textStatic.Text_StaticBG_Advisor_Skill;
import aoc.kingdoms.lukasz.menu_element.textStatic.Text_StaticBG_ID_FlagCiv;
import aoc.kingdoms.lukasz.menu_element.textStatic.Text_StaticBG_ID_FlagCiv_SpecialCiv;
import aoc.kingdoms.lukasz.menu_element.textStatic.Text_StaticBG_ID_FlagCiv_SpecialEmpty;
import aoc.kingdoms.lukasz.menu_element.textStatic.Text_StaticBG_ID_Special;
import aoc.kingdoms.lukasz.menu_element.textStatic.Text_StaticBG_Ruler;
import aoc.kingdoms.lukasz.menu_element.textStatic.Text_StaticBG_RulerTitle;
import aoc.kingdoms.lukasz.menu_element.textStatic.Text_Title_Diplomacy;
import aoc.kingdoms.lukasz.menu_element.textStatic.Text_Title_v2Center;
import aoc.kingdoms.lukasz.menu_element.textStatic.Text_Title_v2_TextLR;
import aoc.kingdoms.lukasz.menus.Dialog;
import aoc.kingdoms.lukasz.menus.Dialog.DialogType;
import aoc.kingdoms.lukasz.menusInGame.InGame_AdvisorRecruit;
import aoc.kingdoms.lukasz.menusInGame.InGame_CivBonuses;
import aoc.kingdoms.lukasz.menusInGame.InGame_Encyclopedia;
import aoc.kingdoms.lukasz.menusInGame.InGame_HideUI;
import aoc.kingdoms.lukasz.menusInGame.InGame_ReleaseAVassal;
import aoc.kingdoms.lukasz.menusInGame.InGame_SelectCivilization_Add_List;
import aoc.kingdoms.lukasz.menusInGame.Civ.InGame_Civ;
import aoc.kingdoms.lukasz.menusInGame.Court.World.InGame_Court_WorldCivs;
import aoc.kingdoms.lukasz.menusInGame.Court.World.InGame_Court_WorldSearch;
import aoc.kingdoms.lukasz.menusInGame.Goods.InGame_Goods;
import aoc.kingdoms.lukasz.menusInGame.Graph.InGame_GraphPopulation;
import aoc.kingdoms.lukasz.menusInGame.Info.InGame_Info;
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.textures.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class InGame_Court extends Menu {
    public static final int ANIMATION_TIME = 60;
    public static long lTime = 0L;
    public static long lTime2 = 0L;
    public static int iActiveCivID = 0;
    public static int FIRE_ID = 0;
    public static boolean inCourt = true;
    public static boolean inSearchProvinces = false;
    public static int modeID = 0;
    public static boolean UPDATE_INCOME_MANPOWER_FROM_VASSAL = false;

    public InGame_Court() {
        List<MenuElement> menuElements = new ArrayList();
        int paddingLeft = Images.boxTitleBORDERWIDTH + CFG.PADDING * 2;
        int paddingLeft2 = Images.boxTitleBORDERWIDTH + CFG.PADDING;
        int menuWidth = ImageManager.getImage(Images.insideTop500).getWidth();
        int menuX = InGame_CourtOptions2.getOtherMenuPosX();
        int menuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY + CFG.PADDING;
        int buttonYPadding = CFG.PADDING * 2;
        int buttonY = 0;
        inCourt = true;
        inSearchProvinces = false;
        if (iActiveCivID != Game.player.iCivID) {
            modeID = 0;
        }

        RulersManager.loadRulerIMG(iActiveCivID);
        menuElements.add(new Text_Title_v2Center(Game.ideologiesManager.getIdeology(Game.getCiv(iActiveCivID).getIdeologyID()).RulerTitle, -1, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 6));
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING * 2;
        int rulerBGY = buttonY - CFG.PADDING;
        menuElements.add(new ButtonRuler2(iActiveCivID, paddingLeft, buttonY) {
            public void actionElement() {
                if (Game.getCiv(InGame_Court.iActiveCivID).getCapitalProvinceID() >= 0) {
                    Game.setActiveProvinceID(Game.getCiv(InGame_Court.iActiveCivID).getCapitalProvinceID());
                    ProvinceBorderManager.action.setProvinceID(Game.iActiveProvince);
                    Game.menuManager.rebuildInGame_Civ();
                }

            }
        });
        int buttonX = paddingLeft + ((MenuElement)menuElements.get(menuElements.size() - 1)).getWidth() + CFG.PADDING;
        int statsH = 0;
        menuElements.add(new Text_StaticBG_RulerTitle(Game.getCiv(iActiveCivID).ruler.Name, buttonX, buttonY, menuWidth - buttonX - paddingLeft, CFG.TEXT_HEIGHT + CFG.PADDING * 6) {
            public void buildElementHover() {
                this.menuElementHover = ButtonRuler2.getHoverRuler(InGame_Court.iActiveCivID, false);
            }

            public String getTextToDraw() {
                try {
                    if (Keyboard.keyboardActionType == KeyboardActionType.INGAME_RULER_NAME) {
                        if (!this.getText().equals(Game.getCiv(InGame_Court.iActiveCivID).ruler.Name)) {
                            this.setText(Game.getCiv(InGame_Court.iActiveCivID).ruler.Name);
                        }

                        return Game.getCiv(InGame_Court.iActiveCivID).ruler.Name + (Keyboard.keyboardActionType == KeyboardActionType.INGAME_RULER_NAME ? Keyboard.getKeyboardVerticalLine() : "");
                    }
                } catch (Exception var2) {
                }

                return super.getTextToDraw();
            }

            public void actionElement() {
                if (Keyboard.keyboardMode && Keyboard.keyboardActionType == KeyboardActionType.INGAME_RULER_NAME) {
                    Game.keyboard.hideKeyboard();
                } else {
                    Game.keyboard.showKeyboard(KeyboardActionType.INGAME_RULER_NAME, Game.getCiv(InGame_Court.iActiveCivID).ruler.Name);
                }

            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        statsH += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        menuElements.add(new Text_StaticBG_Ruler("" + Game.getCiv(iActiveCivID).ruler.BornDay + " " + Game_Calendar.getMonthName(Game.getCiv(iActiveCivID).ruler.BornMonth) + " " + Game.getCiv(iActiveCivID).ruler.BornYear, Game.lang.get("XYearsOld", Math.min(99, Game_Calendar.currentYear - Game.getCiv(iActiveCivID).ruler.BornYear)), buttonX, buttonY, menuWidth - buttonX - paddingLeft, CFG.TEXT_HEIGHT + CFG.PADDING * 3) {
            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Center(Game.getCiv(InGame_Court.iActiveCivID).ruler.Name, CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Born") + ": ", "" + Game.getCiv(InGame_Court.iActiveCivID).ruler.BornDay + " " + Game_Calendar.getMonthName(Game.getCiv(InGame_Court.iActiveCivID).ruler.BornMonth) + " " + Game.getCiv(InGame_Court.iActiveCivID).ruler.BornYear, Images.time, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("XYearsOld", Math.min(99, Game_Calendar.currentYear - Game.getCiv(InGame_Court.iActiveCivID).ruler.BornYear)), "", Images.time, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_RIGHT));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        statsH += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        int tWidth = (menuWidth - buttonX - paddingLeft - CFG.PADDING * 2) / 3;
        statsH = ButtonRuler2.getButtonHeight() - statsH;
        if (Game.getCiv(iActiveCivID).ruler.rulerBonuses.UnitsAttack != 0 || Game.getCiv(iActiveCivID).ruler.rulerBonuses.UnitsDefense != 0 || Game.getCiv(iActiveCivID).ruler.rulerBonuses.GeneralAttack != 0 || Game.getCiv(iActiveCivID).ruler.rulerBonuses.GeneralDefense != 0) {
            tWidth = (menuWidth - buttonX - paddingLeft - CFG.PADDING * 3) / 4;
        }

        menuElements.add(new ButtonReligion2(Game.getCiv(iActiveCivID).getReligionID(), buttonX, buttonY, tWidth, statsH) {
            public void buildElementHover() {
                this.menuElementHover = Game.religionManager.getHoverReligion(this.religionID, InGame_Court.iActiveCivID);
            }

            public void actionElement() {
                InGame_Court_Government.modeID = 0;
                InGame_Court.iActiveCivID = Game.player.iCivID;
                InGame_CourtOptions.iActiveID = InGame_CourtOptions.iGovernmentID;
                InGame_CourtOptions2.disableAllViews();
                Game.menuManager.rebuildInGame_Government();
                Game.menuManager.setVisibleInGame_Court(true);
                InGame_Court.lTime = 0L;
            }
        });
        //1
        buttonX += ((MenuElement)menuElements.get(menuElements.size() - 1)).getWidth() + CFG.PADDING;
        menuElements.add(new ButtonIdeology2(Game.getCiv(iActiveCivID).getIdeologyID(), buttonX, buttonY, tWidth, statsH) {
            public void actionElement() {
                InGame_Court_Government.modeID = 0;
                InGame_Court.iActiveCivID = Game.player.iCivID;
                InGame_CourtOptions.iActiveID = InGame_CourtOptions.iGovernmentID;
                InGame_CourtOptions2.disableAllViews();
                Game.menuManager.rebuildInGame_Government();
                Game.menuManager.setVisibleInGame_Court(true);
                InGame_Court.lTime = 0L;
            }
        });

        menuElements.add(new Text_StaticBG_ID_FlagCiv_SpecialEmpty(CFG.PADDING + Images.boxTitleBORDERWIDTH, rulerBGY, menuWidth - (CFG.PADDING + Images.boxTitleBORDERWIDTH) * 2, ButtonRuler2.getButtonHeight() + CFG.PADDING * 2));
        buttonY = ((MenuElement)menuElements.get(1)).getPosY() + ((MenuElement)menuElements.get(1)).getHeight() + CFG.PADDING * 2;
        menuElements.add(new Text_Title_Diplomacy(Game.lang.get(GameValues.court.COUNCIL_NAME), Images.boxTitleBORDERWIDTH, buttonY, (menuWidth - Images.boxTitleBORDERWIDTH * 2) / 3, CFG.BUTTON_HEIGHT4, modeID == 0) {
            public void actionElement() {
                InGame_Court.modeID = 0;
                InGame_Court.iActiveCivID = Game.player.iCivID;
                Game.menuManager.rebuildInGame_Court();
                Game.menuManager.setVisibleInGame_Court(true);
                InGame_Court.lTime = 0L;
                Game.setRegroupArmyMode(false);
            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(this.getText(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.council, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }

            public int getSFX() {
                return Game.soundsManager.getTab();
            }
        });
        menuElements.add(new Text_Title_Diplomacy(Game.lang.get(Game_Ages.getVassals()), Images.boxTitleBORDERWIDTH + (menuWidth - Images.boxTitleBORDERWIDTH * 2) / 3, buttonY, (menuWidth - Images.boxTitleBORDERWIDTH * 2) / 3, CFG.BUTTON_HEIGHT4, modeID == 1 || modeID == 11) {
            public void actionElement() {
                InGame_Court.modeID = 1;
                InGame_Court.iActiveCivID = Game.player.iCivID;
                Game.menuManager.rebuildInGame_Court();
                Game.menuManager.setVisibleInGame_Court(true);
                InGame_Court.lTime = 0L;
                Game.setRegroupArmyMode(false);
            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(this.getText(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.vassal, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }

            public int getSFX() {
                return Game.soundsManager.getTab();
            }
        });
        menuElements.add(new Text_Title_Diplomacy(Game.lang.get("More"), Images.boxTitleBORDERWIDTH + (menuWidth - Images.boxTitleBORDERWIDTH * 2) / 3 * 2, buttonY, (menuWidth - Images.boxTitleBORDERWIDTH * 2) / 3, CFG.BUTTON_HEIGHT4, modeID == 2) {
            public void actionElement() {
                InGame_Court.modeID = 2;
                InGame_Court.iActiveCivID = Game.player.iCivID;
                Game.menuManager.rebuildInGame_Court();
                Game.menuManager.setVisibleInGame_Court(true);
                InGame_Court.lTime = 0L;
                Game.setRegroupArmyMode(false);
            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(this.getText(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.world, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }

            public int getSFX() {
                return Game.soundsManager.getTab();
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        buttonX = paddingLeft;
        if (modeID == 0) {
            if (iActiveCivID == Game.player.iCivID) {
                int typeW = (menuWidth - paddingLeft * 2 - CFG.PADDING * 4) / 5;
                int typeH = CFG.BUTTON_HEIGHT4;
                menuElements.add(new ButtonIcon("", Images.missions, paddingLeft, buttonY, typeW, typeH) {
                    public void actionElement() {
                        InGame_Court.inCourt = false;
                        InGame_Court.inSearchProvinces = false;
                        InGame_CourtOptions.disableAllViews();
                        Game.menuManager.rebuildInGame_CourtMissions();
                        Game.menuManager.setVisibleInGame_Court(true);
                        InGame_Court.lTime = 0L;
                    }

                    public void buildElementHover() {
                        List<MenuElement_HoverElement> nElements = new ArrayList();
                        List<MenuElement_HoverElement_Type> nData = new ArrayList();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Events"), Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.missions, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements, true);
                    }

                    public int getSFX() {
                        return SoundsManager.getClickSound_CivOptions();
                    }
                });
                int menuHeight = paddingLeft + ((MenuElement)menuElements.get(menuElements.size() - 1)).getWidth() + CFG.PADDING;
                menuElements.add(new ButtonIcon("", Images.encyclopedia, menuHeight, buttonY, typeW, typeH) {
                    public void actionElement() {
                        if (Game.menuManager.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == 15) {
                            Game.menuManager.setVisibleInGame_PopUp(false);
                        } else {
                            InGame_Encyclopedia.sSearch = "";
                            Game.menuManager.rebuildInGame_Encyclopedia();
                        }

                    }

                    public void buildElementHover() {
                        List<MenuElement_HoverElement> nElements = new ArrayList();
                        List<MenuElement_HoverElement_Type> nData = new ArrayList();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Encyclopedia"), Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.encyclopedia, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements, true);
                    }

                    public int getSFX() {
                        return SoundsManager.getClickSound_CivOptions();
                    }
                });
                menuHeight += ((MenuElement)menuElements.get(menuElements.size() - 1)).getWidth() + CFG.PADDING;
                menuElements.add(new ButtonIcon("", Images.development, menuHeight, buttonY, typeW, typeH) {
                    public void actionElement() {
                        InGame_Court.inCourt = false;
                        InGame_Court.inSearchProvinces = false;
                        InGame_CourtOptions.disableAllViews();
                        Game.menuManager.rebuildInGame_CourtStatistics();
                        Game.menuManager.setVisibleInGame_Court(true);
                    }

                    public void buildElementHover() {
                        List<MenuElement_HoverElement> nElements = new ArrayList();
                        List<MenuElement_HoverElement_Type> nData = new ArrayList();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Statistics"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.development, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.getCiv(InGame_Court.iActiveCivID).getCivName(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Flag(InGame_Court.iActiveCivID, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }

                    public int getSFX() {
                        return SoundsManager.getClickSound_CivOptions();
                    }
                });
                menuHeight += ((MenuElement)menuElements.get(menuElements.size() - 1)).getWidth() + CFG.PADDING;
                menuElements.add(new ButtonIcon("", Images.provinces, menuHeight, buttonY, typeW, typeH) {
                    public void actionElement() {
                        InGame_CourtOptions2.actionProvinces(InGame_CourtOptions2.idProvinces);
                    }

                    public void buildElementHover() {
                        List<MenuElement_HoverElement> nElements = new ArrayList();
                        List<MenuElement_HoverElement_Type> nData = new ArrayList();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Provinces"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.provinces, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.getCiv(InGame_Court.iActiveCivID).getCivName(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Flag(InGame_Court.iActiveCivID, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }

                    public int getSFX() {
                        return SoundsManager.getClickSound_CivOptions();
                    }
                });
                menuHeight += ((MenuElement)menuElements.get(menuElements.size() - 1)).getWidth() + CFG.PADDING;
                menuElements.add(new ButtonIcon("", Images.settings, menuHeight, buttonY, typeW, typeH) {
                    public void actionElement() {
                        if (Game.menuManager.getVisibleInGame_Escape()) {
                            Game.menuManager.setVisibleInGame_Escape(false);
                        } else {
                            InGame_CourtOptions2.disableAllViews();
                            Game.menuManager.setVisibleInGame_Escape(true);
                        }

                    }

                    public void buildElementHover() {
                        List<MenuElement_HoverElement> nElements = new ArrayList();
                        List<MenuElement_HoverElement_Type> nData = new ArrayList();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Options"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.settings, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements, true);
                    }

                    public int getSFX() {
                        return SoundsManager.getClickSound_CivOptions();
                    }
                });
                buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
            }

            if (iActiveCivID == Game.player.iCivID) {
                Game.player.playerData.espionage.removeExpiredEspionageMissions();
                if (Game.player.playerData.espionage.iEspionageMissionsSize > 0) {
                    menuElements.add(new Text_Title_v2_TextLR(Game.lang.get("EspionageMission"), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, ""));
                    buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                    int buttonH_Spy = Math.max(CFG.BUTTON_HEIGHT3, Math.max(Math.max(ImageManager.getImage(Images.flag_rect).getHeight(), ImageManager.getImage(Images.spy).getHeight()), CFG.TEXT_HEIGHT) + CFG.PADDING * 2);

                    try {
                        for(int i = 0; i < Game.player.playerData.espionage.iEspionageMissionsSize; ++i) {
                            if (((DiplomacyEspionageMission)Game.player.playerData.espionage.espionageMissions.get(i)).iReportTurnID <= Game_Calendar.TURN_ID) {
                                menuElements.add(new Button_OutlinerEspionageMissionReport(Game.lang.get("Completed"), "", paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH_Spy, Images.spy, ((DiplomacyEspionageMission)Game.player.playerData.espionage.espionageMissions.get(i)).iCivID, ((DiplomacyEspionageMission)Game.player.playerData.espionage.espionageMissions.get(i)).iReportTurnID - DiplomacyManager.sendSpyTime(Game.player.iCivID, ((DiplomacyEspionageMission)Game.player.playerData.espionage.espionageMissions.get(i)).iCivID), ((DiplomacyEspionageMission)Game.player.playerData.espionage.espionageMissions.get(i)).iReportExpiresTurnID - Game_Calendar.TURN_ID, false) {
                                    public void actionElement() {
                                        InGame_CourtOptions2.disableAllViews();
                                        Game.menuManager.rebuildInGame_EspionageReportCourt(this.getCurrent(), Game.player.playerData.espionage.espionageMission_ReportEndTurn(this.getCurrent()));
                                        Game.menuManager.setVisibleInGame_Court(true);
                                        InGame_Court.lTime = 0L;
                                    }
                                });
                                buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                            }
                        }

                        for(int i = 0; i < Game.player.playerData.espionage.iEspionageMissionsSize; ++i) {
                            if (((DiplomacyEspionageMission)Game.player.playerData.espionage.espionageMissions.get(i)).iReportTurnID > Game_Calendar.TURN_ID) {
                                menuElements.add(new Button_OutlinerEspionageMission(Game.lang.get("Progress"), "%", paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH_Spy, Images.spy, ((DiplomacyEspionageMission)Game.player.playerData.espionage.espionageMissions.get(i)).iCivID, ((DiplomacyEspionageMission)Game.player.playerData.espionage.espionageMissions.get(i)).iReportTurnID - DiplomacyManager.sendSpyTime(Game.player.iCivID, ((DiplomacyEspionageMission)Game.player.playerData.espionage.espionageMissions.get(i)).iCivID), ((DiplomacyEspionageMission)Game.player.playerData.espionage.espionageMissions.get(i)).iReportTurnID, false) {
                                    public void actionElement() {
                                    }
                                });
                                buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                            }
                        }
                    } catch (Exception var29) {
                    }
                }
            }

            menuElements.add(new Text_Title_v2_TextLR(Game.lang.get(GameValues.court.ADVISOR_NAME_ADMINISTRATIVE), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, Game.getCiv(iActiveCivID).advisorAdministration.sName != null ? Game.lang.get("XYearsOld", Math.min(99, Game_Calendar.currentYear - Game.getCiv(iActiveCivID).advisorAdministration.iYearOfBirth)) : ""));
            buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING * 2;
            tWidth = menuWidth - ButtonAdvisor.getButtonWidth() - paddingLeft * 2 - CFG.PADDING;
            int maxIconW = ImageManager.getImage(Images.gold).getWidth();
            if (Game.getCiv(iActiveCivID).advisorAdministration.sName == null) {
                menuElements.add(new ButtonAdvisor_No(paddingLeft, buttonY) {
                    public void actionElement() {
                        if (InGame_Court.iActiveCivID == Game.player.iCivID) {
                            if (Game.menuManager.getVisibleInGame_AdvisorRecruit() && InGame_AdvisorRecruit.iActiveAdvisorTypeID == 0) {
                                Game.menuManager.setVisibleInGame_AdvisorRecruit(false);
                            } else {
                                InGame_AdvisorRecruit.iActiveAdvisorTypeID = 0;
                                Game.menuManager.rebuildInGame_AdvisorRecruit();
                            }
                        }

                    }
                });
                buttonX = paddingLeft + ((MenuElement)menuElements.get(menuElements.size() - 1)).getWidth() + CFG.PADDING;
                menuElements.add(new Text_StaticBG(Game.lang.get("NoAdvisor"), CFG.FONT_REGULAR_SMALL, -1, buttonX, buttonY, tWidth, ButtonAdvisor.getButtonHeight()) {
                    public void actionElement() {
                        if (InGame_Court.iActiveCivID == Game.player.iCivID) {
                            if (Game.menuManager.getVisibleInGame_AdvisorRecruit() && InGame_AdvisorRecruit.iActiveAdvisorTypeID == 0) {
                                Game.menuManager.setVisibleInGame_AdvisorRecruit(false);
                            } else {
                                InGame_AdvisorRecruit.iActiveAdvisorTypeID = 0;
                                Game.menuManager.rebuildInGame_AdvisorRecruit();
                            }
                        }

                    }

                    public void buildElementHover() {
                        List<MenuElement_HoverElement> nElements = new ArrayList();
                        List<MenuElement_HoverElement_Type> nData = new ArrayList();
                        if (InGame_Court.iActiveCivID == Game.player.iCivID) {
                            nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("NoAdvisor"), CFG.FONT_BOLD));
                            nData.add(new MenuElement_HoverElement_Type_FlagTitle(InGame_Court.iActiveCivID, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Empty());
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("ClickToHireAnAdvisor"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            this.menuElementHover = new MenuElement_Hover(nElements, true);
                        } else {
                            nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("NoAdvisor"), CFG.FONT_BOLD));
                            nData.add(new MenuElement_HoverElement_Type_FlagTitle(InGame_Court.iActiveCivID, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            this.menuElementHover = new MenuElement_Hover(nElements);
                        }

                    }
                });
            } else {
                menuElements.add(new ButtonAdvisor(paddingLeft, buttonY, Game.getCiv(iActiveCivID).advisorAdministration.sName, Game.getCiv(iActiveCivID).advisorAdministration.imageID, iActiveCivID, -1, 0, Game.getCiv(iActiveCivID).advisorAdministration.sIMG) {
                    public void actionElement() {
                        if (InGame_Court.iActiveCivID == Game.player.iCivID) {
                            if (Game.menuManager.getVisibleInGame_AdvisorRecruit() && InGame_AdvisorRecruit.iActiveAdvisorTypeID == 0) {
                                Game.menuManager.setVisibleInGame_AdvisorRecruit(false);
                            } else {
                                InGame_AdvisorRecruit.iActiveAdvisorTypeID = 0;
                                Game.menuManager.rebuildInGame_AdvisorRecruit();
                            }
                        }

                    }

                    public void actionElementPPM() {
                        if (InGame_Court.iActiveCivID == Game.player.iCivID) {
                            InGame_Court.FIRE_ID = 0;
                            Dialog.setDialogType(DialogType.FIRE_ADVISOR);
                        }

                    }
                });
                buttonX = paddingLeft + ((MenuElement)menuElements.get(menuElements.size() - 1)).getWidth() + CFG.PADDING;
                int statsY = 0;
                int statW = menuWidth - buttonX - paddingLeft;
                int statH = (ButtonAdvisor.getButtonHeight() - CFG.PADDING * 2) / 3;
                menuElements.add(new Text_StaticBG_Advisor_Skill(Game.lang.get("Skill") + ": " + Game.getCiv(iActiveCivID).advisorAdministration.iLevel + " / " + AdvisorManager.getAdvisorMaxLevel(iActiveCivID), buttonX, buttonY, CFG.PADDING, statH) {
                    public void actionElement() {
                        if (InGame_Court.iActiveCivID == Game.player.iCivID && Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.iLevel < AdvisorManager.getAdvisorMaxLevel(InGame_Court.iActiveCivID)) {
                            if (Game.getCiv(InGame_Court.iActiveCivID).fGold < (float)AdvisorManager.getAdvisorPromoteCost(InGame_Court.iActiveCivID, Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.iLevel)) {
                                Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2((float)AdvisorManager.getAdvisorPromoteCost(InGame_Court.iActiveCivID, Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.iLevel), 100), Images.gold);
                            } else if (Game.getCiv(InGame_Court.iActiveCivID).fLegacy < (float)AdvisorManager.getAdvisorPromoteCostLegacy(InGame_Court.iActiveCivID, Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.iLevel)) {
                                Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientLegacy") + ": ", CFG.getPrecision2((float)AdvisorManager.getAdvisorPromoteCostLegacy(InGame_Court.iActiveCivID, Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.iLevel), 100), Images.legacy);
                            }

                            if (AdvisorManager.promoteAdvisor(Game.player.iCivID, 0, false)) {
                                InGame_Info.iCivID = Game.player.iCivID;
                                InGame_Info.iCivID2 = 0;
                                Game.menuManager.rebuildInGame_Info(Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.sName, Game.lang.get("AdvisorSkills") + ": " + Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.iLevel + " / " + AdvisorManager.getAdvisorMaxLevel(InGame_Court.iActiveCivID));
                                InGame_Info.imgID = Images.infoCrown;
                                SteamAchievementsManager.unlockAchievement(SteamAchievementsManager.PROMOTE_ADVISOR);
                            }

                            Game.menuManager.rebuildInGame_CourtSavePos();
                        }

                    }

                    public void buildElementHover() {
                        List<MenuElement_HoverElement> nElements = new ArrayList();
                        List<MenuElement_HoverElement_Type> nData = new ArrayList();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Center(Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.sName, CFG.FONT_BOLD, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("AdvisorSkillLevel") + ": ", "" + Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.iLevel + " / " + AdvisorManager.getAdvisorMaxLevel(InGame_Court.iActiveCivID), Images.skill, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        if (InGame_Court.iActiveCivID == Game.player.iCivID && Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.iLevel < AdvisorManager.getAdvisorMaxLevel(InGame_Court.iActiveCivID)) {
                            nData.add(new MenuElement_HoverElement_Type_Empty());
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("PromoteAdvisor"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.skill, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("AdvisorSkills") + ": ", CFG.FONT_REGULAR_SMALL));
                            nData.add(new MenuElement_HoverElement_Type_Text("+" + CFG.getPrecision2(GameValues.advisors.ADVISOR_BONUSES_UPGRADE_PER_LEVEL * 100.0F, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Cost") + ": ", CFG.FONT_REGULAR_SMALL));
                            nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2((float)AdvisorManager.getAdvisorPromoteCost(InGame_Court.iActiveCivID, Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.iLevel), 100), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                            nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("LegacyPoints") + ": ", CFG.FONT_REGULAR_SMALL));
                            nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2((float)AdvisorManager.getAdvisorPromoteCostLegacy(InGame_Court.iActiveCivID, Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.iLevel), 100), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                            nData.add(new MenuElement_HoverElement_Type_Image(Images.legacy, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                        }

                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }
                });
                ((MenuElement)menuElements.get(menuElements.size() - 1)).setPosX(menuWidth - paddingLeft - ((MenuElement)menuElements.get(menuElements.size() - 1)).getWidth());
                menuElements.add(new Text_StaticBG_Advisor(Game.getCiv(iActiveCivID).advisorAdministration.sName, buttonX, buttonY, ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosX() - buttonX - CFG.PADDING, statH) {
                    public String getTextToDraw() {
                        try {
                            if (Keyboard.keyboardActionType == KeyboardActionType.INGAME_ADVISOR_ADMINISTRATIVE_NAME) {
                                if (!this.getText().equals(Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.sName)) {
                                    this.setText(Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.sName);
                                }

                                return Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.sName + (Keyboard.keyboardActionType == KeyboardActionType.INGAME_ADVISOR_ADMINISTRATIVE_NAME ? Keyboard.getKeyboardVerticalLine() : "");
                            }
                        } catch (Exception var2) {
                        }

                        return super.getTextToDraw();
                    }

                    public void actionElement() {
                        if (Keyboard.keyboardMode && Keyboard.keyboardActionType == KeyboardActionType.INGAME_ADVISOR_ADMINISTRATIVE_NAME) {
                            Game.keyboard.hideKeyboard();
                        } else {
                            Game.keyboard.showKeyboard(KeyboardActionType.INGAME_ADVISOR_ADMINISTRATIVE_NAME, Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.sName);
                        }

                    }

                    public void buildElementHover() {
                        List<MenuElement_HoverElement> nElements = new ArrayList();
                        List<MenuElement_HoverElement_Type> nData = new ArrayList();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Center(Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.sName, CFG.FONT_BOLD, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("AdvisorSkillLevel") + ": ", "" + Game.getCiv(InGame_Court.iActiveCivID).advisorAdministration.iLevel + " / " + AdvisorManager.getAdvisorMaxLevel(InGame_Court.iActiveCivID), Images.skill, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }
                });
                statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                if (Game.getCiv(iActiveCivID).advisorAdministration.TaxEfficiency != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("TaxEfficiency") + "", "+" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorAdministration.TaxEfficiency, 100) + "%", Images.tax, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorAdministration.ProvinceMaintenance != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ProvinceMaintenance") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorAdministration.ProvinceMaintenance, 100) + "%", Images.gold, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorAdministration.GrowthRate != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("GrowthRate") + "", "+" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorAdministration.GrowthRate, 100) + "%", Images.populationGrowth, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorAdministration.ConstructionCost != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ConstructionCost") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorAdministration.ConstructionCost * 100.0F, 100) + "%", Images.construction, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorAdministration.AdministrationBuildingsCost != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("AdministrationBuildingsCost") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorAdministration.AdministrationBuildingsCost * 100.0F, 100) + "%", Images.construction, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorAdministration.EconomyBuildingsCost != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("EconomyBuildingsCost") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorAdministration.EconomyBuildingsCost * 100.0F, 100) + "%", Images.construction, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorAdministration.MilitaryBuildingsCost != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("MilitaryBuildingsCost") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorAdministration.MilitaryBuildingsCost * 100.0F, 100) + "%", Images.construction, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorAdministration.InvestInEconomyCost != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("InvestInEconomyCost") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorAdministration.InvestInEconomyCost * 100.0F, 100) + "%", Game_Calendar.IMG_ECONOMY_UP, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorAdministration.IncreaseTaxEfficiencyCost != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("IncreaseTaxEfficiencyCost") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorAdministration.IncreaseTaxEfficiencyCost * 100.0F, 100) + "%", Images.taxUp, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorAdministration.IncreaseGrowthRateCost != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("IncreaseGrowthRateCost") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorAdministration.IncreaseGrowthRateCost * 100.0F, 100) + "%", Images.populationUp, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorAdministration.DevelopInfrastructureCost != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("DevelopInfrastructureCost") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorAdministration.DevelopInfrastructureCost * 100.0F, 100) + "%", Images.infrastructureUp, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorAdministration.ProductionEfficiency != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ProductionEfficiency") + "", "+" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorAdministration.ProductionEfficiency, 100) + "%", Images.goods, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorAdministration.Research != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ResearchPerMonth") + "", "+" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorAdministration.Research, 100), Game_Calendar.IMG_TECHNOLOGY, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorAdministration.MonthlyLegacy != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("MonthlyLegacy") + "", "+" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorAdministration.MonthlyLegacy, 100), Images.legacy, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorAdministration.GeneralAttack != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("GeneralsAttack") + "", "+" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorAdministration.GeneralAttack, 100), Images.attack, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorAdministration.GeneralDefense != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("GeneralsDefense") + "", "+" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorAdministration.GeneralDefense, 100), Images.defense, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorAdministration.ArmyMaintenance != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ArmyMaintenance") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorAdministration.ArmyMaintenance, 100) + "%", Images.armyMaintenance, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorAdministration.RecruitArmyCost != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ArmyRecruitmentCost") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorAdministration.RecruitArmyCost, 100) + "%", Images.gold, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorAdministration.ConstructionTime != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ConstructionTime") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorAdministration.ConstructionTime * 100.0F, 100) + "%", Images.buildTime, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorAdministration.IncreaseManpowerCost != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("IncreaseManpowerCost") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorAdministration.IncreaseManpowerCost, 100) + "%", Game_Calendar.IMG_MANPOWER, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorAdministration.RecruitmentTime != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("RecruitmentTime") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorAdministration.RecruitmentTime, 100) + "%", Game_Calendar.IMG_MANPOWER_TIME, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorAdministration.LoanInterest != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("LoanInterest") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorAdministration.LoanInterest, 100) + "%", Images.loan, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorAdministration.CoreCost != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("CoreConstruction") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorAdministration.CoreCost, 100) + "%", Images.core, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorAdministration.ReligionCost != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ReligionConversionCost") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorAdministration.ReligionCost, 100) + "%", Images.religion, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorAdministration.IncomeProduction != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("IncomeProduction") + "", "+" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorAdministration.IncomeProduction, 100) + "%", Images.goods, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorAdministration.MaxManpower != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("MaximumManpower") + "", "+" + (int)Game.getCiv(iActiveCivID).advisorAdministration.MaxManpower, Game_Calendar.IMG_MANPOWER_UP, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorAdministration.UnitsAttack != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("UnitsAttack") + "", "+" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorAdministration.UnitsAttack, 100), Images.attack, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorAdministration.UnitsDefense != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("UnitsDefense") + "", "+" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorAdministration.UnitsDefense, 100), Images.defense, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorAdministration.RegimentsLimit != 0) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("RegimentsLimit") + "", "+" + CFG.getPrecision2((float)Game.getCiv(iActiveCivID).advisorAdministration.RegimentsLimit, 1), Images.regimentsLimit, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorAdministration.ImproveRelationsModifier != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ImproveRelationsModifier") + "", "+" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorAdministration.ImproveRelationsModifier, 100) + "%", Images.relations, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorAdministration.ArmyMovementSpeed != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ArmyMovementSpeed") + "", "+" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorAdministration.ArmyMovementSpeed, 100) + "%", Images.movementSpeed, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorAdministration.SiegeEffectiveness != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("SiegeEffectiveness") + "", "+" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorAdministration.SiegeEffectiveness * 100.0F, 100) + "%", Images.siege, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    int var154 = statsY + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }
            }

            menuElements.add(new Text_StaticBG_ID_FlagCiv_SpecialEmpty(CFG.PADDING + Images.boxTitleBORDERWIDTH, buttonY - CFG.PADDING, menuWidth - (CFG.PADDING + Images.boxTitleBORDERWIDTH) * 2, ButtonAdvisor.getButtonHeight() + CFG.PADDING * 2));
            buttonY += ButtonAdvisor.getButtonHeight() + CFG.PADDING * 2;
            menuElements.add(new Text_Title_v2_TextLR(Game.lang.get(GameValues.court.ADVISOR_NAME_ECONOMIC), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, Game.getCiv(iActiveCivID).advisorEconomy.sName != null ? Game.lang.get("XYearsOld", Math.min(99, Game_Calendar.currentYear - Game.getCiv(iActiveCivID).advisorEconomy.iYearOfBirth)) : ""));
            buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING * 2;
            if (Game.getCiv(iActiveCivID).advisorEconomy.sName == null) {
                menuElements.add(new ButtonAdvisor_No(paddingLeft, buttonY) {
                    public void actionElement() {
                        if (InGame_Court.iActiveCivID == Game.player.iCivID) {
                            if (Game.menuManager.getVisibleInGame_AdvisorRecruit() && InGame_AdvisorRecruit.iActiveAdvisorTypeID == 1) {
                                Game.menuManager.setVisibleInGame_AdvisorRecruit(false);
                            } else {
                                InGame_AdvisorRecruit.iActiveAdvisorTypeID = 1;
                                Game.menuManager.rebuildInGame_AdvisorRecruit();
                            }
                        }

                    }
                });
                buttonX = paddingLeft + ((MenuElement)menuElements.get(menuElements.size() - 1)).getWidth() + CFG.PADDING;
                menuElements.add(new Text_StaticBG(Game.lang.get("NoAdvisor"), CFG.FONT_REGULAR_SMALL, -1, buttonX, buttonY, tWidth, ButtonAdvisor.getButtonHeight()) {
                    public void actionElement() {
                        if (InGame_Court.iActiveCivID == Game.player.iCivID) {
                            if (Game.menuManager.getVisibleInGame_AdvisorRecruit() && InGame_AdvisorRecruit.iActiveAdvisorTypeID == 1) {
                                Game.menuManager.setVisibleInGame_AdvisorRecruit(false);
                            } else {
                                InGame_AdvisorRecruit.iActiveAdvisorTypeID = 1;
                                Game.menuManager.rebuildInGame_AdvisorRecruit();
                            }
                        }

                    }

                    public void buildElementHover() {
                        List<MenuElement_HoverElement> nElements = new ArrayList();
                        List<MenuElement_HoverElement_Type> nData = new ArrayList();
                        if (InGame_Court.iActiveCivID == Game.player.iCivID) {
                            nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("NoAdvisor"), CFG.FONT_BOLD));
                            nData.add(new MenuElement_HoverElement_Type_FlagTitle(InGame_Court.iActiveCivID, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Empty());
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("ClickToHireAnAdvisor"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            this.menuElementHover = new MenuElement_Hover(nElements, true);
                        } else {
                            nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("NoAdvisor"), CFG.FONT_BOLD));
                            nData.add(new MenuElement_HoverElement_Type_FlagTitle(InGame_Court.iActiveCivID, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            this.menuElementHover = new MenuElement_Hover(nElements);
                        }

                    }
                });
            } else {
                menuElements.add(new ButtonAdvisor(paddingLeft, buttonY, Game.getCiv(iActiveCivID).advisorEconomy.sName, Game.getCiv(iActiveCivID).advisorEconomy.imageID, iActiveCivID, -1, 1, Game.getCiv(iActiveCivID).advisorEconomy.sIMG) {
                    public void actionElement() {
                        if (InGame_Court.iActiveCivID == Game.player.iCivID) {
                            if (Game.menuManager.getVisibleInGame_AdvisorRecruit() && InGame_AdvisorRecruit.iActiveAdvisorTypeID == 1) {
                                Game.menuManager.setVisibleInGame_AdvisorRecruit(false);
                            } else {
                                InGame_AdvisorRecruit.iActiveAdvisorTypeID = 1;
                                Game.menuManager.rebuildInGame_AdvisorRecruit();
                            }
                        }

                    }

                    public void actionElementPPM() {
                        if (InGame_Court.iActiveCivID == Game.player.iCivID) {
                            InGame_Court.FIRE_ID = 1;
                            Dialog.setDialogType(DialogType.FIRE_ADVISOR);
                        }

                    }
                });
                buttonX = paddingLeft + ((MenuElement)menuElements.get(menuElements.size() - 1)).getWidth() + CFG.PADDING;
                int statsY = 0;
                int statW = menuWidth - buttonX - paddingLeft;
                int statH = (ButtonAdvisor.getButtonHeight() - CFG.PADDING * 2) / 3;
                menuElements.add(new Text_StaticBG_Advisor_Skill(Game.lang.get("Skill") + ": " + Game.getCiv(iActiveCivID).advisorEconomy.iLevel + " / " + AdvisorManager.getAdvisorMaxLevel(iActiveCivID), buttonX, buttonY, CFG.PADDING, statH) {
                    public void actionElement() {
                        if (InGame_Court.iActiveCivID == Game.player.iCivID && Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.iLevel < AdvisorManager.getAdvisorMaxLevel(InGame_Court.iActiveCivID)) {
                            if (Game.getCiv(InGame_Court.iActiveCivID).fGold < (float)AdvisorManager.getAdvisorPromoteCost(InGame_Court.iActiveCivID, Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.iLevel)) {
                                Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2((float)AdvisorManager.getAdvisorPromoteCost(InGame_Court.iActiveCivID, Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.iLevel), 100), Images.gold);
                            } else if (Game.getCiv(InGame_Court.iActiveCivID).fLegacy < (float)AdvisorManager.getAdvisorPromoteCostLegacy(InGame_Court.iActiveCivID, Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.iLevel)) {
                                Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientLegacy") + ": ", CFG.getPrecision2((float)AdvisorManager.getAdvisorPromoteCostLegacy(InGame_Court.iActiveCivID, Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.iLevel), 100), Images.legacy);
                            }

                            if (AdvisorManager.promoteAdvisor(Game.player.iCivID, 1, false)) {
                                InGame_Info.iCivID = Game.player.iCivID;
                                InGame_Info.iCivID2 = 0;
                                Game.menuManager.rebuildInGame_Info(Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.sName, Game.lang.get("AdvisorSkills") + ": " + Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.iLevel + " / " + AdvisorManager.getAdvisorMaxLevel(InGame_Court.iActiveCivID));
                                InGame_Info.imgID = Images.infoCrown;
                                SteamAchievementsManager.unlockAchievement(SteamAchievementsManager.PROMOTE_ADVISOR);
                            }

                            Game.menuManager.rebuildInGame_CourtSavePos();
                        }

                    }

                    public void buildElementHover() {
                        List<MenuElement_HoverElement> nElements = new ArrayList();
                        List<MenuElement_HoverElement_Type> nData = new ArrayList();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Center(Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.sName, CFG.FONT_BOLD, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("AdvisorSkillLevel") + ": ", "" + Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.iLevel + " / " + AdvisorManager.getAdvisorMaxLevel(InGame_Court.iActiveCivID), Images.skill, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        if (InGame_Court.iActiveCivID == Game.player.iCivID && Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.iLevel < AdvisorManager.getAdvisorMaxLevel(InGame_Court.iActiveCivID)) {
                            nData.add(new MenuElement_HoverElement_Type_Empty());
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("PromoteAdvisor"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.skill, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("AdvisorSkills") + ": ", CFG.FONT_REGULAR_SMALL));
                            nData.add(new MenuElement_HoverElement_Type_Text("+" + CFG.getPrecision2(GameValues.advisors.ADVISOR_BONUSES_UPGRADE_PER_LEVEL * 100.0F, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Cost") + ": ", CFG.FONT_REGULAR_SMALL));
                            nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2((float)AdvisorManager.getAdvisorPromoteCost(InGame_Court.iActiveCivID, Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.iLevel), 100), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                            nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("LegacyPoints") + ": ", CFG.FONT_REGULAR_SMALL));
                            nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2((float)AdvisorManager.getAdvisorPromoteCostLegacy(InGame_Court.iActiveCivID, Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.iLevel), 100), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                            nData.add(new MenuElement_HoverElement_Type_Image(Images.legacy, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                        }

                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }
                });
                ((MenuElement)menuElements.get(menuElements.size() - 1)).setPosX(menuWidth - paddingLeft - ((MenuElement)menuElements.get(menuElements.size() - 1)).getWidth());
                menuElements.add(new Text_StaticBG_Advisor(Game.getCiv(iActiveCivID).advisorEconomy.sName, buttonX, buttonY, ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosX() - buttonX - CFG.PADDING, statH) {
                    public String getTextToDraw() {
                        try {
                            if (Keyboard.keyboardActionType == KeyboardActionType.INGAME_ADVISOR_ECONOMIC_NAME) {
                                if (!this.getText().equals(Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.sName)) {
                                    this.setText(Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.sName);
                                }

                                return Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.sName + (Keyboard.keyboardActionType == KeyboardActionType.INGAME_ADVISOR_ECONOMIC_NAME ? Keyboard.getKeyboardVerticalLine() : "");
                            }
                        } catch (Exception var2) {
                        }

                        return super.getTextToDraw();
                    }

                    public void actionElement() {
                        if (Keyboard.keyboardMode && Keyboard.keyboardActionType == KeyboardActionType.INGAME_ADVISOR_ECONOMIC_NAME) {
                            Game.keyboard.hideKeyboard();
                        } else {
                            Game.keyboard.showKeyboard(KeyboardActionType.INGAME_ADVISOR_ECONOMIC_NAME, Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.sName);
                        }

                    }

                    public void buildElementHover() {
                        List<MenuElement_HoverElement> nElements = new ArrayList();
                        List<MenuElement_HoverElement_Type> nData = new ArrayList();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Center(Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.sName, CFG.FONT_BOLD, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("AdvisorSkillLevel") + ": ", "" + Game.getCiv(InGame_Court.iActiveCivID).advisorEconomy.iLevel + " / " + AdvisorManager.getAdvisorMaxLevel(InGame_Court.iActiveCivID), Images.skill, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }
                });
                statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                if (Game.getCiv(iActiveCivID).advisorEconomy.TaxEfficiency != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("TaxEfficiency") + "", "+" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorEconomy.TaxEfficiency, 100) + "%", Images.tax, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorEconomy.ProvinceMaintenance != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ProvinceMaintenance") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorEconomy.ProvinceMaintenance, 100) + "%", Images.gold, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorEconomy.GrowthRate != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("GrowthRate") + "", "+" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorEconomy.GrowthRate, 100) + "%", Images.populationGrowth, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorEconomy.ConstructionCost != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ConstructionCost") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorEconomy.ConstructionCost * 100.0F, 100) + "%", Images.construction, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorEconomy.AdministrationBuildingsCost != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("AdministrationBuildingsCost") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorEconomy.AdministrationBuildingsCost * 100.0F, 100) + "%", Images.construction, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorEconomy.EconomyBuildingsCost != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("EconomyBuildingsCost") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorEconomy.EconomyBuildingsCost * 100.0F, 100) + "%", Images.construction, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorEconomy.MilitaryBuildingsCost != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("MilitaryBuildingsCost") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorEconomy.MilitaryBuildingsCost * 100.0F, 100) + "%", Images.construction, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorEconomy.InvestInEconomyCost != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("InvestInEconomyCost") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorEconomy.InvestInEconomyCost * 100.0F, 100) + "%", Game_Calendar.IMG_ECONOMY_UP, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorEconomy.IncreaseTaxEfficiencyCost != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("IncreaseTaxEfficiencyCost") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorEconomy.IncreaseTaxEfficiencyCost * 100.0F, 100) + "%", Images.taxUp, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorEconomy.IncreaseGrowthRateCost != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("IncreaseGrowthRateCost") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorEconomy.IncreaseGrowthRateCost * 100.0F, 100) + "%", Images.populationUp, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorEconomy.DevelopInfrastructureCost != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("DevelopInfrastructureCost") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorEconomy.DevelopInfrastructureCost * 100.0F, 100) + "%", Images.infrastructureUp, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorEconomy.ProductionEfficiency != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ProductionEfficiency") + "", "+" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorEconomy.ProductionEfficiency, 100) + "%", Images.goods, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorEconomy.Research != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ResearchPerMonth") + "", "+" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorEconomy.Research, 100), Game_Calendar.IMG_TECHNOLOGY, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorEconomy.MonthlyLegacy != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("MonthlyLegacy") + "", "+" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorEconomy.MonthlyLegacy, 100), Images.legacy, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorEconomy.GeneralAttack != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("GeneralsAttack") + "", "+" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorEconomy.GeneralAttack, 100), Images.attack, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorEconomy.GeneralDefense != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("GeneralsDefense") + "", "+" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorEconomy.GeneralDefense, 100), Images.defense, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorEconomy.ArmyMaintenance != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ArmyMaintenance") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorEconomy.ArmyMaintenance, 100) + "%", Images.armyMaintenance, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorEconomy.RecruitArmyCost != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ArmyRecruitmentCost") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorEconomy.RecruitArmyCost, 100) + "%", Images.gold, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorEconomy.ConstructionTime != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ConstructionTime") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorEconomy.ConstructionTime * 100.0F, 100) + "%", Images.buildTime, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorEconomy.IncreaseManpowerCost != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("IncreaseManpowerCost") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorEconomy.IncreaseManpowerCost, 100) + "%", Game_Calendar.IMG_MANPOWER_UP, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorEconomy.RecruitmentTime != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("RecruitmentTime") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorEconomy.RecruitmentTime, 100) + "%", Images.time, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorEconomy.LoanInterest != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("LoanInterest") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorEconomy.LoanInterest, 100) + "%", Images.loan, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorEconomy.CoreCost != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("CoreConstruction") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorEconomy.CoreCost, 100) + "%", Images.core, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorEconomy.ReligionCost != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ReligionConversionCost") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorEconomy.ReligionCost, 100) + "%", Images.religion, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorEconomy.IncomeProduction != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("IncomeProduction") + "", "+" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorEconomy.IncomeProduction, 100) + "%", Images.goods, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorEconomy.MaxManpower != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("MaximumManpower") + "", "+" + (int)Game.getCiv(iActiveCivID).advisorEconomy.MaxManpower, Game_Calendar.IMG_MANPOWER_UP, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorEconomy.UnitsAttack != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("UnitsAttack") + "", "+" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorEconomy.UnitsAttack, 100), Images.attack, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorEconomy.UnitsDefense != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("UnitsDefense") + "", "+" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorEconomy.UnitsDefense, 100), Images.defense, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorEconomy.RegimentsLimit != 0) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("RegimentsLimit") + "", "+" + CFG.getPrecision2((float)Game.getCiv(iActiveCivID).advisorEconomy.RegimentsLimit, 1), Images.regimentsLimit, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorEconomy.ImproveRelationsModifier != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ImproveRelationsModifier") + "", "+" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorEconomy.ImproveRelationsModifier, 100) + "%", Images.relations, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorEconomy.ArmyMovementSpeed != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ArmyMovementSpeed") + "", "+" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorEconomy.ArmyMovementSpeed, 100) + "%", Images.movementSpeed, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorEconomy.SiegeEffectiveness != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("SiegeEffectiveness") + "", "+" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorEconomy.SiegeEffectiveness * 100.0F, 100) + "%", Images.siege, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    int var155 = statsY + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }
            }

            menuElements.add(new Text_StaticBG_ID_FlagCiv_SpecialEmpty(CFG.PADDING + Images.boxTitleBORDERWIDTH, buttonY - CFG.PADDING, menuWidth - (CFG.PADDING + Images.boxTitleBORDERWIDTH) * 2, ButtonAdvisor.getButtonHeight() + CFG.PADDING * 2));
            buttonY += ButtonAdvisor.getButtonHeight() + CFG.PADDING * 2;
            menuElements.add(new Text_Title_v2_TextLR(Game.lang.get(GameValues.court.ADVISOR_NAME_INNOVATION), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, Game.getCiv(iActiveCivID).advisorTechnology.sName != null ? Game.lang.get("XYearsOld", Math.min(99, Game_Calendar.currentYear - Game.getCiv(iActiveCivID).advisorTechnology.iYearOfBirth)) : ""));
            buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING * 2;
            if (Game.getCiv(iActiveCivID).advisorTechnology.sName == null) {
                menuElements.add(new ButtonAdvisor_No(paddingLeft, buttonY) {
                    public void actionElement() {
                        if (InGame_Court.iActiveCivID == Game.player.iCivID) {
                            if (Game.menuManager.getVisibleInGame_AdvisorRecruit() && InGame_AdvisorRecruit.iActiveAdvisorTypeID == 2) {
                                Game.menuManager.setVisibleInGame_AdvisorRecruit(false);
                            } else {
                                InGame_AdvisorRecruit.iActiveAdvisorTypeID = 2;
                                Game.menuManager.rebuildInGame_AdvisorRecruit();
                            }
                        }

                    }
                });
                buttonX = paddingLeft + ((MenuElement)menuElements.get(menuElements.size() - 1)).getWidth() + CFG.PADDING;
                menuElements.add(new Text_StaticBG(Game.lang.get("NoAdvisor"), CFG.FONT_REGULAR_SMALL, -1, buttonX, buttonY, tWidth, ButtonAdvisor.getButtonHeight()) {
                    public void actionElement() {
                        if (InGame_Court.iActiveCivID == Game.player.iCivID) {
                            if (Game.menuManager.getVisibleInGame_AdvisorRecruit() && InGame_AdvisorRecruit.iActiveAdvisorTypeID == 2) {
                                Game.menuManager.setVisibleInGame_AdvisorRecruit(false);
                            } else {
                                InGame_AdvisorRecruit.iActiveAdvisorTypeID = 2;
                                Game.menuManager.rebuildInGame_AdvisorRecruit();
                            }
                        }

                    }

                    public void buildElementHover() {
                        List<MenuElement_HoverElement> nElements = new ArrayList();
                        List<MenuElement_HoverElement_Type> nData = new ArrayList();
                        if (InGame_Court.iActiveCivID == Game.player.iCivID) {
                            nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("NoAdvisor"), CFG.FONT_BOLD));
                            nData.add(new MenuElement_HoverElement_Type_FlagTitle(InGame_Court.iActiveCivID, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Empty());
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("ClickToHireAnAdvisor"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            this.menuElementHover = new MenuElement_Hover(nElements, true);
                        } else {
                            nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("NoAdvisor"), CFG.FONT_BOLD));
                            nData.add(new MenuElement_HoverElement_Type_FlagTitle(InGame_Court.iActiveCivID, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            this.menuElementHover = new MenuElement_Hover(nElements);
                        }

                    }
                });
            } else {
                menuElements.add(new ButtonAdvisor(paddingLeft, buttonY, Game.getCiv(iActiveCivID).advisorTechnology.sName, Game.getCiv(iActiveCivID).advisorTechnology.imageID, iActiveCivID, -1, 2, Game.getCiv(iActiveCivID).advisorTechnology.sIMG) {
                    public void actionElement() {
                        if (InGame_Court.iActiveCivID == Game.player.iCivID) {
                            if (Game.menuManager.getVisibleInGame_AdvisorRecruit() && InGame_AdvisorRecruit.iActiveAdvisorTypeID == 2) {
                                Game.menuManager.setVisibleInGame_AdvisorRecruit(false);
                            } else {
                                InGame_AdvisorRecruit.iActiveAdvisorTypeID = 2;
                                Game.menuManager.rebuildInGame_AdvisorRecruit();
                            }
                        }

                    }

                    public void actionElementPPM() {
                        if (InGame_Court.iActiveCivID == Game.player.iCivID) {
                            InGame_Court.FIRE_ID = 2;
                            Dialog.setDialogType(DialogType.FIRE_ADVISOR);
                        }

                    }
                });
                buttonX = paddingLeft + ((MenuElement)menuElements.get(menuElements.size() - 1)).getWidth() + CFG.PADDING;
                int statsY = 0;
                int statW = menuWidth - buttonX - paddingLeft;
                int statH = (ButtonAdvisor.getButtonHeight() - CFG.PADDING * 2) / 3;
                menuElements.add(new Text_StaticBG_Advisor_Skill(Game.lang.get("Skill") + ": " + Game.getCiv(iActiveCivID).advisorTechnology.iLevel + " / " + AdvisorManager.getAdvisorMaxLevel(iActiveCivID), buttonX, buttonY, CFG.PADDING, statH) {
                    public void actionElement() {
                        if (InGame_Court.iActiveCivID == Game.player.iCivID && Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.iLevel < AdvisorManager.getAdvisorMaxLevel(InGame_Court.iActiveCivID)) {
                            if (Game.getCiv(InGame_Court.iActiveCivID).fGold < (float)AdvisorManager.getAdvisorPromoteCost(InGame_Court.iActiveCivID, Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.iLevel)) {
                                Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2((float)AdvisorManager.getAdvisorPromoteCost(InGame_Court.iActiveCivID, Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.iLevel), 100), Images.gold);
                            } else if (Game.getCiv(InGame_Court.iActiveCivID).fLegacy < (float)AdvisorManager.getAdvisorPromoteCostLegacy(InGame_Court.iActiveCivID, Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.iLevel)) {
                                Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientLegacy") + ": ", CFG.getPrecision2((float)AdvisorManager.getAdvisorPromoteCostLegacy(InGame_Court.iActiveCivID, Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.iLevel), 100), Images.legacy);
                            }

                            if (AdvisorManager.promoteAdvisor(Game.player.iCivID, 2, false)) {
                                InGame_Info.iCivID = Game.player.iCivID;
                                InGame_Info.iCivID2 = 0;
                                Game.menuManager.rebuildInGame_Info(Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.sName, Game.lang.get("AdvisorSkills") + ": " + Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.iLevel + " / " + AdvisorManager.getAdvisorMaxLevel(InGame_Court.iActiveCivID));
                                InGame_Info.imgID = Images.infoCrown;
                                SteamAchievementsManager.unlockAchievement(SteamAchievementsManager.PROMOTE_ADVISOR);
                            }

                            Game.menuManager.rebuildInGame_CourtSavePos();
                        }

                    }

                    public void buildElementHover() {
                        List<MenuElement_HoverElement> nElements = new ArrayList();
                        List<MenuElement_HoverElement_Type> nData = new ArrayList();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Center(Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.sName, CFG.FONT_BOLD, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("AdvisorSkillLevel") + ": ", "" + Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.iLevel + " / " + AdvisorManager.getAdvisorMaxLevel(InGame_Court.iActiveCivID), Images.skill, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        if (InGame_Court.iActiveCivID == Game.player.iCivID && Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.iLevel < AdvisorManager.getAdvisorMaxLevel(InGame_Court.iActiveCivID)) {
                            nData.add(new MenuElement_HoverElement_Type_Empty());
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("PromoteAdvisor"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.skill, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("AdvisorSkills") + ": ", CFG.FONT_REGULAR_SMALL));
                            nData.add(new MenuElement_HoverElement_Type_Text("+" + CFG.getPrecision2(GameValues.advisors.ADVISOR_BONUSES_UPGRADE_PER_LEVEL * 100.0F, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Cost") + ": ", CFG.FONT_REGULAR_SMALL));
                            nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2((float)AdvisorManager.getAdvisorPromoteCost(InGame_Court.iActiveCivID, Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.iLevel), 100), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                            nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("LegacyPoints") + ": ", CFG.FONT_REGULAR_SMALL));
                            nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2((float)AdvisorManager.getAdvisorPromoteCostLegacy(InGame_Court.iActiveCivID, Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.iLevel), 100), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                            nData.add(new MenuElement_HoverElement_Type_Image(Images.legacy, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                        }

                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }
                });
                ((MenuElement)menuElements.get(menuElements.size() - 1)).setPosX(menuWidth - paddingLeft - ((MenuElement)menuElements.get(menuElements.size() - 1)).getWidth());
                menuElements.add(new Text_StaticBG_Advisor(Game.getCiv(iActiveCivID).advisorTechnology.sName, buttonX, buttonY, ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosX() - buttonX - CFG.PADDING, statH) {
                    public String getTextToDraw() {
                        try {
                            if (Keyboard.keyboardActionType == KeyboardActionType.INGAME_ADVISOR_INNOVATION_NAME) {
                                if (!this.getText().equals(Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.sName)) {
                                    this.setText(Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.sName);
                                }

                                return Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.sName + (Keyboard.keyboardActionType == KeyboardActionType.INGAME_ADVISOR_INNOVATION_NAME ? Keyboard.getKeyboardVerticalLine() : "");
                            }
                        } catch (Exception var2) {
                        }

                        return super.getTextToDraw();
                    }

                    public void actionElement() {
                        if (Keyboard.keyboardMode && Keyboard.keyboardActionType == KeyboardActionType.INGAME_ADVISOR_INNOVATION_NAME) {
                            Game.keyboard.hideKeyboard();
                        } else {
                            Game.keyboard.showKeyboard(KeyboardActionType.INGAME_ADVISOR_INNOVATION_NAME, Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.sName);
                        }

                    }

                    public void buildElementHover() {
                        List<MenuElement_HoverElement> nElements = new ArrayList();
                        List<MenuElement_HoverElement_Type> nData = new ArrayList();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Center(Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.sName, CFG.FONT_BOLD, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("AdvisorSkillLevel") + ": ", "" + Game.getCiv(InGame_Court.iActiveCivID).advisorTechnology.iLevel + " / " + AdvisorManager.getAdvisorMaxLevel(InGame_Court.iActiveCivID), Images.skill, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }
                });
                statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                if (Game.getCiv(iActiveCivID).advisorTechnology.TaxEfficiency != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("TaxEfficiency") + "", "+" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorTechnology.TaxEfficiency, 100) + "%", Images.tax, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorTechnology.ProvinceMaintenance != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ProvinceMaintenance") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorTechnology.ProvinceMaintenance, 100) + "%", Images.gold, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorTechnology.GrowthRate != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("GrowthRate") + "", "+" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorTechnology.GrowthRate, 100) + "%", Images.populationGrowth, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorTechnology.ConstructionCost != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ConstructionCost") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorTechnology.ConstructionCost * 100.0F, 100) + "%", Images.construction, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorTechnology.AdministrationBuildingsCost != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("AdministrationBuildingsCost") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorTechnology.AdministrationBuildingsCost * 100.0F, 100) + "%", Images.construction, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorTechnology.EconomyBuildingsCost != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("EconomyBuildingsCost") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorTechnology.EconomyBuildingsCost * 100.0F, 100) + "%", Images.construction, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorTechnology.MilitaryBuildingsCost != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("MilitaryBuildingsCost") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorTechnology.MilitaryBuildingsCost * 100.0F, 100) + "%", Images.construction, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorTechnology.InvestInEconomyCost != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("InvestInEconomyCost") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorTechnology.InvestInEconomyCost * 100.0F, 100) + "%", Game_Calendar.IMG_ECONOMY_UP, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorTechnology.IncreaseTaxEfficiencyCost != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("IncreaseTaxEfficiencyCost") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorTechnology.IncreaseTaxEfficiencyCost * 100.0F, 100) + "%", Images.taxUp, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorTechnology.IncreaseGrowthRateCost != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("IncreaseGrowthRateCost") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorTechnology.IncreaseGrowthRateCost * 100.0F, 100) + "%", Images.populationUp, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorTechnology.DevelopInfrastructureCost != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("DevelopInfrastructureCost") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorTechnology.DevelopInfrastructureCost * 100.0F, 100) + "%", Images.infrastructureUp, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorTechnology.ProductionEfficiency != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ProductionEfficiency") + "", "+" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorTechnology.ProductionEfficiency, 100) + "%", Images.goods, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorTechnology.Research != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ResearchPerMonth") + "", "+" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorTechnology.Research, 100), Game_Calendar.IMG_TECHNOLOGY, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorTechnology.MonthlyLegacy != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("MonthlyLegacy") + "", "+" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorTechnology.MonthlyLegacy, 100), Images.legacy, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorTechnology.GeneralAttack != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("GeneralsAttack") + "", "+" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorTechnology.GeneralAttack, 100), Images.attack, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorTechnology.GeneralDefense != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("GeneralsDefense") + "", "+" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorTechnology.GeneralDefense, 100), Images.defense, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorTechnology.ArmyMaintenance != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ArmyMaintenance") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorTechnology.ArmyMaintenance, 100) + "%", Images.armyMaintenance, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorTechnology.RecruitArmyCost != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ArmyRecruitmentCost") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorTechnology.RecruitArmyCost, 100) + "%", Images.gold, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorTechnology.ConstructionTime != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ConstructionTime") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorTechnology.ConstructionTime * 100.0F, 100) + "%", Images.buildTime, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorTechnology.IncreaseManpowerCost != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("IncreaseManpowerCost") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorTechnology.IncreaseManpowerCost, 100) + "%", Game_Calendar.IMG_MANPOWER_UP, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorTechnology.RecruitmentTime != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("RecruitmentTime") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorTechnology.RecruitmentTime, 100) + "%", Game_Calendar.IMG_MANPOWER_TIME, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorTechnology.LoanInterest != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("LoanInterest") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorTechnology.LoanInterest, 100) + "%", Images.loan, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorTechnology.CoreCost != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("CoreConstruction") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorTechnology.CoreCost, 100) + "%", Images.core, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorTechnology.ReligionCost != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ReligionConversionCost") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorTechnology.ReligionCost, 100) + "%", Images.religion, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorTechnology.IncomeProduction != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("IncomeProduction") + "", "+" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorTechnology.IncomeProduction, 100) + "%", Images.goods, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorTechnology.MaxManpower != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("MaximumManpower") + "", "+" + (int)Game.getCiv(iActiveCivID).advisorTechnology.MaxManpower, Game_Calendar.IMG_MANPOWER_UP, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorTechnology.UnitsAttack != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("UnitsAttack") + "", "+" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorTechnology.UnitsAttack, 100), Images.attack, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorTechnology.UnitsDefense != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("UnitsDefense") + "", "+" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorTechnology.UnitsDefense, 100), Images.defense, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorTechnology.RegimentsLimit != 0) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("RegimentsLimit") + "", "+" + CFG.getPrecision2((float)Game.getCiv(iActiveCivID).advisorTechnology.RegimentsLimit, 1), Images.regimentsLimit, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorTechnology.ImproveRelationsModifier != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ImproveRelationsModifier") + "", "+" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorTechnology.ImproveRelationsModifier, 100) + "%", Images.relations, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorTechnology.ArmyMovementSpeed != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ArmyMovementSpeed") + "", "+" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorTechnology.ArmyMovementSpeed, 100) + "%", Images.movementSpeed, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorTechnology.SiegeEffectiveness != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("SiegeEffectiveness") + "", "+" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorTechnology.SiegeEffectiveness * 100.0F, 100) + "%", Images.siege, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    int var156 = statsY + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }
            }

            menuElements.add(new Text_StaticBG_ID_FlagCiv_SpecialEmpty(CFG.PADDING + Images.boxTitleBORDERWIDTH, buttonY - CFG.PADDING, menuWidth - (CFG.PADDING + Images.boxTitleBORDERWIDTH) * 2, ButtonAdvisor.getButtonHeight() + CFG.PADDING * 2));
            buttonY += ButtonAdvisor.getButtonHeight() + CFG.PADDING * 2;
            menuElements.add(new Text_Title_v2_TextLR(Game.lang.get(GameValues.court.ADVISOR_NAME_MILITARY), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, Game.getCiv(iActiveCivID).advisorMilitary.sName != null ? Game.lang.get("XYearsOld", Math.min(99, Game_Calendar.currentYear - Game.getCiv(iActiveCivID).advisorMilitary.iYearOfBirth)) : ""));
            buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING * 2;
            if (Game.getCiv(iActiveCivID).advisorMilitary.sName == null) {
                menuElements.add(new ButtonAdvisor_No(paddingLeft, buttonY) {
                    public void actionElement() {
                        if (InGame_Court.iActiveCivID == Game.player.iCivID) {
                            if (Game.menuManager.getVisibleInGame_AdvisorRecruit() && InGame_AdvisorRecruit.iActiveAdvisorTypeID == 3) {
                                Game.menuManager.setVisibleInGame_AdvisorRecruit(false);
                            } else {
                                InGame_AdvisorRecruit.iActiveAdvisorTypeID = 3;
                                Game.menuManager.rebuildInGame_AdvisorRecruit();
                            }
                        }

                    }
                });
                buttonX = paddingLeft + ((MenuElement)menuElements.get(menuElements.size() - 1)).getWidth() + CFG.PADDING;
                menuElements.add(new Text_StaticBG(Game.lang.get("NoAdvisor"), CFG.FONT_REGULAR_SMALL, -1, buttonX, buttonY, tWidth, ButtonAdvisor.getButtonHeight()) {
                    public void actionElement() {
                        if (InGame_Court.iActiveCivID == Game.player.iCivID) {
                            if (Game.menuManager.getVisibleInGame_AdvisorRecruit() && InGame_AdvisorRecruit.iActiveAdvisorTypeID == 3) {
                                Game.menuManager.setVisibleInGame_AdvisorRecruit(false);
                            } else {
                                InGame_AdvisorRecruit.iActiveAdvisorTypeID = 3;
                                Game.menuManager.rebuildInGame_AdvisorRecruit();
                            }
                        }

                    }

                    public void buildElementHover() {
                        List<MenuElement_HoverElement> nElements = new ArrayList();
                        List<MenuElement_HoverElement_Type> nData = new ArrayList();
                        if (InGame_Court.iActiveCivID == Game.player.iCivID) {
                            nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("NoAdvisor"), CFG.FONT_BOLD));
                            nData.add(new MenuElement_HoverElement_Type_FlagTitle(InGame_Court.iActiveCivID, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Empty());
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("ClickToHireAnAdvisor"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            this.menuElementHover = new MenuElement_Hover(nElements, true);
                        } else {
                            nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.lang.get("NoAdvisor"), CFG.FONT_BOLD));
                            nData.add(new MenuElement_HoverElement_Type_FlagTitle(InGame_Court.iActiveCivID, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            this.menuElementHover = new MenuElement_Hover(nElements);
                        }

                    }
                });
            } else {
                menuElements.add(new ButtonAdvisorGeneral(paddingLeft, buttonY, Game.getCiv(iActiveCivID).advisorMilitary.sName, Game.getCiv(iActiveCivID).advisorMilitary.imageID, iActiveCivID, -1, Game.getCiv(iActiveCivID).advisorMilitary.sIMG) {
                    public void actionElement() {
                        if (InGame_Court.iActiveCivID == Game.player.iCivID) {
                            if (Game.menuManager.getVisibleInGame_AdvisorRecruit() && InGame_AdvisorRecruit.iActiveAdvisorTypeID == 3) {
                                Game.menuManager.setVisibleInGame_AdvisorRecruit(false);
                            } else {
                                InGame_AdvisorRecruit.iActiveAdvisorTypeID = 3;
                                Game.menuManager.rebuildInGame_AdvisorRecruit();
                            }
                        }

                    }

                    public void actionElementPPM() {
                        if (InGame_Court.iActiveCivID == Game.player.iCivID) {
                            InGame_Court.FIRE_ID = 3;
                            Dialog.setDialogType(DialogType.FIRE_ADVISOR);
                        }

                    }
                });
                buttonX = paddingLeft + ((MenuElement)menuElements.get(menuElements.size() - 1)).getWidth() + CFG.PADDING;
                int statsY = 0;
                int statW = menuWidth - buttonX - paddingLeft;
                int statH = (ButtonAdvisor.getButtonHeight() - CFG.PADDING * 2) / 3;
                menuElements.add(new Text_StaticBG_Advisor_Skill(Game.lang.get("Skill") + ": " + Game.getCiv(iActiveCivID).advisorMilitary.iLevel + " / " + AdvisorManager.getAdvisorMaxLevel(iActiveCivID), buttonX, buttonY, CFG.PADDING, statH) {
                    public void actionElement() {
                        if (InGame_Court.iActiveCivID == Game.player.iCivID && Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.iLevel < AdvisorManager.getAdvisorMaxLevel(InGame_Court.iActiveCivID)) {
                            if (Game.getCiv(InGame_Court.iActiveCivID).fGold < (float)AdvisorManager.getAdvisorPromoteCost(InGame_Court.iActiveCivID, Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.iLevel)) {
                                Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2((float)AdvisorManager.getAdvisorPromoteCost(InGame_Court.iActiveCivID, Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.iLevel), 100), Images.gold);
                            } else if (Game.getCiv(InGame_Court.iActiveCivID).fLegacy < (float)AdvisorManager.getAdvisorPromoteCostLegacy(InGame_Court.iActiveCivID, Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.iLevel)) {
                                Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientLegacy") + ": ", CFG.getPrecision2((float)AdvisorManager.getAdvisorPromoteCostLegacy(InGame_Court.iActiveCivID, Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.iLevel), 100), Images.legacy);
                            }

                            if (AdvisorManager.promoteAdvisor(Game.player.iCivID, 3, false)) {
                                InGame_Info.iCivID = Game.player.iCivID;
                                InGame_Info.iCivID2 = 0;
                                Game.menuManager.rebuildInGame_Info(Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.sName, Game.lang.get("AdvisorSkills") + ": " + Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.iLevel + " / " + AdvisorManager.getAdvisorMaxLevel(InGame_Court.iActiveCivID));
                                InGame_Info.imgID = Images.infoCrown;
                                SteamAchievementsManager.unlockAchievement(SteamAchievementsManager.PROMOTE_ADVISOR);
                            }

                            Game.menuManager.rebuildInGame_CourtSavePos();
                        }

                    }

                    public void buildElementHover() {
                        List<MenuElement_HoverElement> nElements = new ArrayList();
                        List<MenuElement_HoverElement_Type> nData = new ArrayList();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Center(Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.sName, CFG.FONT_BOLD, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("AdvisorSkillLevel") + ": ", "" + Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.iLevel + " / " + AdvisorManager.getAdvisorMaxLevel(InGame_Court.iActiveCivID), Images.skill, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        if (InGame_Court.iActiveCivID == Game.player.iCivID && Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.iLevel < AdvisorManager.getAdvisorMaxLevel(InGame_Court.iActiveCivID)) {
                            nData.add(new MenuElement_HoverElement_Type_Empty());
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("PromoteAdvisor"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.skill, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("AdvisorSkills") + ": ", CFG.FONT_REGULAR_SMALL));
                            nData.add(new MenuElement_HoverElement_Type_Text("+" + CFG.getPrecision2(GameValues.advisors.ADVISOR_BONUSES_UPGRADE_PER_LEVEL * 100.0F, 100) + "%", CFG.FONT_BOLD_SMALL, Colors.COLOR_TEXT_MODIFIER_POSITIVE));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Cost") + ": ", CFG.FONT_REGULAR_SMALL));
                            nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2((float)AdvisorManager.getAdvisorPromoteCost(InGame_Court.iActiveCivID, Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.iLevel), 100), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                            nData.add(new MenuElement_HoverElement_Type_Image(Images.gold, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("LegacyPoints") + ": ", CFG.FONT_REGULAR_SMALL));
                            nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2((float)AdvisorManager.getAdvisorPromoteCostLegacy(InGame_Court.iActiveCivID, Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.iLevel), 100), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                            nData.add(new MenuElement_HoverElement_Type_Image(Images.legacy, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                        }

                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }
                });
                ((MenuElement)menuElements.get(menuElements.size() - 1)).setPosX(menuWidth - paddingLeft - ((MenuElement)menuElements.get(menuElements.size() - 1)).getWidth());
                menuElements.add(new Text_StaticBG_Advisor(Game.getCiv(iActiveCivID).advisorMilitary.sName, buttonX, buttonY, ((MenuElement)menuElements.get(menuElements.size() - 1)).getPosX() - buttonX - CFG.PADDING, statH) {
                    public String getTextToDraw() {
                        try {
                            if (Keyboard.keyboardActionType == KeyboardActionType.INGAME_ADVISOR_MILITARY_NAME) {
                                if (!this.getText().equals(Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.sName)) {
                                    this.setText(Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.sName);
                                }

                                return Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.sName + (Keyboard.keyboardActionType == KeyboardActionType.INGAME_ADVISOR_MILITARY_NAME ? Keyboard.getKeyboardVerticalLine() : "");
                            }
                        } catch (Exception var2) {
                        }

                        return super.getTextToDraw();
                    }

                    public void actionElement() {
                        if (Keyboard.keyboardMode && Keyboard.keyboardActionType == KeyboardActionType.INGAME_ADVISOR_MILITARY_NAME) {
                            Game.keyboard.hideKeyboard();
                        } else {
                            Game.keyboard.showKeyboard(KeyboardActionType.INGAME_ADVISOR_MILITARY_NAME, Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.sName);
                        }

                    }

                    public void buildElementHover() {
                        List<MenuElement_HoverElement> nElements = new ArrayList();
                        List<MenuElement_HoverElement_Type> nData = new ArrayList();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Center(Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.sName, CFG.FONT_BOLD, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("AdvisorSkillLevel") + ": ", "" + Game.getCiv(InGame_Court.iActiveCivID).advisorMilitary.iLevel + " / " + AdvisorManager.getAdvisorMaxLevel(InGame_Court.iActiveCivID), Images.skill, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }
                });
                statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                if (Game.getCiv(iActiveCivID).advisorMilitary.TaxEfficiency != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("TaxEfficiency") + "", "+" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorMilitary.TaxEfficiency, 100) + "%", Images.tax, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorMilitary.ProvinceMaintenance != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ProvinceMaintenance") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorMilitary.ProvinceMaintenance, 100) + "%", Images.gold, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorMilitary.GrowthRate != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("GrowthRate") + "", "+" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorMilitary.GrowthRate, 100) + "%", Images.populationGrowth, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorMilitary.ConstructionCost != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ConstructionCost") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorMilitary.ConstructionCost * 100.0F, 100) + "%", Images.construction, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorMilitary.AdministrationBuildingsCost != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("AdministrationBuildingsCost") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorMilitary.AdministrationBuildingsCost * 100.0F, 100) + "%", Images.construction, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorMilitary.EconomyBuildingsCost != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("EconomyBuildingsCost") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorMilitary.EconomyBuildingsCost * 100.0F, 100) + "%", Images.construction, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorMilitary.MilitaryBuildingsCost != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("MilitaryBuildingsCost") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorMilitary.MilitaryBuildingsCost * 100.0F, 100) + "%", Images.construction, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorMilitary.InvestInEconomyCost != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("InvestInEconomyCost") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorMilitary.InvestInEconomyCost * 100.0F, 100) + "%", Game_Calendar.IMG_ECONOMY_UP, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorMilitary.IncreaseTaxEfficiencyCost != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("IncreaseTaxEfficiencyCost") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorMilitary.IncreaseTaxEfficiencyCost * 100.0F, 100) + "%", Images.taxUp, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorMilitary.IncreaseGrowthRateCost != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("IncreaseGrowthRateCost") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorMilitary.IncreaseGrowthRateCost * 100.0F, 100) + "%", Images.populationUp, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorMilitary.DevelopInfrastructureCost != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("DevelopInfrastructureCost") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorMilitary.DevelopInfrastructureCost * 100.0F, 100) + "%", Images.infrastructureUp, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorMilitary.ProductionEfficiency != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ProductionEfficiency") + "", "+" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorMilitary.ProductionEfficiency, 100) + "%", Images.goods, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorMilitary.Research != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ResearchPerMonth") + "", "+" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorMilitary.Research, 100), Game_Calendar.IMG_TECHNOLOGY, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorMilitary.MonthlyLegacy != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("MonthlyLegacy") + "", "+" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorMilitary.MonthlyLegacy, 100), Images.legacy, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorMilitary.GeneralAttack != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("GeneralsAttack") + "", "+" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorMilitary.GeneralAttack, 100), Images.attack, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorMilitary.GeneralDefense != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("GeneralsDefense") + "", "+" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorMilitary.GeneralDefense, 100), Images.defense, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorMilitary.ArmyMaintenance != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ArmyMaintenance") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorMilitary.ArmyMaintenance, 100) + "%", Images.armyMaintenance, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorMilitary.RecruitArmyCost != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ArmyRecruitmentCost") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorMilitary.RecruitArmyCost, 100) + "%", Images.gold, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorMilitary.ConstructionTime != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ConstructionTime") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorMilitary.ConstructionTime * 100.0F, 100) + "%", Images.buildTime, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorMilitary.IncreaseManpowerCost != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("IncreaseManpowerCost") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorMilitary.IncreaseManpowerCost, 100) + "%", Game_Calendar.IMG_MANPOWER_UP, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorMilitary.RecruitmentTime != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("RecruitmentTime") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorMilitary.RecruitmentTime, 100) + "%", Game_Calendar.IMG_MANPOWER_TIME, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorMilitary.LoanInterest != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("LoanInterest") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorMilitary.LoanInterest, 100) + "%", Images.loan, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorMilitary.CoreCost != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("CoreConstruction") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorMilitary.CoreCost, 100) + "%", Images.core, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorMilitary.ReligionCost != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ReligionConversionCost") + "", "" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorMilitary.ReligionCost, 100) + "%", Images.religion, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorMilitary.IncomeProduction != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("IncomeProduction") + "", "+" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorMilitary.IncomeProduction, 100) + "%", Images.goods, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorMilitary.MaxManpower != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("MaximumManpower") + "", "+" + (int)Game.getCiv(iActiveCivID).advisorMilitary.MaxManpower, Game_Calendar.IMG_MANPOWER_UP, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorMilitary.UnitsAttack != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("UnitsAttack") + "", "+" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorMilitary.UnitsAttack, 100), Images.attack, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorMilitary.UnitsDefense != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("UnitsDefense") + "", "+" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorMilitary.UnitsDefense, 100), Images.defense, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorMilitary.RegimentsLimit != 0) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("RegimentsLimit") + "", "+" + CFG.getPrecision2((float)Game.getCiv(iActiveCivID).advisorMilitary.RegimentsLimit, 1), Images.regimentsLimit, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorMilitary.ImproveRelationsModifier != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ImproveRelationsModifier") + "", "+" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorMilitary.ImproveRelationsModifier, 100) + "%", Images.relations, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorMilitary.ArmyMovementSpeed != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("ArmyMovementSpeed") + "", "+" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorMilitary.ArmyMovementSpeed, 100) + "%", Images.movementSpeed, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    statsY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }

                if (Game.getCiv(iActiveCivID).advisorMilitary.SiegeEffectiveness != 0.0F) {
                    menuElements.add(new ButtonStatsRectIMG_Bonuses_Right(Game.lang.get("SiegeEffectiveness") + "", "+" + CFG.getPrecision2(Game.getCiv(iActiveCivID).advisorMilitary.SiegeEffectiveness * 100.0F, 100) + "%", Images.siege, buttonX, buttonY + statsY, statW, statH, maxIconW));
                    int var157 = statsY + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }
            }

            menuElements.add(new Text_StaticBG_ID_FlagCiv_SpecialEmpty(CFG.PADDING + Images.boxTitleBORDERWIDTH, buttonY - CFG.PADDING, menuWidth - (CFG.PADDING + Images.boxTitleBORDERWIDTH) * 2, ButtonAdvisor.getButtonHeight() + CFG.PADDING * 2));
            buttonY += CFG.PADDING;
            buttonY += ButtonAdvisor.getButtonHeight() + CFG.PADDING;
            if (iActiveCivID == Game.player.iCivID || Game.SPECTATOR_MODE) {
                menuElements.add(new Text_Title_v2_TextLR(Game.lang.get("BattleTactics"), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, "") {
                    public void buildElementHover() {
                        List<MenuElement_HoverElement> nElements = new ArrayList();
                        List<MenuElement_HoverElement_Type> nData = new ArrayList();
                        nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("BattleTacticsDesc"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }
                });
                buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                menuElements.add(new ButtonCurrentSituation(Game.lang.get(GameValues.battleTactics.BATTLE_TACTICS[Game.getCiv(iActiveCivID).getBattleTacticsID()]), Images.battle, paddingLeft, buttonY, menuWidth - paddingLeft * 2 - CFG.BUTTON_HEIGHT2 * 2 - CFG.PADDING * 2, CFG.BUTTON_HEIGHT2, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
                    public void actionElement() {
                        if (Game.menuManager.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == 26) {
                            Game.menuManager.setVisibleInGame_PopUp(false);
                        } else {
                            Game.menuManager.rebuildInGame_BattleTactics();
                        }

                    }

                    public void buildElementHover() {
                        List<MenuElement_HoverElement> nElements = new ArrayList();
                        List<MenuElement_HoverElement_Type> nData = new ArrayList();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("BattleTactics"), Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.battle, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("BattleTacticsDesc"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Line());
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("UnitsAttack") + ": ", (GameValues.battleTactics.BATTLE_TACTICS_ATTACK[Game.getCiv(InGame_Court.iActiveCivID).getBattleTacticsID()] > 0 ? "+" : "") + GameValues.battleTactics.BATTLE_TACTICS_ATTACK[Game.getCiv(InGame_Court.iActiveCivID).getBattleTacticsID()], Images.attack, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, GameValues.battleTactics.BATTLE_TACTICS_ATTACK[Game.getCiv(InGame_Court.iActiveCivID).getBattleTacticsID()] == 0 ? Colors.HOVER_NEUTRAL : (GameValues.battleTactics.BATTLE_TACTICS_ATTACK[Game.getCiv(InGame_Court.iActiveCivID).getBattleTacticsID()] > 0 ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE)));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("UnitsDefense") + ": ", (GameValues.battleTactics.BATTLE_TACTICS_DEFENSE[Game.getCiv(InGame_Court.iActiveCivID).getBattleTacticsID()] > 0 ? "+" : "") + GameValues.battleTactics.BATTLE_TACTICS_DEFENSE[Game.getCiv(InGame_Court.iActiveCivID).getBattleTacticsID()], Images.defense, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, GameValues.battleTactics.BATTLE_TACTICS_DEFENSE[Game.getCiv(InGame_Court.iActiveCivID).getBattleTacticsID()] == 0 ? Colors.HOVER_NEUTRAL : (GameValues.battleTactics.BATTLE_TACTICS_DEFENSE[Game.getCiv(InGame_Court.iActiveCivID).getBattleTacticsID()] > 0 ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE)));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }
                });
                buttonX = paddingLeft + ((MenuElement)menuElements.get(menuElements.size() - 1)).getWidth() + CFG.PADDING;
                menuElements.add(new TextIcon2_Value((GameValues.battleTactics.BATTLE_TACTICS_ATTACK[Game.getCiv(iActiveCivID).getBattleTacticsID()] > 0 ? "+" : "") + GameValues.battleTactics.BATTLE_TACTICS_ATTACK[Game.getCiv(iActiveCivID).getBattleTacticsID()], Images.attack, buttonX, buttonY, CFG.BUTTON_HEIGHT2, CFG.BUTTON_HEIGHT2, Game.getCiv(iActiveCivID).getBattleTacticsID()) {
                    public void buildElementHover() {
                        List<MenuElement_HoverElement> nElements = new ArrayList();
                        List<MenuElement_HoverElement_Type> nData = new ArrayList();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("UnitsAttack") + ": ", (GameValues.battleTactics.BATTLE_TACTICS_ATTACK[Game.getCiv(InGame_Court.iActiveCivID).getBattleTacticsID()] > 0 ? "+" : "") + GameValues.battleTactics.BATTLE_TACTICS_ATTACK[Game.getCiv(InGame_Court.iActiveCivID).getBattleTacticsID()], Images.attack, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, GameValues.battleTactics.BATTLE_TACTICS_ATTACK[Game.getCiv(InGame_Court.iActiveCivID).getBattleTacticsID()] == 0 ? Colors.HOVER_NEUTRAL : (GameValues.battleTactics.BATTLE_TACTICS_ATTACK[Game.getCiv(InGame_Court.iActiveCivID).getBattleTacticsID()] > 0 ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE)));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }

                    protected Color getColor(boolean isActive) {
                        if (this.getIsHovered()) {
                            return super.getColor(isActive);
                        } else {
                            return GameValues.battleTactics.BATTLE_TACTICS_ATTACK[this.getCurrent()] == 0 ? Colors.HOVER_NEUTRAL : (GameValues.battleTactics.BATTLE_TACTICS_ATTACK[this.getCurrent()] > 0 ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE);
                        }
                    }
                });
                buttonX += ((MenuElement)menuElements.get(menuElements.size() - 1)).getWidth() + CFG.PADDING;
                menuElements.add(new TextIcon2_Value((GameValues.battleTactics.BATTLE_TACTICS_DEFENSE[Game.getCiv(iActiveCivID).getBattleTacticsID()] > 0 ? "+" : "") + GameValues.battleTactics.BATTLE_TACTICS_DEFENSE[Game.getCiv(iActiveCivID).getBattleTacticsID()], Images.defense, buttonX, buttonY, CFG.BUTTON_HEIGHT2, CFG.BUTTON_HEIGHT2, Game.getCiv(iActiveCivID).getBattleTacticsID()) {
                    public void buildElementHover() {
                        List<MenuElement_HoverElement> nElements = new ArrayList();
                        List<MenuElement_HoverElement_Type> nData = new ArrayList();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("UnitsDefense") + ": ", (GameValues.battleTactics.BATTLE_TACTICS_DEFENSE[Game.getCiv(InGame_Court.iActiveCivID).getBattleTacticsID()] > 0 ? "+" : "") + GameValues.battleTactics.BATTLE_TACTICS_DEFENSE[Game.getCiv(InGame_Court.iActiveCivID).getBattleTacticsID()], Images.defense, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, GameValues.battleTactics.BATTLE_TACTICS_DEFENSE[Game.getCiv(InGame_Court.iActiveCivID).getBattleTacticsID()] == 0 ? Colors.HOVER_NEUTRAL : (GameValues.battleTactics.BATTLE_TACTICS_DEFENSE[Game.getCiv(InGame_Court.iActiveCivID).getBattleTacticsID()] > 0 ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE)));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }

                    protected Color getColor(boolean isActive) {
                        if (this.getIsHovered()) {
                            return super.getColor(isActive);
                        } else {
                            return GameValues.battleTactics.BATTLE_TACTICS_DEFENSE[this.getCurrent()] == 0 ? Colors.HOVER_NEUTRAL : (GameValues.battleTactics.BATTLE_TACTICS_DEFENSE[this.getCurrent()] > 0 ? Colors.COLOR_TEXT_MODIFIER_POSITIVE : Colors.COLOR_TEXT_MODIFIER_NEGATIVE);
                        }
                    }
                });
                buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
            }

            if (iActiveCivID == Game.player.iCivID) {
                menuElements.add(new Text_Title_v2_TextLR(Game.lang.get("Military"), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, ""));
                buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                menuElements.add(new ButtonCurrentSituation(Game.lang.get("ArmyControlledByAI") + ": " + (Game.player.allowAIMove ? Game.lang.get("On") : Game.lang.get("Off")), Images.ai, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT2, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
                    public void actionElement() {
                        Game.player.allowAIMove = !Game.player.allowAIMove;
                        if (Game.menuManager.getVisibleInGame_Court()) {
                            Game.menuManager.rebuildInGame_CourtSavePos();
                            Game.menuManager.setVisibleInGame_Court(true);
                            InGame_Court.lTime = 0L;
                        }

                    }

                    public void buildElementHover() {
                        List<MenuElement_HoverElement> nElements = new ArrayList();
                        List<MenuElement_HoverElement_Type> nData = new ArrayList();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("ArmyControlledByAI"), Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.ai, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements, true);
                    }

                    protected Color getColor(boolean isActive) {
                        return Game.player.allowAIMove ? Colors.getColorPositive(isActive, this.getIsHovered()) : super.getColor(isActive);
                    }
                });
                buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                menuElements.add(new ButtonCurrentSituation(Game.lang.get("Armies") + ": " + Game.lang.get("Colors"), Images.brush, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT2, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
                    public void actionElement() {
                        Game.menuManager.hideCourtCiv();
                        Game.menuManager.rebuildInGame_CustomizeArmy();
                    }

                    public void buildElementHover() {
                        List<MenuElement_HoverElement> nElements = new ArrayList();
                        List<MenuElement_HoverElement_Type> nData = new ArrayList();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Armies") + ": " + Game.lang.get("Colors"), Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.brush, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements, true);
                    }
                });
                buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                if (GameValues.colonization.ALLOW_COLONIZATION_BY_SPENDING_GOLD || GameValues.colonization.ALLOW_COLONIZATION_BY_SPENDING_GOLD_PLAYER_TRIBAL && Game.ideologiesManager.getIdeology(Game.getCiv(Game.player.iCivID).getIdeologyID()).TRIBAL) {
                    menuElements.add(new Text_Title_v2_TextLR(Game.lang.get("Colonization"), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, ""));
                    buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                    menuElements.add(new ButtonCurrentSituation(Game.lang.get("Colonize") + ": " + Game.lang.get("ChooseAProvince"), Images.populationGrowth, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT2, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
                        public void actionElement() {
                            if (Game.getCiv(Game.player.iCivID).fGold < GameValues.colonization.ALLOW_COLONIZATION_BY_SPENDING_GOLD_COST) {
                                Game.menuManager.addToast_Error(Game.lang.get("InsufficientGold") + ": " + CFG.getPrecision2(GameValues.colonization.ALLOW_COLONIZATION_BY_SPENDING_GOLD_COST, 1), Images.gold);
                            }

                            if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_COLONIZE_CHOOSE_PROVINCE) {
                                Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                            } else {
                                Game.gameActiveProvince.resetLastActiveProvince();
                                Game.setActiveProvinceID(-1);
                                Game.menuManager.setVisibleInGame_ProvinceInfo(false);
                                Game.mapModes.setActiveViewID(Game.mapModes.MODE_COLONIZE_CHOOSE_PROVINCE);
                            }

                        }

                        protected Color getColor(boolean isActive) {
                            return Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_COLONIZE_CHOOSE_PROVINCE ? Colors.HOVER_GOLD : super.getColor(isActive);
                        }

                        public void buildElementHover() {
                            List<MenuElement_HoverElement> nElements = new ArrayList();
                            List<MenuElement_HoverElement_Type> nData = new ArrayList();
                            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Colonize") + ": " + Game.lang.get("ChooseAProvince"), Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.populationGrowth, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Cost") + ": ", "" + CFG.getPrecision2(GameValues.colonization.ALLOW_COLONIZATION_BY_SPENDING_GOLD_COST, 100), Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            this.menuElementHover = new MenuElement_Hover(nElements);
                        }
                    });
                    buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                }
            }
        } else if (modeID == 11) {
            menuElements.add(new ButtonCurrentSituation(Game.lang.get("Back"), Images.council, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
                public int getSFX() {
                    return SoundsManager.SOUND_CLICK_TOP;
                }

                public void actionElement() {
                    InGame_Court.modeID = 1;
                    InGame_Court.iActiveCivID = Game.player.iCivID;
                    Game.menuManager.rebuildInGame_Court();
                    Game.menuManager.setVisibleInGame_Court(true);
                    InGame_Court.lTime = 0L;
                    Game.setRegroupArmyMode(false);
                }

                public void buildElementHover() {
                    List<MenuElement_HoverElement> nElements = new ArrayList();
                    List<MenuElement_HoverElement_Type> nData = new ArrayList();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get(Game_Ages.getVassals()), Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.council, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements, true);
                }
            });
            buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
            menuElements.add(new Text_Title_v2_TextLR(Game.lang.get(Game_Ages.getReleaseAVassal()), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, ""));
            buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
            menuElements.add(new ButtonCurrentSituation(Game.lang.get("SelectCivilization"), Images.vassal, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
                public int getSFX() {
                    return SoundsManager.SOUND_CLICK_TOP;
                }

                public void actionElement() {
                    InGame_SelectCivilization_Add_List.selectedCivTAG = "";
                    Game.menuManager.setViewIDWithoutAnimation(View.IN_GAME_SELECT_CIVILIZATIONS);
                }

                public void buildElementHover() {
                    List<MenuElement_HoverElement> nElements = new ArrayList();
                    List<MenuElement_HoverElement_Type> nData = new ArrayList();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("SelectCivilization"), Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.vassal, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements, true);
                }
            });
            buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
            int tempElementsBefore = menuElements.size();
            List<Game.VassalsToRelease> listRelease = Game.getVassalsToRelease(iActiveCivID);
            if (listRelease.size() > 0) {
                int r0W0 = (int)((float)(menuWidth - paddingLeft * 2 - CFG.PADDING) * 0.25F);
                int r0W = (int)((float)(menuWidth - paddingLeft * 2 - CFG.PADDING) * 0.75F);
                int buttonH = CFG.isDesktop() ? CFG.BUTTON_HEIGHT3 : CFG.BUTTON_HEIGHT2;

                while(listRelease.size() > 0) {
                    int bestID = 0;

                    for(int i = listRelease.size() - 1; i > 0; --i) {
                        if (CFG.compareAlphabetic_TwoString(Game.getCiv(((Game.VassalsToRelease)listRelease.get(bestID)).iCivID).getCivName(), Game.getCiv(((Game.VassalsToRelease)listRelease.get(i)).iCivID).getCivName())) {
                            bestID = i;
                        }
                    }

                    menuElements.add(new Text_StaticBG_ID_FlagCiv("" + Game.getCiv(((Game.VassalsToRelease)listRelease.get(bestID)).iCivID).getCivName(), CFG.FONT_REGULAR_SMALL, CFG.PADDING * 2, buttonX, buttonY, r0W, buttonH, ((Game.VassalsToRelease)listRelease.get(bestID)).iCivID) {
                        public void actionElement() {
                            InGame_ReleaseAVassal.buildData(Game.player.iCivID, this.getCurrent());
                            InGame_ReleaseAVassal.buildData_MapMode(Game.player.iCivID, this.getCurrent());
                            Game.menuManager.rebuildInGame_ReleaseAVassal();
                            Game.mapModes.setActiveViewID(Game.mapModes.MODE_RELEASE_VASSAL);
                        }

                        public void buildElementHover() {
                            List<MenuElement_HoverElement> nElements = new ArrayList();
                            List<MenuElement_HoverElement_Type> nData = new ArrayList();
                            nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get(Game_Ages.getReleaseAVassal()), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                            nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.vassal, CFG.PADDING, 0));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Flag(this.getCurrent(), 0, CFG.PADDING));
                            nData.add(new MenuElement_HoverElement_Type_Text(this.getText(), CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Line());
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            List<Integer> tProvinces = Game.getVassalsToRelease_Provinces(InGame_Court.iActiveCivID, this.getCurrent());

                            for(int i = 0; i < 10 && tProvinces.size() > 0; ++i) {
                                int bestID = 0;

                                for(int j = tProvinces.size() - 1; j > 0; --j) {
                                    if (Game.getProvince((Integer)tProvinces.get(bestID)).getPopulationTotal() < Game.getProvince((Integer)tProvinces.get(j)).getPopulationTotal()) {
                                        bestID = j;
                                    }
                                }

                                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.getProvince((Integer)tProvinces.get(bestID)).getProvinceName() + "  ", CFG.getNumberWithSpaces("" + Game.getProvince((Integer)tProvinces.get(bestID)).getPopulationTotal()), Images.population, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_POPULATION));
                                nElements.add(new MenuElement_HoverElement(nData));
                                nData.clear();
                                tProvinces.remove(bestID);
                            }

                            this.menuElementHover = new MenuElement_Hover(nElements);
                        }
                    });
                    buttonX += ((MenuElement)menuElements.get(menuElements.size() - 1)).getWidth() + CFG.PADDING;
                    menuElements.add(new Text_StaticBG_ID_Special("" + ((Game.VassalsToRelease)listRelease.get(bestID)).iNumOfProvinces, CFG.FONT_REGULAR_SMALL, -1, buttonX, buttonY, r0W0, buttonH, ((Game.VassalsToRelease)listRelease.get(bestID)).iCivID) {
                        public void buildElementHover() {
                            List<MenuElement_HoverElement> nElements = new ArrayList();
                            List<MenuElement_HoverElement_Type> nData = new ArrayList();
                            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Provinces") + ": ", this.getText(), Images.provinces, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            this.menuElementHover = new MenuElement_Hover(nElements);
                        }
                    });
                    buttonX = paddingLeft;
                    buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                    listRelease.remove(bestID);
                }
            }

            if (tempElementsBefore == menuElements.size()) {
                menuElements.add(new Text_StaticBG(Game.lang.get("None"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT2));
                buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
            }
        } else if (modeID == 1) {
            menuElements.add(new ButtonCurrentSituation(Game.lang.get(Game_Ages.getReleaseAVassal()), Images.vassal, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
                public int getSFX() {
                    return SoundsManager.SOUND_CLICK_TOP;
                }

                public void actionElement() {
                    InGame_SelectCivilization_Add_List.selectedCivTAG = "";
                    InGame_Court.modeID = 11;
                    InGame_Court.iActiveCivID = Game.player.iCivID;
                    Game.menuManager.rebuildInGame_Court();
                    Game.menuManager.setVisibleInGame_Court(true);
                    InGame_Court.lTime = 0L;
                    Game.setRegroupArmyMode(false);
                }

                public void buildElementHover() {
                    List<MenuElement_HoverElement> nElements = new ArrayList();
                    List<MenuElement_HoverElement_Type> nData = new ArrayList();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get(Game_Ages.getReleaseAVassal()), Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.vassal, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements, true);
                }
            });
            buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
            if (Game.getCiv(Game.player.iCivID).getPuppetOfCivID() != Game.player.iCivID) {
                menuElements.add(new Text_Title_v2_TextLR(Game.lang.get(Game_Ages.getLord()), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, ""));
                buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                int buttonH2 = CFG.isDesktop() ? CFG.BUTTON_HEIGHT3 : CFG.BUTTON_HEIGHT2;
                int statsRightW = (menuWidth - paddingLeft * 2 - CFG.PADDING * 6) / 7;
                int statsRightH = CFG.BUTTON_HEIGHT;
                int emptyBGH = buttonH2 + CFG.PADDING + statsRightH;
                int nCivID = Game.getCiv(Game.player.iCivID).getPuppetOfCivID();
                menuElements.add(new Text_StaticBG_ID_FlagCiv_SpecialCiv(Game.getCiv(nCivID).getCivName(), CFG.FONT_REGULAR, CFG.PADDING * 2, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH2, nCivID, Images.population, CFG.getNumberWithSpaces("" + Game.getCiv(nCivID).getPopulationTotal())) {
                    public void actionElement() {
                        if (Game.getCiv(this.getCurrent()).getCapitalProvinceID() >= 0 && Game.getProvince(Game.getCiv(this.getCurrent()).getCapitalProvinceID()).getCivID() == this.getCurrent()) {
                            if (Game.iActiveProvince >= 0 && Game.getProvince(Game.iActiveProvince).getCivID() == this.getCurrent()) {
                                Game.menuManager.hideCourtCiv();
                                Game.menuManager.setVisibleInGame_Civ(false);
                                Game.menuManager.rebuildInGame_Civ();
                            } else {
                                Game.mapCoords.centerToProvinceID(Game.getCiv(this.getCurrent()).getCapitalProvinceID());
                                Game.setActiveProvinceID(Game.getCiv(this.getCurrent()).getCapitalProvinceID());
                                ProvinceBorderManager.action.setProvinceID(Game.iActiveProvince);
                            }
                        }

                    }

                    public void buildElementHover() {
                        List<MenuElement_HoverElement> nElements = new ArrayList();
                        List<MenuElement_HoverElement_Type> nData = new ArrayList();
                        nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(this.id, Game.getProvince(Game.getCiv(this.id).getCapitalProvinceID()).getProvinceName()));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Population") + ": ", CFG.getNumberWithSpaces("" + Game.getCiv(this.id).getPopulationTotal()), Images.population, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_POPULATION));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }

                    public Color getColor2(boolean isActive) {
                        return Colors.getColorPopulation(isActive, this.getIsHovered());
                    }
                });
                buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
                menuElements.add(new TextIcon2_Value(Game.lang.get("Tribute"), CFG.FONT_REGULAR_SMALL, Images.tax, paddingLeft, buttonY, statsRightW * 2 + CFG.PADDING, statsRightH, nCivID) {
                    public void buildElementHover() {
                        List<MenuElement_HoverElement> nElements = new ArrayList();
                        List<MenuElement_HoverElement_Type> nData = new ArrayList();
                        nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(this.id, Game.getProvince(Game.getCiv(this.id).getCapitalProvinceID()).getProvinceName()));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Tribute"), "", Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }
                });
                buttonX = paddingLeft + ((MenuElement)menuElements.get(menuElements.size() - 1)).getWidth() + CFG.PADDING;
                menuElements.add(new TextIcon2_Value("" + CFG.getPrecision2(Game.getIncomeFromVassal(nCivID, Game.player.iCivID, Game.getCiv(nCivID).diplomacy.getVassal_TributeLevel(Game.player.iCivID)), 100), CFG.FONT_REGULAR_SMALL, Images.gold, buttonX, buttonY, statsRightW * 2 + CFG.PADDING, statsRightH, nCivID) {
                    public void buildElementHover() {
                        List<MenuElement_HoverElement> nElements = new ArrayList();
                        List<MenuElement_HoverElement_Type> nData = new ArrayList();
                        nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(this.id, Game.getProvince(Game.getCiv(this.id).getCapitalProvinceID()).getProvinceName()));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Tribute") + ": ", CFG.getPrecision2(GameValues.vassal.VASSAL_INCOME_TO_LORD[Game.getCiv(this.id).diplomacy.getVassal_TributeLevel(Game.player.iCivID)] * 100.0F, 10) + "%", Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Gold") + ": ", Game.lang.get("XPerMonth", "" + CFG.getPrecision2(Game.getIncomeFromVassal(this.id, Game.player.iCivID, Game.getCiv(this.id).diplomacy.getVassal_TributeLevel(Game.player.iCivID)), 100)), Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }
                });
                buttonX += ((MenuElement)menuElements.get(menuElements.size() - 1)).getWidth() + CFG.PADDING;
                menuElements.add(new TextIcon2_Value(Game.lang.get("Manpower"), CFG.FONT_REGULAR_SMALL, Game_Calendar.IMG_MANPOWER, buttonX, buttonY, statsRightW * 2 + CFG.PADDING, statsRightH, nCivID) {
                    public void buildElementHover() {
                        List<MenuElement_HoverElement> nElements = new ArrayList();
                        List<MenuElement_HoverElement_Type> nData = new ArrayList();
                        nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(this.id, Game.getProvince(Game.getCiv(this.id).getCapitalProvinceID()).getProvinceName()));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Manpower"), "", Game_Calendar.IMG_MANPOWER, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }
                });
                buttonX += ((MenuElement)menuElements.get(menuElements.size() - 1)).getWidth() + CFG.PADDING;
                double tVal = Game.getManpowerFromVassal_INFO(nCivID, Game.player.iCivID, Game.getCiv(nCivID).diplomacy.getVassal_ManpowerLevel(Game.player.iCivID));
                menuElements.add(new TextIcon2_Value("" + CFG.getPrecision2(tVal, 1), CFG.FONT_REGULAR_SMALL, Game_Calendar.IMG_MANPOWER_UP, buttonX, buttonY, statsRightW, statsRightH, nCivID) {
                    public void buildElementHover() {
                        List<MenuElement_HoverElement> nElements = new ArrayList();
                        List<MenuElement_HoverElement_Type> nData = new ArrayList();
                        nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(this.id, Game.getProvince(Game.getCiv(this.id).getCapitalProvinceID()).getProvinceName()));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_FlagTitle(this.id, 0, CFG.PADDING));
                        nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getCiv(this.id).getCivName(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        double tVal = Game.getManpowerFromVassal_INFO(this.id, Game.player.iCivID, Game.getCiv(this.id).diplomacy.getVassal_ManpowerLevel(Game.player.iCivID));
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaximumManpower") + ": ", (tVal > (double)0.0F ? "+" : "") + CFG.getPrecision2(tVal, 1), Game_Calendar.IMG_MANPOWER, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }
                });
                int var158 = buttonX + ((MenuElement)menuElements.get(menuElements.size() - 1)).getWidth() + CFG.PADDING;
                buttonX = paddingLeft;
                buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                menuElements.add(new Text_StaticBG_ID_FlagCiv_SpecialEmpty(paddingLeft2, buttonY - emptyBGH, menuWidth - paddingLeft2 * 2, emptyBGH));
                buttonY += CFG.PADDING;
            }

            menuElements.add(new Text_Title_v2_TextLR(Game.lang.get(Game_Ages.getManageVassals()), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, "" + CFG.getNumberWithSpaces("" + Game.getCiv(Game.player.iCivID).diplomacy.iVassalsSize)));
            buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
            int tempElementsBefore = menuElements.size();

            try {
                if (Game.getCiv(Game.player.iCivID).diplomacy.iVassalsSize > 0) {
                    Graph_Vertical.graphCivs.clear();

                    for(int a = 0; a < Game.getCiv(Game.player.iCivID).diplomacy.iVassalsSize; ++a) {
                        Graph_Vertical.graphCivs.add(((Vassal)Game.getCiv(Game.player.iCivID).diplomacy.lVassals.get(a)).c);
                    }

                    if (Graph_Vertical.graphCivs.size() == 1 || GameValues.court.COUNCIL_VIEW_VASSAL_GRAPH_INCLUDE_PLAYER) {
                        Graph_Vertical.graphCivs.add(Game.player.iCivID);
                    }

                    if (!Graph_Vertical.graphCivs.isEmpty()) {
                        if (Game.oR.nextInt(100) < 50) {
                            Graph_Vertical graphVertical = new Graph_Vertical(Graph_Vertical_Data_Type.CIVS_LIST_PROVINCES, Game.lang.get("Civilizations"), Game.lang.get("Civilizations"), paddingLeft, buttonY, menuWidth - paddingLeft * 2, (int)((float)menuWidth * 0.35F), true) {
                            };
                            menuElements.add(graphVertical);
                        } else {
                            Graph_Vertical graphVertical = new Graph_Vertical(Graph_Vertical_Data_Type.CIVS_LIST_POPULATION, Game.lang.get("Civilizations"), Game.lang.get("Civilizations"), paddingLeft, buttonY, menuWidth - paddingLeft * 2, (int)((float)menuWidth * 0.35F), true) {
                            };
                            menuElements.add(graphVertical);
                        }

                        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
                    }

                    int buttonH2 = CFG.isDesktop() ? CFG.BUTTON_HEIGHT3 : CFG.BUTTON_HEIGHT2;
                    int statsRightW = (menuWidth - paddingLeft * 2 - CFG.PADDING * 6) / 7;
                    int statsRightH = CFG.BUTTON_HEIGHT;
                    int emptyBGH = buttonH2 + CFG.PADDING * 2 + statsRightH * 2;
                    float goldFromVassals = 0.0F;
                    float manpowerFromVassals = 0.0F;

                    for(int i = 0; i < Game.getCiv(Game.player.iCivID).diplomacy.iVassalsSize; ++i) {
                        goldFromVassals += Game.getIncomeFromVassal(Game.player.iCivID, ((Vassal)Game.getCiv(Game.player.iCivID).diplomacy.lVassals.get(i)).c, ((Vassal)Game.getCiv(Game.player.iCivID).diplomacy.lVassals.get(i)).tL);
                        manpowerFromVassals = (float)((double)manpowerFromVassals + Game.getManpowerFromVassal_INFO(Game.player.iCivID, ((Vassal)Game.getCiv(Game.player.iCivID).diplomacy.lVassals.get(i)).c, ((Vassal)Game.getCiv(Game.player.iCivID).diplomacy.lVassals.get(i)).mL));
                    }

                    int maxIconW = ImageManager.getImage(Images.gold).getWidth();
                    int statW = (menuWidth - paddingLeft * 2 - CFG.PADDING) / 2;
                    int statH = CFG.TEXT_HEIGHT + CFG.PADDING * 4;
                    menuElements.add(new ButtonStatsRectIMG_Bonuses("", (goldFromVassals > 0.0F ? "+" : "") + CFG.getPrecision2(goldFromVassals, 100), Images.gold, buttonX, buttonY, statW, statH, maxIconW) {
                        public void buildElementHover() {
                            List<MenuElement_HoverElement> nElements = new ArrayList();
                            List<MenuElement_HoverElement_Type> nData = new ArrayList();
                            nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(Game.player.iCivID, Game.lang.get("Lord")));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MonthlyIncome") + ": ", "" + this.sText2, Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_POSITIVE));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            this.menuElementHover = new MenuElement_Hover(nElements);
                        }

                        public String getTextToDraw() {
                            if (InGame_Court.UPDATE_INCOME_MANPOWER_FROM_VASSAL) {
                                float goldFromVassals = 0.0F;

                                for(int i = 0; i < Game.getCiv(Game.player.iCivID).diplomacy.iVassalsSize; ++i) {
                                    goldFromVassals += Game.getIncomeFromVassal(Game.player.iCivID, ((Vassal)Game.getCiv(Game.player.iCivID).diplomacy.lVassals.get(i)).c, ((Vassal)Game.getCiv(Game.player.iCivID).diplomacy.lVassals.get(i)).tL);
                                }

                                this.setText2((goldFromVassals > 0.0F ? "+" : "") + CFG.getPrecision2(goldFromVassals, 100));
                                InGame_Court.UPDATE_INCOME_MANPOWER_FROM_VASSAL = false;
                            }

                            return super.getTextToDraw();
                        }
                    });
                    menuElements.add(new ButtonStatsRectIMG_Bonuses("", (manpowerFromVassals > 0.0F ? "+" : "") + CFG.getPrecision2(manpowerFromVassals, 1), Game_Calendar.IMG_MANPOWER, buttonX + statW + CFG.PADDING, buttonY, statW, statH, maxIconW) {
                        public void buildElementHover() {
                            List<MenuElement_HoverElement> nElements = new ArrayList();
                            List<MenuElement_HoverElement_Type> nData = new ArrayList();
                            nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(Game.player.iCivID, Game.lang.get("Lord")));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaximumManpower") + ": ", "" + this.sText2, Game_Calendar.IMG_MANPOWER, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_POSITIVE));
                            nElements.add(new MenuElement_HoverElement(nData));
                            nData.clear();
                            this.menuElementHover = new MenuElement_Hover(nElements);
                        }

                        public String getTextToDraw() {
                            if (InGame_Court.UPDATE_INCOME_MANPOWER_FROM_VASSAL) {
                                float manpowerFromVassals = 0.0F;

                                for(int i = 0; i < Game.getCiv(Game.player.iCivID).diplomacy.iVassalsSize; ++i) {
                                    manpowerFromVassals = (float)((double)manpowerFromVassals + Game.getManpowerFromVassal_INFO(Game.player.iCivID, ((Vassal)Game.getCiv(Game.player.iCivID).diplomacy.lVassals.get(i)).c, ((Vassal)Game.getCiv(Game.player.iCivID).diplomacy.lVassals.get(i)).mL));
                                }

                                this.setText2((manpowerFromVassals > 0.0F ? "+" : "") + CFG.getPrecision2(manpowerFromVassals, 1));
                            }

                            return super.getTextToDraw();
                        }
                    });
                    buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                    List<Integer> tVassals = new ArrayList();

                    for(int i = 0; i < Game.getCiv(Game.player.iCivID).diplomacy.iVassalsSize; ++i) {
                        if (Game.getCiv(((Vassal)Game.getCiv(Game.player.iCivID).diplomacy.lVassals.get(i)).c).getNumOfProvinces() > 0) {
                            tVassals.add(i);
                        }
                    }

                    int bestID;
                    for(; !tVassals.isEmpty(); tVassals.remove(bestID)) {
                        bestID = 0;

                        for(int i = tVassals.size() - 1; i > 0; --i) {
                            if (Game.getCiv(((Vassal)Game.getCiv(Game.player.iCivID).diplomacy.lVassals.get((Integer)tVassals.get(i))).c).getNumOfProvinces() > Game.getCiv(((Vassal)Game.getCiv(Game.player.iCivID).diplomacy.lVassals.get((Integer)tVassals.get(bestID))).c).getNumOfProvinces()) {
                                bestID = i;
                            }
                        }

                        if (Game.getCiv(((Vassal)Game.getCiv(Game.player.iCivID).diplomacy.lVassals.get((Integer)tVassals.get(bestID))).c).getNumOfProvinces() > 0) {
                            int nCivID = ((Vassal)Game.getCiv(Game.player.iCivID).diplomacy.lVassals.get((Integer)tVassals.get(bestID))).c;
                            menuElements.add(new Text_StaticBG_ID_FlagCiv_SpecialCiv(Game.getCiv(nCivID).getCivName(), CFG.FONT_REGULAR, CFG.PADDING * 2, paddingLeft, buttonY, menuWidth - paddingLeft * 2, buttonH2, nCivID, Images.population, CFG.getNumberWithSpaces("" + Game.getCiv(nCivID).getPopulationTotal())) {
                                public void actionElement() {
                                    if (Game.getCiv(this.getCurrent()).getCapitalProvinceID() >= 0 && Game.getProvince(Game.getCiv(this.getCurrent()).getCapitalProvinceID()).getCivID() == this.getCurrent()) {
                                        if (Game.iActiveProvince >= 0 && Game.getProvince(Game.iActiveProvince).getCivID() == this.getCurrent()) {
                                            Game.menuManager.hideCourtCiv();
                                            Game.menuManager.setVisibleInGame_Civ(false);
                                            Game.menuManager.rebuildInGame_Civ();
                                        } else {
                                            Game.mapCoords.centerToProvinceID(Game.getCiv(this.getCurrent()).getCapitalProvinceID());
                                            Game.setActiveProvinceID(Game.getCiv(this.getCurrent()).getCapitalProvinceID());
                                            ProvinceBorderManager.action.setProvinceID(Game.iActiveProvince);
                                        }
                                    }

                                }

                                public void buildElementHover() {
                                    List<MenuElement_HoverElement> nElements = new ArrayList();
                                    List<MenuElement_HoverElement_Type> nData = new ArrayList();
                                    nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(this.id, Game.getProvince(Game.getCiv(this.id).getCapitalProvinceID()).getProvinceName()));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Population") + ": ", CFG.getNumberWithSpaces("" + Game.getCiv(this.id).getPopulationTotal()), Images.population, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.COLOR_POPULATION));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Economy") + ": ", CFG.getPrecision2(Game.getCiv(this.id).getEconomyTotal(), 10), Game_Calendar.IMG_ECONOMY, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_Line());
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    float fGold = Game.getCiv(this.id).fTotalIncomePerMonth;
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Income") + ": ", CFG.getPrecision2(fGold, 10), Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    fGold = Game.getCiv(this.id).fTotalIncomePerMonth + Game.getCiv(this.id).civBonuses.MonthlyIncome - Game.getCiv(this.id).fTotalExpensesPerMonth;
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Balance") + ": ", CFG.getPrecision2(fGold, 10), Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_Line());
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Manpower") + ": ", CFG.getPrecision2(Game.getCiv(this.id).fManpower, 1), Game_Calendar.IMG_MANPOWER, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaximumManpower") + ": ", CFG.getPrecision2(Game.getCiv(this.id).fManpowerMax, 1), Game_Calendar.IMG_MANPOWER, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    this.menuElementHover = new MenuElement_Hover(nElements);
                                }

                                public Color getColor2(boolean isActive) {
                                    return Colors.getColorPopulation(isActive, this.getIsHovered());
                                }
                            });
                            buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight();
                            menuElements.add(new TextIcon2_Value(Game.lang.get("Tribute"), CFG.FONT_REGULAR_SMALL, Images.tax, paddingLeft, buttonY, statsRightW * 2 + CFG.PADDING, statsRightH, nCivID) {
                                public void buildElementHover() {
                                    List<MenuElement_HoverElement> nElements = new ArrayList();
                                    List<MenuElement_HoverElement_Type> nData = new ArrayList();
                                    nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(this.id, Game.getProvince(Game.getCiv(this.id).getCapitalProvinceID()).getProvinceName()));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Tribute"), "", Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    this.menuElementHover = new MenuElement_Hover(nElements);
                                }
                            });
                            buttonX = paddingLeft + ((MenuElement)menuElements.get(menuElements.size() - 1)).getWidth() + CFG.PADDING;
                            menuElements.add(new TextIcon2_Value_Levels(Game.lang.get("Low"), CFG.FONT_REGULAR_SMALL, Images.gold, buttonX, buttonY, statsRightW, statsRightH, nCivID, 0) {
                                public void actionElement() {
                                    Game.getCiv(Game.player.iCivID).diplomacy.setVassal_TributeLevel(this.id, this.iLevel);
                                    Game.getCiv(this.id).updateTotalIncomePerMonth();
                                    Game.getCiv(Game.player.iCivID).updateTotalIncomePerMonth();
                                    Game.getCiv(this.id).updateManpowerPerMonth();
                                    Game.getCiv(Game.player.iCivID).updateManpowerPerMonth();
                                    InGame_Court.UPDATE_INCOME_MANPOWER_FROM_VASSAL = true;
                                    MenuManager var10000 = Game.menuManager;
                                    MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + InGame_Court.this.getMenuPosX(), this.getPosY() + this.getHeight() / 2 + InGame_Court.this.getMenuPosY(), this.getWidth(), this.getHeight()) {
                                        public Color getColor() {
                                            return Colors.HOVER_GOLD;
                                        }
                                    });
                                }

                                public void buildElementHover() {
                                    List<MenuElement_HoverElement> nElements = new ArrayList();
                                    List<MenuElement_HoverElement_Type> nData = new ArrayList();
                                    nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(this.id, Game.getProvince(Game.getCiv(this.id).getCapitalProvinceID()).getProvinceName()));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(this.getText(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Tribute") + ": ", CFG.getPrecision2(GameValues.vassal.VASSAL_INCOME_TO_LORD[this.iLevel] * 100.0F, 10) + "%", Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Gold") + ": ", Game.lang.get("XPerMonth", "" + CFG.getPrecision2(Game.getIncomeFromVassal(Game.player.iCivID, this.id, this.iLevel), 100)), Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    this.menuElementHover = new MenuElement_Hover(nElements);
                                }

                                public int getSFX() {
                                    return SoundsManager.SOUND_GOLD_LEVEL_0;
                                }

                                public boolean isLeveLActive() {
                                    return Game.getCiv(Game.player.iCivID).diplomacy.getVassal_TributeLevel(this.id) == this.iLevel;
                                }
                            });
                            buttonX += ((MenuElement)menuElements.get(menuElements.size() - 1)).getWidth() + CFG.PADDING;
                            menuElements.add(new TextIcon2_Value_Levels(Game.lang.get("Medium"), CFG.FONT_REGULAR_SMALL, Images.gold, buttonX, buttonY, statsRightW, statsRightH, nCivID, 1) {
                                public void actionElement() {
                                    Game.getCiv(Game.player.iCivID).diplomacy.setVassal_TributeLevel(this.id, this.iLevel);
                                    Game.getCiv(this.id).updateTotalIncomePerMonth();
                                    Game.getCiv(Game.player.iCivID).updateTotalIncomePerMonth();
                                    Game.getCiv(this.id).updateManpowerPerMonth();
                                    Game.getCiv(Game.player.iCivID).updateManpowerPerMonth();
                                    InGame_Court.UPDATE_INCOME_MANPOWER_FROM_VASSAL = true;
                                    MenuManager var10000 = Game.menuManager;
                                    MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + InGame_Court.this.getMenuPosX(), this.getPosY() + this.getHeight() / 2 + InGame_Court.this.getMenuPosY(), this.getWidth(), this.getHeight()) {
                                        public Color getColor() {
                                            return Colors.HOVER_GOLD;
                                        }
                                    });
                                }

                                public void buildElementHover() {
                                    List<MenuElement_HoverElement> nElements = new ArrayList();
                                    List<MenuElement_HoverElement_Type> nData = new ArrayList();
                                    nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(this.id, Game.getProvince(Game.getCiv(this.id).getCapitalProvinceID()).getProvinceName()));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(this.getText(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Tribute") + ": ", CFG.getPrecision2(GameValues.vassal.VASSAL_INCOME_TO_LORD[this.iLevel] * 100.0F, 10) + "%", Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Gold") + ": ", Game.lang.get("XPerMonth", "" + CFG.getPrecision2(Game.getIncomeFromVassal(Game.player.iCivID, this.id, this.iLevel), 100)), Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    this.menuElementHover = new MenuElement_Hover(nElements);
                                }

                                public int getSFX() {
                                    return SoundsManager.SOUND_GOLD_LEVEL_1;
                                }

                                public boolean isLeveLActive() {
                                    return Game.getCiv(Game.player.iCivID).diplomacy.getVassal_TributeLevel(this.id) == this.iLevel;
                                }
                            });
                            buttonX += ((MenuElement)menuElements.get(menuElements.size() - 1)).getWidth() + CFG.PADDING;
                            menuElements.add(new TextIcon2_Value_Levels(Game.lang.get("High"), CFG.FONT_REGULAR_SMALL, Images.gold, buttonX, buttonY, statsRightW, statsRightH, nCivID, 2) {
                                public void actionElement() {
                                    Game.getCiv(Game.player.iCivID).diplomacy.setVassal_TributeLevel(this.id, this.iLevel);
                                    Game.getCiv(this.id).updateTotalIncomePerMonth();
                                    Game.getCiv(Game.player.iCivID).updateTotalIncomePerMonth();
                                    Game.getCiv(this.id).updateManpowerPerMonth();
                                    Game.getCiv(Game.player.iCivID).updateManpowerPerMonth();
                                    InGame_Court.UPDATE_INCOME_MANPOWER_FROM_VASSAL = true;
                                    MenuManager var10000 = Game.menuManager;
                                    MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + InGame_Court.this.getMenuPosX(), this.getPosY() + this.getHeight() / 2 + InGame_Court.this.getMenuPosY(), this.getWidth(), this.getHeight()) {
                                        public Color getColor() {
                                            return Colors.HOVER_GOLD;
                                        }
                                    });
                                }

                                public void buildElementHover() {
                                    List<MenuElement_HoverElement> nElements = new ArrayList();
                                    List<MenuElement_HoverElement_Type> nData = new ArrayList();
                                    nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(this.id, Game.getProvince(Game.getCiv(this.id).getCapitalProvinceID()).getProvinceName()));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(this.getText(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_FlagTitle(Game.player.iCivID, 0, CFG.PADDING));
                                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getCiv(Game.player.iCivID).getCivName(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Tribute") + ": ", CFG.getPrecision2(GameValues.vassal.VASSAL_INCOME_TO_LORD[this.iLevel] * 100.0F, 10) + "%", Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Gold") + ": ", Game.lang.get("XPerMonth", "" + CFG.getPrecision2(Game.getIncomeFromVassal(Game.player.iCivID, this.id, this.iLevel), 100)), Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    this.menuElementHover = new MenuElement_Hover(nElements);
                                }

                                public int getSFX() {
                                    return SoundsManager.SOUND_GOLD_LEVEL_2;
                                }

                                public boolean isLeveLActive() {
                                    return Game.getCiv(Game.player.iCivID).diplomacy.getVassal_TributeLevel(this.id) == this.iLevel;
                                }
                            });
                            buttonX += ((MenuElement)menuElements.get(menuElements.size() - 1)).getWidth() + CFG.PADDING;
                            menuElements.add(new TextIcon2_Value("" + CFG.getPrecision2(Game.getIncomeFromVassal(Game.player.iCivID, nCivID, Game.getCiv(Game.player.iCivID).diplomacy.getVassal_TributeLevel(nCivID)), 100), CFG.FONT_REGULAR_SMALL, Images.gold, buttonX, buttonY, statsRightW * 2 + CFG.PADDING, statsRightH, nCivID) {
                                int lastLevel = 0;

                                public void buildElementHover() {
                                    List<MenuElement_HoverElement> nElements = new ArrayList();
                                    List<MenuElement_HoverElement_Type> nData = new ArrayList();
                                    nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(this.id, Game.getProvince(Game.getCiv(this.id).getCapitalProvinceID()).getProvinceName()));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Tribute") + ": ", CFG.getPrecision2(GameValues.vassal.VASSAL_INCOME_TO_LORD[Game.getCiv(Game.player.iCivID).diplomacy.getVassal_TributeLevel(this.id)] * 100.0F, 10) + "%", Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Gold") + ": ", Game.lang.get("XPerMonth", "" + CFG.getPrecision2(Game.getIncomeFromVassal(Game.player.iCivID, this.id, Game.getCiv(Game.player.iCivID).diplomacy.getVassal_TributeLevel(this.id)), 100)), Images.gold, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    this.menuElementHover = new MenuElement_Hover(nElements);
                                }

                                public void setCurrent(int nCurrent) {
                                    this.lastLevel = nCurrent;
                                }

                                public String getTextToDraw() {
                                    if (this.lastLevel != Game.getCiv(Game.player.iCivID).diplomacy.getVassal_TributeLevel(this.id)) {
                                        this.lastLevel = Game.getCiv(Game.player.iCivID).diplomacy.getVassal_TributeLevel(this.id);
                                        this.setText("" + CFG.getPrecision2(Game.getIncomeFromVassal(Game.player.iCivID, this.id, this.lastLevel), 100));
                                    }

                                    return super.getTextToDraw();
                                }
                            });
                            ((MenuElement)menuElements.get(menuElements.size() - 1)).setCurrent(Game.getCiv(Game.player.iCivID).diplomacy.getVassal_TributeLevel(nCivID));
                            buttonY += statsRightH + CFG.PADDING;
                            menuElements.add(new TextIcon2_Value(Game.lang.get("Manpower"), CFG.FONT_REGULAR_SMALL, Game_Calendar.IMG_MANPOWER, paddingLeft, buttonY, statsRightW * 2 + CFG.PADDING, statsRightH, nCivID) {
                                public void buildElementHover() {
                                    List<MenuElement_HoverElement> nElements = new ArrayList();
                                    List<MenuElement_HoverElement_Type> nData = new ArrayList();
                                    nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(this.id, Game.getProvince(Game.getCiv(this.id).getCapitalProvinceID()).getProvinceName()));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Manpower"), "", Game_Calendar.IMG_MANPOWER, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    this.menuElementHover = new MenuElement_Hover(nElements);
                                }
                            });
                            buttonX = paddingLeft + ((MenuElement)menuElements.get(menuElements.size() - 1)).getWidth() + CFG.PADDING;
                            menuElements.add(new TextIcon2_Value_Levels(Game.lang.get("Low"), CFG.FONT_REGULAR_SMALL, Game_Calendar.IMG_MANPOWER, buttonX, buttonY, statsRightW, statsRightH, nCivID, 0) {
                                public void actionElement() {
                                    Game.getCiv(Game.player.iCivID).diplomacy.setVassal_ManpowerLevel(this.id, this.iLevel);
                                    Game.getCiv(this.id).updateTotalIncomePerMonth();
                                    Game.getCiv(Game.player.iCivID).updateTotalIncomePerMonth();
                                    Game.getCiv(this.id).updateManpowerPerMonth();
                                    Game.getCiv(Game.player.iCivID).updateManpowerPerMonth();
                                    InGame_Court.UPDATE_INCOME_MANPOWER_FROM_VASSAL = true;
                                    MenuManager var10000 = Game.menuManager;
                                    MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + InGame_Court.this.getMenuPosX(), this.getPosY() + this.getHeight() / 2 + InGame_Court.this.getMenuPosY(), this.getWidth(), this.getHeight()) {
                                        public Color getColor() {
                                            return DiplomacyManager.COLOR_WAR;
                                        }
                                    });
                                }

                                public void buildElementHover() {
                                    List<MenuElement_HoverElement> nElements = new ArrayList();
                                    List<MenuElement_HoverElement_Type> nData = new ArrayList();
                                    nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(this.id, Game.getProvince(Game.getCiv(this.id).getCapitalProvinceID()).getProvinceName()));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(this.getText(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Manpower") + ": ", CFG.getPrecision2(GameValues.vassal.VASSAL_MANPOWER_TO_LORD[this.iLevel] * 100.0F, 10) + "%", Game_Calendar.IMG_MANPOWER, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    double tVal = Game.getManpowerFromVassal_INFO(Game.player.iCivID, this.id, this.iLevel);
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaximumManpower") + ": ", (tVal > (double)0.0F ? "+" : "") + CFG.getPrecision2(tVal, 1), Game_Calendar.IMG_MANPOWER, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    this.menuElementHover = new MenuElement_Hover(nElements);
                                }

                                public boolean isLeveLActive() {
                                    return Game.getCiv(Game.player.iCivID).diplomacy.getVassal_ManpowerLevel(this.id) == this.iLevel;
                                }
                            });
                            buttonX += ((MenuElement)menuElements.get(menuElements.size() - 1)).getWidth() + CFG.PADDING;
                            menuElements.add(new TextIcon2_Value_Levels(Game.lang.get("Medium"), CFG.FONT_REGULAR_SMALL, Game_Calendar.IMG_MANPOWER, buttonX, buttonY, statsRightW, statsRightH, nCivID, 1) {
                                public void actionElement() {
                                    Game.getCiv(Game.player.iCivID).diplomacy.setVassal_ManpowerLevel(this.id, this.iLevel);
                                    Game.getCiv(this.id).updateTotalIncomePerMonth();
                                    Game.getCiv(Game.player.iCivID).updateTotalIncomePerMonth();
                                    Game.getCiv(this.id).updateManpowerPerMonth();
                                    Game.getCiv(Game.player.iCivID).updateManpowerPerMonth();
                                    InGame_Court.UPDATE_INCOME_MANPOWER_FROM_VASSAL = true;
                                    MenuManager var10000 = Game.menuManager;
                                    MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + InGame_Court.this.getMenuPosX(), this.getPosY() + this.getHeight() / 2 + InGame_Court.this.getMenuPosY(), this.getWidth(), this.getHeight()) {
                                        public Color getColor() {
                                            return DiplomacyManager.COLOR_WAR;
                                        }
                                    });
                                }

                                public void buildElementHover() {
                                    List<MenuElement_HoverElement> nElements = new ArrayList();
                                    List<MenuElement_HoverElement_Type> nData = new ArrayList();
                                    nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(this.id, Game.getProvince(Game.getCiv(this.id).getCapitalProvinceID()).getProvinceName()));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Medium"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Manpower") + ": ", CFG.getPrecision2(GameValues.vassal.VASSAL_MANPOWER_TO_LORD[this.iLevel] * 100.0F, 10) + "%", Game_Calendar.IMG_MANPOWER, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    double tVal = Game.getManpowerFromVassal_INFO(Game.player.iCivID, this.id, this.iLevel);
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaximumManpower") + ": ", (tVal > (double)0.0F ? "+" : "") + CFG.getPrecision2(tVal, 1), Game_Calendar.IMG_MANPOWER, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    this.menuElementHover = new MenuElement_Hover(nElements);
                                }

                                public boolean isLeveLActive() {
                                    return Game.getCiv(Game.player.iCivID).diplomacy.getVassal_ManpowerLevel(this.id) == this.iLevel;
                                }
                            });
                            buttonX += ((MenuElement)menuElements.get(menuElements.size() - 1)).getWidth() + CFG.PADDING;
                            menuElements.add(new TextIcon2_Value_Levels(Game.lang.get("High"), CFG.FONT_REGULAR_SMALL, Game_Calendar.IMG_MANPOWER, buttonX, buttonY, statsRightW, statsRightH, nCivID, 2) {
                                public void actionElement() {
                                    Game.getCiv(Game.player.iCivID).diplomacy.setVassal_ManpowerLevel(this.id, this.iLevel);
                                    Game.getCiv(this.id).updateTotalIncomePerMonth();
                                    Game.getCiv(Game.player.iCivID).updateTotalIncomePerMonth();
                                    Game.getCiv(this.id).updateManpowerPerMonth();
                                    Game.getCiv(Game.player.iCivID).updateManpowerPerMonth();
                                    InGame_Court.UPDATE_INCOME_MANPOWER_FROM_VASSAL = true;
                                    MenuManager var10000 = Game.menuManager;
                                    MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + InGame_Court.this.getMenuPosX(), this.getPosY() + this.getHeight() / 2 + InGame_Court.this.getMenuPosY(), this.getWidth(), this.getHeight()) {
                                        public Color getColor() {
                                            return DiplomacyManager.COLOR_WAR;
                                        }
                                    });
                                }

                                public void buildElementHover() {
                                    List<MenuElement_HoverElement> nElements = new ArrayList();
                                    List<MenuElement_HoverElement_Type> nData = new ArrayList();
                                    nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(this.id, Game.getProvince(Game.getCiv(this.id).getCapitalProvinceID()).getProvinceName()));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(this.getText(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Manpower") + ": ", CFG.getPrecision2(GameValues.vassal.VASSAL_MANPOWER_TO_LORD[this.iLevel] * 100.0F, 10) + "%", Game_Calendar.IMG_MANPOWER, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    double tVal = Game.getManpowerFromVassal_INFO(Game.player.iCivID, this.id, this.iLevel);
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaximumManpower") + ": ", (tVal > (double)0.0F ? "+" : "") + CFG.getPrecision2(tVal, 1), Game_Calendar.IMG_MANPOWER, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    this.menuElementHover = new MenuElement_Hover(nElements);
                                }

                                public boolean isLeveLActive() {
                                    return Game.getCiv(Game.player.iCivID).diplomacy.getVassal_ManpowerLevel(this.id) == this.iLevel;
                                }
                            });
                            buttonX += ((MenuElement)menuElements.get(menuElements.size() - 1)).getWidth() + CFG.PADDING;
                            double tVal = Game.getManpowerFromVassal_INFO(Game.player.iCivID, nCivID, Game.getCiv(Game.player.iCivID).diplomacy.getVassal_ManpowerLevel(nCivID));
                            menuElements.add(new TextIcon2_Value("" + CFG.getPrecision2(tVal, 1), CFG.FONT_REGULAR_SMALL, Game_Calendar.IMG_MANPOWER_UP, buttonX, buttonY, statsRightW, statsRightH, nCivID) {
                                int lastLevel = 0;

                                public void buildElementHover() {
                                    List<MenuElement_HoverElement> nElements = new ArrayList();
                                    List<MenuElement_HoverElement_Type> nData = new ArrayList();
                                    nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(this.id, Game.getProvince(Game.getCiv(this.id).getCapitalProvinceID()).getProvinceName()));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_FlagTitle(Game.player.iCivID, 0, CFG.PADDING));
                                    nData.add(new MenuElement_HoverElement_Type_TextTitle(Game.getCiv(Game.player.iCivID).getCivName(), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    double tVal = Game.getManpowerFromVassal_INFO(Game.player.iCivID, this.id, Game.getCiv(Game.player.iCivID).diplomacy.getVassal_ManpowerLevel(this.id));
                                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("MaximumManpower") + ": ", (tVal > (double)0.0F ? "+" : "") + CFG.getPrecision2(tVal, 1), Game_Calendar.IMG_MANPOWER, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    this.menuElementHover = new MenuElement_Hover(nElements);
                                }

                                public void setCurrent(int nCurrent) {
                                    this.lastLevel = nCurrent;
                                }

                                public String getTextToDraw() {
                                    if (this.lastLevel != Game.getCiv(Game.player.iCivID).diplomacy.getVassal_ManpowerLevel(this.id)) {
                                        this.lastLevel = Game.getCiv(Game.player.iCivID).diplomacy.getVassal_ManpowerLevel(this.id);
                                        this.setText("" + CFG.getPrecision2(Game.getManpowerFromVassal_INFO(Game.player.iCivID, this.id, this.lastLevel), 1));
                                    }

                                    return super.getTextToDraw();
                                }
                            });
                            ((MenuElement)menuElements.get(menuElements.size() - 1)).setCurrent(Game.getCiv(Game.player.iCivID).diplomacy.getVassal_ManpowerLevel(nCivID));
                            buttonX += ((MenuElement)menuElements.get(menuElements.size() - 1)).getWidth() + CFG.PADDING;
                            menuElements.add(new TextIcon2_Value_Levels(Game.lang.get("Wars"), CFG.FONT_REGULAR_SMALL, Images.war, buttonX, buttonY, statsRightW, statsRightH, nCivID, Game.getCiv(Game.player.iCivID).diplomacy.getVassal_CanDeclareWar(nCivID) ? 1 : 0) {
                                public void actionElement() {
                                    Game.getCiv(Game.player.iCivID).diplomacy.setVassal_CanDeclareWar(this.id, !Game.getCiv(Game.player.iCivID).diplomacy.getVassal_CanDeclareWar(this.id));
                                    this.iLevel = Game.getCiv(Game.player.iCivID).diplomacy.getVassal_CanDeclareWar(this.id) ? 1 : 0;
                                    MenuManager var10000 = Game.menuManager;
                                    MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + InGame_Court.this.getMenuPosX(), this.getPosY() + this.getHeight() / 2 + InGame_Court.this.getMenuPosY(), this.getWidth(), this.getHeight()) {
                                        public Color getColor() {
                                            return DiplomacyManager.COLOR_WAR;
                                        }
                                    });
                                }

                                public void buildElementHover() {
                                    List<MenuElement_HoverElement> nElements = new ArrayList();
                                    List<MenuElement_HoverElement_Type> nData = new ArrayList();
                                    nData.add(new MenuElement_HoverElement_Type_FlagCiv_Title(this.id, Game.getProvince(Game.getCiv(this.id).getCapitalProvinceID()).getProvinceName()));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("CanDeclareWars"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(this.iLevel == 1 ? Images.v : Images.x, CFG.PADDING, 0));
                                    nElements.add(new MenuElement_HoverElement(nData));
                                    nData.clear();
                                    if (this.iLevel == 0) {
                                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("LibertyDesire") + ": ", "+" + CFG.getPrecision2(GameValues.vassal.LIBERTY_DESIRE_CANT_DECLARE_WAR, 100), Images.revolutionRisk, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_NEGATIVE));
                                        nElements.add(new MenuElement_HoverElement(nData));
                                        nData.clear();
                                    }

                                    this.menuElementHover = new MenuElement_Hover(nElements, this.iLevel != 0);
                                }

                                public boolean isLeveLActive() {
                                    return 1 == this.iLevel;
                                }

                                public float getScale() {
                                    return Math.min(1.0F, this.getImageScale(this.imageID));
                                }
                            });
                            int var159 = buttonX + ((MenuElement)menuElements.get(menuElements.size() - 1)).getWidth() + CFG.PADDING;
                            buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
                            menuElements.add(new Text_StaticBG_ID_FlagCiv_SpecialEmpty(paddingLeft2, buttonY - emptyBGH, menuWidth - paddingLeft2 * 2, emptyBGH));
                            buttonY += CFG.PADDING;
                        }
                    }
                }
            } catch (Exception ex) {
                CFG.exceptionStack(ex);
            }

            if (tempElementsBefore == menuElements.size()) {
                menuElements.add(new Text_StaticBG(Game.lang.get("None"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT2));
                buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
            }
        } else if (modeID == 2) {
            menuElements.add(new Text_Title_v2_TextLR(Game.lang.get("Tutorial"), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, ""));
            buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
            menuElements.add(new ButtonCurrentSituation(Game.lang.get("Encyclopedia"), Images.encyclopedia, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
                public void actionElement() {
                    if (Game.menuManager.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == 15) {
                        Game.menuManager.setVisibleInGame_PopUp(false);
                    } else {
                        InGame_Encyclopedia.sSearch = "";
                        Game.menuManager.rebuildInGame_Encyclopedia();
                    }

                }

                public void buildElementHover() {
                    List<MenuElement_HoverElement> nElements = new ArrayList();
                    List<MenuElement_HoverElement_Type> nData = new ArrayList();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Encyclopedia"), Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.encyclopedia, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements, true);
                }

                public int getSFX() {
                    return SoundsManager.getClickSound_CivOptions();
                }
            });
            buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
            menuElements.add(new Text_Title_v2_TextLR(Game.lang.get("Goods"), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, "") {
                public void buildElementHover() {
                    List<MenuElement_HoverElement> nElements = new ArrayList();
                    List<MenuElement_HoverElement_Type> nData = new ArrayList();
                    nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("Production2"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
            menuElements.add(new ButtonCurrentSituation(Game.lang.get("LargestGoodsProducers"), Images.goods, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
                public void actionElement() {
                    Game.menuManager.rebuildInGame_Goods();
                    Game.menuManager.setVisibleInGame_Goods(true);
                    InGame_Goods.lTime = 0L;
                }

                public void buildElementHover() {
                    List<MenuElement_HoverElement> nElements = new ArrayList();
                    List<MenuElement_HoverElement_Type> nData = new ArrayList();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("LargestGoodsProducers"), Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.goods, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text_Desc(Game.lang.get("Production2"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }

                public int getSFX() {
                    return SoundsManager.getClickSound_CivOptions();
                }
            });
            buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
            menuElements.add(new Text_Title_v2_TextLR(Game.lang.get("Search"), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, ""));
            buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
            menuElements.add(new ButtonCurrentSituation(Game.lang.get("Search") + ": " + Game.lang.get("Civilizations"), Images.world, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
                public void actionElement() {
                    InGame_Court.inCourt = false;
                    InGame_Court.inSearchProvinces = false;
                    InGame_CourtOptions.disableAllViews();
                    InGame_Court_WorldCivs.sSearch = "";
                    Game.menuManager.rebuildInGame_CourtSearchCivs();
                    Game.menuManager.setVisibleInGame_Court(true);
                    InGame_Court.lTime = 0L;
                }

                public void buildElementHover() {
                    List<MenuElement_HoverElement> nElements = new ArrayList();
                    List<MenuElement_HoverElement_Type> nData = new ArrayList();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Search") + ": " + Game.lang.get("Civilizations"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.world, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements, true);
                }

                public int getSFX() {
                    return SoundsManager.getClickSound_CivOptions();
                }
            });
            buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
            menuElements.add(new ButtonCurrentSituation(Game.lang.get("Search") + ": " + Game.lang.get("Provinces"), Images.world, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
                public void actionElement() {
                    InGame_Court.actionSearchProvinces();
                }

                public void buildElementHover() {
                    List<MenuElement_HoverElement> nElements = new ArrayList();
                    List<MenuElement_HoverElement_Type> nData = new ArrayList();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Search") + ": " + Game.lang.get("Provinces"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.world, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    if (CFG.isDesktop()) {
                        nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Shortcut") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT));
                        nData.add(new MenuElement_HoverElement_Type_Text("F", CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                        nData.add(new MenuElement_HoverElement_Type_Image(Images.world, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }

                    this.menuElementHover = new MenuElement_Hover(nElements, !CFG.isDesktop());
                }

                public int getSFX() {
                    return SoundsManager.getClickSound_CivOptions();
                }
            });
            buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
            menuElements.add(new Text_Title_v2_TextLR(Game.lang.get("Statistics"), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, ""));
            buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
            menuElements.add(new ButtonCurrentSituation(Game.lang.get("Missions") + ": " + Game.lang.get("GoldenAge"), Images.goldenGold, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
                public void actionElement() {
                    InGame_CourtOptions.disableAllViews();
                    Game.menuManager.rebuildInGame_CourtGoldenAges();
                    Game.menuManager.setVisibleInGame_Court(true);
                    InGame_Court.lTime = 0L;
                }

                public void buildElementHover() {
                    List<MenuElement_HoverElement> nElements = new ArrayList();
                    List<MenuElement_HoverElement_Type> nData = new ArrayList();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Missions") + ": " + Game.lang.get("GoldenAge"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.goldenGold, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements, true);
                }

                public int getSFX() {
                    return SoundsManager.getClickSound_CivOptions();
                }
            });
            buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
            menuElements.add(new ButtonCurrentSituation(Game.lang.get("Statistics"), Images.stats, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
                public void actionElement() {
                    InGame_Court.inCourt = false;
                    InGame_Court.inSearchProvinces = false;
                    InGame_CourtOptions.disableAllViews();
                    Game.menuManager.rebuildInGame_CourtStatistics();
                    Game.menuManager.setVisibleInGame_Court(true);
                    InGame_Court.lTime = 0L;
                }

                public void buildElementHover() {
                    List<MenuElement_HoverElement> nElements = new ArrayList();
                    List<MenuElement_HoverElement_Type> nData = new ArrayList();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Statistics"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.stats, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements, true);
                }

                public int getSFX() {
                    return SoundsManager.getClickSound_CivOptions();
                }
            });
            buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
            menuElements.add(new ButtonCurrentSituation(Game.lang.get("Graph"), Images.stats, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
                public void actionElement() {
                    InGame_GraphPopulation.activeModeID = 0;
                    Game.menuManager.rebuildInGame_GraphPopulation();
                }

                public void buildElementHover() {
                    List<MenuElement_HoverElement> nElements = new ArrayList();
                    List<MenuElement_HoverElement_Type> nData = new ArrayList();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Graph"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.stats, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements, true);
                }

                public int getSFX() {
                    return SoundsManager.getClickSound_CivOptions();
                }
            });
            buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
            menuElements.add(new ButtonCurrentSituation(Game.lang.get("CivilizationBonuses"), CivilizationRanking.getCivilizationRank_IMG(Game.player.iCivID), paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
                public void actionElement() {
                    InGame_CivBonuses.iCivID = Game.player.iCivID;
                    InGame_Civ.actionCivBonuses();
                }

                public void buildElementHover() {
                    List<MenuElement_HoverElement> nElements = new ArrayList();
                    List<MenuElement_HoverElement_Type> nData = new ArrayList();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("CivilizationBonuses"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(CivilizationRanking.getCivilizationRank_IMG(Game.player.iCivID), CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements, true);
                }

                public int getSFX() {
                    return SoundsManager.getClickSound_CivOptions();
                }
            });
            buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
            menuElements.add(new Text_Title_v2_TextLR(Game.lang.get("More"), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, ""));
            buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
            menuElements.add(new ButtonCurrentSituation(Game.lang.get("Console"), Images.console, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
                public void actionElement() {
                    if (Game.menuManager.getVisibleInGame_Console()) {
                        Game.menuManager.setVisibleInGame_Console(false);
                    } else {
                        InGame_CourtOptions2.disableAllViews();
                        Game.menuManager.rebuildInGame_Console();
                        Game.menuManager.setVisibleInGame_Court(false);
                    }

                }

                public void buildElementHover() {
                    List<MenuElement_HoverElement> nElements = new ArrayList();
                    List<MenuElement_HoverElement_Type> nData = new ArrayList();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Console"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.console, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements, true);
                }

                public int getSFX() {
                    return SoundsManager.getClickSound_CivOptions();
                }
            });
            buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
            menuElements.add(new ButtonCurrentSituation(Game.lang.get("Hide") + ": UI", Images.x, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
                public void actionElement() {
                    InGame_HideUI.addDate = false;
                    Game.menuManager.setViewIDWithoutAnimation(View.IN_GAME_HIDE_UI);
                    Game.menuManager.addToastGold(Game.lang.get("Close") + " - Click the TOP LEFT", Images.x);
                    Game.menuManager.addToastGold(Game.lang.get("Close") + " - Click the TOP LEFT", Images.x);
                }

                public void buildElementHover() {
                    List<MenuElement_HoverElement> nElements = new ArrayList();
                    List<MenuElement_HoverElement_Type> nData = new ArrayList();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Hide") + ": UI", CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.x, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements, true);
                }

                public int getSFX() {
                    return SoundsManager.getClickSound_CivOptions();
                }
            });
            buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
            menuElements.add(new ButtonCurrentSituation(Game.lang.get("Hide") + ": UI - " + Game.lang.get("Date"), Images.x, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
                public void actionElement() {
                    InGame_HideUI.addDate = true;
                    Game.menuManager.setViewIDWithoutAnimation(View.IN_GAME_HIDE_UI);
                    Game.menuManager.addToastGold(Game.lang.get("Close") + " - Click the TOP LEFT", Images.x);
                    Game.menuManager.addToastGold(Game.lang.get("Close") + " - Click the TOP LEFT", Images.x);
                }

                public void buildElementHover() {
                    List<MenuElement_HoverElement> nElements = new ArrayList();
                    List<MenuElement_HoverElement_Type> nData = new ArrayList();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Hide") + ": UI - " + Game.lang.get("Date"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.x, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements, true);
                }

                public int getSFX() {
                    return SoundsManager.getClickSound_CivOptions();
                }
            });
            buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
            menuElements.add(new Text_Title_v2_TextLR(Game.lang.get("Diplomacy"), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, ""));
            buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
            menuElements.add(new ButtonCurrentSituation(Game.lang.get("CurrentWars"), Images.war, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
                public void actionElement() {
                    InGame_Court.inCourt = false;
                    InGame_Court.inSearchProvinces = false;
                    InGame_CourtOptions.disableAllViews();
                    Game.menuManager.rebuildInGame_CourtWorld_Wars();
                    Game.menuManager.setVisibleInGame_Court(true);
                    InGame_Court.lTime = 0L;
                }

                public void buildElementHover() {
                    List<MenuElement_HoverElement> nElements = new ArrayList();
                    List<MenuElement_HoverElement_Type> nData = new ArrayList();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get(((Map_Data)Game.map.lMaps.get(Game.map.getActiveMapID())).mapData.Name) + ": ", CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Clear(Game.lang.get("CurrentWars"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.war, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements, true);
                }

                public int getSFX() {
                    return SoundsManager.getClickSound_CivOptions();
                }
            });
            buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
            menuElements.add(new ButtonCurrentSituation(Game.lang.get("Alliances"), Images.alliance, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
                public void actionElement() {
                    InGame_Court.inCourt = false;
                    InGame_Court.inSearchProvinces = false;
                    InGame_CourtOptions.disableAllViews();
                    Game.menuManager.rebuildInGame_CourtWorld_Alliances();
                    Game.menuManager.setVisibleInGame_Court(true);
                    InGame_Court.lTime = 0L;
                }

                public void buildElementHover() {
                    List<MenuElement_HoverElement> nElements = new ArrayList();
                    List<MenuElement_HoverElement_Type> nData = new ArrayList();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get(((Map_Data)Game.map.lMaps.get(Game.map.getActiveMapID())).mapData.Name) + ": ", CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Clear(Game.lang.get("Alliances"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.alliance, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements, true);
                }

                public int getSFX() {
                    return SoundsManager.getClickSound_CivOptions();
                }
            });
            buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
            menuElements.add(new ButtonCurrentSituation(Game.lang.get(Game_Ages.getVassals()), Images.vassal, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
                public void actionElement() {
                    InGame_Court.inCourt = false;
                    InGame_Court.inSearchProvinces = false;
                    InGame_CourtOptions.disableAllViews();
                    Game.menuManager.rebuildInGame_CourtWorld_Vassals();
                    Game.menuManager.setVisibleInGame_Court(true);
                    InGame_Court.lTime = 0L;
                }

                public void buildElementHover() {
                    List<MenuElement_HoverElement> nElements = new ArrayList();
                    List<MenuElement_HoverElement_Type> nData = new ArrayList();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get(((Map_Data)Game.map.lMaps.get(Game.map.getActiveMapID())).mapData.Name) + ": ", CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Clear(Game.lang.get(Game_Ages.getVassals()), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.vassal, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements, true);
                }

                public int getSFX() {
                    return SoundsManager.getClickSound_CivOptions();
                }
            });
            buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
            menuElements.add(new ButtonCurrentSituation(Game.lang.get("DefensivePacts"), Images.defensivePact, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
                public void actionElement() {
                    InGame_Court.inCourt = false;
                    InGame_Court.inSearchProvinces = false;
                    InGame_CourtOptions.disableAllViews();
                    Game.menuManager.rebuildInGame_CourtWorld_Defensive();
                    Game.menuManager.setVisibleInGame_Court(true);
                    InGame_Court.lTime = 0L;
                }

                public void buildElementHover() {
                    List<MenuElement_HoverElement> nElements = new ArrayList();
                    List<MenuElement_HoverElement_Type> nData = new ArrayList();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get(((Map_Data)Game.map.lMaps.get(Game.map.getActiveMapID())).mapData.Name) + ": ", CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Clear(Game.lang.get("DefensivePacts"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.defensivePact, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements, true);
                }

                public int getSFX() {
                    return SoundsManager.getClickSound_CivOptions();
                }
            });
            buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
            menuElements.add(new ButtonCurrentSituation(Game.lang.get("NonAggressionPacts"), Images.nonAggression, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
                public void actionElement() {
                    InGame_Court.inCourt = false;
                    InGame_Court.inSearchProvinces = false;
                    InGame_CourtOptions.disableAllViews();
                    Game.menuManager.rebuildInGame_CourtWorld_NonAggression();
                    Game.menuManager.setVisibleInGame_Court(true);
                    InGame_Court.lTime = 0L;
                }

                public void buildElementHover() {
                    List<MenuElement_HoverElement> nElements = new ArrayList();
                    List<MenuElement_HoverElement_Type> nData = new ArrayList();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get(((Map_Data)Game.map.lMaps.get(Game.map.getActiveMapID())).mapData.Name) + ": ", CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Clear(Game.lang.get("NonAggressionPacts"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.nonAggression, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements, true);
                }

                public int getSFX() {
                    return SoundsManager.getClickSound_CivOptions();
                }
            });
            buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
            menuElements.add(new ButtonCurrentSituation(Game.lang.get("Truces"), Images.truce, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true) {
                public void actionElement() {
                    InGame_Court.inCourt = false;
                    InGame_Court.inSearchProvinces = false;
                    InGame_CourtOptions.disableAllViews();
                    Game.menuManager.rebuildInGame_CourtWorld_Truces();
                    Game.menuManager.setVisibleInGame_Court(true);
                    InGame_Court.lTime = 0L;
                }

                public void buildElementHover() {
                    List<MenuElement_HoverElement> nElements = new ArrayList();
                    List<MenuElement_HoverElement_Type> nData = new ArrayList();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get(((Map_Data)Game.map.lMaps.get(Game.map.getActiveMapID())).mapData.Name) + ": ", CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Clear(Game.lang.get("Truces"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.truce, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements, true);
                }

                public int getSFX() {
                    return SoundsManager.getClickSound_CivOptions();
                }
            });
            buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        }

        menuY += InGame_CourtOptions.menuH;
        int menuHeight = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - CFG.PADDING * 3);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, menuHeight)));
        this.initMenu((MenuTitle)null, menuX, menuY, menuWidth, menuHeight, menuElements, false, false);
        this.drawScrollPositionAlways = false;
        Game.menuManager.setInGame_CivOptions_Title(Game.lang.get(GameValues.court.COUNCIL_NAME));
    }

    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean menuIsActive, Status titleStatus) {
        if (lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateX = iTranslateX - CFG.BUTTON_WIDTH + (int)((float)CFG.BUTTON_WIDTH * ((float)(CFG.currentTimeMillis - lTime) / 60.0F));
        }

        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - InGame_CourtOptions.menuH + iTranslateY, this.getWidth(), this.getHeight() + InGame_CourtOptions.menuH + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop500, Images.insideBot500);
        ImageManager.getImage(Images.rulerOver).draw2(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.rulerOver).getWidth() / 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.rulerOver).getHeight()));
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }

    public void setVisible(boolean visible) {
        super.setVisible(visible);
        lTime = CFG.currentTimeMillis;
        lTime2 = CFG.currentTimeMillis;
        if (!visible && Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_COLONIZE_CHOOSE_PROVINCE) {
            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
        }

    }

    public void actionCloseMenu() {
        super.actionCloseMenu();
        if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_COLONIZE_CHOOSE_PROVINCE) {
            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
        }

    }

    public void onHovered() {
        super.onHovered();
        Game.menuManager.setOrderOfMenu_InGameCourt();
    }

    public static final void actionSearchProvinces() {
        inCourt = false;
        inSearchProvinces = true;
        InGame_CourtOptions.disableAllViews();
        InGame_Court_WorldSearch.sSearch = "";
        Game.menuManager.rebuildInGame_CourtSearch();
        Game.menuManager.setVisibleInGame_Court(true);
        lTime = 0L;
    }
}
