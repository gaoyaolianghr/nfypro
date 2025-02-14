package com.nfypro.system.controller;

import com.nfypro.common.core.domain.R;
import com.nfypro.common.core.utils.JwtUtils;
import com.nfypro.common.core.utils.StringUtils;
import com.nfypro.common.core.web.domain.AjaxResult;
import com.nfypro.common.security.auth.AuthUtil;
import com.nfypro.common.security.service.TokenService;
import com.nfypro.common.security.utils.SecurityUtils;
import com.nfypro.system.api.model.LoginUser;
import com.nfypro.system.domain.LoginBody;
import com.nfypro.system.domain.RegisterBody;
import com.nfypro.system.service.impl.SysLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * token 验证码
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/dev-api")
public class CodeController
{
    @Autowired
    private TokenService tokenService;

    @Autowired
    private SysLoginService sysLoginService;

    @GetMapping(value = "/code")
    public AjaxResult getInfo()
    {
        return AjaxResult.success("操作成功");
    }

}
