package zgg.toolkit.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zgg.toolkit.core.enums.ResultCode;
import zgg.toolkit.core.enums.StatusEnum;
import zgg.toolkit.core.exception.BaseException;
import zgg.toolkit.core.utils.HelpUtils;
import zgg.toolkit.core.utils.IdWorker;
import zgg.toolkit.system.base.SystemBaseService;
import zgg.toolkit.system.mapper.PermissionExtendMapper;
import zgg.toolkit.system.mapper.autogen.ModuleMapper;
import zgg.toolkit.system.mapper.autogen.PermissionMapper;
import zgg.toolkit.system.model.dto.EnableDto;
import zgg.toolkit.system.model.dto.ModuleSaveDto;
import zgg.toolkit.system.model.dto.PermissionSaveDto;
import zgg.toolkit.system.model.entity.Module;
import zgg.toolkit.system.model.entity.ModuleExample;
import zgg.toolkit.system.model.entity.Permission;
import zgg.toolkit.system.model.vo.PermissionVo;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by zgg on 2018/11/12
 * 模块和权限是在一张表，模块仅仅用于对权限分组，便于查看、理解
 */
@Service
public class PermissionService extends SystemBaseService {
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private ModuleMapper moduleMapper;
    @Autowired
    private PermissionExtendMapper perExtendMapper;


    /**
     * 权限列表(通过模块树形结构展示)
     * 参考 https://blog.csdn.net/wohaqiyi/article/details/78338108?locationNum=4&fps=1
     *
     * @return
     */
    public List<PermissionVo> findPermissionTree() {
        List<PermissionVo> vos = perExtendMapper.findPermissionTree();
        return generatePermissionTree(vos);
    }

    /**
     * 添加/修改模块基本信息
     *
     * @param dto
     * @return
     */
    public Module saveModule(ModuleSaveDto dto) {
        if (dto.getId() == null) {
            Module module = new Module();
            HelpUtils.copyProperties(dto, module);
            module.setId(IdWorker.nextId());
            module.setStatus(StatusEnum.ENABLE);
            moduleMapper.insert(module);
            // 添加默认权限
            PermissionSaveDto perAdd = new PermissionSaveDto();
            perAdd.setPerName("查看");
            perAdd.setPerCode(module.getModuleCode() + ":view");
            perAdd.setModuleId(module.getId());
            this.savePermission(perAdd);

            return module;
        } else {
            Module module = moduleMapper.selectByPrimaryKey(dto.getId());
            if (module == null) {
                throw new BaseException(ResultCode.DATA_ERROR);
            }
            HelpUtils.copyProperties(dto, module);
            moduleMapper.updateByPrimaryKey(module);
            return module;
        }
    }

    /**
     * 启用/停用模块
     * 会同步修改该拥有该模块下权限的角色权限，相当于通过模块批量管理权限
     * 若当前模块的父模块为关闭状态则无法开启，需要先启用父模块
     * 关闭一个模块时，所有子模块都将关闭，开启模块，只会开启自己
     *
     * @param dto
     */
    @Transactional(rollbackFor = Exception.class)
    public void enableModule(EnableDto dto) {
        Module module = moduleMapper.selectByPrimaryKey(dto.getId());
        if (module == null) {
            throw new BaseException(ResultCode.DATA_ERROR);
        }
        // 开启模块
        if (dto.getStatus() == StatusEnum.ENABLE) {
            if (module.getPid() != 0) {
                ModuleExample example = new ModuleExample();
                example.or().andIdEqualTo(module.getPid())
                        .andStatusEqualTo(StatusEnum.DISABLE);
                long count = moduleMapper.countByExample(example);
                if (count > 0) {
                    throw new BaseException("请先开启父模块");
                }
            }
            module.setStatus(StatusEnum.ENABLE);
            moduleMapper.updateByPrimaryKey(module);
        }
        // 停用模块
        if (dto.getStatus() == StatusEnum.DISABLE) {
            module.setStatus(dto.getStatus());
            moduleMapper.updateByPrimaryKey(module);
            // 停用所有子模块:
            // 方法1，利用mysql存储过程，递归查询子节点，由于在sql端，维护较麻烦，但对于数据量大的树形结构，较为适合
            // 方法2，查询出所有记录，利用java查询子节点，只适用于记录量小的，这里比较适合
            List<Module> children = findModuleAndChildren(module.getId());
            List<Long> ids = children.stream().map(Module::getId).collect(Collectors.toList());
            ModuleExample ep = new ModuleExample();
            ep.or().andIdIn(ids);
            Module m = new Module();
            m.setStatus(StatusEnum.DISABLE);
            moduleMapper.updateByExampleSelective(m, ep);
        }
    }

