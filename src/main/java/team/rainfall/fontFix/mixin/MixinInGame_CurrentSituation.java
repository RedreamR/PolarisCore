package team.rainfall.fontFix.mixin;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.menu.Menu;
import aoc.kingdoms.lukasz.menu_element.Status;
import aoc.kingdoms.lukasz.textures.ImageManager;
import aoc.kingdoms.lukasz.textures.Images;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import team.rainfall.finality.luminosity2.annotations.Mixin;
import team.rainfall.fontFix.Config;
import team.rainfall.fontFix.utils.AnimationUtil;

import static aoc.kingdoms.lukasz.menusInGame.InGame_CurrentSituation.lTime;

@Mixin(mixinClass = "aoc.kingdoms.lukasz.menusInGame.InGame_CurrentSituation")
public class MixinInGame_CurrentSituation extends Menu {
    public void draw(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean menuIsActive, Status titleStatus) {
        if (lTime + Config.getAnimationConfig().CurrentSituation >= CFG.currentTimeMillis) {
            double progress = ((float)(CFG.currentTimeMillis - lTime) / Config.getAnimationConfig().CurrentSituation);
            progress = AnimationUtil.easeOut(progress);
            iTranslateX = iTranslateX - CFG.BUTTON_WIDTH + (int)((float)CFG.BUTTON_WIDTH * progress);
        }

        Renderer.drawBoxCorner(oSB, this.getPosX() + iTranslateX, this.getPosY() - this.getTitle().getHeight() + iTranslateY, this.getWidth(), this.getHeight() + this.getTitle().getHeight() + CFG.PADDING);
        Renderer.drawMenusBox(oSB, this.getPosX() + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), this.getHeight() + CFG.PADDING, false);
        ImageManager.getImage(Images.newGameOver).draw2(oSB, this.getPosX() + this.getWidth() / 2 - ImageManager.getImage(Images.newGameOver).getWidth() / 2 + iTranslateX, this.getPosY() + iTranslateY, this.getWidth(), Math.min(this.getHeight(), ImageManager.getImage(Images.newGameOver).getHeight()));
        super.draw(oSB, iTranslateX, iTranslateY, menuIsActive, titleStatus);
    }
}
