//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.jakowski;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
 
import team.rainfall.fontFix.Config;
import team.rainfall.fontFix.FontFix;
import team.rainfall.fontFix.utils.Consts;

public class GlyphLayout_Game {
    public float width = 1.0F;
    public float height = 1.0F;

    public GlyphLayout_Game() {
    }

    public boolean setText(BitmapFont font, CharSequence str) {
        try {
            if (str != null && str.length() > 0) {
                if(!Thread.currentThread().getName().contains(Consts.GL_THREAD) && FontFix.getDI()){
                    GlyphLayout glyphLayout = FontFix.getGlyphLayoutData(font,str);
                    if (glyphLayout != null) {
                        this.width = glyphLayout.width;
                        this.height = glyphLayout.height;
                    }
                    return true;
                }else {
                    GlyphLayout glyphLayout = new GlyphLayout();
                    glyphLayout.setText(font, str);
                    this.width = glyphLayout.width;
                    this.height = glyphLayout.height;
                }
                return true;
            } else {
                this.width = 1.0F;
                this.height = (float)CFG.TEXT_HEIGHT;
                return false;
            }
        } catch (Exception var4) {
            FontFix.LOGGER.error("ERR setText ",var4);
            return false;
        }
    }

    public float getWidth() {
        return this.width;
    }

    public float getHeight() {
        return this.height;
    }
}
