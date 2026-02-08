//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.jakowski.Player.More;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.jakowski.Missions.MissionTree;
import aoc.kingdoms.lukasz.map.LawsManager;
import aoc.kingdoms.lukasz.map.LegacyManager;
import aoc.kingdoms.lukasz.map.WondersManager;
import aoc.kingdoms.lukasz.map.advisors.AdvisorManager;
import team.rainfall.finality.FinalityLogger;

import java.util.ArrayList;
import java.util.List;

public class PlayerCurrentSituation {
    public int currentSituationNum = 0;
    public boolean noActiveResearch = false;
    public boolean lackOfGeneral = false;
    public boolean availableCivilizationLegacy = false;
    public boolean availableAdvantage = false;
    public boolean wonderCanBeBuilt = false;
    public boolean newLawAvailable = false;
    public int newLawAvailableNum = 0;
    public int noAdvisor = 0;
    public int promoteAdvisor = 0;
    public boolean upgradeCapitalCity = false;
    public boolean militaryAcademyCanBeUpgraded = false;
    public boolean militaryAcademyForGeneralsCanBeUpgraded = false;
    public boolean upgradeSupremeCourt = false;
    public boolean upgradeNuclearReactor = false;
    public boolean nonCoreProvinces = false;
    public int nonCoreProvincesNum = 0;
    public boolean differentReligionProvinces = false;
    public int differentReligionProvincesNum = 0;
    public boolean maxAmountOfGold = false;
    public boolean highInflation = false;
    public boolean chooseRivals = false;
    public boolean allMissionsUnlocked = false;
    public boolean missionCanBeUnlocked = false;
    public int missionCanBeUnlockedNum = 0;
    public List<Integer> playerLegaciesLVL = new ArrayList();

    public PlayerCurrentSituation() {
    }

    public final void updateCurrentSituation() {
        this.updateCurrentSituation(Game.player.iCivID);
    }

