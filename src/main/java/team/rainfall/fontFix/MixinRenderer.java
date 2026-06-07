package team.rainfall.fontFix;

import aoc.kingdoms.lukasz.jakowski.*;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.menus.InitGame;
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.textures.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import team.rainfall.finality.luminosity2.CallbackInfo;
import team.rainfall.finality.luminosity2.annotations.Inject;
import team.rainfall.finality.luminosity2.annotations.Mixin;
import team.rainfall.fontFix.utils.MusicPool;

import javax.security.auth.callback.Callback;
import java.util.List;

import static aoc.kingdoms.lukasz.jakowski.Renderer.Renderer.*;

@SuppressWarnings("unused")
@Mixin(mixinClass = "aoc.kingdoms.lukasz.jakowski.Renderer.Renderer")
public class MixinRenderer {
    public SpriteBatch oSB;
    public static List<BitmapFont> fontMain;
    public static int fontMainSize;
    public static List<BitmapFont> fontBorder;
    public static int fontBorderSize;

    @Inject(methodName = "render")
    public void inject$render(CallbackInfo callbackInfo) {
        MusicPool.POOL.refresh();
    }

    //Shadow Font Rendering
    public static void drawTextWithShadow(SpriteBatch oSB, String sText, int nPosX, int nPosY, Color color) {
        drawTextWithShadow(oSB, 0, sText, nPosX, nPosY, color);
    }

    public static void drawTextWithShadow(SpriteBatch oSB, int fontID, String sText, int nPosX, int nPosY, Color color) {
        try {
            if (sText != null) {
                fontMain.get(fontID).setColor(new Color(0.0F, 0.0F, 0.0F, 0.7F));
                fontMain.get(fontID).draw(oSB, sText, (float)(nPosX - 1), (float)(-nPosY - 1));
                fontMain.get(fontID).setColor(color);
                fontMain.get(fontID).draw(oSB, sText, (float)nPosX, (float)(-nPosY));
            }
        } catch (Exception ignored) {
        }

    }

    public static void drawTextWithShadowScale(SpriteBatch oSB, int fontID, String sText, int nPosX, int nPosY, Color color, float fScale) {
        try {
            if (sText != null) {
                fontMain.get(fontID).getData().setScale(fScale);
                fontMain.get(fontID).setColor(new Color(0.0F, 0.0F, 0.0F, 0.7F));
                fontMain.get(fontID).draw(oSB, sText, (float)(nPosX - 1), (float)(-nPosY - 1));
                fontMain.get(fontID).setColor(color);
                fontMain.get(fontID).draw(oSB, sText, (float)nPosX, (float)(-nPosY));
                fontMain.get(fontID).getData().setScale(1.0F);
            }
        } catch (Exception ignored) {
        }

    }

    public static void drawTextWithShadowRotated(SpriteBatch oSB, String sText, int nPosX, int nPosY, Color color, float rotate) {
        drawTextWithShadowRotated(oSB, 0, sText, nPosX, nPosY, color, rotate);
    }

    public static void drawTextWithShadowRotated(SpriteBatch oSB, int fontID, String sText, int nPosX, int nPosY, Color color, float rotate) {
        if (sText != null) {
            Matrix4 oldTransformMatrix = oSB.getTransformMatrix().cpy();
            try {
                Matrix4 mx4Font = new Matrix4();
                mx4Font.rotate(textRotatedVector3, rotate);
                mx4Font.setTranslation((float)nPosX, (float)(-nPosY), 0.0F);
                oSB.setTransformMatrix(mx4Font);
                fontMain.get(fontID).setColor(new Color(0.0F, 0.0F, 0.0F, 0.7F));
                fontMain.get(fontID).draw(oSB, sText, -1.0F, -1.0F);
                fontMain.get(fontID).setColor(color);
                fontMain.get(fontID).draw(oSB, sText, 0.0F, 0.0F);
            } catch (Exception ignored) {
            } finally {
                oSB.setTransformMatrix(oldTransformMatrix);
            }
        }

    }




