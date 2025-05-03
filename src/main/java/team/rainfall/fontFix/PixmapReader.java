package team.rainfall.fontFix;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.StreamUtils;

import java.io.*;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.zip.InflaterInputStream;

public class PixmapReader {

    private static final byte[] readBuffer = new byte[4096];

    public static Pixmap read(byte[] compressedData) {
        DataInputStream in = null;
        Pixmap pixmap = null;

        try {
            // 创建输入流：字节数组 → 缓冲流 → 解压流 → 数据流
            InputStream byteStream = new ByteArrayInputStream(compressedData);
            InputStream bufferedStream = new BufferedInputStream(byteStream);
            InputStream inflaterStream = new InflaterInputStream(bufferedStream);
            in = new DataInputStream(inflaterStream);

            // 读取基本参数
            int width = in.readInt();
            int height = in.readInt();
            Format format = Format.fromGdx2DPixmapFormat(in.readInt());

            // 创建Pixmap
            pixmap = new Pixmap(width, height, format);
            ByteBuffer pixelBuf = pixmap.getPixels();

            // 准备ByteBuffer
            ((Buffer)pixelBuf).position(0);
            ((Buffer)pixelBuf).limit(pixelBuf.capacity());

            // 读取像素数据
            synchronized(readBuffer) {
                int readBytes;
                while ((readBytes = in.read(readBuffer)) > 0) {
                    pixelBuf.put(readBuffer, 0, readBytes);
                }
            }

            // 重置ByteBuffer
            ((Buffer)pixelBuf).position(0);
            ((Buffer)pixelBuf).limit(pixelBuf.capacity());

        } catch (Exception e) {
            throw new GdxRuntimeException("Couldn't read Pixmap from byte array", e);
        } finally {
            StreamUtils.closeQuietly(in);
            // 如果发生错误且已创建Pixmap，确保释放资源
            if (pixmap != null && Thread.currentThread().isInterrupted()) {
                pixmap.dispose();
            }
        }

        return pixmap;
    }
}