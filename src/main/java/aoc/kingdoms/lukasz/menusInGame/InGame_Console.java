//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.menusInGame;

import aoc.kingdoms.lukasz.events.outcome.EventOutcome_Explode;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.CharactersManager;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Keyboard;
import aoc.kingdoms.lukasz.jakowski.Keyboard.KeyboardActionType;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.map.RulersManager;
import aoc.kingdoms.lukasz.map.civilization.Civilization;
import aoc.kingdoms.lukasz.menu.Menu;
import aoc.kingdoms.lukasz.menu.View;
import aoc.kingdoms.lukasz.menu.menuTitle.MenuTitleIMG;
import aoc.kingdoms.lukasz.menu_element.Empty;
import aoc.kingdoms.lukasz.menu_element.MenuElement;
import aoc.kingdoms.lukasz.menu_element.Status;
import aoc.kingdoms.lukasz.menu_element.textStatic.Text_StaticBG;
import aoc.kingdoms.lukasz.menu_element.textStatic.Text_StaticBG_Write;
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.utilities.FPS;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import team.rainfall.fontFix.FontFix;

import java.util.ArrayList;
import java.util.List;

public class InGame_Console extends Menu {
    protected static final int ANIMATION_TIME = 60;
    public static long lTime = 0L;
    public static String sMessage = "";
    public static final int CONSOLE_LIMIT = 650;
    public static List<String> sConsole = new ArrayList();

