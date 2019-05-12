package zgg.toolkit.apitool.model.entity;

import java.time.LocalDateTime;

/**
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table api_history
 */
public class ApiHistory {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column api_history.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * Database Column Remarks:
     * api的特征值，用于区分是那个api，由projectId和address组合取md5值
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column api_history.api_md5
     *
     * @mbg.generated
     */
    private String apiMd5;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column api_history.header
     *
     * @mbg.generated
     */
    private String header;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column api_history.body
     *
     * @mbg.generated
     */
    private String body;

    /**
     * Database Column Remarks:
     * header和body组合的md5值，判断接口是否有修改
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column api_history.payload_md5
     *
     * @mbg.generated
     */
    private String payloadMd5;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column api_history.create_time
     *
     * @mbg.generated
     */
    private LocalDateTime createTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table api_history
     *
     * @mbg.generated
     */
    public ApiHistory(Long id, String apiMd5, String header, String body, String payloadMd5, LocalDateTime createTime) {
        this.id = id;
        this.apiMd5 = apiMd5;
        this.header = header;
        this.body = body;
        this.payloadMd5 = payloadMd5;
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table api_history
     *
     * @mbg.generated
     */
    public ApiHistory() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column api_history.id
     *
     * @return the value of api_history.id
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column api_history.id
     *
     * @param id the value for api_history.id
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column api_history.api_md5
     *
     * @return the value of api_history.api_md5
     * @mbg.generated
     */
    public String getApiMd5() {
        return apiMd5;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column api_history.api_md5
     *
     * @param apiMd5 the value for api_history.api_md5
     * @mbg.generated
     */
    public void setApiMd5(String apiMd5) {
        this.apiMd5 = apiMd5 == null ? null : apiMd5.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column api_history.header
     *
     * @return the value of api_history.header
     * @mbg.generated
     */
    public String getHeader() {
        return header;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column api_history.header
     *
     * @param header the value for api_history.header
     * @mbg.generated
     */
    public void setHeader(String header) {
        this.header = header == null ? null : header.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column api_history.body
     *
     * @return the value of api_history.body
     * @mbg.generated
     */
    public String getBody() {
        return body;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column api_history.body
     *
     * @param body the value for api_history.body
     * @mbg.generated
     */
    public void setBody(String body) {
        this.body = body == null ? null : body.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column api_history.payload_md5
     *
     * @return the value of api_history.payload_md5
     * @mbg.generated
     */
    public String getPayloadMd5() {
        return payloadMd5;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column api_history.payload_md5
     *
     * @param payloadMd5 the value for api_history.payload_md5
     * @mbg.generated
     */
    public void setPayloadMd5(String payloadMd5) {
        this.payloadMd5 = payloadMd5 == null ? null : payloadMd5.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column api_history.create_time
     *
     * @return the value of api_history.create_time
     * @mbg.generated
     */
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column api_history.create_time
     *
     * @param createTime the value for api_history.create_time
     * @mbg.generated
     */
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}