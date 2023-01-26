package cn.jinronga.systembiz.oauth2.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.jinronga.common.model.constant.GlobalErrorCodeConstants;
import cn.jinronga.common.model.utils.date.DateUtils;
import cn.jinronga.systembiz.oauth2.object.mapper.OAuth2AccessTokenMapper;
import cn.jinronga.systembiz.oauth2.object.mapper.OAuth2RefreshTokenMapper;
import cn.jinronga.systembiz.oauth2.object.model.OAuth2AccessTokenDO;
import cn.jinronga.systembiz.oauth2.object.model.OAuth2RefreshTokenDO;
import cn.jinronga.systembiz.oauth2.service.OAuth2TokenService;
import cn.jinronga.systembiz.oauth2.vo.OAuth2AccessTokenPageReqVO;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static cn.jinronga.common.model.exception.util.ServiceExceptionUtil.exception0;


@Slf4j
@Service
@RequiredArgsConstructor
public class OAuth2TokenServiceImpl implements OAuth2TokenService {


    private final OAuth2RefreshTokenMapper oauth2RefreshTokenMapper;

    private final OAuth2AccessTokenMapper oauth2AccessTokenMapper;

    @Value("${token.refresh.time}")
    private Integer refreshTokenTime;

    @Override
    public OAuth2AccessTokenDO createAccessToken(Long userId, Integer userType, List<String> scopes) {

        OAuth2RefreshTokenDO refreshTokenDO = createOAuth2RefreshToken(userId, userType, scopes);
        // 创建访问令牌
        return createOAuth2AccessToken(refreshTokenDO);
    }

    @Override
    public OAuth2AccessTokenDO refreshAccessToken(String refreshToken, String clientId) {
        return null;
    }

    @Override
    public OAuth2AccessTokenDO getAccessToken(String accessToken) {
//        // 优先从 Redis 中获取
//        OAuth2AccessTokenDO accessTokenDO = redisService.get(accessToken);
//        if (accessTokenDO != null) {
//            return accessTokenDO;
//        }

        // 获取不到，从 MySQL 中获取
        OAuth2AccessTokenDO  accessTokenDO = oauth2AccessTokenMapper.selectOne(Wrappers.<OAuth2AccessTokenDO>
                lambdaQuery().eq(OAuth2AccessTokenDO::getAccessToken,accessToken));
        // 如果在 MySQL 存在，则往 Redis 中写入
        if (accessTokenDO != null && !DateUtils.isExpired(accessTokenDO.getExpiresTime())) {
//            oauth2AccessTokenRedisDAO.set(accessTokenDO);
        }
        return accessTokenDO;
    }

    @Override
    public OAuth2AccessTokenDO checkAccessToken(String accessToken) {
        OAuth2AccessTokenDO accessTokenDO = getAccessToken(accessToken);
        if (accessTokenDO == null) {
            throw exception0(GlobalErrorCodeConstants.UNAUTHORIZED.getCode(), "访问令牌不存在");
        }
        if (DateUtils.isExpired(accessTokenDO.getExpiresTime())) {
            throw exception0(GlobalErrorCodeConstants.UNAUTHORIZED.getCode(), "访问令牌已过期");
        }
        return accessTokenDO;
    }

    @Override
    public OAuth2AccessTokenDO removeAccessToken(String accessToken) {
        return null;
    }

    @Override
    public Page<OAuth2AccessTokenDO> getAccessTokenPage(OAuth2AccessTokenPageReqVO reqVO) {
        return null;
    }


    private OAuth2RefreshTokenDO createOAuth2RefreshToken(Long userId, Integer userType, List<String> scopes) {
        OAuth2RefreshTokenDO refreshToken = new OAuth2RefreshTokenDO().setRefreshToken(IdUtil.fastSimpleUUID())
                .setUserId(userId)
                .setUserType(userType)
                .setScopes(scopes)
                .setExpiresTime(LocalDateTime.now().plusSeconds(refreshTokenTime));
        oauth2RefreshTokenMapper.insert(refreshToken);
        return refreshToken;
    }

    private OAuth2AccessTokenDO createOAuth2AccessToken(OAuth2RefreshTokenDO refreshTokenDO) {
        OAuth2AccessTokenDO accessTokenDO = new OAuth2AccessTokenDO().setAccessToken(IdUtil.fastSimpleUUID())
                .setUserId(refreshTokenDO.getUserId()).setUserType(refreshTokenDO.getUserType())
                .setScopes(refreshTokenDO.getScopes())
                .setRefreshToken(refreshTokenDO.getRefreshToken())
                .setExpiresTime(LocalDateTime.now().plusSeconds(refreshTokenTime));
        oauth2AccessTokenMapper.insert(accessTokenDO);
        // TODO 记录到 Redis 中
        return accessTokenDO;
    }

}
