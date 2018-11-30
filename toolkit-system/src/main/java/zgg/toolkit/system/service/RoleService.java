package zgg.toolkit.system.service;

import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import zgg.toolkit.system.mapper.autogen.UserRoleMapper;
import zgg.toolkit.system.model.dto.EnableDto;
import zgg.toolkit.system.model.dto.RolePerSetDto;
import zgg.toolkit.system.model.dto.RoleQuery;
import zgg.toolkit.system.model.dto.RoleSaveDto;
import zgg.toolkit.system.model.entity.*;

import java.util.List;

/**
 * Created by zgg on 2018/10/18
 */

@Service
public class RoleService extends SystemBaseService {
    private static Logger logger = LoggerFactory.getLogger(RoleService.class);
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;




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
     * 启用/停用角色
     * @param dto
     */
    public void enableRole(EnableDto dto){
        Role role = new Role();
        role.setId(dto.getId());
        role.setStatus(dto.getStatus());
        roleMapper.updateByPrimaryKeySelective(role);
    }

    /**
     * 删除角色(会删除所有用户与角色的关联，角色与权限的关联)
     * @param id
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteRole(Long id){
        roleMapper.deleteByPrimaryKey(id);
        UserRoleExample example = new UserRoleExample();
        example.or().andRoleIdEqualTo(id);
        userRoleMapper.deleteByExample(example);
        RolePermissionExample ep2 = new RolePermissionExample();
        ep2.or().andRoleIdEqualTo(id);
        rolePermissionMapper.deleteByExample(ep2);
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
        logger.debug("111111111111111111");
        logger.info("2222222222222222");
        logger.warn("3333333333333333");
        logger.error("44444444444444444");
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
