package zgg.toolkit.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import zgg.toolkit.core.enums.ResultCode;
import zgg.toolkit.core.exception.BaseException;
import zgg.toolkit.core.model.MapVO;
import zgg.toolkit.core.utils.HelpUtils;
import zgg.toolkit.core.utils.IdWorker;
import zgg.toolkit.system.base.SystemBaseService;
import zgg.toolkit.system.mapper.autogen.PermissionMapper;
import zgg.toolkit.system.model.dto.ModuleAddDto;
import zgg.toolkit.system.model.dto.ModuleUpdateDto;
import zgg.toolkit.system.model.dto.PermissionUpdateDto;
import zgg.toolkit.system.model.entity.Permission;
import zgg.toolkit.system.model.entity.PermissionExample;
import zgg.toolkit.system.model.vo.PermissionVO;

import javax.validation.Valid;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by zgg on 2018/11/12
 * 模块和权限是在一张表，模块仅仅用于对权限分组，便于查看、理解
 */
@Service
public class PermissionService extends SystemBaseService {
    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * 添加新模块
     *
     * @param dto
     * @return
     */
    public Permission addModule(ModuleAddDto dto) {
        PermissionExample example = new PermissionExample();
        example.or().andModuleCodeEqualTo(dto.getModuleCode());
        long count = permissionMapper.countByExample(example);
        if (count != 0){
            throw new BaseException("模块code重复");
        }
        Permission permission = new Permission();
        HelpUtils.copyProperties(dto, permission);
        permission.setId(IdWorker.nextId());
        // 模块默必有的权限
        permission.setPerName("查看");
        permission.setPerCode(dto.getModuleCode() + ":view");
        permissionMapper.insert(permission);
        return permission;
    }


    /**
     * 权限列表（树形结构）
     * 参考 https://blog.csdn.net/wohaqiyi/article/details/78338108?locationNum=4&fps=1
     *
     * @return
     */
    public List<PermissionVO> findPermission() {
        PermissionExample example = new PermissionExample();
        List<Permission> list = permissionMapper.selectByExample(example);

        List<PermissionVO> perTree = generatePermissionTree(list);

        return perTree;
    }

    /**
     * 修改模块基本信息
     *
     * @param dto
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateModule(ModuleUpdateDto dto) {
        PermissionExample example = new PermissionExample();
        example.or().andModuleCodeEqualTo(dto.getOldModuleCode());
        Permission per = new Permission();
        HelpUtils.copyProperties(dto, per);
        permissionMapper.updateByExampleSelective(per, example);

        // 若module_code变了，要修改子模块的parent_module
        if (!dto.getModuleCode().equals(dto.getOldModuleCode())) {
            PermissionExample example2 = new PermissionExample();
            example2.or().andParentModuleEqualTo(dto.getOldModuleCode());
            Permission per2 = new Permission();
            per2.setParentModule(dto.getModuleCode());
            permissionMapper.updateByExampleSelective(per2, example2);
        }
    }

    public List<PermissionVO> generatePermissionTree(List<Permission> list) {
        List<PermissionVO> vos = new ArrayList<>();
        Map<String, List<MapVO>> perMap = new HashMap<>(16);

        for (Permission per : list) {
            // 转为PermissionVO对象
            PermissionVO vo = new PermissionVO();
            HelpUtils.copyProperties(per, vo);
            vos.add(vo);
            // 把同一模块权限放在一起
            if (perMap.containsKey(per.getModuleCode())) {
                perMap.get(per.getModuleCode()).add(new MapVO(per.getPerName(), per.getPerCode()));
            } else {
                perMap.put(per.getModuleCode(), new ArrayList<MapVO>() {{
                    add(new MapVO(per.getPerName(), per.getPerCode()));
                }});
            }
        }

        // 对vos根据 module_code去重,参考 https://blog.csdn.net/u012817635/article/details/79917674
        List<PermissionVO> vosTemp = vos.stream()
                .filter(distinctByKey(PermissionVO::getModuleCode))
                .collect(Collectors.toList());

        // 获取根模块,并设置权限
        List<PermissionVO> parent = vosTemp.stream()
                .filter(it -> "0".equals(it.getParentModule()))
                // 按照 sort升序排列，PermissionVO::getSort.reversed()是降序，要求进行排列的属性或对象必须是实现了Comparable接口的
                .sorted(Comparator.comparing(PermissionVO::getSort))
                .collect(Collectors.toList());

        // 利用根模块查询子模块
        Map<String, String> used = new HashMap<>(vosTemp.size());
        parent.forEach(it -> {
            it.setPermissions(perMap.get(it.getModuleCode()));
            findPerVOChildren(it, vosTemp, perMap, used);
        });

        return parent;
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    private void findPerVOChildren(PermissionVO parent, List<PermissionVO> vos, Map<String, List<MapVO>> perMap, Map<String, String> used) {
        List<PermissionVO> children = new ArrayList<>();
        vos.stream()
                .filter(it -> !used.containsKey(it.getModuleCode()))
                .filter(it -> it.getParentModule().equals(parent.getModuleCode()))
                .sorted(Comparator.comparing(PermissionVO::getSort))
                .forEach(it -> {
                    used.put(it.getModuleCode(), it.getModuleCode());
                    it.setPermissions(perMap.get(it.getModuleCode()));
                    children.add(it);
                    findPerVOChildren(it, vos, perMap, used);
                });
        parent.setChildren(children);
    }

    /**
     * 添加/删除权限
     *
     * @param dto
     */
    public void updatePermission(PermissionUpdateDto dto) {
        PermissionExample example = new PermissionExample();
        PermissionExample.Criteria criteria = example.or();
        // 添加权限
        if (HelpUtils.isNotBlank(dto.getPerName())) {
            criteria.andModuleCodeEqualTo(dto.getModuleCode());
            List<Permission> temp = permissionMapper.selectByExample(example);
            if (temp.size() == 0) {
                throw new BaseException(ResultCode.DATA_ERROR);
            }
            Permission per = temp.get(0);
            per.setId(IdWorker.nextId());
            per.setPerName(dto.getPerName());
            per.setPerCode(dto.getPerCode());
            permissionMapper.insert(per);
        } else {
            //删除权限
            criteria.andPerCodeEqualTo(dto.getPerCode());
            permissionMapper.deleteByExample(example);
        }
    }
}
