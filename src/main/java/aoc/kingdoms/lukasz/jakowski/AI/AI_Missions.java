//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.jakowski.AI;

import aoc.kingdoms.lukasz.jakowski.CFG;
import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.jakowski.Missions.MissionTree;

@SuppressWarnings("unused")
public class AI_Missions {
    public AI_Missions() {
    }

    public static final void updateMissions() {
        try {
            int i;
            for(i = 1 + Game_Calendar.TURN_ID % GameValues.gameUpdateAI.GAME_UPDATE_AI_MISSIONS; i < Game.player.iCivID; i += GameValues.gameUpdateAI.GAME_UPDATE_AI_MISSIONS) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    updateMissions(i);
                }
            }

            if (i == Game.player.iCivID) {
                i += GameValues.gameUpdateAI.GAME_UPDATE_AI_MISSIONS;
            }

            for(; i < Game.getCivsSize(); i += GameValues.gameUpdateAI.GAME_UPDATE_AI_MISSIONS) {
                if (Game.getCiv(i).getNumOfProvinces() > 0) {
                    updateMissions(i);
                }
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }

    public static final void updateMissions(int civID) {
        try {
            if (Game.getCiv(civID).iMissionsSize > 0) {
                for(int i = 0; i < Game.getCiv(civID).iMissionsSize; ++i) {
                    if (MissionTree.canRunMission_Civ(civID, i) && !Game.getCiv(civID).lMissions.get(i).event.options.isEmpty()) {
                        int score = 0;

                        for(int a = 0; a < Game.getCiv(civID).lMissions.get(i).event.options.size(); ++a) {
                            score = (int)((float)score + Game.getCiv(civID).lMissions.get(i).event.options.get(a).ai);
                        }

                        int takeID = 0;
                        if (score > 0) {
                            score = Game.oR.nextInt(score);
                            int a = 0;

                            for(int currentScore = 0; a < Game.getCiv(civID).lMissions.get(i).event.options.size(); ++a) {
                                currentScore += (int) Game.getCiv(civID).lMissions.get(i).event.options.get(a).ai;
                                if ((float)score <= (float)currentScore) {
                                    takeID = a;
                                    break;
                                }
                            }
                        }

                        Game.getCiv(civID).eventsDataVariables.addVariable(Game.getCiv(civID).lMissions.get(i).event.id);
                        Game.getCiv(civID).lMissions.get(i).event.options.get(takeID).executeOutcome(civID);
                    }
                }
            } else {
                for(int i = 0; i < MissionTree.iMissionsSize; ++i) {
                    if (MissionTree.canRunMission(civID, i) && !MissionTree.lMissions.get(i).event.options.isEmpty()) {
                        int score = 0;

                        for(int a = 0; a < MissionTree.lMissions.get(i).event.options.size(); ++a) {
                            score = (int)((float)score + MissionTree.lMissions.get(i).event.options.get(a).ai);
                        }

                        int takeID = 0;
                        if (score > 0) {
                            score = Game.oR.nextInt(score);
                            int a = 0;

                            for(int currentScore = 0; a < MissionTree.lMissions.get(i).event.options.size(); ++a) {
                                currentScore += (int) MissionTree.lMissions.get(i).event.options.get(a).ai;
                                if ((float)score <= (float)currentScore) {
                                    takeID = a;
                                    break;
                                }
                            }
                        }

                        Game.getCiv(civID).eventsDataVariables.addVariable(MissionTree.lMissions.get(i).event.id);
                        MissionTree.lMissions.get(i).event.options.get(takeID).executeOutcome(civID);
                    }
                }
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }
}
