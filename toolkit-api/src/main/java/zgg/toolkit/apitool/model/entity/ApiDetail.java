package zgg.toolkit.apitool.model.entity;

import java.time.LocalDateTime;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table api_detail
 */
public class ApiDetail {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column api_detail.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column api_detail.project_id
     *
     * @mbg.generated
     */
    private Long projectId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column api_detail.group_id
     *
     * @mbg.generated
     */
    private Long groupId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column api_detail.address
     *
     * @mbg.generated
     */
    private String address;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column api_detail.header
     *
     * @mbg.generated
     */
    private String header;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column api_detail.body
     *
     * @mbg.generated
     */
    private String body;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column api_detail.sort
     *
     * @mbg.generated
     */
    private Integer sort;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column api_detail.create_time
     *
     * @mbg.generated
     */
    private LocalDateTime createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column api_detail.update_time
     *
     * @mbg.generated
     */
    private LocalDateTime updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table api_detail
     *
     * @mbg.generated
     */
    public ApiDetail(Long id, Long projectId, Long groupId, String address, String header, String body, Integer sort, LocalDateTime createTime, LocalDateTime updateTime) {
        this.id = id;
        this.projectId = projectId;
        this.groupId = groupId;
        this.address = address;
        this.header = header;
        this.body = body;
        this.sort = sort;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table api_detail
     *
     * @mbg.generated
     */
    public ApiDetail() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column api_detail.id
     *
     * @return the value of api_detail.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column api_detail.id
     *
     * @param id the value for api_detail.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column api_detail.project_id
     *
     * @return the value of api_detail.project_id
     *
     * @mbg.generated
     */
    public Long getProjectId() {
        return projectId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column api_detail.project_id
     *
     * @param projectId the value for api_detail.project_id
     *
     * @mbg.generated
     */
    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column api_detail.group_id
     *
     * @return the value of api_detail.group_id
     *
     * @mbg.generated
     */
    public Long getGroupId() {
        return groupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column api_detail.group_id
     *
     * @param groupId the value for api_detail.group_id
     *
     * @mbg.generated
     */
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column api_detail.address
     *
     * @return the value of api_detail.address
     *
     * @mbg.generated
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column api_detail.address
     *
     * @param address the value for api_detail.address
     *
     * @mbg.generated
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column api_detail.header
     *
     * @return the value of api_detail.header
     *
     * @mbg.generated
     */
    public String getHeader() {
        return header;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column api_detail.header
     *
     * @param header the value for api_detail.header
     *
     * @mbg.generated
     */
    public void setHeader(String header) {
        this.header = header == null ? null : header.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column api_detail.body
     *
     * @return the value of api_detail.body
     *
     * @mbg.generated
     */
    public String getBody() {
        return body;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column api_detail.body
     *
     * @param body the value for api_detail.body
     *
     * @mbg.generated
     */
    public void setBody(String body) {
        this.body = body == null ? null : body.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column api_detail.sort
     *
     * @return the value of api_detail.sort
     *
     * @mbg.generated
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column api_detail.sort
     *
     * @param sort the value for api_detail.sort
     *
     * @mbg.generated
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column api_detail.create_time
     *
     * @return the value of api_detail.create_time
     *
     * @mbg.generated
     */
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column api_detail.create_time
     *
     * @param createTime the value for api_detail.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column api_detail.update_time
     *
     * @return the value of api_detail.update_time
     *
     * @mbg.generated
     */
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column api_detail.update_time
     *
     * @param updateTime the value for api_detail.update_time
     *
     * @mbg.generated
     */
    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}