package aoc.kingdoms.lukasz.menus;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.FileManager;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.GlyphLayout_Game;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.textures.Image;
import aoc.kingdoms.lukasz.textures.ImageManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static aoc.kingdoms.lukasz.jakowski.Renderer.Renderer.*;
import static aoc.kingdoms.lukasz.menus.InitGame.*;

public class HoI4_InitGame {
    private static int progress1 = 0;
    private static int progress2 = 0;
    private static int loadingStatus = 0;
    private static int loadingTip = 0;
    private float scale = 0.8f;
    private int h = 0;

    private int w2 = 0;
    public static boolean available(){
        return FileManager.loadFile("gfx/loading/Progress_1.png").exists();
    }
    public HoI4_InitGame() {
        progress1 = ImageManager.addImage("gfx/loading/Progress_1.png");
        progress2 = ImageManager.addImage("gfx/loading/Progress_2.png");
        loadingStatus = ImageManager.addImage("gfx/loading/loadingStatus.png");
        loadingTip = ImageManager.addImage("gfx/loading/loadingTip.png");
    }

    public void render(SpriteBatch oSB, int iTranslateX, int iTranslateY) {
        if (CFG.currentTimeMillis - 4000L > loadingTime) {
            Renderer.sLoadingText = Game.lang.getLoading("L" + Game.oR.nextInt(Game.lang.iLoading_NumOfTexts)) + "..";
        }
        try {
            loadingTime = CFG.currentTimeMillis;
            GlyphLayout_Game glyphLayout = new GlyphLayout_Game();
            glyphLayout.setText(Renderer.fontMain.get(CFG.FONT_REGULAR), Renderer.sLoadingText);
            iLoadingTextWidth = (int) glyphLayout.width;
            glyphLayout.setText(fontMain.get(CFG.FONT_REGULAR), loadingName);
            w2 = (int) glyphLayout.width;
        } catch (Exception ignored) {

        }

        InitGame.background.draw(oSB, iTranslateX + (CFG.GAME_WIDTH - backgroundWidth) / 2, iTranslateY + (CFG.GAME_HEIGHT - backgroundHeight) / 2, backgroundWidth, backgroundHeight);
        Image loadingStatusImg = ImageManager.getImage(loadingStatus);
        int width = (int) (CFG.GAME_WIDTH * scale);
        float scale2 = (float) width / loadingStatusImg.getWidth();
        h = (int) (loadingStatusImg.getHeight() * scale2);
        loadingStatusImg.draw(oSB, iTranslateX + (CFG.GAME_WIDTH - width) / 2, iTranslateY, scale2);
        Renderer.drawText(oSB, CFG.FONT_REGULAR, InitGame.loadingName, iTranslateX + CFG.GAME_WIDTH / 2 - w2 / 2, (int) (iTranslateY - CFG.TEXT_HEIGHT * 2 + loadingStatusImg.getHeight() * scale2 * 0.5f), Color.WHITE);
        Image loadingTipImg = ImageManager.getImage(loadingTip);
        width = (int) (CFG.GAME_WIDTH * scale);
        scale2 = (float) width / loadingTipImg.getWidth();
        loadingTipImg.draw(oSB, iTranslateX + (CFG.GAME_WIDTH - width) / 2, (int) (iTranslateY + CFG.GAME_HEIGHT - loadingTipImg.getHeight() * scale2), scale2);


        Renderer.drawText(oSB, CFG.FONT_REGULAR, sLoadingText, iTranslateX + CFG.GAME_WIDTH / 2 - iLoadingTextWidth / 2, (int) (iTranslateY + CFG.GAME_HEIGHT + CFG.TEXT_HEIGHT - loadingTipImg.getHeight() * scale2 * 0.5f), Color.WHITE);
    }

    public void drawLoading(SpriteBatch oSB, int iTranslateX, int iTranslateY, float nProgress) {
        nProgress = 0.95f * nProgress;
        Image image = ImageManager.getImage(progress1);
        Image loadingStatusImg = ImageManager.getImage(loadingStatus);
        int width0 = (int) (CFG.GAME_WIDTH * scale);
        float scale0 = (float) width0 / loadingStatusImg.getWidth();

        int width = (int) (CFG.GAME_WIDTH * scale  - 470 * scale0);
        float scale2 = (float) width / image.getWidth();
        int height = (int) (image.getHeight() * scale2);
        int h2 = (int) (h - 28 * scale0);
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(progress1).draw2_Scale(oSB, iTranslateX + CFG.GAME_WIDTH / 2 - width / 2, iTranslateY + h2, width, height, scale2);
        ImageManager.getImage(progress2).draw2_Scale(oSB, iTranslateX + CFG.GAME_WIDTH / 2 - width / 2, iTranslateY + h2, (int) (width * nProgress), height, scale2);
    }

}
