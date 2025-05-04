//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.jakowski.AI.Technology;

import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.map.technology.TechnologyTree;
import java.util.ArrayList;
import java.util.List;

public class AI_SelectTechnology {
    public AI_SelectTechnology() {
    }

    public static void selectTechnology(int civID) {
        List<Integer> possibleResearch = new ArrayList();

        for(int i = 0; i < TechnologyTree.iTechnologySize; ++i) {
            if (Game.getCiv(civID).getAvailableToResearch(i) && TechnologyTree.lTechnology.get(i).AI != 727772) {
                possibleResearch.add(i);
            }
        }

        if (!possibleResearch.isEmpty()) {
            if (possibleResearch.size() == 1) {
                Game.getCiv(civID).setActiveTechResearch((Integer)possibleResearch.get(0));
                Game.getCiv(civID).iAlternativeTechResearch = -1;
            } else {
                int totalScore = 0;

                for(int i = possibleResearch.size() - 1; i >= 0; --i) {
                    totalScore += ((TechnologyTree.Technology)TechnologyTree.lTechnology.get((Integer)possibleResearch.get(i))).AI;
                }

                if (totalScore <= 0) {
                    Game.getCiv(civID).setActiveTechResearch((Integer)possibleResearch.get(0));
                    Game.getCiv(civID).iAlternativeTechResearch = -1;
                } else {
                    int select = Game.oR.nextInt(totalScore);
                    int i = 0;

                    for(int cScore = 0; i < possibleResearch.size(); ++i) {
                        if (select < cScore + ((TechnologyTree.Technology)TechnologyTree.lTechnology.get((Integer)possibleResearch.get(i))).AI) {
                            Game.getCiv(civID).setActiveTechResearch((Integer)possibleResearch.get(i));
                            Game.getCiv(civID).iAlternativeTechResearch = -1;
                            return;
                        }

                        cScore += ((TechnologyTree.Technology)TechnologyTree.lTechnology.get((Integer)possibleResearch.get(i))).AI;
                    }

                    Game.getCiv(civID).setActiveTechResearch((Integer)possibleResearch.get(0));
                    Game.getCiv(civID).iAlternativeTechResearch = -1;
                }
            }
        }
    }
}
