package team.rainfall.fontFix;

import aoc.kingdoms.lukasz.jakowski.AA_KeyManager;
import com.badlogic.gdx.Gdx;
import team.rainfall.finality.FinalityLogger;
import team.rainfall.finality.luminosity2.annotations.Mixin;

@Mixin(mixinClass =  "aoc.kingdoms.lukasz.jakowski.AA_Game$1")
public class MixinAA_Game$1 {
    public boolean keyDown(int keycode) {
        if (Gdx.input.isKeyPressed(129) || Gdx.input.isKeyPressed(130)) {
            if (Gdx.input.isKeyPressed(31)) {
                FinalityLogger.debug("Ctrl+C Pressed");
                FontFix.copy();
                return true;
            }

            if (Gdx.input.isKeyPressed(50)) {
                FinalityLogger.debug("Ctrl+C Pressed");
                FontFix.paste();
                return true;
            }
        }

        return AA_KeyManager.keyDown(keycode);
    }
}
