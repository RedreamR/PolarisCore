//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.menusEditor;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.jakowski.Steam.SteamManager;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.menu.Menu;
import aoc.kingdoms.lukasz.menu.View;
import aoc.kingdoms.lukasz.menu.menuTitle.MenuTitle;
import aoc.kingdoms.lukasz.menu_element.MenuElement;
import aoc.kingdoms.lukasz.menu_element.Status;
import aoc.kingdoms.lukasz.menu_element.button.ButtonCurrentSituation_Value_Mods;
import aoc.kingdoms.lukasz.menu_element.button.ButtonMain;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_Hover;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.textures.Images;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class ManageMods extends Menu {
    public ManageMods() {
        List<MenuElement> menuElements = new ArrayList<>();
        int paddingLeft = CFG.PADDING * 2;
        int titleHeight = CFG.BUTTON_HEIGHT + CFG.BUTTON_HEIGHT / 2;
        int menuX = CFG.GAME_WIDTH / 10;
        int menuY = CFG.GAME_HEIGHT / 10;
        int buttonYPadding = CFG.PADDING * 2;
        menuElements.add(new ButtonMain(Game.lang.get("Back"), 1, -1, paddingLeft, CFG.PADDING, CFG.LEFT_MENU_WIDTH2 - paddingLeft * 2, true) {
            public void actionElement() {
                Renderer.drawArmyInProvince = true;
                Game.menuManager.setViewID(View.EDITOR);
            }
        });
        int buttonY = buttonYPadding + menuElements.get(0).getHeight() + buttonYPadding;
        if(!CFG.isAndroid()) {
            for (int i = 0; i < SteamManager.itemsInstalledAll.size(); ++i) {
                menuElements.add(new ButtonCurrentSituation_Value_Mods(SteamManager.modsFoldersAll_ModName.get(i), Images.technology2, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH2 - paddingLeft * 2, CFG.BUTTON_HEIGHT, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true, i, SteamManager.isTurnedOn(SteamManager.itemsInstalledAll.get(i).getFolder())) {
                    public void actionElement() {
                        SteamManager.addModsTurnedOff(SteamManager.itemsInstalledAll.get(this.getCurrent()).getFolder());
                        this.setCheckboxState(SteamManager.isTurnedOn(SteamManager.itemsInstalledAll.get(this.getCurrent()).getFolder()));
                        Game.menuManager.addToastGold(Game.lang.get("GameNeedsToBeRestartedToApplyTheChanges"), Images.settings);
                    }

                    public void buildElementHover() {
                        List<MenuElement_HoverElement> nElements = new ArrayList<>();
                        List<MenuElement_HoverElement_Type> nData = new ArrayList<>();
                        nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("On") + " / " + Game.lang.get("Off"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                        nElements.add(new MenuElement_HoverElement(nData));
                        nData.clear();
                        this.menuElementHover = new MenuElement_Hover(nElements, true);
                    }
                });
                buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
            }
        }
        for(int i = 0; i < SteamManager.modsFoldersAll.size(); ++i) {
            menuElements.add(new ButtonCurrentSituation_Value_Mods(SteamManager.modsFoldersAll.get(i), Images.technology2, paddingLeft, buttonY, CFG.LEFT_MENU_WIDTH2 - paddingLeft * 2, CFG.BUTTON_HEIGHT, ImageManager.getImage(Images.gold).getWidth() + CFG.PADDING * 4, true, i, SteamManager.isTurnedOn(SteamManager.modsFoldersAll.get(i))) {
                public void actionElement() {
                    SteamManager.addModsTurnedOff(SteamManager.modsFoldersAll.get(this.getCurrent()));
                    this.setCheckboxState(SteamManager.isTurnedOn(SteamManager.modsFoldersAll.get(this.getCurrent())));
                    Game.menuManager.addToastGold(Game.lang.get("GameNeedsToBeRestartedToApplyTheChanges"), Images.settings);
                }

                public void buildElementHover() {
                    List<MenuElement_HoverElement> nElements = new ArrayList<>();
                    List<MenuElement_HoverElement_Type> nData = new ArrayList<>();
                    nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("On") + " / " + Game.lang.get("Off"), CFG.FONT_BOLD, Colors.HOVER_GOLD));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    this.menuElementHover = new MenuElement_Hover(nElements, true);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + buttonYPadding;
        }

        this.initMenu(new MenuTitle(Game.lang.get("InstalledMods"), 1.0F, titleHeight, true, true), menuX, titleHeight + menuY, CFG.LEFT_MENU_WIDTH2, CFG.GAME_HEIGHT - titleHeight - menuY - CFG.PADDING * 2, menuElements, true, false);
    }

    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean menuIsActive, Status titleStatus) {
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawEditorMenuBG(oSB, this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight() + CFG.PADDING, iTranslateX, iTranslateY);
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
}
