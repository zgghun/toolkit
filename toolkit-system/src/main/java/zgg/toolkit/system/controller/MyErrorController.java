package zgg.toolkit.system.controller;

import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import zgg.toolkit.system.enums.ResultCode;
import zgg.toolkit.system.model.common.CommonResult;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Created by zgg on 2018/11/10
 * 覆盖spring自带的BasicErrorController，自定义404，500等错误
 */
//@RestController
//@RequestMapping("/error")
public class MyErrorController extends AbstractErrorController {


    // 请求地址不存在（暂时解决办法，应该直接用全局异常来处理）
    @RequestMapping
    public Object error(HttpServletRequest request) {
        WebRequest webRequest = new ServletWebRequest(request);
        String path = (String) webRequest.getAttribute("javax.servlet.error.request_uri", RequestAttributes.SCOPE_REQUEST);
        HttpStatus status = getStatus(request);
        HashMap<Object, Object> map = new HashMap<>(16);
        map.put("status", status.value());
        map.put("uri", path);
        return new CommonResult(ResultCode.ERROR_URI_NOT_FOUND, map);
    }


    public MyErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
