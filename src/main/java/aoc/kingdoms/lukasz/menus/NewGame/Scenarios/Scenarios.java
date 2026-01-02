//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.menus.NewGame.Scenarios;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.FileManager;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.map.map.MapScale;
import aoc.kingdoms.lukasz.map.map.MapScenarios;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.menu.Menu;
import aoc.kingdoms.lukasz.menu.MenuManager;
import aoc.kingdoms.lukasz.menu.View;
import aoc.kingdoms.lukasz.menu.menuTitle.MenuTitle;
import aoc.kingdoms.lukasz.menu_element.MenuElement;
import aoc.kingdoms.lukasz.menu_element.Status;
import aoc.kingdoms.lukasz.menu_element.button.ButtonFlag2_CivName_Random;
import aoc.kingdoms.lukasz.menu_element.button.ButtonPlay;
import aoc.kingdoms.lukasz.menu_element.button.ButtonStatsRectIMG_Bonuses;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_Hover;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_ImageTitle_BG;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoc.kingdoms.lukasz.menu_element.textStatic.Text_DescScenarios;
import aoc.kingdoms.lukasz.menus.InitGame;
import aoc.kingdoms.lukasz.menus.MainMenu;
import aoc.kingdoms.lukasz.menus.LoadSave.Menu_LoadScenario;
import aoc.kingdoms.lukasz.menus.NewGame.NewGame;
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.textures.Images;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import team.rainfall.fontFix.Config;

import java.util.ArrayList;
import java.util.List;

public class Scenarios extends Menu {
    private int iXPos = 0;
    private int iYPos = 0;
    private int iWidth = 480;
    private int iHeight = 480;
    public static int activeDescID = 0;
    public static int currentActiveDescID = 0;
    public static boolean SCENARIOS_DEFAULT_MODE = true;

