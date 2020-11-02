package come.code.dubbo.provider.two;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * EnableDubbo 指定服务所在的包，从而进行扫描
 *
 * @author 愆凡
 */
@SpringBootApplication
@EnableDubbo(scanBasePackages = {"come.code.dubbo.provider.two.service"})
public class DubboProviderTwoApplication {
	public static void main(String[] args) throws InterruptedException {

		SpringApplication.run(DubboProviderTwoApplication.class, args);

		Thread.currentThread().join();

	}
}
