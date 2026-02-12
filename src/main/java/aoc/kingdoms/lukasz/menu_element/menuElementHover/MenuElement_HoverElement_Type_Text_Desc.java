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
 
import team.rainfall.fontFix.TextSplitter;
import team.rainfall.fontFix.text.Line;
import team.rainfall.fontFix.text.TextColorRenderer;
import team.rainfall.fontFix.text.TextProcessor;

import java.util.ArrayList;
import java.util.List;

public class MenuElement_HoverElement_Type_Text_Desc implements MenuElement_HoverElement_Type {
    public ArrayList<Line> ffLines = new ArrayList<>();
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
        int maxW = (int)((float) ImageManager.getImage(Images.title1Red).getWidth() * 0.85f);
        this.ffLines = TextProcessor.warp2(TextProcessor.tokenize(sText),maxW,nFontID);
        this.iTextHeight = (int) ffLines.get(0).lineHeight;
        int maxWidthInLines = 0;
        for (Line ffLine : this.ffLines) {
            if(this.iTextHeight < ffLine.lineHeight && !ffLine.isImageInText()){
                this.iTextHeight = (int) ffLine.lineHeight;
            }
            if(maxWidthInLines < ffLine.lineWidth){
                maxWidthInLines = (int) ffLine.lineWidth;
            }
        }
        this.iTextWidth = Math.min(maxW,maxWidthInLines);

    }

    public void draw(SpriteBatch oSB, int nPosX, int nPosY, float nAlpha, int iMaxWidth) {
        int extraY = 0;
        for(int i = 0; i < this.ffLines.size(); ++i) {
            TextColorRenderer.drawLine_Hover(oSB, this.fontID, this.ffLines.get(i), nPosX, nPosY + CFG.PADDING + CFG.PADDING / 2 + (int)(((float)CFG.TEXT_HEIGHT - (float)CFG.TEXT_HEIGHT * 0.9F) / 2.0F) + (this.iTextHeight + CFG.PADDING * 2) * i + extraY, new Color(this.oColor.r, this.oColor.g, this.oColor.b, nAlpha));
            if(this.ffLines.get(i).isImageInText()){
                extraY += (int) ffLines.get(i).lineHeight - (this.iTextHeight + CFG.PADDING);
            }
        }

    }

    public int getWidth() {
        return this.iTextWidth;
    }

    public int getHeight() {
        int extraY = 0;
        for (Line ffLine : this.ffLines) {
            if (ffLine.isImageInText()) {
                extraY += (int) ffLine.lineHeight - (this.iTextHeight + CFG.PADDING);
            }
        }
        return CFG.PADDING + CFG.TEXT_HEIGHT_SMALL * this.ffLines.size() + CFG.PADDING * 2 * (this.ffLines.size() - 1) + extraY;
    }
}
