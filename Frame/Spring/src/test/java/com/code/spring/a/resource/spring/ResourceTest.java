package com.code.spring.a.resource.spring;

import cn.hutool.core.io.IoUtil;
import com.code.spring.MySpringApplicationTest;
import org.junit.Test;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.io.Reader;

/**
 * Spring 资源管理 : Resource 示例
 *
 * @see Resource
 * @author 愆凡
 * @date 2021/3/25 11:14
 */
public class ResourceTest extends MySpringApplicationTest {

	/**
	 * 带字符编码的 FileSystemResource 示例
	 * 
	 * @see FileSystemResource
	 * @see EncodedResource
	 * @throws IOException IOException
	 */
	@Test
	public void oneTest() throws IOException {
		String filePath = System.getProperty("user.dir") + "/src/test/java/com/code/spring/a/resource/spring/ResourceTest.java";

		FileSystemResource resource = new FileSystemResource(filePath);
		EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");

		try(Reader reader = encodedResource.getReader()) {
			System.err.println(IoUtil.read(reader));
		}
	}

}
