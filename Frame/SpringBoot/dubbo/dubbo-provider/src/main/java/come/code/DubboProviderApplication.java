package come.code;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

/**
 * @author NotUpToYou
 *
 * EnableDubbo 指定服务所在的包，从而进行扫描
 */
@SpringBootApplication
@EnableDubbo(scanBasePackages = {"come.code.dubbo.provider.serviceimpl"})
public class DubboProviderApplication {
    public static void main(String[] args) throws IOException {

        SpringApplication.run(DubboProviderApplication.class, args);

        System.in.read();

    }
}
