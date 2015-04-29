package com.micro.rent.common.support;

import org.apache.tools.tar.TarEntry;
import org.apache.tools.tar.TarOutputStream;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPOutputStream;

public class TarUtils {
    private static int BUFFER = 1024 * 4; // 缓冲大小
    private static byte[] B_ARRAY = new byte[BUFFER];

    public static void main(String[] args) {
        String inputFileName1 = "D:\\zh.txt";
        //String inputFileName2 = "D:/1.txt";
        // String inputFileName3 = "D:/2.txt";
        List<String> list = new ArrayList<String>();
        list.add(inputFileName1);
        //list.add(inputFileName2);
        //list.add(inputFileName3);

        String targetFileName = "D:\\zh.tar";
        new TarUtils().execute(list, targetFileName);


    }

    /**
     * 方法功能：打包单个文件或文件夹
     * 参数：inputFileName 要打包的文件夹或文件的路径
     * targetFileName 打包后的文件路径
     */
    public void execute(String inputFileName, String targetFileName) {
        File inputFile = new File(inputFileName);
        String base = inputFileName
                .substring(inputFileName.lastIndexOf("/") + 1);
        TarOutputStream out = getTarOutputStream(targetFileName);
        tarPack(out, inputFile, base);
        try {
            if (null != out) {
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        compress(new File(targetFileName));
    }

    /**
     * 方法功能：打包多个文件或文件夹
     * 参数：inputFileNameList 要打包的文件夹或文件的路径的列表
     * targetFileName 打包后的文件路径
     */
    public void execute(List<String> inputFileNameList, String targetFileName) {
        TarOutputStream out = getTarOutputStream(targetFileName);

        for (String inputFileName : inputFileNameList) {
            File inputFile = new File(inputFileName);
            String base = inputFileName.substring(inputFileName
                    .lastIndexOf("/") + 1);
            tarPack(out, inputFile, base);
        }

        try {
            if (null != out) {
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        compress(new File(targetFileName));
    }

    /**
     * 方法功能：打包成tar文件
     * 参数：out 打包后生成文件的流
     * inputFile 要压缩的文件夹或文件
     * base 打包文件中的路径
     */

    private void tarPack(TarOutputStream out, File inputFile, String base) {
        if (inputFile.isDirectory()) //打包文件夹
        {
            packFolder(out, inputFile, base);
        } else //打包文件
        {
            packFile(out, inputFile, base);
        }
    }

    /**
     * 方法功能：遍历文件夹下的内容，如果有子文件夹，就调用tarPack方法
     * 参数：out 打包后生成文件的流
     * inputFile 要压缩的文件夹或文件
     * base 打包文件中的路径
     */
    private void packFolder(TarOutputStream out, File inputFile, String base) {
        File[] fileList = inputFile.listFiles();
        try {
            //在打包文件中添加路径
            out.putNextEntry(new TarEntry(base + "/"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        base = base.length() == 0 ? "" : base + "/";
        for (File file : fileList) {
            tarPack(out, file, base + file.getName());
        }
    }

    /*
     * 方法功能：打包文件
     * 参数：out 压缩后生成文件的流
     *       inputFile 要压缩的文件夹或文件
     *       base 打包文件中的路径
     */
    private void packFile(TarOutputStream out, File inputFile, String base) {
        TarEntry tarEntry = new TarEntry(base);

        //设置打包文件的大小，如果不设置，打包有内容的文件时，会报错
        tarEntry.setSize(inputFile.length());
        try {
            out.putNextEntry(tarEntry);
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileInputStream in = null;
        try {
            in = new FileInputStream(inputFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int b = 0;

        try {
            while ((b = in.read(B_ARRAY, 0, BUFFER)) != -1) {
                out.write(B_ARRAY, 0, b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.err.println("NullPointerException info ======= [FileInputStream is null]");
        } finally {
            try {
                if (null != in) {
                    in.close();
                }
                if (null != out) {
                    out.closeEntry();
                }
            } catch (IOException e) {

            }
        }
    }

    /*
     * 方法功能：把打包的tar文件压缩成gz格式
     * 参数：srcFile 要压缩的tar文件路径
     */
    private void compress(File srcFile) {
        File target = new File(srcFile.getAbsolutePath() + ".gz");
        FileInputStream in = null;
        GZIPOutputStream out = null;
        try {
            in = new FileInputStream(srcFile);
            out = new GZIPOutputStream(new FileOutputStream(target));
            int number = 0;
            while ((number = in.read(B_ARRAY, 0, BUFFER)) != -1) {
                out.write(B_ARRAY, 0, number);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }

                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*
     * 方法功能：获得打包后文件的流
     * 参数：targetFileName 打包后文件的路径
     */
    private TarOutputStream getTarOutputStream(String targetFileName) {
        //如果打包文件没有.tar后缀名，就自动加上
        targetFileName = targetFileName.endsWith(".tar") ? targetFileName : targetFileName + ".tar";
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(targetFileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
                fileOutputStream);
        TarOutputStream out = new TarOutputStream(bufferedOutputStream);

        //如果不加下面这段，当压缩包中的路径字节数超过100 byte时，就会报错
        out.setLongFileMode(TarOutputStream.LONGFILE_GNU);
        return out;
    }
}
