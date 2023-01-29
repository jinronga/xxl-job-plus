package cn.jinronga.systembiz.oauth2.convert;

import cn.jinronga.systembiz.oauth2.object.model.OAuth2AccessTokenDO;
import cn.jinronga.systembiz.oauth2.vo.AuthLoginRespVO;
import cn.jinronga.systembiz.oauth2.vo.OAuth2OpenCheckTokenRespVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-28T21:47:18+0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
public class AuthConvertImpl implements AuthConvert {

    @Override
    public AuthLoginRespVO convert(OAuth2AccessTokenDO accessTokenDO) {
        if ( accessTokenDO == null ) {
            return null;
        }

        AuthLoginRespVO.AuthLoginRespVOBuilder authLoginRespVO = AuthLoginRespVO.builder();

        authLoginRespVO.userId( accessTokenDO.getUserId() );
        authLoginRespVO.accessToken( accessTokenDO.getAccessToken() );
        authLoginRespVO.refreshToken( accessTokenDO.getRefreshToken() );
        authLoginRespVO.expiresTime( accessTokenDO.getExpiresTime() );

        return authLoginRespVO.build();
    }

    @Override
    public OAuth2OpenCheckTokenRespVO convertOAuth2OpenCheckTokenResp(OAuth2AccessTokenDO bean) {
        if ( bean == null ) {
            return null;
        }

        OAuth2OpenCheckTokenRespVO oAuth2OpenCheckTokenRespVO = new OAuth2OpenCheckTokenRespVO();

        oAuth2OpenCheckTokenRespVO.setUserId( bean.getUserId() );
        oAuth2OpenCheckTokenRespVO.setUserType( bean.getUserType() );
        oAuth2OpenCheckTokenRespVO.setClientId( bean.getClientId() );
        List<String> list = bean.getScopes();
        if ( list != null ) {
            oAuth2OpenCheckTokenRespVO.setScopes( new ArrayList<String>( list ) );
        }
        oAuth2OpenCheckTokenRespVO.setAccessToken( bean.getAccessToken() );

        return oAuth2OpenCheckTokenRespVO;
    }
}
