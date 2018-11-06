package zgg.toolkit.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zgg.toolkit.core.enums.GenderEnum;
import zgg.toolkit.core.service.BaseService;
import zgg.toolkit.core.utils.HelpUtils;
import zgg.toolkit.core.utils.IdWorker;
import zgg.toolkit.system.mapper.autogen.UserMapper;
import zgg.toolkit.system.model.dto.UserSaveDto;
import zgg.toolkit.system.model.entity.User;
import zgg.toolkit.system.model.entity.UserExample;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by zgg on 2018/10/18
 */
@Service
public class UserService extends BaseService {
    @Autowired
    private UserMapper userMapper;


    public List<User> listUser() {
        List<User> users = userMapper.selectByExample(new UserExample());
        return users;
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
}
