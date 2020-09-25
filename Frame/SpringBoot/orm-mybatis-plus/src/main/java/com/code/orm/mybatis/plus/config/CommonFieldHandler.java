package com.code.orm.mybatis.plus.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 通用字段填充
 *
 * @author 愆凡
 */
@Slf4j
@Component
public class CommonFieldHandler implements MetaObjectHandler {

	@Override
	public void insertFill(MetaObject metaObject) {
		log.info("start insert fill ....");

		this.setFieldValByName("createTime", new Date(), metaObject);
		this.setFieldValByName("lastUpdateTime", new Date(), metaObject);
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		log.info("start update fill ....");

		this.setFieldValByName("lastUpdateTime", new Date(), metaObject);
	}
}
