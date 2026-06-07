package team.rainfall.fontFix.config;

import com.badlogic.gdx.utils.Array;


public class PolarisConfigData {
    public Array<SplashScreenConfig> splashScreen = new Array<>();
    public Array<LinkConfig> links = new Array<>();
    public Array<ColorConfig> textColors = new Array<>();
    //主菜单面板位置
    public float MainMenu_PanelX = 0.1f;
    public String MainMenu_Alignment = "left";
    public boolean forceFlagNearest = false;
    public boolean extendCharset = false;
    public String defaultCharset = "NONE";
    //A simple method to detect encode,may cause detection mistake
    public boolean fastEncodeCheck = false;
    public boolean forceUTF8 = false;
    public boolean useFluctlight = true;
    public boolean fastLoadFlag = true;
    public boolean applyNSv2 = true;
    public int loadImgVersion = 2;
    public boolean toastWhenPlayMusic = true;
    public int defaultPlayMode = 0;
    public boolean hideBonus = false;
    public boolean seqLoadBG = false;
    public boolean uniqueBGforMainMenu = false;
    public boolean noMainMenuAnimation = false;
    public boolean randNameCache = false;
    public boolean changeBGinInitGame = false;
    public boolean noOverlay = true;
    public float maxCivNameScale = 17.5f;
    public float distanceScale = 0.65f;
    public float scaleStep = 0.05f;
    public int maxCivNameTries = 3000;
    public boolean miniAudio = false;
    InternalString internalString = new InternalString();
    public String getLukaszJakowski(){
        return internalString.lukaszJakowski;
    }
    public String getTeamRainfallQQ(){
        return internalString.teamRainfallQQ;
    }
    public String getTeamRainfallQQurl(){
        return internalString.teamRainfallQQurl;
    }
}
class InternalString{
    String lukaszJakowski = "Lukasz Jakowski";
    String teamRainfallQQ = "Team Rainfall QQ";
    String teamRainfallQQurl = "https://qm.qq.com/q/ALylcUWbUk";
}
