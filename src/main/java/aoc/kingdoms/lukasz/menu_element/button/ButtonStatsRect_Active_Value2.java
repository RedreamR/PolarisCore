//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.menu_element.button;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ButtonStatsRect_Active_Value2 extends ButtonStatsRect_Active_Value {
    public int id;
    public String duration;
    public float durationWidth = -1;
    public ButtonStatsRect_Active_Value2(String sText, int iPosX, int iPosY, int nWidth, int nHeight, int id) {
        super(sText, iPosX, iPosY, nWidth, nHeight, id);
        duration = Game.soundsManager.getDura2(id);
        this.id = id;
    }

    public ButtonStatsRect_Active_Value2(String sText, int iPosX, int iPosY, int nWidth, int nHeight, int id, int iTextPos) {
        super(sText, iPosX, iPosY, nWidth, nHeight, id, iTextPos);
        this.id = id;
    }
    protected void drawText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        if(duration.length() > 1 && durationWidth == -1) {
            Renderer.glyphLayout.setText(Renderer.fontMain.get(this.fontID), duration);
            durationWidth = Renderer.glyphLayout.width;
        }
        Renderer.drawTextWithShadow(oSB, this.fontID, this.getTextToDraw(), this.getPosX() + this.textPosition.getTextPosition() + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.iTextHeight / 2 + iTranslateY, this.getColor(isActive));
        if(duration.length() > 1) {
            Renderer.drawTextWithShadow(oSB, this.fontID, duration, (int) (this.getPosX() + iTranslateX + getWidth() - CFG.PADDING - durationWidth), this.getPosY() + this.getHeight() / 2 - this.iTextHeight / 2 + iTranslateY, this.getColor(isActive));
        }
    }
    public int getCurrent() {
        return this.id;
    }
}
