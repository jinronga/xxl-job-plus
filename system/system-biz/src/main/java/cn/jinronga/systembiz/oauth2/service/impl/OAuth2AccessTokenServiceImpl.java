package cn.jinronga.systembiz.oauth2.service.impl;

import cn.jinronga.systembiz.oauth2.object.model.OAuth2AccessTokenDO;
import cn.jinronga.systembiz.oauth2.object.mapper.OAuth2AccessTokenMapper;
import cn.jinronga.systembiz.oauth2.service.OAuth2AccessTokenService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OAuth2AccessTokenServiceImpl extends ServiceImpl<OAuth2AccessTokenMapper, OAuth2AccessTokenDO> implements OAuth2AccessTokenService {




    @Override
    public OAuth2AccessTokenDO createAccessToken(Long userId, Integer userType, List<String> scopes) {




        return null;
    }
}
