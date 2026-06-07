package team.rainfall.fontFix.mixin;

import team.rainfall.finality.luminosity2.annotations.Mixin;

@Mixin(mixinClass = "aoc.kingdoms.lukasz.jakowski.CFG")
public class MixinCFG {
    public static final float changeAnimationPos(int animationStepID, float animationChangeViewPos, boolean backAnimation, int nWidth) {
        final int TOTAL_FRAMES = 20;
        int step = Math.min(animationStepID, TOTAL_FRAMES);
        float progress = (float) step / TOTAL_FRAMES;
        float easedProgress = 1.0f - (float) Math.pow(1.0f - progress, 3);
        float targetOffset = -nWidth * (backAnimation ? -1 : 1);
        if (step == TOTAL_FRAMES) {
            return targetOffset;
        }
        return targetOffset * easedProgress;
    }
}
