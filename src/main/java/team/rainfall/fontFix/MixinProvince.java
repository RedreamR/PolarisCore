package team.rainfall.fontFix;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.FileManager;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.map.map.Continents;
import aoc.kingdoms.lukasz.map.province.Province;
import aoc.kingdoms.lukasz.textures.Image;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import team.rainfall.finality.FinalityLogger;
import team.rainfall.finality.luminosity2.annotations.Mixin;
import team.rainfall.finality.luminosity2.annotations.Shadow;

import java.awt.*;
import java.util.List;


@Mixin(mixinClass = "aoc.kingdoms.lukasz.map.province.Province")
public class MixinProvince {
    private java.util.List<Short> lPointsX;
    private List<Short> lPointsY;
    private int iProvinceID;
    private Image provinceBG;
    private int iTranslateProvincePosX;
    private int iLevelOfPort;
    private int iMinX;
    private int iMinY;
    private int iMaxX;
    private int iMaxY;

    public final void loadFromCompact(){
        if(FontFix.tried || FontFix.compactScale != null) return;
        try {
            StringBuilder var10000 = (new StringBuilder()).append("map/").append(Game.map.getFile_ActiveMap_Path()).append("data/").append("scales/");
            FileHandle fileHandle = FileManager.loadFile(var10000.append(this.iLevelOfPort == -4 ? 1 : (int)((float)Game.mapBG.iMapScale / Game.mapBG.iMapExtraScale)).append("/").append("compactScale").toString());
            FontFix.compactScale = CompactScale.readCompactFile(fileHandle);
        } catch (Exception e) {
            FinalityLogger.error("COMPACT LOAD ERROR",e);
            FontFix.tried = true;
        }
    }
    public final void loadProvinceBG() {
        Province self = (Province) (Object) this;
        self.getPointsX(0);
        loadFromCompact();

        try {
            if (this.getSeaProvince() && !GameValues.value.LOAD_SEA_PROVINCES) {
                return;
            }
            if(FontFix.compactScale != null){
                try {
                    //FinalityLogger.debug("COMPACT LOADED " + this.iProvinceID);
                    byte[] data = null;
                    for (CompactScale.Scale scale : FontFix.compactScale.scales) {
                        if(scale.getId() == this.iProvinceID){
                            data = scale.getData();
                        }
                    }
                    Pixmap pixmap = PixmapReader.read(data);
                    this.provinceBG = new Image(new Texture(pixmap), Texture.TextureFilter.Nearest, Texture.TextureWrap.ClampToEdge);
                    pixmap.dispose();
                }catch (Exception ignored){

                }
                return;
            }
            StringBuilder var10000 = (new StringBuilder()).append("map/").append(Game.map.getFile_ActiveMap_Path()).append("data/").append("scales/");
            Continents var10002 = Game.continents;
            if (FileManager.loadFile(var10000.append(this.iLevelOfPort == -4 ? 1 : (int)((float)Game.mapBG.iMapScale / Game.mapBG.iMapExtraScale)).append("/").append(this.iProvinceID).toString()).exists()) {
                var10000 = (new StringBuilder()).append("map/").append(Game.map.getFile_ActiveMap_Path()).append("data/").append("scales/");
                Pixmap pixmap = PixmapIO.readCIM(FileManager.loadFile(var10000.append(this.iLevelOfPort == -4 ? 1 : (int)((float)Game.mapBG.iMapScale / Game.mapBG.iMapExtraScale)).append("/").append(this.iProvinceID).toString()));
                this.provinceBG = new Image(new Texture(pixmap), Texture.TextureFilter.Nearest, Texture.TextureWrap.ClampToEdge);
                pixmap.dispose();
            } else {
                this.buildProvinceBG(false);
                this.loadProvinceBG();
            }
        } catch (GdxRuntimeException ex) {
            CFG.exceptionStack(ex);
            CFG.LOG("Build province BG: " + this.iProvinceID);
            this.buildProvinceBG(false);
            this.loadProvinceBG();
        }

    }
    @Shadow
    private boolean getSeaProvince() {
        return false;
    }

    @Shadow
    private void buildProvinceBG(boolean b) {
    }

    public final void drawProvince_ActiveProvince(SpriteBatch oSB) {
        if (!this.getSeaProvince()) {
            if (this.iLevelOfPort == -4) {
                this.provinceBG.draw(oSB, this.iTranslateProvincePosX + this.iMinX, Game.mapCoords.getPosY() + this.iMinY, (float)Game.mapBG.iMapScale);
            } else {
                this.provinceBG.draw(oSB, this.iTranslateProvincePosX + this.iMinX, Game.mapCoords.getPosY() + this.iMinY, Game.mapBG.iMapExtraScale);
            }

        }
    }

    public final void drawProvince_ActiveProvince(SpriteBatch oSB, Image imgProvince) {
        try {
            if (this.iLevelOfPort == -4) {
                imgProvince.draw(oSB, this.iTranslateProvincePosX + this.iMinX, Game.mapCoords.getPosY() + this.iMinY, (float)Game.mapBG.iMapScale);
            } else {
                imgProvince.draw(oSB, this.iTranslateProvincePosX + this.iMinX, Game.mapCoords.getPosY() + this.iMinY, Game.mapBG.iMapExtraScale);
            }
        } catch (Exception ex) {
            //CFG.exceptionStack(ex);
        }
    }
}
