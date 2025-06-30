//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.menu_element.textStatic;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.menu_element.MenuElement_Type;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import team.rainfall.fontFix.text.Line;
import team.rainfall.fontFix.text.TextColorRenderer;
import team.rainfall.fontFix.text.TextProcessor;

import java.util.ArrayList;

public class Text_DescScenarios extends Text_Desc {
    int iLineSize = 0;
    ArrayList<String> sLines = new ArrayList<>();
    public Text_DescScenarios(String sText, int iPosX, int iPosY, int iWidth) {
        super(sText, iPosX, iPosY, iWidth);
        this.typeOfElement = MenuElement_Type.TRANSPARENT_BACKGROUND;
    }

    public Text_DescScenarios(String sText, int iPosX, int iPosY, int iWidth, int fontID) {
        super(sText, iPosX, iPosY, iWidth, fontID);
        this.typeOfElement = MenuElement_Type.TRANSPARENT_BACKGROUND;
    }

    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
        for(int i = 0; i < this.ffLines.size(); ++i) {
            TextColorRenderer.drawLine(oSB, this.fontID, this.ffLines.get(i), this.getPosX() + this.getPadding() + iTranslateX, this.getPosY() + this.getPaddingY() + (this.iTextHeight + CFG.PADDING * 2) * i + iTranslateY, this.getColor(isActive));
        }

    }

    public void setText(String sText) {
        this.updateTextPosition();
        this.ffLines.clear();
        this.iLineSize = 0;
        this.iTextWidth = 0;
        int textPosX = 0;
        int maxW = this.getWidth() - this.getPadding() * 2;
        this.ffLines = TextProcessor.warp(TextProcessor.tokenize(sText),maxW,this.fontID);
        for (Line ffLine : this.ffLines) {
            if(iTextWidth < ffLine.lineWidth){
                iTextWidth = (int) ffLine.lineWidth;
            }
            if(this.iTextHeight < ffLine.lineHeight){
                this.iTextHeight = (int) ffLine.lineHeight;
            }
        }
        this.setHeight(this.iTextHeight * this.ffLines.size() + (this.ffLines.size() - 1) * CFG.PADDING * 2 + this.getPaddingY() * 2);
    }
}
