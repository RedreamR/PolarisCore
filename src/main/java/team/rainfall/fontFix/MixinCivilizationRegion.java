package team.rainfall.fontFix;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.GlyphLayout_Game;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.jakowski.zOther.Point_XY;
import aoc.kingdoms.lukasz.map.province.Province;
import com.badlogic.gdx.math.CatmullRomSpline;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import team.rainfall.finality.luminosity2.annotations.Mixin;
import aoc.kingdoms.lukasz.map.civilization.CivilizationRegion;
import team.rainfall.finality.luminosity2.annotations.Shadow;
import team.rainfall.fontFix.utils.ProvinceUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Mixin(mixinClass = "aoc.kingdoms.lukasz.map.civilization.CivilizationRegion")
public abstract class MixinCivilizationRegion {
    private int iRegionID;
    private List<Integer> lProvinces = new ArrayList();
    private int iProvincesSize;
    protected List<Integer> lCoastlineProvinces = new ArrayList();
    private List<Integer> shortestLine = new ArrayList();
    private int iMinX = 0;
    private int iMaxX = 0;
    private int iMinY = 0;
    private int iMaxY = 0;
    public int iAveragePointPosX = 0;
    public int iAveragePointPosY = 0;
    private float fontScale = 1.0F;
    private float fontScale2 = 1.0F;
    private float fAngle = 0.0F;
    private float fAngle_Low = 0.0F;
    private int iCharMaxWidth = 0;
    private int iCharMaxHeight = 0;
    public List<Point_XY> lPoints = new ArrayList();
    public List<Matrix4> drawMatrix4 = new ArrayList();
    public Point_XY centerCharXY;
    protected boolean drawName = true;
    private List<Boolean> triedToUse = new ArrayList();
    private int numOfTries = 0;

