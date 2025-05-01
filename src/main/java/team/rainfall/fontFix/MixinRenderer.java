package team.rainfall.fontFix;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.FileManager;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import team.rainfall.finality.FinalityLogger;
import team.rainfall.finality.luminosity2.annotations.Mixin;

import java.util.List;

@Mixin(mixinClass = "aoc.kingdoms.lukasz.jakowski.Renderer.Renderer")
public class MixinRenderer {

    public static List<BitmapFont> fontMain;
    public static int fontMainSize;
    public static List<BitmapFont> fontBorder;
    public static int fontBorderSize;

    public static final void clearFonts() {
        FontFix.fonts.clear();
        for(int i = 0; i < fontMainSize; ++i) {
            ((BitmapFont)fontMain.get(i)).dispose();
            fontMain.set(i, null);
        }

        fontMain.clear();
        fontMainSize = 0;
    }

    public static final void loadFont(String sFont, String charset, int fontSize) {
        float texSize = charset.getBytes().length;
        int texSize2 = (int) (texSize * ((float) 2 / 3) + 1024);
        FinalityLogger.debug("FontFix.textureSize = " + texSize2);
        FreeTypeFontGenerator.setMaxTextureSize(texSize2);
        if(!CFG.isDesktop()) FreeTypeFontGenerator.setMaxTextureSize(4096);
        FreeTypeFontGenerator generator = null;
        if (fontSize < 0) {
            fontSize = (int)((float) GameValues.value.DEFAULT_FONT_SIZE * CFG.GUI_SCALE);
        }

        try {
            generator = new FreeTypeFontGenerator(FileManager.loadFile("game/fonts/" + sFont));
        } catch (Exception var5) {
            generator = new FreeTypeFontGenerator(FileManager.loadFile("game/fonts/Roboto-Bold.ttf"));
        }

        FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();

        if(CFG.isDesktop()){
            params.characters = charset;
            params.incremental = false;
        }else {
            params.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!.?";
            params.incremental = true;
        }
        params.size = Math.max(fontSize, 6);
        params.color = FontFix.readFontColor("FontColor");
        params.minFilter = Texture.TextureFilter.Linear;
        params.magFilter = Texture.TextureFilter.Linear;
        fontMain.add(generator.generateFont(params));
        fontMainSize = fontMain.size();
        if(CFG.isDesktop()) {
            generator.dispose();
        }
    }

    public static final void loadFontBorder(String sFont, String charset) {
        float texSize = charset.getBytes().length;
        int texSize2 = (int) (texSize * ((float) 2 / 3) + 1024);
        FinalityLogger.debug("FontFix.textureSize = " + texSize2);
        FreeTypeFontGenerator.setMaxTextureSize(texSize2);
        if(!CFG.isDesktop()) FreeTypeFontGenerator.setMaxTextureSize(4096);
        FreeTypeFontGenerator generator = null;

        try {
            generator = new FreeTypeFontGenerator(FileManager.loadFile("game/fonts/" + sFont));
        } catch (Exception var4) {
            generator = new FreeTypeFontGenerator(FileManager.loadFile("game/fonts/Roboto-Bold.ttf"));
        }

        FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();
        if(CFG.isDesktop()){
            params.characters = charset;
            params.incremental = false;
        }else {
            params.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!.?";
            params.incremental = true;
        }
        params.size = Game.settingsManager.FONT_BORDER_SIZE;
        params.color = new Color(Game.settingsManager.civNamesFontColor_R, Game.settingsManager.civNamesFontColor_G, Game.settingsManager.civNamesFontColor_B, Game.settingsManager.civNamesFontColor_A);
        params.minFilter = Texture.TextureFilter.Linear;
        params.magFilter = Texture.TextureFilter.Linear;
        params.kerning = false;
        params.borderColor = new Color(Game.settingsManager.civNamesFontColorBorder_R, Game.settingsManager.civNamesFontColorBorder_G, Game.settingsManager.civNamesFontColorBorder_B, Game.settingsManager.civNamesFontColorBorder_A);
        params.borderWidth = (float)Game.settingsManager.FONT_BORDER_WIDTH_OF_BORDER;
        fontBorder.add(generator.generateFont(params));
        fontBorderSize = fontBorder.size();
        ((BitmapFont)fontBorder.get(0)).setFixedWidthGlyphs(charset);
        if(CFG.isDesktop()) {
            generator.dispose();
        }
    }

}