    public final void updateCurrentSituation(int iCivID) {
        try {
            FinalityLogger.debug("GTU 4");
            this.currentSituationNum = 0;
            if (Game.getCiv(Game.player.iCivID).getActiveTechResearch() < 0) {
                this.noActiveResearch = true;
                ++this.currentSituationNum;
            } else {
                this.noActiveResearch = false;
            }

            if (Game.getCiv(Game.player.iCivID).getAdvantagePoints() > 0) {
                this.availableAdvantage = true;
                ++this.currentSituationNum;
            } else {
                this.availableAdvantage = false;
            }

            this.updateLackOfGeneral(iCivID);
            this.updateAvailableCivilizationLegacy(iCivID);
            this.updateWonderCanBeBuild(iCivID);
            this.updateMilitaryAcademyCanBeUpgraded(iCivID);
            this.updateMilitaryAcademyForGeneralsCanBeUpgraded(iCivID);
            this.updateNonCoreProvinces(iCivID);
            this.updateDifferentReligion(iCivID);
            this.updateNoAdvisor(iCivID);
            this.updateNewLaw(iCivID);
            this.updateChooseRivals(iCivID);
            this.highInflation = Game.getCiv(iCivID).getInflation() >= GameValues.inflation.INFLATION_CURRENT_SITUATION_INFO;
            if (this.highInflation) {
                ++this.currentSituationNum;
            }

            this.maxAmountOfGold = Game.getCiv(Game.player.iCivID).fGold >= (float)Game.getMaxAmountOfGold(iCivID);
            if (this.maxAmountOfGold) {
                ++this.currentSituationNum;
            }

            this.upgradeSupremeCourt = false;
            if (Game.getCiv(iCivID).getCorruption() > 0.005F && Game.getCiv(iCivID).getSupremeCourtLevel() < Game.getSupremeCourt_MaxLvl(iCivID) && Game.getCiv(iCivID).fGold > Game.getSupremeCourt_Cost(iCivID)) {
                this.upgradeSupremeCourt = true;
                ++this.currentSituationNum;
            }

            this.upgradeNuclearReactor = false;
            if (Game.getCiv(iCivID).canBuildNuke && Game.getCiv(iCivID).getNuclearReactorLevel() < Game.getNuclearReactor_MaxLvl(iCivID) && Game.getCiv(iCivID).fGold > Game.getNuclearReactor_Cost(iCivID)) {
                this.upgradeNuclearReactor = true;
                ++this.currentSituationNum;
            }

            this.upgradeCapitalCity = false;
            if (Game.getCiv(iCivID).getCapitalLevel() < Game.getCapital_MaxLvl(iCivID) && Game.getCiv(iCivID).fGold > Game.getCapital_Cost(iCivID)) {
                this.upgradeCapitalCity = true;
                ++this.currentSituationNum;
            }
            FinalityLogger.debug("GTU 4");
            this.updateMissionsCanBeUnlocked(iCivID);
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    public final void updateNewLaw(int iCivID) {
        this.newLawAvailable = false;
        this.newLawAvailableNum = 0;

        for(int i = 0; i < LawsManager.iLawsSize; ++i) {
            for(int j = ((LawsManager.Law)LawsManager.laws.get(i)).RequiredTechID.length - 1; j > 0; --j) {
                if (Game.getCiv(iCivID).getTechResearched(((LawsManager.Law)LawsManager.laws.get(i)).RequiredTechID[j]) && (((LawsManager.Law)LawsManager.laws.get(i)).RequiredGovernmentID == null || ((LawsManager.Law)LawsManager.laws.get(i)).RequiredGovernmentID[j] < 0 || ((LawsManager.Law)LawsManager.laws.get(i)).RequiredGovernmentID[j] == Game.getCiv(iCivID).getIdeologyID())) {
                    if ((Integer)Game.getCiv(iCivID).laws.get(i) < j) {
                        if (!this.newLawAvailable) {
                            ++this.currentSituationNum;
                        }

                        this.newLawAvailable = true;
                        ++this.newLawAvailableNum;
                    }
                    break;
                }
            }
        }

    }

    public final void updateChooseRivals(int iCivID) {
        this.chooseRivals = false;
        if (Game.getCiv(iCivID).diplomacy.rivals.size() < GameValues.rivals.RIVALS_LIMIT) {
            this.chooseRivals = true;
            ++this.currentSituationNum;
        }

    }

    private final void updateNoAdvisor(int iCivID) {
        try {
            this.noAdvisor = 0;
            this.promoteAdvisor = 0;
            int maxLevel = AdvisorManager.getAdvisorMaxLevel(iCivID);
            if (Game.getCiv(iCivID).advisorAdministration.sName == null) {
                ++this.noAdvisor;
            } else if (Game.getCiv(iCivID).advisorAdministration.iLevel < maxLevel) {
                ++this.promoteAdvisor;
            }

            if (Game.getCiv(iCivID).advisorEconomy.sName == null) {
                ++this.noAdvisor;
            } else if (Game.getCiv(iCivID).advisorEconomy.iLevel < maxLevel) {
                ++this.promoteAdvisor;
            }

            if (Game.getCiv(iCivID).advisorTechnology.sName == null) {
                ++this.noAdvisor;
            } else if (Game.getCiv(iCivID).advisorTechnology.iLevel < maxLevel) {
                ++this.promoteAdvisor;
            }

            if (Game.getCiv(iCivID).advisorMilitary.sName == null) {
                ++this.noAdvisor;
            } else if (Game.getCiv(iCivID).advisorMilitary.iLevel < maxLevel) {
                ++this.promoteAdvisor;
            }

            if (this.noAdvisor > 0) {
                ++this.currentSituationNum;
            }

            if (this.promoteAdvisor > 0) {
                ++this.currentSituationNum;
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    private final void updateLackOfGeneral(int iCivID) {
        try {
            this.lackOfGeneral = false;

            for(int i = 0; i < Game.getCiv(iCivID).iArmyPositionSize; ++i) {
                for(int j = 0; j < Game.getProvince(Game.getCiv(iCivID).getArmyPosition(i)).getArmySize(); ++j) {
                    if (Game.getProvince(Game.getCiv(iCivID).getArmyPosition(i)).getArmy(j).civID == iCivID && Game.getProvince(Game.getCiv(iCivID).getArmyPosition(i)).getArmy(j).armyGeneral == null) {
                        this.lackOfGeneral = true;
                        ++this.currentSituationNum;
                        return;
                    }
                }
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    private final void updateAvailableCivilizationLegacy(int iCivID) {
        try {
            this.availableCivilizationLegacy = false;

            for(int i = 0; i < LegacyManager.iLegaciesSize; ++i) {
                if ((Integer)this.playerLegaciesLVL.get(i) >= 0 && Game.getCiv(iCivID).fLegacy >= (float)((LegacyManager.Legacy)LegacyManager.legacies.get(i)).CostLegacy[(Integer)this.playerLegaciesLVL.get(i)]) {
                    this.availableCivilizationLegacy = true;
                    ++this.currentSituationNum;
                    break;
                }
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    public final void buildPlayerLegaciesLVL() {
        this.buildPlayerLegaciesLVL(Game.player.iCivID);
    }

    public final void buildPlayerLegaciesLVL(int iCivID) {
        try {
            this.playerLegaciesLVL.clear();

            for(int i = 0; i < LegacyManager.iLegaciesSize; ++i) {
                int tLevel = Game.getCiv(iCivID).getLegacyLevel(i) + 1;
                if (tLevel >= ((LegacyManager.Legacy)LegacyManager.legacies.get(i)).CostLegacy.length) {
                    tLevel = -1;
                }

                this.playerLegaciesLVL.add(tLevel);
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    public final void updateWonderCanBeBuild(int iCivID) {
        try {
            this.wonderCanBeBuilt = false;

            for(int i = 0; i < Game.getCiv(iCivID).getNumOfProvinces(); ++i) {
                if (Game.getProvince(Game.getCiv(iCivID).getProvinceID(i)).wonderID >= 0 && !Game.getProvince(Game.getCiv(iCivID).getProvinceID(i)).getWonderBuilt() && Game.getProvince(Game.getCiv(iCivID).getProvinceID(i)).wonderConstruction == null && Game.getCiv(iCivID).fGold >= WondersManager.getWonderConstructionCost(Game.getCiv(iCivID).getProvinceID(i), Game.getProvince(Game.getCiv(iCivID).getProvinceID(i)).wonderID)) {
                    this.wonderCanBeBuilt = true;
                    ++this.currentSituationNum;
                    break;
                }
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    public final void updateMilitaryAcademyCanBeUpgraded(int iCivID) {
        try {
            this.militaryAcademyCanBeUpgraded = false;
            if (Game.getCiv(iCivID).getMilitaryAcademyLevel() < Game.getMilitaryAcademy_MaxLvl(iCivID) && Game.getCiv(iCivID).fGold >= Game.getMilitaryAcademy_Cost(iCivID)) {
                this.militaryAcademyCanBeUpgraded = true;
                ++this.currentSituationNum;
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    public final void updateMilitaryAcademyForGeneralsCanBeUpgraded(int iCivID) {
        try {
            this.militaryAcademyForGeneralsCanBeUpgraded = false;
            if (Game.getCiv(iCivID).getMilitaryAcademyForGeneralsLevel() < Game.getMilitaryAcademyForGenerals_MaxLvl(iCivID) && Game.getCiv(iCivID).fGold >= Game.getMilitaryAcademyForGenerals_Cost(iCivID)) {
                this.militaryAcademyForGeneralsCanBeUpgraded = true;
                ++this.currentSituationNum;
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    public final void updateNonCoreProvinces(int iCivID) {
        try {
            this.nonCoreProvinces = false;
            this.nonCoreProvincesNum = 0;

            for(int i = Game.getCiv(iCivID).getNumOfProvinces() - 1; i >= 0; --i) {
                if (!Game.getProvince(Game.getCiv(iCivID).getProvinceID(i)).isOccupied() && !Game.getProvince(Game.getCiv(iCivID).getProvinceID(i)).haveACore(iCivID) && Game.getProvince(Game.getCiv(iCivID).getProvinceID(i)).coreCreation == null) {
                    if (!this.nonCoreProvinces) {
                        ++this.currentSituationNum;
                    }

                    this.nonCoreProvinces = true;
                    ++this.nonCoreProvincesNum;
                }
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    public final void updateDifferentReligion(int iCivID) {
        try {
            this.differentReligionProvinces = false;
            this.differentReligionProvincesNum = 0;

            for(int i = Game.getCiv(iCivID).getNumOfProvinces() - 1; i >= 0; --i) {
                if (!Game.getProvince(Game.getCiv(iCivID).getProvinceID(i)).isOccupied() && Game.getProvince(Game.getCiv(iCivID).getProvinceID(i)).getReligion() != Game.getCiv(iCivID).getReligionID() && Game.getProvince(Game.getCiv(iCivID).getProvinceID(i)).religionConversion == null) {
                    if (!this.differentReligionProvinces) {
                        ++this.currentSituationNum;
                    }

                    this.differentReligionProvinces = true;
                    ++this.differentReligionProvincesNum;
                }
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    public final void updateMissionsCanBeUnlocked(int iCivID) {
        try {
            if (Game_Calendar.TURN_ID % GameValues.gameUpdate.GAME_UPDATE_CURRENT_SITUATION_MISSION_TREE_EVERY_X_DAYS == 0) {
                this.missionCanBeUnlocked = false;
                this.missionCanBeUnlockedNum = 0;
                if (Game.getCiv(iCivID).iMissionsSize > 0) {
                    for(int i = 0; i < Game.getCiv(iCivID).iMissionsSize; ++i) {
                        if (MissionTree.canRunMission_Civ(iCivID, i)) {
                            this.missionCanBeUnlocked = true;
                            ++this.missionCanBeUnlockedNum;
                        }
                    }

                    if (Game.getCiv(iCivID).iMissionsSize == this.missionCanBeUnlockedNum) {
                        this.allMissionsUnlocked = true;
                    }
                } else {
                    for(int i = 0; i < MissionTree.iMissionsSize; ++i) {
                        if (MissionTree.canRunMission(iCivID, i)) {
                            this.missionCanBeUnlocked = true;
                            ++this.missionCanBeUnlockedNum;
                        }
                    }

                    if (MissionTree.iMissionsSize == this.missionCanBeUnlockedNum) {
                        this.allMissionsUnlocked = true;
                    }
                }
            }

            if (this.missionCanBeUnlocked) {
                ++this.currentSituationNum;
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }
}
