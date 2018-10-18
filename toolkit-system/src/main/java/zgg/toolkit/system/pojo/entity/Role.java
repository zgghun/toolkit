package zgg.toolkit.system.pojo.entity;

import javax.persistence.*;

@Table(name = "sys_role")
public class Role {
    @Id
    private Long id;

    private String name;

    /**
     * enable, disable
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
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取enable, disable
     *
     * @return status - enable, disable
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置enable, disable
     *
     * @param status enable, disable
     */
    public void setStatus(String status) {
        this.status = status;
    }
}