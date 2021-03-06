package zgg.toolkit.system.model.common;

import com.github.pagehelper.Page;
import lombok.Data;

import java.util.List;

/**
 * Created by zgg on 2018/08/29
 */
@Data
public class PageList<T> {
    private List<T> list;
    private Integer pageNum;
    private Integer pageSize;
    private Long total;

    public PageList() {
    }

    public PageList(List<T> list, Integer pageNum, Integer pageSize, Long total) {
        this.list = list;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = total;
    }

    public PageList(List<T> list) {
        this.list = list;
        if (list instanceof Page<?>) {
            Page<?> page = (Page) list;
            this.pageNum = page.getPageNum();
            this.pageSize = page.getPageSize();
            this.total = page.getTotal();
        }
    }
}
