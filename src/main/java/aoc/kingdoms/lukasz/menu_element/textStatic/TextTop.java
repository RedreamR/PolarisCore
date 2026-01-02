//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.menu_element.textStatic;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.jakowski.Renderer.SparksAnimation;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.menu_element.MenuElement;
import aoc.kingdoms.lukasz.menu_element.MenuElement_Type;
import aoc.kingdoms.lukasz.menu_element.button.ButtonGame;
import aoc.kingdoms.lukasz.menus.MainMenu;
import aoc.kingdoms.lukasz.menusInGame.InGame;
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.textures.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import team.rainfall.fontFix.Config;

public class TextTop extends MenuElement {
    public static final int EXTRA_WIDTH_BOX_PADDING;
    public String sText = null;
    public String sText2 = null;
    public int iTextWidth = -1;
    public int iTextHeight = -1;
    public int iTextPosX;
    public int fontID2 = 1;
    public int iTextWidth2 = -1;
    public int iTextHeight2 = -1;
    public int imageID;
    public int textPosY;
    protected static long lTimeAnimation;
    protected static int animationState;
    public static final int ANIMATION_T = 1000;
    public SparksAnimation sparksAnimationTop = new SparksAnimation();
    public float lastValue = -997654.3F;
    public int WIDTH_LAST_TURN_UPDATE = 0;

