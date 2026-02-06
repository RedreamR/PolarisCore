package team.rainfall.fontFix;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import team.rainfall.finality.luminosity2.CallbackInfo;
import team.rainfall.finality.luminosity2.annotations.Inject;
import team.rainfall.finality.luminosity2.annotations.Mixin;
import aoc.kingdoms.lukasz.map.civilization.CivilizationRegion;
@Mixin(mixinClass = "aoc.kingdoms.lukasz.map.civilization.CivilizationRegion")
public class MixinCivilizationRegion {
    @Inject(methodName = "drawCivRegion")
    public final void drawCivRegion_inject(SpriteBatch oSB, CallbackInfo callbackInfo) {}
}
