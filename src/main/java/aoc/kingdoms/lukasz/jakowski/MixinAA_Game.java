package aoc.kingdoms.lukasz.jakowski;

import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.jakowski.Steam.SteamManager;
import aoc.kingdoms.lukasz.map.province.ProvinceDraw;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
 
import team.rainfall.finality.loader.ParamParser;
import team.rainfall.finality.luminosity2.annotations.Mixin;
import team.rainfall.finality.luminosity2.annotations.Shadow;
import team.rainfall.fontFix.FontFix;

import java.lang.reflect.Field;

@Mixin(mixinClass = "aoc.kingdoms.lukasz.jakowski.AA_Game")
public class MixinAA_Game extends ApplicationAdapter {
    private Renderer renderer;
    public void create() {
        FileManager.initLoadInterface();
        boolean disableSteamAPI = false;
        try {
            Field field = Class.forName("team.rainfall.finality.loader.Loader").getDeclaredField("paramParser");
            field.setAccessible(true);
            ParamParser paramParser = (ParamParser) field.get(null);
            disableSteamAPI = paramParser.disableSteamAPI;
        } catch (Exception ignored) {

        }
        if (CFG.isDesktop() && !disableSteamAPI) {
            FontFix.LOGGER.debug("Steam init");
            SteamManager.init();
            SteamManager.loadSubscribedItems();
            SteamManager.userStats.requestCurrentStats();

        }
        CFG.currentTimeMillis = System.currentTimeMillis();
        this.renderer = new Renderer(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        try {
            Game.loadLowSettings();
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

        try {
            Game.loadSettings();
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

        this.initUIScale();


        Game.loadLanguage();
        Renderer.loadFont(Game.lang.get("font"), "A", Game.settingsManager.FONT_MAIN_SIZE);
        CFG.FONT_BOLD = Renderer.fontMain.size() - 1;
        this.initGame();
        this.initInput();
        CFG.isDesktop = Gdx.app.getType() == Application.ApplicationType.Desktop;
        CFG.isAndroid = Gdx.app.getType() == Application.ApplicationType.Android || Gdx.app.getType() == Application.ApplicationType.iOS;
        CFG.isiOS = Gdx.app.getType() == Application.ApplicationType.iOS;
        ProvinceDraw.updateDrawProvinces();
        CFG.UIScale = CFG.getUIScale();
        Game.mapScale.initDefinedScales();
    }
    @Shadow
    protected final void initUIScale() {
    }

    @Shadow
    protected final void initInput() {
    }

    @Shadow
    protected final void initGame() {}
}