    public TextTop(int imageID, String sText, String sText2, int iPosX, int iPosY) {
        this.typeOfElement = MenuElement_Type.TEXT;
        this.imageID = imageID;
        this.setPosX(iPosX);
        this.setPosY(iPosY);
        this.setHeight(InGame.topStatsHeight - CFG.PADDING * 2);
        this.setText(sText);
        this.setText2(sText2);
        this.iTextPosX = EXTRA_WIDTH_BOX_PADDING + ImageManager.getImage(imageID).getWidth() + CFG.PADDING * 2;
        this.fontID = CFG.FONT_REGULAR;
        this.fontID2 = CFG.FONT_REGULAR_SMALL;
        this.textPosY = (this.getHeight() - (this.iTextHeight + this.iTextHeight2 + CFG.PADDING)) / 2;
    }

    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
        iTranslateX = this.getPosX() + iTranslateX;
        iTranslateY = this.getPosY() + iTranslateY;
        oSB.setColor(Colors.COLOR_GRADIENT_OVER_BLUE);
        if(Config.getGradientConfig().textTop < 3) {
            oSB.getColor().a = 0.65F;
            Images.gradientFull.draw(oSB, iTranslateX, iTranslateY, this.getWidth(), this.getHeight());
            oSB.getColor().a = 0.3F;
            Images.gradientXY.draw(oSB, iTranslateX, iTranslateY, this.getWidth(), this.getHeight());
        }
        if(Config.getGradientConfig().textTop < 2) {
            oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.25F));
            Images.gradientXY.draw(oSB, iTranslateX, iTranslateY, this.getWidth(), CFG.PADDING * 2, false, true);
            Images.gradientXY.draw(oSB, iTranslateX, iTranslateY + this.getHeight() - CFG.PADDING * 2, this.getWidth(), CFG.PADDING * 2);
        }
        oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.25F));
        Renderer.drawBox(oSB, Images.statsRectBGBorder, iTranslateX, iTranslateY, this.getWidth(), this.getHeight(), 1.0F);
        if(Config.getGradientConfig().textTop < 1) {
            oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.5F));
            Images.gradientFull.draw(oSB, iTranslateX, iTranslateY + this.getHeight() - 1, this.getWidth(), 1);
            Images.gradientFull.draw(oSB, iTranslateX, iTranslateY, this.getWidth(), 1);
            oSB.setColor(Colors.COLOR_GRADIENT_OVER_BLUE);
            oSB.getColor().a = 0.85F;
            Images.gradientFull.draw(oSB, iTranslateX, iTranslateY + this.getHeight() - 2, this.getWidth(), 1);
            Images.gradientFull.draw(oSB, iTranslateX, iTranslateY + 1, this.getWidth(), 1);
            oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 0.55F));
            Images.gradientFull.draw(oSB, iTranslateX, iTranslateY + this.getHeight() - 1, this.getWidth(), 1);
            Images.gradientFull.draw(oSB, iTranslateX, iTranslateY, this.getWidth(), 1);
            oSB.setColor(Colors.COLOR_GRADIENT_OVER_BLUE);
            oSB.getColor().a = 0.9F;
            Images.gradientFull.draw(oSB, iTranslateX, iTranslateY + this.getHeight() - 2, this.getWidth(), 1);
            Images.gradientFull.draw(oSB, iTranslateX, iTranslateY + 1, this.getWidth(), 1);
        }
        oSB.setColor(Color.WHITE);
        if (this.getIsHovered() || isActive || this.getIsActiveButton()) {
            if(Config.getGradientConfig().textTop < 5) {
                oSB.setColor(new Color(Colors.COLOR_GRADIENT.r, Colors.COLOR_GRADIENT.g, Colors.COLOR_GRADIENT.b, 0.8F));
                Images.gradientXY.draw(oSB, iTranslateX, iTranslateY, this.getWidth(), this.getHeight());
            }
            oSB.setColor(MainMenu.sparksColors);
            this.sparksAnimationTop.draw2(oSB, iTranslateX, iTranslateY, this.getWidth(), this.getHeight());
            oSB.setColor(new Color(0.0F, 0.0F, 0.0F, 1.0F));
            Renderer.drawBox(oSB, Images.statsRectBGBorder, iTranslateX, iTranslateY, this.getWidth(), this.getHeight(), 1.0F);
            oSB.setColor(Color.WHITE);
        }

        if (this.getClickable() && this.getIsHovered() && animationState >= 0) {
            if (animationState == 0) {
                float drawPerc = Math.min((float) (CFG.currentTimeMillis - lTimeAnimation) / 1000.0F, 1.0F);
                oSB.setColor(ButtonGame.getColorLine());
                Images.line_32_off1.draw(oSB, iTranslateX + CFG.PADDING, iTranslateY + 1, (int)((float)(this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                Images.line_32_off1.draw(oSB, iTranslateX + CFG.PADDING, iTranslateY + this.getHeight() - 2, (int)((float)(this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                if (lTimeAnimation < CFG.currentTimeMillis - 1000L) {
                    ++animationState;
                    lTimeAnimation = CFG.currentTimeMillis;
                }
            } else {
                float drawPerc = Math.min((float) (CFG.currentTimeMillis - lTimeAnimation) / 1000.0F, 1.0F);
                oSB.setColor(ButtonGame.getColorLine());
                Images.line_32_off1.draw(oSB, iTranslateX + CFG.PADDING + (int)((float)(this.getWidth() - CFG.PADDING * 2) * drawPerc), iTranslateY + 1, this.getWidth() - CFG.PADDING * 2 - (int)((float)(this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                Images.line_32_off1.draw(oSB, iTranslateX + CFG.PADDING + (int)((float)(this.getWidth() - CFG.PADDING * 2) * drawPerc), iTranslateY + this.getHeight() - 2, this.getWidth() - CFG.PADDING * 2 - (int)((float)(this.getWidth() - CFG.PADDING * 2) * drawPerc), 1);
                if (lTimeAnimation < CFG.currentTimeMillis - 1000L) {
                    animationState = 0;
                    lTimeAnimation = CFG.currentTimeMillis;
                }
            }

            oSB.setColor(Color.WHITE);
        }

        ImageManager.getImage(this.getImageID()).draw(oSB, iTranslateX + EXTRA_WIDTH_BOX_PADDING, iTranslateY + (this.getHeight() - ImageManager.getImage(this.getImageID()).getHeight()) / 2);
        Renderer.drawTextWithShadow(oSB, this.fontID, this.getTextToDraw(), iTranslateX + this.iTextPosX, iTranslateY + this.textPosY, this.getColor(isActive));
        Renderer.drawTextWithShadow(oSB, this.fontID2, this.sText2, iTranslateX + this.iTextPosX, iTranslateY + this.textPosY + this.iTextHeight + CFG.PADDING, Colors.TEXT_TOP_BOT);
    }

    protected Color getColor(boolean isActive) {
        return Colors.getColorTopStats(isActive, this.getIsHovered());
    }

    protected Color getColor2(boolean isActive) {
        return this.getColor(isActive);
    }

    public int getImageID() {
        return this.imageID;
    }

    public String getTextToDraw() {
        return this.sText;
    }

    public final String getText() {
        return this.sText;
    }

    public void setText(String sText) {
        this.sText = sText;

        try {
            Renderer.glyphLayout.setText(Renderer.fontMain.get(this.fontID), sText);
            this.iTextWidth = (int)Renderer.glyphLayout.width;
            this.iTextHeight = (int)Renderer.glyphLayout.height;
            if (super.getWidth() < this.iTextWidth + this.extraWidth()) {
                this.setWidth(this.iTextWidth + this.extraWidth());
            }

            if (Game_Calendar.TURN_ID > this.WIDTH_LAST_TURN_UPDATE + 365) {
                this.setWidth(Math.max(this.iTextWidth, this.iTextWidth2) + this.extraWidth());
                this.WIDTH_LAST_TURN_UPDATE = Game_Calendar.TURN_ID;
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    public void setText2(String sText2) {
        this.sText2 = sText2;

        try {
            Renderer.glyphLayout.setText(Renderer.fontMain.get(this.fontID2), sText2);
            this.iTextWidth2 = (int)Renderer.glyphLayout.width;
            this.iTextHeight2 = (int)Renderer.glyphLayout.height;
            if (super.getWidth() < this.iTextWidth2 + this.extraWidth()) {
                this.setWidth(this.iTextWidth2 + this.extraWidth());
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    public final void setWidthOfButton() {
        this.setWidth(this.extraWidth());
    }

    public final int extraWidth() {
        return ImageManager.getImage(this.imageID).getWidth() + CFG.PADDING * 2 + EXTRA_WIDTH_BOX_PADDING * 2;
    }

    public int getTextWidth() {
        return this.iTextWidth;
    }

    public int getTextHeight() {
        return this.iTextHeight;
    }

    public void setIsHovered(boolean isHovered) {
        super.setIsHovered(isHovered);
        lTimeAnimation = CFG.currentTimeMillis;
        animationState = 0;
    }

    public boolean getIsActiveButton() {
        return false;
    }

    static {
        EXTRA_WIDTH_BOX_PADDING = CFG.PADDING * 3;
        lTimeAnimation = 0L;
        animationState = 0;
    }
}
