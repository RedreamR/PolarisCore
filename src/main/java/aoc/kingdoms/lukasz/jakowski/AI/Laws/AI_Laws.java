//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.jakowski.AI.Laws;

import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.AI.Government.AI_Capital;
import aoc.kingdoms.lukasz.map.LawsManager;

public class AI_Laws {
    public AI_Laws() {
    }

    public static final void adoptNewLaws(int civID) {
        AI_Capital.checkCapital(civID);
        if(Game.getCiv(civID).eventsDataVariables.hasVariable("$$PolarizedLight_NATCP")){
            return;
        }
        if (!(Game.getCiv(civID).fLegacy < (float)GameValues.laws.LAW_ADAPT_REFORM_COST_LEGACY_POINTS)) {
            if (!(Game.getCiv(civID).fGold < (float)GameValues.laws.LAW_ADAPT_REFORM_COST_GOLD)) {
                for(int i = 0; i < LawsManager.iLawsSize; ++i) {
                    if (Game.getCiv(civID).laws.get(i) + 1 < LawsManager.laws.get(i).RequiredTechID.length && !adoptNewLaw(civID, i)) {
                        if (Game.getCiv(civID).fLegacy < (float)GameValues.laws.LAW_ADAPT_REFORM_COST_LEGACY_POINTS) {
                            return;
                        }

                        if (Game.getCiv(civID).fGold < (float)GameValues.laws.LAW_ADAPT_REFORM_COST_GOLD) {
                            return;
                        }
                    }
                }

            }
        }
    }

    public static boolean adoptNewLaw(int civID, int lawID) {
        for(int j = LawsManager.laws.get(lawID).RequiredTechID.length - 1; j > Game.getCiv(civID).laws.get(lawID); --j) {
            if (Game.getCiv(civID).getTechResearched(LawsManager.laws.get(lawID).RequiredTechID[j]) && (LawsManager.laws.get(lawID).RequiredGovernmentID == null || LawsManager.laws.get(lawID).RequiredGovernmentID[j] < 0 || LawsManager.laws.get(lawID).RequiredGovernmentID[j] == Game.getCiv(civID).getIdeologyID())) {
                return LawsManager.adoptReform(civID, lawID, j);
            }
        }

        return true;
    }
}
