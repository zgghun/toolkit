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
import org.springframework.web.bind.annotation.RestControllerAdvice;
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
 * @date 2018/08/27
 * @author nerve
 */

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private static final String ERROR_PAGE_PREFIX = "/error";

    // 参数绑定错误
    @ExceptionHandler(BindException.class)
    public CommonResult bindExceptionHandler(BindException ex) {
        logger.warn(ex.toString());
        Map<String, String> map = new HashMap<>(16);
        ex.getFieldErrors()
                .forEach(it -> map.put(it.getField(), it.getDefaultMessage()));
        return new CommonResult(ResultCode.ERROR_PARAM_BIND, map);
    }

    @ExceptionHandler(AuthenticationException.class)
    public CommonResult accountExceptionHandler(AuthenticationException ex) {
        logger.warn(ex.toString());
        return new CommonResult(ResultCode.ERROR_LOGIN);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public CommonResult permissionExceptionHandler(UnauthorizedException ex) {
        logger.warn(ex.toString());
        return new CommonResult(ResultCode.UNAUTHORIZED, ex.toString());
    }

    // 自定义通用错误
    @ExceptionHandler(BaseException.class)
    public CommonResult baseExceptionHandler(BaseException ex) {
        logger.error(ex.toString());
        return new CommonResult(ResultCode.ERROR_OTHERS, ex.getMessage());
    }

    // 数据库错误
    @ExceptionHandler(DataAccessException.class)
    public CommonResult sqlExceptionHandler(DataAccessException ex) {
        logger.error(ex.getMessage());
        return new CommonResult(ResultCode.ERROR_DATABASE, ex.getCause().getMessage());
    }

    // 系统错误
    @ExceptionHandler(Exception.class)
    public CommonResult exceptionHandler(Exception ex) {
        logger.error(ex.toString());
        return new CommonResult(ResultCode.ERROR, ex.getMessage());
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
