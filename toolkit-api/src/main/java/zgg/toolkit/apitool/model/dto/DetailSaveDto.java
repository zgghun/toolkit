package zgg.toolkit.apitool.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by zgg on 2018/11/26
 */

@Data
public class DetailSaveDto {
    private Long id;

    @NotNull
    private Long projectId;
    @NotNull
    private Long groupId;

    private String address;
    private String header;
    private String body;
    @NotNull
    private Integer sort;
}
