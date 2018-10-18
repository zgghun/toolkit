package zgg.toolkit.core;

import org.junit.Test;
import zgg.toolkit.core.model.PageParam;
import zgg.toolkit.core.utils.IdWorker;

import java.math.BigInteger;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class ToolkitCoreApplicationTests {

    // 计算10位最大可以表示几
    @Test
    public void test2() {
        long max = -1L ^ (-1L << 10L);
        System.out.println(max);
    }

    @Test
    public void test1() {
        PageParam pageParam = new PageParam();
//        pageParam.setPageNum(-1);
//        pageParam.setPageSize(null);
        System.out.println(pageParam.getPageNum() + "---" + pageParam.getPageSize());
    }

    @Test
    public void contextLoads() {
        for (int i = 0; i < 100; i++) {
            long id = IdWorker.nextId();
            String bid = "0000000000" + Long.toBinaryString(id);
            StringBuilder builder = new StringBuilder()
                    .append(id)
                    .append("---")
                    .append(new BigInteger(bid.substring(0, 43), 2))
                    .append("-")
                    .append(new BigInteger(bid.substring(43, 54), 2))
                    .append("-")
                    .append(new BigInteger(bid.substring(54), 2));
            System.out.println(builder.toString());



        }
    }

}
