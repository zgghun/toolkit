package zgg.toolkit.system.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by zgg on 2018/10/19
 */
@Data
public class DeleteDto {
    @NotNull
    private Long id;
}
