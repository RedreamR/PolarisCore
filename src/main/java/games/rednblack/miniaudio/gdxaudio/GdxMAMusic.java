//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package games.rednblack.miniaudio.gdxaudio;

import com.badlogic.gdx.audio.Music;
import games.rednblack.miniaudio.MASound;

public class GdxMAMusic implements Music, GdxEndListener {
    private final GdxMiniAudio gdxMiniAudio;
    private final MASound sound;
    private Music.OnCompletionListener listener;

    public GdxMAMusic(MASound sound, GdxMiniAudio gdxMiniAudio) {
        this.sound = sound;
        this.gdxMiniAudio = gdxMiniAudio;
    }

    public void play() {
        this.sound.play();
    }

    public void pause() {
        this.sound.pause();
    }

    public void stop() {
        this.sound.stop();
    }

    public boolean isPlaying() {
        return this.sound.isPlaying();
    }

    public void setLooping(boolean isLooping) {
        this.sound.setLooping(isLooping);
    }

    public boolean isLooping() {
        return this.sound.isLooping();
    }

    public void setVolume(float volume) {
        this.sound.setVolume(volume);
    }

    public float getVolume() {
        return this.sound.getVolume();
    }

    public void setPan(float pan, float volume) {
        this.sound.setPan(pan);
        this.sound.setVolume(volume);
    }

    public void setPosition(float position) {
        this.sound.seekTo(position);
    }

    public float getPosition() {
        return this.sound.getCursorPosition();
    }

    public void dispose() {
        this.sound.dispose();
        this.listener = null;
    }

    public void setOnCompletionListener(Music.OnCompletionListener listener) {
        this.listener = listener;
        this.gdxMiniAudio.addCompletionListener(this.sound.getAddress(), this);
    }

    public void onSoundEnd(long address) {
        if (this.listener != null) {
            this.listener.onCompletion(this);
        }
    }
    public float getLength(){
        return sound.getLength();
    }
}
