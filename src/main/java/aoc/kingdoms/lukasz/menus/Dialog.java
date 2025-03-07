//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.menus;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.jakowski.SaveLoad.SaveGameManager;
import aoc.kingdoms.lukasz.map.LegacyManager;
import aoc.kingdoms.lukasz.map.advisors.Advisor;
import aoc.kingdoms.lukasz.map.advisors.AdvisorManager;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.menu.Menu;
import aoc.kingdoms.lukasz.menu.View;
import aoc.kingdoms.lukasz.menu.menuTitle.MenuTitle;
import aoc.kingdoms.lukasz.menu_element.MenuElement;
import aoc.kingdoms.lukasz.menu_element.Status;
import aoc.kingdoms.lukasz.menu_element.Toast;
import aoc.kingdoms.lukasz.menu_element.button.Button;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_Hover;
import aoc.kingdoms.lukasz.menusInGame.InGame_CivBonuses;
import aoc.kingdoms.lukasz.menusInGame.InGame_Legacies;
import aoc.kingdoms.lukasz.menusInGame.Court.InGame_Court;
import aoc.kingdoms.lukasz.menusMapEditor.EditorMapProvinceConnections;
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.textures.Images;
import aoc.kingdoms.lukasz.utilities.FPS;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import team.rainfall.finality.FinalityLogger;

import java.util.ArrayList;
import java.util.List;

public class Dialog extends Menu {
    public static String GO_TO_LINK = "";
    public static DialogType dialogType;
    private int iBackgroundAlpha = 5;
    private static final long ANIMATION_DURATION = 200L;
    private int animationStepID = 0;
    private long startTime;
    private int animationChangePosY;
    private boolean closeMenu = false;

    public static final void setDialogType(DialogType nDialogType) {
        dialogType = nDialogType;
        Game.menuManager.dialogMenu.getMenuElement(0).setClickable(true);
        Game.menuManager.dialogMenu.getMenuElement(1).setClickable(true);

        try {
            switch (dialogType) {
                case EXIT_GAME:
                    Game.menuManager.dialogMenu.getTitle().setText(Game.lang.get("ExitTheGame"));
                    break;
                case GENERATE_PROVINCE_CONNECTIONS:
                    Game.menuManager.dialogMenu.getTitle().setText(Game.lang.get("Generate"));
                    break;
                case GENERATE_SUGGESTED_CIVILIZATIONS:
                    Game.menuManager.dialogMenu.getTitle().setText(Game.lang.get("GenerateSuggestedCivilizations") + "?");
                    break;
                case CORE_ALL_PROVINCES:
                    Game.menuManager.dialogMenu.getTitle().setText(Game.lang.get("AddCore") + ": " + Game.lang.get("AllProvinces") + "?");
                    break;
                case DELETE_SAVE:
                    Game.menuManager.dialogMenu.getTitle().setText(Game.lang.get("DeleteSavedGame") + " " + SaveGameManager.deleteSavedGameKey);
                    break;
                case CONVERT_RELIGION_ALL_PROVINCES:
                    Game.menuManager.dialogMenu.getTitle().setText(Game.lang.get("ConvertReligion") + ": " + Game.lang.get("AllProvinces") + "?");
                    break;
                case UNLOCK_LEGACY:
                    Game.menuManager.dialogMenu.getTitle().setText(Game.lang.get("Unlock") + ": " + Game.lang.get(((LegacyManager.Legacy)LegacyManager.legacies.get(InGame_Legacies.UNLOCK_LEGACY_ID)).Name));
                    break;
                case REVERSE_WASTELAND:
                    Game.menuManager.dialogMenu.getTitle().setText(Game.lang.get("Reverse") + "?");
                    break;
                case EXIT_SCENARIO_EDITOR:
                    Game.menuManager.dialogMenu.getTitle().setText(Game.lang.get("ExitScenarioEditor") + "?");
                    break;
                case ESCAPE_TO_MAIN_MENU:
                    Game.menuManager.dialogMenu.getTitle().setText(Game.lang.get("AreYouSure") + " " + Game.lang.get("ExitToMainMenu") + "?");
                    break;
                case CREATE_SCENARIO_ASSIGN_CIVILIZATION:
                    Game.menuManager.dialogMenu.getTitle().setText(Game.lang.get("Select") + " " + Game.getCiv(Game.getProvince(Game.iActiveProvince).getCivID()).getCivName() + "?");
                    break;
                case CREATE_SCENARIO_REMOVE_CIVILIZATION:
                    Game.menuManager.dialogMenu.getTitle().setText(Game.lang.get("Remove") + " " + Game.getCiv(Game.getProvince(CFG.iCreateScenario_ActiveProvinceID).getCivID()).getCivName() + "?");
                    break;
                case FIRE_ADVISOR:
                    Game.menuManager.dialogMenu.getTitle().setText(Game.lang.get("FireAdvisor") + ": " + AdvisorManager.getAdvisorGroupName(InGame_Court.FIRE_ID));
                    break;
                case GO_TO_LINK:
                    Game.menuManager.dialogMenu.getTitle().setText(Game.lang.get("Open") + " " + GO_TO_LINK.substring(0, Math.min(GO_TO_LINK.length(), 30)) + "?");
            }
        } catch (IndexOutOfBoundsException var2) {
            IndexOutOfBoundsException ex = var2;
            CFG.exceptionStack(ex);
        }

        Game.menuManager.dialogMenu.setVisible(true);
    }

