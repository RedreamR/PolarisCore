//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.menu_element;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.map.province.ProvinceDraw;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.textures.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Slider extends MenuElement {
    public int iMin;
    public int iCurrent2 = 0;
    public int iMax;
    public int iCurrentPosX = -1;
    public String sText = null;
    private int iCurrent;
    private int iTextWidth = -1;
    private int iTextHeight = -1;
    private long lTime = 0L;
    public int iDifference_CurrentPosX = 0;
    private int iDifference_PosX = 0;

    public Slider() {
    }

    public Slider(int iPosX, int iPosY, int iWidth, int iHeight, int iMin, int iMax, int iCurrent) {
        this.initSlider("", iPosX, iPosY, iWidth, iHeight, iMin, iMax, iCurrent);
    }

    public Slider(String sText, int iPosX, int iPosY, int iWidth, int iHeight, int iMin, int iMax, int iCurrent) {
        this.initSlider(sText, iPosX, iPosY, iWidth, iHeight, iMin, iMax, iCurrent);
    }

    public void initSlider(String sText, int iPosX, int iPosY, int iWidth, int iHeight, int iMin, int iMax, int iCurrent) {
        this.setPosX(iPosX);
        this.setPosY(iPosY);
        this.setWidth(iWidth);
        this.setHeight(iHeight);
        this.fontID = CFG.FONT_REGULAR_SMALL;
        this.sText = sText;
        this.iMin = iMin;
        this.iMax = iMax;
        this.iCurrent = iCurrent;
        this.updateSlider(-1);
        this.typeOfElement = MenuElement_Type.SLIDER;
    }

    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
        this.drawSliderBG(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
        this.drawSliderText(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
        this.drawSliderBorder(oSB, iTranslateX, iTranslateY, isActive, scrollableY);
        oSB.setColor(Color.WHITE);
    }

    public final void drawSliderBG_UpdateAnimation() {
        if (this.iDifference_CurrentPosX != 0) {
            if (this.lTime == 0L) {
                this.lTime = CFG.currentTimeMillis;
            }

            this.iDifference_CurrentPosX = this.iDifference_PosX - (int)((float)this.iDifference_PosX * ((float)(CFG.currentTimeMillis - this.lTime) / 375.0F));
            if (CFG.currentTimeMillis >= this.lTime + 375L) {
                this.iDifference_CurrentPosX = 0;
            }
        }

    }

    public void drawSliderBG(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
        this.drawSliderBG_UpdateAnimation();
        oSB.setColor(new Color(Colors.COLOR_STATS_RECT_BG.r, Colors.COLOR_STATS_RECT_BG.g, Colors.COLOR_STATS_RECT_BG.b, 0.5F));
        Renderer.drawBox(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight(), 1.0F);
        oSB.setColor(new Color(ProvinceDraw.progressBar.r, ProvinceDraw.progressBar.g, ProvinceDraw.progressBar.b, 0.5F));
        Renderer.drawBoxProgress(oSB, Images.statsRectBG, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.iCurrentPosX + this.iDifference_CurrentPosX, this.getHeight(), this.getWidth());
        oSB.setColor(Color.WHITE);
        oSB.setColor(new Color(Colors.COLOR_GRADIENT_BG_BLUE.r, Colors.COLOR_GRADIENT_BG_BLUE.g, Colors.COLOR_GRADIENT_BG_BLUE.b, 0.2F));
        Images.gradientXY.draw(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight());
        oSB.setColor(Color.WHITE);
    }

    public Color getColorLEFT() {
        return Colors.COLOR_SLIDER_LEFT_BG;
    }

    public Color getColorRIGHT() {
        return Colors.COLOR_SLIDER_RIGHT_BG;
    }

    public void drawSliderText(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
        Renderer.drawTextWithShadow(oSB, this.fontID, this.getDrawText(), this.getPosX() + this.getWidth() / 2 - this.getTextWidth() / 2 + iTranslateX, this.getPosY() + this.getHeight() / 2 - this.getTextHeight() / 2 + iTranslateY, new Color(0.945F, 0.945F, 0.945F, 1.0F));
    }

    public void drawSliderBorder(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean isActive, boolean scrollableY) {
    }

    public String getDrawText() {
        return this.sText + this.iCurrent;
    }

    public void updateSlider(int nX) {
        if (nX >= 0) {
            nX -= this.getPosX();
            this.iCurrent = (int)((float)nX * 100.0F / (float)this.getWidth() * (float)(this.iMax - this.iMin) / 100.0F + (float)this.iMin);
        }

        if (this.iCurrent < this.iMin) {
            this.iCurrent = this.iMin;
        } else if (this.iCurrent > this.iMax) {
            this.iCurrent = this.iMax;
        }

        //current2
        if (nX >= 0) {
            nX -= this.getPosX();
            this.iCurrent2 = (int)((float)nX * 100.0F / (float)this.getWidth() * (float)(this.iMax - this.iMin) / 100.0F + (float)this.iMin);
        }

        if (this.iCurrent2 < this.iMin) {
            this.iCurrent2 = this.iMin;
        } else if (this.iCurrent2 > this.iMax) {
            this.iCurrent2 = this.iMax;
        }

        this.updateCurrentPosX();
        this.updateTextWidth();
        this.iDifference_CurrentPosX = 0;
        this.iDifference_PosX = 0;
    }

    private final void updateCurrentPosX() {
        this.iCurrentPosX = (int)((float)(this.iCurrent - this.iMin) * 100.0F / (float)(this.iMax - this.iMin) * (float)this.getWidth() / 100.0F);
    }

    public final void updateTextWidth() {
        Renderer.glyphLayout.setText((BitmapFont)Renderer.fontMain.get(this.fontID), this.getDrawText());
        this.iTextWidth = (int)Renderer.glyphLayout.width;
        this.iTextHeight = (int)Renderer.glyphLayout.height;
    }

    public final String getText() {
        return this.sText;
    }

    public void setText(String sText) {
        this.sText = sText;
        this.updateTextWidth();
    }

    public synchronized void setCurrent(int nCurrent) {
        int tempCurr = this.iCurrentPosX;
        if (nCurrent > this.iMax) {
            this.iCurrent = this.iMax;
        } else this.iCurrent = Math.max(nCurrent, this.iMin);

        if (nCurrent > this.iMax) {
            this.iCurrent2 = this.iMax;
        } else this.iCurrent2 = Math.max(nCurrent, this.iMin);

        this.updateCurrentPosX();
        this.updateTextWidth();
        if (tempCurr != this.iCurrentPosX) {
            this.lTime = 0L;
            this.iDifference_CurrentPosX = tempCurr - this.iCurrentPosX;
            this.iDifference_PosX = this.iDifference_CurrentPosX;
        }

    }
    public synchronized void setCurrent2(int nCurrent) {
        int tempCurr = this.iCurrentPosX;
        if (nCurrent > this.iMax) {
            this.iCurrent = this.iMax;
        } else this.iCurrent = Math.max(nCurrent, this.iMin);
        this.updateCurrentPosX();
        this.updateTextWidth();
        if (tempCurr != this.iCurrentPosX) {
            this.lTime = 0L;
            this.iDifference_CurrentPosX = tempCurr - this.iCurrentPosX;
            this.iDifference_PosX = this.iDifference_CurrentPosX;
        }

    }
    public final int getCurrent() {
        return this.iCurrent;
    }

    public int getTextWidth() {
        return this.iTextWidth;
    }

    public final int getTextHeight() {
        return this.iTextHeight;
    }

    public void setMin(int iMin) {
        this.iMin = iMin;
        if (this.iCurrent < iMin) {
            this.iCurrent = iMin;
            this.updateTextWidth();
        }

    }

    public void setMax(int iMax) {
        this.iMax = iMax;
        if (this.iCurrent > iMax) {
            this.iCurrent = iMax;
            this.updateTextWidth();
        }

    }

    public int getTextPos() {
        return this.iMax;
    }

    public void scrollByWheel(int nScoll) {
        this.setCurrent(this.getCurrent() + nScoll);
        this.actionElement();
    }

    public boolean getScrollable() {
        return true;
    }
}