    /**
     * 删除模块
     *
     * @param id
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteModule(Long id) {
        ModuleExample example = new ModuleExample();
        example.or().andPidEqualTo(id);
        long l = moduleMapper.countByExample(example);
        if (l > 0) {
            throw new BaseException("请先删除子模块");
        }
        moduleMapper.deleteByPrimaryKey(id);
        // 同时删除此模块下的权限
        this.deletePermission(null, id);
    }

    /**
     * 添加/修改权限
     *
     * @param dto
     */
    public void savePermission(PermissionSaveDto dto) {
        Permission per = new Permission();
        HelpUtils.copyProperties(dto, per);
        if (dto.getId() == null) {
            per.setId(IdWorker.nextId());
            permissionMapper.insert(per);
        } else {
            permissionMapper.updateByPrimaryKey(per);
        }
    }

    /**
     * 根据id或模块id删除权限
     *
     * @param perId
     * @param moduleId
     */
    public void deletePermission(Long perId, Long moduleId) {
        if (perId != null) {
            Permission per = permissionMapper.selectByPrimaryKey(perId);
            if (per != null && HelpUtils.contains(per.getPerCode(), ":view")) {
                throw new BaseException("查看权限不可删除");
            }
            perExtendMapper.deletePermissionById(perId);
        }
        if (moduleId != null) {
            perExtendMapper.deletePermissionByModuleId(moduleId);
        }
    }


    /****************** 内部方法 *********************/

    /**
     * 获取模块及所属子模块
     *
     * @param id
     * @return
     */
    private List<Module> findModuleAndChildren(Long id) {
        List<Module> all = moduleMapper.selectByExample(new ModuleExample());
        Module parent = all.stream().filter(it -> id.equals(it.getId())).findFirst().get();
        List<Module> children = findModuleChildren(parent, all);
        return children;
    }

    private List<Module> findModuleChildren(Module parent, List<Module> all) {
        Set<Object> used = new HashSet<>();
        List<Module> children = new ArrayList<>();
        all.stream()
                .filter(it -> used.add(it.getId()))
                .filter(it -> parent.getId().equals(it.getPid()))
                .forEach(it -> {
                    children.add(it);
                    findModuleChildren(it, all);
                });
        return children;
    }


    private List<PermissionVo> generatePermissionTree(List<PermissionVo> vos) {
        // 获取根模块
        List<PermissionVo> parent = vos.stream()
                .filter(it -> 0 == it.getPid())
                // 按照 sort升序排列，.reversed()是降序，要求进行排列的属性或对象必须是实现了Comparable接口的
                .sorted(Comparator.comparing(PermissionVo::getSort))
                .collect(Collectors.toList());
        // 利用父模块查询子模块
        parent.forEach(it -> {
            findPerVOChildren(it, vos);
        });
        return parent;
    }

    private void findPerVOChildren(PermissionVo parent, List<PermissionVo> vos) {
        Set<Long> used = new HashSet<>();
        List<PermissionVo> children = new ArrayList<>();
        vos.stream()
                .filter(it -> used.add(it.getId()))
                .filter(it -> it.getPid().equals(parent.getId()))
                .sorted(Comparator.comparing(PermissionVo::getSort))
                .forEach(it -> {
                    children.add(it);
                    findPerVOChildren(it, vos);
                });
        parent.setChildren(children);
    }
}
