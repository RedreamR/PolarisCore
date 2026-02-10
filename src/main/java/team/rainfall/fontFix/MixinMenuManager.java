package team.rainfall.fontFix;

import aoc.kingdoms.lukasz.jakowski.AA_KeyManager;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.map.province.ProvinceBorderManager;
import aoc.kingdoms.lukasz.map.province.ProvinceDraw;
import aoc.kingdoms.lukasz.map.province.ProvinceTouchExtraAction;
import aoc.kingdoms.lukasz.menu.View;
import aoc.kingdoms.lukasz.menus.MainMenu;
import team.rainfall.finality.luminosity2.CallbackInfo;
import team.rainfall.finality.luminosity2.annotations.Inject;
import team.rainfall.finality.luminosity2.annotations.Mixin;
import team.rainfall.finality.luminosity2.annotations.Shadow;
import team.rainfall.finality.luminosity2.utils.InjectPosition;

@Mixin(mixinClass = "aoc.kingdoms.lukasz.menu.MenuManager")
public class MixinMenuManager {
    private int fromViewID = -1;
    private int toViewID = -1;
    private int viewID = -1;
    public final void setViewIDWithoutAnimation(View eView) {
        if(eView == View.MAINMENU){
            MainMenu.animTime = -1;
        }
        Game.hoverManager.resetHoverActive_Menu();
        this.viewID = Game.menuManager.getViewID(eView);
        this.updateDrawProvinces();
        ProvinceTouchExtraAction.updateExtraAction();
        AA_KeyManager.updateKeyExtraAction();
        ProvinceBorderManager.updateAction();
        ProvinceDraw.updateDrawMoveUnits();
        Game.mapBG.updateWorldMap();
    }
    @Inject(methodName = "setViewID",position = InjectPosition.HEAD,returnWithValue = true)
    public final void setViewID2(View eView, CallbackInfo ci) {
        if(eView == View.MAINMENU){
            MainMenu.animTime = -1;
            Game.menuManager.setViewIDWithoutAnimation(eView);
            ci.cancel();
        }
    }
    @Shadow
    private final void updateDrawProvinces() {

    }
}
