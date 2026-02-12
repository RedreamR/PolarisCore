package team.rainfall.fontFix;

 
import team.rainfall.finality.luminosity2.annotations.Mixin;
import com.badlogic.gdx.files.FileHandle;
import team.rainfall.finality.luminosity2.annotations.Shadow;

import java.nio.charset.Charset;

@Mixin(mixinClass = "com.badlogic.gdx.files.FileHandle")
public abstract class MixinFileHandle {
    public static int readCount = 0;
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
                        if(Config.isConfigLoaded()){
                            if(!Config.getConfig().defaultCharset.equals("NONE")) {
                                charset = Config.getConfig().defaultCharset;
                            }else {
                                charset = Charset.defaultCharset().name();
                            }
                        }else {
                            charset = Charset.defaultCharset().name();
                        }
                        break;
                }
                readCount++;
                //FontFix.LOGGER.debug("PC.charset "+this.path()+";"+charset+";"+readCount);

            }catch (NullPointerException ignored){
            } catch (Throwable throwable) {
                FontFix.LOGGER.error("Error while detecting charset", throwable);
            }
        }
        return this.readString(charset);
    }

    @Shadow
    public abstract String readString(String charset);

    @Shadow
    public abstract String path();
}
