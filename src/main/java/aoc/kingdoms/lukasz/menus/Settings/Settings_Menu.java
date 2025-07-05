//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.menus.Settings;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.FBO.FBOProvinceNames;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.jakowski.setting.SettingsDesktop;
import aoc.kingdoms.lukasz.jakowski.setting.SettingsManager;
import aoc.kingdoms.lukasz.jakowski.setting.SettingsProvince;
import aoc.kingdoms.lukasz.map.map.Ship.ShipManager;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.menu.Menu;
import aoc.kingdoms.lukasz.menu.View;
import aoc.kingdoms.lukasz.menu.menuTitle.MenuTitleIMG;
import aoc.kingdoms.lukasz.menu_element.Empty;
import aoc.kingdoms.lukasz.menu_element.MenuElement;
import aoc.kingdoms.lukasz.menu_element.Slider;
import aoc.kingdoms.lukasz.menu_element.Status;
import aoc.kingdoms.lukasz.menu_element.button.ButtonGame2;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_Hover;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG_Center;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text_Desc;
import aoc.kingdoms.lukasz.menu_element.textStatic.Text_Title_v2_TextLR;
import aoc.kingdoms.lukasz.menus.Init_SelectLanguage;
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.textures.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import team.rainfall.fontFix.FontFix;

import java.util.ArrayList;
import java.util.List;

public class  Settings_Menu extends Menu {
    public static View goBackToMenu;
    public static long provinceInView_Time;
    public static long drawProvinces_Time;
    public static long drawProvincesFBO_Time;
    public static long drawArmies_Time;
    public static long drawProvincesBorder_Time;
    public static long drawProvincesNames_Time;
    public static long drawCivsNames_Time;
    public static long drawCities_Time;
    public static long drawMoveUnits_Time;
    public static long drawClouds_Time;
    public static long drawShips_Time;
    public static long drawShips2_Time;
    public static long drawMapBorder_Time;
    public static boolean updateTimes;
    public static long lastUpdateTime;
    public int statTextW = 0;
    public int statTextPercW = 0;
    public int nanoW = 0;

