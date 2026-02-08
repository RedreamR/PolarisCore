//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.jakowski;
import static aoc.kingdoms.lukasz.jakowski.Steam.SteamManager.*;
import aoc.kingdoms.lukasz.jakowski.Steam.SteamManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import team.rainfall.fontFix.FontFix;

import java.util.Collections;

public class FileManager {
    public static LoadInterface loadInterface;
    public static boolean IS_MAC = false;
    public FileManager() {
    }

    public static FileHandle getSaveType(String sFile) {
        return loadInterface.getSaveType(sFile);
    }
    public static void readModsTurnedOff2() {
        modsFoldersAll.addAll(modsFolders);

        try {
            if (Gdx.files.external("settings/ModsOff.txt").exists()) {
                FileHandle file;
                file = Gdx.files.external("settings/ModsOff.txt");
                String tempTags = file.readString();
                modsTurnedOff.clear();
                String[] split = tempTags.split(";");
                Collections.addAll(modsTurnedOff, split);
                for (String s : modsTurnedOff) {
                    for (int j = modsFolders.size() - 1; j >= 0; --j) {
                        if (s.equals(modsFolders.get(j))) {
                            modsFolders.remove(j);
                            break;
                        }
                    }
                }
                modsFoldersSize = modsFolders.size();
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }
    public static void initLoadInterface() {
        if(CFG.isAndroid() && FontFix.isLocalStorage()){
            if(!Gdx.files.external("mods").exists()){
                Gdx.files.external("mods").mkdirs();
            }
            FileHandle[] files;
            files = Gdx.files.external("mods").list();
            for(FileHandle file : files) {
                SteamManager.modsFolders.add("mods/" + file.name() + "/");
            }
            SteamManager.modsFoldersSize = SteamManager.modsFolders.size();
            readModsTurnedOff2();
        }

        if (CFG.isDesktop()) {
            if (IS_MAC) {
                loadInterface = new LoadInterface() {
                    public FileHandle loadFile(String sFile) {
                        for(int i = 0; i < SteamManager.modsFoldersSize; ++i) {
                            if (Gdx.files.external(SteamManager.modsFolders.get(i) + sFile).exists()) {
                                return Gdx.files.external(SteamManager.modsFolders.get(i) + sFile);
                            }

                            if (Gdx.files.internal(SteamManager.modsFolders.get(i) + sFile).exists()) {
                                return Gdx.files.internal(SteamManager.modsFolders.get(i) + sFile);
                            }
                        }

                        for(int i = 0; i < SteamManager.itemsInstalledSize; ++i) {
                            if (Gdx.files.absolute(SteamManager.itemsInstalled.get(i).getFolder() + "/" + sFile).exists()) {
                                return Gdx.files.absolute(SteamManager.itemsInstalled.get(i).getFolder() + "/" + sFile);
                            }
                        }

                        if (Gdx.files.external(sFile).exists()) {
                            return Gdx.files.external(sFile);
                        } else {
                            return Gdx.files.internal(sFile);
                        }
                    }

                    public FileHandle getSaveType(String sFile) {
                        return Gdx.files.external(sFile);
                    }
                };
            } else {
                loadInterface = new LoadInterface() {
                    public FileHandle loadFile(String sFile) {
                        for(int i = 0; i < SteamManager.modsFoldersSize; ++i) {
                            if (Gdx.files.internal(SteamManager.modsFolders.get(i) + sFile).exists()) {
                                return Gdx.files.internal(SteamManager.modsFolders.get(i) + sFile);
                            }
                        }

                        for(int i = 0; i < SteamManager.itemsInstalledSize; ++i) {
                            if (Gdx.files.absolute(SteamManager.itemsInstalled.get(i).getFolder() + "/" + sFile).exists()) {
                                return Gdx.files.absolute(SteamManager.itemsInstalled.get(i).getFolder() + "/" + sFile);
                            }
                        }
                        return Gdx.files.internal(sFile);
                    }

                    public FileHandle getSaveType(String sFile) {
                        return Gdx.files.local(sFile);
                    }
                };
            }
        } else {
            loadInterface = new LoadInterface() {
                public FileHandle loadFile(String sFile) {
                    if(FontFix.isLocalStorage()){
                        for(int i = 0; i < SteamManager.modsFoldersSize; ++i) {
                            if (Gdx.files.external(SteamManager.modsFolders.get(i) + sFile).exists()) {
                                return Gdx.files.external(SteamManager.modsFolders.get(i) + sFile);
                            }
                        }
                        return Gdx.files.external(sFile).exists() ? Gdx.files.external(sFile): (Gdx.files.local(sFile).exists() ? Gdx.files.local(sFile) : Gdx.files.internal(sFile));
                    }
                    return Gdx.files.local(sFile).exists() ? Gdx.files.local(sFile) : Gdx.files.internal(sFile);
                }

                public FileHandle getSaveType(String sFile) {
                    if(FontFix.isLocalStorage()){
                        return Gdx.files.external(sFile);
                    }else {
                    return Gdx.files.local(sFile);
                    }
                }
            };
        }

    }

    public static FileHandle loadFile(String sFile) {
        return loadInterface.loadFile(sFile);
    }

    public interface LoadInterface {
        FileHandle loadFile(String var1);

        FileHandle getSaveType(String var1);
    }
}
