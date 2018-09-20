package zgg.toolkit.core.page;

import lombok.Data;

/**
 * Created by zgg on 2018/08/29
 */
@Data
public class PageParam {
    private Integer pageIndex;
    private Integer pageSize;

    public PageParam() {
        this.pageIndex = 1;
        this.pageSize = 20;
    }

    public PageParam(Integer pageIndex, Integer pageSize) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }

    public Integer getPageIndex() {
        return (pageIndex == null || pageIndex < 1) ? 1 : pageIndex;
    }

    public Integer getPageSize() {
        return (pageSize == null || pageSize < 1) ? 20 : pageSize;
    }
}
