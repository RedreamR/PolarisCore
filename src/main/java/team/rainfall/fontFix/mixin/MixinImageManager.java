package team.rainfall.fontFix.mixin;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.FileManager;
import aoc.kingdoms.lukasz.jakowski.Steam.SteamManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.GdxRuntimeException;
import team.rainfall.finality.luminosity2.annotations.Mixin;
import team.rainfall.fontFix.FontFix;

@SuppressWarnings("unused")
@Mixin(mixinClass = "aoc.kingdoms.lukasz.textures.ImageManager")
public class MixinImageManager {
    public static final Texture loadTexture(String sFile, Pixmap.Format nFormat) {
        try {
            try {
                if (CFG.isDesktop()) {
                    if (FileManager.IS_MAC) {
                        for(int i = 0; i < SteamManager.modsFoldersSize; ++i) {
                            if (Gdx.files.external(SteamManager.modsFolders.get(i) + sFile).exists()) {
                                return new Texture(Gdx.files.external(SteamManager.modsFolders.get(i) + sFile), nFormat, false);
                            }
                        }
                    } else {
                        for(int i = 0; i < SteamManager.modsFoldersSize; ++i) {
                            if (Gdx.files.internal(SteamManager.modsFolders.get(i) + sFile).exists()) {
                                return new Texture(Gdx.files.internal(SteamManager.modsFolders.get(i) + sFile), nFormat, false);
                            }
                        }
                    }

                    for(int i = 0; i < SteamManager.itemsInstalledSize; ++i) {
                        if (Gdx.files.absolute(SteamManager.itemsInstalled.get(i).getFolder() + "/" + sFile).exists()) {
                            return new Texture(Gdx.files.absolute(SteamManager.itemsInstalled.get(i).getFolder() + "/" + sFile), nFormat, false);
                        }
                    }
                }
            } catch (Exception ex) {
                CFG.exceptionStack(ex);
            }
            if(CFG.isAndroid() && FontFix.isLocalStorage()){
                if(FileManager.loadFile(sFile).exists()) return new Texture(FileManager.loadFile(sFile), nFormat, false);
            }
            return FileManager.IS_MAC && Gdx.files.external(sFile).exists() ? new Texture(Gdx.files.external(sFile), nFormat, false) : new Texture(Gdx.files.internal(sFile), nFormat, false);
        } catch (GdxRuntimeException ex) {
            CFG.exceptionStack(ex);
            return new Texture("gfx/imageNotFound.png");
        }
    }
}
