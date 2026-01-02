//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package aoc.kingdoms.lukasz.map.civilization;

import aoc.kingdoms.lukasz.jakowski.Game;
import aoc.kingdoms.lukasz.jakowski.GameValues;
import aoc.kingdoms.lukasz.jakowski.Game_Calendar;
import aoc.kingdoms.lukasz.map.advisors.Advisor;
import aoc.kingdoms.lukasz.map.advisors.AdvisorManager;
import team.rainfall.fontFix.utils.AdvisorHelper;
import team.rainfall.fontFix.utils.PoolAdvisor;

import java.util.ArrayList;
import java.util.List;

public class CivilizationAdvisorsPool {
    public List<Advisor> lAdvisors = new ArrayList();
    public int generateYear = -55000;
    public int iAdvisorType;

    public CivilizationAdvisorsPool(int iAdvisorType) {
        this.iAdvisorType = iAdvisorType;
    }
    public final void generateAdvisors2(int iCivID, String sIMG) {
        int j = 0;
        for (PoolAdvisor poolAdvisor : AdvisorHelper.poolAdvisors) {
            if(j >= this.getPoolOfAdvisors(iCivID)){
                return;
            }
            if(poolAdvisor.civID == iCivID && poolAdvisor.type == iAdvisorType){
                this.lAdvisors.add(poolAdvisor.advisor);
                j++;
            }
        }
        for(int i = this.lAdvisors.size(); i < this.getPoolOfAdvisors(iCivID); ++i) {
            int advIMG;
            if (this.iAdvisorType == 3) {
                advIMG = Game.advisorManager.getRandomGeneralImage(iCivID);
            } else {
                advIMG = Game.advisorManager.getRandomImage(iCivID, this.iAdvisorType);
            }

            this.lAdvisors.add(new Advisor(Game.generalManager.getGeneralRandomName(iCivID) + " " + Game.generalManager.getGeneralRandomSurname(iCivID), advIMG, Game_Calendar.currentYear - GameValues.advisors.ADVISOR_YEARS_OLD_MIN - Game.oR.nextInt(Math.max(1, GameValues.advisors.ADVISOR_YEARS_OLD_RANDOM)), sIMG));
            int advID = this.lAdvisors.size() - 1;
            this.lAdvisors.set(advID, buildAdvisorBonuses(this.lAdvisors.get(advID), this.iAdvisorType));
        }

    }
    public final void generateAdvisors(int iCivID, String sIMG) {
        for(int i = this.lAdvisors.size(); i < this.getPoolOfAdvisors(iCivID); ++i) {
            int advIMG;
            if (this.iAdvisorType == 3) {
                advIMG = Game.advisorManager.getRandomGeneralImage(iCivID);
            } else {
                advIMG = Game.advisorManager.getRandomImage(iCivID, this.iAdvisorType);
            }

            this.lAdvisors.add(new Advisor(Game.generalManager.getGeneralRandomName(iCivID) + " " + Game.generalManager.getGeneralRandomSurname(iCivID), advIMG, Game_Calendar.currentYear - GameValues.advisors.ADVISOR_YEARS_OLD_MIN - Game.oR.nextInt(Math.max(1, GameValues.advisors.ADVISOR_YEARS_OLD_RANDOM)), sIMG));
            int advID = this.lAdvisors.size() - 1;
            this.lAdvisors.set(advID, buildAdvisorBonuses(this.lAdvisors.get(advID), this.iAdvisorType));
        }

    }

    public static final Advisor generateAdvisor_Random(int iCivID, int iAdvisorType) {
        int advIMG;
        if (iAdvisorType == 3) {
            advIMG = Game.advisorManager.getRandomGeneralImage(iCivID);
        } else {
            advIMG = Game.advisorManager.getRandomImage(iCivID, iAdvisorType);
        }

        return buildAdvisorBonuses(new Advisor(Game.generalManager.getGeneralRandomName(iCivID) + " " + Game.generalManager.getGeneralRandomSurname(iCivID), advIMG, Game_Calendar.currentYear - GameValues.advisors.ADVISOR_YEARS_OLD_MIN - Game.oR.nextInt(Math.max(1, GameValues.advisors.ADVISOR_YEARS_OLD_RANDOM)), null), iAdvisorType);
    }

