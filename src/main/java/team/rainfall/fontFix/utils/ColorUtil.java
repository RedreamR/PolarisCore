package team.rainfall.fontFix.utils;

import com.badlogic.gdx.graphics.Color;

public class ColorUtil {
    public static Color getDisabledColor(Color baseColor) {
        float gray = 0.299f * baseColor.r + 0.587f * baseColor.g + 0.114f * baseColor.b;
        gray = Math.min(gray * 0.7f, 1f);
        return new Color(gray, gray, gray, baseColor.a * 0.5f);
    }

    public static Color getHoveredColor(Color baseColor) {
        float[] hsv = new float[3];
        baseColor.toHsv(hsv);
        hsv[1] = Math.min(hsv[1] * 1.1f, 1f);
        hsv[2] = Math.min(hsv[2] * 1.15f, 1f);
        Color result = new Color();
        result.fromHsv(hsv);
        result.a = baseColor.a;
        return result;
    }
}
