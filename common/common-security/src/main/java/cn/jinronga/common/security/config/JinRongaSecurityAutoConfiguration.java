package cn.jinronga.common.security.config;

import cn.jinronga.common.security.api.OAuth2TokenApi;
import cn.jinronga.common.security.core.filter.TokenAuthenticationFilter;
import cn.jinronga.common.security.core.filter.handle.GlobalExceptionHandler;
import cn.jinronga.common.security.handle.AuthenticationEntryPointImpl;
import cn.jinronga.common.security.properties.WebProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

import javax.annotation.Resource;

@AutoConfiguration
@EnableConfigurationProperties({SecurityProperties.class, WebProperties.class})
public class JinRongaSecurityAutoConfiguration {

    @Resource
    private SecurityProperties securityProperties;


    /**
     * 应用名
     */
    @Value("${spring.application.name}")
    private String applicationName;

    /**
     * 权限不够处理器 Bean
     */
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new AccessDeniedHandlerImpl();
    }





    /**
     * Spring Security 加密器
     * 考虑到安全性，这里采用 BCryptPasswordEncoder 加密器
     *
     * @see <a href="http://stackabuse.com/password-encoding-with-spring-security/">Password Encoding with Spring Security</a>
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 认证失败处理类 Bean
     */
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new AuthenticationEntryPointImpl();
    }

    /**
     * Token 认证过滤器 Bean
     */
    @Bean
    public TokenAuthenticationFilter authenticationTokenFilter(GlobalExceptionHandler globalExceptionHandler,OAuth2TokenApi auth2TokenApi) {
        return new TokenAuthenticationFilter(globalExceptionHandler,securityProperties,auth2TokenApi);
    }


    @Bean
    public GlobalExceptionHandler globalExceptionHandler() {
        return new GlobalExceptionHandler(applicationName);
    }

}
