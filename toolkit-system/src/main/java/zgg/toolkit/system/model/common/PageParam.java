package zgg.toolkit.system.model.common;

/**
 * Created by zgg on 2018/08/29
 */
public class PageParam {
    /*
     为了配合PageHelper.startPage(Object params)方法，
     startPage方法默认从参数查询 pageNum,pageSize,count,reasonable,pageSizeZero
     所以这里保证参数名一致
     默认值为pageNum=pageNum;pageSize=pageSize;count=countSql;reasonable=reasonable;pageSizeZero=pageSizeZero。
    */

    private Integer pageNum;
    private Integer pageSize;

    public PageParam() {
    }

    public PageParam(Integer pageNum, Integer pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = (pageNum == null || pageNum < 1) ? 1 : pageNum;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = (pageSize == null || pageSize < 1) ? 20 : pageSize;
    }
}
