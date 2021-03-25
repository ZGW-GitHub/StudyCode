package com.code.spring.a.resource.spring;

import cn.hutool.core.io.IoUtil;
import com.code.spring.MySpringApplicationTest;
import org.junit.Test;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.io.Reader;
import java.util.stream.Stream;

/**
 * Spring 资源管理 : ResourceLoader 示例
 *
 * @see ResourceLoader
 * @author 愆凡
 * @date 2021/3/25 11:14
 */
public class ResourceLoaderTest extends MySpringApplicationTest {

	/**
	 * FileSystemResourceLoader 示例
	 *
	 * @see Resource
	 * @see EncodedResource
	 * @see FileSystemResourceLoader
	 * @throws IOException IOException
	 */
	@Test
	public void oneTest() throws IOException {
		String filePath = "/" + System.getProperty("user.dir") + "/src/test/java/com/code/spring/a/resource/spring/ResourceLoaderTest.java";

		FileSystemResourceLoader resourceLoader = new FileSystemResourceLoader();

		Resource resource = resourceLoader.getResource(filePath);
		EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");

		try (Reader reader = encodedResource.getReader()) {
			System.err.println(IoUtil.read(reader));
		}
	}

	/**
	 * 通配路径资源加载器示例
	 * 
	 * @see PathMatchingResourcePatternResolver
	 * @throws IOException IOException
	 */
	@Test
	public void twoTest() throws IOException {
		String fileDir = "/" + System.getProperty("user.dir") + "/src/test/java/com/code/spring/a/resource/spring/";
		String filePath = fileDir + "*.java";

		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(new FileSystemResourceLoader());

		Resource[] resources = resolver.getResources(filePath);

		Stream.of(resources).map(this::getContent).forEach(System.err::println);
	}

	private String getContent(Resource resource) {
		EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");

		try (Reader reader = encodedResource.getReader()) {
			return IoUtil.read(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

}
