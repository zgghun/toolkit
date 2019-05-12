package zgg.toolkit.apitool.mapper.autogen;

import org.apache.ibatis.annotations.Param;
import zgg.toolkit.apitool.model.entity.ApiProject;
import zgg.toolkit.apitool.model.entity.ApiProjectExample;

import java.util.List;

public interface ApiProjectMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table api_project
     *
     * @mbg.generated
     */
    long countByExample(ApiProjectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table api_project
     *
     * @mbg.generated
     */
    int deleteByExample(ApiProjectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table api_project
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table api_project
     *
     * @mbg.generated
     */
    int insert(ApiProject record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table api_project
     *
     * @mbg.generated
     */
    int insertSelective(ApiProject record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table api_project
     *
     * @mbg.generated
     */
    List<ApiProject> selectByExample(ApiProjectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table api_project
     *
     * @mbg.generated
     */
    ApiProject selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table api_project
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") ApiProject record, @Param("example") ApiProjectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table api_project
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") ApiProject record, @Param("example") ApiProjectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table api_project
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ApiProject record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table api_project
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ApiProject record);
}