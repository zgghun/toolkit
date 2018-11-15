package zgg.toolkit.system.service;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zgg.toolkit.core.enums.ResultCode;
import zgg.toolkit.core.enums.StatusEnum;
import zgg.toolkit.core.exception.BaseException;
import zgg.toolkit.core.model.PageList;
import zgg.toolkit.core.model.PageParam;
import zgg.toolkit.core.utils.HelpUtils;
import zgg.toolkit.core.utils.IdWorker;
import zgg.toolkit.system.base.SystemBaseService;
import zgg.toolkit.system.mapper.autogen.RoleMapper;
import zgg.toolkit.system.mapper.autogen.RolePermissionMapper;
import zgg.toolkit.system.model.dto.RolePerSetDto;
import zgg.toolkit.system.model.dto.RoleQuery;
import zgg.toolkit.system.model.dto.RoleSaveDto;
import zgg.toolkit.system.model.entity.Role;
import zgg.toolkit.system.model.entity.RoleExample;
import zgg.toolkit.system.model.entity.RolePermission;
import zgg.toolkit.system.model.entity.RolePermissionExample;

import java.util.List;

/**
 * Created by zgg on 2018/10/18
 */

@Service
public class RoleService extends SystemBaseService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;


    /**
     * 添加/更新角色
     *
     * @param dto
     * @return
     */
    public Role saveRole(RoleSaveDto dto) {
        if (dto.getId() == null) {
            Role role = new Role();
            HelpUtils.copyProperties(dto, role);
            role.setId(IdWorker.nextId());
            roleMapper.insert(role);
            return role;
        } else {
            Role role = roleMapper.selectByPrimaryKey(dto.getId());
            if (role == null) {
                throw new BaseException(ResultCode.DATA_ERROR);
            }
            HelpUtils.copyProperties(dto, role);
            roleMapper.updateByPrimaryKeySelective(role);
            return role;
        }
    }

    /**
     * 删除角色
     * @param ids
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteRole(List<Long> ids){
        RoleExample example = new RoleExample();
        example.or().andIdIn(ids);
        Role role = new Role();
        role.setStatus(StatusEnum.DELETE);
        roleMapper.updateByExampleSelective(role, example);
    }

    /**
     * 查询角色
     * @param query
     * @param pageParam
     * @return
     */
    public PageList<Role> findRole(RoleQuery query, PageParam pageParam){
        RoleExample example = new RoleExample();
        example.setOrderByClause("sort ASC");
        RoleExample.Criteria criteria = example.or();
        if (HelpUtils.isNotBlank(query.getKeyword())){
            criteria.andNameLike("%" + query.getKeyword() + "%");
        }
        if (query.getStatus() != null) {
            criteria.andStatusEqualTo(query.getStatus());
        }else {
            criteria.andStatusNotEqualTo(StatusEnum.DELETE);
        }
        PageHelper.startPage(pageParam);
        List<Role> roles = roleMapper.selectByExample(example);
        return new PageList<>(roles);
    }

    /**
     * 角色权限设置
     * @param dto
     */
    public void setRolePermission(RolePerSetDto dto) {
        //删除旧的
        RolePermissionExample example = new RolePermissionExample();
        example.or().andRoleIdEqualTo(dto.getRoleId());
        rolePermissionMapper.deleteByExample(example);

        //添加新的
        dto.getPerIds().forEach(it -> {
            RolePermission rolePer = new RolePermission(IdWorker.nextId(), dto.getRoleId(), it);
            rolePermissionMapper.insert(rolePer);
        });
    }
}
