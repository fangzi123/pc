package com.micro.rent.common.support.task.context;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.tools.ant.taskdefs.MatchingTask;

/**
 * @author
 * @version 1.0
 * @Description 消息资源任务上下文
 * @date 2012-11-26
 */
public class MessageTaskContext {
    //生成消息类型:errors/dicts/....
    private String messageType;
    //properties文件路径
    private String filePath;
    //目标java文件名称
    private String targetFileName;
    //目标生成包
    private String targetPackage;
    //目标项目, e.g.:core-psfp/src/main/java
    private String targetProject;
    //ant任务对象
    private MatchingTask antTask;

    /**
     * @return the messageType
     */
    public String getMessageType() {
        return messageType;
    }

    /**
     * @param messageType the messageType to set
     */
    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    /**
     * @return the filePath
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * @param filePath the filePath to set
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * @return the targetFileName
     */
    public String getTargetFileName() {
        return targetFileName;
    }

    /**
     * @param targetFileName the targetFileName to set
     */
    public void setTargetFileName(String targetFileName) {
        this.targetFileName = targetFileName;
    }

    /**
     * @return the targetPackage
     */
    public String getTargetPackage() {
        return targetPackage;
    }

    /**
     * @param targetPackage the targetPackage to set
     */
    public void setTargetPackage(String targetPackage) {
        this.targetPackage = targetPackage;
    }

    /**
     * @return the targetProject
     */
    public String getTargetProject() {
        return targetProject;
    }

    /**
     * @param targetProject the targetProject to set
     */
    public void setTargetProject(String targetProject) {
        this.targetProject = targetProject;
    }

    /**
     * @return the antTask
     */
    public MatchingTask getAntTask() {
        return antTask;
    }

    /**
     * @param antTask the antTask to set
     */
    public void setAntTask(MatchingTask antTask) {
        this.antTask = antTask;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }


}
