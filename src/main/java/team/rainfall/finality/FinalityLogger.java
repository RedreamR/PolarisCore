//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package team.rainfall.finality;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import aoc.kingdoms.lukasz.jakowski.CFG;
import team.rainfall.finality.loader.util.Localization;

public class FinalityLogger {
    public FinalityLogger() {
    }

    public static void init() {


    }

    public static void important(String message) {
        CFG.LOG("important",message);
    }

    public static void localizeInfo(String message) {
        CFG.LOG("info",message);
    }

    public static void info(String message) {
        CFG.LOG("info",message);
    }

    public static void error(String message) {

    }

    public static void error(String message, Throwable throwable) {

    }

    private static String getStackTraceAsString(Throwable throwable, boolean stacktraceLimit) {
        return "";
    }

    public static void debug(String message) {
        CFG.LOG("debug",message);
    }

    public static void warn(String message) {
        CFG.LOG("warn",message);
    }

    public static void output(String message) {

    }
}