    public static final Advisor buildAdvisorBonuses(Advisor advisor, int iAdvisorType) {
        int random = 0;
        if (iAdvisorType == 0) {
            random = (Game.oR.nextInt(5000) + Game.oR.nextInt(2750)) % 4;
            switch (random) {
                case 0:
                    advisor.TaxEfficiency = GameValues.advisors.ADVISOR_TAX_EFFICIENCY_MIN + (float)Game.oR.nextInt((int)(GameValues.advisors.ADVISOR_TAX_EFFICIENCY_RANDOM * 100.0F)) / 100.0F;
                    break;
                case 1:
                    advisor.ProvinceMaintenance = -(GameValues.advisors.ADVISOR_PROVINCE_MAINTENANCE_MIN + (float)Game.oR.nextInt((int)(GameValues.advisors.ADVISOR_PROVINCE_MAINTENANCE_RANDOM * 100.0F)) / 100.0F);
                    break;
                case 2:
                    advisor.AdministrationBuildingsCost = -(GameValues.advisors.ADVISOR_CONSTRUCTION_GROUP_COST_MIN + (float)Game.oR.nextInt((int)(GameValues.advisors.ADVISOR_CONSTRUCTION_GROUP_COST_RANDOM * 10000.0F)) / 10000.0F);
                    break;
                case 3:
                    advisor.IncreaseGrowthRateCost = -(GameValues.advisors.ADVISOR_INCREASE_GROWTH_RATE_COST_MIN + (float)Game.oR.nextInt((int)(GameValues.advisors.ADVISOR_INCREASE_GROWTH_RATE_COST_RANDOM * 10000.0F)) / 10000.0F);
                    break;
                default:
                    advisor.GrowthRate = GameValues.advisors.ADVISOR_GROWTH_RATE_MIN + (float)Game.oR.nextInt((int)(GameValues.advisors.ADVISOR_GROWTH_RATE_RANDOM * 100.0F)) / 100.0F;
            }

            random = (Game.oR.nextInt(5000) + Game.oR.nextInt(2750)) % 3;
            switch (random) {
                case 0:
                    advisor.ConstructionTime = -(GameValues.advisors.ADVISOR_CONSTRUCTION_TIME_MIN + (float)Game.oR.nextInt((int)(GameValues.advisors.ADVISOR_CONSTRUCTION_TIME_RANDOM * 10000.0F)) / 10000.0F);
                    break;
                case 1:
                    advisor.IncreaseManpowerCost = -(GameValues.advisors.ADVISOR_INCREASE_MANPOWER_COST_MIN + (float)Game.oR.nextInt((int)(GameValues.advisors.ADVISOR_INCREASE_MANPOWER_COST_RANDOM * 100.0F)) / 100.0F);
                    break;
                default:
                    advisor.RecruitmentTime = -(GameValues.advisors.ADVISOR_RECRUITMENT_TIME_MIN + (float)Game.oR.nextInt((int)(GameValues.advisors.ADVISOR_RECRUITMENT_TIME_RANDOM * 100.0F)) / 100.0F);
            }
        } else if (iAdvisorType == 1) {
            random = (Game.oR.nextInt(5000) + Game.oR.nextInt(3500)) % 100;
            if (random > 97) {
                advisor.ConstructionCost = -(GameValues.advisors.ADVISOR_CONSTRUCTION_COST_MIN + (float)Game.oR.nextInt((int)(GameValues.advisors.ADVISOR_CONSTRUCTION_COST_RANDOM * 100.0F)) / 100.0F);
            } else {
                random = (Game.oR.nextInt(5000) + Game.oR.nextInt(2750)) % 5;
                switch (random) {
                    case 0:
                        advisor.EconomyBuildingsCost = -(GameValues.advisors.ADVISOR_CONSTRUCTION_GROUP_COST_MIN + (float)Game.oR.nextInt((int)(GameValues.advisors.ADVISOR_CONSTRUCTION_GROUP_COST_RANDOM * 10000.0F)) / 10000.0F);
                        break;
                    case 1:
                        advisor.InvestInEconomyCost = -(GameValues.advisors.ADVISOR_INVEST_COST_MIN + (float)Game.oR.nextInt((int)(GameValues.advisors.ADVISOR_INVEST_COST_RANDOM * 10000.0F)) / 10000.0F);
                        break;
                    case 2:
                        advisor.IncreaseTaxEfficiencyCost = -(GameValues.advisors.ADVISOR_INCREASE_TAX_EFFICIENCY_COST_MIN + (float)Game.oR.nextInt((int)(GameValues.advisors.ADVISOR_INCREASE_TAX_EFFICIENCY_COST_RANDOM * 10000.0F)) / 10000.0F);
                        break;
                    case 3:
                        advisor.DevelopInfrastructureCost = -(GameValues.advisors.ADVISOR_DEVELOP_INFRASTRUCTURE_COST_MIN + (float)Game.oR.nextInt((int)(GameValues.advisors.ADVISOR_DEVELOP_INFRASTRUCTURE_COST_RANDOM * 10000.0F)) / 10000.0F);
                        break;
                    default:
                        advisor.ProductionEfficiency = GameValues.advisors.ADVISOR_PRODUCTION_EFFICIENCY_MIN + (float)Game.oR.nextInt((int)(GameValues.advisors.ADVISOR_PRODUCTION_EFFICIENCY_RANDOM * 100.0F)) / 100.0F;
                }
            }

            random = (Game.oR.nextInt(5000) + Game.oR.nextInt(2750)) % 4;
            switch (random) {
                case 0:
                    advisor.LoanInterest = -(GameValues.advisors.ADVISOR_LOAN_INTEREST_MIN + (float)Game.oR.nextInt((int)(GameValues.advisors.ADVISOR_LOAN_INTEREST_RANDOM * 100.0F)) / 100.0F);
                    break;
                case 1:
                    advisor.CoreCost = -(GameValues.advisors.ADVISOR_CORE_COST_MIN + (float)Game.oR.nextInt((int)(GameValues.advisors.ADVISOR_CORE_COST_RANDOM * 100.0F)) / 100.0F);
                    break;
                case 2:
                    advisor.IncomeProduction = GameValues.advisors.ADVISOR_INCOME_PRODUCTION_MIN + (float)Game.oR.nextInt((int)(GameValues.advisors.ADVISOR_INCOME_PRODUCTION_RANDOM * 100.0F)) / 100.0F;
                    break;
                default:
                    advisor.ReligionCost = -(GameValues.advisors.ADVISOR_CONVERT_RELIGION_COST_MIN + (float)Game.oR.nextInt((int)(GameValues.advisors.ADVISOR_CONVERT_RELIGION_COST_RANDOM * 100.0F)) / 100.0F);
            }
        } else if (iAdvisorType == 2) {
            random = (Game.oR.nextInt(5000) + Game.oR.nextInt(2750)) % 3;
            switch (random) {
                case 0:
                    advisor.Research = GameValues.advisors.ADVISOR_RESEARCH_MIN + (float)Game.oR.nextInt((int)(GameValues.advisors.ADVISOR_RESEARCH_RANDOM * 100.0F)) / 100.0F;
                    break;
                case 1:
                    advisor.MonthlyLegacy = GameValues.advisors.ADVISOR_LEGACY_MIN + (float)Game.oR.nextInt((int)(GameValues.advisors.ADVISOR_LEGACY_RANDOM * 100.0F)) / 100.0F;
                    break;
                default:
                    advisor.Research = GameValues.advisors.ADVISOR_RESEARCH_MIN + (float)Game.oR.nextInt((int)(GameValues.advisors.ADVISOR_RESEARCH_RANDOM2 * 100.0F)) / 100.0F;
            }

            random = (Game.oR.nextInt(5000) + Game.oR.nextInt(2750)) % 4;
            switch (random) {
                case 0:
                    advisor.UnitsAttack = (float)((int)(GameValues.advisors.ADVISOR_UNITS_ATTACK_MIN + (GameValues.advisors.ADVISOR_UNITS_ATTACK_RANDOM > 0.0F ? (float)Game.oR.nextInt((int)GameValues.advisors.ADVISOR_UNITS_ATTACK_RANDOM * 100) / 100.0F : 0.0F)));
                    break;
                case 1:
                    advisor.UnitsDefense = (float)((int)(GameValues.advisors.ADVISOR_UNITS_DEFENSE_MIN + (GameValues.advisors.ADVISOR_UNITS_DEFENSE_RANDOM > 0.0F ? (float)Game.oR.nextInt((int)GameValues.advisors.ADVISOR_UNITS_DEFENSE_RANDOM * 100) / 100.0F : 0.0F)));
                    break;
                case 2:
                    advisor.RegimentsLimit = (int)(GameValues.advisors.ADVISOR_REGIMENTS_LIMIT_MIN + (GameValues.advisors.ADVISOR_REGIMENTS_LIMIT_RANDOM > 0.0F ? (float)Game.oR.nextInt((int)GameValues.advisors.ADVISOR_REGIMENTS_LIMIT_RANDOM * 100) / 100.0F : 0.0F));
                    break;
                default:
                    advisor.ImproveRelationsModifier = GameValues.advisors.ADVISOR_IMPROVE_RELATIONS_MIN + (float)Game.oR.nextInt((int)(GameValues.advisors.ADVISOR_IMPROVE_RELATIONS_RANDOM * 100.0F)) / 100.0F;
            }
        } else {
            random = (Game.oR.nextInt(5000) + Game.oR.nextInt(2750)) % 5;
            switch (random) {
                case 0:
                    advisor.GeneralAttack = (float)((int)(GameValues.advisors.ADVISOR_GENERAL_ATTACK_MIN + (GameValues.advisors.ADVISOR_GENERAL_ATTACK_RANDOM > 0.0F ? (float)Game.oR.nextInt((int)GameValues.advisors.ADVISOR_GENERAL_ATTACK_RANDOM * 100) / 100.0F : 0.0F)));
                    break;
                case 1:
                    advisor.GeneralDefense = (float)((int)(GameValues.advisors.ADVISOR_GENERAL_DEFENSE_MIN + (GameValues.advisors.ADVISOR_GENERAL_DEFENSE_RANDOM > 0.0F ? (float)Game.oR.nextInt((int)GameValues.advisors.ADVISOR_GENERAL_DEFENSE_RANDOM * 100) / 100.0F : 0.0F)));
                    break;
                case 2:
                    advisor.RecruitArmyCost = -(GameValues.advisors.ADVISOR_RECRUIT_ARMY_COST_MIN + (float)Game.oR.nextInt((int)(GameValues.advisors.ADVISOR_RECRUIT_ARMY_COST_RANDOM * 100.0F)) / 100.0F);
                    break;
                case 3:
                    advisor.MilitaryBuildingsCost = -(GameValues.advisors.ADVISOR_CONSTRUCTION_GROUP_COST_MIN + (float)Game.oR.nextInt((int)(GameValues.advisors.ADVISOR_CONSTRUCTION_GROUP_COST_RANDOM * 10000.0F)) / 10000.0F);
                    break;
                default:
                    advisor.ArmyMaintenance = -(GameValues.advisors.ADVISOR_ARMY_MAINTENANCE_MIN + (float)Game.oR.nextInt((int)(GameValues.advisors.ADVISOR_ARMY_MAINTENANCE_RANDOM * 100.0F)) / 100.0F);
            }

            random = (Game.oR.nextInt(5000) + Game.oR.nextInt(2750)) % 3;
            switch (random) {
                case 0:
                    advisor.MaxManpower = GameValues.advisors.ADVISOR_MAX_MANPOWER_MIN + (float)Game.oR.nextInt((int)(GameValues.advisors.ADVISOR_MAX_MANPOWER_RANDOM + 1.0F));
                    break;
                case 1:
                    advisor.ArmyMovementSpeed = GameValues.advisors.ADVISOR_ARMY_MOVEMENT_SPEED_MIN + (float)Game.oR.nextInt((int)(GameValues.advisors.ADVISOR_ARMY_MOVEMENT_SPEED_RANDOM * 100.0F)) / 100.0F;
                    break;
                default:
                    advisor.SiegeEffectiveness = GameValues.advisors.ADVISOR_SIEGE_EFFECTIVENESS_MIN + (float)Game.oR.nextInt((int)(GameValues.advisors.ADVISOR_SIEGE_EFFECTIVENESS_RANDOM * 10000.0F)) / 10000.0F;
            }
        }

        return advisor;
    }

