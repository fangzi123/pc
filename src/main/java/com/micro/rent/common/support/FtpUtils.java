package com.micro.rent.common.support;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class FtpUtils {

    private static Logger log = LoggerFactory.getLogger(FtpUtils.class);

    /**
     * Description: 向FTP服务器上传文件
     *
     * @param url      FTP服务器hostname
     * @param port     FTP服务器端口
     * @param username FTP登录账号
     * @param password FTP登录密码
     * @param path     FTP服务器保存目录
     * @param filename 上传到FTP服务器上的文件名
     * @param input    输入流
     * @return 成功返回true，否则返回false
     * @Version1.0
     */
    public static boolean uploadFile(String url, int port, String username, String password,
                                     String path, String filename, InputStream input) {
        boolean success = false;
        FTPClient ftp = new FTPClient();
        try {
            int reply;
            ftp.connect(url, port);//连接FTP服务器
            //如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
            ftp.login(username, password);//登录
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return success;
            }

            //ftp.setControlEncoding("utf-8");
            ftp.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
            //二进制传输，  文件方式 unxi格式
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            //ftp.changeWorkingDirectory(path);
            ftp.storeFile(filename, input);

            input.close();
            ftp.logout();
            success = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return success;
    }


    public static void testUpLoadFromDisk() {
        try {
            FileInputStream in = new FileInputStream(new File("D:\\project-work\\datashiring\\supermarket\\doc\\接口\\exportLevel6.sh"));


            boolean flag = uploadFile("115.29.103.144", 21, "db2inst1", "diattt",
                    "/home/db2inst1/", "exportLevel6.sh", in);
            System.out.println(flag);
        } catch (FileNotFoundException e) {
            log.error(e.getMessage());
        }
    }

    public static void testDownloadFromDisk() {
        File file = new File("d:\\" + DateUtil.getCurrentDate("yyyyMM"));
        if (!file.exists()) {
            file.mkdirs();
        }
        boolean flag = downFiles("115.29.103.144", 21, "db2inst1", "diattt",
                "/wechatap/FIDELCN/", "tar.gz", "d:\\" + DateUtil.getCurrentDate("yyyyMM"));
    }

    public static void main(String[] args) {
        //testUpLoadFromDisk();
        testDownloadFromDisk();
    }


    /**
     * Description: 从FTP服务器下载文件
     *
     * @param url        FTP服务器hostname
     * @param port       FTP服务器端口
     * @param username   FTP登录账号
     * @param password   FTP登录密码
     * @param remotePath FTP服务器上的相对路径
     * @param fileName   要下载的文件名
     * @param localPath  下载后保存到本地的路径
     * @return
     * @Version1.0
     */
    public static boolean downFile(String url, int port, String username, String password,
                                   String remotePath, String fileName, String localPath) {
        boolean success = false;
        FTPClient ftp = new FTPClient();
        try {
            int reply;
            ftp.connect(url, port);
            //如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
            ftp.login(username, password);//登录
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return success;
            }
            ftp.changeWorkingDirectory(remotePath);//转移到FTP服务器目录
            //设置文件类型（二进制）
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
            FTPFile[] fs = ftp.listFiles();
            for (FTPFile ff : fs) {
                if (ff.getName().equals(fileName)) {
                    File localFile = new File(localPath + "/" + ff.getName());

                    OutputStream is = new FileOutputStream(localFile);
                    ftp.retrieveFile(ff.getName(), is);
                    is.close();
                }
            }

            ftp.logout();
            success = true;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return success;
    }


    /**
     * Description: 从FTP服务器下载文件
     *
     * @param url        FTP服务器hostname
     * @param port       FTP服务器端口
     * @param username   FTP登录账号
     * @param password   FTP登录密码
     * @param remotePath FTP服务器上的相对路径
     * @param fileName   要下载的文件名
     * @param localPath  下载后保存到本地的路径
     * @return
     * @Version1.0
     */
    public static boolean downFiles(String url, int port, String username, String password,
                                    String remotePath, String fileName, String localPath) {
        boolean success = false;
        FTPClient ftp = new FTPClient();
        try {
            int reply;
            ftp.connect(url, port);
            //如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
            ftp.login(username, password);//登录
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return success;
            }
            ftp.changeWorkingDirectory(remotePath);//转移到FTP服务器目录
            //设置文件类型（二进制）
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
            FTPFile[] fs = ftp.listFiles();
            for (FTPFile ff : fs) {
                if (ff.getName().contains("tar.gz")) {
                    File localFile = new File(localPath + "/" + ff.getName());

                    OutputStream is = new FileOutputStream(localFile);
                    ftp.retrieveFile(ff.getName(), is);
                    is.close();
                }
            }

            ftp.logout();
            success = true;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return success;
    }
}
