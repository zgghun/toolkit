package zgg.toolkit.core.exception;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import zgg.toolkit.core.enums.RequestType;
import zgg.toolkit.core.enums.ResultEnum;
import zgg.toolkit.core.model.CommonResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理类
 * Created by zgg on 2018/08/27
 */

@ControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private static final String ERROR_PAGE_PREFIX = "/error";

    @ExceptionHandler(BaseException.class)
    public void baseExceptionHandler(HttpServletRequest req, HttpServletResponse rep, BaseException ex) {
        logger.error(ex.toString());
        CommonResult result = new CommonResult();
        errorDeal(req, rep, result);
    }

    // 参数校验错误
    @ExceptionHandler(BindException.class)
    public void bindingExceptionHandler(HttpServletRequest req, HttpServletResponse rep, BindException ex) {
        logger.error(ex.toString());
        Map<String, String> map = new HashMap<>(16);
        ex.getFieldErrors()
                .forEach(it -> map.put(it.getField(), it.getDefaultMessage()));
        CommonResult result = new CommonResult(ResultEnum.BIND_ERROR, map);
        errorDeal(req, rep, result);
    }

    @ExceptionHandler(Exception.class)
    public void exceptionHandler(HttpServletRequest req, HttpServletResponse rep, Exception ex) {
        CommonResult result = new CommonResult(ResultEnum.FAIL, ex.getMessage());
        logger.error(ex.toString());
        errorDeal(req, rep, result);
    }

    // web请求和ajax请求错误处理
    private void errorDeal(HttpServletRequest req, HttpServletResponse rep, CommonResult result) {
        if (RequestType.WEB == getRequestType(req)) {
            String errorPage = ERROR_PAGE_PREFIX + "/501.html";
            try {
                req.getRequestDispatcher(errorPage).forward(req, rep);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }
        if (RequestType.AJAX == getRequestType(req)) {
            try {
                String str = new Gson().toJson(result);
                rep.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
                rep.getWriter().print(str);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private RequestType getRequestType(HttpServletRequest request) {
        String with = request.getHeader("X-Requested-With");
        if (with == null) {
            return RequestType.WEB;
        }
        if ("XMLHttpRequest".equals(with)) {
            return RequestType.AJAX;
        }
        return RequestType.WEB;
    }
}
