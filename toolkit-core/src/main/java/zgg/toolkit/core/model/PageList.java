package zgg.toolkit.core.model;

import com.github.pagehelper.Page;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by zgg on 2018/08/29
 */
@Data
public class PageList<T> extends ArrayList<T> {
    private Integer pageNum;
    private Integer pageSize;
    private Long total;

    public PageList() {
        super();
    }

//    public PageList(Collection<? extends T> c, PageParam page) {
//        super(c);
//        this.pageNum = page.getPageNum();
//        this.pageSize = page.getPageSize();
//        this.total = (long) c.size();
//    }

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
}
