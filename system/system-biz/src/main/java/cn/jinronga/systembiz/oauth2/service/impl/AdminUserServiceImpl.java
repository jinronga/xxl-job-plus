package cn.jinronga.systembiz.oauth2.service.impl;


import cn.jinronga.systembiz.oauth2.object.mapper.AdminUserMapper;
import cn.jinronga.systembiz.oauth2.object.model.AdminUserDO;
import cn.jinronga.systembiz.oauth2.service.AdminUserService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminUserServiceImpl
        extends ServiceImpl<AdminUserMapper, AdminUserDO> implements AdminUserService {


    private final PasswordEncoder passwordEncoder;

    @Override
    public AdminUserDO getUserByUsername(String username) {
        return  this.baseMapper.selectOne(Wrappers
                .<AdminUserDO>lambdaQuery().eq(AdminUserDO::getUsername,username));
    }

    @Override
    public boolean isPasswordMatch(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }


    /**
     * 对密码进行加密
     *
     * @param password 密码
     * @return 加密后的密码
     */
    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
