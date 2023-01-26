package cn.jinronga.common.mybatis.config;


import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import cn.jinronga.common.mybatis.handler.DefaultDBFieldHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@MapperScan(value = "${mybatis-plus.info.base-package}")
public class MybatisCommonConfig {


    @Bean
    public MetaObjectHandler defaultMetaObjectHandler(){
        return new DefaultDBFieldHandler(); // 自动填充参数类
    }
}
