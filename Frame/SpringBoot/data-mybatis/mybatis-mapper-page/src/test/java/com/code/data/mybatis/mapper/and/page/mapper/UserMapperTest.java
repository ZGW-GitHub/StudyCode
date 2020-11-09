package com.code.data.mybatis.mapper.and.page.mapper;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.code.data.mybatis.mapper.and.page.DataMybatisMapperAndPageApplicationTest;
import com.code.data.mybatis.mapper.and.page.entity.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author 愆凡
 * @date 2020/8/14 5:00 下午
 */
@Slf4j
public class UserMapperTest extends DataMybatisMapperAndPageApplicationTest {

	@Autowired
	private UserMapper userMapper;

	@Before
	public void init() {
		ArrayList<User> userList = Lists.newArrayList();

		IntStream.range(1, 6).forEach(i -> {
			String salt = IdUtil.fastSimpleUUID();
			User user = User.builder().name("Mapper" + i).age(10).isActive(true).sex(User.SEX_MAN)
					.phone(SecureUtil.md5(System.currentTimeMillis() + salt)).salt(salt)
					.createTime(new Date()).lastUpdateTime(new Date()).build();
			userList.add(user);
		});

		userMapper.insertList(userList);
	}

	@After
	public void destroy() {
		userMapper.deleteAll();
	}

	@Test
	public void initTest() {
		System.out.println("init success !");
	}

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

		log.info("【 测试主键回写：user.getId() 】= {}", user.getId());
	}

	/**
	 * 测试通用Mapper - 批量保存
	 */
	@Test
	public void insertListTest() {
		List<User> userList = Lists.newArrayList();

		IntStream.range(2, 6).forEach(i -> {
			String salt = IdUtil.fastSimpleUUID();
			User user = User.builder().name("test").age(10).isActive(true).sex(User.SEX_MAN)
					.phone(SecureUtil.md5(System.currentTimeMillis() + salt)).salt(salt)
					.createTime(new Date()).lastUpdateTime(new Date()).build();
			userList.add(user);
		});

		int i = userMapper.insertList(userList);

		Assert.assertEquals(userList.size(), i);

		List<Long> ids = userList.stream().map(User::getId).collect(Collectors.toList());

		log.info("【 测试主键回写：userList.ids 】= {}", ids);
	}

	/**
	 * 测试通用Mapper - 查询全部
	 */
	@Test
	public void queryAllTest() {
		List<User> users = userMapper.selectAll();

		Assert.assertTrue(CollUtil.isNotEmpty(users));

		log.info("【 users 】= {}", users);
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

		log.info("【 userPageInfo 】= {}", userPageInfo);
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

		Assert.assertEquals(3, userPageInfo.getSize());
		Assert.assertEquals(count, userPageInfo.getTotal());

		log.info("【 userPageInfo 】= {}", userPageInfo);
	}

}
