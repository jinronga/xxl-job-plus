package cn.jinronga.common.security.api;


import cn.hutool.http.HttpUtil;
import cn.jinronga.common.model.pojo.R;
import cn.jinronga.common.model.utils.json.JsonUtils;
import cn.jinronga.common.security.constant.ApiConstants;
import cn.jinronga.common.security.dto.OAuth2AccessTokenCheckRespDTO;
import cn.jinronga.common.security.dto.OAuth2AccessTokenRespDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class OAuth2TokenApi {

    String PREFIX =  "/system/auth/check-token";


    //token服务url
    @Value("${token.token-url}")
    private String oauthUrl;


   public R<OAuth2AccessTokenCheckRespDTO> checkAccessToken(String token){
       String res = HttpUtil.get(oauthUrl + PREFIX);
       OAuth2AccessTokenCheckRespDTO  accessTokenRespDTO = JsonUtils.parseObject(res, OAuth2AccessTokenCheckRespDTO.class);
       return R.success(accessTokenRespDTO);
   }
}
