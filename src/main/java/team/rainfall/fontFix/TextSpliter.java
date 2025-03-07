package team.rainfall.fontFix;
import java.util.List;
import java.util.ArrayList;


public class TextSpliter {
    public static String[] splitText(String input) {
        List<String> result = new ArrayList<>();
        StringBuilder currentWord = new StringBuilder();
        boolean isPreviousCJK = false;

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            // 判断字符是否为中文或日文
            if (isCJKCharacter(ch)) {
                if (!isPreviousCJK && currentWord.length() > 0) {
                    result.add(currentWord.toString());
                    currentWord.setLength(0);
                }
                result.add(String.valueOf(ch));
                isPreviousCJK = true;
            } else if (Character.isWhitespace(ch)) {
                if (currentWord.length() > 0) {
                    result.add(currentWord.toString() + " ");
                    currentWord.setLength(0);
                }
                isPreviousCJK = false;
            } else {
                if (isPreviousCJK) {
                    result.add(currentWord.toString());
                    currentWord.setLength(0);
                }
                currentWord.append(ch);
                isPreviousCJK = false;
            }
        }

        // 添加最后一个词
        if (currentWord.length() > 0) {
            result.add(currentWord.toString());
        }

        return result.toArray(new String[0]);
    }

    // 判断字符是否为中文或日文
    public static boolean isCJKCharacter(char ch) {
        Character.UnicodeBlock block = Character.UnicodeBlock.of(ch);
        return block == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || block == Character.UnicodeBlock.HIRAGANA
                || block == Character.UnicodeBlock.KATAKANA
                || block == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || block == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION;
    }
}
