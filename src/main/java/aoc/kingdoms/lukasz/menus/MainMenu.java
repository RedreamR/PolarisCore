package aoc.kingdoms.lukasz.menus;

import aoc.kingdoms.lukasz.jakowski.*;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.jakowski.SaveLoad.LoadSavedGameManager;
import aoc.kingdoms.lukasz.jakowski.SaveLoad.SaveGameManager;
import aoc.kingdoms.lukasz.jakowski.Steam.SteamManager;
import aoc.kingdoms.lukasz.map.diplomacy.DiplomacyManager;
import aoc.kingdoms.lukasz.map.province.ProvinceBorderManager;
import aoc.kingdoms.lukasz.map.province.ProvinceDraw;
import aoc.kingdoms.lukasz.menu.ClickAnimation;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.menu.Menu;
import aoc.kingdoms.lukasz.menu.MenuManager;
import aoc.kingdoms.lukasz.menu.View;
import aoc.kingdoms.lukasz.menu.menuTitle.MenuTitle;
import aoc.kingdoms.lukasz.menu_element.MenuElement;
import aoc.kingdoms.lukasz.menu_element.Status;
import aoc.kingdoms.lukasz.menu_element.button.ButtonGame2;
import aoc.kingdoms.lukasz.menu_element.button.ButtonGame2Sparks_Hovered;
import aoc.kingdoms.lukasz.menu_element.button.ButtonGame2_IMG;
import aoc.kingdoms.lukasz.menu_element.button.ButtonMainTitle;
import aoc.kingdoms.lukasz.menu_element.button.Button_LoadGame_MainMenu;
import aoc.kingdoms.lukasz.menu_element.button.Button_MainMenuIcon;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_Hover;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Button_TextBonus;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Line;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG_Center;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text_Desc;
import aoc.kingdoms.lukasz.menu_element.textStatic.Text_Static;
import aoc.kingdoms.lukasz.menus.Dialog.DialogType;
import aoc.kingdoms.lukasz.menus.Settings.Settings_Menu;
import aoc.kingdoms.lukasz.textures.Image;
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.textures.Images;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
 
import team.rainfall.fontFix.Config;
import team.rainfall.fontFix.FontFix;
import team.rainfall.fontFix.Sternstunden;
import team.rainfall.fontFix.config.LinkConfig;
import team.rainfall.fontFix.utils.AnimationUtil;
import team.rainfall.fontFix.utils.IconParser;

