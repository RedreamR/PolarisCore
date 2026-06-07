package team.rainfall.fontFix.utils;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.SoundsManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import games.rednblack.miniaudio.gdxaudio.GdxMiniAudio;
import team.rainfall.finality.FinalityLogger;
import team.rainfall.fontFix.FontFix;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class MusicPool {
    public static MusicPool POOL = new MusicPool();
    public ArrayList<Music> disposedMusic = new ArrayList<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    public Music addMusic(FileHandle fileHandle){
        if(Game.soundsManager.miniAudio == null){
            return Gdx.audio.newMusic(fileHandle);
        } else {
            if(CFG.isDesktop()){
                return Game.soundsManager.miniAudio.newMusic(fileHandle);
            }else {
                FileHandle cacheDir = Gdx.files.local("cache");
                if (!cacheDir.exists()) {
                    cacheDir.mkdirs();
                }

                FileHandle cacheFile = Gdx.files.local("cache/" + fileHandle.name());

                if (cacheFile.exists()) {
                    if (!checkIfTheSame(fileHandle, cacheFile)) {
                        cacheFile.delete();
                        fileHandle.copyTo(cacheFile);
                    }
                } else {
                    fileHandle.copyTo(cacheFile);
                }

                return Game.soundsManager.miniAudio.newMusic(cacheFile);
            }
        }
    }

    public boolean checkIfTheSame(FileHandle fileHandle1,FileHandle fileHandle2){
        if(fileHandle1.length() != fileHandle2.length()) return false;
        long length1 = fileHandle1.length();
        long length2 = fileHandle2.length();

        //The copying spending on small files can be ignored so we directly return false
        //Actually I haven't seen any real music with a length less than 64kb.
        if(length1 < 65536 || length2 < 65536) return false;

        try (RandomAccessFile raf1 = new RandomAccessFile(fileHandle1.file(), "r");
             RandomAccessFile raf2 = new RandomAccessFile(fileHandle2.file(), "r")) {

            long offset = 32767;
            byte[] buf1 = new byte[256];
            byte[] buf2 = new byte[256];

            while (offset + 256 <= length1) {
                raf1.seek(offset);
                raf2.seek(offset);
                raf1.readFully(buf1);
                raf2.readFully(buf2);
                if (!Arrays.equals(buf1, buf2)) return false;
                offset += 16384;
            }
        } catch (FileNotFoundException ignored) {
            //Ignored as the file must be existed
        } catch (Exception e){
            FontFix.LOGGER.error("Failed while comparing files",e);
            return false;
        }
        return true;
    }

    public void disposeMusic(Music music){
        counter.set(0);
        if(music.isPlaying()){
            music.stop();
        }
        disposedMusic.add(music);
    }
    public synchronized void refresh(){
        if(disposedMusic.isEmpty()){
            counter.set(0);
            return;
        }
        if(counter.addAndGet(1) > 60){
            FontFix.LOGGER.debug("INTO CLEAN");
            for (Music music : disposedMusic) {
                music.dispose();
            }
            disposedMusic.clear();
            counter.set(0);
        }
    }
}
