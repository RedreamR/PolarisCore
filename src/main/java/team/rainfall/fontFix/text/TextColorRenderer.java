package team.rainfall.fontFix.text;

import aoc.kingdoms.lukasz.jakowski.GlyphLayout_Game;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.textures.ImageManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import team.rainfall.finality.FinalityLogger;
import team.rainfall.fontFix.Config;
import team.rainfall.fontFix.config.ColorConfig;
import team.rainfall.fontFix.utils.ColorUtil;

public class TextColorRenderer {
    public static final boolean noColor = false;
    public static boolean noShadow = false;

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
    public static void drawLine3(SpriteBatch oSB, int fontID, Line line, int nPosX, int nPosY, Color color) {
        StringBuilder sameFormatLine = new StringBuilder();
        GlyphLayout_Game glyphLayout = new GlyphLayout_Game();
        int nowX = 0;

        // 记录上一个单词的颜色和阴影状态，只有两者都相同时才合并
        char prevColor = 0;
        boolean prevShadow = false; // 初始阴影状态，将在第一个单词处确定
        boolean currentShadow;
        for (int i = 0; i < line.words.size(); i++) {
            Word word = line.words.get(i);
            char currentColorSign = word.colorSign;
            currentShadow = (line.words.get(i).colorSign == '_'); // 无阴影
            // 2. 检查是否可以与上一段文本合并（颜色和阴影状态都相同）
            if (i > 0 && prevColor == currentColorSign && (prevShadow == currentShadow)) {
                sameFormatLine.append(word.string);
            } else {
                // 如果不能合并，先绘制之前累积的文本
                if (sameFormatLine.length() > 0) {
                    glyphLayout.setText(Renderer.fontMain.get(fontID), sameFormatLine.toString());
                    Color actualColor = getColor(prevColor, color);

                    if (prevShadow) {
                        Renderer.drawText(oSB, fontID, sameFormatLine.toString(), nPosX + nowX, nPosY, actualColor);
                    } else {
                        Renderer.drawTextWithShadow(oSB, fontID, sameFormatLine.toString(), nPosX + nowX, nPosY, actualColor);
                    }
                    nowX += (int) glyphLayout.width;
                }
                // 开始累积新的、不同格式的文本
                sameFormatLine = new StringBuilder(word.string);
            }

            // 3. 更新“上一个”的状态，为下一次循环做准备
            prevColor = currentColorSign;
            prevShadow = currentShadow;
        }

        // 4. 循环结束后，绘制最后一段累积的文本
        if (sameFormatLine.length() > 0) {
            Color actualColor = getColor(prevColor, color);
            if (prevShadow) {
                Renderer.drawText(oSB, fontID, sameFormatLine.toString(), nPosX + nowX, nPosY, actualColor);
            } else {
                Renderer.drawTextWithShadow(oSB, fontID, sameFormatLine.toString(), nPosX + nowX, nPosY, actualColor);
            }
        }
    }
    public static void drawLine_Hover(SpriteBatch oSB, int fontID, Line line, int nPosX, int nPosY, Color color){
        noShadow = false;
        StringBuilder sameColorLine = new StringBuilder();
        GlyphLayout_Game glyphLayout = new GlyphLayout_Game();
        int nowX = 0;
        char prevColor = 0;
        if(line.words.size() == 1 && line.words.get(0).string.startsWith("§") && line.words.get(0).string.length() == 3){
            String s = line.words.get(0).string;
            PicInText_Pool.loadImage(s.charAt(2)).draw(oSB,nPosX,nPosY,(int)line.lineWidth,(int) line.lineHeight);
            return;
        }
        for (int i = 0;i < line.words.size();i++){
            Word word = line.words.get(i);

            if(prevColor == word.colorSign) {
                sameColorLine.append(line.words.get(i).string);
            }else {
                if(sameColorLine.length() > 0) {
                    glyphLayout.setText(Renderer.fontMain.get(fontID),sameColorLine.toString());
                    if(noShadow){
                        Renderer.drawText(oSB, fontID, sameColorLine.toString(), nPosX + nowX, nPosY, getColor(prevColor, color));
                    }else {
                        Renderer.drawTextWithShadow(oSB, fontID, sameColorLine.toString(), nPosX + nowX, nPosY, getColor(prevColor, color));
                    }
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
    public static void drawLine(SpriteBatch oSB, int fontID, Line line, int nPosX, int nPosY, Color color){
        if(Config.getConfig().useNewTextDraw){
            drawLine3(oSB, fontID, line, nPosX, nPosY, color);
            return;
        }
        noShadow = false;
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
                    if(noShadow){
                        Renderer.drawText(oSB, fontID, sameColorLine.toString(), nPosX + nowX, nPosY, getColor(prevColor, color));
                    }else {
                        Renderer.drawTextWithShadow(oSB, fontID, sameColorLine.toString(), nPosX + nowX, nPosY, getColor(prevColor, color));
                    }
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
        if (color == '_') {
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
