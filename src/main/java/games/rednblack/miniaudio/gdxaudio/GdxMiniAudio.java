//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package games.rednblack.miniaudio.gdxaudio;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.audio.AudioDevice;
import com.badlogic.gdx.audio.AudioRecorder;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.LongMap;
import games.rednblack.miniaudio.MADeviceInfo;
import games.rednblack.miniaudio.MAGroup;
import games.rednblack.miniaudio.MASoundEndListener;
import games.rednblack.miniaudio.MASoundPool;
import games.rednblack.miniaudio.MiniAudio;

@SuppressWarnings("unused")
public class GdxMiniAudio implements Audio {
    private final MiniAudio miniAudio = new MiniAudio();
    private final LongMap<GdxEndListener> completionListeners = new LongMap();
    private final Array<GdxEndListener> listeners = new Array();

    public GdxMiniAudio() {
        MASoundEndListener endListener = (maSound) -> {
            GdxEndListener music = (GdxEndListener)this.completionListeners.get(maSound.getAddress());
            if (music != null) {
                music.onSoundEnd(maSound.getAddress());
            }

            Array.ArrayIterator var3 = this.listeners.iterator();

            while(var3.hasNext()) {
                GdxEndListener listener = (GdxEndListener)var3.next();
                listener.onSoundEnd(maSound.getAddress());
            }

        };
        this.miniAudio.setEndListener(endListener);
    }

    public MiniAudio getMiniAudio() {
        return this.miniAudio;
    }

    public void addCompletionListener(long address, GdxEndListener music) {
        this.completionListeners.put(address, music);
    }

    public AudioDevice newAudioDevice(int samplingRate, boolean isMono) {
        throw new UnsupportedOperationException("Use MiniAudio specific API.");
    }

    public AudioRecorder newAudioRecorder(int samplingRate, boolean isMono) {
        throw new UnsupportedOperationException("Use MiniAudio specific API.");
    }

    public Sound newSound(FileHandle fileHandle) {
        MAGroup group = this.miniAudio.createGroup();
        boolean external = fileHandle.type() == FileType.Absolute;
        MASoundPool soundPool = new MASoundPool(this.miniAudio, fileHandle.path(), (short)0, group, external);
        GdxMASound gdxMASound = new GdxMASound(soundPool, group);
        this.listeners.add(gdxMASound);
        return gdxMASound;
    }

    public Music newMusic(FileHandle file) {
        boolean external = file.type() == FileType.Absolute;
        return new GdxMAMusic(this.miniAudio.createSound(file.path(), (short)0, (MAGroup)null, external), this);
    }

    public boolean switchOutputDevice(String deviceIdentifier) {
        return false;
    }

    public String[] getAvailableOutputDevices() {
        MADeviceInfo[] devices = this.miniAudio.enumerateDevices();
        String[] devicesNames = new String[devices.length];

        for(int i = 0; i < devices.length; ++i) {
            devicesNames[i] = devices[i].name;
        }

        return devicesNames;
    }
}
