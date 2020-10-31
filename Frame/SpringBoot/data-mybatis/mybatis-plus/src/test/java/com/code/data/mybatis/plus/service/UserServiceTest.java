package com.code.data.mybatis.plus.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.code.data.mybatis.plus.DataMybatisPlusApplicationTest;
import com.code.data.mybatis.plus.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 愆凡
 * @date 2020/8/14 6:44 下午
 */
@Slf4j
public class UserServiceTest extends DataMybatisPlusApplicationTest {

	@Autowired
	private UserService userService;

	/**
	 * 测试 Mybatis-Plus 新增
	 */
	@Test
	public void testSave() {
		String salt = IdUtil.fastSimpleUUID();

		User user = User.builder().name("test").age(10).isActive(true).sex(User.SEX_MAN)
				.phone(SecureUtil.md5(System.currentTimeMillis() + salt)).salt(salt)
				.createTime(new Date()).lastUpdateTime(new Date()).build();

		boolean save = userService.save(user);

		Assert.assertTrue(save);

		log.debug("【 测试 id 回显 #user.getId() 】= {}", user.getId());
	}

	/**
	 * 测试 Mybatis-Plus 批量新增
	 */
	@Test
	public void testSaveList() {
		List<User> userList = Lists.newArrayList();

		for (int i = 4; i < 14; i++) {
			String salt = IdUtil.fastSimpleUUID();

			User user = User.builder().name("test" + i).age(i).isActive(true).sex(User.SEX_MAN)
					.phone(SecureUtil.md5(System.currentTimeMillis() + salt)).salt(salt)
					.createTime(new Date()).lastUpdateTime(new Date()).build();

			userList.add(user);
		}

		boolean batch = userService.saveBatch(userList);
		Assert.assertTrue(batch);

		List<Long> ids = userList.stream().map(User::getId).collect(Collectors.toList());

		log.debug("【 userList#ids 】= {}", ids);
	}

	/**
	 * 测试 Mybatis-Plus 删除
	 */
	@Test
	public void testDelete() {
		boolean remove = userService.removeById(6L);

		Assert.assertTrue(remove);

		User byId = userService.getById(6L);

		Assert.assertNull(byId);
	}

	/**
	 * 测试 Mybatis-Plus 修改
	 */
	@Test
	public void testUpdate() {
		User user = userService.getById(2L);

		Assert.assertNotNull(user);

		user.setName("修改名字");

		boolean bool = userService.updateById(user);
		Assert.assertTrue(bool);

		User update = userService.getById(2L);
		Assert.assertEquals("修改名字", update.getName());

		log.debug("【 update 】= {}", update);
	}

	/**
	 * 测试 Mybatis-Plus 查询单个
	 */
	@Test
	public void testQueryOne() {
		User user = userService.getById(2L);

		Assert.assertNotNull(user);

		log.debug("【 user 】= {}", user);
	}

	/**
	 * 测试 Mybatis-Plus 查询全部
	 */
	@Test
	public void testQueryAll() {
		List<User> list = userService.list(new QueryWrapper<>());

		Assert.assertTrue(CollUtil.isNotEmpty(list));

		log.debug("【 list 】= {}", list);
	}

	/**
	 * 测试 Mybatis-Plus 分页排序查询
	 */
	@Test
	public void testQueryByPageAndSort() {
		int count = userService.count(new QueryWrapper<>());

		Page<User> userPage = new Page<>(1, 5);

		userPage.setDesc("id");

		IPage<User> page = userService.page(userPage, new QueryWrapper<>());

		Assert.assertEquals(5, page.getSize());
		Assert.assertEquals(count, page.getTotal());

		log.debug("【 page.getRecords() 】= {}", page.getRecords());
	}

	/**
	 * 测试 Mybatis-Plus 自定义查询
	 */
	@Test
	public void testQueryByCondition() {
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.like("name", "test").or().eq("age", "2").orderByDesc("id");

		int count = userService.count(wrapper);
		Page<User> userPage = new Page<>(1, 3);

		IPage<User> page = userService.page(userPage, wrapper);

		Assert.assertEquals(3, page.getSize());
		Assert.assertEquals(count, page.getTotal());

		log.debug("【 page.getRecords() 】= {}", page.getRecords());
	}

}
