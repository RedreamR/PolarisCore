package team.rainfall.fontFix;

import aoc.kingdoms.lukasz.jakowski.FileManager;
import aoc.kingdoms.lukasz.textures.Images;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import team.rainfall.finality.FinalityLogger;
import team.rainfall.fontFix.config.LinkConfig;

public class Config {
    private static PolarisConfigData configData = null;
    public static PolarisConfigData getConfig(){
        if(configData == null){
            try {
                Json json = new Json();
                json.setElementType(PolarisConfigData.class, "links", LinkConfig.class);
                configData = json.fromJson(PolarisConfigData.class, FileManager.loadFile("rainfall/polaris_core.json"));
            }catch (Exception e){
                FinalityLogger.error("Failed to load config file", e);
                buildDefaultConfig();
            }
        }
        return configData;
    }
    public static void buildDefaultConfig(){
        PolarisConfigData configData1 = new PolarisConfigData();
        configData1.links.add(new LinkConfig("You haven't place the config file!!!","Age of History 3","https://www.youtube.com/channel/UCppKzood12fbJhZClXfukFw","sandbox"));
        configData1.links.add(new LinkConfig("Youtube: ","Age of History 3","https://www.youtube.com/channel/UCppKzood12fbJhZClXfukFw","yt"));
        configData1.links.add(new LinkConfig("Android: ","Age of History 3","https://play.google.com/store/apps/details?id=age.of.history3.lukasz.jakowski","android"));
        configData1.links.add(new LinkConfig("iOS: ","Age of History 3","https://apps.apple.com/app/age-of-history-3/id6686394372","app"));
        configData1.links.add(new LinkConfig("Steam: ","Age of History 3","https://store.steampowered.com/app/2772750/Age_of_History_3/","pc"));
        configData = configData1;


    }
}
