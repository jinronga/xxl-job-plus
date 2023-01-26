package cn.jinronga.systembiz.oauth2.service.impl;

import cn.jinronga.common.model.enums.UserTypeEnum;
import cn.jinronga.systembiz.oauth2.convert.AuthConvert;
import cn.jinronga.systembiz.oauth2.enums.LoginLogTypeEnum;
import cn.jinronga.systembiz.oauth2.enums.LoginResultEnum;
import cn.jinronga.systembiz.oauth2.object.model.AdminUserDO;
import cn.jinronga.systembiz.oauth2.object.model.OAuth2AccessTokenDO;
import cn.jinronga.systembiz.oauth2.service.AdminAuthService;
import cn.jinronga.systembiz.oauth2.service.AdminUserService;
import cn.jinronga.systembiz.oauth2.service.OAuth2TokenService;
import cn.jinronga.systembiz.oauth2.vo.AuthLoginReqVO;
import cn.jinronga.systembiz.oauth2.vo.AuthLoginRespVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static cn.jinronga.common.model.constant.ErrorCodeConstants.AUTH_LOGIN_BAD_CREDENTIALS;
import static cn.jinronga.common.model.exception.util.ServiceExceptionUtil.exception;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminAuthServiceImpl implements AdminAuthService {


    private final AdminUserService adminUserService;

    private final OAuth2TokenService oauth2TokenService;


    @Override
    public AdminUserDO authenticate(String username, String password) {
        final LoginLogTypeEnum logTypeEnum = LoginLogTypeEnum.LOGIN_USERNAME;
        AdminUserDO user = adminUserService.getUserByUsername(username);

        if (user == null) {
            throw exception(AUTH_LOGIN_BAD_CREDENTIALS);
        }

        if (!adminUserService.isPasswordMatch(password, user.getPassword())) {
            throw exception(AUTH_LOGIN_BAD_CREDENTIALS);
        }

        if (!adminUserService.isPasswordMatch(password, user.getPassword())) {
            throw exception(AUTH_LOGIN_BAD_CREDENTIALS);
        }
        return user;
    }

    @Override
    public AuthLoginRespVO login(AuthLoginReqVO reqVO) {

        // 使用账号密码，进行登录
        AdminUserDO user = authenticate(reqVO.getUsername(), reqVO.getPassword());


        // 创建 Token 令牌，记录登录日志
        return createTokenAfterLoginSuccess(user.getId(), reqVO.getUsername(), LoginLogTypeEnum.LOGIN_USERNAME);
    }

    private AuthLoginRespVO createTokenAfterLoginSuccess(Long userId, String username, LoginLogTypeEnum logType) {
        // 创建访问令牌
        OAuth2AccessTokenDO accessTokenDO = oauth2TokenService.createAccessToken(userId, getUserType().getValue(),
              null);
        // 构建返回结果
        return AuthConvert.INSTANCE.convert(accessTokenDO);
    }


    private UserTypeEnum getUserType() {
        return UserTypeEnum.ADMIN;
    }

}