    public InGame_Console(boolean visible) {
        List<MenuElement> menuElements = new ArrayList();
        int paddingLeft = CFG.PADDING * 2 + Images.boxTitleBORDERWIDTH;
        int titleHeight = ImageManager.getImage(Images.title2).getHeight();
        int menuWidth = ImageManager.getImage(Images.insideTop).getWidth();
        int menuX = CFG.PADDING * 2;
        int menuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY + CFG.PADDING + ImageManager.getImage(Images.title1Red).getHeight();
        int buttonY = CFG.PADDING;
        int buttonX = Images.boxTitleBORDERWIDTH + CFG.PADDING * 2;
        int buttonH = CFG.TEXT_HEIGHT + CFG.PADDING * 4;
        menuElements.add(new Text_StaticBG_Write(Game.lang.get("TypeMessage") + ": ", CFG.FONT_REGULAR_SMALL, CFG.PADDING * 2, paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT2) {
            public String getTextToDraw() {
                return InGame_Console.sMessage + Keyboard.getKeyboardVerticalLine();
            }

            public void actionElement() {
                Game.keyboard.showKeyboard(KeyboardActionType.CONSOLE, InGame_Console.sMessage);
            }
        });
        buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING * 2;

        for(int i = sConsole.size() - 1; i >= 0; --i) {
            menuElements.add(new Text_StaticBG((String)sConsole.get(i), CFG.FONT_REGULAR_SMALL, paddingLeft, buttonX, buttonY, menuWidth - paddingLeft * 2, buttonH));
            buttonY += ((MenuElement)menuElements.get(menuElements.size() - 1)).getHeight() + CFG.PADDING;
        }

        buttonY = 0;
        int i = 0;

        for(int iSize = menuElements.size(); i < iSize; ++i) {
            if (buttonY < ((MenuElement)menuElements.get(i)).getPosY() + ((MenuElement)menuElements.get(i)).getHeight() + CFG.PADDING * 2) {
                buttonY = ((MenuElement)menuElements.get(i)).getPosY() + ((MenuElement)menuElements.get(i)).getHeight() + CFG.PADDING * 2;
            }
        }

        buttonY = Math.max(buttonY, CFG.BUTTON_HEIGHT + CFG.BUTTON_HEIGHT * 4);
        i = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - CFG.PADDING * 3);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, i)));
        this.initMenu(new MenuTitleIMG(Game.lang.get("Console"), true, false, Images.title1Red) {
            public long getTime() {
                return InGame_Console.lTime;
            }
        }, CFG.GAME_WIDTH / 2 - menuWidth / 2, menuY, menuWidth, i, menuElements, visible, true);
    }

    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean menuIsActive, Status titleStatus) {
        if (lTime + 60L >= CFG.currentTimeMillis) {
            iTranslateX = iTranslateX - CFG.BUTTON_WIDTH + (int)((float)(CFG.BUTTON_WIDTH * 4 / 5) * ((float)(CFG.currentTimeMillis - lTime) / 60.0F));
        }

        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false);
        ImageManager.getImage(Images.newGameOver).draw2(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.newGameOver).getWidth() / 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.newGameOver).getHeight()));
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }

    public void setVisible(boolean visible) {
        super.setVisible(visible);
        lTime = CFG.currentTimeMillis;
        if (!visible) {
            Game.keyboard.hideKeyboard();
        }

    }

    public void actionCloseMenu() {
        super.actionCloseMenu();
        Game.keyboard.hideKeyboard();
    }

    public static final void addMessage(String nMess) {
        sConsole.add(nMess);
        if (sConsole.size() > 650) {
            sConsole.remove(0);
        }

    }

    public static final void execute(String nCommand) {
        addMessage("#" + nCommand);
        String[] tempCommand = nCommand.split("=");

        try {
            if (tempCommand.length > 0) {
                tempCommand[0] = tempCommand[0].toLowerCase();
                if (!tempCommand[0].equals("hi") && !tempCommand[0].equals("hello")) {
                    if (!tempCommand[0].equals("tag") && !tempCommand[0].equals("civtag") && !tempCommand[0].equals("civ") && !tempCommand[0].equals("id")) {
                        if (tempCommand[0].equals("fps")) {
                            FPS.drawFPS = !FPS.drawFPS;
                        } else if (tempCommand[0].equals("debug")) {
                            CFG.debugMode = !CFG.debugMode;
                        } else if (tempCommand[0].equals("callvassals")) {
                            Game.ENABLE_CALL_VASSALS = !Game.ENABLE_CALL_VASSALS;
                        } else if (tempCommand[0].equals("sandbox")) {
                            Game.SANDBOX = !Game.SANDBOX;
                            Game.menuManager.rebuildInGame();
                            Game.menuManager.rebuildInGame_CourtOptions();
                        } else if (tempCommand[0].equals("scale")) {
                            addMessage(Game.lang.get("CurrentScale") + ": " + Game.mapScale.getCurrentScale());
                        } else if (tempCommand[0].equals("hours")) {
                            if (tempCommand.length > 1) {
                                try {
                                    int hours = Integer.parseInt(tempCommand[1]);
                                    hours = Math.max(1, Math.min(24, hours));
                                    Game.HOURS_PER_TURN = hours;
                                    addMessage(Game.lang.get("HoursPerTurn") + ": " + Game.HOURS_PER_TURN);
                                    Game.menuManager.setViewIDWithoutAnimation(View.IN_GAME);
                                } catch (Exception var3) {
                                }
                            }
                        } else if (tempCommand[0].equals(GameValues.console.CONSOLE_COMMAND_KILL_RULER)) {
                            if (Game.iActiveProvince >= 0 && Game.getProvince(Game.iActiveProvince).getCivID() > 0) {
                                RulersManager.deathOfRuler(Game.getProvince(Game.iActiveProvince).getCivID());
                            }
                        } else if (tempCommand[0].equals("occupybyplayer")) {
                            if (Game.iActiveProvince >= 0 && Game.getProvince(Game.iActiveProvince).getCivID() != Game.player.iCivID) {
                                Game.getProvince(Game.iActiveProvince).setOccupiedByCivID(Game.player.iCivID);
                            }
                        } else if (tempCommand[0].equals("addgeneral") && tempCommand.length > 1 && tempCommand[1] != null) {
                            CharactersManager.loadGeneral(Game.player.iCivID, tempCommand[1], -99, -99);
                        } else if (tempCommand[0].equals(GameValues.console.CONSOLE_COMMAND_GOLD)) {
                            Civilization var10000 = Game.getCiv(Game.player.iCivID);
                            var10000.fGold += GameValues.console.CONSOLE_GOLD;
                            addMessage(Game.getCiv(Game.player.iCivID).getCivName() + ": " + CFG.getPrecision2(GameValues.console.CONSOLE_GOLD, 10) + " Gold added");
                        } else if (tempCommand[0].equals(GameValues.console.CONSOLE_COMMAND_LEGACY)) {
                            Civilization var7 = Game.getCiv(Game.player.iCivID);
                            var7.fLegacy += GameValues.console.CONSOLE_LEGACY;
                            addMessage(Game.getCiv(Game.player.iCivID).getCivName() + ": " + CFG.getPrecision2(GameValues.console.CONSOLE_LEGACY, 10) + " Legacy added");
                        } else if (tempCommand[0].equals(GameValues.console.CONSOLE_COMMAND_MANPOWER)) {
                            Civilization var8 = Game.getCiv(Game.player.iCivID);
                            var8.fManpower += (double)GameValues.console.CONSOLE_MANPOWER;
                            addMessage(Game.getCiv(Game.player.iCivID).getCivName() + ": " + CFG.getPrecision2(GameValues.console.CONSOLE_MANPOWER, 10) + " Manpower added");
                        } else if (tempCommand[0].equals(GameValues.console.CONSOLE_COMMAND_DIPLOMACY)) {
                            Civilization var9 = Game.getCiv(Game.player.iCivID);
                            var9.fDiplomacy += GameValues.console.CONSOLE_DIPLOMACY;
                            addMessage(Game.getCiv(Game.player.iCivID).getCivName() + ": " + CFG.getPrecision2(GameValues.console.CONSOLE_DIPLOMACY, 10) + " Diplomacy Points added");
                        } else if (tempCommand[0].equals(GameValues.console.CONSOLE_COMMAND_ADVANTAGE)) {
                            Game.getCiv(Game.player.iCivID).setAdvantagePoints(Game.getCiv(Game.player.iCivID).getAdvantagePoints() + GameValues.console.CONSOLE_ADVANTAGE);
                            addMessage(Game.getCiv(Game.player.iCivID).getCivName() + ": " + CFG.getPrecision2((float)GameValues.console.CONSOLE_ADVANTAGE, 10) + " Advantage points added");
                        } else if (tempCommand[0].equals(GameValues.console.CONSOLE_COMMAND_NUKE)) {
                            Game.getCiv(Game.player.iCivID).canBuildNuke = true;
                            Game.getCiv(Game.player.iCivID).setNukes(Game.getCiv(Game.player.iCivID).getNukes() + 1);
                            addMessage(Game.getCiv(Game.player.iCivID).getCivName() + ": Atomic Bomb added");
                        } else if (tempCommand[0].equals(GameValues.console.CONSOLE_COMMAND_EXPLODE) && Game.iActiveProvince >= 0 && Game.getProvince(Game.iActiveProvince).getCivID() > 0) {
                            EventOutcome_Explode explode = new EventOutcome_Explode(Game.getCiv(Game.getProvince(Game.iActiveProvince).getCivID()).getCivTag());
                            explode.updateCiv(0, 0);
                            addMessage("explode" + Game.getCiv(Game.getProvince(Game.iActiveProvince).getCivID()).getCivName());
                        }
                    } else if (Game.iActiveProvince >= 0) {
                        addMessage(Game.getCiv(Game.getProvince(Game.iActiveProvince).getCivID()).getCivName());
                        addMessage("Civ ID: " + Game.getProvince(Game.iActiveProvince).getCivID());
                        addMessage("CivTAG: " + Game.getCiv(Game.getProvince(Game.iActiveProvince).getCivID()).getCivTag());
                    }
                } else {
                    addMessage("Hello, Welcome to Age of History 3");
                    addMessage("Polaris Core "+ FontFix.CORE_VERSION);
                }
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }
}
