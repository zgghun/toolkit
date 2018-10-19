package zgg.toolkit.system.model.entity;

import zgg.toolkit.core.enums.StatusEnum;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Table(name = "sys_user")
public class User {
    @Id
    private Long id;

    /**
     * 登陆用户名，只能由数字、字母、中文组成，不可全数字
     */
    private String username;

    private String tel;

    private String email;

    private String password;

    private String avatar;

    /**
     * 性别 male、female、unknown
     */
    private String gender;

    /**
     * 账号状态 enable、disable、delete
     */
    private StatusEnum status;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    public User(Long id, String username, String tel, String email, String password, String avatar, String gender, StatusEnum status, LocalDateTime createTime, LocalDateTime updateTime) {
        this.id = id;
        this.username = username;
        this.tel = tel;
        this.email = email;
        this.password = password;
        this.avatar = avatar;
        this.gender = gender;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public User() {
        super();
    }

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取登陆用户名，只能由数字、字母、中文组成，不可全数字
     *
     * @return username - 登陆用户名，只能由数字、字母、中文组成，不可全数字
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置登陆用户名，只能由数字、字母、中文组成，不可全数字
     *
     * @param username 登陆用户名，只能由数字、字母、中文组成，不可全数字
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return tel
     */
    public String getTel() {
        return tel;
    }

    /**
     * @param tel
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * @param avatar
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * 获取性别 male、female、unknown
     *
     * @return gender - 性别 male、female、unknown
     */
    public String getGender() {
        return gender;
    }

    /**
     * 设置性别 male、female、unknown
     *
     * @param gender 性别 male、female、unknown
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * 获取账号状态 enable、disable、delete
     *
     * @return status - 账号状态 enable、disable、delete
     */
    public StatusEnum getStatus() {
        return status;
    }

    /**
     * 设置账号状态 enable、disable、delete
     *
     * @param status 账号状态 enable、disable、delete
     */
    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    /**
     * @return create_time
     */
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}