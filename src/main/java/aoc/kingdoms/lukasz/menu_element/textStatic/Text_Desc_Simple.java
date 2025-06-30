//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.menu_element.textStatic;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.textures.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import team.rainfall.fontFix.text.TextColorRenderer;

public class Text_Desc_Simple extends Text_Desc {
    public Text_Desc_Simple(String sText, int iPosX, int iPosY, int iWidth) {
        super(sText, iPosX, iPosY, iWidth);
    }

    public Text_Desc_Simple(String sText, int iPosX, int iPosY, int iWidth, int fontID) {
        super(sText, iPosX, iPosY, iWidth, fontID);
    }

    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, getBoxAlpha(this.getClickable(), this.getIsHovered(), isActive)));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 0.8F);
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, getBoxAlpha(this.getClickable(), this.getIsHovered(), isActive)));
        Renderer.drawBox(oSB, Images.statsRectBGBorder, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 0.8F);

        for(int i = 0; i < this.ffLines.size(); ++i) {
            TextColorRenderer.drawLine(oSB, this.fontID, ffLines.get(i), this.getPosX() + this.getPadding() + iTranslateX, this.getPosY() + this.getPaddingY() + (this.iTextHeight + CFG.PADDING * 2) * i + iTranslateY, this.getColor(isActive));
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
