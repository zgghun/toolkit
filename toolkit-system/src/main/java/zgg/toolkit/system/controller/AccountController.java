package zgg.toolkit.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zgg.toolkit.system.base.SystemBaseController;
import zgg.toolkit.system.model.dto.LoginDto;
import zgg.toolkit.system.service.AccountService;

import javax.validation.Valid;

/**
 * Created by zgg on 2018/10/26
 */
@RestController
@RequestMapping("/sys/account")
public class AccountController extends SystemBaseController {
    @Autowired
    private AccountService accountService;

    @RequestMapping("/login")
    public Object login(@Valid LoginDto dto){
        return null;
    }


}
