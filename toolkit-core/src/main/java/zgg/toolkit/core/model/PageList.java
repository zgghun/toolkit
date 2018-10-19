package zgg.toolkit.core.model;

import com.github.pagehelper.Page;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by zgg on 2018/08/29
 */
public class PageList<T> extends ArrayList<T> {
    private Integer pageNum;
    private Integer pageSize;
    private Long total;

    public PageList() {
        super();
    }

    private PageList(Collection<? extends T> c, Integer pageNum, Integer pageSize, Long total) {
        super(c);
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = total;
    }

    public static PageList pageResult(List list) {
        if (list instanceof Page<?>) {
            Page<?> page = (Page) list;
            return new PageList(list, page.getPageNum(), page.getPageSize(), page.getTotal());
        }
        return null;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
