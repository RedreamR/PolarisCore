package team.rainfall.fontFix.rulers;

import aoc.kingdoms.lukasz.map.civilization.CivilizationBonuses;
import team.rainfall.finality.luminosity2.annotations.Mixin;

@Mixin(mixinClass = "aoc.kingdoms.lukasz.map.RulersManager$Rulers")
public class MixinRulers{
    public String Desc = "";
    public CivilizationBonuses Bonuses = null;

}
