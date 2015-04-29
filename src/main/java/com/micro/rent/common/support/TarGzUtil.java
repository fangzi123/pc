package com.micro.rent.common.support;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.*;

public class TarGzUtil {
    /**
     * 解压tar.gz文件 tar文件只是把多个文件或文件夹打包合成一个文件，本身并没有进行压缩。gz是进行过压缩的文件。
     *
     * @param dir
     * @param filepath
     * @throws Exception
     */
    public static void deGzipArchive(String dir, String filepath)
            throws Exception {
        final File input = new File(filepath);
        final InputStream is = new FileInputStream(input);
        final CompressorInputStream in = new GzipCompressorInputStream(is, true);
        TarArchiveInputStream tin = new TarArchiveInputStream(in);
        TarArchiveEntry entry = tin.getNextTarEntry();
        while (entry != null) {
            File archiveEntry = new File(dir, entry.getName());
            archiveEntry.getParentFile().mkdirs();
            if (entry.isDirectory()) {
                archiveEntry.mkdir();
                entry = tin.getNextTarEntry();
                continue;
            }
            OutputStream out = new FileOutputStream(archiveEntry);
            IOUtils.copy(tin, out);
            out.close();
            entry = tin.getNextTarEntry();
        }
        in.close();
        tin.close();
    }

    public static void main(String[] args) throws Exception {
        String localpath = "D:\\201406\\";
        File file = new File(localpath);
        File[] files = file.listFiles();
        for (File item : files) {
            if (item.getName().contains("level6_articulo_201406.tar.gz")) {
                String month = item.getName().substring(20, 22);
                System.out.println(month);
            }
            //deGzipArchive(localpath,localpath+ System.getProperty("file.separator")+item.getName());
        }
        String filePath = "D:\\201406\\level6_articulo06.sql";
        String baseName = FilenameUtils.getBaseName(filePath);

        filePath = filePath.substring(0, filePath.length() - 6) + ".sql";

        System.out.println(filePath);
    }
}
