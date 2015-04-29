package com.micro.rent.common.support.task.closure;

import com.micro.rent.common.support.task.context.MessageTaskContext;
import org.apache.commons.collections.Closure;

/**
 * @author
 * @version 1.0
 * @Description 执行闭包基类
 * @date 2012-11-26
 */
public abstract class BaseTaskClosure implements Closure {
    /**
     * @param object
     * @return
     * @Description 输入obj转为资源任务上下文
     * @author
     */
    protected MessageTaskContext toMessageTaskContext(Object object) {
        return object == null ? new MessageTaskContext() : (MessageTaskContext) object;
    }
}
