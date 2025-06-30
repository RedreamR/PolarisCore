package team.rainfall.fontFix.text;

import aoc.kingdoms.lukasz.jakowski.GlyphLayout_Game;
import aoc.kingdoms.lukasz.jakowski.Renderer.Renderer;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import team.rainfall.finality.FinalityLogger;

import javax.swing.border.LineBorder;
import java.util.ArrayList;

public class TextProcessor {
    public static ArrayList<String> tokenize(String string){
        ArrayList<String> tokens = new ArrayList<>();
        StringBuilder currentToken = new StringBuilder();
        for(int i = 0;i < string.length();i++){
            char c = string.charAt(i);
            //§ symbol cannot be displayed in game,so we choose it.
            if(c == '§' && i + 1 < string.length()){
                tokens.add(currentToken.toString());
                currentToken.setLength(0);
                tokens.add("§"+string.charAt(i+1));
                i = i + 1;
                continue;
            }

            if(c == '\\' && i + 1 < string.length() && string.charAt(i+1) == 'n'){
                tokens.add(currentToken.toString());
                currentToken.setLength(0);
                tokens.add("\\n");
                i = i + 1;
                continue;
            }
            if(isCJCharacter(c)){
                tokens.add(currentToken.toString());
                currentToken.setLength(0);
                tokens.add(String.valueOf(c));
                continue;
            }
            //Space split
            if(c == ' '){
                tokens.add(currentToken + " ");
                currentToken.setLength(0);
                continue;
            }
            currentToken.append(c);
        }
        tokens.add(currentToken.toString());
        return tokens;
    }
    public static ArrayList<Line> warp(ArrayList<String> tokens,int maxWidth,int fontID){
        ArrayList<Line> lines = new ArrayList<>();
        Line lineObject = new Line();
        StringBuilder currentLine = new StringBuilder();
        ArrayList<Word> words = new ArrayList<>();
        BitmapFont bitmapFont = Renderer.fontMain.get(fontID);
        Renderer.glyphLayout.setText(bitmapFont,"");
        char currentColorSign = '0';
        for (String token : tokens) {
            if (token.startsWith("§") && token.length() == 2) {
                currentColorSign = token.charAt(1);
                continue;
            }
            if (token.equals("\\n")) {
                currentLine.setLength(0);
                lineObject.words = words;
                words = new ArrayList<>();
                lines.add(lineObject);
                lineObject = new Line();
                continue;
            }
            Renderer.glyphLayout.setText(bitmapFont, currentLine + token);
            Word word = new Word(token, currentColorSign);
            if (Renderer.glyphLayout.width < maxWidth) {
                FinalityLogger.debug("ADD TOKEN "+token+" width "+Renderer.glyphLayout.width+","+maxWidth);
                currentLine.append(token);
                words.add(word);
            } else {
                currentLine.setLength(0);
                lineObject.lineHeight = Renderer.glyphLayout.height;
                lineObject.lineWidth = Renderer.glyphLayout.width;
                lineObject.words = words;
                words = new ArrayList<>();
                lines.add(lineObject);
                lineObject = new Line();
                words.add(word);
                currentLine.append(token);
            }
        }
        if(currentLine.length() > 0){
            Renderer.glyphLayout.setText(bitmapFont, currentLine);
            lineObject.lineHeight = Renderer.glyphLayout.height;
            lineObject.lineWidth = Renderer.glyphLayout.width;
            lineObject.words = words;
            lines.add(lineObject);
        }
        return lines;
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
