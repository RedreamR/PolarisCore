package team.rainfall.fontFix;

import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.map.map.Map_Data;
import aoc.kingdoms.lukasz.map.technology.TechnologyTree;
import team.rainfall.finality.luminosity2.annotations.Mixin;
import team.rainfall.finality.luminosity2.annotations.Shadow;

import java.util.List;
@Mixin(mixinClass = "aoc.kingdoms.lukasz.map.map.Map")
public class MixinMap {
    public int iActiveMapID = 0;
    public List<Map_Data> lMaps;
    public final void setActiveMapID(int nActiveMapID) {
        FontFix.tried = false;
        FontFix.compactScale = null;
        if (this.iActiveMapID != nActiveMapID) {
            this.iActiveMapID = nActiveMapID;
            this.updateWorldMap();
        }
        Game.DRAW_CITIES_MIN_SCALE = ((Map_Data)this.lMaps.get(this.iActiveMapID)).mapData.DRAW_CITIES_MIN_SCALE;
        Game.DRAW_CIV_NAMES_START_DRAWING_MAP_SCALE = ((Map_Data)this.lMaps.get(this.iActiveMapID)).mapData.DRAW_CIV_NAMES_START_DRAWING_MAP_SCALE;
        Game.DRAW_INNER_BORDERS = ((Map_Data)this.lMaps.get(this.iActiveMapID)).mapData.DRAW_INNER_BORDERS;
        Game.DRAW_OCCUPIED_PROVINCES_MIN_SCALE = ((Map_Data)this.lMaps.get(this.iActiveMapID)).mapData.DRAW_OCCUPIED_PROVINCES_MIN_SCALE;
        Game.DRAW_ARMY_MIN_SCALE = ((Map_Data)this.lMaps.get(this.iActiveMapID)).mapData.DRAW_ARMY_MIN_SCALE;
        Game.DRAW_OCCUPIED_SCALE = ((Map_Data)this.lMaps.get(this.iActiveMapID)).mapData.DRAW_OCCUPIED_SCALE;
        TechnologyTree.MapResearchCost = ((Map_Data)this.lMaps.get(this.iActiveMapID)).mapData.ResearchCost;
    }
    @Shadow
    private void updateWorldMap() {
    }

}
