package zgg.toolkit.core.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zgg.toolkit.core.bean.BaseResult;
import zgg.toolkit.core.exception.BaseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zgg on 2018/08/27
 */
@RequestMapping("")
@RestController
public class TestController extends BaseController{

    @GetMapping("/500")
    public BaseResult test500() throws Exception {
        throw new BaseException("自定义异常");
    }

    @GetMapping("/404")
    public BaseResult test404(HttpServletRequest req, HttpServletResponse rep) throws IOException, ServletException {
        req.getRequestDispatcher("/notExist").forward(req, rep);
        return null;
    }

    @GetMapping("/201")
    public BaseResult testRedirect(HttpServletResponse rep) throws IOException {
        rep.sendRedirect("/200");
        return null;
    }

    @GetMapping("/200")
    public BaseResult test200() {
        return result("2222");
    }
}
