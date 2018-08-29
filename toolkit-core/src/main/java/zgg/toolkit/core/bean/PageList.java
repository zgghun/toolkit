package zgg.toolkit.core.bean;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by zgg on 2018/08/29
 */
public class PageList<T> extends ArrayList<T> {
    private Integer pageIndex;
    private Integer pageSize;
    private Integer total;

    public PageList() {
        super();
    }

    public PageList(Collection<? extends T> c, Integer total, PageParam page) {
        super(c);
        this.pageIndex = page.getPageIndex();
        this.pageSize = page.getPageSize();
        this.total = total;
    }

    public PageList(Collection<? extends T> c, Integer pageIndex, Integer pageSize, Integer total) {
        super(c);
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.total = total;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
