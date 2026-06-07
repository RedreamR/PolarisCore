package team.rainfall.fontFix.text;

import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import aoc.kingdoms.lukasz.textures.Image;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import team.rainfall.fontFix.FontFix;


import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextProcessor {
    public static ArrayList<String> tokenize(String string) {
        ArrayList<String> tokens = new ArrayList<>();
        StringBuilder currentToken = new StringBuilder();
        string = processPlaceholders(string);
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (c == '§' && i + 2 < string.length() && string.charAt(i + 1) == '[') {
                tokens.add(currentToken.toString());
                currentToken.setLength(0);
                tokens.add("§" + string.charAt(i + 1) + string.charAt(i + 2) );
                i = i + 2;
                continue;
            }
            //§ symbol cannot be displayed in game,so we choose it.
            if (c == '§' && i + 1 < string.length()) {
                tokens.add(currentToken.toString());
                currentToken.setLength(0);
                tokens.add("§" + string.charAt(i + 1));
                i = i + 1;
                continue;
            }
            if (c == '\\' && i + 1 < string.length() && string.charAt(i + 1) == 'n') {
                tokens.add(currentToken.toString());
                currentToken.setLength(0);
                tokens.add("\\n");
                i = i + 1;
                continue;
            }
            if (isCJCharacter(c)) {
                tokens.add(currentToken.toString());
                currentToken.setLength(0);
                tokens.add(String.valueOf(c));
                continue;
            }
            if (c == '\n') {
                tokens.add(currentToken.toString());
                currentToken.setLength(0);
                tokens.add("\\n");
                continue;
            }
            //Space split
            if (c == ' ') {
                tokens.add(currentToken + " ");
                currentToken.setLength(0);
                continue;
            }
            currentToken.append(c);
        }
        tokens.add(currentToken.toString());
        return tokens;
    }

    // 保持原有公开方法签名，委托给内部方法
    public static ArrayList<Line> warp(ArrayList<String> tokens, int maxWidth, int fontID) {
        return warpInternal(tokens, maxWidth, fontID);
    }

    public static ArrayList<Line> warp2(ArrayList<String> tokens, int maxWidth, int fontID) {
        return warpInternal(tokens, maxWidth, fontID);
    }

    /**
     * 核心换行方法
     *
     * @param tokens   词元列表（由 tokenize 生成）
     * @param maxWidth 最大行宽（像素）
     * @param fontID   字体ID
     * @return 行对象列表
     */
    private static ArrayList<Line> warpInternal(ArrayList<String> tokens, int maxWidth, int fontID) {
        ArrayList<Line> lines = new ArrayList<>();
        Line lineObject = new Line();
        StringBuilder currentLine = new StringBuilder();
        ArrayList<Word> words = new ArrayList<>();
        BitmapFont bitmapFont = Renderer.fontMain.get(fontID);
        Renderer.glyphLayout.setText(bitmapFont, "");
        char currentColorSign = '0';

        for (String token : tokens) {
            // 1. 图片处理（仅在 enableImage 为 true 时）
            if (token.startsWith("§") && token.length() == 3) {
                // 结束当前行（如果有内容）
                if (currentLine.length() > 0) {
                    finalizeLine(lineObject, words, currentLine, bitmapFont, lines);
                    currentLine.setLength(0);
                    words = new ArrayList<>();
                    lineObject = new Line();
                }
                // 创建一个仅包含图片 word 的行（用于占位）
                Line imageLine = new Line();
                ArrayList<Word> imageWords = new ArrayList<>();
                Word imageWord = new Word(token, currentColorSign);
                imageWords.add(imageWord);
                imageLine.words = imageWords;

                // 计算图片的宽高（缩放以适配 maxWidth）
                Image image = PicInText_Pool.loadImage(token.charAt(2));
                if (image.getWidth() > maxWidth) {
                    imageLine.lineWidth = maxWidth;
                    imageLine.lineHeight = (int) (image.getHeight() * ((float) maxWidth / image.getWidth()));
                } else {
                    imageLine.lineWidth = image.getWidth();
                    imageLine.lineHeight = image.getHeight();
                }
                lines.add(imageLine);

                // 重置 lineObject 为新的空行
                lineObject = new Line();
                continue;
            }

            // 2. 颜色标记（长度为2的 §X）
            if (token.startsWith("§") && token.length() == 2) {
                currentColorSign = token.charAt(1);
                continue;
            }

            // 3. 显式换行符
            if (token.equals("\\n")) {
                if (currentLine.length() > 0 || !words.isEmpty()) {
                    finalizeLine(lineObject, words, currentLine, bitmapFont, lines);
                }
                currentLine.setLength(0);
                words = new ArrayList<>();
                lineObject = new Line();
                continue;
            }

            // 4. 普通文本 token：尝试加入当前行
            Renderer.glyphLayout.setText(bitmapFont, currentLine + token);
            Word word = new Word(token, currentColorSign);
            if (Renderer.glyphLayout.width < maxWidth) {
                currentLine.append(token);
                words.add(word);
            } else {
                // 当前行已满，结束当前行并开始新行
                finalizeLine(lineObject, words, currentLine, bitmapFont, lines);
                // 重置为新行，并将当前 token 作为新行的第一个词
                currentLine.setLength(0);
                lineObject = new Line();
                words = new ArrayList<>();
                words.add(word);
                currentLine.append(token);
            }
        }

        if (currentLine.length() > 0 || !words.isEmpty()) {
            finalizeLine(lineObject, words, currentLine, bitmapFont, lines);
        }

        return lines;
    }

    private static void finalizeLine(Line lineObject, ArrayList<Word> words, StringBuilder currentLine,
                                     BitmapFont bitmapFont, ArrayList<Line> lines) {
        Renderer.glyphLayout.setText(bitmapFont, currentLine.toString());
        lineObject.lineHeight = Renderer.glyphLayout.height;
        lineObject.lineWidth = Renderer.glyphLayout.width;
        lineObject.words = words;
        lines.add(lineObject);
    }

    public static String format(String s){
        if(s == null || !s.contains(".")){
            return "[Invalid String]";
        }
        try {
            switch (s.split("\\.")[0]) {
                case "civ":
                    return TextFuncService.formatCiv(s);
                case "player":
                    return TextFuncService.formatPlayer(s);
                case "counter":
                    return TextFuncService.formatCounter(s);
            }
        }catch (Throwable e){
            FontFix.LOGGER.error("Failed to format text: " + s, e);
        }
        return s;
    }

    public static String processPlaceholders(String input) {
        //Fuck Android and ICU4J
        Pattern pattern = Pattern.compile("§\\{([^}]*)\\}");
        Matcher matcher = pattern.matcher(input);
        StringBuffer result = new StringBuffer();
        while (matcher.find()) {
            String placeholderContent = matcher.group(1);
            String replacement = format(placeholderContent.trim());
            matcher.appendReplacement(result, Matcher.quoteReplacement(replacement));
        }
        matcher.appendTail(result);
        return result.toString();
    }

    /**
     * Determine if ch is a CJ(Chinese and Japanese) Character.
     * Ignore Korean because it uses the same split logic as Indo-European language.
     * @param ch the character to determine
     * @return true if ch is a CJ Character, false otherwise
     * @author RedreamR
     */
    static boolean isCJCharacter(char ch) {
        Character.UnicodeBlock block = Character.UnicodeBlock.of(ch);
        return block == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || block == Character.UnicodeBlock.HIRAGANA
                || block == Character.UnicodeBlock.KATAKANA
                || block == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || block == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION;
    }
}
