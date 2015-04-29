package support;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;


/**
 * @author
 * @version 1.0
 * @Description 基于spring-test的后台单元测试基类
 * @date
 */
@ContextConfiguration(locations = {
        "classpath:/spring-basic.xml"
})
@ActiveProfiles("dev")//激活配置
public abstract class AbstractSpringContextTestSupport extends AbstractJUnit4SpringContextTests {
    @Rule
    public TestName name = new TestName();
    protected transient Logger log = LoggerFactory.getLogger(this.getClass());

    @Before
    public void setUp() {
        log.info("调用测试方法: {}", new Object[]{name.getMethodName()});
    }

    @After
    public void tearDown() {
        //TODO ...
    }

    public void logObject(Object obj) {
        if (obj == null) {
            log.debug("select null object");
        } else {
            log.debug(">>>>>>>>>>>>>>>>>>>>>>>" + ReflectionToStringBuilder.toString(obj));
        }
    }
}
