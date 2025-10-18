package team.rainfall.fontFix;

import team.rainfall.fontFix.config.ColorConfig;
import team.rainfall.fontFix.config.LinkConfig;
import com.badlogic.gdx.utils.Array;


public class PolarisConfigData {
    public Array<LinkConfig> links = new Array<>();
    public Array<ColorConfig> textColors = new Array<>();
    public int SplashScreen_FadeIn = 1500;
    public int SplashScreen_FadeOut = 500;
    public float MainMenu_PanelX = 0.1f;
    public String MainMenu_Alignment = "left";
    public boolean forceFlagNearest = false;
    public boolean extendCharset = false;
    //A simple method to detect encode,may cause detection mistake
    public boolean fastEncodeCheck = true;
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

}
