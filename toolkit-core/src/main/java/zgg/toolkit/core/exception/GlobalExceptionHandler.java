package zgg.toolkit.core.exception;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import zgg.toolkit.core.bean.CommonResult;
import zgg.toolkit.core.enums.RequestType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 全局异常处理类
 * Created by zgg on 2018/08/27
 */

@ControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private static final String ERROR_PAGE_PREFIX = "/error";

    @ExceptionHandler(value = Exception.class)
    public void exceptionHandler(HttpServletRequest req, HttpServletResponse rep, Exception ex) {
        CommonResult result = new CommonResult();

        if (ex instanceof BaseException) {
            result = new CommonResult(2, "error", ex.getMessage());
        }
        result= new CommonResult(3, "error", ex.getMessage());
        logger.error(ex.toString());


        // 普通web请求，发生异常跳转错误页面
        if (RequestType.WEB.equals(getRequestType(req))) {
            String errorPage = ERROR_PAGE_PREFIX + "/501.html";
            try {
                req.getRequestDispatcher(errorPage).forward(req, rep);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }
        // ajax请求，发生异常返回 json 数据
        if (RequestType.AJAX.equals(getRequestType(req))) {
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
