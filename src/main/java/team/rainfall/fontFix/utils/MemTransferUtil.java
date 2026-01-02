package team.rainfall.fontFix.utils;

import aoc.kingdoms.lukasz.jakowski.FileManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class MemTransferUtil {
    public static boolean MemTransfer = false;


    public static final void saveMTSettings() {
        FileHandle fileSave;
        if (FileManager.IS_MAC) {
            fileSave = Gdx.files.external("settings/MemoryTransfer.txt");
        } else {
            fileSave = Gdx.files.local("settings/MemoryTransfer.txt");
        }

        fileSave.writeString("" + MemTransfer, false);
    }
}
