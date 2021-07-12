package b.factory.simple.factory;

import b.factory.simple.factory.parse.IConfigParse;
import org.junit.Test;

/**
 * @author 愆凡
 * @date 2020/9/7 10:30 上午
 */
@SuppressWarnings("all")
public class DemoTest {

	/**
	 * 加载并解析配置文件
	 *
	 * @param filePath filePath
	 */
	@Test
	public void load(String filePath) {
		String fileType = getFileType(filePath);
		IConfigParse configParse = ConfigParseFactory.createParse(fileType);

		System.err.println(configParse.doParse(filePath));
	}

	/**
	 * 根据文件路径获取文件类型
	 *
	 * @param filePath filePath
	 * @return str
	 */
	public String getFileType(String filePath) {
		return "json";
	}

}
