package zgg.toolkit.apitool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zgg.toolkit.apitool.model.vo.GroupVo;
import zgg.toolkit.apitool.service.ApiTestService;
import zgg.toolkit.system.base.BaseController;
import zgg.toolkit.system.model.dto.DeleteDto;

import java.util.List;

/**
 * Created by zgg on 2018/10/19
 */
@RestController
@RequestMapping(value = "/apiTest", name = "api测试")
public class ApiTestController extends BaseController {
    @Autowired
    private ApiTestService apiTestService;

    @RequestMapping(value = "", name = "获取所有api")
    public Object findApi() {
        List<GroupVo> api = apiTestService.findApi();
        return commonResult(api);
    }

    @RequestMapping(value = "/233", name = "测试222")
    public Object test(@RequestBody DeleteDto dto) {
        return commonResult("2333333333333");
    }
}
