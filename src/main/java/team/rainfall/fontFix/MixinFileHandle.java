package team.rainfall.fontFix;

import com.badlogic.gdx.utils.Null;
import team.rainfall.finality.FinalityLogger;
import team.rainfall.finality.luminosity2.annotations.Mixin;
import com.badlogic.gdx.files.FileHandle;
import team.rainfall.finality.luminosity2.annotations.Shadow;

import java.nio.charset.Charset;

@Mixin(mixinClass = "com.badlogic.gdx.files.FileHandle")
public abstract class MixinFileHandle {

    public String readString() {
        String charset = null;
        if (EncodeChecker.shouldBeCheck((FileHandle) (Object) this)){
        // || this.path().matches("map/.*/scenarios/.*/descriptions.*") || this.path().matches("map/.*/scenarios/.*/events.*",|| this.path().contains("mods")
            try {
                charset = EncodingDetector.INSTANCE.detectStringCharset((FileHandle) (Object) this);
                switch (charset) {
                    case "UTF-8":
                        charset = "UTF8";
                        break;
                    case "BIG5":
                        charset = "Big5";
                        break;
                    case "Shift_JIS":
                        charset = "Shift_JIS";
                        break;
                    case "GB18030":
                        charset = "GB18030";
                    default:
                        charset = Charset.defaultCharset().name();
                        break;
                }
                FinalityLogger.debug("PC.charset "+this.path()+";"+charset);
            }catch (NullPointerException ignored){
            } catch (Throwable throwable) {
                FinalityLogger.error("Error while detecting charset", throwable);
            }
        }
        return this.readString(charset);
    }

    @Shadow
    public abstract String readString(String charset);

    @Shadow
    public abstract String path();
}
