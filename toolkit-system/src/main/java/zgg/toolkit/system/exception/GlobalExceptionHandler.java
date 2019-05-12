package zgg.toolkit.system.exception;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.MediaType;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import zgg.toolkit.common.util.JsonUtils;
import zgg.toolkit.system.base.BaseException;
import zgg.toolkit.system.enums.RequestType;
import zgg.toolkit.system.enums.ResultCode;
import zgg.toolkit.system.model.common.CommonResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理类
 * Created by zgg on 2018/08/27
 * 可使用@RestControllerAdvice 直接返回 json 对象
 */

@ControllerAdvice
public class GlobalExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private static final String ERROR_PAGE_PREFIX = "/error";

    // 参数绑定错误
    @ExceptionHandler(BindException.class)
    public void bindExceptionHandler(HttpServletRequest req, HttpServletResponse rep, BindException ex) {
        logger.warn(ex.toString());
        Map<String, String> map = new HashMap<>(16);
        ex.getFieldErrors()
                .forEach(it -> map.put(it.getField(), it.getDefaultMessage()));
        CommonResult result = new CommonResult(ResultCode.PARAM_BIND_ERROR, map);
        errorDeal(req, rep, result);
    }

    @ExceptionHandler(AuthenticationException.class)
    public Object accountExceptionHandler(HttpServletRequest req, HttpServletResponse rep, AuthenticationException ex) {
        logger.warn(ex.toString());
        return new CommonResult(ResultCode.LOGIN_ERROR);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public Object permissionExceptionHandler(UnauthorizedException ex) {
        logger.warn(ex.toString());
        return new CommonResult(ResultCode.UNAUTHORIZED, ex.toString());
    }

    // 自定义通用错误
    @ExceptionHandler(BaseException.class)
    public void baseExceptionHandler(HttpServletRequest req, HttpServletResponse rep, BaseException ex) {
        logger.error(ex.toString());
        CommonResult result = new CommonResult(ResultCode.BASE_ERROR, ex.getMessage());
        errorDeal(req, rep, result);
    }

    // 数据库错误
    @ExceptionHandler(DataAccessException.class)
    public void sqlExceptionHandler(HttpServletRequest req, HttpServletResponse rep, DataAccessException ex) {
        logger.error(ex.getMessage());
        CommonResult result = new CommonResult(ResultCode.DATABASE_ERROR, ex.getCause().getMessage());
        errorDeal(req, rep, result);
    }

    // 系统错误
    @ExceptionHandler(Exception.class)
    public void exceptionHandler(HttpServletRequest req, HttpServletResponse rep, Exception ex) {
        logger.error(ex.toString());
        CommonResult result = new CommonResult(ResultCode.ERROR, ex.getMessage());
        errorDeal(req, rep, result);
    }


    // web请求和ajax请求错误处理
    protected void errorDeal(HttpServletRequest req, HttpServletResponse rep, CommonResult result) {
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
                String str = JsonUtils.toJson(result);
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
