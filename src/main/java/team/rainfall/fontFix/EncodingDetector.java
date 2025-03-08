package team.rainfall.fontFix;

import com.badlogic.gdx.files.FileHandle;
import org.mozilla.universalchardet.UniversalDetector;
import team.rainfall.finality.FinalityLogger;

import java.io.*;

public class EncodingDetector {
    UniversalDetector detector = new UniversalDetector(null);
    public final static EncodingDetector INSTANCE = new EncodingDetector();

    public String detectStringCharset(FileHandle fileHandle) {
        if(!fileHandle.exists()){
            return "NONE";
        }
        try {
            File file = fileHandle.file();
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedInputStream reader = new BufferedInputStream(fileInputStream);
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
            FinalityLogger.error("Error while detecting charset: " + e.getMessage(), e);
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
            FinalityLogger.error("Error while detecting charset: " + e.getMessage(), e);
        }
        return "NONE";
    }
}
