package zgg.toolkit.core.model;

/**
 * Created by zgg on 2018/08/29
 */
public class PageParam {
    /*
     为了配合PageHelper.startPage(Object params)方法，
     该方法默认从参数查询 pageNum,pageSize,count,reasonable,pageSizeZero
     所以这里保证参数名一致
     默认值为pageNum=pageNum;pageSize=pageSize;count=countSql;reasonable=reasonable;pageSizeZero=pageSizeZero。
    */

    private Integer pageNum;
    private Integer pageSize;

    public PageParam() {
        this.pageNum = 1;
        this.pageSize = 20;
    }

    public PageParam(Integer pageNum, Integer pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return (pageNum == null || pageNum < 1) ? 1 : pageNum;
    }

    public Integer getPageSize() {
        return (pageSize == null || pageSize < 1) ? 20 : pageSize;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
