package com.micro.rent.common.support.task.predicate;

import com.micro.rent.common.support.task.context.MessageTaskContext;
import org.apache.commons.collections.Predicate;

/**
 * @author
 * @version 1.0
 * @Description 条件基类
 * @date 2012-11-26
 */
public abstract class BaseTaskPredicate implements Predicate {

    /**
     * @param object
     * @return
     * @Description 输入obj转为消息资源任务上下文
     * @author
     */
    protected MessageTaskContext toMessageTaskContext(Object object) {
        return object == null ? new MessageTaskContext() : (MessageTaskContext) object;
    }

}
