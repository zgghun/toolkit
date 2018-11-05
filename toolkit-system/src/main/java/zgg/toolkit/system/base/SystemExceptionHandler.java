package zgg.toolkit.system.base;

import org.apache.shiro.authc.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import zgg.toolkit.core.enums.ResultCode;
import zgg.toolkit.core.exception.GlobalExceptionHandler;
import zgg.toolkit.core.model.CommonResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理类
 * Created by zgg on 2018/08/27
 */

@ControllerAdvice
// 这些异常处理要添加 order，且最优先属性，否则异常会优先被 GlobalExceptionHandler中的 系统错误 异常捕获
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SystemExceptionHandler extends GlobalExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(SystemExceptionHandler.class);


    // 登陆错误
    @ExceptionHandler(AuthenticationException.class)
    public void accountExceptionHandler(HttpServletRequest req, HttpServletResponse rep, AuthenticationException ex) {
        logger.error(ex.toString());
        CommonResult result = new CommonResult(ResultCode.LOGIN_ERROR);
        errorDeal(req, rep, result);
    }

}
