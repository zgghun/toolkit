package zgg.toolkit.apitool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zgg.toolkit.apitool.model.vo.GroupVo;
import zgg.toolkit.apitool.service.ApiTestService;
import zgg.toolkit.core.controller.BaseController;
import zgg.toolkit.core.enums.StatusEnum;
import zgg.toolkit.system.model.dto.DeleteDto;

import java.util.Map;

/**
 * Created by zgg on 2018/10/19
 */
@RestController
@RequestMapping("/apiTest")
public class ApiTestController extends BaseController{
    @Autowired
    private ApiTestService apiTestService;

    @RequestMapping("/")
    public Object findApi(StatusEnum status){
        try {
            Map<String, GroupVo> api = apiTestService.findApi();
            return commonResult(api);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return commonResult();
    }

    @RequestMapping("/233")
    public Object test(DeleteDto dto){
        return commonResult("2333333333333");
    }
}
