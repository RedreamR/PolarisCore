//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.map.province;

import aoc.kingdoms.lukasz.jakowski.AA_KeyManager;
import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game_Ages;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.jakowski.SoundsManager;
import aoc.kingdoms.lukasz.map.BuildingsManager;
import aoc.kingdoms.lukasz.map.ColonizationManager;
import aoc.kingdoms.lukasz.map.FormableCivManager;
import aoc.kingdoms.lukasz.map.ResourcesManager;
import aoc.kingdoms.lukasz.map.army.ArmyManager;
import aoc.kingdoms.lukasz.map.army.ArmyRecruit;
import aoc.kingdoms.lukasz.map.diplomacy.DiplomacyManager;
import aoc.kingdoms.lukasz.menu.ColorPicker;
import aoc.kingdoms.lukasz.menu.Colors;
import aoc.kingdoms.lukasz.menu.MenuManager;
import aoc.kingdoms.lukasz.menu_element.Toast;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Image;
import aoc.kingdoms.lukasz.menu_element.menuElementHover.MenuElement_HoverElement_Type_Text;
import aoc.kingdoms.lukasz.menusInGame.InGame_CivBonuses;
import aoc.kingdoms.lukasz.menusInGame.InGame_MoveCapital_PopUp;
import aoc.kingdoms.lukasz.menusInGame.InGame_ReleaseAVassal;
import aoc.kingdoms.lukasz.menusInGame.InGame_War;
import aoc.kingdoms.lukasz.menusInGame.InGame_Wonder;
import aoc.kingdoms.lukasz.menusInGame.Buildings.InGame_BuildingsGroup;
import aoc.kingdoms.lukasz.menusInGame.Civ.InGame_Civ;
import aoc.kingdoms.lukasz.menusInGame.Court.InGame_Court;
import aoc.kingdoms.lukasz.menusInGame.Court.InGame_Court_Buildings2;
import aoc.kingdoms.lukasz.menusInGame.Court.InGame_Court_IncreaseGrowthRate;
import aoc.kingdoms.lukasz.menusInGame.Court.InGame_Court_IncreaseManpower;
import aoc.kingdoms.lukasz.menusInGame.Court.InGame_Court_IncreaseTaxEfficiency;
import aoc.kingdoms.lukasz.menusInGame.Court.InGame_Court_InvestInEconomy;
import aoc.kingdoms.lukasz.menusInGame.Diplomacy.InGame_SellProvince;
import aoc.kingdoms.lukasz.menusInGame.Goods.InGame_GoodsMarket;
import aoc.kingdoms.lukasz.menusInGame.Peace.InGame_Peace;
import aoc.kingdoms.lukasz.menusInGame.Province.InGame_ProvinceArmy;
import aoc.kingdoms.lukasz.menusInGame.Province.InGame_ProvinceBonuses;
import aoc.kingdoms.lukasz.menusInGame.Province.InGame_ProvinceInfo;
import aoc.kingdoms.lukasz.menusInGame.RecruitArmy.InGame_RecruitArmy_NewArmy;
import aoc.kingdoms.lukasz.menusInGame.RecruitArmy.InGame_RecruitMercenaries;
import aoc.kingdoms.lukasz.menusMapEditor.EditorMapContinents;
import aoc.kingdoms.lukasz.menusMapEditor.EditorMapEconomy;
import aoc.kingdoms.lukasz.menusMapEditor.EditorMapGeoRegions;
import aoc.kingdoms.lukasz.menusMapEditor.EditorMapGrowthRate;
import aoc.kingdoms.lukasz.menusMapEditor.EditorMapOptimizationRegions;
import aoc.kingdoms.lukasz.menusMapEditor.EditorMapResources;
import aoc.kingdoms.lukasz.menusMapEditor.EditorMapTerrainType;
import aoc.kingdoms.lukasz.menusMapEditor.EditorSelectProvinces;
import aoc.kingdoms.lukasz.menusScenarioEditor.ScenarioAssign;
import aoc.kingdoms.lukasz.menusScenarioEditor.ScenarioAssign_InGame;
import aoc.kingdoms.lukasz.menusScenarioEditor.ScenarioCivilizations;
import aoc.kingdoms.lukasz.menusScenarioEditor.Diplomacy.ScenarioRelationsList;
import aoc.kingdoms.lukasz.menusScenarioEditor.Technology.ScenarioTechnologyCivs;
import aoc.kingdoms.lukasz.menusScenarioEditor.Wasteland.ScenarioWasteland;
import aoc.kingdoms.lukasz.textures.Images;
import team.rainfall.finality.FinalityLogger;
import team.rainfall.fontFix.FontFix;

import java.util.ArrayList;
import java.util.List;

public class ProvinceTouchExtraAction {
    public static ExtraAction actionDown_ExtraAction;
    public static ExtraAction actionMove_ExtraAction;
    public static ExtraAction actionUp_ExtraAction;
    public static ExtraAction actionUp_SetActiveProvinceID_ExtraAction;

    public ProvinceTouchExtraAction() {
    }

    public static final void updateExtraAction() {
        updateActionDown_ExtraAction();
        updateActionMove_ExtraAction();
        updateActionUp_ExtraAction();
        updateActionUp_SetActiveProvinceID_ExtraAction();
    }

    private static void updateActionDown_ExtraAction() {
        actionDown_ExtraAction = (nPosX, nPosY, nPointer, button) -> {
        };
    }

    private static void updateActionMove_ExtraAction() {
        actionMove_ExtraAction = (nPosX, nPosY, nPointer, button) -> {
        };
    }

    private static void updateActionUp_ExtraAction() {
        actionUp_ExtraAction = (nPosX, nPosY, nPointer, button) -> {
        };
    }

