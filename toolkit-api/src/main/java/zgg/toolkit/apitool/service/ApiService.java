package zgg.toolkit.apitool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zgg.toolkit.apitool.mapper.autogen.ApiDetailMapper;
import zgg.toolkit.apitool.mapper.autogen.ApiGroupMapper;
import zgg.toolkit.apitool.mapper.autogen.ApiHistoryMapper;
import zgg.toolkit.apitool.mapper.autogen.ApiProjectMapper;
import zgg.toolkit.apitool.model.dto.DetailSaveDto;
import zgg.toolkit.apitool.model.dto.GroupSaveDto;
import zgg.toolkit.apitool.model.dto.HistoryAddDto;
import zgg.toolkit.apitool.model.dto.ProjectSaveDto;
import zgg.toolkit.apitool.model.entity.*;
import zgg.toolkit.apitool.model.vo.ApiVo;
import zgg.toolkit.common.util.HelpUtils;
import zgg.toolkit.common.util.IdWorker;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zgg on 2018/11/26
 */
@Service
public class ApiService {
    @Autowired
    private ApiProjectMapper projectMapper;
    @Autowired
    private ApiGroupMapper groupMapper;
    @Autowired
    private ApiDetailMapper detailMapper;
    @Autowired
    private ApiHistoryMapper historyMapper;


    /**
     * 项目下所有api
     * @param projectId
     * @return
     */
    public List<ApiVo> findAllApi(Long projectId) {
        ApiGroupExample ep1 = new ApiGroupExample();
        ep1.or().andProjectIdEqualTo(projectId);
        List<ApiGroup> groups = groupMapper.selectByExample(ep1);
        ApiDetailExample ep2 = new ApiDetailExample();
        ep2.or().andProjectIdEqualTo(projectId);
        List<ApiDetail> details = detailMapper.selectByExample(ep2);

        List<ApiVo> voList = new ArrayList<>();
        List<ApiDetail> used = new ArrayList<>();
        for (ApiGroup group : groups) {
            List<ApiDetail> find = details.stream()
                    .filter(it -> it.getGroupId().equals(group.getId()))
                    .collect(Collectors.toList());
            used.addAll(find);
            ApiVo vo = new ApiVo();
            vo.setGroup(group);
            vo.setApiDetails(find);
            voList.add(vo);
        }
        // 没有分组的api
        details.removeAll(used);
        ApiVo apiVo = new ApiVo();
        apiVo.setGroup(new ApiGroup(0L, projectId, "未分组", 0));
        apiVo.setApiDetails(details);
        voList.add(apiVo);
        return voList;
    }

    public List<ApiHistory> findApiHistory(Long projectId, String address) {
        String apiMd5 = HelpUtils.md5(projectId + address);
        ApiHistoryExample example = new ApiHistoryExample();
        example.setOrderByClause("create_time DESC LIMIT 10");
        example.or().andApiMd5EqualTo(apiMd5);
        List<ApiHistory> histories = historyMapper.selectByExample(example);
        return histories;
    }

    public ApiProject saveProject(ProjectSaveDto dto) {
        ApiProject project = new ApiProject();
        HelpUtils.copyProperties(dto, project);
        if (dto.getId() == null) {
            project.setId(IdWorker.nextId());
            project.setCreateTime(LocalDateTime.now());
            projectMapper.insert(project);
        } else {
            projectMapper.updateByPrimaryKeySelective(project);
        }
        return project;
    }

    public ApiGroup saveGroup(@Valid GroupSaveDto dto) {
        ApiGroup group = new ApiGroup();
        HelpUtils.copyProperties(dto, group);
        if (dto.getId() == null) {
            group.setId(IdWorker.nextId());
            groupMapper.insert(group);
        } else {
            groupMapper.updateByPrimaryKey(group);
        }
        return group;
    }

    public ApiDetail saveApi(@Valid DetailSaveDto dto) {
        ApiDetail detail = new ApiDetail();
        HelpUtils.copyProperties(dto, detail);
        if (detail.getId() == null) {
            detail.setId(IdWorker.nextId());
            detail.setCreateTime(LocalDateTime.now());
            detail.setUpdateTime(LocalDateTime.now());
        } else {
            detailMapper.updateByPrimaryKeySelective(detail);
        }
        return detail;
    }

    /**
     * 添加历史纪录，用projectId和address的MD5作为唯一判断，header和body的mod5判断是否有修改
     * @param dto
     * @return
     */
    public ApiHistory addApiHistory(HistoryAddDto dto) {
        String apiMd5 = HelpUtils.md5(dto.getProjectId() + dto.getAddress());
        String payloadMd5 = HelpUtils.md5(dto.getHeader() + dto.getBody());
        ApiHistoryExample example = new ApiHistoryExample();
        example.or().andApiMd5EqualTo(apiMd5).andPayloadMd5EqualTo(payloadMd5);
        List<ApiHistory> list = historyMapper.selectByExample(example);
        // 新纪录
        if (list.size() == 0){
            ApiHistory history = new ApiHistory();
            history.setId(IdWorker.nextId());
            history.setApiMd5(apiMd5);
            history.setHeader(dto.getHeader());
            history.setBody(dto.getBody());
            history.setPayloadMd5(payloadMd5);
            history.setCreateTime(LocalDateTime.now());
            historyMapper.insert(history);
            return history;
        }
        return null;
    }
}