import java.lang.reflect.InvocationTargetException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class MainMenu extends Menu {
    private long lastClick = 0;
    private short clickCount = 0;
    public static Color sparksColors = new Color(1.0F, 1.0F, 1.0F, 0.25F);
    private int iXPos = 0;
    private int iYPos = 0;
    private int iWidth = 480;
    private int iHeight = 480;
    private int lastXPos = 0;//上次增加的iXPos
    private final ArrayList<Integer> buttonElementIDs = new ArrayList<>();
    private final ArrayList<Integer> linkElementIDs = new ArrayList<>();
    public static boolean canContinue = false;
    public static Image flag = null;
    public static long animTime = 1000;
    public static long startTime = 0;
    public static SaveGameManager.SaveDetails savedGame = null;
    public static String savedGameKey = null;
    public static float bgAlpha = 0.0F;
    public static long bgTIME;
    public static long bgTIME_CHANGE;

    public MainMenu() {
        List<MenuElement> menuElements = new ArrayList();
        int paddingTopBot = CFG.PADDING * 2 + CFG.PADDING / 2;
        int paddingLeft = Images.boxTitleBORDERWIDTH + CFG.PADDING * 2 + CFG.PADDING / 2;
        this.iWidth = (int) Math.max((float) CFG.LEFT_MENU_WIDTH, (float) Math.min(this.iWidth, CFG.GAME_WIDTH / 4) * CFG.GUI_SCALE);
        int width2 = 0;
        switch (Config.getConfig().MainMenu_Alignment) {
            case "center":
                width2 = this.iWidth / 2;
                break;
            case "right":
                width2 = -this.iWidth / 2;
                break;
        }
        this.iXPos = (int) ((CFG.GAME_WIDTH * Config.getConfig().MainMenu_PanelX - width2) / CFG.GUI_SCALE);
        this.iHeight = paddingTopBot * 2 + paddingTopBot / 2 + (CFG.BUTTON_HEIGHT + CFG.PADDING * 2) * 6;
        this.iYPos = (int) (0.5F * (float) (CFG.GAME_HEIGHT - this.iHeight - ImageManager.getImage(Images.mainTitle).getHeight()));
        if (this.iXPos + this.iWidth > CFG.GAME_WIDTH) {
            this.iXPos = CFG.PADDING * 2;
        }

        Renderer.glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_REGULAR_SMALL), GameValues.text.VERSION);
        Game.versionWidth = (int) Renderer.glyphLayout.width;
        menuElements.add(new ButtonMainTitle("", 0, -1, this.iXPos, this.iYPos, this.iWidth, true) {
            public void actionElement() {
                MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + MainMenu.this.getMenuPosX(), this.getPosY() + this.getHeight() / 2 + MainMenu.this.getMenuPosY(), this.getWidth(), this.getHeight()) {
                    public Color getColor() {
                        return DiplomacyManager.COLOR_WAR;
                    }
                });
            }

            public void actionElementPPM() {
                MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + MainMenu.this.getMenuPosX(), this.getPosY() + this.getHeight() / 2 + MainMenu.this.getMenuPosY(), this.getWidth(), this.getHeight()) {
                    public Color getColor() {
                        return DiplomacyManager.COLOR_ALLIANCE;
                    }
                });
            }

            public void buildElementHover() {
                this.menuElementHover = MainMenu.getHoverAbout();
            }
        });
        buttonElementIDs.add(0);
        int buttonY = this.iYPos + ImageManager.getImage(Images.mainTitle).getHeight() + paddingTopBot;

        try {
            if (canContinue) {
                menuElements.add(new Button_LoadGame_MainMenu(Game.lang.get("Continue") + ": " + Game.getCiv(Game.player.iCivID).getCivName(), Game_Calendar.currentDay + " " + Game_Calendar.getMonthName(Game_Calendar.currentMonth) + " " + Game.gameAges.getYear(Game_Calendar.currentYear), this.iXPos + paddingLeft, buttonY, this.iWidth - paddingLeft * 2) {
                    public void actionElement() {
                        Game.menuManager.setViewIDWithoutAnimation(View.IN_GAME);
                        Game.menuManager.setOrderOfMenu_InGame();
                        if (Game.mapModes.iActiveMapModeID != Game.mapModes.MODE_DEFAULT) {
                            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                        }

                        ProvinceDraw.updateDrawExtraDetails();
                    }

                    public void buildElementHover() {
                        List<MenuElement_HoverElement> nElements = new ArrayList();
                        List<MenuElement_HoverElement_Type> nData = new ArrayList();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Continue"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.getCiv(Game.player.iCivID).getCivName(), "", Images.council, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Line());
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        nData.add(new MenuElement_HoverElement_Type_Text(this.sText2, CFG.FONT_REGULAR_SMALL, Colors.HOVER_RIGHT));
                        nData.add(new MenuElement_HoverElement_Type_Image(Images.time, CFG.PADDING, 0));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements);
                    }

                    protected void drawImage(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                        Exception ex;
                        try {
                            if (Game.getCiv(Game.player.iCivID).getFlag() != null) {
                                oSB.setShader(Renderer.shaderAlpha);

                                try {
                                    Game.getCiv(Game.player.iCivID).getFlag().getTexture().bind(1);
                                    Gdx.gl.glActiveTexture(33984);
                                    ImageManager.getImage(Images.flagMaskDefault).draw(oSB, this.getPosX() + getPaddingIMG() + iTranslateX + (ImageManager.getImage(Images.flagOverDefault).getWidth() - ImageManager.getImage(Images.flagMaskDefault).getWidth()) / 2, this.getPosY() + getPaddingIMG() + iTranslateY + (ImageManager.getImage(Images.flagOverDefault).getHeight() - ImageManager.getImage(Images.flagMaskDefault).getHeight()) / 2, ImageManager.getImage(Images.flagMaskDefault).getWidth(), ImageManager.getImage(Images.flagMaskDefault).getHeight());
                                } catch (Exception var6) {
                                    ex = var6;
                                    CFG.exceptionStack(ex);
                                }

                                oSB.flush();
                                oSB.setShader(Renderer.shaderDefault);
                                ImageManager.getImage(Images.flagOverDefault).draw(oSB, this.getPosX() + getPaddingIMG() + iTranslateX, this.getPosY() + getPaddingIMG() + iTranslateY);
                            }
                        } catch (Exception var7) {
                            ex = var7;
                            CFG.exceptionStack(ex);
                        }

                    }
                });
                buttonElementIDs.add(menuElements.size() - 1);
                buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2;
            } else {
                FileHandle file;
                if (FileManager.IS_MAC) {
                    file = Gdx.files.external("saves/" + Game.map.getFile_ActiveMap_Path() + "AoH.txt");
                } else if (CFG.readLocalFiles()) {
                    file = Gdx.files.local("saves/" + Game.map.getFile_ActiveMap_Path() + "AoH.txt");
                } else {
                    file = Gdx.files.internal("saves/" + Game.map.getFile_ActiveMap_Path() + "AoH.txt");
                }

                if (file.exists()) {
                    String[] tempTags = file.readString().split(";");
                    List<SaveGameManager.SaveDetails> tempSaveDetails = new ArrayList();
                    List<String> tempSaveKey = new ArrayList();

                    int bestID;
                    for (bestID = 0; bestID < tempTags.length; ++bestID) {
                        SaveGameManager.SaveDetails readSD = LoadSavedGameManager.loadSave_Details(tempTags[bestID]);
                        if (readSD != null) {
                            tempSaveDetails.add(readSD);
                            tempSaveKey.add(tempTags[bestID]);
                        }
                    }

                    bestID = 0;

                    for (int i = tempSaveDetails.size() - 1; i > 0; --i) {
                        if (tempSaveDetails.get(i).time > tempSaveDetails.get(bestID).time) {
                            bestID = i;
                        }
                    }

                    savedGame = tempSaveDetails.get(bestID);
                    savedGameKey = tempSaveKey.get(bestID);
                    loadFlag(savedGame.sCivTag);
                    menuElements.add(new Button_LoadGame_MainMenu(Game.lang.get("Continue") + ": " + Game.lang.getCiv(savedGame.sCivTag), savedGame.iDay + " " + Game_Calendar.getMonthName(savedGame.iMonth) + " " + Game.gameAges.getYear(savedGame.iYear), this.iXPos + paddingLeft, buttonY, this.iWidth - paddingLeft * 2) {
                        public void actionElement() {
                            LoadSavedGameManager.key = MainMenu.savedGameKey;
                            Game.menuManager.setViewIDWithoutAnimation(View.LOAD_SAVED_GAME);
                        }
                    });
                    buttonElementIDs.add(menuElements.size() - 1);
                    buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2;
                } else {
                    menuElements.add(new ButtonGame2(Game.lang.get("Continue"), 1, -1, this.iXPos + paddingLeft, buttonY, this.iWidth - paddingLeft * 2, false) {
                    });
                    buttonElementIDs.add(menuElements.size() - 1);
                    buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2;
                }
            }
        } catch (Exception var11) {
            Exception ex = var11;
            CFG.exceptionStack(ex);
        }

        menuElements.add(new ButtonGame2Sparks_Hovered(Game.lang.get("NewGame"), 1, -1, this.iXPos + paddingLeft, buttonY, this.iWidth - paddingLeft * 2, true) {
            public void actionElement() {
                Game.menuManager.setViewID(View.SCENARIOS);
                Game.menuManager.setOrderOfMenu_Scenarios();
            }
        });
        buttonElementIDs.add(menuElements.size() - 1);
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2;
        int statsW = CFG.BUTTON_WIDTH;
        menuElements.add(new ButtonGame2Sparks_Hovered(Game.lang.get("Campaign"), 1, -1, this.iXPos + paddingLeft, buttonY, (this.iWidth - paddingLeft * 2 - CFG.PADDING) / 2, true) {
            public void actionElement() {
                Game.menuManager.setViewID(View.SCENARIOS_CAMPAIGN);
                Game.menuManager.setOrderOfMenu_Scenarios();
            }
        });
        buttonElementIDs.add(menuElements.size() - 1);

        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2;
        menuElements.add(new ButtonGame2(Game.lang.get("LoadGame"), 1, -1, this.iXPos + paddingLeft, buttonY, this.iWidth - paddingLeft * 2, true) {
            public void actionElement() {
                Game.menuManager.setViewID(View.LOAD_GAMES_LIST);
            }
        });
        buttonElementIDs.add(menuElements.size() - 1);
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2 + paddingTopBot / 2;
        menuElements.add(new ButtonGame2(Game.lang.get("Editor"), 1, -1, this.iXPos + paddingLeft, buttonY, (this.iWidth - paddingLeft * 2 - CFG.PADDING) / 2, true) {
            public void actionElement() {
                ProvinceBorderManager.clearProvinceBorder();
                Game.menuManager.setViewID(View.EDITOR);
            }

            public void buildElementHover() {
                if (SteamManager.modsFolders.size() <= 0 && SteamManager.itemsInstalledSize <= 0) {
                    this.menuElementHover = null;
                } else {
                    List<MenuElement_HoverElement> nElements = new ArrayList();
                    List<MenuElement_HoverElement_Type> nData = new ArrayList();
                    if (SteamManager.modsFolders.size() > 0) {
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("InstalledMods") + ": ", "" + SteamManager.modsFolders.size(), Images.technology2, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }

                    if (SteamManager.itemsInstalledSize > 0) {
                        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus("Steam, " + Game.lang.get("InstalledMods") + ": ", "" + SteamManager.itemsInstalledSize, Images.technology2, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                    }

                    this.menuElementHover = new MenuElement_Hover(nElements);
                }

            }
        });
        buttonElementIDs.add(menuElements.size() - 1);
        menuElements.add(new ButtonGame2(Game.lang.get("Settings"), 1, -1, this.iXPos + paddingLeft + (this.iWidth - paddingLeft * 2 - CFG.PADDING) / 2 + CFG.PADDING, buttonY, (this.iWidth - paddingLeft * 2 - CFG.PADDING) / 2, true) {
            public void actionElement() {
                Settings_Menu.goBackToMenu = View.MAINMENU;
                Game.menuManager.setViewID(View.SETTINGS);
            }
        });
        buttonElementIDs.add(menuElements.size() - 1);
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2;
        menuElements.add(new ButtonGame2(Game.lang.get("ExitGame"), 1, -1, this.iXPos + paddingLeft, buttonY, this.iWidth - paddingLeft * 2 - CFG.PADDING - statsW, true) {
            public void actionElement() {
                Dialog.setDialogType(DialogType.EXIT_GAME);
            }
        });
        buttonElementIDs.add(menuElements.size() - 1);
        menuElements.add(new ButtonGame2_IMG(null, 1, -1, this.iXPos + paddingLeft + this.iWidth - paddingLeft * 2 - statsW, buttonY, statsW, true, Images.development) {
            public void actionElement() {
                Game.menuManager.setViewIDWithoutAnimation(View.MAINMENU_STATS);
            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(Game.lang.get("Civilizations") + ": ", Game.lang.get("HallofFame"), Images.development, CFG.FONT_REGULAR_SMALL, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonElementIDs.add(menuElements.size() - 1);
        int var10000 = buttonY + menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING * 2;
        Renderer.glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_REGULAR_SMALL), GameValues.text.VERSION);
        float width1 = Renderer.glyphLayout.width;
        menuElements.add(new Text_Static(GameValues.text.VERSION, CFG.FONT_REGULAR_SMALL, -1, (int) (CFG.GAME_WIDTH - CFG.PADDING * 3 - width1), 0, (int) (CFG.PADDING * 3 + width1), CFG.BUTTON_HEIGHT3) {
            public void actionElement() {
                MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + MainMenu.this.getMenuPosX(), this.getPosY() + this.getHeight() / 2 + MainMenu.this.getMenuPosY(), this.getWidth(), this.getHeight()) {
                    public Color getColor() {
                        return DiplomacyManager.COLOR_WAR;
                    }
                });
                Game.addSimpleTask(new Game.SimpleTask("loadBackground") {
                    public void update() {
                        if (Config.getConfig().uniqueBGforMainMenu) {
                            InitGame.loadBackground2();
                        } else {
                            InitGame.loadBackground();
                        }
                        MainMenu.bgTIME = System.currentTimeMillis();
                        MainMenu.bgTIME_CHANGE = System.currentTimeMillis();
                        MainMenu.bgAlpha = 0.0F;
                    }
                });
            }
        });
        int buttonsY = CFG.GAME_HEIGHT - CFG.BUTTON_HEIGHT * Config.getConfig().links.size;
        for (LinkConfig linkConfig : Config.getConfig().links) {
            menuElements.add(new Button_MainMenuIcon(IconParser.parse(linkConfig.icon), CFG.GAME_WIDTH - CFG.BUTTON_WIDTH, buttonsY, CFG.BUTTON_WIDTH, CFG.BUTTON_HEIGHT) {
                public void actionElement() {
                    Dialog.GO_TO_LINK = linkConfig.link;
                    Dialog.setDialogType(DialogType.GO_TO_LINK);
                }

                public void buildElementHover() {
                    List<MenuElement_HoverElement> nElements = new ArrayList();
                    List<MenuElement_HoverElement_Type> nData = new ArrayList();
                    nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(linkConfig.sText, linkConfig.sText2, IconParser.parse(linkConfig.icon), CFG.FONT_BOLD, CFG.FONT_REGULAR, Colors.HOVER_LEFT, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements);
                }
            });
            linkElementIDs.add(menuElements.size() - 1);
            buttonsY += menuElements.get(menuElements.size() - 1).getHeight();
        }

        menuElements.add(new Text_Static("Lukasz Jakowski", CFG.PADDING * 3, CFG.GAME_HEIGHT - CFG.TEXT_HEIGHT - CFG.PADDING * 3, CFG.FONT_REGULAR_SMALL) {
            public void actionElement() {
                MenuManager var10000 = Game.menuManager;
                MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + MainMenu.this.getMenuPosX(), this.getPosY() + this.getHeight() / 2 + MainMenu.this.getMenuPosY(), this.getWidth(), this.getHeight()) {
                    public Color getColor() {
                        return DiplomacyManager.COLOR_WAR;
                    }
                });
            }

            public void buildElementHover() {
                this.menuElementHover = MainMenu.getHoverAbout();
            }

            protected Color getColor(boolean isActive) {
                return !this.getIsHovered() && !isActive ? Colors.HOVER_RIGHT3 : Colors.HOVER_LEFT;
            }
        });

        String text1 = (CFG.isDesktop() && !FontFix.fakeAndroid)? "Polaris Core by Team Rainfall" : Sternstunden.getCopyrightString();
        menuElements.add(new Text_Static(text1, CFG.PADDING * 3, CFG.GAME_HEIGHT - CFG.TEXT_HEIGHT * 3 - 1 - CFG.PADDING * 3, CFG.FONT_REGULAR_SMALL) {
            public void actionElement() {
                if(System.currentTimeMillis() - lastClick > 700L){
                    clickCount = 0;
                }
                clickCount++;
                lastClick = System.currentTimeMillis();
                MenuManager var10000 = Game.menuManager;
                MenuManager.addClickAnimation(new ClickAnimation(this.getPosX() + this.getWidth() / 2 + MainMenu.this.getMenuPosX(), this.getPosY() + this.getHeight() / 2 + MainMenu.this.getMenuPosY(), this.getWidth(), this.getHeight()) {
                    public Color getColor() {
                        return DiplomacyManager.COLOR_WAR;
                    }
                });
                if(clickCount >= 5){
                    clickCount = 0;
                    Gdx.net.openURI("https://www.bilibili.com/list/215276?oid=1052051297&bvid=BV1eH4y1p7H9");
                }
            }

            public void buildElementHover() {
                this.menuElementHover = MainMenu.getHover_FontFix();
            }

            protected Color getColor(boolean isActive) {
                return !this.getIsHovered() && !isActive ? Colors.HOVER_RIGHT3 : Colors.HOVER_LEFT;
            }
        });
        this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements, true);
        if (!menuElements.get(menuElements.size() - 1).getText().contains("Polaris AoH3") && !menuElements.get(menuElements.size() - 1).getText().contains("Polaris Core")) {
            Gdx.app.exit();
        }
        bgTIME = System.currentTimeMillis();
        bgTIME_CHANGE = System.currentTimeMillis();

    }

    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean menuIsActive, Status titleStatus) {
        if(!Config.getConfig().noMainMenuAnimation && animTime < 100) {
            lastXPos = 0;
            for (Integer buttonElementID : buttonElementIDs) {
                MenuElement element = getMenuElement(buttonElementID);
                element.setPosX(element.getPosX() - iXPos * 2);
            }
            startTime = System.currentTimeMillis();
            animTime = startTime + Config.getAnimationConfig().MainMenu;
        }
        if (bgAlpha < 1.0F) {
            oSB.setColor(0.0F, 0.0F, 0.0F, 1.0F);
            Images.pix.draw(oSB, iTranslateX, iTranslateY, CFG.GAME_WIDTH, CFG.GAME_HEIGHT);
            bgAlpha = Math.min(1.0F, (float) (CFG.currentTimeMillis - bgTIME) / (float) GameValues.text.MAIN_MENU_BG_ANIMATION_TIME);
        }

        int XPos2 = (int) (iXPos * 2 * AnimationUtil.easeOut(System.currentTimeMillis(),animTime,startTime));
        if(Config.getGradientConfig().mainMenu < 2) {
            oSB.setColor(new Color(0.050980393F, 0.08627451F, 0.13333334F, bgAlpha));
        }else {
            //确保下方的gradient被禁用时显示仍然正常
            oSB.setColor(new Color(1, 1, 1, bgAlpha));
        }
        InitGame.background.draw(oSB, iTranslateX + (CFG.GAME_WIDTH - InitGame.backgroundWidth) / 2, iTranslateY + (CFG.GAME_HEIGHT - InitGame.backgroundHeight) / 2, InitGame.backgroundWidth, InitGame.backgroundHeight);
        if(Config.getGradientConfig().mainMenu < 2) {
            oSB.setColor(new Color(1.0F, 1.0F, 1.0F, bgAlpha));
            oSB.setShader(Renderer.shaderAlpha);
            InitGame.background.getTexture().bind(1);
            Gdx.gl.glActiveTexture(33984);
            ImageManager.getImage(Images.gradientHorizontal2).draw(oSB, this.getPosX() + (CFG.GAME_WIDTH - InitGame.backgroundWidth) / 2 + iTranslateX, this.getPosY() + (CFG.GAME_HEIGHT - InitGame.backgroundHeight) / 2 + iTranslateY, InitGame.backgroundWidth, InitGame.backgroundHeight);
            oSB.flush();
        }
        oSB.setShader(Renderer.shaderDefault);
        oSB.setColor(sparksColors);
        MenuManager.sparksAnimation.draw2(oSB, iTranslateX, CFG.GAME_HEIGHT - Images.sparkHeight + iTranslateY, CFG.GAME_WIDTH, Images.sparkHeight);
        oSB.setColor(Color.WHITE);
        Renderer.drawBoxCorner(oSB, iTranslateX + XPos2 - iXPos, iTranslateY + this.iYPos, this.iWidth, this.iHeight + ImageManager.getImage(Images.mainTitle).getHeight());
        Renderer.drawBox_EDGE_TOP_LR(oSB, Images.mainBox, XPos2 + iTranslateX - iXPos, this.iYPos + ImageManager.getImage(Images.mainTitle).getHeight() + iTranslateY, this.iWidth, this.iHeight, true);
        if(Config.getGradientConfig().mainMenu < 4) {
            oSB.setColor(new Color(Colors.COLOR_GRADIENT.r, Colors.COLOR_GRADIENT.g, Colors.COLOR_GRADIENT.b, 0.3F));
            Images.gradientXY.draw(oSB, XPos2 + iTranslateX - iXPos, this.iYPos + ImageManager.getImage(Images.mainTitle).getHeight() + iTranslateY, this.iWidth, this.iHeight, false, true);
        }
        oSB.setColor(Color.WHITE);
        if ((CFG.isDesktop() && GameValues.text.MAIN_MENU_BG_ENABLE_AUTO_BG_CHANGE || !CFG.isDesktop() && GameValues.text.MAIN_MENU_BG_ENABLE_AUTO_BG_CHANGE_MOBILE) && CFG.currentTimeMillis > bgTIME_CHANGE + (long) GameValues.text.MAIN_MENU_BG_CHANGE_BG_EVERY_X_MS) {
            bgTIME_CHANGE = CFG.currentTimeMillis;
            Game.addSimpleTask(new Game.SimpleTask("loadBackground") {
                public void update() {
                    if(Config.getConfig().uniqueBGforMainMenu){
                        InitGame.loadBackground2();
                    }else {
                        InitGame.loadBackground();
                    }
                    MainMenu.bgTIME = System.currentTimeMillis();
                    MainMenu.bgTIME_CHANGE = System.currentTimeMillis();
                    MainMenu.bgAlpha = 0.0F;
                }
            });
        }

        for (Integer buttonElementID : buttonElementIDs) {
            MenuElement element = getMenuElement(buttonElementID);
            element.setPosX(element.getPosX() - lastXPos + XPos2);
        }
        lastXPos = XPos2;
        for (int item : linkElementIDs) {
            if(item < 10) continue;
            getMenuElement(item).setPosX((int) (CFG.GAME_WIDTH - (CFG.BUTTON_WIDTH * AnimationUtil.easeOut(System.currentTimeMillis(), animTime,startTime))));
        }
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }

    public static void loadFlag(String sCivTag) {
        disposeData();
        if (FileManager.loadFile("gfx/flagsXH/" + sCivTag + ".png").exists()) {
            flag = new Image(new Texture(FileManager.loadFile("gfx/flagsXH/" + sCivTag + ".png")), TextureFilter.Linear);
        } else if (FileManager.loadFile("gfx/flagsXH/" + Game.ideologiesManager.getRealTag(sCivTag) + ".png").exists()) {
            flag = new Image(new Texture(FileManager.loadFile("gfx/flagsXH/" + Game.ideologiesManager.getRealTag(sCivTag) + ".png")), TextureFilter.Linear);
        } else if (FileManager.loadFile("gfx/flagsH/" + sCivTag + ".png").exists()) {
            flag = new Image(new Texture(FileManager.loadFile("gfx/flagsH/" + sCivTag + ".png")), TextureFilter.Linear);
        } else if (FileManager.loadFile("gfx/flagsH/" + Game.ideologiesManager.getRealTag(sCivTag) + ".png").exists()) {
            flag = new Image(new Texture(FileManager.loadFile("gfx/flagsH/" + Game.ideologiesManager.getRealTag(sCivTag) + ".png")), TextureFilter.Linear);
        } else if (FileManager.loadFile("gfx/flags/" + sCivTag + ".png").exists()) {
            flag = new Image(new Texture(FileManager.loadFile("gfx/flags/" + sCivTag + ".png")), TextureFilter.Nearest);
        } else if (FileManager.loadFile("gfx/flags/" + Game.ideologiesManager.getRealTag(sCivTag) + ".png").exists()) {
            flag = new Image(new Texture(FileManager.loadFile("gfx/flags/" + Game.ideologiesManager.getRealTag(sCivTag) + ".png")), TextureFilter.Nearest);
        } else {
            flag = new Image(new Texture(FileManager.loadFile("gfx/flagsXH/ran.png")), TextureFilter.Nearest);
        }

    }

    public void dispose() {
        disposeData();
    }

    public static void disposeData() {
        if (flag != null) {
            flag.getTexture().dispose();
            flag = null;
        }

    }

    public static MenuElement_Hover getHoverAbout_Short() {
        List<MenuElement_HoverElement> nElements = new ArrayList();
        List<MenuElement_HoverElement_Type> nData = new ArrayList();
        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Center("Programmer and Designer", CFG.FONT_BOLD, Colors.HOVER_LEFT));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus("Lukasz Jakowski", "", Images.world, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_GOLD, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus("One Man Army!", "", Game_Calendar.IMG_MANPOWER, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_LEFT));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        return new MenuElement_Hover(nElements);
    }

    public static MenuElement_Hover getHoverAbout() {
        List<MenuElement_HoverElement> nElements = new ArrayList();
        List<MenuElement_HoverElement_Type> nData = new ArrayList();
        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Center("Programmer and Designer", CFG.FONT_BOLD, Colors.HOVER_LEFT));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus("Lukasz Jakowski", "", Images.world, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_GOLD, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus("One Man Army!", "", Game_Calendar.IMG_MANPOWER, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_LEFT));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        boolean lineAdded = false;
        if (GameValues.text.MAIN_MENU_LOGO_HOVER_TEXT != null) {
            for (int i = 0; i < GameValues.text.MAIN_MENU_LOGO_HOVER_TEXT.length; ++i) {
                if (GameValues.text.MAIN_MENU_LOGO_HOVER_TEXT[i] != null && !lineAdded) {
                    lineAdded = true;
                    nData.add(new MenuElement_HoverElement_Type_Line());
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                }

                nData.add(new MenuElement_HoverElement_Type_Text_Desc(GameValues.text.MAIN_MENU_LOGO_HOVER_TEXT[i], CFG.FONT_REGULAR_SMALL, Colors.HOVER_LEFT2));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
            }
        }

        return new MenuElement_Hover(nElements);
    }

    public static MenuElement_Hover getHover_FontFix() {
        List<MenuElement_HoverElement> nElements = new ArrayList();
        List<MenuElement_HoverElement_Type> nData = new ArrayList();
        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG_Center(CFG.isDesktop() && !FontFix.fakeAndroid ? "Polaris Core Creator" : "Polaris AoH3 Creator", CFG.FONT_BOLD, Colors.HOVER_LEFT));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus("Team Rainfall", "", Images.world, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_GOLD, Colors.HOVER_GOLD));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(getRandomStr(), "", Game_Calendar.IMG_MANPOWER, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_LEFT));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        nData.add(new MenuElement_HoverElement_Type_Button_TextBonus(getVersion(), "", Images.time, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_LEFT));
        nElements.add(new MenuElement_HoverElement(nData));
        nData.clear();
        if (CFG.isAndroid() || FontFix.fakeAndroid) {
            nData.add(new MenuElement_HoverElement_Type_Button_TextBonus("Sternstunden " + Game.lang.get("Version") + ":" + Sternstunden.getVersion().trim(), "", Images.technology, CFG.FONT_BOLD, CFG.FONT_BOLD, Colors.HOVER_LEFT, Colors.HOVER_LEFT));
            nElements.add(new MenuElement_HoverElement(nData));
            nData.clear();
        }
        return new MenuElement_Hover(nElements);
    }

    public static String getVersion() {
        if (CFG.isDesktop() && !FontFix.fakeAndroid) {
            return "Polaris Core " + Game.lang.get("Version") + ":" + FontFix.CORE_VERSION;
        }
        return "Polaris AoH3 " + Game.lang.get("Version") + ":" + FontFix.POLARIS_VERSION + " " + Sternstunden.versionAppend.trim();
    }

    public static String getRandomStr() {
        String str1 = "What do you want today?";
        if (!Game.lang.get("FontFix_Text1").equals("FontFix_Text1")) {
            str1 = Game.lang.get("FontFix_Text1");
        }
        String str2 = "Light the flame.";
        if (!Game.lang.get("FontFix_Text2").equals("FontFix_Text2")) {
            str2 = Game.lang.get("FontFix_Text2");
        }

        LocalDate today = LocalDate.now(ZoneId.systemDefault());
        DayOfWeek dayOfWeek = today.getDayOfWeek();
        switch (dayOfWeek.getValue()) {
            case 6:
                return str1;
            case 2:
                return str2;
            default:
                return "Rainfall,the storm approaches.";
        }
    }
}
