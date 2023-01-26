package cn.jinronga.systembiz.oauth2.service;


import cn.jinronga.systembiz.oauth2.object.model.AdminUserDO;
import com.baomidou.mybatisplus.extension.service.IService;

public interface AdminUserService extends IService<AdminUserDO> {

    /**
     * 通过用户名查询用户
     *
     * @param username 用户名
     * @return 用户对象信息
     */
    AdminUserDO getUserByUsername(String username);


    /**
     * 判断密码是否匹配
     *
     * @param rawPassword 未加密的密码
     * @param encodedPassword 加密后的密码
     * @return 是否匹配
     */
    boolean isPasswordMatch(String rawPassword, String encodedPassword);




}
