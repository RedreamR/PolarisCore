package team.rainfall.fontFix;

import aoc.kingdoms.lukasz.jakowski.AA_KeyManager;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.menu.View;
import aoc.kingdoms.lukasz.menus.InitGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
 
import team.rainfall.finality.luminosity2.annotations.Mixin;

@Mixin(mixinClass =  "aoc.kingdoms.lukasz.jakowski.AA_Game$1")
public class MixinAA_Game$1 {
    public boolean keyDown(int keycode) {
        if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_RIGHT) || Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
            if (Gdx.input.isKeyPressed(31)) {
                FontFix.copy();
                return true;
            }

            if (Gdx.input.isKeyPressed(50)) {
                FontFix.paste();
                return true;
            }
            if(Gdx.input.isKeyPressed(Input.Keys.R)){
                FontFix.LOGGER.debug("Try to reload");
                InitGame.fromViewID = Game.menuManager.getViewID();
                InitGame.reloadOnResume = true;
                InitGame.fullReload = true;
                InitGame.iStepID = 0;
                Game.menuManager.setViewIDWithoutAnimation(View.INIT_GAME_MENU);
                return true;
            }
        }

        return AA_KeyManager.keyDown(keycode);
    }
}
