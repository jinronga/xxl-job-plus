package cn.jinronga.systembiz.oauth2.object.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@TableName(value = "xxl_job_user")
public class AdminUserDO {


    /**
     * 用户ID
     */
    @TableId
    private Long id;
    /**
     * 用户账号
     */
    private String username;


    /**
     * 加密后的密码
     *
     */
    private String password;



}
