package team.rainfall.fontFix;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.FileManager;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.SoundsManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import team.rainfall.finality.luminosity2.annotations.Mixin;
import team.rainfall.finality.luminosity2.annotations.Shadow;

import java.util.ArrayList;
import java.util.List;

import static aoc.kingdoms.lukasz.jakowski.SoundsManager.masterVolume;
import static aoc.kingdoms.lukasz.jakowski.SoundsManager.musicVolume;

@Mixin(mixinClass = "aoc.kingdoms.lukasz.jakowski.SoundsManager")
public class MixinSoundsManager {
    public final void playStartMusic() {
        FontFix.playStartMusic();
    }
}
