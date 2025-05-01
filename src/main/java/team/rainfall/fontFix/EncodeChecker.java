package team.rainfall.fontFix;

import com.badlogic.gdx.files.FileHandle;

public class EncodeChecker {
    public static boolean shouldBeCheck(FileHandle fileHandle){
        if(fileHandle.isDirectory() || !fileHandle.exists()){
            return false;
        }
        if(fileHandle.name().equals("mod.txt") || fileHandle.name().equalsIgnoreCase("aoh.txt") || fileHandle.name().equalsIgnoreCase("startMusic")){
            return true;
        }
        if(fileHandle.path().contains("game/rulersRandom/link/")) return false;
        if (fileHandle.path().contains("map/") && !fileHandle.path().contains("data/")){
            return true;
        }

        if(fileHandle.path().contains("game/rulersRandom") || fileHandle.path().startsWith("saves") || fileHandle.path().contains("game/randomNames") || fileHandle.path().contains("game/rulers") || fileHandle.path().contains("game/advisors") || fileHandle.path().contains("game/characters") || fileHandle.path().contains("audio/music")){
            return true;
        }
        return false;
    }
}
