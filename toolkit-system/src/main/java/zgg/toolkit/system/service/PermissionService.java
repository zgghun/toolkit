package zgg.toolkit.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zgg.toolkit.core.enums.ResultCode;
import zgg.toolkit.core.enums.StatusEnum;
import zgg.toolkit.core.exception.BaseException;
import zgg.toolkit.core.utils.HelpUtils;
import zgg.toolkit.core.utils.IdWorker;
import zgg.toolkit.system.base.SystemBaseService;
import zgg.toolkit.system.mapper.autogen.PermissionMapper;
import zgg.toolkit.system.model.dto.PermissionSaveDto;
import zgg.toolkit.system.model.entity.Permission;
import zgg.toolkit.system.model.entity.PermissionExample;
import zgg.toolkit.system.model.vo.PermissionVO;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by zgg on 2018/11/12
 */
@Service
public class PermissionService extends SystemBaseService {
    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * 添加/修改权限
     *
     * @param dto
     * @return
     */
    public Permission savePermission(PermissionSaveDto dto) {
        if (dto.getIcon() == null) {
            Permission permission = new Permission();
            HelpUtils.copyProperties(dto, permission);
            permission.setId(IdWorker.nextId());
            permissionMapper.insert(permission);
            return permission;
        } else {
            Permission permission = permissionMapper.selectByPrimaryKey(dto.getId());
            if (permission == null) {
                throw new BaseException(ResultCode.NOT_FOUND_ERROR);
            }
            HelpUtils.copyProperties(dto, permission);
            permissionMapper.updateByPrimaryKey(permission);
            return permission;
        }
    }

    /**
     * 删除权限
     *
     * @param ids
     */
    public void deletePermission(List<Long> ids) {
        PermissionExample example = new PermissionExample();
        example.or().andIdIn(ids);
        Permission permission = new Permission();
        permission.setStatus(StatusEnum.DELETE);
        permissionMapper.updateByExampleSelective(permission, example);
    }

    /**
     * 权限列表
     * 参考 https://blog.csdn.net/wohaqiyi/article/details/78338108?locationNum=4&fps=1
     *
     * @return
     */
    public List<PermissionVO> findPermission() {
        PermissionExample example = new PermissionExample();
        example.setOrderByClause("pid ASC, sort ASC");
        example.or().andStatusNotEqualTo(StatusEnum.DELETE);
        List<Permission> list = permissionMapper.selectByExample(example);

        List<PermissionVO> parent = generatePermissionTree(list);

        return parent;
    }

    public List<PermissionVO> generatePermissionTree(List<Permission> list) {
        ArrayList<PermissionVO> vos = new ArrayList<>();
        list.forEach(it -> {
            PermissionVO vo = new PermissionVO();
            HelpUtils.copyProperties(it, vo);
            vos.add(vo);
        });
        List<PermissionVO> parent = vos.stream().filter(it -> it.getPid() == 0).collect(Collectors.toList());
        HashMap<Long, Long> used = new HashMap<>(vos.size());
        parent.forEach(it -> findPerVOChildren(it, vos, used));
        return parent;
    }

    private void findPerVOChildren(PermissionVO parent, List<PermissionVO> vos, HashMap<Long, Long> used) {
        List<PermissionVO> children = new ArrayList<>();
        vos.stream()
                .filter(it -> !used.containsKey(it.getId()))
                .filter(it -> it.getPid().equals(parent.getId()))
                .forEach(it -> {
                    used.put(it.getId(), it.getPid());
                    findPerVOChildren(it, vos, used);
                    children.add(it);
                });
        parent.setChildren(children);
    }
}
