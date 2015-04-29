package com.micro.rent.common.support;

import com.jcraft.jsch.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

public class ExecHelper {
    private static Logger log = LoggerFactory.getLogger("taskLog");

    public static void execShellCommand(String sshHost, String sshUsr, String sshPwd, int sshPort, String command) throws Exception {
        JSch jsch = new JSch();

        Session session = jsch.getSession(sshUsr, sshHost, sshPort);
        session.setPassword(sshPwd);

        // username and password will be given via UserInfo interface.
        UserInfo ui = new localUserInfo();
        session.setUserInfo(ui);
        session.connect();

        Channel channel = session.openChannel("exec");

        ((ChannelExec) channel).setCommand(command);
        channel.setInputStream(null);

        ((ChannelExec) channel).setErrStream(System.err);

        InputStream in = channel.getInputStream();
        channel.connect();

        byte[] tmp = new byte[1024];
        while (true) {
            while (in.available() > 0) {
                int i = in.read(tmp, 0, 1024);
                if (i < 0)
                    break;
                log.info(new String(tmp, 0, i));
            }
            if (channel.isClosed()) {
                if (in.available() > 0)
                    continue;
                log.info("exit-status: " + channel.getExitStatus());
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (Exception ee) {
            }
        }
        channel.disconnect();
        session.disconnect();
    }

    public static void main(String[] arg) throws Exception {

        execShellCommand("115.29.103.144", "db2inst1", "diattt", 22, "sh exportLevel.sh");

    }

    //登入SSH时的控制信息
    //设置不提示输入密码、不显示登入信息等
    public static class localUserInfo implements UserInfo {
        String passwd;

        public String getPassword() {
            return passwd;
        }

        public boolean promptYesNo(String str) {
            return true;
        }

        public String getPassphrase() {
            return null;
        }

        public boolean promptPassphrase(String message) {
            return true;
        }

        public boolean promptPassword(String message) {
            return true;
        }

        public void showMessage(String message) {

        }
    }

}
