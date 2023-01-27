package cn.jinronga.systembiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"cn.jinronga.common.security.api"})
public class SystemBizApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemBizApplication.class, args);
    }

}
