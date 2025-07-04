package team.rainfall.fontFix;

import aoc.kingdoms.lukasz.jakowski.Game;
import team.rainfall.finality.luminosity2.annotations.Mixin;

@Mixin(mixinClass =  "aoc.kingdoms.lukasz.map.map.MapScenarios")
public class MixinMapScenarios {
    public final void buildProvincesReligion() {
        for(int i = 0; i < Game.getProvincesSize(); ++i) {
            if (!Game.getProvince(i).getSeaProvince() && Game.getProvince(i).getCivID() > 0) {
                Game.getProvince(i).setReligion_LoadScenario(Game.getCiv(Game.getProvince(i).getCivID()).getReligionID());
                Game.getProvince(i).addCore(Game.getProvince(i).getCivID());
            }
        }
    }
    public final void buildProvincesCores() {

    }
}
