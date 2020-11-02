package come.code.dubbo.provider;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * EnableDubbo 指定服务所在的包，从而进行扫描
 *
 * @author 愆凡
 */
@SpringBootApplication
@EnableDubbo(scanBasePackages = {"come.code.dubbo.provider.service"})
public class DubboProviderApplication {
	public static void main(String[] args) throws InterruptedException {

		SpringApplication.run(DubboProviderApplication.class, args);

		Thread.currentThread().join();

	}
}
