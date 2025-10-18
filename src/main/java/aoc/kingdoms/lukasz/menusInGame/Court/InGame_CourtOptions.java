//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.menusInGame.Court;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.menu.Menu;
import aoc.kingdoms.lukasz.menu.menuTitle.MenuTitleIMG_FlagCenter2;
import aoc.kingdoms.lukasz.menu_element.Empty;
import aoc.kingdoms.lukasz.menu_element.MenuElement;
import aoc.kingdoms.lukasz.menu_element.Status;
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.textures.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class InGame_CourtOptions extends Menu {
    protected static final int ANIMATION_TIME = 60;
    public static int menuH = 0;
    public static int iActiveID = -7777;
    public static int buildID = 0;
    public static int iGovernmentID = 0;
    public static int iLawID = 0;

    public InGame_CourtOptions() {
        List<MenuElement> menuElements = new ArrayList();
        int paddingLeft = CFG.PADDING;
        int menuWidth = ImageManager.getImage(Images.insideTop500).getWidth() - Images.boxTitleBORDERWIDTH * 2;
        int menuX = Images.boxTitleBORDERWIDTH + InGame_CourtOptions2.getOtherMenuPosX();
        int menuY = ImageManager.getImage(Images.flagBG).getHeight() + Renderer.boxBGExtraY + CFG.PADDING + ImageManager.getImage(Images.title1Red).getHeight();
        int buttonYPadding = CFG.PADDING * 2;
        int buttonY = 0;
        int buttonW = CFG.BUTTON_HEIGHT2;
        int buttonH = CFG.isDesktop() ? CFG.BUTTON_HEIGHT4 : CFG.BUTTON_HEIGHT2;
        menuElements.add(new Empty(0, 0, 1, 1));
        buttonY = ((MenuElement)menuElements.get(0)).getPosY() + ((MenuElement)menuElements.get(0)).getHeight();
        int menuHeight = buttonY + 2;
        menuHeight = 0;
        menuH = menuHeight + ImageManager.getImage(Images.title500).getHeight();
        this.initMenu(new MenuTitleIMG_FlagCenter2(Game.lang.get(GameValues.court.COUNCIL_NAME), false, false, Images.title500) {
            public int getFlagCivID() {
                return InGame_Court.iActiveCivID;
            }

            public long getTime() {
                return InGame_Court.lTime2;
            }

            public void draw(SpriteBatch oSB, int nPosX, int nPosY, int nWidth, Status titleStatus) {
                super.draw(oSB, nPosX - Images.boxTitleBORDERWIDTH, nPosY, nWidth + Images.boxTitleBORDERWIDTH * 2, titleStatus);
            }

            public void action() {
                super.action();
                Game.menuManager.setOrderOfMenu_InGameCourt();
            }
        }, menuX, menuY, menuWidth, menuHeight, menuElements, false, true);
        this.drawScrollPositionAlways = false;
    }

    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean menuIsActive, Status titleStatus) {
        long elapsedTime = CFG.currentTimeMillis - InGame_Court.lTime;
        float progress = Math.min(1.0f, (float)elapsedTime / 100.0f); // 500ms时长

        // easeOutQuad缓动函数: 1 - (1 - t)^2
        float easeOutProgress = 1.0f - (1.0f - progress) * (1.0f - progress);

        // 应用缓动动画
        if (progress < 1.0f) {
            int slideDistance = CFG.BUTTON_WIDTH;
            iTranslateX = iTranslateX - slideDistance + (int)(slideDistance * easeOutProgress);
        }

        Renderer.drawMenusBoxTopOnly(oSB, this.getPosX() - Images.boxTitleBORDERWIDTH + iTranslateX, this.getPosY() + iTranslateY, this.getWidth() + Images.boxTitleBORDERWIDTH * 2, this.getHeight(), false, Images.insideTop500);
        oSB.setColor(Colors.COLOR_GRADIENT_OVER_BLUE);
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), false, true);
        oSB.setColor(Color.WHITE);
        oSB.setColor(Colors.COLOR_GRADIENT_BG_BLUE);
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), false, true);
        oSB.setColor(Color.WHITE);
        oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.5F));
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), CFG.PADDING * 2, false, true);
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - CFG.PADDING * 2 - 1 + iTranslateY, this.getWidth(), CFG.PADDING * 2);
        oSB.setColor(Colors.COLOR_GRADIENT_BG_BLUE);
        Images.pix.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 + iTranslateY, this.getWidth(), 1);
        oSB.setColor(new Color(Colors.COLOR_BOX_FRAME.r, Colors.COLOR_BOX_FRAME.g, Colors.COLOR_BOX_FRAME.b, 0.35F));
        Images.pix.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 + iTranslateY, this.getWidth(), 1);
        oSB.setColor(Colors.COLOR_BOX_FRAME);
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + this.getHeight() - 1 + iTranslateY, this.getWidth(), 1);
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(Images.newGameOver).draw2(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.newGameOver).getWidth() / 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.newGameOver).getHeight()));
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
        }

        InGame_Court_Buildings2.oBuildingID = null;
    }

    public void actionElement(int nMenuElementID) {
        super.actionElement(nMenuElementID);
        if (InGame_Court.iActiveCivID != Game.player.iCivID) {
            InGame_Court.iActiveCivID = Game.player.iCivID;
        }

    }
}
