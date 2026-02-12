package team.rainfall.fontFix;

import com.badlogic.gdx.files.FileHandle;
import org.mozilla.universalchardet.UniversalDetector;
 

import java.io.*;

public class EncodingDetector {
    private static String bypassEncoding = null;
    UniversalDetector detector = new UniversalDetector(null);
    public final static EncodingDetector INSTANCE = new EncodingDetector();

    public String detectStringCharset(FileHandle fileHandle) {
        if(!fileHandle.exists()){
            return "NONE";
        }
        if(bypassEncoding != null){
            return bypassEncoding;
        }
        try {
            File file = fileHandle.file();
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedInputStream reader = new BufferedInputStream(fileInputStream);
            byte[] buff = new byte[1024];
            int len;
            while ((len = reader.read(buff)) != -1 && !detector.isDone()) {
                detector.handleData(buff, 0, len);
            }
            detector.dataEnd();
            String encoding = detector.getDetectedCharset();
            detector.reset();
            reader.close();
            return encoding == null ? "NONE" : encoding;
        } catch (Exception e) {
            FontFix.LOGGER.error("Error while detecting charset: " + e.getMessage(), e);
        }
        return "NONE";
    }
    public String detectInputStreamCharset(InputStream inputStream){
        try {
            BufferedInputStream reader = new BufferedInputStream(inputStream);
            byte[] buff = new byte[1024];
            int len = 0;
            while ((len = reader.read(buff)) != -1 && !detector.isDone()) {
                detector.handleData(buff, 0, len);
            }
            detector.dataEnd();
            String encoding = detector.getDetectedCharset();
            detector.reset();
            reader.close();
            return encoding == null ? "NONE" : encoding;
        } catch (Exception e) {
            FontFix.LOGGER.error("Error while detecting charset: " + e.getMessage(), e);
        }
        return "NONE";
    }
    public static void setBypassEncoding(String s){
        bypassEncoding = s;
    }
    public static void resetBypassEncoding(){
        bypassEncoding = null;
    }
}
