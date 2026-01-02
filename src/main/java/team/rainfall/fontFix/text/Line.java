package team.rainfall.fontFix.text;

import java.util.ArrayList;

public class Line {
    public float lineHeight = 0;
    public float lineWidth = 0;
    public ArrayList<Word> words;
    public boolean isImageInText(){
        return words.size() == 1 && words.get(0).string.startsWith("§") && words.get(0).string.length() == 3;
    }
}
