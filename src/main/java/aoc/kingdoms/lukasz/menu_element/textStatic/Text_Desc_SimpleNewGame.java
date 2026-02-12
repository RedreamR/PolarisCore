//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.menu_element.textStatic;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.menus.NewGame.NewGameCiv;
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.textures.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import team.rainfall.fontFix.FontFix;
import team.rainfall.fontFix.text.TextColorRenderer;
import team.rainfall.fontFix.text.Word;

public class Text_Desc_SimpleNewGame extends Text_Desc {
    int iLineSize = 0;
    int maxLineSize = 3;
    public static int mls_static = -1;
    public boolean extraRender = true;
    public Text_Desc_SimpleNewGame(String sText, int iPosX, int iPosY, int iWidth) {
        super(sText, iPosX, iPosY, iWidth);
        FontFix.LOGGER.debug("MLS STATIC "+mls_static);
        if(mls_static > 0) {
            maxLineSize = mls_static;
            mls_static = -1;
        }
        if (!NewGameCiv.expandCivDesc) {
            if (this.ffLines.size() > maxLineSize) {
                iLineSize = maxLineSize;
                this.ffLines.get(maxLineSize - 1).words.add(new Word("..",'0'));
            }else {
                iLineSize = ffLines.size();
            }
            this.setHeight(this.iTextHeight * this.iLineSize + (this.iLineSize - 1) * CFG.PADDING * 2 + this.getPaddingY() * 2);
        }else {
            iLineSize = ffLines.size();
        }
    }

    public void refresh(){
        FontFix.LOGGER.debug("TDSN Refresh");
        if (!NewGameCiv.expandCivDesc) {
            if (this.ffLines.size() > maxLineSize) {
                iLineSize = maxLineSize;
                this.ffLines.get(maxLineSize - 1).words.add(new Word("..",'0'));
            }

            this.setHeight(this.iTextHeight * this.iLineSize + (this.iLineSize - 1) * CFG.PADDING * 2 + this.getPaddingY() * 2);
        }else {
            iLineSize = ffLines.size();
        }
    }
    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
        if(extraRender) {
            oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, getBoxAlpha(this.getClickable(), this.getIsHovered(), isActive)));
            Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 0.8F);
        }
        if(ffLines.size() >= maxLineSize) {
            oSB.setColor(new Color(1.0F, 1.0F, 1.0F, 0.75F));
            ImageManager.getImage(Images.activeSort).draw(oSB, this.getPosX() + this.getWidth() - ImageManager.getImage(Images.activeSort).getWidth() - ImageManager.getImage(Images.activeSort).getWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() - ImageManager.getImage(Images.activeSort).getHeight() / 2 + iTranslateY, false, NewGameCiv.expandCivDesc);
        }
        oSB.setColor(Color.WHITE);

        for(int i = 0; i < iLineSize; ++i) {
            TextColorRenderer.drawLine(oSB, this.fontID, this.ffLines.get(i), this.getPosX() + this.getPadding() + iTranslateX, this.getPosY() + this.getPaddingY() + (this.iTextHeight + CFG.PADDING * 2) * i + iTranslateY, this.getColor(isActive));
        }

    }

    protected Color getColor(boolean isActive) {
        if (isActive) {
            return Colors.BUTTON_TEXT_ACTIVE;
        } else if (this.getIsHovered()) {
            return Colors.BUTTON_TEXT_HOVERED;
        } else {
            return this.getClickable() ? Colors.BUTTON_TEXT_DESC_SIMPLE : Colors.BUTTON_TEXT_DISABLED;
        }
    }
}
