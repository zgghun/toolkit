package zgg.toolkit.core;

import org.junit.Test;
import zgg.toolkit.core.page.PageParam;
import zgg.toolkit.core.utils.IdWorker;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class ToolkitCoreApplicationTests {

    // 计算10位最大可以表示几
    @Test
    public void test2(){
        long max = -1L ^ (-1L << 10L);
        System.out.println(max);
    }

    @Test
    public void test1(){
        PageParam pageParam = new PageParam();
        pageParam.setPageIndex(-1);
        pageParam.setPageSize(null);
        System.out.println(pageParam.getPageIndex() + "---" + pageParam.getPageSize());
    }

    @Test
    public void contextLoads() {
        for (int i = 0; i < 1; i++) {
            System.out.println(IdWorker.getNextId());
        }
    }

}