    public Settings_Menu() {
        List<MenuElement> menuElements = new ArrayList();
        int paddingLeft = CFG.PADDING * 2 + Images.boxTitleBORDERWIDTH;
        int titleHeight = ImageManager.getImage(Images.title600).getHeight();
        int menuWidth = ImageManager.getImage(Images.title600).getWidth();
        int menuX = CFG.BUTTON_WIDTH + Renderer.boxBGExtraY + CFG.PADDING;
        int menuY = ImageManager.getImage(Images.topStats).getHeight() + CFG.PADDING * 2 + ImageManager.getImage(Images.title600).getHeight();
        int buttonYPadding = CFG.PADDING;
        int buttonY = buttonYPadding * 2;
        menuElements.add(new ButtonGame2(Game.lang.get("Back"), 1, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, true, CFG.BUTTON_HEIGHT2) {
            public void actionElement() {
                Game.saveSettings();
                Renderer.drawArmyInProvince = true;
                Game.menuManager.setViewID(Settings_Menu.goBackToMenu);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + buttonYPadding * 2;
        menuElements.add(new Text_Title_v2_TextLR(Game.lang.get("Graphics"), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, ""));
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        if (CFG.isDesktop()) {
            menuElements.add(new ButtonGame2(Game.lang.get("Fullscreen"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, true, CFG.BUTTON_HEIGHT, true) {
                public boolean getCheckboxState() {
                    return SettingsDesktop.fullscreen;
                }

                public void actionElement() {
                    SettingsDesktop.fullscreen = !SettingsDesktop.fullscreen;
                    SettingsDesktop.saveConfig();
                    Game.menuManager.addToastGold(Game.lang.get("GameNeedsToBeRestartedToApplyTheChanges"), Images.settings);
                }
            });
            buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
            menuElements.add(new ButtonGame2(Game.lang.get("Resolution") + ": " + (SettingsDesktop.iWidth > 0 && SettingsDesktop.iHeight > 0 ? SettingsDesktop.iWidth + " x " + SettingsDesktop.iHeight : CFG.GAME_WIDTH + "x" + CFG.GAME_HEIGHT), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, true, CFG.BUTTON_HEIGHT) {
                public void updateLanguage() {
                    this.setText(Game.lang.get("Resolution") + ": " + (SettingsDesktop.iWidth > 0 && SettingsDesktop.iHeight > 0 ? SettingsDesktop.iWidth + " x " + SettingsDesktop.iHeight : CFG.GAME_WIDTH + "x" + CFG.GAME_HEIGHT));
                }

                public void actionElement() {
                    Game.menuManager.setViewID(View.SETTINGS_RESOLUTION);
                }
            });
            buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        }

        menuElements.add(new ButtonGame2(Game.lang.get("UIScale"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, true, CFG.BUTTON_HEIGHT) {
            public void actionElement() {
                Game.menuManager.setViewID(View.SETTINGS_UI);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        menuElements.add(new Text_Title_v2_TextLR(Game.lang.get("Game"), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, ""));
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonGame2(Game.lang.get("SelectLanguage"), 1, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, true, CFG.BUTTON_HEIGHT) {
            public void actionElement() {
                Init_SelectLanguage.goBackToMenu = View.SETTINGS;
                Game.menuManager.setViewIDWithoutAnimation(View.INIT_GAME_MENU_LANGUAGE);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonGame2(SettingsProvince.getSettingsText_Double(), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2 - (CFG.BUTTON_WIDTH + CFG.PADDING), true, CFG.BUTTON_HEIGHT, true) {
            public void updateLanguage() {
                this.setText(Game.lang.get("Sidebar") + ": " + (Game.settingsManager.enableHideSideMenu ? Game.lang.get("Off") : Game.lang.get("On")));
            }

            public boolean getCheckboxState() {
                return !Game.settingsManager.enableHideSideMenu;
            }
        });
        menuElements.add(new ButtonGame2(">>", CFG.FONT_BOLD, -1, paddingLeft + (menuWidth - paddingLeft * 2 - (CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2)) + CFG.PADDING * 2 + CFG.BUTTON_WIDTH, buttonY, CFG.BUTTON_WIDTH, true, CFG.BUTTON_HEIGHT) {
            public void actionElement() {
                Game.settingsManager.enableHideSideMenu = !Game.settingsManager.enableHideSideMenu;
                Settings_Menu.this.updateLanguage();
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonGame2("<<", CFG.FONT_BOLD, -1, paddingLeft, buttonY, CFG.BUTTON_WIDTH, true, CFG.BUTTON_HEIGHT) {
            public void actionElement() {
                boolean changed = false;

                for(int i = 0; i < GameValues.value.AUTO_SAVE_DAYS.length; ++i) {
                    if (GameValues.value.AUTO_SAVE_DAYS[i] == Game.settingsManager.AUTO_SAVE_DAYS) {
                        Game.settingsManager.AUTO_SAVE_DAYS = GameValues.value.AUTO_SAVE_DAYS[Math.max(0, i - 1)];
                        changed = true;
                        break;
                    }
                }

                if (!changed) {
                    Game.settingsManager.AUTO_SAVE_DAYS = GameValues.value.AUTO_SAVE_DAYS[0];
                }

                Settings_Menu.this.updateLanguage();
            }
        });
        menuElements.add(new ButtonGame2(Game.lang.get("Autosave") + ": " + (Game.settingsManager.AUTO_SAVE_DAYS == 0 ? Game.lang.get("Off") : Game.lang.get("DaysX", Game.settingsManager.AUTO_SAVE_DAYS)), CFG.FONT_REGULAR, -1, paddingLeft + CFG.BUTTON_WIDTH + CFG.PADDING, buttonY, menuWidth - paddingLeft * 2 - (CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2), true, CFG.BUTTON_HEIGHT) {
            public void updateLanguage() {
                this.setText(Game.lang.get("Autosave") + ": " + (Game.settingsManager.AUTO_SAVE_DAYS == 0 ? Game.lang.get("Off") : Game.lang.get("DaysX", Game.settingsManager.AUTO_SAVE_DAYS)));
            }
        });
        menuElements.add(new ButtonGame2(">>", CFG.FONT_BOLD, -1, paddingLeft + (menuWidth - paddingLeft * 2 - (CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2)) + CFG.PADDING * 2 + CFG.BUTTON_WIDTH, buttonY, CFG.BUTTON_WIDTH, true, CFG.BUTTON_HEIGHT) {
            public void actionElement() {
                boolean changed = false;

                for(int i = 0; i < GameValues.value.AUTO_SAVE_DAYS.length; ++i) {
                    if (GameValues.value.AUTO_SAVE_DAYS[i] == Game.settingsManager.AUTO_SAVE_DAYS) {
                        Game.settingsManager.AUTO_SAVE_DAYS = GameValues.value.AUTO_SAVE_DAYS[Math.min(GameValues.value.AUTO_SAVE_DAYS.length - 1, i + 1)];
                        changed = true;
                        break;
                    }
                }

                if (!changed) {
                    Game.settingsManager.AUTO_SAVE_DAYS = GameValues.value.AUTO_SAVE_DAYS[0];
                }

                Settings_Menu.this.updateLanguage();
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        if (!CFG.isDesktop()) {
            menuElements.add(new ButtonGame2("<<", CFG.FONT_BOLD, -1, paddingLeft, buttonY, CFG.BUTTON_WIDTH, true, CFG.BUTTON_HEIGHT) {
                public void actionElement() {
                    --Game.settingsManager.IN_GAME_LEFT_PADDING_EXTRA;
                    Settings_Menu.this.updateLanguage();
                    Game.menuManager.rebuildInGame_CourtOptions();
                }
            });
            menuElements.add(new ButtonGame2(SettingsProvince.getSettingsText_InGamePaddingLeft(), CFG.FONT_REGULAR, -1, paddingLeft + CFG.BUTTON_WIDTH + CFG.PADDING, buttonY, menuWidth - paddingLeft * 2 - (CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2), true, CFG.BUTTON_HEIGHT) {
                public void updateLanguage() {
                    this.setText(SettingsProvince.getSettingsText_InGamePaddingLeft());
                }
            });
            menuElements.add(new ButtonGame2(">>", CFG.FONT_BOLD, -1, paddingLeft + (menuWidth - paddingLeft * 2 - (CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2)) + CFG.PADDING * 2 + CFG.BUTTON_WIDTH, buttonY, CFG.BUTTON_WIDTH, true, CFG.BUTTON_HEIGHT) {
                public void actionElement() {
                    ++Game.settingsManager.IN_GAME_LEFT_PADDING_EXTRA;
                    Settings_Menu.this.updateLanguage();
                    Game.menuManager.rebuildInGame_CourtOptions();
                }
            });
            buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        }

        menuElements.add(new Text_Title_v2_TextLR(Game.lang.get("Audio"), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, ""));
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonGame2(Game.lang.get("Audio"), 1, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, true, CFG.BUTTON_HEIGHT) {
            public void actionElement() {
                if (Game.menuManager.getVisibleSettingsAudio()) {
                    Game.menuManager.setVisibleSettingsAudio(false);
                } else {
                    Game.menuManager.rebuildSettingsAudio();
                }

            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        menuElements.add(new Text_Title_v2_TextLR(Game.lang.get("Provinces"), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 6, "") {
            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Center(FontFix.langGet("RTD0","Render Time Details"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text_Desc(FontFix.langGet("RTD1","On the right side of the menu, in a black box, you can check which elements take the most render time."), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text_Desc(FontFix.langGet("RTD2","Typically, these are:"), CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text_Desc(FontFix.langGet("RTD3","Province Borders"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text_Desc(FontFix.langGet("RTD4","Province Names"), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text_Desc(FontFix.langGet("RTD5","After each change, check the FPS to see if the improvements are sufficient."), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Line());
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text_Desc(FontFix.langGet("RTD6","Steps to Improve FPS"), CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text_Desc(FontFix.langGet("RTD7","Decrease Province Names to: Medium -> Turn off Double Border -> Decrease Civilization Names to: Low -> Decrease Province Borders to: Medium -> If needed, further decrease Province Borders to: Low -> Continue adjusting other settings as required."), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT2));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonGame2("<<", CFG.FONT_BOLD, -1, paddingLeft, buttonY, CFG.BUTTON_WIDTH, true, CFG.BUTTON_HEIGHT) {
            public void actionElement() {
                SettingsProvince.updateSettingsProvinceBorder(-1);
                Settings_Menu.this.updateLanguage();
            }
        });
        menuElements.add(new ButtonGame2(SettingsProvince.getSettingsText(), CFG.FONT_REGULAR, -1, paddingLeft + CFG.BUTTON_WIDTH + CFG.PADDING, buttonY, menuWidth - paddingLeft * 2 - (CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2), true, CFG.BUTTON_HEIGHT) {
            public void updateLanguage() {
                this.setText(SettingsProvince.getSettingsText());
            }
        });
        menuElements.add(new ButtonGame2(">>", CFG.FONT_BOLD, -1, paddingLeft + (menuWidth - paddingLeft * 2 - (CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2)) + CFG.PADDING * 2 + CFG.BUTTON_WIDTH, buttonY, CFG.BUTTON_WIDTH, true, CFG.BUTTON_HEIGHT) {
            public void actionElement() {
                SettingsProvince.updateSettingsProvinceBorder(1);
                Settings_Menu.this.updateLanguage();
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonGame2(SettingsProvince.getSettingsText_Double(), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2 - (CFG.BUTTON_WIDTH + CFG.PADDING), true, CFG.BUTTON_HEIGHT) {
            public void updateLanguage() {
                this.setText(SettingsProvince.getSettingsText_Double());
            }
        });
        menuElements.add(new ButtonGame2(">>", CFG.FONT_BOLD, -1, paddingLeft + (menuWidth - paddingLeft * 2 - (CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2)) + CFG.PADDING * 2 + CFG.BUTTON_WIDTH, buttonY, CFG.BUTTON_WIDTH, true, CFG.BUTTON_HEIGHT) {
            public void actionElement() {
                SettingsProvince.updateSettings_Double();
                Settings_Menu.this.updateLanguage();
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonGame2("<<", CFG.FONT_BOLD, -1, paddingLeft, buttonY, CFG.BUTTON_WIDTH, true, CFG.BUTTON_HEIGHT) {
            public void actionElement() {
                SettingsProvince.updateSettingsProvinceNames(-1);
                Settings_Menu.this.updateLanguage();
                FBOProvinceNames.redrawnProvinceNames();
            }
        });
        menuElements.add(new ButtonGame2(SettingsProvince.getSettingsText_Names(), CFG.FONT_REGULAR, -1, paddingLeft + CFG.BUTTON_WIDTH + CFG.PADDING, buttonY, menuWidth - paddingLeft * 2 - (CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2), true, CFG.BUTTON_HEIGHT) {
            public void updateLanguage() {
                this.setText(SettingsProvince.getSettingsText_Names());
            }
        });
        menuElements.add(new ButtonGame2(">>", CFG.FONT_BOLD, -1, paddingLeft + (menuWidth - paddingLeft * 2 - (CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2)) + CFG.PADDING * 2 + CFG.BUTTON_WIDTH, buttonY, CFG.BUTTON_WIDTH, true, CFG.BUTTON_HEIGHT) {
            public void actionElement() {
                SettingsProvince.updateSettingsProvinceNames(1);
                Settings_Menu.this.updateLanguage();
                FBOProvinceNames.redrawnProvinceNames();
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonGame2("<<", CFG.FONT_BOLD, -1, paddingLeft, buttonY, CFG.BUTTON_WIDTH, true, CFG.BUTTON_HEIGHT) {
            public void actionElement() {
                SettingsProvince.updateSettingsProvinceNames_Scale(-1);
                Settings_Menu.this.updateLanguage();
                FBOProvinceNames.redrawnProvinceNames();
            }
        });
        menuElements.add(new ButtonGame2(SettingsProvince.getSettingsText_Names(), CFG.FONT_REGULAR, -1, paddingLeft + CFG.BUTTON_WIDTH + CFG.PADDING, buttonY, menuWidth - paddingLeft * 2 - (CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2), true, CFG.BUTTON_HEIGHT) {
            public void updateLanguage() {
                this.setText(Game.lang.get("ProvinceNamesMinScale") + ": " + CFG.getPrecision2(Game.settingsManager.PROVINCE_NAMES_SCALE, 1000));
            }
        });
        menuElements.add(new ButtonGame2(">>", CFG.FONT_BOLD, -1, paddingLeft + (menuWidth - paddingLeft * 2 - (CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2)) + CFG.PADDING * 2 + CFG.BUTTON_WIDTH, buttonY, CFG.BUTTON_WIDTH, true, CFG.BUTTON_HEIGHT) {
            public void actionElement() {
                SettingsProvince.updateSettingsProvinceNames_Scale(1);
                Settings_Menu.this.updateLanguage();
                FBOProvinceNames.redrawnProvinceNames();
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonGame2("<<", CFG.FONT_BOLD, -1, paddingLeft, buttonY, CFG.BUTTON_WIDTH, true, CFG.BUTTON_HEIGHT) {
            public void actionElement() {
                SettingsProvince.updateSettingsCivNames(-1);
                Settings_Menu.this.updateLanguage();
            }
        });
        menuElements.add(new ButtonGame2(SettingsProvince.getSettingsText_CivNames(), CFG.FONT_REGULAR, -1, paddingLeft + CFG.BUTTON_WIDTH + CFG.PADDING, buttonY, menuWidth - paddingLeft * 2 - (CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2), true, CFG.BUTTON_HEIGHT) {
            public void updateLanguage() {
                this.setText(SettingsProvince.getSettingsText_CivNames());
            }
        });
        menuElements.add(new ButtonGame2(">>", CFG.FONT_BOLD, -1, paddingLeft + (menuWidth - paddingLeft * 2 - (CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2)) + CFG.PADDING * 2 + CFG.BUTTON_WIDTH, buttonY, CFG.BUTTON_WIDTH, true, CFG.BUTTON_HEIGHT) {
            public void actionElement() {
                SettingsProvince.updateSettingsCivNames(1);
                Settings_Menu.this.updateLanguage();
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonGame2("<<", CFG.FONT_BOLD, -1, paddingLeft, buttonY, CFG.BUTTON_WIDTH, true, CFG.BUTTON_HEIGHT) {
            public void actionElement() {
                SettingsProvince.updateSettings_ProvinceFlags(-1);
                Settings_Menu.this.updateLanguage();
            }
        });
        menuElements.add(new ButtonGame2(SettingsProvince.getSettingsText_ProvinceFlags(), CFG.FONT_REGULAR, -1, paddingLeft + CFG.BUTTON_WIDTH + CFG.PADDING, buttonY, menuWidth - paddingLeft * 2 - (CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2), true, CFG.BUTTON_HEIGHT) {
            public void updateLanguage() {
                this.setText(SettingsProvince.getSettingsText_ProvinceFlags());
            }
        });
        menuElements.add(new ButtonGame2(">>", CFG.FONT_BOLD, -1, paddingLeft + (menuWidth - paddingLeft * 2 - (CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2)) + CFG.PADDING * 2 + CFG.BUTTON_WIDTH, buttonY, CFG.BUTTON_WIDTH, true, CFG.BUTTON_HEIGHT) {
            public void actionElement() {
                SettingsProvince.updateSettings_ProvinceFlags(1);
                Settings_Menu.this.updateLanguage();
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonGame2(SettingsProvince.getSettingsText_Cities(), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2 - (CFG.BUTTON_WIDTH + CFG.PADDING), true, CFG.BUTTON_HEIGHT) {
            public void updateLanguage() {
                this.setText(SettingsProvince.getSettingsText_Cities());
            }
        });
        menuElements.add(new ButtonGame2(">>", CFG.FONT_BOLD, -1, paddingLeft + (menuWidth - paddingLeft * 2 - (CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2)) + CFG.PADDING * 2 + CFG.BUTTON_WIDTH, buttonY, CFG.BUTTON_WIDTH, true, CFG.BUTTON_HEIGHT) {
            public void actionElement() {
                SettingsProvince.updateSettings_Cities();
                Settings_Menu.this.updateLanguage();
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        menuElements.add(new Slider(Game.lang.get("Ships") + ": ", paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, 0, 100, Game.settingsManager.SHIPS_ON_MAP) {
            public void actionElement() {
                Game.settingsManager.SHIPS_ON_MAP = this.getCurrent();
                ShipManager.updateLimitOfShipsAtSea();
            }

            public String getDrawText() {
                return this.sText + Game.settingsManager.SHIPS_ON_MAP + "%";
            }

            public boolean getScrollable() {
                return false;
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        if (Game.cloudsAnimation.loadClouds()) {
            menuElements.add(new ButtonGame2(SettingsProvince.getSettingsText_Clouds(), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2 - (CFG.BUTTON_WIDTH + CFG.PADDING), true, CFG.BUTTON_HEIGHT) {
                public void updateLanguage() {
                    this.setText(SettingsProvince.getSettingsText_Clouds());
                }
            });
            menuElements.add(new ButtonGame2(">>", CFG.FONT_BOLD, -1, paddingLeft + (menuWidth - paddingLeft * 2 - (CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2)) + CFG.PADDING * 2 + CFG.BUTTON_WIDTH, buttonY, CFG.BUTTON_WIDTH, true, CFG.BUTTON_HEIGHT) {
                public void actionElement() {
                    SettingsProvince.updateSettings_Clouds();
                    Settings_Menu.this.updateLanguage();
                }
            });
            buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        }

        menuElements.add(new ButtonGame2("<<", CFG.FONT_BOLD, -1, paddingLeft, buttonY, CFG.BUTTON_WIDTH, true, CFG.BUTTON_HEIGHT) {
            public void actionElement() {
                Game.DRAW_ARMY_MIN_SCALE = Math.max(0.05F, Game.DRAW_ARMY_MIN_SCALE - 0.05F);
                Settings_Menu.this.updateLanguage();
            }
        });
        menuElements.add(new ButtonGame2(Game.lang.get("DrawArmyScale") + ": " + CFG.getPrecision2(Game.DRAW_ARMY_MIN_SCALE, 100), CFG.FONT_REGULAR, -1, paddingLeft + CFG.BUTTON_WIDTH + CFG.PADDING, buttonY, menuWidth - paddingLeft * 2 - (CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2), true, CFG.BUTTON_HEIGHT) {
            public void updateLanguage() {
                this.setText(Game.lang.get("DrawArmyScale") + ": " + CFG.getPrecision2(Game.DRAW_ARMY_MIN_SCALE, 100));
            }
        });
        menuElements.add(new ButtonGame2(">>", CFG.FONT_BOLD, -1, paddingLeft + (menuWidth - paddingLeft * 2 - (CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2)) + CFG.PADDING * 2 + CFG.BUTTON_WIDTH, buttonY, CFG.BUTTON_WIDTH, true, CFG.BUTTON_HEIGHT) {
            public void actionElement() {
                Game.DRAW_ARMY_MIN_SCALE = Math.min(1.0F, Game.DRAW_ARMY_MIN_SCALE + 0.05F);
                Settings_Menu.this.updateLanguage();
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        if (CFG.isDesktop() || !GameValues.value.MOBILE_DISABLE_FBO) {
            menuElements.add(new ButtonGame2(SettingsProvince.getSettingsText_FBO(), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2 - (CFG.BUTTON_WIDTH + CFG.PADDING), true, CFG.BUTTON_HEIGHT) {
                public void updateLanguage() {
                    this.setText(SettingsProvince.getSettingsText_FBO());
                }
            });
            menuElements.add(new ButtonGame2(">>", CFG.FONT_BOLD, -1, paddingLeft + (menuWidth - paddingLeft * 2 - (CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2)) + CFG.PADDING * 2 + CFG.BUTTON_WIDTH, buttonY, CFG.BUTTON_WIDTH, true, CFG.BUTTON_HEIGHT) {
                public void actionElement() {
                    SettingsProvince.updateSettings_FBO();
                    Settings_Menu.this.updateLanguage();
                }
            });
            buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
            menuElements.add(new ButtonGame2(SettingsProvince.getSettingsText_FBO_Provinces(), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2 - (CFG.BUTTON_WIDTH + CFG.PADDING), true, CFG.BUTTON_HEIGHT) {
                public void updateLanguage() {
                    this.setText(SettingsProvince.getSettingsText_FBO_Provinces());
                }
            });
            menuElements.add(new ButtonGame2(">>", CFG.FONT_BOLD, -1, paddingLeft + (menuWidth - paddingLeft * 2 - (CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2)) + CFG.PADDING * 2 + CFG.BUTTON_WIDTH, buttonY, CFG.BUTTON_WIDTH, true, CFG.BUTTON_HEIGHT) {
                public void actionElement() {
                    SettingsProvince.updateSettings_FBO_Provinces();
                    Settings_Menu.this.updateLanguage();
                }
            });
            buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        }

        menuElements.add(new Text_Title_v2_TextLR(Game.lang.get("ProvinceBorder") + ": " + Game.lang.get("Extra"), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, ""));
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonGame2("<<", CFG.FONT_BOLD, -1, paddingLeft, buttonY, CFG.BUTTON_WIDTH, true, CFG.BUTTON_HEIGHT) {
            public void actionElement() {
                Game.settingsManager.BORDER_EXTRA_WIDTH = Math.max(0.0F, Game.settingsManager.BORDER_EXTRA_WIDTH - 0.25F);
                Settings_Menu.this.updateLanguage();
            }
        });
        menuElements.add(new ButtonGame2(SettingsProvince.getSettingsText_ProvincesBorderExtra(), CFG.FONT_REGULAR, -1, paddingLeft + CFG.BUTTON_WIDTH + CFG.PADDING, buttonY, menuWidth - paddingLeft * 2 - (CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2), true, CFG.BUTTON_HEIGHT) {
            public void updateLanguage() {
                this.setText(SettingsProvince.getSettingsText_ProvincesBorderExtra());
            }
        });
        menuElements.add(new ButtonGame2(">>", CFG.FONT_BOLD, -1, paddingLeft + (menuWidth - paddingLeft * 2 - (CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2)) + CFG.PADDING * 2 + CFG.BUTTON_WIDTH, buttonY, CFG.BUTTON_WIDTH, true, CFG.BUTTON_HEIGHT) {
            public void actionElement() {
                Game.settingsManager.BORDER_EXTRA_WIDTH = Math.max(0.0F, Game.settingsManager.BORDER_EXTRA_WIDTH + 0.25F);
                Settings_Menu.this.updateLanguage();
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        menuElements.add(new Text_Title_v2_TextLR(Game.lang.get("Provinces") + ": " + Game.lang.get("Alpha"), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, ""));
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        menuElements.add(new Slider(Game.lang.get("ProvinceAlpha") + ": ", paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, 10, 255, (int)(Game.settingsManager.PROVINCE_ALPHA * 255.0F)) {
            public void actionElement() {
                Game.settingsManager.PROVINCE_ALPHA = (float)this.getCurrent() / 255.0F;
            }

            public String getDrawText() {
                return this.sText + CFG.getPrecision2((float)this.getCurrent() / 255.0F * 100.0F, 1) + "%";
            }

            public boolean getScrollable() {
                return false;
            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Default") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT));
                nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(31.372551F, 1) + "%", CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        menuElements.add(new Slider(Game.lang.get("Alpha") + ", " + Game.lang.get("OccupiedProvinces") + ": ", paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, 10, 100, (int)(Game.settingsManager.PROVINCE_OCCUPIED_ALPHA_EXTRA * 100.0F)) {
            public void actionElement() {
                Game.settingsManager.PROVINCE_OCCUPIED_ALPHA_EXTRA = (float)this.getCurrent() / 100.0F;
            }

            public String getDrawText() {
                return this.sText + CFG.getPrecision2(Game.settingsManager.PROVINCE_OCCUPIED_ALPHA_EXTRA * 100.0F, 1) + "%";
            }

            public boolean getScrollable() {
                return false;
            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Default") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT));
                nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(25.0F, 1) + "%", CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        menuElements.add(new Slider(Game.lang.get("Alpha") + ", " + Game.lang.get("Wasteland") + ": ", paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, 10, 255, (int)(Game.settingsManager.PROVINCE_ALPHA_WASTELAND * 255.0F)) {
            public void actionElement() {
                Game.settingsManager.PROVINCE_ALPHA_WASTELAND = (float)this.getCurrent() / 255.0F;
            }

            public String getDrawText() {
                return this.sText + CFG.getPrecision2((float)this.getCurrent() / 255.0F * 100.0F, 1) + "%";
            }

            public boolean getScrollable() {
                return false;
            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Default") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT));
                nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(30.000002F, 1) + "%", CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        menuElements.add(new Slider(Game.lang.get("Alpha") + ", " + Game.lang.get("ProvinceNames") + ": ", paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, 10, 255, (int)(Game.settingsManager.PROVINCE_NAMES_ALPHA * 255.0F)) {
            public void actionElement() {
                Game.settingsManager.PROVINCE_NAMES_ALPHA = (float)this.getCurrent() / 255.0F;
            }

            public String getDrawText() {
                return this.sText + CFG.getPrecision2((float)this.getCurrent() / 255.0F * 100.0F, 1) + "%";
            }

            public boolean getScrollable() {
                return false;
            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Default") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT));
                nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(45.0F, 1) + "%", CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        menuElements.add(new Slider(Game.lang.get("Alpha") + ", " + Game.lang.get("CivilizationsNames") + ": ", paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, 10, 255, (int)(Game.settingsManager.CIV_NAMES_TEXT_ALPHA * 255.0F)) {
            public void actionElement() {
                Game.settingsManager.CIV_NAMES_TEXT_ALPHA = (float)this.getCurrent() / 255.0F;
            }

            public String getDrawText() {
                return this.sText + CFG.getPrecision2((float)this.getCurrent() / 255.0F * 100.0F, 1) + "%";
            }

            public boolean getScrollable() {
                return false;
            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("Default") + ": ", CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT));
                nData.add(new MenuElement_HoverElement_Type_Text("" + CFG.getPrecision2(50.0F, 1) + "%", CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        menuElements.add(new Text_Title_v2_TextLR(Game.lang.get("TexturesQuality"), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, ""));
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonGame2(SettingsProvince.getSettingsText_Double(), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2 - (CFG.BUTTON_WIDTH + CFG.PADDING), true, CFG.BUTTON_HEIGHT, true) {
            public void updateLanguage() {
                this.setText(Game.lang.get("TexturesQuality") + ": " + (Game.highTextureSettings ? Game.lang.get("High") : Game.lang.get("Low")));
            }

            public boolean getCheckboxState() {
                return Game.highTextureSettings;
            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_Text_Desc(FontFix.langGet("ReduceTextureQuality","Reduce texture quality for better performance on graphics cards with low VRAM. Additionally, consider disabling FBO."), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        menuElements.add(new ButtonGame2(">>", CFG.FONT_BOLD, -1, paddingLeft + (menuWidth - paddingLeft * 2 - (CFG.BUTTON_WIDTH * 2 + CFG.PADDING * 2)) + CFG.PADDING * 2 + CFG.BUTTON_WIDTH, buttonY, CFG.BUTTON_WIDTH, true, CFG.BUTTON_HEIGHT) {
            public void actionElement() {
                Game.highTextureSettings = !Game.highTextureSettings;
                Game.saveTextueSettings();
                Game.menuManager.addToastGold(Game.lang.get("GameNeedsToBeRestartedToApplyTheChanges"), Images.settings);
                Settings_Menu.this.updateLanguage();
            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_Text_Desc(FontFix.langGet("ReduceTextureQuality","Reduce texture quality for better performance on graphics cards with low VRAM. Additionally, consider disabling FBO."), CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        menuElements.add(new Text_Title_v2_TextLR(Game.lang.get("More"), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, ""));
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonGame2(Game.lang.get("Council") + ": " + Game.lang.get("Tip"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, true, CFG.BUTTON_HEIGHT, true) {
            public boolean getCheckboxState() {
                return Game.settingsManager.COUNCIL_TIPS;
            }

            public void actionElement() {
                Game.settingsManager.COUNCIL_TIPS = !Game.settingsManager.COUNCIL_TIPS;
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        menuElements.add(new ButtonGame2(Game.lang.get("EdgeScrolling"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, true, CFG.BUTTON_HEIGHT, true) {
            public boolean getCheckboxState() {
                return Game.settingsManager.ENABLE_EDGE_SCROLL;
            }

            public void actionElement() {
                Game.settingsManager.ENABLE_EDGE_SCROLL = !Game.settingsManager.ENABLE_EDGE_SCROLL;
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        if (CFG.isDesktop()) {
            menuElements.add(new ButtonGame2(Game.lang.get("VSync"), CFG.FONT_REGULAR, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, true, CFG.BUTTON_HEIGHT, true) {
                public boolean getCheckboxState() {
                    return SettingsDesktop.vSync;
                }

                public void actionElement() {
                    SettingsDesktop.vSync = !SettingsDesktop.vSync;
                    SettingsDesktop.saveConfig();
                    Game.menuManager.addToastGold(Game.lang.get("GameNeedsToBeRestartedToApplyTheChanges"), Images.settings);
                }
            });
            buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        }

        menuElements.add(new ButtonGame2(Game.lang.get("Reset"), 1, -1, paddingLeft, buttonY, menuWidth - paddingLeft * 2, true, CFG.BUTTON_HEIGHT) {
            public void actionElement() {
                Game.settingsManager = new SettingsManager();
                Game.loadSettingsDefault();
                GameValues.updateSettingsFBO();
                Settings_Menu.this.updateLanguage();
            }
        });
        int var10000 = buttonY + ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        buttonY = 0;
        int i = 0;

        for(int iSize = menuElements.size(); i < iSize; ++i) {
            if (buttonY < ((MenuElement)menuElements.get(i)).getPosY() + ((MenuElement)menuElements.get(i)).getHeight() + CFG.PADDING * 2) {
                buttonY = ((MenuElement)menuElements.get(i)).getPosY() + ((MenuElement)menuElements.get(i)).getHeight() + CFG.PADDING * 2;
            }
        }

        i = Math.min(buttonY, CFG.GAME_HEIGHT - CFG.GAME_HEIGHT / 8 - CFG.PADDING * 2);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, i)));
        this.initMenu(new MenuTitleIMG(Game.lang.get("Settings"), false, false, Images.title600), CFG.GAME_WIDTH / 10, CFG.GAME_HEIGHT / 8, menuWidth, i, menuElements, true, false);
        this.drawScrollPositionAlways = false;
        this.statTextW = Renderer.getTextWidth("Provinces Border: 12", CFG.FONT_REGULAR_SMALL);
        this.statTextPercW = Renderer.getTextWidth("1000%", CFG.FONT_REGULAR_SMALL);
        this.nanoW = Renderer.getTextWidth("X16 666 667", CFG.FONT_REGULAR_SMALL);
    }

    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean menuIsActive, Status titleStatus) {
        if (GameValues.value.SETTINGS_MENU_DRAW_TIMES) {
            int paddingL = CFG.PADDING * 4;
            int extraY = 0;
            long totalTime = drawProvinces_Time + drawProvincesFBO_Time + drawArmies_Time + drawProvincesBorder_Time + drawProvincesNames_Time + drawCivsNames_Time + drawCities_Time + drawMoveUnits_Time + drawClouds_Time + drawShips_Time + drawShips2_Time + drawMapBorder_Time;
            Renderer.drawBoxCorner(oSB, this.getPosX() + this.getWidth() + paddingL - CFG.PADDING * 2 + iTranslateX, this.getPosY() + extraY - CFG.PADDING * 2 + iTranslateY, CFG.PADDING * 4 + this.statTextW + this.statTextPercW + CFG.PADDING * 3 + this.nanoW, (CFG.TEXT_HEIGHT_SMALL + CFG.PADDING) * 16 + CFG.PADDING * 2);
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, FontFix.langGet("RTD_A0","Draw"), this.getPosX() + this.getWidth() + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, FontFix.langGet("RTD_A1","Perc"), this.getPosX() + this.getWidth() + this.statTextW + CFG.PADDING * 2 + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, FontFix.langGet("RTD_A2","Nanotime"), this.getPosX() + this.getWidth() + this.statTextW + this.statTextPercW + CFG.PADDING * 3 + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            extraY += CFG.TEXT_HEIGHT_SMALL + CFG.PADDING;
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, FontFix.langGet("RTD_B1","Update Provinces "), this.getPosX() + this.getWidth() + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, "" + CFG.getPrecision2((float)provinceInView_Time / (float)totalTime * 100.0F, 1) + "%", this.getPosX() + this.getWidth() + this.statTextW + CFG.PADDING * 2 + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, CFG.getNumberWithSpaces("" + provinceInView_Time), this.getPosX() + this.getWidth() + this.statTextW + this.statTextPercW + CFG.PADDING * 3 + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            extraY += CFG.TEXT_HEIGHT_SMALL + CFG.PADDING;
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, FontFix.langGet("RTD_B2","Draw Provinces "), this.getPosX() + this.getWidth() + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, "" + CFG.getPrecision2((float)drawProvinces_Time / (float)totalTime * 100.0F, 1) + "%", this.getPosX() + this.getWidth() + this.statTextW + CFG.PADDING * 2 + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, CFG.getNumberWithSpaces("" + drawProvinces_Time), this.getPosX() + this.getWidth() + this.statTextW + this.statTextPercW + CFG.PADDING * 3 + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            extraY += CFG.TEXT_HEIGHT_SMALL + CFG.PADDING;
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, FontFix.langGet("RTD_B3","Provinces FBO "), this.getPosX() + this.getWidth() + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, "" + CFG.getPrecision2((float)drawProvincesFBO_Time / (float)totalTime * 100.0F, 1) + "%", this.getPosX() + this.getWidth() + this.statTextW + CFG.PADDING * 2 + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, CFG.getNumberWithSpaces("" + drawProvincesFBO_Time), this.getPosX() + this.getWidth() + this.statTextW + this.statTextPercW + CFG.PADDING * 3 + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            extraY += CFG.TEXT_HEIGHT_SMALL + CFG.PADDING;
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, FontFix.langGet("RTD_B4","Provinces Border "), this.getPosX() + this.getWidth() + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, "" + CFG.getPrecision2((float)drawProvincesBorder_Time / (float)totalTime * 100.0F, 1) + "%", this.getPosX() + this.getWidth() + this.statTextW + CFG.PADDING * 2 + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, CFG.getNumberWithSpaces("" + drawProvincesBorder_Time), this.getPosX() + this.getWidth() + this.statTextW + this.statTextPercW + CFG.PADDING * 3 + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            extraY += CFG.TEXT_HEIGHT_SMALL + CFG.PADDING;
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, FontFix.langGet("RTD_B5","Provinces Names "), this.getPosX() + this.getWidth() + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, "" + CFG.getPrecision2((float)drawProvincesNames_Time / (float)totalTime * 100.0F, 1) + "%", this.getPosX() + this.getWidth() + this.statTextW + CFG.PADDING * 2 + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, CFG.getNumberWithSpaces("" + drawProvincesNames_Time), this.getPosX() + this.getWidth() + this.statTextW + this.statTextPercW + CFG.PADDING * 3 + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            extraY += CFG.TEXT_HEIGHT_SMALL + CFG.PADDING;
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, FontFix.langGet("RTD_B6","Civs Names "), this.getPosX() + this.getWidth() + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, "" + CFG.getPrecision2((float)drawCivsNames_Time / (float)totalTime * 100.0F, 1) + "%", this.getPosX() + this.getWidth() + this.statTextW + CFG.PADDING * 2 + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, CFG.getNumberWithSpaces("" + drawCivsNames_Time), this.getPosX() + this.getWidth() + this.statTextW + this.statTextPercW + CFG.PADDING * 3 + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            extraY += CFG.TEXT_HEIGHT_SMALL + CFG.PADDING;
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, FontFix.langGet("RTD_B7","Cities & Flags "), this.getPosX() + this.getWidth() + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, "" + CFG.getPrecision2((float)drawCities_Time / (float)totalTime * 100.0F, 1) + "%", this.getPosX() + this.getWidth() + this.statTextW + CFG.PADDING * 2 + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, CFG.getNumberWithSpaces("" + drawCities_Time), this.getPosX() + this.getWidth() + this.statTextW + this.statTextPercW + CFG.PADDING * 3 + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            extraY += CFG.TEXT_HEIGHT_SMALL + CFG.PADDING;
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, FontFix.langGet("RTD_B8","Clouds "), this.getPosX() + this.getWidth() + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, "" + CFG.getPrecision2((float)drawClouds_Time / (float)totalTime * 100.0F, 1) + "%", this.getPosX() + this.getWidth() + this.statTextW + CFG.PADDING * 2 + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, CFG.getNumberWithSpaces("" + drawClouds_Time), this.getPosX() + this.getWidth() + this.statTextW + this.statTextPercW + CFG.PADDING * 3 + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            extraY += CFG.TEXT_HEIGHT_SMALL + CFG.PADDING;
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, FontFix.langGet("RTD_B9","Ships "), this.getPosX() + this.getWidth() + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, "" + CFG.getPrecision2((float)drawShips_Time / (float)totalTime * 100.0F, 1) + "%", this.getPosX() + this.getWidth() + this.statTextW + CFG.PADDING * 2 + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, CFG.getNumberWithSpaces("" + drawShips_Time), this.getPosX() + this.getWidth() + this.statTextW + this.statTextPercW + CFG.PADDING * 3 + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            extraY += CFG.TEXT_HEIGHT_SMALL + CFG.PADDING;
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, FontFix.langGet("RTD_B10","Ships 2 "), this.getPosX() + this.getWidth() + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, "" + CFG.getPrecision2((float)drawShips2_Time / (float)totalTime * 100.0F, 1) + "%", this.getPosX() + this.getWidth() + this.statTextW + CFG.PADDING * 2 + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, CFG.getNumberWithSpaces("" + drawShips2_Time), this.getPosX() + this.getWidth() + this.statTextW + this.statTextPercW + CFG.PADDING * 3 + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            extraY += CFG.TEXT_HEIGHT_SMALL + CFG.PADDING;
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, FontFix.langGet("RTD_B11","Armies "), this.getPosX() + this.getWidth() + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, "" + CFG.getPrecision2((float)drawArmies_Time / (float)totalTime * 100.0F, 1) + "%", this.getPosX() + this.getWidth() + this.statTextW + CFG.PADDING * 2 + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, CFG.getNumberWithSpaces("" + drawArmies_Time), this.getPosX() + this.getWidth() + this.statTextW + this.statTextPercW + CFG.PADDING * 3 + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            extraY += CFG.TEXT_HEIGHT_SMALL + CFG.PADDING;
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, FontFix.langGet("RTD_B12","Move Units "), this.getPosX() + this.getWidth() + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, "" + CFG.getPrecision2((float)drawMoveUnits_Time / (float)totalTime * 100.0F, 1) + "%", this.getPosX() + this.getWidth() + this.statTextW + CFG.PADDING * 2 + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, CFG.getNumberWithSpaces("" + drawMoveUnits_Time), this.getPosX() + this.getWidth() + this.statTextW + this.statTextPercW + CFG.PADDING * 3 + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            extraY += CFG.TEXT_HEIGHT_SMALL + CFG.PADDING;
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, FontFix.langGet("RTD_B13","Map Border "), this.getPosX() + this.getWidth() + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, "" + CFG.getPrecision2((float)drawMapBorder_Time / (float)totalTime * 100.0F, 1) + "%", this.getPosX() + this.getWidth() + this.statTextW + CFG.PADDING * 2 + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, CFG.getNumberWithSpaces("" + drawMapBorder_Time), this.getPosX() + this.getWidth() + this.statTextW + this.statTextPercW + CFG.PADDING * 3 + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            extraY += CFG.TEXT_HEIGHT_SMALL + CFG.PADDING;
            extraY += CFG.TEXT_HEIGHT_SMALL + CFG.PADDING;
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, "1 FPS", this.getPosX() + this.getWidth() + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, "", this.getPosX() + this.getWidth() + this.statTextW + CFG.PADDING * 2 + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
            Renderer.drawText(oSB, CFG.FONT_REGULAR_SMALL, "16 666 667", this.getPosX() + this.getWidth() + this.statTextW + this.statTextPercW + CFG.PADDING * 3 + paddingL + iTranslateX, this.getPosY() + extraY + iTranslateY, Colors.HOVER_LEFT);
        }

        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop600, Images.insideBot600);
        ImageManager.getImage(Images.rulerOver).draw2(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.rulerOver).getWidth() / 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.rulerOver).getHeight()));
        oSB.setColor(Color.WHITE);
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }

    public void updateLanguage() {
        super.updateLanguage();
    }

    static {
        goBackToMenu = View.MAINMENU;
        provinceInView_Time = 0L;
        drawProvinces_Time = 0L;
        drawProvincesFBO_Time = 0L;
        drawArmies_Time = 0L;
        drawProvincesBorder_Time = 0L;
        drawProvincesNames_Time = 0L;
        drawCivsNames_Time = 0L;
        drawCities_Time = 0L;
        drawMoveUnits_Time = 0L;
        drawClouds_Time = 0L;
        drawShips_Time = 0L;
        drawShips2_Time = 0L;
        drawMapBorder_Time = 0L;
        updateTimes = true;
        lastUpdateTime = 0L;
    }
}
