package team.rainfall.fontFix.utils;

import team.rainfall.finality.FinalityLogger;

public class Timer {
    private static long timer = 0;
    private static boolean isNanotime;
    private static String tag;
    public static void start(boolean isNanotime,String tag){
        if(isNanotime){
            timer = System.nanoTime();
        }else {
            timer = System.currentTimeMillis();
        }
        Timer.isNanotime = isNanotime;
        Timer.tag = tag;
    }
    public static long end(){
        if(isNanotime){
            FinalityLogger.debug("FontFix."+tag+" spending "+(System.nanoTime() - timer)+"ns");
            return System.nanoTime() - timer;
        }else {
            FinalityLogger.debug("FontFix."+tag+" spending "+(System.currentTimeMillis() - timer)+"ms");
            return System.currentTimeMillis() - timer;
        }
    }
}