    private static void updateActionUp_SetActiveProvinceID_ExtraAction() {
        if (Game.menuManager.getInGame()) {
            if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_RECRUIT_ARMY) {
                actionUp_SetActiveProvinceID_ExtraAction = (nPosX, nPosY, nPointer, button) -> {
                    if (Game.iActiveProvince >= 0 && (Game.getProvince(Game.iActiveProvince).getCivID() == Game.player.iCivID || Game.getCiv(Game.getProvince(Game.iActiveProvince).getCivID()).getPuppetOfCivID() == Game.player.iCivID)) {
                        if (button != 0) {
                            if (button == 1) {
                                Game.soundsManager.playRecruitArmyCancel();
                                if (!Game.getCiv(Game.player.iCivID).cancelRecruitArmy(new ArmyRecruit(Game.iActiveProvince, Game.armyRecruit.unitID, Game.armyRecruit.armyID, Game.armyRecruit.toArmyKey))) {
                                    Game.menuManager.setVisibleInGame_RecruitArmy(false);
                                    if (Game.menuManager.getVisibleInGame_Armies()) {
                                        Game.menuManager.setVisibleInGame_Armies(false);
                                    }

                                    Game.menuManager.rebuildInGame_ProvinceInfo(true);
                                }
                            }
                        } else if (AA_KeyManager.CTRL_HOLD) {
                            if (Game.getCiv(Game.player.iCivID).fGold < (float)ArmyManager.getRecruitmentCost(Game.player.iCivID, Game.iActiveProvince, Game.armyRecruit.unitID, Game.armyRecruit.armyID)) {
                                Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2((float) ArmyManager.getRecruitmentCost(Game.player.iCivID, Game.iActiveProvince, Game.armyRecruit.unitID, Game.armyRecruit.armyID), 10), Images.gold);
                                Game.soundsManager.playSound(SoundsManager.SOUND_PROVINCE, SoundsManager.PERC_VOLUME_SELECT_PROVINCE);
                            } else if (Game.getCiv(Game.player.iCivID).fManpower < (double) Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).REGIMENT_SIZE) {
                                Game.menuManager.addToastInsufficient(Game.lang.get("Manpower") + ": ", CFG.getPrecision2(Game.getCiv(Game.player.iCivID).fManpower, 1) + " / " + Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).REGIMENT_SIZE, Game_Calendar.IMG_MANPOWER);
                                Game.soundsManager.playSound(SoundsManager.SOUND_PROVINCE, SoundsManager.PERC_VOLUME_SELECT_PROVINCE);
                            } else {
                                for(int i = 0; i < 5; ++i) {
                                    Game.getCiv(Game.player.iCivID).recruitArmy(new ArmyRecruit(Game.iActiveProvince, Game.armyRecruit.unitID, Game.armyRecruit.armyID, Game.armyRecruit.toArmyKey));
                                }

                                Game.soundsManager.playRecruitArmy();
                            }
                        } else if (!AA_KeyManager.SHIFT_HOLD) {
                            if (Game.getCiv(Game.player.iCivID).fGold < (float)ArmyManager.getRecruitmentCost(Game.player.iCivID, Game.iActiveProvince, Game.armyRecruit.unitID, Game.armyRecruit.armyID)) {
                                Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2((float) ArmyManager.getRecruitmentCost(Game.player.iCivID, Game.iActiveProvince, Game.armyRecruit.unitID, Game.armyRecruit.armyID), 10), Images.gold);
                                Game.soundsManager.playSound(SoundsManager.SOUND_PROVINCE, SoundsManager.PERC_VOLUME_SELECT_PROVINCE);
                            } else if (Game.getCiv(Game.player.iCivID).fManpower < (double) Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).REGIMENT_SIZE) {
                                Game.menuManager.addToastInsufficient(Game.lang.get("Manpower") + ": ", CFG.getPrecision2(Game.getCiv(Game.player.iCivID).fManpower, 1) + " / " + Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).REGIMENT_SIZE, Game_Calendar.IMG_MANPOWER);
                                Game.soundsManager.playSound(SoundsManager.SOUND_PROVINCE, SoundsManager.PERC_VOLUME_SELECT_PROVINCE);
                            } else if (Game.getCiv(Game.player.iCivID).recruitArmy(new ArmyRecruit(Game.iActiveProvince, Game.armyRecruit.unitID, Game.armyRecruit.armyID, Game.armyRecruit.toArmyKey))) {
                                Game.soundsManager.playRecruitArmy();
                            }
                        } else if (Game.getCiv(Game.player.iCivID).fGold < (float)ArmyManager.getRecruitmentCost(Game.player.iCivID, Game.iActiveProvince, Game.armyRecruit.unitID, Game.armyRecruit.armyID)) {
                            Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2((float) ArmyManager.getRecruitmentCost(Game.player.iCivID, Game.iActiveProvince, Game.armyRecruit.unitID, Game.armyRecruit.armyID), 10), Images.gold);
                            Game.soundsManager.playSound(SoundsManager.SOUND_PROVINCE, SoundsManager.PERC_VOLUME_SELECT_PROVINCE);
                        } else if (Game.getCiv(Game.player.iCivID).fManpower < (double) Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).REGIMENT_SIZE) {
                            Game.menuManager.addToastInsufficient(Game.lang.get("Manpower") + ": ", CFG.getPrecision2(Game.getCiv(Game.player.iCivID).fManpower, 1) + " / " + Game.gameAges.lAges.get(Game_Calendar.CURRENT_AGEID).REGIMENT_SIZE, Game_Calendar.IMG_MANPOWER);
                            Game.soundsManager.playSound(SoundsManager.SOUND_PROVINCE, SoundsManager.PERC_VOLUME_SELECT_PROVINCE);
                        } else {
                            int added = 1;
                            Game.getCiv(Game.player.iCivID).recruitArmy(new ArmyRecruit(Game.iActiveProvince, Game.armyRecruit.unitID, Game.armyRecruit.armyID, Game.armyRecruit.toArmyKey));

                            for(int i = 0; i < Game.getProvince(Game.iActiveProvince).getNeighboringProvincesSize() && added < 5; ++i) {
                                if (Game.getProvince(Game.getProvince(Game.iActiveProvince).getNeighboringProvinces(i)).getCivID() == Game.player.iCivID || Game.getCiv(Game.getProvince(Game.getProvince(Game.iActiveProvince).getNeighboringProvinces(i)).getCivID()).getPuppetOfCivID() == Game.player.iCivID) {
                                    Game.getCiv(Game.player.iCivID).recruitArmy(new ArmyRecruit(Game.getProvince(Game.iActiveProvince).getNeighboringProvinces(i), Game.armyRecruit.unitID, Game.armyRecruit.armyID, Game.armyRecruit.toArmyKey));
                                    ++added;
                                }
                            }

                            for(int i = added; i < 5; ++i) {
                                Game.getCiv(Game.player.iCivID).recruitArmy(new ArmyRecruit(Game.iActiveProvince, Game.armyRecruit.unitID, Game.armyRecruit.armyID, Game.armyRecruit.toArmyKey));
                            }

                            Game.soundsManager.playRecruitArmy();
                        }

                        ProvinceHover.provinceHoverBuild.build();
                        Game.menuManager.getInGame_RecruitSameType_UpdateLanguage();
                    }

                };
            } else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_NEW_ARMY_CHOOSE_PROVINCE) {
                actionUp_SetActiveProvinceID_ExtraAction = (nPosX, nPosY, nPointer, button) -> {
                    if (Game.iActiveProvince >= 0) {
                        if (button == 0) {
                            if (Game.getProvince(Game.iActiveProvince).getCivID() == Game.player.iCivID || Game.getCiv(Game.getProvince(Game.iActiveProvince).getCivID()).getPuppetOfCivID() == Game.player.iCivID) {
                                InGame_RecruitArmy_NewArmy.iProvinceID = Game.iActiveProvince;
                                Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                            }
                        } else if (button == 1) {
                            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                        }

                        ProvinceHover.provinceHoverBuild.build();
                    }

                };
            } else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_NUKE_CHOOSE_PROVINCE) {
                actionUp_SetActiveProvinceID_ExtraAction = (nPosX, nPosY, nPointer, button) -> {
                    if (Game.iActiveProvince >= 0) {
                        if (button == 0) {
                            if (Game.getProvince(Game.iActiveProvince).getCivID() == Game.player.iCivID) {
                                Game.menuManager.addToastInsufficient(Game.getProvince(Game.iActiveProvince).getProvinceName() + ": ", Game.getCiv(Game.getProvince(Game.iActiveProvince).getCivID()).getCivName(), Images.nuke);
                            } else if (!DiplomacyManager.isAtWar(Game.player.iCivID, Game.getProvince(Game.iActiveProvince).getCivID())) {
                                Game.menuManager.addToast_Error(Game.lang.get("AtPeace"), Images.peace);
                            } else {
                                Game.dropAtomicBomb(Game.player.iCivID, Game.iActiveProvince);
                                if (Game.getCiv(Game.player.iCivID).getNukes() <= 0) {
                                    Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                                }
                            }
                        } else if (button == 1) {
                            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                        }

                        ProvinceHover.provinceHoverBuild.build();
                    }

                };
            } else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_SELL_PROVINCES) {
                actionUp_SetActiveProvinceID_ExtraAction = (nPosX, nPosY, nPointer, button) -> {
                    if (Game.iActiveProvince >= 0) {
                        if (button == 0) {
                            if (Game.getProvince(Game.iActiveProvince).getCivID() == Game.player.iCivID) {
                                InGame_SellProvince.getSellProvince(Game.iActiveProvince);
                                Game.menuManager.addToastGold(Game.getProvince(Game.iActiveProvince).getProvinceName() + " -> " + Game.getCiv(Game.getProvince(Game.iActiveProvince).getCivID()).getCivName(), Images.provinces);
                            } else if (Game.getProvince(Game.iActiveProvince).getCivID() > 0) {
                                Game.menuManager.addToast_Error(Game.getCiv(Game.getProvince(Game.iActiveProvince).getCivID()).getCivName(), Images.council);
                            }
                        } else if (button == 1) {
                            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                        }

                        ProvinceHover.provinceHoverBuild.build();
                    }

                };
            } else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_COLONIZE_CHOOSE_PROVINCE) {
                actionUp_SetActiveProvinceID_ExtraAction = (nPosX, nPosY, nPointer, button) -> {
                    if (Game.iActiveProvince >= 0) {
                        if (button == 0) {
                            if (Game.getProvince(Game.iActiveProvince).getCivID() > 0) {
                                Game.menuManager.addToast_Error(Game.lang.get("ThisProvinceCanNotBeColonized"), Images.populationGrowth);
                            } else if (Game.getCiv(Game.player.iCivID).fGold < GameValues.colonization.ALLOW_COLONIZATION_BY_SPENDING_GOLD_COST) {
                                Game.menuManager.addToast_Error(Game.lang.get("InsufficientGold") + ": " + CFG.getPrecision2(GameValues.colonization.ALLOW_COLONIZATION_BY_SPENDING_GOLD_COST, 1), Images.gold);
                            } else {
                                ColonizationManager.establishSettlement_Gold(Game.player.iCivID, Game.iActiveProvince);
                                Game.menuManager.addToastGold(Game.lang.get("SettlementEstablished"), Images.populationGrowth);
                                if (Game.getCiv(Game.player.iCivID).fGold < GameValues.colonization.ALLOW_COLONIZATION_BY_SPENDING_GOLD_COST) {
                                    Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                                }
                            }
                        } else if (button == 1) {
                            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                        }

                        ProvinceHover.provinceHoverBuild.build();
                    }

                };
            } else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_WARS) {
                actionUp_SetActiveProvinceID_ExtraAction = (nPosX, nPosY, nPointer, button) -> {
                    if (Game.iActiveProvince >= 0) {
                        if (button == 0) {
                            if (Game.getProvince(Game.iActiveProvince).getCivID() > 0) {
                                if (Game.getCiv(Game.getProvince(Game.iActiveProvince).getCivID()).diplomacy.isAtWar()) {
                                    Game.menuManager.addToastBonus(Game.getCiv(Game.getProvince(Game.iActiveProvince).getCivID()).getCivName() + ": ", CFG.getNumberWithSpaces("" + Game.getCiv(Game.getProvince(Game.iActiveProvince).getCivID()).diplomacy.iWarsSize), Images.war, Game.getProvince(Game.iActiveProvince).getCivID());
                                    Game.clearActiveArmy();
                                    Game.menuManager.showInGame_Battle_HideMenus();
                                    String toWarKey = Game.getCiv(Game.getProvince(Game.iActiveProvince).getCivID()).diplomacy.lWars.get(Game.oR.nextInt(Game.getCiv(Game.getProvince(Game.iActiveProvince).getCivID()).diplomacy.lWars.size()));
                                    if (Game.menuManager.getVisibleInGame_War() && InGame_War.key == toWarKey) {
                                        Game.menuManager.setVisibleInGame_War(false);
                                    } else {
                                        InGame_War.key = toWarKey;
                                        Game.menuManager.rebuildInGame_War();
                                    }
                                } else {
                                    Game.menuManager.addToastBonus(Game.getCiv(Game.getProvince(Game.iActiveProvince).getCivID()).getCivName() + ": ", Game.lang.get("AtPeace"), Images.peace, Game.getProvince(Game.iActiveProvince).getCivID());
                                }
                            }
                        } else if (button == 1) {
                            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                        }

                        ProvinceHover.provinceHoverBuild.build();
                    }

                };
            } else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_MERCENARIES_CHOOSE_PROVINCE) {
                actionUp_SetActiveProvinceID_ExtraAction = (nPosX, nPosY, nPointer, button) -> {
                    if (Game.iActiveProvince >= 0) {
                        if (button == 0) {
                            if (Game.getProvince(Game.iActiveProvince).getCivID() == Game.player.iCivID) {
                                InGame_RecruitMercenaries.confirm(Game.iActiveProvince);
                                Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                            }
                        } else if (button == 1) {
                            Game.mapModes.setActiveViewID(Game.mapModes.MODE_DEFAULT);
                        }

                        ProvinceHover.provinceHoverBuild.build();
                    }

                };
            } else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_PEACE_VIEW) {
                actionUp_SetActiveProvinceID_ExtraAction = (nPosX, nPosY, nPointer, button) -> {
                    if (Game.iActiveProvince >= 0) {
                        ProvinceTouchExtraAction.actionPeaceView(Game.iActiveProvince, false);
                    }

                };
            } else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_RELEASE_VASSAL) {
                actionUp_SetActiveProvinceID_ExtraAction = (nPosX, nPosY, nPointer, button) -> {
                    if (Game.iActiveProvince >= 0) {
                        ProvinceTouchExtraAction.actionReleaseVassal(Game.iActiveProvince, false);
                        Game.menuManager.rebuildInGame_ReleaseAVassal_SavePos();
                    }

                };
            } else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_BUILDING) {
                actionUp_SetActiveProvinceID_ExtraAction = (nPosX, nPosY, nPointer, button) -> {
                    if (Game.iActiveProvince >= 0) {
                        if (button == 0) {
                            ProvinceTouchExtraAction.actionBuilding(Game.iActiveProvince);
                        } else if (button == 1) {
                            Game.getProvince(Game.iActiveProvince).cancelBuildingConstruction(Game.getProvince(Game.iActiveProvince).getCivID(), InGame_Court_Buildings2.oBuildingID.getPosX(), InGame_Court_Buildings2.oBuildingID.getPosY());
                        }

                        ProvinceHover.provinceHoverBuild.build();
                    }

                };
            } else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_CONVERT_RELIGION) {
                actionUp_SetActiveProvinceID_ExtraAction = (nPosX, nPosY, nPointer, button) -> {
                    if (Game.iActiveProvince >= 0) {
                        if (button == 0 && Game.getProvince(Game.iActiveProvince).getCivID() == Game.player.iCivID && Game.getProvince(Game.iActiveProvince).getReligion() != Game.getCiv(Game.getProvince(Game.iActiveProvince).getCivID()).getReligionID() && !Game.getProvince(Game.iActiveProvince).addReligionConversion()) {
                            Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2((float)Game.religionManager.getReligionConversionCost(Game.iActiveProvince), 100), Images.gold);
                        }

                        ProvinceHover.provinceHoverBuild.build();
                    }

                };
            } else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_CORE) {
                actionUp_SetActiveProvinceID_ExtraAction = (nPosX, nPosY, nPointer, button) -> {
                    if (Game.iActiveProvince >= 0) {
                        if (button == 0 && Game.getProvince(Game.iActiveProvince).getCivID() == Game.player.iCivID && !Game.getProvince(Game.iActiveProvince).haveACore(Game.player.iCivID) && Game.getProvince(Game.iActiveProvince).coreCreation == null && !Game.getProvince(Game.iActiveProvince).addCoreCreation()) {
                            Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2(Game.getCoreCreationCost(Game.iActiveProvince), 100), Images.gold);
                        }

                        ProvinceHover.provinceHoverBuild.build();
                    }

                };
            } else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_INVEST_IN_ECONOMY) {
                actionUp_SetActiveProvinceID_ExtraAction = (nPosX, nPosY, nPointer, button) -> {
                    if (Game.iActiveProvince >= 0) {
                        if (button == 0) {
                            if (!AA_KeyManager.CTRL_HOLD && !AA_KeyManager.SHIFT_HOLD) {
                                if (Game.getProvince(Game.iActiveProvince).getCivID() == Game.player.iCivID) {
                                    for(int u = 0; u < InGame_Court_InvestInEconomy.CLICK_X_TIMES; ++u) {
                                        if (Game.canInvestInEconomy(Game.iActiveProvince)) {
                                            Game.menuManager.addToastGold(Game.lang.get("IncreasePopulationGrowthRate"), Images.populationUp);
                                            Game.menuManager.addToastInsufficient(Game.lang.get("MaximumEconomy") + ": ", CFG.getPrecision2(Game.getProvince(Game.iActiveProvince).getEconomyWithBonuses(), 10) + " / " + CFG.getPrecision2(Game.getMaxEconomy(Game.iActiveProvince), 10), Game_Calendar.IMG_ECONOMY);
                                            break;
                                        }

                                        if (Game.getCiv(Game.player.iCivID).fLegacy < Game.getInvestCost_Legacy(Game.iActiveProvince)) {
                                            Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientLegacy") + ": ", CFG.getPrecision2(Game.getInvestCost_Legacy(Game.iActiveProvince), 100), Images.legacy);
                                        } else {
                                            if (!Game.getProvince(Game.iActiveProvince).addInvestInProvince()) {
                                                Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2(Game.getInvestCost(Game.iActiveProvince), 100), Images.gold);
                                                break;
                                            }

                                            ProvinceDraw.addProvinceDot_Economy(Game.iActiveProvince);
                                        }
                                    }
                                }
                            } else if (Game.getProvince(Game.iActiveProvince).getCivID() == Game.player.iCivID) {
                                for(int i = 0; i < 5; ++i) {
                                    if (Game.canInvestInEconomy(Game.iActiveProvince)) {
                                        Game.menuManager.addToastGold(Game.lang.get("IncreasePopulationGrowthRate"), Images.populationUp);
                                        Game.menuManager.addToastInsufficient(Game.lang.get("MaximumEconomy") + ": ", CFG.getPrecision2(Game.getProvince(Game.iActiveProvince).getEconomyWithBonuses(), 10) + " / " + CFG.getPrecision2(Game.getMaxEconomy(Game.iActiveProvince), 10), Game_Calendar.IMG_ECONOMY);
                                        break;
                                    }

                                    if (Game.getCiv(Game.player.iCivID).fLegacy < Game.getInvestCost_Legacy(Game.iActiveProvince)) {
                                        Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientLegacy") + ": ", CFG.getPrecision2(Game.getInvestCost_Legacy(Game.iActiveProvince), 100), Images.legacy);
                                    } else {
                                        if (!Game.getProvince(Game.iActiveProvince).addInvestInProvince()) {
                                            Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2(Game.getInvestCost(Game.iActiveProvince), 100), Images.gold);
                                            break;
                                        }

                                        ProvinceDraw.addProvinceDot_Economy(Game.iActiveProvince);
                                    }
                                }
                            }
                        } else if (button == 1) {
                        }

                        ProvinceHover.provinceHoverBuild.build();
                    }

                };
            } else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DEVELOP_INFRASTRUCTURE) {
                actionUp_SetActiveProvinceID_ExtraAction = (nPosX, nPosY, nPointer, button) -> {
                    if (Game.iActiveProvince >= 0) {
                        if (button == 0) {
                            if (Game.getProvince(Game.iActiveProvince).getCivID() == Game.player.iCivID && InGame_ProvinceInfo.addDevelopInfrastructureCost(Game.iActiveProvince)) {
                                ProvinceDraw.addProvinceDot_Infrastructure(Game.iActiveProvince);
                            }
                        } else if (button == 1) {
                        }

                        ProvinceHover.provinceHoverBuild.build();
                    }

                };
            } else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_INCREASE_TAX_EFFICIENCY) {
                actionUp_SetActiveProvinceID_ExtraAction = (nPosX, nPosY, nPointer, button) -> {
                    if (Game.iActiveProvince >= 0) {
                        if (button == 0) {
                            if (!AA_KeyManager.CTRL_HOLD && !AA_KeyManager.SHIFT_HOLD) {
                                if (Game.getProvince(Game.iActiveProvince).getCivID() == Game.player.iCivID) {
                                    for(int u = 0; u < InGame_Court_IncreaseTaxEfficiency.CLICK_X_TIMES; ++u) {
                                        if (Game.getCiv(Game.player.iCivID).fLegacy < Game.getIncreaseTaxEfficiencyCostLegacy(Game.iActiveProvince)) {
                                            Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientLegacy") + ": ", CFG.getPrecision2(Game.getIncreaseTaxEfficiencyCostLegacy(Game.iActiveProvince), 100), Images.legacy);
                                            break;
                                        }

                                        if (!Game.getProvince(Game.iActiveProvince).addIncreaseTaxEfficiencyInProvince()) {
                                            Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2(Game.getIncreaseTaxEfficiencyCost(Game.iActiveProvince), 100), Images.gold);
                                            break;
                                        }

                                        ProvinceDraw.addProvinceDot_TaxEfficiency(Game.iActiveProvince);
                                    }
                                }
                            } else if (Game.getProvince(Game.iActiveProvince).getCivID() == Game.player.iCivID) {
                                for(int i = 0; i < 5; ++i) {
                                    if (Game.getCiv(Game.player.iCivID).fLegacy < Game.getIncreaseTaxEfficiencyCostLegacy(Game.iActiveProvince)) {
                                        Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientLegacy") + ": ", CFG.getPrecision2(Game.getIncreaseTaxEfficiencyCostLegacy(Game.iActiveProvince), 100), Images.legacy);
                                        break;
                                    }

                                    if (!Game.getProvince(Game.iActiveProvince).addIncreaseTaxEfficiencyInProvince()) {
                                        Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2(Game.getIncreaseTaxEfficiencyCost(Game.iActiveProvince), 100), Images.gold);
                                        break;
                                    }

                                    ProvinceDraw.addProvinceDot_TaxEfficiency(Game.iActiveProvince);
                                }
                            }
                        } else if (button == 1) {
                        }

                        ProvinceHover.provinceHoverBuild.build();
                    }

                };
            } else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_MOVE_CAPITAL) {
                actionUp_SetActiveProvinceID_ExtraAction = (nPosX, nPosY, nPointer, button) -> {
                    if (Game.iActiveProvince >= 0) {
                        if (button == 0) {
                            if (Game.getProvince(Game.iActiveProvince).getCivID() == Game.player.iCivID) {
                                if (Game.menuManager.getVisibleInGame_PopUp() && MenuManager.IN_GAME_POP_UP_MENU_ID == 7 && Game.iActiveProvince == InGame_MoveCapital_PopUp.toProvinceID) {
                                    Game.menuManager.setVisibleInGame_PopUp(false);
                                } else {
                                    InGame_MoveCapital_PopUp.toProvinceID = Game.iActiveProvince;
                                    Game.menuManager.rebuildInGame_MoveCapital_PopUp();
                                }
                            }
                        } else if (button == 1) {
                        }

                        ProvinceHover.provinceHoverBuild.build();
                    }

                };
            } else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_INCREASE_MANPOWER) {
                actionUp_SetActiveProvinceID_ExtraAction = (nPosX, nPosY, nPointer, button) -> {
                    if (Game.iActiveProvince >= 0) {
                        if (button == 0) {
                            if (!AA_KeyManager.CTRL_HOLD && !AA_KeyManager.SHIFT_HOLD) {
                                if (Game.getProvince(Game.iActiveProvince).getCivID() == Game.player.iCivID) {
                                    for(int u = 0; u < InGame_Court_IncreaseManpower.CLICK_X_TIMES; ++u) {
                                        if (Game.getCiv(Game.player.iCivID).fLegacy < Game.getIncreaseManpowerCostLegacy(Game.iActiveProvince)) {
                                            Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientLegacy") + ": ", CFG.getPrecision2(Game.getIncreaseManpowerCostLegacy(Game.iActiveProvince), 100), Images.legacy);
                                            break;
                                        }

                                        if (!Game.getProvince(Game.iActiveProvince).addIncreaseManpowerInProvince()) {
                                            Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2(Game.getIncreaseManpowerCost(Game.iActiveProvince), 100), Images.gold);
                                            break;
                                        }

                                        ProvinceDraw.addProvinceDot_Manpower(Game.iActiveProvince);
                                    }
                                }
                            } else if (Game.getProvince(Game.iActiveProvince).getCivID() == Game.player.iCivID) {
                                for(int i = 0; i < 5; ++i) {
                                    if (Game.getCiv(Game.player.iCivID).fLegacy < Game.getIncreaseManpowerCostLegacy(Game.iActiveProvince)) {
                                        Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientLegacy") + ": ", CFG.getPrecision2(Game.getIncreaseManpowerCostLegacy(Game.iActiveProvince), 100), Images.legacy);
                                        break;
                                    }

                                    if (!Game.getProvince(Game.iActiveProvince).addIncreaseManpowerInProvince()) {
                                        Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2(Game.getIncreaseManpowerCost(Game.iActiveProvince), 100), Images.gold);
                                        break;
                                    }

                                    ProvinceDraw.addProvinceDot_Manpower(Game.iActiveProvince);
                                }
                            }
                        } else if (button == 1) {
                        }

                        ProvinceHover.provinceHoverBuild.build();
                    }

                };
            } else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_INCREASE_GROWTH_RATE) {
                actionUp_SetActiveProvinceID_ExtraAction = (nPosX, nPosY, nPointer, button) -> {
                    if (Game.iActiveProvince >= 0) {
                        if (button == 0) {
                            if (!AA_KeyManager.CTRL_HOLD && !AA_KeyManager.SHIFT_HOLD) {
                                if (Game.getProvince(Game.iActiveProvince).getCivID() == Game.player.iCivID) {
                                    for(int u = 0; u < InGame_Court_IncreaseGrowthRate.CLICK_X_TIMES; ++u) {
                                        if (!Game.getProvince(Game.iActiveProvince).addIncreaseGrowthRateInProvince()) {
                                            Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2(Game.getIncreaseGrowthRateCost(Game.iActiveProvince), 100), Images.gold);
                                            break;
                                        }

                                        ProvinceDraw.addProvinceDot_GrowthRate(Game.iActiveProvince);
                                    }
                                }
                            } else if (Game.getProvince(Game.iActiveProvince).getCivID() == Game.player.iCivID) {
                                for(int i = 0; i < 5; ++i) {
                                    if (!Game.getProvince(Game.iActiveProvince).addIncreaseGrowthRateInProvince()) {
                                        Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2(Game.getIncreaseGrowthRateCost(Game.iActiveProvince), 100), Images.gold);
                                        break;
                                    }

                                    ProvinceDraw.addProvinceDot_GrowthRate(Game.iActiveProvince);
                                }
                            }
                        } else if (button == 1) {
                        }

                        ProvinceHover.provinceHoverBuild.build();
                    }

                };
            } else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_WONDERS) {
                actionUp_SetActiveProvinceID_ExtraAction = (nPosX, nPosY, nPointer, button) -> {
                    if (Game.iActiveProvince >= 0 && Game.getProvince(Game.iActiveProvince).wonderID >= 0) {
                        if (Game.menuManager.getVisibleInGame_Wonder()) {
                            if (InGame_Wonder.iProvinceID != Game.iActiveProvince) {
                                InGame_Wonder.iProvinceID = Game.iActiveProvince;
                                Game.menuManager.rebuildInGame_Wonder();
                                InGame_Wonder.lTime = 0L;
                            }
                        } else {
                            InGame_Wonder.iProvinceID = Game.iActiveProvince;
                            Game.menuManager.rebuildInGame_Wonder();
                        }

                        if (Game.menuManager.getVisibleInGame_Court() || Game.menuManager.getVisibleInGame_Civ()) {
                            Game.menuManager.hideCourtCiv();
                        }
                    }

                };
            } else {
                actionUp_SetActiveProvinceID_ExtraAction = (nPosX, nPosY, nPointer, button) -> {
                    if (Game.iActiveProvince >= 0) {
                        if (Game.menuManager.getColorPicker().getVisible() && Game.getProvince(Game.iActiveProvince).getCivID() > 0) {
                            ColorPicker.ACTIVE_CIV_ID = Game.getProvince(Game.iActiveProvince).getCivID();
                            Game.menuManager.getColorPicker().setActiveRGBColor(Game.getCiv(Game.getProvince(Game.iActiveProvince).getCivID()).getR(), Game.getCiv(Game.getProvince(Game.iActiveProvince).getCivID()).getG(), Game.getCiv(Game.getProvince(Game.iActiveProvince).getCivID()).getB());
                        }

                        if (Game.getProvince(Game.iActiveProvince).getSeaProvince()) {
                            Game.menuManager.hideInGameMenus();
                        } else if (Game.menuManager.getVisibleInGame_GoodsMarket()) {
                            if (Game.iActiveProvince != Game.iOldActiveProvinceID) {
                                if (Game.iActiveProvince >= 0 && Game.getProvince(Game.iActiveProvince).getCivID() > 0 && Game.getProvince(Game.iActiveProvince).getCivID() != InGame_GoodsMarket.iActiveCivID) {
                                    InGame_GoodsMarket.iActiveCivID = Game.getProvince(Game.iActiveProvince).getCivID();
                                    Game.menuManager.rebuildInGame_GoodsMarket();
                                    InGame_GoodsMarket.lTime = 0L;
                                }
                            } else {
                                Game.menuManager.hideCourtCiv();
                                if(FontFix.isCommandMode){
                                    Game.clearActiveArmy();
                                    int size = Game.getProvince(Game.iActiveProvince).getArmySize();
                                    for (int i = 0; i < size; i++) {
                                        FontFix.addActiveArmy(Game.iActiveProvince,Game.getProvince(Game.iActiveProvince).getArmy(i).key);
                                    }
                                    FinalityLogger.debug("AAA-B "+Game.activeArmySize);
                                    actionUp_SetActiveArmy();

                                }else {
                                    Game.menuManager.rebuildInGame_ProvinceInfo(true);
                                }
                                Game.menuManager.setVisibleInGame_Buildings(false, false);
                                if (Game.menuManager.getVisibleInGame_TechnologyChoose()) {
                                    Game.menuManager.setVisibleInGame_TechnologyChoose(false);
                                }

                                if (Game.menuManager.getVisibleInGame_TechnologyTree()) {
                                    Game.menuManager.setVisibleInGame_TechnologyTree(false);
                                }

                                if (Game.menuManager.getVisibleDeclareWar()) {
                                    Game.menuManager.setVisibleInGame_PopUp(false);
                                }

                                if (Game.menuManager.getVisible_SpecialAlliance()) {
                                    Game.menuManager.setVisibleInGame_PopUp(false);
                                }

                                if (Game.menuManager.getVisibleFormCiv() || Game.menuManager.getVisibleSellProvince() || Game.menuManager.getVisibleRevolutions()) {
                                    Game.menuManager.setVisibleInGame_PopUp(false);
                                }

                                if (Game.menuManager.getVisibleInGame_CurrentSituation()) {
                                    Game.menuManager.setVisibleInGame_CurrentSituation(false);
                                }

                                if (Game.menuManager.getVisibleInGame_Nukes()) {
                                    Game.menuManager.setVisibleInGame_Nukes(false);
                                }

                                if (Game.menuManager.getVisibleInGame_GoodsMarket()) {
                                    Game.menuManager.setVisibleInGame_GoodsMarket(false);
                                }
                            }
                        } else if (Game.menuManager.getVisibleInGame_Civ() && !InGame_Civ.enabledByScaleOut) {
                            if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DIPLOMACY) {
                                if (Game.iActiveProvince != Game.iOldActiveProvinceID) {
                                    if (Game.getProvince(Game.iActiveProvince).getCivID() > 0 && InGame_Civ.iActiveCivID != Game.getProvince(Game.iActiveProvince).getCivID()) {
                                        Game.menuManager.rebuildInGame_Civ();
                                        InGame_Civ.lTime = 0L;
                                        Game.soundsManager.playSound(Game.soundsManager.getDiplomacy());
                                    }
                                } else {
                                    ProvinceTouchExtraAction.actionSetActiveProvinceID();
                                }
                            } else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DIPLOMACY_IMPROVE_RELATIONS) {
                                if (Game.getProvince(Game.iActiveProvince).getCivID() == Game.player.iCivID) {
                                    Game.mapModes.setActiveViewID(Game.mapModes.MODE_DIPLOMACY);
                                    InGame_Civ.iRebuildToCivID = InGame_Civ.iActiveCivID;
                                    Game.menuManager.rebuildInGame_Civ(true);
                                    InGame_Civ.lTime = 0L;
                                } else {
                                    int iActiveCivID = Game.getProvince(Game.iActiveProvince).getCivID();
                                    if (DiplomacyManager.isAtWar(Game.player.iCivID, iActiveCivID)) {
                                        Game.menuManager.addToastNegative("", Game.lang.get("WeAreAtWar"), Images.war);
                                    } else if (!Game.getCiv(Game.player.iCivID).diplomacy.isRival(iActiveCivID) && !Game.getCiv(iActiveCivID).diplomacy.isRival(Game.player.iCivID)) {
                                        if (Game.getCiv(Game.player.iCivID).diplomacy.isImprovingRelations(iActiveCivID)) {
                                            Game.getCiv(Game.player.iCivID).diplomacy.removeImproveRelations(iActiveCivID);
                                            Game.menuManager.addToastNegative(Game.lang.get("Removed") + ": ", Game.getCiv(iActiveCivID).getCivName(), Images.relationsDown);
                                            ProvinceDraw.addDiplomacyLines(Game.getCiv(iActiveCivID).getCapitalProvinceID(), Game.getCiv(Game.player.iCivID).getCapitalProvinceID(), Colors.HOVER_POSITIVE);
                                        } else if (Game.getCiv(iActiveCivID).diplomacy.getRelation(Game.player.iCivID) >= GameValues.diplomacy.DIPLOMACY_IMPROVE_RELATIONS_MAX) {
                                            Game.menuManager.addToastNegative(Game.lang.get("MaximumOpinion") + ": ", CFG.getPrecision2(GameValues.diplomacy.DIPLOMACY_IMPROVE_RELATIONS_MAX, 1), Images.relations);
                                        } else {
                                            Game.getCiv(Game.player.iCivID).diplomacy.addImproveRelations(Game.player.iCivID, iActiveCivID);
                                            Game.menuManager.addToastPositive(Game.lang.get("ImprovingRelations") + ": ", Game.getCiv(iActiveCivID).getCivName(), Images.relationsDown);
                                            ProvinceDraw.addDiplomacyLines(Game.getCiv(Game.player.iCivID).getCapitalProvinceID(), Game.getCiv(iActiveCivID).getCapitalProvinceID(), Colors.HOVER_POSITIVE);
                                        }
                                    } else {
                                        Game.menuManager.addToastNegative("", Game.lang.get("WeAreRivals"), Images.rivals);
                                    }

                                    Game.menuManager.rebuildInGame_Right();
                                    Game.soundsManager.playSound(SoundsManager.DIPLOMACY_CLICK);
                                }
                            } else if (Game.mapModes.iActiveMapModeID == Game.mapModes.MODE_DIPLOMACY_DAMAGE_RELATIONS) {
                                if (Game.getProvince(Game.iActiveProvince).getCivID() != Game.player.iCivID) {
                                    int iActiveCivID = Game.getProvince(Game.iActiveProvince).getCivID();
                                    if (DiplomacyManager.isAtWar(Game.player.iCivID, iActiveCivID)) {
                                        Game.menuManager.addToastNegative("", Game.lang.get("WeAreAtWar"), Images.war);
                                    } else if (Game.getCiv(Game.player.iCivID).diplomacy.isDamagingRelations(iActiveCivID)) {
                                        Game.getCiv(Game.player.iCivID).diplomacy.removeDamageRelations(iActiveCivID);
                                        Game.menuManager.addToastNegative(Game.lang.get("Removed") + ": ", Game.getCiv(iActiveCivID).getCivName(), Images.relationsDown);
                                        ProvinceDraw.addDiplomacyLines(Game.getCiv(iActiveCivID).getCapitalProvinceID(), Game.getCiv(Game.player.iCivID).getCapitalProvinceID(), Colors.HOVER_POSITIVE);
                                    } else if (Game.getCiv(iActiveCivID).diplomacy.getRelation(Game.player.iCivID) <= GameValues.diplomacy.DIPLOMACY_DAMAGE_RELATIONS_MAX) {
                                        Game.menuManager.addToastInsufficient(Game.lang.get("MaximumOpinion") + ": ", CFG.getPrecision2(GameValues.diplomacy.DIPLOMACY_DAMAGE_RELATIONS_MAX, 1), Images.relations);
                                    } else {
                                        Game.getCiv(Game.player.iCivID).diplomacy.addDamageRelations(Game.player.iCivID, iActiveCivID);
                                        ProvinceDraw.addDiplomacyLines(Game.getCiv(Game.player.iCivID).getCapitalProvinceID(), Game.getCiv(iActiveCivID).getCapitalProvinceID(), Colors.HOVER_NEGATIVE);
                                        Game.menuManager.addToastNegative(Game.lang.get("DamagingRelations") + ": ", Game.getCiv(iActiveCivID).getCivName(), Images.relationsDown);
                                    }

                                    Game.menuManager.rebuildInGame_Right();
                                    Game.soundsManager.playSound(SoundsManager.DIPLOMACY_CLICK);
                                } else {
                                    Game.mapModes.setActiveViewID(Game.mapModes.MODE_DIPLOMACY);
                                    InGame_Civ.iRebuildToCivID = InGame_Civ.iActiveCivID;
                                    Game.menuManager.rebuildInGame_Civ(true);
                                    InGame_Civ.lTime = 0L;
                                }
                            }
                        } else if (Game.menuManager.getVisibleInGame_Court() && InGame_Court.iActiveCivID != Game.player.iCivID && Game.getProvince(Game.iActiveProvince).getCivID() > 0 && InGame_Court.iActiveCivID != Game.getProvince(Game.iActiveProvince).getCivID()) {
                            Game.menuManager.setVisibleInGame_Court(false);
                            Game.menuManager.rebuildInGame_Civ();
                            Game.soundsManager.playSound(Game.soundsManager.getDiplomacy());
                        } else if (!Game.menuManager.getVisibleInGame_ProvinceInfo()) {
                            if(FontFix.isCommandMode){
                                Game.clearActiveArmy();
                                int size = Game.getProvince(Game.iActiveProvince).getArmySize();
                                for (int i = 0; i < size; i++) {
                                    FontFix.addActiveArmy(Game.iActiveProvince,Game.getProvince(Game.iActiveProvince).getArmy(i).key);
                                }
                                FinalityLogger.debug("AAA-A "+Game.activeArmySize);
                                actionUp_SetActiveArmy();
                            }else {
                                Game.menuManager.rebuildInGame_ProvinceInfo(true);
                            }
                            Game.menuManager.setVisibleInGame_Buildings(false, false);
                            Game.menuManager.hideCourtCiv();
                            if (Game.menuManager.getVisibleInGame_TechnologyChoose()) {
                                Game.menuManager.setVisibleInGame_TechnologyChoose(false);
                            }

                            if (Game.menuManager.getVisibleInGame_TechnologyTree()) {
                                Game.menuManager.setVisibleInGame_TechnologyTree(false);
                            }

                            if (Game.menuManager.getVisibleDeclareWar()) {
                                Game.menuManager.setVisibleInGame_PopUp(false);
                            }

                            if (Game.menuManager.getVisible_SpecialAlliance()) {
                                Game.menuManager.setVisibleInGame_PopUp(false);
                            }

                            if (Game.menuManager.getVisibleFormCiv() || Game.menuManager.getVisibleSellProvince() || Game.menuManager.getVisibleRevolutions()) {
                                Game.menuManager.setVisibleInGame_PopUp(false);
                            }

                            if (Game.menuManager.getVisibleInGame_CurrentSituation()) {
                                Game.menuManager.setVisibleInGame_CurrentSituation(false);
                            }

                            if (Game.menuManager.getVisibleInGame_Nukes()) {
                                Game.menuManager.setVisibleInGame_Nukes(false);
                            }
                        } else if (InGame_ProvinceInfo.iProvinceID != Game.iActiveProvince) {
                            if(FontFix.isCommandMode){
                                Game.clearActiveArmy();
                                int size = Game.getProvince(Game.iActiveProvince).getArmySize();
                                for (int i = 0; i < size; i++) {
                                    FontFix.addActiveArmy(Game.iActiveProvince,Game.getProvince(Game.iActiveProvince).getArmy(i).key);
                                }
                                actionUp_SetActiveArmy();
                                FinalityLogger.debug("AAA-D "+Game.activeArmySize);
                            }else {
                                Game.menuManager.rebuildInGame_ProvinceInfo(true);
                            }
                            if (Game.menuManager.getVisibleInGame_ProvinceBonuses()) {
                                InGame_ProvinceBonuses.iProvinceID = InGame_ProvinceInfo.iProvinceID;
                                Game.menuManager.rebuildInGame_ProvinceBonuses();
                                Game.menuManager.setVisibleInGame_ProvinceBonuses(true, true);
                                InGame_ProvinceBonuses.lTime = 0L;
                            }

                            if (Game.menuManager.getVisibleInGame_Buildings()) {
                                if (CFG.isDesktop() && Game.getProvince(Game.iActiveProvince).getCivID() == Game.player.iCivID) {
                                    InGame_BuildingsGroup.iProvinceID = Game.iActiveProvince;
                                    Game.menuManager.rebuildInGame_Buildings(true);
                                } else {
                                    Game.menuManager.setVisibleInGame_Buildings(false, false);
                                }
                            }

                            Game.menuManager.hideCourtCiv();
                        }

                        if (Game.menuManager.getVisibleInGame_Generals()) {
                            Game.menuManager.setVisibleInGame_Generals(false);
                        }

                        if (Game.menuManager.getVisibleInGame_GeneralRecruit()) {
                            Game.menuManager.setVisibleInGame_GeneralRecruit(false);
                        }

                        if (Game.menuManager.getVisibleInGame_CivBonuses() && Game.getProvince(Game.iActiveProvince).getCivID() != 0 && InGame_CivBonuses.iCivID != Game.getProvince(Game.iActiveProvince).getCivID()) {
                            InGame_CivBonuses.iCivID = Game.getProvince(Game.iActiveProvince).getCivID();
                            Game.menuManager.rebuildInGame_CivBonuses();
                            Game.menuManager.setVisibleInGame_CivBonuses(true);
                            InGame_CivBonuses.lTime = 0L;
                        }

                        if (Game.menuManager.getVisibleInGame_Wonder() && Game.iActiveProvince != InGame_Wonder.iProvinceID) {
                            if (Game.getProvince(Game.iActiveProvince).wonderID >= 0) {
                                InGame_Wonder.iProvinceID = Game.iActiveProvince;
                                Game.menuManager.rebuildInGame_Wonder();
                                InGame_Wonder.lTime = 0L;
                            } else {
                                Game.menuManager.setVisibleInGame_Wonder(false);
                            }
                        }

                        if (Game.menuManager.getVisibleInGame_ProvinceArmy() && !FontFix.isCommandMode) {
                            Game.menuManager.setVisibleInGame_ProvinceArmy(false);
                        }

                        if (Game.menuManager.getVisibleInGame_ReorganizeUnits()) {
                            Game.menuManager.setVisibleInGame_ReorganizeUnits(false);
                        }

                        if (Game.menuManager.getVisibleInGame_DisbandArmy()) {
                            Game.menuManager.setVisibleInGame_DisbandUnits(false);
                        }

                        if (Game.menuManager.getVisibleInGame_Battle()) {
                            Game.menuManager.setVisibleInGame_Battle(false);
                        }

                        if (Game.menuManager.getVisibleInGame_Siege()) {
                            Game.menuManager.setVisibleInGame_Siege(false);
                        }

                        if (Game.menuManager.getVisibleInGame_War()) {
                            Game.menuManager.setVisibleInGame_War(false);
                        }

                        if (Game.menuManager.getVisibleInGame_RecruitArmy()) {
                            Game.menuManager.setVisibleInGame_RecruitArmy(false);
                        }

                        if (Game.menuManager.getVisibleInGame_Armies()) {
                            Game.menuManager.setVisibleInGame_Armies(false);
                        }

                        if (Game.menuManager.getVisibleInGame_TechnologyChoose()) {
                            Game.menuManager.setVisibleInGame_TechnologyChoose(false);
                        }

                        if (Game.menuManager.getVisibleDeclareWar()) {
                            Game.menuManager.setVisibleInGame_PopUp(false);
                        }

                        if (Game.menuManager.getVisible_SpecialAlliance()) {
                            Game.menuManager.setVisibleInGame_PopUp(false);
                        }

                        if (Game.menuManager.getVisibleFormCiv() || Game.menuManager.getVisibleSellProvince() || Game.menuManager.getVisibleRevolutions()) {
                            Game.menuManager.setVisibleInGame_PopUp(false);
                        }

                        if (Game.menuManager.getVisibleInGame_Nukes()) {
                            Game.menuManager.setVisibleInGame_Nukes(false);
                        }

                        if (Game.menuManager.getVisibleInGame_TechnologyTree()) {
                            Game.menuManager.setVisibleInGame_TechnologyTree(false);
                        }

                        if (Game.menuManager.getVisibleInGame_CurrentSituation()) {
                            Game.menuManager.setVisibleInGame_CurrentSituation(false);
                        }
                        if(!FontFix.isCommandMode) {
                            Game.clearActiveArmy();
                        }
                    }

                };
            }

        } else if (Game.menuManager.getInNewGame()) {
            actionUp_SetActiveProvinceID_ExtraAction = (nPosX, nPosY, nPointer, button) -> {
                if (Game.iActiveProvince >= 0 && Game.getProvince(Game.iActiveProvince).getCivID() > 0 && Game.player.iCivID != Game.getProvince(Game.iActiveProvince).getCivID()) {
                    Game.player.iCivID = Game.getProvince(Game.iActiveProvince).getCivID();
                    Game.menuManager.rebuildNewGameCiv();
                }

            };
        } else if (Game.menuManager.getInMapEditorGrowthRate()) {
            actionUp_SetActiveProvinceID_ExtraAction = (nPosX, nPosY, nPointer, button) -> {
                if (Game.iActiveProvince >= 0 && !Game.getProvince(Game.iActiveProvince).getSeaProvince()) {
                    EditorMapGrowthRate.actionUpdateData(true);
                }

            };
        } else if (Game.menuManager.getInMapEditorEconomy()) {
            actionUp_SetActiveProvinceID_ExtraAction = (nPosX, nPosY, nPointer, button) -> {
                if (Game.iActiveProvince >= 0 && !Game.getProvince(Game.iActiveProvince).getSeaProvince()) {
                    EditorMapEconomy.actionUpdateData(true);
                }

            };
        } else if (Game.menuManager.getInMapEditorTerrain()) {
            actionUp_SetActiveProvinceID_ExtraAction = (nPosX, nPosY, nPointer, button) -> {
                if (Game.iActiveProvince >= 0 && !Game.getProvince(Game.iActiveProvince).getSeaProvince()) {
                    EditorMapTerrainType.actionUpdateData(true);
                }

            };
        } else if (Game.menuManager.getInScenarioEditorTechnologiesCivs()) {
            actionUp_SetActiveProvinceID_ExtraAction = (nPosX, nPosY, nPointer, button) -> {
                if (Game.iActiveProvince >= 0 && !Game.getProvince(Game.iActiveProvince).getSeaProvince() && Game.getProvince(Game.iActiveProvince).getCivID() > 0) {
                    Game.getCiv(Game.getProvince(Game.iActiveProvince).getCivID()).scenarioEditorData.TechnologyID = ScenarioTechnologyCivs.iActiveTechnology;
                    ProvinceDrawDetails.updateDrawProvinceDetails_Technology();
                }

            };
        } else if (Game.menuManager.getInScenarioEditorArmies()) {
            actionUp_SetActiveProvinceID_ExtraAction = (nPosX, nPosY, nPointer, button) -> {
                if (Game.iActiveProvince >= 0 && !Game.getProvince(Game.iActiveProvince).getSeaProvince() && Game.getProvince(Game.iActiveProvince).getCivID() > 0) {
                    Game.menuManager.rebuildInGame_ScenarioEditorArmies();
                }

            };
        } else if (Game.menuManager.getInScenarioEditorGovernment()) {
            actionUp_SetActiveProvinceID_ExtraAction = (nPosX, nPosY, nPointer, button) -> {
                if (Game.iActiveProvince >= 0 && !Game.getProvince(Game.iActiveProvince).getSeaProvince() && Game.getProvince(Game.iActiveProvince).getCivID() > 0) {
                    Game.menuManager.rebuildInGame_ScenarioEditorGovernment();
                }

            };
        } else if (Game.menuManager.getInScenarioEditorRelations()) {
            actionUp_SetActiveProvinceID_ExtraAction = (nPosX, nPosY, nPointer, button) -> {
                if (Game.iActiveProvince >= 0 && !Game.getProvince(Game.iActiveProvince).getSeaProvince() && Game.getProvince(Game.iActiveProvince).getCivID() > 0) {
                    ScenarioRelationsList.activeCivID2 = Game.getProvince(Game.iActiveProvince).getCivID();
                    Game.menuManager.rebuildInGame_ScenarioEditorRelations();
                }

            };
        } else if (Game.menuManager.getInMapEditorResource()) {
            actionUp_SetActiveProvinceID_ExtraAction = (nPosX, nPosY, nPointer, button) -> {
                if (Game.iActiveProvince >= 0 && !Game.getProvince(Game.iActiveProvince).getSeaProvince()) {
                    EditorMapResources.actionUpdateData(true);
                }

            };
        } else if (Game.menuManager.getInMapEditorContinents()) {
            actionUp_SetActiveProvinceID_ExtraAction = (nPosX, nPosY, nPointer, button) -> {
                if (Game.iActiveProvince >= 0 && !Game.getProvince(Game.iActiveProvince).getSeaProvince()) {
                    EditorMapContinents.actionUpdateData(true);
                }

            };
        } else if (Game.menuManager.getInEditorSelectProvinces()) {
            actionUp_SetActiveProvinceID_ExtraAction = (nPosX, nPosY, nPointer, button) -> {
                if (Game.iActiveProvince >= 0 && !Game.getProvince(Game.iActiveProvince).getSeaProvince()) {
                    if (CFG.selectMode) {
                        EditorSelectProvinces.addSelectedProvince(Game.iActiveProvince);
                    } else {
                        EditorSelectProvinces.removeSelectedProvince(Game.iActiveProvince);
                    }
                }

            };
        } else if (Game.menuManager.getInScenarioCores()) {
            actionUp_SetActiveProvinceID_ExtraAction = (nPosX, nPosY, nPointer, button) -> {
                if (Game.iActiveProvince >= 0) {
                    if (!Game.getProvince(Game.iActiveProvince).getSeaProvince()) {
                        if (CFG.brushTool) {
                            if (CFG.selectMode) {
                                EditorSelectProvinces.addSelectedProvince(Game.iActiveProvince);
                            } else {
                                EditorSelectProvinces.removeSelectedProvince(Game.iActiveProvince);
                            }
                        } else {
                            EditorSelectProvinces.selectedProvinces.clear();
                            EditorSelectProvinces.addSelectedProvince(Game.iActiveProvince);
                        }
                    }

                    Game.menuManager.rebuildScenarioCoresList_InProvince();
                }

            };
        } else if (Game.menuManager.getInScenarioReligion()) {
            actionUp_SetActiveProvinceID_ExtraAction = (nPosX, nPosY, nPointer, button) -> {
                if (Game.iActiveProvince >= 0 && !Game.getProvince(Game.iActiveProvince).getSeaProvince()) {
                    if (CFG.brushTool) {
                        if (CFG.selectMode) {
                            EditorSelectProvinces.addSelectedProvince(Game.iActiveProvince);
                        } else {
                            EditorSelectProvinces.removeSelectedProvince(Game.iActiveProvince);
                        }
                    } else {
                        EditorSelectProvinces.selectedProvinces.clear();
                        EditorSelectProvinces.addSelectedProvince(Game.iActiveProvince);
                    }
                }

            };
        } else if (Game.menuManager.getInScenarioEditorBuildings()) {
            actionUp_SetActiveProvinceID_ExtraAction = (nPosX, nPosY, nPointer, button) -> {
                if (Game.iActiveProvince >= 0) {
                    if (!Game.getProvince(Game.iActiveProvince).getSeaProvince() && Game.getProvince(Game.iActiveProvince).getCivID() > 0) {
                        Game.menuManager.rebuildInGame_ScenarioEditorBuildings();
                    }

                    if (!Game.getProvince(Game.iActiveProvince).getSeaProvince()) {
                        if (CFG.brushTool) {
                            if (CFG.selectMode) {
                                EditorSelectProvinces.addSelectedProvince(Game.iActiveProvince);
                            } else {
                                EditorSelectProvinces.removeSelectedProvince(Game.iActiveProvince);
                            }
                        } else {
                            EditorSelectProvinces.selectedProvinces.clear();
                            EditorSelectProvinces.addSelectedProvince(Game.iActiveProvince);
                        }
                    }
                }

            };
        } else if (Game.menuManager.getInEditorFormableCiv()) {
            actionUp_SetActiveProvinceID_ExtraAction = (nPosX, nPosY, nPointer, button) -> {
                if (Game.iActiveProvince >= 0 && !Game.getProvince(Game.iActiveProvince).getSeaProvince()) {
                    if (CFG.selectMode) {
                        FormableCivManager.activeFormableCiv.addProvince(Game.iActiveProvince);
                    } else {
                        FormableCivManager.activeFormableCiv.removeProvince(Game.iActiveProvince);
                    }
                }

            };
        } else if (Game.menuManager.getInMapEditorGeoRegions()) {
            actionUp_SetActiveProvinceID_ExtraAction = (nPosX, nPosY, nPointer, button) -> {
                if (Game.iActiveProvince >= 0 && !Game.getProvince(Game.iActiveProvince).getSeaProvince()) {
                    EditorMapGeoRegions.actionUpdateData(true);
                }

            };
        } else if (Game.menuManager.getInMapEditorOptimizationRegions()) {
            actionUp_SetActiveProvinceID_ExtraAction = (nPosX, nPosY, nPointer, button) -> {
                if (Game.iActiveProvince >= 0) {
                    EditorMapOptimizationRegions.actionUpdateData(true);
                }

            };
        } else if (Game.menuManager.getInScenarioWasteland()) {
            actionUp_SetActiveProvinceID_ExtraAction = (nPosX, nPosY, nPointer, button) -> {
                if (Game.iActiveProvince >= 0) {
                    ScenarioWasteland.actionUpdateData(true);
                }

            };
        } else if (Game.menuManager.getInScenarioAssign()) {
            actionUp_SetActiveProvinceID_ExtraAction = (nPosX, nPosY, nPointer, button) -> {
                if (Game.iActiveProvince >= 0) {
                    if (AA_KeyManager.CTRL_HOLD) {
                        CFG.iCreateScenario_AssignProvinces_Civ = Game.getProvince(Game.iActiveProvince).getCivID();
                    } else {
                        ScenarioAssign.actionUpdateData(true);
                    }
                }

            };
        } else if (Game.menuManager.getInScenarioAssignInGame()) {
            actionUp_SetActiveProvinceID_ExtraAction = (nPosX, nPosY, nPointer, button) -> {
                if (Game.iActiveProvince >= 0) {
                    if (AA_KeyManager.CTRL_HOLD) {
                        CFG.iCreateScenario_AssignProvinces_Civ = Game.getProvince(Game.iActiveProvince).getCivID();
                    } else {
                        ScenarioAssign_InGame.actionUpdateData();
                    }
                }

            };
        } else if (Game.menuManager.getInScenarioCivilizations()) {
            actionUp_SetActiveProvinceID_ExtraAction = (nPosX, nPosY, nPointer, button) -> ScenarioCivilizations.actionUpdateData();
        } else {
            actionUp_SetActiveProvinceID_ExtraAction = (nPosX, nPosY, nPointer, button) -> {
            };
        }
    }

    public static final void actionUp_SetActiveArmy() {
        InGame_ProvinceArmy.iActiveID = 0;
        InGame_ProvinceArmy.iCivFlagID = 0;
        InGame_ProvinceArmy.iProvinceID = 0;
        if (Game.menuManager.getInGame()) {
            if (Game.activeArmySize > 0) {
                InGame_ProvinceArmy.sActiveKEY = Game.activeArmy.get(0).key;
                Game.menuManager.rebuildInGame_ProvinceArmy();
                Game.soundsManager.playSelectedArmy();
                Game.menuManager.rebuildInGame_ProvinceArmy_HideMenus();
            } else {
                Game.menuManager.setVisibleInGame_ProvinceArmy(false);
            }
        }

    }

    public static final void actionBuilding(int nProvinceID) {
        try {
            if (Game.getProvince(nProvinceID).getCivID() == Game.player.iCivID || Game.getCiv(Game.getProvince(nProvinceID).getCivID()).getPuppetOfCivID() == Game.player.iCivID) {
                if (Game.getProvince(nProvinceID).getUsedBuildingsSlots() >= Game.getProvince(nProvinceID).iBuildingsLimit) {
                    List<MenuElement_HoverElement> nElements = new ArrayList();
                    List<MenuElement_HoverElement_Type> nData = new ArrayList();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("BuildingSlots") + ": "));
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.getProvince(nProvinceID).getUsedBuildingsSlots() + " / " + Game.getProvince(nProvinceID).iBuildingsLimit, Colors.COLOR_TEXT_MODIFIER_NEGATIVE));
                    nData.add(new MenuElement_HoverElement_Type_Image(Images.build, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("IncreaseEconomyInAProvinceToUnlockMoreBuildingSlots"), Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_Image(Game_Calendar.IMG_ECONOMY, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    Game.menuManager.addToast(new Toast(nElements, 0, 6000));
                } else if (Game.getProvince(nProvinceID).isOccupied()) {
                    Game.menuManager.addToast_Error(Game.lang.get("OccupiedProvince"), Images.war);
                } else if (Game.getProvince(nProvinceID).buildingBuilt(InGame_Court_Buildings2.oBuildingID.getPosX(), InGame_Court_Buildings2.oBuildingID.getPosY())) {
                    Game.menuManager.addToastGold(Game.lang.get("BuildingConstructed"), Images.build);
                } else if (Game.getProvince(nProvinceID).isUnderConstruction(InGame_Court_Buildings2.oBuildingID.getPosX(), InGame_Court_Buildings2.oBuildingID.getPosY())) {
                    Game.menuManager.addToastGold(Game.lang.get("UnderConstruction"), Images.build);
                } else if (BuildingsManager.buildings.get(InGame_Court_Buildings2.oBuildingID.getPosX()).SeaAccessRequired && Game.getProvince(nProvinceID).getLevelOfPort() < 0) {
                    Game.menuManager.addToastGold(Game.lang.get("NoAccessToTheSea"), Images.ship);
                } else if (BuildingsManager.buildings.get(InGame_Court_Buildings2.oBuildingID.getPosX()).RequiredGovernmentID >= 0 && BuildingsManager.buildings.get(InGame_Court_Buildings2.oBuildingID.getPosX()).RequiredGovernmentID != Game.getCiv(Game.getProvince(nProvinceID).getCivID()).getIdeologyID()) {
                    Game.menuManager.addToastGold(Game.lang.get("Government") + ": " + Game.ideologiesManager.getIdeology(BuildingsManager.buildings.get(InGame_Court_Buildings2.oBuildingID.getPosX()).RequiredGovernmentID).Name, Images.government);
                } else if (BuildingsManager.buildings.get(InGame_Court_Buildings2.oBuildingID.getPosX()).RequiredReligionID >= 0 && BuildingsManager.buildings.get(InGame_Court_Buildings2.oBuildingID.getPosX()).RequiredReligionID != Game.getCiv(Game.getProvince(nProvinceID).getCivID()).getReligionID()) {
                    Game.menuManager.addToastGold(Game.lang.get("Religion") + ": " + Game.religionManager.getReligion(BuildingsManager.buildings.get(InGame_Court_Buildings2.oBuildingID.getPosX()).RequiredReligionID).Name, Images.religion);
                } else if (BuildingsManager.buildings.get(InGame_Court_Buildings2.oBuildingID.getPosX()).RequiredResource >= 0 && BuildingsManager.buildings.get(InGame_Court_Buildings2.oBuildingID.getPosX()).RequiredResource != Game.getProvince(nProvinceID).getResourceID()) {
                    List<MenuElement_HoverElement> nElements = new ArrayList();
                    List<MenuElement_HoverElement_Type> nData = new ArrayList();
                    nData.add(new MenuElement_HoverElement_Type_Text(Game.lang.get("RequiredResource") + ": "));
                    nData.add(new MenuElement_HoverElement_Type_Text(ResourcesManager.getResourceName(BuildingsManager.buildings.get(InGame_Court_Buildings2.oBuildingID.getPosX()).RequiredResource), Colors.HOVER_GOLD));
                    nData.add(new MenuElement_HoverElement_Type_Image(Images.goods, CFG.PADDING, 0));
                    nElements.add(new MenuElement_HoverElement(nData));
                    nData.clear();
                    Game.menuManager.addToast(new Toast(nElements, 0, 6000));
                } else if (!Game.getProvince(nProvinceID).addBuildingConstruction(InGame_Court_Buildings2.oBuildingID.getPosX(), InGame_Court_Buildings2.oBuildingID.getPosY())) {
                    Game.menuManager.addToastInsufficient(Game.lang.get("InsufficientGold") + ": ", CFG.getPrecision2((float)Game.getBuildingConstructionCost(Game.player.iCivID, nProvinceID, InGame_Court_Buildings2.oBuildingID.getPosX(), InGame_Court_Buildings2.oBuildingID.getPosY()), 100), Images.gold);
                }
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    public static void actionPeaceView(int nProvinceID, boolean onlyTake) {
        if (Game.getProvince(nProvinceID).peaceTreatyIsToTake) {
            if (onlyTake) {
                if (!Game.player.peaceTreaty.isProvinceTaken(nProvinceID)) {
                    Game.player.peaceTreaty.takeProvince(nProvinceID);
                }
            } else if (CFG.brushTool) {
                if (InGame_Peace.brushModeDemand) {
                    if (!Game.player.peaceTreaty.isProvinceTaken(nProvinceID)) {
                        Game.player.peaceTreaty.takeProvince(nProvinceID);
                    }
                } else if (Game.player.peaceTreaty.isProvinceTaken(nProvinceID)) {
                    Game.player.peaceTreaty.takeProvince(nProvinceID);
                }
            } else {
                Game.player.peaceTreaty.takeProvince(nProvinceID);
            }
        }

    }

    public static void actionReleaseVassal(int nProvinceID, boolean onlyAdd) {
        if (Game.getProvince(nProvinceID).getCivID() == Game.player.iCivID) {
            if (!onlyAdd && InGame_ReleaseAVassal.releaseVassalData.lProvinces.contains(nProvinceID)) {
                InGame_ReleaseAVassal.removeProvince(nProvinceID);
                Game.getProvince(nProvinceID).peaceTreatyIsTaken = false;
            } else {
                InGame_ReleaseAVassal.addProvince(nProvinceID);
                Game.getProvince(nProvinceID).peaceTreatyIsTaken = true;
            }
        }

    }

    public static void actionSetActiveProvinceID() {
        int tActiveProvinceID = Game.iActiveProvince;
        Game.menuManager.hideCourtCiv();
        Game.menuManager.setVisibleInGame_Buildings(false, false);
        if (Game.menuManager.getVisibleInGame_TechnologyChoose()) {
            Game.menuManager.setVisibleInGame_TechnologyChoose(false);
        }

        if (Game.menuManager.getVisibleInGame_TechnologyTree()) {
            Game.menuManager.setVisibleInGame_TechnologyTree(false);
        }

        if (Game.menuManager.getVisibleDeclareWar()) {
            Game.menuManager.setVisibleInGame_PopUp(false);
        }

        if (Game.menuManager.getVisible_SpecialAlliance()) {
            Game.menuManager.setVisibleInGame_PopUp(false);
        }

        if (Game.menuManager.getVisibleFormCiv() || Game.menuManager.getVisibleSellProvince() || Game.menuManager.getVisibleRevolutions()) {
            Game.menuManager.setVisibleInGame_PopUp(false);
        }

        if (Game.menuManager.getVisibleInGame_CurrentSituation()) {
            Game.menuManager.setVisibleInGame_CurrentSituation(false);
        }

        if (Game.menuManager.getVisibleInGame_Nukes()) {
            Game.menuManager.setVisibleInGame_Nukes(false);
        }

        if (Game.menuManager.getVisibleInGame_GoodsMarket()) {
            Game.menuManager.setVisibleInGame_GoodsMarket(false);
        }

        Game.setActiveProvinceID(tActiveProvinceID);
        ProvinceBorderManager.action.setProvinceID(Game.iActiveProvince);
        if(FontFix.isCommandMode){
            Game.clearActiveArmy();
            int size = Game.getProvince(Game.iActiveProvince).getArmySize();
            for (int i = 0; i < size; i++) {
                FontFix.addActiveArmy(Game.iActiveProvince,Game.getProvince(Game.iActiveProvince).getArmy(i).key);
            }
            FinalityLogger.debug("AAA-C "+Game.activeArmySize);
            actionUp_SetActiveArmy();
        }else {
            Game.menuManager.rebuildInGame_ProvinceInfo(true);
        }
    }

    public interface ExtraAction {
        void extraAction(int var1, int var2, int var3, int var4);
    }
}
