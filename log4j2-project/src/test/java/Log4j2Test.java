import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @description:
 * @author:DerTraum
 * @date:2020/12/30
 * @since
 */
@SpringBootTest
public class Log4j2Test {

    private final static Logger LOGGER = LoggerFactory.getLogger(Log4j2Test.class);

    @AfterClass
    public static void setUp(){
        LOGGER.info("AfterClass : setUp方法执行");
    }

    @BeforeClass
    public static void before(){
        LOGGER.info("BeforeClass : before方法执行");
    }

    @Test
    public void test(){
        LOGGER.info("打印info级别");
        LOGGER.debug("打印debug级别");
        LOGGER.error("打印error级别");
        LOGGER.warn("打印warn级别");
    }
}
