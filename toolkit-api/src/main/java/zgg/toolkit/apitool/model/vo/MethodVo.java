package zgg.toolkit.apitool.model.vo;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Data
public class MethodVo {
    private String name;
    private String url;

    private RequestMethod requestMethod;
    private String contentType;

    private List<ParameterVo> parameters;
}
