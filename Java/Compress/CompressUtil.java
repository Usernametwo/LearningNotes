package com.bego.compress;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 压缩工具类
 */
public class CompressUtil {

    public static void main(String[] args) throws Exception {
        compress("D:\\Workspace\\Idea", "D:\\Workspace\\a.zip");
    }

    private static final Integer BUFFER_SIZE = 1024;

    /**
     * @param srcPath
     * @param desPath
     * @throws Exception
     */
    public static void compress(String srcPath, String desPath) throws Exception {
        File srcFile = new File(srcPath);
        File desFile = new File(desPath);
        preHandler(srcFile, desFile);
        try (FileOutputStream fos = new FileOutputStream(desFile);) {
            zipCompress(srcFile, fos);
        }
    }

    /**
     * 数据检查
     * @param srcFile
     * @param desFile
     */
    private static void preHandler(File srcFile, File desFile) {
        if (!desFile.isFile()) {
            System.out.println("目标路径必须为文件");
        }
    }

    /**
     * Zip格式压缩
     * @param srcFile
     * @param fos
     * @throws IOException
     */
    private static void zipCompress(File srcFile, FileOutputStream fos) throws IOException {
        try (ZipOutputStream zos = new ZipOutputStream(fos);) {
            zipCompress(srcFile, zos, "");
            zos.flush();
        }

    }

    private static void zipCompress(File srcFile, ZipOutputStream zos, String basePath) throws IOException {
        if (srcFile.isDirectory()) {
            zipCompressDir(srcFile, zos, basePath);
        } else {
            zipCompressFile(srcFile, zos, basePath);
        }
    }

    /**
     * 以Zip格式压缩文件夹
     * @param dir
     * @param zos
     * @param basePath
     * @throws IOException
     */
    private static void zipCompressDir(File dir, ZipOutputStream zos, String basePath) throws IOException {
        File[] files = dir.listFiles();
        if (files.length == 0) {
            ZipEntry entry = new ZipEntry(basePath + dir.getName() + File.separator);
            zos.putNextEntry(entry);
            zos.closeEntry();
        }
        for (File file : files) {
            zipCompress(file, zos, basePath + dir.getName() + File.separator);
        }
    }

    /**
     * Zip格式压缩文件
     * @param file
     * @param zos
     * @param basePath
     * @throws IOException
     */
    private static void zipCompressFile(File file, ZipOutputStream zos, String basePath) throws IOException {
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));) {
            ZipEntry entry = new ZipEntry(basePath + file.getName());
            zos.putNextEntry(entry);
            int count = 0;
            byte[] data = new byte[BUFFER_SIZE];
            while ((count = bis.read(data, 0, BUFFER_SIZE)) != -1) {
                zos.write(data, 0, count);
            }
            zos.closeEntry();
        }
    }
}

