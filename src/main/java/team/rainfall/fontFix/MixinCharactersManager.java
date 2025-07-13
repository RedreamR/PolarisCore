package team.rainfall.fontFix;

import aoc.kingdoms.lukasz.jakowski.*;
import aoc.kingdoms.lukasz.map.advisors.Advisor;
import aoc.kingdoms.lukasz.map.advisors.AdvisorManager;
import team.rainfall.finality.luminosity2.annotations.Mixin;
import team.rainfall.fontFix.utils.AdvisorHelper;

@Mixin(mixinClass = "aoc.kingdoms.lukasz.jakowski.CharactersManager")
public class MixinCharactersManager {
    public static final void loadAdvisor(int iCivID, String fileName, int iAdvisorType) {
        try {
            if (FileManager.loadFile("game/characters/" + fileName + ".json").exists()) {
                    try {
                            Advisor advisor = AdvisorHelper.loadAdvisor(iCivID,fileName,iAdvisorType);
                            switch (iAdvisorType) {
                                case 0:
                                    if (Game.getCiv(iCivID).advisorAdministration.sName != null) {
                                        AdvisorManager.updateCivBonuses(Game.getCiv(iCivID).advisorAdministration, iCivID, -1);
                                    }

                                    Game.getCiv(iCivID).advisorAdministration = advisor;
                                    AdvisorManager.updateCivBonuses(Game.getCiv(iCivID).advisorAdministration, iCivID, 1);
                                    return;
                                case 1:
                                    if (Game.getCiv(iCivID).advisorEconomy.sName != null) {
                                        AdvisorManager.updateCivBonuses(Game.getCiv(iCivID).advisorEconomy, iCivID, -1);
                                    }

                                    Game.getCiv(iCivID).advisorEconomy = advisor;
                                    AdvisorManager.updateCivBonuses(Game.getCiv(iCivID).advisorEconomy, iCivID, 1);
                                    return;
                                case 2:
                                    if (Game.getCiv(iCivID).advisorTechnology.sName != null) {
                                        AdvisorManager.updateCivBonuses(Game.getCiv(iCivID).advisorTechnology, iCivID, -1);
                                    }

                                    Game.getCiv(iCivID).advisorTechnology = advisor;
                                    AdvisorManager.updateCivBonuses(Game.getCiv(iCivID).advisorTechnology, iCivID, 1);
                                    return;
                                default:
                                    if (Game.getCiv(iCivID).advisorMilitary.sName != null) {
                                        AdvisorManager.updateCivBonuses(Game.getCiv(iCivID).advisorMilitary, iCivID, -1);
                                    }

                                    Game.getCiv(iCivID).advisorMilitary = advisor;
                                    AdvisorManager.updateCivBonuses(Game.getCiv(iCivID).advisorMilitary, iCivID, 1);
                            }
                    } catch (Exception ex) {
                        CFG.exceptionStack(ex);
                    }
            }
        } catch (Exception ex) {
            CFG.exceptionStack(ex);
        }

    }
}
