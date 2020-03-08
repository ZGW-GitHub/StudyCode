package springboot.code.test;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author NotUpToYou
 * @date 2020/3/8 周日 14:42
 */
@Data
@Component
@ConfigurationProperties("user")
public class User {
    private String id;
    private String name;
}


