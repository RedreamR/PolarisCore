package team.rainfall.fontFix;

import java.io.*;
import java.nio.file.*;

public class NativeLibraryLoader {

    /**
     * 加载原生库，首先将其从JAR解压到临时目录
     * @param libraryName 原生库名称（不带平台特定的前缀或后缀）
     * @throws IOException 如果解压或加载过程中出错
     * @throws UnsatisfiedLinkError 如果库加载失败
     */
    public static void loadLibrary(String libraryName) throws IOException {
        // 获取系统特定的库文件名
        String systemLibraryName = System.mapLibraryName(libraryName);

        // 获取临时目录路径
        String tempDir = System.getProperty("java.io.tmpdir");
        Path tempPath = Paths.get(tempDir, systemLibraryName);

        // 从JAR中提取库文件到临时目录
        extractLibraryFromJar("/" + systemLibraryName, tempPath);

        // 加载库
        System.load(tempPath.toString());
    }

    /**
     * 从JAR中提取文件到指定路径
     * @param resourcePath JAR中的资源路径
     * @param destination 目标路径
     * @throws IOException 如果提取过程中出错
     */
    private static void extractLibraryFromJar(String resourcePath, Path destination) throws IOException {
        // 如果目标文件已存在且不是新提取的，则先删除
        Files.deleteIfExists(destination);
        // 从JAR中读取资源
        try (InputStream is = NativeLibraryLoader.class.getResourceAsStream(resourcePath)) {
            if (is == null) {
                throw new FileNotFoundException("Resource not found: " + resourcePath);
            }

            // 创建所有必要的父目录
            Files.createDirectories(destination.getParent());

            // 将资源写入临时文件
            Files.copy(is, destination, StandardCopyOption.REPLACE_EXISTING);

            // 在Unix-like系统上设置可执行权限
            if (!destination.toFile().setExecutable(true)) {
                System.err.println("Warning: Could not set executable permission for " + destination);
            }
        }
    }
}