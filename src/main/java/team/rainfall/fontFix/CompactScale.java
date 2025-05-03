package team.rainfall.fontFix;

import com.badlogic.gdx.files.FileHandle;
import team.rainfall.finality.FinalityLogger;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompactScale {
    public ArrayList<Scale> scales = new ArrayList<>();
    public static CompactScale readCompactFile(FileHandle fileHandle) throws IOException {
        FinalityLogger.debug("READ FROM COMPACT FILE");
        ArrayList<Scale> scales = new ArrayList<>();
        try (DataInputStream dis = new DataInputStream(Files.newInputStream(fileHandle.file().toPath()))) {
            // 读取单元数量 (2字节)
            byte[] unitCountBytes = new byte[2];
            dis.readFully(unitCountBytes);
            int unitCount = ByteBuffer.wrap(unitCountBytes)
                    .order(ByteOrder.BIG_ENDIAN)
                    .getShort() & 0xFFFF;

            // 读取所有单元
            for (int i = 0; i < unitCount; i++) {
                //FinalityLogger.debug("LOAD ID "+i+",ADDR "+dis.available());
                // 读取单元ID (2字节)
                byte[] idBytes = new byte[2];
                dis.readFully(idBytes);
                int id = ByteBuffer.wrap(idBytes)
                        .order(ByteOrder.BIG_ENDIAN)
                        .getShort() & 0xFFFF;

                // 读取数据段长度 (2字节)
                byte[] lengthBytes = new byte[2];
                dis.readFully(lengthBytes);
                int length = ByteBuffer.wrap(lengthBytes)
                        .order(ByteOrder.BIG_ENDIAN)
                        .getShort() & 0xFFFF;

                // 读取数据段
                byte[] data = new byte[length];
                //FinalityLogger.debug("ADDR2 "+dis.available()+" length"+length);
                dis.readFully(data);

                // 创建Scale对象并添加到列表
                scales.add(new Scale(id, data));
            }
        }
        CompactScale compactScale = new CompactScale();
        compactScale.scales = scales;
        return compactScale;
    }

    public static class Scale {
        private final int id;
        private final byte[] data;

        public Scale(int id, byte[] data) {
            this.id = id;
            this.data = data;
        }

        public int getId() {
            return id;
        }

        public byte[] getData() {
            return data;
        }

        @Override
        public String toString() {
            return "Scale{id=" + id + ", data.length=" + data.length + "}";
        }
    }
}
