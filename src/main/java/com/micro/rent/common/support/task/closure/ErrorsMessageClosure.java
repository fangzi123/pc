package com.micro.rent.common.support.task.closure;

import com.micro.rent.common.support.TemplateUtils;
import com.micro.rent.common.support.task.context.MessageTaskContext;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.tools.ant.taskdefs.MatchingTask;
import org.apache.velocity.VelocityContext;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

/**
 * @author
 * @version 1.0
 * @Description errors消息资源执行闭包
 * @date 2012-11-26
 */
public class ErrorsMessageClosure extends BaseTaskClosure {

    @Override
    public void execute(Object input) {
        try {
            //获取上下文
            MessageTaskContext context = this.toMessageTaskContext(input);
            //获取ant任务对象
            MatchingTask task = context.getAntTask();

            task.log(String.format("message task context: %s", context));

            //读取资源文件
            Properties errorsCodeMsgProp = new Properties();
            try {
                errorsCodeMsgProp.load(new FileInputStream(context.getFilePath()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            task.log(String.format("read errors message resources: %s", errorsCodeMsgProp));

            //将参数输入模板生成对应java文件
            VelocityContext velocityContext = new VelocityContext();
            velocityContext.put("package", context.getTargetPackage());//设置包名
            velocityContext.put("createdate", DateFormatUtils.format(new Date(), "yyyy-MM-dd"));//设置创建日期
            velocityContext.put("prefix", "E");//设置前缀
            velocityContext.put("errors", errorsCodeMsgProp);//设置映射属性
            TemplateUtils.VelocityHelper.executeTemplateGenerator(
                    "template/errorsTemplate.vm",
                    new StringBuilder()
                            .append(context.getTargetProject()).append("/")
                            .append(context.getTargetPackage().replace(".", "/")).append("/")
                            .append(context.getTargetFileName()).toString()
                    ,
                    velocityContext
            );
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

}
