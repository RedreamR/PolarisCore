//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.menu_element.menuElementHover;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.GlyphLayout_Game;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.textures.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import team.rainfall.finality.FinalityLogger;
import team.rainfall.fontFix.FontFix;
import team.rainfall.fontFix.TextSpliter;

import java.util.ArrayList;
import java.util.List;

public class MenuElement_HoverElement_Type_Text_Desc implements MenuElement_HoverElement_Type {
    public List<String> sLines = new ArrayList();
    public int iLineSize = 0;
    public int iTextWidth = 0;
    public int iTextHeight = 0;
    private Color oColor;
    private int fontID = 0;

    public MenuElement_HoverElement_Type_Text_Desc(String sText) {
        this.init(sText, CFG.FONT_BOLD_SMALL, Colors.HOVER_LEFT);
    }

    public MenuElement_HoverElement_Type_Text_Desc(String sText, int fontID) {
        this.init(sText, fontID, Colors.HOVER_LEFT);
    }

    public MenuElement_HoverElement_Type_Text_Desc(String sText, Color nColor) {
        this.init(sText, CFG.FONT_BOLD_SMALL, nColor);
    }

    public MenuElement_HoverElement_Type_Text_Desc(String sText, int fontID, Color nColor) {
        this.init(sText, fontID, nColor);
    }

    public final void init(String sText, int nFontID, Color oColor) {
        this.oColor = oColor;
        this.fontID = nFontID;
        String[] words = TextSpliter.splitText(sText);
        int maxW = (int)((float) ImageManager.getImage(Images.title1Red).getWidth() * 0.85f);
        int textPosX = 0;

        StringBuilder currentLine = new StringBuilder();
        int tTextWidth = 0;
        int i = 0;

        for(int iSize = words.length; i < iSize; ++i) {
            Renderer.glyphLayout.setText((BitmapFont)Renderer.fontMain.get(this.fontID), words[i]);
            tTextWidth = (int)Renderer.glyphLayout.width;
            textPosX += tTextWidth;
            if (textPosX < maxW && !words[i].isEmpty()) {
                currentLine.append(words[i]);
                this.iTextWidth = Math.max(this.iTextWidth, Math.min(textPosX, maxW));
            } else if(!words[i].isEmpty()){
                FinalityLogger.debug("DESC "+currentLine);
                this.sLines.add(currentLine.toString());
                currentLine = new StringBuilder(words[i]);
                textPosX = tTextWidth;
            }
        }
        int tempHeight = 0;
        if (currentLine.length() > 0) {
            Renderer.glyphLayout.setText(Renderer.fontMain.get(this.fontID), currentLine.toString());
            tempHeight = (int)Renderer.glyphLayout.height;
            FinalityLogger.debug("DESC_F "+currentLine);
            this.sLines.add(currentLine.toString());
        }

        if (!this.sLines.isEmpty() && !((String) this.sLines.get(0)).isEmpty()) {
            Renderer.glyphLayout.setText((BitmapFont)Renderer.fontMain.get(this.fontID), (CharSequence)this.sLines.get(0));
            this.iTextHeight = (int)Renderer.glyphLayout.height;
        }

        if(!this.sLines.isEmpty() && ((String) this.sLines.get(0)).isEmpty() && !this.sLines.get(0).isEmpty()){
            this.sLines.remove(0);
            Renderer.glyphLayout.setText((BitmapFont)Renderer.fontMain.get(this.fontID), (CharSequence)this.sLines.get(0));
            this.iTextHeight = (int)(Renderer.glyphLayout.height);
        }
        if(this.iTextHeight < tempHeight ){
            iTextHeight = tempHeight;
        }
        this.iLineSize = this.sLines.size();

        for(i = 0; i < this.iLineSize; ++i) {
            GlyphLayout_Game glyphLayout = new GlyphLayout_Game();
            glyphLayout.setText((BitmapFont)Renderer.fontMain.get(this.fontID), (CharSequence)this.sLines.get(i));
            if (glyphLayout.width > (float)this.iTextWidth) {
                this.iTextWidth = (int)glyphLayout.width;
            }
        }

    }

    public void draw(SpriteBatch oSB, int nPosX, int nPosY, float nAlpha, int iMaxWidth) {
        for(int i = 0; i < this.iLineSize; ++i) {
            Renderer.drawTextWithShadow(oSB, this.fontID, (String)this.sLines.get(i), nPosX, nPosY + CFG.PADDING + CFG.PADDING / 2 + (int)(((float)CFG.TEXT_HEIGHT - (float)CFG.TEXT_HEIGHT * 0.9F) / 2.0F) + (this.iTextHeight + CFG.PADDING * 2) * i, new Color(this.oColor.r, this.oColor.g, this.oColor.b, nAlpha));
        }

    }

    public int getWidth() {
        return this.iTextWidth;
    }

    public int getHeight() {
        return CFG.PADDING + CFG.TEXT_HEIGHT_SMALL * this.iLineSize + CFG.PADDING * 2 * (this.iLineSize - 1);
    }
}
