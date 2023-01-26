package cn.jinronga.systembiz.oauth2.service;


import cn.jinronga.systembiz.oauth2.object.model.OAuth2AccessTokenDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface OAuth2AccessTokenService extends IService<OAuth2AccessTokenDO> {


    /**
     * 创建访问令牌
     * 注意：该流程中，会包含创建刷新令牌的创建
     *
     * 参考 DefaultTokenServices 的 createAccessToken 方法
     *
     * @param userId 用户编号
     * @param userType 用户类型
     * @param clientId 客户端编号
     * @param scopes 授权范围
     * @return 访问令牌的信息
     */
    OAuth2AccessTokenDO createAccessToken(Long userId, Integer userType, List<String> scopes);


}
