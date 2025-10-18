package team.rainfall.fontFix.mixin;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.menu.Menu;
import team.rainfall.finality.luminosity2.annotations.Mixin;
import team.rainfall.fontFix.Config;
import team.rainfall.fontFix.utils.AnimationUtil;
@Mixin(mixinClass = "aoc.kingdoms.lukasz.menusInGame.InGame")
public class MixinInGame extends Menu {
    public static int iMinimapPosY = 0;
    public static boolean inAnimation = false;
    public static boolean hideAnimation = false;
    public long minimapTime = 0L;
    public int minimapAnimationTime = 275;
    public int minimapElementID = 0;
    public final void minimapAnimation() {
        if (inAnimation) {
            float progress = (float)(CFG.currentTimeMillis - this.minimapTime) / (float) Config.getAnimationConfig().MiniMap;
            progress = (float) AnimationUtil.easeOut(progress);
            progress = Math.min(1f,progress);
            if(hideAnimation){
                iMinimapPosY = (int) (progress * this.getMenuElement(this.minimapElementID).getHeight());
            }else {
                iMinimapPosY = (int) ((1 - progress) * this.getMenuElement(this.minimapElementID).getHeight());
            }
            if(CFG.currentTimeMillis - this.minimapTime > Config.getAnimationConfig().MiniMap){
                inAnimation = false;
            }
        }

    }
}
