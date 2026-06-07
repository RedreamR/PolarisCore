//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.menu_element.button;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.textures.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ButtonMainTitle2 extends Button {
    public long lTime = 0L;
    public int ANIMATION_TIME = 1500;
    public int iconID = 0;
    public ButtonMainTitle2(String sText, int fontID, int iTextPositionX, int iPosX, int iPosY, boolean isClickable) {
        this.init(sText, fontID, iTextPositionX, iPosX, iPosY, ImageManager.getImage(Images.logo2).getWidth(), ImageManager.getImage(Images.logo2).getHeight(), isClickable, true, false, false);
        this.lTime = System.currentTimeMillis();
    }
    public ButtonMainTitle2(int iconID,String sText, int fontID, int iTextPositionX, int iPosX, int iPosY, boolean isClickable) {
        this.iconID = iconID;
        float scale = CFG.GAME_WIDTH * 0.2f / ImageManager.getImage(iconID).getWidth();
        this.init(sText, fontID, iTextPositionX, iPosX, iPosY, (int) (CFG.GAME_WIDTH * 0.2f), (int) (ImageManager.getImage(iconID).getHeight() * scale), isClickable, true, false, false);
        this.lTime = System.currentTimeMillis();
    }

    protected void drawButtonBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive) {
        oSB.setColor(new Color(1.0F, 1.0F, 1.0F, !this.getIsHovered() && !isActive ? 1.0F : 0.9F));
        if (this.lTime + (long)this.ANIMATION_TIME > System.currentTimeMillis()) {
            float t = (float)(System.currentTimeMillis() - this.lTime) / (float)this.ANIMATION_TIME;
            if (t > 1.0F) {
                t = 1.0F;
            }

            if (t < 0.0F) {
                t = 0.0F;
            }

            t = 1.0F - (float)Math.pow(1.0F - t, 6.0F);
            float drawY = this.getPosY() - this.getHeight() * (1 - t);
            ImageManager.getImage(iconID).draw(oSB, this.getPosX(), (int)drawY,this.getWidth(),this.getHeight());
        } else {
            ImageManager.getImage(iconID).draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY,this.getWidth(),this.getHeight());
        }

        oSB.setColor(Color.WHITE);
    }
}
