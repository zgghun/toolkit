package zgg.toolkit.system.mapper;

import zgg.toolkit.system.model.vo.PermissionVO;

import java.util.List;

/**
 * Created by zgg on 2018/11/16
 */
public interface PermissionExtendMapper {
    void deletePermissionById(Long id);

    void deletePermissionByModuleId(Long moduleId);

    List<PermissionVO> findPermissionTree();

}
