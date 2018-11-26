package zgg.toolkit.apitool.model.vo;

import lombok.Data;
import zgg.toolkit.apitool.model.entity.ApiDetail;
import zgg.toolkit.apitool.model.entity.ApiGroup;

import java.util.List;

/**
 * Created by zgg on 2018/11/26
 */
@Data
public class ApiVo {
    private ApiGroup group;
    private List<ApiDetail> apiDetails;
}
