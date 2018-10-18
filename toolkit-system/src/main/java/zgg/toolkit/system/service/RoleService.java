package zgg.toolkit.system.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.management.Sensor;
import zgg.toolkit.core.enums.StatusEnum;
import zgg.toolkit.core.utils.IdWorker;
import zgg.toolkit.system.mapper.RoleMapper;
import zgg.toolkit.system.pojo.dto.RoleSaveDto;
import zgg.toolkit.system.pojo.entity.Role;

/**
 * Created by zgg on 2018/10/18
 */

@Service
public class RoleService {
    @Autowired
    private RoleMapper roleMapper;

    /**
     * 添加/更新角色
     * @param dto
     * @return
     */
    public Role saveRole(RoleSaveDto dto) {
        if (dto.getId() == null) {
            Role role = new Role();
            role.setId(IdWorker.nextId());
            role.setName(dto.getName());
            role.setStatus(StatusEnum.ENABLE.name());
            roleMapper.insert(role);
            return role;
        }else {
            Role role = new Role();
            role.setId(dto.getId());
            role.setName(dto.getName());
            role.setStatus(dto.getStatus().name());
            roleMapper.updateByPrimaryKey(role);
            return role;
        }
    }
}
