package zgg.toolkit.apitool.model.vo;

import lombok.Data;

@Data
public class ParameterVo {
    private String key;
    private String value;
    private String dataType;

    private String validInfo;

    public ParameterVo() {
    }

    public ParameterVo(String key, String value, String dataType, String validInfo) {

        this.key = key;
        this.value = value;
        this.dataType = dataType;
        this.validInfo = validInfo;
    }
}
