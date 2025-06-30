package team.rainfall.fontFix.text;

import aoc.kingdoms.lukasz.jakowski.GlyphLayout_Game;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.menu.Colors;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import team.rainfall.finality.FinalityLogger;
import team.rainfall.fontFix.Config;
import team.rainfall.fontFix.config.ColorConfig;
import team.rainfall.fontFix.utils.ColorUtil;

public class TextColorRenderer {
    public static final boolean noColor = false;
    public static void drawLine2(SpriteBatch oSB, int fontID, Line line, int nPosX, int nPosY, Color color){
        StringBuilder sameColorLine = new StringBuilder();
        GlyphLayout_Game glyphLayout = new GlyphLayout_Game();
        int nowX = 0;
        char prevColor = 0;
        for (int i = 0;i < line.words.size();i++){
            Word word = line.words.get(i);

            if(prevColor == word.colorSign) {
                sameColorLine.append(line.words.get(i).string);
            }else {
                if(sameColorLine.length() > 0) {
                    glyphLayout.setText(Renderer.fontMain.get(fontID),sameColorLine.toString());

                    Renderer.drawText(oSB, fontID, sameColorLine.toString(), nPosX + nowX, nPosY, getColor(prevColor,color));
                    nowX += (int) glyphLayout.width;
                }
                sameColorLine = new StringBuilder(word.string);
            }
            prevColor = word.colorSign;
        }
        if(sameColorLine.length() > 0) {

            Renderer.drawText(oSB, fontID, sameColorLine.toString(), nPosX + nowX, nPosY, getColor(prevColor,color));
        }
    }
    public static void drawLine(SpriteBatch oSB, int fontID, Line line, int nPosX, int nPosY, Color color){
        StringBuilder sameColorLine = new StringBuilder();
        GlyphLayout_Game glyphLayout = new GlyphLayout_Game();
        int nowX = 0;
        char prevColor = 0;
        for (int i = 0;i < line.words.size();i++){
            Word word = line.words.get(i);

            if(prevColor == word.colorSign) {
                sameColorLine.append(line.words.get(i).string);
            }else {
                if(sameColorLine.length() > 0) {
                    glyphLayout.setText(Renderer.fontMain.get(fontID),sameColorLine.toString());

                    Renderer.drawTextWithShadow(oSB, fontID, sameColorLine.toString(), nPosX + nowX, nPosY, getColor(prevColor,color));
                    nowX += (int) glyphLayout.width;
                }
                sameColorLine = new StringBuilder(word.string);
            }
            prevColor = word.colorSign;
        }
        if(sameColorLine.length() > 0) {

            Renderer.drawTextWithShadow(oSB, fontID, sameColorLine.toString(), nPosX + nowX, nPosY, getColor(prevColor,color));
        }
    }
    public static Color getColor(char color,Color fallback){
        if(noColor){
            return fallback;
        }
        if(color == '0'){
            return fallback;
        }
        for (ColorConfig textColor : Config.getConfig().textColors) {
            if(textColor.sign == color){
                if(fallback == Colors.BUTTON_TEXT_DISABLED){
                    return ColorUtil.getDisabledColor(Color.valueOf(textColor.color));
                }
                if(fallback == Colors.BUTTON_TEXT_HOVERED){
                    return ColorUtil.getHoveredColor(Color.valueOf(textColor.color));
                }
                return Color.valueOf(textColor.color);
            }
        }
        return fallback;
    }

}
