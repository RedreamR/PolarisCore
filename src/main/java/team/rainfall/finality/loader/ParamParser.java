//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package team.rainfall.finality.loader;

public class ParamParser {
    public boolean forceNoVDF = false;
    public boolean isReboot = false;
    public LaunchMode mode;
    public String gameFilePath;
    public boolean disableSteamAPI;

    public ParamParser() {
        this.mode = LaunchMode.ONLY_LAUNCH;
        this.gameFilePath = null;
        this.disableSteamAPI = false;
    }
}
