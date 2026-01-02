//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.menusInGame;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.SoundsManager;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.menu.Menu;
import aoc.kingdoms.lukasz.menu.menuTitle.MenuTitleIMG;
import aoc.kingdoms.lukasz.menu_element.Empty;
import aoc.kingdoms.lukasz.menu_element.MenuElement;
import aoc.kingdoms.lukasz.menu_element.Slider;
import aoc.kingdoms.lukasz.menu_element.Status;
import aoc.kingdoms.lukasz.menu_element.button.ButtonGame;
import aoc.kingdoms.lukasz.menu_element.button.ButtonStatsRect_Active;
import aoc.kingdoms.lukasz.menu_element.button.ButtonStatsRect_Active_Value;
import aoc.kingdoms.lukasz.menu_element.button.ButtonStatsRect_Active_Value2;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_Hover;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_TextTitle_BG;
import aoc.kingdoms.lukasz.menu_element.textStatic.Text_Title_v2_TextLR;
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.textures.Images;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import team.rainfall.fontFix.utils.AnimationUtil;

import java.util.ArrayList;
import java.util.List;

public class InGame_Audio extends Menu {
    protected static final int ANIMATION_TIME = 60;
    public static long lTime = 0L;
    public static boolean shouldRefresh = false;
    public InGame_Audio() {

        List<MenuElement> menuElements = new ArrayList<>();
        int paddingLeft = CFG.PADDING * 2 + Images.boxTitleBORDERWIDTH;
        int titleHeight = ImageManager.getImage(Images.title500).getHeight();
        int menuWidth = ImageManager.getImage(Images.title500).getWidth();
        int menuX = CFG.BUTTON_WIDTH + Renderer.boxBGExtraY + CFG.PADDING;
        int menuY = ImageManager.getImage(Images.topStats).getHeight() + CFG.PADDING * 2 + ImageManager.getImage(Images.title500).getHeight();
        int buttonY = CFG.PADDING;
        menuElements.add(new Slider(Game.lang.get("MasterVolume") + ": ", paddingLeft, buttonY, menuWidth - paddingLeft * 2 - CFG.PADDING, CFG.BUTTON_HEIGHT4, 0, 100, (int) (SoundsManager.masterVolume * 100.0F)) {
            public void actionElement() {
                Game.settingsManager.VOLUME_MASTER = (float) this.getCurrent() / 100.0F;
                Game.soundsManager.setMasterVolume((float) this.getCurrent() / 100.0F);
            }

            public String getDrawText() {
                return super.getDrawText() + "%";
            }
        });
        buttonY += menuElements.get(0).getHeight() + CFG.PADDING;
        menuElements.add(new Slider(Game.lang.get("MusicVolume") + ": ", paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, 0, 100, (int) (SoundsManager.musicVolume * 100.0F)) {
            public void actionElement() {
                Game.settingsManager.VOLUME_MUSIC = (float) this.getCurrent() / 100.0F;
                Game.soundsManager.setMusicVolume((float) this.getCurrent() / 100.0F);
            }

            public String getDrawText() {
                return super.getDrawText() + "%";
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new Slider(Game.lang.get("AmbienceVolume") + ": ", paddingLeft, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING) / 2, CFG.BUTTON_HEIGHT4, 0, 100, (int) (SoundsManager.ambienceVolume * 100.0F)) {
            public void actionElement() {
                Game.settingsManager.VOLUME_AMBIENCE = (float) this.getCurrent() / 100.0F;
                Game.soundsManager.setAmbienceVolume((float) this.getCurrent() / 100.0F);
            }

            public String getDrawText() {
                return super.getDrawText() + "%";
            }
        });
        menuElements.add(new Slider(Game.lang.get("EffectVolume") + ": ", paddingLeft + (menuWidth - paddingLeft * 2 - CFG.PADDING) / 2 + CFG.PADDING, buttonY, (menuWidth - paddingLeft * 2 - CFG.PADDING) / 2, CFG.BUTTON_HEIGHT4, 0, 100, (int) (SoundsManager.soundsVolume * 100.0F)) {
            public void actionElement() {
                Game.settingsManager.VOLUME_SOUNDS = (float) this.getCurrent() / 100.0F;
                Game.soundsManager.setSoundsVolume((float) this.getCurrent() / 100.0F);
            }

            public String getDrawText() {
                return super.getDrawText() + "%";
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new Text_Title_v2_TextLR(Game.soundsManager.getCurrentMusicTittle(), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, Game.soundsManager.currentMusicDuraStr));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        final int LRid = menuElements.size() - 1;
        menuElements.add(new ButtonStatsRect_Active("<<", paddingLeft, buttonY, (int) ((float) menuWidth * 1 / 3) - paddingLeft, CFG.BUTTON_HEIGHT4) {
            public void actionElement() {
                Game.soundsManager.loadPreviousMusic();
                menuElements.get(LRid).setText(Game.soundsManager.getCurrentMusicTittle());
                Text_Title_v2_TextLR lr = (Text_Title_v2_TextLR) menuElements.get(LRid);
                lr.sTextRight = Game.soundsManager.currentMusicDuraStr;
            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Previous"), Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("NowPlaying") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text(Game.soundsManager.getCurrentMusicTittle(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        menuElements.add(new ButtonStatsRect_Active("||", (int) ((float) menuWidth * 1 / 3), buttonY, (int) ((float) menuWidth * 1 / 3), CFG.BUTTON_HEIGHT4) {
            public void actionElement() {
                if (this.getText().equals("||")) {
                    Game.soundsManager.currentMusic.pause();
                    this.setText(">");
                } else {
                    Game.soundsManager.currentMusic.play();
                    this.setText("||");
                }

            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Pause"), Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("NowPlaying") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text(Game.soundsManager.getCurrentMusicTittle(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        menuElements.add(new ButtonStatsRect_Active(">>", (int) ((float) menuWidth * 2 / 3), buttonY, (int) ((float) menuWidth * 1 / 3) - paddingLeft, CFG.BUTTON_HEIGHT4) {
            public void actionElement() {
                Game.soundsManager.loadNextMusic();
                menuElements.get(LRid).setText(Game.soundsManager.getCurrentMusicTittle());
                Text_Title_v2_TextLR lr = (Text_Title_v2_TextLR) menuElements.get(LRid);
                lr.sTextRight = Game.soundsManager.currentMusicDuraStr;
            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("Next"), Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("NowPlaying") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text(Game.soundsManager.getCurrentMusicTittle(), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new Slider(Game.lang.get("Progress") + ": ", paddingLeft, buttonY, (int) (menuWidth * 0.85f) - paddingLeft, CFG.BUTTON_HEIGHT4, 0, 100, (int) (Game.soundsManager.currentMusic.getPosition())) {
            int current = -1;

            public void actionElement() {
                current = iCurrent2;
                setCurrent2(iCurrent2);
            }

            public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
                if (current > -1) {
                    this.setCurrent(current);
                    Game.soundsManager.currentMusic.setPosition(Game.soundsManager.currentMusicDuration * (current / 100f));
                    current = -5;
                }
                synchronized (this) {
                    if (current < -1) {
                        current++;
                    }
                    if (current == -1) {
                        this.setCurrent2((int) (Game.soundsManager.currentMusic.getPosition() / Game.soundsManager.currentMusicDuration * 100f));
                    }
                }
                if(shouldRefresh){
                    menuElements.get(LRid).setText(Game.soundsManager.getCurrentMusicTittle());
                    Text_Title_v2_TextLR lr = (Text_Title_v2_TextLR) menuElements.get(LRid);
                    lr.sTextRight = Game.soundsManager.currentMusicDuraStr;
                    shouldRefresh = false;
                }
                this.drawSliderBG(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
                this.drawSliderText(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
                this.drawSliderBorder(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
                oSB.setColor(Color.WHITE);
            }

            public String getDrawText() {
                return super.getDrawText() + "%";
            }
        });
        menuElements.add(new ButtonGame(SoundsManager.getPlayModeShortStr(), (int) ((float) menuWidth * 0.9f), buttonY, (int) (menuWidth * 0.1f ) - CFG.PADDING, CFG.BUTTON_HEIGHT4) {
            public void actionElement() {
                SoundsManager.playMode++;
                if(SoundsManager.playMode > 2){
                    SoundsManager.playMode = 0;
                }
                this.setText(SoundsManager.getPlayModeShortStr());
            }

            public void buildElementHover() {
                List<MenuElement_HoverElement> nElements = new ArrayList();
                List<MenuElement_HoverElement_Type> nData = new ArrayList();
                nData.add(new MenuElement_HoverElement_Type_TextTitle_BG(Game.lang.get("SwitchPlayMode"), Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("PlayMode") + ": ", CFG.FONT_REGULAR_SMALL));
                nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get(SoundsManager.getPlayModeStr()), CFG.FONT_BOLD_SMALL, Colors.HOVER_GOLD));
                nElements.add(new MenuElement_HoverElement(nData));
                nData.clear();
                this.menuElementHover = new MenuElement_Hover(nElements);
            }
        });
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        menuElements.add(new Text_Title_v2_TextLR(Game.lang.get("Musics"), CFG.BUTTON_WIDTH / 4, Images.boxTitleBORDERWIDTH, buttonY, menuWidth - Images.boxTitleBORDERWIDTH * 2, CFG.TEXT_HEIGHT + CFG.PADDING * 4, ""));
        buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        for (int i = 0; i < Game.soundsManager.lTitles.size(); ++i) {
            menuElements.add(new ButtonStatsRect_Active_Value2(Game.soundsManager.lTitles.get(i).replace("_", " "), paddingLeft, buttonY, menuWidth - paddingLeft * 2, CFG.BUTTON_HEIGHT4, i) {
                public void actionElement() {
                    Game.soundsManager.loadNextMusic(Game.soundsManager.lTitles.get(this.getCurrent()), this.getCurrent());
                    menuElements.get(LRid).setText(Game.soundsManager.getCurrentMusicTittle());
                    Text_Title_v2_TextLR lr = (Text_Title_v2_TextLR) menuElements.get(LRid);
                    lr.sTextRight = Game.soundsManager.currentMusicDuraStr;
                }

                protected Color getColor(boolean isActive) {
                    return this.getCurrent() == Game.soundsManager.iCurrentMusicID ? Colors.HOVER_GOLD : super.getColor(isActive);
                }
            });
            buttonY += menuElements.get(menuElements.size() - 1).getHeight() + CFG.PADDING;
        }

        buttonY = 0;
        int i = 0;

        for (int iSize = menuElements.size(); i < iSize; ++i) {
            if (buttonY < menuElements.get(i).getPosY() + menuElements.get(i).getHeight() + CFG.PADDING * 2) {
                buttonY = menuElements.get(i).getPosY() + menuElements.get(i).getHeight() + CFG.PADDING * 2;
            }
        }

        i = Math.min(buttonY, CFG.GAME_HEIGHT - menuY - CFG.PADDING * 2);
        menuElements.add(new Empty(0, 0, menuWidth, Math.max(buttonY, i)));
        this.initMenu(new MenuTitleIMG(Game.lang.get("Audio"), true, false, Images.title500) {
            public long getTime() {
                return InGame_Audio.lTime;
            }
        }, CFG.GAME_WIDTH - menuWidth - CFG.GAME_HEIGHT / 8, CFG.GAME_HEIGHT / 8, menuWidth, i, menuElements, false, true);
        this.drawScrollPositionAlways = false;
    }

    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean menuIsActive, Status titleStatus) {
        if (lTime + 60L >= CFG.currentTimeMillis) {
            float progress = (CFG.currentTimeMillis - lTime) / 60.0F;
            iTranslateY = iTranslateY - CFG.BUTTON_HEIGHT + (int) ((float) CFG.BUTTON_HEIGHT * AnimationUtil.easeOut(progress));
        }

        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false, Images.insideTop500, Images.insideBot500);
        ImageManager.getImage(Images.rulerOver).draw2(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.rulerOver).getWidth() / 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.rulerOver).getHeight()));
        oSB.setColor(Color.WHITE);
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }

    public void setVisible(boolean visible) {
        super.setVisible(visible);
        lTime = CFG.currentTimeMillis;
    }
}
