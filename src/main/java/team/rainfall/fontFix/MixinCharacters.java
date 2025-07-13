package team.rainfall.fontFix;

import aoc.kingdoms.lukasz.map.civilization.CivilizationBonuses;
import team.rainfall.finality.luminosity2.annotations.Mixin;

@Mixin(mixinClass = "aoc.kingdoms.lukasz.jakowski.CharactersManager$Characters")
public class MixinCharacters {
    public String Desc;
    public CivilizationBonuses Bonuses;
}