    public void dispose() {
        this.oSB.dispose();
        for (int i = 0; i < fontMain.size(); ++i) {
            fontMain.get(i).dispose();
        }

        for (int i = 0; i < fontBorder.size(); ++i) {
            fontBorder.get(i).dispose();
        }

    }

    public static void loadFont(String sFont, String charset, int fontSize) {
        float texSize = charset.getBytes().length;
        int texSize2 = (int) (texSize * ((float) 2 / 3) + 1024);
        FreeTypeFontGenerator.setMaxTextureSize(texSize2);
        if (!CFG.isDesktop() && !FontFix.getDI()) {
            FreeTypeFontGenerator.setMaxTextureSize(Config.getConfig().extendCharset ? 8192 : 4096);
        }
        FreeTypeFontGenerator generator;
        if (fontSize < 0) {
            fontSize = (int) ((float) GameValues.value.DEFAULT_FONT_SIZE * CFG.GUI_SCALE);
        }

        try {
            generator = new FreeTypeFontGenerator(FileManager.loadFile("game/fonts/" + sFont));
        } catch (Exception var5) {
            generator = new FreeTypeFontGenerator(FileManager.loadFile("game/fonts/Roboto-Bold.ttf"));
        }

        FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();

        if (CFG.isDesktop() && !FontFix.getDI()) {
            params.characters = charset;
            params.incremental = false;
        } else {
            params.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!.?";
            params.incremental = true;
        }
        params.size = Math.max(fontSize, 6);
        params.color = FontFix.readFontColor("FontColor");
        params.minFilter = Texture.TextureFilter.Linear;
        params.magFilter = Texture.TextureFilter.Linear;
        fontMain.add(generator.generateFont(params));
        fontMainSize = fontMain.size();
        if (CFG.isDesktop() && !FontFix.getDI()) {
            generator.dispose();
        }
    }

    public static void loadFontBorder(String sFont, String charset) {
        float texSize = charset.getBytes().length;
        int texSize2 = (int) (texSize * ((float) 2 / 3) + 1024);
        FreeTypeFontGenerator.setMaxTextureSize(texSize2);
        if (!CFG.isDesktop()) FreeTypeFontGenerator.setMaxTextureSize(4096);
        FreeTypeFontGenerator generator = null;

        try {
            generator = new FreeTypeFontGenerator(FileManager.loadFile("game/fonts/" + sFont));
        } catch (Exception var4) {
            generator = new FreeTypeFontGenerator(FileManager.loadFile("game/fonts/Roboto-Bold.ttf"));
        }

        FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();
        if (CFG.isDesktop()) {
            params.characters = charset;
            params.incremental = false;
        } else {
            params.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!.?";
            params.incremental = true;
        }
        params.size = Game.settingsManager.FONT_BORDER_SIZE;
        params.color = new Color(Game.settingsManager.civNamesFontColor_R, Game.settingsManager.civNamesFontColor_G, Game.settingsManager.civNamesFontColor_B, Game.settingsManager.civNamesFontColor_A);
        params.minFilter = Texture.TextureFilter.Linear;
        params.magFilter = Texture.TextureFilter.Linear;
        params.kerning = false;
        params.borderColor = new Color(Game.settingsManager.civNamesFontColorBorder_R, Game.settingsManager.civNamesFontColorBorder_G, Game.settingsManager.civNamesFontColorBorder_B, Game.settingsManager.civNamesFontColorBorder_A);
        params.borderWidth = (float) Game.settingsManager.FONT_BORDER_WIDTH_OF_BORDER;
        fontBorder.add(generator.generateFont(params));
        fontBorderSize = fontBorder.size();
        fontBorder.get(0).setFixedWidthGlyphs(charset);
        if (CFG.isDesktop()) {
            generator.dispose();
        }
    }

