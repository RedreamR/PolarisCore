//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.menu_element.textStatic;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.GlyphLayout_Game;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.menu_element.MenuElement_Type;
import aoc.kingdoms.lukasz.textures.Images;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import team.rainfall.finality.FinalityLogger;
import team.rainfall.fontFix.FontFix;
import team.rainfall.fontFix.TextSpliter;

import javax.xml.soap.Text;
import java.util.ArrayList;
import java.util.List;

public class Text_Desc extends Text_Static {
    public List<String> sLines = new ArrayList();
    public int iLineSize = 0;

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
        String[] words = TextSpliter.splitText(sText);
        int maxW = iWidth - CFG.PADDING * 2;
        int textPosX = 0;
        StringBuilder currentLine = new StringBuilder();
        int i = 0;
        int tTextWidth = 0;
        for(int iSize = words.length; i < iSize; ++i) {
            Renderer.glyphLayout.setText((BitmapFont)Renderer.fontMain.get(this.fontID),currentLine  + words[i]);
            textPosX = (int) Renderer.glyphLayout.width;
            if (textPosX < maxW && !words[i].isEmpty()) {
                currentLine.append(words[i]);
                this.iTextWidth = Math.max(this.iTextWidth, Math.min(textPosX, maxW));
            } else if(!words[i].isEmpty()){
                this.sLines.add(currentLine.toString());
                currentLine = new StringBuilder(words[i]);
                textPosX = tTextWidth;
            }
        }

        if (currentLine.length() > 0) {
            this.sLines.add(currentLine.toString());
        }

        GlyphLayout_Game glyphLayout;

        if (!this.sLines.isEmpty() && !((String) this.sLines.get(0)).isEmpty()) {
            glyphLayout = new GlyphLayout_Game();
            glyphLayout.setText((BitmapFont)Renderer.fontMain.get(this.fontID), (CharSequence)this.sLines.get(0));
            this.iTextHeight = (int)glyphLayout.height;
        } else {
            glyphLayout = new GlyphLayout_Game();
            glyphLayout.setText((BitmapFont)Renderer.fontMain.get(this.fontID), "ABC");
            this.iTextHeight = (int)glyphLayout.height;
        }

        this.iLineSize = this.sLines.size();

        for(i = 0; i < this.iLineSize; ++i) {
            glyphLayout = new GlyphLayout_Game();
            glyphLayout.setText((BitmapFont)Renderer.fontMain.get(this.fontID), (CharSequence)this.sLines.get(i));
            if (glyphLayout.width > (float)this.getWidth()) {
                this.setWidth((int)glyphLayout.width);
            }
        }

        this.setHeight(this.iTextHeight * this.sLines.size() + (this.sLines.size() - 1) * CFG.PADDING * 2 + this.getPaddingY() * 2);
    }

    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, getBoxAlpha(this.getClickable(), this.getIsHovered(), isActive)));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 0.8F);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT.r, Colors.COLOR_GRADIENT.g, Colors.COLOR_GRADIENT.b, 0.175F));
        Images.gradientFull.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(new Color(Colors.COLOR_GRADIENT.r, Colors.COLOR_GRADIENT.g, Colors.COLOR_GRADIENT.b, 0.3F));
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), false, true);
        oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.45F));
        Renderer.drawBox(oSB, Images.statsRectBGBorder, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0F);
        oSB.setColor(Color.WHITE);

        for(int i = 0; i < this.iLineSize; ++i) {
            Renderer.drawTextWithShadow(oSB, this.fontID, (String)this.sLines.get(i), this.getPosX() + this.getPadding() + iTranslateX, this.getPosY() + this.getPaddingY() + (this.iTextHeight + CFG.PADDING * 2) * i + iTranslateY, this.getColor(isActive));
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
