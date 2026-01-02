package team.rainfall.fontFix;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.FileManager;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.menus.MainMenu;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Json;
import team.rainfall.finality.FinalityLogger;
import team.rainfall.fontFix.config.*;

import java.lang.reflect.Field;

public class Config {
    private static PolarisConfigData configData = null;
    private static AnimationConfig animationConfig = null;
    private static GradientConfig gradientConfig = null;
    private static ElementColorsConfig elementColorsConfig = null;
    public static boolean isConfigLoaded(){
        return configData != null;
    }
    public static void applyElementColorsConfig() {
        if (elementColorsConfig == null) {
            try {
                Json json = new Json();
                json.setIgnoreUnknownFields(true);
                elementColorsConfig = json.fromJson(ElementColorsConfig.class, FileManager.loadFile("rainfall/polaris_core_colors.json"));
            } catch (Exception e) {
                FinalityLogger.error("Failed to load element color config file", e);
                elementColorsConfig = new ElementColorsConfig();
            }
        }
        MainMenu.sparksColors = Color.valueOf(elementColorsConfig.MAIN_MENU_SPARK);
        for (Field declaredField : ElementColorsConfig.class.getDeclaredFields()) {
            try {
                declaredField.setAccessible(true);
                String name = declaredField.getName();
                String colorHex = (String) declaredField.get(elementColorsConfig);
                Color color1 = Color.valueOf(colorHex);
                Colors.class.getDeclaredField(name).set(null, color1);
            } catch (IllegalAccessException | NoSuchFieldException ignored) {}
        }
    }

    public static GradientConfig getGradientConfig() {
        if (gradientConfig == null) {
            try {
                Json json = new Json();
                json.setIgnoreUnknownFields(true);
                gradientConfig = json.fromJson(GradientConfig.class, FileManager.loadFile("rainfall/polaris_core_grad.json"));
            } catch (Exception e) {
                FinalityLogger.error("Failed to load anim config file", e);
                gradientConfig = new GradientConfig();
            }
        }
        return gradientConfig;
    }

    public static AnimationConfig getAnimationConfig() {
        if (animationConfig == null) {
            try {
                Json json = new Json();
                json.setIgnoreUnknownFields(true);
                animationConfig = json.fromJson(AnimationConfig.class, FileManager.loadFile("rainfall/polaris_core_anim.json"));
            } catch (Exception e) {
                FinalityLogger.error("Failed to load anim config file", e);
                animationConfig = new AnimationConfig();
            }
        }
        return animationConfig;
    }

    public static PolarisConfigData getConfig() {
        if (configData == null) {
            try {
                Json json = new Json();
                json.setIgnoreUnknownFields(true);
                json.setElementType(PolarisConfigData.class, "links", LinkConfig.class);
                configData = json.fromJson(PolarisConfigData.class, FileManager.loadFile("rainfall/polaris_core.json"));
                if(CFG.isAndroid() || FontFix.fakeAndroid) {
                    if (Sternstunden.getPackageString().equals("age.of.history3.polaris") || Sternstunden.getPackageString().equals("MoonIsBrightTonight0")) {
                        configData.splashScreen.add(new SplashScreenConfig("polaris", 5000));
                    }
                }
            } catch (Exception e) {
                FinalityLogger.error("Failed to load config file", e);
                buildDefaultConfig();
            }
        }
        applyElementColorsConfig();
        return configData;
    }

    public static void buildDefaultConfig() {
        PolarisConfigData configData1 = new PolarisConfigData();
        configData1.links.add(new LinkConfig("You haven't place the config file!!!", "Age of History 3", "https://www.youtube.com/channel/UCppKzood12fbJhZClXfukFw", "sandbox"));
        configData1.links.add(new LinkConfig("Youtube: ", "Age of History 3", "https://www.youtube.com/channel/UCppKzood12fbJhZClXfukFw", "yt"));
        configData1.links.add(new LinkConfig("Android: ", "Age of History 3", "https://play.google.com/store/apps/details?id=age.of.history3.lukasz.jakowski", "android"));
        configData1.links.add(new LinkConfig("iOS: ", "Age of History 3", "https://apps.apple.com/app/age-of-history-3/id6686394372", "app"));
        configData1.links.add(new LinkConfig("Steam: ", "Age of History 3", "https://store.steampowered.com/app/2772750/Age_of_History_3/", "pc"));
        configData = configData1;


    }
}
