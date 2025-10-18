package team.rainfall.fontFix;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.FileManager;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.map.IdeologiesManager;
import aoc.kingdoms.lukasz.textures.Image;
import com.badlogic.gdx.files.FileHandle;
import team.rainfall.finality.luminosity2.annotations.Mixin;
import team.rainfall.fontFix.utils.RandomNameCache;
import team.rainfall.fontFix.utils.Timer;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
@Mixin(mixinClass = "aoc.kingdoms.lukasz.map.GeneralManager")
public class MixinGeneralManager {
    public Image noGeneral;
    public List<Integer> generalsImagesSize = new ArrayList();
    private ArrayList<List<String>> generalNames = new ArrayList();
    private ArrayList<List<String>> generalSurnames = new ArrayList();

    public String getGeneralRandomName(int iCivID) {
        if(Config.getConfig().randNameCache){
            String s = RandomNameCache.getGeneralRandomName(iCivID);
            if(s != null) return s;
            // - - -
            try {
                return (String)((List)this.generalNames.get(Game.getCiv(iCivID).iGroupID)).get(Game.oR.nextInt(((List)this.generalNames.get(Game.getCiv(iCivID).iGroupID)).size()));
            } catch (Exception var6) {
                CFG.exceptionStack(var6);
                return (String)((List)this.generalNames.get(0)).get(Game.oR.nextInt(((List)this.generalNames.get(0)).size()));
            }
        }

        String sCivTAG = Game.getCiv(iCivID).realTag;
        String civTagWithGP = Game.getCiv(iCivID).realTag + "_gp" + Game.ideologiesManager.getIdeology(Game.getCiv(iCivID).getIdeologyID()).GOV_GROUP_ID;
        String civTagWithGov = Game.getCiv(iCivID).getCivTag();
        FileHandle fileList;

        if (FileManager.loadFile("game/randomNames/names/" + civTagWithGov + ".txt").exists()) {
            fileList = FileManager.loadFile("game/randomNames/names/" + civTagWithGov + ".txt");
            String[] tSplit = fileList.readString().split(";");
            if (tSplit.length > 0) {
                return tSplit[Game.oR.nextInt(tSplit.length)];
            }
        } else if (FileManager.loadFile("game/rulersRandom/link/" + civTagWithGov + ".txt").exists()) {
            fileList = FileManager.loadFile("game/rulersRandom/link/" + civTagWithGov + ".txt");
            sCivTAG = fileList.readString();
            if (FileManager.loadFile("game/randomNames/names/" + sCivTAG + ".txt").exists()) {
                FileHandle fileList2 = FileManager.loadFile("game/randomNames/names/" + sCivTAG + ".txt");
                String[] tSplit = fileList2.readString().split(";");
                if (tSplit.length > 0) {
                    return tSplit[Game.oR.nextInt(tSplit.length)];
                }
            }
        }
        if(!Config.getConfig().fastLoadFlag) {
            if (FileManager.loadFile("game/randomNames/names/" + civTagWithGP + ".txt").exists()) {
                fileList = FileManager.loadFile("game/randomNames/names/" + civTagWithGP + ".txt");
                String[] tSplit = fileList.readString().split(";");
                if (tSplit.length > 0) {
                    return tSplit[Game.oR.nextInt(tSplit.length)];
                }
            } else if (FileManager.loadFile("game/rulersRandom/link/" + civTagWithGP + ".txt").exists()) {
                fileList = FileManager.loadFile("game/rulersRandom/link/" + civTagWithGP + ".txt");
                sCivTAG = fileList.readString();
                if (FileManager.loadFile("game/randomNames/names/" + sCivTAG + ".txt").exists()) {
                    FileHandle fileList2 = FileManager.loadFile("game/randomNames/names/" + sCivTAG + ".txt");
                    String[] tSplit = fileList2.readString().split(";");
                    if (tSplit.length > 0) {
                        return tSplit[Game.oR.nextInt(tSplit.length)];
                    }
                }
            }
        }
        if (FileManager.loadFile("game/randomNames/names/" + sCivTAG + ".txt").exists()) {
            fileList = FileManager.loadFile("game/randomNames/names/" + sCivTAG + ".txt");
            String[] tSplit = fileList.readString().split(";");
            if (tSplit.length > 0) {
                return tSplit[Game.oR.nextInt(tSplit.length)];
            }
        } else if (FileManager.loadFile("game/rulersRandom/link/" + sCivTAG + ".txt").exists()) {
            fileList = FileManager.loadFile("game/rulersRandom/link/" + sCivTAG + ".txt");
            sCivTAG = fileList.readString();
            if (FileManager.loadFile("game/randomNames/names/" + sCivTAG + ".txt").exists()) {
                FileHandle fileList2 = FileManager.loadFile("game/randomNames/names/" + sCivTAG + ".txt");
                String[] tSplit = fileList2.readString().split(";");
                if (tSplit.length > 0) {
                    return tSplit[Game.oR.nextInt(tSplit.length)];
                }
            }
        }

        try {
            return (String)((List)this.generalNames.get(Game.getCiv(iCivID).iGroupID)).get(Game.oR.nextInt(((List)this.generalNames.get(Game.getCiv(iCivID).iGroupID)).size()));
        } catch (Exception var6) {
            Exception ex = var6;
            CFG.exceptionStack(ex);
            return (String)((List)this.generalNames.get(0)).get(Game.oR.nextInt(((List)this.generalNames.get(0)).size()));
        }
    }

