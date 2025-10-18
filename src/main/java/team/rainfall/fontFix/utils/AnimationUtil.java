package team.rainfall.fontFix.utils;

public class AnimationUtil {
    public static double easeOut(long currentTime, long targetTime,long startTime) {
        if (currentTime <= 0) {
            return 0.0;
        }
        if (currentTime >= targetTime) {
            return 1.0;
        }
        currentTime = currentTime - startTime;
        targetTime = targetTime - startTime;
        double progress = (double) currentTime / targetTime;
        return progress * (2 - progress);
    }
    public static double easeOut(double f){
        return  1.0f - (1.0f - f) * (1.0f - f);
    }
    public static double easeOut(float f){
        return  1.0f - (1.0f - f) * (1.0f - f);
    }
}