    public Scenarios() {
        List<MenuElement> menuElements = new ArrayList();
        int paddingTopBot = CFG.PADDING * 2 + CFG.PADDING / 2;
        this.iXPos = CFG.GAME_WIDTH / 10;
        this.iWidth = Math.max(CFG.LEFT_MENU_WIDTH, Math.min(this.iWidth, CFG.GAME_WIDTH / 4));
        this.iHeight = paddingTopBot * 3 + paddingTopBot / 2 + (CFG.BUTTON_HEIGHT + CFG.PADDING * 2) * 6;
        this.iYPos = (int)(0.5F * (float)(CFG.GAME_HEIGHT - this.iHeight - ImageManager.getImage(Images.mainTitle).getHeight()));
        menuElements.add(new ButtonPlay("", 1, -1, CFG.GAME_WIDTH - ImageManager.getImage(Images.buttonPlay).getWidth(), CFG.GAME_HEIGHT - ImageManager.getImage(Images.buttonPlay).getHeight(), false) {
            public void actionElement() {
                if (!Game.reloadScenario && !MainMenu.canContinue) {
                    Game.mapScale.definedScale = MapScale.defScales.definedScale_Default;
                    Game.mapScale.setCurrentScale(1.0F);
                    NewGame.setRandomCiv();
                    Game.menuManager.setViewIDWithoutAnimation(View.CLOUDS_MENU);
                } else {
                    Game.reloadScenario = false;
                    Menu_LoadScenario.editorMode = false;
                    Menu_LoadScenario.goToMenu = View.CLOUDS_MENU;
                    Game.menuManager.setViewIDWithoutAnimation(View.LOAD_SCENARIO);
                }

            }

            public void updateLanguage() {
                this.setText(Game.lang.get("PLAY"));
            }
        });
        menuElements.add(new ButtonPlay("", 1, -1, 0, CFG.GAME_HEIGHT - ImageManager.getImage(Images.buttonPlay).getHeight(), true) {
            public void actionElement() {
                Game.menuManager.setViewID(View.MAINMENU);
            }

            public void updateLanguage() {
                this.setText(Game.lang.get("Back"));
            }
        });
        menuElements.add(new Text_DescScenarios(Game.lang.get("1440Desc1") + " " + Game.lang.get("1440Desc2") + " " + Game.lang.get("1440Desc3"), CFG.BUTTON_HEIGHT, 150, CFG.GAME_WIDTH - CFG.BUTTON_HEIGHT * 2) {
            public boolean getClickable() {
                return false;
            }

            public int getPosY() {
                return ScenariosList_NewGame.iYPos / 2 - this.getHeight() / 2;
            }

            protected Color getColor(boolean isActive) {
                return Colors.HOVER_RIGHT2;
            }
        });
        menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("Scenarios"), "", Images.outliner, CFG.PADDING, CFG.GAME_HEIGHT - ImageManager.getImage(Images.buttonPlay).getHeight() - CFG.PADDING * 2 - CFG.BUTTON_HEIGHT3, ImageManager.getImage(Images.buttonPlay).getWidth() * 3 / 4, CFG.BUTTON_HEIGHT3, ImageManager.getImage(Images.time).getWidth()) {
            public void actionElement() {
                Scenarios.SCENARIOS_DEFAULT_MODE = !Scenarios.SCENARIOS_DEFAULT_MODE;
                Game.menuManager.setViewIDWithoutAnimation(View.SCENARIOS);
            }
        });
        menuElements.add(new ButtonStatsRectIMG_Bonuses(Game.lang.get("Campaign"), "", Images.outliner, CFG.PADDING, CFG.GAME_HEIGHT - ImageManager.getImage(Images.buttonPlay).getHeight() - CFG.PADDING * 2 - CFG.BUTTON_HEIGHT3 - CFG.PADDING - CFG.BUTTON_HEIGHT3, ImageManager.getImage(Images.buttonPlay).getWidth() * 3 / 4, CFG.BUTTON_HEIGHT3, ImageManager.getImage(Images.time).getWidth()) {
            public void actionElement() {
                Game.menuManager.setViewID(View.SCENARIOS_CAMPAIGN);
                Game.menuManager.setOrderOfMenu_Scenarios();
            }
        });
        menuElements.add(new ButtonFlag2_CivName_Random(CFG.PADDING, CFG.GAME_HEIGHT - ImageManager.getImage(Images.buttonPlay).getHeight() - CFG.PADDING * 4 - CFG.BUTTON_HEIGHT3 * 2 - (ImageManager.getImage(Images.flagOverDefault).getHeight() + CFG.TEXT_HEIGHT + CFG.PADDING * 2), true) {
            public void actionElement() {
                MapScenarios.loadRandomScenario();
            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("RandomScenario"), Colors.HOVER_GOLD));
                nData.add(new MenuElement_HoverElement_Type_ImageTitle_BG(Images.dice, CFG.PADDING, 0));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements, true);
            }
        });
        this.initMenu(null, 0, 0, CFG.GAME_WIDTH, CFG.GAME_HEIGHT, menuElements, true);
        this.updateDesc();
    }

    public void updateDesc() {
        if (!SCENARIOS_DEFAULT_MODE) {
            this.getMenuElement(2).setText("");
        } else {
            if (currentActiveDescID != activeDescID) {
                String sDesc = "";
                if (FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + (String)Game.mapScenarios.lScenarios_TagsList.get(activeDescID) + "/" + "Desc.txt").exists()) {
                    FileHandle tempFileT = FileManager.loadFile("map/" + Game.map.getFile_ActiveMap_Path() + "scenarios/" + (String)Game.mapScenarios.lScenarios_TagsList.get(activeDescID) + "/" + "Desc.txt");
                    String tempT = tempFileT.readString();
                    String[] sSplit = tempT.split(";");

                    for(int i = 0; i < sSplit.length; ++i) {
                        sDesc = sDesc + Game.lang.get(sSplit[i]) + (i == sSplit.length - 1 ? "" : " ");
                    }
                }

                currentActiveDescID = activeDescID;
                this.getMenuElement(2).setText(sDesc);
            }

        }
    }

    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean menuIsActive, Status titleStatus) {
        this.updateDesc();
        oSB.setColor(new Color(0.050980393F, 0.08627451F, 0.13333334F, 1.0F));
        InitGame.background.draw(oSB, iTranslateX + (CFG.GAME_WIDTH - InitGame.backgroundWidth) / 2, iTranslateY + (CFG.GAME_HEIGHT - InitGame.backgroundHeight) / 2, InitGame.backgroundWidth, InitGame.backgroundHeight);
        oSB.setColor(Color.WHITE);
        oSB.setShader(Renderer.shaderAlpha);
        InitGame.background.getTexture().bind(1);
        Gdx.gl.glActiveTexture(33984);
        Images.gradientXY.draw(oSB, this.getPosX() + (CFG.GAME_WIDTH - InitGame.backgroundWidth) / 2 + iTranslateX, this.getPosY() + (CFG.GAME_HEIGHT - InitGame.backgroundHeight) / 2 + iTranslateY, InitGame.backgroundWidth, InitGame.backgroundHeight);
        oSB.flush();
        oSB.setShader(Renderer.shaderDefault);
        oSB.setColor(MainMenu.sparksColors);
        if(Config.getAnimationConfig().Scenario_Spark > 1) {
            MenuManager.sparksAnimationHover.draw2(oSB, iTranslateX, iTranslateY, CFG.GAME_WIDTH, Images.sparkHeight, false, true);
        }else if (Config.getAnimationConfig().Scenario_Spark == 1) {
            MenuManager.sparksAnimation.draw2(oSB, iTranslateX, CFG.GAME_HEIGHT - Images.sparkHeight + iTranslateY, CFG.GAME_WIDTH, Images.sparkHeight);
        }
        oSB.setColor(Color.WHITE);
        oSB.setColor(Color.WHITE);
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }

    public void onHovered() {
        super.onHovered();
        Game.menuManager.setOrderOfMenu_Scenarios();
    }
}
