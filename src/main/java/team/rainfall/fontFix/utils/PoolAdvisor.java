package team.rainfall.fontFix.utils;

import aoc.kingdoms.lukasz.map.advisors.Advisor;

public class PoolAdvisor{
    public Advisor advisor;
    public int civID;
    public int type;
    public PoolAdvisor(Advisor advisor,int civID,int type){
        this.advisor = advisor;
        this.civID = civID;
        this.type = type;
    }
}