    public String getGeneralRandomSurname(int iCivID) {
        if(Config.getConfig().randNameCache) {
            String s = RandomNameCache.getGeneralRandomSurName(iCivID);
            if (s != null) return s;
            // - - -
            try {
                return (String)((List)this.generalSurnames.get(Game.getCiv(iCivID).iGroupID)).get(Game.oR.nextInt(((List)this.generalSurnames.get(Game.getCiv(iCivID).iGroupID)).size()));
            } catch (Exception var6) {
                Exception ex = var6;
                CFG.exceptionStack(ex);
                return (String)((List)this.generalSurnames.get(0)).get(Game.oR.nextInt(((List)this.generalSurnames.get(0)).size()));
            }
        }
        String sCivTAG = Game.getCiv(iCivID).realTag;
        String civTagWithGov = Game.getCiv(iCivID).getCivTag();
        FileHandle fileList;

        if (FileManager.loadFile("game/randomNames/surnames/" + civTagWithGov + ".txt").exists()) {
            fileList = FileManager.loadFile("game/randomNames/surnames/" + civTagWithGov + ".txt");
            String[] tSplit = fileList.readString().split(";");
            return tSplit.length > 0 ? tSplit[Game.oR.nextInt(tSplit.length)] : "";
        } else {
            if (FileManager.loadFile("game/rulersRandom/link/" + civTagWithGov + ".txt").exists()) {
                fileList = FileManager.loadFile("game/rulersRandom/link/" + civTagWithGov + ".txt");
                sCivTAG = fileList.readString();
                if (FileManager.loadFile("game/randomNames/surnames/" + sCivTAG + ".txt").exists()) {
                    FileHandle fileList2 = FileManager.loadFile("game/randomNames/surnames/" + sCivTAG + ".txt");
                    String[] tSplit = fileList2.readString().split(";");
                    if (tSplit.length > 0) {
                        return tSplit[Game.oR.nextInt(tSplit.length)];
                    }
                }
            }
        }

        if (FileManager.loadFile("game/randomNames/surnames/" + sCivTAG + ".txt").exists()) {
            fileList = FileManager.loadFile("game/randomNames/surnames/" + sCivTAG + ".txt");
            String[] tSplit = fileList.readString().split(";");
            return tSplit.length > 0 ? tSplit[Game.oR.nextInt(tSplit.length)] : "";
        } else {
            if (FileManager.loadFile("game/rulersRandom/link/" + sCivTAG + ".txt").exists()) {
                fileList = FileManager.loadFile("game/rulersRandom/link/" + sCivTAG + ".txt");
                sCivTAG = fileList.readString();
                if (FileManager.loadFile("game/randomNames/surnames/" + sCivTAG + ".txt").exists()) {
                    FileHandle fileList2 = FileManager.loadFile("game/randomNames/surnames/" + sCivTAG + ".txt");
                    String[] tSplit = fileList2.readString().split(";");
                    if (tSplit.length > 0) {
                        return tSplit[Game.oR.nextInt(tSplit.length)];
                    }
                }
            }

            try {
                return (String)((List)this.generalSurnames.get(Game.getCiv(iCivID).iGroupID)).get(Game.oR.nextInt(((List)this.generalSurnames.get(Game.getCiv(iCivID).iGroupID)).size()));
            } catch (Exception var6) {
                Exception ex = var6;
                CFG.exceptionStack(ex);
                return (String)((List)this.generalSurnames.get(0)).get(Game.oR.nextInt(((List)this.generalSurnames.get(0)).size()));
            }
        }
    }
}
