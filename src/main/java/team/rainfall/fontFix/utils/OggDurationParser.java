package team.rainfall.fontFix.utils;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class OggDurationParser {

    public static float getOggDuration(FileHandle file) {
        try {
            int sampleRate = parseSampleRate(file);
            long lastPageGranule = findLastPageGranule(file);
            return (float) lastPageGranule / sampleRate;
        } catch (IOException e) {
            throw new GdxRuntimeException("Error reading OGG file: " + file.path(), e);
        }
    }

    private static int parseSampleRate(FileHandle file) throws IOException {
        byte[] header = new byte[64];
        int read = file.readBytes(header, 0, header.length);
        if (read < 64) {
            throw new GdxRuntimeException("OGG file too small");
        }

        if (!"OggS".equals(new String(header, 0, 4))) {
            throw new GdxRuntimeException("Invalid OGG file header");
        }

        int segmentCount = header[26] & 0xFF;
        int headerSize = 27 + segmentCount;

        // 检查是否有足够的长度来读取vorbis标识
        if (headerSize + 18 >= header.length) {
            throw new GdxRuntimeException("Invalid OGG header structure");
        }

        // 验证vorbis标识头
        if (!"vorbis".equals(new String(header, headerSize + 1, 6))) {
            throw new GdxRuntimeException("Not a Vorbis OGG file");
        }

        ByteBuffer buffer = ByteBuffer.wrap(header).order(ByteOrder.LITTLE_ENDIAN);
        return buffer.getInt(headerSize + 12);
    }

    private static long findLastPageGranule(FileHandle file) throws IOException {
        final int MAX_SEARCH = 65536;
        int fileSize = (int) file.length();

        if (fileSize < 28) {
            throw new GdxRuntimeException("File too small to be an OGG file");
        }

        int searchSize = Math.min(fileSize, MAX_SEARCH);
        long startOffset = Math.max(0, fileSize - searchSize);

        InputStream in = file.read();
        long skipped = 0;
        while (skipped < startOffset) {
            long actualSkipped = in.skip(startOffset - skipped);
            if (actualSkipped <= 0) break;
            skipped += actualSkipped;
        }

        byte[] tail = new byte[searchSize];
        int totalRead = 0;
        while (totalRead < searchSize) {
            int read = in.read(tail, totalRead, searchSize - totalRead);
            if (read <= 0) break;
            totalRead += read;
        }
        in.close();

        ByteBuffer buffer = ByteBuffer.wrap(tail).order(ByteOrder.LITTLE_ENDIAN);

        // 从后向前搜索'OggS'魔数
        for (int i = totalRead - 4; i >= 0; i--) {
            if (tail[i] == 'O' && tail[i+1] == 'g' && tail[i+2] == 'g' && tail[i+3] == 'S') {
                // 确保有足够字节读取granule position
                if (i + 14 <= totalRead) {
                    return buffer.getLong(i + 6);
                }
            }
        }

        throw new GdxRuntimeException("No valid OGG page found at end of file");
    }
}