    public static void drawLoading(SpriteBatch oSB, int iTranslateX, int iTranslateY, float nProgress) {
        int nHeight = ImageManager.getImage(Images.logo).getHeight() + CFG.BUTTON_HEIGHT * 2;
        if (CFG.currentTimeMillis - 4000L > loadingTime) {
            try {
                sLoadingText = Game.lang.getLoading("L" + Game.oR.nextInt(Game.lang.iLoading_NumOfTexts)) + "..";
                loadingTime = CFG.currentTimeMillis;
                GlyphLayout_Game glyphLayout = new GlyphLayout_Game();
                glyphLayout.setText(fontMain.get(CFG.FONT_REGULAR), sLoadingText);
                iLoadingTextWidth = (int) glyphLayout.width;
                if (Config.getConfig().changeBGinInitGame) {
                    InitGame.loadBackground();
                }
            } catch (Exception ex) {
                CFG.exceptionStack(ex);
            }
        }
        if(Config.getGradientConfig().loading < 2) {
            oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F));
            Images.gradientFull.draw(oSB, iTranslateX, iTranslateY + CFG.GAME_HEIGHT - CFG.TEXT_HEIGHT - CFG.PADDING * 11, CFG.GAME_WIDTH, CFG.TEXT_HEIGHT + CFG.PADDING * 6);
        }
        if(Config.getGradientConfig().loading < 5) {
            oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.4F));
            Images.gradientXY.draw(oSB, iTranslateX + CFG.GAME_WIDTH / 2 - (iLoadingTextWidth + CFG.PADDING * 6) / 2, iTranslateY + CFG.GAME_HEIGHT - CFG.TEXT_HEIGHT - CFG.PADDING * 11, iLoadingTextWidth + CFG.PADDING * 6, CFG.TEXT_HEIGHT + CFG.PADDING * 6);
            oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F));
            Images.gradientFull.draw(oSB, iTranslateX, iTranslateY + CFG.GAME_HEIGHT - CFG.TEXT_HEIGHT - CFG.PADDING * 11 + 1, CFG.GAME_WIDTH, 1);
            Images.gradientFull.draw(oSB, iTranslateX, iTranslateY + CFG.GAME_HEIGHT - CFG.TEXT_HEIGHT - CFG.PADDING * 11 + CFG.TEXT_HEIGHT + CFG.PADDING * 6 - 2, CFG.GAME_WIDTH, 1);
        }
        oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.65F));
        drawText(oSB, CFG.FONT_REGULAR, sLoadingText, iTranslateX + CFG.GAME_WIDTH / 2 - iLoadingTextWidth / 2, iTranslateY + CFG.GAME_HEIGHT - CFG.TEXT_HEIGHT - CFG.PADDING * 8, new Color(Colors.COLOR_LOGO.r, Colors.COLOR_LOGO.g, Colors.COLOR_LOGO.b, 0.75F));
        oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.15F));
        ImageManager.getImage(Images.logo).draw(oSB, iTranslateX + CFG.GAME_WIDTH / 2 - ImageManager.getImage(Images.logo).getWidth() / 2, iTranslateY + CFG.GAME_HEIGHT - CFG.TEXT_HEIGHT - CFG.PADDING * 14 - ImageManager.getImage(Images.logo).getHeight());
        oSB.setColor(Color.WHITE);
        ImageManager.getImage(Images.logo).draw2(oSB, iTranslateX + CFG.GAME_WIDTH / 2 - ImageManager.getImage(Images.logo).getWidth() / 2, iTranslateY + CFG.GAME_HEIGHT - CFG.TEXT_HEIGHT - CFG.PADDING * 14 - ImageManager.getImage(Images.logo).getHeight(), Math.min(ImageManager.getImage(Images.logo).getWidth(), (int) ((float) ImageManager.getImage(Images.logo).getWidth() * nProgress)), ImageManager.getImage(Images.logo).getHeight());
    }
}
