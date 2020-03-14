package springboot.code;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import springboot.code.test.SystemUser;
import springboot.code.test.Test;
import springboot.code.test.User;

/**
 * @author NotUpToYou
 * @date 2020/3/8 周日 14:41
 */
@SpringBootApplication
public class DemoApplication{

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(DemoApplication.class, args);
//        Test bean = context.getBean(Test.class);
//
//        User user = context.getBean(User.class);
//        System.out.println(user.toString());
//        SystemUser systemUser = bean.getSystemUser(user);
//        System.out.println(systemUser.toString());

        SystemUser systemUser1 = context.getBean(SystemUser.class);
        System.out.println(systemUser1.toString());


    }

}
