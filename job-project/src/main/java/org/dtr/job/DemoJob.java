package org.dtr.job;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * @author liudong
 * 2023/6/20 10:43
 * @version 1.0
 */
@Component
@Slf4j
public class DemoJob {

    @XxlJob("aJobHandler")
    public void aJobHandler() throws Exception {


        String str = "aJobHandler, Hello World." + LocalDateTime.now();
        XxlJobHelper.log(str);
        log.info(str);

        for (int i = 0; i < 5; i++) {
            XxlJobHelper.log("beat at:" + i);
            TimeUnit.SECONDS.sleep(1);
        }
        // default success
    }
}
