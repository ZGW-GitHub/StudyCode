package com.code.orm.mybatis.mapper.and.page;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author 愆凡
 * @date 2020/8/14 4:48 下午
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.code.orm.mybatis.mapper.and.page.mapper"})
public class OrmMybatisMapperAndPageApplication {

	public static void main(String[] args) {

		SpringApplication.run(OrmMybatisMapperAndPageApplication.class, args);

	}

}
