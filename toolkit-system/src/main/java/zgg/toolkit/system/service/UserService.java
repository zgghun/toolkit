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
import zgg.toolkit.core.service.BaseService;
import zgg.toolkit.core.utils.HelpUtils;
import zgg.toolkit.core.utils.IdWorker;
import zgg.toolkit.system.base.SystemBaseService;
import zgg.toolkit.system.mapper.UserExtendMapper;
import zgg.toolkit.system.mapper.autogen.UserDetailMapper;
import zgg.toolkit.system.mapper.autogen.UserMapper;
import zgg.toolkit.system.mapper.autogen.UserRoleMapper;
import zgg.toolkit.system.model.dto.UserDetailSaveDto;
import zgg.toolkit.system.model.dto.UserQuery;
import zgg.toolkit.system.model.dto.UserRoleSetDto;
import zgg.toolkit.system.model.dto.UserSaveDto;
import zgg.toolkit.system.model.entity.*;
import zgg.toolkit.system.model.vo.LoginInfo;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by zgg on 2018/10/18
 */
@Service
public class UserService extends SystemBaseService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserDetailMapper userDetailMapper;
    @Autowired
    private UserExtendMapper userExtendMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;


    /**
     * 添加/修改用户
     * @param dto
     * @return
     */
    public User saveUser(UserSaveDto dto) {
        if (dto.getId() == null) {
            User user = new User();
            HelpUtils.copyProperties(dto, user);
            user.setId(IdWorker.nextId());
            user.setPassword(HelpUtils.md5(dto.getPassword()));
            user.setCreateTime(LocalDateTime.now());
            user.setUpdateTime(LocalDateTime.now());
            userMapper.insert(user);

            user.setPassword("*********");
            return user;
        } else {
            User user = userMapper.selectByPrimaryKey(dto.getId());
            if (user == null) {
                throw new BaseException(ResultCode.NOT_FOUND_ERROR);
            }
            HelpUtils.copyProperties(dto, user);
            user.setPassword(HelpUtils.md5(dto.getPassword()));
            user.setUpdateTime(LocalDateTime.now());
            userMapper.updateByPrimaryKeySelective(user);
            user.setPassword("*********");
            return user;
        }
    }

    /**
     * 删除用户
     * @param ids
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteUser(List<Long> ids) {
        UserExample example = new UserExample();
        example.or().andIdIn(ids);
        User user = new User();
        user.setStatus(StatusEnum.DELETE);
        userMapper.updateByExampleSelective(user, example);
    }

    /**
     * 查询用户
     * @param query
     * @param pageParam
     * @return
     */
    public PageList<User> findUser(UserQuery query, PageParam pageParam) {
        PageHelper.startPage(pageParam);
        List<User> users = userExtendMapper.findUser(query);
        users.forEach(it -> it.setPassword("********"));
        return new PageList<>(users);
    }

    /**
     * 保存用户扩展信息
     * @param dto
     * @param loginInfo
     * @return
     */
    public UserDetail saveUserDetail(UserDetailSaveDto dto, LoginInfo loginInfo) {
        UserDetail userDetail = new UserDetail();
        HelpUtils.copyProperties(dto, userDetail);
        return userDetail;
    }

    /**
     * 获取用户扩展信息
     * @param id
     * @return
     */
    public UserDetail getUserDetail(Long id) {
        return userDetailMapper.selectByPrimaryKey(id);
    }

    /**
     * 用户角色设置
     * @param dto
     */
    @Transactional(rollbackFor = Exception.class)
    public void setUserRole(UserRoleSetDto dto) {
        //删除旧的
        UserRoleExample example = new UserRoleExample();
        example.or().andUserIdEqualTo(dto.getUserId());
        userRoleMapper.deleteByExample(example);

        // 添加新的
        dto.getRoleIds().forEach(it -> {
            UserRole userRole = new UserRole(IdWorker.nextId(), dto.getUserId(), it);
            userRoleMapper.insert(userRole);
        });
    }
}