    public final void updatePoolOfAdvisors(int iCivID) {
        if (this.lAdvisors.isEmpty()) {
            this.generateAdvisors2(iCivID, null);
            this.generateYear = Game_Calendar.currentYear;
        } else if (this.generateYear + GameValues.advisors.RECRUIT_ADVISOR_REGENERATE_YEARS <= Game_Calendar.currentYear) {
            this.lAdvisors.clear();
            this.generateAdvisors(iCivID, null);
            this.generateYear = Game_Calendar.currentYear;
        } else if (this.lAdvisors.size() < this.getPoolOfAdvisors(iCivID)) {
            this.generateAdvisors(iCivID, null);
        }

    }

    public final boolean recruitAdvisorID(int iCivID, int id) {
        if (id < this.lAdvisors.size()) {
            if (Game.getCiv(iCivID).fGold < (float)AdvisorManager.getRecruitGoldCost(iCivID)) {
                return false;
            } else if (Game.getCiv(iCivID).fLegacy < (float)AdvisorManager.getRecruitCostLegacy(iCivID)) {
                return false;
            } else {
                Civilization var10000 = Game.getCiv(iCivID);
                var10000.fGold -= (float)AdvisorManager.getRecruitGoldCost(iCivID);
                var10000 = Game.getCiv(iCivID);
                var10000.fLegacy -= (float)AdvisorManager.getRecruitCostLegacy(iCivID);
                Game.getCiv(iCivID).eventsData3.addRecruitedAdvisors(1);
                if (iCivID == Game.player.iCivID) {
                    ++Game.stats.civStats.ra;
                }

                switch (this.iAdvisorType) {
                    case 0:
                        if (Game.getCiv(iCivID).advisorAdministration.sName != null) {
                            AdvisorManager var9 = Game.advisorManager;
                            AdvisorManager.updateCivBonuses(Game.getCiv(iCivID).advisorAdministration, iCivID, -1);
                        }

                        Game.getCiv(iCivID).advisorAdministration = this.lAdvisors.get(id);
                        AdvisorManager var10 = Game.advisorManager;
                        AdvisorManager.updateCivBonuses(Game.getCiv(iCivID).advisorAdministration, iCivID, 1);
                        break;
                    case 1:
                        if (Game.getCiv(iCivID).advisorEconomy.sName != null) {
                            AdvisorManager var7 = Game.advisorManager;
                            AdvisorManager.updateCivBonuses(Game.getCiv(iCivID).advisorEconomy, iCivID, -1);
                        }

                        Game.getCiv(iCivID).advisorEconomy = this.lAdvisors.get(id);
                        AdvisorManager var8 = Game.advisorManager;
                        AdvisorManager.updateCivBonuses(Game.getCiv(iCivID).advisorEconomy, iCivID, 1);
                        break;
                    case 2:
                        if (Game.getCiv(iCivID).advisorTechnology.sName != null) {
                            AdvisorManager var5 = Game.advisorManager;
                            AdvisorManager.updateCivBonuses(Game.getCiv(iCivID).advisorTechnology, iCivID, -1);
                        }

                        Game.getCiv(iCivID).advisorTechnology = this.lAdvisors.get(id);
                        AdvisorManager var6 = Game.advisorManager;
                        AdvisorManager.updateCivBonuses(Game.getCiv(iCivID).advisorTechnology, iCivID, 1);
                        break;
                    default:
                        if (Game.getCiv(iCivID).advisorMilitary.sName != null) {
                            AdvisorManager var11 = Game.advisorManager;
                            AdvisorManager.updateCivBonuses(Game.getCiv(iCivID).advisorMilitary, iCivID, -1);
                        }

                        Game.getCiv(iCivID).advisorMilitary = this.lAdvisors.get(id);
                        AdvisorManager var12 = Game.advisorManager;
                        AdvisorManager.updateCivBonuses(Game.getCiv(iCivID).advisorMilitary, iCivID, 1);
                }

                this.lAdvisors.remove(id);
                this.generateAdvisors(iCivID, null);
                return true;
            }
        } else {
            return false;
        }
    }

    public int getPoolOfAdvisors(int iCivID) {
        return GameValues.advisors.RECRUIT_ADVISOR_DEFAULT_POOL + Game.getCiv(iCivID).civBonuses.AdvisorPoolSize;
    }

    public void clearData() {
        this.lAdvisors.clear();
        this.generateYear = -55000;
    }
}
