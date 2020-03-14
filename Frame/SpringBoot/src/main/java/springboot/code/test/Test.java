package springboot.code.test;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * @author NotUpToYou
 * @date 2020/3/8 周日 14:58
 */
@Component
@EnableConfigurationProperties(User.class)
public class Test {

    /**
     * 不加 @Bean 属性注入会失败
     * @return Bean User
     */
    @Bean
    public SystemUser getSystemUser(User user) {
        SystemUser systemUser = new SystemUser();
        systemUser.setId(user.getId());
        systemUser.setName(user.getName());
        return systemUser;
    }

}
