package zgg.toolkit.core.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import zgg.toolkit.core.bean.CommonResult;
import zgg.toolkit.core.bean.Student;
import zgg.toolkit.core.bean.User;
import zgg.toolkit.core.exception.BaseException;
import zgg.toolkit.core.page.PageList;
import zgg.toolkit.core.page.PageParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by zgg on 2018/08/27
 */
@RequestMapping("")
@RestController
public class TestController extends BaseController{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/page2")
    public CommonResult testBody(@RequestBody Student user){
        logger.info(user.toString());
        return commonResult();
    }


    @GetMapping("/page")
    public CommonResult testPage(PageParam page){
        User user = new User(999999999999999999L, "AAA", 17, LocalDateTime.now(), LocalDate.now(), LocalTime.now(), new Date());
        List<User> users = Arrays.asList(
                user,
                new User()
        );
        PageList<User> pageList = new PageList<>(users, 20, page);
        return commonResult(pageList);
    }

    @GetMapping("/500")
    public CommonResult test500() throws Exception {
        throw new BaseException("自定义异常");
    }

    @GetMapping("/404")
    public CommonResult test404(HttpServletRequest req, HttpServletResponse rep) throws IOException, ServletException {
        req.getRequestDispatcher("/notExist").forward(req, rep);
        return null;
    }

    @GetMapping("/201")
    public CommonResult testRedirect(HttpServletResponse rep) throws IOException {
        rep.sendRedirect("/200");
        return null;
    }

    @GetMapping("/200")
    public CommonResult test200() {
        return commonResult("2222");
    }
}