    // 新增配置常量
    public static float scaleStep = 0.1f;
    public static float maxCivNameScale = 16.0F; // 字体最大缩放限制
    public static int MAX_RETRIES = 1000;
    public static float distanceScale = 0.65f;
    protected final float buildScaleOfText(int nFontID) {
        float outTextH = 1.0F;
        maxCivNameScale = Config.getConfig().maxCivNameScale;
        distanceScale = Config.getConfig().distanceScale;
        scaleStep = Config.getConfig().scaleStep;
        MAX_RETRIES = Config.getConfig().maxCivNameTries;
        try {
            if (this.shortestLine.size() > 1) {
                float iDistance = (float) Math.sqrt(Math.pow(Game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).iCenterShiftX - Game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).iCenterShiftX, 2.0F) + Math.pow(Game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).iCenterShiftY - Game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).iCenterShiftY, 2.0F));
                iDistance *= distanceScale;
                GlyphLayout_Game glyphLayout = new GlyphLayout_Game();
                synchronized (this) {
                    glyphLayout.setText(Renderer.fontBorder.get(nFontID), Game.getCiv(Game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).getCivID()).sCivName_UpperCase);
                    int tempNumOfIterations = 0;
                    float tempScale = this.fontScale;

                    try {
                        while (true) {
                            if (iDistance > glyphLayout.width) {
                                // 修改点：检查是否达到最大缩放限制
                                if (tempScale >= maxCivNameScale) {
                                    this.fontScale = maxCivNameScale;
                                    // 确保最后一次测量使用的是最大缩放，以便获取正确的 outTextH
                                    Renderer.fontBorder.get(nFontID).getData().setScale(maxCivNameScale);
                                    glyphLayout.setText(Renderer.fontBorder.get(nFontID), Game.getCiv(Game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).getCivID()).sCivName_UpperCase);
                                    outTextH = glyphLayout.height;
                                    break;
                                }

                                tempScale += scaleStep;
                                Renderer.fontBorder.get(nFontID).getData().setScale(tempScale);
                                glyphLayout.setText(Renderer.fontBorder.get(nFontID), Game.getCiv(Game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).getCivID()).sCivName_UpperCase);
                                outTextH = glyphLayout.height;
                                if (iDistance < glyphLayout.width) {
                                    this.fontScale = tempScale - scaleStep;
                                    break;
                                }
                            } else {
                                tempScale -= scaleStep;
                                Renderer.fontBorder.get(nFontID).getData().setScale(tempScale);
                                glyphLayout.setText(Renderer.fontBorder.get(nFontID), Game.getCiv(Game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).getCivID()).sCivName_UpperCase);
                                outTextH = glyphLayout.height;
                                if (iDistance > glyphLayout.width) {
                                    this.fontScale = tempScale + scaleStep;
                                    break;
                                }
                            }

                            if (tempNumOfIterations++ > 999) {
                                this.fontScale = 1.0E-4F;
                                break;
                            }
                        }
                    } catch (IndexOutOfBoundsException var13) {
                        this.fontScale = 1.0E-4F;
                    } catch (NullPointerException ex) {
                        this.fontScale = 1.0E-4F;
                        CFG.exceptionStack(ex);

                        try {
                            Game.getCiv(Game.getProvince(this.lProvinces.get(0)).getCivID()).setUpdateRegions(true);
                        } catch (Exception var12) {
                        }
                    } catch (IllegalStateException ex) {
                        this.fontScale = 1.0E-4F;
                        CFG.exceptionStack(ex);
                    }
                }

                this.buildAveragePoint();
                this.buildDrawData(nFontID);
            }
        } catch (NullPointerException var17) {
            this.fontScale = 1.0E-4F;

            try {
                Game.getCiv(Game.getProvince(this.lProvinces.get(0)).getCivID()).setUpdateRegions(true);
            } catch (Exception var11) {
            }
        }

        this.fontScale2 = this.fontScale * 0.875F;
        return outTextH;
    }

    @Shadow
    public abstract void buildAveragePoint();

    @Shadow
    public abstract void buildDrawData(int nFontID);

    public final boolean buildRegionPath() {
        try {
            this.drawName = false;
            this.buildMinMaxBounds();
            if (this.lProvinces.size() == 1) {
                return false;
            } else {
                if (this.lProvinces.size() > 1) {
                    if (!Game.settingsManager.DRAW_CIVILIZATIONS_NAMES_OVER_PROVINCES_IN_GAME) {
                        return false;
                    }

                    int startID = -1;

                    for (int i = 0; i < this.iProvincesSize; ++i) {
                        if (!(Boolean) this.triedToUse.get(i)) {
                            startID = i;
                            break;
                        }
                    }

                    if (startID == -1) {
                        return false;
                    }

                    int fromProvinceID_LEFTRIGHT = startID;
                    int toProvinceID_LEFTRIGHT = startID;
                    int fromProvinceID_RIGHTLEFT = startID;
                    int toProvinceID_RIGHTLEFT = startID;
                    int fromProvinceID_BOTTOM = startID;
                    int toProvinceID_TOP = startID;
                    int fromProvinceID_LR = startID;
                    int toProvinceID_LR = startID;
                    int leftBottomDistance = (int) Math.sqrt(Math.pow(this.iMinX - Game.getProvince(this.lProvinces.get(startID)).getCenterX(), 2.0F) + Math.pow(this.iMaxY - Game.getProvince(this.lProvinces.get(startID)).getCenterY(), 2.0F));
                    int rightTopDistance = (int) Math.sqrt(Math.pow(this.iMaxX - Game.getProvince(this.lProvinces.get(startID)).getCenterX(), 2.0F) + Math.pow(this.iMinY - Game.getProvince(this.lProvinces.get(startID)).getCenterY(), 2.0F));
                    int rightBottomDistance = (int) Math.sqrt(Math.pow(this.iMaxX - Game.getProvince(this.lProvinces.get(startID)).getCenterX(), 2.0F) + Math.pow(this.iMaxY - Game.getProvince(this.lProvinces.get(startID)).getCenterY(), 2.0F));
                    int leftTopDistance = (int) Math.sqrt(Math.pow(this.iMinX - Game.getProvince(this.lProvinces.get(startID)).getCenterX(), 2.0F) + Math.pow(this.iMinY - Game.getProvince(this.lProvinces.get(startID)).getCenterY(), 2.0F));
                    this.triedToUse.add(true);

                    for (int i = startID + 1; i < this.iProvincesSize; ++i) {
                        if (!(Boolean) this.triedToUse.get(i)) {
                            int toPosX = Game.getProvince(this.lProvinces.get(i)).iCenterShiftX;
                            int toPosY = Game.getProvince(this.lProvinces.get(i)).iCenterShiftY;
                            int tempDistance = CivilizationRegion.getLineWidth(this.iMinX, this.iMaxY, toPosX, toPosY);
                            if (tempDistance < leftBottomDistance) {
                                leftBottomDistance = tempDistance;
                                fromProvinceID_LEFTRIGHT = i;
                            }

                            tempDistance = CivilizationRegion.getLineWidth(this.iMaxX, this.iMinY, toPosX, toPosY);
                            if (tempDistance < rightTopDistance) {
                                rightTopDistance = tempDistance;
                                toProvinceID_LEFTRIGHT = i;
                            }

                            tempDistance = CivilizationRegion.getLineWidth(this.iMaxX, this.iMaxY, toPosX, toPosY);
                            if (tempDistance < rightBottomDistance) {
                                rightBottomDistance = tempDistance;
                                fromProvinceID_RIGHTLEFT = i;
                            }

                            tempDistance = CivilizationRegion.getLineWidth(this.iMinX, this.iMinY, toPosX, toPosY);
                            if (tempDistance < leftTopDistance) {
                                leftTopDistance = tempDistance;
                                toProvinceID_RIGHTLEFT = i;
                            }

                            if (Game.getProvince(this.lProvinces.get(fromProvinceID_BOTTOM)).iCenterShiftY < toPosY) {
                                fromProvinceID_BOTTOM = i;
                            }

                            if (Game.getProvince(this.lProvinces.get(toProvinceID_TOP)).iCenterShiftY > toPosY) {
                                toProvinceID_TOP = i;
                            }

                            if (Game.getProvince(this.lProvinces.get(fromProvinceID_LR)).iCenterShiftX > toPosX && toPosY >= this.iMinY + (this.iMaxY - this.iMinY) / 2) {
                                fromProvinceID_LR = i;
                            }

                            if (Game.getProvince(this.lProvinces.get(toProvinceID_LR)).iCenterShiftX < toPosX && toPosY <= this.iMinY + (this.iMaxY - this.iMinY) / 2) {
                                toProvinceID_LR = i;
                            }
                        }
                    }

                    if (this.getLineWidth(fromProvinceID_LEFTRIGHT, toProvinceID_LEFTRIGHT) > this.getLineWidth(fromProvinceID_RIGHTLEFT, toProvinceID_RIGHTLEFT)) {
                        if (this.getLineWidth(fromProvinceID_LEFTRIGHT, toProvinceID_LEFTRIGHT) > this.getLineWidth(fromProvinceID_BOTTOM, toProvinceID_TOP)) {
                            if (this.getLineWidth(fromProvinceID_LEFTRIGHT, toProvinceID_LEFTRIGHT) > this.getLineWidth(fromProvinceID_LR, toProvinceID_LR)) {
                                this.shortestLine.add(fromProvinceID_LEFTRIGHT);
                                this.shortestLine.add(toProvinceID_LEFTRIGHT);
                            } else {
                                this.shortestLine.add(fromProvinceID_LR);
                                this.shortestLine.add(toProvinceID_LR);
                            }
                        } else if (this.getLineWidth(fromProvinceID_BOTTOM, toProvinceID_TOP) > this.getLineWidth(fromProvinceID_LR, toProvinceID_LR)) {
                            this.shortestLine.add(fromProvinceID_BOTTOM);
                            this.shortestLine.add(toProvinceID_TOP);
                        } else {
                            this.shortestLine.add(fromProvinceID_LR);
                            this.shortestLine.add(toProvinceID_LR);
                        }
                    } else if (this.getLineWidth(fromProvinceID_RIGHTLEFT, toProvinceID_RIGHTLEFT) > this.getLineWidth(fromProvinceID_BOTTOM, toProvinceID_TOP)) {
                        if (this.getLineWidth(fromProvinceID_RIGHTLEFT, toProvinceID_RIGHTLEFT) > this.getLineWidth(fromProvinceID_LR, toProvinceID_LR)) {
                            this.shortestLine.add(fromProvinceID_RIGHTLEFT);
                            this.shortestLine.add(toProvinceID_RIGHTLEFT);
                        } else {
                            this.shortestLine.add(fromProvinceID_LR);
                            this.shortestLine.add(toProvinceID_LR);
                        }
                    } else if (this.getLineWidth(fromProvinceID_BOTTOM, toProvinceID_TOP) > this.getLineWidth(fromProvinceID_LR, toProvinceID_LR)) {
                        this.shortestLine.add(fromProvinceID_BOTTOM);
                        this.shortestLine.add(toProvinceID_TOP);
                    } else {
                        this.shortestLine.add(fromProvinceID_LR);
                        this.shortestLine.add(toProvinceID_LR);
                    }

                    if (Game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).getCenterX() > Game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).getCenterX()) {
                        int tempS = this.shortestLine.get(0);
                        this.shortestLine.set(0, this.shortestLine.get(1));
                        this.shortestLine.set(1, tempS);
                    }

                    if (this.shortestLine.isEmpty() || Objects.equals(this.shortestLine.get(0), this.shortestLine.get(1))) {
                        this.shortestLine.clear();
                        this.triedToUse.clear();
                        return false;
                    }

                    Point_XY tD = this.canDrawTextProperly(this.lProvinces.get(this.shortestLine.get(0)), this.lProvinces.get(this.shortestLine.get(1)));
                    if (tD != null) {
                        if (CivilizationRegion.getLineWidth(tD.getPosX(), tD.getPosY(), Game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).iCenterShiftX, Game.getProvince(this.lProvinces.get(this.shortestLine.get(0))).iCenterShiftY) < CivilizationRegion.getLineWidth(tD.getPosX(), tD.getPosY(), Game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).iCenterShiftX, Game.getProvince(this.lProvinces.get(this.shortestLine.get(1))).iCenterShiftY)) {
                            this.triedToUse.set(this.shortestLine.get(0), true);
                        } else {
                            this.triedToUse.set(this.shortestLine.get(1), true);
                        }

                        this.shortestLine.clear();
                        // 修改点：使用常量 MAX_RETRIES 替代硬编码的 100
                        return this.numOfTries++ < MAX_RETRIES && this.buildRegionPath();
                    }

                    this.triedToUse.clear();
                }

                this.updateDrawRegionName();
                return true;
            }
        } catch (StackOverflowError ex) {
            CFG.exceptionStack(ex);
            return false;
        }
    }
    @Shadow
    private final Point_XY canDrawTextProperly(int fromProvinceID, int toProvinceID) {
        return null;
    }
    @Shadow
    public abstract void buildMinMaxBounds();

    @Shadow
    public abstract void updateDrawRegionName();
    @Shadow
    protected int getLineWidth(int fromCenterPosProvinceID, int toCenterPosProvinceID) {
        return 0;
    }

}
