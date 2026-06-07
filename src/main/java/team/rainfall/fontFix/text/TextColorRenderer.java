package team.rainfall.fontFix.text;

import aoc.kingdoms.lukasz.jakowski.GlyphLayout_Game;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.textures.ImageManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
 
import team.rainfall.fontFix.Config;
import team.rainfall.fontFix.config.ColorConfig;
import team.rainfall.fontFix.utils.ColorUtil;

public class TextColorRenderer {
    public static final boolean noColor = false;
    public static boolean noShadow = false;

    public static void drawLine(SpriteBatch oSB, int fontID, Line line, int nPosX, int nPosY, Color color) {
        drawLineCore(oSB, fontID, line, nPosX, nPosY, color, true, true, false);
    }

    // Only used in Text_Desc2_Special
    public static void drawLine2(SpriteBatch oSB, int fontID, Line line, int nPosX, int nPosY, Color color) {
        drawLineCore(oSB, fontID, line, nPosX, nPosY, color, false, false, false);
    }

    public static void drawLine_Hover(SpriteBatch oSB, int fontID, Line line, int nPosX, int nPosY, Color color) {
        drawLineCore(oSB, fontID, line, nPosX, nPosY, color, true, false, true);
    }

    /**
     * Draw a line
     * @param oSB         SpriteBatch
     * @param fontID      Font ID
     * @param line        文本行对象
     * @param nPosX       绘制X坐标
     * @param nPosY       绘制Y坐标
     * @param color       默认颜色（fallback）
     * @param enableImage 是否检测并绘制内嵌图片（如 "§X"）
     * @param respectShadow 是否按阴影状态合并文本段，并且动态选择带/不带阴影的绘制方法
     * @param forceShadow 是否强制使用阴影绘制（覆盖 respectShadow 中的阴影决策）
     */
    private static void drawLineCore(SpriteBatch oSB, int fontID, Line line, int nPosX, int nPosY,
                                     Color color, boolean enableImage, boolean respectShadow, boolean forceShadow) {
        // 1. 图片处理（仅当 enableImage 为 true 时）
        if (enableImage && line.words.size() == 1) {
            Word firstWord = line.words.get(0);
            String s = firstWord.string;
            if (s.startsWith("§") && s.length() == 3) {
                char imageChar = s.charAt(2);
                PicInText_Pool.loadImage(imageChar).draw(oSB, nPosX, nPosY, (int) line.lineWidth, (int) line.lineHeight);
                return;
            }
        }

        // 2. 正常文本绘制
        StringBuilder sameFormatLine = new StringBuilder();
        GlyphLayout_Game glyphLayout = new GlyphLayout_Game();
        int nowX = 0;

        char prevColor = 0;
        boolean prevShadow = false;   // 仅当 respectShadow 为 true 时有效
        boolean currentShadow;

        for (int i = 0; i < line.words.size(); i++) {
            Word word = line.words.get(i);
            char currentColorSign = word.colorSign;
            currentShadow = (word.colorSign == '_'); // '_' 表示无阴影，否则为有阴影

            // 判断是否与上一段文本合并
            boolean canMerge;
            if (respectShadow) {
                canMerge = (i > 0 && prevColor == currentColorSign && prevShadow == currentShadow);
            } else {
                canMerge = (i > 0 && prevColor == currentColorSign);
            }

            if (canMerge) {
                sameFormatLine.append(word.string);
            } else {
                // 绘制上一段累积的文本
                if (sameFormatLine.length() > 0) {
                    glyphLayout.setText(Renderer.fontMain.get(fontID), sameFormatLine.toString());
                    Color actualColor = getColor(prevColor, color);

                    boolean shouldDrawShadow;
                    if (forceShadow) {
                        shouldDrawShadow = true;      // 强制阴影模式
                    } else if (respectShadow) {
                        shouldDrawShadow = !prevShadow; // prevShadow == false 表示有阴影（因为 '_' 代表无阴影）
                    } else {
                        shouldDrawShadow = false;     // 简化模式：无阴影
                    }

                    if (shouldDrawShadow) {
                        Renderer.drawTextWithShadow(oSB, fontID, sameFormatLine.toString(), nPosX + nowX, nPosY, actualColor);
                    } else {
                        Renderer.drawText(oSB, fontID, sameFormatLine.toString(), nPosX + nowX, nPosY, actualColor);
                    }
                    nowX += (int) glyphLayout.width;
                }
                // 开始新段落
                sameFormatLine = new StringBuilder(word.string);
                prevColor = currentColorSign;
                prevShadow = currentShadow;
            }
        }

        // 绘制最后一段
        if (sameFormatLine.length() > 0) {
            Color actualColor = getColor(prevColor, color);
            boolean shouldDrawShadow;
            if (forceShadow) {
                shouldDrawShadow = true;
            } else if (respectShadow) {
                shouldDrawShadow = !prevShadow;
            } else {
                shouldDrawShadow = false;
            }

            if (shouldDrawShadow) {
                Renderer.drawTextWithShadow(oSB, fontID, sameFormatLine.toString(), nPosX + nowX, nPosY, actualColor);
            } else {
                Renderer.drawText(oSB, fontID, sameFormatLine.toString(), nPosX + nowX, nPosY, actualColor);
            }
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
