package zgg.toolkit.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zgg.toolkit.core.service.BaseService;
import zgg.toolkit.core.utils.IdWorker;
import zgg.toolkit.system.mapper.UserMapper;
import zgg.toolkit.system.model.dto.UserSaveDto;
import zgg.toolkit.system.model.entity.User;

import java.time.LocalDateTime;

/**
 * Created by zgg on 2018/10/18
 */
@Service
public class UserService extends BaseService {
    @Autowired
    private UserMapper userMapper;

    public User saveUser(UserSaveDto dto) {
        if (dto.getId() == null) {
            User user = new User(
                    IdWorker.nextId(),
                    dto.getUsername(),
                    dto.getTel(),
                    dto.getEmail(),
                    dto.getPassword(),
                    dto.getAvatar(),
                    dto.getGender().name(),
                    dto.getStatus(),
                    LocalDateTime.now(),
                    LocalDateTime.now()
            );
            userMapper.insertSelective(user);
            return user;
        } else {
            return null;
        }
    }
}
