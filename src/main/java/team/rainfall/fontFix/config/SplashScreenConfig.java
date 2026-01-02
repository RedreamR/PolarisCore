package team.rainfall.fontFix.config;

public class SplashScreenConfig {
    public String path;
    public int duration = 2000;
    public SplashScreenConfig(){

    }
    public SplashScreenConfig(String path,int duration){
        this.path = path;
        this.duration = duration;
    }
}
