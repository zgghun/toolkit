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
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
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
     * 权限列表（树形结构）
     * 参考 https://blog.csdn.net/wohaqiyi/article/details/78338108?locationNum=4&fps=1
     *
     * @param moduleName 查询指定模块
     * @return
     */
    public List<PermissionVO> findPermission() {
        PermissionExample example = new PermissionExample();
        PermissionExample.Criteria criteria = example.or()
                .andStatusNotEqualTo(StatusEnum.DELETE);

        List<Permission> list = permissionMapper.selectByExample(example);

        List<PermissionVO> perTree = generatePermissionTree(list);

        return perTree;
    }

    public List<PermissionVO> generatePermissionTree(List<Permission> list) {
        List<PermissionVO> vos = new ArrayList<>();
        Map<String, List<String>> perMap = new HashMap<>(16);

        for (Permission permission : list) {
            // 转为PermissionVO对象
            PermissionVO vo = new PermissionVO();
            HelpUtils.copyProperties(permission, vo);
            vos.add(vo);
            // 把同一模块权限放在一起
            if (perMap.containsKey(permission.getModuleCode())) {
                perMap.get(permission.getModuleCode()).add(permission.getPerCode());
            } else {
                perMap.put(permission.getModuleCode(), new ArrayList<>(Arrays.asList(permission.getPerCode())));
            }
        }

        // 对vos根据 module_code去重,参考 https://blog.csdn.net/u012817635/article/details/79917674
        List<PermissionVO> vosTemp = vos.stream()
                .filter(distinctByKey(PermissionVO::getModuleCode))
                .collect(Collectors.toList());

        // 获取根模块
        List<PermissionVO> parent = vosTemp.stream()
                .filter(it -> "0".equals(it.getParentModule()))
                // 按照 sort升序排列，PermissionVO::getSort.reversed()是降序，要求进行排列的属性或对象必须是实现了Comparable接口的
                .sorted(Comparator.comparing(PermissionVO::getSort))
                .collect(Collectors.toList());

        HashMap<String, String> used = new HashMap<>(vos.size());
        // 利用根模块查询子模块
        parent.forEach(it -> findPerVOChildren(it, vosTemp, used));
        return parent;
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    private void findPerVOChildren(PermissionVO parent, List<PermissionVO> vos, HashMap<String, String> used) {
        List<PermissionVO> children = new ArrayList<>();
        vos.stream()
                .filter(it -> !used.containsKey(it.getModuleCode()))
                .filter(it -> it.getParentModule().equals(parent.getModuleCode()))
                .forEach(it -> {
                    used.put(it.getModuleCode(), it.getModuleCode());
                    findPerVOChildren(it, vos, used);
                    children.add(it);
                });
        parent.setChildren(children);
    }
}
