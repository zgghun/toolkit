package zgg.toolkit.system.base;

import org.apache.shiro.authc.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import zgg.toolkit.core.enums.ResultCode;
import zgg.toolkit.core.model.CommonResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理类
 * Created by zgg on 2018/08/27
 */

@ControllerAdvice
@RestController
// 这些异常处理要添加 order，且最优先，否则异常会优先被 GlobalExceptionHandler中的 系统错误 异常捕获
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SystemExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(SystemExceptionHandler.class);


    // 登陆错误
    @ExceptionHandler(AuthenticationException.class)
    public Object accountExceptionHandler(HttpServletRequest req, HttpServletResponse rep, AuthenticationException ex) {
        logger.debug(ex.toString());
        return new CommonResult(ResultCode.LOGIN_ERROR);
    }

}
