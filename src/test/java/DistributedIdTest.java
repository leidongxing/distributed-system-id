import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.StopWatch;
import snowflake.SnowflakeByHandle;
import snowflake.SnowflakeByHutool;
import uuid.UUIDGenerate;

import java.util.UUID;
import java.util.stream.IntStream;

/**
 * @author LeiDongxing
 * created on 2021/3/2
 */
@Slf4j
public class DistributedIdTest {
    int loopCount = 100000;

    @Test
    public void testId() {
        log.info("UUID: {}", UUIDGenerate.getId());
        log.info("Snowflake id: {}",  new SnowflakeByHandle(0, 0).nextId());
        log.info("Snowflake hutool id: : {}", new SnowflakeByHutool().snowflakeId());

    }


    @Test
    public void test() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("uuid");
        IntStream.rangeClosed(1, loopCount).parallel().forEach(__ -> UUIDGenerate.getId());
        stopWatch.stop();

        SnowflakeByHandle idWorker = new SnowflakeByHandle(0, 0);
        stopWatch.start("SnowflakeByHandle");
        IntStream.rangeClosed(1, loopCount).parallel().forEach(__ -> idWorker.nextId());
        stopWatch.stop();

        SnowflakeByHutool snowflakeByHutool = new SnowflakeByHutool();
        stopWatch.start("SnowflakeByHutool");
        IntStream.rangeClosed(1, loopCount).parallel().forEach(__ -> snowflakeByHutool.snowflakeId());
        stopWatch.stop();

        System.out.println(stopWatch.prettyPrint());
    }
}
