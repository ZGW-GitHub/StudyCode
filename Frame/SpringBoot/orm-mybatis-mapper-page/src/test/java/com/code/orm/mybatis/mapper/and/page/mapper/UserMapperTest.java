package com.code.orm.mybatis.mapper.and.page.mapper;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.code.orm.mybatis.mapper.and.page.OrmMybatisMapperAndPageApplicationTest;
import com.code.orm.mybatis.mapper.and.page.entity.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zgw
 * @date 2020/8/14 5:00 下午
 */
@Slf4j
public class UserMapperTest extends OrmMybatisMapperAndPageApplicationTest {

	@Autowired
	private UserMapper userMapper;

	/**
	 * 测试通用Mapper - 保存
	 */
	@Test
	public void insertTest() {
		String salt = IdUtil.fastSimpleUUID();

		User user = User.builder().name("test").age(10).isActive(true).sex(User.SEX_MAN)
				.phone(SecureUtil.md5(System.currentTimeMillis() + salt)).salt(salt)
				.createTime(new Date()).lastUpdateTime(new Date()).build();

		userMapper.insertUseGeneratedKeys(user);
		Assert.assertNotNull(user.getId());

		log.debug("【 测试主键回写：user.getId() 】= {}", user.getId());
	}

	/**
	 * 测试通用Mapper - 批量保存
	 */
	@Test
	public void insertListTest() {
		List<User> userList = Lists.newArrayList();

		for (int i = 4; i < 10; i++) {
			String salt = IdUtil.fastSimpleUUID();
			User user = User.builder().name("test").age(10).isActive(true).sex(User.SEX_MAN)
					.phone(SecureUtil.md5(System.currentTimeMillis() + salt)).salt(salt)
					.createTime(new Date()).lastUpdateTime(new Date()).build();
			userList.add(user);
		}

		int i = userMapper.insertList(userList);

		Assert.assertEquals(userList.size(), i);

		List<Long> ids = userList.stream().map(User::getId).collect(Collectors.toList());

		log.debug("【 测试主键回写：userList.ids 】= {}", ids);
	}

	/**
	 * 测试通用Mapper - 删除
	 */
	@Test
	public void deleteTest() {
		Long primaryKey = 1L;

		int i = userMapper.deleteByPrimaryKey(primaryKey);

		Assert.assertEquals(1, i);

		User user = userMapper.selectByPrimaryKey(primaryKey);

		Assert.assertNull(user);
	}

	/**
	 * 测试通用Mapper - 更新
	 */
	@Test
	public void updateTest() {
		Long primaryKey = 2L;

		User user = userMapper.selectByPrimaryKey(primaryKey);
		user.setName("通用Mapper");

		int i = userMapper.updateByPrimaryKeySelective(user);

		Assert.assertEquals(1, i);

		User update = userMapper.selectByPrimaryKey(primaryKey);

		Assert.assertNotNull(update);
		Assert.assertEquals("通用Mapper", update.getName());

		log.debug("【 update 】= {}", update);
	}

	/**
	 * 测试通用Mapper - 查询单个
	 */
	@Test
	public void queryOneTest() {
		User user = userMapper.selectByPrimaryKey(2L);

		Assert.assertNotNull(user);

		log.debug("【 user 】= {}", user);
	}

	/**
	 * 测试通用Mapper - 查询全部
	 */
	@Test
	public void queryAllTest() {
		List<User> users = userMapper.selectAll();

		Assert.assertTrue(CollUtil.isNotEmpty(users));

		log.debug("【 users 】= {}", users);
	}

	/**
	 * 测试分页助手 - 分页排序查询
	 */
	@Test
	public void queryByPageAndSortTest() {
		int currentPage = 1;
		int pageSize = 5;
		String orderBy = "id desc";

		int count = userMapper.selectCount(null);

		// 分页
		PageHelper.startPage(currentPage, pageSize, orderBy);
		// 查询
		List<User> users = userMapper.selectAll();
		PageInfo<User> userPageInfo = new PageInfo<>(users);

		Assert.assertEquals(5, userPageInfo.getSize());
		Assert.assertEquals(count, userPageInfo.getTotal());

		log.debug("【 userPageInfo 】= {}", userPageInfo);
	}

	/**
	 * 测试通用Mapper - 条件查询
	 */
	@Test
	public void queryByConditionTest() {
		Example example = new Example(User.class);
		// 过滤
		example.createCriteria().andLike("name", "%Mapper%").orEqualTo("phone", "17300000001");
		// 排序
		example.setOrderByClause("id desc");
		int count = userMapper.selectCountByExample(example);
		// 分页
		PageHelper.startPage(1, 3);
		// 查询
		List<User> userList = userMapper.selectByExample(example);
		PageInfo<User> userPageInfo = new PageInfo<>(userList);

		Assert.assertEquals(1, userPageInfo.getSize());
		Assert.assertEquals(count, userPageInfo.getTotal());

		log.debug("【 userPageInfo 】= {}", userPageInfo);
	}

}
