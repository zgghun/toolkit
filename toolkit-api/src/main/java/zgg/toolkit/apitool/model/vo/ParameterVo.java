package zgg.toolkit.apitool.model.vo;

import lombok.Data;

@Data
public class ParameterVo {
    private String key;
    private String value;
    private String dataType;

    private String validInfo;
}
