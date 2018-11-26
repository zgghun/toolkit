package zgg.toolkit.apitool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zgg.toolkit.apitool.model.dto.DetailSaveDto;
import zgg.toolkit.apitool.model.dto.GroupSaveDto;
import zgg.toolkit.apitool.model.dto.HistoryAddDto;
import zgg.toolkit.apitool.model.dto.ProjectSaveDto;
import zgg.toolkit.apitool.model.entity.ApiDetail;
import zgg.toolkit.apitool.model.entity.ApiGroup;
import zgg.toolkit.apitool.model.entity.ApiHistory;
import zgg.toolkit.apitool.model.entity.ApiProject;
import zgg.toolkit.apitool.model.vo.ApiVo;
import zgg.toolkit.apitool.service.ApiService;
import zgg.toolkit.core.controller.BaseController;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by zgg on 2018/11/26
 */
@RestController
@RequestMapping("/apiTool")
public class ApiController extends BaseController{
    @Autowired
    private ApiService apiService;

    // 所有api接口
    @GetMapping("/allApi")
    public Object findAllApi(@RequestParam Long projectId){
        List<ApiVo> allApi = apiService.findAllApi(projectId);
        return commonResult(allApi);
    }

    // api接口历史，由projectId和address组合的md5区分
    @GetMapping("/apiHistory")
    public Object findApiHistory(@RequestParam Long projectId, @RequestParam String address){
        List<ApiHistory> apiHistory = apiService.findApiHistory(projectId, address);
        return commonResult(apiHistory);
    }

    // 添加/修改工程
    @PostMapping("/addProject")
    public Object addProject(@Valid ProjectSaveDto dto){
        ApiProject apiProject = apiService.saveProject(dto);
        return commonResult(apiProject);
    }

    // 添加/修改组
    @PostMapping("/addGroup")
    public Object addGroup(@Valid GroupSaveDto dto){
        ApiGroup group = apiService.saveGroup(dto);
        return commonResult(group);
    }

    // 添加/修改api
    @PostMapping("/addApi")
    public Object saveApi(@Valid DetailSaveDto dto){
        ApiDetail detail = apiService.saveApi(dto);
        return commonResult(detail);
    }

    // 添加api历史
    @PostMapping("/addHistory")
    public Object addApiHistory(@Valid HistoryAddDto dto){
        ApiHistory history = apiService.addApiHistory(dto);
        return commonResult(history);
    }
}
