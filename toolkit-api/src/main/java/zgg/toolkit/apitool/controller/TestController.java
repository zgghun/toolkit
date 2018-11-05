package zgg.toolkit.apitool.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zgg.toolkit.core.controller.BaseController;

/**
 * Created by zgg on 2018/10/19
 */
@RestController
@RequestMapping("/test")
public class TestController extends BaseController{

    @RequestMapping("")
    public Object test(){
        return commonResult("2333333333333");
    }
}
