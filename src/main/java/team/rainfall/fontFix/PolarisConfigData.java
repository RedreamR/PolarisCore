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
    //A simple method to detect encode,may cause detection mistake
    public boolean fastEncodeCheck = true;
    public boolean useFluctlight = true;
    public boolean fastLoadFlag = true;
    public boolean applyNSv2 = true;
    public int loadImgVersion = 2;
    public boolean toastWhenPlayMusic = true;
}
