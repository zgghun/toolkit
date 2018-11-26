package zgg.toolkit.apitool.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by zgg on 2018/11/26
 */

@Data
public class HistoryAddDto {
    @NotNull
    private Long projectId;
    private String address;
    private String header;
    private String body;
}
