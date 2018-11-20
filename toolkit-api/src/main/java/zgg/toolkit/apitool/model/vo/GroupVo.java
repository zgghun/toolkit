package zgg.toolkit.apitool.model.vo;

import lombok.Data;

import java.util.List;

@Data
public class GroupVo {
    private String name;
    private List<MethodVo> methods;
}
