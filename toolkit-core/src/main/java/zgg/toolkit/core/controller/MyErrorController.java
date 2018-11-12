package zgg.toolkit.core.controller;

import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import zgg.toolkit.core.enums.ResultCode;
import zgg.toolkit.core.model.CommonResult;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Created by zgg on 2018/11/10
 *  覆盖spring自带的BasicErrorController，自定义404，500等错误
 */
@RestController
@RequestMapping("/error")
public class MyErrorController extends AbstractErrorController {


    @RequestMapping
    @ResponseBody
    public Object error(HttpServletRequest request) {
        WebRequest webRequest = new ServletWebRequest(request);
        String path = (String) webRequest.getAttribute("javax.servlet.error.request_uri", RequestAttributes.SCOPE_REQUEST);
        HttpStatus status = getStatus(request);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("status", status.value());
        map.put("path", path);
        return new CommonResult(ResultCode.PATH_ERROR, map);
    }


    public MyErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
