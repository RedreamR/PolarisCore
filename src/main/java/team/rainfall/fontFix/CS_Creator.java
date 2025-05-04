package team.rainfall.fontFix;

import com.badlogic.gdx.graphics.PixmapIO;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CS_Creator {
    public static void createCompactFile(File outputFile, File inputDirectory) throws IOException {
        List<File> files;
        if(outputFile.exists()){
            outputFile.delete();
        }
        try (Stream<Path> paths = Files.list(inputDirectory.toPath())) {
            files = paths.filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .sorted() // 可选: 按文件名排序
                    .collect(Collectors.toList());
        }

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(outputFile))) {
            // 首先写入单元数量(2字节)
            byte[] unitCountBytes = ByteBuffer.allocate(2)
                    .order(ByteOrder.BIG_ENDIAN)
                    .putShort((short) files.size())
                    .array();
            dos.write(unitCountBytes);

            // 遍历所有文件
            for (File file : files) {
                // 解析文件名作为ID
                int id;
                try {
                    id = Integer.parseInt(file.getName().replaceAll("[^0-9]", ""));
                } catch (NumberFormatException e) {
                    continue;
                }

                if (id < 0 || id > 65535) {
                    throw new IOException("ID必须在0-65535范围内: " + id);
                }

                byte[] fileContent = Files.readAllBytes(file.toPath());

                if (fileContent.length > 65535) {
                    throw new IOException("文件大小超过65535字节限制: " + file.getName());
                }

                // 写入单元ID(2字节)
                byte[] idBytes = ByteBuffer.allocate(2)
                        .order(ByteOrder.BIG_ENDIAN)
                        .putShort((short) id)
                        .array();
                dos.write(idBytes);

                // 写入数据段长度(2字节)
                byte[] segmentLengthBytes = ByteBuffer.allocate(2)
                        .order(ByteOrder.BIG_ENDIAN)
                        .putShort((short) fileContent.length)
                        .array();
                dos.write(segmentLengthBytes);

                // 写入数据段内容
                dos.write(fileContent);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        File inputDirectory = new File("D:\\SteamLibrary\\steamapps\\common\\Age of History 3\\map\\Earth3\\data\\scales\\1");
        File outputFile = new File("D:\\SteamLibrary\\steamapps\\common\\Age of History 3\\map\\Earth3\\data\\scales\\1\\compactScale");
        createCompactFile(outputFile, inputDirectory);
    }
}
