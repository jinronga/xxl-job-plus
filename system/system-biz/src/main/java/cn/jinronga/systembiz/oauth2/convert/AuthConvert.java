package cn.jinronga.systembiz.oauth2.convert;


import cn.hutool.core.date.LocalDateTimeUtil;
import cn.jinronga.common.model.enums.UserTypeEnum;
import cn.jinronga.systembiz.oauth2.object.model.OAuth2AccessTokenDO;
import cn.jinronga.systembiz.oauth2.vo.AuthLoginRespVO;
import cn.jinronga.systembiz.oauth2.vo.OAuth2OpenCheckTokenRespVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthConvert {

    AuthConvert INSTANCE = Mappers.getMapper(AuthConvert.class);


    AuthLoginRespVO convert(OAuth2AccessTokenDO accessTokenDO);

    OAuth2OpenCheckTokenRespVO convertOAuth2OpenCheckTokenResp(OAuth2AccessTokenDO bean);


    default OAuth2OpenCheckTokenRespVO convertOAuth2OpenCheckTokenResp2(OAuth2AccessTokenDO bean) {
        OAuth2OpenCheckTokenRespVO respVO = convertOAuth2OpenCheckTokenResp(bean);
        respVO.setExp(LocalDateTimeUtil.toEpochMilli(bean.getExpiresTime()) / 1000L);
        respVO.setUserType(UserTypeEnum.ADMIN.getValue());
        return respVO;
    }

}
