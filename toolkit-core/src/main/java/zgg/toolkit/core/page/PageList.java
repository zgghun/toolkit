package zgg.toolkit.core.page;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by zgg on 2018/08/29
 */
@Data
public class PageList<T> extends ArrayList<T> {
    private Integer pageIndex;
    private Integer pageSize;
    private Integer total;

    public PageList() {
        super();
    }

    public PageList(Collection<? extends T> c, PageParam page) {
        super(c);
        this.pageIndex = page.getPageIndex();
        this.pageSize = page.getPageSize();
        this.total = c.size();
    }

    public PageList(Collection<? extends T> c, Integer total, PageParam page) {
        super(c);
        this.pageIndex = page.getPageIndex();
        this.pageSize = page.getPageSize();
        this.total = total;
    }
}
