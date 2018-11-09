package zgg.toolkit.system.service;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zgg.toolkit.core.enums.StatusEnum;
import zgg.toolkit.core.model.PageList;
import zgg.toolkit.core.model.PageParam;
import zgg.toolkit.core.service.BaseService;
import zgg.toolkit.core.utils.HelpUtils;
import zgg.toolkit.core.utils.IdWorker;
import zgg.toolkit.system.base.SystemBaseService;
import zgg.toolkit.system.mapper.UserExtendMapper;
import zgg.toolkit.system.mapper.autogen.UserDetailMapper;
import zgg.toolkit.system.mapper.autogen.UserMapper;
import zgg.toolkit.system.model.dto.UserDetailSaveDto;
import zgg.toolkit.system.model.dto.UserQuery;
import zgg.toolkit.system.model.dto.UserSaveDto;
import zgg.toolkit.system.model.entity.User;
import zgg.toolkit.system.model.entity.UserDetail;
import zgg.toolkit.system.model.entity.UserExample;
import zgg.toolkit.system.model.vo.LoginInfo;

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


    public PageList<User> findUser(UserQuery query, PageParam pageParam) {
        PageHelper.startPage(pageParam);
        List<User> users = userExtendMapper.findUser(query);
        users.forEach(it -> it.setPassword("********"));
        return new PageList<>(users);
    }

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
            HelpUtils.copyProperties(dto, user);
            user.setPassword(HelpUtils.md5(dto.getPassword()));
            user.setUpdateTime(LocalDateTime.now());
            userMapper.updateByPrimaryKeySelective(user);
            user.setPassword("*********");
            return user;
        }
    }

    @Transactional
    public void deleteUser(List<Long> ids) {
        UserExample example = new UserExample();
        example.or().andIdIn(ids);
        User user = new User();
        user.setStatus(StatusEnum.DELETE);
        userMapper.updateByExampleSelective(user, example);
        System.out.println(1111);
    }

    public UserDetail saveUserDetail(UserDetailSaveDto dto, LoginInfo loginInfo) {
        UserDetail userDetail = new UserDetail();
        HelpUtils.copyProperties(dto, userDetail);
        return userDetail;
    }

    public UserDetail getUserDetail(Long id) {
        UserDetail detail = userDetailMapper.selectByPrimaryKey(id);
        return detail;
    }
}
