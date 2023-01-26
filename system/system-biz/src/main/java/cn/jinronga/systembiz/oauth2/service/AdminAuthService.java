package cn.jinronga.systembiz.oauth2.service;

import cn.jinronga.systembiz.oauth2.object.model.AdminUserDO;
import cn.jinronga.systembiz.oauth2.vo.AuthLoginReqVO;
import cn.jinronga.systembiz.oauth2.vo.AuthLoginRespVO;

import javax.validation.Valid;

public interface AdminAuthService {


    /**
     * 验证账号 + 密码。如果通过，则返回用户
     *
     * @param username 账号
     * @param password 密码
     * @return 用户
     */
    AdminUserDO authenticate(String username, String password);

    /**
     * 账号登录
     *
     * @param reqVO 登录信息
     * @return 登录结果
     */
    AuthLoginRespVO login(@Valid AuthLoginReqVO reqVO);

}
