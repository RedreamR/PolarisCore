//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.menu_element.textStatic;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.GlyphLayout_Game;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.menu_element.MenuElement_Type;
import aoc.kingdoms.lukasz.textures.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import team.rainfall.finality.FinalityLogger;
import team.rainfall.fontFix.TextSplitter;
import team.rainfall.fontFix.text.Line;
import team.rainfall.fontFix.text.TextColorRenderer;
import team.rainfall.fontFix.text.TextProcessor;
import team.rainfall.fontFix.text.Word;

import java.util.ArrayList;
import java.util.List;

public class Text_Desc extends Text_Static {
    public ArrayList<Line> ffLines = new ArrayList<>();
    public boolean extraRender = true;

    public Text_Desc(String sText, int iPosX, int iPosY, int iWidth) {
        this.init(sText, iPosX, iPosY, iWidth, CFG.FONT_REGULAR_SMALL);
    }

    public Text_Desc(String sText, int iPosX, int iPosY, int iWidth, int nFontID) {
        this.init(sText, iPosX, iPosY, iWidth, nFontID);
    }

    public void init(String sText, int iPosX, int iPosY, int iWidth, int nFontID) {
        this.typeOfElement = MenuElement_Type.TEXT;
        this.fontID = nFontID;
        this.iTextPositionX = 0;
        this.setPosX(iPosX);
        this.setPosY(iPosY);
        this.setWidth(iWidth);
        this.updateTextPosition();
        int maxW = iWidth - CFG.PADDING * 2;
        this.ffLines = TextProcessor.warp(TextProcessor.tokenize(sText),maxW,nFontID);
        this.iTextWidth = (int) Math.max(maxW,ffLines.get(0).lineWidth);
        this.iTextHeight = (int) ffLines.get(0).lineHeight;
        for (Line ffLine : this.ffLines) {
           if(this.iTextHeight < ffLine.lineHeight){
               this.iTextHeight = (int) ffLine.lineHeight;
           }
        }
        this.setHeight(this.iTextHeight * this.ffLines.size() + (this.ffLines.size() - 1) * CFG.PADDING * 2 + this.getPaddingY() * 2);
    }

    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
        if(extraRender) {
            oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, getBoxAlpha(this.getClickable(), this.getIsHovered(), isActive)));
            Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 0.8F);
            oSB.setColor(new Color(Colors.COLOR_GRADIENT.r, Colors.COLOR_GRADIENT.g, Colors.COLOR_GRADIENT.b, 0.175F));
            Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
            oSB.setColor(new Color(Colors.COLOR_GRADIENT.r, Colors.COLOR_GRADIENT.g, Colors.COLOR_GRADIENT.b, 0.3F));
            Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), false, true);
            oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.45F));
            Renderer.drawBox(oSB, Images.statsRectBGBorder, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0F);
        }
        oSB.setColor(Color.WHITE);
        for(int i = 0; i < this.ffLines.size(); ++i) {
            TextColorRenderer.drawLine(oSB, this.fontID, this.ffLines.get(i), this.getPosX() + this.getPadding() + iTranslateX, this.getPosY() + this.getPaddingY() + (this.iTextHeight + CFG.PADDING * 2) * i + iTranslateY, this.getColor(isActive));
        }

    }

    public static final float getBoxAlpha(boolean clickable, boolean isHovered, boolean isActive) {
        return clickable ? (isActive ? 0.85F : (isHovered ? 0.7F : 0.5F)) : 0.2F;
    }

    protected Color getColor(boolean isActive) {
        if (isActive) {
            return Colors.BUTTON_TEXT_ACTIVE;
        } else if (this.getIsHovered()) {
            return Colors.BUTTON_TEXT_HOVERED;
        } else {
            return this.getClickable() ? Colors.BUTTON_TEXT : Colors.BUTTON_TEXT_DISABLED;
        }
    }

    public final int getPadding() {
        return CFG.PADDING * 2;
    }

    public final int getPaddingY() {
        return CFG.PADDING * 3;
    }
}