    public static final void dialogTrue() {
        MenuElement_Hover.resetAnimation();

        try {
            int i;
            switch (dialogType) {
                case EXIT_GAME:
                    System.exit(0);
                    return;
                case GENERATE_PROVINCE_CONNECTIONS:
                    EditorMapProvinceConnections.generateConnections();
                    Game.menuManager.addToast(Game.lang.get("Done"));
                    return;
                case GENERATE_SUGGESTED_CIVILIZATIONS:
                    return;
                case CORE_ALL_PROVINCES:
                    for(i = 0; i < Game.getCiv(Game.player.iCivID).getNumOfProvinces(); ++i) {
                        if (!Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(i)).isOccupied() && Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(i)).coreCreation == null && !Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(i)).haveACore(Game.player.iCivID)) {
                            Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(i)).addCoreCreation();
                        }
                    }

                    Game.menuManager.rebuildInGame_CoreSavePos();
                    Game.menuManager.setVisibleInGame_Court(true);
                    InGame_Court.lTime = 0L;
                    Game.menuManager.addToast(Game.lang.get("Done"));
                    Game.player.currSituation.updateCurrentSituation();
                    return;
                case DELETE_SAVE:
                    SaveGameManager.deleteSavedGame(SaveGameManager.deleteSavedGameKey, true);
                    if (Game.menuManager.getInLoadGamesList()) {
                        Game.menuManager.setViewID(View.LOAD_GAMES_LIST);
                    }

                    return;
                case CONVERT_RELIGION_ALL_PROVINCES:
                    for(i = 0; i < Game.getCiv(Game.player.iCivID).getNumOfProvinces(); ++i) {
                        if (!Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(i)).isOccupied() && Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(i)).religionConversion == null && Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(i)).getReligion() != Game.getCiv(Game.player.iCivID).getReligionID()) {
                            Game.getProvince(Game.getCiv(Game.player.iCivID).getProvinceID(i)).addReligionConversion();
                        }
                    }

                    Game.menuManager.rebuildInGame_ReligionSavePos();
                    Game.menuManager.setVisibleInGame_Court(true);
                    InGame_Court.lTime = 0L;
                    Game.menuManager.addToast(Game.lang.get("Done"));
                    Game.player.currSituation.updateCurrentSituation();
                    return;
                case UNLOCK_LEGACY:
                    InGame_Legacies.unlockLegacy();
                    return;
                case REVERSE_WASTELAND:
                    for(i = 0; i < Game.getProvincesSize(); ++i) {
                        if (!Game.getProvince(i).getSeaProvince()) {
                            if (Game.getProvince(i).getWasteland() < 0) {
                                Game.getProvince(i).setWasteland(0);
                            } else {
                                Game.getProvince(i).setWasteland(-1);
                            }
                        }
                    }

                    Game.updateProvincesInView = true;
                    Game.menuManager.addToast(Game.lang.get("Done"));
                    return;
                case EXIT_SCENARIO_EDITOR:
                    Game.menuManager.setViewID(View.EDITOR_SCENARIOS_LIST);
                    return;
                case ESCAPE_TO_MAIN_MENU:
                    MainMenu.canContinue = true;
                    Game.menuManager.setVisibleInGame_Escape(false);
                    Game.menuManager.setViewID(View.MAINMENU);
                    return;
                case CREATE_SCENARIO_ASSIGN_CIVILIZATION:
                    CFG.iCreateScenario_AssignProvinces_Civ = Game.getProvince(Game.iActiveProvince).getCivID();
                    return;
                case CREATE_SCENARIO_REMOVE_CIVILIZATION:
                    Game.menuManager.addToast(new Toast(Game.getCiv(Game.getProvince(CFG.iCreateScenario_ActiveProvinceID).getCivID()).getCivName() + " - " + Game.lang.get("Removed"), CFG.FONT_BOLD, 3500, Colors.HOVER_IMPORTANT));
                    Game.removeCivilization(Game.getProvince(CFG.iCreateScenario_ActiveProvinceID).getCivID());
                    Game.menuManager.rebuildScenarioCivilizationsList();
                    return;
                case FIRE_ADVISOR:
                    AdvisorManager var10000;
                    if (InGame_Court.FIRE_ID == 0) {
                        if (Game.getCiv(Game.player.iCivID).advisorAdministration.sName != null) {
                            var10000 = Game.advisorManager;
                            AdvisorManager.updateCivBonuses(Game.getCiv(Game.player.iCivID).advisorAdministration, Game.player.iCivID, -1);
                            Game.menuManager.addToastGold(Game.lang.get("Done"), Images.v);
                        }

                        Game.getCiv(Game.player.iCivID).advisorAdministration = new Advisor();
                    } else if (InGame_Court.FIRE_ID == 1) {
                        if (Game.getCiv(Game.player.iCivID).advisorEconomy.sName != null) {
                            var10000 = Game.advisorManager;
                            AdvisorManager.updateCivBonuses(Game.getCiv(Game.player.iCivID).advisorEconomy, Game.player.iCivID, -1);
                            Game.menuManager.addToastGold(Game.lang.get("Done"), Images.v);
                        }

                        Game.getCiv(Game.player.iCivID).advisorEconomy = new Advisor();
                    } else if (InGame_Court.FIRE_ID == 2) {
                        if (Game.getCiv(Game.player.iCivID).advisorTechnology.sName != null) {
                            var10000 = Game.advisorManager;
                            AdvisorManager.updateCivBonuses(Game.getCiv(Game.player.iCivID).advisorTechnology, Game.player.iCivID, -1);
                            Game.menuManager.addToastGold(Game.lang.get("Done"), Images.v);
                        }

                        Game.getCiv(Game.player.iCivID).advisorTechnology = new Advisor();
                    } else if (InGame_Court.FIRE_ID == 3) {
                        if (Game.getCiv(Game.player.iCivID).advisorMilitary.sName != null) {
                            var10000 = Game.advisorManager;
                            AdvisorManager.updateCivBonuses(Game.getCiv(Game.player.iCivID).advisorMilitary, Game.player.iCivID, -1);
                            Game.menuManager.addToastGold(Game.lang.get("Done"), Images.v);
                        }

                        Game.getCiv(Game.player.iCivID).advisorMilitary = new Advisor();
                    }

                    Game.getCiv(Game.player.iCivID).updateResearchPerMonth();
                    Game.getCiv(Game.player.iCivID).updateLegacyPerMonth();
                    if (Game.menuManager.getVisibleInGame_Court()) {
                        Game.menuManager.rebuildInGame_CourtSavePos();
                        Game.menuManager.setVisibleInGame_Court(true);
                        InGame_Court.lTime = 0L;
                    }

                    if (Game.menuManager.getVisibleInGame_CivBonuses()) {
                        Game.menuManager.rebuildInGame_CivBonuses();
                        Game.menuManager.setVisibleInGame_CivBonuses(true);
                        InGame_CivBonuses.lTime = 0L;
                    }

                    return;
                case GO_TO_LINK:
                    try {
                        Gdx.net.openURI(GO_TO_LINK);
                    } catch (GdxRuntimeException var1) {
                        Game.menuManager.addToast(Game.lang.get("Error"));
                    }

                    return;
            }
        } catch (IndexOutOfBoundsException var2) {
            CFG.exceptionStack(var2);
        } catch (NullPointerException var3) {
            NullPointerException ex = var3;
            CFG.exceptionStack(ex);
        }

    }

    public static final void dialogFalse() {
        MenuElement_Hover.resetAnimation();
        switch (dialogType) {
            default:
        }
    }

    public Dialog() {

        List<MenuElement> menuElements = new ArrayList();
        int titleHeight = CFG.BUTTON_HEIGHT + CFG.BUTTON_HEIGHT / 4;
        int tWidth = CFG.LEFT_MENU_WIDTH + CFG.LEFT_MENU_WIDTH / 2;
        if (CFG.GAME_WIDTH <= tWidth - CFG.PADDING * 8) {
            tWidth = CFG.GAME_WIDTH - CFG.PADDING * 8;
        }

        menuElements.add(new Button(Game.lang.get("No"), 0, -1, 0, 0, tWidth / 2, true) {
            public void actionElement() {
                Dialog.this.disableButtons();
                Dialog.dialogFalse();
                Dialog.this.closeMenu();
            }

            protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                ImageManager.getImage(isActive ? Images.boxBIG_Red : Images.boxBIG).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), false, true);
                oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.2F));
                Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), false, true);
                oSB.setColor(Colors.COLOR_BOX_FRAME);
                ImageManager.getImage(Images.line_32_vertical).draw(oSB, this.getPosX() + this.getWidth() - 1 + iTranslateX, this.getPosY() + iTranslateY, 1, this.getHeight());
                oSB.setColor(Color.WHITE);
            }

            protected Color getColor(boolean isActive) {
                return Colors.getColorTopStats(isActive, this.getIsHovered());
            }
        });
        menuElements.add(new Button(Game.lang.get("Yes"), 0, -1, tWidth / 2, 0, tWidth - tWidth / 2, true) {
            public void actionElement() {
                Dialog.this.disableButtons();
                Dialog.dialogTrue();
                Dialog.this.closeMenu();
            }

            protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
                ImageManager.getImage(isActive ? Images.boxBIG_Red : Images.boxBIG).draw2(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), true, true);
                oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.2F));
                Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), false, true);
                oSB.setColor(Color.WHITE);
            }

            protected Color getColor(boolean isActive) {
                return Colors.getColorTopStats(isActive, this.getIsHovered());
            }
        });
        this.initMenu(new MenuTitle("", 1.0F, titleHeight, true, true), CFG.GAME_WIDTH / 2 - tWidth / 2, CFG.GAME_HEIGHT / 2 - CFG.BUTTON_HEIGHT / 2 - titleHeight / 2, tWidth, CFG.BUTTON_HEIGHT * 2, menuElements, false);
    }

    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean menuIsActive, Status titleStatus) {
        if (this.closeMenu) {
            this.iBackgroundAlpha -= 8;
            if (this.iBackgroundAlpha <= 0) {
                this.iBackgroundAlpha = 0;
            }
        } else if (this.iBackgroundAlpha < 75) {
            this.iBackgroundAlpha += 4;
        }
        updateChangePosY();
        oSB.setColor(new Color(0.0F, 0.0F, 0.0F, (float)this.iBackgroundAlpha / 2.0F / 255.0F));
        Images.pix.draw2(oSB, iTranslateX, iTranslateY, CFG.GAME_WIDTH, CFG.GAME_HEIGHT);
        oSB.setColor(Color.WHITE);
        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.animationChangePosY + this.getPosY() - this.getTitle().getHeight(), this.getWidth(), this.getTitle().getHeight() + CFG.BUTTON_HEIGHT);
        oSB.setColor(new Color(0.0125F, 0.0125F, 0.0125F, (float)this.iBackgroundAlpha * 1.45F / 255.0F));
        ImageManager.getImage(Images.patt).draw2(oSB, iTranslateX, iTranslateY, CFG.GAME_WIDTH, CFG.GAME_HEIGHT);
        oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.4F));
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() - Images.gradientXY.getHeight() + this.animationChangePosY, this.getWidth(), Images.gradientXY.getHeight());
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + CFG.BUTTON_HEIGHT + this.animationChangePosY, this.getWidth(), Images.gradientXY.getHeight(), false, true);
        oSB.setColor(Color.WHITE);
        super.draw(oSB, iTranslateX, this.animationChangePosY + iTranslateY, menuIsActive, titleStatus);
    }

    private final void updateChangePosY() {
//        switch (this.animationStepID) {
//            case 0:
//            case 1:
//            case 12:
//                this.animationChangePosY = (int)((float)this.animationChangePosY - (float)(CFG.GAME_HEIGHT / 2 + this.getTitle().getHeight()) * 2.5F / 100.0F * (float)(this.closeMenu ? -1 : 1));
//                break;
//            case 2:
//            case 3:
//            case 10:
//            case 11:
//                this.animationChangePosY = (int)((float)this.animationChangePosY - (float)(CFG.GAME_HEIGHT / 2 + this.getTitle().getHeight()) * 5.0F / 100.0F * (float)(this.closeMenu ? -1 : 1));
//                break;
//            case 4:
//            case 5:
//            case 8:
//            case 9:
//                this.animationChangePosY = (int)((float)this.animationChangePosY - (float)(CFG.GAME_HEIGHT / 2 + this.getTitle().getHeight()) * 10.0F / 100.0F * (float)(this.closeMenu ? -1 : 1));
//                break;
//            case 6:
//            case 7:
//                this.animationChangePosY = (int)((float)this.animationChangePosY - (float)(CFG.GAME_HEIGHT / 2 + this.getTitle().getHeight()) * 15.0F / 100.0F * (float)(this.closeMenu ? -1 : 1));
//                break;
//            case 13:
//                this.animationChangePosY = 0;
//        }
        //this.animationChangePosY = (int)((float)this.animationChangePosY - (float)(CFG.GAME_HEIGHT / 2 + this.getTitle().getHeight()) * 2.5F / 100.0F * (float)(this.closeMenu ? -1 : 1));
        this.animationChangePosY = CFG.GAME_HEIGHT / 2 + this.getTitle().getHeight();
        float fPerc = (float) ((System.currentTimeMillis() - startTime)) / (ANIMATION_DURATION);
        if(fPerc > 1){
            this.animationChangePosY = 0;
            this.animationStepID = 13;
            if(closeMenu){
                this.animationChangePosY = CFG.GAME_HEIGHT;
                setVisible(false);
            }
            return;
        }
        fPerc = (float) Math.sqrt(1 - fPerc * fPerc * fPerc);
        FinalityLogger.debug("fPerc "+fPerc);
        if(!closeMenu) {
            animationChangePosY = (int) (animationChangePosY * (fPerc));
        }else {
            animationChangePosY = (int) (animationChangePosY * (1 - fPerc));
        }
        FPS var10001 = Renderer.uFPS;
        if (Renderer.uFPS.iNumOfFPS < 22) {
            this.animationStepID = 13;
            this.animationChangePosY = 0;
        }

        if (this.closeMenu && this.animationStepID == 13) {
            this.animationChangePosY = CFG.GAME_HEIGHT;
            this.setVisible(false);
        }

       // ++this.animationStepID;
    }

    public final void disableButtons() {
        this.getMenuElement(0).setClickable(false);
        this.getMenuElement(1).setClickable(false);
    }

    public final void closeMenu() {
        this.closeMenu = true;
        this.resetAnimation();
    }

    public final void setVisible(boolean visible) {
        super.setVisible(visible);
        this.closeMenu = !visible;
        this.iBackgroundAlpha = 5;
        this.resetAnimation();
    }

    private final void resetAnimation() {
        this.startTime = System.currentTimeMillis();
        this.animationStepID = 0;
        if (!this.closeMenu) {
            this.animationChangePosY = CFG.GAME_HEIGHT / 2 + this.getTitle().getHeight();
        }

    }

    static {
        dialogType = Dialog.DialogType.EXIT_GAME;
    }

    public static enum DialogType {
        EXIT_GAME,
        PAUSE_GAME,
        FIRE_ADVISOR,
        UNLOCK_LEGACY,
        REVERSE_WASTELAND,
        EXIT_SCENARIO_EDITOR,
        DELETE_SAVE,
        CREATE_SCENARIO_REMOVE_CIVILIZATION,
        GENERATE_PROVINCE_CONNECTIONS,
        GENERATE_SUGGESTED_CIVILIZATIONS,
        CREATE_SCENARIO_ASSIGN_CIVILIZATION,
        ESCAPE_TO_MAIN_MENU,
        CORE_ALL_PROVINCES,
        CONVERT_RELIGION_ALL_PROVINCES,
        GO_TO_LINK;

        private DialogType() {
        }
    }
}
