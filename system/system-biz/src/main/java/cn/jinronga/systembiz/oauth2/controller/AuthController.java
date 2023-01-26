package cn.jinronga.systembiz.oauth2.controller;

import cn.hutool.core.lang.Assert;
import cn.jinronga.common.model.pojo.R;
import cn.jinronga.systembiz.oauth2.convert.AuthConvert;
import cn.jinronga.systembiz.oauth2.object.model.OAuth2AccessTokenDO;
import cn.jinronga.systembiz.oauth2.service.AdminAuthService;
import cn.jinronga.systembiz.oauth2.service.OAuth2TokenService;
import cn.jinronga.systembiz.oauth2.vo.AuthLoginReqVO;
import cn.jinronga.systembiz.oauth2.vo.AuthLoginRespVO;
import cn.jinronga.systembiz.oauth2.vo.OAuth2OpenCheckTokenRespVO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static cn.jinronga.common.model.pojo.R.success;


@RequiredArgsConstructor
@RequestMapping("/system/auth")
@Validated
@Slf4j
@RestController
public class AuthController {
    private final AdminAuthService authService;

    private final OAuth2TokenService oAuth2TokenService;

    @PostMapping("/login")
    @PermitAll
    @ApiOperation("使用账号密码登录")
    public R<AuthLoginRespVO> login(@RequestBody @Valid AuthLoginReqVO reqVO) {
        return success(authService.login(reqVO));
    }


    /**
     * 对应 Spring Security OAuth 的 CheckTokenEndpoint 类的 checkToken 方法
     */
    @PostMapping("/check-token")
    @PermitAll
    @ApiOperation(value = "校验访问令牌")
    @ApiImplicitParam(name = "token", required = true, value = "访问令牌", example = "biu", dataTypeClass = String.class)
    public R<OAuth2OpenCheckTokenRespVO> checkToken(HttpServletRequest request,
                                                               @RequestParam("token") String token) {


        // 校验令牌
        OAuth2AccessTokenDO accessTokenDO = oAuth2TokenService.checkAccessToken(token);
        Assert.notNull(accessTokenDO, "访问令牌不能为空"); // 防御性检查
        return success(AuthConvert.INSTANCE.convertOAuth2OpenCheckTokenResp2(accessTokenDO));
    }
}
