package zgg.toolkit.system.model.entity;

import javax.persistence.*;

@Table(name = "sys_permission")
public class Permission {
    @Id
    private Long id;

    /**
     * 顶层模块 pid 为 0
     */
    private Long pid;

    @Column(name = "module_name")
    private String moduleName;

    @Column(name = "modele_code")
    private String modeleCode;

    @Column(name = "module_path")
    private String modulePath;

    @Column(name = "per_name")
    private String perName;

    @Column(name = "per_code")
    private String perCode;

    private String icon;

    private Integer sort;

    /**
     * enable disable
     */
    private String status;

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
     * 获取顶层模块 pid 为 0
     *
     * @return pid - 顶层模块 pid 为 0
     */
    public Long getPid() {
        return pid;
    }

    /**
     * 设置顶层模块 pid 为 0
     *
     * @param pid 顶层模块 pid 为 0
     */
    public void setPid(Long pid) {
        this.pid = pid;
    }

    /**
     * @return module_name
     */
    public String getModuleName() {
        return moduleName;
    }

    /**
     * @param moduleName
     */
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    /**
     * @return modele_code
     */
    public String getModeleCode() {
        return modeleCode;
    }

    /**
     * @param modeleCode
     */
    public void setModeleCode(String modeleCode) {
        this.modeleCode = modeleCode;
    }

    /**
     * @return module_path
     */
    public String getModulePath() {
        return modulePath;
    }

    /**
     * @param modulePath
     */
    public void setModulePath(String modulePath) {
        this.modulePath = modulePath;
    }

    /**
     * @return per_name
     */
    public String getPerName() {
        return perName;
    }

    /**
     * @param perName
     */
    public void setPerName(String perName) {
        this.perName = perName;
    }

    /**
     * @return per_code
     */
    public String getPerCode() {
        return perCode;
    }

    /**
     * @param perCode
     */
    public void setPerCode(String perCode) {
        this.perCode = perCode;
    }

    /**
     * @return icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * @return sort
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * @param sort
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取enable disable
     *
     * @return status - enable disable
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置enable disable
     *
     * @param status enable disable
     */
    public void setStatus(String status) {
        this.status = status;
    }
}