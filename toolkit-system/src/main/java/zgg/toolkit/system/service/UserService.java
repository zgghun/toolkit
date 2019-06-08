package zgg.toolkit.system.service;

import com.github.pagehelper.PageHelper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zgg.toolkit.common.util.HelpUtils;
import zgg.toolkit.common.util.IdWorker;
import zgg.toolkit.common.util.StringUtils;
import zgg.toolkit.system.base.BaseException;
import zgg.toolkit.system.base.BaseService;
import zgg.toolkit.system.constant.SysConst;
import zgg.toolkit.system.enums.ResultCode;
import zgg.toolkit.system.enums.StatusEnum;
import zgg.toolkit.system.mapper.UserExtendMapper;
import zgg.toolkit.system.mapper.autogen.UserDetailMapper;
import zgg.toolkit.system.mapper.autogen.UserMapper;
import zgg.toolkit.system.mapper.autogen.UserRoleMapper;
import zgg.toolkit.system.model.common.MapVo;
import zgg.toolkit.system.model.common.PageList;
import zgg.toolkit.system.model.common.PageParam;
import zgg.toolkit.system.model.dto.*;
import zgg.toolkit.system.model.entity.*;
import zgg.toolkit.system.model.vo.LoginInfo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zgg on 2018/10/18
 */
@Service
public class UserService extends BaseService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserDetailMapper userDetailMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private UserExtendMapper userExtendMapper;


    /**
     * 添加/修改用户
     *
     * @param dto
     * @return
     */
    public User saveUser(UserSaveDto dto) {
        if (dto.getId() == null) {
            User user = new User();
            HelpUtils.copyProperties(dto, user);
            user.setId(IdWorker.nextId());
            user.setPassword(HelpUtils.md5(dto.getPassword()));
            user.setStatus(StatusEnum.ENABLE);
            user.setCreateTime(LocalDateTime.now());
            user.setUpdateTime(LocalDateTime.now());
            userMapper.insert(user);
            user.setPassword(SysConst.MASK);
            return user;
        } else {
            User user = userMapper.selectByPrimaryKey(dto.getId());
            if (user == null) {
                throw new BaseException(ResultCode.ERROR_DATA_NOT_EXIST);
            }
            HelpUtils.copyProperties(dto, user);
            user.setPassword(HelpUtils.md5(dto.getPassword()));
            user.setUpdateTime(LocalDateTime.now());
            userMapper.updateByPrimaryKeySelective(user);
            user.setPassword(SysConst.MASK);
            return user;
        }
    }

    /**
     * 删除用户
     *
     * @param dto
     */
    @Transactional(rollbackFor = Exception.class)
    public void enableUser(EnableDto dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setStatus(StatusEnum.DELETE);
        userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 查询用户
     *
     * @param query
     * @param pageParam
     * @return
     */
    public PageList<User> findUser(UserQuery query, PageParam pageParam) {
        PageHelper.startPage(pageParam);
        List<User> users = userExtendMapper.findUser(query);
        users.forEach(it -> it.setPassword(SysConst.MASK));
//        for (int i = 0; i < 200; i++) {
//            commonTaskExecutor.execute(() -> {
//                try {
//                    Thread.sleep(100L);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                logger.info("***************" + Thread.currentThread().getName() + "**********");
//            });
//        }
        return new PageList<>(users);
    }

    /**
     * 保存用户扩展信息
     *
     * @param dto
     * @param loginInfo
     * @return
     */
    public UserDetail saveUserDetail(UserDetailSaveDto dto, LoginInfo loginInfo) {
        UserDetail detail = userDetailMapper.selectByPrimaryKey(dto.getId());
        if (detail == null) {
            detail = new UserDetail();
            HelpUtils.copyProperties(dto, detail);
            userDetailMapper.insertSelective(detail);
        } else {
            HelpUtils.copyProperties(dto, detail);
            userDetailMapper.updateByPrimaryKeySelective(detail);
        }
        return detail;
    }

    /**
     * 获取用户扩展信息
     *
     * @param id
     * @return
     */
    public UserDetail getUserDetail(Long id) {
        return userDetailMapper.selectByPrimaryKey(id);
    }

    /**
     * 用户角色设置
     *
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

    /**
     * 登陆用户查询
     *
     * @param username
     * @param password
     * @return
     */
    public User getUser(String username, String password) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isAllNumber(username)) {
            // 全数字，电话登陆
            criteria.andTelEqualTo(username);
        } else if (username.contains("@")) {
            // 包含 @ 为邮箱登陆
            criteria.andEmailEqualTo(username);
        } else {
            // 用户名登陆（必定包含 中文，或字母+数字）
            criteria.andUsernameEqualTo(username);
        }
        criteria.andPasswordEqualTo(password);
        List<User> users = userMapper.selectByExample(example);
        if (users.size() == 1) {
            return users.get(0);
        }
        if (users.size() > 1) {
            throw new BaseException(ResultCode.ERROR_DATA_NOT_EXIST);
        }
        return null;
    }

    /**
     * 获取用户权限
     *
     * @param user
     * @return
     */
    public List<String> getUserPermission(User user) {
        List<Permission> perList = userExtendMapper.findLoginUserPer(user.getId());
        List<String> per = perList.stream().map(Permission::getPerCode).collect(Collectors.toList());
        return per;
    }

    /**
     * 登陆
     *
     * @param username 用户名、电话、邮箱
     * @param password 明文密码
     * @param captcha  验证码
     */
    public LoginInfo login(String username, String password, String captcha) {
        Session session = SecurityUtils.getSubject().getSession();
        // 验证码验证
        session.removeAttribute(SysConst.SESSION_CAPTCHA);

        UsernamePasswordToken token = new UsernamePasswordToken(username, HelpUtils.md5(password));
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            logger.debug("登陆错误：{}", e.getMessage());
            throw e;
        }

        User user = (User) subject.getPrincipals().getPrimaryPrincipal();
        // 返回登陆信息，并把登陆信息存到session中，之后前端再次获取登录信息直接从session获取
        LoginInfo loginInfo = this.getLoginInfo(user);
        session.setAttribute(SysConst.SESSION_LOGIN_INFO, loginInfo);

        return loginInfo;
    }

    /**
     * 获取用户信息、权限、可访问模块
     *
     * @param user
     * @return
     */
    public LoginInfo getLoginInfo(User user) {
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setUserId(user.getId());
        loginInfo.setUsername(user.getUsername());
        loginInfo.setTel(user.getTel());
        loginInfo.setAvatar(user.getAvatar());

        List<Permission> permissions = userExtendMapper.findLoginUserPer(user.getId());
        List<Long> moduleIds = permissions.stream().map(Permission::getModuleId).distinct().collect(Collectors.toList());
        List<String> perList = permissions.stream().map(Permission::getPerCode).collect(Collectors.toList());
        List<MapVo> modules = new ArrayList<>();
        if (permissions.size() > 0) {
            modules.addAll(userExtendMapper.findUserModule(moduleIds));
        }

        // 用于控制前端路由
        loginInfo.setModules(modules);
        // 前端根据权限做细粒度控制
        loginInfo.setPermissions(perList);

        return loginInfo;
    }
}
