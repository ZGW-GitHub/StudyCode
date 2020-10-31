package com.code.data.mybatis.plus.mapper;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.code.data.mybatis.plus.DataMybatisPlusApplicationTest;
import com.code.data.mybatis.plus.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @author 愆凡
 * @date 2020/8/14 6:43 下午
 */
@Slf4j
public class UserMapperTest extends DataMybatisPlusApplicationTest {

	/**
	 * 测试插入数据
	 */
	@Test
	public void testActiveRecordInsert() {
		User user = new User().setId(2L).selectById();

		user.setName("VIP")
				.setSalt(IdUtil.fastSimpleUUID())
				.setPhone(SecureUtil.md5(System.currentTimeMillis() + user.getSalt()));

		Assert.assertTrue(user.insert());

		// 成功直接拿回写的 ID
		log.debug("【 User 】= {}", user);
	}

	/**
	 * 测试更新数据
	 */
	@Test
	public void testActiveRecordUpdate() {
		// 根据id更新
		Assert.assertTrue(
				new User().setId(2L).setName("管理员-1").updateById()
		);

		// 根据 eq 函数中的属性值更新
		Assert.assertTrue(
				new User().update(new UpdateWrapper<User>().lambda().set(User::getName, "普通用户-1").eq(User::getId, 3))
		);
	}

	/**
	 * 测试查询数据
	 */
	@Test
	public void testActiveRecordSelect() {
		Assert.assertEquals("普通用户-1", new User().setId(2L).selectById().getName());

		User user = new User().selectOne(new QueryWrapper<User>().lambda().eq(User::getId, 2));
		Assert.assertEquals("普通用户-1", user.getName());

		List<User> Users = new User().selectAll();
		Assert.assertTrue(Users.size() > 0);

		log.debug("【 Users 】= {}", Users);
	}

	/**
	 * 测试删除数据
	 */
	@Test
	public void testActiveRecordDelete() {
		Assert.assertTrue(new User().setId(23L).deleteById());

		Assert.assertTrue(new User().delete(new QueryWrapper<User>().lambda().eq(User::getName, "test")));
	}

}
